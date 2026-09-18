package com.epherical.croptopia.datagen;

import com.epherical.croptopia.blocks.CroptopiaCropBlock;
import com.epherical.croptopia.common.generator.ConfiguredFeatureKeys;
import com.epherical.croptopia.common.generator.PlacedFeatureKeys;
import com.epherical.croptopia.register.helpers.FarmlandCrop;
import com.epherical.croptopia.register.helpers.Tree;
import com.epherical.croptopia.register.helpers.TreeCrop;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.NoiseThresholdProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.List;
import java.util.Random;

public class CroptopiaWorldGeneration {


    public CroptopiaWorldGeneration() {
    }


    public void addConfiguredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        for (TreeCrop treeCrop : TreeCrop.TREE_CROPS)
            context.register(treeCrop.getConfiguredFeatureKey(), treeCrop.getTreeConfig());
        for (Tree tree : Tree.copy())
            context.register(tree.getConfiguredFeatureKey(), tree.getTreeGen());
        for (FarmlandCrop farmlandCrop : FarmlandCrop.FARMLAND_CROPS)
            context.register(farmlandCrop.getConfigKey(), createCropPatch(farmlandCrop.asBlock()));

        context.register(ConfiguredFeatureKeys.DISK_SALT_KEY, WorldGenFeatures.DISK_SALT);
        //context.register(ConfiguredFeatureKeys.RANDOM_CROP_KEY, WorldGenFeatures.RANDOM_CROP);
    }

    public void addPlacedFeatures(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> lookup = context.lookup(Registries.CONFIGURED_FEATURE);
        for (TreeCrop treeCrop : TreeCrop.TREE_CROPS)
            context.register(treeCrop.getPlacedFeatureKey(), new PlacedFeature(lookup.getOrThrow(treeCrop.getConfiguredFeatureKey()), WorldGenFeatures.datagenModifierLists.get(treeCrop.getPlacedFeatureKey())));
        for (Tree tree : Tree.copy())
            context.register(tree.getPlacedFeatureKey(), new PlacedFeature(lookup.getOrThrow(tree.getConfiguredFeatureKey()), WorldGenFeatures.datagenModifierLists.get(tree.getPlacedFeatureKey())));
        context.register(PlacedFeatureKeys.DISK_SALT_PLACED_KEY, new PlacedFeature(lookup.getOrThrow(ConfiguredFeatureKeys.DISK_SALT_KEY), WorldGenFeatures.datagenModifierLists.get(PlacedFeatureKeys.DISK_SALT_PLACED_KEY)));
        for (FarmlandCrop farmlandCrop : FarmlandCrop.FARMLAND_CROPS)
            context.register(farmlandCrop.getPlacedKey(), new PlacedFeature(lookup.getOrThrow(farmlandCrop.getConfigKey()), modifiers()));
    }

    private List<PlacementModifier> modifiers() {
        return List.of(
                RarityFilter.onAverageOnceEvery(7),
                InSquarePlacement.spread(),
                CountPlacement.of(UniformInt.of(1, 3)),
                RandomOffsetPlacement.horizontal(UniformInt.of(-3, 3)),
                PlacementUtils.HEIGHTMAP,
                BiomeFilter.biome(),
                BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE));
    }

    private SimpleBlockConfiguration createCropConfiguration(Block block) {
        Random random = new Random();
        return new SimpleBlockConfiguration(
                new NoiseThresholdProvider(
                        random.nextLong(100, 10000000L),
                        new NormalNoise.NoiseParameters(0, random.nextDouble(1.0, 5.0)),
                        0.005F,
                        -0.8F,
                        0.25F,
                        block.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7),
                        List.of(Blocks.AIR.defaultBlockState()), List.of(Blocks.AIR.defaultBlockState())
                )
        );
    }

    private ConfiguredFeature<SimpleBlockConfiguration, ?> createCropPatch(Block block) {
        return new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, createCropConfiguration(block));
    }
}
