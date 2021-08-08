package me.thonk.croptopia.generator;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.function.Supplier;

public class CroptopiaSaplingGenerator extends SaplingGenerator {

    private final Supplier<ConfiguredFeature<TreeFeatureConfig, ?>> tree;

    public CroptopiaSaplingGenerator(Supplier<ConfiguredFeature<TreeFeatureConfig, ?>> tree) {
        this.tree = tree;
    }

    @Nullable
    @Override
    protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random random, boolean bees) {
        return tree.get();
    }
}
