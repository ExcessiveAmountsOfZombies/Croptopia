package me.thonk.croptopia.registry;


public class FoodRegistry {

    public static final Food.Builder EDIBLE_3_BUILDER = new Food.Builder().hunger(3).saturation(0.4F);
    public static final Food.Builder EDIBLE_1_BUILDER = new Food.Builder().hunger(1).saturation(0.4F);
    public static final Food.Builder EDIBLE_5_BUILDER = new Food.Builder().hunger(5).saturation(0.5F);
    public static final Food.Builder EDIBLE_7_BUILDER = new Food.Builder().hunger(7).saturation(0.6F);
    public static final Food.Builder EDIBLE_9_BUILDER = new Food.Builder().hunger(9).saturation(0.6F);
    public static final Food.Builder EDIBLE_10_BUILDER = new Food.Builder().hunger(10).saturation(0.8F);
    public static final Food.Builder EDIBLE_14_BUILDER = new Food.Builder().hunger(14).saturation(1.0F);
    public static final Food.Builder EDIBLE_18_BUILDER = new Food.Builder().hunger(18).saturation(1.4F);

    public static final Food EDIBLE_3 = EDIBLE_3_BUILDER.build();
    public static final Food EDIBLE_1 = EDIBLE_1_BUILDER.build();
    public static final Food EDIBLE_5 = EDIBLE_5_BUILDER.build();
    public static final Food EDIBLE_7 = EDIBLE_7_BUILDER.build();
    public static final Food EDIBLE_9 = EDIBLE_9_BUILDER.build();
    public static final Food EDIBLE_10 = EDIBLE_10_BUILDER.build();
    public static final Food EDIBLE_14 = EDIBLE_14_BUILDER.build();
    public static final Food EDIBLE_18 = EDIBLE_18_BUILDER.build();

}
