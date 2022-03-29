package me.thonk.croptopia.generator;


import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Supplier;

public class CroptopiaSaplingGenerator extends AbstractTreeGrower {

    private final Supplier<Holder<ConfiguredFeature<TreeConfiguration, ?>>> tree;


    public CroptopiaSaplingGenerator(Supplier<Holder<ConfiguredFeature<TreeConfiguration, ?>>> tree) {
        this.tree = tree;
    }

    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random p_60014_, boolean p_60015_) {
        return tree.get();
    }
}
