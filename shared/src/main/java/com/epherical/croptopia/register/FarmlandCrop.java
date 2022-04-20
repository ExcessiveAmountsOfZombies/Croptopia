package com.epherical.croptopia.register;

import com.epherical.croptopia.items.SeedItem;
import com.epherical.croptopia.util.BlockConvertible;
import com.epherical.croptopia.util.FoodConstructor;
import com.epherical.croptopia.util.ItemConvertibleWithPlural;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
}
