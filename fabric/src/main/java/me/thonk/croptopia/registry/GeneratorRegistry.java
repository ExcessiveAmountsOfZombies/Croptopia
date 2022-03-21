package me.thonk.croptopia.registry;

import com.google.common.collect.ImmutableList;
import me.thonk.common.FeatureNames;
import me.thonk.croptopia.blocks.CroptopiaCropBlock;
import me.thonk.croptopia.blocks.LeafCropBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.decorator.BiomePlacementModifier;
import net.minecraft.world.gen.decorator.BlockFilterPlacementModifier;
import net.minecraft.world.gen.decorator.CountPlacementModifier;
import net.minecraft.world.gen.decorator.SquarePlacementModifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.trunk.ForkingTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static me.thonk.croptopia.Croptopia.createIdentifier;

public class GeneratorRegistry {

    private static Map<String, RegistryKey<PlacedFeature>> keyMap = new HashMap<>();
    private static Map<RegistryKey<ConfiguredFeature<?, ?>>, ConfiguredFeature<?, ?>> featureMap = new HashMap<>();

    public static final ConfiguredFeature<?, ?> config = Feature.SIMPLE_BLOCK.configure(new SimpleBlockFeatureConfig(
            new WeightedBlockStateProvider(DataPool.<BlockState>builder()
            .add(BlockRegistry.artichokeCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.asparagusCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.barleyCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.basilCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 20)
            .add(BlockRegistry.bellPepperCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.blackBeanCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.blackberryCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.blueberryCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.broccoliCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.cabbageCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.cantaloupeCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.cauliflowerCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.celeryCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.coffeeCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 20)
            .add(BlockRegistry.cornCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.cranberryCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.cucumberCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.currantCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.eggplantCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 20)
            .add(BlockRegistry.elderberryCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.garlicCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 20)
            .add(BlockRegistry.gingerCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.grapeCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.greenBeanCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.greenOnionCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 20)
            .add(BlockRegistry.honeydewCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 20)
            .add(BlockRegistry.hopsCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.kaleCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.kiwiCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.leekCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.lettuceCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.mustardCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.oatCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.oliveCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.onionCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 20)
            .add(BlockRegistry.peanutCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 20)
            .add(BlockRegistry.chilePepperCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.pineappleCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 20)
            .add(BlockRegistry.radishCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.raspberryCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.rhubarbCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 20)
            .add(BlockRegistry.riceCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 20)
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
            .add(BlockRegistry.turnipCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 20)
            .add(BlockRegistry.yamCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.zucchiniCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.vanillaCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 20)
            .add(BlockRegistry.pepperCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(BlockRegistry.teaCropBlock.getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .build())));

    public static final PlacedFeature RANDOM_CROP = register(createIdentifier("random_crop"),
            Feature.RANDOM_PATCH.configure(ConfiguredFeatures.createRandomPatchFeatureConfig(3, config.withInAirFilter())).withPlacement(CountPlacementModifier.of(3), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> APPLE_TREE = register(createIdentifier(FeatureNames.APPLE_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.appleCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> BANANA_TREE = register(createIdentifier(FeatureNames.BANANA_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.JUNGLE_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 8, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.getDefaultState(), 90).add(LeavesRegistry.bananaCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));


    public static final ConfiguredFeature<TreeFeatureConfig, ?> ORANGE_TREE = register(createIdentifier(FeatureNames.ORANGE_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 4, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.orangeCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> PERSIMMON_TREE = register(createIdentifier(FeatureNames.PERSIMMON_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.persimmonCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> PLUM_TREE = register(createIdentifier(FeatureNames.PLUM_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.plumCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> CHERRY_TREE = register(createIdentifier(FeatureNames.CHERRY_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.cherryCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> LEMON_TREE = register(createIdentifier(FeatureNames.LEMON_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.lemonCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> GRAPEFRUIT_TREE = register(createIdentifier(FeatureNames.GRAPEFRUIT_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.JUNGLE_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 8, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.getDefaultState(), 90).add(LeavesRegistry.grapefruitCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> KUMQUAT_TREE = register(createIdentifier(FeatureNames.KUMQUAT_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.JUNGLE_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 8, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.getDefaultState(), 90).add(LeavesRegistry.kumquatCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> PEACH_TREE = register(createIdentifier(FeatureNames.PEACH_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.peachCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> COCONUT_TREE = register(createIdentifier(FeatureNames.COCONUT_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.JUNGLE_LOG.getDefaultState()),
                    new ForkingTrunkPlacer(5, 2, 3),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.getDefaultState(), 90).add(LeavesRegistry.coconutCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 1),
                    new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> NUTMEG_TREE = register(createIdentifier(FeatureNames.NUTMEG_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.JUNGLE_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 8, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.getDefaultState(), 90).add(LeavesRegistry.nutmegCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> FIG_TREE = register(createIdentifier(FeatureNames.FIG_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.JUNGLE_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 8, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.getDefaultState(), 90).add(LeavesRegistry.figCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> NECTARINE_TREE = register(createIdentifier(FeatureNames.NECTARINE_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 4, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.nectarineCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> MANGO_TREE = register(createIdentifier(FeatureNames.MANGO_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.JUNGLE_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 8, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.getDefaultState(), 90).add(LeavesRegistry.mangoCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> DRAGON_FRUIT_TREE = register(createIdentifier(FeatureNames.DRAGON_FRUIT_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.JUNGLE_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 7, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.dragonFruitCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> STAR_FRUIT_TREE = register(createIdentifier(FeatureNames.STAR_FRUIT_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.starFruitCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> AVOCADO_TREE = register(createIdentifier(FeatureNames.AVOCADO_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.SPRUCE_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.avocadoCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> APRICOT_TREE = register(createIdentifier(FeatureNames.APRICOT_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 2, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.apricotCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> PEAR_TREE = register(createIdentifier(FeatureNames.PEAR_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 2, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.pearCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> LIME_TREE = register(createIdentifier(FeatureNames.LIME_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 2, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.limeCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> DATE_TREE = register(createIdentifier(FeatureNames.DATE_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.JUNGLE_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 8, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.getDefaultState(), 90).add(LeavesRegistry.dateCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> ALMOND_TREE = register(createIdentifier(FeatureNames.ALMOND_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.DARK_OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.DARK_OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.almondCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> CASHEW_TREE = register(createIdentifier(FeatureNames.CASHEW_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.DARK_OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.DARK_OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.cashewCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> PECAN_TREE = register(createIdentifier(FeatureNames.PECAN_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.DARK_OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.DARK_OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.pecanCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> WALNUT_TREE = register(createIdentifier(FeatureNames.WALNUT_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.DARK_OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.DARK_OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.walnutCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> CINNAMON_TREE = register(createIdentifier(FeatureNames.CINNAMON_TREE),
            Feature.TREE.configure((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(BlockRegistry.cinnamonLog.getDefaultState()),
                    new StraightTrunkPlacer(4, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(LeavesRegistry.cinnamonLeaves.getDefaultState(), 90).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));

    public static final PlacedFeature APPLE_TREE_CONFIGURED = register(createIdentifier(FeatureNames.APPLE_TREE_CONFIGURED),
            APPLE_TREE.withPlacement(SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));

    public static final PlacedFeature BANANA_TREE_CONFIGURED = register(createIdentifier(FeatureNames.BANANA_TREE_CONFIGURED),
            BANANA_TREE.withPlacement(SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));

    public static final PlacedFeature ORANGE_TREE_CONFIGURED = register(createIdentifier(FeatureNames.ORANGE_TREE_CONFIGURED),
            ORANGE_TREE.withPlacement(SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));

    public static final PlacedFeature PERSIMMON_TREE_CONFIGURED = register(createIdentifier(FeatureNames.PERSIMMON_TREE_CONFIGURED),
            PERSIMMON_TREE.withPlacement(SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));

    public static final PlacedFeature PLUM_TREE_CONFIGURED = register(createIdentifier(FeatureNames.PLUM_TREE_CONFIGURED),
            PLUM_TREE.withPlacement(SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));

    public static final PlacedFeature CHERRY_TREE_CONFIGURED = register(createIdentifier(FeatureNames.CHERRY_TREE_CONFIGURED),
            CHERRY_TREE.withPlacement(SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));

    public static final PlacedFeature LEMON_TREE_CONFIGURED = register(createIdentifier(FeatureNames.LEMON_TREE_CONFIGURED),
            LEMON_TREE.withPlacement(SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));

    public static final PlacedFeature GRAPEFRUIT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.GRAPEFRUIT_TREE_CONFIGURED),
            GRAPEFRUIT_TREE.withPlacement(SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));

    public static final PlacedFeature KUMQUAT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.KUMQUAT_TREE_CONFIGURED),
            KUMQUAT_TREE.withPlacement(SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));

    public static final PlacedFeature PEACH_TREE_CONFIGURED = register(createIdentifier(FeatureNames.PEACH_TREE_CONFIGURED),
            PEACH_TREE.withPlacement(SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));

    public static final PlacedFeature COCONUT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.COCONUT_TREE_CONFIGURED),
            COCONUT_TREE.withPlacement(SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.2F, 5), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));

    public static final PlacedFeature NUTMEG_TREE_CONFIGURED = register(createIdentifier(FeatureNames.NUTMEG_TREE_CONFIGURED),
            NUTMEG_TREE.withPlacement(SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));

    public static final PlacedFeature FIG_TREE_CONFIGURED = register(createIdentifier(FeatureNames.FIG_TREE_CONFIGURED),
            FIG_TREE.withPlacement(SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));

    public static final PlacedFeature NECTARINE_TREE_CONFIGURED = register(createIdentifier(FeatureNames.NECTARINE_TREE_CONFIGURED),
            NECTARINE_TREE.withPlacement(SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));

    public static final PlacedFeature MANGO_TREE_CONFIGURED = register(createIdentifier(FeatureNames.MANGO_TREE_CONFIGURED),
            MANGO_TREE.withPlacement(SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));

    public static final PlacedFeature DRAGON_FRUIT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.DRAGON_FRUIT_TREE_CONFIGURED),
            DRAGON_FRUIT_TREE.withPlacement(SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));

    public static final PlacedFeature STAR_FRUIT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.STAR_FRUIT_TREE_CONFIGURED),
            STAR_FRUIT_TREE.withPlacement(SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));

    public static final PlacedFeature AVOCADO_TREE_CONFIGURED = register(createIdentifier(FeatureNames.AVOCADO_TREE_CONFIGURED),
            AVOCADO_TREE.withPlacement(SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));

    public static final PlacedFeature APRICOT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.APRICOT_TREE_CONFIGURED),
            APRICOT_TREE.withPlacement(SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));

    public static final PlacedFeature PEAR_TREE_CONFIGURED = register(createIdentifier(FeatureNames.PEAR_TREE_CONFIGURED),
            PEAR_TREE.withPlacement(SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));

    public static final PlacedFeature LIME_TREE_CONFIGURED = register(createIdentifier(FeatureNames.LIME_TREE_CONFIGURED),
            LIME_TREE.withPlacement(SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));

    public static final PlacedFeature DATE_TREE_CONFIGURED = register(createIdentifier(FeatureNames.DATE_TREE_CONFIGURED),
            DATE_TREE.withPlacement(SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));

    public static final PlacedFeature ALMOND_TREE_CONFIGURED = register(createIdentifier(FeatureNames.ALMOND_TREE_CONFIGURED),
            ALMOND_TREE.withPlacement(SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.25F, 5), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));

    public static final PlacedFeature CASHEW_TREE_CONFIGURED = register(createIdentifier(FeatureNames.CASHEW_TREE_CONFIGURED),
            CASHEW_TREE.withPlacement(SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.25F, 5), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));

    public static final PlacedFeature PECAN_TREE_CONFIGURED = register(createIdentifier(FeatureNames.PECAN_TREE_CONFIGURED),
            PECAN_TREE.withPlacement(SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.25F, 5), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));

    public static final PlacedFeature WALNUT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.WALNUT_TREE_CONFIGURED),
            WALNUT_TREE.withPlacement(SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.25F, 5), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));

    public static final PlacedFeature CINNAMON_TREE_CONFIGURED = register(createIdentifier(FeatureNames.CINNAMON_TREE_CONFIGURED),
            CINNAMON_TREE.withPlacement(SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(1, 0.1F, 6), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));


    public static final ConfiguredFeature<?, ?> DISK_SALT = register(createIdentifier(FeatureNames.DISK_SALT),
            Feature.DISK.configure((new DiskFeatureConfig(BlockRegistry.salt.getDefaultState(),
                    UniformIntProvider.create(2, 4), 2,
                    ImmutableList.of(Blocks.DIRT.getDefaultState(), Blocks.GRASS_BLOCK.getDefaultState())))));

    public static final PlacedFeature DISK_SALT_CONFIGURED = register(createIdentifier(FeatureNames.DISK_SALT_CONFIGURED),
            DISK_SALT.withPlacement(PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP, BiomePlacementModifier.of(), SquarePlacementModifier.of()));


    private static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> register(Identifier id, ConfiguredFeature<FC, ?> configuredFeature) {
        ConfiguredFeature<FC, ?> feature = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, id, configuredFeature);
        RegistryKey<ConfiguredFeature<?, ?>> key = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, id);

        featureMap.put(key, feature);
        return feature;
    }

    public static PlacedFeature register(Identifier id, PlacedFeature feature) {
        PlacedFeature placedFeature = Registry.register(BuiltinRegistries.PLACED_FEATURE, id, feature);
        RegistryKey<PlacedFeature> key = RegistryKey.of(Registry.PLACED_FEATURE_KEY, id);
        keyMap.put(id.getPath(), key);
        return placedFeature;
    }

    public static Map<RegistryKey<ConfiguredFeature<?, ?>>, ConfiguredFeature<?, ?>> getFeatureMap() {
        return featureMap;
    }

    public static Map<String, RegistryKey<PlacedFeature>> getKeyMap() {
        return keyMap;
    }

    public static RegistryKey<PlacedFeature> getFeatureKey(String key) {
        return keyMap.get(key);
    }
}
