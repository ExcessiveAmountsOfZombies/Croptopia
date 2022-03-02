package me.thonk.croptopia.datagen;

import me.thonk.croptopia.registry.ItemRegistry;
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
        itemModelGenerator.register(ItemRegistry.roastedPumpkinSeeds, Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.roastedSunflowerSeeds, Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.pumpkinBars, Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.cornBread, Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.pumpkinSoup, Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.meringue, Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.cabbageRoll, Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.borscht, Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.goulash, Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.beetrootSalad, Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.candiedKumquats, Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.shrimp, Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.tuna, Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.calamari, Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.crab, Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.roe, Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.clam, Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.oyster, Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.cookedShrimp, Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.cookedTuna, Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.cookedCalamari, Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.steamedCrab, Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.glowingCalamari, Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.seaLettuce, Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.deepFriedShrimp, Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.tunaRoll, Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.friedCalamari, Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.crabLegs, Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.steamedClams, Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.grilledOysters, Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.anchovy, Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.cookedAnchovy, Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.anchovyPizza, Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.mashedPotatoes, Models.GENERATED);
    }
}
