package me.thonk.croptopia.generator;

import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.function.Supplier;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

public class CroptopiaSaplingGenerator extends AbstractTreeGrower {

    private final Supplier<Holder<ConfiguredFeature<TreeConfiguration, ?>>> tree;

    public CroptopiaSaplingGenerator(Supplier<Holder<ConfiguredFeature<TreeConfiguration, ?>>> tree) {
        this.tree = tree;
    }

    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random random, boolean bees) {
        return tree.get();
    }
}
