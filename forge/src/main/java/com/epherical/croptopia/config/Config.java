package com.epherical.croptopia.config;


import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.epherical.croptopia.common.FeatureNames;
import com.google.common.collect.SetMultimap;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

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
    public void initConfig(ModConfig.ModConfigEvent configEvent) {
        if (configEvent.getConfig().getSpec() == config) {
            canRightClickHarvest = rightClickHarvest.get();
            isSaltDisabled = disableSaltGeneration.get();
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

    public static class TreeConfiguration {
        String featureKey;
        List<RegistryKey<Biome>> treesInBiome;
        List<String> keys;

        public TreeConfiguration(String featureKey, List<RegistryKey<Biome>> treesInBiome) {
            this.featureKey = featureKey;
            this.treesInBiome = treesInBiome;
            this.keys = new ArrayList<>();
            for (RegistryKey<Biome> biomeRegistryKey : treesInBiome) {
                keys.add(biomeRegistryKey.location().toString());
            }
        }


        public static void createSameTreeConfigs(SetMultimap<String, RegistryKey<Biome>> map, Collection<RegistryKey<Biome>> biomes, String... keys) {
            for (String key : keys) {
                map.putAll(key, biomes);
            }
        }

        public static List<TreeConfiguration> init() {
            Collection<RegistryKey<Biome>> forestBiomes = Arrays.asList(Biomes.FOREST, Biomes.WOODED_HILLS, Biomes.FLOWER_FOREST);
            Collection<RegistryKey<Biome>> jungleBiomes = Arrays.asList(Biomes.JUNGLE, Biomes.JUNGLE_EDGE, Biomes.JUNGLE_HILLS);
            Collection<RegistryKey<Biome>> plainsKeys = Arrays.asList(Biomes.PLAINS, Biomes.SUNFLOWER_PLAINS);
            Collection<RegistryKey<Biome>> darkForestKeys = Arrays.asList(Biomes.DARK_FOREST, Biomes.DARK_FOREST_HILLS);

            HashMultimap<String, RegistryKey<Biome>> biomes = HashMultimap.create();

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
            RegistryKey<Biome> aspenForest = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("aspen_forest"));
            RegistryKey<Biome> autumnalValley = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("autumnal_valley"));
            //RegistryKey<Biome> baobabSavanna = RegistryKey.of(Registry.BIOME_KEY, bygID("baobab_savanna"));
            RegistryKey<Biome> bayou = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("bayou"));
            RegistryKey<Biome> blackForest = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("black_forest"));
            //RegistryKey<Biome> borealisGrove = RegistryKey.of(Registry.BIOME_KEY, bygID("borealis_grove"));
            RegistryKey<Biome> canadianShield = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("canadian_shield"));
            RegistryKey<Biome> cherryBlossomForest = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("cherry_blossom_forest"));
            RegistryKey<Biome> cikaWoods = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("cika_woods"));
            RegistryKey<Biome> coniferousForest = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("coniferous_forest"));
            RegistryKey<Biome> cragGardens = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("crag_gardens"));
            RegistryKey<Biome> cypressSwamplands = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("cypress_swamplands"));
            RegistryKey<Biome> deadSea = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("dead_sea"));
            RegistryKey<Biome> daciteRidges = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("dacite_ridges"));
            RegistryKey<Biome> windsweptDunes = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("windswept_dunes"));
            RegistryKey<Biome> ebonyWoods = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("ebony_woods"));
            RegistryKey<Biome> forgottenForest = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("forgotten_forest"));
            RegistryKey<Biome> grove = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("temperate_grove"));
            RegistryKey<Biome> guianaShield = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("guiana_shield"));
            RegistryKey<Biome> jacarandaForest = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("jacaranda_forest"));
            RegistryKey<Biome> mapleTaiga = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("maple_taiga"));
            RegistryKey<Biome> coconinoMeadow = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("coconino_meadow"));
            RegistryKey<Biome> mojaveDesert = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("mojave_desert"));
            RegistryKey<Biome> lushTundra = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("lush_tundra"));
            RegistryKey<Biome> orchard = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("orchard"));
            RegistryKey<Biome> prairie = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("prairie"));
            RegistryKey<Biome> redOakForest = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("red_oak_forest"));
            RegistryKey<Biome> redRockValley = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("red_rock_valley"));
            RegistryKey<Biome> roseFields = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("rose_fields"));
            RegistryKey<Biome> autumnalForest = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("autumnal_forest"));
            RegistryKey<Biome> autumnalTaiga = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("autumnal_taiga"));
            RegistryKey<Biome> shatteredGlacier = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("shattered_glacier"));
            RegistryKey<Biome> firecrackerShrubland = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("firecracker_shrubland"));
            RegistryKey<Biome> sierraBadlands = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("sierra_badlands"));
            RegistryKey<Biome> skyrisVale = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("skyris_vale"));
            RegistryKey<Biome> redwoodThicket = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("redwood_thicket"));
            RegistryKey<Biome> frostedTaiga = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("frosted_taiga"));
            RegistryKey<Biome> frostedConiferousForest = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("frosted_coniferous_forest"));
            RegistryKey<Biome> fragmentForest = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("fragment_forest"));
            RegistryKey<Biome> tropicalIsland = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("tropical_islands"));
            RegistryKey<Biome> tropicalRainforest = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("tropical_rainforest"));
            RegistryKey<Biome> twilightMeadow = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("twilight_meadow"));
            RegistryKey<Biome> weepingWitchForest = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("weeping_witch_forest"));
            RegistryKey<Biome> whiteMangroveMarshes = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("white_mangrove_marshes"));
            RegistryKey<Biome> temperateRainforest = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("temperate_rainforest"));
            RegistryKey<Biome> zelkovaForest = RegistryKey.create(Registry.BIOME_REGISTRY, bygID("zelkova_forest"));

            Collection<RegistryKey<Biome>> bygWoods = Arrays.asList(aspenForest, orchard, redOakForest);
            Collection<RegistryKey<Biome>> cherry = Arrays.asList(cherryBlossomForest);

            Collection<RegistryKey<Biome>> nutty = Arrays.asList(weepingWitchForest, daciteRidges, ebonyWoods,
                    mapleTaiga, twilightMeadow);
            Collection<RegistryKey<Biome>> jungleByg = Arrays.asList(cragGardens, tropicalIsland, tropicalRainforest);

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
            for (Map.Entry<String, Collection<RegistryKey<Biome>>> entry : biomes.asMap().entrySet()) {
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
