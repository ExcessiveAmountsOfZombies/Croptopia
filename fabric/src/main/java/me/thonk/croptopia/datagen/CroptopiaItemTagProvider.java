package me.thonk.croptopia.datagen;

import me.thonk.croptopia.registry.Content;
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

}
