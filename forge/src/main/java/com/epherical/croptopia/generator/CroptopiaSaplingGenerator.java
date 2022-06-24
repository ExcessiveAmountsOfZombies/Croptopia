package com.epherical.croptopia.generator;


import java.util.Random;
import java.util.function.Supplier;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import javax.annotation.Nullable;

public class CroptopiaSaplingGenerator extends Tree {

    private final Supplier<ConfiguredFeature<BaseTreeFeatureConfig, ?>> tree;

    public CroptopiaSaplingGenerator(Supplier<ConfiguredFeature<BaseTreeFeatureConfig, ?>> tree) {
        this.tree = tree;
    }

    @Nullable
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random p_225546_1_, boolean p_225546_2_) {
        return tree.get();
    }
}
