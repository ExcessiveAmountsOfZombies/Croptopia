package com.epherical.croptopia.config;


import com.epherical.croptopia.common.FeatureNames;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.SetMultimap;
import net.minecraft.core.Registry;
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
            ResourceKey<Biome> aspenForest = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("aspen_forest"));
            ResourceKey<Biome> autumnalValley = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("autumnal_valley"));
            //RegistryKey<Biome> baobabSavanna = RegistryKey.of(Registry.BIOME_KEY, bygID("baobab_savanna"));
            ResourceKey<Biome> bayou = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("bayou"));
            ResourceKey<Biome> blackForest = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("black_forest"));
            //RegistryKey<Biome> borealisGrove = RegistryKey.of(Registry.BIOME_KEY, bygID("borealis_grove"));
            ResourceKey<Biome> canadianShield = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("canadian_shield"));
            ResourceKey<Biome> cherryBlossomForest = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("cherry_blossom_forest"));
            ResourceKey<Biome> cikaWoods = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("cika_woods"));
            ResourceKey<Biome> coniferousForest = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("coniferous_forest"));
            ResourceKey<Biome> cragGardens = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("crag_gardens"));
            ResourceKey<Biome> cypressSwamplands = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("cypress_swamplands"));
            ResourceKey<Biome> deadSea = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("dead_sea"));
            ResourceKey<Biome> daciteRidges = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("dacite_ridges"));
            ResourceKey<Biome> windsweptDunes = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("windswept_dunes"));
            ResourceKey<Biome> ebonyWoods = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("ebony_woods"));
            ResourceKey<Biome> forgottenForest = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("forgotten_forest"));
            ResourceKey<Biome> grove = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("temperate_grove"));
            ResourceKey<Biome> guianaShield = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("guiana_shield"));
            ResourceKey<Biome> jacarandaForest = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("jacaranda_forest"));
            ResourceKey<Biome> mapleTaiga = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("maple_taiga"));
            ResourceKey<Biome> coconinoMeadow = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("coconino_meadow"));
            ResourceKey<Biome> mojaveDesert = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("mojave_desert"));
            ResourceKey<Biome> lushTundra = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("lush_tundra"));
            ResourceKey<Biome> orchard = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("orchard"));
            ResourceKey<Biome> prairie = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("prairie"));
            ResourceKey<Biome> redOakForest = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("red_oak_forest"));
            ResourceKey<Biome> redRockValley = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("red_rock_valley"));
            ResourceKey<Biome> roseFields = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("rose_fields"));
            ResourceKey<Biome> autumnalForest = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("autumnal_forest"));
            ResourceKey<Biome> autumnalTaiga = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("autumnal_taiga"));
            ResourceKey<Biome> shatteredGlacier = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("shattered_glacier"));
            ResourceKey<Biome> firecrackerShrubland = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("firecracker_shrubland"));
            ResourceKey<Biome> sierraBadlands = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("sierra_badlands"));
            ResourceKey<Biome> skyrisVale = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("skyris_vale"));
            ResourceKey<Biome> redwoodThicket = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("redwood_thicket"));
            ResourceKey<Biome> frostedTaiga = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("frosted_taiga"));
            ResourceKey<Biome> frostedConiferousForest = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("frosted_coniferous_forest"));
            ResourceKey<Biome> fragmentForest = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("fragment_forest"));
            ResourceKey<Biome> tropicalIsland = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("tropical_islands"));
            ResourceKey<Biome> tropicalRainforest = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("tropical_rainforest"));
            ResourceKey<Biome> twilightMeadow = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("twilight_meadow"));
            ResourceKey<Biome> weepingWitchForest = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("weeping_witch_forest"));
            ResourceKey<Biome> whiteMangroveMarshes = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("white_mangrove_marshes"));
            ResourceKey<Biome> temperateRainforest = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("temperate_rainforest"));
            ResourceKey<Biome> zelkovaForest = ResourceKey.create(Registry.BIOME_REGISTRY, bygID("zelkova_forest"));
            //Biomes'O'Plenty biomes
            ResourceKey<Biome> auroralGarden = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("auroral_garden"));
            ResourceKey<Biome> bambooGrove = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("bamboo_grove"));
            ResourceKey<Biome> bopBayou = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("bayou"));
            ResourceKey<Biome> bog = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("bog"));
            ResourceKey<Biome> cherryBlossomGrove = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("cherry_blossom_grove"));
            ResourceKey<Biome> cloverPatch = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("clover_patch"));
            ResourceKey<Biome> coldDesert = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("cold_desert"));
            ResourceKey<Biome> bopConiferousForest = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("coniferous_forest"));
            ResourceKey<Biome> crag = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("crag"));
            ResourceKey<Biome> deadForest = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("dead_forest"));
            ResourceKey<Biome> dryland = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("dryland"));
            ResourceKey<Biome> duneBeach = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("duneBeach"));
            ResourceKey<Biome> field = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("field"));
            ResourceKey<Biome> firClearing = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("fir_clearing"));
            ResourceKey<Biome> floodplain = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("floodplain"));
            ResourceKey<Biome> foresetedField = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("forested_field"));
            ResourceKey<Biome> bopFloodplain = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("floodplain"));
            ResourceKey<Biome> fungalJungle = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("fungal_jungle"));
            ResourceKey<Biome> glowingGrotto = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("glowing_grotto"));
            ResourceKey<Biome> grassland = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("grassland"));
            ResourceKey<Biome> highland = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("highland"));
            ResourceKey<Biome> highlandMoor = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("highland_moor"));
            ResourceKey<Biome> jadeCliffs = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("jade_cliffs"));
            ResourceKey<Biome> lavenderField = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("lavender_field"));
            ResourceKey<Biome> lavenderForest = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("lavender_forest"));
            ResourceKey<Biome> lushDesert = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("lush_desert"));
            ResourceKey<Biome> lushSavanna = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("lush_savanna"));
            ResourceKey<Biome> mapleWoods = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("maples_woods"));
            ResourceKey<Biome> marsh = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("marsh"));
            ResourceKey<Biome> mediterraneanForest = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("mediterranean_forest"));
            ResourceKey<Biome> muskeg = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("muskeg"));
            ResourceKey<Biome> mysticGrove = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("mystic_grove"));
            ResourceKey<Biome> oldGrowthDeadForest = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("old_growth_dead_forest"));
            ResourceKey<Biome> oldGrowthWoodland = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("old_growth_woodland"));
            ResourceKey<Biome> ominousWoods = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("ominous_woods"));
            ResourceKey<Biome> bopOrchard = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("orchard"));
            ResourceKey<Biome> originValley = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("origin_valley"));
            ResourceKey<Biome> pasture = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("pasture"));
            ResourceKey<Biome> bopPrairie = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("prairie"));
            ResourceKey<Biome> pumpkinPatch = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("pumpkin_patch"));
            ResourceKey<Biome> rainforest = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("rainforest"));
            ResourceKey<Biome> redwoodForest = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("redwood_forest"));
            ResourceKey<Biome> rockyRainforest = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("rocky_rainforest"));
            ResourceKey<Biome> rockyShrubland = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("rocky_shrubland"));
            ResourceKey<Biome> scrubland = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("scrubland"));
            ResourceKey<Biome> seasonalForest = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("seasonal_forest"));
            ResourceKey<Biome> seasonalOrchard = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("seasonal_orchard"));
            ResourceKey<Biome> shrubland = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("shrubland"));
            ResourceKey<Biome> snowyConiferousForest = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("snowy_coniferous_forest"));
            ResourceKey<Biome> snowyFirClearing = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("snowy_fir_clearing"));
            ResourceKey<Biome> snowyMapleWoods = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("snowy_maple_woods"));
            ResourceKey<Biome> spiderNest = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("spider_nest"));
            ResourceKey<Biome> tropics = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("tropics"));
            ResourceKey<Biome> tundra = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("tundra"));
            ResourceKey<Biome> volcanicPlains = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("volcanic_plains"));
            ResourceKey<Biome> volcano = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("volcano"));
            ResourceKey<Biome> wasteland = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("wasteland"));
            ResourceKey<Biome> wetland = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("wetland"));
            ResourceKey<Biome> woodedScrubland = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("wooded_scrubland"));
            ResourceKey<Biome> woodedWasteland = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("wooded_wasteland"));
            ResourceKey<Biome> woodland = ResourceKey.create(Registry.BIOME_REGISTRY, bopID("woodland"));

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

    private static ResourceLocation bopID(String name) { return new ResourceLocation("biomesoplenty", name); }

    public Map<TreeConfiguration, TreeBuilder> getBuilderMap() {
        return builderMap;
    }

    public Multimap<ResourceLocation, String> getFeatures() {
        return features;
    }
}
