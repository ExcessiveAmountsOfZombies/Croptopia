package com.epherical.croptopia.register;

import com.epherical.croptopia.blocks.CroptopiaCropBlock;
import com.epherical.croptopia.items.CropItem;
import com.epherical.croptopia.items.SeedItem;
import com.epherical.croptopia.util.BlockConvertible;
import com.epherical.croptopia.util.FoodConstructor;
import com.epherical.croptopia.util.ItemConvertibleWithPlural;
import com.google.common.collect.ImmutableSet;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.epherical.croptopia.CroptopiaMod.createCropSettings;
import static com.epherical.croptopia.CroptopiaMod.createGroup;
import static com.epherical.croptopia.util.FoodConstructor.createFood;

/**
 * FarmlandCrop represents the Item, Block, and Item seed that creates a croptopia crop.
 */
public class FarmlandCrop implements ItemConvertibleWithPlural, BlockConvertible {

    private static final Set<FarmlandCrop> FARMLAND_CROPS = new HashSet<>();

    private final String name;
    private final boolean plural;
    private final TagCategory tagCategory;
    private Item cropItem;
    private Block cropBlock;
    private SeedItem seedItem;
    private final TagKey<Biome> biomes;
    private final FoodConstructor food;

    public FarmlandCrop(String cropName, boolean isPlural, TagCategory category, FoodConstructor registry, TagKey<Biome> biomes) {
        Objects.requireNonNull(category);
        this.name = cropName;
        this.plural = isPlural;
        this.tagCategory = category;
        this.food = registry;
        this.biomes = biomes;
        if (this.food == null) {
            this.cropItem = new CropItem(createGroup());
        } else {
            this.cropItem = new CropItem(createGroup().food(createFood(food)));
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
        return name;
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

    public static Set<FarmlandCrop> getFarmlandCrops() {
        return ImmutableSet.copyOf(FARMLAND_CROPS);
    }
}
