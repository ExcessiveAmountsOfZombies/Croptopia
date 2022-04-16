package me.thonk.croptopia.registry;

import com.google.common.collect.ImmutableList;
import me.thonk.common.FeatureNames;
import me.thonk.croptopia.blocks.CroptopiaCropBlock;
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
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static me.thonk.croptopia.Croptopia.createIdentifier;

public class GeneratorRegistry {

    private static final Map<String, RegistryKey<PlacedFeature>> keyMap = new HashMap<>();

    public static final SimpleBlockFeatureConfig config = (new SimpleBlockFeatureConfig(
            new WeightedBlockStateProvider(DataPool.<BlockState>builder()
            .add(Content.Farmland.ARTICHOKE.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.ASPARAGUS.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.BARLEY.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.BASIL.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 20)
            .add(Content.Farmland.BELLPEPPER.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.BLACKBEAN.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.BLACKBERRY.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.BLUEBERRY.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.BROCCOLI.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.CABBAGE.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.CANTALOUPE.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.CAULIFLOWER.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.CELERY.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.COFFEE_BEANS.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 20)
            .add(Content.Farmland.CORN.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.CRANBERRY.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.CUCUMBER.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.CURRANT.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.EGGPLANT.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 20)
            .add(Content.Farmland.ELDERBERRY.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.GARLIC.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 20)
            .add(Content.Farmland.GINGER.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.GRAPE.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.GREENBEAN.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.GREENONION.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 20)
            .add(Content.Farmland.HONEYDEW.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 20)
            .add(Content.Farmland.HOPS.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.KALE.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.KIWI.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.LEEK.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.LETTUCE.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.MUSTARD.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.OAT.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.OLIVE.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.ONION.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 20)
            .add(Content.Farmland.PEANUT.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 20)
            .add(Content.Farmland.CHILE_PEPPER.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.PINEAPPLE.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 20)
            .add(Content.Farmland.RADISH.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.RASPBERRY.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.RHUBARB.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 20)
            .add(Content.Farmland.RICE.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 20)
            .add(Content.Farmland.RUTABAGA.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.SAGUARO.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.SOYBEAN.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.SPINACH.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.SQUASH.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.STRAWBERRY.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.SWEETPOTATO.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.TOMATILLO.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.TOMATO.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.TURMERIC.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.TURNIP.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 20)
            .add(Content.Farmland.YAM.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.ZUCCHINI.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.VANILLA.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 20)
            .add(Content.Farmland.PEPPER.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.TEA_LEAVES.asBlock().getDefaultState().with(CroptopiaCropBlock.AGE, 7), 10)
            .build())));

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> RANDOM_CROP = register(createIdentifier("random_crop"), Feature.RANDOM_PATCH,
            ConfiguredFeatures.createRandomPatchFeatureConfig(6, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, config)));

    public static final RegistryEntry<PlacedFeature> RANDOM_CROP_PLACED = register(createIdentifier("random_crop"),
            RANDOM_CROP, CountPlacementModifier.of(3), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> CINNAMON_TREE = register(createIdentifier(FeatureNames.CINNAMON_TREE),
            Feature.TREE, ((new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(BlockRegistry.cinnamonLog.getDefaultState()),
                    new StraightTrunkPlacer(4, 3, 0),
                    new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(LeavesRegistry.cinnamonLeaves.getDefaultState(), 90).build()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));

    public static final RegistryEntry<PlacedFeature> APPLE_TREE_CONFIGURED = register(createIdentifier(FeatureNames.APPLE_TREE_CONFIGURED),
            Content.Tree.APPLE.getTreeGen(), SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> BANANA_TREE_CONFIGURED = register(createIdentifier(FeatureNames.BANANA_TREE_CONFIGURED),
            Content.Tree.BANANA.getTreeGen(), SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> ORANGE_TREE_CONFIGURED = register(createIdentifier(FeatureNames.ORANGE_TREE_CONFIGURED),
            Content.Tree.ORANGE.getTreeGen(), SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> PERSIMMON_TREE_CONFIGURED = register(createIdentifier(FeatureNames.PERSIMMON_TREE_CONFIGURED),
            Content.Tree.PERSIMMON.getTreeGen(), SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> PLUM_TREE_CONFIGURED = register(createIdentifier(FeatureNames.PLUM_TREE_CONFIGURED),
            Content.Tree.PLUM.getTreeGen(), SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> CHERRY_TREE_CONFIGURED = register(createIdentifier(FeatureNames.CHERRY_TREE_CONFIGURED),
            Content.Tree.CHERRY.getTreeGen(), SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> LEMON_TREE_CONFIGURED = register(createIdentifier(FeatureNames.LEMON_TREE_CONFIGURED),
            Content.Tree.LEMON.getTreeGen(), SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> GRAPEFRUIT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.GRAPEFRUIT_TREE_CONFIGURED),
            Content.Tree.GRAPEFRUIT.getTreeGen(), SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> KUMQUAT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.KUMQUAT_TREE_CONFIGURED),
            Content.Tree.KUMQUAT.getTreeGen(), SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> PEACH_TREE_CONFIGURED = register(createIdentifier(FeatureNames.PEACH_TREE_CONFIGURED),
            Content.Tree.PEACH.getTreeGen(), SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> COCONUT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.COCONUT_TREE_CONFIGURED),
            Content.Tree.COCONUT.getTreeGen(), SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.2F, 5), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> NUTMEG_TREE_CONFIGURED = register(createIdentifier(FeatureNames.NUTMEG_TREE_CONFIGURED),
            Content.Tree.NUTMEG.getTreeGen(), SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> FIG_TREE_CONFIGURED = register(createIdentifier(FeatureNames.FIG_TREE_CONFIGURED),
            Content.Tree.FIG.getTreeGen(), SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> NECTARINE_TREE_CONFIGURED = register(createIdentifier(FeatureNames.NECTARINE_TREE_CONFIGURED),
            Content.Tree.NECTARINE.getTreeGen(), SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> MANGO_TREE_CONFIGURED = register(createIdentifier(FeatureNames.MANGO_TREE_CONFIGURED),
            Content.Tree.MANGO.getTreeGen(), SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> DRAGON_FRUIT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.DRAGON_FRUIT_TREE_CONFIGURED),
            Content.Tree.DRAGONFRUIT.getTreeGen(), SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> STAR_FRUIT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.STAR_FRUIT_TREE_CONFIGURED),
            Content.Tree.STARFRUIT.getTreeGen(), SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> AVOCADO_TREE_CONFIGURED = register(createIdentifier(FeatureNames.AVOCADO_TREE_CONFIGURED),
            Content.Tree.AVOCADO.getTreeGen(), SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> APRICOT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.APRICOT_TREE_CONFIGURED),
            Content.Tree.APRICOT.getTreeGen(), SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> PEAR_TREE_CONFIGURED = register(createIdentifier(FeatureNames.PEAR_TREE_CONFIGURED),
            Content.Tree.PEAR.getTreeGen(), SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> LIME_TREE_CONFIGURED = register(createIdentifier(FeatureNames.LIME_TREE_CONFIGURED),
            Content.Tree.LIME.getTreeGen(), SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> DATE_TREE_CONFIGURED = register(createIdentifier(FeatureNames.DATE_TREE_CONFIGURED),
            Content.Tree.DATE.getTreeGen(), SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> ALMOND_TREE_CONFIGURED = register(createIdentifier(FeatureNames.ALMOND_TREE_CONFIGURED),
            Content.Tree.ALMOND.getTreeGen(), SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.25F, 5), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> CASHEW_TREE_CONFIGURED = register(createIdentifier(FeatureNames.CASHEW_TREE_CONFIGURED),
            Content.Tree.CASHEW.getTreeGen(), SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.25F, 5), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> PECAN_TREE_CONFIGURED = register(createIdentifier(FeatureNames.PECAN_TREE_CONFIGURED),
            Content.Tree.PECAN.getTreeGen(), SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.25F, 5), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> WALNUT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.WALNUT_TREE_CONFIGURED),
            Content.Tree.WALNUT.getTreeGen(), SquarePlacementModifier.of(), PlacedFeatures.createCountExtraModifier(0, 0.25F, 5), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());

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
