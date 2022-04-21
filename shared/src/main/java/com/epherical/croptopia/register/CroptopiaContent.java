package com.epherical.croptopia.register;

import com.epherical.croptopia.CroptopiaMod;
import com.epherical.croptopia.common.ItemNamesV2;
import com.epherical.croptopia.util.RegisterFunction;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

import static com.epherical.croptopia.util.FoodConstructor.REG_1;
import static com.epherical.croptopia.util.FoodConstructor.REG_3;

public class CroptopiaContent {

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
    public static final FarmlandCrop COFFEE_BEANS = new FarmlandCrop(/*ItemNamesV2.COFFEE_BEANS*/ "coffee", false, TagCategory.CROPS, REG_3, BiomeTags.IS_MOUNTAIN);
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
    public static final FarmlandCrop TEA_LEAVES = new FarmlandCrop(/*ItemNamesV2.TEA_LEAVES*/ "tea", false, TagCategory.CROPS, null, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop TOMATILLO = new FarmlandCrop(ItemNamesV2.TOMATILLO, true, TagCategory.VEGETABLES, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop TOMATO = new FarmlandCrop(ItemNamesV2.TOMATO, true, TagCategory.VEGETABLES, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop TURMERIC = new FarmlandCrop(ItemNamesV2.TURMERIC, false, TagCategory.CROPS, null, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop TURNIP = new FarmlandCrop(ItemNamesV2.TURNIP, true, TagCategory.VEGETABLES, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop VANILLA = new FarmlandCrop(ItemNamesV2.VANILLA, false, TagCategory.CROPS, null, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop YAM = new FarmlandCrop(ItemNamesV2.YAM, true, TagCategory.VEGETABLES, REG_3, BiomeTags.IS_MOUNTAIN);
    public static final FarmlandCrop ZUCCHINI = new FarmlandCrop(ItemNamesV2.ZUCCHINI, false, TagCategory.VEGETABLES, REG_3, BiomeTags.IS_MOUNTAIN);

    public static final TreeCrop ALMOND = new TreeCrop(ItemNamesV2.ALMOND, true,  Blocks.DARK_OAK_LOG, Blocks.DARK_OAK_LEAVES, TagCategory.NUTS, REG_3, 4, 3, 0);
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

    public static void registerBlocks(RegisterFunction<Block> register) {
        for (FarmlandCrop farmlandCrop : FarmlandCrop.getFarmlandCrops()) {
            register.register(farmlandCrop.asBlock(), CroptopiaMod.createIdentifier(farmlandCrop.name() + "_crop"));
            CroptopiaMod.cropBlocks.add(farmlandCrop.asBlock());
        }
        for (TreeCrop treeCrop : TreeCrop.getTreeCrops()) {
            register.register(treeCrop.asBlock(), CroptopiaMod.createIdentifier(treeCrop.name() + "_crop"));
            CroptopiaMod.cropBlocks.add(treeCrop.asBlock());
            CroptopiaMod.leafBlocks.add(treeCrop.asBlock());
            treeCrop.setTree(register(CroptopiaMod.createIdentifier(treeCrop.name() + "_tree"), treeCrop.getTreeConfig()));
            register.register(treeCrop.getSaplingBlock(), CroptopiaMod.createIdentifier(treeCrop.name() + "_sapling"));
        }
    }

    public static void registerItems(RegisterFunction<Item> register) {
        for (FarmlandCrop farmlandCrop : FarmlandCrop.getFarmlandCrops()) {
            register.register(farmlandCrop.asItem(), CroptopiaMod.createIdentifier(farmlandCrop.name()));
            if (farmlandCrop.name().equals(ItemNamesV2.VANILLA)) {
                register.register(farmlandCrop.getSeedItem(), CroptopiaMod.createIdentifier(farmlandCrop.name() + "_seeds"));
            } else {
                register.register(farmlandCrop.getSeedItem(), CroptopiaMod.createIdentifier(farmlandCrop.name() + "_seed"));
            }
            CroptopiaMod.cropItems.add(farmlandCrop.asItem());
            CroptopiaMod.seeds.add(farmlandCrop.getSeedItem());
        }
        for (TreeCrop treeCrop : TreeCrop.getTreeCrops()) {
            register.register(treeCrop.asItem(), CroptopiaMod.createIdentifier(treeCrop.name()));
            register.register(treeCrop.getSaplingItem(), CroptopiaMod.createIdentifier(treeCrop.name() + "_sapling"));
        }
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<FC, ?>> register(ResourceLocation id, ConfiguredFeature<FC, F> feature) {
        return badRegister(BuiltinRegistries.CONFIGURED_FEATURE, id, feature);
    }

    public static <V extends T, T> Holder<V> badRegister(Registry<T> registry, ResourceLocation id, V value) {
        //noinspection unchecked
        return BuiltinRegistries.register((Registry<V>) registry, id, value);
    }
}
