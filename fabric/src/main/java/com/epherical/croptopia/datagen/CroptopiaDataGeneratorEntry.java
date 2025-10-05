package com.epherical.croptopia.datagen;

import com.epherical.croptopia.common.generator.ConfiguredFeatureKeys;
import com.epherical.croptopia.register.Content;
import com.epherical.croptopia.register.helpers.Tree;
import com.epherical.croptopia.register.helpers.TreeCrop;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class CroptopiaDataGeneratorEntry implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack resources = fabricDataGenerator.createPack();
        resources.addProvider(CroptopiaBlockTagProvider::new);
        resources.addProvider(CroptopiaItemTagProvider::new);
        resources.addProvider(CroptopiaBiomeTagProvider::new);
        resources.addProvider(CroptopiaIndependentItemTagProvider::new);
        // tags always first
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            resources.addProvider(CroptopiaModelProvider::new);
        } else {
            Content.class.getName();
        }
        resources.addProvider(CroptopiaRecipeProvider::new);
        resources.addProvider(CroptopiaWorldGeneration::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.CONFIGURED_FEATURE, bootstrapContext -> {
            for (TreeCrop treeCrop : TreeCrop.TREE_CROPS) {
                bootstrapContext.register(treeCrop.getConfiguredFeatureKey(), treeCrop.getTreeConfig());
            }
            for (Tree tree : Tree.copy()) {
                bootstrapContext.register(tree.getConfiguredFeatureKey(), tree.getTreeGen());
            }
            bootstrapContext.register(ConfiguredFeatureKeys.DISK_SALT_KEY, WorldGenFeatures.DISK_SALT);
            bootstrapContext.register(ConfiguredFeatureKeys.RANDOM_CROP_KEY, WorldGenFeatures.RANDOM_CROP);
        });
    }
}
