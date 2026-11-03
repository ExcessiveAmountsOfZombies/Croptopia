package com.epherical.croptopia.datagen;

import com.epherical.croptopia.common.ItemNamesV2;
import com.epherical.croptopia.common.MiscNames;
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
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.triggers.CriteriaTriggers;
import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.advancements.triggers.InventoryChangeTrigger;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.MultiRegistryBootstrap;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.Recipe;
import java.util.Set;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.epherical.croptopia.register.Content.*;
import static net.minecraft.data.recipes.RecipeCategory.FOOD;
import static net.minecraft.data.recipes.RecipeCategory.MISC;

public class CroptopiaRecipeProvider extends RecipeProvider {

    public CroptopiaRecipeProvider(BootstrapContext<Recipe<?>> recipes, BootstrapContext<Advancement> advancements) {
        super(recipes, advancements);
    }


    @Override
    public void buildRecipes() {
        RecipeOutput exporter = this.output;


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

    }

    protected void generateBarkWood(RecipeOutput exporter) {
        for (Tree crop : Tree.copy()) {
            shaped(MISC, crop.getWood())
                    .pattern("##")
                    .pattern("##")
                    .define('#', crop.getLog())
                    .unlockedBy("has_" + crop.getLowercaseName() + "_log", has(crop.getLog()))
                    .save(exporter);
            shaped(MISC, crop.getStrippedWood())
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
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), FOOD, CookingBookCategory.FOOD, output, exp, time)
                .unlockedBy("has_" + inputName, has(input))
                .save(exporter, "croptopia:" + getItemName(output) + "_from_" + inputName);
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(input), FOOD, output, exp, time / 2)
                .unlockedBy("has_" + inputName, has(input))
                .save(exporter, "croptopia:" + getItemName(output) + "_from_smoking_" + inputName);

    }

    protected void generateFurnace(RecipeOutput exporter) {
        final int time = 200;
        final float exp = 0.2f;
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

        offerFoodCookingRecipe(exporter, RAW_BACON, ItemNamesV2.RAW_BACON, COOKED_BACON, time, exp, true);

        offerFoodCookingRecipe(exporter, Items.SUGAR, "sugar", CARAMEL, time, exp, true);
        offerFoodCookingRecipe(exporter, Items.SUGAR_CANE, "sugar_cane", MOLASSES, time, exp, false);
        offerFoodCookingRecipe(exporter, Items.BREAD, "bread", TOAST, time, exp, false);

        offerFoodCookingRecipe(exporter, WATER_BOTTLE, ItemNamesV2.WATER_BOTTLE, SALT, 800, 0.1f, false);
        offerFoodCookingRecipe(exporter, RAW_RAVAGER_MEAT, ItemNamesV2.RAW_RAVAGER_MEAT, COOKED_RAVAGER_MEAT, 800, 0.1f, false);

        SimpleCookingRecipeBuilder.blasting(Ingredient.of(WATER_BOTTLE), MISC, CookingBookCategory.MISC, SALT, 0.1f, 400);
    }

    protected void generateUtensil(RecipeOutput exporter) {
        shaped(MISC, COOKING_POT)
                .pattern("# #")
                .pattern("# #")
                .pattern(" # ")
                .define('#', Items.IRON_INGOT)
                .unlockedBy("has_iron", has(Items.IRON_INGOT))
                .save(exporter);
        shaped(MISC, FOOD_PRESS)
                .pattern("I")
                .pattern("H")
                .pattern("I")
                .define('I', Items.PISTON).define('H', Items.HOPPER)
                .unlockedBy("has_piston", has(Items.PISTON))
                .unlockedBy("has_hopper", has(Items.HOPPER))
                .save(exporter);
        shaped(MISC, FRYING_PAN)
                .pattern("#  ")
                .pattern(" ##")
                .pattern(" ##")
                .define('#', Items.IRON_INGOT)
                .unlockedBy("has_iron", has(Items.IRON_INGOT))
                .save(exporter);
        shaped(MISC, KNIFE)
                .pattern(" #")
                .pattern("i ")
                .define('i', Items.STICK).define('#', Items.IRON_INGOT)
                .unlockedBy("has_iron", has(Items.IRON_INGOT))
                .save(exporter);
        shaped(MISC, MORTAR_AND_PESTLE)
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

        shapeless(MISC, Items.DYE.orange(), 2)
                .requires(turmericTag)
                .requires(turmericTag)
                .requires(turmericTag)
                .unlockedBy("has_turmeric", has(TURMERIC))
                .save(exporter, "croptopia:" + getItemName(Items.DYE.orange()));
        shapeless(MISC, Items.DYE.purple(), 2)
                .requires(grapeTag)
                .requires(grapeTag)
                .requires(grapeTag)
                .unlockedBy("has_grape", has(GRAPE))
                .save(exporter, "croptopia:" + getItemName(Items.DYE.purple()));
    }

    protected void generateMiscShaped(RecipeOutput exporter) {
        shaped(MISC, ROASTED_PUMPKIN_SEEDS)
                .pattern("123")
                .pattern(" 4 ")
                .define('1', Items.PUMPKIN_SEEDS)
                .define('3', PEPPER.asItem())
                .define('2', independentTag("salts"))
                .define('4', FRYING_PAN)
                .unlockedBy("has_pumpkin_seed", has(Items.PUMPKIN_SEEDS))
                .save(exporter);
        shaped(FOOD, ROASTED_SUNFLOWER_SEEDS)
                .pattern("123")
                .pattern(" 4 ")
                .define('1', Items.SUNFLOWER)
                .define('3', PEPPER.asItem())
                .define('2', independentTag("salts"))
                .define('4', FRYING_PAN)
                .unlockedBy("has_sunflower", has(Items.SUNFLOWER))
                .save(exporter);
        shaped(FOOD, PUMPKIN_BARS, 3)
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
        shaped(FOOD, CORN_BREAD)
                .pattern("111")
                .define('1', independentTag("corn"))
                .unlockedBy("has_corn", has(CORN.asItem()))
                .save(exporter);
        shaped(FOOD, PUMPKIN_SOUP, 2)
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
        shaped(FOOD, MERINGUE, 2)
                .pattern("243")
                .pattern("111")
                .define('1', Items.EGG)
                .define('2', independentTag("salts"))
                .define('3', Items.SUGAR)
                .define('4', independentTag("vanilla"))
                .unlockedBy("has_egg", has(Items.EGG))
                .save(exporter);
        shaped(FOOD, CABBAGE_ROLL, 2)
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
        shaped(FOOD, BORSCHT, 2)
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
        shaped(FOOD, GOULASH)
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
        shaped(FOOD, BEETROOT_SALAD)
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
        shaped(FOOD, STEAMED_CRAB)
                .pattern("1")
                .pattern("2")
                .pattern("3")
                .define('1', independentTag("crabs"))
                .define('2', independentTag("water_bottles"))
                .define('3', COOKING_POT)
                .unlockedBy("has_crab", has(CRAB))
                .save(exporter);
        shaped(FOOD, DEEP_FRIED_SHRIMP, 2)
                .pattern("111")
                .pattern("456")
                .define('1', independentTag("shrimp"))
                .define('4', Items.EGG)
                .define('6', Items.BREAD)
                .define('5', FRYING_PAN)
                .unlockedBy("has_shrimp", has(SHRIMP))
                .save(exporter);
        shaped(FOOD, TUNA_ROLL, 2)
                .pattern("234")
                .pattern(" 1 ")
                .define('1', independentTag("tuna"))
                .define('2', Items.DRIED_KELP)
                .define('3', independentTag("rice"))
                .define('4', independentTag("onions"))
                .unlockedBy("has_tuna", has(TUNA))
                .save(exporter);
        shaped(FOOD, FRIED_CALAMARI, 2)
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
        shaped(FOOD, CRAB_LEGS, 2)
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
        shaped(FOOD, STEAMED_CLAMS, 2)
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
        shaped(FOOD, GRILLED_OYSTERS, 2)
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
        shaped(FOOD, ANCHOVY_PIZZA, 1)
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
        shaped(FOOD, MASHED_POTATOES, 1)
                .pattern("1 ")
                .pattern("24")
                .pattern("3 ")
                .define('1', independentTag("potatoes"))
                .define('2', independentTag("salts"))
                .define('3', MORTAR_AND_PESTLE)
                .define('4', independentTag("milks"))
                .unlockedBy("has_milk", has(Items.MILK_BUCKET))
                .save(exporter);
        shapeless(FOOD, TORTILLA, 2)
                .requires(independentTag("flour"))
                .requires(FRYING_PAN)
                .requires(independentTag("water_bottles"))
                .unlockedBy("took_flour", has(independentTag("flour")))
                .unlockedBy("has_frying_pan", has(FRYING_PAN))
                .save(exporter);
        shaped(FOOD, SWEET_CREPES, 1)
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
        shaped(FOOD, BAKED_CREPES, 1)
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
        shaped(FOOD, QUICHE, 1)
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
        shaped(FOOD, DAUPHINE_POTATOES, 1)
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
        shaped(FOOD, CROQUE_MONSIEUR, 1)
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
        shaped(FOOD, CROQUE_MADAME, 1)
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
        shaped(FOOD, SUNNY_SIDE_EGGS, 2)
                .pattern("121")
                .define('2', FRYING_PAN)
                .define('1', Items.EGG)
                .unlockedBy("has_frying_pan", has(FRYING_PAN))
                .save(exporter);
        shaped(FOOD, MACARON, 2)
                .pattern("122")
                .pattern("565")
                .define('1', Items.EGG)
                .define('2', Items.SUGAR)
                .define('5', independentTag("almonds"))
                .define('6', FOOD_PRESS)
                .unlockedBy("has_food_press", has(FOOD_PRESS))
                .save(exporter);
        shaped(FOOD, THE_BIG_BREAKFAST, 1)
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
        shaped(FOOD, GROUND_PORK, 2)
                .pattern("1")
                .pattern("2")
                .define('1', croptopia("pork_replacements"))
                .define('2', FOOD_PRESS)
                .unlockedBy("has_food_press", has(FOOD_PRESS))
                .save(exporter);
        shaped(FOOD, SAUSAGE, 1)
                .pattern("1")
                .pattern("2")
                .pattern("3")
                .define('1', independentTag("ground_pork"))
                .define('2', independentTag("salts"))
                .define('3', independentTag("paprika"))
                .unlockedBy("has_ground_pork", has(GROUND_PORK))
                .save(exporter);
        shaped(FOOD, CINNAMON_ROLL, 3)
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
        shaped(FOOD, HASHED_BROWN, 4)
                .pattern("123")
                .pattern(" 4 ")
                .define('4', KNIFE)
                .define('1', independentTag("potatoes"))
                .define('2', FRYING_PAN)
                .define('3', independentTag("olive_oils"))
                .unlockedBy("has_frying_pan", has(FRYING_PAN))
                .save(exporter);

        shaped(FOOD, BEEF_JERKY, 14)
                .pattern("111")
                .pattern("121")
                .pattern("111")
                .define('1', Items.BEEF)
                .define('2', independentTag("salts"))
                .unlockedBy("has_salt", has(SALT))
                .save(exporter);
        shaped(FOOD, PORK_JERKY, 14)
                .pattern("111")
                .pattern("121")
                .pattern("111")
                .define('1', Items.PORKCHOP)
                .define('2', independentTag("salts"))
                .unlockedBy("has_salt", has(SALT))
                .save(exporter);
        shaped(FOOD, DRAGON_EGG_OMELETTE, 1)
                .pattern(" 1 ")
                .pattern(" 2 ")
                .pattern("3 4")
                .define('1', Items.DRAGON_EGG)
                .define('2', independentTag("cheeses"))
                .define('3', independentTag("salts"))
                .define('4', PEPPER)
                .unlockedBy("has_dragon_egg", has(Items.DRAGON_EGG))
                .save(exporter);
        shaped(FOOD, NETHER_STAR_CAKE, 1)
                .pattern(" 1 ")
                .pattern("222")
                .pattern("444")
                .define('1', Items.NETHER_STAR)
                .define('2', DOUGH)
                .define('4', Items.SUGAR)
                .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
                .save(exporter);
        shaped(FOOD, TRANSCENDENTAL_BREAKFAST, 1)
                .pattern("616")
                .pattern("234")
                .pattern("656")
                .define('1', MOUNTAIN_SALT)
                .define('2', NETHER_STAR_CAKE)
                .define('3', TUNA_SANDWICH)
                .define('4', DRAGON_EGG_OMELETTE)
                .define('5', COOKED_RAVAGER_MEAT)
                .define('6', THE_BIG_BREAKFAST)
                .unlockedBy(
                        "has_transcendental_breakfast_ingredients",
                        hasAll(MOUNTAIN_SALT, NETHER_STAR_CAKE, TUNA_SANDWICH, DRAGON_EGG_OMELETTE, COOKED_RAVAGER_MEAT, THE_BIG_BREAKFAST)
                )
                .save(exporter);


        shapeless(FOOD, BUTTER)
                .requires(COOKING_POT)
                .requires(FOOD_PRESS)
                .requires(independentTag("milks"))
                .requires(independentTag("salts"))
                .unlockedBy("has_milk", has(Items.MILK_BUCKET))
                .save(exporter);


        shapeless(FOOD, BUTTERED_TOAST)
                .requires(independentTag("toasts"))
                .requires(independentTag("butters"))
                .unlockedBy("has_butter", has(BUTTER))
                .save(exporter);


        shapeless(FOOD, CAESAR_SALAD)
                .requires(Items.BOWL)
                .requires(independentTag("lettuce"))
                .requires(independentTag("olives"))
                .requires(independentTag("garlic"))
                .requires(independentTag("toasts"))
                .unlockedBy("has_bowl", has(Items.BOWL))
                .save(exporter);


        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(this.items.getOrThrow(independentTag("blackbeans"))), FOOD, BAKED_BEANS, 0.1f, 200)
                .unlockedBy("has_beans", has(independentTag("blackbeans")))
                .save(exporter);


        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(this.items.getOrThrow(independentTag("sweetpotatos"))), FOOD, BAKED_SWEET_POTATO, 0.1f, 200)
                .unlockedBy("has_potato", has(independentTag("sweetpotatos")))
                .save(exporter);


       /* SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(this.items.getOrThrow(independentTag("yams"))), FOOD, BAKED_YAM, 0.1f, 200)
                .unlockedBy("has_yams", has(independentTag("yams")))
                .save(exporter);*/


        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(Items.SUGAR), FOOD, CARAMEL, 0.1f, 200)
                .unlockedBy("has_sugar", has(Items.SUGAR))
                .save(exporter);


        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(Items.SUGAR_CANE), FOOD, MOLASSES, 0.1f, 200)
                .unlockedBy("has_sugar_cane", has(Items.SUGAR_CANE))
                .save(exporter);


        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(this.items.getOrThrow(independentTag("corn"))), FOOD, POPCORN, 0.1f, 200)
                .unlockedBy("has_corn", has(independentTag("corn")))
                .save(exporter);


        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(this.items.getOrThrow(independentTag("grapes"))), FOOD, RAISINS, 0.1f, 200)
                .unlockedBy("has_grapes", has(independentTag("grapes")))
                .save(exporter);


        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(Items.BREAD), FOOD, TOAST, 0.1f, 200)
                .unlockedBy("has_bread", has(Items.BREAD))
                .save(exporter);


        shapeless(FOOD, CANDIED_NUTS, 4)
                .requires(independentTag("nuts"))
                .requires(independentTag("nuts"))
                .requires(Items.SUGAR, 2)
                .unlockedBy("has_sugar", has(Items.SUGAR))
                .save(exporter);


        shapeless(FOOD, CANDY_CORN)
                .requires(Items.SUGAR, 2)
                .requires(independentTag("corn"))
                .unlockedBy("has_sugar", has(Items.SUGAR))
                .save(exporter);


        shapeless(FOOD, CARNITAS)
                .requires(Items.PORKCHOP)
                .requires(independentTag("tortillas"))
                .requires(independentTag("cabbage"))
                .requires(independentTag("onions"))
                .requires(FRYING_PAN)
                .unlockedBy("has_tortillas", has(independentTag("tortillas")))
                .save(exporter);


        shapeless(FOOD, CASHEW_CHICKEN)
                .requires(independentTag("cashews"))
                .requires(Items.COOKED_CHICKEN)
                .requires(independentTag("soy_sauces"))
                .requires(independentTag("cabbage"))
                .requires(Items.CARROT)
                .unlockedBy("has_carrot", has(Items.CARROT))
                .save(exporter);


        shapeless(FOOD, CHEESE)
                .requires(COOKING_POT)
                .requires(independentTag("milks"))
                .requires(independentTag("salts"))
                .unlockedBy("has_milks", has(independentTag("milks")))
                .save(exporter);


        shapeless(FOOD, CHEESEBURGER)
                .requires(Items.BREAD)
                .requires(independentTag("cheeses"))
                .requires(FRYING_PAN)
                .requires(croptopia("beef_replacements"))
                .unlockedBy("has_beef", has(Items.BEEF))
                .save(exporter);


        shapeless(FOOD, CHEESE_PIZZA)
                .requires(independentTag("doughs"))
                .requires(independentTag("cheeses"))
                .requires(independentTag("cheeses"))
                .requires(independentTag("tomatoes"))
                .requires(FRYING_PAN)
                .unlockedBy("has_pan", has(FRYING_PAN))
                .save(exporter);


        shapeless(FOOD, CHICKEN_AND_DUMPLINGS)
                .requires(independentTag("doughs"))
                .requires(independentTag("chile_peppers"))
                .requires(Items.CHICKEN)
                .requires(COOKING_POT)
                .unlockedBy("has_chicken", has(Items.CHICKEN))
                .save(exporter);


        shapeless(FOOD, CHICKEN_AND_NOODLES)
                .requires(independentTag("noodles"))
                .requires(independentTag("chile_peppers"))
                .requires(independentTag("olive_oils"))
                .requires(croptopia("chicken_replacements"))
                .requires(COOKING_POT)
                .unlockedBy("has_pot", has(COOKING_POT))
                .save(exporter);


        shapeless(FOOD, CHICKEN_AND_RICE)
                .requires(independentTag("rice"))
                .requires(independentTag("chile_peppers"))
                .requires(independentTag("olive_oils"))
                .requires(croptopia("chicken_replacements"))
                .unlockedBy("has_rice", has(independentTag("rice")))
                .save(exporter);


        shapeless(FOOD, CHILI_RELLENO)
                .requires(Items.EGG)
                .requires(independentTag("chile_peppers"))
                .requires(independentTag("olive_oils"))
                .requires(independentTag("flour"))
                .requires(independentTag("salts"))
                .requires(COOKING_POT)
                .unlockedBy("has_pot", has(COOKING_POT))
                .save(exporter);


        shapeless(FOOD, CHIMICHANGA)
                .requires(BURRITO)
                .requires(independentTag("flour"))
                .requires(COOKING_POT)
                .unlockedBy("has_pot", has(COOKING_POT))
                .save(exporter);


        shapeless(FOOD, CHOCOLATE, 4)
                .requires(Items.COCOA_BEANS)
                .requires(independentTag("butters"))
                .requires(COOKING_POT)
                .unlockedBy("has_cocoa", has(Items.COCOA_BEANS))
                .save(exporter);


        shapeless(FOOD, CHOCOLATE_MILKSHAKE)
                .requires(Items.GLASS_BOTTLE)
                .requires(independentTag("chocolates"))
                .requires(independentTag("milks"))
                .requires(VANILLA_ICE_CREAM)
                .unlockedBy("has_milks", has(independentTag("milks")))
                .save(exporter);


        shapeless(FOOD, CHURROS, 3)
                .requires(independentTag("milks"))
                .requires(Items.SUGAR)
                .requires(independentTag("flour"))
                .requires(CINNAMON)
                .unlockedBy("has_milks", has(independentTag("milks")))
                .save(exporter);


        shapeless(FOOD, COFFEE)
                .requires(Items.GLASS_BOTTLE)
                .requires(independentTag("coffee_beans"))
                .requires(FOOD_PRESS)
                .unlockedBy("has_food_press",  has(FOOD_PRESS))
                .save(exporter);


        shapeless(FOOD, CORN_HUSK, 8)
                .requires(independentTag("corn"))
                .requires(independentTag("corn"))
                .requires(independentTag("corn"))
                .requires(independentTag("corn"))
                .unlockedBy("has_corn", has(independentTag("corn")))
                .save(exporter);


        shapeless(FOOD, CREMA, 4)
                .requires(independentTag("milks"))
                .requires(independentTag("limes"))
                .requires(independentTag("salts"))
                .unlockedBy("has_milks", has(independentTag("milks")))
                .save(exporter);


        shapeless(FOOD, CUCUMBER_SALAD)
                .requires(Items.BOWL)
                .requires(independentTag("cucumbers"))
                .requires(independentTag("lettuce"))
                .requires(independentTag("spinach"))
                .unlockedBy("has_spinach", has(independentTag("spinach")))
                .save(exporter);


        shapeless(FOOD, GUIDE)
                .requires(independentTag("crops"))
                .requires(independentTag("crops"))
                .requires(Items.BOOK)
                .unlockedBy("has_crops", has(independentTag("crops")))
                .save(exporter);


        shapeless(FOOD, DOUGH)
                .requires(COOKING_POT)
                .requires(independentTag("water_bottles"))
                .requires(independentTag("flour"))
                .unlockedBy("has_water_bottles", has(independentTag("water_bottles")))
                .save(exporter);


        shapeless(FOOD, DOUGHNUT)
                .requires(independentTag("flour"))
                .requires(independentTag("flour"))
                .requires(independentTag("milks"))
                .requires(Items.SUGAR)
                .unlockedBy("has_milks", has(independentTag("milks")))
                .save(exporter);


        shapeless(FOOD, EGG_ROLL)
                .requires(independentTag("doughs"))
                .requires(independentTag("lettuce"))
                .requires(Items.EGG)
                .requires(croptopia("meat_replacements"))
                .unlockedBy("has_meat", has(croptopia("meat_replacements")))
                .save(exporter);


        shapeless(FOOD, ENCHILADA, 2)
                .requires(croptopia("meat_replacements"))
                .requires(independentTag("tomatoes"))
                .requires(independentTag("cheeses"))
                .requires(independentTag("tortillas"))
                .unlockedBy("has_tortillas", has(independentTag("tortillas")))
                .save(exporter);


        shapeless(FOOD, FAJITAS, 2)
                .requires(croptopia("meat_replacements"))
                .requires(independentTag("bellpeppers"))
                .requires(independentTag("onions"))
                .requires(independentTag("tomatoes"))
                .requires(independentTag("cheeses"))
                .requires(FRYING_PAN)
                .unlockedBy("has_frying_pan", has(FRYING_PAN))
                .save(exporter);


        shapeless(FOOD, FLOUR)
                .requires(croptopia("flourable"))
                .requires(croptopia("flourable"))
                .unlockedBy("has_flourable", has(croptopia("flourable")))
                .save(exporter);


        shapeless(FOOD, FRENCH_FRIES)
                .requires(Items.POTATO)
                .requires(COOKING_POT)
                .requires(independentTag("salts"))
                .requires(independentTag("olive_oils"))
                .unlockedBy("has_pot", has(COOKING_POT))
                .save(exporter);


        shapeless(FOOD, FRIED_CHICKEN)
                .requires(independentTag("flour"))
                .requires(independentTag("chile_peppers"))
                .requires(independentTag("olive_oils"))
                .requires(croptopia("chicken_replacements"))
                .requires(FRYING_PAN)
                .unlockedBy("has_frying_pan", has(FRYING_PAN))
                .save(exporter);


        shapeless(FOOD, FRUIT_SALAD)
                .requires(Items.BOWL)
                .requires(independentTag("strawberries"))
                .requires(independentTag("bananas"))
                .requires(independentTag("grapes"))
                .requires(Items.APPLE)
                .unlockedBy("has_apple", has(Items.APPLE))
                .save(exporter);


        shapeless(FOOD, FRUIT_SMOOTHIE, 2)
                .requires(Items.GLASS_BOTTLE)
                .requires(independentTag("fruits"))
                .requires(independentTag("fruits"))
                .requires(independentTag("fruits"))
                .requires(Items.ICE)
                .requires(independentTag("milks"))
                .unlockedBy("has_milks", has(independentTag("milks")))
                .save(exporter);


        shapeless(FOOD, GRILLED_CHEESE)
                .requires(Items.BREAD)
                .requires(independentTag("cheeses"))
                .requires(FRYING_PAN)
                .unlockedBy("has_frying_pan", has(FRYING_PAN))
                .save(exporter);


        shapeless(FOOD, HAMBURGER)
                .requires(Items.BREAD)
                .requires(FRYING_PAN)
                .requires(Items.BEEF)
                .unlockedBy("has_frying_pan", has(FRYING_PAN))
                .save(exporter);


        shapeless(FOOD, HAM_SANDWICH)
                .requires(Items.BREAD)
                .requires(Items.COOKED_PORKCHOP)
                .requires(independentTag("cheeses"))
                .unlockedBy("has_bread",  has(Items.BREAD))
                .save(exporter);


        shapeless(FOOD, HORCHATA)
                .requires(independentTag("rice"))
                .requires(independentTag("almonds"))
                .requires(independentTag("limes"))
                .requires(independentTag("water_bottles"))
                .requires(Items.SUGAR)
                .requires(CINNAMON)
                .unlockedBy("has_sugar", has(Items.SUGAR))
                .save(exporter);


        shapeless(FOOD, KALE_CHIPS)
                .requires(independentTag("kale"))
                .requires(FRYING_PAN)
                .requires(independentTag("salts"))
                .requires(independentTag("olive_oils"))
                .unlockedBy("has_frying_pan", has(FRYING_PAN))
                .save(exporter);


        shapeless(FOOD, KALE_SMOOTHIE)
                .requires(Items.GLASS_BOTTLE)
                .requires(independentTag("kale"))
                .requires(Items.ICE)
                .requires(independentTag("milks"))
                .requires(independentTag("mangos"))
                .requires(independentTag("yoghurts"))
                .requires(independentTag("tomatoes"))
                .unlockedBy("has_milks", has(independentTag("milks")))
                .save(exporter);


        shapeless(FOOD, LEAFY_SALAD)
                .requires(Items.BOWL)
                .requires(independentTag("lettuce"))
                .requires(independentTag("spinach"))
                .requires(independentTag("kale"))
                .unlockedBy("has_lettuce", has(independentTag("lettuce")))
                .save(exporter);


        shapeless(FOOD, LEEK_SOUP)
                .requires(Items.BOWL)
                .requires(independentTag("leek"))
                .requires(Items.POTATO)
                .requires(independentTag("milks"))
                .unlockedBy("has_milks", has(independentTag("milks")))
                .save(exporter);


        shapeless(FOOD, LEMONADE)
                .requires(Items.GLASS_BOTTLE)
                .requires(independentTag("lemons"))
                .requires(FOOD_PRESS)
                .unlockedBy("has_lemons", has(independentTag("lemons")))
                .save(exporter);


        shapeless(FOOD, LEMON_CHICKEN)
                .requires(independentTag("lemons"))
                .requires(independentTag("chile_peppers"))
                .requires(independentTag("tomatoes"))
                .requires(croptopia("chicken_replacements"))
                .requires(COOKING_POT)
                .unlockedBy("has_lemons", has(independentTag("lemons")))
                .save(exporter);


        shapeless(FOOD, LIMEADE)
                .requires(Items.GLASS_BOTTLE)
                .requires(independentTag("limes"))
                .requires(independentTag("lemons"))
                .requires(FOOD_PRESS)
                .unlockedBy("has_lemons", has(independentTag("lemons")))
                .save(exporter);


        shapeless(FOOD, MEAD)
                .requires(Items.HONEY_BOTTLE)
                .requires(independentTag("water_bottles"))
                .requires(FOOD_PRESS)
                .requires(Items.GLASS_BOTTLE)
                .unlockedBy("has_food_press", has(FOOD_PRESS))
                .save(exporter);


        shapeless(FOOD, NOODLE)
                .requires(COOKING_POT)
                .requires(independentTag("water_bottles"))
                .requires(independentTag("salts"))
                .requires(independentTag("flour"))
                .unlockedBy("has_pot", has(COOKING_POT))
                .save(exporter);


        shapeless(FOOD, NOUGAT, 2)
                .requires(independentTag("nuts"))
                .requires(independentTag("nuts"))
                .requires(Items.SUGAR, 2)
                .requires(Items.EGG, 2)
                .unlockedBy("has_nuts", has(independentTag("nuts")))
                .save(exporter);


        shapeless(FOOD, NUTTY_COOKIE, 4)
                .requires(independentTag("nuts"))
                .requires(independentTag("nuts"))
                .requires(Items.SUGAR)
                .requires(independentTag("flour"))
                .unlockedBy("has_nuts", has(independentTag("nuts")))
                .save(exporter);


        shapeless(FOOD, OATMEAL)
                .requires(Items.BOWL)
                .requires(independentTag("oat"))
                .requires(independentTag("milks"))
                .unlockedBy("has_milks", has(independentTag("milks")))
                .save(exporter);


        shapeless(FOOD, OLIVE_OIL)
                .requires(independentTag("olives"))
                .requires(independentTag("olives"))
                .requires(FOOD_PRESS)
                .unlockedBy("has_olives", has(independentTag("olives")))
                .save(exporter);


        shapeless(FOOD, ONION_RINGS)
                .requires(independentTag("onions"))
                .requires(COOKING_POT)
                .requires(independentTag("salts"))
                .requires(independentTag("olive_oils"))
                .requires(independentTag("flour"))
                .unlockedBy("has_olives", has(independentTag("olive_oils")))
                .save(exporter);


        shapeless(FOOD, PAPRIKA)
                .requires(independentTag("chile_peppers"))
                .requires(MORTAR_AND_PESTLE)
                .unlockedBy("has_chile", has(independentTag("chile_peppers")))
                .save(exporter);


        shapeless(FOOD, PEANUT_BUTTER_AND_JAM)
                .requires(Items.BREAD)
                .requires(PEANUT_BUTTER)
                .requires(independentTag("jams"))
                .unlockedBy("has_jams", has(independentTag("jams")))
                .save(exporter);


        shapeless(FOOD, PEPPERONI, 4)
                .requires(Items.BEEF)
                .requires(Items.PORKCHOP)
                .requires(independentTag("paprika"))
                .requires(independentTag("chile_peppers"))
                .unlockedBy("has_paprika", has(independentTag("paprika")))
                .save(exporter);


        shapeless(FOOD, PINEAPPLE_PEPPERONI_PIZZA)
                .requires(independentTag("doughs"))
                .requires(independentTag("cheeses"))
                .requires(independentTag("tomatoes"))
                .requires(independentTag("pineapples"))
                .requires(independentTag("pineapples"))
                .requires(independentTag("pepperoni"))
                .requires(FRYING_PAN)
                .unlockedBy("has_pepperoni", has(independentTag("pepperoni")))
                .save(exporter);


        shapeless(FOOD, PIZZA)
                .requires(independentTag("doughs"))
                .requires(independentTag("cheeses"))
                .requires(independentTag("tomatoes"))
                .requires(FRYING_PAN)
                .unlockedBy("has_tomatoes", has(independentTag("tomatoes")))
                .save(exporter);


        shapeless(FOOD, PORK_AND_BEANS)
                .requires(Items.BOWL)
                .requires(independentTag("blackbeans"))
                .requires(Items.PORKCHOP)
                .unlockedBy("has_blackbeans", has(independentTag("blackbeans")))
                .save(exporter);


        shapeless(FOOD, POTATO_CHIPS)
                .requires(Items.POTATO)
                .requires(FRYING_PAN)
                .requires(independentTag("salts"))
                .requires(independentTag("olive_oils"))
                .unlockedBy("has_salts", has(independentTag("olive_oils")))
                .save(exporter);


        shapeless(FOOD, PROTEIN_BAR, 3)
                .requires(independentTag("nuts"))
                .requires(independentTag("nuts"))
                .requires(Items.SUGAR)
                .requires(independentTag("caramel"))
                .requires(independentTag("chocolates"))
                .requires(independentTag("salts"))
                .unlockedBy("has_salts", has(independentTag("salts")))
                .save(exporter);


        shapeless(FOOD, PUMPKIN_SPICE_LATTE)
                .requires(Items.SUGAR)
                .requires(independentTag("paprika"))
                .requires(independentTag("milks"))
                .requires(Items.PUMPKIN)
                .requires(COFFEE)
                .unlockedBy("has_milks", has(independentTag("milks")))
                .save(exporter);


        shapeless(FOOD, QUESADILLA, 2)
                .requires(independentTag("tortillas"))
                .requires(independentTag("tortillas"))
                .requires(independentTag("cheeses"))
                .requires(independentTag("avocados"))
                .requires(croptopia("chicken_replacements"))
                .requires(FRYING_PAN)
                .unlockedBy("has_frying_pan", has(FRYING_PAN))
                .save(exporter);


        shapeless(FOOD, OATMEAL_COOKIE, 4)
                .requires(independentTag("raisins"))
                .requires(OATMEAL)
                .requires(Items.SUGAR)
                .requires(independentTag("flour"))
                .unlockedBy("has_flour", has(independentTag("flour")))
                .save(exporter);



        shapeless(FOOD, RAVIOLI, 2)
                .requires(independentTag("noodles"))
                .requires(independentTag("cheeses"))
                .unlockedBy("has_cheeses", has(independentTag("cheeses")))
                .save(exporter);


        shapeless(FOOD, REFRIED_BEANS, 2)
                .requires(independentTag("blackbeans"))
                .requires(independentTag("blackbeans"))
                .requires(independentTag("chile_peppers"))
                .requires(independentTag("cheeses"))
                .requires(FRYING_PAN)
                .unlockedBy("has_cheeses", has(independentTag("cheeses")))
                .save(exporter);


        SimpleCookingRecipeBuilder.smelting(Ingredient.of(this.items.getOrThrow(independentTag("nuts"))),
                        FOOD, CookingBookCategory.FOOD, ROASTED_NUTS, 0.1f, 200)
                .unlockedBy("has_nuts", has(independentTag("nuts")))
                .save(exporter, "croptopia:roasted_nuts_from_smelting");


        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(this.items.getOrThrow(independentTag("nuts"))), FOOD, ROASTED_NUTS, 0.1f, 200)
                .unlockedBy("has_nuts", has(independentTag("nuts")))
                .save(exporter, "croptopia:roasted_nuts_from_campfirecooking");


        SimpleCookingRecipeBuilder.smoking(Ingredient.of(this.items.getOrThrow(independentTag("nuts"))), FOOD, ROASTED_NUTS, 0.1f, 200)
                .unlockedBy("has_nuts", has(independentTag("nuts")))
                .save(exporter, "croptopia:roasted_nuts_from_smoking");
        shapeless(FOOD, RUM)
                .requires(Items.GLASS_BOTTLE)
                .requires(independentTag("molasses"))
                .requires(independentTag("water_bottles"))
                .unlockedBy("has_molasses", has(independentTag("molasses")))
                .save(exporter);


        shapeless(FOOD, RUM_RAISIN_ICE_CREAM)
                .requires(Items.SUGAR)
                .requires(independentTag("raisins"))
                .requires(independentTag("rums"))
                .requires(independentTag("milks"))
                .requires(Items.EGG)
                .requires(COOKING_POT)
                .unlockedBy("has_milks", has(independentTag("milks")))
                .save(exporter);


        shapeless(FOOD, SALSA, 4)
                .requires(independentTag("tomatoes"))
                .requires(independentTag("chile_peppers"))
                .requires(independentTag("salts"))
                .requires(independentTag("limes"))
                .requires(independentTag("tomatillos"))
                .unlockedBy("has_tomatoes", has(independentTag("tomatoes")))
                .save(exporter);


        shapeless(FOOD, SAUCY_CHIPS)
                .requires(Items.BOWL)
                .requires(croptopia("sauces"))
                .requires(POTATO_CHIPS)
                .unlockedBy("has_sauces", has(croptopia("sauces")))
                .save(exporter);


        shapeless(FOOD, SCRAMBLED_EGGS)
                .requires(FRYING_PAN)
                .requires(Items.EGG)
                .requires(independentTag("cheeses"))
                .unlockedBy("has_cheeses", has(independentTag("cheeses")))
                .save(exporter);


        shaped(FOOD, AJVAR)
                .pattern("456")
                .pattern("123")
                .define('4', independentTag("chile_peppers"))
                .define('5', independentTag("olive_oils"))
                .define('6', independentTag("salts"))
                .define('1', independentTag("bellpeppers"))
                .define('2', independentTag("eggplants"))
                .define('3', independentTag("garlic"))
                .unlockedBy("has_garlic", has(independentTag("garlic")))
                .save(exporter);


        shaped(FOOD, AJVAR_TOAST)
                .pattern("1")
                .pattern("2")
                .define('1', AJVAR)
                .define('2', TOAST)
                .unlockedBy("has_toast", has(TOAST))
                .save(exporter);


        shaped(FOOD, AVOCADO_TOAST)
                .pattern("1")
                .pattern("2")
                .define('1', independentTag("avocados"))
                .define('2', TOAST)
                .unlockedBy("has_toast", has(TOAST))
                .save(exporter);


        shaped(FOOD, RAW_BACON)
                .pattern(" 2")
                .pattern("1 ")
                .define('2', Items.PORKCHOP)
                .define('1', KNIFE)
                .unlockedBy("has_knife", has(KNIFE))
                .save(exporter);


        shaped(FOOD, BEEF_STEW)
                .pattern("1 4")
                .pattern("253")
                .define('1', Items.CARROT)
                .define('4', Items.CARROT)
                .define('2', Items.BEEF)
                .define('5', independentTag("flour"))
                .define('3', Items.POTATO)
                .unlockedBy("has_flour", has(independentTag("flour")))
                .save(exporter);


        shaped(FOOD, BEEF_STIR_FRY)
                .pattern("164")
                .pattern("253")
                .define('1', independentTag("broccoli"))
                .define('6', Items.CARROT)
                .define('4', Items.BEEF)
                .define('2', independentTag("olive_oils"))
                .define('5', independentTag("soy_sauces"))
                .define('3', independentTag("garlic"))
                .unlockedBy("has_garlic", has(independentTag("garlic")))
                .save(exporter);


        shaped(FOOD, BEEF_WELLINGTON)
                .pattern("94 ")
                .pattern("2F3")
                .pattern("516")
                .define('9', independentTag("flour"))
                .define('4', independentTag("onions"))
                .define('2', independentTag("mustard"))
                .define('F', FRYING_PAN)
                .define('3', PEPPER)
                .define('5', Items.EGG)
                .define('1', Items.BEEF)
                .define('6', Items.BROWN_MUSHROOM)
                .unlockedBy("has_mushroom", has(Items.BROWN_MUSHROOM))
                .save(exporter);


        shaped(FOOD, BUTTERED_GREEN_BEANS)
                .pattern("45 ")
                .pattern("123")
                .define('4', independentTag("salts"))
                .define('5', PEPPER)
                .define('1', independentTag("butters"))
                .define('2', independentTag("greenbeans"))
                .define('3', independentTag("gingers"))
                .unlockedBy("has_gingers", has(independentTag("gingers")))
                .save(exporter);


        shaped(FOOD, CHEESY_ASPARAGUS)
                .pattern(" 1 ")
                .pattern("324")
                .define('1', independentTag("asparagus"))
                .define('3', independentTag("cheeses"))
                .define('2', independentTag("olive_oils"))
                .define('4', PEPPER)
                .unlockedBy("has_asparagus", has(independentTag("asparagus")))
                .save(exporter);


        shaped(FOOD, CHOCOLATE_ICE_CREAM)
                .pattern(" 1 ")
                .pattern("324")
                .pattern(" 5 ")
                .define('1', Items.EGG)
                .define('3', independentTag("milk_bottles"))
                .define('2', independentTag("chocolates"))
                .define('4', Items.SUGAR)
                .define('5', COOKING_POT)
                .unlockedBy("has_pot", has(COOKING_POT))
                .save(exporter);


        shaped(FOOD, CORNISH_PASTY)
                .pattern("567")
                .pattern("234")
                .pattern(" 1 ")
                .define('5', independentTag("onions"))
                .define('6', independentTag("rutabagas"))
                .define('7', PEPPER)
                .define('2', independentTag("flour"))
                .define('3', Items.BEEF)
                .define('4', Items.POTATO)
                .define('1', FRYING_PAN)
                .unlockedBy("has_pan", has(FRYING_PAN))
                .save(exporter);


        shaped(FOOD, EGGPLANT_PARMESAN)
                .pattern("61 ")
                .pattern("324")
                .pattern(" 57")
                .define('6', independentTag("basil"))
                .define('1', Items.EGG)
                .define('3', independentTag("olive_oils"))
                .define('2', independentTag("eggplants"))
                .define('4', PEPPER)
                .define('5', independentTag("cheeses"))
                .define('7', Items.BREAD)
                .unlockedBy("has_bread", has(Items.BREAD))
                .save(exporter);


        shaped(FOOD, ETON_MESS)
                .pattern("343")
                .pattern("121")
                .define('3', independentTag("strawberries"))
                .define('4', WHIPPING_CREAM)
                .define('1', Items.EGG)
                .define('2', Items.SUGAR)
                .unlockedBy("has_sugar", has(Items.SUGAR))
                .save(exporter);


        shaped(FOOD, FIGGY_PUDDING)
                .pattern("456")
                .pattern("123")
                .define('4', independentTag("dates"))
                .define('5', independentTag("figs"))
                .define('6', Items.SUGAR)
                .define('1', Items.EGG)
                .define('2', independentTag("water_bottles"))
                .define('3', WHIPPING_CREAM)
                .unlockedBy("has_whipping_cream", has(WHIPPING_CREAM))
                .save(exporter);


        shaped(FOOD, FISH_AND_CHIPS)
                .pattern(" 4 ")
                .pattern("2F3")
                .pattern("51 ")
                .define('4', independentTag("flour"))
                .define('2', independentTag("salts"))
                .define('F', FRYING_PAN)
                .define('3', PEPPER)
                .define('5', croptopia("fishes"))
                .define('1', Items.POTATO)
                .unlockedBy("has_potato", has(Items.POTATO))
                .save(exporter);


        shaped(FOOD, FRUIT_CAKE, 3)
                .pattern("61 ")
                .pattern("324")
                .pattern("857")
                .define('6', independentTag("salts"))
                .define('1', independentTag("fruits"))
                .define('3', independentTag("lemons"))
                .define('2', Items.SUGAR)
                .define('4', independentTag("pecans"))
                .define('8', Items.EGG)
                .define('5', CINNAMON)
                .define('7', independentTag("nutmegs"))
                .unlockedBy("has_cinnamon",  has(CINNAMON))
                .save(exporter);


        shaped(FOOD, GRILLED_EGGPLANT)
                .pattern("625")
                .pattern("314")
                .define('6', independentTag("paprika"))
                .define('2', independentTag("salts"))
                .define('5', PEPPER)
                .define('3', independentTag("olive_oils"))
                .define('1', independentTag("eggplants"))
                .define('4', independentTag("garlic"))
                .unlockedBy("has_eggplants", has(independentTag("eggplants")))
                .save(exporter);


        shaped(FOOD, KIWI_SORBET)
                .pattern("1")
                .pattern("2")
                .define('1', independentTag("kiwis"))
                .define('2', Items.HONEY_BOTTLE)
                .unlockedBy("has_honey", has(Items.HONEY_BOTTLE))
                .save(exporter);


        shaped(FOOD, LEMON_COCONUT_BAR, 2)
                .pattern("314")
                .pattern("526")
                .define('3', Items.SUGAR)
                .define('1', independentTag("lemons"))
                .define('4', Items.EGG)
                .define('5', independentTag("butters"))
                .define('2', independentTag("coconuts"))
                .define('6', independentTag("flour"))
                .unlockedBy("has_flour", has(independentTag("flour")))
                .save(exporter);


        shaped(FOOD, MILK_BOTTLE, 16)
                .pattern("   ")
                .pattern("212")
                .pattern(" 2 ")
                .define('2', Items.GLASS)
                .define('1', Items.MILK_BUCKET)
                .unlockedBy("has_milk", has(Items.MILK_BUCKET))
                .save(exporter);


        shaped(FOOD, NETHER_WART_STEW)
                .pattern(" 1 ")
                .pattern("234")
                .define('1', independentTag("flour"))
                .define('2', Items.NETHER_WART)
                .define('3', Items.CRIMSON_FUNGUS)
                .define('4', Items.WARPED_FUNGUS)
                .unlockedBy("has_flour", has(independentTag("flour")))
                .save(exporter);


        shaped(FOOD, PEANUT_BUTTER, 4)
                .pattern(" 2 ")
                .pattern("212")
                .pattern(" 2 ")
                .define('2', independentTag("peanuts"))
                .define('1', FOOD_PRESS)
                .unlockedBy("has_food_press", has(FOOD_PRESS))
                .save(exporter);


        shaped(FOOD, PEANUT_BUTTER_W_CELERY)
                .pattern(" 2")
                .pattern("1 ")
                .define('2', independentTag("celery"))
                .define('1', PEANUT_BUTTER)
                .unlockedBy("has_peanuts", has(PEANUT_BUTTER))
                .save(exporter);


        shaped(FOOD, POTATO_SOUP)
                .pattern(" 1 ")
                .pattern("234")
                .pattern(" 5 ")
                .define('1', Items.POTATO)
                .define('2', independentTag("flour"))
                .define('3', independentTag("greenonions"))
                .define('4', RAW_BACON)
                .define('5', independentTag("water_bottles"))
                .unlockedBy("has_bacon", has(RAW_BACON))
                .save(exporter);


        shaped(FOOD, RATATOUILLE, 2)
                .pattern("364")
                .pattern("512")
                .pattern("789")
                .define('3', independentTag("tomatoes"))
                .define('6', independentTag("olive_oils"))
                .define('4', independentTag("squashes"))
                .define('5', independentTag("zucchini"))
                .define('1', FRYING_PAN)
                .define('2', independentTag("eggplants"))
                .define('7', independentTag("onions"))
                .define('8', independentTag("bellpeppers"))
                .define('9', independentTag("basil"))
                .unlockedBy("has_eggplants", has(independentTag("eggplants")))
                .save(exporter);


        shaped(FOOD, RHUBARB_CRISP)
                .pattern("456")
                .pattern("123")
                .pattern("7  ")
                .define('4', independentTag("oat"))
                .define('5', independentTag("flour"))
                .define('6', independentTag("butters"))
                .define('1', independentTag("rhubarb"))
                .define('2', Items.SUGAR)
                .define('3', independentTag("cinnamon"))
                .define('7', independentTag("salts"))
                .unlockedBy("has_salts", has(independentTag("salts")))
                .save(exporter);


        shaped(FOOD, ROASTED_ASPARAGUS, 2)
                .pattern("   ")
                .pattern("213")
                .pattern("465")
                .define('2', independentTag("olive_oils"))
                .define('1', independentTag("asparagus"))
                .define('3', independentTag("garlic"))
                .define('4', independentTag("salts"))
                .define('6', FRYING_PAN)
                .define('5', PEPPER)
                .unlockedBy("has_asparagus", has(independentTag("asparagus")))
                .save(exporter);


        shaped(FOOD, ROASTED_RADISHES, 2)
                .pattern("   ")
                .pattern("213")
                .pattern("465")
                .define('2', independentTag("olive_oils"))
                .define('1', independentTag("radishes"))
                .define('3', independentTag("garlic"))
                .define('4', independentTag("salts"))
                .define('6', FRYING_PAN)
                .define('5', PEPPER)
                .unlockedBy("has_pepper", has(PEPPER))
                .save(exporter);


        shaped(FOOD, ROASTED_SQUASH, 2)
                .pattern("   ")
                .pattern("213")
                .pattern("465")
                .define('2', independentTag("olive_oils"))
                .define('1', independentTag("squashes"))
                .define('3', independentTag("garlic"))
                .define('4', independentTag("salts"))
                .define('6', FRYING_PAN)
                .define('5', PEPPER)
                .unlockedBy("has_salts", has(independentTag("salts")))
                .save(exporter);


        shaped(FOOD, ROASTED_TURNIPS, 2)
                .pattern("   ")
                .pattern("213")
                .pattern("465")
                .define('2', independentTag("olive_oils"))
                .define('1', independentTag("turnips"))
                .define('3', independentTag("garlic"))
                .define('4', independentTag("salts"))
                .define('6', FRYING_PAN)
                .define('5', PEPPER)
                .unlockedBy("has_turnips", has(independentTag("turnips")))
                .save(exporter);


        shaped(FOOD, SCONES, 2)
                .pattern("567")
                .pattern("234")
                .pattern(" 1 ")
                .define('5', Items.EGG)
                .define('6', independentTag("vanilla"))
                .define('7', independentTag("blueberries"))
                .define('2', independentTag("flour"))
                .define('3', independentTag("salts"))
                .define('4', Items.SUGAR)
                .define('1', FRYING_PAN)
                .unlockedBy("has_salts", has(independentTag("salts")))
                .save(exporter);


        shaped(FOOD, SHEPHERDS_PIE)
                .pattern("213")
                .pattern("4F5")
                .pattern("678")
                .define('2', independentTag("salts"))
                .define('1', croptopia("beef_mutton"))
                .define('3', PEPPER)
                .define('4', Items.POTATO)
                .define('F', FRYING_PAN)
                .define('5', independentTag("tomatoes"))
                .define('6', independentTag("corn"))
                .define('7', independentTag("garlic"))
                .define('8', independentTag("onions"))
                .unlockedBy("has_tomatoes", has(independentTag("tomatoes")))
                .save(exporter);


        shaped(FOOD, STEAMED_BROCCOLI)
                .pattern("121")
                .define('1', independentTag("broccoli"))
                .define('2', independentTag("water_bottles"))
                .unlockedBy("has_broccoli", has(independentTag("broccoli")))
                .save(exporter);


        shaped(FOOD, STEAMED_GREEN_BEANS, 2)
                .pattern("121")
                .define('1', independentTag("greenbeans"))
                .define('2', independentTag("water_bottles"))
                .unlockedBy("has_greenbeans", has(independentTag("greenbeans")))
                .save(exporter);


        shaped(FOOD, STICKY_TOFFEE_PUDDING)
                .pattern("7 8")
                .pattern("456")
                .pattern("123")
                .define('7', independentTag("dates"))
                .define('8', independentTag("salts"))
                .define('4', WHIPPING_CREAM)
                .define('5', Items.SUGAR)
                .define('6', independentTag("butters"))
                .define('1', Items.EGG)
                .define('2', independentTag("water_bottles"))
                .define('3', independentTag("vanilla"))
                .unlockedBy("has_waterbottles", has(independentTag("water_bottles")))
                .save(exporter);


        shaped(FOOD, STIR_FRY)
                .pattern("314")
                .pattern("526")
                .define('3', independentTag("garlic"))
                .define('1', independentTag("broccoli"))
                .define('4', independentTag("greenonions"))
                .define('5', independentTag("bellpeppers"))
                .define('2', independentTag("olive_oils"))
                .define('6', Items.CARROT)
                .unlockedBy("has_carrots", has(Items.CARROT))
                .save(exporter);


        shaped(FOOD, STUFFED_ARTICHOKE)
                .pattern("456")
                .pattern("123")
                .pattern("7 8")
                .define('4', Items.BREAD)
                .define('5', independentTag("cheeses"))
                .define('6', independentTag("olive_oils"))
                .define('1', independentTag("artichokes"))
                .define('2', PEPPER)
                .define('3', independentTag("lemons"))
                .define('7', independentTag("salts"))
                .define('8', FRYING_PAN)
                .unlockedBy("has_lemons", has(independentTag("lemons")))
                .save(exporter);


        shaped(FOOD, TEA)
                .pattern("   ")
                .pattern(" 2 ")
                .pattern(" 1 ")
                .define('2', independentTag("tea_leaves"))
                .define('1', independentTag("water_bottles"))
                .unlockedBy("has_water_bottles", has(independentTag("water_bottles")))
                .save(exporter);


        shaped(FOOD, TOAST_SANDWICH, 2)
                .pattern("121")
                .define('1', Items.BREAD)
                .define('2', BUTTERED_TOAST)
                .unlockedBy("has_butteredtoast", has(BUTTERED_TOAST))
                .save(exporter);


        shaped(FOOD, TREACLE_TART, 3)
                .pattern("234")
                .pattern("111")
                .define('2', Items.HONEY_BOTTLE)
                .define('3', independentTag("lemons"))
                .define('4', WHIPPING_CREAM)
                .define('1', Items.BREAD)
                .unlockedBy("has_lemons", has(independentTag("lemons")))
                .save(exporter);


        shaped(FOOD, TRIFLE)
                .pattern(" 5 ")
                .pattern("647")
                .pattern("123")
                .define('5', independentTag("strawberries"))
                .define('6', Items.BREAD)
                .define('4', independentTag("wines"))
                .define('7', independentTag("butters"))
                .define('1', Items.EGG)
                .define('2', independentTag("milks"))
                .define('3', independentTag("vanilla"))
                .unlockedBy("has_milks", has(independentTag("milks")))
                .save(exporter);


        shaped(FOOD, WATER_BOTTLE, 16)
                .pattern("   ")
                .pattern("212")
                .pattern(" 2 ")
                .define('2', Items.GLASS)
                .define('1', Items.WATER_BUCKET)
                .unlockedBy("has_water", has(Items.WATER_BUCKET))
                .save(exporter);


        shapeless(FOOD, SNICKER_DOODLE, 4)
                .requires(CINNAMON)
                .requires(Items.SUGAR)
                .requires(independentTag("flour"))
                .unlockedBy("has_flour", has(independentTag("flour")))
                .save(exporter);


        shapeless(FOOD, SOY_MILK)
                .requires(Items.GLASS_BOTTLE)
                .requires(independentTag("soybeans"))
                .requires(FOOD_PRESS)
                .unlockedBy("has_food_press", has(FOOD_PRESS))
                .save(exporter);


        shapeless(FOOD, SOY_SAUCE)
                .requires(FOOD_PRESS)
                .requires(independentTag("soybeans"))
                .requires(independentTag("water_bottles"))
                .unlockedBy("has_soy", has(independentTag("soybeans")))
                .save(exporter);


        shapeless(FOOD, SPAGHETTI_SQUASH)
                .requires(independentTag("squashes"))
                .requires(independentTag("chile_peppers"))
                .requires(independentTag("olive_oils"))
                .requires(FRYING_PAN)
                .unlockedBy("has_frying_pan", has(FRYING_PAN))
                .save(exporter);


        shapeless(FOOD, STEAMED_RICE)
                .requires(independentTag("rice"))
                .requires(COOKING_POT)
                .requires(independentTag("salts"))
                .requires(independentTag("water_bottles"))
                .unlockedBy("has_water_bottles", has(independentTag("water_bottles")))
                .save(exporter);


        shapeless(FOOD, STUFFED_POBLANOS)
                .requires(croptopia("beef_replacements"))
                .requires(independentTag("chile_peppers"))
                .requires(independentTag("blackbeans"))
                .requires(independentTag("corn"))
                .requires(independentTag("cheeses"))
                .requires(independentTag("rice"))
                .requires(COOKING_POT)
                .unlockedBy("has_corn", has(independentTag("corn")))
                .save(exporter);


        shapeless(FOOD, SUPREME_PIZZA)
                .requires(independentTag("doughs"))
                .requires(independentTag("cheeses"))
                .requires(independentTag("tomatoes"))
                .requires(independentTag("bellpeppers"))
                .requires(independentTag("olives"))
                .requires(croptopia("meat_replacements"))
                .requires(FRYING_PAN)
                .unlockedBy("has_frying_pan", has(FRYING_PAN))
                .save(exporter);


        shapeless(FOOD, SUSHI)
                .requires(Items.SEAGRASS)
                .requires(croptopia("fishes"))
                .requires(independentTag("rice"))
                .unlockedBy("has_rice", has(independentTag("rice")))
                .save(exporter);


        shapeless(FOOD, SWEET_POTATO_FRIES)
                .requires(independentTag("sweetpotatos"))
                .requires(COOKING_POT)
                .requires(independentTag("salts"))
                .requires(independentTag("olive_oils"))
                .unlockedBy("has_olives", has(independentTag("olive_oils")))
                .save(exporter);


        shapeless(FOOD, TACO)
                .requires(independentTag("tortillas"))
                .requires(independentTag("cheeses"))
                .requires(independentTag("lettuce"))
                .requires(SALSA)
                .requires(croptopia("meat_replacements"))
                .unlockedBy("has_salsa", has(SALSA))
                .save(exporter);


        shapeless(FOOD, TAMALES, 2)
                .requires(Items.CHICKEN)
                .requires(independentTag("onions"))
                .requires(CORN_HUSK)
                .requires(independentTag("flour"))
                .requires(independentTag("salts"))
                .requires(independentTag("chile_peppers"))
                .requires(COOKING_POT)
                .unlockedBy("has_cooking_pot", has(COOKING_POT))
                .save(exporter);


        shapeless(FOOD, TOAST_WITH_JAM)
                .requires(independentTag("toasts"))
                .requires(independentTag("jams"))
                .unlockedBy("has_jams", has(independentTag("jams")))
                .save(exporter);


        shapeless(FOOD, TOFU)
                .requires(COOKING_POT)
                .requires(independentTag("water_bottles"))
                .requires(independentTag("soybeans"))
                .unlockedBy("has_water_bottles", has(independentTag("water_bottles")))
                .save(exporter);


        shapeless(FOOD, TOFUBURGER)
                .requires(Items.BREAD)
                .requires(independentTag("lettuce"))
                .requires(FRYING_PAN)
                .requires(independentTag("tofu"))
                .requires(independentTag("onions"))
                .unlockedBy("has_onions", has(independentTag("onions")))
                .save(exporter);


        shapeless(FOOD, TOFU_AND_DUMPLINGS)
                .requires(independentTag("doughs"))
                .requires(independentTag("chile_peppers"))
                .requires(independentTag("tofu"))
                .requires(COOKING_POT)
                .unlockedBy("has_pot", has(COOKING_POT))
                .save(exporter);


        shapeless(FOOD, TOSTADA)
                .requires(independentTag("blackbeans"))
                .requires(independentTag("blackbeans"))
                .requires(independentTag("tomatoes"))
                .requires(independentTag("lettuce"))
                .requires(independentTag("tortillas"))
                .unlockedBy("has_tortillas", has(independentTag("tortillas")))
                .save(exporter);


        shapeless(FOOD, TRAIL_MIX, 4)
                .requires(independentTag("nuts"))
                .requires(independentTag("nuts"))
                .requires(independentTag("nuts"))
                .requires(independentTag("raisins"))
                .requires(independentTag("salts"))
                .requires(independentTag("chocolates"))
                .unlockedBy("has_chocolates", has(independentTag("chocolates")))
                .save(exporter);


        shapeless(FOOD, TRES_LECHE_CAKE, 2)
                .requires(Items.EGG)
                .requires(Items.SUGAR)
                .requires(independentTag("milks"))
                .requires(independentTag("flour"))
                .requires(independentTag("vanilla"))
                .requires(independentTag("rums"))
                .requires(WHIPPING_CREAM)
                .unlockedBy("has_whipping_cream", has(WHIPPING_CREAM))
                .save(exporter);


        shapeless(FOOD, VEGGIE_SALAD)
                .requires(Items.BOWL)
                .requires(independentTag("cucumbers"))
                .requires(Items.CARROT)
                .requires(independentTag("corn"))
                .requires(independentTag("lettuce"))
                .unlockedBy("has_lettuce", has(independentTag("lettuce")))
                .save(exporter);


        shapeless(FOOD, WHIPPING_CREAM, 4)
                .requires(independentTag("milks"))
                .requires(Items.SUGAR)
                .requires(independentTag("vanilla"))
                .unlockedBy("has_vanilla", has(independentTag("vanilla")))
                .save(exporter);


        shapeless(FOOD, WINE)
                .requires(Items.GLASS_BOTTLE)
                .requires(independentTag("grapes"))
                .requires(independentTag("grapes"))
                .unlockedBy("has_grapes", has(independentTag("grapes")))
                .requires(FOOD_PRESS)
                .save(exporter);


        shapeless(FOOD, YAM_JAM)
                .requires(independentTag("yams"))
                .requires(independentTag("vanilla"))
                .requires(independentTag("milks"))
                .requires(independentTag("milks"))
                .unlockedBy("has_milks", has(independentTag("milks")))
                .save(exporter);


        shapeless(FOOD, YOGHURT)
                .requires(Items.BOWL)
                .requires(independentTag("milks"))
                .requires(independentTag("strawberries"))
                .unlockedBy("has_milks", has(independentTag("milks")))
                .save(exporter);


        shaped(FOOD, CARROT_CAKE)
                .pattern("111")
                .pattern("232")
                .pattern(" 4 ")
                .define('1', Items.CARROT)
                .define('2', Items.SUGAR)
                .define('3', independentTag("milks"))
                .define('4', independentTag("flour"))
                .unlockedBy("has_carrot", has(Items.CARROT))
                .save(exporter);


        shapeless(FOOD, PICKLED_CUCUMBER, 2)
                .requires(independentTag("water_bottles"))
                .requires(independentTag("salts"))
                .requires(independentTag("cucumbers"))
                .requires(independentTag("cucumbers"))
                .unlockedBy("has_cucumbers", has(independentTag("cucumbers")))
                .save(exporter);


        shapeless(FOOD, PICKLED_BEETS, 2)
                .requires(independentTag("water_bottles"))
                .requires(independentTag("salts"))
                .requires(Items.BEETROOT)
                .requires(Items.BEETROOT)
                .unlockedBy("has_beetroot", has(Items.BEETROOT))
                .save(exporter);


        shapeless(FOOD, PICKLED_RADISH, 2)
                .requires(independentTag("water_bottles"))
                .requires(independentTag("salts"))
                .requires(independentTag("radishes"))
                .requires(independentTag("radishes"))
                .unlockedBy("has_radishes", has(independentTag("radishes")))
                .save(exporter);


        shapeless(FOOD, PICKLED_GARLIC, 2)
                .requires(independentTag("water_bottles"))
                .requires(independentTag("salts"))
                .requires(independentTag("garlic"))
                .requires(independentTag("garlic"))
                .unlockedBy("has_garlic", has(independentTag("garlic")))
                .save(exporter);


        shapeless(FOOD, PICKLED_ONIONS, 2)
                .requires(independentTag("water_bottles"))
                .requires(independentTag("salts"))
                .requires(independentTag("onions"))
                .requires(independentTag("onions"))
                .unlockedBy("has_onions", has(independentTag("onions")))
                .save(exporter);


        shapeless(FOOD, PICKLED_GINGER, 2)
                .requires(independentTag("water_bottles"))
                .requires(independentTag("salts"))
                .requires(independentTag("gingers"))
                .requires(independentTag("gingers"))
                .unlockedBy("has_gingers", has(independentTag("gingers")))
                .save(exporter);


        shapeless(FOOD, KIMCHI, 2)
                .requires(independentTag("water_bottles"))
                .requires(independentTag("salts"))
                .requires(independentTag("cabbage"))
                .requires(independentTag("cabbage"))
                .unlockedBy("has_cabbage", has(independentTag("cabbage")))
                .save(exporter);


        shapeless(FOOD, SAUERKRAUT, 2)
                .requires(independentTag("milks"))
                .requires(independentTag("salts"))
                .requires(independentTag("cabbage"))
                .requires(independentTag("cabbage"))
                .unlockedBy("has_cabbage", has(independentTag("cabbage")))
                .save(exporter);


        shapeless(FOOD, PICKLED_ANCHOVIES, 2)
                .requires(independentTag("water_bottles"))
                .requires(independentTag("salts"))
                .requires(ANCHOVY)
                .requires(ANCHOVY)
                .unlockedBy("has_anchovy", has(ANCHOVY))
                .save(exporter);


        shapeless(FOOD, PICKLED_EGGS, 2)
                .requires(independentTag("water_bottles"))
                .requires(independentTag("salts"))
                .requires(Items.EGG)
                .requires(Items.EGG)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(exporter);


        shaped(FOOD, BIBIMBAP)
                .pattern("123")
                .pattern(" 45")
                .define('1', independentTag("rice"))
                .define('2', independentTag("vegetables"))
                .define('3', Items.BEEF)
                .define('4', SUNNY_SIDE_EGGS)
                .define('5', SOY_SAUCE)
                .unlockedBy("has_sunny_side_eggs", has(SUNNY_SIDE_EGGS))
                .save(exporter);


        shaped(FOOD, BIBIM_NENGMYUM)
                .pattern("123")
                .pattern(" 45")
                .define('1', independentTag("noodles"))
                .define('2', Items.WHEAT)
                .define('3', independentTag("cucumbers"))
                .define('4', PICKLED_RADISH)
                .define('5', Items.EGG)
                .unlockedBy("has_pickled_radish", has(PICKLED_RADISH))
                .save(exporter);


        shapeless(FOOD, EGG_FRIED_RICE)
                .requires(independentTag("rice"))
                .requires(Items.EGG)
                .requires(independentTag("soy_sauces"))
                .requires(independentTag("salts"))
                .unlockedBy("has_rice", has(independentTag("rice")))
                .save(exporter);


        shapeless(FOOD, FRIED_RICE)
                .requires(independentTag("rice"))
                .requires(independentTag("soy_sauces"))
                .requires(croptopia("fried_rice_ingredients"))
                .requires(independentTag("salts"))
                .unlockedBy("has_fried_rice_ingredient", has(croptopia("fried_rice_ingredients")))
                .save(exporter);


        shapeless(FOOD, VEGGIE_FRIED_RICE)
                .requires(independentTag("rice"))
                .requires(independentTag("soy_sauces"))
                .requires(independentTag("greenonions"))
                .requires(Items.CARROT)
                .requires(independentTag("greenbeans"))
                .requires(independentTag("salts"))
                .unlockedBy("has_greenbeans", has(independentTag("greenbeans")))
                .save(exporter);


        shaped(FOOD, ORANGE_CHICKEN)
                .pattern("12")
                .pattern(" 3")
                .define('1', independentTag("oranges"))
                .define('2', Items.CHICKEN)
                .define('3', PEPPER)
                .unlockedBy("has_chicken", has(Items.CHICKEN))
                .save(exporter);


        shaped(FOOD, PINEAPPLE_CHICKEN)
                .pattern("12")
                .define('1', independentTag("pineapples"))
                .define('2', Items.CHICKEN)
                .unlockedBy("has_pineapple", has(independentTag("pineapples")))
                .save(exporter);

        shapeless(FOOD, TERYAKI_CHICKEN)
                .requires(Items.CHICKEN)
                .requires(independentTag("soy_sauces"))
                .requires(PEPPER)
                .unlockedBy("has_soy_sauce", has(independentTag("soy_sauces")))
                .save(exporter);


        shapeless(FOOD, COOKING_OIL)
                .requires(croptopia("converts_to_cooking_oil"))
                .requires(croptopia("converts_to_cooking_oil"))
                .requires(FOOD_PRESS)
                .unlockedBy("has_oil_crop", has(croptopia("converts_to_cooking_oil")))
                .save(exporter);

    }

    private TagKey<Item> croptopia(String name) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MiscNames.MOD_ID, name));
    }

    public static TagKey<Item> independentTag(String name) {
        Identifier location = Identifier.fromNamespaceAndPath("c", name);
        return TagKey.create(Registries.ITEM, location);
    }

    private Criterion<InventoryChangeTrigger.TriggerInstance> hasAll(ItemLike... items) {

        return CriteriaTriggers.INVENTORY_CHANGED.createCriterion(
                new InventoryChangeTrigger.TriggerInstance(
                        Optional.empty(),
                        InventoryChangeTrigger.TriggerInstance.Slots.ANY,
                        Arrays.stream(items)
                                .map(item -> ItemPredicate.Builder.item().of(output.lookup(Registries.ITEM), item).build())
                                .toList()
                )
        );
    }

    public static MultiRegistryBootstrap create() {
        return new MultiRegistryBootstrap() {
            @Override
            public Set<ResourceKey<? extends Registry<?>>> requestedRegistries() {
                return Set.of(Registries.RECIPE, Registries.ADVANCEMENT);
            }

            @Override
            public void run(MultiRegistryBootstrap.BootstrapGetter registries) {
                new CroptopiaRecipeProvider(registries.get(Registries.RECIPE), registries.get(Registries.ADVANCEMENT)).buildRecipes();
            }
        };
    }
}