package me.thonk.croptopia.registry;


import net.minecraft.world.food.FoodProperties;

public class FoodRegistry {

    public static final FoodProperties.Builder EDIBLE_3_BUILDER = new FoodProperties.Builder().nutrition(3).saturationMod(0.4F);
    public static final FoodProperties.Builder EDIBLE_1_BUILDER = new FoodProperties.Builder().nutrition(1).saturationMod(0.4F);
    public static final FoodProperties.Builder EDIBLE_5_BUILDER = new FoodProperties.Builder().nutrition(5).saturationMod(0.5F);
    public static final FoodProperties.Builder EDIBLE_7_BUILDER = new FoodProperties.Builder().nutrition(7).saturationMod(0.6F);
    public static final FoodProperties.Builder EDIBLE_9_BUILDER = new FoodProperties.Builder().nutrition(9).saturationMod(0.6F);
    public static final FoodProperties.Builder EDIBLE_10_BUILDER = new FoodProperties.Builder().nutrition(10).saturationMod(0.8F);
    public static final FoodProperties.Builder EDIBLE_14_BUILDER = new FoodProperties.Builder().nutrition(14).saturationMod(1.0F);
    public static final FoodProperties.Builder EDIBLE_18_BUILDER = new FoodProperties.Builder().nutrition(18).saturationMod(1.4F);

    public static final FoodProperties EDIBLE_3 = EDIBLE_3_BUILDER.build();
    public static final FoodProperties EDIBLE_1 = EDIBLE_1_BUILDER.build();
    public static final FoodProperties EDIBLE_5 = EDIBLE_5_BUILDER.build();
    public static final FoodProperties EDIBLE_7 = EDIBLE_7_BUILDER.build();
    public static final FoodProperties EDIBLE_9 = EDIBLE_9_BUILDER.build();
    public static final FoodProperties EDIBLE_10 = EDIBLE_10_BUILDER.build();
    public static final FoodProperties EDIBLE_14 = EDIBLE_14_BUILDER.build();
    public static final FoodProperties EDIBLE_18 = EDIBLE_18_BUILDER.build();

}
