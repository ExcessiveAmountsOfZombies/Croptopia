package me.thonk.croptopia.config;

import com.google.common.collect.HashMultimap;
import me.thonk.common.FeatureNames;
import me.thonk.common.MiscNames;
import me.thonk.croptopia.Constants;
import me.thonk.croptopia.Croptopia;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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

        URL path = null;
        if (devEnvironment) {
            // If we are in a development environment, we are going to use the config that is present in the jar instead.
            path = getClass().getClassLoader().getResource(configName);
        } else {
            try (InputStream stream = getClass().getClassLoader().getResourceAsStream(configName)) {
                byte[] bytes = new byte[stream.available()];
                stream.read(bytes);
                LOGGER.debug("Creating default config file: " + configName);
                if (createdFile(file)) {
                    try (FileOutputStream outputStream = new FileOutputStream(file)) {
                        outputStream.write(bytes);
                    }
                }
                path = file.toURI().toURL();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.loader = HoconConfigurationLoader.builder()
                .sink(() -> {
                    if (!canCreateFile(file)) {
                        return null;
                    }
                    return new BufferedWriter(new FileWriter(file));
                })
                .defaultOptions(options -> options.serializers(builder -> builder.registerAll(serializers.build())))
                .url(path)
                .build();
        try {
            this.rootNode = loader.load();
            parseConfig(rootNode);
        } catch (ConfigurateException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    protected void parseConfig(ConfigurationNode node) {
        File configDirectory = new File(FabricLoader.getInstance().getConfigDir().toFile(), MiscNames.MOD_ID);

        if (configDirectory.exists()) {
            // now we're going to delete the options file. This is our conversion code
            File file = new File(configDirectory, "options.json");
            if (file.exists()) {
                ConfigurationNode cropsNode = node.node("configuredSeeds");
                try {
                    Constants.OPTIONS.readOptionsFile(file);
                    cropsNode.setList(ConfigurableSeed.class, Constants.OPTIONS.readConfiguredSeeds(file));
                    node.node("generateSaltInWorld").set(!Constants.OPTIONS.disableSaltOre);
                } catch (SerializationException e) {
                    e.printStackTrace();
                }
                try {
                    loader.save(node);
                } catch (ConfigurateException e) {
                    e.printStackTrace();
                }
                try {
                    Files.delete(file.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                LOGGER.info("Deleted old options json file");
            } else {
                addTreeConfigIfDoesNotExist(node, "treeConfig");
                if (!node.hasChild("configuredSeeds")) {
                    ConfigurationNode cropsNode = node.node("configuredSeeds");
                    try {
                        cropsNode.setList(ConfigurableSeed.class, Croptopia.getSeeds());
                    } catch (SerializationException e) {
                        e.printStackTrace();
                    }
                    try {
                        loader.save(node);
                    } catch (ConfigurateException e) {
                        e.printStackTrace();
                    }
                }
            }
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
            Collection<RegistryKey<Biome>> forestBiomes = Arrays.asList(BiomeKeys.FOREST, BiomeKeys.WOODED_HILLS, BiomeKeys.FLOWER_FOREST);
            Collection<RegistryKey<Biome>> jungleBiomes = Arrays.asList(BiomeKeys.JUNGLE, BiomeKeys.JUNGLE_EDGE, BiomeKeys.JUNGLE_HILLS,
                    BiomeKeys.MODIFIED_JUNGLE, BiomeKeys.MODIFIED_JUNGLE_EDGE);
            Collection<RegistryKey<Biome>> plainsKeys = Arrays.asList(BiomeKeys.PLAINS, BiomeKeys.SUNFLOWER_PLAINS);
            Collection<RegistryKey<Biome>> darkForestKeys = Arrays.asList(BiomeKeys.DARK_FOREST, BiomeKeys.DARK_FOREST_HILLS);

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
                    "fig_tree_configured");

            TreeConfiguration.createSameTreeConfigs(biomes, plainsKeys,
                    "apple_tree_configured",
                    "orange_tree_configured",
                    "peach_tree_configured");

            TreeConfiguration.createSameTreeConfigs(biomes, darkForestKeys,
                    "almond_tree_configured",
                    "cashew_tree_configured",
                    "pecan_tree_configured",
                    "walnut_tree_configured");

            RegistryKey<Biome> woodlands = RegistryKey.of(Registry.BIOME_KEY, travID("woodlands"));
            RegistryKey<Biome> wooded_plateau = RegistryKey.of(Registry.BIOME_KEY, travID("wooded_plateau"));
            RegistryKey<Biome> wooded_island = RegistryKey.of(Registry.BIOME_KEY, travID("wooded_island"));
            RegistryKey<Biome> autumnal_woods = RegistryKey.of(Registry.BIOME_KEY, travID("autumnal_woods"));
            RegistryKey<Biome> autumnal_wooded_hills = RegistryKey.of(Registry.BIOME_KEY, travID("autumnal_wooded_hills"));
            RegistryKey<Biome> lush_swamp = RegistryKey.of(Registry.BIOME_KEY, travID("lush_swamp"));
            RegistryKey<Biome> mini_jungle = RegistryKey.of(Registry.BIOME_KEY, travID("mini_jungle"));

            Collection<RegistryKey<Biome>> wooded = Arrays.asList(wooded_island, wooded_plateau, woodlands);
            Collection<RegistryKey<Biome>> autumnal = Arrays.asList(autumnal_woods, autumnal_wooded_hills);
            Collection<RegistryKey<Biome>> jungle = Arrays.asList(mini_jungle);
            Collection<RegistryKey<Biome>> lush = Arrays.asList(lush_swamp);

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

            List<TreeConfiguration> allTreeConfigs = new ArrayList<>();
            for (Map.Entry<String, Collection<RegistryKey<Biome>>> entry : biomes.asMap().entrySet()) {
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

    private static Identifier travID(String name) {
        return new Identifier("traverse", name);
    }
}
