package com.epherical.croptopia.util;

import net.minecraft.item.Food;
import net.minecraft.world.food.FoodProperties;

public class FoodConstructor() {
    public static final FoodConstructor REG_1 = new FoodConstructor(1, 0.2F);
    public static final FoodConstructor REG_2 = new FoodConstructor(2, 0.2F);
    public static final FoodConstructor REG_3 = new FoodConstructor(3, 0.6F);
    public static final FoodConstructor REG_4 = new FoodConstructor(4, 0.6F);
    public static final FoodConstructor REG_5 = new FoodConstructor(5, 1.2F);
    public static final FoodConstructor REG_6 = new FoodConstructor(6, 1.2F);
    public static final FoodConstructor REG_7 = new FoodConstructor(7, 1.2F);
    public static final FoodConstructor REG_8 = new FoodConstructor(8, 1.2F);
    public static final FoodConstructor REG_9 = new FoodConstructor(9, 1.5F);
    public static final FoodConstructor REG_10 = new FoodConstructor(10, 1.6F);
    public static final FoodConstructor REG_11 = new FoodConstructor(11, 1.7F);
    public static final FoodConstructor REG_12 = new FoodConstructor(12, 1.8F);
    public static final FoodConstructor REG_13 = new FoodConstructor(13, 1.9F);
    public static final FoodConstructor REG_14 = new FoodConstructor(14, 2.0F);
    public static final FoodConstructor REG_15 = new FoodConstructor(15, 2.1F);
    public static final FoodConstructor REG_16 = new FoodConstructor(16, 2.2F);
    public static final FoodConstructor REG_17 = new FoodConstructor(17, 2.3F);
    public static final FoodConstructor REG_18 = new FoodConstructor(18, 2.4F);
    public static final FoodConstructor REG_19 = new FoodConstructor(19, 2.5F);
    public static final FoodConstructor REG_20 = new FoodConstructor(20, 2.6F);


    private final int food;
    private final float satMod;

    public FoodConstructor(int food, float v) {

        this.food = food;
        this.satMod = v;
    }


    public static Food.Builder createBuilder(FoodConstructor reg) {
        return new Food.Builder().nutrition(reg.food).saturationMod(reg.satMod);
    }

    public static Food createFood(FoodConstructor reg) {
        return createBuilder(reg).build();
    }
}
