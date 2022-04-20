package me.thonk.croptopia.datagen;

import me.thonk.croptopia.registry.Content;
import me.thonk.croptopia.registry.Content;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class CroptopiaModelProvider extends FabricModelProvider {


    public CroptopiaModelProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(Content.ROASTED_PUMPKIN_SEEDS, Models.GENERATED);
        itemModelGenerator.register(Content.ROASTED_SUNFLOWER_SEEDS, Models.GENERATED);
        itemModelGenerator.register(Content.PUMPKIN_BARS, Models.GENERATED);
        itemModelGenerator.register(Content.CORN_BREAD, Models.GENERATED);
        itemModelGenerator.register(Content.PUMPKIN_SOUP, Models.GENERATED);
        itemModelGenerator.register(Content.MERINGUE, Models.GENERATED);
        itemModelGenerator.register(Content.CABBAGE_ROLL, Models.GENERATED);
        itemModelGenerator.register(Content.BORSCHT, Models.GENERATED);
        itemModelGenerator.register(Content.GOULASH, Models.GENERATED);
        itemModelGenerator.register(Content.BEETROOT_SALAD, Models.GENERATED);
        itemModelGenerator.register(Content.CANDIED_KUMQUATS, Models.GENERATED);
        itemModelGenerator.register(Content.Seafood.SHRIMP.asItem(), Models.GENERATED);
        itemModelGenerator.register(Content.Seafood.TUNA.asItem(), Models.GENERATED);
        itemModelGenerator.register(Content.Seafood.CALAMARI.asItem(), Models.GENERATED);
        itemModelGenerator.register(Content.Seafood.CRAB.asItem(), Models.GENERATED);
        itemModelGenerator.register(Content.Seafood.ROE.asItem(), Models.GENERATED);
        itemModelGenerator.register(Content.Seafood.CLAM.asItem(), Models.GENERATED);
        itemModelGenerator.register(Content.Seafood.OYSTER.asItem(), Models.GENERATED);
        itemModelGenerator.register(Content.Furnace.COOKED_SHRIMP.asItem(), Models.GENERATED);
        itemModelGenerator.register(Content.Furnace.COOKED_TUNA.asItem(), Models.GENERATED);
        itemModelGenerator.register(Content.Furnace.COOKED_CALAMARI.asItem(), Models.GENERATED);
        itemModelGenerator.register(Content.STEAMED_CRAB, Models.GENERATED);
        itemModelGenerator.register(Content.Seafood.GLOWING_CALAMARI.asItem(), Models.GENERATED);
        itemModelGenerator.register(Content.SEA_LETTUCE, Models.GENERATED);
        itemModelGenerator.register(Content.DEEP_FRIED_SHRIMP, Models.GENERATED);
        itemModelGenerator.register(Content.TUNA_ROLL, Models.GENERATED);
        itemModelGenerator.register(Content.FRIED_CALAMARI, Models.GENERATED);
        itemModelGenerator.register(Content.CRAB_LEGS, Models.GENERATED);
        itemModelGenerator.register(Content.STEAMED_CLAMS, Models.GENERATED);
        itemModelGenerator.register(Content.GRILLED_OYSTERS, Models.GENERATED);
        itemModelGenerator.register(Content.Seafood.ANCHOVY.asItem(), Models.GENERATED);
        itemModelGenerator.register(Content.Furnace.COOKED_ANCHOVY.asItem(), Models.GENERATED);
        itemModelGenerator.register(Content.ANCHOVY_PIZZA, Models.GENERATED);
        itemModelGenerator.register(Content.MASHED_POTATOES, Models.GENERATED);
    }
}
