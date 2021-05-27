package me.thonk.croptopia.registry;

import com.google.common.collect.ImmutableList;
import me.thonk.common.FeatureNames;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.decorator.CountExtraDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.trunk.ForkingTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.HashMap;
import java.util.Map;

import static me.thonk.croptopia.Croptopia.createIdentifier;

public class GeneratorRegistry {

    private static Map<String, RegistryKey<ConfiguredFeature<?, ?>>> keyMap = new HashMap<>();
    private static Map<RegistryKey<ConfiguredFeature<?, ?>>, ConfiguredFeature<?, ?>> featureMap = new HashMap<>();

    public static final ConfiguredFeature<TreeFeatureConfig, ?> APPLE_TREE = register(createIdentifier(FeatureNames.APPLE_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 95).add(LeavesRegistry.appleCrop.getDefaultState(), 5).build()),
                    new SimpleBlockStateProvider(BlockRegistry.appleSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> BANANA_TREE = register(createIdentifier(FeatureNames.BANANA_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 8, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.getDefaultState(), 95).add(LeavesRegistry.bananaCrop.getDefaultState(), 5).build()),
                    new SimpleBlockStateProvider(BlockRegistry.bananaSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));


    public static final ConfiguredFeature<TreeFeatureConfig, ?> ORANGE_TREE = register(createIdentifier(FeatureNames.ORANGE_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 4, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 95).add(LeavesRegistry.orangeCrop.getDefaultState(), 5).build()),
                    new SimpleBlockStateProvider(BlockRegistry.orangeSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> PERSIMMON_TREE = register(createIdentifier(FeatureNames.PERSIMMON_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 95).add(LeavesRegistry.persimmonCrop.getDefaultState(), 5).build()),
                    new SimpleBlockStateProvider(BlockRegistry.persimmonSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> PLUM_TREE = register(createIdentifier(FeatureNames.PLUM_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 95).add(LeavesRegistry.plumCrop.getDefaultState(), 5).build()),
                    new SimpleBlockStateProvider(BlockRegistry.plumSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> CHERRY_TREE = register(createIdentifier(FeatureNames.CHERRY_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 95).add(LeavesRegistry.cherryCrop.getDefaultState(), 5).build()),
                    new SimpleBlockStateProvider(BlockRegistry.cherrySaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> LEMON_TREE = register(createIdentifier(FeatureNames.LEMON_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 95).add(LeavesRegistry.lemonCrop.getDefaultState(), 5).build()),
                    new SimpleBlockStateProvider(BlockRegistry.lemonSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> GRAPEFRUIT_TREE = register(createIdentifier(FeatureNames.GRAPEFRUIT_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 8, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.getDefaultState(), 95).add(LeavesRegistry.grapefruitCrop.getDefaultState(), 5).build()),
                    new SimpleBlockStateProvider(BlockRegistry.grapefruitSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> KUMQUAT_TREE = register(createIdentifier(FeatureNames.KUMQUAT_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 8, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.getDefaultState(), 95).add(LeavesRegistry.kumquatCrop.getDefaultState(), 5).build()),
                    new SimpleBlockStateProvider(BlockRegistry.kumquatSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> PEACH_TREE = register(createIdentifier(FeatureNames.PEACH_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 95).add(LeavesRegistry.peachCrop.getDefaultState(), 5).build()),
                    new SimpleBlockStateProvider(BlockRegistry.peachSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> COCONUT_TREE = register(createIdentifier(FeatureNames.COCONUT_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.getDefaultState()),
                    new ForkingTrunkPlacer(5, 2, 3),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.getDefaultState(), 95).add(LeavesRegistry.coconutCrop.getDefaultState(), 5).build()),
                    new SimpleBlockStateProvider(BlockRegistry.coconutSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 1),
                    new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> NUTMEG_TREE = register(createIdentifier(FeatureNames.NUTMEG_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 8, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.getDefaultState(), 95).add(LeavesRegistry.nutmegCrop.getDefaultState(), 5).build()),
                    new SimpleBlockStateProvider(BlockRegistry.nutmegSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> FIG_TREE = register(createIdentifier(FeatureNames.FIG_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 8, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.getDefaultState(), 95).add(LeavesRegistry.figCrop.getDefaultState(), 5).build()),
                    new SimpleBlockStateProvider(BlockRegistry.figSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> NECTARINE_TREE = register(createIdentifier(FeatureNames.NECTARINE_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 4, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 95).add(LeavesRegistry.nectarineCrop.getDefaultState(), 5).build()),
                    new SimpleBlockStateProvider(BlockRegistry.nectarineSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> MANGO_TREE = register(createIdentifier(FeatureNames.MANGO_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 8, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.getDefaultState(), 95).add(LeavesRegistry.mangoCrop.getDefaultState(), 5).build()),
                    new SimpleBlockStateProvider(BlockRegistry.mangoSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> DRAGON_FRUIT_TREE = register(createIdentifier(FeatureNames.DRAGON_FRUIT_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 7, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 95).add(LeavesRegistry.dragonFruitCrop.getDefaultState(), 5).build()),
                    new SimpleBlockStateProvider(BlockRegistry.dragonFruitSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> STAR_FRUIT_TREE = register(createIdentifier(FeatureNames.STAR_FRUIT_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 95).add(LeavesRegistry.starFruitCrop.getDefaultState(), 5).build()),
                    new SimpleBlockStateProvider(BlockRegistry.starFruitSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> AVOCADO_TREE = register(createIdentifier(FeatureNames.AVOCADO_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.SPRUCE_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 95).add(LeavesRegistry.avocadoCrop.getDefaultState(), 5).build()),
                    new SimpleBlockStateProvider(BlockRegistry.avocadoSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> APRICOT_TREE = register(createIdentifier(FeatureNames.APRICOT_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 2, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 95).add(LeavesRegistry.apricotCrop.getDefaultState(), 5).build()),
                    new SimpleBlockStateProvider(BlockRegistry.apricotSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> PEAR_TREE = register(createIdentifier(FeatureNames.PEAR_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 2, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 95).add(LeavesRegistry.pearCrop.getDefaultState(), 5).build()),
                    new SimpleBlockStateProvider(BlockRegistry.pearSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> LIME_TREE = register(createIdentifier(FeatureNames.LIME_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 2, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 95).add(LeavesRegistry.limeCrop.getDefaultState(), 5).build()),
                    new SimpleBlockStateProvider(BlockRegistry.limeSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> DATE_TREE = register(createIdentifier(FeatureNames.DATE_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 8, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.getDefaultState(), 95).add(LeavesRegistry.dateCrop.getDefaultState(), 5).build()),
                    new SimpleBlockStateProvider(BlockRegistry.dateSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> ALMOND_TREE = register(createIdentifier(FeatureNames.ALMOND_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.DARK_OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.DARK_OAK_LEAVES.getDefaultState(), 95).add(LeavesRegistry.almondCrop.getDefaultState(), 5).build()),
                    new SimpleBlockStateProvider(BlockRegistry.almondSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> CASHEW_TREE = register(createIdentifier(FeatureNames.CASHEW_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.DARK_OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.DARK_OAK_LEAVES.getDefaultState(), 95).add(LeavesRegistry.cashewCrop.getDefaultState(), 5).build()),
                    new SimpleBlockStateProvider(BlockRegistry.cashewSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> PECAN_TREE = register(createIdentifier(FeatureNames.PECAN_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.DARK_OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.DARK_OAK_LEAVES.getDefaultState(), 95).add(LeavesRegistry.pecanCrop.getDefaultState(), 5).build()),
                    new SimpleBlockStateProvider(BlockRegistry.pecanSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> WALNUT_TREE = register(createIdentifier(FeatureNames.WALNUT_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.DARK_OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.DARK_OAK_LEAVES.getDefaultState(), 95).add(LeavesRegistry.walnutCrop.getDefaultState(), 5).build()),
                    new SimpleBlockStateProvider(BlockRegistry.walnutSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> CINNAMON_TREE = register(createIdentifier(FeatureNames.CINNAMON_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(BlockRegistry.cinnamonLog.getDefaultState()),
                    new StraightTrunkPlacer(4, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(LeavesRegistry.cinnamonLeaves.getDefaultState(), 95).build()),
                    new SimpleBlockStateProvider(BlockRegistry.cinnamonSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> APPLE_TREE_CONFIGURED = register(createIdentifier(FeatureNames.APPLE_TREE_CONFIGURED),
            APPLE_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> BANANA_TREE_CONFIGURED = register(createIdentifier(FeatureNames.BANANA_TREE_CONFIGURED),
            BANANA_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> ORANGE_TREE_CONFIGURED = register(createIdentifier(FeatureNames.ORANGE_TREE_CONFIGURED),
            ORANGE_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> PERSIMMON_TREE_CONFIGURED = register(createIdentifier(FeatureNames.PERSIMMON_TREE_CONFIGURED),
            PERSIMMON_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> PLUM_TREE_CONFIGURED = register(createIdentifier(FeatureNames.PLUM_TREE_CONFIGURED),
            PLUM_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> CHERRY_TREE_CONFIGURED = register(createIdentifier(FeatureNames.CHERRY_TREE_CONFIGURED),
            CHERRY_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> LEMON_TREE_CONFIGURED = register(createIdentifier(FeatureNames.LEMON_TREE_CONFIGURED),
            LEMON_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> GRAPEFRUIT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.GRAPEFRUIT_TREE_CONFIGURED),
            GRAPEFRUIT_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> KUMQUAT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.KUMQUAT_TREE_CONFIGURED),
            KUMQUAT_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> PEACH_TREE_CONFIGURED = register(createIdentifier(FeatureNames.PEACH_TREE_CONFIGURED),
            PEACH_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> COCONUT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.COCONUT_TREE_CONFIGURED),
            COCONUT_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> NUTMEG_TREE_CONFIGURED = register(createIdentifier(FeatureNames.NUTMEG_TREE_CONFIGURED),
            NUTMEG_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> FIG_TREE_CONFIGURED = register(createIdentifier(FeatureNames.FIG_TREE_CONFIGURED),
            FIG_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> NECTARINE_TREE_CONFIGURED = register(createIdentifier(FeatureNames.NECTARINE_TREE_CONFIGURED),
            NECTARINE_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> MANGO_TREE_CONFIGURED = register(createIdentifier(FeatureNames.MANGO_TREE_CONFIGURED),
            MANGO_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> DRAGON_FRUIT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.DRAGON_FRUIT_TREE_CONFIGURED),
            DRAGON_FRUIT_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> STAR_FRUIT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.STAR_FRUIT_TREE_CONFIGURED),
            STAR_FRUIT_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> AVOCADO_TREE_CONFIGURED = register(createIdentifier(FeatureNames.AVOCADO_TREE_CONFIGURED),
            AVOCADO_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> APRICOT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.APRICOT_TREE_CONFIGURED),
            APRICOT_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> PEAR_TREE_CONFIGURED = register(createIdentifier(FeatureNames.PEAR_TREE_CONFIGURED),
            PEAR_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> LIME_TREE_CONFIGURED = register(createIdentifier(FeatureNames.LIME_TREE_CONFIGURED),
            LIME_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> DATE_TREE_CONFIGURED = register(createIdentifier(FeatureNames.DATE_TREE_CONFIGURED),
            DATE_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> ALMOND_TREE_CONFIGURED = register(createIdentifier(FeatureNames.ALMOND_TREE_CONFIGURED),
            ALMOND_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.25F, 5)))));

    public static final ConfiguredFeature<?, ?> CASHEW_TREE_CONFIGURED = register(createIdentifier(FeatureNames.CASHEW_TREE_CONFIGURED),
            CASHEW_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.25F, 5)))));

    public static final ConfiguredFeature<?, ?> PECAN_TREE_CONFIGURED = register(createIdentifier(FeatureNames.PECAN_TREE_CONFIGURED),
            PECAN_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.25F, 5)))));

    public static final ConfiguredFeature<?, ?> WALNUT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.WALNUT_TREE_CONFIGURED),
            WALNUT_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.25F, 5)))));

    public static final ConfiguredFeature<?, ?> CINNAMON_TREE_CONFIGURED = register(createIdentifier(FeatureNames.CINNAMON_TREE_CONFIGURED),
            CINNAMON_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.25F, 5)))));


    public static final ConfiguredFeature<?, ?> DISK_SALT = register(createIdentifier(FeatureNames.DISK_SALT),
            Feature.DISK.configure((new DiskFeatureConfig(BlockRegistry.salt.getDefaultState(),
                    UniformIntProvider.create(2, 4), 2,
                    ImmutableList.of(Blocks.DIRT.getDefaultState(), Blocks.GRASS_BLOCK.getDefaultState())))));

    public static final ConfiguredFeature<?, ?> DISK_SALT_CONFIGURED = register(createIdentifier(FeatureNames.DISK_SALT_CONFIGURED),
            DISK_SALT.decorate(ConfiguredFeatures.Decorators.SQUARE_TOP_SOLID_HEIGHTMAP));


    private static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> register(Identifier id, ConfiguredFeature<FC, ?> configuredFeature) {
        ConfiguredFeature<FC, ?> feature = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, id, configuredFeature);
        RegistryKey<ConfiguredFeature<?, ?>> key = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, id);
        keyMap.put(id.getPath(), key);
        featureMap.put(key, feature);
        return feature;
    }

    public static Map<RegistryKey<ConfiguredFeature<?, ?>>, ConfiguredFeature<?, ?>> getFeatureMap() {
        return featureMap;
    }

    public static Map<String, RegistryKey<ConfiguredFeature<?, ?>>> getKeyMap() {
        return keyMap;
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> getFeatureKey(String key) {
        return keyMap.get(key);
    }
}
