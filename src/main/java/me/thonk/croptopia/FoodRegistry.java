package me.thonk.croptopia;

import net.minecraft.item.FoodComponent;

public class FoodRegistry {

    public static final FoodComponent EDIBLE_OK = new FoodComponent.Builder().hunger(3).saturationModifier(0.6F).build();
    public static final FoodComponent EDIBLE_YUCK = new FoodComponent.Builder().hunger(1).saturationModifier(0.6F).build();

}
