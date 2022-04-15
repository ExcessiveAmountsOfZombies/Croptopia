package me.thonk.croptopia.registry;

import me.thonk.croptopia.Croptopia;
import net.minecraft.item.ItemConvertible;

import static net.minecraft.block.ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE;

public class Composter {

    public static void init() {
        Croptopia.cropItems.forEach(cropItem -> {
            registerCompostableItem(0.65F, cropItem);
        });
        Croptopia.getSeeds().forEach(configurableSeed -> {
            registerCompostableItem(0.3F, configurableSeed.getSeedItem());
        });
        for (Content.Tree crop : Content.Tree.values()) {
            registerCompostableItem(0.5F, crop.getSapling());
        }
    }

    public static void registerCompostableItem(float levelIncreaseChance, ItemConvertible item) {
        ITEM_TO_LEVEL_INCREASE_CHANCE.put(item.asItem(), levelIncreaseChance);
    }
}
