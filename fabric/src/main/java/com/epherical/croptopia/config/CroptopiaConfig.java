package com.epherical.croptopia.config;

import com.google.common.collect.HashMultimap;
import com.epherical.croptopia.common.FeatureNames;
import com.epherical.croptopia.common.MiscNames;
import com.epherical.croptopia.Croptopia;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;
import org.spongepowered.configurate.serialize.TypeSerializerCollection;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CroptopiaConfig {

    protected static final Logger LOGGER = LogManager.getLogger();

    protected HoconConfigurationLoader loader;
    protected ConfigurationNode rootNode;

    protected final String configName;
    protected final boolean devEnvironment;
    private TypeSerializerCollection.Builder serializers;

    public CroptopiaConfig(boolean devEnvironment, String configName) {
        this.devEnvironment = devEnvironment;
        this.configName = configName;
        this.serializers = TypeSerializerCollection.builder();
    }

    public CroptopiaConfig(boolean devEnvironment) {
        this(devEnvironment, "croptopia.conf");
    }

    public <T, V extends TypeSerializer<T>> void addSerializer(Class<T> clazz, V instance) {
        serializers.register(clazz, instance);
    }

    public void addSerializer(TypeSerializerCollection collection) {
        serializers.registerAll(collection);
    }


    public boolean loadConfig() {
        File configDirectory = new File(FabricLoader.getInstance().getConfigDir().toFile(), MiscNames.MOD_ID);
        File file = new File(configDirectory, configName);

        boolean createdFile = false;
        URL path = null;
        if (devEnvironment) {
            // If we are in a development environment, we are going to use the config that is present in the jar instead.
            path = getClass().getClassLoader().getResource(configName);
        } else {
            try (InputStream stream = getClass().getClassLoader().getResourceAsStream(configName)) {
                byte[] bytes = new byte[stream.available()];
                stream.read(bytes);
                LOGGER.debug("Creating default config file: " + configName);
                createdFile = createdFile(file);
                if (createdFile) {
                    try (FileOutputStream outputStream = new FileOutputStream(file)) {
                        outputStream.write(bytes);
                    }
                }
            } catch (Exception e) {
                LOGGER.warn("Could not find an internal config file for {}", configName);
            } finally {
                try {
                    createdFile = createdFile(file);
                    path = file.toURI().toURL();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }
        this.loader = HoconConfigurationLoader.builder()
                .sink(() -> new BufferedWriter(new FileWriter(file)))
                .defaultOptions(options -> options.serializers(builder -> builder.registerAll(serializers.build())))
                .url(path)
                .build();
        try {
            if (createdFile) {
                this.loader.save(generateConfig(CommentedConfigurationNode.root()));
            }
        } catch (ConfigurateException e) {
            e.printStackTrace();
        }
        try {
            this.rootNode = loader.load();
            parseConfig(rootNode);
        } catch (ConfigurateException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    protected ConfigurationNode generateConfig(CommentedConfigurationNode node) {
        try {
            node.node("generateSaltInWorld").set(true);
        } catch (SerializationException e) {
            e.printStackTrace();
        }
        return node;
    }

    protected void parseConfig(ConfigurationNode node) {
        File configDirectory = new File(FabricLoader.getInstance().getConfigDir().toFile(), MiscNames.MOD_ID);

        if (configDirectory.exists()) {
            addTreeConfigIfDoesNotExist(node, "treeConfig");
        }
    }

    private boolean canCreateFile(File file) {
        return file.exists();
    }

    private boolean createdFile(File file) {
        try {
            if (!file.getParentFile().exists() && file.getParentFile().mkdirs()) {
                LOGGER.debug("Created directory for: " + file.getParentFile().getCanonicalPath());
            }

            if (!file.exists() && file.createNewFile()) {
                return true;
            }
        } catch (IOException e) {
            LOGGER.warn("Error creating new config file ", e);
            return false;
        }
        return false;
    }

    public ConfigurationNode getRootNode() {
        return rootNode;
    }

    public boolean generateSaltInWorld() {
        return getRootNode().node("generateSaltInWorld").getBoolean();
    }

    private boolean addTreeConfigIfDoesNotExist(ConfigurationNode node, String nodeToAdd) {
        if (!node.hasChild(nodeToAdd)) {
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
                    "apple_tree_configured",
                    "cherry_tree_configured",
                    "plum_tree_configured");

            TreeConfiguration.createSameTreeConfigs(biomes, autumnal,
                    "pear_tree_configured",
                    "persimmon_tree_configured",
                    "plum_tree_configured");

            TreeConfiguration.createSameTreeConfigs(biomes, jungle,
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

            TreeConfiguration.createSameTreeConfigs(biomes, lush,
                    FeatureNames.CINNAMON_TREE_CONFIGURED);

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
                allTreeConfigs.add(new TreeConfiguration(entry.getKey(), entry.getValue()));
            }

            ConfigurationNode node1 = node.node(nodeToAdd);
            try {
                node1.setList(TreeConfiguration.class, allTreeConfigs);
            } catch (SerializationException e) {
                e.printStackTrace();
            }
            try {
                loader.save(node);
            } catch (ConfigurateException e) {
                e.printStackTrace();
            }

            return true;
        }

        return false;
    }

    private static ResourceLocation travID(String name) {
        return new ResourceLocation("traverse", name);
    }

    private static ResourceLocation bygID(String name) {
        return new ResourceLocation("byg", name);
    }
}
