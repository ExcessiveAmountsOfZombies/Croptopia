package me.thonk.croptopia.generator;

import me.thonk.croptopia.blocks.LeavesRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.UniformIntDistribution;
import net.minecraft.world.gen.decorator.CountExtraDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.trunk.ForkingTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import static me.thonk.croptopia.Croptopia.createIdentifier;

public class TreeGeneratorRegistry {
    public static final ConfiguredFeature<?, ?> APPLE_TREE = registerTree(createIdentifier("apple_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.OAK_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.appleCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(5, 3, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> BANANA_TREE = registerTree(createIdentifier("banana_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.JUNGLE_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.bananaCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(4, 8, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> ORANGE_TREE = registerTree(createIdentifier("orange_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.OAK_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.orangeCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(4, 4, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> PERSIMMON_TREE = registerTree(createIdentifier("persimmon_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.OAK_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.persimmonCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(5, 3, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> PLUM_TREE = registerTree(createIdentifier("plum_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.OAK_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.plumCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(5, 3, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> CHERRY_TREE = registerTree(createIdentifier("cherry_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.OAK_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.cherryCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(5, 3, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> LEMON_TREE = registerTree(createIdentifier("lemon_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.OAK_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.lemonCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(5, 3, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> GRAPEFRUIT_TREE = registerTree(createIdentifier("grapefruit_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.JUNGLE_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.grapefruitCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(4, 8, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> KUMQUAT_TREE = registerTree(createIdentifier("kumquat_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.JUNGLE_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.kumquatCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(4, 8, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> PEACH_TREE = registerTree(createIdentifier("peach_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.OAK_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.peachCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(5, 3, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> COCONUT_TREE = registerTree(createIdentifier("coconut_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.JUNGLE_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.coconutCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 1),
                    new ForkingTrunkPlacer(5, 2, 3),
                    new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> NUTMEG_TREE = registerTree(createIdentifier("nutmeg_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.JUNGLE_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.nutmegCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(4, 8, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> FIG_TREE = registerTree(createIdentifier("fig_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.JUNGLE_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.figCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(4, 8, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> NECTARINE_TREE = registerTree(createIdentifier("nectarine_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.OAK_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.nectarineCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(4, 4, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> MANGO_TREE = registerTree(createIdentifier("mango_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.JUNGLE_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.mangoCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(5, 8, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> DRAGON_FRUIT_TREE = registerTree(createIdentifier("dragon_fruit_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.OAK_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.dragonFruitCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(5, 7, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> STAR_FRUIT_TREE = registerTree(createIdentifier("star_fruit_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.OAK_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.starFruitCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(5, 3, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> AVOCADO_TREE = registerTree(createIdentifier("avocado_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.SPRUCE_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.OAK_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.avocadoCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(5, 3, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> APRICOT_TREE = registerTree(createIdentifier("apricot_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.OAK_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.apricotCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(5, 2, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> PEAR_TREE = registerTree(createIdentifier("pear_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.OAK_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.pearCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(5, 2, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> LIME_TREE = registerTree(createIdentifier("lime_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.OAK_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.limeCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(5, 2, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> DATE_TREE = registerTree(createIdentifier("date_tree"),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.getDefaultState()),
                    new WeightedBlockStateProvider().addState(Blocks.JUNGLE_LEAVES.getDefaultState(), 95).addState(LeavesRegistry.dateCrop.getDefaultState(), 5),
                    new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                    new StraightTrunkPlacer(5, 8, 0),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> APPLE_TREE_CONFIGURED = registerTree(createIdentifier("apple_tree_configured"),
            APPLE_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> BANANA_TREE_CONFIGURED = registerTree(createIdentifier("banana_tree_configured"),
            BANANA_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> ORANGE_TREE_CONFIGURED = registerTree(createIdentifier("orange_tree_configured"),
            ORANGE_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> PERSIMMON_TREE_CONFIGURED = registerTree(createIdentifier("persimmon_tree_configured"),
            PERSIMMON_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> PLUM_TREE_CONFIGURED = registerTree(createIdentifier("plum_tree_configured"),
            PLUM_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> CHERRY_TREE_CONFIGURED = registerTree(createIdentifier("cherry_tree_configured"),
            CHERRY_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> LEMON_TREE_CONFIGURED = registerTree(createIdentifier("lemon_tree_configured"),
            LEMON_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> GRAPEFRUIT_TREE_CONFIGURED = registerTree(createIdentifier("grapefruit_tree_configured"),
            GRAPEFRUIT_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> KUMQUAT_TREE_CONFIGURED = registerTree(createIdentifier("kumquat_tree_configured"),
            KUMQUAT_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> PEACH_TREE_CONFIGURED = registerTree(createIdentifier("peach_tree_configured"),
            PEACH_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> COCONUT_TREE_CONFIGURED = registerTree(createIdentifier("coconut_tree_configured"),
            COCONUT_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> NUTMEG_TREE_CONFIGURED = registerTree(createIdentifier("nutmeg_tree_configured"),
            NUTMEG_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> FIG_TREE_CONFIGURED = registerTree(createIdentifier("fig_tree_configured"),
            FIG_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> NECTARINE_TREE_CONFIGURED = registerTree(createIdentifier("nectarine_tree_configured"),
            NECTARINE_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> MANGO_TREE_CONFIGURED = registerTree(createIdentifier("mango_tree_configured"),
            MANGO_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> DRAGON_FRUIT_TREE_CONFIGURED = registerTree(createIdentifier("dragon_fruit_tree_configured"),
            DRAGON_FRUIT_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> STAR_FRUIT_TREE_CONFIGURED = registerTree(createIdentifier("star_fruit_tree_configured"),
            STAR_FRUIT_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> AVOCADO_TREE_CONFIGURED = registerTree(createIdentifier("avocado_tree_configured"),
            AVOCADO_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> APRICOT_TREE_CONFIGURED = registerTree(createIdentifier("apricot_tree_configured"),
            APRICOT_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> PEAR_TREE_CONFIGURED = registerTree(createIdentifier("pear_tree_configured"),
            PEAR_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> LIME_TREE_CONFIGURED = registerTree(createIdentifier("lime_tree_configured"),
            LIME_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));

    public static final ConfiguredFeature<?, ?> DATE_TREE_CONFIGURED = registerTree(createIdentifier("date_tree_configured"),
            DATE_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.1F, 1)))));


    private static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> registerTree(Identifier id, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, id, configuredFeature);
    }

}
