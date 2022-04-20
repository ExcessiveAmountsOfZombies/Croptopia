package me.thonk.croptopia.datagen;

import com.google.common.collect.ImmutableMap;
import me.thonk.common.ItemNames;
import me.thonk.common.MiscNames;
import me.thonk.croptopia.Croptopia;
import me.thonk.croptopia.mixin.datagen.IdentifierAccessor;
import me.thonk.croptopia.registry.Content;
import me.thonk.croptopia.registry.Content;
import me.thonk.croptopia.util.ItemConvertibleWithPlural;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.Registry;
import net.minecraft.data.recipes.FinishedRecipe;
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


    public CroptopiaRecipeProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateRecipes(Consumer<FinishedRecipe> exporter) {
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

    protected void generateSeeds(Consumer<FinishedRecipe> exporter) {
        for (Content.Farmland crop : Content.Farmland.values()) {
            TagKey<Item> tag = independentTag(crop.getPlural());
            ShapelessRecipeBuilder.shapeless(crop.getSeed())
                    .requires(tag)
                    .unlockedBy("has_" + crop.getLowerCaseName(), RecipeProvider.has(crop))
                    .save(exporter);
        }
    }

    protected void generateSaplings(Consumer<FinishedRecipe> exporter) {
        for (Content.Tree crop : Content.Tree.values()) {
            TagKey<Item> tag = independentTag(crop.getPlural());
            ShapelessRecipeBuilder.shapeless(crop.getSapling())
                    .requires(tag).requires(tag).requires(ItemTags.SAPLINGS)
                    .unlockedBy("has_" + crop.getLowerCaseName(), RecipeProvider.has(crop))
                    .save(exporter);
        }
        // Bark saplings come from the leaves, not the crop
    }

    protected void generateBarkWood(Consumer<FinishedRecipe> exporter) {
        for (Content.Bark crop : Content.Bark.values()) {
            ShapedRecipeBuilder.shaped(crop.getWood())
                    .pattern("##")
                    .pattern("##")
                    .define('#', crop.getLog())
                    .unlockedBy("has_" + crop.getLowerCaseName() + "_log", RecipeProvider.has(crop.getLog()))
                    .save(exporter);
            ShapedRecipeBuilder.shaped(crop.getStrippedWood())
                    .pattern("##")
                    .pattern("##")
                    .define('#', crop.getStrippedLog())
                    .unlockedBy("has_stripped" + crop.getLowerCaseName() + "_log", RecipeProvider.has(crop.getStrippedLog()))
                    .save(exporter);
        }
    }

    protected void generateJams(Consumer<FinishedRecipe> exporter) {
        for (Content.Jam jam : Content.Jam.values()) {
            TagKey<Item> tag = independentTag(jam.getCrop().getPlural());
            ShapelessRecipeBuilder.shapeless(jam)
                    .requires(tag).requires(Items.SUGAR).requires(Content.Utensil.COOKING_POT)
                    .unlockedBy("has_" + jam.name().toLowerCase(), RecipeProvider.has(tag))
                    .save(exporter);
        }
    }

    protected void generateJuices(Consumer<FinishedRecipe> exporter) {
        for (Content.Juice juice : Content.Juice.values()) {
            TagKey<Item> tag = independentTag(juice.getCrop().getPlural());
            ShapelessRecipeBuilder.shapeless(juice)
                    .requires(tag).requires(Content.Utensil.FOOD_PRESS).requires(Items.GLASS_BOTTLE)
                    .unlockedBy("has_" + juice.name().toLowerCase(), RecipeProvider.has(tag))
                    .save(exporter);
        }
    }

    protected void generateSmoothies(Consumer<FinishedRecipe> exporter) {
        for (Content.Smoothie smoothie : Content.Smoothie.values()) {
            TagKey<Item> tag = independentTag(smoothie.getCrop().getPlural());
            ShapelessRecipeBuilder.shapeless(smoothie)
                    .requires(tag).requires(Items.ICE).requires(independentTag("milks")).requires(Items.GLASS_BOTTLE)
                    .unlockedBy("has_" + smoothie.name().toLowerCase(), RecipeProvider.has(tag))
                    .save(exporter);
        }
    }

    protected void generateIceCream(Consumer<FinishedRecipe> exporter) {
        for (Content.IceCream iceCream : Content.IceCream.values()) {
            TagKey<Item> tag = independentTag(iceCream.getCrop().getPlural());
            ShapelessRecipeBuilder.shapeless(iceCream)
                    .requires(tag).requires(Items.SUGAR).requires(Items.EGG).requires(independentTag("milks")).requires(Content.Utensil.COOKING_POT)
                    .unlockedBy("has_" + iceCream.name().toLowerCase(), RecipeProvider.has(tag))
                    .save(exporter);
        }
    }

    protected void generatePie(Consumer<FinishedRecipe> exporter) {
        for (Content.Pie pie : Content.Pie.values()) {
            TagKey<Item> tag = independentTag(pie.getCrop().getPlural());
            ShapelessRecipeBuilder.shapeless(pie)
                    .requires(tag).requires(Items.SUGAR).requires(Items.EGG).requires(independentTag("flour")).requires(independentTag("doughs")).requires(Content.Utensil.FRYING_PAN)
                    .unlockedBy("has_" + pie.name().toLowerCase(), RecipeProvider.has(tag))
                    .save(exporter);
        }
    }

    protected void offerFoodCookingRecipe(Consumer<FinishedRecipe> exporter, ItemLike input, String inputName, ItemLike output, int time, float exp, boolean campFire) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), output, exp, time)
                .unlockedBy("has_" + inputName, RecipeProvider.has(input))
                .save(exporter, RecipeProvider.getItemName(output) + "_from_" + inputName);
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(input), output, exp, time/2)
                .unlockedBy("has_" + inputName, RecipeProvider.has(input))
                .save(exporter, RecipeProvider.getItemName(output) + "_from_smoking_" + inputName);
        // TODO campfire
    }

    protected void generateFurnace(Consumer<FinishedRecipe> exporter) {
        final int time = 200; // default vanilla time
        final float exp = 0.2f; // default vanilla experience
        var cookingList = new ImmutableMap.Builder<ItemConvertibleWithPlural, ItemLike>()
                .put(Content.Farmland.BLACKBEAN, Content.Furnace.BAKED_BEANS)
                .put(Content.Farmland.SWEETPOTATO, Content.Furnace.BAKED_SWEET_POTATO)
                .put(Content.Farmland.YAM, Content.Furnace.BAKED_YAM)
                .put(Content.Seafood.ANCHOVY, Content.Furnace.COOKED_ANCHOVY)
                .put(Content.Seafood.CALAMARI, Content.Furnace.COOKED_CALAMARI)
                .put(Content.Seafood.GLOWING_CALAMARI, Content.Furnace.COOKED_CALAMARI)
                .put(Content.Seafood.SHRIMP, Content.Furnace.COOKED_SHRIMP)
                .put(Content.Seafood.TUNA, Content.Furnace.COOKED_TUNA)
                .put(Content.Farmland.CORN, Content.Furnace.POPCORN)
                .put(Content.Farmland.GRAPE, Content.Furnace.RAISINS)
                .build();
        cookingList.forEach((input, output) -> offerFoodCookingRecipe(exporter, input, input.getLowercaseName(), output, time, exp, true));
        // raw bacon is not yet moved
        offerFoodCookingRecipe(exporter, Content.RAW_BACON, ItemNames.RAW_BACON, Content.Furnace.COOKED_BACON, time, exp, true);
        // now the vanilla ingredients
        offerFoodCookingRecipe(exporter, Items.SUGAR, "sugar", Content.Furnace.CARAMEL, time, exp, true);
        offerFoodCookingRecipe(exporter, Items.SUGAR_CANE, "sugar_cane", Content.Furnace.MOLASSES, time, exp, false);
        offerFoodCookingRecipe(exporter, Items.BREAD, "bread", Content.Furnace.TOAST, time, exp, false);
        // only salt missing
        offerFoodCookingRecipe(exporter,Content.WATER_BOTTLE, ItemNames.WATER_BOTTLE, Content.SALT,800,0.1f, false);
    }

    protected void generateUtensil(Consumer<FinishedRecipe> exporter) {
        ShapedRecipeBuilder.shaped(Content.Utensil.COOKING_POT)
                .pattern("# #")
                .pattern("# #")
                .pattern(" # ")
                .define('#', Items.IRON_INGOT)
                .unlockedBy("has_iron", RecipeProvider.has(Items.IRON_INGOT))
                .save(exporter);
        ShapedRecipeBuilder.shaped(Content.Utensil.FOOD_PRESS)
                .pattern("I")
                .pattern("H")
                .pattern("I")
                .define('I', Items.PISTON).define('H', Items.HOPPER)
                .unlockedBy("has_piston", RecipeProvider.has(Items.PISTON))
                .unlockedBy("has_hopper", RecipeProvider.has(Items.HOPPER))
                .save(exporter);
        ShapedRecipeBuilder.shaped(Content.Utensil.FRYING_PAN)
                .pattern("#  ")
                .pattern(" ##")
                .pattern(" ##")
                .define('#', Items.IRON_INGOT)
                .unlockedBy("has_iron", RecipeProvider.has(Items.IRON_INGOT))
                .save(exporter);
        ShapedRecipeBuilder.shaped(Content.Utensil.KNIFE)
                .pattern(" #")
                .pattern("i ")
                .define('i', Items.STICK).define('#', Items.IRON_INGOT)
                .unlockedBy("has_iron", RecipeProvider.has(Items.IRON_INGOT))
                .save(exporter);
        ShapedRecipeBuilder.shaped(Content.Utensil.MORTAR_AND_PESTLE)
                .pattern("i")
                .pattern("#")
                .define('i', Items.STICK).define('#', Items.BOWL)
                .unlockedBy("has_bowl", RecipeProvider.has(Items.BOWL))
                .save(exporter);
    }

    protected void generateMiscShapeless(Consumer<FinishedRecipe> exporter) {
        TagKey<Item> saltTag = independentTag("salts");
        ShapelessRecipeBuilder.shapeless(Items.DEAD_BUSH)
                .requires(saltTag).requires(ItemTags.SAPLINGS)
                .unlockedBy("has_salts", RecipeProvider.has(saltTag))
                .save(exporter);
        TagKey<Item> kumquatTag = independentTag(Content.Tree.KUMQUAT.getPlural());
        ShapelessRecipeBuilder.shapeless(Content.CANDIED_KUMQUATS, 7)
                .requires(kumquatTag)
                .requires(kumquatTag)
                .requires(kumquatTag)
                .requires(kumquatTag)
                .requires(kumquatTag)
                .requires(kumquatTag)
                .requires(kumquatTag)
                .requires(independentTag("vanilla"))
                .requires(Items.HONEY_BOTTLE)
                .unlockedBy("has_kumquat", RecipeProvider.has(Content.Tree.KUMQUAT))
                .save(exporter);
    }

    protected void generateMiscShaped(Consumer<FinishedRecipe> exporter) {
        ShapedRecipeBuilder.shaped(Content.ROASTED_PUMPKIN_SEEDS)
                .pattern("123")
                .pattern(" 4 ")
                .define('1', Items.PUMPKIN_SEEDS)
                .define('3', Content.Farmland.PEPPER.asItem())
                .define('2', independentTag("salts"))
                .define('4', Content.Utensil.FRYING_PAN)
                .unlockedBy("has_pumpkin_seed", RecipeProvider.has(Items.PUMPKIN_SEEDS))
                .save(exporter);
        ShapedRecipeBuilder.shaped(Content.ROASTED_SUNFLOWER_SEEDS)
                .pattern("123")
                .pattern(" 4 ")
                .define('1', Items.SUNFLOWER)
                .define('3', Content.Farmland.PEPPER.asItem())
                .define('2', independentTag("salts"))
                .define('4', Content.Utensil.FRYING_PAN)
                .unlockedBy("has_sunflower", RecipeProvider.has(Items.SUNFLOWER))
                .save(exporter);
        ShapedRecipeBuilder.shaped(Content.PUMPKIN_BARS, 3)
                .pattern("586")
                .pattern("124")
                .pattern("373")
                .define('1', Items.EGG)
                .define('2', Items.SUGAR)
                .define('3', Items.PUMPKIN)
                .define('4', independentTag("flour"))
                .define('5', Content.Bark.CINNAMON)
                .define('6', independentTag("salts"))
                .define('7', independentTag("butters"))
                .define('8', independentTag("vanilla"))
                .unlockedBy("has_pumpkin", RecipeProvider.has(Items.PUMPKIN))
                .unlockedBy("has_cinnamon", RecipeProvider.has(Content.Bark.CINNAMON))
                .save(exporter);
        ShapedRecipeBuilder.shaped(Content.CORN_BREAD)
                .pattern("111")
                .define('1', independentTag("corn"))
                .unlockedBy("has_corn", RecipeProvider.has(Content.Farmland.CORN.asItem()))
                .save(exporter);
        ShapedRecipeBuilder.shaped(Content.PUMPKIN_SOUP, 2)
                .pattern("123")
                .pattern(" 5 ")
                .pattern("464")
                .define('1', independentTag("onions"))
                .define('2', independentTag("garlic"))
                .define('3', Content.Farmland.PEPPER.asItem())
                .define('4', Items.PUMPKIN)
                .define('5', independentTag("salts"))
                .define('6', Content.Utensil.COOKING_POT)
                .unlockedBy("has_pumpkin", RecipeProvider.has(Items.PUMPKIN))
                .save(exporter);
        ShapedRecipeBuilder.shaped(Content.MERINGUE, 2)
                .pattern("243")
                .pattern("111")
                .define('1', Items.EGG)
                .define('2', independentTag("salts"))
                .define('3', Items.SUGAR)
                .define('4', independentTag("vanilla"))
                .unlockedBy("has_egg", RecipeProvider.has(Items.EGG))
                .save(exporter);
        ShapedRecipeBuilder.shaped(Content.CABBAGE_ROLL, 2)
                .pattern("121")
                .pattern("456")
                .pattern("585")
                .define('8', Content.Utensil.FRYING_PAN)
                .define('1', croptopia("beef_replacements"))
                .define('2', independentTag("onions"))
                .define('6', independentTag("rice"))
                .define('4', independentTag("salts"))
                .define('5', independentTag("cabbage"))
                .unlockedBy("has_cabbage", RecipeProvider.has(Content.Farmland.CABBAGE.asItem()))
                .save(exporter);
        ShapedRecipeBuilder.shaped(Content.BORSCHT, 2)
                .pattern("123")
                .pattern("456")
                .pattern("789")
                .define('1', Items.CARROT)
                .define('2', Items.POTATO)
                .define('3', Items.BEETROOT)
                .define('4', independentTag("onions"))
                .define('5', independentTag("tomatoes"))
                .define('6', independentTag("water_bottles"))
                .define('8', Content.Utensil.COOKING_POT)
                .define('7', independentTag("cabbage"))
                .define('9', independentTag("garlic"))
                .unlockedBy("has_cabbage", RecipeProvider.has(Content.Farmland.CABBAGE.asItem()))
                .save(exporter);
        ShapedRecipeBuilder.shaped(Content.GOULASH)
                .pattern("123")
                .pattern("454")
                .pattern("183")
                .define('8', Content.Utensil.FRYING_PAN)
                .define('1', croptopia("pork_replacements"))
                .define('3', croptopia("beef_replacements"))
                .define('2', independentTag("onions"))
                .define('4', independentTag("cabbage"))
                .define('5', independentTag("tomatoes"))
                .unlockedBy("has_cabbage", RecipeProvider.has(Content.Farmland.CABBAGE.asItem()))
                .save(exporter);
        ShapedRecipeBuilder.shaped(Content.BEETROOT_SALAD)
                .pattern("111")
                .pattern("745")
                .pattern(" 6 ")
                .define('1', Items.BEETROOT)
                .define('4', independentTag("cheeses"))
                .define('5', independentTag("lemons"))
                .define('6', Content.Utensil.COOKING_POT)
                .define('7', independentTag("lettuce"))
                .unlockedBy("has_beetroot", RecipeProvider.has(Items.BEETROOT))
                .save(exporter);
        ShapedRecipeBuilder.shaped(Content.STEAMED_CRAB)
                .pattern("1")
                .pattern("2")
                .pattern("3")
                .define('1', independentTag("crabs"))
                .define('2', independentTag("water_bottles"))
                .define('3', Content.Utensil.COOKING_POT)
                .unlockedBy("has_crab", RecipeProvider.has(Content.Seafood.CRAB))
                .save(exporter);
        ShapedRecipeBuilder.shaped(Content.DEEP_FRIED_SHRIMP, 2)
                .pattern("111")
                .pattern("456")
                .define('1', independentTag("shrimp"))
                .define('4', Items.EGG)
                .define('6', Items.BREAD)
                .define('5', Content.Utensil.FRYING_PAN)
                .unlockedBy("has_shrimp", RecipeProvider.has(Content.Seafood.SHRIMP))
                .save(exporter);
        ShapedRecipeBuilder.shaped(Content.TUNA_ROLL, 2)
                .pattern("234")
                .pattern(" 1 ")
                .define('1', independentTag("tuna"))
                .define('2', Items.DRIED_KELP)
                .define('3', independentTag("rice"))
                .define('4', independentTag("onions"))
                .unlockedBy("has_tuna", RecipeProvider.has(Content.Seafood.TUNA))
                .save(exporter);
        ShapedRecipeBuilder.shaped(Content.FRIED_CALAMARI, 2)
                .pattern("123")
                .pattern("456")
                .define('1', independentTag("calamari"))
                .define('2', independentTag("lemons"))
                .define('3', independentTag("olive_oils"))
                .define('4', independentTag("flour"))
                .define('5', Content.Utensil.FRYING_PAN)
                .define('6', independentTag("sea_lettuce"))
                .unlockedBy("has_calamari", RecipeProvider.has(Content.Seafood.CALAMARI))
                .save(exporter);
        ShapedRecipeBuilder.shaped(Content.CRAB_LEGS, 2)
                .pattern("123")
                .pattern("455")
                .pattern(" 7 ")
                .define('5', independentTag("crabs"))
                .define('1', independentTag("butters"))
                .define('2', independentTag("garlic"))
                .define('3', independentTag("salts"))
                .define('4', Content.Farmland.PEPPER.asItem())
                .define('7', Content.Utensil.FRYING_PAN)
                .unlockedBy("has_crab", RecipeProvider.has(Content.Seafood.CRAB))
                .save(exporter);
        ShapedRecipeBuilder.shaped(Content.STEAMED_CLAMS, 2)
                .pattern("123")
                .pattern("455")
                .pattern(" 7 ")
                .define('5', independentTag("clams"))
                .define('1', independentTag("butters"))
                .define('2', independentTag("garlic"))
                .define('3', independentTag("salts"))
                .define('4', Content.Farmland.PEPPER.asItem())
                .define('7', Content.Utensil.FRYING_PAN)
                .unlockedBy("has_clams", RecipeProvider.has(Content.Seafood.CLAM))
                .save(exporter);
        ShapedRecipeBuilder.shaped(Content.GRILLED_OYSTERS, 2)
                .pattern("121")
                .pattern("456")
                .pattern(" 7 ")
                .define('1', independentTag("oysters"))
                .define('2', independentTag("cheeses"))
                .define('4', independentTag("lemons"))
                .define('5', independentTag("garlic"))
                .define('6', independentTag("salts"))
                .define('7', Content.Utensil.FRYING_PAN)
                .unlockedBy("has_oysters", RecipeProvider.has(Content.GRILLED_OYSTERS))
                .save(exporter);
        ShapedRecipeBuilder.shaped(Content.ANCHOVY_PIZZA, 1)
                .pattern("123")
                .pattern(" 4 ")
                .pattern(" 7 ")
                .define('1', independentTag("tomatoes"))
                .define('2', independentTag("anchovies"))
                .define('3', independentTag("cheeses"))
                .define('4', independentTag("doughs"))
                .define('7', Content.Utensil.FRYING_PAN)
                .unlockedBy("has_anchovies", RecipeProvider.has(Content.Seafood.ANCHOVY))
                .save(exporter);
        ShapedRecipeBuilder.shaped(Content.MASHED_POTATOES, 1)
                .pattern("1 ")
                .pattern("24")
                .pattern("3 ")
                .define('1', independentTag("potatoes"))
                .define('2', independentTag("salts"))
                .define('3', Content.Utensil.MORTAR_AND_PESTLE)
                .define('4', independentTag("milks"))
                .unlockedBy("has_milk", RecipeProvider.has(Items.MILK_BUCKET))
                .save(exporter);
        ShapelessRecipeBuilder.shapeless(Content.TORTILLA, 2)
                .requires(independentTag("flour"))
                .requires(Content.Utensil.FRYING_PAN)
                .requires(independentTag("water_bottles"))
                .unlockedBy("took_flour", RecipeProvider.has(independentTag("flour")))
                .unlockedBy("has_frying_pan", RecipeProvider.has(Content.Utensil.FRYING_PAN))
                .save(exporter);
    }

    private TagKey<Item> croptopia(String name) {
        return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(MiscNames.MOD_ID, name));
    }

    private TagKey<Item> independentTag(String name) {
        IdentifierAccessor accessor = (IdentifierAccessor) Croptopia.createIdentifier(name);
        accessor.setNamespace("${dependent}"); // lmao
        return TagKey.create(Registry.ITEM_REGISTRY, (ResourceLocation) accessor);
    }


}
