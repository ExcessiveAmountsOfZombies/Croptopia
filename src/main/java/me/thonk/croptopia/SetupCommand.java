package me.thonk.croptopia;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.block.CropBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.pattern.CachedBlockPosition;
import net.minecraft.command.argument.BlockPosArgumentType;
import net.minecraft.command.argument.BlockStateArgument;
import net.minecraft.command.argument.BlockStateArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.command.SetBlockCommand;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Clearable;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class SetupCommand {


    private static final SimpleCommandExceptionType FAILED_EXCEPTION = new SimpleCommandExceptionType(new TranslatableText("commands.setblock.failed"));

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register((CommandManager.literal("croptest").requires((serverCommandSource) -> {
            return serverCommandSource.hasPermissionLevel(2);
        })).then(CommandManager.argument("pos", BlockPosArgumentType.blockPos()).then((((CommandManager.argument("block", BlockStateArgumentType.blockState()).executes((commandContext) -> {
            return execute(commandContext.getSource(), BlockPosArgumentType.getLoadedBlockPos(commandContext, "pos"), BlockStateArgumentType.getBlockState(commandContext, "block"), SetBlockCommand.Mode.REPLACE, null);
        })).then(CommandManager.literal("destroy").executes((commandContext) -> {
            return execute(commandContext.getSource(), BlockPosArgumentType.getLoadedBlockPos(commandContext, "pos"), BlockStateArgumentType.getBlockState(commandContext, "block"), SetBlockCommand.Mode.DESTROY, null);
        }))).then(CommandManager.literal("keep").executes((commandContext) -> {
            return execute(commandContext.getSource(), BlockPosArgumentType.getLoadedBlockPos(commandContext, "pos"), BlockStateArgumentType.getBlockState(commandContext, "block"), SetBlockCommand.Mode.REPLACE, (cachedBlockPosition) -> cachedBlockPosition.getWorld().isAir(cachedBlockPosition.getBlockPos()));
        }))).then(CommandManager.literal("replace").executes((commandContext) -> {
            return execute(commandContext.getSource(), BlockPosArgumentType.getLoadedBlockPos(commandContext, "pos"), BlockStateArgumentType.getBlockState(commandContext, "block"), SetBlockCommand.Mode.REPLACE, null);
        })))));
    }

    private static int execute(ServerCommandSource source, BlockPos pos, BlockStateArgument block, SetBlockCommand.Mode mode, @Nullable Predicate<CachedBlockPosition> condition) throws CommandSyntaxException {
        ServerWorld serverWorld = source.getWorld();
        if (condition != null && !condition.test(new CachedBlockPosition(serverWorld, pos, true))) {
            throw FAILED_EXCEPTION.create();
        } else {

            ServerWorld world = source.getWorld();
            world.setBlockState(pos, block.getBlockState(), 2);
            world.setBlockState(pos.add(1, 0, 0), block.getBlockState().with(Properties.AGE_7, 1), 2);
            world.setBlockState(pos.add(2, 0, 0), block.getBlockState().with(Properties.AGE_7, 5), 2);
            world.setBlockState(pos.add(3, 0, 0), block.getBlockState().with(Properties.AGE_7, 7), 2);

            serverWorld.updateNeighbors(pos, block.getBlockState().getBlock());
            source.sendFeedback(new TranslatableText("commands.setblock.success", pos.getX(), pos.getY(), pos.getZ()), true);
            return 1;
        }
    }

    public interface Filter {
        @Nullable
        BlockStateArgument filter(BlockBox box, BlockPos pos, BlockStateArgument block, ServerWorld world);
    }

    public static enum Mode {
        REPLACE,
        DESTROY;

        private Mode() {
        }
    }
}
