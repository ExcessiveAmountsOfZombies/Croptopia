package me.thonk.croptopia.datagen;

import me.thonk.croptopia.Croptopia;
import me.thonk.croptopia.datagen.tags.IndependentEntry;
import me.thonk.croptopia.mixin.datagen.ObjectBuilderAccessor;
import me.thonk.croptopia.registry.ItemRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.data.DataCache;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.tag.TagKey;
import net.minecraft.tag.TagManagerLoader;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;

public class CroptopiaItemProvider extends FabricTagProvider.ItemTagProvider {


    public CroptopiaItemProvider(FabricDataGenerator dataGenerator, @Nullable BlockTagProvider blockTagProvider) {
        super(dataGenerator, blockTagProvider);
    }

    @Override
    protected void generateTags() {
        createCategoryTag("fruits", "almonds", ItemRegistry.almond);
        createCategoryTag("fruits", "apricots", ItemRegistry.apricot);
        createCategoryTag("fruits", "avocados", ItemRegistry.avocado);
        createCategoryTag("fruits", "bananas", ItemRegistry.banana);
        createCategoryTag("fruits", "bellpeppers", ItemRegistry.bellPepper);
        createCategoryTag("fruits", "blackberries", ItemRegistry.blackberry);
        createCategoryTag("fruits", "blueberries", ItemRegistry.blueberry);
        createCategoryTag("fruits", "cantaloupes", ItemRegistry.cantaloupe);
        createCategoryTag("fruits", "cashews", ItemRegistry.cashew);
        createCategoryTag("fruits", "cherries", ItemRegistry.cherry);
        createCategoryTag("fruits", "coconuts", ItemRegistry.coconut);
        createCategoryTag("fruits", "cranberries", ItemRegistry.cranberry);
        createCategoryTag("fruits", "currants", ItemRegistry.currant);
        createCategoryTag("fruits", "dates", ItemRegistry.date);
        createCategoryTag("fruits", "dragonfruits", ItemRegistry.dragonFruit);
        createCategoryTag("fruits", "elderberries", ItemRegistry.elderberry);
        createCategoryTag("fruits", "figs", ItemRegistry.fig);
        createCategoryTag("fruits", "grapefruits", ItemRegistry.grapefruit);
        createCategoryTag("fruits", "grapes", ItemRegistry.grape);
        createCategoryTag("fruits", "honeydew", ItemRegistry.honeydew);
        createCategoryTag("fruits", "kiwis", ItemRegistry.kiwi);
        createCategoryTag("fruits", "kumquat", ItemRegistry.kumquat);
        createCategoryTag("fruits", "lemons", ItemRegistry.lemon);
        createCategoryTag("fruits", "limes", ItemRegistry.lime);
        createCategoryTag("fruits", "mangos", ItemRegistry.mango);
        createCategoryTag("fruits", "nectarines", ItemRegistry.nectarine);
        createCategoryTag("fruits", "olives", ItemRegistry.olive);
        createCategoryTag("fruits", "oranges", ItemRegistry.orange);
        createCategoryTag("fruits", "peaches", ItemRegistry.peach);
        createCategoryTag("fruits", "pears", ItemRegistry.pear);
        createCategoryTag("fruits", "pecans", ItemRegistry.pecan);
        createCategoryTag("fruits", "persimmons", ItemRegistry.persimmon);
        createCategoryTag("fruits", "pineapples", ItemRegistry.pineapple);
        createCategoryTag("fruits", "plums", ItemRegistry.plum);
        createCategoryTag("fruits", "raspberries", ItemRegistry.raspberry);
        createCategoryTag("fruits", "starfruits", ItemRegistry.starFruit);
        createCategoryTag("fruits", "strawberries", ItemRegistry.strawberry);
        createCategoryTag("fruits", "walnuts", ItemRegistry.walnut);

        createCategoryTag("grain", "barley", ItemRegistry.barley);
        createCategoryTag("grain", "corn", ItemRegistry.corn);
        createCategoryTag("grain", "oats", ItemRegistry.oat);
        createCategoryTag("grain", "rice", ItemRegistry.rice);

        createCategoryTag("jams", "apricot_jams", ItemRegistry.apricotJam);
        createCategoryTag("jams", "blackberry_jams", ItemRegistry.blackberryJam);
        createCategoryTag("jams", "blueberry_jams", ItemRegistry.blueberryJam);
        createCategoryTag("jams", "cherry_jams", ItemRegistry.cherryJam);
        createCategoryTag("jams", "elderberry_jams", ItemRegistry.elderberryJam);
        createCategoryTag("jams", "grape_jams", ItemRegistry.grapeJam);
        createCategoryTag("jams", "peach_jams", ItemRegistry.peachJam);
        createCategoryTag("jams", "raspberry_jams", ItemRegistry.raspberryJam);
        createCategoryTag("jams", "strawberry_jams", ItemRegistry.strawberryJam);

        createCategoryTag("juices", "apple_juices", ItemRegistry.appleJuice);
        createCategoryTag("juices", "cranberry_juices", ItemRegistry.cranberryJuice);
        createCategoryTag("juices", "grape_juices", ItemRegistry.grapeJuice);
        createCategoryTag("juices", "melon_juices", ItemRegistry.melonJuice);
        createCategoryTag("juices", "orange_juices", ItemRegistry.orangeJuice);
        createCategoryTag("juices", "pineapple_juices", ItemRegistry.pineappleJuice);
        createCategoryTag("juices", "saguaro_juices", ItemRegistry.saguaroJuice);
        createCategoryTag("juices", "tomato_juices", ItemRegistry.tomatoJuice);

        createCategoryTag("nuts", "almonds", ItemRegistry.almond);
        createCategoryTag("nuts", "pecans", ItemRegistry.pecan);
        createCategoryTag("nuts", "walnuts", ItemRegistry.walnut);

        createCategoryTag("vegetables", "artichokes", ItemRegistry.artichoke);
        createCategoryTag("vegetables", "asparagus", ItemRegistry.asparagus);
        createCategoryTag("vegetables", "broccoli", ItemRegistry.broccoli);
        createCategoryTag("vegetables", "cabbage", ItemRegistry.cabbage);
        createCategoryTag("vegetables", "cauliflower", ItemRegistry.cauliflower);
        createCategoryTag("vegetables", "celery", ItemRegistry.celery);
        createCategoryTag("vegetables", "cucumber", ItemRegistry.cucumber);
        createCategoryTag("vegetables", "eggplant", ItemRegistry.eggplant);
        createCategoryTag("vegetables", "garlic", ItemRegistry.garlic);
        createCategoryTag("vegetables", "ginger", ItemRegistry.ginger);
        createCategoryTag("vegetables", "greenbeans", ItemRegistry.greenBean);
        createCategoryTag("vegetables", "greenonions", ItemRegistry.greenOnion);
        createCategoryTag("vegetables", "kale", ItemRegistry.kale);
        createCategoryTag("vegetables", "leek", ItemRegistry.leek);
        createCategoryTag("vegetables", "lettuce", ItemRegistry.lettuce);
        createCategoryTag("vegetables", "mustard", ItemRegistry.mustard);
        createCategoryTag("vegetables", "onion", ItemRegistry.onion);
        createCategoryTag("vegetables", "radishes", ItemRegistry.radish);
        createCategoryTag("vegetables", "rhubarb", ItemRegistry.rhubarb);
        createCategoryTag("vegetables", "rutabagas", ItemRegistry.rutabaga);
        createCategoryTag("vegetables", "soybeans", ItemRegistry.soybean);
        createCategoryTag("vegetables", "spinach", ItemRegistry.spinach);
        createCategoryTag("vegetables", "squash", ItemRegistry.squash);
        createCategoryTag("vegetables", "sweetpotatos", ItemRegistry.sweetPotato);
        createCategoryTag("vegetables", "tomatillos", ItemRegistry.tomatillo);
        createCategoryTag("vegetables", "tomato", ItemRegistry.tomato);
        createCategoryTag("vegetables", "turnips", ItemRegistry.turnip);
        createCategoryTag("vegetables", "yams", ItemRegistry.yam);
        createCategoryTag("vegetables", "zucchini", ItemRegistry.zucchini);

        createGeneralTag("almond_brittles", ItemRegistry.almondBrittle);
        createGeneralTag("apple_pies", ItemRegistry.applePie);
        createGeneralTag("artichoke_dips", ItemRegistry.artichokeDip);
        createGeneralTag("baked_beans", ItemRegistry.bakedBeans);
        createGeneralTag("banana_cream_pies", ItemRegistry.bananaCreamPie);
        createGeneralTag("banana_nut_breads", ItemRegistry.bananaNutBread);
        createGeneralTag("banana_smoothies", ItemRegistry.bananaSmoothie);
        createGeneralTag("beef_jerkies", ItemRegistry.beefJerky);
        createGeneralTag("beef_wellington", ItemRegistry.beefWellington);
        createGeneralTag("beers", ItemRegistry.beer);
        createGeneralTag("blts", ItemRegistry.BLT);
        createGeneralTag("brownies", ItemRegistry.brownies);
        createGeneralTag("buttered_toasts", ItemRegistry.butteredToast);
        createGeneralTag("butters", ItemRegistry.butter);
        createGeneralTag("caesar_salads", ItemRegistry.caesarSalad);
        createGeneralTag("candied_nuts", ItemRegistry.candiedNuts);
        createGeneralTag("candy_corns", ItemRegistry.candyCorn);
        createGeneralTag("caramel", ItemRegistry.caramel);
        createGeneralTag("cashew_chickens", ItemRegistry.cashewChicken);
        createGeneralTag("cheese_cakes", ItemRegistry.cheeseCake);
        createGeneralTag("cheese_pizzas", ItemRegistry.cheesePizza);
        createGeneralTag("cheeseburgers", ItemRegistry.cheeseburger);
        createGeneralTag("cheeses", ItemRegistry.cheese);
        createGeneralTag("cherry_pies", ItemRegistry.cherryPie);
        createGeneralTag("chicken_and_dumplings", ItemRegistry.chickenAndDumplings);
        createGeneralTag("chicken_and_noodles", ItemRegistry.chickenAndNoodles);
        createGeneralTag("chicken_and_rice", ItemRegistry.chickenAndRice);
        createGeneralTag("chocolate_milkshakes", ItemRegistry.chocolateMilkshake);
        createGeneralTag("chocolates", ItemRegistry.chocolate);
        createGeneralTag("coffees", ItemRegistry.coffee);
        createGeneralTag("cooking_pots", ItemRegistry.cookingPot);
        createGeneralTag("cornish_pasty", ItemRegistry.cornishPasty);
        createGeneralTag("cucumber_salads", ItemRegistry.cucumberSalad);
        createGeneralTag("doughnuts", ItemRegistry.doughnut);
        createGeneralTag("doughs", ItemRegistry.dough);
        createGeneralTag("egg_rolls", ItemRegistry.eggRoll);
        createGeneralTag("eton_mess", ItemRegistry.etonMess);
        createGeneralTag("figgy_pudding", ItemRegistry.figgyPudding);
        createGeneralTag("fish_and_chips", ItemRegistry.fishAndChips);
        createGeneralTag("flour", ItemRegistry.flour);
        createGeneralTag("food_press", ItemRegistry.foodPress);
        createGeneralTag("french_fries", ItemRegistry.frenchFries);
        createGeneralTag("fried_chickens", ItemRegistry.friedChicken);
        createGeneralTag("fruit_salads", ItemRegistry.fruitSalad);
        createGeneralTag("fruit_smoothies", ItemRegistry.fruitSmoothie);
        createGeneralTag("frying_pans", ItemRegistry.fryingPan);
        createGeneralTag("grilled_cheeses", ItemRegistry.grilledCheese);
        createGeneralTag("ham_sandwiches", ItemRegistry.hamSandwich);
        createGeneralTag("hamburgers", ItemRegistry.hamburger);
        createGeneralTag("kale_chips", ItemRegistry.kaleChips);
        createGeneralTag("kale_smoothies", ItemRegistry.kaleSmoothie);
        createGeneralTag("leafy_salads", ItemRegistry.leafySalad);
        createGeneralTag("leek_soups", ItemRegistry.leekSoup);
        createGeneralTag("lemon_chickens", ItemRegistry.lemonChicken);
        createGeneralTag("lemonades", ItemRegistry.lemonade);
        createGeneralTag("limeades", ItemRegistry.limeade);
        createGeneralTag("mango_ice_creams", ItemRegistry.mangoIceCream);
        createGeneralTag("meads", ItemRegistry.mead);
        createGeneralTag("milk_bottles", ItemRegistry.milkBottle);
        createGeneralTag("molasses", ItemRegistry.molasses);
        createGeneralTag("mortar_and_pestles", ItemRegistry.mortarAndPestle);
        createGeneralTag("noodles", ItemRegistry.noodle);
        createGeneralTag("nougats", ItemRegistry.nougat);
        createGeneralTag("nutty_cookies", ItemRegistry.nuttyCookie);
        createGeneralTag("oatmeals", ItemRegistry.oatmeal);
        createGeneralTag("olive_oils", ItemRegistry.oliveOil);
        createGeneralTag("onion_rings", ItemRegistry.onionRings);
        createGeneralTag("peanut_butter_and_jam", ItemRegistry.peanutButterAndJam);
        createGeneralTag("pecan_ice_creams", ItemRegistry.pecanIceCream);
        createGeneralTag("pecan_pies", ItemRegistry.pecanPie);
        createGeneralTag("pepperoni", ItemRegistry.pepperoni);
        createGeneralTag("pineapple_pepperoni_pizzas", ItemRegistry.pineapplePepperoniPizza);
        createGeneralTag("pizzas", ItemRegistry.pizza);
        createGeneralTag("popcorns", ItemRegistry.popcorn);
        createGeneralTag("pork_and_beanss", ItemRegistry.porkAndBeans);
        createGeneralTag("pork_jerkies", ItemRegistry.porkJerky);
        createGeneralTag("potato_chips", ItemRegistry.potatoChips);
        createGeneralTag("protein_bars", ItemRegistry.proteinBar);
        createGeneralTag("pumpkin_spice_lattes", ItemRegistry.pumpkinSpiceLatte);
        createGeneralTag("raisin_oatmeal_cookies", ItemRegistry.oatmealCookie);
        createGeneralTag("raisins", ItemRegistry.raisins);
        createGeneralTag("ravioli", ItemRegistry.ravioli);
        createGeneralTag("roasted_nuts", ItemRegistry.roastedNuts);
        createGeneralTag("rum_raisin_ice_creams", ItemRegistry.rumRaisinIceCream);
        createGeneralTag("rums", ItemRegistry.rum);
        createGeneralTag("saguaros", ItemRegistry.saguaro);
        createGeneralTag("salsas", ItemRegistry.salsa);
        createGeneralTag("salt_ores", ItemRegistry.saltOre);
        createGeneralTag("salts", ItemRegistry.salt);
        createGeneralTag("saucy_chips", ItemRegistry.saucyChips);
        createGeneralTag("scones", ItemRegistry.scones);
        createGeneralTag("scrambled_eggs", ItemRegistry.scrambledEggs);
        createGeneralTag("shepherds_pie", ItemRegistry.shepherdsPie);
        createGeneralTag("snicker_doodles", ItemRegistry.snickerDoodle);
        createGeneralTag("soy_milks", ItemRegistry.soyMilk);
        createGeneralTag("soy_sauces", ItemRegistry.soySauce);
        createGeneralTag("spaghetti_squashs", ItemRegistry.spaghettiSquash);
        createGeneralTag("steamed_rices", ItemRegistry.steamedRice);
        createGeneralTag("sticky_toffee_pudding", ItemRegistry.stickyToffeePudding);
        createGeneralTag("strawberry_ice_creams", ItemRegistry.strawberryIceCream);
        createGeneralTag("strawberry_smoothies", ItemRegistry.strawberrySmoothie);
        createGeneralTag("supreme_pizzas", ItemRegistry.supremePizza);
        createGeneralTag("sushis", ItemRegistry.sushi);
        createGeneralTag("sweet_potato_friess", ItemRegistry.sweetPotatoFries);
        createGeneralTag("tacos", ItemRegistry.taco);
        createGeneralTag("tea", ItemRegistry.tea);
        createGeneralTag("tea_leaves", ItemRegistry.teaLeaves);
        createGeneralTag("toast_with_jam", ItemRegistry.toastWithJam);
        createGeneralTag("toasts", ItemRegistry.toast);
        createGeneralTag("tofu", ItemRegistry.tofu);
        createGeneralTag("tofu_and_dumplings", ItemRegistry.tofuAndDumplings);
        createGeneralTag("tofuburgers", ItemRegistry.tofuBurger);
        createGeneralTag("tortillas", ItemRegistry.tortilla);
        createGeneralTag("trail_mixes", ItemRegistry.trailMix);
        createGeneralTag("treacle_tarts", ItemRegistry.treacleTart);
        createGeneralTag("trifle", ItemRegistry.trifle);
        createGeneralTag("tuna_sandwiches", ItemRegistry.tunaSandwich);
        createGeneralTag("turmeric", ItemRegistry.turmeric);
        createGeneralTag("vanilla_ice_creams", ItemRegistry.vanillaIceCream);
        createGeneralTag("veggie_salads", ItemRegistry.veggieSalad);
        createGeneralTag("wines", ItemRegistry.wine);
        createGeneralTag("yam_jam", ItemRegistry.yamJam);
        createGeneralTag("yoghurts", ItemRegistry.yoghurt);
    }

    @Override
    protected Path getOutput(Identifier id) {
        RegistryKey<? extends Registry<?>> registryKey = this.registry.getKey();
        Path rootOutput = this.root.getOutput();
        return rootOutput.resolve("dependents/platform/" + TagManagerLoader.getPath(registryKey) + "/" + id.getPath() + ".json");
    }

    @Override
    public void run(DataCache cache) {
        super.run(cache);
    }

    private static TagKey<Item> register(String id) {
        return TagKey.of(Registry.ITEM_KEY, Croptopia.createIdentifier(id));
    }

    private void createCategoryTag(String category, String name, Item item) {
        String path = Registry.ITEM.getId(item).getPath();
        TagKey<Item> forgeFriendlyTag = register(category + "/" + path);
        this.getOrCreateTagBuilder(forgeFriendlyTag).add(item);
        ObjectBuilderAccessor fabricGeneralTag = (ObjectBuilderAccessor) (Object) this.getOrCreateTagBuilder(register(name)).add(item);
        IndependentEntry independentEntry = new IndependentEntry(category + "/" + path);
        fabricGeneralTag.getBuilder().add(new Tag.TrackedEntry(independentEntry, fabricGeneralTag.getSource()));

        ObjectBuilderAccessor group = (ObjectBuilderAccessor) (Object)this.getOrCreateTagBuilder(register(category));
        group.getBuilder().add(new Tag.TrackedEntry(independentEntry, group.getSource()));
    }

    private void createGeneralTag(String name, Item item) {
        TagKey<Item> pluralTag = register(name);
        this.getOrCreateTagBuilder(pluralTag).add(item);
    }

}
