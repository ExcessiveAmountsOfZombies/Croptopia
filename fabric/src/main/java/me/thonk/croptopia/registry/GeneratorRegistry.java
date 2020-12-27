package me.thonk.croptopia.registry;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.UniformIntDistribution;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
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

    public static final ConfiguredFeature<?, ?> APPLE_TREE = register(createIdentifier("apple_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.OAK_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.appleCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(5, 3, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> BANANA_TREE = register(createIdentifier("banana_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.JUNGLE_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.bananaCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(4, 8, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> ORANGE_TREE = register(createIdentifier("orange_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.OAK_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.orangeCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(4, 4, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> PERSIMMON_TREE = register(createIdentifier("persimmon_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.OAK_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.persimmonCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(5, 3, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> PLUM_TREE = register(createIdentifier("plum_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.OAK_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.plumCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(5, 3, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> CHERRY_TREE = register(createIdentifier("cherry_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.OAK_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.cherryCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(5, 3, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> LEMON_TREE = register(createIdentifier("lemon_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.OAK_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.lemonCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(5, 3, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> GRAPEFRUIT_TREE = register(createIdentifier("grapefruit_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.JUNGLE_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.grapefruitCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(4, 8, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> KUMQUAT_TREE = register(createIdentifier("kumquat_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.JUNGLE_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.kumquatCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(4, 8, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> PEACH_TREE = register(createIdentifier("peach_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.OAK_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.peachCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(5, 3, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> COCONUT_TREE = register(createIdentifier("coconut_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.JUNGLE_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.coconutCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 1),
                    new ForkingTrunkPlacer(5, 2, 3),
                    new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> NUTMEG_TREE = register(createIdentifier("nutmeg_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.JUNGLE_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.nutmegCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(4, 8, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> FIG_TREE = register(createIdentifier("fig_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.JUNGLE_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.figCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(4, 8, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> NECTARINE_TREE = register(createIdentifier("nectarine_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.OAK_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.nectarineCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(4, 4, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> MANGO_TREE = register(createIdentifier("mango_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.JUNGLE_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.mangoCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(5, 8, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> DRAGON_FRUIT_TREE = register(createIdentifier("dragon_fruit_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.OAK_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.dragonFruitCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(5, 7, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> STAR_FRUIT_TREE = register(createIdentifier("star_fruit_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.OAK_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.starFruitCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(5, 3, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> AVOCADO_TREE = register(createIdentifier("avocado_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.SPRUCE_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.OAK_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.avocadoCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(5, 3, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> APRICOT_TREE = register(createIdentifier("apricot_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.OAK_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.apricotCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(5, 2, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> PEAR_TREE = register(createIdentifier("pear_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.OAK_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.pearCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(5, 2, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> LIME_TREE = register(createIdentifier("lime_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.OAK_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.limeCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(5, 2, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> DATE_TREE = register(createIdentifier("date_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.JUNGLE_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.dateCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(5, 8, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> ALMOND_TREE = register(createIdentifier("almond_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.DARK_OAK_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.DARK_OAK_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.almondCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(4, 3, 0),
                    new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> CASHEW_TREE = register(createIdentifier("cashew_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.DARK_OAK_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.DARK_OAK_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.cashewCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(4, 3, 0),
                    new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> PECAN_TREE = register(createIdentifier("pecan_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.DARK_OAK_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.DARK_OAK_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.pecanCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(4, 3, 0),
                    new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> WALNUT_TREE = register(createIdentifier("walnut_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.DARK_OAK_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.DARK_OAK_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.walnutCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(4, 3, 0),
                    new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> APPLE_TREE_CONFIGURED = register(createIdentifier("apple_tree_configured"),
            APPLE_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> BANANA_TREE_CONFIGURED = register(createIdentifier("banana_tree_configured"),
            BANANA_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> ORANGE_TREE_CONFIGURED = register(createIdentifier("orange_tree_configured"),
            ORANGE_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> PERSIMMON_TREE_CONFIGURED = register(createIdentifier("persimmon_tree_configured"),
            PERSIMMON_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> PLUM_TREE_CONFIGURED = register(createIdentifier("plum_tree_configured"),
            PLUM_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> CHERRY_TREE_CONFIGURED = register(createIdentifier("cherry_tree_configured"),
            CHERRY_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> LEMON_TREE_CONFIGURED = register(createIdentifier("lemon_tree_configured"),
            LEMON_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> GRAPEFRUIT_TREE_CONFIGURED = register(createIdentifier("grapefruit_tree_configured"),
            GRAPEFRUIT_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> KUMQUAT_TREE_CONFIGURED = register(createIdentifier("kumquat_tree_configured"),
            KUMQUAT_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> PEACH_TREE_CONFIGURED = register(createIdentifier("peach_tree_configured"),
            PEACH_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> COCONUT_TREE_CONFIGURED = register(createIdentifier("coconut_tree_configured"),
            COCONUT_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> NUTMEG_TREE_CONFIGURED = register(createIdentifier("nutmeg_tree_configured"),
            NUTMEG_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> FIG_TREE_CONFIGURED = register(createIdentifier("fig_tree_configured"),
            FIG_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> NECTARINE_TREE_CONFIGURED = register(createIdentifier("nectarine_tree_configured"),
            NECTARINE_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> MANGO_TREE_CONFIGURED = register(createIdentifier("mango_tree_configured"),
            MANGO_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> DRAGON_FRUIT_TREE_CONFIGURED = register(createIdentifier("dragon_fruit_tree_configured"),
            DRAGON_FRUIT_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> STAR_FRUIT_TREE_CONFIGURED = register(createIdentifier("star_fruit_tree_configured"),
            STAR_FRUIT_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> AVOCADO_TREE_CONFIGURED = register(createIdentifier("avocado_tree_configured"),
            AVOCADO_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> APRICOT_TREE_CONFIGURED = register(createIdentifier("apricot_tree_configured"),
            APRICOT_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> PEAR_TREE_CONFIGURED = register(createIdentifier("pear_tree_configured"),
            PEAR_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> LIME_TREE_CONFIGURED = register(createIdentifier("lime_tree_configured"),
            LIME_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> DATE_TREE_CONFIGURED = register(createIdentifier("date_tree_configured"),
            DATE_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> ALMOND_TREE_CONFIGURED = register(createIdentifier("almond_tree_configured"),
            ALMOND_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.25F, 5)))));

    public static final ConfiguredFeature<?, ?> CASHEW_TREE_CONFIGURED = register(createIdentifier("cashew_tree_configured"),
            CASHEW_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.25F, 5)))));

    public static final ConfiguredFeature<?, ?> PECAN_TREE_CONFIGURED = register(createIdentifier("pecan_tree_configured"),
            PECAN_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.25F, 5)))));

    public static final ConfiguredFeature<?, ?> WALNUT_TREE_CONFIGURED = register(createIdentifier("walnut_tree_configured"),
            WALNUT_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.25F, 5)))));


    public static final ConfiguredFeature<?, ?> DISK_SALT = register(createIdentifier("disk_salt"),
            Feature.DISK.configure((new DiskFeatureConfig(BlockRegistry.salt.getDefaultState(),
                    UniformIntDistribution.of(2, 4), 2,
                    ImmutableList.of(Blocks.DIRT.getDefaultState(), Blocks.GRASS_BLOCK.getDefaultState())))));

    public static final ConfiguredFeature<?, ?> DISK_SALT_CONFIGURED = register(createIdentifier("disk_salt_configured"),
            DISK_SALT.decorate(ConfiguredFeatures.Decorators.SQUARE_TOP_SOLID_HEIGHTMAP
                    .decorate(Decorator.CHANCE.configure(new ChanceDecoratorConfig(25)))).repeat(2));


    private static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> register(Identifier id, ConfiguredFeature<FC, ?> configuredFeature) {
        ConfiguredFeature<FC, ?> feature = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, id, configuredFeature);
        RegistryKey<ConfiguredFeature<?, ?>> key = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, id);
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
