package me.thonk.croptopia.registry;

import com.google.common.collect.ImmutableList;
import me.thonk.common.FeatureNames;
import me.thonk.croptopia.blocks.CroptopiaCropBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static me.thonk.croptopia.Croptopia.createIdentifier;

public class GeneratorRegistry {

    private static final Map<String, ResourceKey<PlacedFeature>> keyMap = new HashMap<>();

    public static final SimpleBlockConfiguration config = (new SimpleBlockConfiguration(
            new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
            .add(Content.Farmland.ARTICHOKE.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.ASPARAGUS.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.BARLEY.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.BASIL.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
            .add(Content.Farmland.BELLPEPPER.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.BLACKBEAN.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.BLACKBERRY.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.BLUEBERRY.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.BROCCOLI.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.CABBAGE.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.CANTALOUPE.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.CAULIFLOWER.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.CELERY.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.COFFEE_BEANS.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
            .add(Content.Farmland.CORN.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.CRANBERRY.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.CUCUMBER.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.CURRANT.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.EGGPLANT.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
            .add(Content.Farmland.ELDERBERRY.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.GARLIC.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
            .add(Content.Farmland.GINGER.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.GRAPE.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.GREENBEAN.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.GREENONION.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 60)
            .add(Content.Farmland.HONEYDEW.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
            .add(Content.Farmland.HOPS.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.KALE.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.KIWI.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.LEEK.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.LETTUCE.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.MUSTARD.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.OAT.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.OLIVE.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.ONION.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
            .add(Content.Farmland.PEANUT.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
            .add(Content.Farmland.CHILE_PEPPER.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.PINEAPPLE.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
            .add(Content.Farmland.RADISH.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.RASPBERRY.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.RHUBARB.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
            .add(Content.Farmland.RICE.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
            .add(Content.Farmland.RUTABAGA.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.SAGUARO.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.SOYBEAN.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.SPINACH.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.SQUASH.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.STRAWBERRY.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.SWEETPOTATO.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.TOMATILLO.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.TOMATO.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.TURMERIC.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.TURNIP.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
            .add(Content.Farmland.YAM.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.ZUCCHINI.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.VANILLA.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
            .add(Content.Farmland.PEPPER.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .add(Content.Farmland.TEA_LEAVES.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
            .build())));

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> RANDOM_CROP = register(createIdentifier("random_crop"), Feature.RANDOM_PATCH,
            FeatureUtils.simpleRandomPatchConfiguration(6, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, config)));

    public static final Holder<PlacedFeature> RANDOM_CROP_PLACED = register(createIdentifier("random_crop"),
            RANDOM_CROP, CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

    public static final Holder<PlacedFeature> APPLE_TREE_CONFIGURED = register(createIdentifier(FeatureNames.APPLE_TREE_CONFIGURED),
            Content.Tree.APPLE.getTreeGen(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(35), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> BANANA_TREE_CONFIGURED = register(createIdentifier(FeatureNames.BANANA_TREE_CONFIGURED),
            Content.Tree.BANANA.getTreeGen(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(35), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> ORANGE_TREE_CONFIGURED = register(createIdentifier(FeatureNames.ORANGE_TREE_CONFIGURED),
            Content.Tree.ORANGE.getTreeGen(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(35), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> PERSIMMON_TREE_CONFIGURED = register(createIdentifier(FeatureNames.PERSIMMON_TREE_CONFIGURED),
            Content.Tree.PERSIMMON.getTreeGen(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(35), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> PLUM_TREE_CONFIGURED = register(createIdentifier(FeatureNames.PLUM_TREE_CONFIGURED),
            Content.Tree.PLUM.getTreeGen(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(35), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> CHERRY_TREE_CONFIGURED = register(createIdentifier(FeatureNames.CHERRY_TREE_CONFIGURED),
            Content.Tree.CHERRY.getTreeGen(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(35), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> LEMON_TREE_CONFIGURED = register(createIdentifier(FeatureNames.LEMON_TREE_CONFIGURED),
            Content.Tree.LEMON.getTreeGen(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(35), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> GRAPEFRUIT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.GRAPEFRUIT_TREE_CONFIGURED),
            Content.Tree.GRAPEFRUIT.getTreeGen(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(35), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> KUMQUAT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.KUMQUAT_TREE_CONFIGURED),
            Content.Tree.KUMQUAT.getTreeGen(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(35), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> PEACH_TREE_CONFIGURED = register(createIdentifier(FeatureNames.PEACH_TREE_CONFIGURED),
            Content.Tree.PEACH.getTreeGen(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(35), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> COCONUT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.COCONUT_TREE_CONFIGURED),
            Content.Tree.COCONUT.getTreeGen(), InSquarePlacement.spread(), PlacementUtils.countExtra(0, 0.2F, 5), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> NUTMEG_TREE_CONFIGURED = register(createIdentifier(FeatureNames.NUTMEG_TREE_CONFIGURED),
            Content.Tree.NUTMEG.getTreeGen(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(35), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> FIG_TREE_CONFIGURED = register(createIdentifier(FeatureNames.FIG_TREE_CONFIGURED),
            Content.Tree.FIG.getTreeGen(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(35), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> NECTARINE_TREE_CONFIGURED = register(createIdentifier(FeatureNames.NECTARINE_TREE_CONFIGURED),
            Content.Tree.NECTARINE.getTreeGen(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(35), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> MANGO_TREE_CONFIGURED = register(createIdentifier(FeatureNames.MANGO_TREE_CONFIGURED),
            Content.Tree.MANGO.getTreeGen(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(35), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> DRAGON_FRUIT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.DRAGON_FRUIT_TREE_CONFIGURED),
            Content.Tree.DRAGONFRUIT.getTreeGen(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(35), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> STAR_FRUIT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.STAR_FRUIT_TREE_CONFIGURED),
            Content.Tree.STARFRUIT.getTreeGen(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(35), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> AVOCADO_TREE_CONFIGURED = register(createIdentifier(FeatureNames.AVOCADO_TREE_CONFIGURED),
            Content.Tree.AVOCADO.getTreeGen(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(35), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> APRICOT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.APRICOT_TREE_CONFIGURED),
            Content.Tree.APRICOT.getTreeGen(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(35), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> PEAR_TREE_CONFIGURED = register(createIdentifier(FeatureNames.PEAR_TREE_CONFIGURED),
            Content.Tree.PEAR.getTreeGen(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(35), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> LIME_TREE_CONFIGURED = register(createIdentifier(FeatureNames.LIME_TREE_CONFIGURED),
            Content.Tree.LIME.getTreeGen(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(35), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> DATE_TREE_CONFIGURED = register(createIdentifier(FeatureNames.DATE_TREE_CONFIGURED),
            Content.Tree.DATE.getTreeGen(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(35), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> ALMOND_TREE_CONFIGURED = register(createIdentifier(FeatureNames.ALMOND_TREE_CONFIGURED),
            Content.Tree.ALMOND.getTreeGen(), InSquarePlacement.spread(), PlacementUtils.countExtra(0, 0.25F, 5), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> CASHEW_TREE_CONFIGURED = register(createIdentifier(FeatureNames.CASHEW_TREE_CONFIGURED),
            Content.Tree.CASHEW.getTreeGen(), InSquarePlacement.spread(), PlacementUtils.countExtra(0, 0.25F, 5), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> PECAN_TREE_CONFIGURED = register(createIdentifier(FeatureNames.PECAN_TREE_CONFIGURED),
            Content.Tree.PECAN.getTreeGen(), InSquarePlacement.spread(), PlacementUtils.countExtra(0, 0.25F, 5), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> WALNUT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.WALNUT_TREE_CONFIGURED),
            Content.Tree.WALNUT.getTreeGen(), InSquarePlacement.spread(), PlacementUtils.countExtra(0, 0.25F, 5), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> CINNAMON_TREE_CONFIGURED = register(createIdentifier(FeatureNames.CINNAMON_TREE_CONFIGURED),
            Content.Bark.CINNAMON.getTreeGen(),InSquarePlacement.spread(), PlacementUtils.countExtra(1, 0.1F, 6), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());


    public static final Holder<ConfiguredFeature<DiskConfiguration, ?>> DISK_SALT = register(createIdentifier(FeatureNames.DISK_SALT),
            Feature.DISK, ((new DiskConfiguration(Content.SALT_ORE_BLOCK.defaultBlockState(),
                    UniformInt.of(2, 4), 2,
                    ImmutableList.of(Blocks.DIRT.defaultBlockState(), Blocks.GRASS_BLOCK.defaultBlockState())))));

    public static final Holder<PlacedFeature> DISK_SALT_CONFIGURED = register(createIdentifier(FeatureNames.DISK_SALT_CONFIGURED),
            DISK_SALT, PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome(), InSquarePlacement.spread());


    public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<FC, ?>> register(ResourceLocation id, F feature, FC config) {
        return badRegister(BuiltinRegistries.CONFIGURED_FEATURE, id, new ConfiguredFeature<>(feature, config));
    }

    public static <V extends T, T> Holder<V> badRegister(Registry<T> registry, ResourceLocation id, V value) {
        return BuiltinRegistries.register((Registry<V>) registry, id, value);
    }

    /*public static <FC extends FeatureConfig, F extends Feature<FC>> RegistryEntry<ConfiguredFeature<FC, F>> register(Identifier id, F feature, FC config) {
        return BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_FEATURE, id, new ConfiguredFeature<>(feature, config));
    }*/

    public static Holder<PlacedFeature> register(ResourceLocation id, Holder<? extends ConfiguredFeature<?, ?>> registryEntry, List<PlacementModifier> modifiers) {
        ResourceKey<PlacedFeature> key = ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, id);
        keyMap.put(id.getPath(), key);
        return BuiltinRegistries.register(BuiltinRegistries.PLACED_FEATURE, id, new PlacedFeature(Holder.hackyErase(registryEntry), List.copyOf(modifiers)));
    }

    public static Holder<PlacedFeature> register(ResourceLocation id, Holder<? extends ConfiguredFeature<?, ?>> registryEntry, PlacementModifier... modifiers) {
        return register(id, registryEntry, List.of(modifiers));
    }

    public static ResourceKey<PlacedFeature> getFeatureKey(String key) {
        return keyMap.get(key);
    }
}
