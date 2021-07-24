package me.thonk.croptopia.generator;


import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import javax.annotation.Nullable;
import java.util.Random;

public class CroptopiaSaplingGenerator extends AbstractTreeGrower {

    private final ConfiguredFeature<TreeConfiguration, ?> tree;

    public CroptopiaSaplingGenerator(ConfiguredFeature<?, ?> tree) {
        this.tree = (ConfiguredFeature<TreeConfiguration, ?>) tree;
    }

    @Nullable
    @Override
    protected ConfiguredFeature<TreeConfiguration, ?> getConfiguredFeature(Random p_60014_, boolean p_60015_) {
        return tree;
    }
}
