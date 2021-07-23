package me.thonk.croptopia.registry;

import me.thonk.common.BlockNames;
import net.minecraft.world.level.block.Block;

import static me.thonk.croptopia.CroptopiaForge.*;


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
    public static Block cinnamonLeaves = createRegularLeavesBlock();

    public static void init() {
        registerBlock(BlockNames.APPLE_CROP, appleCrop);
        registerBlock(BlockNames.BANANA_CROP, bananaCrop);
        registerBlock(BlockNames.ORANGE_CROP, orangeCrop);
        registerBlock(BlockNames.PERSIMMON_CROP, persimmonCrop);
        registerBlock(BlockNames.PLUM_CROP, plumCrop);
        registerBlock(BlockNames.CHERRY_CROP, cherryCrop);
        registerBlock(BlockNames.LEMON_CROP, lemonCrop);
        registerBlock(BlockNames.GRAPEFRUIT_CROP, grapefruitCrop);
        registerBlock(BlockNames.KUMQUAT_CROP, kumquatCrop);
        registerBlock(BlockNames.PEACH_CROP, peachCrop);
        registerBlock(BlockNames.COCONUT_CROP, coconutCrop);
        registerBlock(BlockNames.NUTMEG_CROP, nutmegCrop);
        registerBlock(BlockNames.FIG_CROP, figCrop);
        registerBlock(BlockNames.NECTARINE_CROP, nectarineCrop);
        registerBlock(BlockNames.MANGO_CROP, mangoCrop);
        registerBlock(BlockNames.DRAGONFRUIT_CROP, dragonFruitCrop);
        registerBlock(BlockNames.STARFRUIT_CROP, starFruitCrop);
        registerBlock(BlockNames.AVOCADO_CROP, avocadoCrop);
        registerBlock(BlockNames.APRICOT_CROP, apricotCrop);
        registerBlock(BlockNames.PEAR_CROP, pearCrop);
        registerBlock(BlockNames.LIME_CROP, limeCrop);
        registerBlock(BlockNames.DATE_CROP, dateCrop);
        registerBlock(BlockNames.ALMOND_CROP, almondCrop);
        registerBlock(BlockNames.CASHEW_CROP, cashewCrop);
        registerBlock(BlockNames.PECAN_CROP, pecanCrop);
        registerBlock(BlockNames.WALNUT_CROP, walnutCrop);
        registerBlock(BlockNames.CINNAMON_LEAVES, cinnamonLeaves);
    }

}
