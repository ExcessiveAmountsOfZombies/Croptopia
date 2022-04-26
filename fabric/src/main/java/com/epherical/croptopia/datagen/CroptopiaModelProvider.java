package com.epherical.croptopia.datagen;

import com.epherical.croptopia.register.Content;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;

public class CroptopiaModelProvider extends FabricModelProvider {


    public CroptopiaModelProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(Content.ROASTED_PUMPKIN_SEEDS, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.ROASTED_SUNFLOWER_SEEDS, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.PUMPKIN_BARS, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.CORN_BREAD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.PUMPKIN_SOUP, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.MERINGUE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.CABBAGE_ROLL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.BORSCHT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.GOULASH, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.BEETROOT_SALAD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.CANDIED_KUMQUATS, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.SHRIMP.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.TUNA.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.CALAMARI.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.CRAB.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.ROE.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.CLAM.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.OYSTER.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.COOKED_SHRIMP.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.COOKED_TUNA.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.COOKED_CALAMARI.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.STEAMED_CRAB, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.GLOWING_CALAMARI.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.SEA_LETTUCE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.DEEP_FRIED_SHRIMP, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.TUNA_ROLL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.FRIED_CALAMARI, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.CRAB_LEGS, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.STEAMED_CLAMS, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.GRILLED_OYSTERS, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.ANCHOVY.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.COOKED_ANCHOVY.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.ANCHOVY_PIZZA, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.MASHED_POTATOES, ModelTemplates.FLAT_ITEM);
    }
}
