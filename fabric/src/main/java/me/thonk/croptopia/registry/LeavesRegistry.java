package me.thonk.croptopia.registry;

import net.minecraft.block.Block;

import static me.thonk.croptopia.Croptopia.createLeavesBlock;
import static me.thonk.croptopia.Croptopia.registerBlock;


public class LeavesRegistry {

    public static Block appleCrop = createLeavesBlock();
    public static Block bananaCrop = createLeavesBlock();
    public static Block orangeCrop = createLeavesBlock();
    public static Block persimmonCrop = createLeavesBlock();
    public static Block plumCrop = createLeavesBlock();
    public static Block cherryCrop = createLeavesBlock();
    public static Block lemonCrop = createLeavesBlock();
    public static Block grapefruitCrop = createLeavesBlock();
    public static Block kumquatCrop = createLeavesBlock();
    public static Block peachCrop = createLeavesBlock();
    public static Block coconutCrop = createLeavesBlock();
    public static Block nutmegCrop = createLeavesBlock();
    public static Block figCrop = createLeavesBlock();
    public static Block nectarineCrop = createLeavesBlock();
    public static Block mangoCrop = createLeavesBlock();
    public static Block dragonFruitCrop = createLeavesBlock();
    public static Block starFruitCrop = createLeavesBlock();
    public static Block avocadoCrop = createLeavesBlock();
    public static Block apricotCrop = createLeavesBlock();
    public static Block pearCrop = createLeavesBlock();
    public static Block limeCrop = createLeavesBlock();
    public static Block dateCrop = createLeavesBlock();
    public static Block almondCrop = createLeavesBlock();
    public static Block cashewCrop = createLeavesBlock();
    public static Block pecanCrop = createLeavesBlock();
    public static Block walnutCrop = createLeavesBlock();

    public static void init() {
        registerBlock("apple_crop", appleCrop);
        registerBlock("banana_crop", bananaCrop);
        registerBlock("orange_crop", orangeCrop);
        registerBlock("persimmon_crop", persimmonCrop);
        registerBlock("plum_crop", plumCrop);
        registerBlock("cherry_crop", cherryCrop);
        registerBlock("lemon_crop", lemonCrop);
        registerBlock("grapefruit_crop", grapefruitCrop);
        registerBlock("kumquat_crop", kumquatCrop);
        registerBlock("peach_crop", peachCrop);
        registerBlock("coconut_crop", coconutCrop);
        registerBlock("nutmeg_crop", nutmegCrop);
        registerBlock("fig_crop", figCrop);
        registerBlock("nectarine_crop", nectarineCrop);
        registerBlock("mango_crop", mangoCrop);
        registerBlock("dragonfruit_crop", dragonFruitCrop);
        registerBlock("starfruit_crop", starFruitCrop);
        registerBlock("avocado_crop", avocadoCrop);
        registerBlock("apricot_crop", apricotCrop);
        registerBlock("pear_crop", pearCrop);
        registerBlock("lime_crop", limeCrop);
        registerBlock("date_crop", dateCrop);
        registerBlock("almond_crop", almondCrop);
        registerBlock("cashew_crop", cashewCrop);
        registerBlock("pecan_crop", pecanCrop);
        registerBlock("walnut_crop", walnutCrop);
    }

}
