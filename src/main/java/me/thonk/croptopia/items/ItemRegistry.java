package me.thonk.croptopia.items;

import me.thonk.croptopia.Croptopia;
import me.thonk.croptopia.FoodRegistry;
import me.thonk.croptopia.blocks.BlockRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

import static me.thonk.croptopia.Croptopia.CROPTOPIA_ITEM_GROUP;
import static me.thonk.croptopia.FoodRegistry.*;

public class ItemRegistry {

    public static Item onion;
    public static Item corn;
    public static Item hops;
    public static Item rice;
    public static Item lettuce;
    public static Item cucumber;
    public static Item grape;
    public static Item strawberry;
    public static Item tomato;
    public static Item garlic;
    public static Item peanut;


    public static Item onionSeed;
    public static Item cornSeed;
    public static Item hopsSeed;
    public static Item riceSeed;
    public static Item lettuceSeed;
    public static Item cucumberSeed;
    public static Item grapeSeed;
    public static Item strawberrySeed;
    public static Item tomatoSeed;
    public static Item garlicSeed;
    public static Item peanutSeed;



    public static void init() {
        onion = registerItem("onion", new Item(createGroup().food(ONION_FOOD)));
        corn = registerItem("corn", new Item(createGroup().food(CORN_FOOD)));
        hops = registerItem("hops", new Item(createGroup()));
        rice = registerItem("rice", new Item(createGroup().food(RICE_FOOD)));
        lettuce = registerItem("lettuce", new Item(createGroup().food(LETTUCE_FOOD)));
        cucumber = registerItem("cucumber", new Item(createGroup().food(CUCUMBER_FOOD)));
        grape = registerItem("grape", new Item(createGroup().food(GRAPE_FOOD)));
        strawberry = registerItem("strawberry", new Item(createGroup().food(STRAWBERRY_FOOD)));
        tomato = registerItem("tomato", new Item(createGroup().food(TOMATO_FOOD)));
        garlic = registerItem("garlic", new Item(createGroup().food(GARLIC_FOOD)));
        peanut = registerItem("peanut", new Item(createGroup().food(PEANUT_FOOD)));



        onionSeed = registerItem("onion_seed", new CroptopiaItem(BlockRegistry.onionCropBlock, createGroup()));
        cornSeed = registerItem("corn_seed", new CroptopiaItem(BlockRegistry.cornCropBlock, createGroup()));
        hopsSeed = registerItem("hops_seed", new CroptopiaItem(BlockRegistry.hopsCropBlock, createGroup()));
        riceSeed = registerItem("rice_seed", new CroptopiaItem(BlockRegistry.riceCropBlock, createGroup()));
        lettuceSeed = registerItem("lettuce_seed", new CroptopiaItem(BlockRegistry.lettuceCropBlock, createGroup()));
        cucumberSeed = registerItem("cucumber_seed", new CroptopiaItem(BlockRegistry.cucumberCropBlock, createGroup()));
        grapeSeed = registerItem("grape_seed", new CroptopiaItem(BlockRegistry.grapeCropBlock, createGroup()));
        strawberrySeed = registerItem("strawberry_seed", new CroptopiaItem(BlockRegistry.strawberryCropBlock, createGroup()));
        tomatoSeed = registerItem("tomato_seed", new CroptopiaItem(BlockRegistry.tomatoCropBlock, createGroup()));
        garlicSeed = registerItem("garlic_seed", new CroptopiaItem(BlockRegistry.garlicCropBlock, createGroup()));
        peanutSeed = registerItem("peanut_seed", new CroptopiaItem(BlockRegistry.peanutCropBlock, createGroup()));

    }


    public static Item registerItem(String itemName, Item item) {
        Registry.register(Registry.ITEM, Croptopia.createIdentifier(itemName), item);
        return item;
    }

    public static Item.Settings createGroup() {
        return new Item.Settings().group(CROPTOPIA_ITEM_GROUP);
    }
}
