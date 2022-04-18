package me.thonk.croptopia.datagen;

import me.thonk.croptopia.registry.Content;
import me.thonk.croptopia.registry.ItemRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.tag.ItemTags;

public class CroptopiaItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public CroptopiaItemTagProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateTags() {
        generateSaplings();
        generateBarkLogs();
        // currently, only generates air, but leaves item tag isn't used by vanilla anyway
        // generateLeaves();
        generateMisc();
    }

    protected void generateSaplings() {
        FabricTagBuilder<Item> saplings = getOrCreateTagBuilder(ItemTags.SAPLINGS);
        for (Content.Tree crop : Content.Tree.values()) {
            saplings.add(crop.getSapling());
        }
        for (Content.Bark crop : Content.Bark.values()) {
            saplings.add(crop.getSapling());
        }
    }

    protected void generateBarkLogs() {
        FabricTagBuilder<Item> burnableLog = getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN);
        for (Content.Bark crop : Content.Bark.values()) {
            // add different log types to log tag of this crop
            getOrCreateTagBuilder(crop.getLogItemTag())
                    .add(crop.getLog().asItem())
                    .add(crop.getStrippedLog().asItem())
                    .add(crop.getWood().asItem())
                    .add(crop.getStrippedWood().asItem());
            // make this crop log burnable
            burnableLog.addTag(crop.getLogItemTag());
        }
    }

    protected void generateLeaves() {
        FabricTagBuilder<Item> leaves = getOrCreateTagBuilder(ItemTags.LEAVES);
        for (Content.Tree crop : Content.Tree.values()) {
            leaves.add(crop.getLeaves().asItem());
        }
        for (Content.Bark crop : Content.Bark.values()) {
            leaves.add(crop.getLeaves().asItem());
        }
    }

    protected void generateMisc() {
        // explicitly used as dolphin food in vanilla
        FabricTagBuilder<Item> fishes = getOrCreateTagBuilder(ItemTags.FISHES);
        fishes.add(Content.Seafood.ANCHOVY.asItem());
        fishes.add(Content.Seafood.CALAMARI.asItem());
        fishes.add(Content.Seafood.CLAM.asItem());
        fishes.add(Content.Seafood.CRAB.asItem());
        fishes.add(Content.Seafood.OYSTER.asItem());
        fishes.add(Content.Seafood.ROE.asItem());
        fishes.add(Content.Seafood.SHRIMP.asItem());
        fishes.add(Content.Seafood.TUNA.asItem());
        // fox food: all berries added by croptopia
        FabricTagBuilder<Item> foxFood = getOrCreateTagBuilder(ItemTags.FOX_FOOD);
        foxFood.add(Content.Farmland.BLACKBERRY.asItem());
        foxFood.add(Content.Farmland.BLUEBERRY.asItem());
        foxFood.add(Content.Farmland.CRANBERRY.asItem());
        foxFood.add(Content.Farmland.ELDERBERRY.asItem());
        foxFood.add(Content.Farmland.RASPBERRY.asItem());
        foxFood.add(Content.Farmland.STRAWBERRY.asItem());
        // piglin food: more cannibalism (which already happens in vanilla)
        FabricTagBuilder<Item> piglinFood = getOrCreateTagBuilder(ItemTags.PIGLIN_FOOD);
        piglinFood.add(ItemRegistry.hamSandwich);
        piglinFood.add(ItemRegistry.pepperoni);
        piglinFood.add(ItemRegistry.porkAndBeans);
        piglinFood.add(ItemRegistry.porkJerky);
        piglinFood.add(ItemRegistry.rawBacon);
        piglinFood.add(ItemRegistry.cookedBacon);
    }

}
