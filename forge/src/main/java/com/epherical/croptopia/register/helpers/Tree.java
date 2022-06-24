package com.epherical.croptopia.register.helpers;

import com.epherical.croptopia.blocks.CroptopiaSaplingBlock;
import com.epherical.croptopia.common.MiscNames;
import com.epherical.croptopia.generator.CroptopiaSaplingGenerator;
import com.epherical.croptopia.register.Content;
import com.epherical.croptopia.register.TagCategory;
import com.epherical.croptopia.util.BlockConvertible;
import com.epherical.croptopia.util.ItemConvertibleWithPlural;
import com.epherical.croptopia.util.RegisterFunction;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

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
    private Block leaves;
    //private final ConfiguredFeature<TreeConfiguration, ?> treeGen;
    //private Holder<ConfiguredFeature<TreeConfiguration, ?>> tree;
    private final Item sapling;
    private Block saplingBlock;

    public Tree(String name, boolean hasPlural, TagCategory category, Supplier<ConfiguredFeature<BaseTreeFeatureConfig, ?>> supplier) {
        Objects.requireNonNull(category);
        this.hasPlural = hasPlural;
        this.tagCategory = category;
        this.name = name;
        item = new Item(createGroup());
        // in the following we use registerItem because of AliasedBlockItem
        log = new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).sound(SoundType.WOOD).strength(2.0F));
        strippedLog = new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).sound(SoundType.WOOD).strength(2.0F));
        wood = new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).sound(SoundType.WOOD).strength(2.0F));
        strippedWood = new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).sound(SoundType.WOOD).strength(2.0F));
        // left is leaves and saplings
        leaves = createRegularLeavesBlock();
        //treeGen = createBarkGen(iTreeGen, jTreeGen, kTreeGen, log, leaves);
        saplingBlock = new CroptopiaSaplingBlock(new CroptopiaSaplingGenerator(supplier), createSaplingSettings());
        sapling = new BlockNamedItem(saplingBlock, createGroup());
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

    public Block getLeaves() {
        return leaves;
    }

    public Item getSapling() {
        return sapling;
    }

    public Block getSaplingBlock() {
        return saplingBlock;
    }

    /*public ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeGen() {
        return treeGen;
    }*/

    /*public Holder<ConfiguredFeature<BaseTreeFeatureConfig, ?>> getTree() {
        return tree;
    }*/

   /* public void setTree(Holder<ConfiguredFeature<BaseTreeFeatureConfig, ?>> tree) {
        this.tree = tree;
    }*/

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
            //tree.tree = Content.register(createIdentifier(tree.name + "_tree"), tree.getTreeGen());
        }
    }

    public static void registerItems(RegisterFunction<Item> register) {
        for (Tree tree : TREES) {
            register.register(createIdentifier(tree.name), tree.item);
            register.register(createIdentifier(tree.name + "_log"), new BlockNamedItem(tree.log, createGroup()));
            register.register(createIdentifier("stripped_" + tree.name + "_log"), new BlockNamedItem(tree.strippedLog, createGroup()));
            register.register(createIdentifier(tree.name + "_wood"), new BlockNamedItem(tree.wood, createGroup()));
            register.register(createIdentifier("stripped_" + tree.name + "_wood"), new BlockNamedItem(tree.strippedWood, createGroup()));
            register.register(createIdentifier(tree.name + "_sapling"), tree.sapling);
        }
    }

    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> createBarkGen(int i, int j, int k, Block log, Block leaves) {
        return Feature.TREE.configured(new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(log.defaultBlockState()),
                new WeightedBlockStateProvider().add(leaves.defaultBlockState(), 90),
                new BlobFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), 3),
                new StraightTrunkPlacer(i, j, k),
                new TwoLayerFeature(1, 0, 2)).ignoreVines().build());
    }

    public static void attemptPop(BlockState state, ItemUseContext context, BlockPos pos) {
        for (Tree crop : TREES) {
            if (state.getBlock().equals(crop.getLog()) || state.getBlock().equals(crop.getWood())) {
                Block.popResource(context.getLevel(), pos, new ItemStack(crop.asItem()));
            }
        }
    }
}
