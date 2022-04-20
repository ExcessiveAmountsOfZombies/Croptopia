package me.thonk.croptopia.registry;

import me.thonk.croptopia.Croptopia;
import net.minecraft.world.level.ItemLike;

import static net.minecraft.world.level.block.ComposterBlock.COMPOSTABLES;

public class Composter {

    public static void init() {
        for (Content.Farmland crop : Content.Farmland.values()) {
            registerCompostableItem(0.65F, crop.asItem());
            registerCompostableItem(0.3F, crop.getSeed());
        }
        for (Content.Tree crop : Content.Tree.values()) {
            registerCompostableItem(0.65F, crop.asItem());
            registerCompostableItem(0.5F, crop.getSapling());
        }
        for (Content.Bark crop : Content.Bark.values()) {
            registerCompostableItem(0.65F, crop.asItem());
            registerCompostableItem(0.5F, crop.getSapling());
        }
    }

    public static void registerCompostableItem(float levelIncreaseChance, ItemLike item) {
        COMPOSTABLES.put(item.asItem(), levelIncreaseChance);
    }
}
