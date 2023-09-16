package com.epherical.croptopia.datagen;

import com.epherical.croptopia.Croptopia;
import com.epherical.croptopia.mixin.datagen.IdentifierAccessor;
import com.epherical.croptopia.mixin.datagen.ObjectBuilderAccessor;
import com.epherical.croptopia.mixin.datagen.PathProviderAccessor;
import com.epherical.croptopia.mixin.datagen.TagProviderAccessor;
import com.epherical.croptopia.register.Content;
import com.epherical.croptopia.register.TagCategory;
import com.epherical.croptopia.register.helpers.FarmlandCrop;
import com.epherical.croptopia.register.helpers.Furnace;
import com.epherical.croptopia.register.helpers.IceCream;
import com.epherical.croptopia.register.helpers.Jam;
import com.epherical.croptopia.register.helpers.Juice;
import com.epherical.croptopia.register.helpers.Pie;
import com.epherical.croptopia.register.helpers.Seafood;
import com.epherical.croptopia.register.helpers.Smoothie;
import com.epherical.croptopia.register.helpers.Tree;
import com.epherical.croptopia.register.helpers.TreeCrop;
import com.epherical.croptopia.register.helpers.Utensil;
import com.epherical.croptopia.util.PluralInfo;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.impl.datagen.ForcedTagEntry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.VanillaItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagEntry;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.TagManager;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class CroptopiaIndependentItemTagProvider extends FabricTagProvider.ItemTagProvider {



    public CroptopiaIndependentItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture, null);
        ((TagProviderAccessor) this).setPathProvider(
                new DependentPathProvider(output,
                        PackOutput.Target.DATA_PACK,
                        TagManager.getTagDir(Registries.ITEM)));
    }

    @Override
    public String getName() {
        return "Croptopia Independent Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        generateCrops();
        generateSeedsSaplings();
        generateOtherEnums();
        generateMisc();
    }

    protected void generateCrops() {
        for (FarmlandCrop crop : FarmlandCrop.FARMLAND_CROPS) {
            createCategoryTag(crop.getTagCategory().getLowerCaseName(), PluralInfo.plural(crop.getLowercaseName(), crop.hasPlural()), crop.asItem());
            if (crop.getTagCategory() != TagCategory.CROPS) { // don't double only-crops
                createCategoryTag(TagCategory.CROPS.getLowerCaseName(), PluralInfo.plural(crop.getLowercaseName(), crop.hasPlural()), crop.asItem());
            }
        }
        for (TreeCrop crop : TreeCrop.TREE_CROPS) {
            createCategoryTag(crop.getTagCategory().getLowerCaseName(), PluralInfo.plural(crop.getLowercaseName(), crop.hasPlural()), crop.asItem());
            if (crop.getTagCategory() != TagCategory.CROPS) { // don't double only-crops
                createCategoryTag(TagCategory.CROPS.getLowerCaseName(), PluralInfo.plural(crop.getLowercaseName(), crop.hasPlural()), crop.asItem());
            }
            if (crop.getTagCategory() == TagCategory.NUTS) { // nuts are fruits
                createCategoryTag(TagCategory.FRUITS.getLowerCaseName(), PluralInfo.plural(crop.getLowercaseName(), crop.hasPlural()), crop.asItem());
            }
        }
        for (Tree crop : Tree.copy()) {
            createCategoryTag(crop.getTagCategory().getLowerCaseName(), PluralInfo.plural(crop.getLowercaseName(), crop.hasPlural()), crop.asItem());
        }
        // the following four are all done above with a category tag of crops I believe
        /*createGeneralTag("saguaros", Content.saguaro);
        createGeneralTag("turmeric", Content.turmeric);
        createGeneralTag("tea_leaves", Content.teaLeaves);
        createGeneralTag("cinnamon", Content.cinnamon);*/
    }

    protected void generateSeedsSaplings() {
        // these should be singular, they are pluralized in the method, this is because forge seed tags don't include the "seed" portion.
        for (FarmlandCrop crop : FarmlandCrop.FARMLAND_CROPS) {
            if (crop == Content.CHILE_PEPPER) {
                createSeedSaplingTag("seeds", "chilepepper", crop.getSeedItem());
            } else {
                createSeedSaplingTag("seeds", crop.getLowercaseName(), crop.getSeedItem());
            }
        }
        for (TreeCrop crop : TreeCrop.TREE_CROPS) {
            createSeedSaplingTag("saplings", crop.getLowercaseName(), crop.getSaplingItem());
        }
        for (Tree crop : Tree.copy()) {
            createSeedSaplingTag("saplings", crop.getLowercaseName(), crop.getSapling());
        }
    }

    protected void generateOtherEnums() {
        for (Seafood seafood : Seafood.copy()) {
            createGeneralTag(seafood.getPlural(), seafood.asItem());
        }
        for (Furnace furnace : Furnace.copy()) {
            createGeneralTag(furnace.getPlural(), furnace.asItem());
        }
        for (Juice juice : Juice.copy()) {
            createCategoryTag("juices", juice.name().toLowerCase() + "s", juice.asItem());
        }
        for (Jam jam : Jam.copy()) {
            createCategoryTag("jams", jam.name().toLowerCase() + "s", jam.asItem());
        }
        for (Smoothie smoothie : Smoothie.copy()) {
            createGeneralTag(smoothie.name().toLowerCase() + "s", smoothie.asItem());
        }
        for (IceCream iceCream : IceCream.copy()) {
            createGeneralTag(iceCream.name().toLowerCase() + "s", iceCream.asItem());
        }
        for (Pie pie : Pie.copy()) {
            createGeneralTag(pie.name().toLowerCase() + "s", pie.asItem());
        }
        for (Utensil utensil : Utensil.copy()) {
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
        createGeneralTag("tofuburgers", Content.TOFUBURGER);
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

        createGeneralTag("baked_crepes", Content.BAKED_CREPES);
        createGeneralTag("cinnamon_rolls", Content.CINNAMON_ROLL);
        createGeneralTag("croque_madame", Content.CROQUE_MADAME);
        createGeneralTag("croque_monsieur", Content.CROQUE_MONSIEUR);
        createGeneralTag("dauphine_potatoes", Content.DAUPHINE_POTATOES);
        createGeneralTag("fried_frog_legs", Content.FRIED_FROG_LEGS);
        createGeneralTag("frog_legs", Content.FROG_LEGS);
        createGeneralTag("ground_pork", Content.GROUND_PORK);
        createGeneralTag("hashed_brown", Content.HASHED_BROWN);
        createGeneralTag("macaron", Content.MACARON);
        createGeneralTag("quiche", Content.QUICHE);
        createGeneralTag("sausages", Content.SAUSAGE);
        createGeneralTag("sunny_side_eggs", Content.SUNNY_SIDE_EGGS);
        createGeneralTag("sweet_crepes", Content.SWEET_CREPES);
        createGeneralTag("the_big_breakfast", Content.THE_BIG_BREAKFAST);

        this.tag(register("water_bottles")).add(reverseLookup(Content.WATER_BOTTLE)).add(reverseLookup(Items.WATER_BUCKET)).addOptional(new ResourceLocation("early_buckets:wooden_water_bucket"));
        this.tag(register("milks")).add(reverseLookup(Content.MILK_BOTTLE)).add(reverseLookup(Content.SOY_MILK)).add(reverseLookup(Items.MILK_BUCKET)).addOptionalTag(independentTag("milk_buckets"));
        this.tag(register("potatoes")).add(reverseLookup(Items.POTATO)).add(reverseLookup(Content.SWEETPOTATO.asItem()));
    }

    private static TagKey<Item> register(String id) {
        return TagKey.create(Registries.ITEM, Croptopia.createIdentifier(id));
    }

    private void createCategoryTag(String category, String name, Item item) {
        String path = reverseLookup(item).location().getPath();
        TagKey<Item> forgeFriendlyTag = register(category + "/" + path);
        ResourceLocation independentEntry = independentTag(category + "/" + path);
        this.tag(forgeFriendlyTag).add(reverseLookup(item));
        ObjectBuilderAccessor fabricGeneralTag = (ObjectBuilderAccessor) this.tag(register(name)).add(reverseLookup(item));
        fabricGeneralTag.getBuilder().add(new ForcedTagEntry(TagEntry.tag(independentEntry)));

        // this is the group i.e vegetables.json encompassing all the vegetables in the mod. it should pull from zucchini.json and not vegetables/zucchini.json
        ObjectBuilderAccessor group = (ObjectBuilderAccessor) this.tag(register(category));
        // we need a new independentEntry
        ResourceLocation entryForGroup = independentTag(name);
        group.getBuilder().add(new ForcedTagEntry(TagEntry.tag(entryForGroup)));
    }

    private FabricTagBuilder createGeneralTag(String name, Item item) {
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
        if (item == Content.VANILLA.getSeedItem()) {
            pluralSeedName = reverseLookup(item).location().getPath();
        } else {
            pluralSeedName = reverseLookup(item).location().getPath() + "s";
        }

        // Forge tags use seed/cropname, but not including seed name. artichoke good artichoke_seed bad.
        TagKey<Item> forgeFriendlyTag = register(category + "/" + name);
        ResourceLocation independentEntry = independentTag(category + "/" + name);

        this.tag(forgeFriendlyTag).add(reverseLookup(item));
        ObjectBuilderAccessor<?> group = (ObjectBuilderAccessor<?>) this.tag(register(category));
        group.getBuilder().add(new ForcedTagEntry(TagEntry.tag(independentEntry)));

        ObjectBuilderAccessor<?> fabricGeneralTag = (ObjectBuilderAccessor<?>) this.tag(register(pluralSeedName)).add(reverseLookup(item));
        fabricGeneralTag.getBuilder().add(new ForcedTagEntry(TagEntry.tag(independentEntry)));
    }

    private ResourceLocation independentTag(String name) {
        IdentifierAccessor accessor = (IdentifierAccessor) Croptopia.createIdentifier(name);
        accessor.setNamespace("${dependent}"); // lmao
        return (ResourceLocation) accessor;
    }
}
