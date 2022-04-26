package com.epherical.croptopia.register.helpers;

import com.epherical.croptopia.blocks.CroptopiaSaplingBlock;
import com.epherical.croptopia.common.MiscNames;
import com.epherical.croptopia.generator.CroptopiaSaplingGenerator;
import com.epherical.croptopia.register.Content;
import com.epherical.croptopia.register.TagCategory;
import com.epherical.croptopia.util.BlockConvertible;
import com.epherical.croptopia.util.ItemConvertibleWithPlural;
import com.epherical.croptopia.util.RegisterFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
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
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.epherical.croptopia.CroptopiaMod.*;

public class Tree implements ItemConvertibleWithPlural, BlockConvertible {
    private static final List<Tree> TREES = new ArrayList<>();

    private final String name;
    private final boolean hasPlural;
    private final TagCategory tagCategory;
    private final Item item;
    private Block log;
    private Block strippedLog;
    private Block wood;
    private Block strippedWood;
    private final TagKey<Item> logItemTag;
    private final TagKey<Block> logBlockTag;
    private Block leaves;
    private final ConfiguredFeature<TreeConfiguration, ?> treeGen;
    private Holder<ConfiguredFeature<TreeConfiguration, ?>> tree;
    private final Item sapling;
    private Block saplingBlock;

    public Tree(String name, boolean hasPlural, TagCategory category, int iTreeGen, int jTreeGen, int kTreeGen) {
        Objects.requireNonNull(category);
        this.hasPlural = hasPlural;
        this.tagCategory = category;
        this.name = name;
        item = new Item(createGroup());
        // in the following we use registerItem because of AliasedBlockItem
        log = new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).sound(SoundType.WOOD).strength(2.0F));
        strippedLog = new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).sound(SoundType.WOOD).strength(2.0F));
        wood = new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).sound(SoundType.WOOD).strength(2.0F));
        strippedWood = new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).sound(SoundType.WOOD).strength(2.0F));
        // create the tags (will be filled by datagen)
        String tagName = name + "_logs";
        logItemTag = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(MiscNames.MOD_ID, tagName));
        logBlockTag = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(MiscNames.MOD_ID, tagName));
        // left is leaves and saplings
        leaves = createRegularLeavesBlock();
        treeGen = createBarkGen(iTreeGen, jTreeGen, kTreeGen, log, leaves);
        saplingBlock = new CroptopiaSaplingBlock(new CroptopiaSaplingGenerator(() -> tree), createSaplingSettings());
        sapling = new ItemNameBlockItem(saplingBlock, createGroup());
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

    public Holder<ConfiguredFeature<TreeConfiguration, ?>> getTree() {
        return tree;
    }

    public void setTree(Holder<ConfiguredFeature<TreeConfiguration, ?>> tree) {
        this.tree = tree;
    }

    @Override
    public String name() {
        return name;
    }

    public static List<Tree> copy() {
        return TREES;
    }

    public static void registerBlocks(RegisterFunction<Block> register) {
        for (Tree tree : TREES) {
            tree.log = register.register(createIdentifier(tree.name + "_log"), tree.log);
            tree.strippedLog = register.register(createIdentifier("stripped_" + tree.name + "_log"), tree.strippedLog);
            tree.wood = register.register(createIdentifier(tree.name + "_wood"), tree.wood);
            tree.strippedWood = register.register(createIdentifier("stripped_" + tree.name + "_wood"), tree.strippedWood);
            tree.leaves = register.register(createIdentifier(tree.name + "_leaves"), tree.leaves);
            leafBlocks.add(tree.leaves);
            tree.saplingBlock = register.register(createIdentifier(tree.name + "_sapling"), tree.saplingBlock);
            cropBlocks.add(tree.saplingBlock);
            tree.tree = Content.register(createIdentifier(tree.name + "_tree"), tree.getTreeGen());
        }
    }

    public static void registerItems(RegisterFunction<Item> register) {
        for (Tree tree : TREES) {
            register.register(createIdentifier(tree.name), tree.item);
            register.register(createIdentifier(tree.name + "_log"), new ItemNameBlockItem(tree.log, createGroup()));
            register.register(createIdentifier("stripped_" + tree.name + "_log"), new ItemNameBlockItem(tree.strippedLog, createGroup()));
            register.register(createIdentifier(tree.name + "_wood"), new ItemNameBlockItem(tree.wood, createGroup()));
            register.register(createIdentifier("stripped_" + tree.name + "_wood"), new ItemNameBlockItem(tree.strippedWood, createGroup()));
            register.register(createIdentifier(tree.name + "_sapling"), tree.sapling);
        }
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
