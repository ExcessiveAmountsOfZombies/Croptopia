package me.thonk.croptopia.datagen;

import me.thonk.croptopia.registry.Content;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.BlockTags;
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

}
