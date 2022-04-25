package com.epherical.croptopia.register;

import com.epherical.croptopia.common.BlockNames;
import com.epherical.croptopia.common.ItemNamesV2;
import com.epherical.croptopia.items.Drink;
import com.epherical.croptopia.items.Soup;
import com.epherical.croptopia.register.helpers.FarmlandCrop;
import com.epherical.croptopia.register.helpers.Furnace;
import com.epherical.croptopia.register.helpers.IceCream;
import com.epherical.croptopia.register.helpers.Jam;
import com.epherical.croptopia.register.helpers.Juice;
import com.epherical.croptopia.register.helpers.Pie;
import com.epherical.croptopia.register.helpers.Seafood;
import com.epherical.croptopia.register.helpers.Smoothie;
import com.epherical.croptopia.register.helpers.Tree;
import com.epherical.croptopia.register.helpers.TreeCrop;
import com.epherical.croptopia.register.helpers.Utensil;
import com.epherical.croptopia.util.FoodConstructor;
import com.epherical.croptopia.util.RegisterFunction;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.material.Material;

import java.util.Arrays;
import java.util.stream.Stream;

import static com.epherical.croptopia.CroptopiaMod.createGroup;
import static com.epherical.croptopia.CroptopiaMod.createIdentifier;
import static com.epherical.croptopia.util.FoodConstructor.*;

public class Content {

    // TODO: abstract these classes a bit further.
    public static final FarmlandCrop ARTICHOKE = new FarmlandCrop(ItemNamesV2.ARTICHOKE, true, TagCategory.VEGETABLES, REG_1, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop ASPARAGUS = new FarmlandCrop(ItemNamesV2.ASPARAGUS, false, TagCategory.VEGETABLES, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop BARLEY = new FarmlandCrop(ItemNamesV2.BARLEY, false, TagCategory.GRAIN, REG_1, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop BASIL = new FarmlandCrop(ItemNamesV2.BASIL, false, TagCategory.CROPS, REG_1, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop BELLPEPPER = new FarmlandCrop(ItemNamesV2.BELLPEPPER, true, TagCategory.FRUITS, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop BLACKBEAN = new FarmlandCrop(ItemNamesV2.BLACKBEAN, true, TagCategory.CROPS, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop BLACKBERRY = new FarmlandCrop(ItemNamesV2.BLACKBERRY, true, TagCategory.FRUITS, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop BLUEBERRY = new FarmlandCrop(ItemNamesV2.BLUEBERRY, true, TagCategory.FRUITS, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop BROCCOLI = new FarmlandCrop(ItemNamesV2.BROCCOLI, false, TagCategory.VEGETABLES, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop CABBAGE = new FarmlandCrop(ItemNamesV2.CABBAGE, false, TagCategory.VEGETABLES, REG_1, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop CANTALOUPE = new FarmlandCrop(ItemNamesV2.CANTALOUPE, true, TagCategory.FRUITS, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop CAULIFLOWER = new FarmlandCrop(ItemNamesV2.CAULIFLOWER, false, TagCategory.VEGETABLES, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop CELERY = new FarmlandCrop(ItemNamesV2.CELERY, false, TagCategory.VEGETABLES, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop CHILE_PEPPER = new FarmlandCrop(ItemNamesV2.CHILE_PEPPER, true, TagCategory.CROPS, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop COFFEE_BEANS = new FarmlandCrop("coffee", ItemNamesV2.COFFEE_BEANS, false, TagCategory.CROPS, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop CORN = new FarmlandCrop(ItemNamesV2.CORN, false, TagCategory.GRAIN, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop CRANBERRY = new FarmlandCrop(ItemNamesV2.CRANBERRY, true, TagCategory.FRUITS, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop CUCUMBER = new FarmlandCrop(ItemNamesV2.CUCUMBER, true, TagCategory.VEGETABLES, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop CURRANT = new FarmlandCrop(ItemNamesV2.CURRANT, true, TagCategory.FRUITS, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop EGGPLANT = new FarmlandCrop(ItemNamesV2.EGGPLANT, true, TagCategory.VEGETABLES, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop ELDERBERRY = new FarmlandCrop(ItemNamesV2.ELDERBERRY, true, TagCategory.FRUITS, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop GARLIC = new FarmlandCrop(ItemNamesV2.GARLIC, false, TagCategory.VEGETABLES, REG_1, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop GINGER = new FarmlandCrop(ItemNamesV2.GINGER, true, TagCategory.VEGETABLES, null, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop GRAPE = new FarmlandCrop(ItemNamesV2.GRAPE, true, TagCategory.FRUITS, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop GREENBEAN = new FarmlandCrop(ItemNamesV2.GREENBEAN, true, TagCategory.VEGETABLES, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop GREENONION = new FarmlandCrop(ItemNamesV2.GREENONION, true, TagCategory.VEGETABLES, REG_1, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop HONEYDEW = new FarmlandCrop(ItemNamesV2.HONEYDEW, false, TagCategory.FRUITS, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop HOPS = new FarmlandCrop(ItemNamesV2.HOPS, false, TagCategory.CROPS, null, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop KALE = new FarmlandCrop(ItemNamesV2.KALE, false, TagCategory.VEGETABLES, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop KIWI = new FarmlandCrop(ItemNamesV2.KIWI, true, TagCategory.FRUITS, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop LEEK = new FarmlandCrop(ItemNamesV2.LEEK, false, TagCategory.VEGETABLES, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop LETTUCE = new FarmlandCrop(ItemNamesV2.LETTUCE, false, TagCategory.VEGETABLES, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop MUSTARD = new FarmlandCrop(ItemNamesV2.MUSTARD, false, TagCategory.VEGETABLES, null, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop OAT = new FarmlandCrop(ItemNamesV2.OAT, false, TagCategory.GRAIN, REG_1, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop OLIVE = new FarmlandCrop(ItemNamesV2.OLIVE, true, TagCategory.FRUITS, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop ONION = new FarmlandCrop(ItemNamesV2.ONION, true, TagCategory.VEGETABLES, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop PEANUT = new FarmlandCrop(ItemNamesV2.PEANUT, true, TagCategory.CROPS, REG_1, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop PEPPER = new FarmlandCrop(ItemNamesV2.PEPPER, false, TagCategory.CROPS, null, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop PINEAPPLE = new FarmlandCrop(ItemNamesV2.PINEAPPLE, true, TagCategory.FRUITS, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop RADISH = new FarmlandCrop(ItemNamesV2.RADISH, true, TagCategory.VEGETABLES, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop RASPBERRY = new FarmlandCrop(ItemNamesV2.RASPBERRY, true, TagCategory.FRUITS, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop RHUBARB = new FarmlandCrop(ItemNamesV2.RHUBARB, false, TagCategory.VEGETABLES, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop RICE = new FarmlandCrop(ItemNamesV2.RICE, false, TagCategory.GRAIN, REG_1, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop RUTABAGA = new FarmlandCrop(ItemNamesV2.RUTABAGA, true, TagCategory.VEGETABLES, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop SAGUARO = new FarmlandCrop(ItemNamesV2.SAGUARO, true, TagCategory.FRUITS, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop SOYBEAN = new FarmlandCrop(ItemNamesV2.SOYBEAN, true, TagCategory.VEGETABLES, REG_1, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop SPINACH = new FarmlandCrop(ItemNamesV2.SPINACH, false, TagCategory.VEGETABLES, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop SQUASH = new FarmlandCrop(ItemNamesV2.SQUASH, true, TagCategory.VEGETABLES, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop STRAWBERRY = new FarmlandCrop(ItemNamesV2.STRAWBERRY, true, TagCategory.FRUITS, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop SWEETPOTATO = new FarmlandCrop(ItemNamesV2.SWEETPOTATO, true, TagCategory.VEGETABLES, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop TEA_LEAVES = new FarmlandCrop("tea", ItemNamesV2.TEA_LEAVES, false, TagCategory.CROPS, null, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop TOMATILLO = new FarmlandCrop(ItemNamesV2.TOMATILLO, true, TagCategory.VEGETABLES, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop TOMATO = new FarmlandCrop(ItemNamesV2.TOMATO, true, TagCategory.VEGETABLES, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop TURMERIC = new FarmlandCrop(ItemNamesV2.TURMERIC, false, TagCategory.CROPS, null, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop TURNIP = new FarmlandCrop(ItemNamesV2.TURNIP, true, TagCategory.VEGETABLES, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop VANILLA = new FarmlandCrop(ItemNamesV2.VANILLA, false, TagCategory.CROPS, null, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop YAM = new FarmlandCrop(ItemNamesV2.YAM, true, TagCategory.VEGETABLES, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop ZUCCHINI = new FarmlandCrop(ItemNamesV2.ZUCCHINI, false, TagCategory.VEGETABLES, REG_3, BiomeTags.IS_MOUNTAIN);

    public static final TreeCrop ALMOND = new TreeCrop(ItemNamesV2.ALMOND, true, Blocks.DARK_OAK_LOG, Blocks.DARK_OAK_LEAVES, TagCategory.NUTS, REG_3, 4, 3, 0);
    public static final TreeCrop APPLE = new TreeCrop(ItemNamesV2.APPLE, true, Blocks.OAK_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, null, 5, 3, 0);
    public static final TreeCrop APRICOT = new TreeCrop(ItemNamesV2.APRICOT, true, Blocks.OAK_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, REG_3, 5, 2, 0);
    public static final TreeCrop AVOCADO = new TreeCrop(ItemNamesV2.AVOCADO, true, Blocks.SPRUCE_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, REG_3, 5, 3, 0);
    public static final TreeCrop BANANA = new TreeCrop(ItemNamesV2.BANANA, true, Blocks.JUNGLE_LOG, Blocks.JUNGLE_LEAVES, TagCategory.FRUITS, REG_3, 4, 8, 0);
    public static final TreeCrop CASHEW = new TreeCrop(ItemNamesV2.CASHEW, true, Blocks.DARK_OAK_LOG, Blocks.DARK_OAK_LEAVES, TagCategory.CROPS, REG_1, 4, 3, 0);
    public static final TreeCrop CHERRY = new TreeCrop(ItemNamesV2.CHERRY, true, Blocks.OAK_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, REG_3, 5, 3, 0);
    public static final TreeCrop COCONUT = new TreeCrop(ItemNamesV2.COCONUT, true, Blocks.JUNGLE_LOG, Blocks.JUNGLE_LEAVES, TagCategory.FRUITS, REG_1, 5, 2, 3);
    public static final TreeCrop DATE = new TreeCrop(ItemNamesV2.DATE, true, Blocks.JUNGLE_LOG, Blocks.JUNGLE_LEAVES, TagCategory.FRUITS, REG_3, 5, 8, 0);
    public static final TreeCrop DRAGONFRUIT = new TreeCrop(ItemNamesV2.DRAGONFRUIT, true, Blocks.JUNGLE_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, REG_3, 5, 7, 0);
    public static final TreeCrop FIG = new TreeCrop(ItemNamesV2.FIG, true, Blocks.JUNGLE_LOG, Blocks.JUNGLE_LEAVES, TagCategory.FRUITS, REG_3, 4, 8, 0);
    public static final TreeCrop GRAPEFRUIT = new TreeCrop(ItemNamesV2.GRAPEFRUIT, true, Blocks.JUNGLE_LOG, Blocks.JUNGLE_LEAVES, TagCategory.FRUITS, REG_3, 4, 8, 0);
    public static final TreeCrop KUMQUAT = new TreeCrop(ItemNamesV2.KUMQUAT, true, Blocks.JUNGLE_LOG, Blocks.JUNGLE_LEAVES, TagCategory.FRUITS, REG_3, 4, 8, 0);
    public static final TreeCrop LEMON = new TreeCrop(ItemNamesV2.LEMON, true, Blocks.OAK_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, REG_3, 5, 3, 0);
    public static final TreeCrop LIME = new TreeCrop(ItemNamesV2.LIME, true, Blocks.OAK_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, REG_3, 5, 2, 0);
    public static final TreeCrop MANGO = new TreeCrop(ItemNamesV2.MANGO, true, Blocks.JUNGLE_LOG, Blocks.JUNGLE_LEAVES, TagCategory.FRUITS, REG_3, 5, 8, 0);
    public static final TreeCrop NECTARINE = new TreeCrop(ItemNamesV2.NECTARINE, true, Blocks.OAK_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, REG_3, 4, 4, 0);
    public static final TreeCrop NUTMEG = new TreeCrop(ItemNamesV2.NUTMEG, true, Blocks.JUNGLE_LOG, Blocks.JUNGLE_LEAVES, TagCategory.CROPS, REG_1, 4, 8, 0);
    public static final TreeCrop ORANGE = new TreeCrop(ItemNamesV2.ORANGE, true, Blocks.OAK_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, REG_3, 4, 4, 0);
    public static final TreeCrop PEACH = new TreeCrop(ItemNamesV2.PEACH, true, Blocks.OAK_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, REG_3, 5, 3, 0);
    public static final TreeCrop PEAR = new TreeCrop(ItemNamesV2.PEAR, true, Blocks.OAK_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, REG_3, 5, 2, 0);
    public static final TreeCrop PECAN = new TreeCrop(ItemNamesV2.PECAN, true, Blocks.DARK_OAK_LOG, Blocks.DARK_OAK_LEAVES, TagCategory.NUTS, REG_3, 4, 3, 0);
    public static final TreeCrop PERSIMMON = new TreeCrop(ItemNamesV2.PERSIMMON, true, Blocks.OAK_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, REG_3, 5, 3, 0);
    public static final TreeCrop PLUM = new TreeCrop(ItemNamesV2.PLUM, true, Blocks.OAK_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, REG_3, 5, 3, 0);
    public static final TreeCrop STARFRUIT = new TreeCrop(ItemNamesV2.STARFRUIT, true, Blocks.OAK_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, REG_3, 5, 3, 0);
    public static final TreeCrop WALNUT = new TreeCrop(ItemNamesV2.WALNUT, true, Blocks.DARK_OAK_LOG, Blocks.DARK_OAK_LEAVES, TagCategory.NUTS, REG_3, 4, 3, 0);

    public static final Tree CINNAMON = new Tree(ItemNamesV2.CINNAMON, false, TagCategory.CROPS, 4, 3, 0);

    public static final Seafood ANCHOVY = new Seafood(ItemNamesV2.ANCHOVY, true, REG_1);
    public static final Seafood CALAMARI = new Seafood(ItemNamesV2.CALAMARI, false, REG_1);
    public static final Seafood CLAM = new Seafood(ItemNamesV2.CLAM, true, REG_3);
    public static final Seafood CRAB = new Seafood(ItemNamesV2.CRAB, true, REG_1);
    public static final Seafood GLOWING_CALAMARI = new Seafood(ItemNamesV2.GLOWING_CALAMARI, false, REG_3);
    public static final Seafood OYSTER = new Seafood(ItemNamesV2.OYSTER, true, REG_3);
    public static final Seafood ROE = new Seafood(ItemNamesV2.ROE, false, REG_1);
    public static final Seafood SHRIMP = new Seafood(ItemNamesV2.SHRIMP, false, REG_1);
    public static final Seafood TUNA = new Seafood(ItemNamesV2.TUNA, false, REG_3);

    public static final Furnace BAKED_BEANS = new Furnace(ItemNamesV2.BAKED_BEANS, false, REG_5);
    public static final Furnace BAKED_SWEET_POTATO = new Furnace(ItemNamesV2.BAKED_SWEET_POTATO, true, REG_7);
    public static final Furnace BAKED_YAM = new Furnace(ItemNamesV2.BAKED_YAM, true, REG_7);
    public static final Furnace CARAMEL = new Furnace(ItemNamesV2.CARAMEL, false, null);
    public static final Furnace COOKED_ANCHOVY = new Furnace(ItemNamesV2.COOKED_ANCHOVY, true, REG_4);
    public static final Furnace COOKED_BACON = new Furnace(ItemNamesV2.COOKED_BACON, false, REG_7);
    public static final Furnace COOKED_CALAMARI = new Furnace(ItemNamesV2.COOKED_CALAMARI, false, REG_5);
    public static final Furnace COOKED_SHRIMP = new Furnace(ItemNamesV2.COOKED_SHRIMP, false, REG_5);
    public static final Furnace COOKED_TUNA = new Furnace(ItemNamesV2.COOKED_TUNA, false, REG_6);
    public static final Furnace MOLASSES = new Furnace(ItemNamesV2.MOLASSES, false, null);
    public static final Furnace POPCORN = new Furnace(ItemNamesV2.POPCORN, false, REG_3);
    public static final Furnace RAISINS = new Furnace(ItemNamesV2.RAISINS, false, REG_5);
    public static final Furnace TOAST = new Furnace(ItemNamesV2.TOAST, true, REG_7);

    public static final Juice APPLE_JUICE = new Juice(ItemNamesV2.APPLE_JUICE, APPLE);
    public static final Juice CRANBERRY_JUICE = new Juice(ItemNamesV2.CRANBERRY_JUICE, CRANBERRY);
    public static final Juice GRAPE_JUICE = new Juice(ItemNamesV2.GRAPE_JUICE, GRAPE);
    public static final Juice MELON_JUICE = new Juice(ItemNamesV2.MELON_JUICE, );
    public static final Juice ORANGE_JUICE = new Juice(ItemNamesV2.ORANGE_JUICE, ORANGE);
    public static final Juice PINEAPPLE_JUICE = new Juice(ItemNamesV2.PINEAPPLE_JUICE, PINEAPPLE);
    public static final Juice SAGUARO_JUICE = new Juice(ItemNamesV2.SAGUARO_JUICE, SAGUARO);
    public static final Juice TOMATO_JUICE = new Juice(ItemNamesV2.TOMATO_JUICE, TOMATO, false);

    public static final Jam APRICOT_JAM = new Jam(ItemNamesV2.APRICOT_JAM, APRICOT);
    public static final Jam BLACKBERRY_JAM = new Jam(ItemNamesV2.BLACKBERRY_JAM, BLACKBERRY);
    public static final Jam BLUEBERRY_JAM = new Jam(ItemNamesV2.BLUEBERRY_JAM, BLUEBERRY);
    public static final Jam CHERRY_JAM = new Jam(ItemNamesV2.CHERRY_JAM, CHERRY);
    public static final Jam ELDERBERRY_JAM = new Jam(ItemNamesV2.ELDERBERRY_JAM, ELDERBERRY);
    public static final Jam GRAPE_JAM = new Jam(ItemNamesV2.GRAPE_JAM, GRAPE);
    public static final Jam PEACH_JAM = new Jam(ItemNamesV2.PEACH_JAM, PEACH);
    public static final Jam RASPBERRY_JAM = new Jam(ItemNamesV2.RASPBERRY_JAM, RASPBERRY);
    public static final Jam STRAWBERRY_JAM = new Jam(ItemNamesV2.STRAWBERRY_JAM, STRAWBERRY);

    public static final Smoothie BANANA_SMOOTHIE = new Smoothie(ItemNamesV2.BANANA_SMOOTHIE, BANANA);
    public static final Smoothie STRAWBERRY_SMOOTHIE = new Smoothie(ItemNamesV2.STRAWBERRY_SMOOTHIE, STRAWBERRY);

    public static final IceCream MANGO_ICE_CREAM = new IceCream(ItemNamesV2.MANGO_ICE_CREAM, MANGO);
    public static final IceCream PECAN_ICE_CREAM = new IceCream(ItemNamesV2.PECAN_ICE_CREAM, PECAN);
    public static final IceCream STRAWBERRY_ICE_CREAM = new IceCream(ItemNamesV2.STRAWBERRY_ICE_CREAM, STRAWBERRY);
    public static final IceCream VANILLA_ICE_CREAM = new IceCream(ItemNamesV2.VANILLA_ICE_CREAM, VANILLA);

    public static final Pie APPLE_PIE = new Pie(ItemNamesV2.APPLE_PIE, APPLE);
    public static final Pie CHERRY_PIE = new Pie(ItemNamesV2.CHERRY_PIE, CHERRY);
    public static final Pie PECAN_PIE = new Pie(ItemNamesV2.PECAN_PIE, PECAN);
    public static final Pie RHUBARB_PIE = new Pie(ItemNamesV2.RHUBARB_PIE, RHUBARB);

    public static final Utensil COOKING_POT = new Utensil(ItemNamesV2.COOKING_POT, true);
    public static final Utensil FOOD_PRESS = new Utensil(ItemNamesV2.FOOD_PRESS, false);
    public static final Utensil FRYING_PAN = new Utensil(ItemNamesV2.FRYING_PAN, true);
    public static final Utensil KNIFE = new Utensil(ItemNamesV2.KNIFE, true);
    public static final Utensil MORTAR_AND_PESTLE = new Utensil(ItemNamesV2.MORTAR_AND_PESTLE, true);

    // Spices
    public static final Item PAPRIKA = new Item(createGroup()); // TODO need recipe to make paprika in future update
    public static final Item SALT = new Item(createGroup());

    // secondary ingredients?
    public static final Item OLIVE_OIL = new Item(createGroup());
    public static final Item CHEESE = new Item(createGroup().food(FoodConstructor.createFood(REG_5)));
    public static final Item FLOUR = new Item(createGroup());
    public static final Item BUTTER = new Item(createGroup().food(FoodConstructor.createFood(REG_3)));
    public static final Item NOODLE = new Item(createGroup());
    public static final Item TOFU = new Item(createGroup().food(FoodConstructor.createFood(REG_5)));
    public static final Item CHOCOLATE = new Item(createGroup().food(FoodConstructor.createFood(REG_5)));
    public static final Item TORTILLA = new Item(createGroup().food(FoodConstructor.createFood(REG_5)));
    public static final Item SOY_SAUCE = new Item(createGroup());
    public static final Item DOUGH = new Item(createGroup());
    public static final Item RAVIOLI = new Item(createGroup());
    public static final Item SALSA = new Item(createGroup().food(FoodConstructor.createFood(REG_5)));
    public static final Item ARTICHOKE_DIP = new Item(createGroup().food(FoodConstructor.createFood(REG_5)));
    public static final Item PEPPERONI = new Item(createGroup().food(FoodConstructor.createFood(REG_5)));

    // drinks
    public static final Item COFFEE = new Drink(createGroup().food(FoodConstructor.createBuilder(REG_5).alwaysEat().build()).craftRemainder(Items.GLASS_BOTTLE));
    public static final Item LEMONADE = new Drink(createGroup().food(FoodConstructor.createBuilder(REG_5).alwaysEat().build()).craftRemainder(Items.GLASS_BOTTLE));
    public static final Item LIMEADE = new Drink(createGroup().food(FoodConstructor.createBuilder(REG_5).alwaysEat().build()).craftRemainder(Items.GLASS_BOTTLE));
    public static final Item SOY_MILK = new Drink(createGroup().food(FoodConstructor.createBuilder(REG_5).alwaysEat().build()).craftRemainder(Items.GLASS_BOTTLE));

    public static final Item KALE_SMOOTHIE = new Drink(createGroup().food(FoodConstructor.createBuilder(REG_14).alwaysEat().build()));
    public static final Item FRUIT_SMOOTHIE = new Drink(createGroup().food(FoodConstructor.createBuilder(REG_7).alwaysEat().build()));

    public static final Item CHOCOLATE_MILKSHAKE = new Drink(createGroup().food(FoodConstructor.createBuilder(REG_7).alwaysEat().build()));

    public static final Item BEER = new Drink(createGroup().food(FoodConstructor.createBuilder(REG_7).alwaysEat().build()));
    public static final Item WINE = new Drink(createGroup().food(FoodConstructor.createBuilder(REG_7).alwaysEat().build()));
    public static final Item MEAD = new Drink(createGroup().food(FoodConstructor.createBuilder(REG_7).alwaysEat().build()));
    public static final Item RUM = new Drink(createGroup().food(FoodConstructor.createBuilder(REG_7).alwaysEat().build()).craftRemainder(Items.GLASS_BOTTLE));
    public static final Item PUMPKIN_SPICE_LATTE = new Drink(createGroup().food(FoodConstructor.createBuilder(REG_14).alwaysEat().build()));

    // snacks?
    public static final Item BEEF_JERKY = new Item(createGroup().food(FoodConstructor.createFood(REG_5)));
    public static final Item PORK_JERKY = new Item(createGroup().food(FoodConstructor.createFood(REG_5)));
    public static final Item KALE_CHIPS = new Item(createGroup().food(FoodConstructor.createFood(REG_5)));
    public static final Item POTATO_CHIPS = new Item(createGroup().food(FoodConstructor.createFood(REG_5)));
    public static final Item STEAMED_RICE = new Item(createGroup().food(FoodConstructor.createFood(REG_5)));
    public static final Item FRENCH_FRIES = new Item(createGroup().food(FoodConstructor.createFood(REG_5)));
    public static final Item SWEET_POTATO_FRIES = new Item(createGroup().food(FoodConstructor.createFood(REG_5)));
    public static final Item ONION_RINGS = new Item(createGroup().food(FoodConstructor.createFood(REG_5)));
    public static final Item DOUGHNUT = new Item(createGroup().food(FoodConstructor.createFood(REG_5)));
    public static final Item CUCUMBER_SALAD = new Soup(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item CAESAR_SALAD = new Soup(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item LEAFY_SALAD = new Soup(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item FRUIT_SALAD = new Soup(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item VEGGIE_SALAD = new Soup(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item PORK_AND_BEANS = new Soup(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item OATMEAL = new Soup(createGroup().food(FoodConstructor.createFood(REG_7)));
    public static final Item LEEK_SOUP = new Soup(createGroup().food(FoodConstructor.createFood(REG_7)));
    public static final Item YOGHURT = new Soup(createGroup().food(FoodConstructor.createFood(REG_5)));
    public static final Item SAUCY_CHIPS = new Soup(createGroup().food(FoodConstructor.createFood(REG_7)));
    public static final Item ROASTED_NUTS = new Item(createGroup().food(FoodConstructor.createFood(REG_5)));
    public static final Item TRAIL_MIX = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item PROTEIN_BAR = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item NOUGAT = new Item(createGroup().food(FoodConstructor.createFood(REG_7)));

    // breakfast
    public static final Item SCRAMBLED_EGGS = new Item(createGroup().food(FoodConstructor.createFood(REG_7)));
    public static final Item BUTTERED_TOAST = new Item(createGroup().food(FoodConstructor.createFood(REG_9)));
    public static final Item TOAST_WITH_JAM = new Item(createGroup().food(FoodConstructor.createFood(REG_9)));


    // meals
    public static final Item HAM_SANDWICH = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item PEANUT_BUTTER_AND_JAM = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item BLT = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item GRILLED_CHEESE = new Item(createGroup().food(FoodConstructor.createFood(REG_7)));
    public static final Item TUNA_SANDWICH = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item CHEESEBURGER = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item HAMBURGER = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item TOFUBURGER = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item PIZZA = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item SUPREME_PIZZA = new Item(createGroup().food(FoodConstructor.createFood(REG_18)));
    public static final Item CHEESE_PIZZA = new Item(createGroup().food(FoodConstructor.createFood(REG_14)));
    public static final Item PINEAPPLE_PEPPERONI_PIZZA = new Item(createGroup().food(FoodConstructor.createFood(REG_18)));
    public static final Item LEMON_CHICKEN = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item FRIED_CHICKEN = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item CHICKEN_AND_NOODLES = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item CHICKEN_AND_DUMPLINGS = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item TOFU_AND_DUMPLINGS = new Item(createGroup().food(FoodConstructor.createFood(REG_14)));
    public static final Item SPAGHETTI_SQUASH = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item CHICKEN_AND_RICE = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item TACO = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item SUSHI = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item EGG_ROLL = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item CASHEW_CHICKEN = new Item(createGroup().food(FoodConstructor.createFood(REG_14)));

    // desert block?
    //public static final Item coffeeCake;
    //public static final Item chocolateCake;
    //public static final Item strawberryShortCake;
    //public static final Item carrotCake;
    //public static final Item turtleCake;

    // desert item
    public static final Item YAM_JAM = new Item(createGroup().food(FoodConstructor.createFood(REG_14)));
    public static final Item BANANA_CREAM_PIE = new Item(createGroup().food(FoodConstructor.createFood(REG_14)));
    public static final Item CANDY_CORN = new Item(createGroup().food(FoodConstructor.createFood(REG_5)));
    public static final Item RUM_RAISIN_ICE_CREAM = new Item(createGroup().food(FoodConstructor.createFood(REG_14)));
    public static final Item CHEESE_CAKE = new Item(createGroup().food(FoodConstructor.createFood(REG_14)));
    public static final Item BROWNIES = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item SNICKER_DOODLE = new Item(createGroup().food(FoodConstructor.createFood(REG_7)));
    public static final Item BANANA_NUT_BREAD = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item CANDIED_NUTS = new Item(createGroup().food(FoodConstructor.createFood(REG_5)));
    public static final Item ALMOND_BRITTLE = new Item(createGroup().food(FoodConstructor.createFood(REG_7)));
    public static final Item OATMEAL_COOKIE = new Item(createGroup().food(FoodConstructor.createFood(REG_5)));
    public static final Item NUTTY_COOKIE = new Item(createGroup().food(FoodConstructor.createFood(REG_5)));
    //public static final Item praline = new Item(createGroup().food(EDIBLE_5));

    public static final Item BURRITO = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item TOSTADA = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item HORCHATA = new Drink(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item CARNITAS = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item FAJITAS = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item ENCHILADA = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item CHURROS = new Item(createGroup().food(FoodConstructor.createFood(REG_5)));
    public static final Item TAMALES = new Item(createGroup().food(FoodConstructor.createFood(REG_14)));
    public static final Item TRES_LECHE_CAKE = new Item(createGroup().food(FoodConstructor.createFood(REG_18)));
    public static final Item STUFFED_POBLANOS = new Item(createGroup().food(FoodConstructor.createFood(REG_14)));
    public static final Item CHILI_RELLENO = new Item(createGroup().food(FoodConstructor.createFood(REG_14)));
    public static final Item CREMA = new Item(createGroup().food(FoodConstructor.createFood(REG_3)));
    public static final Item REFRIED_BEANS = new Item(createGroup().food(FoodConstructor.createFood(REG_7)));
    public static final Item CHIMICHANGA = new Item(createGroup().food(FoodConstructor.createFood(REG_14)));
    public static final Item QUESADILLA = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));

    public static final Item CORN_HUSK = new Item(createGroup());
    public static final Item WHIPPING_CREAM = new Item(createGroup());

    // 1.4.0
    public static final Item SHEPHERDS_PIE = new Item(createGroup().food(FoodConstructor.createFood(REG_18)));
    public static final Item BEEF_WELLINGTON = new Item(createGroup().food(FoodConstructor.createFood(REG_18)));
    public static final Item FISH_AND_CHIPS = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item ETON_MESS = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item TEA = new Drink(createGroup().food(FoodConstructor.createBuilder(REG_5).alwaysEat().build()));
    public static final Item CORNISH_PASTY = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item SCONES = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item FIGGY_PUDDING = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item TREACLE_TART = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item STICKY_TOFFEE_PUDDING = new Item(createGroup().food(FoodConstructor.createFood(REG_14)));
    public static final Item TRIFLE = new Item(createGroup().food(FoodConstructor.createFood(REG_14)));
    public static final Item WATER_BOTTLE = new Item(createGroup());
    public static final Item MILK_BOTTLE = new Item(createGroup());

    // 1.7.0
    public static final Item AJVAR = new Item(createGroup().food(FoodConstructor.createFood(REG_7)));
    public static final Item AJVAR_TOAST = new Item(createGroup().food(FoodConstructor.createFood(REG_14)));
    public static final Item AVOCADO_TOAST = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item BEEF_STEW = new Item(createGroup().food(FoodConstructor.createFood(REG_14)));
    public static final Item BEEF_STIR_FRY = new Item(createGroup().food(FoodConstructor.createFood(REG_14)));
    public static final Item BUTTERED_GREEN_BEANS = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item CHEESY_ASPARAGUS = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item CHOCOLATE_ICE_CREAM = new Item(createGroup().food(FoodConstructor.createFood(REG_14)));
    public static final Item EGGPLANT_PARMESAN = new Item(createGroup().food(FoodConstructor.createFood(REG_18)));
    public static final Item FRUIT_CAKE = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item GRILLED_EGGPLANT = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item KIWI_SORBET = new Item(createGroup().food(FoodConstructor.createFood(REG_7)));
    public static final Item LEMON_COCONUT_BAR = new Item(createGroup().food(FoodConstructor.createFood(REG_7)));
    public static final Item NETHER_WART_STEW = new Item(createGroup().food(FoodConstructor.createFood(REG_7)));
    public static final Item PEANUT_BUTTER = new Item(createGroup().food(FoodConstructor.createFood(REG_5)));
    public static final Item PEANUT_BUTTER_W_CELERY = new Item(createGroup().food(FoodConstructor.createFood(REG_7)));
    public static final Item POTATO_SOUP = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item RATATOUILLE = new Item(createGroup().food(FoodConstructor.createFood(REG_18)));
    public static final Item RAW_BACON = new Item(createGroup().food(FoodConstructor.createFood(REG_1)));
    public static final Item RHUBARB_CRISP = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item ROASTED_ASPARAGUS = new Item(createGroup().food(FoodConstructor.createFood(REG_7)));
    public static final Item ROASTED_RADISHES = new Item(createGroup().food(FoodConstructor.createFood(REG_7)));
    public static final Item ROASTED_SQUASH = new Item(createGroup().food(FoodConstructor.createFood(REG_7)));
    public static final Item ROASTED_TURNIPS = new Item(createGroup().food(FoodConstructor.createFood(REG_7)));
    public static final Item STEAMED_BROCCOLI = new Item(createGroup().food(FoodConstructor.createFood(REG_7)));
    public static final Item STEAMED_GREEN_BEANS = new Item(createGroup().food(FoodConstructor.createFood(REG_7)));
    public static final Item STIR_FRY = new Item(createGroup().food(FoodConstructor.createFood(REG_18)));
    public static final Item STUFFED_ARTICHOKE = new Item(createGroup().food(FoodConstructor.createFood(REG_18)));
    public static final Item TOAST_SANDWICH = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));

    // 2.0.0
    public static final Item ROASTED_PUMPKIN_SEEDS = new Item(createGroup().food(FoodConstructor.createFood(REG_4)));
    public static final Item ROASTED_SUNFLOWER_SEEDS = new Item(createGroup().food(FoodConstructor.createFood(REG_4)));
    public static final Item PUMPKIN_BARS = new Item(createGroup().food(FoodConstructor.createFood(REG_6)));
    public static final Item CORN_BREAD = new Item(createGroup().food(FoodConstructor.createFood(REG_5)));
    public static final Item PUMPKIN_SOUP = new Soup(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item MERINGUE = new Item(createGroup().food(FoodConstructor.createFood(REG_6)));
    public static final Item CABBAGE_ROLL = new Item(createGroup().food(FoodConstructor.createFood(REG_14)));
    public static final Item BORSCHT = new Item(createGroup().food(FoodConstructor.createFood(REG_12)));
    public static final Item GOULASH = new Item(createGroup().food(FoodConstructor.createFood(REG_16)));
    public static final Item BEETROOT_SALAD = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item CANDIED_KUMQUATS = new Item(createGroup().food(FoodConstructor.createFood(REG_6)));
    public static final Item STEAMED_CRAB = new Item(createGroup().food(FoodConstructor.createFood(REG_6)));
    public static final Item SEA_LETTUCE = new Item(createGroup().food(FoodConstructor.createFood(REG_1)));
    public static final Item DEEP_FRIED_SHRIMP = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item TUNA_ROLL = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item FRIED_CALAMARI = new Item(createGroup().food(FoodConstructor.createFood(REG_10)));
    public static final Item CRAB_LEGS = new Item(createGroup().food(FoodConstructor.createFood(REG_11)));
    public static final Item STEAMED_CLAMS = new Item(createGroup().food(FoodConstructor.createFood(REG_11)));
    public static final Item GRILLED_OYSTERS = new Item(createGroup().food(FoodConstructor.createFood(REG_11)));
    public static final Item ANCHOVY_PIZZA = new Item(createGroup().food(FoodConstructor.createFood(REG_15)));
    public static final Item MASHED_POTATOES = new Item(createGroup().food(FoodConstructor.createFood(REG_9)));

    public static Block SALT_ORE_BLOCK = new Block(BlockBehaviour.Properties.of(Material.SAND).strength(0.5F).sound(SoundType.SAND));
    public static final Item SALT_ORE = new ItemNameBlockItem(SALT_ORE_BLOCK, createGroup());

    public static Item GUIDE;

    public static void registerBlocks(RegisterFunction<Block> register) {
        FarmlandCrop.registerBlocks(register);
        TreeCrop.registerBlocks(register);
        Tree.registerBlocks(register);


        register.register(createIdentifier(BlockNames.SALT_ORE), SALT_ORE_BLOCK);
    }

    public static void registerItems(RegisterFunction<Item> register) {
        FarmlandCrop.registerItems(register);
        TreeCrop.registerItems(register);
        Seafood.registerItems(register);
        Furnace.registerItems(register);
        Juice.registerItems(register);
        Jam.registerItems(register);
        Smoothie.registerItems(register);
        IceCream.registerItems(register);
        Pie.registerItems(register);
        Utensil.registerItems(register);
        Tree.registerItems(register);

        register.register(createIdentifier(ItemNamesV2.PAPRIKA), PAPRIKA);
        register.register(createIdentifier(ItemNamesV2.SALT), SALT);
        register.register(createIdentifier(ItemNamesV2.OLIVE_OIL), OLIVE_OIL);
        register.register(createIdentifier(ItemNamesV2.CHEESE), CHEESE);
        register.register(createIdentifier(ItemNamesV2.FLOUR), FLOUR);
        register.register(createIdentifier(ItemNamesV2.BUTTER), BUTTER);
        register.register(createIdentifier(ItemNamesV2.NOODLE), NOODLE);
        register.register(createIdentifier(ItemNamesV2.TOFU), TOFU);
        register.register(createIdentifier(ItemNamesV2.CHOCOLATE), CHOCOLATE);
        register.register(createIdentifier(ItemNamesV2.TORTILLA), TORTILLA);
        register.register(createIdentifier(ItemNamesV2.SOY_SAUCE), SOY_SAUCE);
        register.register(createIdentifier(ItemNamesV2.DOUGH), DOUGH);
        register.register(createIdentifier(ItemNamesV2.RAVIOLI), RAVIOLI);
        register.register(createIdentifier(ItemNamesV2.SALSA), SALSA);
        register.register(createIdentifier(ItemNamesV2.ARTICHOKE_DIP), ARTICHOKE_DIP);
        register.register(createIdentifier(ItemNamesV2.PEPPERONI), PEPPERONI);
        register.register(createIdentifier(ItemNamesV2.COFFEE), COFFEE);
        register.register(createIdentifier(ItemNamesV2.LEMONADE), LEMONADE);
        register.register(createIdentifier(ItemNamesV2.LIMEADE), LIMEADE);
        register.register(createIdentifier(ItemNamesV2.SOY_MILK), SOY_MILK);
        register.register(createIdentifier(ItemNamesV2.KALE_SMOOTHIE), KALE_SMOOTHIE);
        register.register(createIdentifier(ItemNamesV2.FRUIT_SMOOTHIE), FRUIT_SMOOTHIE);
        register.register(createIdentifier(ItemNamesV2.CHOCOLATE_MILKSHAKE), CHOCOLATE_MILKSHAKE);
        register.register(createIdentifier(ItemNamesV2.BEER), BEER);
        register.register(createIdentifier(ItemNamesV2.WINE), WINE);
        register.register(createIdentifier(ItemNamesV2.MEAD), MEAD);
        register.register(createIdentifier(ItemNamesV2.RUM), RUM);
        register.register(createIdentifier(ItemNamesV2.PUMPKIN_SPICE_LATTE), PUMPKIN_SPICE_LATTE);
        register.register(createIdentifier(ItemNamesV2.BEEF_JERKY), BEEF_JERKY);
        register.register(createIdentifier(ItemNamesV2.PORK_JERKY), PORK_JERKY);
        register.register(createIdentifier(ItemNamesV2.KALE_CHIPS), KALE_CHIPS);
        register.register(createIdentifier(ItemNamesV2.POTATO_CHIPS), POTATO_CHIPS);
        register.register(createIdentifier(ItemNamesV2.STEAMED_RICE), STEAMED_RICE);
        register.register(createIdentifier(ItemNamesV2.FRENCH_FRIES), FRENCH_FRIES);
        register.register(createIdentifier(ItemNamesV2.SWEET_POTATO_FRIES), SWEET_POTATO_FRIES);
        register.register(createIdentifier(ItemNamesV2.ONION_RINGS), ONION_RINGS);
        register.register(createIdentifier(ItemNamesV2.DOUGHNUT), DOUGHNUT);
        register.register(createIdentifier(ItemNamesV2.CUCUMBER_SALAD), CUCUMBER_SALAD);
        register.register(createIdentifier(ItemNamesV2.CAESAR_SALAD), CAESAR_SALAD);
        register.register(createIdentifier(ItemNamesV2.LEAFY_SALAD), LEAFY_SALAD);
        register.register(createIdentifier(ItemNamesV2.FRUIT_SALAD), FRUIT_SALAD);
        register.register(createIdentifier(ItemNamesV2.VEGGIE_SALAD), VEGGIE_SALAD);
        register.register(createIdentifier(ItemNamesV2.PORK_AND_BEANS), PORK_AND_BEANS);
        register.register(createIdentifier(ItemNamesV2.OATMEAL), OATMEAL);
        register.register(createIdentifier(ItemNamesV2.LEEK_SOUP), LEEK_SOUP);
        register.register(createIdentifier(ItemNamesV2.YOGHURT), YOGHURT);
        register.register(createIdentifier(ItemNamesV2.SAUCY_CHIPS), SAUCY_CHIPS);
        register.register(createIdentifier(ItemNamesV2.ROASTED_NUTS), ROASTED_NUTS);
        register.register(createIdentifier(ItemNamesV2.TRAIL_MIX), TRAIL_MIX);
        register.register(createIdentifier(ItemNamesV2.PROTEIN_BAR), PROTEIN_BAR);
        register.register(createIdentifier(ItemNamesV2.NOUGAT), NOUGAT);
        register.register(createIdentifier(ItemNamesV2.SCRAMBLED_EGGS), SCRAMBLED_EGGS);
        register.register(createIdentifier(ItemNamesV2.BUTTERED_TOAST), BUTTERED_TOAST);
        register.register(createIdentifier(ItemNamesV2.TOAST_WITH_JAM), TOAST_WITH_JAM);
        register.register(createIdentifier(ItemNamesV2.HAM_SANDWICH), HAM_SANDWICH);
        register.register(createIdentifier(ItemNamesV2.PEANUT_BUTTER_AND_JAM), PEANUT_BUTTER_AND_JAM);
        register.register(createIdentifier(ItemNamesV2.BLT), BLT);
        register.register(createIdentifier(ItemNamesV2.GRILLED_CHEESE), GRILLED_CHEESE);
        register.register(createIdentifier(ItemNamesV2.TUNA_SANDWICH), TUNA_SANDWICH);
        register.register(createIdentifier(ItemNamesV2.CHEESEBURGER), CHEESEBURGER);
        register.register(createIdentifier(ItemNamesV2.HAMBURGER), HAMBURGER);
        register.register(createIdentifier(ItemNamesV2.TOFUBURGER), TOFUBURGER);
        register.register(createIdentifier(ItemNamesV2.PIZZA), PIZZA);
        register.register(createIdentifier(ItemNamesV2.SUPREME_PIZZA), SUPREME_PIZZA);
        register.register(createIdentifier(ItemNamesV2.CHEESE_PIZZA), CHEESE_PIZZA);
        register.register(createIdentifier(ItemNamesV2.PINEAPPLE_PEPPERONI_PIZZA), PINEAPPLE_PEPPERONI_PIZZA);
        register.register(createIdentifier(ItemNamesV2.LEMON_CHICKEN), LEMON_CHICKEN);
        register.register(createIdentifier(ItemNamesV2.FRIED_CHICKEN), FRIED_CHICKEN);
        register.register(createIdentifier(ItemNamesV2.CHICKEN_AND_NOODLES), CHICKEN_AND_NOODLES);
        register.register(createIdentifier(ItemNamesV2.CHICKEN_AND_DUMPLINGS), CHICKEN_AND_DUMPLINGS);
        register.register(createIdentifier(ItemNamesV2.TOFU_AND_DUMPLINGS), TOFU_AND_DUMPLINGS);
        register.register(createIdentifier(ItemNamesV2.SPAGHETTI_SQUASH), SPAGHETTI_SQUASH);
        register.register(createIdentifier(ItemNamesV2.CHICKEN_AND_RICE), CHICKEN_AND_RICE);
        register.register(createIdentifier(ItemNamesV2.TACO), TACO);
        register.register(createIdentifier(ItemNamesV2.SUSHI), SUSHI);
        register.register(createIdentifier(ItemNamesV2.EGG_ROLL), EGG_ROLL);
        register.register(createIdentifier(ItemNamesV2.CASHEW_CHICKEN), CASHEW_CHICKEN);
        register.register(createIdentifier(ItemNamesV2.YAM_JAM), YAM_JAM);
        register.register(createIdentifier(ItemNamesV2.BANANA_CREAM_PIE), BANANA_CREAM_PIE);
        register.register(createIdentifier(ItemNamesV2.CANDY_CORN), CANDY_CORN);
        register.register(createIdentifier(ItemNamesV2.RUM_RAISIN_ICE_CREAM), RUM_RAISIN_ICE_CREAM);
        register.register(createIdentifier(ItemNamesV2.CHEESE_CAKE), CHEESE_CAKE);
        register.register(createIdentifier(ItemNamesV2.BROWNIES), BROWNIES);
        register.register(createIdentifier(ItemNamesV2.SNICKER_DOODLE), SNICKER_DOODLE);
        register.register(createIdentifier(ItemNamesV2.BANANA_NUT_BREAD), BANANA_NUT_BREAD);
        register.register(createIdentifier(ItemNamesV2.CANDIED_NUTS), CANDIED_NUTS);
        register.register(createIdentifier(ItemNamesV2.ALMOND_BRITTLE), ALMOND_BRITTLE);
        register.register(createIdentifier(ItemNamesV2.RAISIN_OATMEAL_COOKIE), OATMEAL_COOKIE);
        register.register(createIdentifier(ItemNamesV2.NUTTY_COOKIE), NUTTY_COOKIE);
        register.register(createIdentifier(ItemNamesV2.BURRITO), BURRITO);
        register.register(createIdentifier(ItemNamesV2.TOSTADA), TOSTADA);
        register.register(createIdentifier(ItemNamesV2.HORCHATA), HORCHATA);
        register.register(createIdentifier(ItemNamesV2.CARNITAS), CARNITAS);
        register.register(createIdentifier(ItemNamesV2.FAJITAS), FAJITAS);
        register.register(createIdentifier(ItemNamesV2.ENCHILADA), ENCHILADA);
        register.register(createIdentifier(ItemNamesV2.CHURROS), CHURROS);
        register.register(createIdentifier(ItemNamesV2.TAMALES), TAMALES);
        register.register(createIdentifier(ItemNamesV2.TRES_LECHE_CAKE), TRES_LECHE_CAKE);
        register.register(createIdentifier(ItemNamesV2.STUFFED_POBLANOS), STUFFED_POBLANOS);
        register.register(createIdentifier(ItemNamesV2.CHILI_RELLENO), CHILI_RELLENO);
        register.register(createIdentifier(ItemNamesV2.CREMA), CREMA);
        register.register(createIdentifier(ItemNamesV2.REFRIED_BEANS), REFRIED_BEANS);
        register.register(createIdentifier(ItemNamesV2.CHIMICHANGA), CHIMICHANGA);
        register.register(createIdentifier(ItemNamesV2.QUESADILLA), QUESADILLA);
        register.register(createIdentifier(ItemNamesV2.CORN_HUSK), CORN_HUSK);
        register.register(createIdentifier(ItemNamesV2.WHIPPING_CREAM), WHIPPING_CREAM);
        register.register(createIdentifier(ItemNamesV2.SHEPHERDS_PIE), SHEPHERDS_PIE);
        register.register(createIdentifier(ItemNamesV2.BEEF_WELLINGTON), BEEF_WELLINGTON);
        register.register(createIdentifier(ItemNamesV2.FISH_AND_CHIPS), FISH_AND_CHIPS);
        register.register(createIdentifier(ItemNamesV2.ETON_MESS), ETON_MESS);
        register.register(createIdentifier(ItemNamesV2.TEA), TEA);
        register.register(createIdentifier(ItemNamesV2.CORNISH_PASTY), CORNISH_PASTY);
        register.register(createIdentifier(ItemNamesV2.SCONES), SCONES);
        register.register(createIdentifier(ItemNamesV2.FIGGY_PUDDING), FIGGY_PUDDING);
        register.register(createIdentifier(ItemNamesV2.TREACLE_TART), TREACLE_TART);
        register.register(createIdentifier(ItemNamesV2.STICKY_TOFFEE_PUDDING), STICKY_TOFFEE_PUDDING);
        register.register(createIdentifier(ItemNamesV2.TRIFLE), TRIFLE);
        register.register(createIdentifier(ItemNamesV2.WATER_BOTTLE), WATER_BOTTLE);
        register.register(createIdentifier(ItemNamesV2.MILK_BOTTLE), MILK_BOTTLE);
        register.register(createIdentifier(ItemNamesV2.AJVAR), AJVAR);
        register.register(createIdentifier(ItemNamesV2.AJVAR_TOAST), AJVAR_TOAST);
        register.register(createIdentifier(ItemNamesV2.AVOCADO_TOAST), AVOCADO_TOAST);
        register.register(createIdentifier(ItemNamesV2.BEEF_STEW), BEEF_STEW);
        register.register(createIdentifier(ItemNamesV2.BEEF_STIR_FRY), BEEF_STIR_FRY);
        register.register(createIdentifier(ItemNamesV2.BUTTERED_GREEN_BEANS), BUTTERED_GREEN_BEANS);
        register.register(createIdentifier(ItemNamesV2.CHEESY_ASPARAGUS), CHEESY_ASPARAGUS);
        register.register(createIdentifier(ItemNamesV2.CHOCOLATE_ICE_CREAM), CHOCOLATE_ICE_CREAM);
        register.register(createIdentifier(ItemNamesV2.EGGPLANT_PARMESAN), EGGPLANT_PARMESAN);
        register.register(createIdentifier(ItemNamesV2.FRUIT_CAKE), FRUIT_CAKE);
        register.register(createIdentifier(ItemNamesV2.GRILLED_EGGPLANT), GRILLED_EGGPLANT);
        register.register(createIdentifier(ItemNamesV2.KIWI_SORBET), KIWI_SORBET);
        register.register(createIdentifier(ItemNamesV2.LEMON_COCONUT_BAR), LEMON_COCONUT_BAR);
        register.register(createIdentifier(ItemNamesV2.NETHER_WART_STEW), NETHER_WART_STEW);
        register.register(createIdentifier(ItemNamesV2.PEANUT_BUTTER), PEANUT_BUTTER);
        register.register(createIdentifier(ItemNamesV2.PEANUT_BUTTER_W_CELERY), PEANUT_BUTTER_W_CELERY);
        register.register(createIdentifier(ItemNamesV2.POTATO_SOUP), POTATO_SOUP);
        register.register(createIdentifier(ItemNamesV2.RATATOUILLE), RATATOUILLE);
        register.register(createIdentifier(ItemNamesV2.RAW_BACON), RAW_BACON);
        register.register(createIdentifier(ItemNamesV2.RHUBARB_CRISP), RHUBARB_CRISP);
        register.register(createIdentifier(ItemNamesV2.ROASTED_ASPARAGUS), ROASTED_ASPARAGUS);
        register.register(createIdentifier(ItemNamesV2.ROASTED_RADISHES), ROASTED_RADISHES);
        register.register(createIdentifier(ItemNamesV2.ROASTED_SQUASH), ROASTED_SQUASH);
        register.register(createIdentifier(ItemNamesV2.ROASTED_TURNIPS), ROASTED_TURNIPS);
        register.register(createIdentifier(ItemNamesV2.STEAMED_BROCCOLI), STEAMED_BROCCOLI);
        register.register(createIdentifier(ItemNamesV2.STEAMED_GREEN_BEANS), STEAMED_GREEN_BEANS);
        register.register(createIdentifier(ItemNamesV2.STIR_FRY), STIR_FRY);
        register.register(createIdentifier(ItemNamesV2.STUFFED_ARTICHOKE), STUFFED_ARTICHOKE);
        register.register(createIdentifier(ItemNamesV2.TOAST_SANDWICH), TOAST_SANDWICH);
        register.register(createIdentifier(ItemNamesV2.ROASTED_PUMPKIN_SEEDS), ROASTED_PUMPKIN_SEEDS);
        register.register(createIdentifier(ItemNamesV2.ROASTED_SUNFLOWER_SEEDS), ROASTED_SUNFLOWER_SEEDS);
        register.register(createIdentifier(ItemNamesV2.PUMPKIN_BARS), PUMPKIN_BARS);
        register.register(createIdentifier(ItemNamesV2.CORN_BREAD), CORN_BREAD);
        register.register(createIdentifier(ItemNamesV2.PUMPKIN_SOUP), PUMPKIN_SOUP);
        register.register(createIdentifier(ItemNamesV2.MERINGUE), MERINGUE);
        register.register(createIdentifier(ItemNamesV2.CABBAGE_ROLL), CABBAGE_ROLL);
        register.register(createIdentifier(ItemNamesV2.BORSCHT), BORSCHT);
        register.register(createIdentifier(ItemNamesV2.GOULASH), GOULASH);
        register.register(createIdentifier(ItemNamesV2.BEETROOT_SALAD), BEETROOT_SALAD);
        register.register(createIdentifier(ItemNamesV2.CANDIED_KUMQUATS), CANDIED_KUMQUATS);
        register.register(createIdentifier(ItemNamesV2.STEAMED_CRAB), STEAMED_CRAB);
        register.register(createIdentifier(ItemNamesV2.SEA_LETTUCE), SEA_LETTUCE);
        register.register(createIdentifier(ItemNamesV2.DEEP_FRIED_SHRIMP), DEEP_FRIED_SHRIMP);
        register.register(createIdentifier(ItemNamesV2.TUNA_ROLL), TUNA_ROLL);
        register.register(createIdentifier(ItemNamesV2.FRIED_CALAMARI), FRIED_CALAMARI);
        register.register(createIdentifier(ItemNamesV2.CRAB_LEGS), CRAB_LEGS);
        register.register(createIdentifier(ItemNamesV2.STEAMED_CLAMS), STEAMED_CLAMS);
        register.register(createIdentifier(ItemNamesV2.GRILLED_OYSTERS), GRILLED_OYSTERS);
        register.register(createIdentifier(ItemNamesV2.ANCHOVY_PIZZA), ANCHOVY_PIZZA);
        register.register(createIdentifier(ItemNamesV2.MASHED_POTATOES), MASHED_POTATOES);
        register.register(createIdentifier(ItemNamesV2.SALT_ORE), SALT_ORE);
    }

    public static Stream<Item> createCropStream() {
        return Stream.concat(
                Arrays.stream(FarmlandCrop.copy().toArray(new FarmlandCrop[0])),
                Stream.concat(Arrays.stream(TreeCrop.copy().toArray(new TreeCrop[0])), Arrays.stream(Tree.copy().toArray(new Tree[0])))
        ).map(ItemLike::asItem);
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<FC, ?>> register(ResourceLocation id, ConfiguredFeature<FC, F> feature) {
        return badRegister(BuiltinRegistries.CONFIGURED_FEATURE, id, feature);
    }

    public static <V extends T, T> Holder<V> badRegister(Registry<T> registry, ResourceLocation id, V value) {
        //noinspection unchecked
        return BuiltinRegistries.register((Registry<V>) registry, id, value);
    }
}
