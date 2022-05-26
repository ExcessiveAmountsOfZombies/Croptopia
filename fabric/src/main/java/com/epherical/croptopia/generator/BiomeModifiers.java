package com.epherical.croptopia.generator;

import com.epherical.croptopia.Croptopia;
import com.epherical.croptopia.config.TreeConfiguration;
import com.epherical.croptopia.registry.GeneratorRegistry;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import org.spongepowered.configurate.serialize.SerializationException;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class BiomeModifiers {


    public static void init(Croptopia croptopia) {

        // generate in ALL biomes
        BiomeModifications.addFeature(context -> true,
                GenerationStep.Decoration.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("random_crop"));

        try {
            List<TreeConfiguration> trees = croptopia.config.getRootNode().node("treeConfig").getList(TreeConfiguration.class);
            for (TreeConfiguration tree : trees) {
                BiomeModifications.addFeature(BiomeSelectors.includeByKey(tree.getTreesAllowedInBiome()),
                        GenerationStep.Decoration.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey(tree.getFeatureKey()));
            }
        } catch (SerializationException e) {
            e.printStackTrace();
        }

        Collection<ResourceKey<Biome>> exclusion = Arrays.asList(Biomes.SWAMP, Biomes.SWAMP);

        if (croptopia.config.generateSaltInWorld()) {
            BiomeModifications.addFeature(BiomeSelectors.excludeByKey(exclusion),
                    GenerationStep.Decoration.UNDERGROUND_ORES, GeneratorRegistry.getFeatureKey("disk_salt_configured"));
        }
    }
}
