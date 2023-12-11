package com.epherical.croptopia.datagen;

import com.epherical.croptopia.Croptopia;
import com.epherical.croptopia.common.ItemNamesV2;
import com.epherical.croptopia.common.MiscNames;
import com.epherical.croptopia.mixin.datagen.IdentifierAccessor;
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
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

public class CroptopiaRecipeProvider extends FabricRecipeProvider {


    public CroptopiaRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(RecipeOutput exporter) {
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
            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, crop.getSeedItem())
                    .requires(tag)
                    .unlockedBy("has_" + crop.getLowercaseName(), RecipeProvider.has(crop))
                    .save(exporter);
        }
    }

    protected void generateSaplings(RecipeOutput exporter) {
        for (TreeCrop crop : TreeCrop.copy()) {
            TagKey<Item> tag = independentTag(crop.getPlural());
            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, crop.getSaplingItem())
                    .requires(tag).requires(tag).requires(ItemTags.SAPLINGS)
                    .unlockedBy("has_" + crop.getLowercaseName(), RecipeProvider.has(crop))
                    .save(exporter);
        }
        // Bark saplings come from the leaves, not the crop
    }

    protected void generateBarkWood(RecipeOutput exporter) {
        for (Tree crop : Tree.copy()) {
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, crop.getWood())
                    .pattern("##")
                    .pattern("##")
                    .define('#', crop.getLog())
                    .unlockedBy("has_" + crop.getLowercaseName() + "_log", RecipeProvider.has(crop.getLog()))
                    .save(exporter);
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, crop.getStrippedWood())
                    .pattern("##")
                    .pattern("##")
                    .define('#', crop.getStrippedLog())
                    .unlockedBy("has_stripped" + crop.getLowercaseName() + "_log", RecipeProvider.has(crop.getStrippedLog()))
                    .save(exporter);
        }
    }

    protected void generateJams(RecipeOutput exporter) {
        for (Jam jam : Jam.copy()) {
            TagKey<Item> tag = independentTag(jam.getCrop().getPlural());
            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, jam)
                    .requires(tag).requires(Items.SUGAR).requires(Content.COOKING_POT)
                    .unlockedBy("has_" + jam.getCrop().getLowercaseName(), RecipeProvider.has(tag))
                    .save(exporter);
        }
    }

    protected void generateJuices(RecipeOutput exporter) {
        for (Juice juice : Juice.copy()) {
            TagKey<Item> tag = independentTag(juice.getCrop().getPlural());
            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, juice)
                    .requires(tag).requires(Content.FOOD_PRESS).requires(Items.GLASS_BOTTLE)
                    .unlockedBy("has_" + juice.getCrop().getLowercaseName(), RecipeProvider.has(tag))
                    .save(exporter);
        }
    }

    protected void generateSmoothies(RecipeOutput exporter) {
        for (Smoothie smoothie : Smoothie.copy()) {
            TagKey<Item> tag = independentTag(smoothie.getCrop().getPlural());
            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, smoothie)
                    .requires(tag).requires(Items.ICE).requires(independentTag("milks")).requires(Items.GLASS_BOTTLE)
                    .unlockedBy("has_" + smoothie.getCrop().getLowercaseName(), RecipeProvider.has(tag))
                    .save(exporter);
        }
    }

    protected void generateIceCream(RecipeOutput exporter) {
        for (IceCream iceCream : IceCream.copy()) {
            TagKey<Item> tag = independentTag(iceCream.getCrop().getPlural());
            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, iceCream)
                    .requires(tag).requires(Items.SUGAR).requires(Items.EGG).requires(independentTag("milks")).requires(Content.COOKING_POT)
                    .unlockedBy("has_" + iceCream.getCrop().getLowercaseName(), RecipeProvider.has(tag))
                    .save(exporter);
        }
    }

    protected void generatePie(RecipeOutput exporter) {
        for (Pie pie : Pie.copy()) {
            TagKey<Item> tag = independentTag(pie.getCrop().getPlural());
            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, pie)
                    .requires(tag).requires(Items.SUGAR).requires(Items.EGG).requires(independentTag("flour")).requires(independentTag("doughs")).requires(Content.FRYING_PAN)
                    .unlockedBy("has_" + pie.getCrop().getLowercaseName(), RecipeProvider.has(tag))
                    .save(exporter);
        }
    }

    protected void offerFoodCookingRecipe(RecipeOutput exporter, ItemLike input, String inputName, ItemLike output, int time, float exp, boolean campFire) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), RecipeCategory.FOOD, output, exp, time)
                .unlockedBy("has_" + inputName, RecipeProvider.has(input))
                .save(exporter, RecipeProvider.getItemName(output) + "_from_" + inputName);
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(input), RecipeCategory.FOOD, output, exp, time / 2)
                .unlockedBy("has_" + inputName, RecipeProvider.has(input))
                .save(exporter, RecipeProvider.getItemName(output) + "_from_smoking_" + inputName);
        // TODO campfire
    }

    protected void generateFurnace(RecipeOutput exporter) {
        final int time = 200; // default vanilla time
        final float exp = 0.2f; // default vanilla experience
        var cookingList = new ImmutableMap.Builder<ItemConvertibleWithPlural, ItemLike>()
                .put(Content.BLACKBEAN, Content.BAKED_BEANS)
                .put(Content.SWEETPOTATO, Content.BAKED_SWEET_POTATO)
                .put(Content.YAM, Content.BAKED_YAM)
                .put(Content.ANCHOVY, Content.COOKED_ANCHOVY)
                .put(Content.CALAMARI, Content.COOKED_CALAMARI)
                .put(Content.GLOWING_CALAMARI, Content.COOKED_CALAMARI)
                .put(Content.SHRIMP, Content.COOKED_SHRIMP)
                .put(Content.TUNA, Content.COOKED_TUNA)
                .put(Content.CORN, Content.POPCORN)
                .put(Content.GRAPE, Content.RAISINS)
                .build();
        cookingList.forEach((input, output) -> offerFoodCookingRecipe(exporter, input, input.getLowercaseName(), output, time, exp, true));
        // raw bacon is not yet moved
        offerFoodCookingRecipe(exporter, Content.RAW_BACON, ItemNamesV2.RAW_BACON, Content.COOKED_BACON, time, exp, true);
        // now the vanilla ingredients
        offerFoodCookingRecipe(exporter, Items.SUGAR, "sugar", Content.CARAMEL, time, exp, true);
        offerFoodCookingRecipe(exporter, Items.SUGAR_CANE, "sugar_cane", Content.MOLASSES, time, exp, false);
        offerFoodCookingRecipe(exporter, Items.BREAD, "bread", Content.TOAST, time, exp, false);
        // only salt missing
        offerFoodCookingRecipe(exporter, Content.WATER_BOTTLE, ItemNamesV2.WATER_BOTTLE, Content.SALT, 800, 0.1f, false);
    }

    protected void generateUtensil(RecipeOutput exporter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Content.COOKING_POT)
                .pattern("# #")
                .pattern("# #")
                .pattern(" # ")
                .define('#', Items.IRON_INGOT)
                .unlockedBy("has_iron", RecipeProvider.has(Items.IRON_INGOT))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Content.FOOD_PRESS)
                .pattern("I")
                .pattern("H")
                .pattern("I")
                .define('I', Items.PISTON).define('H', Items.HOPPER)
                .unlockedBy("has_piston", RecipeProvider.has(Items.PISTON))
                .unlockedBy("has_hopper", RecipeProvider.has(Items.HOPPER))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Content.FRYING_PAN)
                .pattern("#  ")
                .pattern(" ##")
                .pattern(" ##")
                .define('#', Items.IRON_INGOT)
                .unlockedBy("has_iron", RecipeProvider.has(Items.IRON_INGOT))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Content.KNIFE)
                .pattern(" #")
                .pattern("i ")
                .define('i', Items.STICK).define('#', Items.IRON_INGOT)
                .unlockedBy("has_iron", RecipeProvider.has(Items.IRON_INGOT))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Content.MORTAR_AND_PESTLE)
                .pattern("i")
                .pattern("#")
                .define('i', Items.STICK).define('#', Items.BOWL)
                .unlockedBy("has_bowl", RecipeProvider.has(Items.BOWL))
                .save(exporter);
    }

    protected void generateMiscShapeless(RecipeOutput exporter) {
        TagKey<Item> saltTag = independentTag("salts");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.DEAD_BUSH)
                .requires(saltTag).requires(ItemTags.SAPLINGS)
                .unlockedBy("has_salts", RecipeProvider.has(saltTag))
                .save(exporter);
        TagKey<Item> kumquatTag = independentTag(Content.KUMQUAT.getPlural());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Content.CANDIED_KUMQUATS, 7)
                .requires(kumquatTag)
                .requires(kumquatTag)
                .requires(kumquatTag)
                .requires(kumquatTag)
                .requires(kumquatTag)
                .requires(kumquatTag)
                .requires(kumquatTag)
                .requires(independentTag("vanilla"))
                .requires(Items.HONEY_BOTTLE)
                .unlockedBy("has_kumquat", RecipeProvider.has(Content.KUMQUAT))
                .save(exporter);
        TagKey<Item> turmericTag = independentTag(Content.TURMERIC.getPlural());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.ORANGE_DYE, 2)
                .requires(turmericTag)
                .requires(turmericTag)
                .requires(turmericTag)
                .unlockedBy("has_turmeric", RecipeProvider.has(Content.TURMERIC))
                .save(exporter);
        TagKey<Item> grapeTag = independentTag(Content.GRAPE.getPlural());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.PURPLE_DYE, 2)
                .requires(grapeTag)
                .requires(grapeTag)
                .requires(grapeTag)
                .unlockedBy("has_grape", RecipeProvider.has(Content.GRAPE))
                .save(exporter);
    }

    protected void generateMiscShaped(RecipeOutput exporter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Content.ROASTED_PUMPKIN_SEEDS)
                .pattern("123")
                .pattern(" 4 ")
                .define('1', Items.PUMPKIN_SEEDS)
                .define('3', Content.PEPPER.asItem())
                .define('2', independentTag("salts"))
                .define('4', Content.FRYING_PAN)
                .unlockedBy("has_pumpkin_seed", RecipeProvider.has(Items.PUMPKIN_SEEDS))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Content.ROASTED_SUNFLOWER_SEEDS)
                .pattern("123")
                .pattern(" 4 ")
                .define('1', Items.SUNFLOWER)
                .define('3', Content.PEPPER.asItem())
                .define('2', independentTag("salts"))
                .define('4', Content.FRYING_PAN)
                .unlockedBy("has_sunflower", RecipeProvider.has(Items.SUNFLOWER))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Content.PUMPKIN_BARS, 3)
                .pattern("586")
                .pattern("124")
                .pattern("373")
                .define('1', Items.EGG)
                .define('2', Items.SUGAR)
                .define('3', Items.PUMPKIN)
                .define('4', independentTag("flour"))
                .define('5', Content.CINNAMON)
                .define('6', independentTag("salts"))
                .define('7', independentTag("butters"))
                .define('8', independentTag("vanilla"))
                .unlockedBy("has_pumpkin", RecipeProvider.has(Items.PUMPKIN))
                .unlockedBy("has_cinnamon", RecipeProvider.has(Content.CINNAMON))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Content.CORN_BREAD)
                .pattern("111")
                .define('1', independentTag("corn"))
                .unlockedBy("has_corn", RecipeProvider.has(Content.CORN.asItem()))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Content.PUMPKIN_SOUP, 2)
                .pattern("123")
                .pattern(" 5 ")
                .pattern("464")
                .define('1', independentTag("onions"))
                .define('2', independentTag("garlic"))
                .define('3', Content.PEPPER.asItem())
                .define('4', Items.PUMPKIN)
                .define('5', independentTag("salts"))
                .define('6', Content.COOKING_POT)
                .unlockedBy("has_pumpkin", RecipeProvider.has(Items.PUMPKIN))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Content.MERINGUE, 2)
                .pattern("243")
                .pattern("111")
                .define('1', Items.EGG)
                .define('2', independentTag("salts"))
                .define('3', Items.SUGAR)
                .define('4', independentTag("vanilla"))
                .unlockedBy("has_egg", RecipeProvider.has(Items.EGG))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Content.CABBAGE_ROLL, 2)
                .pattern("121")
                .pattern("456")
                .pattern("585")
                .define('8', Content.FRYING_PAN)
                .define('1', croptopia("beef_replacements"))
                .define('2', independentTag("onions"))
                .define('6', independentTag("rice"))
                .define('4', independentTag("salts"))
                .define('5', independentTag("cabbage"))
                .unlockedBy("has_cabbage", RecipeProvider.has(Content.CABBAGE.asItem()))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Content.BORSCHT, 2)
                .pattern("123")
                .pattern("456")
                .pattern("789")
                .define('1', Items.CARROT)
                .define('2', Items.POTATO)
                .define('3', Items.BEETROOT)
                .define('4', independentTag("onions"))
                .define('5', independentTag("tomatoes"))
                .define('6', independentTag("water_bottles"))
                .define('8', Content.COOKING_POT)
                .define('7', independentTag("cabbage"))
                .define('9', independentTag("garlic"))
                .unlockedBy("has_cabbage", RecipeProvider.has(Content.CABBAGE.asItem()))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Content.GOULASH)
                .pattern("123")
                .pattern("454")
                .pattern("183")
                .define('8', Content.FRYING_PAN)
                .define('1', croptopia("pork_replacements"))
                .define('3', croptopia("beef_replacements"))
                .define('2', independentTag("onions"))
                .define('4', independentTag("cabbage"))
                .define('5', independentTag("tomatoes"))
                .unlockedBy("has_cabbage", RecipeProvider.has(Content.CABBAGE.asItem()))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Content.BEETROOT_SALAD)
                .pattern("111")
                .pattern("745")
                .pattern(" 6 ")
                .define('1', Items.BEETROOT)
                .define('4', independentTag("cheeses"))
                .define('5', independentTag("lemons"))
                .define('6', Content.COOKING_POT)
                .define('7', independentTag("lettuce"))
                .unlockedBy("has_beetroot", RecipeProvider.has(Items.BEETROOT))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Content.STEAMED_CRAB)
                .pattern("1")
                .pattern("2")
                .pattern("3")
                .define('1', independentTag("crabs"))
                .define('2', independentTag("water_bottles"))
                .define('3', Content.COOKING_POT)
                .unlockedBy("has_crab", RecipeProvider.has(Content.CRAB))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Content.DEEP_FRIED_SHRIMP, 2)
                .pattern("111")
                .pattern("456")
                .define('1', independentTag("shrimp"))
                .define('4', Items.EGG)
                .define('6', Items.BREAD)
                .define('5', Content.FRYING_PAN)
                .unlockedBy("has_shrimp", RecipeProvider.has(Content.SHRIMP))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Content.TUNA_ROLL, 2)
                .pattern("234")
                .pattern(" 1 ")
                .define('1', independentTag("tuna"))
                .define('2', Items.DRIED_KELP)
                .define('3', independentTag("rice"))
                .define('4', independentTag("onions"))
                .unlockedBy("has_tuna", RecipeProvider.has(Content.TUNA))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Content.FRIED_CALAMARI, 2)
                .pattern("123")
                .pattern("456")
                .define('1', independentTag("calamari"))
                .define('2', independentTag("lemons"))
                .define('3', independentTag("olive_oils"))
                .define('4', independentTag("flour"))
                .define('5', Content.FRYING_PAN)
                .define('6', independentTag("sea_lettuce"))
                .unlockedBy("has_calamari", RecipeProvider.has(Content.CALAMARI))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Content.CRAB_LEGS, 2)
                .pattern("123")
                .pattern("455")
                .pattern(" 7 ")
                .define('5', independentTag("crabs"))
                .define('1', independentTag("butters"))
                .define('2', independentTag("garlic"))
                .define('3', independentTag("salts"))
                .define('4', Content.PEPPER.asItem())
                .define('7', Content.FRYING_PAN)
                .unlockedBy("has_crab", RecipeProvider.has(Content.CRAB))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Content.STEAMED_CLAMS, 2)
                .pattern("123")
                .pattern("455")
                .pattern(" 7 ")
                .define('5', independentTag("clams"))
                .define('1', independentTag("butters"))
                .define('2', independentTag("garlic"))
                .define('3', independentTag("salts"))
                .define('4', Content.PEPPER.asItem())
                .define('7', Content.FRYING_PAN)
                .unlockedBy("has_clams", RecipeProvider.has(Content.CLAM))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Content.GRILLED_OYSTERS, 2)
                .pattern("121")
                .pattern("456")
                .pattern(" 7 ")
                .define('1', independentTag("oysters"))
                .define('2', independentTag("cheeses"))
                .define('4', independentTag("lemons"))
                .define('5', independentTag("garlic"))
                .define('6', independentTag("salts"))
                .define('7', Content.FRYING_PAN)
                .unlockedBy("has_oysters", RecipeProvider.has(Content.GRILLED_OYSTERS))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Content.ANCHOVY_PIZZA, 1)
                .pattern("123")
                .pattern(" 4 ")
                .pattern(" 7 ")
                .define('1', independentTag("tomatoes"))
                .define('2', independentTag("anchovies"))
                .define('3', independentTag("cheeses"))
                .define('4', independentTag("doughs"))
                .define('7', Content.FRYING_PAN)
                .unlockedBy("has_anchovies", RecipeProvider.has(Content.ANCHOVY))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Content.MASHED_POTATOES, 1)
                .pattern("1 ")
                .pattern("24")
                .pattern("3 ")
                .define('1', independentTag("potatoes"))
                .define('2', independentTag("salts"))
                .define('3', Content.MORTAR_AND_PESTLE)
                .define('4', independentTag("milks"))
                .unlockedBy("has_milk", RecipeProvider.has(Items.MILK_BUCKET))
                .save(exporter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Content.TORTILLA, 2)
                .requires(independentTag("flour"))
                .requires(Content.FRYING_PAN)
                .requires(independentTag("water_bottles"))
                .unlockedBy("took_flour", RecipeProvider.has(independentTag("flour")))
                .unlockedBy("has_frying_pan", RecipeProvider.has(Content.FRYING_PAN))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Content.SWEET_CREPES, 1)
                .pattern("123")
                .pattern("4 5")
                .pattern(" 6 ")
                .define('1', independentTag("flour"))
                .define('2', Items.EGG)
                .define('3', independentTag("milks"))
                .define('4', independentTag("jams"))
                .define('5', Items.SUGAR)
                .define('6', Content.FRYING_PAN)
                .unlockedBy("took_flour", RecipeProvider.has(independentTag("flour")))
                .unlockedBy("has_frying_pan", RecipeProvider.has(Content.FRYING_PAN))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Content.BAKED_CREPES, 1)
                .pattern("121")
                .pattern("356")
                .pattern(" 7 ")
                .define('1', Items.EGG)
                .define('2', independentTag("flour"))
                .define('3', independentTag("milks"))
                .define('7', Content.FRYING_PAN)
                .define('6', independentTag("cheeses"))
                .define('5', independentTag("spinach"))
                .unlockedBy("has_frying_pan", RecipeProvider.has(Content.FRYING_PAN))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Content.QUICHE, 1)
                .pattern(" 1 ")
                .pattern("234")
                .pattern("5 6")
                .define('1', Content.FRYING_PAN)
                .define('5', independentTag("flour"))
                .define('6', independentTag("onions"))
                .define('2', independentTag("milks"))
                .define('3', Items.EGG)
                .define('4', independentTag("spinach"))
                .unlockedBy("has_frying_pan", RecipeProvider.has(Content.FRYING_PAN))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Content.DAUPHINE_POTATOES, 1)
                .pattern("213")
                .pattern("456")
                .define('1', Content.FRYING_PAN)
                .define('2', independentTag("water_bottles"))
                .define('3', independentTag("milks"))
                .define('4', independentTag("butters"))
                .define('5', independentTag("flour"))
                .define('6', independentTag("olive_oils"))
                .unlockedBy("has_frying_pan", RecipeProvider.has(Content.FRYING_PAN))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Content.CROQUE_MONSIEUR, 1)
                .pattern(" 1 ")
                .pattern(" 26")
                .pattern("435")
                .define('1', Content.FRYING_PAN)
                .define('2', Items.BREAD)
                .define('3', independentTag("cheeses"))
                .define('4', croptopia("pork_replacements"))
                .define('5', independentTag("butters"))
                .define('6', independentTag("flour"))
                .unlockedBy("has_frying_pan", RecipeProvider.has(Content.FRYING_PAN))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Content.CROQUE_MADAME, 1)
                .pattern(" 1 ")
                .pattern("726")
                .pattern("435")
                .define('1', Content.FRYING_PAN)
                .define('2', Items.BREAD)
                .define('3', independentTag("cheeses"))
                .define('4', croptopia("pork_replacements"))
                .define('5', independentTag("butters"))
                .define('6', independentTag("flour"))
                .define('7', Items.EGG)
                .unlockedBy("has_frying_pan", RecipeProvider.has(Content.FRYING_PAN))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Content.SUNNY_SIDE_EGGS, 2)
                .pattern("121")
                .define('2', Content.FRYING_PAN)
                .define('1', Items.EGG)
                .unlockedBy("has_frying_pan", RecipeProvider.has(Content.FRYING_PAN))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Content.MACARON, 2)
                .pattern("122")
                .pattern("565")
                .define('1', Items.EGG)
                .define('2', Items.SUGAR)
                .define('5', independentTag("almonds"))
                .define('6', Content.FOOD_PRESS)
                .unlockedBy("has_food_press", RecipeProvider.has(Content.FOOD_PRESS))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Content.THE_BIG_BREAKFAST, 1)
                .pattern("123")
                .pattern("736")
                .pattern(" 45")
                .define('7', Content.FRYING_PAN)
                .define('1', Items.EGG)
                .define('2', Content.RAW_BACON)
                .define('3', Content.HASHED_BROWN)
                .define('4', Content.BAKED_BEANS)
                .define('5', independentTag("sausages"))
                .define('6', Content.TOAST)
                .unlockedBy("has_frying_pan", RecipeProvider.has(Content.FRYING_PAN))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Content.GROUND_PORK, 2)
                .pattern("1")
                .pattern("2")
                .define('1', croptopia("pork_replacements"))
                .define('2', Content.FOOD_PRESS)
                .unlockedBy("has_food_press", RecipeProvider.has(Content.FOOD_PRESS))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Content.SAUSAGE, 1)
                .pattern("1")
                .pattern("2")
                .pattern("3")
                .define('1', independentTag("ground_pork"))
                .define('2', independentTag("salts"))
                .define('3', independentTag("paprika"))
                .unlockedBy("has_ground_pork", RecipeProvider.has(Content.GROUND_PORK))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Content.CINNAMON_ROLL, 3)
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
                .define('8', Content.WHIPPING_CREAM)
                .define('9', Content.FRYING_PAN)
                .unlockedBy("has_frying_pan", RecipeProvider.has(Content.FRYING_PAN))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Content.HASHED_BROWN, 4)
                .pattern("123")
                .pattern(" 4 ")
                .define('4', Content.KNIFE)
                .define('1', independentTag("potatoes"))
                .define('2', Content.FRYING_PAN)
                .define('3', independentTag("olive_oils"))
                .unlockedBy("has_frying_pan", RecipeProvider.has(Content.FRYING_PAN))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, Content.BEEF_JERKY, 14)
                .pattern("111")
                .pattern("121")
                .pattern("111")
                .define('1', Items.BEEF)
                .define('2', independentTag("salts"))
                .unlockedBy("has_salt", RecipeProvider.has(Content.SALT))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, Content.PORK_JERKY, 14)
                .pattern("111")
                .pattern("121")
                .pattern("111")
                .define('1', Items.PORKCHOP)
                .define('2', independentTag("salts"))
                .unlockedBy("has_salt", RecipeProvider.has(Content.SALT))
                .save(exporter);
        //cooked frog leg	furnace

    }

    private TagKey<Item> croptopia(String name) {
        return TagKey.create(Registries.ITEM, new ResourceLocation(MiscNames.MOD_ID, name));
    }

    public static TagKey<Item> independentTag(String name) {
        IdentifierAccessor accessor = (IdentifierAccessor) Croptopia.createIdentifier(name);
        accessor.setNamespace("${dependent}"); // lmao
        return TagKey.create(Registries.ITEM, (ResourceLocation) accessor);
    }

}
