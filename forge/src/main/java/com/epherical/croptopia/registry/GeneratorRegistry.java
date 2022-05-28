package com.epherical.croptopia.registry;

import com.epherical.croptopia.blocks.CroptopiaCropBlock;
import com.epherical.croptopia.register.Content;
import com.google.common.collect.ImmutableList;
import com.epherical.croptopia.common.FeatureNames;
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

import static com.epherical.croptopia.CroptopiaForge.createIdentifier;

public class GeneratorRegistry {

    private static final Map<String, Holder<PlacedFeature>> keyMap = new HashMap<>();

    public static final SimpleBlockConfiguration config = (new SimpleBlockConfiguration(

            new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                    .add(Content.ARTICHOKE.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.ASPARAGUS.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.BARLEY.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.BASIL.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
                    .add(Content.BELLPEPPER.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.BLACKBEAN.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.BLACKBERRY.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.BLUEBERRY.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.BROCCOLI.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.CABBAGE.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.CANTALOUPE.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.CAULIFLOWER.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.CELERY.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.COFFEE_BEANS.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
                    .add(Content.CORN.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.CRANBERRY.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.CUCUMBER.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.CURRANT.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.EGGPLANT.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
                    .add(Content.ELDERBERRY.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.GARLIC.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
                    .add(Content.GINGER.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.GRAPE.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.GREENBEAN.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.GREENONION.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
                    .add(Content.HONEYDEW.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
                    .add(Content.HOPS.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.KALE.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.KIWI.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.LEEK.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.LETTUCE.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.MUSTARD.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.OAT.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.OLIVE.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.ONION.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
                    .add(Content.PEANUT.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
                    .add(Content.CHILE_PEPPER.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.PINEAPPLE.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
                    .add(Content.RADISH.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.RASPBERRY.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.RHUBARB.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
                    .add(Content.RICE.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
                    .add(Content.RUTABAGA.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.SAGUARO.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.SOYBEAN.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.SPINACH.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.SQUASH.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.STRAWBERRY.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.SWEETPOTATO.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.TOMATILLO.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.TOMATO.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.TURMERIC.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.TURNIP.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
                    .add(Content.YAM.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.ZUCCHINI.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.VANILLA.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
                    .add(Content.PEPPER.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .add(Content.TEA_LEAVES.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                    .build())));

    public static void init() {

    }

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> RANDOM_CROP = register(createIdentifier("random_crop"), Feature.RANDOM_PATCH,
            FeatureUtils.simpleRandomPatchConfiguration(3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, config)));

    public static final Holder<PlacedFeature> RANDOM_CROP_PLACED = register(createIdentifier("random_crop"),
            RANDOM_CROP, CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

    public static final Holder<PlacedFeature> APPLE_TREE_CONFIGURED = register(createIdentifier(FeatureNames.APPLE_TREE_CONFIGURED),
            Content.APPLE.getTree(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(10), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> BANANA_TREE_CONFIGURED = register(createIdentifier(FeatureNames.BANANA_TREE_CONFIGURED),
            Content.BANANA.getTree(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(10), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> ORANGE_TREE_CONFIGURED = register(createIdentifier(FeatureNames.ORANGE_TREE_CONFIGURED),
            Content.ORANGE.getTree(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(10), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> PERSIMMON_TREE_CONFIGURED = register(createIdentifier(FeatureNames.PERSIMMON_TREE_CONFIGURED),
            Content.PERSIMMON.getTree(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(10), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> PLUM_TREE_CONFIGURED = register(createIdentifier(FeatureNames.PLUM_TREE_CONFIGURED),
            Content.PLUM.getTree(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(10), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> CHERRY_TREE_CONFIGURED = register(createIdentifier(FeatureNames.CHERRY_TREE_CONFIGURED),
            Content.CHERRY.getTree(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(10), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> LEMON_TREE_CONFIGURED = register(createIdentifier(FeatureNames.LEMON_TREE_CONFIGURED),
            Content.LEMON.getTree(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(10), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> GRAPEFRUIT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.GRAPEFRUIT_TREE_CONFIGURED),
            Content.GRAPEFRUIT.getTree(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(10), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> KUMQUAT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.KUMQUAT_TREE_CONFIGURED),
            Content.KUMQUAT.getTree(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(10), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());
    
    public static final Holder<PlacedFeature> PEACH_TREE_CONFIGURED = register(createIdentifier(FeatureNames.PEACH_TREE_CONFIGURED),
            Content.PEACH.getTree(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(10), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> COCONUT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.COCONUT_TREE_CONFIGURED),
            Content.COCONUT.getTree(), InSquarePlacement.spread(), PlacementUtils.countExtra(0, 0.2F, 5), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> NUTMEG_TREE_CONFIGURED = register(createIdentifier(FeatureNames.NUTMEG_TREE_CONFIGURED),
            Content.NUTMEG.getTree(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(10), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> FIG_TREE_CONFIGURED = register(createIdentifier(FeatureNames.FIG_TREE_CONFIGURED),
            Content.FIG.getTree(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(10), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> NECTARINE_TREE_CONFIGURED = register(createIdentifier(FeatureNames.NECTARINE_TREE_CONFIGURED),
            Content.NECTARINE.getTree(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(10), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> MANGO_TREE_CONFIGURED = register(createIdentifier(FeatureNames.MANGO_TREE_CONFIGURED),
            Content.MANGO.getTree(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(10), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> DRAGON_FRUIT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.DRAGON_FRUIT_TREE_CONFIGURED),
            Content.DRAGONFRUIT.getTree(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(10), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> STAR_FRUIT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.STAR_FRUIT_TREE_CONFIGURED),
            Content.STARFRUIT.getTree(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(10), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> AVOCADO_TREE_CONFIGURED = register(createIdentifier(FeatureNames.AVOCADO_TREE_CONFIGURED),
            Content.AVOCADO.getTree(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(10), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> APRICOT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.APRICOT_TREE_CONFIGURED),
            Content.APRICOT.getTree(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(10), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> PEAR_TREE_CONFIGURED = register(createIdentifier(FeatureNames.PEAR_TREE_CONFIGURED),
            Content.PEAR.getTree(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(10), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> LIME_TREE_CONFIGURED = register(createIdentifier(FeatureNames.LIME_TREE_CONFIGURED),
            Content.LIME.getTree(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(10), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> DATE_TREE_CONFIGURED = register(createIdentifier(FeatureNames.DATE_TREE_CONFIGURED),
            Content.DATE.getTree(), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(10), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> ALMOND_TREE_CONFIGURED = register(createIdentifier(FeatureNames.ALMOND_TREE_CONFIGURED),
            Content.ALMOND.getTree(), InSquarePlacement.spread(), PlacementUtils.countExtra(0, 0.25F, 5), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> CASHEW_TREE_CONFIGURED = register(createIdentifier(FeatureNames.CASHEW_TREE_CONFIGURED),
            Content.CASHEW.getTree(), InSquarePlacement.spread(), PlacementUtils.countExtra(0, 0.25F, 5), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> PECAN_TREE_CONFIGURED = register(createIdentifier(FeatureNames.PECAN_TREE_CONFIGURED),
            Content.PECAN.getTree(), InSquarePlacement.spread(), PlacementUtils.countExtra(0, 0.25F, 5), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> WALNUT_TREE_CONFIGURED = register(createIdentifier(FeatureNames.WALNUT_TREE_CONFIGURED),
            Content.WALNUT.getTree(), InSquarePlacement.spread(), PlacementUtils.countExtra(0, 0.25F, 5), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> CINNAMON_TREE_CONFIGURED = register(createIdentifier(FeatureNames.CINNAMON_TREE_CONFIGURED),
            Content.CINNAMON.getTree(),InSquarePlacement.spread(), PlacementUtils.countExtra(1, 0.1F, 6), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());


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
    
    /*public static <FC extends FeatureConfig, F extends Feature<FC>> Holder<ConfiguredFeature<FC, F>> register(ResourceLocation id, F feature, FC config) {
        return BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_FEATURE, id, new ConfiguredFeature<>(feature, config));
    }*/

    public static Holder<PlacedFeature> register(ResourceLocation id, Holder<? extends ConfiguredFeature<?, ?>> registryEntry, List<PlacementModifier> modifiers) {
        ResourceKey<PlacedFeature> key = ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, id);
        Holder<PlacedFeature> feature = BuiltinRegistries.register(BuiltinRegistries.PLACED_FEATURE, id, new PlacedFeature(Holder.hackyErase(registryEntry), List.copyOf(modifiers)));
        keyMap.put(id.getPath(), feature);
        return feature;
    }

    public static Holder<PlacedFeature> register(ResourceLocation id, Holder<? extends ConfiguredFeature<?, ?>> registryEntry, PlacementModifier... modifiers) {
        return register(id, registryEntry, List.of(modifiers));
    }

    public static Holder<PlacedFeature> getFeatureKey(String key) {
        return keyMap.get(key);
    }
}
