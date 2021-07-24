package me.thonk.croptopia.generator;


import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Supplier;

public class CroptopiaSaplingGenerator extends AbstractTreeGrower {

    private final Supplier<ConfiguredFeature<TreeConfiguration, ?>> tree;


    public CroptopiaSaplingGenerator(Supplier<ConfiguredFeature<TreeConfiguration, ?>> tree) {
        this.tree = tree;
    }

    @Nullable
    @Override
    protected ConfiguredFeature<TreeConfiguration, ?> getConfiguredFeature(Random p_60014_, boolean p_60015_) {
        return tree.get();
    }
}
