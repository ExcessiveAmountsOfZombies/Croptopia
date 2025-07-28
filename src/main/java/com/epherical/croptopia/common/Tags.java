package com.epherical.croptopia.common;

import com.epherical.croptopia.CroptopiaMod;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class Tags {

    private static final List<TagKey<Biome>> CROPTOPIA_BIOME_TAGS = new ArrayList<>();

    public static final TagKey<Biome> HAS_ARTICHOKE = create("has_crop/" + ItemNamesV2.ARTICHOKE);
    public static final TagKey<Biome> HAS_ASPARAGUS = create("has_crop/" + ItemNamesV2.ASPARAGUS);
    public static final TagKey<Biome> HAS_BARLEY = create("has_crop/" + ItemNamesV2.BARLEY);
    public static final TagKey<Biome> HAS_BASIL = create("has_crop/" + ItemNamesV2.BASIL);
    public static final TagKey<Biome> HAS_BELLPEPPER = create("has_crop/" + ItemNamesV2.BELLPEPPER);
    public static final TagKey<Biome> HAS_BLACKBEAN = create("has_crop/" + ItemNamesV2.BLACKBEAN);
    public static final TagKey<Biome> HAS_BLACKBERRY = create("has_crop/" + ItemNamesV2.BLACKBERRY);
    public static final TagKey<Biome> HAS_BLUEBERRY = create("has_crop/" + ItemNamesV2.BLUEBERRY);
    public static final TagKey<Biome> HAS_BROCCOLI = create("has_crop/" + ItemNamesV2.BROCCOLI);
    public static final TagKey<Biome> HAS_CABBAGE = create("has_crop/" + ItemNamesV2.CABBAGE);
    public static final TagKey<Biome> HAS_CANTALOUPE = create("has_crop/" + ItemNamesV2.CANTALOUPE);
    public static final TagKey<Biome> HAS_CAULIFLOWER = create("has_crop/" + ItemNamesV2.CAULIFLOWER);
    public static final TagKey<Biome> HAS_CELERY = create("has_crop/" + ItemNamesV2.CELERY);
    public static final TagKey<Biome> HAS_CHILE_PEPPER = create("has_crop/" + ItemNamesV2.CHILE_PEPPER);
    public static final TagKey<Biome> HAS_COFFEE_BEANS = create("has_crop/" + ItemNamesV2.COFFEE_BEANS);
    public static final TagKey<Biome> HAS_CORN = create("has_crop/" + ItemNamesV2.CORN);
    public static final TagKey<Biome> HAS_CRANBERRY = create("has_crop/" + ItemNamesV2.CRANBERRY);
    public static final TagKey<Biome> HAS_CUCUMBER = create("has_crop/" + ItemNamesV2.CUCUMBER);
    public static final TagKey<Biome> HAS_CURRANT = create("has_crop/" + ItemNamesV2.CURRANT);
    public static final TagKey<Biome> HAS_EGGPLANT = create("has_crop/" + ItemNamesV2.EGGPLANT);
    public static final TagKey<Biome> HAS_ELDERBERRY = create("has_crop/" + ItemNamesV2.ELDERBERRY);
    public static final TagKey<Biome> HAS_GARLIC = create("has_crop/" + ItemNamesV2.GARLIC);
    public static final TagKey<Biome> HAS_GINGER = create("has_crop/" + ItemNamesV2.GINGER);
    public static final TagKey<Biome> HAS_GRAPE = create("has_crop/" + ItemNamesV2.GRAPE);
    public static final TagKey<Biome> HAS_GREENBEAN = create("has_crop/" + ItemNamesV2.GREENBEAN);
    public static final TagKey<Biome> HAS_GREENONION = create("has_crop/" + ItemNamesV2.GREENONION);
    public static final TagKey<Biome> HAS_HONEYDEW = create("has_crop/" + ItemNamesV2.HONEYDEW);
    public static final TagKey<Biome> HAS_HOPS = create("has_crop/" + ItemNamesV2.HOPS);
    public static final TagKey<Biome> HAS_KALE = create("has_crop/" + ItemNamesV2.KALE);
    public static final TagKey<Biome> HAS_KIWI = create("has_crop/" + ItemNamesV2.KIWI);
    public static final TagKey<Biome> HAS_LEEK = create("has_crop/" + ItemNamesV2.LEEK);
    public static final TagKey<Biome> HAS_LETTUCE = create("has_crop/" + ItemNamesV2.LETTUCE);
    public static final TagKey<Biome> HAS_MUSTARD = create("has_crop/" + ItemNamesV2.MUSTARD);
    public static final TagKey<Biome> HAS_OAT = create("has_crop/" + ItemNamesV2.OAT);
    public static final TagKey<Biome> HAS_OLIVE = create("has_crop/" + ItemNamesV2.OLIVE);
    public static final TagKey<Biome> HAS_ONION = create("has_crop/" + ItemNamesV2.ONION);
    public static final TagKey<Biome> HAS_PEANUT = create("has_crop/" + ItemNamesV2.PEANUT);
    public static final TagKey<Biome> HAS_PEPPER = create("has_crop/" + ItemNamesV2.PEPPER);
    public static final TagKey<Biome> HAS_PINEAPPLE = create("has_crop/" + ItemNamesV2.PINEAPPLE);
    public static final TagKey<Biome> HAS_RADISH = create("has_crop/" + ItemNamesV2.RADISH);
    public static final TagKey<Biome> HAS_RASPBERRY = create("has_crop/" + ItemNamesV2.RASPBERRY);
    public static final TagKey<Biome> HAS_RHUBARB = create("has_crop/" + ItemNamesV2.RHUBARB);
    public static final TagKey<Biome> HAS_RICE = create("has_crop/" + ItemNamesV2.RICE);
    public static final TagKey<Biome> HAS_RUTABAGA = create("has_crop/" + ItemNamesV2.RUTABAGA);
    public static final TagKey<Biome> HAS_SAGUARO = create("has_crop/" + ItemNamesV2.SAGUARO);
    public static final TagKey<Biome> HAS_SOYBEAN = create("has_crop/" + ItemNamesV2.SOYBEAN);
    public static final TagKey<Biome> HAS_SPINACH = create("has_crop/" + ItemNamesV2.SPINACH);
    public static final TagKey<Biome> HAS_SQUASH = create("has_crop/" + ItemNamesV2.SQUASH);
    public static final TagKey<Biome> HAS_STRAWBERRY = create("has_crop/" + ItemNamesV2.STRAWBERRY);
    public static final TagKey<Biome> HAS_SWEETPOTATO = create("has_crop/" + ItemNamesV2.SWEETPOTATO);
    public static final TagKey<Biome> HAS_TEA_LEAVES = create("has_crop/" + ItemNamesV2.TEA_LEAVES);
    public static final TagKey<Biome> HAS_TOMATILLO = create("has_crop/" + ItemNamesV2.TOMATILLO);
    public static final TagKey<Biome> HAS_TOMATO = create("has_crop/" + ItemNamesV2.TOMATO);
    public static final TagKey<Biome> HAS_TURMERIC = create("has_crop/" + ItemNamesV2.TURMERIC);
    public static final TagKey<Biome> HAS_TURNIP = create("has_crop/" + ItemNamesV2.TURNIP);
    public static final TagKey<Biome> HAS_VANILLA = create("has_crop/" + ItemNamesV2.VANILLA);
    public static final TagKey<Biome> HAS_YAM = create("has_crop/" + ItemNamesV2.YAM);
    public static final TagKey<Biome> HAS_ZUCCHINI = create("has_crop/" + ItemNamesV2.ZUCCHINI);

    public static final TagKey<Biome> HAS_ALMOND = create("has_tree/" + ItemNamesV2.ALMOND);
    public static final TagKey<Biome> HAS_APPLE = create("has_tree/" + ItemNamesV2.APPLE);
    public static final TagKey<Biome> HAS_APRICOT = create("has_tree/" + ItemNamesV2.APRICOT);
    public static final TagKey<Biome> HAS_AVOCADO = create("has_tree/" + ItemNamesV2.AVOCADO);
    public static final TagKey<Biome> HAS_BANANA = create("has_tree/" + ItemNamesV2.BANANA);
    public static final TagKey<Biome> HAS_CASHEW = create("has_tree/" + ItemNamesV2.CASHEW);
    public static final TagKey<Biome> HAS_CHERRY = create("has_tree/" + ItemNamesV2.CHERRY);
    public static final TagKey<Biome> HAS_COCONUT = create("has_tree/" + ItemNamesV2.COCONUT);
    public static final TagKey<Biome> HAS_DATE = create("has_tree/" + ItemNamesV2.DATE);
    public static final TagKey<Biome> HAS_DRAGONFRUIT = create("has_tree/" + ItemNamesV2.DRAGONFRUIT);
    public static final TagKey<Biome> HAS_FIG = create("has_tree/" + ItemNamesV2.FIG);
    public static final TagKey<Biome> HAS_GRAPEFRUIT = create("has_tree/" + ItemNamesV2.GRAPEFRUIT);
    public static final TagKey<Biome> HAS_KUMQUAT = create("has_tree/" + ItemNamesV2.KUMQUAT);
    public static final TagKey<Biome> HAS_LEMON = create("has_tree/" + ItemNamesV2.LEMON);
    public static final TagKey<Biome> HAS_LIME = create("has_tree/" + ItemNamesV2.LIME);
    public static final TagKey<Biome> HAS_MANGO = create("has_tree/" + ItemNamesV2.MANGO);
    public static final TagKey<Biome> HAS_NECTARINE = create("has_tree/" + ItemNamesV2.NECTARINE);
    public static final TagKey<Biome> HAS_NUTMEG = create("has_tree/" + ItemNamesV2.NUTMEG);
    public static final TagKey<Biome> HAS_ORANGE = create("has_tree/" + ItemNamesV2.ORANGE);
    public static final TagKey<Biome> HAS_PEACH = create("has_tree/" + ItemNamesV2.PEACH);
    public static final TagKey<Biome> HAS_PEAR = create("has_tree/" + ItemNamesV2.PEAR);
    public static final TagKey<Biome> HAS_PECAN = create("has_tree/" + ItemNamesV2.PECAN);
    public static final TagKey<Biome> HAS_PERSIMMON = create("has_tree/" + ItemNamesV2.PERSIMMON);
    public static final TagKey<Biome> HAS_PLUM = create("has_tree/" + ItemNamesV2.PLUM);
    public static final TagKey<Biome> HAS_STARFRUIT = create("has_tree/" + ItemNamesV2.STARFRUIT);
    public static final TagKey<Biome> HAS_WALNUT = create("has_tree/" + ItemNamesV2.WALNUT);
    public static final TagKey<Biome> HAS_CINNAMON = create("has_tree/" + ItemNamesV2.CINNAMON);

    public static final TagKey<Biome> HAS_SALT = create("has_salt/" + ItemNamesV2.SALT);


    public static final TagKey<Block> FARMLAND = create("c", "farmland");
    public static final TagKey<Block> FARMLANDS = create("c", "farmlands");

    private static TagKey<Block> create(String nm, String key) {
        return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(nm, key));
    }

    private static TagKey<Biome> create(String key) {
        TagKey<Biome> biomeKey = TagKey.create(Registries.BIOME, CroptopiaMod.createIdentifier(key));
        CROPTOPIA_BIOME_TAGS.add(biomeKey);
        return biomeKey;
    }

    public static List<TagKey<Biome>> getCroptopiaBiomeTags() {
        return ImmutableList.copyOf(CROPTOPIA_BIOME_TAGS);
    }
}
