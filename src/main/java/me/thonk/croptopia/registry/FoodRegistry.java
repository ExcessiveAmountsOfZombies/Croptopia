package me.thonk.croptopia.registry;

import net.minecraft.item.FoodComponent;

public class FoodRegistry {

    public static final FoodComponent EDIBLE_3 = new FoodComponent.Builder().hunger(3).saturationModifier(0.4F).build();
    public static final FoodComponent EDIBLE_1 = new FoodComponent.Builder().hunger(1).saturationModifier(0.4F).build();
    public static final FoodComponent EDIBLE_5 = new FoodComponent.Builder().hunger(5).saturationModifier(0.5F).build();
    public static final FoodComponent EDIBLE_7 = new FoodComponent.Builder().hunger(7).saturationModifier(0.6F).build();
    public static final FoodComponent EDIBLE_10 = new FoodComponent.Builder().hunger(10).saturationModifier(0.8F).build();
    public static final FoodComponent EDIBLE_14 = new FoodComponent.Builder().hunger(14).saturationModifier(1.0F).build();
    public static final FoodComponent EDIBLE_18 = new FoodComponent.Builder().hunger(18).saturationModifier(1.4F).build();

}
