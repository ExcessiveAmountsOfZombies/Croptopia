package com.epherical.croptopia.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class CroptopiaDataGeneratorEntry implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack resources = fabricDataGenerator.createPack();
        resources.addProvider(CroptopiaBlockTagProvider::new);
        resources.addProvider(CroptopiaItemTagProvider::new);
        resources.addProvider(CroptopiaBiomeTagProvider::new);
        resources.addProvider(CroptopiaIndependentItemTagProvider::new);
        // tags always first
        resources.addProvider(CroptopiaModelProvider::new);
        resources.addProvider(CroptopiaRecipeProvider::new);
        resources.addProvider(CroptopiaWorldGeneration::new);

    }
}
