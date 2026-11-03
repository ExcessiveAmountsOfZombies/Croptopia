package com.epherical.croptopia;


import com.epherical.croptopia.blocks.LeafCropBlock;
import com.epherical.croptopia.common.MiscNames;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TintedParticleLeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

import java.util.ArrayList;

public class CroptopiaCommon {


    public static ArrayList<Item> cropItems = new ArrayList<>();
    public static ArrayList<Block> cropBlocks = new ArrayList<>();
    public static ArrayList<Block> leafBlocks = new ArrayList<>();
    public static ArrayList<Item> seeds = new ArrayList<>();


    public static void init() {

    }

    public static Item.Properties createDrink(Identifier id) {

        return new Item.Properties()
                .component(DataComponents.CONSUMABLE, Consumables.DEFAULT_DRINK)
                .usingConvertsTo(Items.GLASS_BOTTLE)
                .setId(ResourceKey.create(Registries.ITEM, id));
    }

    public static Item.Properties createSoup(Identifier id) {
        return new Item.Properties()
                .usingConvertsTo(Items.BOWL)
                .setId(ResourceKey.create(Registries.ITEM, id));
    }

    public static Item.Properties createGroup(Identifier id) {
        return new Item.Properties().setId(ResourceKey.create(Registries.ITEM, id));
    }

    public static Identifier createIdentifier(String name) {
        return Identifier.fromNamespaceAndPath(MiscNames.MOD_ID, name);
    }

    public static BlockBehaviour.Properties createCropSettings(Identifier id) {
        return BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollision().randomTicks().instabreak().sound(SoundType.CROP)
                .setId(ResourceKey.create(Registries.BLOCK, id));
    }

    public static LeafCropBlock createLeavesBlock(Identifier id) {
        return new LeafCropBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.2F).ignitedByLava().randomTicks()
                .sound(SoundType.GRASS).noOcclusion().isValidSpawn(CroptopiaCommon::canSpawnOnLeaves)
                .isSuffocating((a, b, c) -> false).isViewBlocking((state, level, pos, box) -> false)
                .setId(ResourceKey.create(Registries.BLOCK, id)));
    }

    public static LeavesBlock createRegularLeavesBlock(Identifier id) {
        return new TintedParticleLeavesBlock(0.01F, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.2F)
                .ignitedByLava().randomTicks().sound(SoundType.GRASS).noOcclusion()
                .isValidSpawn(CroptopiaCommon::canSpawnOnLeaves).isSuffocating(CroptopiaCommon::never).isViewBlocking((state, level, pos, box) -> false)
                .setId(ResourceKey.create(Registries.BLOCK, id)));
    }

    public static BlockBehaviour.Properties createSaplingSettings(Identifier id) {
        return BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollision().randomTicks().instabreak().sound(SoundType.GRASS)
                .setId(ResourceKey.create(Registries.BLOCK, id));
    }

    private static boolean never(BlockState state, BlockGetter world, BlockPos pos) {
        return false;
    }

    public static boolean canSpawnOnLeaves(BlockState state, BlockGetter world, BlockPos pos, EntityType<?> type) {
        return type == EntityTypes.OCELOT || type == EntityTypes.PARROT;
    }
}
