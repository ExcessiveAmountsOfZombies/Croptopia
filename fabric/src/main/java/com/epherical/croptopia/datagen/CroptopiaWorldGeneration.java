package com.epherical.croptopia.datagen;

import com.epherical.croptopia.common.generator.ConfiguredFeatureKeys;
import com.epherical.croptopia.common.generator.PlacedFeatureKeys;
import com.epherical.croptopia.register.helpers.Tree;
import com.epherical.croptopia.register.helpers.TreeCrop;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.concurrent.CompletableFuture;

public class CroptopiaWorldGeneration extends FabricDynamicRegistryProvider {


    public CroptopiaWorldGeneration(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        HolderLookup.RegistryLookup<ConfiguredFeature<?, ?>> lookup = registries.lookupOrThrow(Registries.CONFIGURED_FEATURE);
        HolderLookup.RegistryLookup<PlacedFeature> placed = registries.lookupOrThrow(Registries.PLACED_FEATURE);
        for (TreeCrop treeCrop : TreeCrop.copy()) {
            addConfiguredFeature(treeCrop.getTree(), treeCrop.getTreeConfig(), entries);
            Holder.Reference<ConfiguredFeature<?, ?>> standAlone = Holder.Reference.createStandAlone(lookup, ResourceKey.create(Registries.CONFIGURED_FEATURE, treeCrop.getTree().location()));
            entries.add(ResourceKey.create(Registries.PLACED_FEATURE, treeCrop.getPlacedFeatureName()),
                    new PlacedFeature(standAlone, WorldGenFeatures.datagenModifierLists.get(treeCrop.getPlacedFeatureName())));
        }

        for (Tree tree : Tree.copy()) {
            addConfiguredFeature(tree.getTree(), tree.getTreeGen(), entries);
            Holder.Reference<ConfiguredFeature<?, ?>> standAlone = Holder.Reference.createStandAlone(lookup, ResourceKey.create(Registries.CONFIGURED_FEATURE, tree.getTree().location()));
            entries.add(ResourceKey.create(Registries.PLACED_FEATURE, tree.getPlacedFeatureName()),
                    new PlacedFeature(standAlone, WorldGenFeatures.datagenModifierLists.get(tree.getPlacedFeatureName())));
        }

        addConfiguredFeature(ConfiguredFeatureKeys.DISK_SALT_KEY, WorldGenFeatures.DISK_SALT, entries);
        Holder.Reference<ConfiguredFeature<?, ?>> standAlone = Holder.Reference.createStandAlone(lookup, ConfiguredFeatureKeys.DISK_SALT_KEY);
        entries.add(PlacedFeatureKeys.DISK_SALT_PLACED_KEY, new PlacedFeature(standAlone, WorldGenFeatures.datagenModifierLists.get()))

    }

    @Override
    public String getName() {
        return "Croptopia World Gen";
    }


    private Holder<ConfiguredFeature<?, ?>> addConfiguredFeature(ResourceKey<ConfiguredFeature<?, ?>> key, ConfiguredFeature<?, ?> config, Entries entries) {
        return entries.add(key, config);
    }


}
