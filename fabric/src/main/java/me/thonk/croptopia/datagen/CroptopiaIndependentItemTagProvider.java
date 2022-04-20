package me.thonk.croptopia.datagen;

import me.thonk.croptopia.Croptopia;
import me.thonk.croptopia.datagen.tags.IndependentEntry;
import me.thonk.croptopia.mixin.datagen.ObjectBuilderAccessor;
import me.thonk.croptopia.registry.Content;
import me.thonk.croptopia.registry.Content;
import me.thonk.croptopia.util.PluralInfo;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.data.DataCache;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tag.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

import java.nio.file.Path;

public class CroptopiaIndependentItemTagProvider extends FabricTagProvider.ItemTagProvider {


    public CroptopiaIndependentItemTagProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateTags() {
        generateCrops();
        generateSeedsSaplings();
        generateOtherEnums();
        generateMisc();
    }

    protected void generateCrops() {
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
        /*createGeneralTag("saguaros", Content.saguaro);
        createGeneralTag("turmeric", Content.turmeric);
        createGeneralTag("tea_leaves", Content.teaLeaves);
        createGeneralTag("cinnamon", Content.cinnamon);*/
    }

    protected void generateSeedsSaplings() {
        // these should be singular, they are pluralized in the method, this is because forge seed tags don't include the "seed" portion.
        for (Content.Farmland crop : Content.Farmland.values()) {
            if (crop == Content.Farmland.CHILE_PEPPER) {
                createSeedSaplingTag("seeds", "chilepepper", crop.getSeed());
            } else {
                createSeedSaplingTag("seeds", crop.getLowerCaseName(), crop.getSeed());
            }
        }
        for (Content.Tree crop : Content.Tree.values()) {
            createSeedSaplingTag("saplings", crop.getLowerCaseName(), crop.getSapling());
        }
        for (Content.Bark crop : Content.Bark.values()) {
            createSeedSaplingTag("saplings", crop.getLowerCaseName(), crop.getSapling());
        }
    }

    protected void generateOtherEnums() {
        for (Content.Seafood seafood : Content.Seafood.values()) {
            createGeneralTag(seafood.getPlural(), seafood.asItem());
        }

        for (Content.Furnace cooked : Content.Furnace.values()) {
            createGeneralTag(cooked.getPlural(), cooked.asItem());
        }

        for (Content.Juice juice : Content.Juice.values()) {
            createCategoryTag("juices", juice.name().toLowerCase() + "_juices", juice.asItem());
        }
        for (Content.Jam jam : Content.Jam.values()) {
            createCategoryTag("jams", jam.name().toLowerCase() + "_jams", jam.asItem());
        }
        for (Content.Smoothie smoothie : Content.Smoothie.values()) {
            createGeneralTag(smoothie.name().toLowerCase() + "_smoothies", smoothie.asItem());
        }
        for (Content.IceCream iceCream : Content.IceCream.values()) {
            createGeneralTag(iceCream.name().toLowerCase() + "_ice_creams", iceCream.asItem());
        }
        for (Content.Pie pie : Content.Pie.values()) {
            createGeneralTag(pie.name().toLowerCase() + "_pies", pie.asItem());
        }

        for (Content.Utensil utensil : Content.Utensil.values()) {
            createGeneralTag(utensil.getPlural(), utensil.asItem());
        }
    }

    protected void generateMisc() {
        createGeneralTag("almond_brittles", Content.ALMOND_BRITTLE);
        createGeneralTag("artichoke_dips", Content.ARTICHOKE_DIP);
        createGeneralTag("banana_cream_pies", Content.BANANA_CREAM_PIE);
        createGeneralTag("banana_nut_breads", Content.BANANA_NUT_BREAD);
        createGeneralTag("beef_jerkies", Content.BEEF_JERKY);
        createGeneralTag("beef_wellington", Content.BEEF_WELLINGTON);
        createGeneralTag("beers", Content.BEER);
        createGeneralTag("blts", Content.BLT);
        createGeneralTag("brownies", Content.BROWNIES);
        createGeneralTag("buttered_toasts", Content.BUTTERED_TOAST);
        createGeneralTag("butters", Content.BUTTER);
        createGeneralTag("caesar_salads", Content.CAESAR_SALAD);
        createGeneralTag("candied_nuts", Content.CANDIED_NUTS);
        createGeneralTag("candy_corns", Content.CANDY_CORN);
        createGeneralTag("cashew_chickens", Content.CASHEW_CHICKEN);
        createGeneralTag("cheese_cakes", Content.CHEESE_CAKE);
        createGeneralTag("cheese_pizzas", Content.CHEESE_PIZZA);
        createGeneralTag("cheeseburgers", Content.CHEESEBURGER);
        createGeneralTag("cheeses", Content.CHEESE);
        createGeneralTag("chicken_and_dumplings", Content.CHICKEN_AND_DUMPLINGS);
        createGeneralTag("chicken_and_noodles", Content.CHICKEN_AND_NOODLES);
        createGeneralTag("chicken_and_rice", Content.CHICKEN_AND_RICE);
        createGeneralTag("chocolate_milkshakes", Content.CHOCOLATE_MILKSHAKE);
        createGeneralTag("chocolates", Content.CHOCOLATE);
        createGeneralTag("coffees", Content.COFFEE);
        createGeneralTag("cornish_pasty", Content.CORNISH_PASTY);
        createGeneralTag("cucumber_salads", Content.CUCUMBER_SALAD);
        createGeneralTag("doughnuts", Content.DOUGHNUT);
        createGeneralTag("doughs", Content.DOUGH);
        createGeneralTag("egg_rolls", Content.EGG_ROLL);
        createGeneralTag("eton_mess", Content.ETON_MESS);
        createGeneralTag("figgy_pudding", Content.FIGGY_PUDDING);
        createGeneralTag("fish_and_chips", Content.FISH_AND_CHIPS);
        createGeneralTag("flour", Content.FLOUR);
        createGeneralTag("french_fries", Content.FRENCH_FRIES);
        createGeneralTag("fried_chickens", Content.FRIED_CHICKEN);
        createGeneralTag("fruit_salads", Content.FRUIT_SALAD);
        createGeneralTag("fruit_smoothies", Content.FRUIT_SMOOTHIE);
        createGeneralTag("grilled_cheeses", Content.GRILLED_CHEESE);
        createGeneralTag("ham_sandwiches", Content.HAM_SANDWICH);
        createGeneralTag("hamburgers", Content.HAMBURGER);
        createGeneralTag("kale_chips", Content.KALE_CHIPS);
        createGeneralTag("kale_smoothies", Content.KALE_SMOOTHIE);
        createGeneralTag("leafy_salads", Content.LEAFY_SALAD);
        createGeneralTag("leek_soups", Content.LEEK_SOUP);
        createGeneralTag("lemon_chickens", Content.LEMON_CHICKEN);
        createGeneralTag("lemonades", Content.LEMONADE);
        createGeneralTag("limeades", Content.LIMEADE);
        createGeneralTag("meads", Content.MEAD);
        createGeneralTag("milk_bottles", Content.MILK_BOTTLE);
        createGeneralTag("noodles", Content.NOODLE);
        createGeneralTag("nougats", Content.NOUGAT);
        createGeneralTag("nutty_cookies", Content.NUTTY_COOKIE);
        createGeneralTag("oatmeals", Content.OATMEAL);
        createGeneralTag("olive_oils", Content.OLIVE_OIL);
        createGeneralTag("onion_rings", Content.ONION_RINGS);
        createGeneralTag("paprika", Content.PAPRIKA);
        createGeneralTag("peanut_butter_and_jam", Content.PEANUT_BUTTER_AND_JAM);
        createGeneralTag("pepperoni", Content.PEPPERONI);
        createGeneralTag("pineapple_pepperoni_pizzas", Content.PINEAPPLE_PEPPERONI_PIZZA);
        createGeneralTag("pizzas", Content.PIZZA);
        createGeneralTag("pork_and_beanss", Content.PORK_AND_BEANS);
        createGeneralTag("pork_jerkies", Content.PORK_JERKY);
        createGeneralTag("potato_chips", Content.POTATO_CHIPS);
        createGeneralTag("protein_bars", Content.PROTEIN_BAR);
        createGeneralTag("pumpkin_spice_lattes", Content.PUMPKIN_SPICE_LATTE);
        createGeneralTag("raisin_oatmeal_cookies", Content.OATMEAL_COOKIE);
        createGeneralTag("ravioli", Content.RAVIOLI);
        createGeneralTag("roasted_nuts", Content.ROASTED_NUTS);
        createGeneralTag("rum_raisin_ice_creams", Content.RUM_RAISIN_ICE_CREAM);
        createGeneralTag("rums", Content.RUM);
        createGeneralTag("salsas", Content.SALSA);
        createGeneralTag("salt_ores", Content.SALT_ORE);
        createGeneralTag("salts", Content.SALT);
        createGeneralTag("saucy_chips", Content.SAUCY_CHIPS);
        createGeneralTag("scones", Content.SCONES);
        createGeneralTag("scrambled_eggs", Content.SCRAMBLED_EGGS);
        createGeneralTag("shepherds_pie", Content.SHEPHERDS_PIE);
        createGeneralTag("snicker_doodles", Content.SNICKER_DOODLE);
        createGeneralTag("soy_milks", Content.SOY_MILK);
        createGeneralTag("soy_sauces", Content.SOY_SAUCE);
        createGeneralTag("spaghetti_squashs", Content.SPAGHETTI_SQUASH);
        createGeneralTag("steamed_rices", Content.STEAMED_RICE);
        createGeneralTag("sticky_toffee_pudding", Content.STICKY_TOFFEE_PUDDING);
        createGeneralTag("supreme_pizzas", Content.SUPREME_PIZZA);
        createGeneralTag("sushis", Content.SUSHI);
        createGeneralTag("sweet_potato_friess", Content.SWEET_POTATO_FRIES);
        createGeneralTag("tacos", Content.TACO);
        createGeneralTag("tea", Content.TEA);
        createGeneralTag("toast_with_jam", Content.TOAST_WITH_JAM);
        createGeneralTag("tofu", Content.TOFU);
        createGeneralTag("tofu_and_dumplings", Content.TOFU_AND_DUMPLINGS);
        createGeneralTag("tofuburgers", Content.TOFU_BURGER);
        createGeneralTag("tortillas", Content.TORTILLA);
        createGeneralTag("trail_mixes", Content.TRAIL_MIX);
        createGeneralTag("treacle_tarts", Content.TREACLE_TART);
        createGeneralTag("trifle", Content.TRIFLE);
        createGeneralTag("tuna_sandwiches", Content.TUNA_SANDWICH);
        createGeneralTag("veggie_salads", Content.VEGGIE_SALAD);
        createGeneralTag("wines", Content.WINE);
        createGeneralTag("yam_jam", Content.YAM_JAM);
        createGeneralTag("yoghurts", Content.YOGHURT);

        createGeneralTag("roasted_pumpkin_seeds", Content.ROASTED_PUMPKIN_SEEDS);
        createGeneralTag("roasted_sunflower_seeds", Content.ROASTED_SUNFLOWER_SEEDS);
        createGeneralTag("pumpkin_bars", Content.PUMPKIN_BARS);
        createGeneralTag("corn_breads", Content.CORN_BREAD);
        createGeneralTag("pumpkin_soups", Content.PUMPKIN_SOUP);
        createGeneralTag("meringue", Content.MERINGUE);
        createGeneralTag("cabbage_rolls", Content.CABBAGE_ROLL);
        createGeneralTag("borscht", Content.BORSCHT);
        createGeneralTag("goulashes", Content.GOULASH);
        createGeneralTag("beetroot_salads", Content.BEETROOT_SALAD);
        createGeneralTag("candied_kumquats", Content.CANDIED_KUMQUATS);
        createGeneralTag("steamed_crabs", Content.STEAMED_CRAB);
        createGeneralTag("sea_lettuce", Content.SEA_LETTUCE);
        createGeneralTag("deep_fried_shrimp", Content.DEEP_FRIED_SHRIMP);
        createGeneralTag("tuna_rolls", Content.TUNA_ROLL);
        createGeneralTag("fried_calamari", Content.FRIED_CALAMARI);
        createGeneralTag("crab_legs", Content.CRAB_LEGS);
        createGeneralTag("steamed_clams", Content.STEAMED_CLAMS);
        createGeneralTag("grilled_oysters", Content.GRILLED_OYSTERS);
        createGeneralTag("anchovy_pizzas", Content.ANCHOVY_PIZZA);
        createGeneralTag("mashed_potatoes", Content.MASHED_POTATOES);

        this.getOrCreateTagBuilder(register("water_bottles")).add(Content.WATER_BOTTLE).add(Items.WATER_BUCKET);
        this.getOrCreateTagBuilder(register("milks")).add(Content.MILK_BOTTLE).add(Content.SOY_MILK).add(Items.MILK_BUCKET);
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
        String pluralSeedName;
        if (item == Content.Farmland.VANILLA.getSeed()) {
           pluralSeedName = Registry.ITEM.getId(item).getPath();
        } else {
            pluralSeedName = Registry.ITEM.getId(item).getPath() + "s";
        }

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
