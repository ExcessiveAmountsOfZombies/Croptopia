package com.epherical.croptopia.generator;


import java.util.function.Supplier;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

public class CroptopiaSaplingGenerator extends AbstractTreeGrower {

    private final Supplier<ResourceKey<ConfiguredFeature<?, ?>>> tree;

    public CroptopiaSaplingGenerator(Supplier<ResourceKey<ConfiguredFeature<?, ?>>> tree) {
        this.tree = tree;
    }

    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean b) {
        return tree.get();
    }
}
