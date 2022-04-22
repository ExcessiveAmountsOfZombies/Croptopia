package com.epherical.croptopia.register;

import com.epherical.croptopia.CroptopiaMod;
import com.epherical.croptopia.blocks.CroptopiaSaplingBlock;
import com.epherical.croptopia.common.MiscNames;
import com.epherical.croptopia.generator.CroptopiaSaplingGenerator;
import com.epherical.croptopia.util.BlockConvertible;
import com.epherical.croptopia.util.ItemConvertibleWithPlural;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import java.util.Objects;

import static com.epherical.croptopia.CroptopiaMod.createGroup;

public class Tree implements ItemConvertibleWithPlural, BlockConvertible {
    /*private final String name;
    private final boolean hasPlural;
    private final TagCategory tagegory;
    private final Item item;
    private final Block log;
    private final Block strippedLog;
    private final Block wood;
    private final Block strippedWood;
    private final TagKey<Item> logItemTag;
    private final TagKey<Block> logBlockTag;
    private final Block leaves;
    private final Holder<ConfiguredFeature<TreeConfiguration, ?>> treeGen;
    private final Item sapling;
    private final Block saplingBlock;*/

    public Tree(String name, boolean hasPlural, TagCategory tagegory, int iTreeGen, int jTreeGen, int kTreeGen) {
        /*Objects.requireNonNull(tagegory);
        this.hasPlural = hasPlural;
        this.tagegory = tagegory;
        name = name;
        item = new Item(createGroup());
        Registry.register(Registry.ITEM, CroptopiaMod.createIdentifier(lowerCaseName), item);
        // in the following we use registerItem because of AliasedBlockItem
        log = new RotatedPillarBlock(FabricBlockSettings.of(Material.WOOD, MaterialColor.COLOR_BROWN).sound(SoundType.WOOD).strength(2.0F));
        registerBlock(lowerCaseName + "_log", log);
        registerItem(lowerCaseName + "_log", new ItemNameBlockItem(log, createGroup()));
        strippedLog = new RotatedPillarBlock(FabricBlockSettings.of(Material.WOOD, MaterialColor.COLOR_BROWN).sound(SoundType.WOOD).strength(2.0F));
        registerBlock("stripped_" + lowerCaseName + "_log", strippedLog);
        registerItem("stripped_" + lowerCaseName + "_log", new ItemNameBlockItem(strippedLog, createGroup()));
        wood = new RotatedPillarBlock(FabricBlockSettings.of(Material.WOOD, MaterialColor.COLOR_BROWN).sound(SoundType.WOOD).strength(2.0F));
        registerBlock(lowerCaseName + "_wood", wood);
        registerItem(lowerCaseName + "_wood", new ItemNameBlockItem(wood, createGroup()));
        strippedWood = new RotatedPillarBlock(FabricBlockSettings.of(Material.WOOD, MaterialColor.COLOR_BROWN).sound(SoundType.WOOD).strength(2.0F));
        registerBlock("stripped_" + lowerCaseName + "_wood", strippedWood);
        registerItem("stripped_" + lowerCaseName + "_wood", new ItemNameBlockItem(strippedWood, createGroup()));
        // create the tags (will be filled by datagen)
        String tagName = lowerCaseName + "_logs";
        logItemTag = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(MiscNames.MOD_ID, tagName));
        logBlockTag = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(MiscNames.MOD_ID, tagName));
        // left is leaves and saplings
        leaves = createRegularLeavesBlock();
        registerBlock(lowerCaseName + "_leaves", leaves);
        treeGen = createBarkGen(lowerCaseName + "_tree", iTreeGen, jTreeGen, kTreeGen, log, leaves);
        saplingBlock = new CroptopiaSaplingBlock(new CroptopiaSaplingGenerator(() -> treeGen), Content.createSaplingSettings());
        registerBlock(lowerCaseName + "_sapling", saplingBlock);
        sapling = new ItemNameBlockItem(saplingBlock, createGroup());
        registerItem(lowerCaseName + "_sapling", sapling);*/
    }

    @Override
    public Block asBlock() {
        return null;
    }

    @Override
    public String name() {
        return name();
    }

    @Override
    public boolean hasPlural() {
        return false;
    }

    @Override
    public Item asItem() {
        return null;
    }
}
