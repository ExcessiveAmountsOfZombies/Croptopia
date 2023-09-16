package com.epherical.croptopia.register.helpers;

import com.epherical.croptopia.CroptopiaMod;
import com.epherical.croptopia.blocks.CroptopiaSaplingBlock;
import com.epherical.croptopia.common.ItemNamesV2;
import com.epherical.croptopia.common.MiscNames;
import com.epherical.croptopia.generator.CroptopiaSaplingGenerator;
import com.epherical.croptopia.items.CropItem;
import com.epherical.croptopia.items.CroptopiaSaplingItem;
import com.epherical.croptopia.register.Content;
import com.epherical.croptopia.register.TagCategory;
import com.epherical.croptopia.util.BlockConvertible;
import com.epherical.croptopia.util.ItemConvertibleWithPlural;
import com.epherical.croptopia.util.RegisterFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
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
import net.minecraft.world.level.material.MapColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.epherical.croptopia.CroptopiaMod.*;
import static com.epherical.croptopia.util.FoodConstructor.createFood;

public class Tree implements ItemConvertibleWithPlural, BlockConvertible {
    private static final List<Tree> TREES = new ArrayList<>();

    private final String name;
    private final boolean hasPlural;
    private final TagCategory tagCategory;
    private Item item;
    private Block log;
    private Block strippedLog;
    private Block wood;
    private Block strippedWood;
    private final TagKey<Item> logItemTag;
    private final TagKey<Block> logBlockTag;
    private Block leaves;
    private ConfiguredFeature<TreeConfiguration, ?> treeGen;
    private Item sapling;
    private Block saplingBlock;
    private final ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureKey;
    private final ResourceKey<PlacedFeature> placedFeatureKey;

    public Tree(String name, boolean hasPlural, TagCategory category, int iTreeGen, int jTreeGen, int kTreeGen,
                ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureKey, ResourceKey<PlacedFeature> placedFeatureKey) {
        Objects.requireNonNull(category);
        // TERRIBLE CODE DESIGN
        Content.BLOCK_REGISTER.reg(register -> {
            registerBlock(register);
            treeGen = createBarkGen(iTreeGen, jTreeGen, kTreeGen, log, leaves);
        });
        Content.ITEM_REGISTER.reg(this::registerItem);
        // TERRIBLE CODE DESIGN
        this.configuredFeatureKey = configuredFeatureKey;
        this.placedFeatureKey = placedFeatureKey;
        this.hasPlural = hasPlural;
        this.tagCategory = category;
        this.name = name;

        // in the following we use registerItem because of AliasedBlockItem
        //log = new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).ignitedByLava().sound(SoundType.WOOD).strength(2.0F));
        //strippedLog = new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).ignitedByLava().sound(SoundType.WOOD).strength(2.0F));
        //wood = new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).ignitedByLava().sound(SoundType.WOOD).strength(2.0F));
        //strippedWood = new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).ignitedByLava().sound(SoundType.WOOD).strength(2.0F));
        // create the tags (will be filled by datagen)
        String tagName = name + "_logs";
        logItemTag = TagKey.create(Registries.ITEM, new ResourceLocation(MiscNames.MOD_ID, tagName));
        logBlockTag = TagKey.create(Registries.BLOCK, new ResourceLocation(MiscNames.MOD_ID, tagName));
        // left is leaves and saplings
        //leaves = createRegularLeavesBlock();
        //saplingBlock = new CroptopiaSaplingBlock(new CroptopiaSaplingGenerator(() -> configuredFeatureKey), createSaplingSettings().ignitedByLava());
        //sapling = new ItemNameBlockItem(saplingBlock, createGroup());
        TREES.add(this);
    }

    @Override
    public boolean hasPlural() {
        return hasPlural;
    }

    public TagCategory getTagCategory() {
        return tagCategory;
    }

    @Override
    public Item asItem() {
        return item;
    }

    @Override
    public Block asBlock() {
        return log;
    }

    public Block getLog() {
        return log;
    }

    public Block getStrippedLog() {
        return strippedLog;
    }

    public Block getWood() {
        return wood;
    }

    public Block getStrippedWood() {
        return strippedWood;
    }

    public TagKey<Item> getLogItemTag() {
        return logItemTag;
    }

    public TagKey<Block> getLogBlockTag() {
        return logBlockTag;
    }

    public Block getLeaves() {
        return leaves;
    }

    public Item getSapling() {
        return sapling;
    }

    public Block getSaplingBlock() {
        return saplingBlock;
    }

    public ConfiguredFeature<TreeConfiguration, ?> getTreeGen() {
        return treeGen;
    }

    public ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeatureKey() {
        return configuredFeatureKey;
    }

    public ResourceKey<PlacedFeature> getPlacedFeatureKey() {
        return placedFeatureKey;
    }

    @Override
    public String name() {
        return name;
    }

    public static List<Tree> copy() {
        return TREES;
    }

    /*public static void registerBlocks(RegisterFunction<Block> register) {
        for (Tree tree : TREES) {
            tree.log = register.register(createIdentifier(tree.name + "_log"), tree.log);
            tree.strippedLog = register.register(createIdentifier("stripped_" + tree.name + "_log"), tree.strippedLog);
            tree.wood = register.register(createIdentifier(tree.name + "_wood"), tree.wood);
            tree.strippedWood = register.register(createIdentifier("stripped_" + tree.name + "_wood"), tree.strippedWood);
            tree.leaves = register.register(createIdentifier(tree.name + "_leaves"), tree.leaves);
            leafBlocks.add(tree.leaves);
            tree.saplingBlock = register.register(createIdentifier(tree.name + "_sapling"), tree.saplingBlock);
            cropBlocks.add(tree.saplingBlock);
        }
    }*/

    /*public static void registerItems(RegisterFunction<Item> register) {
        for (Tree tree : TREES) {
            register.register(createIdentifier(tree.name), tree.item);
            register.register(createIdentifier(tree.name + "_log"), new ItemNameBlockItem(tree.log, createGroup()));
            register.register(createIdentifier("stripped_" + tree.name + "_log"), new ItemNameBlockItem(tree.strippedLog, createGroup()));
            register.register(createIdentifier(tree.name + "_wood"), new ItemNameBlockItem(tree.wood, createGroup()));
            register.register(createIdentifier("stripped_" + tree.name + "_wood"), new ItemNameBlockItem(tree.strippedWood, createGroup()));
            register.register(createIdentifier(tree.name + "_sapling"), tree.sapling);
        }
    }*/

    public void registerItem(RegisterFunction<Item> register) {
        item = register.register(createIdentifier(name), () -> new Item(createGroup()));
        register.register(createIdentifier(name + "_log"), () -> new ItemNameBlockItem(log, createGroup()));
        register.register(createIdentifier("stripped_" + name + "_log"), () -> new ItemNameBlockItem(strippedLog, createGroup()));
        register.register(createIdentifier(name + "_wood"), () -> new ItemNameBlockItem(wood, createGroup()));
        register.register(createIdentifier("stripped_" + name + "_wood"), () -> new ItemNameBlockItem(strippedWood, createGroup()));
        sapling = register.register(createIdentifier(name + "_sapling"), () -> new ItemNameBlockItem(saplingBlock, createGroup()));
    }

    public void registerBlock(RegisterFunction<Block> register) {
        log = register.register(createIdentifier(name + "_log"), () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).ignitedByLava().sound(SoundType.WOOD).strength(2.0F)));
        strippedLog = register.register(createIdentifier("stripped_" + name + "_log"), () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).ignitedByLava().sound(SoundType.WOOD).strength(2.0F)));
        wood = register.register(createIdentifier(name + "_wood"), () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).ignitedByLava().sound(SoundType.WOOD).strength(2.0F)));
        strippedWood = register.register(createIdentifier("stripped_" + name + "_wood"), () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).ignitedByLava().sound(SoundType.WOOD).strength(2.0F)));
        leaves = register.register(createIdentifier(name + "_leaves"), CroptopiaMod::createRegularLeavesBlock);
        saplingBlock = register.register(createIdentifier(name + "_sapling"), () -> new CroptopiaSaplingBlock(new CroptopiaSaplingGenerator(() -> configuredFeatureKey), createSaplingSettings().ignitedByLava()));
        leafBlocks.add(leaves);
        cropBlocks.add(saplingBlock);
    }

    public static ConfiguredFeature<TreeConfiguration, ?> createBarkGen(int i, int j, int k, Block log, Block leaves) {
        return new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                SimpleStateProvider.simple(log.defaultBlockState()),
                new StraightTrunkPlacer(i, j, k),
                new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(leaves.defaultBlockState(), 90).build()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 2)).ignoreVines().build());
    }

    public static void attemptPop(BlockState state, UseOnContext context, BlockPos pos) {
        for (Tree crop : TREES) {
            if (state.getBlock().equals(crop.getLog()) || state.getBlock().equals(crop.getWood())) {
                Block.popResource(context.getLevel(), pos, new ItemStack(crop.asItem()));
            }
        }
    }
}
