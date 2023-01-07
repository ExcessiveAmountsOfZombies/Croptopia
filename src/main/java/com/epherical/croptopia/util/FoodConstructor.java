package com.epherical.croptopia.util;

import net.minecraft.world.food.FoodProperties;

public record FoodConstructor(int hunger, float satMod) {
    public static final FoodConstructor RAW_CROP_1 = new FoodConstructor(1, 0.1F);
    public static final FoodConstructor RAW_CROP_2 = new FoodConstructor(1, 0.1F);

    public static final FoodConstructor RAW_MEAT_1 = new FoodConstructor(1, 0.2F);

    public static final FoodConstructor JUICE_5 = new FoodConstructor(5, 0.6F);
    public static final FoodConstructor JAM_3 = new FoodConstructor(3, 0.5F);

    public static final FoodConstructor FURNACE_3 = new FoodConstructor(3, 0.5F);
    public static final FoodConstructor FURNACE_4 = new FoodConstructor(4, 0.5F);
    public static final FoodConstructor FURNACE_5 = new FoodConstructor(5, 0.8F);
    public static final FoodConstructor FURNACE_7 = new FoodConstructor(7, 0.8F);

    public static final FoodConstructor ICE_CREAM_7 = new FoodConstructor(7, 1.0F);
    public static final FoodConstructor PIE_10 = new FoodConstructor(10, 1.2F);


    public static final FoodConstructor REG_1 = new FoodConstructor(1, 0.2F);
    public static final FoodConstructor REG_2 = new FoodConstructor(2, 0.2F);
    public static final FoodConstructor REG_3 = new FoodConstructor(3, 0.6F);
    public static final FoodConstructor REG_4 = new FoodConstructor(4, 0.6F);
    public static final FoodConstructor REG_5 = new FoodConstructor(5, 1.2F);
    public static final FoodConstructor REG_6 = new FoodConstructor(6, 1.2F);
    public static final FoodConstructor REG_7 = new FoodConstructor(7, 1.2F);
    public static final FoodConstructor REG_8 = new FoodConstructor(8, 1.2F);
    public static final FoodConstructor REG_9 = new FoodConstructor(9, 1.5F);
    public static final FoodConstructor REG_10 = new FoodConstructor(10, 1.2F);
    public static final FoodConstructor REG_11 = new FoodConstructor(11, 1.4F);
    public static final FoodConstructor REG_12 = new FoodConstructor(12, 1.3F);
    public static final FoodConstructor REG_13 = new FoodConstructor(13, 1.5F);
    public static final FoodConstructor REG_14 = new FoodConstructor(14, 1.4F);
    public static final FoodConstructor REG_15 = new FoodConstructor(15, 1.3F);
    public static final FoodConstructor REG_16 = new FoodConstructor(16, 1.2F);
    public static final FoodConstructor REG_17 = new FoodConstructor(17, 1.1F);
    public static final FoodConstructor REG_18 = new FoodConstructor(18, 1.1F);
    public static final FoodConstructor REG_19 = new FoodConstructor(19, 1.1F);
    public static final FoodConstructor REG_20 = new FoodConstructor(20, 1.0F);


    public static FoodProperties.Builder createBuilder(FoodConstructor reg) {
        return new FoodProperties.Builder().nutrition(reg.hunger).saturationMod(reg.satMod);
    }

    public static FoodProperties createFood(FoodConstructor reg) {
        return createBuilder(reg).build();
    }
}
