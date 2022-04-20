package com.epherical.croptopia.config;


import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import com.epherical.croptopia.common.FeatureNames;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(modid = "croptopia", bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private final ForgeConfigSpec.Builder CONFIG_BUILDER = new ForgeConfigSpec.Builder();
    public ForgeConfigSpec config;

    public static ForgeConfigSpec.ConfigValue<Boolean> rightClickHarvest;
    public final Map<TreeConfiguration, TreeBuilder> builderMap = new HashMap<>();
    public static boolean canRightClickHarvest;

    public Config() {
        rightClickHarvest = CONFIG_BUILDER.comment("allows the user to right click harvest crops")
                .translation("croptopia.config.rightclickharvest")
                .define("rightClickHarvest", true);
        List<TreeConfiguration> trees = TreeConfiguration.init();
        CONFIG_BUILDER.comment("Croptopia tree generation").push("worldGeneration");
        for (TreeConfiguration tree : trees) {
            builderMap.put(tree, new TreeBuilder(CONFIG_BUILDER, tree));
        }
        CONFIG_BUILDER.pop();


        this.config = CONFIG_BUILDER.build();
    }

    @SubscribeEvent
    public void initConfig(ModConfigEvent configEvent) {
        if (configEvent.getConfig().getSpec() == config) {
            canRightClickHarvest = rightClickHarvest.get();
        }
    }

    public static class TreeBuilder {
        public final ForgeConfigSpec.ConfigValue<List<? extends String>> acceptableBiomes;

        private TreeBuilder(ForgeConfigSpec.Builder builder, TreeConfiguration configuration) {
            acceptableBiomes = builder.comment("Settings for " + configuration.featureKey).push(configuration.featureKey)
                    .defineList("biomes", configuration.keys, o -> true);
            builder.pop();
        }
    }

    public static class CropBuilder {
       /* public final ForgeConfigSpec.ConfigValue<List<? extends String>> acceptableBiomes;

        private CropBuilder(ForgeConfigSpec.Builder builder ) {

        }*/

    }

    public static class CropConfiguration {
        String seed;
        List<String> biomes;

        public CropConfiguration(String seed, List<String> biomes) {
            this.seed = seed;
            this.biomes = biomes;
        }

        public static void init() {

        }
    }

    public static class TreeConfiguration {
        String featureKey;
        List<ResourceKey<Biome>> treesInBiome;
        List<String> keys;
        
        public TreeConfiguration(String featureKey, List<ResourceKey<Biome>> treesInBiome) {
            this.featureKey = featureKey;
            this.treesInBiome = treesInBiome;
            this.keys = new ArrayList<>();
            for (ResourceKey<Biome> biomeResourceKey : treesInBiome) {
                keys.add(biomeResourceKey.location().toString());
            }
        }


        public static void createSameTreeConfigs(SetMultimap<String, ResourceKey<Biome>> map, Collection<ResourceKey<Biome>> biomes, String... keys) {
            for (String key : keys) {
                map.putAll(key, biomes);
            }
        }
        
        public static List<TreeConfiguration> init() {
            Collection<ResourceKey<Biome>> forestBiomes = Arrays.asList(Biomes.FOREST, Biomes.WINDSWEPT_FOREST, Biomes.FLOWER_FOREST);
            Collection<ResourceKey<Biome>> jungleBiomes = Arrays.asList(Biomes.JUNGLE, Biomes.SPARSE_JUNGLE);
            Collection<ResourceKey<Biome>> plainsKeys = Arrays.asList(Biomes.PLAINS, Biomes.SUNFLOWER_PLAINS);
            Collection<ResourceKey<Biome>> darkForestKeys = Arrays.asList(Biomes.DARK_FOREST);
            
            HashMultimap<String, ResourceKey<Biome>> biomes = HashMultimap.create();

            TreeConfiguration.createSameTreeConfigs(biomes, forestBiomes,
                    "lime_tree_configured",
                    "pear_tree_configured",
                    "apricot_tree_configured",
                    "avocado_tree_configured",
                    "star_fruit_tree_configured",
                    "lemon_tree_configured",
                    "cherry_tree_configured",
                    "plum_tree_configured",
                    "persimmon_tree_configured",
                    "orange_tree_configured",
                    "nectarine_tree_configured");

            TreeConfiguration.createSameTreeConfigs(biomes, jungleBiomes,
                    "date_tree_configured",
                    "dragon_fruit_tree_configured",
                    "mango_tree_configured",
                    "nutmeg_tree_configured",
                    "coconut_tree_configured",
                    "kumquat_tree_configured",
                    "grapefruit_tree_configured",
                    "banana_tree_configured",
                    "fig_tree_configured",
                    FeatureNames.CINNAMON_TREE_CONFIGURED);

            TreeConfiguration.createSameTreeConfigs(biomes, plainsKeys,
                    "apple_tree_configured",
                    "orange_tree_configured",
                    "peach_tree_configured");

            TreeConfiguration.createSameTreeConfigs(biomes, darkForestKeys,
                    "almond_tree_configured",
                    "cashew_tree_configured",
                    "pecan_tree_configured",
                    "walnut_tree_configured");

            List<TreeConfiguration> allTreeConfigs = new ArrayList<>();
            for (Map.Entry<String, Collection<ResourceKey<Biome>>> entry : biomes.asMap().entrySet()) {
                allTreeConfigs.add(new TreeConfiguration(entry.getKey(), new ArrayList<>(entry.getValue())));
            }
            return allTreeConfigs;
        }
    }


}
