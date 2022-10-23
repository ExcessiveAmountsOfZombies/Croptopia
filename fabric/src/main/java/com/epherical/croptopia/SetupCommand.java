package com.epherical.croptopia;

import com.epherical.croptopia.common.PlatformAdapter;
import com.epherical.croptopia.register.Content;
import com.epherical.croptopia.register.helpers.FarmlandCrop;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.network.chat.Component;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.ResourceOrTagLocationArgument;
import net.minecraft.commands.arguments.blocks.BlockInput;
import net.minecraft.commands.arguments.blocks.BlockStateArgument;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.server.commands.SetBlockCommand;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.Tag;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Predicate;

public class SetupCommand {

    private static Map<FarmlandCrop, Tag.Builder> builderMap = new HashMap<>();

    private static final SimpleCommandExceptionType FAILED_EXCEPTION = new SimpleCommandExceptionType(Component.translatable("commands.setblock.failed"));

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandBuildContext) {
        dispatcher.register((Commands.literal("croptest").requires((serverCommandSource) -> {
            return serverCommandSource.hasPermission(2);
        })).then(Commands.argument("pos", BlockPosArgument.blockPos()).then((((Commands.argument("block", BlockStateArgument.block(commandBuildContext)).executes((commandContext) -> {
            return execute(commandContext.getSource(), BlockPosArgument.getLoadedBlockPos(commandContext, "pos"), BlockStateArgument.getBlock(commandContext, "block"), SetBlockCommand.Mode.REPLACE, null);
        })).then(Commands.literal("destroy").executes((commandContext) -> {
            return execute(commandContext.getSource(), BlockPosArgument.getLoadedBlockPos(commandContext, "pos"), BlockStateArgument.getBlock(commandContext, "block"), SetBlockCommand.Mode.DESTROY, null);
        }))).then(Commands.literal("keep").executes((commandContext) -> {
            return execute(commandContext.getSource(), BlockPosArgument.getLoadedBlockPos(commandContext, "pos"), BlockStateArgument.getBlock(commandContext, "block"), SetBlockCommand.Mode.REPLACE, (cachedBlockPosition) -> cachedBlockPosition.getLevel().isEmptyBlock(cachedBlockPosition.getPos()));
        }))).then(Commands.literal("replace").executes((commandContext) -> {
            return execute(commandContext.getSource(), BlockPosArgument.getLoadedBlockPos(commandContext, "pos"), BlockStateArgument.getBlock(commandContext, "block"), SetBlockCommand.Mode.REPLACE, null);
        })))));

        dispatcher.register(Commands.literal("croptopia")
                .requires(stack -> stack.hasPermission(4))
                .then(Commands.literal("addcrop")
                        .then(Commands.argument("location", StringArgumentType.string())
                                .suggests((context, builder) -> {
                                    for (Category value : Category.values()) {
                                        builder.suggest(value.name().toLowerCase(Locale.ROOT));
                                    }
                                    return builder.buildFuture();
                                })
                                .then(Commands.argument("biome", ResourceOrTagLocationArgument.resourceOrTag(Registry.BIOME_REGISTRY))
                                        .executes(context -> {
                                            Category location = Category.valueOf(StringArgumentType.getString(context, "location").toUpperCase(Locale.ROOT));
                                            ResourceOrTagLocationArgument.Result<Biome> biome = ResourceOrTagLocationArgument.getBiome(context, "biome");
                                            for (FarmlandCrop crop : location.crops) {
                                                if (builderMap.containsKey(crop)) {
                                                    Tag.Builder builder = builderMap.get(crop);
                                                    builder.addOptionalElement(new ResourceLocation(biome.asPrintable()), "croptopia");
                                                } else {
                                                    Tag.Builder builder = new Tag.Builder();
                                                    builder.addOptionalElement(new ResourceLocation(biome.asPrintable()), "croptopia");
                                                    builderMap.put(crop, builder);
                                                }
                                            }
                                            return 1;
                                        }))))
                .then(Commands.literal("export")
                        .executes(context -> {
                            Path croptopia = FabricLoader.getInstance().getConfigDir().resolve("croptopia");
                            for (Map.Entry<FarmlandCrop, Tag.Builder> entry : builderMap.entrySet()) {
                                Block block = entry.getKey().asBlock();

                                Path crop = croptopia.resolve(Registry.BLOCK.getKey(block).getPath() + ".json");
                                try {
                                    Files.writeString(crop, entry.getValue().serializeToJson().toString());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            builderMap.clear();
                            return 1;
                        })));
    }

    private static int execute(CommandSourceStack source, BlockPos pos, BlockInput block, SetBlockCommand.Mode mode, @Nullable Predicate<BlockInWorld> condition) throws CommandSyntaxException {
        ServerLevel serverWorld = source.getLevel();
        if (condition != null && !condition.test(new BlockInWorld(serverWorld, pos, true))) {
            throw FAILED_EXCEPTION.create();
        } else {

            ServerLevel world = source.getLevel();
            world.setBlock(pos, block.getState(), 2);
            world.setBlock(pos.offset(1, 0, 0), block.getState().setValue(BlockStateProperties.AGE_7, 1), 2);
            world.setBlock(pos.offset(2, 0, 0), block.getState().setValue(BlockStateProperties.AGE_7, 5), 2);
            world.setBlock(pos.offset(3, 0, 0), block.getState().setValue(BlockStateProperties.AGE_7, 7), 2);

            serverWorld.blockUpdated(pos, block.getState().getBlock());
            source.sendSuccess(Component.translatable("commands.setblock.success", pos.getX(), pos.getY(), pos.getZ()), true);
            return 1;
        }
    }

    public enum Category {
        SWAMP(
                Content.ARTICHOKE,
                Content.ASPARAGUS,
                Content.CRANBERRY,
                Content.CURRANT
        ),
        PLAINS(

                Content.BELLPEPPER,
                Content.BROCCOLI,
                Content.CABBAGE,
                Content.CORN,
                Content.CUCUMBER,
                Content.GREENBEAN,
                Content.KIWI,
                Content.LETTUCE,
                Content.STRAWBERRY,
                Content.MUSTARD,
                Content.PEPPER,
                Content.OAT,
                Content.BARLEY,
                Content.SOYBEAN,
                Content.PEPPER

        ),
        FOREST(
                Content.BLACKBEAN,
                Content.BLACKBERRY,
                Content.BLUEBERRY,
                Content.CANTALOUPE,
                Content.CAULIFLOWER,
                Content.CELERY,
                Content.ELDERBERRY,
                Content.GRAPE,
                Content.RADISH,
                Content.RASPBERRY,
                Content.SPINACH,
                Content.STRAWBERRY,
                Content.TOMATILLO,
                Content.TOMATO,
                Content.TEA_LEAVES
        ),
        JUNGLE(
                Content.COFFEE_BEANS,
                Content.EGGPLANT,
                Content.GARLIC,
                Content.GREENONION,
                Content.HONEYDEW,
                Content.ONION,
                Content.PEANUT,
                Content.PINEAPPLE,
                Content.RHUBARB,
                Content.RICE,
                Content.TURNIP,
                Content.BASIL,
                Content.VANILLA
        ),
        SAVANNA(
                Content.HOPS,
                Content.KIWI,
                Content.LEEK,
                Content.OLIVE,
                Content.RUTABAGA,
                Content.SQUASH,
                Content.YAM,
                Content.ZUCCHINI,
                Content.TURMERIC,
                Content.GINGER
        ),
        DESERT(
                Content.SAGUARO
        ),
        WINTER_FOREST(
                Content.SQUASH,
                Content.ONION,
                Content.BLACKBERRY
        ),
        WINTER(
                Content.SQUASH,
                Content.CORN,
                Content.YAM,
                Content.SWEETPOTATO
        ),
        MOUNTAIN_NO_SNOW(
                Content.BLACKBERRY,
                Content.BLUEBERRY,
                Content.STRAWBERRY
        ),
        MAGICAL(
                Content.RICE,
                Content.GREENONION,
                Content.VANILLA
        ),
        BARREN(
                Content.PEPPER
        );

        private final FarmlandCrop[] crops;

        Category(FarmlandCrop... crops) {
            this.crops = crops;
        }

        public FarmlandCrop[] getCrops() {
            return crops;
        }
    }
}
