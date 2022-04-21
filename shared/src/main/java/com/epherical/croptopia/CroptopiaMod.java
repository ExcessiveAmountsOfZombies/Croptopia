package com.epherical.croptopia;

import com.epherical.croptopia.common.MiscNames;
import com.epherical.croptopia.common.PlatformAdapter;
import com.epherical.croptopia.items.SeedItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

import java.util.ArrayList;

public record CroptopiaMod(PlatformAdapter<?> platform) {
    public static ArrayList<Item> cropItems = new ArrayList<>();
    public static ArrayList<Block> cropBlocks = new ArrayList<>();
    public static ArrayList<SeedItem> seeds = new ArrayList<>();

    private static CroptopiaMod mod;

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
}
