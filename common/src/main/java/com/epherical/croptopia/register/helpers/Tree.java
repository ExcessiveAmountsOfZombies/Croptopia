package com.epherical.croptopia.register.helpers;

import com.epherical.croptopia.CroptopiaCommon;
import com.epherical.croptopia.blocks.CroptopiaSaplingBlock;
import com.epherical.croptopia.common.MiscNames;
import com.epherical.croptopia.register.Content;
import com.epherical.croptopia.register.TagCategory;
import com.epherical.croptopia.util.BlockConvertible;
import com.epherical.croptopia.util.ItemConvertibleWithPlural;
import com.epherical.croptopia.util.RegisterFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.epherical.croptopia.CroptopiaCommon.*;

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
    private Item leavesItem;
    private TreeFeature treeGen;
    private Item sapling;
    private Block saplingBlock;
    private final ResourceKey<Feature> configuredFeatureKey;
    private final ResourceKey<PlacedFeature> placedFeatureKey;

    public Tree(String name, boolean hasPlural, TagCategory category, int iTreeGen, int jTreeGen, int kTreeGen,
                ResourceKey<Feature> configuredFeatureKey, ResourceKey<PlacedFeature> placedFeatureKey) {
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
        logItemTag = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MiscNames.MOD_ID, tagName));
        logBlockTag = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MiscNames.MOD_ID, tagName));
        // left is leaves and saplings
        //leaves = createRegularLeavesBlock();
        //saplingBlock = new CroptopiaSaplingBlock(new CroptopiaSaplingGenerator((id) -> configuredFeatureKey), createSaplingSettings().ignitedByLava());
        //sapling = new ItemNameBlockItem(saplingBlock, createGroup(id));
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

    public Item getLeavesItem() {
        return leavesItem;
    }

    public Item getSapling() {
        return sapling;
    }

    public Block getSaplingBlock() {
        return saplingBlock;
    }

    public TreeFeature getTreeGen() {
        return treeGen;
    }

    public ResourceKey<Feature> getConfiguredFeatureKey() {
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
            register.register(createIdentifier(tree.name + "_log"), new ItemNameBlockItem(tree.log, createGroup(id)));
            register.register(createIdentifier("stripped_" + tree.name + "_log"), new ItemNameBlockItem(tree.strippedLog, createGroup(id)));
            register.register(createIdentifier(tree.name + "_wood"), new ItemNameBlockItem(tree.wood, createGroup(id)));
            register.register(createIdentifier("stripped_" + tree.name + "_wood"), new ItemNameBlockItem(tree.strippedWood, createGroup(id)));
            register.register(createIdentifier(tree.name + "_sapling"), tree.sapling);
        }
    }*/

    public void registerItem(RegisterFunction<Item> register) {
        item = register.register(createIdentifier(name), (id) -> new Item(createGroup(id).compostable(ContextIntProviders.COMPOSTABLE_MEDIUM)));
        leavesItem = register.register(createIdentifier(name + "_leaves"), (id) -> new BlockItem(leaves, createGroup(id)));
        register.register(createIdentifier(name + "_log"), (id) -> new BlockItem(log, createGroup(id).useBlockDescriptionPrefix()));
        register.register(createIdentifier("stripped_" + name + "_log"), (id) -> new BlockItem(strippedLog, createGroup(id).useBlockDescriptionPrefix()));
        register.register(createIdentifier(name + "_wood"), (id) -> new BlockItem(wood, createGroup(id).useBlockDescriptionPrefix()));
        register.register(createIdentifier("stripped_" + name + "_wood"), (id) -> new BlockItem(strippedWood, createGroup(id).useBlockDescriptionPrefix()));
        sapling = register.register(createIdentifier(name + "_sapling"), (id) -> new BlockItem(saplingBlock, createGroup(id).compostable(ContextIntProviders.COMPOSTABLE_LOW_MEDIUM).useBlockDescriptionPrefix()));
    }

    public void registerBlock(RegisterFunction<Block> register) {
        log = register.register(createIdentifier(name + "_log"), (id) -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).ignitedByLava().sound(SoundType.WOOD).strength(2.0F)
                .setId(ResourceKey.create(Registries.BLOCK, id))));
        strippedLog = register.register(createIdentifier("stripped_" + name + "_log"), (id) -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).ignitedByLava().sound(SoundType.WOOD).strength(2.0F)
                .setId(ResourceKey.create(Registries.BLOCK, id))));
        wood = register.register(createIdentifier(name + "_wood"), (id) -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).ignitedByLava().sound(SoundType.WOOD).strength(2.0F)
                .setId(ResourceKey.create(Registries.BLOCK, id))));
        strippedWood = register.register(createIdentifier("stripped_" + name + "_wood"), (id) -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).ignitedByLava().sound(SoundType.WOOD).strength(2.0F)
                .setId(ResourceKey.create(Registries.BLOCK, id))));
        leaves = register.register(createIdentifier(name + "_leaves"), CroptopiaCommon::createRegularLeavesBlock);
        saplingBlock = register.register(createIdentifier(name + "_sapling"), (id) -> new CroptopiaSaplingBlock(createTree(configuredFeatureKey), createSaplingSettings(id).ignitedByLava()));
        leafBlocks.add(leaves);
        cropBlocks.add(saplingBlock);
    }

    private static TreeGrower createTree(ResourceKey<Feature> key) {
        return new TreeGrower(key.identifier().toString(), WeightedList.of(key), WeightedList.of(), WeightedList.of(), key);
    }

    public static TreeFeature createBarkGen(int i, int j, int k, Block log, Block leaves) {
        return new TreeFeature.Builder(
                new SimpleStateProvider(log.defaultBlockState()),
                new StraightTrunkPlacer(i, j, k),
                new WeightedStateProvider(WeightedList.<BlockState>builder().add(leaves.defaultBlockState(), 90).build()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 2),
                Holder.direct(RuleBasedStateProvider.ifTrueThenProvide(
                        BlockPredicate.not(BlockPredicate.matchesTag(BlockTags.CANNOT_REPLACE_BELOW_TREE_TRUNK)),
                        Blocks.DIRT))).ignoreVines().build();
    }

    public static void attemptPop(BlockState state, UseOnContext context, BlockPos pos) {
        for (Tree crop : TREES) {
            if (state.getBlock().equals(crop.getLog()) || state.getBlock().equals(crop.getWood())) {
                Block.popResource(context.getLevel(), pos, new ItemStack(crop.asItem()));
            }
        }
    }
}
