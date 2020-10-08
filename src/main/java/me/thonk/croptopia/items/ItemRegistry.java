package me.thonk.croptopia.items;

import me.thonk.croptopia.blocks.BlockRegistry;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.MilkBucketItem;

import static me.thonk.croptopia.Croptopia.createGroup;
import static me.thonk.croptopia.Croptopia.registerItem;
import static me.thonk.croptopia.FoodRegistry.*;
import static net.minecraft.world.biome.Biome.Category.*;

public class ItemRegistry {



    // Fruits & Vegetables // cropitem
    public static Item artichoke = registerItem("artichoke", new Item(createGroup().food(EDIBLE_1)));
    public static Item asparagus = registerItem("asparagus", new Item(createGroup().food(EDIBLE_3)));
    public static Item barley = registerItem("barley", new Item(createGroup().food(EDIBLE_1)));
    public static Item bellPepper = registerItem("bellpepper", new Item(createGroup().food(EDIBLE_3)));
    public static Item blackBean = registerItem("blackbean", new Item(createGroup().food(EDIBLE_3)));
    public static Item blackberry = registerItem("blackberry", new Item(createGroup().food(EDIBLE_3)));
    public static Item blueberry = registerItem("blueberry", new Item(createGroup().food(EDIBLE_3)));
    public static Item broccoli = registerItem("broccoli", new Item(createGroup().food(EDIBLE_3)));
    public static Item cabbage = registerItem("cabbage", new Item(createGroup().food(EDIBLE_1)));
    public static Item cantaloupe = registerItem("cantaloupe", new Item(createGroup().food(EDIBLE_3)));
    public static Item cauliflower = registerItem("cauliflower", new Item(createGroup().food(EDIBLE_3)));
    public static Item celery = registerItem("celery", new Item(createGroup().food(EDIBLE_3)));
    public static Item coffeeBeans = registerItem("coffee_beans", new Item(createGroup().food(EDIBLE_3)));
    public static Item corn = registerItem("corn", new Item(createGroup().food(EDIBLE_3)));
    public static Item cranberry = registerItem("cranberry", new Item(createGroup().food(EDIBLE_3)));
    public static Item cucumber = registerItem("cucumber", new Item(createGroup().food(EDIBLE_3)));
    public static Item currant = registerItem("currant", new Item(createGroup().food(EDIBLE_3)));
    public static Item eggplant = registerItem("eggplant", new Item(createGroup().food(EDIBLE_3)));
    public static Item elderberry = registerItem("elderberry", new Item(createGroup().food(EDIBLE_3)));
    public static Item garlic = registerItem("garlic", new Item(createGroup().food(EDIBLE_1)));
    public static Item grape = registerItem("grape", new Item(createGroup().food(EDIBLE_3)));
    public static Item greenBean = registerItem("greenbean", new Item(createGroup().food(EDIBLE_3)));
    public static Item greenOnion = registerItem("greenonion", new Item(createGroup().food(EDIBLE_1)));
    public static Item honeydew = registerItem("honeydew", new Item(createGroup().food(EDIBLE_3)));
    public static Item hops = registerItem("hops", new Item(createGroup()));
    public static Item kale = registerItem("kale", new Item(createGroup().food(EDIBLE_3)));
    public static Item kiwi = registerItem("kiwi", new Item(createGroup().food(EDIBLE_3)));
    public static Item leek = registerItem("leek", new Item(createGroup().food(EDIBLE_3)));
    public static Item lettuce = registerItem("lettuce", new Item(createGroup().food(EDIBLE_3)));
    public static Item oat = registerItem("oat", new Item(createGroup().food(EDIBLE_1)));
    public static Item olive = registerItem("olive", new Item(createGroup().food(EDIBLE_3)));
    public static Item onion = registerItem("onion", new Item(createGroup().food(EDIBLE_3)));
    public static Item peanut = registerItem("peanut", new Item(createGroup().food(EDIBLE_1)));
    public static Item pineapple = registerItem("pineapple", new Item(createGroup().food(EDIBLE_3)));
    public static Item radish = registerItem("radish", new Item(createGroup().food(EDIBLE_3)));
    public static Item raspberry = registerItem("raspberry", new Item(createGroup().food(EDIBLE_3)));
    public static Item rhubarb = registerItem("rhubarb", new Item(createGroup().food(EDIBLE_3)));
    public static Item rice = registerItem("rice", new Item(createGroup().food(EDIBLE_1)));
    public static Item rutabaga = registerItem("rutabaga", new Item(createGroup().food(EDIBLE_3)));
    public static Item saguaro = registerItem("saguaro", new Item(createGroup().food(EDIBLE_3)));
    public static Item soybean = registerItem("soybean", new Item(createGroup().food(EDIBLE_1)));
    public static Item spinach = registerItem("spinach", new Item(createGroup().food(EDIBLE_3)));
    public static Item squash = registerItem("squash", new Item(createGroup().food(EDIBLE_3)));
    public static Item strawberry = registerItem("strawberry", new Item(createGroup().food(EDIBLE_3)));
    public static Item sweetPotato = registerItem("sweetpotato", new Item(createGroup().food(EDIBLE_3)));
    public static Item tomatillo = registerItem("tomatillo", new Item(createGroup().food(EDIBLE_3)));
    public static Item tomato = registerItem("tomato", new Item(createGroup().food(EDIBLE_3)));
    public static Item turnip = registerItem("turnip", new Item(createGroup().food(EDIBLE_3)));
    public static Item yam = registerItem("yam", new Item(createGroup().food(EDIBLE_3)));
    public static Item zucchini = registerItem("zucchini", new Item(createGroup().food(EDIBLE_3)));

    // Trees
    public static Item orange = registerItem("orange", new Item(createGroup().food(EDIBLE_3)));
    public static Item banana = registerItem("banana", new Item(createGroup().food(EDIBLE_3)));
    public static Item persimmon = registerItem("persimmon", new Item(createGroup().food(EDIBLE_3)));
    public static Item plum = registerItem("plum", new Item(createGroup().food(EDIBLE_3)));
    public static Item cherry = registerItem("cherry", new Item(createGroup().food(EDIBLE_3)));
    public static Item lemon = registerItem("lemon", new Item(createGroup().food(EDIBLE_3)));
    public static Item grapefruit = registerItem("grapefruit", new Item(createGroup().food(EDIBLE_3)));
    public static Item kumquat = registerItem("kumquat", new Item(createGroup().food(EDIBLE_3)));
    public static Item peach = registerItem("peach", new Item(createGroup().food(EDIBLE_3)));
    public static Item coconut = registerItem("coconut", new Item(createGroup().food(EDIBLE_1)));
    public static Item nutmeg = registerItem("nutmeg", new Item(createGroup().food(EDIBLE_1)));
    public static Item fig = registerItem("fig", new Item(createGroup().food(EDIBLE_3)));
    public static Item nectarine = registerItem("nectarine", new Item(createGroup().food(EDIBLE_3)));
    public static Item mango = registerItem("mango", new Item(createGroup().food(EDIBLE_3)));
    public static Item dragonFruit = registerItem("dragonfruit", new Item(createGroup().food(EDIBLE_3)));
    public static Item starFruit = registerItem("starfruit", new Item(createGroup().food(EDIBLE_3)));
    public static Item avocado = registerItem("avocado", new Item(createGroup().food(EDIBLE_3)));
    public static Item apricot = registerItem("apricot", new Item(createGroup().food(EDIBLE_3)));
    public static Item pear = registerItem("pear", new Item(createGroup().food(EDIBLE_3)));
    public static Item lime = registerItem("lime", new Item(createGroup().food(EDIBLE_3)));
    public static Item date = registerItem("date", new Item(createGroup().food(EDIBLE_3)));

    // Spices
    public static Item mustard = registerItem("mustard", new Item(createGroup()));
    public static Item vanilla = registerItem("vanilla", new Item(createGroup()));
    public static Item paprika = registerItem("paprika", new Item(createGroup()));
    public static Item pepper = registerItem("pepper", new Item(createGroup()));
    public static Item salt = registerItem("salt", new Item(createGroup()));
    public static Item turmeric = registerItem("turmeric", new Item(createGroup()));
    public static Item ginger = registerItem("ginger", new Item(createGroup()));
    // Herbs
    public static Item chives = registerItem("chives", new Item(createGroup().food(EDIBLE_1)));
    public static Item basil = registerItem("basil", new Item(createGroup().food(EDIBLE_1)));


    public static Item artichokeSeed = registerItem("artichoke_seed", new CroptopiaSeedItem(BlockRegistry.artichokeCropBlock, createGroup(), SWAMP));
    public static Item asparagusSeed = registerItem("asparagus_seed", new CroptopiaSeedItem(BlockRegistry.asparagusCropBlock, createGroup(), SWAMP));
    public static Item bellPepperSeed = registerItem("bellpepper_seed", new CroptopiaSeedItem(BlockRegistry.bellPepperCropBlock, createGroup(), PLAINS));
    public static Item blackBeanSeed = registerItem("blackbean_seed", new CroptopiaSeedItem(BlockRegistry.blackBeanCropBlock, createGroup(), FOREST));
    public static Item blackberrySeed = registerItem("blackberry_seed", new CroptopiaSeedItem(BlockRegistry.blackberryCropBlock, createGroup(), FOREST));
    public static Item blueberrySeed = registerItem("blueberry_seed", new CroptopiaSeedItem(BlockRegistry.blueberryCropBlock, createGroup(), FOREST));
    public static Item broccoliSeed = registerItem("broccoli_seed", new CroptopiaSeedItem(BlockRegistry.broccoliCropBlock, createGroup(), PLAINS));
    public static Item cabbageSeed = registerItem("cabbage_seed", new CroptopiaSeedItem(BlockRegistry.cabbageCropBlock, createGroup(), PLAINS));
    public static Item cantaloupeSeed = registerItem("cantaloupe_seed", new CroptopiaSeedItem(BlockRegistry.cantaloupeCropBlock, createGroup(), FOREST));
    public static Item cauliflowerSeed = registerItem("cauliflower_seed", new CroptopiaSeedItem(BlockRegistry.cauliflowerCropBlock, createGroup(), FOREST));
    public static Item celerySeed = registerItem("celery_seed", new CroptopiaSeedItem(BlockRegistry.celeryCropBlock, createGroup(), FOREST));
    public static Item coffeeSeed = registerItem("coffee_seed", new CroptopiaSeedItem(BlockRegistry.coffeeCropBlock, createGroup(), JUNGLE));
    public static Item cornSeed = registerItem("corn_seed", new CroptopiaSeedItem(BlockRegistry.cornCropBlock, createGroup(), PLAINS));
    public static Item cranberrySeed = registerItem("cranberry_seed", new CroptopiaSeedItem(BlockRegistry.cranberryCropBlock, createGroup(), SWAMP));
    public static Item cucumberSeed = registerItem("cucumber_seed", new CroptopiaSeedItem(BlockRegistry.cucumberCropBlock, createGroup(), PLAINS));
    public static Item currantSeed = registerItem("currant_seed", new CroptopiaSeedItem(BlockRegistry.currantCropBlock, createGroup(), SWAMP));
    public static Item eggplantSeed = registerItem("eggplant_seed", new CroptopiaSeedItem(BlockRegistry.eggplantCropBlock, createGroup(), JUNGLE));
    public static Item elderberrySeed = registerItem("elderberry_seed", new CroptopiaSeedItem(BlockRegistry.elderberryCropBlock, createGroup(), FOREST));
    public static Item garlicSeed = registerItem("garlic_seed", new CroptopiaSeedItem(BlockRegistry.garlicCropBlock, createGroup(), JUNGLE));
    public static Item grapeSeed = registerItem("grape_seed", new CroptopiaSeedItem(BlockRegistry.grapeCropBlock, createGroup(), FOREST));
    public static Item greenBeanSeed = registerItem("greenbean_seed", new CroptopiaSeedItem(BlockRegistry.greenBeanCropBlock, createGroup(), PLAINS));
    public static Item greenOnionSeed = registerItem("greenonion_seed", new CroptopiaSeedItem(BlockRegistry.greenOnionCropBlock, createGroup(), JUNGLE));
    public static Item honeydewSeed = registerItem("honeydew_seed", new CroptopiaSeedItem(BlockRegistry.honeydewCropBlock, createGroup(), JUNGLE));
    public static Item hopsSeed = registerItem("hops_seed", new CroptopiaSeedItem(BlockRegistry.hopsCropBlock, createGroup(), SAVANNA));
    public static Item kaleSeed = registerItem("kale_seed", new CroptopiaSeedItem(BlockRegistry.kaleCropBlock, createGroup(), PLAINS));
    public static Item kiwiSeed = registerItem("kiwi_seed", new CroptopiaSeedItem(BlockRegistry.kiwiCropBlock, createGroup(), SAVANNA));
    public static Item leekSeed = registerItem("leek_seed", new CroptopiaSeedItem(BlockRegistry.leekCropBlock, createGroup(), SAVANNA));
    public static Item lettuceSeed = registerItem("lettuce_seed", new CroptopiaSeedItem(BlockRegistry.lettuceCropBlock, createGroup(), PLAINS));
    public static Item oliveSeed = registerItem("olive_seed", new CroptopiaSeedItem(BlockRegistry.oliveCropBlock, createGroup(), SAVANNA));
    public static Item onionSeed = registerItem("onion_seed", new CroptopiaSeedItem(BlockRegistry.onionCropBlock, createGroup(), JUNGLE));
    public static Item peanutSeed = registerItem("peanut_seed", new CroptopiaSeedItem(BlockRegistry.peanutCropBlock, createGroup(), JUNGLE));
    public static Item pineappleSeed = registerItem("pineapple_seed", new CroptopiaSeedItem(BlockRegistry.pineappleCropBlock, createGroup(), JUNGLE));
    public static Item radishSeed = registerItem("radish_seed", new CroptopiaSeedItem(BlockRegistry.radishCropBlock, createGroup(), FOREST));
    public static Item raspberrySeed = registerItem("raspberry_seed", new CroptopiaSeedItem(BlockRegistry.raspberryCropBlock, createGroup(), FOREST));
    public static Item rhubarbSeed = registerItem("rhubarb_seed", new CroptopiaSeedItem(BlockRegistry.rhubarbCropBlock, createGroup(), JUNGLE));
    public static Item riceSeed = registerItem("rice_seed", new CroptopiaSeedItem(BlockRegistry.riceCropBlock, createGroup(), JUNGLE));
    public static Item rutabagaSeed = registerItem("rutabaga_seed", new CroptopiaSeedItem(BlockRegistry.rutabagaCropBlock, createGroup(), SAVANNA));
    public static Item saguaroSeed = registerItem("saguaro_seed", new CroptopiaSeedItem(BlockRegistry.saguaroCropBlock, createGroup(), DESERT));
    public static Item spinachSeed = registerItem("spinach_seed", new CroptopiaSeedItem(BlockRegistry.spinachCropBlock, createGroup(), FOREST));
    public static Item squashSeed = registerItem("squash_seed", new CroptopiaSeedItem(BlockRegistry.squashCropBlock, createGroup(), SAVANNA));
    public static Item strawberrySeed = registerItem("strawberry_seed", new CroptopiaSeedItem(BlockRegistry.strawberryCropBlock, createGroup(), FOREST));
    public static Item sweetPotatoSeed = registerItem("sweetpotato_seed", new CroptopiaSeedItem(BlockRegistry.sweetPotatoCropBlock, createGroup(), PLAINS));
    public static Item tomatilloSeed = registerItem("tomatillo_seed", new CroptopiaSeedItem(BlockRegistry.tomatilloCropBlock, createGroup(), FOREST));
    public static Item tomatoSeed = registerItem("tomato_seed", new CroptopiaSeedItem(BlockRegistry.tomatoCropBlock, createGroup(), FOREST));
    public static Item turnipSeed = registerItem("turnip_seed", new CroptopiaSeedItem(BlockRegistry.turnipCropBlock, createGroup(), JUNGLE));
    public static Item yamSeed = registerItem("yam_seed", new CroptopiaSeedItem(BlockRegistry.yamCropBlock, createGroup(), SAVANNA));
    public static Item zucchiniSeed = registerItem("zucchini_seed", new CroptopiaSeedItem(BlockRegistry.zucchiniCropBlock, createGroup(), SAVANNA));
    public static Item mustardSeed = registerItem("mustard_seed", new CroptopiaSeedItem(BlockRegistry.mustardCropBlock, createGroup(), PLAINS));
    public static Item paprikaSeed = registerItem("paprika_seed", new CroptopiaSeedItem(BlockRegistry.paprikaCropBlock, createGroup(), DESERT));
    public static Item pepperSeed = registerItem("pepper_seed", new CroptopiaSeedItem(BlockRegistry.pepperCropBlock, createGroup(), PLAINS));
    public static Item turmericSeed = registerItem("turmeric_seed", new CroptopiaSeedItem(BlockRegistry.turmericCropBlock, createGroup(), SAVANNA));
    public static Item gingerSeed = registerItem("ginger_seed", new CroptopiaSeedItem(BlockRegistry.gingerCropBlock, createGroup(), SAVANNA));
    public static Item chivesSeed = registerItem("chives_seed", new CroptopiaSeedItem(BlockRegistry.chivesCropBlock, createGroup(), JUNGLE));
    public static Item basilSeed = registerItem("basil_seed", new CroptopiaSeedItem(BlockRegistry.basilCropBlock, createGroup(), JUNGLE));
    public static Item oatSeed = registerItem("oat_seed", new CroptopiaSeedItem(BlockRegistry.oatCropBlock, createGroup(), PLAINS));
    public static Item barleySeed = registerItem("barley_seed", new CroptopiaSeedItem(BlockRegistry.barleyCropBlock, createGroup(), PLAINS));
    public static Item soybeanSeed = registerItem("soybean_seed", new CroptopiaSeedItem(BlockRegistry.soybeanCropBlock, createGroup(), PLAINS));



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
    public static Item dumpling = new Item(createGroup());
    public static Item ravioli = new Item(createGroup());
    public static Item salsa = new Item(createGroup().food(EDIBLE_3));
    public static Item artichokeDip = new Item(createGroup().food(EDIBLE_3));
    public static Item pepperoni = new Item(createGroup().food(EDIBLE_5));

    // drinks
    public static Item grapeJuice = new CroptopiaDrink(createGroup().food(EDIBLE_5));
    public static Item orangeJuice = new CroptopiaDrink(createGroup().food(EDIBLE_5));
    public static Item appleJuice = new CroptopiaDrink(createGroup().food(EDIBLE_5));
    public static Item cranberryJuice = new CroptopiaDrink(createGroup().food(EDIBLE_5));
    public static Item saguaroJuice = new CroptopiaDrink(createGroup().food(EDIBLE_5));
    public static Item tomatoJuice = new CroptopiaDrink(createGroup().food(EDIBLE_5));
    public static Item melonJuice = new CroptopiaDrink(createGroup().food(EDIBLE_5));
    public static Item pineappleJuice = new CroptopiaDrink(createGroup().food(EDIBLE_5));
    public static Item coffee = new CroptopiaDrink(createGroup().food(EDIBLE_5));
    public static Item lemonade = new CroptopiaDrink(createGroup().food(EDIBLE_5));
    public static Item limeade = new CroptopiaDrink(createGroup().food(EDIBLE_5));
    public static Item soyMilk = new MilkBucketItem(createGroup().food(EDIBLE_5));
    //public static Item tea;

    public static Item strawberrySmoothie = new CroptopiaDrink(createGroup().food(EDIBLE_7));
    public static Item bananaSmoothie = new CroptopiaDrink(createGroup().food(EDIBLE_7));
    public static Item kaleSmoothie = new CroptopiaDrink(createGroup().food(EDIBLE_7));
    public static Item fruitSmoothie = new CroptopiaDrink(createGroup().food(EDIBLE_7));

    public static Item chocolateMilkshake = new CroptopiaDrink(createGroup().food(EDIBLE_7));

    public static Item beer = new CroptopiaDrink(createGroup().food(EDIBLE_7));
    public static Item wine = new CroptopiaDrink(createGroup().food(EDIBLE_7));
    public static Item mead = new CroptopiaDrink(createGroup().food(EDIBLE_7));
    public static Item rum = new CroptopiaDrink(createGroup().food(EDIBLE_7));
    public static Item pumpkinSpiceLatte = new CroptopiaDrink(createGroup().food(EDIBLE_7));

    // jams
    public static Item grapeJam = new CroptopiaDrink(createGroup().food(EDIBLE_3));
    public static Item strawberryJam = new CroptopiaDrink(createGroup().food(EDIBLE_3));
    public static Item peachJam = new CroptopiaDrink(createGroup().food(EDIBLE_3));
    public static Item apricotJam = new CroptopiaDrink(createGroup().food(EDIBLE_3));
    public static Item blackberryJam = new CroptopiaDrink(createGroup().food(EDIBLE_3));
    public static Item blueberryJam = new CroptopiaDrink(createGroup().food(EDIBLE_3));
    public static Item cherryJam = new CroptopiaDrink(createGroup().food(EDIBLE_3));
    public static Item elderberryJam = new CroptopiaDrink(createGroup().food(EDIBLE_3));
    public static Item raspberryJam = new CroptopiaDrink(createGroup().food(EDIBLE_3));

    // snacks?
    public static Item beefJerky = new Item(createGroup().food(EDIBLE_5));
    public static Item porkJerky = new Item(createGroup().food(EDIBLE_5));
    public static Item kaleChips = new Item(createGroup().food(EDIBLE_5));
    public static Item potatoChips = new Item(createGroup().food(EDIBLE_5));
    public static Item steamedRice = new Item(createGroup().food(EDIBLE_5));
    public static Item eggRoll = new Item(createGroup().food(EDIBLE_5));
    public static Item frenchFries = new Item(createGroup().food(EDIBLE_5));
    public static Item sweetPotatoFries = new Item(createGroup().food(EDIBLE_5));
    public static Item onionRings = new Item(createGroup().food(EDIBLE_5));
    public static Item raisins = new Item(createGroup().food(EDIBLE_3));
    public static Item donut = new Item(createGroup().food(EDIBLE_5));
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
    public static Item pizza = new Item(createGroup().food(EDIBLE_14));
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

    // desert block?
    public static Item coffeeCake;
    public static Item chocolateCake;
    public static Item fruitCake;
    public static Item strawberryShortCake;
    public static Item carrotCake;

    // desert item
    public static Item applePie = new Item(createGroup().food(EDIBLE_14));
    public static Item yamJam = new Item(createGroup().food(EDIBLE_14));
    public static Item bananaCreamPie = new Item(createGroup().food(EDIBLE_14));
    public static Item candyCorn = new Item(createGroup().food(EDIBLE_5));
    public static Item vanillaIceCream = new Item(createGroup().food(EDIBLE_10));
    public static Item strawberryIceCream = new Item(createGroup().food(EDIBLE_10));
    public static Item mangoIceCream = new Item(createGroup().food(EDIBLE_10));
    public static Item rumRaisinIceCream = new Item(createGroup().food(EDIBLE_14));
    public static Item cherryPie = new Item(createGroup().food(EDIBLE_14));
    public static Item cheeseCake = new Item(createGroup().food(EDIBLE_14));
    public static Item brownies = new Item(createGroup().food(EDIBLE_10));
    public static Item snickerDoodle = new Item(createGroup().food(EDIBLE_7));
    public static Item bananaNutBread = new Item(createGroup().food(EDIBLE_10));

    // cooking utensils?
    public static Item foodPress = new Item(createGroup().maxCount(1).maxDamageIfAbsent(500));
    public static Item fryingPan = new Item(createGroup().maxCount(1).maxDamageIfAbsent(500));
    public static Item pot = new Item(createGroup().maxCount(1).maxDamageIfAbsent(500));


    public static void init() {

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
        registerItem("dumpling", dumpling);
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

        registerItem("beef_jerky", beefJerky);
        registerItem("pork_jerky", porkJerky);
        registerItem("kale_chips", kaleChips);
        registerItem("potato_chips", potatoChips);
        registerItem("steamed_rice", steamedRice);
        registerItem("egg_roll", eggRoll);
        registerItem("french_fries", frenchFries);
        registerItem("sweet_potato_fries", sweetPotatoFries);
        registerItem("onion_rings", onionRings);
        registerItem("raisins", raisins);
        registerItem("donut", donut);
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

        registerItem("scrambled_eggs", scrambledEggs);
        registerItem("buttered_toast", butteredToast);
        registerItem("toast_with_jam", toastWithJam);

        registerItem("ham_sandwich", hamSandwich);
        registerItem("peanut_butter_and_jam", peanutButterAndJam);
        registerItem("blt", BLT);
        registerItem("grilled_cheese", grilledCheese);
        registerItem("tuna_sandwich", tunaSandwich);
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

        registerItem("apple_pie", applePie);
        registerItem("yam_jam", yamJam);
        registerItem("banana_cream_pie", bananaCreamPie);
        registerItem("candy_corn", candyCorn);
        registerItem("vanilla_ice_cream", vanillaIceCream);
        registerItem("strawberry_ice_cream", strawberryIceCream);
        registerItem("mango_ice_cream", mangoIceCream);
        registerItem("rum_raisin_ice_cream", rumRaisinIceCream);
        registerItem("cherry_pie", cherryPie);
        registerItem("cheese_cake", cheeseCake);
        registerItem("brownies", brownies);
        registerItem("snicker_doodle", snickerDoodle);
        registerItem("banana_nut_bread", bananaNutBread);

        registerItem("food_press", foodPress);
    }
}
