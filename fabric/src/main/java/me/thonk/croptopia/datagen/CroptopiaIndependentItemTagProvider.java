package me.thonk.croptopia.datagen;

import me.thonk.croptopia.Croptopia;
import me.thonk.croptopia.datagen.tags.IndependentEntry;
import me.thonk.croptopia.mixin.datagen.ObjectBuilderAccessor;
import me.thonk.croptopia.registry.Content;
import me.thonk.croptopia.registry.ItemRegistry;
import me.thonk.croptopia.util.PluralInfo;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.data.DataCache;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tag.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;

public class CroptopiaIndependentItemTagProvider extends FabricTagProvider.ItemTagProvider {


    public CroptopiaIndependentItemTagProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateTags() {
        generateMisc();
    }

    protected void generateMisc() {
        for (Content.Farmland crop : Content.Farmland.values()) {
            createCategoryTag(crop.getTagegory().getLowerCaseName(), PluralInfo.plural(crop.getLowerCaseName(), crop.hasPlural()), crop.asItem());
            if (crop.getTagegory() != Content.TagCategory.CROPS) { // don't double only-crops
                createCategoryTag(Content.TagCategory.CROPS.getLowerCaseName(), PluralInfo.plural(crop.getLowerCaseName(), crop.hasPlural()), crop.asItem());
            }
        }
        for (Content.Tree crop : Content.Tree.values()) {
            createCategoryTag(crop.getTagegory().getLowerCaseName(), PluralInfo.plural(crop.getLowerCaseName(), crop.hasPlural()), crop.asItem());
            if (crop.getTagegory() != Content.TagCategory.CROPS) { // don't double only-crops
                createCategoryTag(Content.TagCategory.CROPS.getLowerCaseName(), PluralInfo.plural(crop.getLowerCaseName(), crop.hasPlural()), crop.asItem());
            }
            if (crop.getTagegory() == Content.TagCategory.NUTS) { // nuts are fruits
                createCategoryTag(Content.TagCategory.FRUITS.getLowerCaseName(), PluralInfo.plural(crop.getLowerCaseName(), crop.hasPlural()), crop.asItem());
            }
        }
        for (Content.Bark crop : Content.Bark.values()) {
            createCategoryTag(crop.getTagegory().getLowerCaseName(), PluralInfo.plural(crop.getLowerCaseName(), crop.hasPlural()), crop.asItem());
        }
        // the following four are all done above with a category tag of crops I believe
        /*createGeneralTag("saguaros", ItemRegistry.saguaro);
        createGeneralTag("turmeric", ItemRegistry.turmeric);
        createGeneralTag("tea_leaves", ItemRegistry.teaLeaves);
        createGeneralTag("cinnamon", ItemRegistry.cinnamon);*/

        // these should be singular, they are pluralized in the method, this is because forge seed tags don't include the "seed" portion.
        for (Content.Farmland crop : Content.Farmland.values()) {
            if (crop == Content.Farmland.CHILE_PEPPER) {
                createSeedSaplingTag("seeds", "chilepepper", crop.getSeed());
            }
            else {
                createSeedSaplingTag("seeds", crop.getLowerCaseName(), crop.getSeed());
            }
        }
        for (Content.Tree crop : Content.Tree.values()) {
            createSeedSaplingTag("saplings", crop.getLowerCaseName(), crop.getSapling());
        }
        for (Content.Bark crop : Content.Bark.values()) {
            createSeedSaplingTag("saplings", crop.getLowerCaseName(), crop.getSapling());
        }

        for (Content.Juice juice : Content.Juice.values()) {
            createCategoryTag("juices", juice.name().toLowerCase()+"_juices", juice.asItem());
        }
        for (Content.Jam jam : Content.Jam.values()) {
            createCategoryTag("jams", jam.name().toLowerCase()+"_jams", jam.asItem());
        }

        for (Content.Smoothie smoothie : Content.Smoothie.values()) {
            createGeneralTag(smoothie.name().toLowerCase()+"_smoothies", smoothie.asItem());
        }
        for (Content.IceCream iceCream : Content.IceCream.values()) {
            createGeneralTag(iceCream.name().toLowerCase()+"_ice_creams", iceCream.asItem());
        }
        for (Content.Utensil utensil : Content.Utensil.values()) {
            createGeneralTag(PluralInfo.plural(utensil.name().toLowerCase(),utensil.hasPlural()), utensil.asItem());
        }

        createGeneralTag("almond_brittles", ItemRegistry.almondBrittle);
        createGeneralTag("apple_pies", Content.Pie.APPLE.asItem());
        createGeneralTag("artichoke_dips", ItemRegistry.artichokeDip);
        createGeneralTag("baked_beans", Content.Furnace.BAKED_BEANS.asItem());
        createGeneralTag("banana_cream_pies", ItemRegistry.bananaCreamPie);
        createGeneralTag("banana_nut_breads", ItemRegistry.bananaNutBread);
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
        createGeneralTag("caramel", Content.Furnace.CARAMEL.asItem());
        createGeneralTag("cashew_chickens", ItemRegistry.cashewChicken);
        createGeneralTag("cheese_cakes", ItemRegistry.cheeseCake);
        createGeneralTag("cheese_pizzas", ItemRegistry.cheesePizza);
        createGeneralTag("cheeseburgers", ItemRegistry.cheeseburger);
        createGeneralTag("cheeses", ItemRegistry.cheese);
        createGeneralTag("cherry_pies", Content.Pie.CHERRY.asItem());
        createGeneralTag("chicken_and_dumplings", ItemRegistry.chickenAndDumplings);
        createGeneralTag("chicken_and_noodles", ItemRegistry.chickenAndNoodles);
        createGeneralTag("chicken_and_rice", ItemRegistry.chickenAndRice);
        createGeneralTag("chocolate_milkshakes", ItemRegistry.chocolateMilkshake);
        createGeneralTag("chocolates", ItemRegistry.chocolate);
        createGeneralTag("coffees", ItemRegistry.coffee);
        createGeneralTag("cornish_pasty", ItemRegistry.cornishPasty);
        createGeneralTag("cucumber_salads", ItemRegistry.cucumberSalad);
        createGeneralTag("doughnuts", ItemRegistry.doughnut);
        createGeneralTag("doughs", ItemRegistry.dough);
        createGeneralTag("egg_rolls", ItemRegistry.eggRoll);
        createGeneralTag("eton_mess", ItemRegistry.etonMess);
        createGeneralTag("figgy_pudding", ItemRegistry.figgyPudding);
        createGeneralTag("fish_and_chips", ItemRegistry.fishAndChips);
        createGeneralTag("flour", ItemRegistry.flour);
        createGeneralTag("french_fries", ItemRegistry.frenchFries);
        createGeneralTag("fried_chickens", ItemRegistry.friedChicken);
        createGeneralTag("fruit_salads", ItemRegistry.fruitSalad);
        createGeneralTag("fruit_smoothies", ItemRegistry.fruitSmoothie);
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
        createGeneralTag("meads", ItemRegistry.mead);
        createGeneralTag("milk_bottles", ItemRegistry.milkBottle);
        createGeneralTag("molasses", Content.Furnace.MOLASSES.asItem());
        createGeneralTag("noodles", ItemRegistry.noodle);
        createGeneralTag("nougats", ItemRegistry.nougat);
        createGeneralTag("nutty_cookies", ItemRegistry.nuttyCookie);
        createGeneralTag("oatmeals", ItemRegistry.oatmeal);
        createGeneralTag("olive_oils", ItemRegistry.oliveOil);
        createGeneralTag("onion_rings", ItemRegistry.onionRings);
        createGeneralTag("peanut_butter_and_jam", ItemRegistry.peanutButterAndJam);
        createGeneralTag("pecan_pies", Content.Pie.PECAN.asItem());
        createGeneralTag("pepperoni", ItemRegistry.pepperoni);
        createGeneralTag("pineapple_pepperoni_pizzas", ItemRegistry.pineapplePepperoniPizza);
        createGeneralTag("pizzas", ItemRegistry.pizza);
        createGeneralTag("popcorns", Content.Furnace.POPCORN.asItem());
        createGeneralTag("pork_and_beanss", ItemRegistry.porkAndBeans);
        createGeneralTag("pork_jerkies", ItemRegistry.porkJerky);
        createGeneralTag("potato_chips", ItemRegistry.potatoChips);
        createGeneralTag("protein_bars", ItemRegistry.proteinBar);
        createGeneralTag("pumpkin_spice_lattes", ItemRegistry.pumpkinSpiceLatte);
        createGeneralTag("raisin_oatmeal_cookies", ItemRegistry.oatmealCookie);
        createGeneralTag("raisins", Content.Furnace.RAISINS.asItem());
        createGeneralTag("ravioli", ItemRegistry.ravioli);
        createGeneralTag("roasted_nuts", ItemRegistry.roastedNuts);
        createGeneralTag("rum_raisin_ice_creams", ItemRegistry.rumRaisinIceCream);
        createGeneralTag("rums", ItemRegistry.rum);
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
        createGeneralTag("supreme_pizzas", ItemRegistry.supremePizza);
        createGeneralTag("sushis", ItemRegistry.sushi);
        createGeneralTag("sweet_potato_friess", ItemRegistry.sweetPotatoFries);
        createGeneralTag("tacos", ItemRegistry.taco);
        createGeneralTag("tea", ItemRegistry.tea);
        createGeneralTag("toast_with_jam", ItemRegistry.toastWithJam);
        createGeneralTag("toasts", Content.Furnace.TOAST.asItem());
        createGeneralTag("tofu", ItemRegistry.tofu);
        createGeneralTag("tofu_and_dumplings", ItemRegistry.tofuAndDumplings);
        createGeneralTag("tofuburgers", ItemRegistry.tofuBurger);
        createGeneralTag("tortillas", ItemRegistry.tortilla);
        createGeneralTag("trail_mixes", ItemRegistry.trailMix);
        createGeneralTag("treacle_tarts", ItemRegistry.treacleTart);
        createGeneralTag("trifle", ItemRegistry.trifle);
        createGeneralTag("tuna_sandwiches", ItemRegistry.tunaSandwich);
        createGeneralTag("veggie_salads", ItemRegistry.veggieSalad);
        createGeneralTag("wines", ItemRegistry.wine);
        createGeneralTag("yam_jam", ItemRegistry.yamJam);
        createGeneralTag("yoghurts", ItemRegistry.yoghurt);

        createGeneralTag("roasted_pumpkin_seeds", ItemRegistry.roastedPumpkinSeeds);
        createGeneralTag("roasted_sunflower_seeds", ItemRegistry.roastedSunflowerSeeds);
        createGeneralTag("pumpkin_bars", ItemRegistry.pumpkinBars);
        createGeneralTag("corn_breads", ItemRegistry.cornBread);
        createGeneralTag("pumpkin_soups", ItemRegistry.pumpkinSoup);
        createGeneralTag("meringue", ItemRegistry.meringue);
        createGeneralTag("cabbage_rolls", ItemRegistry.cabbageRoll);
        createGeneralTag("borscht", ItemRegistry.borscht);
        createGeneralTag("goulashes", ItemRegistry.goulash);
        createGeneralTag("beetroot_salads", ItemRegistry.beetrootSalad);
        createGeneralTag("candied_kumquats", ItemRegistry.candiedKumquats);
        createGeneralTag("shrimp", Content.Seafood.SHRIMP.asItem());
        createGeneralTag("tuna", Content.Seafood.TUNA.asItem());
        createGeneralTag("calamari", Content.Seafood.CALAMARI.asItem()).add(Content.Seafood.GLOWING_CALAMARI.asItem());
        createGeneralTag("crabs", Content.Seafood.CRAB.asItem());
        createGeneralTag("roe", Content.Seafood.ROE.asItem());
        createGeneralTag("clams", Content.Seafood.CLAM.asItem());
        createGeneralTag("oysters", Content.Seafood.OYSTER.asItem());
        createGeneralTag("cooked_shrimp", Content.Furnace.COOKED_SHRIMP.asItem());
        createGeneralTag("cooked_tuna", Content.Furnace.COOKED_TUNA.asItem());
        createGeneralTag("cooked_calamari", Content.Furnace.COOKED_CALAMARI.asItem());
        createGeneralTag("steamed_crabs", ItemRegistry.steamedCrab);
        createGeneralTag("glowing_calamari", Content.Seafood.GLOWING_CALAMARI.asItem());
        createGeneralTag("sea_lettuce", ItemRegistry.seaLettuce);
        createGeneralTag("deep_fried_shrimp", ItemRegistry.deepFriedShrimp);
        createGeneralTag("tuna_rolls", ItemRegistry.tunaRoll);
        createGeneralTag("fried_calamari", ItemRegistry.friedCalamari);
        createGeneralTag("crab_legs", ItemRegistry.crabLegs);
        createGeneralTag("steamed_clams", ItemRegistry.steamedClams);
        createGeneralTag("grilled_oysters", ItemRegistry.grilledOysters);
        createGeneralTag("anchovies", Content.Seafood.ANCHOVY.asItem());
        createGeneralTag("cooked_anchovies", Content.Furnace.COOKED_ANCHOVY.asItem());
        createGeneralTag("anchovy_pizzas", ItemRegistry.anchovyPizza);
        createGeneralTag("mashed_potatoes", ItemRegistry.mashedPotatoes);

        this.getOrCreateTagBuilder(register("water_bottles")).add(ItemRegistry.waterBottle).add(Items.WATER_BUCKET);
        this.getOrCreateTagBuilder(register("milks")).add(ItemRegistry.milkBottle).add(ItemRegistry.soyMilk).add(Items.MILK_BUCKET);
        this.getOrCreateTagBuilder(register("potatoes")).add(Items.POTATO).add(Content.Farmland.SWEETPOTATO.asItem());
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
        IndependentEntry independentEntry = new IndependentEntry(category + "/" + path);
        this.getOrCreateTagBuilder(forgeFriendlyTag).add(item);
        ObjectBuilderAccessor fabricGeneralTag = (ObjectBuilderAccessor) (Object) this.getOrCreateTagBuilder(register(name)).add(item);
        fabricGeneralTag.getBuilder().add(new Tag.TrackedEntry(independentEntry, fabricGeneralTag.getSource()));

        // this is the group i.e vegetables.json encompassing all the vegetables in the mod. it should pull from zucchini.json and not vegetables/zucchini.json
        ObjectBuilderAccessor group = (ObjectBuilderAccessor) (Object) this.getOrCreateTagBuilder(register(category));
        // we need a new independentEntry
        IndependentEntry entryForGroup = new IndependentEntry(name);
        group.getBuilder().add(new Tag.TrackedEntry(entryForGroup, group.getSource()));
    }

    private FabricTagProvider.FabricTagBuilder createGeneralTag(String name, Item item) {
        TagKey<Item> pluralTag = register(name);
        return this.getOrCreateTagBuilder(pluralTag).add(item);
    }

    /**
     * Special method for forge/fabric differentiations.
     * Forge conventions are sapling:"saplingName" without "sapling" appended ex: forge:saplings/apple
     * In fabric we would just do c:apple_saplings
     * This method creates the appropriate tags for both platforms
     * Forge: forge:saplings/apple
     * Fabric: c:apple_saplings
     * Saplings.json -> references Fabric -> references forge
     */
    private void createSeedSaplingTag(String category, String name, Item item) {
        String pluralSeedName = Registry.ITEM.getId(item).getPath() + "s";
        // Forge tags use seed/cropname, but not including seed name. artichoke good artichoke_seed bad.
        TagKey<Item> forgeFriendlyTag = register(category + "/" + name);
        IndependentEntry independentEntry = new IndependentEntry(category + "/" + name);

        this.getOrCreateTagBuilder(forgeFriendlyTag).add(item);
        ObjectBuilderAccessor group = (ObjectBuilderAccessor) (Object) this.getOrCreateTagBuilder(register(category));
        group.getBuilder().add(new Tag.TrackedEntry(independentEntry, group.getSource()));

        ObjectBuilderAccessor fabricGeneralTag = (ObjectBuilderAccessor) (Object) this.getOrCreateTagBuilder(register(pluralSeedName)).add(item);
        fabricGeneralTag.getBuilder().add(new Tag.TrackedEntry(independentEntry, fabricGeneralTag.getSource()));
    }

}
