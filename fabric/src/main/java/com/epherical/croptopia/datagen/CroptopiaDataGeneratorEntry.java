package com.epherical.croptopia.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class CroptopiaDataGeneratorEntry implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.addProvider(CroptopiaBlockTagProvider::new);
        fabricDataGenerator.addProvider(CroptopiaItemTagProvider::new);
        fabricDataGenerator.addProvider(CroptopiaBiomeTagProvider::new);
        fabricDataGenerator.addProvider(CroptopiaIndependentItemTagProvider::new);
        // tags always first
        fabricDataGenerator.addProvider(CroptopiaModelProvider::new);
        fabricDataGenerator.addProvider(CroptopiaRecipeProvider::new);
    }
}
