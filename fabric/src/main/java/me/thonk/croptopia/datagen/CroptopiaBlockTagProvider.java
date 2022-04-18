package me.thonk.croptopia.datagen;

import me.thonk.croptopia.registry.Content;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.ItemTags;

public class CroptopiaBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public CroptopiaBlockTagProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateTags() {
        generateSaplings();
        generateBarkLogs();
    }

    protected void generateSaplings() {
        FabricTagBuilder<Block> saplings = getOrCreateTagBuilder(BlockTags.SAPLINGS);
        for (Content.Tree crop : Content.Tree.values()) {
            saplings.add(crop.getSaplingBlock());
        }
        for (Content.Bark crop : Content.Bark.values()) {
            saplings.add(crop.getSaplingBlock());
        }
    }

    protected void generateBarkLogs() {
        FabricTagBuilder<Block> burnableLog = getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN);
        for (Content.Bark crop : Content.Bark.values()) {
            // add different log types to log tag of this crop
            getOrCreateTagBuilder(crop.getLogBlockTag())
                    .add(crop.getLog())
                    .add(crop.getStrippedLog())
                    .add(crop.getWood())
                    .add(crop.getStrippedWood());
            // make this crop log burnable
            burnableLog.addTag(crop.getLogBlockTag());
        }
    }

}
