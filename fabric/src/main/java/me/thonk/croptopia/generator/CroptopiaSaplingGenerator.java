package com.epherical.croptopia.generator;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class CroptopiaSaplingGenerator extends SaplingGenerator {

    private final ConfiguredFeature<TreeFeatureConfig, ?> tree;

    public CroptopiaSaplingGenerator(ConfiguredFeature<?, ?> tree) {
        this.tree = (ConfiguredFeature<TreeFeatureConfig, ?>) tree;
    }

    @Override
    protected @Nullable ConfiguredFeature<TreeFeatureConfig, ?> createTreeFeature(Random random, boolean bl) {
        return tree;
    }
}
