package com.epherical.croptopia.register.helpers;

import com.epherical.croptopia.CroptopiaMod;
import com.epherical.croptopia.blocks.CroptopiaCropBlock;
import com.epherical.croptopia.common.ItemNamesV2;
import com.epherical.croptopia.items.CropItem;
import com.epherical.croptopia.items.SeedItem;
import com.epherical.croptopia.register.Content;
import com.epherical.croptopia.register.TagCategory;
import com.epherical.croptopia.util.BlockConvertible;
import com.epherical.croptopia.util.FoodConstructor;
import com.epherical.croptopia.util.ItemConvertibleWithPlural;
import com.epherical.croptopia.util.RegisterFunction;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.epherical.croptopia.CroptopiaMod.*;
import static com.epherical.croptopia.util.FoodConstructor.createFood;

/**
 * FarmlandCrop represents the Item, Block, and Item seed that creates a croptopia crop.
 */
public class FarmlandCrop implements ItemConvertibleWithPlural, BlockConvertible {

    public static final List<FarmlandCrop> FARMLAND_CROPS = new ArrayList<>();

    private final String name;
    private final String dropName;
    private final boolean plural;
    private final TagCategory tagCategory;

    private final FoodConstructor registry;

    private Item cropItem;
    private Block cropBlock;
    private Item seedItem;
    private final TagKey<Biome> biomes; // todo implement

    public FarmlandCrop(String cropName, boolean isPlural, TagCategory category, FoodConstructor registry, TagKey<Biome> biomes) {
        this(cropName, cropName, isPlural, category, registry, biomes);
    }

    public FarmlandCrop(String cropName, String dropName, boolean isPlural, TagCategory category, FoodConstructor registry, TagKey<Biome> biomes) {
        Objects.requireNonNull(category);
        // TERRIBLE CODE DESIGN
        Content.BLOCK_REGISTER.reg(this::registerBlock);
        Content.ITEM_REGISTER.reg(this::registerItem);
        // TERRIBLE CODE DESIGN
        this.name = cropName;
        this.dropName = dropName;
        this.plural = isPlural;
        this.tagCategory = category;
        this.biomes = biomes;
        this.registry = registry;
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

    public Item getSeedItem() {
        return seedItem;
    }

    public static List<FarmlandCrop> copy() {
        return FARMLAND_CROPS;
    }

    /*public static void registerBlocks(RegisterFunction<Block> register) {
        for (FarmlandCrop farmlandCrop : FARMLAND_CROPS) {
            register.register(createIdentifier(farmlandCrop.name + "_crop"), farmlandCrop.asBlock());
            CroptopiaMod.cropBlocks.add(farmlandCrop.asBlock());
        }
    }*/

    public void registerBlock(RegisterFunction<Block> register) {
        this.cropBlock = register.register(createIdentifier(this.name + "_crop"), () -> new CroptopiaCropBlock(createCropSettings()));
        CroptopiaMod.cropBlocks.add(this.asBlock());
    }

    /*public static void registerItems(RegisterFunction<Item> register) {
        for (FarmlandCrop farmlandCrop : FARMLAND_CROPS) {
            register.register(createIdentifier(farmlandCrop.dropName), () ->  farmlandCrop.asItem());
            if (farmlandCrop.name().equals(ItemNamesV2.VANILLA)) {
                register.register(createIdentifier(farmlandCrop.name + "_seeds"), () ->  farmlandCrop.seedItem);
            } else {
                register.register(createIdentifier(farmlandCrop.name + "_seed"), () ->  farmlandCrop.seedItem);
            }
            CroptopiaMod.cropItems.add(farmlandCrop.asItem());
            CroptopiaMod.seeds.add(farmlandCrop.seedItem);
        }
    }*/

    public void registerItem(RegisterFunction<Item> register) {
        this.cropItem = register.register(createIdentifier(this.dropName), () -> {
            if (registry == null) {
                return new CropItem(createGroup());
            } else {
                return new CropItem(createGroup().food(createFood(registry)));
            }
        });
        if (this.name().equals(ItemNamesV2.VANILLA)) {
            this.seedItem = register.register(createIdentifier(this.name + "_seeds"), () -> new SeedItem(cropBlock, createGroup(), biomes));
        } else {
            this.seedItem = register.register(createIdentifier(this.name + "_seed"), () -> new SeedItem(cropBlock, createGroup(), biomes));
        }
        cropItems.add(this.asItem());
        seeds.add(this.seedItem);
    }

}
