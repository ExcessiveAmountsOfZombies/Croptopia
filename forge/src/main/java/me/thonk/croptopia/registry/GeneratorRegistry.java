package me.thonk.croptopia.registry;

import com.google.common.collect.ImmutableList;
import me.thonk.common.FeatureNames;
import me.thonk.croptopia.blocks.CroptopiaCropBlock;
import me.thonk.croptopia.blocks.LeafCropBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.HashMap;
import java.util.Map;

import static me.thonk.croptopia.CroptopiaForge.createIdentifier;

public class GeneratorRegistry {

    private static Map<String, ResourceKey<PlacedFeature>> keyMap = new HashMap<>();
    private static Map<ResourceKey<ConfiguredFeature<?, ?>>, ConfiguredFeature<?, ?>> featureMap = new HashMap<>();

    public static final ConfiguredFeature<?, ?> randomCrops = Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(
            new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                    .add(BlockRegistry.artichokeCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.asparagusCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.barleyCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.basilCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
                    .add(BlockRegistry.bellPepperCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.blackBeanCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.blackberryCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.blueberryCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.broccoliCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.cabbageCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.cantaloupeCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.cauliflowerCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.celeryCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.coffeeCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
                    .add(BlockRegistry.cornCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.cranberryCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.cucumberCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.currantCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.eggplantCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
                    .add(BlockRegistry.elderberryCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.garlicCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
                    .add(BlockRegistry.gingerCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.grapeCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.greenBeanCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.greenOnionCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
                    .add(BlockRegistry.honeydewCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
                    .add(BlockRegistry.hopsCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.kaleCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.kiwiCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.leekCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.lettuceCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.mustardCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.oatCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.oliveCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.onionCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
                    .add(BlockRegistry.peanutCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
                    .add(BlockRegistry.chilePepperCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.pineappleCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
                    .add(BlockRegistry.radishCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.raspberryCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.rhubarbCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
                    .add(BlockRegistry.riceCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
                    .add(BlockRegistry.rutabagaCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.saguaroCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.soybeanCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.spinachCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.squashCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.strawberryCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.sweetPotatoCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.tomatilloCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.tomatoCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.turmericCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.turnipCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
                    .add(BlockRegistry.yamCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.zucchiniCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.vanillaCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
                    .add(BlockRegistry.pepperCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(BlockRegistry.teaCropBlock.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .build())));

    public static final PlacedFeature RANDOM_CROP = register(createIdentifier("random_crop"),
            Feature.RANDOM_PATCH.configured(FeatureUtils.simpleRandomPatchConfiguration(3, randomCrops.onlyWhenEmpty())).placed(CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));

    public static final ConfiguredFeature<TreeConfiguration, ?> APPLE_TREE = register(createIdentifier(FeatureNames.APPLE_TREE),
            Feature.TREE.configured(new TreeConfiguration.TreeConfigurationBuilder(
                    SimpleStateProvider.simple(Blocks.OAK_LOG.defaultBlockState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.OAK_LEAVES.defaultBlockState(), 90).add(LeavesRegistry.appleCrop.defaultBlockState().setValue(LeafCropBlock.AGE, 3), 10).build()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build()));

    public static final ConfiguredFeature<TreeConfiguration, ?> BANANA_TREE = register(createIdentifier(FeatureNames.BANANA_TREE),
            Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(
                    SimpleStateProvider.simple(Blocks.JUNGLE_LOG.defaultBlockState()),
                    new StraightTrunkPlacer(4, 8, 0),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.defaultBlockState(), 90).add(LeavesRegistry.bananaCrop.defaultBlockState().setValue(LeafCropBlock.AGE, 3), 10).build()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));


    public static final ConfiguredFeature<TreeConfiguration, ?> ORANGE_TREE = register(createIdentifier(FeatureNames.ORANGE_TREE),
            Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(
                    SimpleStateProvider.simple(Blocks.OAK_LOG.defaultBlockState()),
                    new StraightTrunkPlacer(4, 4, 0),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.OAK_LEAVES.defaultBlockState(), 90).add(LeavesRegistry.orangeCrop.defaultBlockState().setValue(LeafCropBlock.AGE, 3), 10).build()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeConfiguration, ?> PERSIMMON_TREE = register(createIdentifier(FeatureNames.PERSIMMON_TREE),
            Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(
                    SimpleStateProvider.simple(Blocks.OAK_LOG.defaultBlockState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.OAK_LEAVES.defaultBlockState(), 90).add(LeavesRegistry.persimmonCrop.defaultBlockState().setValue(LeafCropBlock.AGE, 3), 10).build()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeConfiguration, ?> PLUM_TREE = register(createIdentifier(FeatureNames.PLUM_TREE),
            Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(
                    SimpleStateProvider.simple(Blocks.OAK_LOG.defaultBlockState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.OAK_LEAVES.defaultBlockState(), 90).add(LeavesRegistry.plumCrop.defaultBlockState().setValue(LeafCropBlock.AGE, 3), 10).build()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeConfiguration, ?> CHERRY_TREE = register(createIdentifier(FeatureNames.CHERRY_TREE),
            Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(
                    SimpleStateProvider.simple(Blocks.OAK_LOG.defaultBlockState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.OAK_LEAVES.defaultBlockState(), 90).add(LeavesRegistry.cherryCrop.defaultBlockState().setValue(LeafCropBlock.AGE, 3), 10).build()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeConfiguration, ?> LEMON_TREE = register(createIdentifier(FeatureNames.LEMON_TREE),
            Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(
                    SimpleStateProvider.simple(Blocks.OAK_LOG.defaultBlockState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.OAK_LEAVES.defaultBlockState(), 90).add(LeavesRegistry.lemonCrop.defaultBlockState().setValue(LeafCropBlock.AGE, 3), 10).build()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeConfiguration, ?> GRAPEFRUIT_TREE = register(createIdentifier(FeatureNames.GRAPEFRUIT_TREE),
            Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(
                    SimpleStateProvider.simple(Blocks.JUNGLE_LOG.defaultBlockState()),
                    new StraightTrunkPlacer(4, 8, 0),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.defaultBlockState(), 90).add(LeavesRegistry.grapefruitCrop.defaultBlockState().setValue(LeafCropBlock.AGE, 3), 10).build()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeConfiguration, ?> KUMQUAT_TREE = register(createIdentifier(FeatureNames.KUMQUAT_TREE),
            Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(
                    SimpleStateProvider.simple(Blocks.JUNGLE_LOG.defaultBlockState()),
                    new StraightTrunkPlacer(4, 8, 0),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.defaultBlockState(), 90).add(LeavesRegistry.kumquatCrop.defaultBlockState().setValue(LeafCropBlock.AGE, 3), 10).build()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeConfiguration, ?> PEACH_TREE = register(createIdentifier(FeatureNames.PEACH_TREE),
            Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(
                    SimpleStateProvider.simple(Blocks.OAK_LOG.defaultBlockState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.OAK_LEAVES.defaultBlockState(), 90).add(LeavesRegistry.peachCrop.defaultBlockState().setValue(LeafCropBlock.AGE, 3), 10).build()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeConfiguration, ?> COCONUT_TREE = register(createIdentifier(FeatureNames.COCONUT_TREE),
            Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(
                    SimpleStateProvider.simple(Blocks.JUNGLE_LOG.defaultBlockState()),
                    new ForkingTrunkPlacer(5, 2, 3),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.defaultBlockState(), 90).add(LeavesRegistry.coconutCrop.defaultBlockState().setValue(LeafCropBlock.AGE, 3), 10).build()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 1),
                    new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeConfiguration, ?> NUTMEG_TREE = register(createIdentifier(FeatureNames.NUTMEG_TREE),
            Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(
                    SimpleStateProvider.simple(Blocks.JUNGLE_LOG.defaultBlockState()),
                    new StraightTrunkPlacer(4, 8, 0),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.defaultBlockState(), 90).add(LeavesRegistry.nutmegCrop.defaultBlockState().setValue(LeafCropBlock.AGE, 3), 10).build()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeConfiguration, ?> FIG_TREE = register(createIdentifier(FeatureNames.FIG_TREE),
            Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(
                    SimpleStateProvider.simple(Blocks.JUNGLE_LOG.defaultBlockState()),
                    new StraightTrunkPlacer(4, 8, 0),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.defaultBlockState(), 90).add(LeavesRegistry.figCrop.defaultBlockState().setValue(LeafCropBlock.AGE, 3), 10).build()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeConfiguration, ?> NECTARINE_TREE = register(createIdentifier(FeatureNames.NECTARINE_TREE),
            Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(
                    SimpleStateProvider.simple(Blocks.OAK_LOG.defaultBlockState()),
                    new StraightTrunkPlacer(4, 4, 0),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.OAK_LEAVES.defaultBlockState(), 90).add(LeavesRegistry.nectarineCrop.defaultBlockState().setValue(LeafCropBlock.AGE, 3), 10).build()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeConfiguration, ?> MANGO_TREE = register(createIdentifier(FeatureNames.MANGO_TREE),
            Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(
                    SimpleStateProvider.simple(Blocks.JUNGLE_LOG.defaultBlockState()),
                    new StraightTrunkPlacer(5, 8, 0),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.defaultBlockState(), 90).add(LeavesRegistry.mangoCrop.defaultBlockState().setValue(LeafCropBlock.AGE, 3), 10).build()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeConfiguration, ?> DRAGON_FRUIT_TREE = register(createIdentifier(FeatureNames.DRAGON_FRUIT_TREE),
            Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(
                    SimpleStateProvider.simple(Blocks.JUNGLE_LOG.defaultBlockState()),
                    new StraightTrunkPlacer(5, 7, 0),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.OAK_LEAVES.defaultBlockState(), 90).add(LeavesRegistry.dragonFruitCrop.defaultBlockState().setValue(LeafCropBlock.AGE, 3), 10).build()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeConfiguration, ?> STAR_FRUIT_TREE = register(createIdentifier(FeatureNames.STAR_FRUIT_TREE),
            Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(
                    SimpleStateProvider.simple(Blocks.OAK_LOG.defaultBlockState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.OAK_LEAVES.defaultBlockState(), 90).add(LeavesRegistry.starFruitCrop.defaultBlockState().setValue(LeafCropBlock.AGE, 3), 10).build()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeConfiguration, ?> AVOCADO_TREE = register(createIdentifier(FeatureNames.AVOCADO_TREE),
            Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(
                    SimpleStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState()),
                    new StraightTrunkPlacer(5, 3, 0),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.OAK_LEAVES.defaultBlockState(), 90).add(LeavesRegistry.avocadoCrop.defaultBlockState().setValue(LeafCropBlock.AGE, 3), 10).build()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeConfiguration, ?> APRICOT_TREE = register(createIdentifier(FeatureNames.APRICOT_TREE),
            Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(
                    SimpleStateProvider.simple(Blocks.OAK_LOG.defaultBlockState()),
                    new StraightTrunkPlacer(5, 2, 0),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.OAK_LEAVES.defaultBlockState(), 90).add(LeavesRegistry.apricotCrop.defaultBlockState().setValue(LeafCropBlock.AGE, 3), 10).build()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeConfiguration, ?> PEAR_TREE = register(createIdentifier(FeatureNames.PEAR_TREE),
            Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(
                    SimpleStateProvider.simple(Blocks.OAK_LOG.defaultBlockState()),
                    new StraightTrunkPlacer(5, 2, 0),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.OAK_LEAVES.defaultBlockState(), 90).add(LeavesRegistry.pearCrop.defaultBlockState().setValue(LeafCropBlock.AGE, 3), 10).build()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeConfiguration, ?> LIME_TREE = register(createIdentifier(FeatureNames.LIME_TREE),
            Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(
                    SimpleStateProvider.simple(Blocks.OAK_LOG.defaultBlockState()),
                    new StraightTrunkPlacer(5, 2, 0),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.OAK_LEAVES.defaultBlockState(), 90).add(LeavesRegistry.limeCrop.defaultBlockState().setValue(LeafCropBlock.AGE, 3), 10).build()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeConfiguration, ?> DATE_TREE = register(createIdentifier(FeatureNames.DATE_TREE),
            Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(
                    SimpleStateProvider.simple(Blocks.JUNGLE_LOG.defaultBlockState()),
                    new StraightTrunkPlacer(5, 8, 0),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.defaultBlockState(), 90).add(LeavesRegistry.dateCrop.defaultBlockState().setValue(LeafCropBlock.AGE, 3), 10).build()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeConfiguration, ?> ALMOND_TREE = register(createIdentifier(FeatureNames.ALMOND_TREE),
            Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(
                    SimpleStateProvider.simple(Blocks.DARK_OAK_LOG.defaultBlockState()),
                    new StraightTrunkPlacer(4, 3, 0),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.DARK_OAK_LEAVES.defaultBlockState(), 90).add(LeavesRegistry.almondCrop.defaultBlockState().setValue(LeafCropBlock.AGE, 3), 10).build()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeConfiguration, ?> CASHEW_TREE = register(createIdentifier(FeatureNames.CASHEW_TREE),
            Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(
                    SimpleStateProvider.simple(Blocks.DARK_OAK_LOG.defaultBlockState()),
                    new StraightTrunkPlacer(4, 3, 0),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.DARK_OAK_LEAVES.defaultBlockState(), 90).add(LeavesRegistry.cashewCrop.defaultBlockState().setValue(LeafCropBlock.AGE, 3), 10).build()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeConfiguration, ?> PECAN_TREE = register(createIdentifier(FeatureNames.PECAN_TREE),
            Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(
                    SimpleStateProvider.simple(Blocks.DARK_OAK_LOG.defaultBlockState()),
                    new StraightTrunkPlacer(4, 3, 0),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.DARK_OAK_LEAVES.defaultBlockState(), 90).add(LeavesRegistry.pecanCrop.defaultBlockState().setValue(LeafCropBlock.AGE, 3), 10).build()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeConfiguration, ?> WALNUT_TREE = register(createIdentifier(FeatureNames.WALNUT_TREE),
            Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(
                    SimpleStateProvider.simple(Blocks.DARK_OAK_LOG.defaultBlockState()),
                    new StraightTrunkPlacer(4, 3, 0),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.DARK_OAK_LEAVES.defaultBlockState(), 90).add(LeavesRegistry.walnutCrop.defaultBlockState().setValue(LeafCropBlock.AGE, 3), 10).build()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeConfiguration, ?> CINNAMON_TREE = register(createIdentifier(FeatureNames.CINNAMON_TREE),
            Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(
                    SimpleStateProvider.simple(BlockRegistry.cinnamonLog.defaultBlockState()),
                    new StraightTrunkPlacer(4, 3, 0),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(LeavesRegistry.cinnamonLeaves.defaultBlockState(), 90).build()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));

    public static final PlacedFeature APPLE_TREE_CONFIGURED = register(createIdentifier(FeatureNames.APPLE_TREE_CONFIGURED),
            APPLE_TREE.placed(InSquarePlacement.spread(), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome(), PlacementUtils.countExtra(0, 0.1F, 1)));

    public static final PlacedFeature BANANA_TREE_CONFIGURED = register(createIdentifier(FeatureNames.BANANA_TREE_CONFIGURED),
            BANANA_TREE.placed(InSquarePlacement.spread(), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome(), PlacementUtils.countExtra(0, 0.1F, 1)));

    public static final PlacedFeature ORANGE_TREE_CONFIGURED = register(createIdentifier(FeatureNames.ORANGE_TREE_CONFIGURED),
            ORANGE_TREE.placed(InSquarePlacement.spread(), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome(), PlacementUtils.countExtra(0, 0.1F, 1)));

    public static final PlacedFeature PERSIMMON_TREE_CONFIGURED = register(createIdentifier(FeatureNames.PERSIMMON_TREE_CONFIGURED),
            PERSIMMON_TREE.placed(InSquarePlacement.spread(), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome(), PlacementUtils.countExtra(0, 0.1F, 1)));

    public static final PlacedFeature PLUM_TREE_CONFIGURED = register(createIdentifier(FeatureNames.PLUM_TREE_CONFIGURED),
            PLUM_TREE.placed(InSquarePlacement.spread(), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome(), PlacementUtils.countExtra(0, 0.1F, 1)));

    public static final PlacedFeature CHERRY_TREE_CONFIGURED = register(createIdentifier(FeatureNames.CHERRY_TREE_CONFIGURED),
            CHERRY_TREE.placed(InSquarePlacement.spread(), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome(), PlacementUtils.countExtra(0, 0.1F, 1)));

    public static final PlacedFeature LEMON_TREE_CONFIGURED = register(createIdentifier(FeatureNames.LEMON_TREE_CONFIGURED),
            LEMON_TREE.placed(InSquarePlacement.spread(), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome(), PlacementUtils.countExtra(0, 0.1F, 1)));

    public static final PlacedFeature GRAPEFRUIT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.GRAPEFRUIT_TREE_CONFIGURED),
            GRAPEFRUIT_TREE.placed(InSquarePlacement.spread(), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome(), PlacementUtils.countExtra(0, 0.1F, 1)));

    public static final PlacedFeature KUMQUAT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.KUMQUAT_TREE_CONFIGURED),
            KUMQUAT_TREE.placed(InSquarePlacement.spread(), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome(), PlacementUtils.countExtra(0, 0.1F, 1)));

    public static final PlacedFeature PEACH_TREE_CONFIGURED = register(createIdentifier(FeatureNames.PEACH_TREE_CONFIGURED),
            PEACH_TREE.placed(InSquarePlacement.spread(), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome(), PlacementUtils.countExtra(0, 0.2F, 5)));

    public static final PlacedFeature COCONUT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.COCONUT_TREE_CONFIGURED),
            COCONUT_TREE.placed(InSquarePlacement.spread(), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome(), PlacementUtils.countExtra(0, 0.2F, 5)));

    public static final PlacedFeature NUTMEG_TREE_CONFIGURED = register(createIdentifier(FeatureNames.NUTMEG_TREE_CONFIGURED),
            NUTMEG_TREE.placed(InSquarePlacement.spread(), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome(), PlacementUtils.countExtra(0, 0.1F, 1)));

    public static final PlacedFeature FIG_TREE_CONFIGURED = register(createIdentifier(FeatureNames.FIG_TREE_CONFIGURED),
            FIG_TREE.placed(InSquarePlacement.spread(), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome(), PlacementUtils.countExtra(0, 0.1F, 1)));

    public static final PlacedFeature NECTARINE_TREE_CONFIGURED = register(createIdentifier(FeatureNames.NECTARINE_TREE_CONFIGURED),
            NECTARINE_TREE.placed(InSquarePlacement.spread(), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome(), PlacementUtils.countExtra(0, 0.1F, 1)));

    public static final PlacedFeature MANGO_TREE_CONFIGURED = register(createIdentifier(FeatureNames.MANGO_TREE_CONFIGURED),
            MANGO_TREE.placed(InSquarePlacement.spread(), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome(), PlacementUtils.countExtra(0, 0.1F, 1)));

    public static final PlacedFeature DRAGON_FRUIT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.DRAGON_FRUIT_TREE_CONFIGURED),
            DRAGON_FRUIT_TREE.placed(InSquarePlacement.spread(), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome(), PlacementUtils.countExtra(0, 0.1F, 1)));

    public static final PlacedFeature STAR_FRUIT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.STAR_FRUIT_TREE_CONFIGURED),
            STAR_FRUIT_TREE.placed(InSquarePlacement.spread(), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome(), PlacementUtils.countExtra(0, 0.1F, 1)));

    public static final PlacedFeature AVOCADO_TREE_CONFIGURED = register(createIdentifier(FeatureNames.AVOCADO_TREE_CONFIGURED),
            AVOCADO_TREE.placed(InSquarePlacement.spread(), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome(), PlacementUtils.countExtra(0, 0.1F, 1)));

    public static final PlacedFeature APRICOT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.APRICOT_TREE_CONFIGURED),
            APRICOT_TREE.placed(InSquarePlacement.spread(), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome(), PlacementUtils.countExtra(0, 0.1F, 1)));

    public static final PlacedFeature PEAR_TREE_CONFIGURED = register(createIdentifier(FeatureNames.PEAR_TREE_CONFIGURED),
            PEAR_TREE.placed(InSquarePlacement.spread(), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome(), PlacementUtils.countExtra(0, 0.1F, 1)));

    public static final PlacedFeature LIME_TREE_CONFIGURED = register(createIdentifier(FeatureNames.LIME_TREE_CONFIGURED),
            LIME_TREE.placed(InSquarePlacement.spread(), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome(), PlacementUtils.countExtra(0, 0.1F, 1)));

    public static final PlacedFeature DATE_TREE_CONFIGURED = register(createIdentifier(FeatureNames.DATE_TREE_CONFIGURED),
            DATE_TREE.placed(InSquarePlacement.spread(), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome(), PlacementUtils.countExtra(0, 0.1F, 1)));

    public static final PlacedFeature ALMOND_TREE_CONFIGURED = register(createIdentifier(FeatureNames.ALMOND_TREE_CONFIGURED),
            ALMOND_TREE.placed(InSquarePlacement.spread(), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome(), PlacementUtils.countExtra(0, 0.25F, 5)));

    public static final PlacedFeature CASHEW_TREE_CONFIGURED = register(createIdentifier(FeatureNames.CASHEW_TREE_CONFIGURED),
            CASHEW_TREE.placed(InSquarePlacement.spread(), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome(), PlacementUtils.countExtra(0, 0.25F, 5)));

    public static final PlacedFeature PECAN_TREE_CONFIGURED = register(createIdentifier(FeatureNames.PECAN_TREE_CONFIGURED),
            PECAN_TREE.placed(InSquarePlacement.spread(), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome(), PlacementUtils.countExtra(0, 0.25F, 5)));

    public static final PlacedFeature WALNUT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.WALNUT_TREE_CONFIGURED),
            WALNUT_TREE.placed(InSquarePlacement.spread(), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome(), PlacementUtils.countExtra(0, 0.25F, 5)));

    public static final PlacedFeature CINNAMON_TREE_CONFIGURED = register(createIdentifier(FeatureNames.CINNAMON_TREE_CONFIGURED),
            CINNAMON_TREE.placed(InSquarePlacement.spread(), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome(), PlacementUtils.countExtra(0, 0.25F, 5)));

    public static final ConfiguredFeature<?, ?> DISK_SALT = register(createIdentifier(FeatureNames.DISK_SALT),
            Feature.DISK.configured((new DiskConfiguration(BlockRegistry.salt.defaultBlockState(),
                    UniformInt.of(2, 4), 2,
                    ImmutableList.of(Blocks.DIRT.defaultBlockState(), Blocks.GRASS_BLOCK.defaultBlockState())))));

    public static final PlacedFeature DISK_SALT_CONFIGURED = register(createIdentifier(FeatureNames.DISK_SALT_CONFIGURED),
            DISK_SALT.placed(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome(), InSquarePlacement.spread()));


    private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(ResourceLocation id, ConfiguredFeature<FC, ?> withConfigurationdFeature) {
        ConfiguredFeature<FC, ?> feature = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, id, withConfigurationdFeature);
        ResourceKey<ConfiguredFeature<?, ?>> key = ResourceKey.create(Registry.CONFIGURED_FEATURE_REGISTRY, id);
        featureMap.put(key, feature);
        return feature;
    }

    public static PlacedFeature register(ResourceLocation id, PlacedFeature feature) {
        PlacedFeature placedFeature = Registry.register(BuiltinRegistries.PLACED_FEATURE, id, feature);
        ResourceKey<PlacedFeature> key = ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, id);
        keyMap.put(id.getPath(), key);
        return placedFeature;
    }

    public static Map<ResourceKey<ConfiguredFeature<?, ?>>, ConfiguredFeature<?, ?>> getFeatureMap() {
        return featureMap;
    }

    public static void init() {}

    public static Map<String, ResourceKey<PlacedFeature>> getKeyMap() {
        return keyMap;
    }

    public static ResourceKey<PlacedFeature> getFeatureKey(String key) {
        return keyMap.get(key);
    }
}
