package com.epherical.croptopia.register.helpers;

import com.epherical.croptopia.CroptopiaMod;
import com.epherical.croptopia.blocks.CroptopiaCropBlock;
import com.epherical.croptopia.common.ItemNamesV2;
import com.epherical.croptopia.items.CropItem;
import com.epherical.croptopia.items.SeedItem;
import com.epherical.croptopia.register.TagCategory;
import com.epherical.croptopia.util.BlockConvertible;
import com.epherical.croptopia.util.FoodConstructor;
import com.epherical.croptopia.util.ItemConvertibleWithPlural;
import com.epherical.croptopia.util.RegisterFunction;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.epherical.croptopia.CroptopiaMod.*;
import static com.epherical.croptopia.util.FoodConstructor.createFood;

/**
 * FarmlandCrop represents the Item, Block, and Item seed that creates a croptopia crop.
 */
public class FarmlandCrop implements ItemConvertibleWithPlural, BlockConvertible {

    private static final List<FarmlandCrop> FARMLAND_CROPS = new ArrayList<>();

    private final String name;
    private final String dropName;
    private final boolean plural;
    private final TagCategory tagCategory;
    private final Item cropItem;
    private final Block cropBlock;
    private final SeedItem seedItem;
    private final Biome.Category biomes; // todo implement

    public FarmlandCrop(String cropName, boolean isPlural, TagCategory category, FoodConstructor registry, Biome.Category biomes) {
        this(cropName, cropName, isPlural, category, registry, biomes);
    }

    public FarmlandCrop(String cropName, String dropName, boolean isPlural, TagCategory category, FoodConstructor registry, Biome.Category biomes) {
        Objects.requireNonNull(category);
        this.name = cropName;
        this.dropName = dropName;
        this.plural = isPlural;
        this.tagCategory = category;
        this.biomes = biomes;
        if (registry == null) {
            this.cropItem = new CropItem(createGroup());
        } else {
            this.cropItem = new CropItem(createGroup().food(createFood(registry)));
        }
        cropBlock = new CroptopiaCropBlock(createCropSettings());
        seedItem = new SeedItem(cropBlock, createGroup(), biomes);
        FARMLAND_CROPS.add(this);
    }

    @Override
    public Block asBlock() {
        return cropBlock;
    }

    @Override
    public String name() {
        return dropName;
    }

    @Override
    public boolean hasPlural() {
        return plural;
    }

    @Override
    public Item asItem() {
        return cropItem;
    }

    public TagCategory getTagCategory() {
        return tagCategory;
    }

    public SeedItem getSeedItem() {
        return seedItem;
    }

    public static List<FarmlandCrop> copy() {
        return FARMLAND_CROPS;
    }

    public static void registerBlocks(RegisterFunction<Block> register) {
        for (FarmlandCrop farmlandCrop : FARMLAND_CROPS) {
            register.register(createIdentifier(farmlandCrop.name + "_crop"), farmlandCrop.asBlock());
            CroptopiaMod.cropBlocks.add(farmlandCrop.asBlock());
        }
    }

    public static void registerItems(RegisterFunction<Item> register) {
        for (FarmlandCrop farmlandCrop : FARMLAND_CROPS) {
            register.register(createIdentifier(farmlandCrop.dropName), farmlandCrop.asItem());
            if (farmlandCrop.name().equals(ItemNamesV2.VANILLA)) {
                register.register(createIdentifier(farmlandCrop.name + "_seeds"), farmlandCrop.seedItem);
            } else {
                register.register(createIdentifier(farmlandCrop.name + "_seed"), farmlandCrop.seedItem);
            }
            CroptopiaMod.cropItems.add(farmlandCrop.asItem());
            CroptopiaMod.seeds.add(farmlandCrop.seedItem);
        }
    }
}
