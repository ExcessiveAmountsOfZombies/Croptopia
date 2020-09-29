package me.thonk.croptopia.blocks;

import me.thonk.croptopia.Croptopia;
import me.thonk.croptopia.generator.CroptopiaSaplingGenerator;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;

import static me.thonk.croptopia.items.ItemRegistry.*;

public class BlockRegistry {

    public static ArrayList<Block> cropBlocks = new ArrayList<>();

    public static Block artichokeCropBlock;
    public static Block asparagusCropBlock;
    public static Block barleyCropBlock;
    public static Block basilCropBlock;
    public static Block bellPepperCropBlock;
    public static Block blackBeanCropBlock;
    public static Block blackberryCropBlock;
    public static Block blueberryCropBlock;
    public static Block broccoliCropBlock;
    public static Block cabbageCropBlock;
    public static Block cantaloupeCropBlock;
    public static Block cauliflowerCropBlock;
    public static Block celeryCropBlock;
    public static Block chivesCropBlock;
    public static Block coffeeCropBlock;
    public static Block cornCropBlock;
    public static Block cranberryCropBlock;
    public static Block cucumberCropBlock;
    public static Block currantCropBlock;
    public static Block eggplantCropBlock;
    public static Block elderberryCropBlock;
    public static Block garlicCropBlock;
    public static Block gingerCropBlock;
    public static Block grapeCropBlock;
    public static Block greenBeanCropBlock;
    public static Block greenOnionCropBlock;
    public static Block honeydewCropBlock;
    public static Block hopsCropBlock;
    public static Block kaleCropBlock;
    public static Block kiwiCropBlock;
    public static Block leekCropBlock;
    public static Block lettuceCropBlock;
    public static Block mustardCropBlock;
    public static Block oatCropBlock;
    public static Block oliveCropBlock;
    public static Block onionCropBlock;
    public static Block paprikaCropBlock;
    public static Block peanutCropBlock;
    public static Block pepperCropBlock;
    public static Block pineappleCropBlock;
    public static Block radishCropBlock;
    public static Block raspberryCropBlock;
    public static Block rhubarbCropBlock;
    public static Block riceCropBlock;
    public static Block rutabagaCropBlock;
    public static Block saguaroCropBlock;
    public static Block soybeanCropBlock;
    public static Block spinachCropBlock;
    public static Block squashCropBlock;
    public static Block strawberryCropBlock;
    public static Block sweetPotatoCropBlock;
    public static Block tomatilloCropBlock;
    public static Block tomatoCropBlock;
    public static Block turmericCropBlock;
    public static Block turnipCropBlock;
    public static Block yamCropBlock;
    public static Block zucchiniCropBlock;



    // tree
    public static Block vanillaCropBlock;
    // real block
    public static Block salt;


    public static Block appleSaplingBlock;
    public static Block bananaSaplingBlock;
    public static Block orangeSaplingBlock;
    public static Block persimmonSaplingBlock;
    public static Block plumSaplingBlock;
    public static Block cherrySaplingBlock;
    public static Block lemonSaplingBlock;
    public static Block grapefruitSaplingBlock;
    public static Block kumquatSaplingBlock;
    public static Block peachSaplingBlock;
    public static Block coconutSaplingBlock;
    public static Block nutmegSaplingBlock;
    public static Block figSaplingBlock;
    public static Block nectarineSaplingBlock;
    public static Block mangoSaplingBlock;
    public static Block dragonFruitSaplingBlock;
    public static Block starFruitSaplingBlock;
    public static Block avocadoSaplingBlock;
    public static Block apricotSaplingBlock;
    public static Block pearSaplingBlock;
    public static Block limeSaplingBlock;
    public static Block dateSaplingBlock;


    public static void init() {
        appleSaplingBlock = registerBlock("apple_sapling", new CroptopiaSaplingBlock(new CroptopiaSaplingGenerator(), createSaplingSettings()));
        bananaSaplingBlock = registerBlock("banana_sapling", new CroptopiaSaplingBlock(new CroptopiaSaplingGenerator(), createSaplingSettings()));
        orangeSaplingBlock = registerBlock("orange_sapling", new CroptopiaSaplingBlock(new CroptopiaSaplingGenerator(), createSaplingSettings()));
        persimmonSaplingBlock = registerBlock("persimmon_sapling", new CroptopiaSaplingBlock(new CroptopiaSaplingGenerator(), createSaplingSettings()));
        plumSaplingBlock = registerBlock("plum_sapling", new CroptopiaSaplingBlock(new CroptopiaSaplingGenerator(), createSaplingSettings()));
        cherrySaplingBlock = registerBlock("cherry_sapling", new CroptopiaSaplingBlock(new CroptopiaSaplingGenerator(), createSaplingSettings()));
        lemonSaplingBlock = registerBlock("lemon_sapling", new CroptopiaSaplingBlock(new CroptopiaSaplingGenerator(), createSaplingSettings()));
        grapefruitSaplingBlock = registerBlock("grapefruit_sapling", new CroptopiaSaplingBlock(new CroptopiaSaplingGenerator(), createSaplingSettings()));
        kumquatSaplingBlock = registerBlock("kumquat_sapling", new CroptopiaSaplingBlock(new CroptopiaSaplingGenerator(), createSaplingSettings()));
        peachSaplingBlock = registerBlock("peach_sapling", new CroptopiaSaplingBlock(new CroptopiaSaplingGenerator(), createSaplingSettings()));
        coconutSaplingBlock = registerBlock("coconut_sapling", new CroptopiaSaplingBlock(new CroptopiaSaplingGenerator(), createSaplingSettings()));
        nutmegSaplingBlock = registerBlock("nutmeg_sapling", new CroptopiaSaplingBlock(new CroptopiaSaplingGenerator(), createSaplingSettings()));
        figSaplingBlock = registerBlock("fig_sapling", new CroptopiaSaplingBlock(new CroptopiaSaplingGenerator(), createSaplingSettings()));
        nectarineSaplingBlock = registerBlock("nectarine_sapling", new CroptopiaSaplingBlock(new CroptopiaSaplingGenerator(), createSaplingSettings()));
        mangoSaplingBlock = registerBlock("mango_sapling", new CroptopiaSaplingBlock(new CroptopiaSaplingGenerator(), createSaplingSettings()));
        dragonFruitSaplingBlock = registerBlock("dragonfruit_sapling", new CroptopiaSaplingBlock(new CroptopiaSaplingGenerator(), createSaplingSettings()));
        starFruitSaplingBlock = registerBlock("starfruit_sapling", new CroptopiaSaplingBlock(new CroptopiaSaplingGenerator(), createSaplingSettings()));
        avocadoSaplingBlock = registerBlock("avocado_sapling", new CroptopiaSaplingBlock(new CroptopiaSaplingGenerator(), createSaplingSettings()));
        apricotSaplingBlock = registerBlock("apricot_sapling", new CroptopiaSaplingBlock(new CroptopiaSaplingGenerator(), createSaplingSettings()));
        pearSaplingBlock = registerBlock("pear_sapling", new CroptopiaSaplingBlock(new CroptopiaSaplingGenerator(), createSaplingSettings()));
        limeSaplingBlock = registerBlock("lime_sapling", new CroptopiaSaplingBlock(new CroptopiaSaplingGenerator(), createSaplingSettings()));
        dateSaplingBlock = registerBlock("date_sapling", new CroptopiaSaplingBlock(new CroptopiaSaplingGenerator(), createSaplingSettings()));




        artichokeCropBlock = registerBlock("artichoke_crop", new CroptopiaCropBlock(createCropSettings(), artichokeSeed));
        asparagusCropBlock = registerBlock("asparagus_crop", new CroptopiaCropBlock(createCropSettings(), asparagusSeed));
        barleyCropBlock = registerBlock("barley_crop", new CroptopiaCropBlock(createCropSettings(), barleySeed));
        basilCropBlock = registerBlock("basil_crop", new CroptopiaCropBlock(createCropSettings(), basilSeed));
        bellPepperCropBlock = registerBlock("bellpepper_crop", new CroptopiaCropBlock(createCropSettings(), bellPepperSeed));
        blackBeanCropBlock = registerBlock("blackbean_crop", new CroptopiaCropBlock(createCropSettings(), blackBeanSeed));
        blackberryCropBlock = registerBlock("blackberry_crop", new CroptopiaCropBlock(createCropSettings(), blackberrySeed));
        blueberryCropBlock = registerBlock("blueberry_crop", new CroptopiaCropBlock(createCropSettings(), blueberrySeed));
        broccoliCropBlock = registerBlock("broccoli_crop", new CroptopiaCropBlock(createCropSettings(), broccoliSeed));
        cabbageCropBlock = registerBlock("cabbage_crop", new CroptopiaCropBlock(createCropSettings(), cabbageSeed));
        cantaloupeCropBlock = registerBlock("cantaloupe_crop", new CroptopiaCropBlock(createCropSettings(), cantaloupeSeed));
        cauliflowerCropBlock = registerBlock("cauliflower_crop", new CroptopiaCropBlock(createCropSettings(), cauliflowerSeed));
        celeryCropBlock = registerBlock("celery_crop", new CroptopiaCropBlock(createCropSettings(), celerySeed));
        chivesCropBlock = registerBlock("chives_crop", new CroptopiaCropBlock(createCropSettings(), chivesSeed));
        coffeeCropBlock = registerBlock("coffee_crop", new CroptopiaCropBlock(createCropSettings(), coffeeSeed));
        cornCropBlock = registerBlock("corn_crop", new CornCropBlock(createCropSettings(), cornSeed));
        cranberryCropBlock = registerBlock("cranberry_crop", new CroptopiaCropBlock(createCropSettings(), cranberrySeed));
        cucumberCropBlock = registerBlock("cucumber_crop", new CroptopiaCropBlock(createCropSettings(), cucumberSeed));
        currantCropBlock = registerBlock("currant_crop", new CroptopiaCropBlock(createCropSettings(), currantSeed));
        eggplantCropBlock = registerBlock("eggplant_crop", new CroptopiaCropBlock(createCropSettings(), eggplantSeed));
        elderberryCropBlock = registerBlock("elderberry_crop", new CroptopiaCropBlock(createCropSettings(), elderberrySeed));
        garlicCropBlock = registerBlock("garlic_crop", new CroptopiaCropBlock(createCropSettings(), garlicSeed));
        gingerCropBlock = registerBlock("ginger_crop", new CroptopiaCropBlock(createCropSettings(), gingerSeed));
        grapeCropBlock = registerBlock("grape_crop", new CroptopiaCropBlock(createCropSettings(), grapeSeed));
        greenBeanCropBlock = registerBlock("greenbean_crop", new CroptopiaCropBlock(createCropSettings(), greenBeanSeed));
        greenOnionCropBlock = registerBlock("greenonion_crop", new CroptopiaCropBlock(createCropSettings(), greenOnionSeed));
        honeydewCropBlock = registerBlock("honeydew_crop", new CroptopiaCropBlock(createCropSettings(), honeydewSeed));
        hopsCropBlock = registerBlock("hops_crop", new CroptopiaCropBlock(createCropSettings(), hopsSeed));
        kaleCropBlock = registerBlock("kale_crop", new CroptopiaCropBlock(createCropSettings(), kaleSeed));
        kiwiCropBlock = registerBlock("kiwi_crop", new CroptopiaCropBlock(createCropSettings(), kiwiSeed));
        leekCropBlock = registerBlock("leek_crop", new CroptopiaCropBlock(createCropSettings(), leekSeed));
        lettuceCropBlock = registerBlock("lettuce_crop", new CroptopiaCropBlock(createCropSettings(), lettuceSeed));
        mustardCropBlock = registerBlock("mustard_crop", new CroptopiaCropBlock(createCropSettings(), mustardSeed));
        oatCropBlock = registerBlock("oat_crop", new CroptopiaCropBlock(createCropSettings(), oatSeed));
        oliveCropBlock = registerBlock("olive_crop", new CroptopiaCropBlock(createCropSettings(), oliveSeed));
        onionCropBlock = registerBlock("onion_crop", new CroptopiaCropBlock(createCropSettings(), onionSeed));
        paprikaCropBlock = registerBlock("paprika_crop", new CroptopiaCropBlock(createCropSettings(), paprikaSeed));
        peanutCropBlock = registerBlock("peanut_crop", new CroptopiaCropBlock(createCropSettings(), peanutSeed));
        pepperCropBlock = registerBlock("pepper_crop", new CroptopiaCropBlock(createCropSettings(), pepperSeed));
        pineappleCropBlock = registerBlock("pineapple_crop", new CroptopiaCropBlock(createCropSettings(), pineappleSeed));
        radishCropBlock = registerBlock("radish_crop", new CroptopiaCropBlock(createCropSettings(), radishSeed));
        raspberryCropBlock = registerBlock("raspberry_crop", new CroptopiaCropBlock(createCropSettings(), raspberrySeed));
        rhubarbCropBlock = registerBlock("rhubarb_crop", new CroptopiaCropBlock(createCropSettings(), rhubarbSeed));
        riceCropBlock = registerBlock("rice_crop", new CroptopiaCropBlock(createCropSettings(), riceSeed));
        rutabagaCropBlock = registerBlock("rutabaga_crop", new CroptopiaCropBlock(createCropSettings(), rutabagaSeed));
        saguaroCropBlock = registerBlock("saguaro_crop", new CroptopiaCropBlock(createCropSettings(), saguaroSeed));
        soybeanCropBlock = registerBlock("soybean_crop", new CroptopiaCropBlock(createCropSettings(), soybeanSeed));
        spinachCropBlock = registerBlock("spinach_crop", new CroptopiaCropBlock(createCropSettings(), spinachSeed));
        squashCropBlock = registerBlock("squash_crop", new CroptopiaCropBlock(createCropSettings(), squashSeed));
        strawberryCropBlock = registerBlock("strawberry_crop", new CroptopiaCropBlock(createCropSettings(), strawberrySeed));
        sweetPotatoCropBlock = registerBlock("sweetpotato_crop", new CroptopiaCropBlock(createCropSettings(), sweetPotatoSeed));
        tomatilloCropBlock = registerBlock("tomatillo_crop", new CroptopiaCropBlock(createCropSettings(), tomatilloSeed));
        tomatoCropBlock = registerBlock("tomato_crop", new CroptopiaCropBlock(createCropSettings(), tomatoSeed));
        turmericCropBlock = registerBlock("turmeric_crop", new CroptopiaCropBlock(createCropSettings(), turmericSeed));
        turnipCropBlock = registerBlock("turnip_crop", new CroptopiaCropBlock(createCropSettings(), turnipSeed));
        yamCropBlock = registerBlock("yam_crop", new CroptopiaCropBlock(createCropSettings(), yamSeed));
        zucchiniCropBlock = registerBlock("zucchini_crop", new CroptopiaCropBlock(createCropSettings(), zucchiniSeed));
    }

    public static Block registerBlock(String blockName, Block item) {
        cropBlocks.add(item);
        System.out.println("\"" + blockName + "\",");
        Registry.register(Registry.BLOCK, Croptopia.createIdentifier(blockName), item);
        return item;
    }

    public static FabricBlockSettings createCropSettings() {
        return FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP);
    }

    public static FabricBlockSettings createSaplingSettings() {
        return FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS);
    }


}
