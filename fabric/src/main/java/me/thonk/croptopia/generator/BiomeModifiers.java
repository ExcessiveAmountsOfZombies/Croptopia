package me.thonk.croptopia.generator;

import me.thonk.common.FeatureNames;
import me.thonk.croptopia.Constants;
import me.thonk.croptopia.registry.GeneratorRegistry;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

import java.util.Arrays;
import java.util.Collection;

public class BiomeModifiers {


    public static void init() {


        // generate in ALL biomes
        BiomeModifications.addFeature(context -> {
            Biome biome = context.getBiome();
            return biome.getCategory() != Biome.Category.OCEAN;
        }, GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("random_crop"));

        Collection<RegistryKey<Biome>> forestBiomes = Arrays.asList(BiomeKeys.FOREST, BiomeKeys.WINDSWEPT_FOREST, BiomeKeys.FLOWER_FOREST);

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

        Collection<RegistryKey<Biome>> jungleBiomes = Arrays.asList(BiomeKeys.JUNGLE, BiomeKeys.SPARSE_JUNGLE);

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
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(jungleBiomes),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey(FeatureNames.CINNAMON_TREE_CONFIGURED));

        Collection<RegistryKey<Biome>> plainsKeys = Arrays.asList(BiomeKeys.PLAINS, BiomeKeys.SUNFLOWER_PLAINS);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(plainsKeys),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("apple_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(plainsKeys),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("orange_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(plainsKeys),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("peach_tree_configured"));

        Collection<RegistryKey<Biome>> darkForestKeys = Arrays.asList(BiomeKeys.DARK_FOREST, BiomeKeys.DARK_FOREST);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(darkForestKeys),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("almond_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(darkForestKeys),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("cashew_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(darkForestKeys),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("pecan_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(darkForestKeys),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("walnut_tree_configured"));

        Collection<RegistryKey<Biome>> exclusion = Arrays.asList(BiomeKeys.SWAMP, BiomeKeys.SWAMP);

        if (!Constants.OPTIONS.disableSaltOre()) {
            BiomeModifications.addFeature(BiomeSelectors.excludeByKey(exclusion),
                    GenerationStep.Feature.UNDERGROUND_ORES, GeneratorRegistry.getFeatureKey("disk_salt_configured"));
        }

        if (FabricLoader.getInstance().isModLoaded("traverse")) {
            addTraverseSupport();
        }

    }

    private static void addTraverseSupport() {
        RegistryKey<Biome> woodlands = RegistryKey.of(Registry.BIOME_KEY, travID("woodlands"));
        RegistryKey<Biome> wooded_plateau = RegistryKey.of(Registry.BIOME_KEY, travID("wooded_plateau"));
        RegistryKey<Biome> wooded_island = RegistryKey.of(Registry.BIOME_KEY, travID("wooded_island"));
        RegistryKey<Biome> autumnal_woods = RegistryKey.of(Registry.BIOME_KEY, travID("autumnal_woods"));
        RegistryKey<Biome> autumnal_wooded_hills = RegistryKey.of(Registry.BIOME_KEY, travID("autumnal_wooded_hills"));
        RegistryKey<Biome> lush_swamp = RegistryKey.of(Registry.BIOME_KEY, travID("lush_swamp"));
        RegistryKey<Biome> mini_jungle = RegistryKey.of(Registry.BIOME_KEY, travID("mini_jungle"));

        Collection<RegistryKey<Biome>> wooded = Arrays.asList(wooded_island, wooded_plateau, woodlands);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(wooded),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("apple_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(wooded),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("cherry_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(wooded),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("plum_tree_configured"));

        Collection<RegistryKey<Biome>> autumnal = Arrays.asList(autumnal_woods, autumnal_wooded_hills);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(autumnal),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("pear_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(autumnal),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("persimmon_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(autumnal),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("plum_tree_configured"));


        Collection<RegistryKey<Biome>> jungle = Arrays.asList(mini_jungle);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(jungle),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("date_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(jungle),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("dragon_fruit_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(jungle),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("mango_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(jungle),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("nutmeg_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(jungle),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("coconut_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(jungle),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("kumquat_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(jungle),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("grapefruit_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(jungle),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("banana_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(jungle),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("fig_tree_configured"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(jungle),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey(FeatureNames.CINNAMON_TREE_CONFIGURED));

        Collection<RegistryKey<Biome>> lush = Arrays.asList(lush_swamp);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(lush),
                GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey(FeatureNames.CINNAMON_TREE_CONFIGURED));

    }


    private static Identifier travID(String name) {
        return new Identifier("traverse", name);
    }
}
