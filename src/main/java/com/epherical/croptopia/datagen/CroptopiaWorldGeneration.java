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
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.NoiseThresholdCountPlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.neoforged.neoforge.common.loot.AddTableLootModifier;

import java.util.List;

public class CroptopiaWorldGeneration {


    public CroptopiaWorldGeneration() {}


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
                NoiseThresholdCountPlacement.of(-0.7, 0, 8),
                RarityFilter.onAverageOnceEvery(5),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                BiomeFilter.biome());
    }

    private SimpleBlockConfiguration createCropConfiguration(Block block) {
        return new SimpleBlockConfiguration(SimpleStateProvider.simple(block.defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7)));
    }

    private ConfiguredFeature<RandomPatchConfiguration,?> createCropPatch(Block block) {
        return new ConfiguredFeature<>(Feature.RANDOM_PATCH, FeatureUtils.simpleRandomPatchConfiguration(6,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, createCropConfiguration(block))));
    }
}
