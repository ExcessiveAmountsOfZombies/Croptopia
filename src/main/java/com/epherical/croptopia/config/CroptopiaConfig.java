package com.epherical.croptopia.config;

import com.epherical.epherolib.config.CommonConfig;
import com.epherical.epherolib.libs.org.spongepowered.configurate.CommentedConfigurationNode;
import com.epherical.epherolib.libs.org.spongepowered.configurate.ConfigurationNode;
import com.epherical.epherolib.libs.org.spongepowered.configurate.loader.AbstractConfigurationLoader;
import com.epherical.epherolib.libs.org.spongepowered.configurate.serialize.SerializationException;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static com.epherical.croptopia.common.generator.PlacedFeatureKeys.*;

public class CroptopiaConfig extends CommonConfig {

    public boolean generateSaltInWorld = true;
    public List<TreeConfiguration> treeConfigurations = new ArrayList<>();
    public TreeMultimap<ResourceKey<Biome>, TreeConfiguration> treeMap = TreeMultimap.create(
            Comparator.comparing(ResourceKey::toString),
            Comparator.comparing(o -> o.getFeatureKey().toString()));
    public boolean rightClickHarvest = true;


    public CroptopiaConfig(AbstractConfigurationLoader.Builder<?, ?> loaderBuilder, String configName) {
        super(loaderBuilder, configName);
    }

    /*@Override*/
    public void parseConfig(CommentedConfigurationNode node) {
        configVersion = node.node("version").getInt(configVersion);
        generateSaltInWorld = node.node("generateSaltInWorld").getBoolean(generateSaltInWorld);
        rightClickHarvest = node.node("rightCLickHarvest").getBoolean(rightClickHarvest);
        try {
            treeConfigurations = node.node("treeConfig").getList(TreeConfiguration.class);
            if (treeConfigurations == null) treeConfigurations = new ArrayList<>();
            for (TreeConfiguration treeConfiguration : treeConfigurations) {
                for (ResourceKey<Biome> biomeResourceKey : treeConfiguration.getTreesAllowedInBiome()) {
                    treeMap.put(biomeResourceKey, treeConfiguration);
                }
            }
        } catch (SerializationException e) {
            e.printStackTrace();
        }

    }

    /*@Override*/
    public CommentedConfigurationNode generateConfig(CommentedConfigurationNode node) {
        try {
            node.node("version").set(configVersion).comment("Config Version, don't edit");
            node.node("generateSaltInWorld").set(generateSaltInWorld);
            node.node("rightClickHarvest").set(rightClickHarvest).set("Determines whether or not right click harvesting works (forge only)");
            generateTreeConfig(node, "treeConfig");
        } catch (SerializationException e) {
            e.printStackTrace();
        }
        return node;
    }

    private void generateTreeConfig(CommentedConfigurationNode node, String nodeToAdd) {
        Collection<ResourceKey<Biome>> forestBiomes = Arrays.asList(Biomes.FOREST, Biomes.WINDSWEPT_FOREST, Biomes.FLOWER_FOREST);
        Collection<ResourceKey<Biome>> jungleBiomes = Arrays.asList(Biomes.JUNGLE, Biomes.SPARSE_JUNGLE);
        Collection<ResourceKey<Biome>> plainsKeys = Arrays.asList(Biomes.PLAINS, Biomes.SUNFLOWER_PLAINS);
        Collection<ResourceKey<Biome>> darkForestKeys = Arrays.asList(Biomes.DARK_FOREST);

        HashMultimap<ResourceKey<PlacedFeature>, ResourceKey<Biome>> biomes = HashMultimap.create();

        TreeConfiguration.createSameTreeConfigs(biomes, forestBiomes,
                LIME_TREE_PLACED_KEY,
                PEAR_TREE_PLACED_KEY,
                APRICOT_TREE_PLACED_KEY,
                AVOCADO_TREE_PLACED_KEY,
                STARFRUIT_TREE_PLACED_KEY,
                LEMON_TREE_PLACED_KEY,
                CHERRY_TREE_PLACED_KEY,
                PLUM_TREE_PLACED_KEY,
                PERSIMMON_TREE_PLACED_KEY,
                ORANGE_TREE_PLACED_KEY,
                NECTARINE_TREE_PLACED_KEY);

        TreeConfiguration.createSameTreeConfigs(biomes, jungleBiomes,
                DATE_TREE_PLACED_KEY,
                DRAGONFRUIT_TREE_PLACED_KEY,
                MANGO_TREE_PLACED_KEY,
                NUTMEG_TREE_PLACED_KEY,
                COCONUT_TREE_PLACED_KEY,
                KUMQUAT_TREE_PLACED_KEY,
                GRAPEFRUIT_TREE_PLACED_KEY,
                BANANA_TREE_PLACED_KEY,
                FIG_TREE_PLACED_KEY,
                CINNAMON_TREE_PLACED_KEY);

        TreeConfiguration.createSameTreeConfigs(biomes, plainsKeys,
                APPLE_TREE_PLACED_KEY,
                ORANGE_TREE_PLACED_KEY,
                PEACH_TREE_PLACED_KEY);

        TreeConfiguration.createSameTreeConfigs(biomes, darkForestKeys,
                ALMOND_TREE_PLACED_KEY,
                CASHEW_TREE_PLACED_KEY,
                PECAN_TREE_PLACED_KEY,
                WALNUT_TREE_PLACED_KEY);

        ResourceKey<Biome> woodlands = ResourceKey.create(Registries.BIOME, travID("woodlands"));
        ResourceKey<Biome> wooded_plateau = ResourceKey.create(Registries.BIOME, travID("wooded_plateau"));
        ResourceKey<Biome> wooded_island = ResourceKey.create(Registries.BIOME, travID("wooded_island"));
        ResourceKey<Biome> autumnal_woods = ResourceKey.create(Registries.BIOME, travID("autumnal_woods"));
        ResourceKey<Biome> autumnal_wooded_hills = ResourceKey.create(Registries.BIOME, travID("autumnal_wooded_hills"));
        ResourceKey<Biome> lush_swamp = ResourceKey.create(Registries.BIOME, travID("lush_swamp"));
        ResourceKey<Biome> mini_jungle = ResourceKey.create(Registries.BIOME, travID("mini_jungle"));

        Collection<ResourceKey<Biome>> wooded = Arrays.asList(wooded_island, wooded_plateau, woodlands);
        Collection<ResourceKey<Biome>> autumnal = Arrays.asList(autumnal_woods, autumnal_wooded_hills);
        Collection<ResourceKey<Biome>> jungle = Arrays.asList(mini_jungle);
        Collection<ResourceKey<Biome>> lush = Arrays.asList(lush_swamp);

        TreeConfiguration.createSameTreeConfigs(biomes, wooded,
                APPLE_TREE_PLACED_KEY,
                CHERRY_TREE_PLACED_KEY,
                PLUM_TREE_PLACED_KEY);

        TreeConfiguration.createSameTreeConfigs(biomes, autumnal,
                PEAR_TREE_PLACED_KEY,
                PERSIMMON_TREE_PLACED_KEY,
                PLUM_TREE_PLACED_KEY);

        TreeConfiguration.createSameTreeConfigs(biomes, jungle,
                DATE_TREE_PLACED_KEY,
                DRAGONFRUIT_TREE_PLACED_KEY,
                MANGO_TREE_PLACED_KEY,
                NUTMEG_TREE_PLACED_KEY,
                COCONUT_TREE_PLACED_KEY,
                KUMQUAT_TREE_PLACED_KEY,
                GRAPEFRUIT_TREE_PLACED_KEY,
                BANANA_TREE_PLACED_KEY,
                FIG_TREE_PLACED_KEY,
                CINNAMON_TREE_PLACED_KEY);

        TreeConfiguration.createSameTreeConfigs(biomes, lush,
                CINNAMON_TREE_PLACED_KEY);

        //RegistryKey<Biome> alliumFields = RegistryKey.of(Registries.BIOME_KEY, bygID("allium_fields"));
        //RegistryKey<Biome> amaranthFields = RegistryKey.of(Registries.BIOME_KEY, bygID("amaranth_fields"));
        //RegistryKey<Biome> araucariaSavanna = RegistryKey.of(Registries.BIOME_KEY, bygID("araucaria_savanna"));wwwwwwwwwwww
        ResourceKey<Biome> aspenForest = ResourceKey.create(Registries.BIOME, bygID("aspen_forest"));
        ResourceKey<Biome> autumnalValley = ResourceKey.create(Registries.BIOME, bygID("autumnal_valley"));
        //RegistryKey<Biome> baobabSavanna = RegistryKey.of(Registries.BIOME_KEY, bygID("baobab_savanna"));
        ResourceKey<Biome> bayou = ResourceKey.create(Registries.BIOME, bygID("bayou"));
        ResourceKey<Biome> blackForest = ResourceKey.create(Registries.BIOME, bygID("black_forest"));
        //RegistryKey<Biome> borealisGrove = RegistryKey.of(Registries.BIOME_KEY, bygID("borealis_grove"));
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
                APPLE_TREE_PLACED_KEY);

        TreeConfiguration.createSameTreeConfigs(biomes, Arrays.asList(jacarandaForest, autumnalForest, autumnalTaiga),
                PEAR_TREE_PLACED_KEY,
                PERSIMMON_TREE_PLACED_KEY,
                PLUM_TREE_PLACED_KEY);

        TreeConfiguration.createSameTreeConfigs(biomes, Arrays.asList(cypressSwamplands, whiteMangroveMarshes, temperateRainforest),
                CINNAMON_TREE_PLACED_KEY);

        TreeConfiguration.createSameTreeConfigs(biomes, jungleByg,
                DATE_TREE_PLACED_KEY,
                DRAGONFRUIT_TREE_PLACED_KEY,
                MANGO_TREE_PLACED_KEY,
                NUTMEG_TREE_PLACED_KEY,
                COCONUT_TREE_PLACED_KEY,
                KUMQUAT_TREE_PLACED_KEY,
                GRAPEFRUIT_TREE_PLACED_KEY,
                BANANA_TREE_PLACED_KEY,
                FIG_TREE_PLACED_KEY,
                CINNAMON_TREE_PLACED_KEY);

        TreeConfiguration.createSameTreeConfigs(biomes, nutty,
                ALMOND_TREE_PLACED_KEY,
                CASHEW_TREE_PLACED_KEY,
                PECAN_TREE_PLACED_KEY,
                WALNUT_TREE_PLACED_KEY);

        TreeConfiguration.createSameTreeConfigs(biomes, cherry,
                CHERRY_TREE_PLACED_KEY);

        TreeConfiguration.createSameTreeConfigs(biomes, bygWoods,
                LIME_TREE_PLACED_KEY,
                PEAR_TREE_PLACED_KEY,
                APRICOT_TREE_PLACED_KEY,
                AVOCADO_TREE_PLACED_KEY,
                STARFRUIT_TREE_PLACED_KEY,
                LEMON_TREE_PLACED_KEY,
                CHERRY_TREE_PLACED_KEY,
                PLUM_TREE_PLACED_KEY,
                PERSIMMON_TREE_PLACED_KEY,
                ORANGE_TREE_PLACED_KEY,
                NECTARINE_TREE_PLACED_KEY);

        List<TreeConfiguration> allTreeConfigs = new ArrayList<>();
        for (Map.Entry<ResourceKey<PlacedFeature>, Collection<ResourceKey<Biome>>> entry : biomes.asMap().entrySet()) {
            allTreeConfigs.add(new TreeConfiguration(entry.getKey(), entry.getValue()));
        }

        ConfigurationNode node1 = node.node(nodeToAdd);
        try {
            node1.setList(TreeConfiguration.class, allTreeConfigs);
        } catch (SerializationException e) {
            e.printStackTrace();
        }
    }

    private static ResourceLocation travID(String name) {
        return new ResourceLocation("traverse", name);
    }

    private static ResourceLocation bygID(String name) {
        return new ResourceLocation("byg", name);
    }
}
