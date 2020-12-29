package me.thonk.croptopia.generator;

import net.minecraft.block.trees.Tree;
import net.minecraft.item.Items;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

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
