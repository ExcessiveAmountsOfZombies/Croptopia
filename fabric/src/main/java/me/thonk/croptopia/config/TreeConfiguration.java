package me.thonk.croptopia.config;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TreeConfiguration {

    private Collection<RegistryKey<Biome>> treesAllowedInBiome;
    private String featureKey;

    public TreeConfiguration(String featureKey, Collection<RegistryKey<Biome>> treesAllowedInBiome) {
        this.featureKey = featureKey;
        this.treesAllowedInBiome = treesAllowedInBiome;
    }


    public static void createSameTreeConfigs(SetMultimap<String, RegistryKey<Biome>> map, Collection<RegistryKey<Biome>> biomes, String... keys) {
        for (String key : keys) {
            map.putAll(key, biomes);
        }
    }

    public String getFeatureKey() {
        return featureKey;
    }

    public Collection<RegistryKey<Biome>> getTreesAllowedInBiome() {
        return treesAllowedInBiome;
    }

    public static class Serializer implements TypeSerializer<TreeConfiguration> {
        public static final Serializer INSTANCE = new Serializer();

        private final String KEY_FEATURE_NAME = "featureName";
        private final String KEY_ACCEPTABLE_BIOMES = "acceptableBiomes";

        @Override
        public TreeConfiguration deserialize(Type type, ConfigurationNode node) throws SerializationException {
            String key = node.node(KEY_FEATURE_NAME).getString();
            List<Identifier> ids = node.node(KEY_ACCEPTABLE_BIOMES).getList(Identifier.class);
            List<RegistryKey<Biome>> biomeKeys = new ArrayList<>();
            if (ids != null) {
                for (Identifier id : ids) {
                    biomeKeys.add(RegistryKey.of(Registry.BIOME_KEY, id));
                }
            }

            return new TreeConfiguration(key, biomeKeys);
        }

        @Override
        public void serialize(Type type, TreeConfiguration obj, ConfigurationNode node) throws SerializationException {
            if (obj == null) {
                node.raw(null);
                return;
            }

            node.node(KEY_FEATURE_NAME).set(obj.featureKey);
            List<Identifier> identifiers = new ArrayList<>();
            for (RegistryKey<Biome> registryKey : obj.treesAllowedInBiome) {
                identifiers.add(registryKey.getValue());
            }
            node.node(KEY_ACCEPTABLE_BIOMES).setList(Identifier.class, identifiers);
        }
    }

}
