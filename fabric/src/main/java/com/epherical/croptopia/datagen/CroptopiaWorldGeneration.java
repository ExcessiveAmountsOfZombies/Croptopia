package com.epherical.croptopia.datagen;

import com.epherical.croptopia.register.helpers.Tree;
import com.epherical.croptopia.register.helpers.TreeCrop;
import com.epherical.croptopia.registry.GeneratorRegistry;
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
        for (TreeCrop treeCrop : TreeCrop.copy()) {
            addTreeConfiguredFeature(treeCrop.getTree(), treeCrop.getTreeConfig(), entries);
        }

        for (Tree tree : Tree.copy()) {
            addTreeConfiguredFeature(tree.getTree(), tree.getTreeGen(), entries);
        }

        // TODO: not sure how to fix this, but the ConfiguredFeature json gets generated above, but then the PlacedFeature also generates whats
        //  in the ConfiguredFeature json instead of just being a reference. It's probably because of the Holder.direct, but how do you turn it into
        //  a reference?
        //Holder.Reference.createStandAlone(lookup, ).
        GeneratorRegistry.datagenPlacedFeatures.forEach((key, value) -> {
            entries.add(key, value.value());
        });
    }

    @Override
    public String getName() {
        return "Croptopia World Gen";
    }


    private Holder<ConfiguredFeature<?, ?>> addTreeConfiguredFeature(ResourceKey<ConfiguredFeature<?, ?>> key, ConfiguredFeature<?, ?> config, Entries entries) {
        return entries.add(key, config);
    }

}
