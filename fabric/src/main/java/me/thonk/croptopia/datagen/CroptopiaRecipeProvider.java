package me.thonk.croptopia.datagen;

import com.google.common.collect.ImmutableMap;
import me.thonk.common.ItemNames;
import me.thonk.common.MiscNames;
import me.thonk.croptopia.Croptopia;
import me.thonk.croptopia.mixin.datagen.IdentifierAccessor;
import me.thonk.croptopia.registry.Content;
import me.thonk.croptopia.registry.ItemRegistry;
import me.thonk.croptopia.util.ItemConvertibleWithPlural;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.RecipeProvider;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Consumer;

public class CroptopiaRecipeProvider extends FabricRecipeProvider {


    public CroptopiaRecipeProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateRecipes(Consumer<RecipeJsonProvider> exporter) {
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

    protected void generateSeeds(Consumer<RecipeJsonProvider> exporter) {
        for (Content.Farmland crop : Content.Farmland.values()) {
            TagKey<Item> tag = independentTag(crop.getPlural());
            ShapelessRecipeJsonBuilder.create(crop.getSeed())
                    .input(tag)
                    .criterion("has_" + crop.getLowerCaseName(), RecipeProvider.conditionsFromItem(crop))
                    .offerTo(exporter);
        }
    }

    protected void generateSaplings(Consumer<RecipeJsonProvider> exporter) {
        for (Content.Tree crop : Content.Tree.values()) {
            TagKey<Item> tag = independentTag(crop.getPlural());
            ShapelessRecipeJsonBuilder.create(crop.getSapling())
                    .input(tag).input(tag).input(ItemTags.SAPLINGS)
                    .criterion("has_" + crop.getLowerCaseName(), RecipeProvider.conditionsFromItem(crop))
                    .offerTo(exporter);
        }
        // Bark saplings come from the leaves, not the crop
    }

    protected void generateBarkWood(Consumer<RecipeJsonProvider> exporter) {
        for (Content.Bark crop : Content.Bark.values()) {
            ShapedRecipeJsonBuilder.create(crop.getWood())
                    .pattern("##")
                    .pattern("##")
                    .input('#', crop.getLog())
                    .criterion("has_" + crop.getLowerCaseName() + "_log", RecipeProvider.conditionsFromItem(crop.getLog()))
                    .offerTo(exporter);
            ShapedRecipeJsonBuilder.create(crop.getStrippedWood())
                    .pattern("##")
                    .pattern("##")
                    .input('#', crop.getStrippedLog())
                    .criterion("has_stripped" + crop.getLowerCaseName() + "_log", RecipeProvider.conditionsFromItem(crop.getStrippedLog()))
                    .offerTo(exporter);
        }
    }

    protected void generateJams(Consumer<RecipeJsonProvider> exporter) {
        for (Content.Jam jam : Content.Jam.values()) {
            TagKey<Item> tag = independentTag(jam.getCrop().getPlural());
            ShapelessRecipeJsonBuilder.create(jam)
                    .input(tag).input(Items.SUGAR).input(Content.Utensil.COOKING_POT)
                    .criterion("has_" + jam.name().toLowerCase(), RecipeProvider.conditionsFromTag(tag))
                    .offerTo(exporter);
        }
    }

    protected void generateJuices(Consumer<RecipeJsonProvider> exporter) {
        for (Content.Juice juice : Content.Juice.values()) {
            TagKey<Item> tag = independentTag(juice.getCrop().getPlural());
            ShapelessRecipeJsonBuilder.create(juice)
                    .input(tag).input(Content.Utensil.FOOD_PRESS).input(Items.GLASS_BOTTLE)
                    .criterion("has_" + juice.name().toLowerCase(), RecipeProvider.conditionsFromTag(tag))
                    .offerTo(exporter);
        }
    }

    protected void generateSmoothies(Consumer<RecipeJsonProvider> exporter) {
        for (Content.Smoothie smoothie : Content.Smoothie.values()) {
            TagKey<Item> tag = independentTag(smoothie.getCrop().getPlural());
            ShapelessRecipeJsonBuilder.create(smoothie)
                    .input(tag).input(Items.ICE).input(independentTag("milks")).input(Items.GLASS_BOTTLE)
                    .criterion("has_" + smoothie.name().toLowerCase(), RecipeProvider.conditionsFromTag(tag))
                    .offerTo(exporter);
        }
    }

    protected void generateIceCream(Consumer<RecipeJsonProvider> exporter) {
        for (Content.IceCream iceCream : Content.IceCream.values()) {
            TagKey<Item> tag = independentTag(iceCream.getCrop().getPlural());
            ShapelessRecipeJsonBuilder.create(iceCream)
                    .input(tag).input(Items.SUGAR).input(Items.EGG).input(independentTag("milks")).input(Content.Utensil.COOKING_POT)
                    .criterion("has_" + iceCream.name().toLowerCase(), RecipeProvider.conditionsFromTag(tag))
                    .offerTo(exporter);
        }
    }

    protected void generatePie(Consumer<RecipeJsonProvider> exporter) {
        for (Content.Pie pie : Content.Pie.values()) {
            TagKey<Item> tag = independentTag(pie.getCrop().getPlural());
            ShapelessRecipeJsonBuilder.create(pie)
                    .input(tag).input(Items.SUGAR).input(Items.EGG).input(independentTag("flour")).input(independentTag("doughs")).input(Content.Utensil.FRYING_PAN)
                    .criterion("has_" + pie.name().toLowerCase(), RecipeProvider.conditionsFromTag(tag))
                    .offerTo(exporter);
        }
    }

    protected void offerFoodCookingRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, String inputName, ItemConvertible output, int time, float exp, boolean campFire) {
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(input), output, exp, time)
                .criterion("has_" + inputName, RecipeProvider.conditionsFromItem(input))
                .offerTo(exporter, RecipeProvider.getItemPath(output) + "_from_" + inputName);
        CookingRecipeJsonBuilder.createSmoking(Ingredient.ofItems(input), output, exp, time/2)
                .criterion("has_" + inputName, RecipeProvider.conditionsFromItem(input))
                .offerTo(exporter, RecipeProvider.getItemPath(output) + "_from_smoking_" + inputName);
        // TODO campfire
    }

    protected void generateFurnace(Consumer<RecipeJsonProvider> exporter) {
        final int time = 200; // default vanilla time
        final float exp = 0.2f; // default vanilla experience
        var cookingList = new ImmutableMap.Builder<ItemConvertibleWithPlural, ItemConvertible>()
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
        offerFoodCookingRecipe(exporter, ItemRegistry.rawBacon, ItemNames.RAW_BACON, Content.Furnace.COOKED_BACON, time, exp, true);
        // now the vanilla ingredients
        offerFoodCookingRecipe(exporter, Items.SUGAR, "sugar", Content.Furnace.CARAMEL, time, exp, true);
        offerFoodCookingRecipe(exporter, Items.SUGAR_CANE, "sugar_cane", Content.Furnace.MOLASSES, time, exp, false);
        offerFoodCookingRecipe(exporter, Items.BREAD, "bread", Content.Furnace.TOAST, time, exp, false);
        // only salt missing
        offerFoodCookingRecipe(exporter,ItemRegistry.waterBottle, ItemNames.WATER_BOTTLE, ItemRegistry.salt,800,0.1f, false);
    }

    protected void generateUtensil(Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(Content.Utensil.COOKING_POT)
                .pattern("# #")
                .pattern("# #")
                .pattern(" # ")
                .input('#', Items.IRON_INGOT)
                .criterion("has_iron", RecipeProvider.conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(Content.Utensil.FOOD_PRESS)
                .pattern("I")
                .pattern("H")
                .pattern("I")
                .input('I', Items.PISTON).input('H', Items.HOPPER)
                .criterion("has_piston", RecipeProvider.conditionsFromItem(Items.PISTON))
                .criterion("has_hopper", RecipeProvider.conditionsFromItem(Items.HOPPER))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(Content.Utensil.FRYING_PAN)
                .pattern("#  ")
                .pattern(" ##")
                .pattern(" ##")
                .input('#', Items.IRON_INGOT)
                .criterion("has_iron", RecipeProvider.conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(Content.Utensil.KNIFE)
                .pattern(" #")
                .pattern("i ")
                .input('i', Items.STICK).input('#', Items.IRON_INGOT)
                .criterion("has_iron", RecipeProvider.conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(Content.Utensil.MORTAR_AND_PESTLE)
                .pattern("i")
                .pattern("#")
                .input('i', Items.STICK).input('#', Items.BOWL)
                .criterion("has_bowl", RecipeProvider.conditionsFromItem(Items.BOWL))
                .offerTo(exporter);
    }

    protected void generateMiscShapeless(Consumer<RecipeJsonProvider> exporter) {
        ShapelessRecipeJsonBuilder.create(ItemRegistry.candiedKumquats, 7)
                .independentTag(tag("kumquat"))
                .independentTag(tag("kumquat"))
                .independentTag(tag("kumquat"))
                .independentTag(tag("kumquat"))
                .independentTag(tag("kumquat"))
                .independentTag(tag("kumquat"))
                .independentTag(tag("kumquat"))
                .independentTag(tag("vanilla"))
                .independentTag(Items.HONEY_BOTTLE)
                .criterion("has_kumquat", RecipeProvider.conditionsFromItem(Content.Tree.KUMQUAT.asItem()))
                .offerTo(exporter);

    }

    protected void generateMiscShaped(Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(ItemRegistry.roastedPumpkinSeeds)
                .pattern("123")
                .pattern(" 4 ")
                .input('1', Items.PUMPKIN_SEEDS)
                .input('3', Content.Farmland.PEPPER.asItem())
                .input('2', independentTag("salts"))
                .input('4', Content.Utensil.FRYING_PAN)
                .criterion("has_pumpkin_seed", RecipeProvider.conditionsFromItem(Items.PUMPKIN_SEEDS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(ItemRegistry.roastedSunflowerSeeds)
                .pattern("123")
                .pattern(" 4 ")
                .input('1', Items.SUNFLOWER)
                .input('3', Content.Farmland.PEPPER.asItem())
                .input('2', independentTag("salts"))
                .input('4', Content.Utensil.FRYING_PAN)
                .criterion("has_sunflower", RecipeProvider.conditionsFromItem(Items.SUNFLOWER))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(ItemRegistry.pumpkinBars, 3)
                .pattern("586")
                .pattern("124")
                .pattern("373")
                .input('1', Items.EGG)
                .input('2', Items.SUGAR)
                .input('3', Items.PUMPKIN)
                .input('4', independentTag("flour"))
                .input('5', Content.Bark.CINNAMON)
                .input('6', independentTag("salts"))
                .input('7', independentTag("butters"))
                .input('8', independentTag("vanilla"))
                .criterion("has_pumpkin", RecipeProvider.conditionsFromItem(Items.PUMPKIN))
                .criterion("has_cinnamon", RecipeProvider.conditionsFromItem(Content.Bark.CINNAMON))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(ItemRegistry.cornBread)
                .pattern("111")
                .input('1', independentTag("corn"))
                .criterion("has_corn", RecipeProvider.conditionsFromItem(Content.Farmland.CORN.asItem()))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(ItemRegistry.pumpkinSoup, 2)
                .pattern("123")
                .pattern(" 5 ")
                .pattern("464")
                .input('1', independentTag("onions"))
                .input('2', independentTag("garlic"))
                .input('3', Content.Farmland.PEPPER.asItem())
                .input('4', Items.PUMPKIN)
                .input('5', independentTag("salts"))
                .input('6', Content.Utensil.COOKING_POT)
                .criterion("has_pumpkin", RecipeProvider.conditionsFromItem(Items.PUMPKIN))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(ItemRegistry.meringue, 2)
                .pattern("243")
                .pattern("111")
                .input('1', Items.EGG)
                .input('2', independentTag("salts"))
                .input('3', Items.SUGAR)
                .input('4', independentTag("vanilla"))
                .criterion("has_egg", RecipeProvider.conditionsFromItem(Items.EGG))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(ItemRegistry.cabbageRoll, 2)
                .pattern("121")
                .pattern("456")
                .pattern("585")
                .input('8', Content.Utensil.FRYING_PAN)
                .input('1', croptopia("beef_replacements"))
                .input('2', independentTag("onions"))
                .input('6', independentTag("rice"))
                .input('4', independentTag("salts"))
                .input('5', independentTag("cabbage"))
                .criterion("has_cabbage", RecipeProvider.conditionsFromItem(Content.Farmland.CABBAGE.asItem()))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(ItemRegistry.borscht, 2)
                .pattern("123")
                .pattern("456")
                .pattern("789")
                .input('1', Items.CARROT)
                .input('2', Items.POTATO)
                .input('3', Items.BEETROOT)
                .input('4', independentTag("onions"))
                .input('5', independentTag("tomatoes"))
                .input('6', independentTag("water_bottles"))
                .input('8', Content.Utensil.COOKING_POT)
                .input('7', independentTag("cabbage"))
                .input('9', independentTag("garlic"))
                .criterion("has_cabbage", RecipeProvider.conditionsFromItem(Content.Farmland.CABBAGE.asItem()))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(ItemRegistry.goulash)
                .pattern("123")
                .pattern("454")
                .pattern("183")
                .input('8', Content.Utensil.FRYING_PAN)
                .input('1', croptopia("pork_replacements"))
                .input('3', croptopia("beef_replacements"))
                .input('2', independentTag("onions"))
                .input('4', independentTag("cabbage"))
                .input('5', independentTag("tomatoes"))
                .criterion("has_cabbage", RecipeProvider.conditionsFromItem(Content.Farmland.CABBAGE.asItem()))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(ItemRegistry.beetrootSalad)
                .pattern("111")
                .pattern("745")
                .pattern(" 6 ")
                .input('1', Items.BEETROOT)
                .input('4', independentTag("cheeses"))
                .input('5', independentTag("lemons"))
                .input('6', Content.Utensil.COOKING_POT)
                .input('7', independentTag("lettuce"))
                .criterion("has_beetroot", RecipeProvider.conditionsFromItem(Items.BEETROOT))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(ItemRegistry.steamedCrab)
                .pattern("1")
                .pattern("2")
                .pattern("3")
                .input('1', independentTag("crabs"))
                .input('2', independentTag("water_bottles"))
                .input('3', Content.Utensil.COOKING_POT)
                .criterion("has_crab", RecipeProvider.conditionsFromItem(Content.Seafood.CRAB))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(ItemRegistry.deepFriedShrimp, 2)
                .pattern("111")
                .pattern("456")
                .input('1', independentTag("shrimp"))
                .input('4', Items.EGG)
                .input('6', Items.BREAD)
                .input('5', Content.Utensil.FRYING_PAN)
                .criterion("has_shrimp", RecipeProvider.conditionsFromItem(Content.Seafood.SHRIMP))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(ItemRegistry.tunaRoll, 2)
                .pattern("234")
                .pattern(" 1 ")
                .input('1', independentTag("tuna"))
                .input('2', Items.DRIED_KELP)
                .input('3', independentTag("rice"))
                .input('4', independentTag("onions"))
                .criterion("has_tuna", RecipeProvider.conditionsFromItem(Content.Seafood.TUNA))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(ItemRegistry.friedCalamari, 2)
                .pattern("123")
                .pattern("456")
                .input('1', independentTag("calamari"))
                .input('2', independentTag("lemons"))
                .input('3', independentTag("olive_oils"))
                .input('4', independentTag("flour"))
                .input('5', Content.Utensil.FRYING_PAN)
                .input('6', independentTag("sea_lettuce"))
                .criterion("has_calamari", RecipeProvider.conditionsFromItem(Content.Seafood.CALAMARI))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(ItemRegistry.crabLegs, 2)
                .pattern("123")
                .pattern("455")
                .pattern(" 7 ")
                .input('5', independentTag("crabs"))
                .input('1', independentTag("butters"))
                .input('2', independentTag("garlic"))
                .input('3', independentTag("salts"))
                .input('4', Content.Farmland.PEPPER.asItem())
                .input('7', Content.Utensil.FRYING_PAN)
                .criterion("has_crab", RecipeProvider.conditionsFromItem(Content.Seafood.CRAB))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(ItemRegistry.steamedClams, 2)
                .pattern("123")
                .pattern("455")
                .pattern(" 7 ")
                .input('5', independentTag("clams"))
                .input('1', independentTag("butters"))
                .input('2', independentTag("garlic"))
                .input('3', independentTag("salts"))
                .input('4', Content.Farmland.PEPPER.asItem())
                .input('7', Content.Utensil.FRYING_PAN)
                .criterion("has_clams", RecipeProvider.conditionsFromItem(Content.Seafood.CLAM))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(ItemRegistry.grilledOysters, 2)
                .pattern("121")
                .pattern("456")
                .pattern(" 7 ")
                .input('1', independentTag("oysters"))
                .input('2', independentTag("cheeses"))
                .input('4', independentTag("lemons"))
                .input('5', independentTag("garlic"))
                .input('6', independentTag("salts"))
                .input('7', Content.Utensil.FRYING_PAN)
                .criterion("has_oysters", RecipeProvider.conditionsFromItem(ItemRegistry.grilledOysters))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(ItemRegistry.anchovyPizza, 1)
                .pattern("123")
                .pattern(" 4 ")
                .pattern(" 7 ")
                .input('1', independentTag("tomatoes"))
                .input('2', independentTag("anchovies"))
                .input('3', independentTag("cheeses"))
                .input('4', independentTag("doughs"))
                .input('7', Content.Utensil.FRYING_PAN)
                .criterion("has_anchovies", RecipeProvider.conditionsFromItem(Content.Seafood.ANCHOVY))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(ItemRegistry.mashedPotatoes, 1)
                .pattern("1 ")
                .pattern("24")
                .pattern("3 ")
                .input('1', independentTag("potatoes"))
                .input('2', independentTag("salts"))
                .input('3', Content.Utensil.MORTAR_AND_PESTLE)
                .input('4', independentTag("milks"))
                .criterion("has_milk", RecipeProvider.conditionsFromItem(Items.MILK_BUCKET))
                .offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(ItemRegistry.tortilla, 2)
                .input(independentTag("flour"))
                .input(Content.Utensil.FRYING_PAN)
                .input(independentTag("water_bottles"))
                .criterion("took_flour", RecipeProvider.conditionsFromTag(independentTag("flour")))
                .criterion("has_frying_pan", RecipeProvider.conditionsFromItem(Content.Utensil.FRYING_PAN))
                .offerTo(exporter);
    }

    private TagKey<Item> croptopia(String name) {
        return TagKey.of(Registry.ITEM_KEY, new Identifier(MiscNames.MOD_ID, name));
    }

    private TagKey<Item> independentTag(String name) {
        IdentifierAccessor accessor = (IdentifierAccessor) Croptopia.createIdentifier(name);
        accessor.setNamespace("${dependent}"); // lmao
        return TagKey.of(Registry.ITEM_KEY, (Identifier) accessor);
    }


}
