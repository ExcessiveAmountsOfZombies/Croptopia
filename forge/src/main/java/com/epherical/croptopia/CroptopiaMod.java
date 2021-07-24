package com.epherical.croptopia;

import com.epherical.croptopia.blocks.LeafCropBlock;
import com.epherical.croptopia.common.MiscNames;
import com.epherical.croptopia.common.PlatformAdapter;
import com.epherical.croptopia.items.SeedItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;

public class CroptopiaMod {
    public static ArrayList<Item> cropItems = new ArrayList<>();
    public static ArrayList<Block> cropBlocks = new ArrayList<>();
    public static ArrayList<Block> leafBlocks = new ArrayList<>();
    public static ArrayList<SeedItem> seeds = new ArrayList<>();

    private static CroptopiaMod mod;
    private PlatformAdapter<?> platform;

    public CroptopiaMod(PlatformAdapter<?> platform) {
        this.platform = platform;
        mod = this;
    }

    public static CroptopiaMod getInstance() {
        return mod;
    }

    public static Item.Properties createGroup() {
        return new Item.Properties().tab(mod.platform.getTab());
    }

    public static ResourceLocation createIdentifier(String name) {
        return new ResourceLocation(MiscNames.MOD_ID, name);
    }

    public static BlockBehaviour.Properties createCropSettings() {
        return BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP);
    }

    public static LeafCropBlock createLeavesBlock() {
        return new LeafCropBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(CroptopiaMod::canSpawnOnLeaves).isSuffocating((a,b,c) -> false).isViewBlocking((a,b,c) -> false));
    }

    public static LeavesBlock createRegularLeavesBlock() {
        return new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(CroptopiaMod::canSpawnOnLeaves).isSuffocating(CroptopiaMod::never).isViewBlocking(CroptopiaMod::never));
    }

    public static BlockBehaviour.Properties createSaplingSettings() {
        return BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS);
    }

    private static boolean never(BlockState state, BlockGetter world, BlockPos pos) {
        return false;
    }

    public static boolean canSpawnOnLeaves(BlockState state, BlockGetter world, BlockPos pos, EntityType<?> type) {
        return type == EntityType.OCELOT || type == EntityType.PARROT;
    }
}
