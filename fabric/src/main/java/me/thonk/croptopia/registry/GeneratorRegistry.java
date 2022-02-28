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
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.BlockFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.trunk.ForkingTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static me.thonk.croptopia.Croptopia.createIdentifier;

public class GeneratorRegistry {

    private static final Map<String, RegistryKey<PlacedFeature>> keyMap = new HashMap<>();

    public static final SimpleBlockFeatureConfig config = (new SimpleBlockFeatureConfig(
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
            .build())));

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> RANDOM_CROP = register(createIdentifier("random_crop"), Feature.RANDOM_PATCH,
            ConfiguredFeatures.createRandomPatchFeatureConfig(3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, config)));

    public static final RegistryEntry<PlacedFeature> RANDOM_CROP_PLACED = register(createIdentifier("random_crop"),
            RANDOM_CROP, CountPlacementModifier.of(3), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> APPLE_TREE = register(createIdentifier(FeatureNames.APPLE_TREE),
            Feature.TREE, ((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.appleCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> BANANA_TREE = register(createIdentifier(FeatureNames.BANANA_TREE),
            Feature.TREE, ((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.JUNGLE_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 8, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.getDefaultState(), 90).add(LeavesRegistry.bananaCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));


    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> ORANGE_TREE = register(createIdentifier(FeatureNames.ORANGE_TREE),
            Feature.TREE, ((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 4, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.orangeCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> PERSIMMON_TREE = register(createIdentifier(FeatureNames.PERSIMMON_TREE),
            Feature.TREE, ((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.persimmonCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> PLUM_TREE = register(createIdentifier(FeatureNames.PLUM_TREE),
            Feature.TREE, ((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.plumCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> CHERRY_TREE = register(createIdentifier(FeatureNames.CHERRY_TREE),
            Feature.TREE, ((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.cherryCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> LEMON_TREE = register(createIdentifier(FeatureNames.LEMON_TREE),
            Feature.TREE, ((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.lemonCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> GRAPEFRUIT_TREE = register(createIdentifier(FeatureNames.GRAPEFRUIT_TREE),
            Feature.TREE, ((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.JUNGLE_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 8, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.getDefaultState(), 90).add(LeavesRegistry.grapefruitCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> KUMQUAT_TREE = register(createIdentifier(FeatureNames.KUMQUAT_TREE),
            Feature.TREE, ((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.JUNGLE_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 8, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.getDefaultState(), 90).add(LeavesRegistry.kumquatCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> PEACH_TREE = register(createIdentifier(FeatureNames.PEACH_TREE),
            Feature.TREE, ((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.peachCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> COCONUT_TREE = register(createIdentifier(FeatureNames.COCONUT_TREE),
            Feature.TREE, ((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.JUNGLE_LOG.getDefaultState()),
                    new ForkingTrunkPlacer(5, 2, 3),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.getDefaultState(), 90).add(LeavesRegistry.coconutCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 1),
                    new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> NUTMEG_TREE = register(createIdentifier(FeatureNames.NUTMEG_TREE),
            Feature.TREE, ((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.JUNGLE_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 8, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.getDefaultState(), 90).add(LeavesRegistry.nutmegCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FIG_TREE = register(createIdentifier(FeatureNames.FIG_TREE),
            Feature.TREE, ((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.JUNGLE_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 8, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.getDefaultState(), 90).add(LeavesRegistry.figCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> NECTARINE_TREE = register(createIdentifier(FeatureNames.NECTARINE_TREE),
            Feature.TREE, ((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 4, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.nectarineCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> MANGO_TREE = register(createIdentifier(FeatureNames.MANGO_TREE),
            Feature.TREE, ((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.JUNGLE_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 8, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.getDefaultState(), 90).add(LeavesRegistry.mangoCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> DRAGON_FRUIT_TREE = register(createIdentifier(FeatureNames.DRAGON_FRUIT_TREE),
            Feature.TREE, ((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.JUNGLE_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 7, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.dragonFruitCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> STAR_FRUIT_TREE = register(createIdentifier(FeatureNames.STAR_FRUIT_TREE),
            Feature.TREE, ((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.starFruitCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> AVOCADO_TREE = register(createIdentifier(FeatureNames.AVOCADO_TREE),
            Feature.TREE, ((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.SPRUCE_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.avocadoCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> APRICOT_TREE = register(createIdentifier(FeatureNames.APRICOT_TREE),
            Feature.TREE, ((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 2, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.apricotCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> PEAR_TREE = register(createIdentifier(FeatureNames.PEAR_TREE),
            Feature.TREE, ((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 2, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.pearCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> LIME_TREE = register(createIdentifier(FeatureNames.LIME_TREE),
            Feature.TREE, ((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 2, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.limeCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> DATE_TREE = register(createIdentifier(FeatureNames.DATE_TREE),
            Feature.TREE, ((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.JUNGLE_LOG.getDefaultState()),
                    new StraightTrunkPlacer(5, 8, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.getDefaultState(), 90).add(LeavesRegistry.dateCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> ALMOND_TREE = register(createIdentifier(FeatureNames.ALMOND_TREE),
            Feature.TREE, ((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.DARK_OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.DARK_OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.almondCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> CASHEW_TREE = register(createIdentifier(FeatureNames.CASHEW_TREE),
            Feature.TREE, ((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.DARK_OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.DARK_OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.cashewCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> PECAN_TREE = register(createIdentifier(FeatureNames.PECAN_TREE),
            Feature.TREE, ((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.DARK_OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.DARK_OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.pecanCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> WALNUT_TREE = register(createIdentifier(FeatureNames.WALNUT_TREE),
            Feature.TREE, ((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(Blocks.DARK_OAK_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.DARK_OAK_LEAVES.getDefaultState(), 90).add(LeavesRegistry.walnutCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> CINNAMON_TREE = register(createIdentifier(FeatureNames.CINNAMON_TREE),
            Feature.TREE, ((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(BlockRegistry.cinnamonLog.getDefaultState()),
                    new StraightTrunkPlacer(4, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(LeavesRegistry.cinnamonLeaves.getDefaultState(), 90).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));

    public static final RegistryEntry<PlacedFeature> APPLE_TREE_CONFIGURED = register(createIdentifier(FeatureNames.APPLE_TREE_CONFIGURED),
            APPLE_TREE, SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> BANANA_TREE_CONFIGURED = register(createIdentifier(FeatureNames.BANANA_TREE_CONFIGURED),
            BANANA_TREE, SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> ORANGE_TREE_CONFIGURED = register(createIdentifier(FeatureNames.ORANGE_TREE_CONFIGURED),
            ORANGE_TREE, SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> PERSIMMON_TREE_CONFIGURED = register(createIdentifier(FeatureNames.PERSIMMON_TREE_CONFIGURED),
            PERSIMMON_TREE, SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> PLUM_TREE_CONFIGURED = register(createIdentifier(FeatureNames.PLUM_TREE_CONFIGURED),
            PLUM_TREE, SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> CHERRY_TREE_CONFIGURED = register(createIdentifier(FeatureNames.CHERRY_TREE_CONFIGURED),
            CHERRY_TREE, SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> LEMON_TREE_CONFIGURED = register(createIdentifier(FeatureNames.LEMON_TREE_CONFIGURED),
            LEMON_TREE, SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> GRAPEFRUIT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.GRAPEFRUIT_TREE_CONFIGURED),
            GRAPEFRUIT_TREE, SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> KUMQUAT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.KUMQUAT_TREE_CONFIGURED),
            KUMQUAT_TREE, SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> PEACH_TREE_CONFIGURED = register(createIdentifier(FeatureNames.PEACH_TREE_CONFIGURED),
            PEACH_TREE, SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> COCONUT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.COCONUT_TREE_CONFIGURED),
            COCONUT_TREE, SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> NUTMEG_TREE_CONFIGURED = register(createIdentifier(FeatureNames.NUTMEG_TREE_CONFIGURED),
            NUTMEG_TREE, SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> FIG_TREE_CONFIGURED = register(createIdentifier(FeatureNames.FIG_TREE_CONFIGURED),
            FIG_TREE, SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> NECTARINE_TREE_CONFIGURED = register(createIdentifier(FeatureNames.NECTARINE_TREE_CONFIGURED),
            NECTARINE_TREE, SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> MANGO_TREE_CONFIGURED = register(createIdentifier(FeatureNames.MANGO_TREE_CONFIGURED),
            MANGO_TREE, SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> DRAGON_FRUIT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.DRAGON_FRUIT_TREE_CONFIGURED),
            DRAGON_FRUIT_TREE, SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> STAR_FRUIT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.STAR_FRUIT_TREE_CONFIGURED),
            STAR_FRUIT_TREE, SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> AVOCADO_TREE_CONFIGURED = register(createIdentifier(FeatureNames.AVOCADO_TREE_CONFIGURED),
            AVOCADO_TREE, SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> APRICOT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.APRICOT_TREE_CONFIGURED),
            APRICOT_TREE, SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> PEAR_TREE_CONFIGURED = register(createIdentifier(FeatureNames.PEAR_TREE_CONFIGURED),
            PEAR_TREE, SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> LIME_TREE_CONFIGURED = register(createIdentifier(FeatureNames.LIME_TREE_CONFIGURED),
            LIME_TREE, SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> DATE_TREE_CONFIGURED = register(createIdentifier(FeatureNames.DATE_TREE_CONFIGURED),
            DATE_TREE, SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> ALMOND_TREE_CONFIGURED = register(createIdentifier(FeatureNames.ALMOND_TREE_CONFIGURED),
            ALMOND_TREE, SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.25F, 5), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> CASHEW_TREE_CONFIGURED = register(createIdentifier(FeatureNames.CASHEW_TREE_CONFIGURED),
            CASHEW_TREE, SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.25F, 5), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> PECAN_TREE_CONFIGURED = register(createIdentifier(FeatureNames.PECAN_TREE_CONFIGURED),
            PECAN_TREE, SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.25F, 5), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> WALNUT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.WALNUT_TREE_CONFIGURED),
            WALNUT_TREE, SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.25F, 5), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> CINNAMON_TREE_CONFIGURED = register(createIdentifier(FeatureNames.CINNAMON_TREE_CONFIGURED),
            CINNAMON_TREE,SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(1, 0.1F, 6), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());


    public static final RegistryEntry<ConfiguredFeature<DiskFeatureConfig, ?>> DISK_SALT = register(createIdentifier(FeatureNames.DISK_SALT),
            Feature.DISK, ((new DiskFeatureConfig(BlockRegistry.salt.getDefaultState(),
                    UniformIntProvider.create(2, 4), 2,
                    ImmutableList.of(Blocks.DIRT.getDefaultState(), Blocks.GRASS_BLOCK.getDefaultState())))));

    public static final RegistryEntry<PlacedFeature> DISK_SALT_CONFIGURED = register(createIdentifier(FeatureNames.DISK_SALT_CONFIGURED),
            DISK_SALT, PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP, BiomePlacementModifier.of(), SquarePlacementModifier.of());


    public static <FC extends FeatureConfig, F extends Feature<FC>> RegistryEntry<ConfiguredFeature<FC, ?>> register(Identifier id, F feature, FC config) {
        return badRegister(BuiltinRegistries.CONFIGURED_FEATURE, id, new ConfiguredFeature<>(feature, config));
    }

    public static <V extends T, T> RegistryEntry<V> badRegister(Registry<T> registry, Identifier id, V value) {
        return BuiltinRegistries.add((Registry<V>) registry, id, value);
    }
    
    /*public static <FC extends FeatureConfig, F extends Feature<FC>> RegistryEntry<ConfiguredFeature<FC, F>> register(Identifier id, F feature, FC config) {
        return BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_FEATURE, id, new ConfiguredFeature<>(feature, config));
    }*/

    public static RegistryEntry<PlacedFeature> register(Identifier id, RegistryEntry<? extends ConfiguredFeature<?, ?>> registryEntry, List<PlacementModifier> modifiers) {
        RegistryKey<PlacedFeature> key = RegistryKey.of(Registry.PLACED_FEATURE_KEY, id);
        keyMap.put(id.getPath(), key);
        return BuiltinRegistries.add(BuiltinRegistries.PLACED_FEATURE, id, new PlacedFeature(RegistryEntry.upcast(registryEntry), List.copyOf(modifiers)));
    }

    public static RegistryEntry<PlacedFeature> register(Identifier id, RegistryEntry<? extends ConfiguredFeature<?, ?>> registryEntry, PlacementModifier... modifiers) {
        return register(id, registryEntry, List.of(modifiers));
    }

    public static RegistryKey<PlacedFeature> getFeatureKey(String key) {
        return keyMap.get(key);
    }
}
