package me.thonk.croptopia.items;

import me.thonk.croptopia.Croptopia;
import me.thonk.croptopia.blocks.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ComposterBlock;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

import java.util.ArrayList;

import static me.thonk.croptopia.Croptopia.CROPTOPIA_ITEM_GROUP;
import static me.thonk.croptopia.FoodRegistry.*;
import static net.minecraft.world.biome.Biome.Category.*;

public class ItemRegistry {

    private static ArrayList<Item> seeds = new ArrayList<>();

    // Fruits & Vegetables // cropitem
    public static Item artichoke;
    public static Item asparagus;
    public static Item barley;
    public static Item bellPepper;
    public static Item blackBean;
    public static Item blackberry;
    public static Item blueberry;
    public static Item broccoli;
    public static Item cabbage;
    public static Item cantaloupe;
    public static Item cauliflower;
    public static Item celery;
    public static Item coffee;
    public static Item corn;
    public static Item cranberry;
    public static Item cucumber;
    public static Item currant;
    public static Item eggplant;
    public static Item elderberry;
    public static Item garlic;
    public static Item grape;
    public static Item greenBean;
    public static Item greenOnion;
    public static Item honeydew;
    public static Item hops;
    public static Item kale;
    public static Item kiwi;
    public static Item leek;
    public static Item lettuce;
    public static Item oat;
    public static Item olive;
    public static Item onion;
    public static Item peanut;
    public static Item pineapple;
    public static Item radish;
    public static Item raspberry;
    public static Item rhubarb;
    public static Item rice;
    public static Item rutabaga;
    public static Item saguaro;
    public static Item soybean;
    public static Item spinach;
    public static Item squash;
    public static Item strawberry;
    public static Item sweetPotato;
    public static Item tomatillo;
    public static Item tomato;
    public static Item turnip;
    public static Item yam;
    public static Item zucchini;

    // Trees
    public static Item orange;
    public static Item banana;
    public static Item persimmon;
    public static Item plum;
    public static Item cherry;
    public static Item lemon;
    public static Item grapefruit;
    public static Item kumquat;
    public static Item peach;
    public static Item coconut;
    public static Item nutmeg;
    public static Item fig;
    public static Item nectarine;
    public static Item mango;
    public static Item dragonFruit;
    public static Item starFruit;
    public static Item avocado;
    public static Item apricot;
    public static Item pear;
    public static Item lime;
    public static Item date;

    // Spices
    public static Item mustard;
    public static Item vanilla;
    public static Item paprika;
    public static Item pepper;
    public static Item salt;
    public static Item turmeric;
    public static Item ginger;
    // Herbs
    public static Item chives;
    public static Item basil;


    public static Item artichokeSeed;
    public static Item asparagusSeed;
    public static Item bellPepperSeed;
    public static Item blackBeanSeed;
    public static Item blackberrySeed;
    public static Item blueberrySeed;
    public static Item broccoliSeed;
    public static Item cabbageSeed;
    public static Item cantaloupeSeed;
    public static Item cauliflowerSeed;
    public static Item celerySeed;
    public static Item coffeeSeed;
    public static Item cornSeed;
    public static Item cranberrySeed;
    public static Item cucumberSeed;
    public static Item currantSeed;
    public static Item eggplantSeed;
    public static Item elderberrySeed;
    public static Item garlicSeed;
    public static Item grapeSeed;
    public static Item greenBeanSeed;
    public static Item greenOnionSeed;
    public static Item honeydewSeed;
    public static Item hopsSeed;
    public static Item kaleSeed;
    public static Item kiwiSeed;
    public static Item leekSeed;
    public static Item lettuceSeed;
    public static Item oliveSeed;
    public static Item onionSeed;
    public static Item peanutSeed;
    public static Item pineappleSeed;
    public static Item radishSeed;
    public static Item raspberrySeed;
    public static Item rhubarbSeed;
    public static Item riceSeed;
    public static Item rutabagaSeed;
    public static Item saguaroSeed;
    public static Item spinachSeed;
    public static Item squashSeed;
    public static Item strawberrySeed;
    public static Item sweetPotatoSeed;
    public static Item tomatilloSeed;
    public static Item tomatoSeed;
    public static Item turnipSeed;
    public static Item yamSeed;
    public static Item zucchiniSeed;
    public static Item mustardSeed;
    public static Item paprikaSeed;
    public static Item pepperSeed;
    public static Item turmericSeed;
    public static Item gingerSeed;
    public static Item chivesSeed;
    public static Item basilSeed;
    public static Item oatSeed;
    public static Item barleySeed;
    public static Item soybeanSeed;



    public static Item appleSapling;
    public static Item bananaSapling;
    public static Item orangeSapling;
    public static Item persimmonSapling;
    public static Item plumSapling;
    public static Item cherrySapling;
    public static Item lemonSapling;
    public static Item grapefruitSapling;
    public static Item kumquatSapling;
    public static Item peachSapling;
    public static Item coconutSapling;
    public static Item nutmegSapling;
    public static Item figSapling;
    public static Item nectarineSapling;
    public static Item mangoSapling;
    public static Item dragonFruitSapling;
    public static Item starFruitSapling;
    public static Item avocadoSapling;
    public static Item apricotSapling;
    public static Item pearSapling;
    public static Item limeSapling;
    public static Item dateSapling;


    public static Item vanillaSeed;

    // intermediary foods. These would be extra ingredients before making a big main food.
    public static Item oliveOil;
    public static Item cheese;
    public static Item chocolate;
    public static Item flour;
    public static Item butter;
    public static Item noodle;
    public static Item tofu;

    public static void init() {

        artichoke = registerItem("artichoke", new Item(createGroup().food(EDIBLE_YUCK)));
        asparagus = registerItem("asparagus", new Item(createGroup().food(EDIBLE_OK)));
        barley = registerItem("barley", new Item(createGroup().food(EDIBLE_YUCK)));
        bellPepper = registerItem("bellpepper", new Item(createGroup().food(EDIBLE_OK)));
        blackBean = registerItem("blackbean", new Item(createGroup().food(EDIBLE_OK)));
        blackberry = registerItem("blackberry", new Item(createGroup().food(EDIBLE_OK)));
        blueberry = registerItem("blueberry", new Item(createGroup().food(EDIBLE_OK)));
        broccoli = registerItem("broccoli", new Item(createGroup().food(EDIBLE_OK)));
        cabbage = registerItem("cabbage", new Item(createGroup().food(EDIBLE_YUCK)));
        cantaloupe = registerItem("cantaloupe", new Item(createGroup().food(EDIBLE_OK)));
        cauliflower = registerItem("cauliflower", new Item(createGroup().food(EDIBLE_OK)));
        celery = registerItem("celery", new Item(createGroup().food(EDIBLE_OK)));
        coffee = registerItem("coffee", new Item(createGroup().food(EDIBLE_OK)));
        corn = registerItem("corn", new Item(createGroup().food(EDIBLE_OK)));
        cranberry = registerItem("cranberry", new Item(createGroup().food(EDIBLE_OK)));
        cucumber = registerItem("cucumber", new Item(createGroup().food(EDIBLE_OK)));
        currant = registerItem("currant", new Item(createGroup().food(EDIBLE_OK)));
        eggplant = registerItem("eggplant", new Item(createGroup().food(EDIBLE_OK)));
        elderberry = registerItem("elderberry", new Item(createGroup().food(EDIBLE_OK)));
        garlic = registerItem("garlic", new Item(createGroup().food(EDIBLE_YUCK)));
        grape = registerItem("grape", new Item(createGroup().food(EDIBLE_OK)));
        greenBean = registerItem("greenbean", new Item(createGroup().food(EDIBLE_OK)));
        greenOnion = registerItem("greenonion", new Item(createGroup().food(EDIBLE_YUCK)));
        honeydew = registerItem("honeydew", new Item(createGroup().food(EDIBLE_OK)));
        hops = registerItem("hops", new Item(createGroup()));
        kale = registerItem("kale", new Item(createGroup().food(EDIBLE_OK)));
        kiwi = registerItem("kiwi", new Item(createGroup().food(EDIBLE_OK)));
        leek = registerItem("leek", new Item(createGroup().food(EDIBLE_OK)));
        lettuce = registerItem("lettuce", new Item(createGroup().food(EDIBLE_OK)));
        olive = registerItem("olive", new Item(createGroup().food(EDIBLE_OK)));
        onion = registerItem("onion", new Item(createGroup().food(EDIBLE_OK)));
        peanut = registerItem("peanut", new Item(createGroup().food(EDIBLE_YUCK)));
        pineapple = registerItem("pineapple", new Item(createGroup().food(EDIBLE_OK)));
        radish = registerItem("radish", new Item(createGroup().food(EDIBLE_OK)));
        raspberry = registerItem("raspberry", new Item(createGroup().food(EDIBLE_OK)));
        rhubarb = registerItem("rhubarb", new Item(createGroup().food(EDIBLE_OK)));
        rice = registerItem("rice", new Item(createGroup().food(EDIBLE_YUCK)));
        rutabaga = registerItem("rutabaga", new Item(createGroup().food(EDIBLE_OK)));
        saguaro = registerItem("saguaro", new Item(createGroup().food(EDIBLE_OK)));
        spinach = registerItem("spinach", new Item(createGroup().food(EDIBLE_OK)));
        squash = registerItem("squash", new Item(createGroup().food(EDIBLE_OK)));
        strawberry = registerItem("strawberry", new Item(createGroup().food(EDIBLE_OK)));
        sweetPotato = registerItem("sweetpotato", new Item(createGroup().food(EDIBLE_OK)));
        tomatillo = registerItem("tomatillo", new Item(createGroup().food(EDIBLE_OK)));
        tomato = registerItem("tomato", new Item(createGroup().food(EDIBLE_OK)));
        turnip = registerItem("turnip", new Item(createGroup().food(EDIBLE_OK)));
        yam = registerItem("yam", new Item(createGroup().food(EDIBLE_OK)));
        zucchini = registerItem("zucchini", new Item(createGroup().food(EDIBLE_OK)));
        oat = registerItem("oat", new Item(createGroup().food(EDIBLE_YUCK)));
        soybean = registerItem("soybean", new Item(createGroup().food(EDIBLE_YUCK)));

        orange = registerItem("orange", new Item(createGroup().food(EDIBLE_OK)));
        banana = registerItem("banana", new Item(createGroup().food(EDIBLE_OK)));
        persimmon = registerItem("persimmon", new Item(createGroup().food(EDIBLE_OK)));
        plum = registerItem("plum", new Item(createGroup().food(EDIBLE_OK)));
        cherry = registerItem("cherry", new Item(createGroup().food(EDIBLE_OK)));
        lemon = registerItem("lemon", new Item(createGroup().food(EDIBLE_OK)));
        grapefruit = registerItem("grapefruit", new Item(createGroup().food(EDIBLE_OK)));
        kumquat = registerItem("kumquat", new Item(createGroup().food(EDIBLE_OK)));
        peach = registerItem("peach", new Item(createGroup().food(EDIBLE_OK)));
        coconut = registerItem("coconut", new Item(createGroup().food(EDIBLE_YUCK)));
        nutmeg = registerItem("nutmeg", new Item(createGroup().food(EDIBLE_YUCK)));
        fig = registerItem("fig", new Item(createGroup().food(EDIBLE_OK)));
        nectarine = registerItem("nectarine", new Item(createGroup().food(EDIBLE_OK)));
        mango = registerItem("mango", new Item(createGroup().food(EDIBLE_OK)));
        dragonFruit = registerItem("dragonfruit", new Item(createGroup().food(EDIBLE_OK)));
        starFruit = registerItem("starfruit", new Item(createGroup().food(EDIBLE_OK)));
        avocado = registerItem("avocado", new Item(createGroup().food(EDIBLE_OK)));
        apricot = registerItem("apricot", new Item(createGroup().food(EDIBLE_OK)));
        pear = registerItem("pear", new Item(createGroup().food(EDIBLE_OK)));
        lime = registerItem("lime", new Item(createGroup().food(EDIBLE_OK)));
        date = registerItem("date", new Item(createGroup().food(EDIBLE_OK)));

        mustard = registerItem("mustard", new Item(createGroup()));
        vanilla = registerItem("vanilla", new Item(createGroup()));
        paprika = registerItem("paprika", new Item(createGroup()));
        pepper = registerItem("pepper", new Item(createGroup()));
        salt = registerItem("salt", new Item(createGroup()));
        turmeric = registerItem("turmeric", new Item(createGroup()));
        ginger = registerItem("ginger", new Item(createGroup()));
        chives = registerItem("chives", new Item(createGroup().food(EDIBLE_YUCK)));
        basil = registerItem("basil", new Item(createGroup().food(EDIBLE_YUCK)));

        artichokeSeed = registerItem("artichoke_seed", new CroptopiaSeedItem(BlockRegistry.artichokeCropBlock, createGroup(), SWAMP));
        asparagusSeed = registerItem("asparagus_seed", new CroptopiaSeedItem(BlockRegistry.asparagusCropBlock, createGroup(), SWAMP));
        bellPepperSeed = registerItem("bellpepper_seed", new CroptopiaSeedItem(BlockRegistry.bellPepperCropBlock, createGroup(), PLAINS));
        blackBeanSeed = registerItem("blackbean_seed", new CroptopiaSeedItem(BlockRegistry.blackBeanCropBlock, createGroup(), FOREST));
        blackberrySeed = registerItem("blackberry_seed", new CroptopiaSeedItem(BlockRegistry.blackberryCropBlock, createGroup(), FOREST));
        blueberrySeed = registerItem("blueberry_seed", new CroptopiaSeedItem(BlockRegistry.blueberryCropBlock, createGroup(), FOREST));
        broccoliSeed = registerItem("broccoli_seed", new CroptopiaSeedItem(BlockRegistry.broccoliCropBlock, createGroup(), PLAINS));
        cabbageSeed = registerItem("cabbage_seed", new CroptopiaSeedItem(BlockRegistry.cabbageCropBlock, createGroup(), PLAINS));
        cantaloupeSeed = registerItem("cantaloupe_seed", new CroptopiaSeedItem(BlockRegistry.cantaloupeCropBlock, createGroup(), FOREST));
        cauliflowerSeed = registerItem("cauliflower_seed", new CroptopiaSeedItem(BlockRegistry.cauliflowerCropBlock, createGroup(), FOREST));
        celerySeed = registerItem("celery_seed", new CroptopiaSeedItem(BlockRegistry.celeryCropBlock, createGroup(), FOREST));
        coffeeSeed = registerItem("coffee_seed", new CroptopiaSeedItem(BlockRegistry.coffeeCropBlock, createGroup(), JUNGLE));
        cornSeed = registerItem("corn_seed", new CroptopiaSeedItem(BlockRegistry.cornCropBlock, createGroup(), PLAINS));
        cranberrySeed = registerItem("cranberry_seed", new CroptopiaSeedItem(BlockRegistry.cranberryCropBlock, createGroup(), SWAMP));
        cucumberSeed = registerItem("cucumber_seed", new CroptopiaSeedItem(BlockRegistry.cucumberCropBlock, createGroup(), PLAINS));
        currantSeed = registerItem("currant_seed", new CroptopiaSeedItem(BlockRegistry.currantCropBlock, createGroup(), SWAMP));
        eggplantSeed = registerItem("eggplant_seed", new CroptopiaSeedItem(BlockRegistry.eggplantCropBlock, createGroup(), JUNGLE));
        elderberrySeed = registerItem("elderberry_seed", new CroptopiaSeedItem(BlockRegistry.elderberryCropBlock, createGroup(), FOREST));
        garlicSeed = registerItem("garlic_seed", new CroptopiaSeedItem(BlockRegistry.garlicCropBlock, createGroup(), JUNGLE));
        grapeSeed = registerItem("grape_seed", new CroptopiaSeedItem(BlockRegistry.grapeCropBlock, createGroup(), FOREST));
        greenBeanSeed = registerItem("greenbean_seed", new CroptopiaSeedItem(BlockRegistry.greenBeanCropBlock, createGroup(), PLAINS));
        greenOnionSeed = registerItem("greenonion_seed", new CroptopiaSeedItem(BlockRegistry.greenOnionCropBlock, createGroup(), JUNGLE));
        honeydewSeed = registerItem("honeydew_seed", new CroptopiaSeedItem(BlockRegistry.honeydewCropBlock, createGroup(), JUNGLE));
        hopsSeed = registerItem("hops_seed", new CroptopiaSeedItem(BlockRegistry.hopsCropBlock, createGroup(), SAVANNA));
        kaleSeed = registerItem("kale_seed", new CroptopiaSeedItem(BlockRegistry.kaleCropBlock, createGroup(), PLAINS));
        kiwiSeed = registerItem("kiwi_seed", new CroptopiaSeedItem(BlockRegistry.kiwiCropBlock, createGroup(), SAVANNA));
        leekSeed = registerItem("leek_seed", new CroptopiaSeedItem(BlockRegistry.leekCropBlock, createGroup(), SAVANNA));
        lettuceSeed = registerItem("lettuce_seed", new CroptopiaSeedItem(BlockRegistry.lettuceCropBlock, createGroup(), PLAINS));
        oliveSeed = registerItem("olive_seed", new CroptopiaSeedItem(BlockRegistry.oliveCropBlock, createGroup(), SAVANNA));
        onionSeed = registerItem("onion_seed", new CroptopiaSeedItem(BlockRegistry.onionCropBlock, createGroup(), JUNGLE));
        peanutSeed = registerItem("peanut_seed", new CroptopiaSeedItem(BlockRegistry.peanutCropBlock, createGroup(), JUNGLE));
        pineappleSeed = registerItem("pineapple_seed", new CroptopiaSeedItem(BlockRegistry.pineappleCropBlock, createGroup(), JUNGLE));
        radishSeed = registerItem("radish_seed", new CroptopiaSeedItem(BlockRegistry.radishCropBlock, createGroup(), FOREST));
        raspberrySeed = registerItem("raspberry_seed", new CroptopiaSeedItem(BlockRegistry.raspberryCropBlock, createGroup(), FOREST));
        rhubarbSeed = registerItem("rhubarb_seed", new CroptopiaSeedItem(BlockRegistry.rhubarbCropBlock, createGroup(), JUNGLE));
        riceSeed = registerItem("rice_seed", new CroptopiaSeedItem(BlockRegistry.riceCropBlock, createGroup(), JUNGLE));
        rutabagaSeed = registerItem("rutabaga_seed", new CroptopiaSeedItem(BlockRegistry.rutabagaCropBlock, createGroup(), SAVANNA));
        saguaroSeed = registerItem("saguaro_seed", new CroptopiaSeedItem(BlockRegistry.saguaroCropBlock, createGroup(), DESERT));
        spinachSeed = registerItem("spinach_seed", new CroptopiaSeedItem(BlockRegistry.spinachCropBlock, createGroup(), FOREST));
        squashSeed = registerItem("squash_seed", new CroptopiaSeedItem(BlockRegistry.squashCropBlock, createGroup(), SAVANNA));
        strawberrySeed = registerItem("strawberry_seed", new CroptopiaSeedItem(BlockRegistry.strawberryCropBlock, createGroup(), FOREST));
        sweetPotatoSeed = registerItem("sweetpotato_seed", new CroptopiaSeedItem(BlockRegistry.sweetPotatoCropBlock, createGroup(), PLAINS));
        tomatilloSeed = registerItem("tomatillo_seed", new CroptopiaSeedItem(BlockRegistry.tomatilloCropBlock, createGroup(), FOREST));
        tomatoSeed = registerItem("tomato_seed", new CroptopiaSeedItem(BlockRegistry.tomatoCropBlock, createGroup(), FOREST));
        turnipSeed = registerItem("turnip_seed", new CroptopiaSeedItem(BlockRegistry.turnipCropBlock, createGroup(), JUNGLE));
        yamSeed = registerItem("yam_seed", new CroptopiaSeedItem(BlockRegistry.yamCropBlock, createGroup(), SAVANNA));
        zucchiniSeed = registerItem("zucchini_seed", new CroptopiaSeedItem(BlockRegistry.zucchiniCropBlock, createGroup(), SAVANNA));
        oatSeed = registerItem("oat_seed", new CroptopiaSeedItem(BlockRegistry.oatCropBlock, createGroup(), PLAINS));


        mustardSeed = registerItem("mustard_seed", new CroptopiaSeedItem(BlockRegistry.mustardCropBlock, createGroup(), PLAINS));
        paprikaSeed = registerItem("paprika_seed", new CroptopiaSeedItem(BlockRegistry.paprikaCropBlock, createGroup(), DESERT));
        pepperSeed = registerItem("pepper_seed", new CroptopiaSeedItem(BlockRegistry.pepperCropBlock, createGroup(), PLAINS));
        turmericSeed = registerItem("turmeric_seed", new CroptopiaSeedItem(BlockRegistry.turmericCropBlock, createGroup(), SAVANNA));
        gingerSeed = registerItem("ginger_seed", new CroptopiaSeedItem(BlockRegistry.gingerCropBlock, createGroup(), SAVANNA));
        chivesSeed = registerItem("chives_seed", new CroptopiaSeedItem(BlockRegistry.chivesCropBlock, createGroup(), JUNGLE));
        basilSeed = registerItem("basil_seed", new CroptopiaSeedItem(BlockRegistry.basilCropBlock, createGroup(), JUNGLE));


        appleSapling = registerItem("apple_sapling", new AliasedBlockItem(BlockRegistry.appleSaplingBlock, createGroup()));
        bananaSapling = registerItem("banana_sapling", new AliasedBlockItem(BlockRegistry.bananaSaplingBlock, createGroup()));
        orangeSapling = registerItem("orange_sapling", new AliasedBlockItem(BlockRegistry.orangeSaplingBlock, createGroup()));
        persimmonSapling = registerItem("persimmon_sapling", new AliasedBlockItem(BlockRegistry.persimmonSaplingBlock, createGroup()));
        plumSapling = registerItem("plum_sapling", new AliasedBlockItem(BlockRegistry.plumSaplingBlock, createGroup()));
        cherrySapling = registerItem("cherry_sapling", new AliasedBlockItem(BlockRegistry.cherrySaplingBlock, createGroup()));
        lemonSapling = registerItem("lemon_sapling", new AliasedBlockItem(BlockRegistry.lemonSaplingBlock, createGroup()));
        grapefruitSapling = registerItem("grapefruit_sapling", new AliasedBlockItem(BlockRegistry.grapefruitSaplingBlock, createGroup()));
        kumquatSapling = registerItem("kumquat_sapling", new AliasedBlockItem(BlockRegistry.kumquatSaplingBlock, createGroup()));
        peachSapling = registerItem("peach_sapling", new AliasedBlockItem(BlockRegistry.peachSaplingBlock, createGroup()));
        coconutSapling = registerItem("coconut_sapling", new AliasedBlockItem(BlockRegistry.coconutSaplingBlock, createGroup()));
        nutmegSapling = registerItem("nutmeg_sapling", new AliasedBlockItem(BlockRegistry.nutmegSaplingBlock, createGroup()));
        figSapling = registerItem("fig_sapling", new AliasedBlockItem(BlockRegistry.figSaplingBlock, createGroup()));
        nectarineSapling = registerItem("nectarine_sapling", new AliasedBlockItem(BlockRegistry.nectarineSaplingBlock, createGroup()));
        mangoSapling = registerItem("mango_sapling", new AliasedBlockItem(BlockRegistry.mangoSaplingBlock, createGroup()));
        dragonFruitSapling = registerItem("dragonfruit_sapling", new AliasedBlockItem(BlockRegistry.dragonFruitSaplingBlock, createGroup()));
        starFruitSapling = registerItem("starfruit_sapling", new AliasedBlockItem(BlockRegistry.starFruitSaplingBlock, createGroup()));
        avocadoSapling = registerItem("avocado_sapling", new AliasedBlockItem(BlockRegistry.avocadoSaplingBlock, createGroup()));
        apricotSapling = registerItem("apricot_sapling", new AliasedBlockItem(BlockRegistry.apricotSaplingBlock, createGroup()));
        pearSapling = registerItem("pear_sapling", new AliasedBlockItem(BlockRegistry.pearSaplingBlock, createGroup()));
        limeSapling = registerItem("lime_sapling", new AliasedBlockItem(BlockRegistry.limeSaplingBlock, createGroup()));
        dateSapling = registerItem("date_sapling", new AliasedBlockItem(BlockRegistry.dateSaplingBlock, createGroup()));

    }


    public static Item registerItem(String itemName, Item item) {
        Registry.register(Registry.ITEM, Croptopia.createIdentifier(itemName), item);
        // \bregisterItem\b..[A-Z]\w+",
        //System.out.println( "\"" + itemName + "\",");
        if (item instanceof CroptopiaSeedItem) {
            seeds.add(item);
        }
        return item;
    }

    public static ArrayList<Item> getSeeds() {
        return seeds;
    }

    public static Item.Settings createGroup() {
        return new Item.Settings().group(CROPTOPIA_ITEM_GROUP);
    }
}
