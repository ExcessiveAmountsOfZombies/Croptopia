package me.thonk.croptopia;

import me.thonk.croptopia.blocks.BlockRegistry;
import me.thonk.croptopia.blocks.CroptopiaCropBlock;
import me.thonk.croptopia.blocks.CroptopiaLeafBlock;
import me.thonk.croptopia.blocks.LeavesRegistry;
import me.thonk.croptopia.items.CropLootTableModifier;
import me.thonk.croptopia.items.CroptopiaSeedItem;
import me.thonk.croptopia.items.ItemRegistry;
import me.thonk.croptopia.recipe.DamageDurabilitySerializer;
import me.thonk.croptopia.table.BiomeLootCondition;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;

import java.util.ArrayList;


public class Croptopia implements ModInitializer {

    public static ArrayList<Block> cropBlocks = new ArrayList<>();
    public static ArrayList<Block> leafBlocks = new ArrayList<>();
    private static ArrayList<Item> seeds = new ArrayList<>();

    public static final String MOD_ID = "croptopia";
    public static final ItemGroup CROPTOPIA_ITEM_GROUP = FabricItemGroupBuilder.create(new Identifier(MOD_ID, "croptopia"))
            .icon(() -> new ItemStack(ItemRegistry.onion))
            .build();
    public static final LootConditionType BIOME_CHECK =  registerLootCondition("biome_check", new BiomeLootCondition.Serializer());
    public static final DamageDurabilitySerializer DAMAGE_DURABILITY =
            registerSerializer("crafting_damage_durability", new DamageDurabilitySerializer());


    // TODO MAKE SALT ORE

    @Override
    public void onInitialize() {
        LeavesRegistry.init();
        BlockRegistry.init();
        ItemRegistry.init();
        CropLootTableModifier.init();


        CommandRegistrationCallback.EVENT.register((commandDispatcher, b) -> {
            SetupCommand.register(commandDispatcher);
        });
    }

    public static Identifier createIdentifier(String name) {
        //System.out.println("\"" + MOD_ID + ":" + name + "\",");
        return new Identifier(MOD_ID, name);
    }

    public static LootConditionType registerLootCondition(String id, JsonSerializer<? extends LootCondition> serializer) {
        return Registry.register(Registry.LOOT_CONDITION_TYPE, new Identifier(id), new LootConditionType(serializer));
    }

    public static <S extends RecipeSerializer<T>, T extends Recipe<?>> S registerSerializer(String id, S serializer) {
        return Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(MOD_ID, id), serializer);
    }

    public static Item registerItem(String itemName, Item item) {
        Registry.register(Registry.ITEM, Croptopia.createIdentifier(itemName), item);
        if (item instanceof AliasedBlockItem) {
            ((AliasedBlockItem) item).appendBlocks(Item.BLOCK_ITEMS, item);
        }

        if (item instanceof CroptopiaSeedItem) {
            CroptopiaCropBlock block = (CroptopiaCropBlock) ((CroptopiaSeedItem) item).getBlock();
            block.setSeedsItem(item);
        }

        // \bregisterItem\b..[A-Z]\w+",
        //System.out.println( "\"" + itemName + "\",");
        if (item instanceof CroptopiaSeedItem) {
            seeds.add(item);
        }
        return item;
    }

    public static Item.Settings createGroup() {
        return new Item.Settings().group(CROPTOPIA_ITEM_GROUP);
    }

    public static Block registerBlock(String blockName, Block item) {
        cropBlocks.add(item);

        if (item instanceof CroptopiaLeafBlock) {
            leafBlocks.add(item);
            //System.out.println("\"" + blockName + "\",");
        } else {
            //System.out.println("\"" + blockName + "\",");
        }
        // Debug sout for easy json writing.
        Registry.register(Registry.BLOCK, Croptopia.createIdentifier(blockName), item);
        return item;
    }

    public static FabricBlockSettings createCropSettings() {
        return FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP);
    }

    public static FabricBlockSettings createSaplingSettings() {
        return FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS);
    }

    public static CroptopiaLeafBlock createLeavesBlock() {
        return new CroptopiaLeafBlock(FabricBlockSettings.of(Material.LEAVES).strength(0.2F).ticksRandomly().sounds(BlockSoundGroup.GRASS).nonOpaque().allowsSpawning(Croptopia::canSpawnOnLeaves).suffocates(Croptopia::never).blockVision(Croptopia::never));
    }

    private static Boolean canSpawnOnLeaves(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        return type == EntityType.OCELOT || type == EntityType.PARROT;
    }

    private static boolean never(BlockState state, BlockView world, BlockPos pos) {
        return false;
    }


    public static ArrayList<Item> getSeeds() {
        return seeds;
    }
}
