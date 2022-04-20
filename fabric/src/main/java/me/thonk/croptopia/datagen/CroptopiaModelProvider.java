package me.thonk.croptopia.datagen;

import me.thonk.croptopia.registry.Content;
import me.thonk.croptopia.registry.Content;
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
        itemModelGenerator.generateFlatItem(Content.Seafood.SHRIMP.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.Seafood.TUNA.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.Seafood.CALAMARI.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.Seafood.CRAB.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.Seafood.ROE.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.Seafood.CLAM.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.Seafood.OYSTER.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.Furnace.COOKED_SHRIMP.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.Furnace.COOKED_TUNA.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.Furnace.COOKED_CALAMARI.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.STEAMED_CRAB, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.Seafood.GLOWING_CALAMARI.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.SEA_LETTUCE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.DEEP_FRIED_SHRIMP, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.TUNA_ROLL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.FRIED_CALAMARI, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.CRAB_LEGS, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.STEAMED_CLAMS, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.GRILLED_OYSTERS, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.Seafood.ANCHOVY.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.Furnace.COOKED_ANCHOVY.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.ANCHOVY_PIZZA, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Content.MASHED_POTATOES, ModelTemplates.FLAT_ITEM);
    }
}
