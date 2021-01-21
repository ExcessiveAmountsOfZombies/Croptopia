package me.thonk.croptopia.registry;

import me.thonk.common.ItemNames;
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
    public static Item pepperSeed = new SeedItem(BlockRegistry.chilePepperCropBlock, createGroup(), PLAINS);
    public static Item turmericSeed = new SeedItem(BlockRegistry.turmericCropBlock, createGroup(), SAVANNA);
    public static Item gingerSeed = new SeedItem(BlockRegistry.gingerCropBlock, createGroup(), SAVANNA);
    public static Item basilSeed = new SeedItem(BlockRegistry.basilCropBlock, createGroup(), JUNGLE);
    public static Item oatSeed = new SeedItem(BlockRegistry.oatCropBlock, createGroup(), PLAINS);
    public static Item barleySeed = new SeedItem(BlockRegistry.barleyCropBlock, createGroup(), PLAINS);
    public static Item soybeanSeed = new SeedItem(BlockRegistry.soybeanCropBlock, createGroup(), PLAINS);
    public static Item vanillaSeed;



    public static Item appleSapling = registerItem(ItemNames.APPLE_SAPLING, new AliasedBlockItem(BlockRegistry.appleSaplingBlock, createGroup()));
    public static Item bananaSapling = registerItem(ItemNames.BANANA_SAPLING, new AliasedBlockItem(BlockRegistry.bananaSaplingBlock, createGroup()));
    public static Item orangeSapling = registerItem(ItemNames.ORANGE_SAPLING, new AliasedBlockItem(BlockRegistry.orangeSaplingBlock, createGroup()));
    public static Item persimmonSapling = registerItem(ItemNames.PERSIMMON_SAPLING, new AliasedBlockItem(BlockRegistry.persimmonSaplingBlock, createGroup()));
    public static Item plumSapling = registerItem(ItemNames.PLUM_SAPLING, new AliasedBlockItem(BlockRegistry.plumSaplingBlock, createGroup()));
    public static Item cherrySapling = registerItem(ItemNames.CHERRY_SAPLING, new AliasedBlockItem(BlockRegistry.cherrySaplingBlock, createGroup()));
    public static Item lemonSapling = registerItem(ItemNames.LEMON_SAPLING, new AliasedBlockItem(BlockRegistry.lemonSaplingBlock, createGroup()));
    public static Item grapefruitSapling = registerItem(ItemNames.GRAPEFRUIT_SAPLING, new AliasedBlockItem(BlockRegistry.grapefruitSaplingBlock, createGroup()));
    public static Item kumquatSapling = registerItem(ItemNames.KUMQUAT_SAPLING, new AliasedBlockItem(BlockRegistry.kumquatSaplingBlock, createGroup()));
    public static Item peachSapling = registerItem(ItemNames.PEACH_SAPLING, new AliasedBlockItem(BlockRegistry.peachSaplingBlock, createGroup()));
    public static Item coconutSapling = registerItem(ItemNames.COCONUT_SAPLING, new AliasedBlockItem(BlockRegistry.coconutSaplingBlock, createGroup()));
    public static Item nutmegSapling = registerItem(ItemNames.NUTMEG_SAPLING, new AliasedBlockItem(BlockRegistry.nutmegSaplingBlock, createGroup()));
    public static Item figSapling = registerItem(ItemNames.FIG_SAPLING, new AliasedBlockItem(BlockRegistry.figSaplingBlock, createGroup()));
    public static Item nectarineSapling = registerItem(ItemNames.NECTARINE_SAPLING, new AliasedBlockItem(BlockRegistry.nectarineSaplingBlock, createGroup()));
    public static Item mangoSapling = registerItem(ItemNames.MANGO_SAPLING, new AliasedBlockItem(BlockRegistry.mangoSaplingBlock, createGroup()));
    public static Item dragonFruitSapling = registerItem(ItemNames.DRAGONFRUIT_SAPLING, new AliasedBlockItem(BlockRegistry.dragonFruitSaplingBlock, createGroup()));
    public static Item starFruitSapling = registerItem(ItemNames.STARFRUIT_SAPLING, new AliasedBlockItem(BlockRegistry.starFruitSaplingBlock, createGroup()));
    public static Item avocadoSapling = registerItem(ItemNames.AVOCADO_SAPLING, new AliasedBlockItem(BlockRegistry.avocadoSaplingBlock, createGroup()));
    public static Item apricotSapling = registerItem(ItemNames.APRICOT_SAPLING, new AliasedBlockItem(BlockRegistry.apricotSaplingBlock, createGroup()));
    public static Item pearSapling = registerItem(ItemNames.PEAR_SAPLING, new AliasedBlockItem(BlockRegistry.pearSaplingBlock, createGroup()));
    public static Item limeSapling = registerItem(ItemNames.LIME_SAPLING, new AliasedBlockItem(BlockRegistry.limeSaplingBlock, createGroup()));
    public static Item dateSapling = registerItem(ItemNames.DATE_SAPLING, new AliasedBlockItem(BlockRegistry.dateSaplingBlock, createGroup()));
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
    public static Item fryingPan = new SwordItem(ToolMaterials.IRON, 5, -2.0F, createGroup().maxCount(1).maxDamageIfAbsent(500));
    public static Item cookingPot = new Item(createGroup().maxCount(1).maxDamageIfAbsent(500));
    public static Item mortarAndPestle = new Item(createGroup().maxCount(1).maxDamageIfAbsent(500));

    public static Item saltOre = new AliasedBlockItem(BlockRegistry.salt, createGroup());


    public static void init() {
        registerItem(ItemNames.ARTICHOKE, artichoke);
        registerItem(ItemNames.ASPARAGUS, asparagus);
        registerItem(ItemNames.BARLEY, barley);
        registerItem(ItemNames.BELLPEPPER, bellPepper);
        registerItem(ItemNames.BLACKBEAN, blackBean);
        registerItem(ItemNames.BLACKBERRY, blackberry);
        registerItem(ItemNames.BLUEBERRY, blueberry);
        registerItem(ItemNames.BROCCOLI, broccoli);
        registerItem(ItemNames.CABBAGE, cabbage);
        registerItem(ItemNames.CANTALOUPE, cantaloupe);
        registerItem(ItemNames.CAULIFLOWER, cauliflower);
        registerItem(ItemNames.CELERY, celery);
        registerItem(ItemNames.COFFEE_BEANS, coffeeBeans);
        registerItem(ItemNames.CORN, corn);
        registerItem(ItemNames.CRANBERRY, cranberry);
        registerItem(ItemNames.CUCUMBER, cucumber);
        registerItem(ItemNames.CURRANT, currant);
        registerItem(ItemNames.EGGPLANT, eggplant);
        registerItem(ItemNames.ELDERBERRY, elderberry);
        registerItem(ItemNames.GARLIC, garlic);
        registerItem(ItemNames.GRAPE, grape);
        registerItem(ItemNames.GREENBEAN, greenBean);
        registerItem(ItemNames.GREENONION, greenOnion);
        registerItem(ItemNames.HONEYDEW, honeydew);
        registerItem(ItemNames.HOPS, hops);
        registerItem(ItemNames.KALE, kale);
        registerItem(ItemNames.KIWI, kiwi);
        registerItem(ItemNames.LEEK, leek);
        registerItem(ItemNames.LETTUCE, lettuce);
        registerItem(ItemNames.OAT, oat);
        registerItem(ItemNames.OLIVE, olive);
        registerItem(ItemNames.ONION, onion);
        registerItem(ItemNames.PEANUT, peanut);
        registerItem(ItemNames.PINEAPPLE, pineapple);
        registerItem(ItemNames.RADISH, radish);
        registerItem(ItemNames.RASPBERRY, raspberry);
        registerItem(ItemNames.RHUBARB, rhubarb);
        registerItem(ItemNames.RICE, rice);
        registerItem(ItemNames.RUTABAGA, rutabaga);
        registerItem(ItemNames.SAGUARO, saguaro);
        registerItem(ItemNames.SOYBEAN, soybean);
        registerItem(ItemNames.SPINACH, spinach);
        registerItem(ItemNames.SQUASH, squash);
        registerItem(ItemNames.STRAWBERRY, strawberry);
        registerItem(ItemNames.SWEETPOTATO, sweetPotato);
        registerItem(ItemNames.TOMATILLO, tomatillo);
        registerItem(ItemNames.TOMATO, tomato);
        registerItem(ItemNames.TURNIP, turnip);
        registerItem(ItemNames.YAM, yam);
        registerItem(ItemNames.ZUCCHINI, zucchini);

        registerItem(ItemNames.ORANGE, orange);
        registerItem(ItemNames.BANANA, banana);
        registerItem(ItemNames.PERSIMMON, persimmon);
        registerItem(ItemNames.PLUM, plum);
        registerItem(ItemNames.CHERRY, cherry);
        registerItem(ItemNames.LEMON, lemon);
        registerItem(ItemNames.GRAPEFRUIT, grapefruit);
        registerItem(ItemNames.KUMQUAT, kumquat);
        registerItem(ItemNames.PEACH, peach);
        registerItem(ItemNames.COCONUT, coconut);
        registerItem(ItemNames.NUTMEG, nutmeg);
        registerItem(ItemNames.FIG, fig);
        registerItem(ItemNames.NECTARINE, nectarine);
        registerItem(ItemNames.MANGO, mango);
        registerItem(ItemNames.DRAGONFRUIT, dragonFruit);
        registerItem(ItemNames.STARFRUIT, starFruit);
        registerItem(ItemNames.AVOCADO, avocado);
        registerItem(ItemNames.APRICOT, apricot);
        registerItem(ItemNames.PEAR, pear);
        registerItem(ItemNames.LIME, lime);
        registerItem(ItemNames.DATE, date);
        registerItem(ItemNames.ALMOND, almond);
        registerItem(ItemNames.CASHEW, cashew);
        registerItem(ItemNames.PECAN, pecan);
        registerItem(ItemNames.WALNUT, walnut);

        registerItem(ItemNames.MUSTARD, mustard);
        registerItem(ItemNames.VANILLA, vanilla);
        registerItem(ItemNames.PAPRIKA, paprika);
        registerItem(ItemNames.CHILE_PEPPER, chilePepper);
        registerItem(ItemNames.SALT, salt);
        registerItem(ItemNames.TURMERIC, turmeric);
        registerItem(ItemNames.GINGER, ginger);
        registerItem(ItemNames.BASIL, basil);

        registerItem(ItemNames.ARTICHOKE_SEED, artichokeSeed);
        registerItem(ItemNames.ASPARAGUS_SEED, asparagusSeed);
        registerItem(ItemNames.BELLPEPPER_SEED, bellPepperSeed);
        registerItem(ItemNames.BLACKBEAN_SEED, blackBeanSeed);
        registerItem(ItemNames.BLACKBERRY_SEED, blackberrySeed);
        registerItem(ItemNames.BLUEBERRY_SEED, blueberrySeed);
        registerItem(ItemNames.BROCCOLI_SEED, broccoliSeed);
        registerItem(ItemNames.CABBAGE_SEED, cabbageSeed);
        registerItem(ItemNames.CANTALOUPE_SEED, cantaloupeSeed);
        registerItem(ItemNames.CAULIFLOWER_SEED, cauliflowerSeed);
        registerItem(ItemNames.CELERY_SEED, celerySeed);
        registerItem(ItemNames.COFFEE_SEED, coffeeSeed);
        registerItem(ItemNames.CORN_SEED, cornSeed);
        registerItem(ItemNames.CRANBERRY_SEED, cranberrySeed);
        registerItem(ItemNames.CUCUMBER_SEED, cucumberSeed);
        registerItem(ItemNames.CURRANT_SEED, currantSeed);
        registerItem(ItemNames.EGGPLANT_SEED, eggplantSeed);
        registerItem(ItemNames.ELDERBERRY_SEED, elderberrySeed);
        registerItem(ItemNames.GARLIC_SEED, garlicSeed);
        registerItem(ItemNames.GRAPE_SEED, grapeSeed);
        registerItem(ItemNames.GREENBEAN_SEED, greenBeanSeed);
        registerItem(ItemNames.GREENONION_SEED, greenOnionSeed);
        registerItem(ItemNames.HONEYDEW_SEED, honeydewSeed);
        registerItem(ItemNames.HOPS_SEED, hopsSeed);
        registerItem(ItemNames.KALE_SEED, kaleSeed);
        registerItem(ItemNames.KIWI_SEED, kiwiSeed);
        registerItem(ItemNames.LEEK_SEED, leekSeed);
        registerItem(ItemNames.LETTUCE_SEED, lettuceSeed);
        registerItem(ItemNames.OLIVE_SEED, oliveSeed);
        registerItem(ItemNames.ONION_SEED, onionSeed);
        registerItem(ItemNames.PEANUT_SEED, peanutSeed);
        registerItem(ItemNames.PINEAPPLE_SEED, pineappleSeed);
        registerItem(ItemNames.RADISH_SEED, radishSeed);
        registerItem(ItemNames.RASPBERRY_SEED, raspberrySeed);
        registerItem(ItemNames.RHUBARB_SEED, rhubarbSeed);
        registerItem(ItemNames.RICE_SEED, riceSeed);
        registerItem(ItemNames.RUTABAGA_SEED, rutabagaSeed);
        registerItem(ItemNames.SAGUARO_SEED, saguaroSeed);
        registerItem(ItemNames.SPINACH_SEED, spinachSeed);
        registerItem(ItemNames.SQUASH_SEED, squashSeed);
        registerItem(ItemNames.STRAWBERRY_SEED, strawberrySeed);
        registerItem(ItemNames.SWEETPOTATO_SEED, sweetPotatoSeed);
        registerItem(ItemNames.TOMATILLO_SEED, tomatilloSeed);
        registerItem(ItemNames.TOMATO_SEED, tomatoSeed);
        registerItem(ItemNames.TURNIP_SEED, turnipSeed);
        registerItem(ItemNames.YAM_SEED, yamSeed);
        registerItem(ItemNames.ZUCCHINI_SEED, zucchiniSeed);
        registerItem(ItemNames.MUSTARD_SEED, mustardSeed);
        registerItem(ItemNames.CHILE_PEPPER_SEED, pepperSeed);
        registerItem(ItemNames.TURMERIC_SEED, turmericSeed);
        registerItem(ItemNames.GINGER_SEED, gingerSeed);
        registerItem(ItemNames.BASIL_SEED, basilSeed);
        registerItem(ItemNames.OAT_SEED, oatSeed);
        registerItem(ItemNames.BARLEY_SEED, barleySeed);
        registerItem(ItemNames.SOYBEAN_SEED, soybeanSeed);



        registerItem(ItemNames.ALMOND_SAPLING, almondSapling);
        registerItem(ItemNames.CASHEW_SAPLING, cashewSapling);
        registerItem(ItemNames.PECAN_SAPLING, pecanSapling);
        registerItem(ItemNames.WALNUT_SAPLING, walnutSapling);


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
        registerItem(ItemNames.SNICKER_DOODLE, snickerDoodle); // todo no recipe
        registerItem(ItemNames.BANANA_NUT_BREAD, bananaNutBread); // todo no recipe no nuts
        registerItem(ItemNames.PECAN_PIE, pecanPie);
        registerItem(ItemNames.CANDIED_NUTS, candiedNuts);
        registerItem(ItemNames.ALMOND_BRITTLE, almondBrittle);
        registerItem(ItemNames.RAISIN_OATMEAL_COOKIE, oatmealCookie);
        registerItem(ItemNames.NUTTY_COOKIE, nuttyCookie);

        registerItem(ItemNames.FOOD_PRESS, foodPress);
        registerItem(ItemNames.FRYING_PAN, fryingPan);
        registerItem(ItemNames.COOKING_POT, cookingPot);
        registerItem(ItemNames.MORTAR_AND_PESTLE, mortarAndPestle);

        registerItem(ItemNames.SALT_ORE, saltOre);
    }
}
