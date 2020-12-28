package me.thonk.croptopia.registry;

import me.thonk.common.ItemNames;
import me.thonk.croptopia.items.Drink;
import me.thonk.croptopia.items.SeedItem;
import net.minecraft.item.*;

import static me.thonk.common.ItemNames.*;
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
    public static Item pepperSeed = new SeedItem(BlockRegistry.chilePepperCropBlock, createGroup(), PLAINS);
    public static Item turmericSeed = new SeedItem(BlockRegistry.turmericCropBlock, createGroup(), SAVANNA);
    public static Item gingerSeed = new SeedItem(BlockRegistry.gingerCropBlock, createGroup(), SAVANNA);
    public static Item basilSeed = new SeedItem(BlockRegistry.basilCropBlock, createGroup(), JUNGLE);
    public static Item oatSeed = new SeedItem(BlockRegistry.oatCropBlock, createGroup(), PLAINS);
    public static Item barleySeed = new SeedItem(BlockRegistry.barleyCropBlock, createGroup(), PLAINS);
    public static Item soybeanSeed = new SeedItem(BlockRegistry.soybeanCropBlock, createGroup(), PLAINS);
    public static Item vanillaSeed;



    public static Item appleSapling = registerItem(APPLE_SAPLING, new AliasedBlockItem(BlockRegistry.appleSaplingBlock, createGroup()));
    public static Item bananaSapling = registerItem(BANANA_SAPLING, new AliasedBlockItem(BlockRegistry.bananaSaplingBlock, createGroup()));
    public static Item orangeSapling = registerItem(ORANGE_SAPLING, new AliasedBlockItem(BlockRegistry.orangeSaplingBlock, createGroup()));
    public static Item persimmonSapling = registerItem(PERSIMMON_SAPLING, new AliasedBlockItem(BlockRegistry.persimmonSaplingBlock, createGroup()));
    public static Item plumSapling = registerItem(PLUM_SAPLING, new AliasedBlockItem(BlockRegistry.plumSaplingBlock, createGroup()));
    public static Item cherrySapling = registerItem(CHERRY_SAPLING, new AliasedBlockItem(BlockRegistry.cherrySaplingBlock, createGroup()));
    public static Item lemonSapling = registerItem(LEMON_SAPLING, new AliasedBlockItem(BlockRegistry.lemonSaplingBlock, createGroup()));
    public static Item grapefruitSapling = registerItem(GRAPEFRUIT_SAPLING, new AliasedBlockItem(BlockRegistry.grapefruitSaplingBlock, createGroup()));
    public static Item kumquatSapling = registerItem(KUMQUAT_SAPLING, new AliasedBlockItem(BlockRegistry.kumquatSaplingBlock, createGroup()));
    public static Item peachSapling = registerItem(PEACH_SAPLING, new AliasedBlockItem(BlockRegistry.peachSaplingBlock, createGroup()));
    public static Item coconutSapling = registerItem(COCONUT_SAPLING, new AliasedBlockItem(BlockRegistry.coconutSaplingBlock, createGroup()));
    public static Item nutmegSapling = registerItem(NUTMEG_SAPLING, new AliasedBlockItem(BlockRegistry.nutmegSaplingBlock, createGroup()));
    public static Item figSapling = registerItem(FIG_SAPLING, new AliasedBlockItem(BlockRegistry.figSaplingBlock, createGroup()));
    public static Item nectarineSapling = registerItem(NECTARINE_SAPLING, new AliasedBlockItem(BlockRegistry.nectarineSaplingBlock, createGroup()));
    public static Item mangoSapling = registerItem(MANGO_SAPLING, new AliasedBlockItem(BlockRegistry.mangoSaplingBlock, createGroup()));
    public static Item dragonFruitSapling = registerItem(DRAGONFRUIT_SAPLING, new AliasedBlockItem(BlockRegistry.dragonFruitSaplingBlock, createGroup()));
    public static Item starFruitSapling = registerItem(STARFRUIT_SAPLING, new AliasedBlockItem(BlockRegistry.starFruitSaplingBlock, createGroup()));
    public static Item avocadoSapling = registerItem(AVOCADO_SAPLING, new AliasedBlockItem(BlockRegistry.avocadoSaplingBlock, createGroup()));
    public static Item apricotSapling = registerItem(APRICOT_SAPLING, new AliasedBlockItem(BlockRegistry.apricotSaplingBlock, createGroup()));
    public static Item pearSapling = registerItem(PEAR_SAPLING, new AliasedBlockItem(BlockRegistry.pearSaplingBlock, createGroup()));
    public static Item limeSapling = registerItem(LIME_SAPLING, new AliasedBlockItem(BlockRegistry.limeSaplingBlock, createGroup()));
    public static Item dateSapling = registerItem(DATE_SAPLING, new AliasedBlockItem(BlockRegistry.dateSaplingBlock, createGroup()));
    public static Item almondSapling = new AliasedBlockItem(BlockRegistry.almondSaplingBlock, createGroup());
    public static Item cashewSapling = new AliasedBlockItem(BlockRegistry.cashewSaplingBlock, createGroup());
    public static Item pecanSapling = new AliasedBlockItem(BlockRegistry.pecanSaplingBlock, createGroup());
    public static Item walnutSapling = new AliasedBlockItem(BlockRegistry.walnutSaplingBlock, createGroup());

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
    public static Item rum = new Drink(createGroup().food(EDIBLE_7_BUILDER.alwaysEdible().build()));
    public static Item pumpkinSpiceLatte = new Drink(createGroup().food(EDIBLE_14_BUILDER.alwaysEdible().build()));

    // jams
    public static Item grapeJam = new Drink(createGroup().food(EDIBLE_3_BUILDER.alwaysEdible().build()).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item strawberryJam = new Drink(createGroup().food(EDIBLE_3_BUILDER.alwaysEdible().build()).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item peachJam = new Drink(createGroup().food(EDIBLE_3_BUILDER.alwaysEdible().build()).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item apricotJam = new Drink(createGroup().food(EDIBLE_3_BUILDER.alwaysEdible().build()).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item blackberryJam = new Drink(createGroup().food(EDIBLE_3_BUILDER.alwaysEdible().build()).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item blueberryJam = new Drink(createGroup().food(EDIBLE_3_BUILDER.alwaysEdible().build()).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item cherryJam = new Drink(createGroup().food(EDIBLE_3_BUILDER.alwaysEdible().build()).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item elderberryJam = new Drink(createGroup().food(EDIBLE_3_BUILDER.alwaysEdible().build()).recipeRemainder(Items.GLASS_BOTTLE));
    public static Item raspberryJam = new Drink(createGroup().food(EDIBLE_3_BUILDER.alwaysEdible().build()).recipeRemainder(Items.GLASS_BOTTLE));

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
    public static Item roastedNuts = new Item(createGroup().food(EDIBLE_5));
    public static Item trailMix = new Item(createGroup().food(EDIBLE_10));
    public static Item proteinBar = new Item(createGroup().food(EDIBLE_10));
    public static Item nougat = new Item(createGroup().food(EDIBLE_7));

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
    public static Item cashewChicken = new Item(createGroup().food(EDIBLE_14));

    // desert block?
    public static Item coffeeCake;
    public static Item chocolateCake;
    public static Item fruitCake;
    public static Item strawberryShortCake;
    public static Item carrotCake;
    public static Item turtleCake;

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
    public static Item praline = new Item(createGroup().food(EDIBLE_5));

    // cooking utensils?
    public static Item foodPress = new Item(createGroup().maxCount(1).maxDamageIfAbsent(500));
    public static Item fryingPan = new SwordItem(ToolMaterials.IRON, 5, -1.0F, createGroup().maxCount(1).maxDamageIfAbsent(500));
    public static Item cookingPot = new Item(createGroup().maxCount(1).maxDamageIfAbsent(500));
    public static Item mortarAndPestle = new Item(createGroup().maxCount(1).maxDamageIfAbsent(500));

    public static Item saltOre = new AliasedBlockItem(BlockRegistry.salt, createGroup());


    public static void init() {
        registerItem(ARTICHOKE, artichoke);
        registerItem(ASPARAGUS, asparagus);
        registerItem(BARLEY, barley);
        registerItem(BELLPEPPER, bellPepper);
        registerItem(BLACKBEAN, blackBean);
        registerItem(BLACKBERRY, blackberry);
        registerItem(BLUEBERRY, blueberry);
        registerItem(BROCCOLI, broccoli);
        registerItem(CABBAGE, cabbage);
        registerItem(CANTALOUPE, cantaloupe);
        registerItem(CAULIFLOWER, cauliflower);
        registerItem(CELERY, celery);
        registerItem(COFFEE_BEANS, coffeeBeans);
        registerItem(CORN, corn);
        registerItem(CRANBERRY, cranberry);
        registerItem(CUCUMBER, cucumber);
        registerItem(CURRANT, currant);
        registerItem(EGGPLANT, eggplant);
        registerItem(ELDERBERRY, elderberry);
        registerItem(GARLIC, garlic);
        registerItem(GRAPE, grape);
        registerItem(GREENBEAN, greenBean);
        registerItem(GREENONION, greenOnion);
        registerItem(HONEYDEW, honeydew);
        registerItem(HOPS, hops);
        registerItem(KALE, kale);
        registerItem(KIWI, kiwi);
        registerItem(LEEK, leek);
        registerItem(LETTUCE, lettuce);
        registerItem(OAT, oat);
        registerItem(OLIVE, olive);
        registerItem(ONION, onion);
        registerItem(PEANUT, peanut);
        registerItem(PINEAPPLE, pineapple);
        registerItem(RADISH, radish);
        registerItem(RASPBERRY, raspberry);
        registerItem(RHUBARB, rhubarb);
        registerItem(RICE, rice);
        registerItem(RUTABAGA, rutabaga);
        registerItem(SAGUARO, saguaro);
        registerItem(SOYBEAN, soybean);
        registerItem(SPINACH, spinach);
        registerItem(SQUASH, squash);
        registerItem(STRAWBERRY, strawberry);
        registerItem(SWEETPOTATO, sweetPotato);
        registerItem(TOMATILLO, tomatillo);
        registerItem(TOMATO, tomato);
        registerItem(TURNIP, turnip);
        registerItem(YAM, yam);
        registerItem(ZUCCHINI, zucchini);

        registerItem(ORANGE, orange);
        registerItem(BANANA, banana);
        registerItem(PERSIMMON, persimmon);
        registerItem(PLUM, plum);
        registerItem(CHERRY, cherry);
        registerItem(LEMON, lemon);
        registerItem(GRAPEFRUIT, grapefruit);
        registerItem(KUMQUAT, kumquat);
        registerItem(PEACH, peach);
        registerItem(COCONUT, coconut);
        registerItem(NUTMEG, nutmeg);
        registerItem(FIG, fig);
        registerItem(NECTARINE, nectarine);
        registerItem(MANGO, mango);
        registerItem(DRAGONFRUIT, dragonFruit);
        registerItem(STARFRUIT, starFruit);
        registerItem(AVOCADO, avocado);
        registerItem(APRICOT, apricot);
        registerItem(PEAR, pear);
        registerItem(LIME, lime);
        registerItem(DATE, date);
        registerItem(ALMOND, almond);
        registerItem(CASHEW, cashew);
        registerItem(PECAN, pecan);
        registerItem(WALNUT, walnut);

        registerItem(MUSTARD, mustard);
        registerItem(VANILLA, vanilla);
        registerItem(PAPRIKA, paprika);
        registerItem(CHILE_PEPPER, chilePepper);
        registerItem(SALT, salt);
        registerItem(TURMERIC, turmeric);
        registerItem(GINGER, ginger);
        registerItem(BASIL, basil);

        registerItem(ARTICHOKE_SEED, artichokeSeed);
        registerItem(ASPARAGUS_SEED, asparagusSeed);
        registerItem(BELLPEPPER_SEED, bellPepperSeed);
        registerItem(BLACKBEAN_SEED, blackBeanSeed);
        registerItem(BLACKBERRY_SEED, blackberrySeed);
        registerItem(BLUEBERRY_SEED, blueberrySeed);
        registerItem(BROCCOLI_SEED, broccoliSeed);
        registerItem(CABBAGE_SEED, cabbageSeed);
        registerItem(CANTALOUPE_SEED, cantaloupeSeed);
        registerItem(CAULIFLOWER_SEED, cauliflowerSeed);
        registerItem(CELERY_SEED, celerySeed);
        registerItem(COFFEE_SEED, coffeeSeed);
        registerItem(CORN_SEED, cornSeed);
        registerItem(CRANBERRY_SEED, cranberrySeed);
        registerItem(CUCUMBER_SEED, cucumberSeed);
        registerItem(CURRANT_SEED, currantSeed);
        registerItem(EGGPLANT_SEED, eggplantSeed);
        registerItem(ELDERBERRY_SEED, elderberrySeed);
        registerItem(GARLIC_SEED, garlicSeed);
        registerItem(GRAPE_SEED, grapeSeed);
        registerItem(GREENBEAN_SEED, greenBeanSeed);
        registerItem(GREENONION_SEED, greenOnionSeed);
        registerItem(HONEYDEW_SEED, honeydewSeed);
        registerItem(HOPS_SEED, hopsSeed);
        registerItem(KALE_SEED, kaleSeed);
        registerItem(KIWI_SEED, kiwiSeed);
        registerItem(LEEK_SEED, leekSeed);
        registerItem(LETTUCE_SEED, lettuceSeed);
        registerItem(OLIVE_SEED, oliveSeed);
        registerItem(ONION_SEED, onionSeed);
        registerItem(PEANUT_SEED, peanutSeed);
        registerItem(PINEAPPLE_SEED, pineappleSeed);
        registerItem(RADISH_SEED, radishSeed);
        registerItem(RASPBERRY_SEED, raspberrySeed);
        registerItem(RHUBARB_SEED, rhubarbSeed);
        registerItem(RICE_SEED, riceSeed);
        registerItem(RUTABAGA_SEED, rutabagaSeed);
        registerItem(SAGUARO_SEED, saguaroSeed);
        registerItem(SPINACH_SEED, spinachSeed);
        registerItem(SQUASH_SEED, squashSeed);
        registerItem(STRAWBERRY_SEED, strawberrySeed);
        registerItem(SWEETPOTATO_SEED, sweetPotatoSeed);
        registerItem(TOMATILLO_SEED, tomatilloSeed);
        registerItem(TOMATO_SEED, tomatoSeed);
        registerItem(TURNIP_SEED, turnipSeed);
        registerItem(YAM_SEED, yamSeed);
        registerItem(ZUCCHINI_SEED, zucchiniSeed);
        registerItem(MUSTARD_SEED, mustardSeed);
        registerItem(CHILE_PEPPER_SEED, pepperSeed);
        registerItem(TURMERIC_SEED, turmericSeed);
        registerItem(GINGER_SEED, gingerSeed);
        registerItem(BASIL_SEED, basilSeed);
        registerItem(OAT_SEED, oatSeed);
        registerItem(BARLEY_SEED, barleySeed);
        registerItem(SOYBEAN_SEED, soybeanSeed);



        registerItem(ALMOND_SAPLING, almondSapling);
        registerItem(CASHEW_SAPLING, cashewSapling);
        registerItem(PECAN_SAPLING, pecanSapling);
        registerItem(WALNUT_SAPLING, walnutSapling);


        registerItem(OLIVE_OIL, oliveOil);
        registerItem(CHEESE, cheese);
        registerItem(FLOUR, flour);
        registerItem(BUTTER, butter);
        registerItem(NOODLE, noodle);
        registerItem(TOFU, tofu);
        registerItem(MOLASSES, molasses);
        registerItem(CARAMEL, caramel);
        registerItem(CHOCOLATE, chocolate);
        registerItem(TORTILLA, tortilla);
        registerItem(SOY_SAUCE, soySauce);
        registerItem(DOUGH, dough);
        registerItem(RAVIOLI, ravioli);
        registerItem(SALSA, salsa);
        registerItem(ARTICHOKE_DIP, artichokeDip);
        registerItem(PEPPERONI, pepperoni);

        registerItem(GRAPE_JUICE, grapeJuice);
        registerItem(ORANGE_JUICE, orangeJuice);
        registerItem(APPLE_JUICE, appleJuice);
        registerItem(CRANBERRY_JUICE, cranberryJuice);
        registerItem(SAGUARO_JUICE, saguaroJuice);
        registerItem(TOMATO_JUICE, tomatoJuice);
        registerItem(MELON_JUICE, melonJuice);
        registerItem(PINEAPPLE_JUICE, pineappleJuice);
        registerItem(COFFEE, coffee);
        registerItem(LEMONADE, lemonade);
        registerItem(LIMEADE, limeade);
        registerItem(SOY_MILK, soyMilk);
        registerItem(STRAWBERRY_SMOOTHIE, strawberrySmoothie);
        registerItem(BANANA_SMOOTHIE, bananaSmoothie);
        registerItem(KALE_SMOOTHIE, kaleSmoothie);
        registerItem(FRUIT_SMOOTHIE, fruitSmoothie);
        registerItem(CHOCOLATE_MILKSHAKE, chocolateMilkshake);
        registerItem(BEER, beer);
        registerItem(WINE, wine);
        registerItem(MEAD, mead);
        registerItem(RUM, rum);
        registerItem(PUMPKIN_SPICE_LATTE, pumpkinSpiceLatte);

        registerItem(GRAPE_JAM, grapeJam);
        registerItem(STRAWBERRY_JAM, strawberryJam);
        registerItem(PEACH_JAM, peachJam);
        registerItem(APRICOT_JAM, apricotJam);
        registerItem(BLACKBERRY_JAM, blackberryJam);
        registerItem(BLUEBERRY_JAM, blueberryJam);
        registerItem(CHERRY_JAM, cherryJam);
        registerItem(ELDERBERRY_JAM, elderberryJam);
        registerItem(RASPBERRY_JAM, raspberryJam);

        registerItem(BEEF_JERKY, beefJerky); // todo no recipe
        registerItem(PORK_JERKY, porkJerky); // todo no recipe
        registerItem(KALE_CHIPS, kaleChips);
        registerItem(POTATO_CHIPS, potatoChips);
        registerItem(STEAMED_RICE, steamedRice);
        registerItem(FRENCH_FRIES, frenchFries);
        registerItem(SWEET_POTATO_FRIES, sweetPotatoFries);
        registerItem(ONION_RINGS, onionRings);
        registerItem(RAISINS, raisins);
        registerItem(DOUGHNUT, doughnut);
        registerItem(POPCORN, popcorn);
        registerItem(BAKED_BEANS, bakedBeans);
        registerItem(TOAST, toast);
        registerItem(CUCUMBER_SALAD, cucumberSalad);
        registerItem(CAESAR_SALAD, caesarSalad);
        registerItem(LEAFY_SALAD, leafySalad);
        registerItem(FRUIT_SALAD, fruitSalad);
        registerItem(VEGGIE_SALAD, veggieSalad);
        registerItem(PORK_AND_BEANS, porkAndBeans);
        registerItem(OATMEAL, oatmeal);
        registerItem(LEEK_SOUP, leekSoup);
        registerItem(YOGHURT, yoghurt);
        registerItem(SAUCY_CHIPS, saucyChips);
        registerItem(ROASTED_NUTS, roastedNuts);
        registerItem(TRAIL_MIX, trailMix);
        registerItem(PROTEIN_BAR, proteinBar);
        registerItem(NOUGAT, nougat);

        registerItem(SCRAMBLED_EGGS, scrambledEggs);
        registerItem(BUTTERED_TOAST, butteredToast);
        registerItem(TOAST_WITH_JAM, toastWithJam);

        registerItem(HAM_SANDWICH, hamSandwich);
        registerItem(PEANUT_BUTTER_AND_JAM, peanutButterAndJam);
        registerItem(ItemNames.BLT, BLT);
        registerItem(GRILLED_CHEESE, grilledCheese);
        registerItem(TUNA_SANDWICH, tunaSandwich); // todo no recipe
        registerItem(CHEESEBURGER, cheeseburger);
        registerItem(HAMBURGER, hamburger);
        registerItem(TOFUBURGER, tofuBurger);
        registerItem(PIZZA, pizza);
        registerItem(SUPREME_PIZZA, supremePizza);
        registerItem(CHEESE_PIZZA, cheesePizza);
        registerItem(PINEAPPLE_PEPPERONI_PIZZA, pineapplePepperoniPizza);
        registerItem(LEMON_CHICKEN, lemonChicken);
        registerItem(FRIED_CHICKEN, friedChicken);
        registerItem(CHICKEN_AND_NOODLES, chickenAndNoodles);
        registerItem(CHICKEN_AND_DUMPLINGS, chickenAndDumplings);
        registerItem(TOFU_AND_DUMPLINGS, tofuAndDumplings);
        registerItem(SPAGHETTI_SQUASH, spaghettiSquash);
        registerItem(CHICKEN_AND_RICE, chickenAndRice);
        registerItem(TACO, taco);
        registerItem(SUSHI, sushi);
        registerItem(EGG_ROLL, eggRoll);
        registerItem(CASHEW_CHICKEN, cashewChicken);

        registerItem(APPLE_PIE, applePie);
        registerItem(YAM_JAM, yamJam);
        registerItem(BANANA_CREAM_PIE, bananaCreamPie);
        registerItem(CANDY_CORN, candyCorn);
        registerItem(VANILLA_ICE_CREAM, vanillaIceCream);
        registerItem(STRAWBERRY_ICE_CREAM, strawberryIceCream);
        registerItem(MANGO_ICE_CREAM, mangoIceCream);
        registerItem(RUM_RAISIN_ICE_CREAM, rumRaisinIceCream);
        registerItem(PECAN_ICE_CREAM, pecanIceCream);
        registerItem(CHERRY_PIE, cherryPie);
        registerItem(CHEESE_CAKE, cheeseCake);
        registerItem(BROWNIES, brownies);
        registerItem(SNICKER_DOODLE, snickerDoodle); // todo no recipe
        registerItem(BANANA_NUT_BREAD, bananaNutBread); // todo no recipe no nuts
        registerItem(PECAN_PIE, pecanPie);
        registerItem(CANDIED_NUTS, candiedNuts);
        registerItem(ALMOND_BRITTLE, almondBrittle);
        registerItem(RAISIN_OATMEAL_COOKIE, oatmealCookie);
        registerItem(NUTTY_COOKIE, nuttyCookie);

        registerItem(FOOD_PRESS, foodPress);
        registerItem(FRYING_PAN, fryingPan);
        registerItem(COOKING_POT, cookingPot);
        registerItem(MORTAR_AND_PESTLE, mortarAndPestle);

        registerItem(SALT_ORE, saltOre);
    }
}
