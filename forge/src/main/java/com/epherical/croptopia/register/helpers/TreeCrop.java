package com.epherical.croptopia.register.helpers;

import com.epherical.croptopia.CroptopiaMod;
import com.epherical.croptopia.blocks.CroptopiaSaplingBlock;
import com.epherical.croptopia.blocks.LeafCropBlock;
import com.epherical.croptopia.common.ItemNamesV2;
import com.epherical.croptopia.generator.CroptopiaSaplingGenerator;
import com.epherical.croptopia.items.CropItem;
import com.epherical.croptopia.items.CroptopiaSaplingItem;
import com.epherical.croptopia.register.Content;
import com.epherical.croptopia.register.TagCategory;
import com.epherical.croptopia.util.BlockConvertible;
import com.epherical.croptopia.util.FoodConstructor;
import com.epherical.croptopia.util.ItemConvertibleWithPlural;
import com.epherical.croptopia.util.RegisterFunction;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

import static com.epherical.croptopia.CroptopiaMod.*;
import static com.epherical.croptopia.CroptopiaMod.createGroup;
import static com.epherical.croptopia.CroptopiaMod.createLeavesBlock;
import static com.epherical.croptopia.util.FoodConstructor.createFood;

public class TreeCrop implements ItemConvertibleWithPlural, BlockConvertible {

    private static final List<TreeCrop> TREE_CROPS = new ArrayList<>();

    private final String name;
    private final boolean isPlural;
    private final TagCategory category;
    private final Item item;
    private final Block leaves;
    //private Holder<ConfiguredFeature<TreeConfiguration, ?>> tree;
    //private final ConfiguredFeature<TreeConfiguration, ?> treeConfig;
    private final CroptopiaSaplingItem saplingItem;
    private final CroptopiaSaplingBlock saplingBlock;

    public TreeCrop(String name, boolean plural, Block logType, Block leafType, TagCategory category, FoodConstructor constructor, Supplier<ConfiguredFeature<BaseTreeFeatureConfig, ?>> tree) {
        Objects.requireNonNull(leafType);
        Objects.requireNonNull(category);
        Objects.requireNonNull(logType);
        this.name = name;
        this.isPlural = plural;
        this.category = category;
        if (constructor == null) {
            item = Items.APPLE;
        } else {
            item = new CropItem(createGroup().food(createFood(constructor)));
        }
        leaves = createLeavesBlock();
        //treeConfig = createTreeGen(base, randA, randB, logType, leafType, leaves);
        saplingBlock = new CroptopiaSaplingBlock(new CroptopiaSaplingGenerator(tree), createSaplingSettings());
        saplingItem = new CroptopiaSaplingItem(saplingBlock, leaves, leafType, createGroup());
        TREE_CROPS.add(this);
    }

    /**
     * @return The crop block associated with a tree crop
     */
    @Override
    public Block asBlock() {
        return leaves;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public boolean hasPlural() {
        return isPlural;
    }

    /**
     * @return The item product of the tree crop.
     */
    @Override
    public Item asItem() {
        return item;
    }

    /*public void setTree(Holder<ConfiguredFeature<TreeConfiguration, ?>> tree) {
        this.tree = tree;
    }

    protected ConfiguredFeature<TreeConfiguration, ?> getTreeConfig() {
        return treeConfig;
    }

    public Holder<ConfiguredFeature<TreeConfiguration, ?>> getTree() {
        return tree;
    }*/

    public CroptopiaSaplingBlock getSaplingBlock() {
        return saplingBlock;
    }

    public CroptopiaSaplingItem getSaplingItem() {
        return saplingItem;
    }

    public Block getLeaves() {
        return leaves;
    }

    public TagCategory getTagCategory() {
        return category;
    }

    public static List<TreeCrop> copy() {
        return TREE_CROPS;
    }

    public static void registerBlocks(RegisterFunction<Block> register) {
        for (TreeCrop treeCrop : TREE_CROPS) {
            register.register(createIdentifier(treeCrop.name() + "_crop"), treeCrop.asBlock());
            cropBlocks.add(treeCrop.asBlock());
            cropBlocks.add(treeCrop.saplingBlock);
            leafBlocks.add(treeCrop.asBlock());
            //treeCrop.tree = Content.register(createIdentifier(treeCrop.name() + "_tree"), treeCrop.getTreeConfig());
            register.register(createIdentifier(treeCrop.name() + "_sapling"), treeCrop.getSaplingBlock());
        }
    }

    public static void registerItems(RegisterFunction<Item> register) {
        for (TreeCrop treeCrop : TREE_CROPS) {
            if (!Objects.equals(treeCrop.name(), ItemNamesV2.APPLE)) {
                register.register(createIdentifier(treeCrop.name()), treeCrop.asItem());
                CroptopiaMod.cropItems.add(treeCrop.asItem());
            }
            register.register(createIdentifier(treeCrop.name() + "_sapling"), treeCrop.getSaplingItem());
        }
    }
}
