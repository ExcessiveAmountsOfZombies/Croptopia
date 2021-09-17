package me.thonk.croptopia.registry;

import com.google.common.collect.ImmutableList;
import me.thonk.common.FeatureNames;
import me.thonk.croptopia.blocks.CroptopiaCropBlock;
import me.thonk.croptopia.blocks.LeafCropBlock;
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
import net.minecraft.world.gen.placer.SimpleBlockPlacer;
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


    public static final RandomPatchFeatureConfig config = new RandomPatchFeatureConfig.Builder(
            new WeightedBlockStateProvider(DataPool.<BlockState>builder()
            .add(BlockRegistry.artichokeCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.asparagusCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.barleyCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.basilCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.bellPepperCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.blackBeanCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.blackberryCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.blueberryCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.broccoliCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.cabbageCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.cantaloupeCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.cauliflowerCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.celeryCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.coffeeCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.cornCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.cranberryCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.cucumberCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.currantCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.eggplantCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.elderberryCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.garlicCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.gingerCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.grapeCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.greenBeanCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.greenOnionCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.honeydewCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.hopsCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.kaleCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.kiwiCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.leekCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.lettuceCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.mustardCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.oatCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.oliveCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.onionCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.peanutCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.chilePepperCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.pineappleCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.radishCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.raspberryCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.rhubarbCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.riceCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.rutabagaCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.saguaroCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.soybeanCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.spinachCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.squashCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.strawberryCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.sweetPotatoCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.tomatilloCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.tomatoCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.turmericCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.turnipCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.yamCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.zucchiniCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.vanillaCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.pepperCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.teaCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .build()),
            SimpleBlockPlacer.INSTANCE).tries(24).build();

    public static final ConfiguredFeature<?, ?> RANDOM_CROP = register(createIdentifier("random_crop"),
            Feature.RANDOM_PATCH.configure(config).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> APPLE_TREE = register(createIdentifier(FeatureNames.APPLE_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.appleCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new SimpleBlockStateProvider(BlockRegistry.appleSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> BANANA_TREE = register(createIdentifier(FeatureNames.BANANA_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 8, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.getDefaultState(), 90).add(LeavesRegistry.bananaCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new SimpleBlockStateProvider(BlockRegistry.bananaSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));


    public static final ConfiguredFeature<TreeFeatureConfig, ?> ORANGE_TREE = register(createIdentifier(FeatureNames.ORANGE_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 4, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.orangeCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new SimpleBlockStateProvider(BlockRegistry.orangeSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> PERSIMMON_TREE = register(createIdentifier(FeatureNames.PERSIMMON_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.persimmonCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new SimpleBlockStateProvider(BlockRegistry.persimmonSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> PLUM_TREE = register(createIdentifier(FeatureNames.PLUM_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.plumCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new SimpleBlockStateProvider(BlockRegistry.plumSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> CHERRY_TREE = register(createIdentifier(FeatureNames.CHERRY_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.cherryCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new SimpleBlockStateProvider(BlockRegistry.cherrySaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> LEMON_TREE = register(createIdentifier(FeatureNames.LEMON_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.lemonCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new SimpleBlockStateProvider(BlockRegistry.lemonSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> GRAPEFRUIT_TREE = register(createIdentifier(FeatureNames.GRAPEFRUIT_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 8, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.getDefaultState(), 90).add(LeavesRegistry.grapefruitCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new SimpleBlockStateProvider(BlockRegistry.grapefruitSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> KUMQUAT_TREE = register(createIdentifier(FeatureNames.KUMQUAT_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 8, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.getDefaultState(), 90).add(LeavesRegistry.kumquatCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new SimpleBlockStateProvider(BlockRegistry.kumquatSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> PEACH_TREE = register(createIdentifier(FeatureNames.PEACH_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.peachCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new SimpleBlockStateProvider(BlockRegistry.peachSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> COCONUT_TREE = register(createIdentifier(FeatureNames.COCONUT_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.getDefaultState()),
                    new ForkingTrunkPlacer(5, 2, 3),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.getDefaultState(), 90).add(LeavesRegistry.coconutCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new SimpleBlockStateProvider(BlockRegistry.coconutSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 1),
                    new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> NUTMEG_TREE = register(createIdentifier(FeatureNames.NUTMEG_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 8, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.getDefaultState(), 90).add(LeavesRegistry.nutmegCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new SimpleBlockStateProvider(BlockRegistry.nutmegSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> FIG_TREE = register(createIdentifier(FeatureNames.FIG_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 8, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.getDefaultState(), 90).add(LeavesRegistry.figCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new SimpleBlockStateProvider(BlockRegistry.figSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> NECTARINE_TREE = register(createIdentifier(FeatureNames.NECTARINE_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 4, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.nectarineCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new SimpleBlockStateProvider(BlockRegistry.nectarineSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> MANGO_TREE = register(createIdentifier(FeatureNames.MANGO_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 8, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.getDefaultState(), 90).add(LeavesRegistry.mangoCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new SimpleBlockStateProvider(BlockRegistry.mangoSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> DRAGON_FRUIT_TREE = register(createIdentifier(FeatureNames.DRAGON_FRUIT_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 7, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.dragonFruitCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new SimpleBlockStateProvider(BlockRegistry.dragonFruitSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> STAR_FRUIT_TREE = register(createIdentifier(FeatureNames.STAR_FRUIT_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.starFruitCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new SimpleBlockStateProvider(BlockRegistry.starFruitSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> AVOCADO_TREE = register(createIdentifier(FeatureNames.AVOCADO_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.SPRUCE_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.avocadoCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new SimpleBlockStateProvider(BlockRegistry.avocadoSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> APRICOT_TREE = register(createIdentifier(FeatureNames.APRICOT_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 2, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.apricotCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new SimpleBlockStateProvider(BlockRegistry.apricotSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> PEAR_TREE = register(createIdentifier(FeatureNames.PEAR_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 2, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.pearCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new SimpleBlockStateProvider(BlockRegistry.pearSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> LIME_TREE = register(createIdentifier(FeatureNames.LIME_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 2, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.limeCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new SimpleBlockStateProvider(BlockRegistry.limeSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> DATE_TREE = register(createIdentifier(FeatureNames.DATE_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 8, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.getDefaultState(), 90).add(LeavesRegistry.dateCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new SimpleBlockStateProvider(BlockRegistry.dateSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> ALMOND_TREE = register(createIdentifier(FeatureNames.ALMOND_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.DARK_OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.DARK_OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.almondCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new SimpleBlockStateProvider(BlockRegistry.almondSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> CASHEW_TREE = register(createIdentifier(FeatureNames.CASHEW_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.DARK_OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.DARK_OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.cashewCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new SimpleBlockStateProvider(BlockRegistry.cashewSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> PECAN_TREE = register(createIdentifier(FeatureNames.PECAN_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.DARK_OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.DARK_OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.pecanCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new SimpleBlockStateProvider(BlockRegistry.pecanSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> WALNUT_TREE = register(createIdentifier(FeatureNames.WALNUT_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.DARK_OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.DARK_OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.walnutCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new SimpleBlockStateProvider(BlockRegistry.walnutSaplingBlock.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> CINNAMON_TREE = register(createIdentifier(FeatureNames.CINNAMON_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(BlockRegistry.cinnamonLog.getDefaultState()),
                    new StraightTrunkPlacer(4, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(LeavesRegistry.cinnamonLeaves.getDefaultState(), 90).build()),
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
