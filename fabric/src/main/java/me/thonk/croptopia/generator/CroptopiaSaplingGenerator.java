package me.thonk.croptopia.generator;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.function.Supplier;

public class CroptopiaSaplingGenerator extends SaplingGenerator {

    private final Supplier<RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>>> tree;

    public CroptopiaSaplingGenerator(Supplier<RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>>> tree) {
        this.tree = tree;
    }

    @Nullable
    @Override
    protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        return tree.get();
    }
}
