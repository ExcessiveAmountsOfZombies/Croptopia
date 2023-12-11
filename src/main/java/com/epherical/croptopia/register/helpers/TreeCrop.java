package com.epherical.croptopia.register.helpers;

import com.epherical.croptopia.CroptopiaMod;
import com.epherical.croptopia.blocks.CroptopiaSaplingBlock;
import com.epherical.croptopia.blocks.LeafCropBlock;
import com.epherical.croptopia.common.ItemNamesV2;
import com.epherical.croptopia.items.CropItem;
import com.epherical.croptopia.items.CroptopiaSaplingItem;
import com.epherical.croptopia.register.Content;
import com.epherical.croptopia.register.TagCategory;
import com.epherical.croptopia.util.BlockConvertible;
import com.epherical.croptopia.util.FoodConstructor;
import com.epherical.croptopia.util.ItemConvertibleWithPlural;
import com.epherical.croptopia.util.RegisterFunction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.epherical.croptopia.CroptopiaMod.*;
import static com.epherical.croptopia.CroptopiaMod.createGroup;
import static com.epherical.croptopia.util.FoodConstructor.createFood;

public class TreeCrop implements ItemConvertibleWithPlural, BlockConvertible {

    public static final List<TreeCrop> TREE_CROPS = new ArrayList<>();

    private final String name;
    private final boolean isPlural;
    private final TagCategory category;
    private Item item;
    private Block leaves;

    private Block leafType;

    private ConfiguredFeature<TreeConfiguration, ?> treeConfig;
    private Item saplingItem;
    private Block saplingBlock;

    private final FoodConstructor constructor;

    private final ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureKey;
    private final ResourceKey<PlacedFeature> placedFeatureKey;

    public TreeCrop(String name, boolean plural, Block logType, Block leafType, TagCategory category, FoodConstructor constructor, int base, int randA, int randB,
                    ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureKey, ResourceKey<PlacedFeature> placedFeatureKey) {
        Objects.requireNonNull(leafType);
        Objects.requireNonNull(category);
        Objects.requireNonNull(logType);
        // TERRIBLE CODE DESIGN
        Content.BLOCK_REGISTER.reg(register -> {
            registerBlock(register);
            treeConfig = createTreeGen(base, randA, randB, logType, leafType, leaves);
        });
        Content.ITEM_REGISTER.reg(this::registerItem);
        // TERRIBLE CODE DESIGN
        this.configuredFeatureKey = configuredFeatureKey;
        this.placedFeatureKey = placedFeatureKey;
        this.name = name;
        this.isPlural = plural;
        this.category = category;
        this.constructor = constructor;
        this.leafType = leafType;
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

    public ConfiguredFeature<TreeConfiguration, ?> getTreeConfig() {
        return treeConfig;
    }

    public Block getSaplingBlock() {
        return saplingBlock;
    }

    public Item getSaplingItem() {
        return saplingItem;
    }

    public Block getLeaves() {
        return leaves;
    }

    public TagCategory getTagCategory() {
        return category;
    }

    public ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeatureKey() {
        return configuredFeatureKey;
    }

    public ResourceKey<PlacedFeature> getPlacedFeatureKey() {
        return placedFeatureKey;
    }

    /*public static void registerBlocks(RegisterFunction<Block> register) {
        for (TreeCrop treeCrop : TREE_CROPS) {
            register.register(createIdentifier(treeCrop.name() + "_crop"), treeCrop.asBlock());
            cropBlocks.add(treeCrop.asBlock());
            cropBlocks.add(treeCrop.saplingBlock);
            leafBlocks.add(treeCrop.asBlock());
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
    }*/

    public void registerItem(RegisterFunction<Item> register) {
        if (!Objects.equals(name(), ItemNamesV2.APPLE)) {
            item = register.register(createIdentifier(name()), () -> new CropItem(createGroup().food(createFood(constructor))));
            CroptopiaMod.cropItems.add(asItem());
        } else {
            item = Items.APPLE;
        }
        saplingItem = register.register(createIdentifier(name() + "_sapling"), () -> new CroptopiaSaplingItem(saplingBlock, leaves, leafType, createGroup()));
    }

    public void registerBlock(RegisterFunction<Block> register) {
        TreeGrower grower = new TreeGrower("croptopia_" + name, 0.1f, Optional.empty(), Optional.empty(), Optional.of(configuredFeatureKey), Optional.empty(), Optional.empty(), Optional.empty());
        saplingBlock = register.register(createIdentifier(name() + "_sapling"), () -> new CroptopiaSaplingBlock(grower, createSaplingSettings()));
        leaves = register.register(createIdentifier(name() + "_crop"), CroptopiaMod::createLeavesBlock);

        cropBlocks.add(asBlock());
        cropBlocks.add(saplingBlock);
        leafBlocks.add(asBlock());
    }

    public static List<TreeCrop> copy() {
        return TREE_CROPS;
    }

    public static ConfiguredFeature<TreeConfiguration, ?> createTreeGen(int i, int j, int k, Block logType, Block leafType, Block leafCrop) {
        return new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                SimpleStateProvider.simple(logType.defaultBlockState()),
                new StraightTrunkPlacer(i, j, k),
                new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(leafType.defaultBlockState(), 90).add(leafCrop.defaultBlockState().setValue(LeafCropBlock.AGE, 3), 20).build()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 2)).ignoreVines().build());
    }
}
