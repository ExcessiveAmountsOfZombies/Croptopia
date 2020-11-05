package me.thonk.croptopia.generator;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

import java.util.Arrays;
import java.util.Collection;

public class FeaturePlacement {


    public static void init() {
        Collection<RegistryKey<Biome>> forestBiomes = Arrays.asList(BiomeKeys.FOREST, BiomeKeys.WOODED_HILLS, BiomeKeys.FLOWER_FOREST);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(forestBiomes),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("lime_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(forestBiomes),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("pear_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(forestBiomes),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("apricot_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(forestBiomes),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("avocado_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(forestBiomes),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("star_fruit_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(forestBiomes),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("lemon_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(forestBiomes),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("cherry_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(forestBiomes),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("plum_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(forestBiomes),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("persimmon_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(forestBiomes),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("orange_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(forestBiomes),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("nectarine_tree_configured"));

        Collection<RegistryKey<Biome>> jungleBiomes = Arrays.asList(BiomeKeys.JUNGLE, BiomeKeys.JUNGLE_EDGE, BiomeKeys.JUNGLE_HILLS,
                BiomeKeys.MODIFIED_JUNGLE, BiomeKeys.MODIFIED_JUNGLE_EDGE);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(jungleBiomes),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("date_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(jungleBiomes),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("dragon_fruit_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(jungleBiomes),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("mango_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(jungleBiomes),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("nutmeg_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(jungleBiomes),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("coconut_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(jungleBiomes),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("kumquat_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(jungleBiomes),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("grapefruit_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(jungleBiomes),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("banana_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(jungleBiomes),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("fig_tree_configured"));

        Collection<RegistryKey<Biome>> plainsKeys = Arrays.asList(BiomeKeys.PLAINS, BiomeKeys.SUNFLOWER_PLAINS);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(plainsKeys),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("apple_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(plainsKeys),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("orange_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(plainsKeys),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("peach_tree_configured"));

        Collection<RegistryKey<Biome>> darkForestKeys = Arrays.asList(BiomeKeys.DARK_FOREST, BiomeKeys.DARK_FOREST_HILLS);


        BiomeModifications.addFeature(BiomeSelectors.includeByKey(darkForestKeys),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("almond_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(darkForestKeys),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("cashew_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(darkForestKeys),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("pecan_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(darkForestKeys),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("walnut_tree_configured"));


    }
}
