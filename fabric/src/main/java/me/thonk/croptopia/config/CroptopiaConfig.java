package me.thonk.croptopia.config;

import me.thonk.common.MiscNames;
import me.thonk.croptopia.Constants;
import me.thonk.croptopia.Croptopia;
import net.fabricmc.loader.api.FabricLoader;
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
}
