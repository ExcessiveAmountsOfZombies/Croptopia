package me.thonk.croptopia.items;

import me.thonk.croptopia.blocks.BlockRegistry;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;

import static me.thonk.croptopia.Croptopia.createGroup;
import static me.thonk.croptopia.Croptopia.registerItem;
import static me.thonk.croptopia.FoodRegistry.EDIBLE_OK;
import static me.thonk.croptopia.FoodRegistry.EDIBLE_YUCK;
import static net.minecraft.world.biome.Biome.Category.*;

public class ItemRegistry {



    // Fruits & Vegetables // cropitem
    public static Item artichoke = registerItem("artichoke", new Item(createGroup().food(EDIBLE_YUCK)));
    public static Item asparagus = registerItem("asparagus", new Item(createGroup().food(EDIBLE_OK)));
    public static Item barley = registerItem("barley", new Item(createGroup().food(EDIBLE_YUCK)));
    public static Item bellPepper = registerItem("bellpepper", new Item(createGroup().food(EDIBLE_OK)));
    public static Item blackBean = registerItem("blackbean", new Item(createGroup().food(EDIBLE_OK)));
    public static Item blackberry = registerItem("blackberry", new Item(createGroup().food(EDIBLE_OK)));
    public static Item blueberry = registerItem("blueberry", new Item(createGroup().food(EDIBLE_OK)));
    public static Item broccoli = registerItem("broccoli", new Item(createGroup().food(EDIBLE_OK)));
    public static Item cabbage = registerItem("cabbage", new Item(createGroup().food(EDIBLE_YUCK)));
    public static Item cantaloupe = registerItem("cantaloupe", new Item(createGroup().food(EDIBLE_OK)));
    public static Item cauliflower = registerItem("cauliflower", new Item(createGroup().food(EDIBLE_OK)));
    public static Item celery = registerItem("celery", new Item(createGroup().food(EDIBLE_OK)));
    public static Item coffee = registerItem("coffee", new Item(createGroup().food(EDIBLE_OK)));
    public static Item corn = registerItem("corn", new Item(createGroup().food(EDIBLE_OK)));
    public static Item cranberry = registerItem("cranberry", new Item(createGroup().food(EDIBLE_OK)));
    public static Item cucumber = registerItem("cucumber", new Item(createGroup().food(EDIBLE_OK)));
    public static Item currant = registerItem("currant", new Item(createGroup().food(EDIBLE_OK)));
    public static Item eggplant = registerItem("eggplant", new Item(createGroup().food(EDIBLE_OK)));
    public static Item elderberry = registerItem("elderberry", new Item(createGroup().food(EDIBLE_OK)));
    public static Item garlic = registerItem("garlic", new Item(createGroup().food(EDIBLE_YUCK)));
    public static Item grape = registerItem("grape", new Item(createGroup().food(EDIBLE_OK)));
    public static Item greenBean = registerItem("greenbean", new Item(createGroup().food(EDIBLE_OK)));
    public static Item greenOnion = registerItem("greenonion", new Item(createGroup().food(EDIBLE_YUCK)));
    public static Item honeydew = registerItem("honeydew", new Item(createGroup().food(EDIBLE_OK)));
    public static Item hops = registerItem("hops", new Item(createGroup()));
    public static Item kale = registerItem("kale", new Item(createGroup().food(EDIBLE_OK)));
    public static Item kiwi = registerItem("kiwi", new Item(createGroup().food(EDIBLE_OK)));
    public static Item leek = registerItem("leek", new Item(createGroup().food(EDIBLE_OK)));
    public static Item lettuce = registerItem("lettuce", new Item(createGroup().food(EDIBLE_OK)));
    public static Item oat = registerItem("oat", new Item(createGroup().food(EDIBLE_YUCK)));
    public static Item olive = registerItem("olive", new Item(createGroup().food(EDIBLE_OK)));
    public static Item onion = registerItem("onion", new Item(createGroup().food(EDIBLE_OK)));
    public static Item peanut = registerItem("peanut", new Item(createGroup().food(EDIBLE_YUCK)));
    public static Item pineapple = registerItem("pineapple", new Item(createGroup().food(EDIBLE_OK)));
    public static Item radish = registerItem("radish", new Item(createGroup().food(EDIBLE_OK)));
    public static Item raspberry = registerItem("raspberry", new Item(createGroup().food(EDIBLE_OK)));
    public static Item rhubarb = registerItem("rhubarb", new Item(createGroup().food(EDIBLE_OK)));
    public static Item rice = registerItem("rice", new Item(createGroup().food(EDIBLE_YUCK)));
    public static Item rutabaga = registerItem("rutabaga", new Item(createGroup().food(EDIBLE_OK)));
    public static Item saguaro = registerItem("saguaro", new Item(createGroup().food(EDIBLE_OK)));
    public static Item soybean = registerItem("soybean", new Item(createGroup().food(EDIBLE_YUCK)));
    public static Item spinach = registerItem("spinach", new Item(createGroup().food(EDIBLE_OK)));
    public static Item squash = registerItem("squash", new Item(createGroup().food(EDIBLE_OK)));
    public static Item strawberry = registerItem("strawberry", new Item(createGroup().food(EDIBLE_OK)));
    public static Item sweetPotato = registerItem("sweetpotato", new Item(createGroup().food(EDIBLE_OK)));
    public static Item tomatillo = registerItem("tomatillo", new Item(createGroup().food(EDIBLE_OK)));
    public static Item tomato = registerItem("tomato", new Item(createGroup().food(EDIBLE_OK)));
    public static Item turnip = registerItem("turnip", new Item(createGroup().food(EDIBLE_OK)));
    public static Item yam = registerItem("yam", new Item(createGroup().food(EDIBLE_OK)));
    public static Item zucchini = registerItem("zucchini", new Item(createGroup().food(EDIBLE_OK)));

    // Trees
    public static Item orange = registerItem("orange", new Item(createGroup().food(EDIBLE_OK)));
    public static Item banana = registerItem("banana", new Item(createGroup().food(EDIBLE_OK)));
    public static Item persimmon = registerItem("persimmon", new Item(createGroup().food(EDIBLE_OK)));
    public static Item plum = registerItem("plum", new Item(createGroup().food(EDIBLE_OK)));
    public static Item cherry = registerItem("cherry", new Item(createGroup().food(EDIBLE_OK)));
    public static Item lemon = registerItem("lemon", new Item(createGroup().food(EDIBLE_OK)));
    public static Item grapefruit = registerItem("grapefruit", new Item(createGroup().food(EDIBLE_OK)));
    public static Item kumquat = registerItem("kumquat", new Item(createGroup().food(EDIBLE_OK)));
    public static Item peach = registerItem("peach", new Item(createGroup().food(EDIBLE_OK)));
    public static Item coconut = registerItem("coconut", new Item(createGroup().food(EDIBLE_YUCK)));
    public static Item nutmeg = registerItem("nutmeg", new Item(createGroup().food(EDIBLE_YUCK)));
    public static Item fig = registerItem("fig", new Item(createGroup().food(EDIBLE_OK)));
    public static Item nectarine = registerItem("nectarine", new Item(createGroup().food(EDIBLE_OK)));
    public static Item mango = registerItem("mango", new Item(createGroup().food(EDIBLE_OK)));
    public static Item dragonFruit = registerItem("dragonfruit", new Item(createGroup().food(EDIBLE_OK)));
    public static Item starFruit = registerItem("starfruit", new Item(createGroup().food(EDIBLE_OK)));
    public static Item avocado = registerItem("avocado", new Item(createGroup().food(EDIBLE_OK)));
    public static Item apricot = registerItem("apricot", new Item(createGroup().food(EDIBLE_OK)));
    public static Item pear = registerItem("pear", new Item(createGroup().food(EDIBLE_OK)));
    public static Item lime = registerItem("lime", new Item(createGroup().food(EDIBLE_OK)));
    public static Item date = registerItem("date", new Item(createGroup().food(EDIBLE_OK)));

    // Spices
    public static Item mustard = registerItem("mustard", new Item(createGroup()));
    public static Item vanilla = registerItem("vanilla", new Item(createGroup()));
    public static Item paprika = registerItem("paprika", new Item(createGroup()));
    public static Item pepper = registerItem("pepper", new Item(createGroup()));
    public static Item salt = registerItem("salt", new Item(createGroup()));
    public static Item turmeric = registerItem("turmeric", new Item(createGroup()));
    public static Item ginger = registerItem("ginger", new Item(createGroup()));
    // Herbs
    public static Item chives = registerItem("chives", new Item(createGroup().food(EDIBLE_YUCK)));
    public static Item basil = registerItem("basil", new Item(createGroup().food(EDIBLE_YUCK)));


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
    public static Item barleySeed;
    public static Item soybeanSeed;



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

    // intermediary foods. These would be extra ingredients before making a big main food.
    public static Item oliveOil;
    public static Item cheese;
    public static Item chocolate;
    public static Item flour;
    public static Item butter;
    public static Item noodle;
    public static Item tofu;

    public static void init() {}
}
