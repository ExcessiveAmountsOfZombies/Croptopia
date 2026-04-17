package com.epherical.croptopia.register;

import com.epherical.croptopia.common.BlockNames;
import com.epherical.croptopia.common.ItemNamesV2;
import com.epherical.croptopia.common.MiscNames;
import com.epherical.croptopia.common.Tags;
import com.epherical.croptopia.common.generator.ConfiguredFeatureKeys;
import com.epherical.croptopia.common.generator.PlacedFeatureKeys;
import com.epherical.croptopia.items.Drink;
import com.epherical.croptopia.items.ReferenceItem;
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
import com.epherical.croptopia.register.helpers.VanillaCrops;
import com.epherical.croptopia.util.FoodConstructor;
import com.epherical.croptopia.util.RegisterFunction;
import com.epherical.croptopia.util.RegistryDelay;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
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
import net.minecraft.world.level.material.MapColor;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static com.epherical.croptopia.CroptopiaMod.createGroup;
import static com.epherical.croptopia.CroptopiaMod.createIdentifier;
import static com.epherical.croptopia.common.ItemNamesV2.*;
import static com.epherical.croptopia.util.FoodConstructor.*;

public class Content {

    public static final RegistryDelay<Item, Item> ITEM_REGISTER = new RegistryDelay<>(MiscNames.MOD_ID);
    public static final RegistryDelay<Block, Block> BLOCK_REGISTER = new RegistryDelay<>(MiscNames.MOD_ID);


    public static final FarmlandCrop ARTICHOKE = new FarmlandCrop(ItemNamesV2.ARTICHOKE, true, TagCategory.VEGETABLES, RAW_CROP_1, Tags.HAS_ARTICHOKE);
    public static final FarmlandCrop ASPARAGUS = new FarmlandCrop(ItemNamesV2.ASPARAGUS, false, TagCategory.VEGETABLES, RAW_CROP_1, Tags.HAS_ASPARAGUS);
    public static final FarmlandCrop BARLEY = new FarmlandCrop(ItemNamesV2.BARLEY, false, TagCategory.GRAIN, RAW_CROP_1, Tags.HAS_BARLEY);
    public static final FarmlandCrop BASIL = new FarmlandCrop(ItemNamesV2.BASIL, false, TagCategory.CROPS, RAW_CROP_1, Tags.HAS_BASIL);
    public static final FarmlandCrop BELLPEPPER = new FarmlandCrop(ItemNamesV2.BELLPEPPER, true, TagCategory.FRUITS, RAW_CROP_1, Tags.HAS_BELLPEPPER);
    public static final FarmlandCrop BLACKBEAN = new FarmlandCrop(ItemNamesV2.BLACKBEAN, true, TagCategory.CROPS, RAW_CROP_1, Tags.HAS_BLACKBEAN);
    public static final FarmlandCrop BLACKBERRY = new FarmlandCrop(ItemNamesV2.BLACKBERRY, true, TagCategory.FRUITS, RAW_CROP_1, Tags.HAS_BLACKBERRY);
    public static final FarmlandCrop BLUEBERRY = new FarmlandCrop(ItemNamesV2.BLUEBERRY, true, TagCategory.FRUITS, RAW_CROP_1, Tags.HAS_BLUEBERRY);
    public static final FarmlandCrop BROCCOLI = new FarmlandCrop(ItemNamesV2.BROCCOLI, false, TagCategory.VEGETABLES, RAW_CROP_1, Tags.HAS_BROCCOLI);
    public static final FarmlandCrop CABBAGE = new FarmlandCrop(ItemNamesV2.CABBAGE, false, TagCategory.VEGETABLES, RAW_CROP_1, Tags.HAS_CABBAGE);
    public static final FarmlandCrop CANTALOUPE = new FarmlandCrop(ItemNamesV2.CANTALOUPE, true, TagCategory.FRUITS, RAW_CROP_1, Tags.HAS_CANTALOUPE);
    public static final FarmlandCrop CAULIFLOWER = new FarmlandCrop(ItemNamesV2.CAULIFLOWER, false, TagCategory.VEGETABLES, RAW_CROP_1, Tags.HAS_CAULIFLOWER);
    public static final FarmlandCrop CELERY = new FarmlandCrop(ItemNamesV2.CELERY, false, TagCategory.VEGETABLES, RAW_CROP_1, Tags.HAS_CELERY);
    public static final FarmlandCrop CHILE_PEPPER = new FarmlandCrop(ItemNamesV2.CHILE_PEPPER, true, TagCategory.CROPS, RAW_CROP_1, Tags.HAS_CHILE_PEPPER);
    public static final FarmlandCrop COFFEE_BEANS = new FarmlandCrop("coffee", ItemNamesV2.COFFEE_BEANS, false, TagCategory.CROPS, RAW_CROP_1, Tags.HAS_COFFEE_BEANS);
    public static final FarmlandCrop CORN = new FarmlandCrop(ItemNamesV2.CORN, false, TagCategory.GRAIN, RAW_CROP_1, Tags.HAS_CORN);
    public static final FarmlandCrop CRANBERRY = new FarmlandCrop(ItemNamesV2.CRANBERRY, true, TagCategory.FRUITS, RAW_CROP_1, Tags.HAS_CRANBERRY);
    public static final FarmlandCrop CUCUMBER = new FarmlandCrop(ItemNamesV2.CUCUMBER, true, TagCategory.VEGETABLES, RAW_CROP_1, Tags.HAS_CUCUMBER);
    public static final FarmlandCrop CURRANT = new FarmlandCrop(ItemNamesV2.CURRANT, true, TagCategory.FRUITS, RAW_CROP_1, Tags.HAS_CURRANT);
    public static final FarmlandCrop EGGPLANT = new FarmlandCrop(ItemNamesV2.EGGPLANT, true, TagCategory.VEGETABLES, RAW_CROP_1, Tags.HAS_EGGPLANT);
    public static final FarmlandCrop ELDERBERRY = new FarmlandCrop(ItemNamesV2.ELDERBERRY, true, TagCategory.FRUITS, RAW_CROP_1, Tags.HAS_ELDERBERRY);
    public static final FarmlandCrop GARLIC = new FarmlandCrop(ItemNamesV2.GARLIC, false, TagCategory.VEGETABLES, RAW_CROP_1, Tags.HAS_GARLIC);
    public static final FarmlandCrop GINGER = new FarmlandCrop(ItemNamesV2.GINGER, true, TagCategory.VEGETABLES, null, Tags.HAS_GINGER);
    public static final FarmlandCrop GRAPE = new FarmlandCrop(ItemNamesV2.GRAPE, true, TagCategory.FRUITS, RAW_CROP_1, Tags.HAS_GRAPE);
    public static final FarmlandCrop GREENBEAN = new FarmlandCrop(ItemNamesV2.GREENBEAN, true, TagCategory.VEGETABLES, RAW_CROP_1, Tags.HAS_GREENBEAN);
    public static final FarmlandCrop GREENONION = new FarmlandCrop(ItemNamesV2.GREENONION, true, TagCategory.VEGETABLES, RAW_CROP_1, Tags.HAS_GREENONION);
    public static final FarmlandCrop HONEYDEW = new FarmlandCrop(ItemNamesV2.HONEYDEW, false, TagCategory.FRUITS, RAW_CROP_1, Tags.HAS_HONEYDEW);
    public static final FarmlandCrop HOPS = new FarmlandCrop(ItemNamesV2.HOPS, false, TagCategory.CROPS, null, Tags.HAS_HOPS);
    public static final FarmlandCrop KALE = new FarmlandCrop(ItemNamesV2.KALE, false, TagCategory.VEGETABLES, RAW_CROP_1, Tags.HAS_KALE);
    public static final FarmlandCrop KIWI = new FarmlandCrop(ItemNamesV2.KIWI, true, TagCategory.FRUITS, RAW_CROP_1, Tags.HAS_KIWI);
    public static final FarmlandCrop LEEK = new FarmlandCrop(ItemNamesV2.LEEK, false, TagCategory.VEGETABLES, RAW_CROP_1, Tags.HAS_LEEK);
    public static final FarmlandCrop LETTUCE = new FarmlandCrop(ItemNamesV2.LETTUCE, false, TagCategory.VEGETABLES, RAW_CROP_1, Tags.HAS_LETTUCE);
    public static final FarmlandCrop MUSTARD = new FarmlandCrop(ItemNamesV2.MUSTARD, false, TagCategory.VEGETABLES, null, Tags.HAS_MUSTARD);
    public static final FarmlandCrop OAT = new FarmlandCrop(ItemNamesV2.OAT, false, TagCategory.GRAIN, RAW_CROP_1, Tags.HAS_OAT);
    public static final FarmlandCrop OLIVE = new FarmlandCrop(ItemNamesV2.OLIVE, true, TagCategory.FRUITS, RAW_CROP_1, Tags.HAS_OLIVE);
    public static final FarmlandCrop ONION = new FarmlandCrop(ItemNamesV2.ONION, true, TagCategory.VEGETABLES, RAW_CROP_1, Tags.HAS_ONION);
    public static final FarmlandCrop PEANUT = new FarmlandCrop(ItemNamesV2.PEANUT, true, TagCategory.CROPS, RAW_CROP_1, Tags.HAS_PEANUT);
    public static final FarmlandCrop PEPPER = new FarmlandCrop(ItemNamesV2.PEPPER, false, TagCategory.CROPS, null, Tags.HAS_PEPPER);
    public static final FarmlandCrop PINEAPPLE = new FarmlandCrop(ItemNamesV2.PINEAPPLE, true, TagCategory.FRUITS, RAW_CROP_1, Tags.HAS_PINEAPPLE);
    public static final FarmlandCrop RADISH = new FarmlandCrop(ItemNamesV2.RADISH, true, TagCategory.VEGETABLES, RAW_CROP_1, Tags.HAS_RADISH);
    public static final FarmlandCrop RASPBERRY = new FarmlandCrop(ItemNamesV2.RASPBERRY, true, TagCategory.FRUITS, RAW_CROP_1, Tags.HAS_RASPBERRY);
    public static final FarmlandCrop RHUBARB = new FarmlandCrop(ItemNamesV2.RHUBARB, false, TagCategory.VEGETABLES, RAW_CROP_1, Tags.HAS_RHUBARB);
    public static final FarmlandCrop RICE = new FarmlandCrop(ItemNamesV2.RICE, false, TagCategory.GRAIN, REG_1, Tags.HAS_RICE);
    public static final FarmlandCrop RUTABAGA = new FarmlandCrop(ItemNamesV2.RUTABAGA, true, TagCategory.VEGETABLES, RAW_CROP_1, Tags.HAS_RUTABAGA);
    public static final FarmlandCrop SAGUARO = new FarmlandCrop(ItemNamesV2.SAGUARO, true, TagCategory.FRUITS, RAW_CROP_1, Tags.HAS_SAGUARO);
    public static final FarmlandCrop SOYBEAN = new FarmlandCrop(ItemNamesV2.SOYBEAN, true, TagCategory.VEGETABLES, RAW_CROP_1, Tags.HAS_SOYBEAN);
    public static final FarmlandCrop SPINACH = new FarmlandCrop(ItemNamesV2.SPINACH, false, TagCategory.VEGETABLES, RAW_CROP_1, Tags.HAS_SPINACH);
    public static final FarmlandCrop SQUASH = new FarmlandCrop(ItemNamesV2.SQUASH, true, TagCategory.VEGETABLES, RAW_CROP_1, Tags.HAS_SQUASH);
    public static final FarmlandCrop STRAWBERRY = new FarmlandCrop(ItemNamesV2.STRAWBERRY, true, TagCategory.FRUITS, RAW_CROP_1, Tags.HAS_STRAWBERRY);
    public static final FarmlandCrop SWEETPOTATO = new FarmlandCrop(ItemNamesV2.SWEETPOTATO, true, TagCategory.VEGETABLES, RAW_CROP_1, Tags.HAS_SWEETPOTATO);
    public static final FarmlandCrop TEA_LEAVES = new FarmlandCrop("tea", ItemNamesV2.TEA_LEAVES, false, TagCategory.CROPS, null, Tags.HAS_TEA_LEAVES);
    public static final FarmlandCrop TOMATILLO = new FarmlandCrop(ItemNamesV2.TOMATILLO, true, TagCategory.VEGETABLES, RAW_CROP_1, Tags.HAS_TOMATILLO);
    public static final FarmlandCrop TOMATO = new FarmlandCrop(ItemNamesV2.TOMATO, true, TagCategory.VEGETABLES, RAW_CROP_1, Tags.HAS_TOMATO);
    public static final FarmlandCrop TURMERIC = new FarmlandCrop(ItemNamesV2.TURMERIC, false, TagCategory.CROPS, null, Tags.HAS_TURMERIC);
    public static final FarmlandCrop TURNIP = new FarmlandCrop(ItemNamesV2.TURNIP, true, TagCategory.VEGETABLES, RAW_CROP_1, Tags.HAS_TURNIP);
    public static final FarmlandCrop VANILLA = new FarmlandCrop(ItemNamesV2.VANILLA, false, TagCategory.CROPS, null, Tags.HAS_VANILLA);
    public static final FarmlandCrop YAM = new FarmlandCrop(ItemNamesV2.YAM, true, TagCategory.VEGETABLES, RAW_CROP_1, Tags.HAS_YAM);
    public static final FarmlandCrop ZUCCHINI = new FarmlandCrop(ItemNamesV2.ZUCCHINI, false, TagCategory.VEGETABLES, RAW_CROP_1, Tags.HAS_ZUCCHINI);

    public static final TreeCrop ALMOND = new TreeCrop(ItemNamesV2.ALMOND, true, Blocks.DARK_OAK_LOG, Blocks.DARK_OAK_LEAVES, TagCategory.NUTS, RAW_CROP_2, 4, 3, 0,
            ConfiguredFeatureKeys.ALMOND_TREE_KEY, PlacedFeatureKeys.ALMOND_TREE_PLACED_KEY);
    public static final TreeCrop APPLE = new TreeCrop(ItemNamesV2.APPLE, true, Blocks.OAK_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, null, 5, 3, 0,
            ConfiguredFeatureKeys.APPLE_TREE_KEY, PlacedFeatureKeys.APPLE_TREE_PLACED_KEY);
    public static final TreeCrop APRICOT = new TreeCrop(ItemNamesV2.APRICOT, true, Blocks.OAK_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, RAW_CROP_2, 5, 2, 0,
            ConfiguredFeatureKeys.APRICOT_TREE_KEY, PlacedFeatureKeys.APRICOT_TREE_PLACED_KEY);
    public static final TreeCrop AVOCADO = new TreeCrop(ItemNamesV2.AVOCADO, true, Blocks.SPRUCE_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, RAW_CROP_2, 5, 3, 0,
            ConfiguredFeatureKeys.AVOCADO_TREE_KEY, PlacedFeatureKeys.AVOCADO_TREE_PLACED_KEY);
    public static final TreeCrop BANANA = new TreeCrop(ItemNamesV2.BANANA, true, Blocks.JUNGLE_LOG, Blocks.JUNGLE_LEAVES, TagCategory.FRUITS, RAW_CROP_2, 4, 8, 0,
            ConfiguredFeatureKeys.BANANA_TREE_KEY, PlacedFeatureKeys.BANANA_TREE_PLACED_KEY);
    public static final TreeCrop CASHEW = new TreeCrop(ItemNamesV2.CASHEW, true, Blocks.DARK_OAK_LOG, Blocks.DARK_OAK_LEAVES, TagCategory.CROPS, RAW_CROP_2, 4, 3, 0,
            ConfiguredFeatureKeys.CASHEW_TREE_KEY, PlacedFeatureKeys.CASHEW_TREE_PLACED_KEY);
    public static final TreeCrop CHERRY = new TreeCrop(ItemNamesV2.CHERRY, true, Blocks.OAK_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, RAW_CROP_2, 5, 3, 0,
            ConfiguredFeatureKeys.CHERRY_TREE_KEY, PlacedFeatureKeys.CHERRY_TREE_PLACED_KEY);
    public static final TreeCrop COCONUT = new TreeCrop(ItemNamesV2.COCONUT, true, Blocks.JUNGLE_LOG, Blocks.JUNGLE_LEAVES, TagCategory.FRUITS, RAW_CROP_2, 5, 2, 3,
            ConfiguredFeatureKeys.COCONUT_TREE_KEY, PlacedFeatureKeys.COCONUT_TREE_PLACED_KEY);
    public static final TreeCrop DATE = new TreeCrop(ItemNamesV2.DATE, true, Blocks.JUNGLE_LOG, Blocks.JUNGLE_LEAVES, TagCategory.FRUITS, RAW_CROP_2, 5, 8, 0,
            ConfiguredFeatureKeys.DATE_TREE_KEY, PlacedFeatureKeys.DATE_TREE_PLACED_KEY);
    public static final TreeCrop DRAGONFRUIT = new TreeCrop(ItemNamesV2.DRAGONFRUIT, true, Blocks.JUNGLE_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, RAW_CROP_2, 5, 7, 0,
            ConfiguredFeatureKeys.DRAGON_FRUIT_TREE_KEY, PlacedFeatureKeys.DRAGONFRUIT_TREE_PLACED_KEY);
    public static final TreeCrop FIG = new TreeCrop(ItemNamesV2.FIG, true, Blocks.JUNGLE_LOG, Blocks.JUNGLE_LEAVES, TagCategory.FRUITS, RAW_CROP_2, 4, 8, 0,
            ConfiguredFeatureKeys.FIG_TREE_KEY, PlacedFeatureKeys.FIG_TREE_PLACED_KEY);
    public static final TreeCrop GRAPEFRUIT = new TreeCrop(ItemNamesV2.GRAPEFRUIT, true, Blocks.JUNGLE_LOG, Blocks.JUNGLE_LEAVES, TagCategory.FRUITS, RAW_CROP_2, 4, 8, 0,
            ConfiguredFeatureKeys.GRAPEFRUIT_TREE_KEY, PlacedFeatureKeys.GRAPEFRUIT_TREE_PLACED_KEY);
    public static final TreeCrop KUMQUAT = new TreeCrop(ItemNamesV2.KUMQUAT, true, Blocks.JUNGLE_LOG, Blocks.JUNGLE_LEAVES, TagCategory.FRUITS, RAW_CROP_2, 4, 8, 0,
            ConfiguredFeatureKeys.KUMQUAT_TREE_KEY, PlacedFeatureKeys.KUMQUAT_TREE_PLACED_KEY);
    public static final TreeCrop LEMON = new TreeCrop(ItemNamesV2.LEMON, true, Blocks.OAK_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, RAW_CROP_2, 5, 3, 0,
            ConfiguredFeatureKeys.LEMON_TREE_KEY, PlacedFeatureKeys.LEMON_TREE_PLACED_KEY);
    public static final TreeCrop LIME = new TreeCrop(ItemNamesV2.LIME, true, Blocks.OAK_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, RAW_CROP_2, 5, 2, 0,
            ConfiguredFeatureKeys.LIME_TREE_KEY, PlacedFeatureKeys.LIME_TREE_PLACED_KEY);
    public static final TreeCrop MANGO = new TreeCrop(ItemNamesV2.MANGO, true, Blocks.JUNGLE_LOG, Blocks.JUNGLE_LEAVES, TagCategory.FRUITS, RAW_CROP_2, 5, 8, 0,
            ConfiguredFeatureKeys.MANGO_TREE_KEY, PlacedFeatureKeys.MANGO_TREE_PLACED_KEY);
    public static final TreeCrop NECTARINE = new TreeCrop(ItemNamesV2.NECTARINE, true, Blocks.OAK_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, RAW_CROP_2, 4, 4, 0,
            ConfiguredFeatureKeys.NECTARINE_TREE_KEY, PlacedFeatureKeys.NECTARINE_TREE_PLACED_KEY);
    public static final TreeCrop NUTMEG = new TreeCrop(ItemNamesV2.NUTMEG, true, Blocks.JUNGLE_LOG, Blocks.JUNGLE_LEAVES, TagCategory.CROPS, RAW_CROP_2, 4, 8, 0,
            ConfiguredFeatureKeys.NUTMEG_TREE_KEY, PlacedFeatureKeys.NUTMEG_TREE_PLACED_KEY);
    public static final TreeCrop ORANGE = new TreeCrop(ItemNamesV2.ORANGE, true, Blocks.OAK_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, RAW_CROP_2, 4, 4, 0,
            ConfiguredFeatureKeys.ORANGE_TREE_KEY, PlacedFeatureKeys.ORANGE_TREE_PLACED_KEY);
    public static final TreeCrop PEACH = new TreeCrop(ItemNamesV2.PEACH, true, Blocks.OAK_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, RAW_CROP_2, 5, 3, 0,
            ConfiguredFeatureKeys.PEACH_TREE_KEY, PlacedFeatureKeys.PEACH_TREE_PLACED_KEY);
    public static final TreeCrop PEAR = new TreeCrop(ItemNamesV2.PEAR, true, Blocks.OAK_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, RAW_CROP_2, 5, 2, 0,
            ConfiguredFeatureKeys.PEAR_TREE_KEY, PlacedFeatureKeys.PEAR_TREE_PLACED_KEY);
    public static final TreeCrop PECAN = new TreeCrop(ItemNamesV2.PECAN, true, Blocks.DARK_OAK_LOG, Blocks.DARK_OAK_LEAVES, TagCategory.NUTS, RAW_CROP_2, 4, 3, 0,
            ConfiguredFeatureKeys.PECAN_TREE_KEY, PlacedFeatureKeys.PECAN_TREE_PLACED_KEY);
    public static final TreeCrop PERSIMMON = new TreeCrop(ItemNamesV2.PERSIMMON, true, Blocks.OAK_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, RAW_CROP_2, 5, 3, 0,
            ConfiguredFeatureKeys.PERSIMMON_TREE_KEY, PlacedFeatureKeys.PERSIMMON_TREE_PLACED_KEY);
    public static final TreeCrop PLUM = new TreeCrop(ItemNamesV2.PLUM, true, Blocks.OAK_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, RAW_CROP_2, 5, 3, 0,
            ConfiguredFeatureKeys.PLUM_TREE_KEY, PlacedFeatureKeys.PLUM_TREE_PLACED_KEY);
    public static final TreeCrop STARFRUIT = new TreeCrop(ItemNamesV2.STARFRUIT, true, Blocks.OAK_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, RAW_CROP_2, 5, 3, 0,
            ConfiguredFeatureKeys.STAR_FRUIT_TREE_KEY, PlacedFeatureKeys.STARFRUIT_TREE_PLACED_KEY);
    public static final TreeCrop WALNUT = new TreeCrop(ItemNamesV2.WALNUT, true, Blocks.DARK_OAK_LOG, Blocks.DARK_OAK_LEAVES, TagCategory.NUTS, RAW_CROP_2, 4, 3, 0,
            ConfiguredFeatureKeys.WALNUT_TREE_KEY, PlacedFeatureKeys.WALNUT_TREE_PLACED_KEY);

    public static final Tree CINNAMON = new Tree(ItemNamesV2.CINNAMON, false, TagCategory.CROPS, 4, 3, 0, ConfiguredFeatureKeys.CINNAMON_TREE_KEY, PlacedFeatureKeys.CINNAMON_TREE_PLACED_KEY);

    public static final Seafood ANCHOVY = new Seafood(ItemNamesV2.ANCHOVY, true, RAW_MEAT_1);
    public static final Seafood CALAMARI = new Seafood(ItemNamesV2.CALAMARI, false, RAW_MEAT_1);
    public static final Seafood CLAM = new Seafood(ItemNamesV2.CLAM, true, RAW_MEAT_1);
    public static final Seafood CRAB = new Seafood(ItemNamesV2.CRAB, true, RAW_MEAT_1);
    public static final Seafood GLOWING_CALAMARI = new Seafood(ItemNamesV2.GLOWING_CALAMARI, false, RAW_MEAT_1);
    public static final Seafood OYSTER = new Seafood(ItemNamesV2.OYSTER, true, RAW_MEAT_1);
    public static final Seafood ROE = new Seafood(ItemNamesV2.ROE, false, RAW_MEAT_1);
    public static final Seafood SHRIMP = new Seafood(ItemNamesV2.SHRIMP, false, RAW_MEAT_1);
    public static final Seafood TUNA = new Seafood(ItemNamesV2.TUNA, false, RAW_MEAT_1);

    public static final Furnace BAKED_BEANS = new Furnace(ItemNamesV2.BAKED_BEANS, false, FURNACE_5);
    public static final Furnace BAKED_SWEET_POTATO = new Furnace(ItemNamesV2.BAKED_SWEET_POTATO, true, FURNACE_5);
    public static final Furnace BAKED_YAM = new Furnace(ItemNamesV2.BAKED_YAM, true, FURNACE_5);
    public static final Furnace CARAMEL = new Furnace(ItemNamesV2.CARAMEL, false, null);
    public static final Furnace COOKED_ANCHOVY = new Furnace(ItemNamesV2.COOKED_ANCHOVY, true, FURNACE_4);
    public static final Furnace COOKED_BACON = new Furnace(ItemNamesV2.COOKED_BACON, false, FURNACE_7);
    public static final Furnace COOKED_CALAMARI = new Furnace(ItemNamesV2.COOKED_CALAMARI, false, FURNACE_5);
    public static final Furnace COOKED_SHRIMP = new Furnace(ItemNamesV2.COOKED_SHRIMP, false, FURNACE_5);
    public static final Furnace COOKED_TUNA = new Furnace(ItemNamesV2.COOKED_TUNA, false, REG_6);
    public static final Furnace MOLASSES = new Furnace(ItemNamesV2.MOLASSES, false, null);
    public static final Furnace POPCORN = new Furnace(ItemNamesV2.POPCORN, false, FURNACE_3);
    public static final Furnace RAISINS = new Furnace(ItemNamesV2.RAISINS, false, FURNACE_3);
    public static final Furnace TOAST = new Furnace(ItemNamesV2.TOAST, true, FURNACE_7);

    public static final Juice APPLE_JUICE = new Juice(ItemNamesV2.APPLE_JUICE, APPLE);
    public static final Juice CRANBERRY_JUICE = new Juice(ItemNamesV2.CRANBERRY_JUICE, CRANBERRY);
    public static final Juice GRAPE_JUICE = new Juice(ItemNamesV2.GRAPE_JUICE, GRAPE);
    public static final Juice MELON_JUICE = new Juice(ItemNamesV2.MELON_JUICE, VanillaCrops.MELON);
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
    public static Item PAPRIKA; // TODO need recipe to make paprika in future update
    public static Item SALT;

    // secondary ingredients?
    public static Item OLIVE_OIL;
    public static Item CHEESE;
    public static Item FLOUR;
    public static Item BUTTER;
    public static Item NOODLE;
    public static Item TOFU;
    public static Item CHOCOLATE;
    public static Item TORTILLA;
    public static Item SOY_SAUCE;
    public static Item DOUGH;
    public static Item RAVIOLI;
    public static Item SALSA;
    public static Item ARTICHOKE_DIP;
    public static Item PEPPERONI;

    // drinks
    public static Item COFFEE;
    public static Item LEMONADE;
    public static Item LIMEADE;
    public static Item SOY_MILK;

    public static Item KALE_SMOOTHIE;
    public static Item FRUIT_SMOOTHIE;

    public static Item CHOCOLATE_MILKSHAKE;

    public static Item BEER;
    public static Item WINE;
    public static Item MEAD;
    public static Item RUM;
    public static Item PUMPKIN_SPICE_LATTE;

    // snacks?
    public static Item BEEF_JERKY;
    public static Item PORK_JERKY;
    public static Item KALE_CHIPS;
    public static Item POTATO_CHIPS;
    public static Item STEAMED_RICE;
    public static Item FRENCH_FRIES;
    public static Item SWEET_POTATO_FRIES;
    public static Item ONION_RINGS;
    public static Item DOUGHNUT;
    public static Item CUCUMBER_SALAD;
    public static Item CAESAR_SALAD;
    public static Item LEAFY_SALAD;
    public static Item FRUIT_SALAD;
    public static Item VEGGIE_SALAD;
    public static Item PORK_AND_BEANS;
    public static Item OATMEAL;
    public static Item LEEK_SOUP;
    public static Item YOGHURT;
    public static Item SAUCY_CHIPS;
    public static Item ROASTED_NUTS;
    public static Item TRAIL_MIX;
    public static Item PROTEIN_BAR;
    public static Item NOUGAT;

    // breakfast
    public static Item SCRAMBLED_EGGS;
    public static Item BUTTERED_TOAST;
    public static Item TOAST_WITH_JAM;


    // meals
    public static Item HAM_SANDWICH;
    public static Item PEANUT_BUTTER_AND_JAM;
    public static Item BLT;
    public static Item GRILLED_CHEESE;
    public static Item TUNA_SANDWICH;
    public static Item CHEESEBURGER;
    public static Item HAMBURGER;
    public static Item TOFUBURGER;
    public static Item PIZZA;
    public static Item SUPREME_PIZZA;
    public static Item CHEESE_PIZZA;
    public static Item PINEAPPLE_PEPPERONI_PIZZA;
    public static Item LEMON_CHICKEN;
    public static Item FRIED_CHICKEN;
    public static Item CHICKEN_AND_NOODLES;
    public static Item CHICKEN_AND_DUMPLINGS;
    public static Item TOFU_AND_DUMPLINGS;
    public static Item SPAGHETTI_SQUASH;
    public static Item CHICKEN_AND_RICE;
    public static Item TACO;
    public static Item SUSHI;
    public static Item EGG_ROLL;
    public static Item CASHEW_CHICKEN;

    // desert block?
    //public static final Item coffeeCake;
    //public static final Item chocolateCake;
    //public static final Item strawberryShortCake;
    //public static final Item carrotCake;
    //public static final Item turtleCake;

    // desert item
    public static Item YAM_JAM;
    public static Item BANANA_CREAM_PIE;
    public static Item CANDY_CORN;
    public static Item RUM_RAISIN_ICE_CREAM;
    public static Item CHEESE_CAKE;
    public static Item BROWNIES;
    public static Item SNICKER_DOODLE;
    public static Item BANANA_NUT_BREAD;
    public static Item CANDIED_NUTS;
    public static Item ALMOND_BRITTLE;
    public static Item OATMEAL_COOKIE;
    public static Item NUTTY_COOKIE;
    //public static final Item praline = new Item(createGroup().food(EDIBLE_5));

    public static Item BURRITO;
    public static Item TOSTADA;
    public static Item HORCHATA;
    public static Item CARNITAS;
    public static Item FAJITAS;
    public static Item ENCHILADA;
    public static Item CHURROS;
    public static Item TAMALES;
    public static Item TRES_LECHE_CAKE;
    public static Item STUFFED_POBLANOS;
    public static Item CHILI_RELLENO;
    public static Item CREMA;
    public static Item REFRIED_BEANS;
    public static Item CHIMICHANGA;
    public static Item QUESADILLA;

    public static Item CORN_HUSK;
    public static Item WHIPPING_CREAM;

    // 1.4.0
    public static Item SHEPHERDS_PIE;
    public static Item BEEF_WELLINGTON;
    public static Item FISH_AND_CHIPS;
    public static Item ETON_MESS;
    public static Item TEA;
    public static Item CORNISH_PASTY;
    public static Item SCONES;
    public static Item FIGGY_PUDDING;
    public static Item TREACLE_TART;
    public static Item STICKY_TOFFEE_PUDDING;
    public static Item TRIFLE;
    public static Item WATER_BOTTLE;
    public static Item MILK_BOTTLE;

    // 1.7.0
    public static Item AJVAR;
    public static Item AJVAR_TOAST;
    public static Item AVOCADO_TOAST;
    public static Item BEEF_STEW;
    public static Item BEEF_STIR_FRY;
    public static Item BUTTERED_GREEN_BEANS;
    public static Item CHEESY_ASPARAGUS;
    public static Item CHOCOLATE_ICE_CREAM;
    public static Item EGGPLANT_PARMESAN;
    public static Item FRUIT_CAKE;
    public static Item GRILLED_EGGPLANT;
    public static Item KIWI_SORBET;
    public static Item LEMON_COCONUT_BAR;
    public static Item NETHER_WART_STEW;
    public static Item PEANUT_BUTTER;
    public static Item PEANUT_BUTTER_W_CELERY;
    public static Item POTATO_SOUP;
    public static Item RATATOUILLE;
    public static Item RAW_BACON;
    public static Item RHUBARB_CRISP;
    public static Item ROASTED_ASPARAGUS;
    public static Item ROASTED_RADISHES;
    public static Item ROASTED_SQUASH;
    public static Item ROASTED_TURNIPS;
    public static Item STEAMED_BROCCOLI;
    public static Item STEAMED_GREEN_BEANS;
    public static Item STIR_FRY;
    public static Item STUFFED_ARTICHOKE;
    public static Item TOAST_SANDWICH;

    // 2.0.0
    public static Item ROASTED_PUMPKIN_SEEDS;
    public static Item ROASTED_SUNFLOWER_SEEDS;
    public static Item PUMPKIN_BARS;
    public static Item CORN_BREAD;
    public static Item PUMPKIN_SOUP;
    public static Item MERINGUE;
    public static Item CABBAGE_ROLL;
    public static Item BORSCHT;
    public static Item GOULASH;
    public static Item BEETROOT_SALAD;
    public static Item CANDIED_KUMQUATS;
    public static Item STEAMED_CRAB;
    public static Item SEA_LETTUCE;
    public static Item DEEP_FRIED_SHRIMP;
    public static Item TUNA_ROLL;
    public static Item FRIED_CALAMARI;
    public static Item CRAB_LEGS;
    public static Item STEAMED_CLAMS;
    public static Item GRILLED_OYSTERS;
    public static Item ANCHOVY_PIZZA;
    public static Item MASHED_POTATOES;

    // 2.1.0
    public static Item BAKED_CREPES;
    public static Item CINNAMON_ROLL; // 3
    public static Item CROQUE_MADAME;
    public static Item CROQUE_MONSIEUR;
    public static Item DAUPHINE_POTATOES;
    public static Item FRIED_FROG_LEGS;
    public static Item FROG_LEGS;
    public static Item GROUND_PORK;
    public static Item HASHED_BROWN;
    public static Item MACARON;
    public static Item QUICHE;
    public static Item SAUSAGE;
    public static Item SUNNY_SIDE_EGGS;
    public static Item SWEET_CREPES;
    public static Item THE_BIG_BREAKFAST;

    // V-3.0.0
    /*public static final Item CARROT_CAKE;
    public static final Item PICKLED_CUCUMBER;
    public static final Item PICKLED_BEETS;
    public static final Item PICKLED_RADISH;
    public static final Item PICKLED_GARLIC;
    public static final Item PICKLED_ONIONS;
    public static final Item PICKLED_GINGER;
    public static final Item KIMCHI;
    public static final Item SAUERKRAUT;
    public static final Item PICKLED_ANCHOVIES;
    public static final Item PICKLED_EGGS;

    public static final Item BIBIMBAP;
    public static final Item TTEOKBOKKI;
    public static final Item BIBIM_NENGMYUM;

    public static final Item EGG_FRIED_RICE; // rice, egg, soy sauce, salt,
    public static final Item FRIED_RICE; // rice, soy sauce, <MIXED INGREDIENT (beef, pork, shrimp, tofu)>, salt,
    public static final Item VEGGIE_FRIED_RICE; // rice, soy sauce, green onion, carrot, green beans, salt

    public static final Item SESAME_CHICKEN;
    public static final Item ORANGE_CHICKEN;
    public static final Item PINEAPPLE_CHICKEN;
    public static final Item BEEF_AND_BROCCOLI;
    public static final Item EGG_FU_YUNG;
    public static final Item TERYAKI_CHICKEN;

    public static final Item COOKING_OIL; // crafted by using (avocado/walnut/almond/corn/vegetables/w/e else for oils)
    public static final FarmlandCrop FLAX;*/



    public static Block SALT_ORE_BLOCK;
    public static Item SALT_ORE;

    public static Item GUIDE;

    public static void registerBlocks(RegisterFunction<Block> register) {
        for (Consumer<RegisterFunction<Block>> entry : BLOCK_REGISTER.getEntries()) {
            entry.accept(register);
        }

        SALT_ORE_BLOCK = register.register(createIdentifier(BlockNames.SALT_ORE), () ->new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).strength(0.5F).sound(SoundType.SAND)));
    }


    public static void registerItems(RegisterFunction<Item> register) {
        for (Consumer<RegisterFunction<Item>> entry : ITEM_REGISTER.getEntries()) {
            entry.accept(register);
        }

        PAPRIKA = register.register(createIdentifier(ItemNamesV2.PAPRIKA), () -> new Item(createGroup()));
        SALT = register.register(createIdentifier(ItemNamesV2.SALT), () -> new Item(createGroup()));
        OLIVE_OIL = register.register(createIdentifier(ItemNamesV2.OLIVE_OIL), () -> new Item(createGroup()));
        CHEESE = register.register(createIdentifier(ItemNamesV2.CHEESE), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_3))));
        FLOUR = register.register(createIdentifier(ItemNamesV2.FLOUR), () -> new Item(createGroup()));
        BUTTER = register.register(createIdentifier(ItemNamesV2.BUTTER), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_3))));
        NOODLE = register.register(createIdentifier(ItemNamesV2.NOODLE), () -> new Item(createGroup()));
        TOFU = register.register(createIdentifier(ItemNamesV2.TOFU), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_5))));
        CHOCOLATE = register.register(createIdentifier(ItemNamesV2.CHOCOLATE), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_5))));
        TORTILLA = register.register(createIdentifier(ItemNamesV2.TORTILLA), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_5))));
        SOY_SAUCE = register.register(createIdentifier(ItemNamesV2.SOY_SAUCE), () -> new Item(createGroup()));
        DOUGH = register.register(createIdentifier(ItemNamesV2.DOUGH), () -> new Item(createGroup()));
        RAVIOLI = register.register(createIdentifier(ItemNamesV2.RAVIOLI), () -> new Item(createGroup()));
        SALSA = register.register(createIdentifier(ItemNamesV2.SALSA), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_5))));
        ARTICHOKE_DIP = register.register(createIdentifier(ItemNamesV2.ARTICHOKE_DIP), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_5))));
        PEPPERONI = register.register(createIdentifier(ItemNamesV2.PEPPERONI), () -> new Item(createGroup().food(FoodConstructor.createBuilder(REG_5).meat().build())));
        COFFEE = register.register(createIdentifier(ItemNamesV2.COFFEE), () -> new Drink(createGroup().food(FoodConstructor.createBuilder(REG_5).alwaysEat().build()).craftRemainder(Items.GLASS_BOTTLE)));
        LEMONADE = register.register(createIdentifier(ItemNamesV2.LEMONADE), () -> new Drink(createGroup().food(FoodConstructor.createBuilder(REG_5).alwaysEat().build()).craftRemainder(Items.GLASS_BOTTLE)));
        LIMEADE = register.register(createIdentifier(ItemNamesV2.LIMEADE), () -> new Drink(createGroup().food(FoodConstructor.createBuilder(REG_5).alwaysEat().build()).craftRemainder(Items.GLASS_BOTTLE)));
        SOY_MILK = register.register(createIdentifier(ItemNamesV2.SOY_MILK), () -> new Drink(createGroup().food(FoodConstructor.createBuilder(REG_5).alwaysEat().build()).craftRemainder(Items.GLASS_BOTTLE)));
        KALE_SMOOTHIE = register.register(createIdentifier(ItemNamesV2.KALE_SMOOTHIE), () -> new Drink(createGroup().food(FoodConstructor.createBuilder(REG_14).alwaysEat().build()).craftRemainder(Items.GLASS_BOTTLE)));
        FRUIT_SMOOTHIE = register.register(createIdentifier(ItemNamesV2.FRUIT_SMOOTHIE), () -> new Drink(createGroup().food(FoodConstructor.createBuilder(REG_7).alwaysEat().build()).craftRemainder(Items.GLASS_BOTTLE)));
        CHOCOLATE_MILKSHAKE = register.register(createIdentifier(ItemNamesV2.CHOCOLATE_MILKSHAKE), () -> new Drink(createGroup().food(FoodConstructor.createBuilder(REG_7).alwaysEat().build()).craftRemainder(Items.GLASS_BOTTLE)));
        BEER = register.register(createIdentifier(ItemNamesV2.BEER), () -> new Drink(createGroup().food(FoodConstructor.createBuilder(REG_7).alwaysEat().build()).craftRemainder(Items.GLASS_BOTTLE)));
        WINE = register.register(createIdentifier(ItemNamesV2.WINE), () -> new Drink(createGroup().food(FoodConstructor.createBuilder(REG_7).alwaysEat().build()).craftRemainder(Items.GLASS_BOTTLE)));
        MEAD = register.register(createIdentifier(ItemNamesV2.MEAD), () -> new Drink(createGroup().food(FoodConstructor.createBuilder(REG_7).alwaysEat().build()).craftRemainder(Items.GLASS_BOTTLE)));
        RUM = register.register(createIdentifier(ItemNamesV2.RUM), () -> new Drink(createGroup().food(FoodConstructor.createBuilder(REG_7).alwaysEat().build()).craftRemainder(Items.GLASS_BOTTLE)));
        PUMPKIN_SPICE_LATTE = register.register(createIdentifier(ItemNamesV2.PUMPKIN_SPICE_LATTE), () -> new Drink(createGroup().food(FoodConstructor.createBuilder(REG_14).alwaysEat().build())));
        BEEF_JERKY = register.register(createIdentifier(ItemNamesV2.BEEF_JERKY), () -> new Item(createGroup().food(FoodConstructor.createBuilder(REG_5).meat().build())));
        PORK_JERKY = register.register(createIdentifier(ItemNamesV2.PORK_JERKY), () -> new Item(createGroup().food(FoodConstructor.createBuilder(REG_5).meat().build())));
        KALE_CHIPS = register.register(createIdentifier(ItemNamesV2.KALE_CHIPS), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_5))));
        POTATO_CHIPS = register.register(createIdentifier(ItemNamesV2.POTATO_CHIPS), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_5))));
        STEAMED_RICE = register.register(createIdentifier(ItemNamesV2.STEAMED_RICE), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_5))));
        FRENCH_FRIES = register.register(createIdentifier(ItemNamesV2.FRENCH_FRIES), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_5))));
        SWEET_POTATO_FRIES = register.register(createIdentifier(ItemNamesV2.SWEET_POTATO_FRIES), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_5))));
        ONION_RINGS = register.register(createIdentifier(ItemNamesV2.ONION_RINGS), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_5))));
        DOUGHNUT = register.register(createIdentifier(ItemNamesV2.DOUGHNUT), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_5))));
        CUCUMBER_SALAD = register.register(createIdentifier(ItemNamesV2.CUCUMBER_SALAD), () -> new Soup(createGroup().food(FoodConstructor.createFood(REG_10))));
        CAESAR_SALAD = register.register(createIdentifier(ItemNamesV2.CAESAR_SALAD), () -> new Soup(createGroup().food(FoodConstructor.createFood(REG_10))));
        LEAFY_SALAD = register.register(createIdentifier(ItemNamesV2.LEAFY_SALAD), () -> new Soup(createGroup().food(FoodConstructor.createFood(REG_10))));
        FRUIT_SALAD = register.register(createIdentifier(ItemNamesV2.FRUIT_SALAD), () -> new Soup(createGroup().food(FoodConstructor.createFood(REG_10))));
        VEGGIE_SALAD = register.register(createIdentifier(ItemNamesV2.VEGGIE_SALAD), () -> new Soup(createGroup().food(FoodConstructor.createFood(REG_10))));
        PORK_AND_BEANS = register.register(createIdentifier(ItemNamesV2.PORK_AND_BEANS), () -> new Soup(createGroup().food(FoodConstructor.createFood(REG_10))));
        OATMEAL = register.register(createIdentifier(ItemNamesV2.OATMEAL), () -> new Soup(createGroup().food(FoodConstructor.createFood(REG_7))));
        LEEK_SOUP = register.register(createIdentifier(ItemNamesV2.LEEK_SOUP), () -> new Soup(createGroup().food(FoodConstructor.createFood(REG_7))));
        YOGHURT = register.register(createIdentifier(ItemNamesV2.YOGHURT), () -> new Soup(createGroup().food(FoodConstructor.createFood(REG_5))));
        SAUCY_CHIPS = register.register(createIdentifier(ItemNamesV2.SAUCY_CHIPS), () -> new Soup(createGroup().food(FoodConstructor.createFood(REG_7))));
        ROASTED_NUTS = register.register(createIdentifier(ItemNamesV2.ROASTED_NUTS), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_5))));
        TRAIL_MIX = register.register(createIdentifier(ItemNamesV2.TRAIL_MIX), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        PROTEIN_BAR = register.register(createIdentifier(ItemNamesV2.PROTEIN_BAR), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        NOUGAT = register.register(createIdentifier(ItemNamesV2.NOUGAT), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_7))));
        SCRAMBLED_EGGS = register.register(createIdentifier(ItemNamesV2.SCRAMBLED_EGGS), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_7))));
        BUTTERED_TOAST = register.register(createIdentifier(ItemNamesV2.BUTTERED_TOAST), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_9))));
        TOAST_WITH_JAM = register.register(createIdentifier(ItemNamesV2.TOAST_WITH_JAM), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_9))));
        HAM_SANDWICH = register.register(createIdentifier(ItemNamesV2.HAM_SANDWICH), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        PEANUT_BUTTER_AND_JAM = register.register(createIdentifier(ItemNamesV2.PEANUT_BUTTER_AND_JAM), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        BLT = register.register(createIdentifier(ItemNamesV2.BLT), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        GRILLED_CHEESE = register.register(createIdentifier(ItemNamesV2.GRILLED_CHEESE), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_7))));
        TUNA_SANDWICH = register.register(createIdentifier(ItemNamesV2.TUNA_SANDWICH), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        CHEESEBURGER = register.register(createIdentifier(ItemNamesV2.CHEESEBURGER), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        HAMBURGER = register.register(createIdentifier(ItemNamesV2.HAMBURGER), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        TOFUBURGER = register.register(createIdentifier(ItemNamesV2.TOFUBURGER), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        PIZZA = register.register(createIdentifier(ItemNamesV2.PIZZA), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        SUPREME_PIZZA = register.register(createIdentifier(ItemNamesV2.SUPREME_PIZZA), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_18))));
        CHEESE_PIZZA = register.register(createIdentifier(ItemNamesV2.CHEESE_PIZZA), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_14))));
        PINEAPPLE_PEPPERONI_PIZZA = register.register(createIdentifier(ItemNamesV2.PINEAPPLE_PEPPERONI_PIZZA), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_18))));
        LEMON_CHICKEN = register.register(createIdentifier(ItemNamesV2.LEMON_CHICKEN), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        FRIED_CHICKEN = register.register(createIdentifier(ItemNamesV2.FRIED_CHICKEN), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        CHICKEN_AND_NOODLES = register.register(createIdentifier(ItemNamesV2.CHICKEN_AND_NOODLES), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        CHICKEN_AND_DUMPLINGS = register.register(createIdentifier(ItemNamesV2.CHICKEN_AND_DUMPLINGS), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        TOFU_AND_DUMPLINGS = register.register(createIdentifier(ItemNamesV2.TOFU_AND_DUMPLINGS), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_14))));
        SPAGHETTI_SQUASH = register.register(createIdentifier(ItemNamesV2.SPAGHETTI_SQUASH), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        CHICKEN_AND_RICE = register.register(createIdentifier(ItemNamesV2.CHICKEN_AND_RICE), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        TACO = register.register(createIdentifier(ItemNamesV2.TACO), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        SUSHI = register.register(createIdentifier(ItemNamesV2.SUSHI), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        EGG_ROLL = register.register(createIdentifier(ItemNamesV2.EGG_ROLL), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        CASHEW_CHICKEN = register.register(createIdentifier(ItemNamesV2.CASHEW_CHICKEN), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_14))));
        YAM_JAM = register.register(createIdentifier(ItemNamesV2.YAM_JAM), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_14))));
        BANANA_CREAM_PIE = register.register(createIdentifier(ItemNamesV2.BANANA_CREAM_PIE), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_14))));
        CANDY_CORN = register.register(createIdentifier(ItemNamesV2.CANDY_CORN), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_5))));
        RUM_RAISIN_ICE_CREAM = register.register(createIdentifier(ItemNamesV2.RUM_RAISIN_ICE_CREAM), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_14))));
        CHEESE_CAKE = register.register(createIdentifier(ItemNamesV2.CHEESE_CAKE), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_14))));
        BROWNIES = register.register(createIdentifier(ItemNamesV2.BROWNIES), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        SNICKER_DOODLE = register.register(createIdentifier(ItemNamesV2.SNICKER_DOODLE), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_7))));
        BANANA_NUT_BREAD = register.register(createIdentifier(ItemNamesV2.BANANA_NUT_BREAD), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        CANDIED_NUTS = register.register(createIdentifier(ItemNamesV2.CANDIED_NUTS), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_5))));
        ALMOND_BRITTLE = register.register(createIdentifier(ItemNamesV2.ALMOND_BRITTLE), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_7))));
        OATMEAL_COOKIE = register.register(createIdentifier(ItemNamesV2.RAISIN_OATMEAL_COOKIE), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_5))));
        NUTTY_COOKIE = register.register(createIdentifier(ItemNamesV2.NUTTY_COOKIE), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_5))));
        BURRITO = register.register(createIdentifier(ItemNamesV2.BURRITO), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        TOSTADA = register.register(createIdentifier(ItemNamesV2.TOSTADA), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        HORCHATA = register.register(createIdentifier(ItemNamesV2.HORCHATA), () -> new Drink(createGroup().food(FoodConstructor.createFood(REG_10))));
        CARNITAS = register.register(createIdentifier(ItemNamesV2.CARNITAS), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        FAJITAS = register.register(createIdentifier(ItemNamesV2.FAJITAS), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        ENCHILADA = register.register(createIdentifier(ItemNamesV2.ENCHILADA), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        CHURROS = register.register(createIdentifier(ItemNamesV2.CHURROS), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_5))));
        TAMALES = register.register(createIdentifier(ItemNamesV2.TAMALES), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_14))));
        TRES_LECHE_CAKE = register.register(createIdentifier(ItemNamesV2.TRES_LECHE_CAKE), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_18))));
        STUFFED_POBLANOS = register.register(createIdentifier(ItemNamesV2.STUFFED_POBLANOS), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_14))));
        CHILI_RELLENO = register.register(createIdentifier(ItemNamesV2.CHILI_RELLENO), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_14))));
        CREMA = register.register(createIdentifier(ItemNamesV2.CREMA), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_3))));
        REFRIED_BEANS = register.register(createIdentifier(ItemNamesV2.REFRIED_BEANS), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_7))));
        CHIMICHANGA = register.register(createIdentifier(ItemNamesV2.CHIMICHANGA), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_14))));
        QUESADILLA = register.register(createIdentifier(ItemNamesV2.QUESADILLA), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        CORN_HUSK = register.register(createIdentifier(ItemNamesV2.CORN_HUSK), () -> new Item(createGroup()));
        WHIPPING_CREAM = register.register(createIdentifier(ItemNamesV2.WHIPPING_CREAM), () -> new Item(createGroup()));
        SHEPHERDS_PIE = register.register(createIdentifier(ItemNamesV2.SHEPHERDS_PIE), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_18))));
        BEEF_WELLINGTON = register.register(createIdentifier(ItemNamesV2.BEEF_WELLINGTON), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_18))));
        FISH_AND_CHIPS = register.register(createIdentifier(ItemNamesV2.FISH_AND_CHIPS), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        ETON_MESS = register.register(createIdentifier(ItemNamesV2.ETON_MESS), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        TEA = register.register(createIdentifier(ItemNamesV2.TEA), () -> new Drink(createGroup().food(FoodConstructor.createBuilder(REG_5).alwaysEat().build())));
        CORNISH_PASTY = register.register(createIdentifier(ItemNamesV2.CORNISH_PASTY), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        SCONES = register.register(createIdentifier(ItemNamesV2.SCONES), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        FIGGY_PUDDING = register.register(createIdentifier(ItemNamesV2.FIGGY_PUDDING), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        TREACLE_TART = register.register(createIdentifier(ItemNamesV2.TREACLE_TART), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        STICKY_TOFFEE_PUDDING = register.register(createIdentifier(ItemNamesV2.STICKY_TOFFEE_PUDDING), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_14))));
        TRIFLE = register.register(createIdentifier(ItemNamesV2.TRIFLE), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_14))));
        WATER_BOTTLE = register.register(createIdentifier(ItemNamesV2.WATER_BOTTLE), () -> new Item(createGroup()));
        MILK_BOTTLE = register.register(createIdentifier(ItemNamesV2.MILK_BOTTLE), () -> new Item(createGroup()));
        AJVAR = register.register(createIdentifier(ItemNamesV2.AJVAR), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_7))));
        AJVAR_TOAST = register.register(createIdentifier(ItemNamesV2.AJVAR_TOAST), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_14))));
        AVOCADO_TOAST = register.register(createIdentifier(ItemNamesV2.AVOCADO_TOAST), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        BEEF_STEW = register.register(createIdentifier(ItemNamesV2.BEEF_STEW), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_14))));
        BEEF_STIR_FRY = register.register(createIdentifier(ItemNamesV2.BEEF_STIR_FRY), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_14))));
        BUTTERED_GREEN_BEANS = register.register(createIdentifier(ItemNamesV2.BUTTERED_GREEN_BEANS), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        CHEESY_ASPARAGUS = register.register(createIdentifier(ItemNamesV2.CHEESY_ASPARAGUS), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        CHOCOLATE_ICE_CREAM = register.register(createIdentifier(ItemNamesV2.CHOCOLATE_ICE_CREAM), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_14))));
        EGGPLANT_PARMESAN = register.register(createIdentifier(ItemNamesV2.EGGPLANT_PARMESAN), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_18))));
        FRUIT_CAKE = register.register(createIdentifier(ItemNamesV2.FRUIT_CAKE), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        GRILLED_EGGPLANT = register.register(createIdentifier(ItemNamesV2.GRILLED_EGGPLANT), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        KIWI_SORBET = register.register(createIdentifier(ItemNamesV2.KIWI_SORBET), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_7))));
        LEMON_COCONUT_BAR = register.register(createIdentifier(ItemNamesV2.LEMON_COCONUT_BAR), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_7))));
        NETHER_WART_STEW = register.register(createIdentifier(ItemNamesV2.NETHER_WART_STEW), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_7))));
        PEANUT_BUTTER = register.register(createIdentifier(ItemNamesV2.PEANUT_BUTTER), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_5))));
        PEANUT_BUTTER_W_CELERY = register.register(createIdentifier(ItemNamesV2.PEANUT_BUTTER_W_CELERY), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_7))));
        POTATO_SOUP = register.register(createIdentifier(ItemNamesV2.POTATO_SOUP), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        RATATOUILLE = register.register(createIdentifier(ItemNamesV2.RATATOUILLE), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_18))));
        RAW_BACON = register.register(createIdentifier(ItemNamesV2.RAW_BACON), () -> new Item(createGroup().food(FoodConstructor.createBuilder(REG_1).meat().build())));
        RHUBARB_CRISP = register.register(createIdentifier(ItemNamesV2.RHUBARB_CRISP), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        ROASTED_ASPARAGUS = register.register(createIdentifier(ItemNamesV2.ROASTED_ASPARAGUS), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_7))));
        ROASTED_RADISHES = register.register(createIdentifier(ItemNamesV2.ROASTED_RADISHES), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_7))));
        ROASTED_SQUASH = register.register(createIdentifier(ItemNamesV2.ROASTED_SQUASH), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_7))));
        ROASTED_TURNIPS = register.register(createIdentifier(ItemNamesV2.ROASTED_TURNIPS), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_7))));
        STEAMED_BROCCOLI = register.register(createIdentifier(ItemNamesV2.STEAMED_BROCCOLI), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_7))));
        STEAMED_GREEN_BEANS = register.register(createIdentifier(ItemNamesV2.STEAMED_GREEN_BEANS), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_7))));
        STIR_FRY = register.register(createIdentifier(ItemNamesV2.STIR_FRY), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_12))));
        STUFFED_ARTICHOKE = register.register(createIdentifier(ItemNamesV2.STUFFED_ARTICHOKE), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_18))));
        TOAST_SANDWICH = register.register(createIdentifier(ItemNamesV2.TOAST_SANDWICH), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        ROASTED_PUMPKIN_SEEDS = register.register(createIdentifier(ItemNamesV2.ROASTED_PUMPKIN_SEEDS), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_4))));
        ROASTED_SUNFLOWER_SEEDS = register.register(createIdentifier(ItemNamesV2.ROASTED_SUNFLOWER_SEEDS), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_4))));
        PUMPKIN_BARS = register.register(createIdentifier(ItemNamesV2.PUMPKIN_BARS), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_6))));
        CORN_BREAD = register.register(createIdentifier(ItemNamesV2.CORN_BREAD), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_5))));
        PUMPKIN_SOUP = register.register(createIdentifier(ItemNamesV2.PUMPKIN_SOUP), () -> new Soup(createGroup().food(FoodConstructor.createFood(REG_10))));
        MERINGUE = register.register(createIdentifier(ItemNamesV2.MERINGUE), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_6))));
        CABBAGE_ROLL = register.register(createIdentifier(ItemNamesV2.CABBAGE_ROLL), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_14))));
        BORSCHT = register.register(createIdentifier(ItemNamesV2.BORSCHT), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_12))));
        GOULASH = register.register(createIdentifier(ItemNamesV2.GOULASH), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_16))));
        BEETROOT_SALAD = register.register(createIdentifier(ItemNamesV2.BEETROOT_SALAD), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        CANDIED_KUMQUATS = register.register(createIdentifier(ItemNamesV2.CANDIED_KUMQUATS), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_6))));
        STEAMED_CRAB = register.register(createIdentifier(ItemNamesV2.STEAMED_CRAB), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_6))));
        SEA_LETTUCE = register.register(createIdentifier(ItemNamesV2.SEA_LETTUCE), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_1))));
        DEEP_FRIED_SHRIMP = register.register(createIdentifier(ItemNamesV2.DEEP_FRIED_SHRIMP), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        TUNA_ROLL = register.register(createIdentifier(ItemNamesV2.TUNA_ROLL), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        FRIED_CALAMARI = register.register(createIdentifier(ItemNamesV2.FRIED_CALAMARI), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_10))));
        CRAB_LEGS = register.register(createIdentifier(ItemNamesV2.CRAB_LEGS), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_11))));
        STEAMED_CLAMS = register.register(createIdentifier(ItemNamesV2.STEAMED_CLAMS), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_11))));
        GRILLED_OYSTERS = register.register(createIdentifier(ItemNamesV2.GRILLED_OYSTERS), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_11))));
        ANCHOVY_PIZZA = register.register(createIdentifier(ItemNamesV2.ANCHOVY_PIZZA), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_15))));
        MASHED_POTATOES = register.register(createIdentifier(ItemNamesV2.MASHED_POTATOES), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_9))));

        BAKED_CREPES = register.register(createIdentifier(ItemNamesV2.BAKED_CREPES), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_12))));
        CINNAMON_ROLL = register.register(createIdentifier(ItemNamesV2.CINNAMON_ROLL), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_8))));
        CROQUE_MADAME = register.register(createIdentifier(ItemNamesV2.CROQUE_MADAME), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_14))));
        CROQUE_MONSIEUR = register.register(createIdentifier(ItemNamesV2.CROQUE_MONSIEUR), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_13))));
        DAUPHINE_POTATOES = register.register(createIdentifier(ItemNamesV2.DAUPHINE_POTATOES), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_12))));
        FRIED_FROG_LEGS = register.register(createIdentifier(ItemNamesV2.FRIED_FROG_LEGS), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_6))));
        FROG_LEGS = register.register(createIdentifier(ItemNamesV2.FROG_LEGS), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_3))));
        GROUND_PORK = register.register(createIdentifier(ItemNamesV2.GROUND_PORK), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_1))));
        HASHED_BROWN = register.register(createIdentifier(ItemNamesV2.HASHED_BROWN), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_2))));
        MACARON = register.register(createIdentifier(ItemNamesV2.MACARON), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_5))));
        QUICHE = register.register(createIdentifier(ItemNamesV2.QUICHE), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_12))));
        SAUSAGE = register.register(createIdentifier(ItemNamesV2.SAUSAGE), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_3))));
        SUNNY_SIDE_EGGS = register.register(createIdentifier(ItemNamesV2.SUNNY_SIDE_EGGS), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_5))));
        SWEET_CREPES = register.register(createIdentifier(ItemNamesV2.SWEET_CREPES), () -> new Item(createGroup().food(FoodConstructor.createFood(REG_8))));
        THE_BIG_BREAKFAST = register.register(createIdentifier(ItemNamesV2.THE_BIG_BREAKFAST), () -> new ReferenceItem(createGroup().food(FoodConstructor.createFood(REG_20)),
                Component.literal("Patricia! Daddy want the Big Breakfast").setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY))));

        SALT_ORE = register.register(createIdentifier(ItemNamesV2.SALT_ORE), () ->  new ItemNameBlockItem(SALT_ORE_BLOCK, createGroup()));
    }

    public static Stream<Item> createCropStream() {
        return Stream.concat(
                Arrays.stream(FarmlandCrop.FARMLAND_CROPS.toArray(new FarmlandCrop[0])),
                Stream.concat(Arrays.stream(TreeCrop.TREE_CROPS.toArray(new TreeCrop[0])), Arrays.stream(Tree.copy().toArray(new Tree[0])))
        ).map(ItemLike::asItem);
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<FC, ?>> register(ResourceLocation id, ConfiguredFeature<FC, F> feature) {
        return Holder.direct(feature);
    }
}
