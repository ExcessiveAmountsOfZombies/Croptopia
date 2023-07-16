package com.epherical.croptopia.config;

import com.google.common.collect.SetMultimap;
import net.minecraft.core.registries.Registries;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public class TreeConfiguration {

    private Set<ResourceKey<Biome>> treesAllowedInBiome;
    private String featureKey;

    public TreeConfiguration(String featureKey, Collection<ResourceKey<Biome>> treesAllowedInBiome) {
        this.featureKey = featureKey;
        this.treesAllowedInBiome = Set.copyOf(treesAllowedInBiome);
    }


    public static void createSameTreeConfigs(SetMultimap<String, ResourceKey<Biome>> map, Collection<ResourceKey<Biome>> biomes, String... keys) {
        for (String key : keys) {
            map.putAll(key, biomes);
        }
    }

    public String getFeatureKey() {
        return featureKey;
    }

    public Set<ResourceKey<Biome>> getTreesAllowedInBiome() {
        return treesAllowedInBiome;
    }

    public static class Serializer implements TypeSerializer<TreeConfiguration> {
        public static final Serializer INSTANCE = new Serializer();

        private final String KEY_FEATURE_NAME = "featureName";
        private final String KEY_ACCEPTABLE_BIOMES = "acceptableBiomes";

        @Override
        public TreeConfiguration deserialize(Type type, ConfigurationNode node) throws SerializationException {
            String key = node.node(KEY_FEATURE_NAME).getString();
            List<ResourceLocation> ids = node.node(KEY_ACCEPTABLE_BIOMES).getList(ResourceLocation.class);
            List<ResourceKey<Biome>> biomeKeys = new ArrayList<>();
            if (ids != null) {
                for (ResourceLocation id : ids) {
                    biomeKeys.add(ResourceKey.create(Registries.BIOME, id));
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
            List<ResourceLocation> identifiers = new ArrayList<>();
            for (ResourceKey<Biome> registryKey : obj.treesAllowedInBiome) {
                identifiers.add(registryKey.location());
            }
            node.node(KEY_ACCEPTABLE_BIOMES).setList(ResourceLocation.class, identifiers);
        }
    }

}
