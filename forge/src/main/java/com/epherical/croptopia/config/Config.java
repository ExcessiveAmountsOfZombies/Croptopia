package com.epherical.croptopia.config;


import com.epherical.croptopia.common.FeatureNames;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.SetMultimap;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(modid = "croptopia", bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private final ForgeConfigSpec.Builder CONFIG_BUILDER = new ForgeConfigSpec.Builder();
    public ForgeConfigSpec config;

    public static ForgeConfigSpec.ConfigValue<Boolean> rightClickHarvest;
    public static ForgeConfigSpec.ConfigValue<Boolean> disableSaltGeneration;
    public final Map<TreeConfiguration, TreeBuilder> builderMap = new HashMap<>();
    private final Multimap<ResourceLocation, String> features = HashMultimap.create();
    public static boolean canRightClickHarvest;
    public static boolean isSaltDisabled;

    public Config() {
        rightClickHarvest = CONFIG_BUILDER.comment("allows the user to right click harvest crops")
                .translation("croptopia.config.rightclickharvest")
                .define("rightClickHarvest", true);
        disableSaltGeneration = CONFIG_BUILDER.comment("allows the user to disable salt generation in the world, default is false")
                .define("disableSaltGeneration", false);
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
            isSaltDisabled = disableSaltGeneration.get();
            features.clear();
            for (Map.Entry<TreeConfiguration, TreeBuilder> entry : builderMap.entrySet()) {
                for (String s : entry.getValue().acceptableBiomes.get()) {
                    features.put(new ResourceLocation(s), entry.getKey().featureKey);
                }
            }
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

    public static void setFeatures(Config config) {
        for (TreeConfiguration treeConfiguration : TreeConfiguration.init()) {
            for (String key : treeConfiguration.keys) {
                config.features.put(new ResourceLocation(key), treeConfiguration.featureKey);
            }
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


            //RegistryKey<Biome> alliumFields = RegistryKey.of(Registry.BIOME_KEY, bygID("allium_fields"));
            //RegistryKey<Biome> amaranthFields = RegistryKey.of(Registry.BIOME_KEY, bygID("amaranth_fields"));
            //RegistryKey<Biome> araucariaSavanna = RegistryKey.of(Registry.BIOME_KEY, bygID("araucaria_savanna"));wwwwwwwwwwww
            ResourceKey<Biome> aspenForest = ResourceKey.create(Registries.BIOME, bygID("aspen_forest"));
            ResourceKey<Biome> autumnalValley = ResourceKey.create(Registries.BIOME, bygID("autumnal_valley"));
            //RegistryKey<Biome> baobabSavanna = RegistryKey.of(Registry.BIOME_KEY, bygID("baobab_savanna"));
            ResourceKey<Biome> bayou = ResourceKey.create(Registries.BIOME, bygID("bayou"));
            ResourceKey<Biome> blackForest = ResourceKey.create(Registries.BIOME, bygID("black_forest"));
            //RegistryKey<Biome> borealisGrove = RegistryKey.of(Registry.BIOME_KEY, bygID("borealis_grove"));
            ResourceKey<Biome> canadianShield = ResourceKey.create(Registries.BIOME, bygID("canadian_shield"));
            ResourceKey<Biome> cherryBlossomForest = ResourceKey.create(Registries.BIOME, bygID("cherry_blossom_forest"));
            ResourceKey<Biome> cikaWoods = ResourceKey.create(Registries.BIOME, bygID("cika_woods"));
            ResourceKey<Biome> coniferousForest = ResourceKey.create(Registries.BIOME, bygID("coniferous_forest"));
            ResourceKey<Biome> cragGardens = ResourceKey.create(Registries.BIOME, bygID("crag_gardens"));
            ResourceKey<Biome> cypressSwamplands = ResourceKey.create(Registries.BIOME, bygID("cypress_swamplands"));
            ResourceKey<Biome> deadSea = ResourceKey.create(Registries.BIOME, bygID("dead_sea"));
            ResourceKey<Biome> daciteRidges = ResourceKey.create(Registries.BIOME, bygID("dacite_ridges"));
            ResourceKey<Biome> windsweptDunes = ResourceKey.create(Registries.BIOME, bygID("windswept_dunes"));
            ResourceKey<Biome> ebonyWoods = ResourceKey.create(Registries.BIOME, bygID("ebony_woods"));
            ResourceKey<Biome> forgottenForest = ResourceKey.create(Registries.BIOME, bygID("forgotten_forest"));
            ResourceKey<Biome> grove = ResourceKey.create(Registries.BIOME, bygID("temperate_grove"));
            ResourceKey<Biome> guianaShield = ResourceKey.create(Registries.BIOME, bygID("guiana_shield"));
            ResourceKey<Biome> jacarandaForest = ResourceKey.create(Registries.BIOME, bygID("jacaranda_forest"));
            ResourceKey<Biome> mapleTaiga = ResourceKey.create(Registries.BIOME, bygID("maple_taiga"));
            ResourceKey<Biome> coconinoMeadow = ResourceKey.create(Registries.BIOME, bygID("coconino_meadow"));
            ResourceKey<Biome> mojaveDesert = ResourceKey.create(Registries.BIOME, bygID("mojave_desert"));
            ResourceKey<Biome> lushTundra = ResourceKey.create(Registries.BIOME, bygID("lush_tundra"));
            ResourceKey<Biome> orchard = ResourceKey.create(Registries.BIOME, bygID("orchard"));
            ResourceKey<Biome> prairie = ResourceKey.create(Registries.BIOME, bygID("prairie"));
            ResourceKey<Biome> redOakForest = ResourceKey.create(Registries.BIOME, bygID("red_oak_forest"));
            ResourceKey<Biome> redRockValley = ResourceKey.create(Registries.BIOME, bygID("red_rock_valley"));
            ResourceKey<Biome> roseFields = ResourceKey.create(Registries.BIOME, bygID("rose_fields"));
            ResourceKey<Biome> autumnalForest = ResourceKey.create(Registries.BIOME, bygID("autumnal_forest"));
            ResourceKey<Biome> autumnalTaiga = ResourceKey.create(Registries.BIOME, bygID("autumnal_taiga"));
            ResourceKey<Biome> shatteredGlacier = ResourceKey.create(Registries.BIOME, bygID("shattered_glacier"));
            ResourceKey<Biome> firecrackerShrubland = ResourceKey.create(Registries.BIOME, bygID("firecracker_shrubland"));
            ResourceKey<Biome> sierraBadlands = ResourceKey.create(Registries.BIOME, bygID("sierra_badlands"));
            ResourceKey<Biome> skyrisVale = ResourceKey.create(Registries.BIOME, bygID("skyris_vale"));
            ResourceKey<Biome> redwoodThicket = ResourceKey.create(Registries.BIOME, bygID("redwood_thicket"));
            ResourceKey<Biome> frostedTaiga = ResourceKey.create(Registries.BIOME, bygID("frosted_taiga"));
            ResourceKey<Biome> frostedConiferousForest = ResourceKey.create(Registries.BIOME, bygID("frosted_coniferous_forest"));
            ResourceKey<Biome> fragmentForest = ResourceKey.create(Registries.BIOME, bygID("fragment_forest"));
            ResourceKey<Biome> tropicalIsland = ResourceKey.create(Registries.BIOME, bygID("tropical_islands"));
            ResourceKey<Biome> tropicalRainforest = ResourceKey.create(Registries.BIOME, bygID("tropical_rainforest"));
            ResourceKey<Biome> twilightMeadow = ResourceKey.create(Registries.BIOME, bygID("twilight_meadow"));
            ResourceKey<Biome> weepingWitchForest = ResourceKey.create(Registries.BIOME, bygID("weeping_witch_forest"));
            ResourceKey<Biome> whiteMangroveMarshes = ResourceKey.create(Registries.BIOME, bygID("white_mangrove_marshes"));
            ResourceKey<Biome> temperateRainforest = ResourceKey.create(Registries.BIOME, bygID("temperate_rainforest"));
            ResourceKey<Biome> zelkovaForest = ResourceKey.create(Registries.BIOME, bygID("zelkova_forest"));

            Collection<ResourceKey<Biome>> bygWoods = Arrays.asList(aspenForest, orchard, redOakForest);
            Collection<ResourceKey<Biome>> cherry = Arrays.asList(cherryBlossomForest);

            Collection<ResourceKey<Biome>> nutty = Arrays.asList(weepingWitchForest, daciteRidges, ebonyWoods,
                    mapleTaiga, twilightMeadow);
            Collection<ResourceKey<Biome>> jungleByg = Arrays.asList(cragGardens, tropicalIsland, tropicalRainforest);

            TreeConfiguration.createSameTreeConfigs(biomes, Collections.singleton(prairie),
                    FeatureNames.APPLE_TREE_CONFIGURED);

            TreeConfiguration.createSameTreeConfigs(biomes, Arrays.asList(jacarandaForest, autumnalForest, autumnalTaiga),
                    "pear_tree_configured",
                    "persimmon_tree_configured",
                    "plum_tree_configured");

            TreeConfiguration.createSameTreeConfigs(biomes, Arrays.asList(cypressSwamplands, whiteMangroveMarshes, temperateRainforest),
                    FeatureNames.CINNAMON_TREE_CONFIGURED);

            TreeConfiguration.createSameTreeConfigs(biomes, jungleByg,
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

            TreeConfiguration.createSameTreeConfigs(biomes, nutty,
                    "almond_tree_configured",
                    "cashew_tree_configured",
                    "pecan_tree_configured",
                    "walnut_tree_configured");

            TreeConfiguration.createSameTreeConfigs(biomes, cherry,
                    "cherry_tree_configured");

            TreeConfiguration.createSameTreeConfigs(biomes, bygWoods,
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

            List<TreeConfiguration> allTreeConfigs = new ArrayList<>();
            for (Map.Entry<String, Collection<ResourceKey<Biome>>> entry : biomes.asMap().entrySet()) {
                allTreeConfigs.add(new TreeConfiguration(entry.getKey(), new ArrayList<>(entry.getValue())));
            }
            return allTreeConfigs;
        }
    }

    private static ResourceLocation bygID(String name) {
        return new ResourceLocation("byg", name);
    }

    public Map<TreeConfiguration, TreeBuilder> getBuilderMap() {
        return builderMap;
    }

    public Multimap<ResourceLocation, String> getFeatures() {
        return features;
    }
}
