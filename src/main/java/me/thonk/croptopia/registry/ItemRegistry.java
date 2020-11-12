package me.thonk.croptopia.registry;

import me.thonk.croptopia.items.Drink;
import me.thonk.croptopia.items.SeedItem;
import net.minecraft.item.*;

import static me.thonk.croptopia.Croptopia.createGroup;
import static me.thonk.croptopia.Croptopia.registerItem;
import static me.thonk.croptopia.registry.FoodRegistry.*;
import static net.minecraft.world.biome.Biome.Category.*;

public class ItemRegistry {
    // Fruits & Vegetables // cropitem
    public static Item artichoke = new Item(createGroup().food(EDIBLE_1));
    public static Item asparagus = new Item(createGroup().food(EDIBLE_3));
    public static Item barley = new Item(createGroup().food(EDIBLE_1));
    public static Item bellPepper = new Item(createGroup().food(EDIBLE_3));
    public static Item blackBean = new Item(createGroup().food(EDIBLE_3));
    public static Item blackberry = new Item(createGroup().food(EDIBLE_3));
    public static Item blueberry =  new Item(createGroup().food(EDIBLE_3));
    public static Item broccoli =  new Item(createGroup().food(EDIBLE_3));
    public static Item cabbage = new Item(createGroup().food(EDIBLE_1));
    public static Item cantaloupe = new Item(createGroup().food(EDIBLE_3));
    public static Item cauliflower = new Item(createGroup().food(EDIBLE_3));
    public static Item celery = new Item(createGroup().food(EDIBLE_3));
    public static Item coffeeBeans = new Item(createGroup().food(EDIBLE_3));
    public static Item corn = new Item(createGroup().food(EDIBLE_3));
    public static Item cranberry = new Item(createGroup().food(EDIBLE_3));
    public static Item cucumber = new Item(createGroup().food(EDIBLE_3));
    public static Item currant =  new Item(createGroup().food(EDIBLE_3));
    public static Item eggplant = new Item(createGroup().food(EDIBLE_3));
    public static Item elderberry =  new Item(createGroup().food(EDIBLE_3));
    public static Item garlic =  new Item(createGroup().food(EDIBLE_1));
    public static Item grape =  new Item(createGroup().food(EDIBLE_3));
    public static Item greenBean =  new Item(createGroup().food(EDIBLE_3));
    public static Item greenOnion =  new Item(createGroup().food(EDIBLE_1));
    public static Item honeydew =  new Item(createGroup().food(EDIBLE_3));
    public static Item hops =  new Item(createGroup());
    public static Item kale =  new Item(createGroup().food(EDIBLE_3));
    public static Item kiwi =  new Item(createGroup().food(EDIBLE_3));
    public static Item leek =  new Item(createGroup().food(EDIBLE_3));
    public static Item lettuce =  new Item(createGroup().food(EDIBLE_3));
    public static Item oat = new Item(createGroup().food(EDIBLE_1));
    public static Item olive = new Item(createGroup().food(EDIBLE_3));
    public static Item onion = new Item(createGroup().food(EDIBLE_3));
    public static Item peanut = new Item(createGroup().food(EDIBLE_1));
    public static Item pineapple = new Item(createGroup().food(EDIBLE_3));
    public static Item radish = new Item(createGroup().food(EDIBLE_3));
    public static Item raspberry = new Item(createGroup().food(EDIBLE_3));
    public static Item rhubarb = new Item(createGroup().food(EDIBLE_3));
    public static Item rice = new Item(createGroup().food(EDIBLE_1));
    public static Item rutabaga = new Item(createGroup().food(EDIBLE_3));
    public static Item saguaro = new Item(createGroup().food(EDIBLE_3));
    public static Item soybean = new Item(createGroup().food(EDIBLE_1));
    public static Item spinach = new Item(createGroup().food(EDIBLE_3));
    public static Item squash = new Item(createGroup().food(EDIBLE_3));
    public static Item strawberry = new Item(createGroup().food(EDIBLE_3));
    public static Item sweetPotato = new Item(createGroup().food(EDIBLE_3));
    public static Item tomatillo = new Item(createGroup().food(EDIBLE_3));
    public static Item tomato = new Item(createGroup().food(EDIBLE_3));
    public static Item turnip = new Item(createGroup().food(EDIBLE_3));
    public static Item yam = new Item(createGroup().food(EDIBLE_3));
    public static Item zucchini = new Item(createGroup().food(EDIBLE_3));

    // Trees
    public static Item orange = new Item(createGroup().food(EDIBLE_3));
    public static Item banana = new Item(createGroup().food(EDIBLE_3));
    public static Item persimmon = new Item(createGroup().food(EDIBLE_3));
    public static Item plum = new Item(createGroup().food(EDIBLE_3));
    public static Item cherry = new Item(createGroup().food(EDIBLE_3));
    public static Item lemon = new Item(createGroup().food(EDIBLE_3));
    public static Item grapefruit = new Item(createGroup().food(EDIBLE_3));
    public static Item kumquat = new Item(createGroup().food(EDIBLE_3));
    public static Item peach = new Item(createGroup().food(EDIBLE_3));
    public static Item coconut = new Item(createGroup().food(EDIBLE_1));
    public static Item nutmeg = new Item(createGroup().food(EDIBLE_1));
    public static Item fig = new Item(createGroup().food(EDIBLE_3));
    public static Item nectarine = new Item(createGroup().food(EDIBLE_3));
    public static Item mango = new Item(createGroup().food(EDIBLE_3));
    public static Item dragonFruit = new Item(createGroup().food(EDIBLE_3));
    public static Item starFruit = new Item(createGroup().food(EDIBLE_3));
    public static Item avocado = new Item(createGroup().food(EDIBLE_3));
    public static Item apricot = new Item(createGroup().food(EDIBLE_3));
    public static Item pear = new Item(createGroup().food(EDIBLE_3));
    public static Item lime = new Item(createGroup().food(EDIBLE_3));
    public static Item date = new Item(createGroup().food(EDIBLE_3));
    public static Item almond = new Item(createGroup().food(EDIBLE_3));
    public static Item cashew = new Item(createGroup().food(EDIBLE_1));
    public static Item pecan = new Item(createGroup().food(EDIBLE_3));
    public static Item walnut = new Item(createGroup().food(EDIBLE_3));

    // Spices
    public static Item mustard = new Item(createGroup());
    public static Item vanilla = new Item(createGroup());
    public static Item paprika = new Item(createGroup()); // TODO need recipe to make paprika in future update
    public static Item chilePepper = new Item(createGroup());
    public static Item salt = new Item(createGroup());
    public static Item turmeric = new Item(createGroup());
    public static Item ginger = new Item(createGroup());
    // Herbs
    public static Item basil = new Item(createGroup().food(EDIBLE_1));


    public static Item artichokeSeed = new SeedItem(BlockRegistry.artichokeCropBlock, createGroup(), SWAMP);
    public static Item asparagusSeed = new SeedItem(BlockRegistry.asparagusCropBlock, createGroup(), SWAMP);
    public static Item bellPepperSeed = new SeedItem(BlockRegistry.bellPepperCropBlock, createGroup(), PLAINS);
    public static Item blackBeanSeed = new SeedItem(BlockRegistry.blackBeanCropBlock, createGroup(), FOREST);
    public static Item blackberrySeed = new SeedItem(BlockRegistry.blackberryCropBlock, createGroup(), FOREST);
    public static Item blueberrySeed = new SeedItem(BlockRegistry.blueberryCropBlock, createGroup(), FOREST);
    public static Item broccoliSeed = new SeedItem(BlockRegistry.broccoliCropBlock, createGroup(), PLAINS);
    public static Item cabbageSeed = new SeedItem(BlockRegistry.cabbageCropBlock, createGroup(), PLAINS);
    public static Item cantaloupeSeed = new SeedItem(BlockRegistry.cantaloupeCropBlock, createGroup(), FOREST);
    public static Item cauliflowerSeed = new SeedItem(BlockRegistry.cauliflowerCropBlock, createGroup(), FOREST);
    public static Item celerySeed = new SeedItem(BlockRegistry.celeryCropBlock, createGroup(), FOREST);
    public static Item coffeeSeed = new SeedItem(BlockRegistry.coffeeCropBlock, createGroup(), JUNGLE);
    public static Item cornSeed = new SeedItem(BlockRegistry.cornCropBlock, createGroup(), PLAINS);
    public static Item cranberrySeed = new SeedItem(BlockRegistry.cranberryCropBlock, createGroup(), SWAMP);
    public static Item cucumberSeed = new SeedItem(BlockRegistry.cucumberCropBlock, createGroup(), PLAINS);
    public static Item currantSeed = new SeedItem(BlockRegistry.currantCropBlock, createGroup(), SWAMP);
    public static Item eggplantSeed = new SeedItem(BlockRegistry.eggplantCropBlock, createGroup(), JUNGLE);
    public static Item elderberrySeed = new SeedItem(BlockRegistry.elderberryCropBlock, createGroup(), FOREST);
    public static Item garlicSeed = new SeedItem(BlockRegistry.garlicCropBlock, createGroup(), JUNGLE);
    public static Item grapeSeed = new SeedItem(BlockRegistry.grapeCropBlock, createGroup(), FOREST);
    public static Item greenBeanSeed = new SeedItem(BlockRegistry.greenBeanCropBlock, createGroup(), PLAINS);
    public static Item greenOnionSeed = new SeedItem(BlockRegistry.greenOnionCropBlock, createGroup(), JUNGLE);
    public static Item honeydewSeed =new SeedItem(BlockRegistry.honeydewCropBlock, createGroup(), JUNGLE);
    public static Item hopsSeed = new SeedItem(BlockRegistry.hopsCropBlock, createGroup(), SAVANNA);
    public static Item kaleSeed = new SeedItem(BlockRegistry.kaleCropBlock, createGroup(), PLAINS);
    public static Item kiwiSeed = new SeedItem(BlockRegistry.kiwiCropBlock, createGroup(), SAVANNA);
    public static Item leekSeed = new SeedItem(BlockRegistry.leekCropBlock, createGroup(), SAVANNA);
    public static Item lettuceSeed =new SeedItem(BlockRegistry.lettuceCropBlock, createGroup(), PLAINS);
    public static Item oliveSeed = new SeedItem(BlockRegistry.oliveCropBlock, createGroup(), SAVANNA);
    public static Item onionSeed = new SeedItem(BlockRegistry.onionCropBlock, createGroup(), JUNGLE);
    public static Item peanutSeed = new SeedItem(BlockRegistry.peanutCropBlock, createGroup(), JUNGLE);
    public static Item pineappleSeed = new SeedItem(BlockRegistry.pineappleCropBlock, createGroup(), JUNGLE);
    public static Item radishSeed = new SeedItem(BlockRegistry.radishCropBlock, createGroup(), FOREST);
    public static Item raspberrySeed = new SeedItem(BlockRegistry.raspberryCropBlock, createGroup(), FOREST);
    public static Item rhubarbSeed = new SeedItem(BlockRegistry.rhubarbCropBlock, createGroup(), JUNGLE);
    public static Item riceSeed = new SeedItem(BlockRegistry.riceCropBlock, createGroup(), JUNGLE);
    public static Item rutabagaSeed = new SeedItem(BlockRegistry.rutabagaCropBlock, createGroup(), SAVANNA);
    public static Item saguaroSeed = new SeedItem(BlockRegistry.saguaroCropBlock, createGroup(), DESERT);
    public static Item spinachSeed = new SeedItem(BlockRegistry.spinachCropBlock, createGroup(), FOREST);
    public static Item squashSeed = new SeedItem(BlockRegistry.squashCropBlock, createGroup(), SAVANNA);
    public static Item strawberrySeed = new SeedItem(BlockRegistry.strawberryCropBlock, createGroup(), FOREST);
    public static Item sweetPotatoSeed = new SeedItem(BlockRegistry.sweetPotatoCropBlock, createGroup(), PLAINS);
    public static Item tomatilloSeed = new SeedItem(BlockRegistry.tomatilloCropBlock, createGroup(), FOREST);
    public static Item tomatoSeed = new SeedItem(BlockRegistry.tomatoCropBlock, createGroup(), FOREST);
    public static Item turnipSeed = new SeedItem(BlockRegistry.turnipCropBlock, createGroup(), JUNGLE);
    public static Item yamSeed = new SeedItem(BlockRegistry.yamCropBlock, createGroup(), SAVANNA);
    public static Item zucchiniSeed = new SeedItem(BlockRegistry.zucchiniCropBlock, createGroup(), SAVANNA);
    public static Item mustardSeed = new SeedItem(BlockRegistry.mustardCropBlock, createGroup(), PLAINS);
    //public static Item paprikaSeed = registerItem("paprika_seed", new CroptopiaSeedItem(BlockRegistry.paprikaCropBlock, createGroup(), DESERT));
    public static Item pepperSeed = new SeedItem(BlockRegistry.chilePepperCropBlock, createGroup(), PLAINS);
    public static Item turmericSeed = new SeedItem(BlockRegistry.turmericCropBlock, createGroup(), SAVANNA);
    public static Item gingerSeed = new SeedItem(BlockRegistry.gingerCropBlock, createGroup(), SAVANNA);
    //public static Item chivesSeed = registerItem("chives_seed", new CroptopiaSeedItem(BlockRegistry.chivesCropBlock, createGroup(), JUNGLE));
    public static Item basilSeed = new SeedItem(BlockRegistry.basilCropBlock, createGroup(), JUNGLE);
    public static Item oatSeed = new SeedItem(BlockRegistry.oatCropBlock, createGroup(), PLAINS);
    public static Item barleySeed = new SeedItem(BlockRegistry.barleyCropBlock, createGroup(), PLAINS);
    public static Item soybeanSeed = new SeedItem(BlockRegistry.soybeanCropBlock, createGroup(), PLAINS);



    public static Item appleSapling = registerItem("apple_sapling", new AliasedBlockItem(BlockRegistry.appleSaplingBlock, createGroup()));
    public static Item bananaSapling = registerItem("banana_sapling", new AliasedBlockItem(BlockRegistry.bananaSaplingBlock, createGroup()));
    public static Item orangeSapling = registerItem("orange_sapling", new AliasedBlockItem(BlockRegistry.orangeSaplingBlock, createGroup()));
    public static Item persimmonSapling = registerItem("persimmon_sapling", new AliasedBlockItem(BlockRegistry.persimmonSaplingBlock, createGroup()));
    public static Item plumSapling = registerItem("plum_sapling", new AliasedBlockItem(BlockRegistry.plumSaplingBlock, createGroup()));
    public static Item cherrySapling = registerItem("cherry_sapling", new AliasedBlockItem(BlockRegistry.cherrySaplingBlock, createGroup()));
    public static Item lemonSapling = registerItem("lemon_sapling", new AliasedBlockItem(BlockRegistry.lemonSaplingBlock, createGroup()));
    public static Item grapefruitSapling = registerItem("grapefruit_sapling", new AliasedBlockItem(BlockRegistry.grapefruitSaplingBlock, createGroup()));
    public static Item kumquatSapling = registerItem("kumquat_sapling", new AliasedBlockItem(BlockRegistry.kumquatSaplingBlock, createGroup()));
    public static Item peachSapling = registerItem("peach_sapling", new AliasedBlockItem(BlockRegistry.peachSaplingBlock, createGroup()));
    public static Item coconutSapling = registerItem("coconut_sapling", new AliasedBlockItem(BlockRegistry.coconutSaplingBlock, createGroup()));
    public static Item nutmegSapling = registerItem("nutmeg_sapling", new AliasedBlockItem(BlockRegistry.nutmegSaplingBlock, createGroup()));
    public static Item figSapling = registerItem("fig_sapling", new AliasedBlockItem(BlockRegistry.figSaplingBlock, createGroup()));
    public static Item nectarineSapling = registerItem("nectarine_sapling", new AliasedBlockItem(BlockRegistry.nectarineSaplingBlock, createGroup()));
    public static Item mangoSapling = registerItem("mango_sapling", new AliasedBlockItem(BlockRegistry.mangoSaplingBlock, createGroup()));
    public static Item dragonFruitSapling = registerItem("dragonfruit_sapling", new AliasedBlockItem(BlockRegistry.dragonFruitSaplingBlock, createGroup()));
    public static Item starFruitSapling = registerItem("starfruit_sapling", new AliasedBlockItem(BlockRegistry.starFruitSaplingBlock, createGroup()));
    public static Item avocadoSapling = registerItem("avocado_sapling", new AliasedBlockItem(BlockRegistry.avocadoSaplingBlock, createGroup()));
    public static Item apricotSapling = registerItem("apricot_sapling", new AliasedBlockItem(BlockRegistry.apricotSaplingBlock, createGroup()));
    public static Item pearSapling = registerItem("pear_sapling", new AliasedBlockItem(BlockRegistry.pearSaplingBlock, createGroup()));
    public static Item limeSapling = registerItem("lime_sapling", new AliasedBlockItem(BlockRegistry.limeSaplingBlock, createGroup()));
    public static Item dateSapling = registerItem("date_sapling", new AliasedBlockItem(BlockRegistry.dateSaplingBlock, createGroup()));
    public static Item almondSapling = new AliasedBlockItem(BlockRegistry.almondSaplingBlock, createGroup());
    public static Item cashewSapling = new AliasedBlockItem(BlockRegistry.cashewSaplingBlock, createGroup());
    public static Item pecanSapling = new AliasedBlockItem(BlockRegistry.pecanSaplingBlock, createGroup());
    public static Item walnutSapling = new AliasedBlockItem(BlockRegistry.walnutSaplingBlock, createGroup());


    public static Item vanillaSeed;

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
    public static Item grapeJuice = new Drink(createGroup().food(EDIBLE_5).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item orangeJuice = new Drink(createGroup().food(EDIBLE_5).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item appleJuice = new Drink(createGroup().food(EDIBLE_5).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item cranberryJuice = new Drink(createGroup().food(EDIBLE_5).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item saguaroJuice = new Drink(createGroup().food(EDIBLE_5).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item tomatoJuice = new Drink(createGroup().food(EDIBLE_5).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item melonJuice = new Drink(createGroup().food(EDIBLE_5).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item pineappleJuice = new Drink(createGroup().food(EDIBLE_5).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item coffee = new Drink(createGroup().food(EDIBLE_5).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item lemonade = new Drink(createGroup().food(EDIBLE_5).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item limeade = new Drink(createGroup().food(EDIBLE_5).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item soyMilk = new Drink(createGroup().food(EDIBLE_5).recipeRemainder(Items.GLASS_BOTTLE));
    //public static Item tea;

    public static Item strawberrySmoothie = new Drink(createGroup().food(EDIBLE_7));
    public static Item bananaSmoothie = new Drink(createGroup().food(EDIBLE_7));
    public static Item kaleSmoothie = new Drink(createGroup().food(EDIBLE_14));
    public static Item fruitSmoothie = new Drink(createGroup().food(EDIBLE_7));

    public static Item chocolateMilkshake = new Drink(createGroup().food(EDIBLE_7));

    public static Item beer = new Drink(createGroup().food(EDIBLE_7));
    public static Item wine = new Drink(createGroup().food(EDIBLE_7));
    public static Item mead = new Drink(createGroup().food(EDIBLE_7));
    public static Item rum = new Drink(createGroup().food(EDIBLE_7));
    public static Item pumpkinSpiceLatte = new Drink(createGroup().food(EDIBLE_14));

    // jams
    public static Item grapeJam = new Drink(createGroup().food(EDIBLE_3).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item strawberryJam = new Drink(createGroup().food(EDIBLE_3).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item peachJam = new Drink(createGroup().food(EDIBLE_3).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item apricotJam = new Drink(createGroup().food(EDIBLE_3).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item blackberryJam = new Drink(createGroup().food(EDIBLE_3).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item blueberryJam = new Drink(createGroup().food(EDIBLE_3).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item cherryJam = new Drink(createGroup().food(EDIBLE_3).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item elderberryJam = new Drink(createGroup().food(EDIBLE_3).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item raspberryJam = new Drink(createGroup().food(EDIBLE_3).recipeRemainder(Items.GLASS_BOTTLE));

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
    public static Item cucumberSalad = new Item(createGroup().food(EDIBLE_10));
    public static Item caesarSalad = new Item(createGroup().food(EDIBLE_10));
    public static Item leafySalad = new Item(createGroup().food(EDIBLE_10));
    public static Item fruitSalad = new Item(createGroup().food(EDIBLE_10));
    public static Item veggieSalad = new Item(createGroup().food(EDIBLE_10));
    public static Item porkAndBeans = new Item(createGroup().food(EDIBLE_10));
    public static Item oatmeal = new Item(createGroup().food(EDIBLE_7));
    public static Item leekSoup = new Item(createGroup().food(EDIBLE_7));
    public static Item yoghurt = new Item(createGroup().food(EDIBLE_5));
    public static Item saucyChips = new Item(createGroup().food(EDIBLE_7));
    /*public static Item roastedNuts = new Item(createGroup().food(EDIBLE_5));
    public static Item trailMix = new Item(createGroup().food(EDIBLE_10));
    public static Item proteinBar = new Item(createGroup().food(EDIBLE_10));
    public static Item nougat = new Item(createGroup().food(EDIBLE_7));*/

    // breakfast
    public static Item scrambledEggs = new Item(createGroup().food(EDIBLE_7));
    public static Item butteredToast = new Item(createGroup().food(EDIBLE_7));
    public static Item toastWithJam = new Item(createGroup().food(EDIBLE_7));


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
    //public static Item cashewChicken = new Item(createGroup().food(EDIBLE_14));

    // desert block?
    public static Item coffeeCake;
    public static Item chocolateCake;
    public static Item fruitCake;
    public static Item strawberryShortCake;
    public static Item carrotCake;
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
    //public static Item pecanIceCream = new Item(createGroup().food(EDIBLE_10));
    public static Item cherryPie = new Item(createGroup().food(EDIBLE_14));
    public static Item cheeseCake = new Item(createGroup().food(EDIBLE_14));
    public static Item brownies = new Item(createGroup().food(EDIBLE_10));
    public static Item snickerDoodle = new Item(createGroup().food(EDIBLE_7));
    public static Item bananaNutBread = new Item(createGroup().food(EDIBLE_10));
    /*public static Item pecanPie = new Item(createGroup().food(EDIBLE_14));
    public static Item candiedNuts = new Item(createGroup().food(EDIBLE_5));
    public static Item almondBrittle = new Item(createGroup().food(EDIBLE_7));
    public static Item oatmealCookie = new Item(createGroup().food(EDIBLE_5));
    public static Item nuttyCookie = new Item(createGroup().food(EDIBLE_5));
    public static Item praline = new Item(createGroup().food(EDIBLE_5));*/

    // cooking utensils?
    public static Item foodPress = new Item(createGroup().maxCount(1).maxDamageIfAbsent(500));
    public static Item fryingPan = new SwordItem(ToolMaterials.IRON, 3, 1f, createGroup().maxCount(1).maxDamageIfAbsent(500));
    public static Item cookingPot = new Item(createGroup().maxCount(1).maxDamageIfAbsent(500));
    public static Item mortarAndPestle = new Item(createGroup().maxCount(1).maxDamageIfAbsent(500));

    public static Item saltOre = new AliasedBlockItem(BlockRegistry.salt, createGroup());


    public static void init() {
        registerItem("artichoke", artichoke);
        registerItem("asparagus", asparagus);
        registerItem("barley", barley);
        registerItem("bellpepper", bellPepper);
        registerItem("blackbean", blackBean);
        registerItem("blackberry", blackberry);
        registerItem("blueberry", blueberry);
        registerItem("broccoli", broccoli);
        registerItem("cabbage", cabbage);
        registerItem("cantaloupe", cantaloupe);
        registerItem("cauliflower", cauliflower);
        registerItem("celery", celery);
        registerItem("coffee_beans", coffeeBeans);
        registerItem("corn", corn);
        registerItem("cranberry", cranberry);
        registerItem("cucumber", cucumber);
        registerItem("currant", currant);
        registerItem("eggplant", eggplant);
        registerItem("elderberry", elderberry);
        registerItem("garlic", garlic);
        registerItem("grape", grape);
        registerItem("greenbean", greenBean);
        registerItem("greenonion", greenOnion);
        registerItem("honeydew", honeydew);
        registerItem("hops", hops);
        registerItem("kale", kale);
        registerItem("kiwi", kiwi);
        registerItem("leek", leek);
        registerItem("lettuce", lettuce);
        registerItem("oat", oat);
        registerItem("olive", olive);
        registerItem("onion", onion);
        registerItem("peanut", peanut);
        registerItem("pineapple", pineapple);
        registerItem("radish", radish);
        registerItem("raspberry", raspberry);
        registerItem("rhubarb", rhubarb);
        registerItem("rice", rice);
        registerItem("rutabaga", rutabaga);
        registerItem("saguaro", saguaro);
        registerItem("soybean", soybean);
        registerItem("spinach", spinach);
        registerItem("squash", squash);
        registerItem("strawberry", strawberry);
        registerItem("sweetpotato", sweetPotato);
        registerItem("tomatillo", tomatillo);
        registerItem("tomato", tomato);
        registerItem("turnip", turnip);
        registerItem("yam", yam);
        registerItem("zucchini", zucchini);

        registerItem("orange", orange);
        registerItem("banana", banana);
        registerItem("persimmon", persimmon);
        registerItem("plum", plum);
        registerItem("cherry", cherry);
        registerItem("lemon", lemon);
        registerItem("grapefruit", grapefruit);
        registerItem("kumquat", kumquat);
        registerItem("peach", peach);
        registerItem("coconut", coconut);
        registerItem("nutmeg", nutmeg);
        registerItem("fig", fig);
        registerItem("nectarine", nectarine);
        registerItem("mango", mango);
        registerItem("dragonfruit", dragonFruit);
        registerItem("starfruit", starFruit);
        registerItem("avocado", avocado);
        registerItem("apricot", apricot);
        registerItem("pear", pear);
        registerItem("lime", lime);
        registerItem("date", date);
        registerItem("almond", almond);
        registerItem("cashew", cashew);
        registerItem("pecan", pecan);
        registerItem("walnut", walnut);

        registerItem("mustard", mustard);
        registerItem("vanilla", vanilla);
        registerItem("paprika", paprika);
        registerItem("chile_pepper", chilePepper);
        registerItem("salt", salt);
        registerItem("turmeric", turmeric);
        registerItem("ginger", ginger);
        registerItem("basil", basil);

        registerItem("artichoke_seed", artichokeSeed);
        registerItem("asparagus_seed", asparagusSeed);
        registerItem("bellpepper_seed", bellPepperSeed);
        registerItem("blackbean_seed", blackBeanSeed);
        registerItem("blackberry_seed", blackberrySeed);
        registerItem("blueberry_seed", blueberrySeed);
        registerItem("broccoli_seed", broccoliSeed);
        registerItem("cabbage_seed", cabbageSeed);
        registerItem("cantaloupe_seed", cantaloupeSeed);
        registerItem("cauliflower_seed", cauliflowerSeed);
        registerItem("celery_seed", celerySeed);
        registerItem("coffee_seed", coffeeSeed);
        registerItem("corn_seed", cornSeed);
        registerItem("cranberry_seed", cranberrySeed);
        registerItem("cucumber_seed", cucumberSeed);
        registerItem("currant_seed", currantSeed);
        registerItem("eggplant_seed", eggplantSeed);
        registerItem("elderberry_seed", elderberrySeed);
        registerItem("garlic_seed", garlicSeed);
        registerItem("grape_seed", grapeSeed);
        registerItem("greenbean_seed", greenBeanSeed);
        registerItem("greenonion_seed", greenOnionSeed);
        registerItem("honeydew_seed", honeydewSeed);
        registerItem("hops_seed", hopsSeed);
        registerItem("kale_seed", kaleSeed);
        registerItem("kiwi_seed", kiwiSeed);
        registerItem("leek_seed", leekSeed);
        registerItem("lettuce_seed", lettuceSeed);
        registerItem("olive_seed", oliveSeed);
        registerItem("onion_seed", onionSeed);
        registerItem("peanut_seed", peanutSeed);
        registerItem("pineapple_seed", pineappleSeed);
        registerItem("radish_seed", radishSeed);
        registerItem("raspberry_seed", raspberrySeed);
        registerItem("rhubarb_seed", rhubarbSeed);
        registerItem("rice_seed", riceSeed);
        registerItem("rutabaga_seed", rutabagaSeed);
        registerItem("saguaro_seed", saguaroSeed);
        registerItem("spinach_seed", spinachSeed);
        registerItem("squash_seed", squashSeed);
        registerItem("strawberry_seed", strawberrySeed);
        registerItem("sweetpotato_seed", sweetPotatoSeed);
        registerItem("tomatillo_seed", tomatilloSeed);
        registerItem("tomato_seed", tomatoSeed);
        registerItem("turnip_seed", turnipSeed);
        registerItem("yam_seed", yamSeed);
        registerItem("zucchini_seed", zucchiniSeed);
        registerItem("mustard_seed", mustardSeed);
        registerItem("chile_pepper_seed", pepperSeed);
        registerItem("turmeric_seed", turmericSeed);
        registerItem("ginger_seed", gingerSeed);
        registerItem("basil_seed", basilSeed);
        registerItem("oat_seed", oatSeed);
        registerItem("barley_seed", barleySeed);
        registerItem("soybean_seed", soybeanSeed);



        registerItem("almond_sapling", almondSapling);
        registerItem("cashew_sapling", cashewSapling);
        registerItem("pecan_sapling", pecanSapling);
        registerItem("walnut_sapling", walnutSapling);


        registerItem("olive_oil", oliveOil);
        registerItem("cheese", cheese);
        registerItem("flour", flour);
        registerItem("butter", butter);
        registerItem("noodle", noodle);
        registerItem("tofu", tofu);
        registerItem("molasses", molasses);
        registerItem("caramel", caramel);
        registerItem("chocolate", chocolate);
        registerItem("tortilla", tortilla);
        registerItem("soy_sauce", soySauce);
        registerItem("dough", dough);
        registerItem("ravioli", ravioli);
        registerItem("salsa", salsa);
        registerItem("artichoke_dip", artichokeDip);
        registerItem("pepperoni", pepperoni);

        registerItem("grape_juice", grapeJuice);
        registerItem("orange_juice", orangeJuice);
        registerItem("apple_juice", appleJuice);
        registerItem("cranberry_juice", cranberryJuice);
        registerItem("saguaro_juice", saguaroJuice);
        registerItem("tomato_juice", tomatoJuice);
        registerItem("melon_juice", melonJuice);
        registerItem("pineapple_juice", pineappleJuice);
        registerItem("coffee", coffee);
        registerItem("lemonade", lemonade);
        registerItem("limeade", limeade);
        registerItem("soy_milk", soyMilk);
        registerItem("strawberry_smoothie", strawberrySmoothie);
        registerItem("banana_smoothie", bananaSmoothie);
        registerItem("kale_smoothie", kaleSmoothie);
        registerItem("fruit_smoothie", fruitSmoothie);
        registerItem("chocolate_milkshake", chocolateMilkshake);
        registerItem("beer", beer);
        registerItem("wine", wine);
        registerItem("mead", mead);
        registerItem("rum", rum);
        registerItem("pumpkin_spice_latte", pumpkinSpiceLatte);

        registerItem("grape_jam", grapeJam);
        registerItem("strawberry_jam", strawberryJam);
        registerItem("peach_jam", peachJam);
        registerItem("apricot_jam", apricotJam);
        registerItem("blackberry_jam", blackberryJam);
        registerItem("blueberry_jam", blueberryJam);
        registerItem("cherry_jam", cherryJam);
        registerItem("elderberry_jam", elderberryJam);
        registerItem("raspberry_jam", raspberryJam);

        registerItem("beef_jerky", beefJerky); // todo no recipe
        registerItem("pork_jerky", porkJerky); // todo no recipe
        registerItem("kale_chips", kaleChips);
        registerItem("potato_chips", potatoChips);
        registerItem("steamed_rice", steamedRice);
        registerItem("french_fries", frenchFries);
        registerItem("sweet_potato_fries", sweetPotatoFries);
        registerItem("onion_rings", onionRings);
        registerItem("raisins", raisins);
        registerItem("doughnut", doughnut);
        registerItem("popcorn", popcorn);
        registerItem("baked_beans", bakedBeans);
        registerItem("toast", toast);
        registerItem("cucumber_salad", cucumberSalad);
        registerItem("caesar_salad", caesarSalad);
        registerItem("leafy_salad", leafySalad);
        registerItem("fruit_salad", fruitSalad);
        registerItem("veggie_salad", veggieSalad);
        registerItem("pork_and_beans", porkAndBeans);
        registerItem("oatmeal", oatmeal);
        registerItem("leek_soup", leekSoup);
        registerItem("yoghurt", yoghurt);
        registerItem("saucy_chips", saucyChips);
        /*registerItem("roasted_nuts", roastedNuts);
        registerItem("trail_mix", trailMix);
        registerItem("protein_bar", proteinBar);
        registerItem("nougat", nougat);*/

        registerItem("scrambled_eggs", scrambledEggs);
        registerItem("buttered_toast", butteredToast);
        registerItem("toast_with_jam", toastWithJam);

        registerItem("ham_sandwich", hamSandwich);
        registerItem("peanut_butter_and_jam", peanutButterAndJam);
        registerItem("blt", BLT);
        registerItem("grilled_cheese", grilledCheese);
        registerItem("tuna_sandwich", tunaSandwich); // todo no recipe
        registerItem("cheeseburger", cheeseburger);
        registerItem("hamburger", hamburger);
        registerItem("tofuburger", tofuBurger);
        registerItem("pizza", pizza);
        registerItem("supreme_pizza", supremePizza);
        registerItem("cheese_pizza", cheesePizza);
        registerItem("pineapple_pepperoni_pizza", pineapplePepperoniPizza);
        registerItem("lemon_chicken", lemonChicken);
        registerItem("fried_chicken", friedChicken);
        registerItem("chicken_and_noodles", chickenAndNoodles);
        registerItem("chicken_and_dumplings", chickenAndDumplings);
        registerItem("tofu_and_dumplings", tofuAndDumplings);
        registerItem("spaghetti_squash", spaghettiSquash);
        registerItem("chicken_and_rice", chickenAndRice);
        registerItem("taco", taco);
        registerItem("sushi", sushi);
        registerItem("egg_roll", eggRoll);
        //registerItem("cashew_chicken", cashewChicken);

        registerItem("apple_pie", applePie);
        registerItem("yam_jam", yamJam);
        registerItem("banana_cream_pie", bananaCreamPie);
        registerItem("candy_corn", candyCorn);
        registerItem("vanilla_ice_cream", vanillaIceCream);
        registerItem("strawberry_ice_cream", strawberryIceCream);
        registerItem("mango_ice_cream", mangoIceCream);
        registerItem("rum_raisin_ice_cream", rumRaisinIceCream);
        //registerItem("pecan_ice_cream", pecanIceCream);
        registerItem("cherry_pie", cherryPie);
        registerItem("cheese_cake", cheeseCake);
        registerItem("brownies", brownies);
        registerItem("snicker_doodle", snickerDoodle); // todo no recipe
        registerItem("banana_nut_bread", bananaNutBread); // todo no recipe no nuts
        /*registerItem("pecan_pie", pecanPie);
        registerItem("candied_nuts", candiedNuts);
        registerItem("almond_brittle", almondBrittle);
        registerItem("oatmeal_cookie", oatmealCookie);
        registerItem("nutty_cookie", nuttyCookie);*/

        registerItem("food_press", foodPress);
        registerItem("frying_pan", fryingPan);
        registerItem("cooking_pot", cookingPot);
        registerItem("mortar_and_pestle", mortarAndPestle);

        registerItem("salt_ore", saltOre);
    }
}
