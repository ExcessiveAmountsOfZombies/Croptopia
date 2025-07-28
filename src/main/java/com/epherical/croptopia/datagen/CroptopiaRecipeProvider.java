package com.epherical.croptopia.datagen;

import com.epherical.croptopia.common.ItemNamesV2;
import com.epherical.croptopia.common.MiscNames;
import com.epherical.croptopia.register.Content;
import com.epherical.croptopia.register.helpers.FarmlandCrop;
import com.epherical.croptopia.register.helpers.IceCream;
import com.epherical.croptopia.register.helpers.Jam;
import com.epherical.croptopia.register.helpers.Juice;
import com.epherical.croptopia.register.helpers.Pie;
import com.epherical.croptopia.register.helpers.Smoothie;
import com.epherical.croptopia.register.helpers.Tree;
import com.epherical.croptopia.register.helpers.TreeCrop;
import com.epherical.croptopia.util.ItemConvertibleWithPlural;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;

import static com.epherical.croptopia.register.Content.*;
import static net.minecraft.data.recipes.RecipeCategory.FOOD;
import static net.minecraft.data.recipes.RecipeCategory.MISC;
import static net.minecraft.data.recipes.ShapelessRecipeBuilder.shapeless;

public class CroptopiaRecipeProvider extends RecipeProvider {

    public CroptopiaRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }


    @Override
    public void buildRecipes(RecipeOutput exporter) {

        // todo; we need to generate every recipe via this method now.
        //

        generateSeeds(exporter);
        generateSaplings(exporter);
        generateBarkWood(exporter);
        generateJams(exporter);
        generateJuices(exporter);
        generateSmoothies(exporter);
        generateIceCream(exporter);
        generatePie(exporter);
        generateFurnace(exporter);
        generateUtensil(exporter);
        generateMiscShapeless(exporter);
        generateMiscShaped(exporter);
    }

    protected void generateSeeds(RecipeOutput exporter) {
        for (FarmlandCrop crop : FarmlandCrop.copy()) {
            TagKey<Item> tag = independentTag(crop.getPlural());
            shapeless(MISC, crop.getSeedItem())
                    .requires(tag)
                    .unlockedBy("has_" + crop.getLowercaseName(), has(crop))
                    .save(exporter);
        }
    }

    protected void generateSaplings(RecipeOutput exporter) {
        for (TreeCrop crop : TreeCrop.copy()) {
            TagKey<Item> tag = independentTag(crop.getPlural());
            shapeless(MISC, crop.getSaplingItem())
                    .requires(tag).requires(tag).requires(ItemTags.SAPLINGS)
                    .unlockedBy("has_" + crop.getLowercaseName(), has(crop))
                    .save(exporter);
        }
        // Bark saplings come from the leaves, not the crop
    }

    protected void generateBarkWood(RecipeOutput exporter) {
        for (Tree crop : Tree.copy()) {
            ShapedRecipeBuilder.shaped(MISC, crop.getWood())
                    .pattern("##")
                    .pattern("##")
                    .define('#', crop.getLog())
                    .unlockedBy("has_" + crop.getLowercaseName() + "_log", has(crop.getLog()))
                    .save(exporter);
            ShapedRecipeBuilder.shaped(MISC, crop.getStrippedWood())
                    .pattern("##")
                    .pattern("##")
                    .define('#', crop.getStrippedLog())
                    .unlockedBy("has_stripped" + crop.getLowercaseName() + "_log", has(crop.getStrippedLog()))
                    .save(exporter);
        }
    }

    protected void generateJams(RecipeOutput exporter) {
        for (Jam jam : Jam.copy()) {
            TagKey<Item> tag = independentTag(jam.getCrop().getPlural());
            shapeless(MISC, jam)
                    .requires(tag).requires(Items.SUGAR).requires(COOKING_POT)
                    .unlockedBy("has_" + jam.getCrop().getLowercaseName(), has(tag))
                    .save(exporter);
        }
    }

    protected void generateJuices(RecipeOutput exporter) {
        for (Juice juice : Juice.copy()) {
            TagKey<Item> tag = independentTag(juice.getCrop().getPlural());
            shapeless(MISC, juice)
                    .requires(tag).requires(FOOD_PRESS).requires(Items.GLASS_BOTTLE)
                    .unlockedBy("has_" + juice.getCrop().getLowercaseName(), has(tag))
                    .save(exporter);
        }
    }

    protected void generateSmoothies(RecipeOutput exporter) {
        for (Smoothie smoothie : Smoothie.copy()) {
            TagKey<Item> tag = independentTag(smoothie.getCrop().getPlural());
            shapeless(MISC, smoothie)
                    .requires(tag).requires(Items.ICE).requires(independentTag("milks")).requires(Items.GLASS_BOTTLE)
                    .unlockedBy("has_" + smoothie.getCrop().getLowercaseName(), has(tag))
                    .save(exporter);
        }
    }

    protected void generateIceCream(RecipeOutput exporter) {
        for (IceCream iceCream : IceCream.copy()) {
            TagKey<Item> tag = independentTag(iceCream.getCrop().getPlural());
            shapeless(MISC, iceCream)
                    .requires(tag).requires(Items.SUGAR).requires(Items.EGG).requires(independentTag("milks")).requires(COOKING_POT)
                    .unlockedBy("has_" + iceCream.getCrop().getLowercaseName(), has(tag))
                    .save(exporter);
        }
    }

    protected void generatePie(RecipeOutput exporter) {
        for (Pie pie : Pie.copy()) {
            TagKey<Item> tag = independentTag(pie.getCrop().getPlural());
            shapeless(MISC, pie)
                    .requires(tag).requires(Items.SUGAR).requires(Items.EGG).requires(independentTag("flour")).requires(independentTag("doughs")).requires(FRYING_PAN)
                    .unlockedBy("has_" + pie.getCrop().getLowercaseName(), has(tag))
                    .save(exporter);
        }
    }

    protected void offerFoodCookingRecipe(RecipeOutput exporter, ItemLike input, String inputName, ItemLike output, int time, float exp, boolean campFire) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), FOOD, output, exp, time)
                .unlockedBy("has_" + inputName, has(input))
                .save(exporter, "croptopia:" + getItemName(output) + "_from_" + inputName);
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(input), FOOD, output, exp, time / 2)
                .unlockedBy("has_" + inputName, has(input))
                .save(exporter, "croptopia:" + getItemName(output) + "_from_smoking_" + inputName);
        // TODO campfire
    }

    protected void generateFurnace(RecipeOutput exporter) {
        final int time = 200; // default vanilla time
        final float exp = 0.2f; // default vanilla experience
        var cookingList = new ImmutableMap.Builder<ItemConvertibleWithPlural, ItemLike>()
                .put(BLACKBEAN, BAKED_BEANS)
                .put(SWEETPOTATO, BAKED_SWEET_POTATO)
                .put(YAM, BAKED_YAM)
                .put(ANCHOVY, COOKED_ANCHOVY)
                .put(CALAMARI, COOKED_CALAMARI)
                .put(GLOWING_CALAMARI, COOKED_CALAMARI)
                .put(SHRIMP, COOKED_SHRIMP)
                .put(TUNA, COOKED_TUNA)
                .put(CORN, POPCORN)
                .put(GRAPE, RAISINS)
                .build();
        cookingList.forEach((input, output) -> offerFoodCookingRecipe(exporter, input, input.getLowercaseName(), output, time, exp, true));
        // raw bacon is not yet moved
        offerFoodCookingRecipe(exporter, RAW_BACON, ItemNamesV2.RAW_BACON, COOKED_BACON, time, exp, true);
        // now the vanilla ingredients
        offerFoodCookingRecipe(exporter, Items.SUGAR, "sugar", CARAMEL, time, exp, true);
        offerFoodCookingRecipe(exporter, Items.SUGAR_CANE, "sugar_cane", MOLASSES, time, exp, false);
        offerFoodCookingRecipe(exporter, Items.BREAD, "bread", TOAST, time, exp, false);
        // only salt missing
        offerFoodCookingRecipe(exporter, WATER_BOTTLE, ItemNamesV2.WATER_BOTTLE, SALT, 800, 0.1f, false);
        offerFoodCookingRecipe(exporter, RAW_RAVAGER_MEAT, ItemNamesV2.RAW_RAVAGER_MEAT, COOKED_RAVAGER_MEAT, 800, 0.1f, false);

        SimpleCookingRecipeBuilder.blasting(Ingredient.of(WATER_BOTTLE), MISC, SALT, 0.1f, 400);
    }

    protected void generateUtensil(RecipeOutput exporter) {
        ShapedRecipeBuilder.shaped(MISC, COOKING_POT)
                .pattern("# #")
                .pattern("# #")
                .pattern(" # ")
                .define('#', Items.IRON_INGOT)
                .unlockedBy("has_iron", has(Items.IRON_INGOT))
                .save(exporter);
        ShapedRecipeBuilder.shaped(MISC, FOOD_PRESS)
                .pattern("I")
                .pattern("H")
                .pattern("I")
                .define('I', Items.PISTON).define('H', Items.HOPPER)
                .unlockedBy("has_piston", has(Items.PISTON))
                .unlockedBy("has_hopper", has(Items.HOPPER))
                .save(exporter);
        ShapedRecipeBuilder.shaped(MISC, FRYING_PAN)
                .pattern("#  ")
                .pattern(" ##")
                .pattern(" ##")
                .define('#', Items.IRON_INGOT)
                .unlockedBy("has_iron", has(Items.IRON_INGOT))
                .save(exporter);
        ShapedRecipeBuilder.shaped(MISC, KNIFE)
                .pattern(" #")
                .pattern("i ")
                .define('i', Items.STICK).define('#', Items.IRON_INGOT)
                .unlockedBy("has_iron", has(Items.IRON_INGOT))
                .save(exporter);
        ShapedRecipeBuilder.shaped(MISC, MORTAR_AND_PESTLE)
                .pattern("i")
                .pattern("#")
                .define('i', Items.STICK).define('#', Items.BOWL)
                .unlockedBy("has_bowl", has(Items.BOWL))
                .save(exporter);
    }

    protected void generateMiscShapeless(RecipeOutput exporter) {
        TagKey<Item> saltTag = independentTag("salts");
        TagKey<Item> butterTag = independentTag("butters");
        TagKey<Item> kumquatTag = independentTag(KUMQUAT.getPlural());
        TagKey<Item> turmericTag = independentTag(TURMERIC.getPlural());
        TagKey<Item> grapeTag = independentTag(GRAPE.getPlural());
        TagKey<Item> almondTag = independentTag(ALMOND.getPlural());
        TagKey<Item> artichoke = independentTag(ARTICHOKE.getPlural());
        TagKey<Item> banana = independentTag(BANANA.getPlural());
        TagKey<Item> vanilla = independentTag(VANILLA.getPlural());
        TagKey<Item> hops = independentTag(HOPS.getPlural());
        TagKey<Item> barley = independentTag(BARLEY.getPlural());
        TagKey<Item> lettuce = independentTag(LETTUCE.getPlural());
        TagKey<Item> tomato = independentTag(TOMATO.getPlural());
        TagKey<Item> blackbean = independentTag(BLACKBEAN.getPlural());
        TagKey<Item> rice = independentTag(RICE.getPlural());
        TagKey<Item> tortilla = independentTag("tortillas");
        TagKey<Item> flour = independentTag("flour");
        TagKey<Item> milks = independentTag("milks");
        TagKey<Item> cheese = independentTag("cheeses");
        TagKey<Item> nuts = independentTag("nuts");
        TagKey<Item> chocolates = independentTag("chocolates");
        shapeless(FOOD, ALMOND_BRITTLE, 2)
                .requires(butterTag)
                .requires(almondTag)
                .requires(Items.SUGAR, 2)
                .unlockedBy("has_almond", has(ALMOND))
                .save(exporter);
        shapeless(FOOD, ARTICHOKE_DIP)
                .requires(artichoke)
                .requires(cheese)
                .unlockedBy("has_artichoke", has(ARTICHOKE))
                .save(exporter);
        shapeless(FOOD, BANANA_CREAM_PIE)
                .requires(banana)
                .requires(vanilla)
                .requires(Items.SUGAR)
                .requires(Items.EGG)
                .requires(milks)
                .requires(FRYING_PAN)
                .unlockedBy("has_banana", has(BANANA))
                .save(exporter);
        shapeless(FOOD, BANANA_NUT_BREAD, 2)
                .requires(CINNAMON)
                .requires(Items.SUGAR)
                .requires(flour)
                .requires(banana)
                .requires(nuts)
                .unlockedBy("has_banana", has(banana))
                .save(exporter);
        shapeless(FOOD, BEER)
                .requires(Items.GLASS_BOTTLE)
                .requires(hops)
                .requires(barley)
                .requires(FOOD_PRESS)
                .unlockedBy("has_hops", has(HOPS))
                .save(exporter);
        shapeless(FOOD, BLT)
                .requires(Items.BREAD)
                .requires(COOKED_BACON)
                .requires(lettuce)
                .requires(tomato)
                .unlockedBy("has_cooked_bacon", has(COOKED_BACON))
                .save(exporter);
        shapeless(FOOD, BROWNIES)
                .requires(Items.SUGAR)
                .requires(Items.EGG)
                .requires(FRYING_PAN)
                .requires(flour)
                .requires(milks)
                .requires(chocolates)
                .unlockedBy("has_chocolates", has(chocolates))
                .save(exporter);
        shapeless(FOOD, BURRITO)
                .requires(tortilla)
                .requires(rice)
                .requires(blackbean)
                .requires(tomato)
                .unlockedBy("has_rice", has(rice))
                .save(exporter);


        shapeless(MISC, Items.DEAD_BUSH)
                .requires(saltTag).requires(ItemTags.SAPLINGS)
                .unlockedBy("has_salts", has(saltTag))
                .save(exporter, "croptopia:" + getItemName(Items.DEAD_BUSH));
        shapeless(MISC, CANDIED_KUMQUATS, 7)
                .requires(kumquatTag)
                .requires(kumquatTag)
                .requires(kumquatTag)
                .requires(kumquatTag)
                .requires(kumquatTag)
                .requires(kumquatTag)
                .requires(kumquatTag)
                .requires(independentTag("vanilla"))
                .requires(Items.HONEY_BOTTLE)
                .unlockedBy("has_kumquat", has(KUMQUAT))
                .save(exporter);

        shapeless(MISC, Items.ORANGE_DYE, 2)
                .requires(turmericTag)
                .requires(turmericTag)
                .requires(turmericTag)
                .unlockedBy("has_turmeric", has(TURMERIC))
                .save(exporter, "croptopia:" + getItemName(Items.ORANGE_DYE));
        shapeless(MISC, Items.PURPLE_DYE, 2)
                .requires(grapeTag)
                .requires(grapeTag)
                .requires(grapeTag)
                .unlockedBy("has_grape", has(GRAPE))
                .save(exporter, "croptopia:" + getItemName(Items.PURPLE_DYE));
    }

    protected void generateMiscShaped(RecipeOutput exporter) {
        ShapedRecipeBuilder.shaped(MISC, ROASTED_PUMPKIN_SEEDS)
                .pattern("123")
                .pattern(" 4 ")
                .define('1', Items.PUMPKIN_SEEDS)
                .define('3', PEPPER.asItem())
                .define('2', independentTag("salts"))
                .define('4', FRYING_PAN)
                .unlockedBy("has_pumpkin_seed", has(Items.PUMPKIN_SEEDS))
                .save(exporter);
        ShapedRecipeBuilder.shaped(MISC, ROASTED_SUNFLOWER_SEEDS)
                .pattern("123")
                .pattern(" 4 ")
                .define('1', Items.SUNFLOWER)
                .define('3', PEPPER.asItem())
                .define('2', independentTag("salts"))
                .define('4', FRYING_PAN)
                .unlockedBy("has_sunflower", has(Items.SUNFLOWER))
                .save(exporter);
        ShapedRecipeBuilder.shaped(MISC, PUMPKIN_BARS, 3)
                .pattern("586")
                .pattern("124")
                .pattern("373")
                .define('1', Items.EGG)
                .define('2', Items.SUGAR)
                .define('3', Items.PUMPKIN)
                .define('4', independentTag("flour"))
                .define('5', CINNAMON)
                .define('6', independentTag("salts"))
                .define('7', independentTag("butters"))
                .define('8', independentTag("vanilla"))
                .unlockedBy("has_pumpkin", has(Items.PUMPKIN))
                .unlockedBy("has_cinnamon", has(CINNAMON))
                .save(exporter);
        ShapedRecipeBuilder.shaped(MISC, CORN_BREAD)
                .pattern("111")
                .define('1', independentTag("corn"))
                .unlockedBy("has_corn", has(CORN.asItem()))
                .save(exporter);
        ShapedRecipeBuilder.shaped(MISC, PUMPKIN_SOUP, 2)
                .pattern("123")
                .pattern(" 5 ")
                .pattern("464")
                .define('1', independentTag("onions"))
                .define('2', independentTag("garlic"))
                .define('3', PEPPER.asItem())
                .define('4', Items.PUMPKIN)
                .define('5', independentTag("salts"))
                .define('6', COOKING_POT)
                .unlockedBy("has_pumpkin", has(Items.PUMPKIN))
                .save(exporter);
        ShapedRecipeBuilder.shaped(MISC, MERINGUE, 2)
                .pattern("243")
                .pattern("111")
                .define('1', Items.EGG)
                .define('2', independentTag("salts"))
                .define('3', Items.SUGAR)
                .define('4', independentTag("vanilla"))
                .unlockedBy("has_egg", has(Items.EGG))
                .save(exporter);
        ShapedRecipeBuilder.shaped(MISC, CABBAGE_ROLL, 2)
                .pattern("121")
                .pattern("456")
                .pattern("585")
                .define('8', FRYING_PAN)
                .define('1', croptopia("beef_replacements"))
                .define('2', independentTag("onions"))
                .define('6', independentTag("rice"))
                .define('4', independentTag("salts"))
                .define('5', independentTag("cabbage"))
                .unlockedBy("has_cabbage", has(CABBAGE.asItem()))
                .save(exporter);
        ShapedRecipeBuilder.shaped(MISC, BORSCHT, 2)
                .pattern("123")
                .pattern("456")
                .pattern("789")
                .define('1', Items.CARROT)
                .define('2', Items.POTATO)
                .define('3', Items.BEETROOT)
                .define('4', independentTag("onions"))
                .define('5', independentTag("tomatoes"))
                .define('6', independentTag("water_bottles"))
                .define('8', COOKING_POT)
                .define('7', independentTag("cabbage"))
                .define('9', independentTag("garlic"))
                .unlockedBy("has_cabbage", has(CABBAGE.asItem()))
                .save(exporter);
        ShapedRecipeBuilder.shaped(MISC, GOULASH)
                .pattern("123")
                .pattern("454")
                .pattern("183")
                .define('8', FRYING_PAN)
                .define('1', croptopia("pork_replacements"))
                .define('3', croptopia("beef_replacements"))
                .define('2', independentTag("onions"))
                .define('4', independentTag("cabbage"))
                .define('5', independentTag("tomatoes"))
                .unlockedBy("has_cabbage", has(CABBAGE.asItem()))
                .save(exporter);
        ShapedRecipeBuilder.shaped(MISC, BEETROOT_SALAD)
                .pattern("111")
                .pattern("745")
                .pattern(" 6 ")
                .define('1', Items.BEETROOT)
                .define('4', independentTag("cheeses"))
                .define('5', independentTag("lemons"))
                .define('6', COOKING_POT)
                .define('7', independentTag("lettuce"))
                .unlockedBy("has_beetroot", has(Items.BEETROOT))
                .save(exporter);
        ShapedRecipeBuilder.shaped(MISC, STEAMED_CRAB)
                .pattern("1")
                .pattern("2")
                .pattern("3")
                .define('1', independentTag("crabs"))
                .define('2', independentTag("water_bottles"))
                .define('3', COOKING_POT)
                .unlockedBy("has_crab", has(CRAB))
                .save(exporter);
        ShapedRecipeBuilder.shaped(MISC, DEEP_FRIED_SHRIMP, 2)
                .pattern("111")
                .pattern("456")
                .define('1', independentTag("shrimp"))
                .define('4', Items.EGG)
                .define('6', Items.BREAD)
                .define('5', FRYING_PAN)
                .unlockedBy("has_shrimp", has(SHRIMP))
                .save(exporter);
        ShapedRecipeBuilder.shaped(MISC, TUNA_ROLL, 2)
                .pattern("234")
                .pattern(" 1 ")
                .define('1', independentTag("tuna"))
                .define('2', Items.DRIED_KELP)
                .define('3', independentTag("rice"))
                .define('4', independentTag("onions"))
                .unlockedBy("has_tuna", has(TUNA))
                .save(exporter);
        ShapedRecipeBuilder.shaped(MISC, FRIED_CALAMARI, 2)
                .pattern("123")
                .pattern("456")
                .define('1', independentTag("calamari"))
                .define('2', independentTag("lemons"))
                .define('3', independentTag("olive_oils"))
                .define('4', independentTag("flour"))
                .define('5', FRYING_PAN)
                .define('6', independentTag("sea_lettuce"))
                .unlockedBy("has_calamari", has(CALAMARI))
                .save(exporter);
        ShapedRecipeBuilder.shaped(MISC, CRAB_LEGS, 2)
                .pattern("123")
                .pattern("455")
                .pattern(" 7 ")
                .define('5', independentTag("crabs"))
                .define('1', independentTag("butters"))
                .define('2', independentTag("garlic"))
                .define('3', independentTag("salts"))
                .define('4', PEPPER.asItem())
                .define('7', FRYING_PAN)
                .unlockedBy("has_crab", has(CRAB))
                .save(exporter);
        ShapedRecipeBuilder.shaped(MISC, STEAMED_CLAMS, 2)
                .pattern("123")
                .pattern("455")
                .pattern(" 7 ")
                .define('5', independentTag("clams"))
                .define('1', independentTag("butters"))
                .define('2', independentTag("garlic"))
                .define('3', independentTag("salts"))
                .define('4', PEPPER.asItem())
                .define('7', FRYING_PAN)
                .unlockedBy("has_clams", has(CLAM))
                .save(exporter);
        ShapedRecipeBuilder.shaped(MISC, GRILLED_OYSTERS, 2)
                .pattern("121")
                .pattern("456")
                .pattern(" 7 ")
                .define('1', independentTag("oysters"))
                .define('2', independentTag("cheeses"))
                .define('4', independentTag("lemons"))
                .define('5', independentTag("garlic"))
                .define('6', independentTag("salts"))
                .define('7', FRYING_PAN)
                .unlockedBy("has_oysters", has(GRILLED_OYSTERS))
                .save(exporter);
        ShapedRecipeBuilder.shaped(MISC, ANCHOVY_PIZZA, 1)
                .pattern("123")
                .pattern(" 4 ")
                .pattern(" 7 ")
                .define('1', independentTag("tomatoes"))
                .define('2', independentTag("anchovies"))
                .define('3', independentTag("cheeses"))
                .define('4', independentTag("doughs"))
                .define('7', FRYING_PAN)
                .unlockedBy("has_anchovies", has(ANCHOVY))
                .save(exporter);
        ShapedRecipeBuilder.shaped(MISC, MASHED_POTATOES, 1)
                .pattern("1 ")
                .pattern("24")
                .pattern("3 ")
                .define('1', independentTag("potatoes"))
                .define('2', independentTag("salts"))
                .define('3', MORTAR_AND_PESTLE)
                .define('4', independentTag("milks"))
                .unlockedBy("has_milk", has(Items.MILK_BUCKET))
                .save(exporter);
        shapeless(MISC, TORTILLA, 2)
                .requires(independentTag("flour"))
                .requires(FRYING_PAN)
                .requires(independentTag("water_bottles"))
                .unlockedBy("took_flour", has(independentTag("flour")))
                .unlockedBy("has_frying_pan", has(FRYING_PAN))
                .save(exporter);
        ShapedRecipeBuilder.shaped(MISC, SWEET_CREPES, 1)
                .pattern("123")
                .pattern("4 5")
                .pattern(" 6 ")
                .define('1', independentTag("flour"))
                .define('2', Items.EGG)
                .define('3', independentTag("milks"))
                .define('4', independentTag("jams"))
                .define('5', Items.SUGAR)
                .define('6', FRYING_PAN)
                .unlockedBy("took_flour", has(independentTag("flour")))
                .unlockedBy("has_frying_pan", has(FRYING_PAN))
                .save(exporter);
        ShapedRecipeBuilder.shaped(MISC, BAKED_CREPES, 1)
                .pattern("121")
                .pattern("356")
                .pattern(" 7 ")
                .define('1', Items.EGG)
                .define('2', independentTag("flour"))
                .define('3', independentTag("milks"))
                .define('7', FRYING_PAN)
                .define('6', independentTag("cheeses"))
                .define('5', independentTag("spinach"))
                .unlockedBy("has_frying_pan", has(FRYING_PAN))
                .save(exporter);
        ShapedRecipeBuilder.shaped(MISC, QUICHE, 1)
                .pattern(" 1 ")
                .pattern("234")
                .pattern("5 6")
                .define('1', FRYING_PAN)
                .define('5', independentTag("flour"))
                .define('6', independentTag("onions"))
                .define('2', independentTag("milks"))
                .define('3', Items.EGG)
                .define('4', independentTag("spinach"))
                .unlockedBy("has_frying_pan", has(FRYING_PAN))
                .save(exporter);
        ShapedRecipeBuilder.shaped(MISC, DAUPHINE_POTATOES, 1)
                .pattern("213")
                .pattern("456")
                .define('1', FRYING_PAN)
                .define('2', independentTag("water_bottles"))
                .define('3', independentTag("milks"))
                .define('4', independentTag("butters"))
                .define('5', independentTag("flour"))
                .define('6', independentTag("olive_oils"))
                .unlockedBy("has_frying_pan", has(FRYING_PAN))
                .save(exporter);
        ShapedRecipeBuilder.shaped(MISC, CROQUE_MONSIEUR, 1)
                .pattern(" 1 ")
                .pattern(" 26")
                .pattern("435")
                .define('1', FRYING_PAN)
                .define('2', Items.BREAD)
                .define('3', independentTag("cheeses"))
                .define('4', croptopia("pork_replacements"))
                .define('5', independentTag("butters"))
                .define('6', independentTag("flour"))
                .unlockedBy("has_frying_pan", has(FRYING_PAN))
                .save(exporter);
        ShapedRecipeBuilder.shaped(MISC, CROQUE_MADAME, 1)
                .pattern(" 1 ")
                .pattern("726")
                .pattern("435")
                .define('1', FRYING_PAN)
                .define('2', Items.BREAD)
                .define('3', independentTag("cheeses"))
                .define('4', croptopia("pork_replacements"))
                .define('5', independentTag("butters"))
                .define('6', independentTag("flour"))
                .define('7', Items.EGG)
                .unlockedBy("has_frying_pan", has(FRYING_PAN))
                .save(exporter);
        ShapedRecipeBuilder.shaped(MISC, SUNNY_SIDE_EGGS, 2)
                .pattern("121")
                .define('2', FRYING_PAN)
                .define('1', Items.EGG)
                .unlockedBy("has_frying_pan", has(FRYING_PAN))
                .save(exporter);
        ShapedRecipeBuilder.shaped(MISC, MACARON, 2)
                .pattern("122")
                .pattern("565")
                .define('1', Items.EGG)
                .define('2', Items.SUGAR)
                .define('5', independentTag("almonds"))
                .define('6', FOOD_PRESS)
                .unlockedBy("has_food_press", has(FOOD_PRESS))
                .save(exporter);
        ShapedRecipeBuilder.shaped(MISC, THE_BIG_BREAKFAST, 1)
                .pattern("123")
                .pattern("736")
                .pattern(" 45")
                .define('7', FRYING_PAN)
                .define('1', Items.EGG)
                .define('2', RAW_BACON)
                .define('3', HASHED_BROWN)
                .define('4', BAKED_BEANS)
                .define('5', independentTag("sausages"))
                .define('6', TOAST)
                .unlockedBy("has_frying_pan", has(FRYING_PAN))
                .save(exporter);
        ShapedRecipeBuilder.shaped(MISC, GROUND_PORK, 2)
                .pattern("1")
                .pattern("2")
                .define('1', croptopia("pork_replacements"))
                .define('2', FOOD_PRESS)
                .unlockedBy("has_food_press", has(FOOD_PRESS))
                .save(exporter);
        ShapedRecipeBuilder.shaped(MISC, SAUSAGE, 1)
                .pattern("1")
                .pattern("2")
                .pattern("3")
                .define('1', independentTag("ground_pork"))
                .define('2', independentTag("salts"))
                .define('3', independentTag("paprika"))
                .unlockedBy("has_ground_pork", has(GROUND_PORK))
                .save(exporter);
        ShapedRecipeBuilder.shaped(MISC, CINNAMON_ROLL, 3)
                .pattern("123")
                .pattern("456")
                .pattern("798")
                .define('1', independentTag("milks"))
                .define('2', independentTag("doughs"))
                .define('3', Items.EGG)
                .define('4', independentTag("butters"))
                .define('5', independentTag("salts"))
                .define('6', Items.SUGAR)
                .define('7', independentTag("cinnamon"))
                .define('8', WHIPPING_CREAM)
                .define('9', FRYING_PAN)
                .unlockedBy("has_frying_pan", has(FRYING_PAN))
                .save(exporter);
        ShapedRecipeBuilder.shaped(MISC, HASHED_BROWN, 4)
                .pattern("123")
                .pattern(" 4 ")
                .define('4', KNIFE)
                .define('1', independentTag("potatoes"))
                .define('2', FRYING_PAN)
                .define('3', independentTag("olive_oils"))
                .unlockedBy("has_frying_pan", has(FRYING_PAN))
                .save(exporter);

        ShapedRecipeBuilder.shaped(FOOD, BEEF_JERKY, 14)
                .pattern("111")
                .pattern("121")
                .pattern("111")
                .define('1', Items.BEEF)
                .define('2', independentTag("salts"))
                .unlockedBy("has_salt", has(SALT))
                .save(exporter);
        ShapedRecipeBuilder.shaped(FOOD, PORK_JERKY, 14)
                .pattern("111")
                .pattern("121")
                .pattern("111")
                .define('1', Items.PORKCHOP)
                .define('2', independentTag("salts"))
                .unlockedBy("has_salt", has(SALT))
                .save(exporter);
        ShapedRecipeBuilder.shaped(FOOD, DRAGON_EGG_OMELETTE, 1)
                .pattern(" 1 ")
                .pattern(" 2 ")
                .pattern("3 4")
                .define('1', Items.DRAGON_EGG)
                .define('2', independentTag("cheeses"))
                .define('3', independentTag("salts"))
                .define('4', PEPPER)
                .unlockedBy("has_dragon_egg", has(Items.DRAGON_EGG))
                .save(exporter);
        ShapedRecipeBuilder.shaped(FOOD, NETHER_STAR_CAKE, 1)
                .pattern(" 1 ")
                .pattern("222")
                .pattern("444")
                .define('1', Items.NETHER_STAR)
                .define('2', DOUGH)
                .define('4', Items.SUGAR)
                .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
                .save(exporter);
        ShapedRecipeBuilder.shaped(FOOD, TRANSCENDENTAL_BREAKFAST, 1)
                .pattern("616")
                .pattern("234")
                .pattern("656")
                .define('1', MOUNTAIN_SALT)
                .define('2', NETHER_STAR_CAKE)
                .define('3', TUNA_SANDWICH)
                .define('4', DRAGON_EGG_OMELETTE)
                .define('5', COOKED_RAVAGER_MEAT)
                .define('6', THE_BIG_BREAKFAST)
                .unlockedBy("has_nether_star_cake", has(NETHER_STAR_CAKE))
                .save(exporter);
        //cooked frog leg	furnace

    }

    private TagKey<Item> croptopia(String name) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiscNames.MOD_ID, name));
    }

    public static TagKey<Item> independentTag(String name) {
        ResourceLocation location = ResourceLocation.fromNamespaceAndPath("c", name);
        return TagKey.create(Registries.ITEM, location);
    }

}
