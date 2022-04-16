package me.thonk.croptopia.registry;

import me.thonk.common.ItemNames;
import me.thonk.croptopia.items.*;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;

import static me.thonk.croptopia.Croptopia.createGroup;
import static me.thonk.croptopia.Croptopia.registerItem;
import static me.thonk.croptopia.registry.FoodRegistry.*;
import static net.minecraft.world.biome.Biome.Category.*;

public class ItemRegistry {

    // Spices
    public static Item paprika = new Item(createGroup()); // TODO need recipe to make paprika in future update
    public static Item salt = new Item(createGroup());

    // secondary ingredients?
    public static Item oliveOil = new Item(createGroup());
    public static Item cheese = new Item(createGroup().food(EDIBLE_5));
    public static Item flour = new Item(createGroup());
    public static Item butter = new Item(createGroup().food(EDIBLE_3));
    public static Item noodle = new Item(createGroup());
    public static Item tofu = new Item(createGroup().food(EDIBLE_5));
    public static Item molasses = new Item(createGroup());
    public static Item caramel = new Item(createGroup());
    public static Item chocolate = new Item(createGroup().food(EDIBLE_5));
    public static Item tortilla = new Item(createGroup().food(EDIBLE_3));
    public static Item soySauce = new Item(createGroup());
    public static Item dough = new Item(createGroup());
    public static Item ravioli = new Item(createGroup());
    public static Item salsa = new Item(createGroup().food(EDIBLE_3));
    public static Item artichokeDip = new Item(createGroup().food(EDIBLE_3));
    public static Item pepperoni = new Item(createGroup().food(EDIBLE_5));

    // drinks
    public static Item grapeJuice = new Drink(createGroup().food(EDIBLE_5_BUILDER.alwaysEdible().build()).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item orangeJuice = new Drink(createGroup().food(EDIBLE_5_BUILDER.alwaysEdible().build()).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item appleJuice = new Drink(createGroup().food(EDIBLE_5_BUILDER.alwaysEdible().build()).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item cranberryJuice = new Drink(createGroup().food(EDIBLE_5_BUILDER.alwaysEdible().build()).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item saguaroJuice = new Drink(createGroup().food(EDIBLE_5_BUILDER.alwaysEdible().build()).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item tomatoJuice = new Drink(createGroup().food(EDIBLE_5_BUILDER.alwaysEdible().build()).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item melonJuice = new Drink(createGroup().food(EDIBLE_5_BUILDER.alwaysEdible().build()).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item pineappleJuice = new Drink(createGroup().food(EDIBLE_5_BUILDER.alwaysEdible().build()).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item coffee = new Drink(createGroup().food(EDIBLE_5_BUILDER.alwaysEdible().build()).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item lemonade = new Drink(createGroup().food(EDIBLE_5_BUILDER.alwaysEdible().build()).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item limeade = new Drink(createGroup().food(EDIBLE_5_BUILDER.alwaysEdible().build()).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item soyMilk = new Drink(createGroup().food(EDIBLE_5_BUILDER.alwaysEdible().build()).recipeRemainder(Items.GLASS_BOTTLE));
    //public static Item tea;

    public static Item strawberrySmoothie = new Drink(createGroup().food(EDIBLE_7_BUILDER.alwaysEdible().build()));
    public static Item bananaSmoothie = new Drink(createGroup().food(EDIBLE_7_BUILDER.alwaysEdible().build()));
    public static Item kaleSmoothie = new Drink(createGroup().food(EDIBLE_14_BUILDER.alwaysEdible().build()));
    public static Item fruitSmoothie = new Drink(createGroup().food(EDIBLE_7_BUILDER.alwaysEdible().build()));

    public static Item chocolateMilkshake = new Drink(createGroup().food(EDIBLE_7_BUILDER.alwaysEdible().build()));

    public static Item beer = new Drink(createGroup().food(EDIBLE_7_BUILDER.alwaysEdible().build()));
    public static Item wine = new Drink(createGroup().food(EDIBLE_7_BUILDER.alwaysEdible().build()));
    public static Item mead = new Drink(createGroup().food(EDIBLE_7_BUILDER.alwaysEdible().build()));
    public static Item rum = new Drink(createGroup().food(EDIBLE_7_BUILDER.alwaysEdible().build()).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item pumpkinSpiceLatte = new Drink(createGroup().food(EDIBLE_14_BUILDER.alwaysEdible().build()));

    // jams
    public static Item grapeJam = new Drink(createGroup().food(EDIBLE_3_BUILDER.alwaysEdible().build()));
    public static Item strawberryJam = new Drink(createGroup().food(EDIBLE_3_BUILDER.alwaysEdible().build()));
    public static Item peachJam = new Drink(createGroup().food(EDIBLE_3_BUILDER.alwaysEdible().build()));
    public static Item apricotJam = new Drink(createGroup().food(EDIBLE_3_BUILDER.alwaysEdible().build()));
    public static Item blackberryJam = new Drink(createGroup().food(EDIBLE_3_BUILDER.alwaysEdible().build()));
    public static Item blueberryJam = new Drink(createGroup().food(EDIBLE_3_BUILDER.alwaysEdible().build()));
    public static Item cherryJam = new Drink(createGroup().food(EDIBLE_3_BUILDER.alwaysEdible().build()));
    public static Item elderberryJam = new Drink(createGroup().food(EDIBLE_3_BUILDER.alwaysEdible().build()));
    public static Item raspberryJam = new Drink(createGroup().food(EDIBLE_3_BUILDER.alwaysEdible().build()));

    // snacks?
    public static Item beefJerky = new Item(createGroup().food(EDIBLE_5));
    public static Item porkJerky = new Item(createGroup().food(EDIBLE_5));
    public static Item kaleChips = new Item(createGroup().food(EDIBLE_5));
    public static Item potatoChips = new Item(createGroup().food(EDIBLE_5));
    public static Item steamedRice = new Item(createGroup().food(EDIBLE_5));
    public static Item frenchFries = new Item(createGroup().food(EDIBLE_5));
    public static Item sweetPotatoFries = new Item(createGroup().food(EDIBLE_5));
    public static Item onionRings = new Item(createGroup().food(EDIBLE_5));
    public static Item raisins = new Item(createGroup().food(EDIBLE_3));
    public static Item doughnut = new Item(createGroup().food(EDIBLE_5));
    public static Item popcorn = new Item(createGroup().food(EDIBLE_3));
    public static Item bakedBeans = new Item(createGroup().food(EDIBLE_5));
    public static Item toast = new Item(createGroup().food(EDIBLE_7));
    public static Item cucumberSalad = new SoupItem(createGroup().food(EDIBLE_10));
    public static Item caesarSalad = new SoupItem(createGroup().food(EDIBLE_10));
    public static Item leafySalad = new SoupItem(createGroup().food(EDIBLE_10));
    public static Item fruitSalad = new SoupItem(createGroup().food(EDIBLE_10));
    public static Item veggieSalad = new SoupItem(createGroup().food(EDIBLE_10));
    public static Item porkAndBeans = new SoupItem(createGroup().food(EDIBLE_10));
    public static Item oatmeal = new SoupItem(createGroup().food(EDIBLE_7));
    public static Item leekSoup = new SoupItem(createGroup().food(EDIBLE_7));
    public static Item yoghurt = new SoupItem(createGroup().food(EDIBLE_5));
    public static Item saucyChips = new SoupItem(createGroup().food(EDIBLE_7));
    public static Item roastedNuts = new Item(createGroup().food(EDIBLE_5));
    public static Item trailMix = new Item(createGroup().food(EDIBLE_10));
    public static Item proteinBar = new Item(createGroup().food(EDIBLE_10));
    public static Item nougat = new Item(createGroup().food(EDIBLE_7));

    // breakfast
    public static Item scrambledEggs = new Item(createGroup().food(EDIBLE_7));
    public static Item butteredToast = new Item(createGroup().food(EDIBLE_9));
    public static Item toastWithJam = new Item(createGroup().food(EDIBLE_9));


    // meals
    public static Item hamSandwich = new Item(createGroup().food(EDIBLE_10));
    public static Item peanutButterAndJam = new Item(createGroup().food(EDIBLE_10));
    public static Item BLT = new Item(createGroup().food(EDIBLE_10));
    public static Item grilledCheese = new Item(createGroup().food(EDIBLE_7));
    public static Item tunaSandwich = new Item(createGroup().food(EDIBLE_10));
    public static Item cheeseburger = new Item(createGroup().food(EDIBLE_10));
    public static Item hamburger = new Item(createGroup().food(EDIBLE_10));
    public static Item tofuBurger = new Item(createGroup().food(EDIBLE_10));
    public static Item pizza = new Item(createGroup().food(EDIBLE_10));
    public static Item supremePizza = new Item(createGroup().food(EDIBLE_18));
    public static Item cheesePizza = new Item(createGroup().food(EDIBLE_14));
    public static Item pineapplePepperoniPizza = new Item(createGroup().food(EDIBLE_18));
    public static Item lemonChicken = new Item(createGroup().food(EDIBLE_10));
    public static Item friedChicken = new Item(createGroup().food(EDIBLE_10));
    public static Item chickenAndNoodles = new Item(createGroup().food(EDIBLE_10));
    public static Item chickenAndDumplings = new Item(createGroup().food(EDIBLE_10));
    public static Item tofuAndDumplings = new Item(createGroup().food(EDIBLE_14));
    public static Item spaghettiSquash = new Item(createGroup().food(EDIBLE_10));
    public static Item chickenAndRice = new Item(createGroup().food(EDIBLE_10));
    public static Item taco = new Item(createGroup().food(EDIBLE_10));
    public static Item sushi = new Item(createGroup().food(EDIBLE_10));
    public static Item eggRoll = new Item(createGroup().food(EDIBLE_10));
    public static Item cashewChicken = new Item(createGroup().food(EDIBLE_14));

    // desert block?
    //public static Item coffeeCake;
    //public static Item chocolateCake;
    //public static Item strawberryShortCake;
    //public static Item carrotCake;
    //public static Item turtleCake;

    // desert item
    public static Item applePie = new Item(createGroup().food(EDIBLE_14));
    public static Item yamJam = new Item(createGroup().food(EDIBLE_14));
    public static Item bananaCreamPie = new Item(createGroup().food(EDIBLE_14));
    public static Item candyCorn = new Item(createGroup().food(EDIBLE_5));
    public static Item vanillaIceCream = new Item(createGroup().food(EDIBLE_10));
    public static Item strawberryIceCream = new Item(createGroup().food(EDIBLE_10));
    public static Item mangoIceCream = new Item(createGroup().food(EDIBLE_10));
    public static Item rumRaisinIceCream = new Item(createGroup().food(EDIBLE_14));
    public static Item pecanIceCream = new Item(createGroup().food(EDIBLE_10));
    public static Item cherryPie = new Item(createGroup().food(EDIBLE_14));
    public static Item cheeseCake = new Item(createGroup().food(EDIBLE_14));
    public static Item brownies = new Item(createGroup().food(EDIBLE_10));
    public static Item snickerDoodle = new Item(createGroup().food(EDIBLE_7));
    public static Item bananaNutBread = new Item(createGroup().food(EDIBLE_10));
    public static Item pecanPie = new Item(createGroup().food(EDIBLE_14));
    public static Item candiedNuts = new Item(createGroup().food(EDIBLE_5));
    public static Item almondBrittle = new Item(createGroup().food(EDIBLE_7));
    public static Item oatmealCookie = new Item(createGroup().food(EDIBLE_5));
    public static Item nuttyCookie = new Item(createGroup().food(EDIBLE_5));
    //public static Item praline = new Item(createGroup().food(EDIBLE_5));

    public static Item burrito = new Item(createGroup().food(EDIBLE_10));
    public static Item tostada = new Item(createGroup().food(EDIBLE_10));
    public static Item horchata = new Item(createGroup().food(EDIBLE_10));
    public static Item carnitas = new Item(createGroup().food(EDIBLE_10));
    public static Item fajitas = new Item(createGroup().food(EDIBLE_10));
    public static Item enchilada = new Item(createGroup().food(EDIBLE_10));
    public static Item churros = new Item(createGroup().food(EDIBLE_5));
    public static Item tamales = new Item(createGroup().food(EDIBLE_14));
    public static Item tresLecheCake = new Item(createGroup().food(EDIBLE_18));
    public static Item stuffedPoblanos = new Item(createGroup().food(EDIBLE_14));
    public static Item chiliRelleno = new Item(createGroup().food(EDIBLE_14));
    public static Item crema = new Item(createGroup().food(EDIBLE_3));
    public static Item refriedBeans = new Item(createGroup().food(EDIBLE_7));
    public static Item chimichanga = new Item(createGroup().food(EDIBLE_14));
    public static Item quesadilla = new Item(createGroup().food(EDIBLE_10));

    public static Item cinnamon = new Item(createGroup());
    public static Item cornHusk = new Item(createGroup());
    public static Item whippingCream = new Item(createGroup());

    public static Item cinnamonSapling = new AliasedBlockItem(BlockRegistry.cinnamonSaplingBlock, createGroup());
    public static Item cinnamonLog = new AliasedBlockItem(BlockRegistry.cinnamonLog, createGroup());
    public static Item strippedCinnamonLog = new AliasedBlockItem(BlockRegistry.strippedCinnamonLog, createGroup());
    public static Item cinnamonWood = new AliasedBlockItem(BlockRegistry.cinnamonWood, createGroup());
    public static Item strippedCinnamonWood = new AliasedBlockItem(BlockRegistry.strippedCinnamonWood, createGroup());

    // 1.4.0
    public static Item shepherdsPie = new Item(createGroup().food(EDIBLE_18));
    public static Item beefWellington = new Item(createGroup().food(EDIBLE_18));
    public static Item fishAndChips = new Item(createGroup().food(EDIBLE_10));
    public static Item etonMess = new Item(createGroup().food(EDIBLE_10));
    public static Item tea = new Drink(createGroup().food(EDIBLE_5_BUILDER.alwaysEdible().build()));
    public static Item cornishPasty = new Item(createGroup().food(EDIBLE_10));
    public static Item scones = new Item(createGroup().food(EDIBLE_10));
    public static Item figgyPudding = new Item(createGroup().food(EDIBLE_10));
    public static Item treacleTart = new Item(createGroup().food(EDIBLE_10));
    public static Item stickyToffeePudding = new Item(createGroup().food(EDIBLE_14));
    public static Item trifle = new Item(createGroup().food(EDIBLE_14));
    public static Item waterBottle = new Item(createGroup());
    public static Item milkBottle = new Item(createGroup());

    // 1.7.0
    public static Item ajvar = new Item(createGroup().food(EDIBLE_7));
    public static Item ajvarToast = new Item(createGroup().food(EDIBLE_14));
    public static Item avocadoToast = new Item(createGroup().food(EDIBLE_10));
    public static Item bakedSweetPotato = new Item(createGroup().food(EDIBLE_7));
    public static Item bakedYam = new Item(createGroup().food(EDIBLE_7));
    public static Item beefStew = new Item(createGroup().food(EDIBLE_14));
    public static Item beefStirFry = new Item(createGroup().food(EDIBLE_14));
    public static Item butteredGreenBeans = new Item(createGroup().food(EDIBLE_10));
    public static Item cheesyAsparagus = new Item(createGroup().food(EDIBLE_10));
    public static Item chocolateIceCream = new Item(createGroup().food(EDIBLE_14));
    public static Item cookedBacon = new Item(createGroup().food(EDIBLE_7));
    public static Item eggplantParmesan = new Item(createGroup().food(EDIBLE_18));
    public static Item fruitCake = new Item(createGroup().food(EDIBLE_10));
    public static Item grilledEggplant = new Item(createGroup().food(EDIBLE_10));
    public static Item kiwiSorbet = new Item(createGroup().food(EDIBLE_7));
    public static Item knife = new CookingUtensil(createGroup().maxCount(1));
    public static Item lemonCoconutBar = new Item(createGroup().food(EDIBLE_7));
    public static Item netherWartStew = new Item(createGroup().food(EDIBLE_7));
    public static Item peanutButter = new Item(createGroup().food(EDIBLE_5));
    public static Item peanutButterWCelery = new Item(createGroup().food(EDIBLE_7));
    public static Item potatoSoup = new Item(createGroup().food(EDIBLE_10));
    public static Item ratatouille = new Item(createGroup().food(EDIBLE_18));
    public static Item rawBacon = new Item(createGroup().food(EDIBLE_1));
    public static Item rhubarbCrisp = new Item(createGroup().food(EDIBLE_10));
    public static Item rhubarbPie = new Item(createGroup().food(EDIBLE_10));
    public static Item roastedAsparagus = new Item(createGroup().food(EDIBLE_7));
    public static Item roastedRadishes = new Item(createGroup().food(EDIBLE_7));
    public static Item roastedSquash = new Item(createGroup().food(EDIBLE_7));
    public static Item roastedTurnips = new Item(createGroup().food(EDIBLE_7));
    public static Item steamedBroccoli = new Item(createGroup().food(EDIBLE_7));
    public static Item steamedGreenBeans = new Item(createGroup().food(EDIBLE_7));
    public static Item stirFry = new Item(createGroup().food(EDIBLE_18));
    public static Item stuffedArtichoke = new Item(createGroup().food(EDIBLE_18));
    public static Item toastSandwich = new Item(createGroup().food(EDIBLE_10));

    // cooking utensils?
    public static Item foodPress = new CookingUtensil(createGroup().maxCount(1));
    public static Item fryingPan = new CookingUtensil(createGroup().maxCount(1));
    public static Item cookingPot = new CookingUtensil(createGroup().maxCount(1));
    public static Item mortarAndPestle = new CookingUtensil(createGroup().maxCount(1));

    public static Item saltOre = new AliasedBlockItem(BlockRegistry.salt, createGroup());

    public static Item guide = new GuideBookItem(createGroup());


    public static void init() {
        registerItem(ItemNames.GUIDE, guide);

        registerItem(ItemNames.PAPRIKA, paprika);
        registerItem(ItemNames.SALT, salt);


        registerItem(ItemNames.OLIVE_OIL, oliveOil);
        registerItem(ItemNames.CHEESE, cheese);
        registerItem(ItemNames.FLOUR, flour);
        registerItem(ItemNames.BUTTER, butter);
        registerItem(ItemNames.NOODLE, noodle);
        registerItem(ItemNames.TOFU, tofu);
        registerItem(ItemNames.MOLASSES, molasses);
        registerItem(ItemNames.CARAMEL, caramel);
        registerItem(ItemNames.CHOCOLATE, chocolate);
        registerItem(ItemNames.TORTILLA, tortilla);
        registerItem(ItemNames.SOY_SAUCE, soySauce);
        registerItem(ItemNames.DOUGH, dough);
        registerItem(ItemNames.RAVIOLI, ravioli);
        registerItem(ItemNames.SALSA, salsa);
        registerItem(ItemNames.ARTICHOKE_DIP, artichokeDip);
        registerItem(ItemNames.PEPPERONI, pepperoni);

        registerItem(ItemNames.GRAPE_JUICE, grapeJuice);
        registerItem(ItemNames.ORANGE_JUICE, orangeJuice);
        registerItem(ItemNames.APPLE_JUICE, appleJuice);
        registerItem(ItemNames.CRANBERRY_JUICE, cranberryJuice);
        registerItem(ItemNames.SAGUARO_JUICE, saguaroJuice);
        registerItem(ItemNames.TOMATO_JUICE, tomatoJuice);
        registerItem(ItemNames.MELON_JUICE, melonJuice);
        registerItem(ItemNames.PINEAPPLE_JUICE, pineappleJuice);
        registerItem(ItemNames.COFFEE, coffee);
        registerItem(ItemNames.LEMONADE, lemonade);
        registerItem(ItemNames.LIMEADE, limeade);
        registerItem(ItemNames.SOY_MILK, soyMilk);
        registerItem(ItemNames.STRAWBERRY_SMOOTHIE, strawberrySmoothie);
        registerItem(ItemNames.BANANA_SMOOTHIE, bananaSmoothie);
        registerItem(ItemNames.KALE_SMOOTHIE, kaleSmoothie);
        registerItem(ItemNames.FRUIT_SMOOTHIE, fruitSmoothie);
        registerItem(ItemNames.CHOCOLATE_MILKSHAKE, chocolateMilkshake);
        registerItem(ItemNames.BEER, beer);
        registerItem(ItemNames.WINE, wine);
        registerItem(ItemNames.MEAD, mead);
        registerItem(ItemNames.RUM, rum);
        registerItem(ItemNames.PUMPKIN_SPICE_LATTE, pumpkinSpiceLatte);

        registerItem(ItemNames.GRAPE_JAM, grapeJam);
        registerItem(ItemNames.STRAWBERRY_JAM, strawberryJam);
        registerItem(ItemNames.PEACH_JAM, peachJam);
        registerItem(ItemNames.APRICOT_JAM, apricotJam);
        registerItem(ItemNames.BLACKBERRY_JAM, blackberryJam);
        registerItem(ItemNames.BLUEBERRY_JAM, blueberryJam);
        registerItem(ItemNames.CHERRY_JAM, cherryJam);
        registerItem(ItemNames.ELDERBERRY_JAM, elderberryJam);
        registerItem(ItemNames.RASPBERRY_JAM, raspberryJam);

        registerItem(ItemNames.BEEF_JERKY, beefJerky); // todo no recipe
        registerItem(ItemNames.PORK_JERKY, porkJerky); // todo no recipe
        registerItem(ItemNames.KALE_CHIPS, kaleChips);
        registerItem(ItemNames.POTATO_CHIPS, potatoChips);
        registerItem(ItemNames.STEAMED_RICE, steamedRice);
        registerItem(ItemNames.FRENCH_FRIES, frenchFries);
        registerItem(ItemNames.SWEET_POTATO_FRIES, sweetPotatoFries);
        registerItem(ItemNames.ONION_RINGS, onionRings);
        registerItem(ItemNames.RAISINS, raisins);
        registerItem(ItemNames.DOUGHNUT, doughnut);
        registerItem(ItemNames.POPCORN, popcorn);
        registerItem(ItemNames.BAKED_BEANS, bakedBeans);
        registerItem(ItemNames.TOAST, toast);
        registerItem(ItemNames.CUCUMBER_SALAD, cucumberSalad);
        registerItem(ItemNames.CAESAR_SALAD, caesarSalad);
        registerItem(ItemNames.LEAFY_SALAD, leafySalad);
        registerItem(ItemNames.FRUIT_SALAD, fruitSalad);
        registerItem(ItemNames.VEGGIE_SALAD, veggieSalad);
        registerItem(ItemNames.PORK_AND_BEANS, porkAndBeans);
        registerItem(ItemNames.OATMEAL, oatmeal);
        registerItem(ItemNames.LEEK_SOUP, leekSoup);
        registerItem(ItemNames.YOGHURT, yoghurt);
        registerItem(ItemNames.SAUCY_CHIPS, saucyChips);
        registerItem(ItemNames.ROASTED_NUTS, roastedNuts);
        registerItem(ItemNames.TRAIL_MIX, trailMix);
        registerItem(ItemNames.PROTEIN_BAR, proteinBar);
        registerItem(ItemNames.NOUGAT, nougat);

        registerItem(ItemNames.SCRAMBLED_EGGS, scrambledEggs);
        registerItem(ItemNames.BUTTERED_TOAST, butteredToast);
        registerItem(ItemNames.TOAST_WITH_JAM, toastWithJam);

        registerItem(ItemNames.HAM_SANDWICH, hamSandwich);
        registerItem(ItemNames.PEANUT_BUTTER_AND_JAM, peanutButterAndJam);
        registerItem(ItemNames.BLT, BLT);
        registerItem(ItemNames.GRILLED_CHEESE, grilledCheese);
        registerItem(ItemNames.TUNA_SANDWICH, tunaSandwich); // todo no recipe
        registerItem(ItemNames.CHEESEBURGER, cheeseburger);
        registerItem(ItemNames.HAMBURGER, hamburger);
        registerItem(ItemNames.TOFUBURGER, tofuBurger);
        registerItem(ItemNames.PIZZA, pizza);
        registerItem(ItemNames.SUPREME_PIZZA, supremePizza);
        registerItem(ItemNames.CHEESE_PIZZA, cheesePizza);
        registerItem(ItemNames.PINEAPPLE_PEPPERONI_PIZZA, pineapplePepperoniPizza);
        registerItem(ItemNames.LEMON_CHICKEN, lemonChicken);
        registerItem(ItemNames.FRIED_CHICKEN, friedChicken);
        registerItem(ItemNames.CHICKEN_AND_NOODLES, chickenAndNoodles);
        registerItem(ItemNames.CHICKEN_AND_DUMPLINGS, chickenAndDumplings);
        registerItem(ItemNames.TOFU_AND_DUMPLINGS, tofuAndDumplings);
        registerItem(ItemNames.SPAGHETTI_SQUASH, spaghettiSquash);
        registerItem(ItemNames.CHICKEN_AND_RICE, chickenAndRice);
        registerItem(ItemNames.TACO, taco);
        registerItem(ItemNames.SUSHI, sushi);
        registerItem(ItemNames.EGG_ROLL, eggRoll);
        registerItem(ItemNames.CASHEW_CHICKEN, cashewChicken);

        registerItem(ItemNames.APPLE_PIE, applePie);
        registerItem(ItemNames.YAM_JAM, yamJam);
        registerItem(ItemNames.BANANA_CREAM_PIE, bananaCreamPie);
        registerItem(ItemNames.CANDY_CORN, candyCorn);
        registerItem(ItemNames.VANILLA_ICE_CREAM, vanillaIceCream);
        registerItem(ItemNames.STRAWBERRY_ICE_CREAM, strawberryIceCream);
        registerItem(ItemNames.MANGO_ICE_CREAM, mangoIceCream);
        registerItem(ItemNames.RUM_RAISIN_ICE_CREAM, rumRaisinIceCream);
        registerItem(ItemNames.PECAN_ICE_CREAM, pecanIceCream);
        registerItem(ItemNames.CHERRY_PIE, cherryPie);
        registerItem(ItemNames.CHEESE_CAKE, cheeseCake);
        registerItem(ItemNames.BROWNIES, brownies);
        registerItem(ItemNames.SNICKER_DOODLE, snickerDoodle);
        registerItem(ItemNames.BANANA_NUT_BREAD, bananaNutBread);
        registerItem(ItemNames.PECAN_PIE, pecanPie);
        registerItem(ItemNames.CANDIED_NUTS, candiedNuts);
        registerItem(ItemNames.ALMOND_BRITTLE, almondBrittle);
        registerItem(ItemNames.RAISIN_OATMEAL_COOKIE, oatmealCookie);
        registerItem(ItemNames.NUTTY_COOKIE, nuttyCookie);

        registerItem(ItemNames.BURRITO, burrito);
        registerItem(ItemNames.TOSTADA, tostada);
        registerItem(ItemNames.HORCHATA, horchata);
        registerItem(ItemNames.CARNITAS, carnitas);
        registerItem(ItemNames.FAJITAS, fajitas);
        registerItem(ItemNames.ENCHILADA, enchilada);
        registerItem(ItemNames.CHURROS, churros);
        registerItem(ItemNames.TAMALES, tamales);
        registerItem(ItemNames.TRES_LECHE_CAKE, tresLecheCake);
        registerItem(ItemNames.STUFFED_POBLANOS, stuffedPoblanos);
        registerItem(ItemNames.CHILI_RELLENO, chiliRelleno);
        registerItem(ItemNames.CREMA, crema);
        registerItem(ItemNames.REFRIED_BEANS, refriedBeans);
        registerItem(ItemNames.CHIMICHANGA, chimichanga);
        registerItem(ItemNames.QUESADILLA, quesadilla);

        registerItem(ItemNames.AJVAR, ajvar);
        registerItem(ItemNames.AJVAR_TOAST, ajvarToast);
        registerItem(ItemNames.AVOCADO_TOAST, avocadoToast);
        registerItem(ItemNames.BAKED_SWEET_POTATO, bakedSweetPotato);
        registerItem(ItemNames.BAKED_YAM, bakedYam);
        registerItem(ItemNames.BEEF_STEW, beefStew);
        registerItem(ItemNames.BEEF_STIR_FRY, beefStirFry);
        registerItem(ItemNames.BUTTERED_GREEN_BEANS, butteredGreenBeans);
        registerItem(ItemNames.CHEESY_ASPARAGUS, cheesyAsparagus);
        registerItem(ItemNames.CHOCOLATE_ICE_CREAM, chocolateIceCream);
        registerItem(ItemNames.COOKED_BACON, cookedBacon);
        registerItem(ItemNames.EGGPLANT_PARMESAN, eggplantParmesan);
        registerItem(ItemNames.FRUIT_CAKE, fruitCake);
        registerItem(ItemNames.GRILLED_EGGPLANT, grilledEggplant);
        registerItem(ItemNames.KIWI_SORBET, kiwiSorbet);
        registerItem(ItemNames.KNIFE, knife);
        registerItem(ItemNames.LEMON_COCONUT_BAR, lemonCoconutBar);
        registerItem(ItemNames.NETHER_WART_STEW, netherWartStew);
        registerItem(ItemNames.PEANUT_BUTTER, peanutButter);
        registerItem(ItemNames.PEANUT_BUTTER_W_CELERY, peanutButterWCelery);
        registerItem(ItemNames.POTATO_SOUP, potatoSoup);
        registerItem(ItemNames.RATATOUILLE, ratatouille);
        registerItem(ItemNames.RAW_BACON, rawBacon);
        registerItem(ItemNames.RHUBARB_CRISP, rhubarbCrisp);
        registerItem(ItemNames.RHUBARB_PIE, rhubarbPie);
        registerItem(ItemNames.ROASTED_ASPARAGUS, roastedAsparagus);
        registerItem(ItemNames.ROASTED_RADISHES, roastedRadishes);
        registerItem(ItemNames.ROASTED_SQUASH, roastedSquash);
        registerItem(ItemNames.ROASTED_TURNIPS, roastedTurnips);
        registerItem(ItemNames.STEAMED_BROCCOLI, steamedBroccoli);
        registerItem(ItemNames.STEAMED_GREEN_BEANS, steamedGreenBeans);
        registerItem(ItemNames.STIR_FRY, stirFry);
        registerItem(ItemNames.STUFFED_ARTICHOKE, stuffedArtichoke);
        registerItem(ItemNames.TOAST_SANDWICH, toastSandwich);

        registerItem(ItemNames.SHEPHERDS_PIE, shepherdsPie);
        registerItem(ItemNames.BEEF_WELLINGTON, beefWellington);
        registerItem(ItemNames.FISH_AND_CHIPS, fishAndChips);
        registerItem(ItemNames.ETON_MESS, etonMess);
        registerItem(ItemNames.TEA, tea);
        registerItem(ItemNames.CORNISH_PASTY, cornishPasty);
        registerItem(ItemNames.SCONES, scones);
        registerItem(ItemNames.FIGGY_PUDDING, figgyPudding);
        registerItem(ItemNames.TREACLE_TART, treacleTart);
        registerItem(ItemNames.STICKY_TOFFEE_PUDDING, stickyToffeePudding);
        registerItem(ItemNames.TRIFLE, trifle);
        registerItem(ItemNames.WATER_BOTTLE, waterBottle);
        registerItem(ItemNames.MILK_BOTTLE, milkBottle);

        registerItem(ItemNames.CINNAMON, cinnamon);
        registerItem(ItemNames.CORN_HUSK, cornHusk);
        registerItem(ItemNames.WHIPPING_CREAM, whippingCream);

        registerItem(ItemNames.FOOD_PRESS, foodPress);
        registerItem(ItemNames.FRYING_PAN, fryingPan);
        registerItem(ItemNames.COOKING_POT, cookingPot);
        registerItem(ItemNames.MORTAR_AND_PESTLE, mortarAndPestle);

        registerItem(ItemNames.CINNAMON_SAPLING, cinnamonSapling);
        registerItem(ItemNames.CINNAMON_LOG, cinnamonLog);
        registerItem(ItemNames.STRIPPED_CINNAMON_LOG, strippedCinnamonLog);
        registerItem(ItemNames.CINNAMON_WOOD, cinnamonWood);
        registerItem(ItemNames.STRIPPED_CINNAMON_WOOD, strippedCinnamonWood);

        registerItem(ItemNames.SALT_ORE, saltOre);
    }
}
