package com.epherical.croptopia.registry;

import net.minecraft.item.FoodComponent;

public class FoodRegistry {

    public static final FoodComponent.Builder EDIBLE_3_BUILDER = new FoodComponent.Builder().hunger(3).saturationModifier(0.4F);
    public static final FoodComponent.Builder EDIBLE_1_BUILDER = new FoodComponent.Builder().hunger(1).saturationModifier(0.4F);
    public static final FoodComponent.Builder EDIBLE_5_BUILDER = new FoodComponent.Builder().hunger(5).saturationModifier(0.5F);
    public static final FoodComponent.Builder EDIBLE_7_BUILDER = new FoodComponent.Builder().hunger(7).saturationModifier(0.6F);
    public static final FoodComponent.Builder EDIBLE_9_BUILDER = new FoodComponent.Builder().hunger(9).saturationModifier(0.6F);
    public static final FoodComponent.Builder EDIBLE_10_BUILDER = new FoodComponent.Builder().hunger(10).saturationModifier(0.8F);
    public static final FoodComponent.Builder EDIBLE_14_BUILDER = new FoodComponent.Builder().hunger(14).saturationModifier(1.0F);
    public static final FoodComponent.Builder EDIBLE_18_BUILDER = new FoodComponent.Builder().hunger(18).saturationModifier(1.4F);

    public static final FoodComponent EDIBLE_3 = EDIBLE_3_BUILDER.build();
    public static final FoodComponent EDIBLE_1 = EDIBLE_1_BUILDER.build();
    public static final FoodComponent EDIBLE_5 = EDIBLE_5_BUILDER.build();
    public static final FoodComponent EDIBLE_7 = EDIBLE_7_BUILDER.build();
    public static final FoodComponent EDIBLE_9 = EDIBLE_9_BUILDER.build();
    public static final FoodComponent EDIBLE_10 = EDIBLE_10_BUILDER.build();
    public static final FoodComponent EDIBLE_14 = EDIBLE_14_BUILDER.build();
    public static final FoodComponent EDIBLE_18 = EDIBLE_18_BUILDER.build();

}
