package me.thonk.croptopia.generator;


import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.Random;

public class CroptopiaSaplingGenerator extends Tree {

    private final ConfiguredFeature<BaseTreeFeatureConfig, ?> tree;

    public CroptopiaSaplingGenerator(ConfiguredFeature<?, ?> tree) {
        this.tree = (ConfiguredFeature<BaseTreeFeatureConfig, ?>) tree;
    }


    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean largeHive) {
        return tree;
    }
}
