package me.thonk.croptopia.registry;

import net.minecraft.world.food.FoodProperties;

public record FoodRegistry(int hunger, float satMod) {
    public static final FoodRegistry REG_1 = new FoodRegistry(1, 0.2F);
    public static final FoodRegistry REG_2 = new FoodRegistry(2, 0.2F);
    public static final FoodRegistry REG_3 = new FoodRegistry(3, 0.6F);
    public static final FoodRegistry REG_4 = new FoodRegistry(4, 0.6F);
    public static final FoodRegistry REG_5 = new FoodRegistry(5, 1.2F);
    public static final FoodRegistry REG_6 = new FoodRegistry(6, 1.2F);
    public static final FoodRegistry REG_7 = new FoodRegistry(7, 1.2F);
    public static final FoodRegistry REG_8 = new FoodRegistry(8, 1.2F);
    public static final FoodRegistry REG_9 = new FoodRegistry(9, 1.5F);
    public static final FoodRegistry REG_10 = new FoodRegistry(10, 1.6F);
    public static final FoodRegistry REG_11 = new FoodRegistry(11, 1.7F);
    public static final FoodRegistry REG_12 = new FoodRegistry(12, 1.8F);
    public static final FoodRegistry REG_13 = new FoodRegistry(13, 1.9F);
    public static final FoodRegistry REG_14 = new FoodRegistry(14, 2.0F);
    public static final FoodRegistry REG_15 = new FoodRegistry(15, 2.1F);
    public static final FoodRegistry REG_16 = new FoodRegistry(16, 2.2F);
    public static final FoodRegistry REG_17 = new FoodRegistry(17, 2.3F);
    public static final FoodRegistry REG_18 = new FoodRegistry(18, 2.4F);
    public static final FoodRegistry REG_19 = new FoodRegistry(19, 2.5F);
    public static final FoodRegistry REG_20 = new FoodRegistry(20, 2.6F);


    public static FoodProperties.Builder createBuilder(FoodRegistry reg) {
        return new FoodProperties.Builder().nutrition(reg.hunger).saturationMod(reg.satMod);
    }

    public static FoodProperties createComponent(FoodRegistry reg) {
        return createBuilder(reg).build();
    }
}
