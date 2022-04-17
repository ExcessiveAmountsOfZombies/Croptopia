package me.thonk.croptopia.datagen;

import me.thonk.croptopia.Croptopia;
import me.thonk.croptopia.mixin.datagen.IdentifierAccessor;
import me.thonk.croptopia.registry.ItemRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.RecipeProvider;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
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
        ShapedRecipeJsonBuilder.create(ItemRegistry.roastedPumpkinSeeds)
                .pattern("   ")
                .pattern("123")
                .pattern(" 4 ")
                .input('1', Items.PUMPKIN_SEEDS)
                .input('3', ItemRegistry.pepper)
                .input('2', tag("salts"))
                .input('4', ItemRegistry.fryingPan)
                .criterion("has_pumpkin_seed", RecipeProvider.conditionsFromItem(Items.PUMPKIN_SEEDS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(ItemRegistry.roastedSunflowerSeeds)
                .pattern("   ")
                .pattern("123")
                .pattern(" 4 ")
                .input('1', Items.SUNFLOWER)
                .input('3', ItemRegistry.pepper)
                .input('2', tag("salts"))
                .input('4', ItemRegistry.fryingPan)
                .criterion("has_sunflower", RecipeProvider.conditionsFromItem(Items.SUNFLOWER))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(ItemRegistry.pumpkinBars, 3)
                .pattern("586")
                .pattern("124")
                .pattern("373")
                .input('1', Items.EGG)
                .input('2', Items.SUGAR)
                .input('3', Items.PUMPKIN)
                .input('4', tag("flour"))
                .input('5', ItemRegistry.cinnamon)
                .input('6', tag("salts"))
                .input('7', tag("butters"))
                .input('8', tag("vanilla"))
                .criterion("has_pumpkin", RecipeProvider.conditionsFromItem(Items.PUMPKIN))
                .criterion("has_cinnamon", RecipeProvider.conditionsFromItem(ItemRegistry.cinnamon))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(ItemRegistry.cornBread)
                .pattern("111")
                .input('1', tag("corn"))
                .criterion("has_corn", RecipeProvider.conditionsFromItem(ItemRegistry.corn))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(ItemRegistry.pumpkinSoup, 2)
                .pattern("123")
                .pattern(" 5 ")
                .pattern("464")
                .input('1', tag("onions"))
                .input('2', tag("garlic"))
                .input('3', ItemRegistry.pepper)
                .input('4', Items.PUMPKIN)
                .input('5', tag("salts"))
                .input('6', ItemRegistry.cookingPot)
                .criterion("has_pumpkin", RecipeProvider.conditionsFromItem(Items.PUMPKIN))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(ItemRegistry.meringue, 2)
                .pattern("243")
                .pattern("111")
                .input('1', Items.EGG)
                .input('2', tag("salts"))
                .input('3', Items.SUGAR)
                .input('4', tag("vanilla"))
                .criterion("has_egg", RecipeProvider.conditionsFromItem(Items.EGG))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(ItemRegistry.cabbageRoll, 2)
                .pattern("121")
                .pattern("456")
                .pattern("585")
                .input('8', ItemRegistry.fryingPan)
                .input('1', croptopia("beef_replacements"))
                .input('2', tag("onions"))
                .input('6', tag("rice"))
                .input('4', tag("salts"))
                .input('5', tag("cabbage"))
                .criterion("has_cabbage", RecipeProvider.conditionsFromItem(ItemRegistry.cabbage))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(ItemRegistry.borscht, 2)
                .pattern("123")
                .pattern("456")
                .pattern("789")
                .input('1', Items.CARROT)
                .input('2', Items.POTATO)
                .input('3', Items.BEETROOT)
                .input('4', tag("onions"))
                .input('5', tag("tomatoes"))
                .input('6', tag("water_bottles"))
                .input('8', ItemRegistry.cookingPot)
                .input('7', tag("cabbage"))
                .input('9', tag("garlic"))
                .criterion("has_cabbage", RecipeProvider.conditionsFromItem(ItemRegistry.cabbage))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(ItemRegistry.goulash)
                .pattern("123")
                .pattern("454")
                .pattern("183")
                .input('8', ItemRegistry.fryingPan)
                .input('1', croptopia("pork_replacements"))
                .input('3', croptopia("beef_replacements"))
                .input('2', tag("onions"))
                .input('4', tag("cabbage"))
                .input('5', tag("tomatoes"))
                .criterion("has_cabbage", RecipeProvider.conditionsFromItem(ItemRegistry.cabbage))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(ItemRegistry.beetrootSalad)
                .pattern("111")
                .pattern("745")
                .pattern(" 6 ")
                .input('1', Items.BEETROOT)
                .input('4', tag("cheeses"))
                .input('5', tag("lemons"))
                .input('6', ItemRegistry.cookingPot)
                .input('7', tag("lettuce"))
                .criterion("has_beetroot", RecipeProvider.conditionsFromItem(Items.BEETROOT))
                .offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(ItemRegistry.candiedKumquats, 7)
                .input(tag("kumquat"))
                .input(tag("kumquat"))
                .input(tag("kumquat"))
                .input(tag("kumquat"))
                .input(tag("kumquat"))
                .input(tag("kumquat"))
                .input(tag("kumquat"))
                .input(tag("vanilla"))
                .input(Items.HONEY_BOTTLE)
                .criterion("has_kumquat", RecipeProvider.conditionsFromItem(ItemRegistry.kumquat))
                .offerTo(exporter);
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ItemRegistry.shrimp), ItemRegistry.cookedShrimp, 0.2f, 200)
                .criterion("has_shrimp", RecipeProvider.conditionsFromItem(ItemRegistry.shrimp))
                .offerTo(exporter);
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ItemRegistry.tuna), ItemRegistry.cookedTuna, 0.2f, 200)
                .criterion("has_tuna", RecipeProvider.conditionsFromItem(ItemRegistry.tuna))
                .offerTo(exporter);
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ItemRegistry.calamari), ItemRegistry.cookedCalamari, 0.2f, 200)
                .criterion("has_calamari", RecipeProvider.conditionsFromItem(ItemRegistry.calamari))
                .offerTo(exporter);
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ItemRegistry.glowingCalamari), ItemRegistry.cookedCalamari, 0.2f, 200)
                .criterion("has_glowing_calamari", RecipeProvider.conditionsFromItem(ItemRegistry.glowingCalamari))
                .offerTo(exporter, RecipeProvider.getItemPath(ItemRegistry.cookedCalamari) + "_from_glowing_calamari");
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ItemRegistry.anchovy), ItemRegistry.cookedAnchovy, 0.2f, 200)
                .criterion("has_anchovy", RecipeProvider.conditionsFromItem(ItemRegistry.anchovy))
                .offerTo(exporter);
        RecipeProvider.offerCookingRecipe(exporter, "smoking", RecipeSerializer.SMELTING, 100, ItemRegistry.shrimp, ItemRegistry.cookedShrimp, 0.2f);
        RecipeProvider.offerCookingRecipe(exporter, "smoking", RecipeSerializer.SMELTING, 100, ItemRegistry.tuna, ItemRegistry.cookedTuna, 0.2f);
        RecipeProvider.offerCookingRecipe(exporter, "smoking", RecipeSerializer.SMELTING, 100, ItemRegistry.calamari, ItemRegistry.cookedCalamari, 0.2f);
        RecipeProvider.offerCookingRecipe(exporter, "smoking", RecipeSerializer.SMELTING, 100, ItemRegistry.anchovy, ItemRegistry.cookedAnchovy, 0.2f);
        RecipeProvider.offerCookingRecipe(exporter, "glowing_calamri_smoking", RecipeSerializer.SMELTING, 100, ItemRegistry.glowingCalamari, ItemRegistry.cookedCalamari, 0.2f);
        ShapedRecipeJsonBuilder.create(ItemRegistry.steamedCrab)
                .pattern("1")
                .pattern("2")
                .pattern("3")
                .input('1', tag("crabs"))
                .input('2', tag("water_bottles"))
                .input('3', ItemRegistry.cookingPot)
                .criterion("has_crab", RecipeProvider.conditionsFromItem(ItemRegistry.crab))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(ItemRegistry.deepFriedShrimp, 2)
                .pattern("111")
                .pattern("456")
                .input('1', tag("shrimp"))
                .input('4', Items.EGG)
                .input('6', Items.BREAD)
                .input('5', ItemRegistry.fryingPan)
                .criterion("has_shrimp", RecipeProvider.conditionsFromItem(ItemRegistry.shrimp))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(ItemRegistry.tunaRoll, 2)
                .pattern("234")
                .pattern(" 1 ")
                .input('1', tag("tuna"))
                .input('2', Items.DRIED_KELP)
                .input('3', tag("rice"))
                .input('4', tag("onions"))
                .criterion("has_tuna", RecipeProvider.conditionsFromItem(ItemRegistry.tuna))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(ItemRegistry.friedCalamari, 2)
                .pattern("123")
                .pattern("456")
                .input('1', tag("calamari"))
                .input('2', tag("lemons"))
                .input('3', tag("olive_oils"))
                .input('4', tag("flour"))
                .input('5', ItemRegistry.fryingPan)
                .input('6', tag("sea_lettuce"))
                .criterion("has_calamari", RecipeProvider.conditionsFromItem(ItemRegistry.calamari))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(ItemRegistry.crabLegs, 2)
                .pattern("123")
                .pattern("455")
                .pattern(" 7 ")
                .input('5', tag("crabs"))
                .input('1', tag("butters"))
                .input('2', tag("garlic"))
                .input('3', tag("salts"))
                .input('4', ItemRegistry.pepper)
                .input('7', ItemRegistry.fryingPan)
                .criterion("has_crab", RecipeProvider.conditionsFromItem(ItemRegistry.crab))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(ItemRegistry.steamedClams, 2)
                .pattern("123")
                .pattern("455")
                .pattern(" 7 ")
                .input('5', tag("clams"))
                .input('1', tag("butters"))
                .input('2', tag("garlic"))
                .input('3', tag("salts"))
                .input('4', ItemRegistry.pepper)
                .input('7', ItemRegistry.fryingPan)
                .criterion("has_clams", RecipeProvider.conditionsFromItem(ItemRegistry.clam))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(ItemRegistry.grilledOysters, 2)
                .pattern("121")
                .pattern("456")
                .pattern(" 7 ")
                .input('1', tag("oysters"))
                .input('2', tag("cheeses"))
                .input('4', tag("lemons"))
                .input('5', tag("garlic"))
                .input('6', tag("salts"))
                .input('7', ItemRegistry.fryingPan)
                .criterion("has_oysters", RecipeProvider.conditionsFromItem(ItemRegistry.grilledOysters))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(ItemRegistry.anchovyPizza, 1)
                .pattern("123")
                .pattern(" 4 ")
                .pattern(" 7 ")
                .input('1', tag("tomatoes"))
                .input('2', tag("anchovies"))
                .input('3', tag("cheeses"))
                .input('4', tag("doughs"))
                .input('7', ItemRegistry.fryingPan)
                .criterion("has_anchovies", RecipeProvider.conditionsFromItem(ItemRegistry.anchovy))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(ItemRegistry.mashedPotatoes, 1)
                .pattern("1 ")
                .pattern("24")
                .pattern("3 ")
                .input('1', tag("potatoes"))
                .input('2', tag("salts"))
                .input('3', ItemRegistry.mortarAndPestle)
                .input('4', tag("milks"))
                .criterion("has_milk", RecipeProvider.conditionsFromItem(Items.MILK_BUCKET))
                .offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(ItemRegistry.tortilla, 2)
                .input(tag("flour"))
                .input(ItemRegistry.fryingPan)
                .input(tag("water_bottles"))
                .criterion("took_flour", RecipeProvider.conditionsFromTag(tag("flour")))
                .criterion("has_frying_pan", RecipeProvider.conditionsFromItem(ItemRegistry.fryingPan))
                .offerTo(exporter);
    }

    private TagKey<Item> croptopia(String name) {
        return TagKey.of(Registry.ITEM_KEY, new Identifier("croptopia", name));
    }

    private TagKey<Item> tag(String name) {
        IdentifierAccessor accessor = (IdentifierAccessor) Croptopia.createIdentifier(name);
        accessor.setNamespace("${dependent}"); // lmao
        return TagKey.of(Registry.ITEM_KEY, (Identifier) accessor);
    }


}
