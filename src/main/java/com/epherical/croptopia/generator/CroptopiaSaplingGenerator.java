package com.epherical.croptopia.generator;


import java.util.function.Supplier;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

public class CroptopiaSaplingGenerator extends AbstractTreeGrower {

    private final Supplier<Holder<ConfiguredFeature<TreeConfiguration, ?>>> tree;

    public CroptopiaSaplingGenerator(Supplier<Holder<ConfiguredFeature<TreeConfiguration, ?>>> tree) {
        this.tree = tree;
    }

    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean b) {
        return tree.get();
    }
}
