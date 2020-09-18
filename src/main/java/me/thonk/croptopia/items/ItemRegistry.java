package me.thonk.croptopia.items;

import me.thonk.croptopia.Croptopia;
import me.thonk.croptopia.blocks.BlockRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

import static me.thonk.croptopia.Croptopia.CROPTOPIA_ITEM_GROUP;
import static me.thonk.croptopia.FoodRegistry.*;

public class ItemRegistry {

    // Fruits & Vegetables // cropitem
    public static Item artichoke;
    public static Item asparagus;
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
    /*public static Item orange;
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
    public static Item date;*/

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

    public static void init() {

        artichoke = registerItem("artichoke", new Item(createGroup().food(EDIBLE_YUCK)));
        asparagus = registerItem("asparagus", new Item(createGroup().food(EDIBLE_OK)));
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

        artichokeSeed = registerItem("artichoke_seed", new CroptopiaItem(BlockRegistry.artichokeCropBlock, createGroup()));
        asparagusSeed = registerItem("asparagus_seed", new CroptopiaItem(BlockRegistry.asparagusCropBlock, createGroup()));
        bellPepperSeed = registerItem("bellpepper_seed", new CroptopiaItem(BlockRegistry.bellPepperCropBlock, createGroup()));
        blackBeanSeed = registerItem("blackbean_seed", new CroptopiaItem(BlockRegistry.blackBeanCropBlock, createGroup()));
        blackberrySeed = registerItem("blackberry_seed", new CroptopiaItem(BlockRegistry.blackberryCropBlock, createGroup()));
        blueberrySeed = registerItem("blueberry_seed", new CroptopiaItem(BlockRegistry.blueberryCropBlock, createGroup()));
        broccoliSeed = registerItem("broccoli_seed", new CroptopiaItem(BlockRegistry.broccoliCropBlock, createGroup()));
        cabbageSeed = registerItem("cabbage_seed", new CroptopiaItem(BlockRegistry.cabbageCropBlock, createGroup()));
        cantaloupeSeed = registerItem("cantaloupe_seed", new CroptopiaItem(BlockRegistry.cantaloupeCropBlock, createGroup()));
        cauliflowerSeed = registerItem("cauliflower_seed", new CroptopiaItem(BlockRegistry.cauliflowerCropBlock, createGroup()));
        celerySeed = registerItem("celery_seed", new CroptopiaItem(BlockRegistry.celeryCropBlock, createGroup()));
        coffeeSeed = registerItem("coffee_seed", new CroptopiaItem(BlockRegistry.coffeeCropBlock, createGroup()));
        cornSeed = registerItem("corn_seed", new CroptopiaItem(BlockRegistry.cornCropBlock, createGroup()));
        cranberrySeed = registerItem("cranberry_seed", new CroptopiaItem(BlockRegistry.cranberryCropBlock, createGroup()));
        cucumberSeed = registerItem("cucumber_seed", new CroptopiaItem(BlockRegistry.cucumberCropBlock, createGroup()));
        currantSeed = registerItem("currant_seed", new CroptopiaItem(BlockRegistry.currantCropBlock, createGroup()));
        eggplantSeed = registerItem("eggplant_seed", new CroptopiaItem(BlockRegistry.eggplantCropBlock, createGroup()));
        elderberrySeed = registerItem("elderberry_seed", new CroptopiaItem(BlockRegistry.elderberryCropBlock, createGroup()));
        garlicSeed = registerItem("garlic_seed", new CroptopiaItem(BlockRegistry.garlicCropBlock, createGroup()));
        grapeSeed = registerItem("grape_seed", new CroptopiaItem(BlockRegistry.grapeCropBlock, createGroup()));
        greenBeanSeed = registerItem("greenbean_seed", new CroptopiaItem(BlockRegistry.greenBeanCropBlock, createGroup()));
        greenOnionSeed = registerItem("greenonion_seed", new CroptopiaItem(BlockRegistry.greenOnionCropBlock, createGroup()));
        honeydewSeed = registerItem("honeydew_seed", new CroptopiaItem(BlockRegistry.honeydewCropBlock, createGroup()));
        hopsSeed = registerItem("hops_seed", new CroptopiaItem(BlockRegistry.hopsCropBlock, createGroup()));
        kaleSeed = registerItem("kale_seed", new CroptopiaItem(BlockRegistry.kaleCropBlock, createGroup()));
        kiwiSeed = registerItem("kiwi_seed", new CroptopiaItem(BlockRegistry.kiwiCropBlock, createGroup()));
        leekSeed = registerItem("leek_seed", new CroptopiaItem(BlockRegistry.leekCropBlock, createGroup()));
        lettuceSeed = registerItem("lettuce_seed", new CroptopiaItem(BlockRegistry.lettuceCropBlock, createGroup()));
        oliveSeed = registerItem("olive_seed", new CroptopiaItem(BlockRegistry.oliveCropBlock, createGroup()));
        onionSeed = registerItem("onion_seed", new CroptopiaItem(BlockRegistry.onionCropBlock, createGroup()));
        peanutSeed = registerItem("peanut_seed", new CroptopiaItem(BlockRegistry.peanutCropBlock, createGroup()));
        pineappleSeed = registerItem("pineapple_seed", new CroptopiaItem(BlockRegistry.pineappleCropBlock, createGroup()));
        radishSeed = registerItem("radish_seed", new CroptopiaItem(BlockRegistry.radishCropBlock, createGroup()));
        raspberrySeed = registerItem("raspberry_seed", new CroptopiaItem(BlockRegistry.raspberryCropBlock, createGroup()));
        rhubarbSeed = registerItem("rhubarb_seed", new CroptopiaItem(BlockRegistry.rhubarbCropBlock, createGroup()));
        riceSeed = registerItem("rice_seed", new CroptopiaItem(BlockRegistry.riceCropBlock, createGroup()));
        rutabagaSeed = registerItem("rutabaga_seed", new CroptopiaItem(BlockRegistry.rutabagaCropBlock, createGroup()));
        saguaroSeed = registerItem("saguaro_seed", new CroptopiaItem(BlockRegistry.saguaroCropBlock, createGroup()));
        spinachSeed = registerItem("spinach_seed", new CroptopiaItem(BlockRegistry.spinachCropBlock, createGroup()));
        squashSeed = registerItem("sqaush_seed", new CroptopiaItem(BlockRegistry.squashCropBlock, createGroup()));
        strawberrySeed = registerItem("strawberry_seed", new CroptopiaItem(BlockRegistry.strawberryCropBlock, createGroup()));
        sweetPotatoSeed = registerItem("sweetpotato_seed", new CroptopiaItem(BlockRegistry.sweetPotatoCropBlock, createGroup()));
        tomatilloSeed = registerItem("tomatillo_seed", new CroptopiaItem(BlockRegistry.tomatilloCropBlock, createGroup()));
        tomatoSeed = registerItem("tomato_seed", new CroptopiaItem(BlockRegistry.tomatoCropBlock, createGroup()));
        turnipSeed = registerItem("turnip_seed", new CroptopiaItem(BlockRegistry.turnipCropBlock, createGroup()));
        yamSeed = registerItem("yam_seed", new CroptopiaItem(BlockRegistry.yamCropBlock, createGroup()));
        zucchiniSeed = registerItem("zucchini_seed", new CroptopiaItem(BlockRegistry.zucchiniCropBlock, createGroup()));

    }


    public static Item registerItem(String itemName, Item item) {
        Registry.register(Registry.ITEM, Croptopia.createIdentifier(itemName), item);
        return item;
    }

    public static Item.Settings createGroup() {
        return new Item.Settings().group(CROPTOPIA_ITEM_GROUP);
    }
}
