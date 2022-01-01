package me.thonk.croptopia.generator;

import me.thonk.croptopia.Croptopia;
import me.thonk.croptopia.config.TreeConfiguration;
import me.thonk.croptopia.registry.GeneratorRegistry;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import org.spongepowered.configurate.serialize.SerializationException;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class BiomeModifiers {


    public static void init(Croptopia croptopia) {

        // generate in ALL biomes
        BiomeModifications.addFeature(context -> {
            Biome biome = context.getBiome();
            return biome.getCategory() != Biome.Category.OCEAN;
        }, GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey("random_crop"));

        try {
            List<TreeConfiguration> trees = croptopia.config.getRootNode().node("treeConfig").getList(TreeConfiguration.class);
            for (TreeConfiguration tree : trees) {
                BiomeModifications.addFeature(BiomeSelectors.includeByKey(tree.getTreesAllowedInBiome()),
                        GenerationStep.Feature.VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey(tree.getFeatureKey()));
            }
        } catch (SerializationException e) {
            e.printStackTrace();
        }

        Collection<RegistryKey<Biome>> exclusion = Arrays.asList(BiomeKeys.SWAMP, BiomeKeys.SWAMP);

        if (croptopia.config.generateSaltInWorld()) {
            BiomeModifications.addFeature(BiomeSelectors.excludeByKey(exclusion),
                    GenerationStep.Feature.UNDERGROUND_ORES, GeneratorRegistry.getFeatureKey("disk_salt_configured"));
        }
    }
}
