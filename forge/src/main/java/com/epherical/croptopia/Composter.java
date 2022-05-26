package com.epherical.croptopia;

import com.epherical.croptopia.register.helpers.FarmlandCrop;
import com.epherical.croptopia.register.helpers.Tree;
import com.epherical.croptopia.register.helpers.TreeCrop;
import net.minecraft.world.level.ItemLike;

import static net.minecraft.world.level.block.ComposterBlock.COMPOSTABLES;

public class Composter {

    public static void init() {
        for (FarmlandCrop crop : FarmlandCrop.copy()) {
            registerCompostableItem(0.65F, crop.asItem());
            registerCompostableItem(0.3F, crop.getSeedItem());
        }
        for (TreeCrop crop : TreeCrop.copy()) {
            registerCompostableItem(0.65F, crop.asItem());
            registerCompostableItem(0.5F, crop.getSaplingItem());
        }
        for (Tree crop : Tree.copy()) {
            registerCompostableItem(0.65F, crop.asItem());
            registerCompostableItem(0.5F, crop.getSapling());
        }
    }

    public static void registerCompostableItem(float levelIncreaseChance, ItemLike item) {
        COMPOSTABLES.put(item.asItem(), levelIncreaseChance);
    }
}
