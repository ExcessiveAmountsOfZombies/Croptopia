package me.thonk.croptopia.blocks;

import me.thonk.croptopia.Croptopia;
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


    public static void init() {
        artichokeCropBlock = registerBlock("artichoke_crop", new CroptopiaCropBlock(createBlockSettings(), artichokeSeed));
        asparagusCropBlock = registerBlock("asparagus_crop", new CroptopiaCropBlock(createBlockSettings(), asparagusSeed));
        barleyCropBlock = registerBlock("barley_crop", new CroptopiaCropBlock(createBlockSettings(), barleySeed));
        basilCropBlock = registerBlock("basil_crop", new CroptopiaCropBlock(createBlockSettings(), basilSeed));
        bellPepperCropBlock = registerBlock("bellpepper_crop", new CroptopiaCropBlock(createBlockSettings(), bellPepperSeed));
        blackBeanCropBlock = registerBlock("blackbean_crop", new CroptopiaCropBlock(createBlockSettings(), blackBeanSeed));
        blackberryCropBlock = registerBlock("blackberry_crop", new CroptopiaCropBlock(createBlockSettings(), blackberrySeed));
        blueberryCropBlock = registerBlock("blueberry_crop", new CroptopiaCropBlock(createBlockSettings(), blueberrySeed));
        broccoliCropBlock = registerBlock("broccoli_crop", new CroptopiaCropBlock(createBlockSettings(), broccoliSeed));
        cabbageCropBlock = registerBlock("cabbage_crop", new CroptopiaCropBlock(createBlockSettings(), cabbageSeed));
        cantaloupeCropBlock = registerBlock("cantaloupe_crop", new CroptopiaCropBlock(createBlockSettings(), cantaloupeSeed));
        cauliflowerCropBlock = registerBlock("cauliflower_crop", new CroptopiaCropBlock(createBlockSettings(), cauliflowerSeed));
        celeryCropBlock = registerBlock("celery_crop", new CroptopiaCropBlock(createBlockSettings(), celerySeed));
        chivesCropBlock = registerBlock("chives_crop", new CroptopiaCropBlock(createBlockSettings(), chivesSeed));
        coffeeCropBlock = registerBlock("coffee_crop", new CroptopiaCropBlock(createBlockSettings(), coffeeSeed));
        cornCropBlock = registerBlock("corn_crop", new CornCropBlock(createBlockSettings(), cornSeed));
        cranberryCropBlock = registerBlock("cranberry_crop", new CroptopiaCropBlock(createBlockSettings(), cranberrySeed));
        cucumberCropBlock = registerBlock("cucumber_crop", new CroptopiaCropBlock(createBlockSettings(), cucumberSeed));
        currantCropBlock = registerBlock("currant_crop", new CroptopiaCropBlock(createBlockSettings(), currantSeed));
        eggplantCropBlock = registerBlock("eggplant_crop", new CroptopiaCropBlock(createBlockSettings(), eggplantSeed));
        elderberryCropBlock = registerBlock("elderberry_crop", new CroptopiaCropBlock(createBlockSettings(), elderberrySeed));
        garlicCropBlock = registerBlock("garlic_crop", new CroptopiaCropBlock(createBlockSettings(), garlicSeed));
        gingerCropBlock = registerBlock("ginger_crop", new CroptopiaCropBlock(createBlockSettings(), gingerSeed));
        grapeCropBlock = registerBlock("grape_crop", new CroptopiaCropBlock(createBlockSettings(), grapeSeed));
        greenBeanCropBlock = registerBlock("greenbean_crop", new CroptopiaCropBlock(createBlockSettings(), greenBeanSeed));
        greenOnionCropBlock = registerBlock("greenonion_crop", new CroptopiaCropBlock(createBlockSettings(), greenOnionSeed));
        honeydewCropBlock = registerBlock("honeydew_crop", new CroptopiaCropBlock(createBlockSettings(), honeydewSeed));
        hopsCropBlock = registerBlock("hops_crop", new CroptopiaCropBlock(createBlockSettings(), hopsSeed));
        kaleCropBlock = registerBlock("kale_crop", new CroptopiaCropBlock(createBlockSettings(), kaleSeed));
        kiwiCropBlock = registerBlock("kiwi_crop", new CroptopiaCropBlock(createBlockSettings(), kiwiSeed));
        leekCropBlock = registerBlock("leek_crop", new CroptopiaCropBlock(createBlockSettings(), leekSeed));
        lettuceCropBlock = registerBlock("lettuce_crop", new CroptopiaCropBlock(createBlockSettings(), lettuceSeed));
        mustardCropBlock = registerBlock("mustard_crop", new CroptopiaCropBlock(createBlockSettings(), mustardSeed));
        oatCropBlock = registerBlock("oat_crop", new CroptopiaCropBlock(createBlockSettings(), oatSeed));
        oliveCropBlock = registerBlock("olive_crop", new CroptopiaCropBlock(createBlockSettings(), oliveSeed));
        onionCropBlock = registerBlock("onion_crop", new CroptopiaCropBlock(createBlockSettings(), onionSeed));
        paprikaCropBlock = registerBlock("paprika_crop", new CroptopiaCropBlock(createBlockSettings(), paprikaSeed));
        peanutCropBlock = registerBlock("peanut_crop", new CroptopiaCropBlock(createBlockSettings(), peanutSeed));
        pepperCropBlock = registerBlock("pepper_crop", new CroptopiaCropBlock(createBlockSettings(), pepperSeed));
        pineappleCropBlock = registerBlock("pineapple_crop", new CroptopiaCropBlock(createBlockSettings(), pineappleSeed));
        radishCropBlock = registerBlock("radish_crop", new CroptopiaCropBlock(createBlockSettings(), radishSeed));
        raspberryCropBlock = registerBlock("raspberry_crop", new CroptopiaCropBlock(createBlockSettings(), raspberrySeed));
        rhubarbCropBlock = registerBlock("rhubarb_crop", new CroptopiaCropBlock(createBlockSettings(), rhubarbSeed));
        riceCropBlock = registerBlock("rice_crop", new CroptopiaCropBlock(createBlockSettings(), riceSeed));
        rutabagaCropBlock = registerBlock("rutabaga_crop", new CroptopiaCropBlock(createBlockSettings(), rutabagaSeed));
        saguaroCropBlock = registerBlock("saguaro_crop", new CroptopiaCropBlock(createBlockSettings(), saguaroSeed));
        soybeanCropBlock = registerBlock("soybean_crop", new CroptopiaCropBlock(createBlockSettings(), soybeanSeed));
        spinachCropBlock = registerBlock("spinach_crop", new CroptopiaCropBlock(createBlockSettings(), spinachSeed));
        squashCropBlock = registerBlock("squash_crop", new CroptopiaCropBlock(createBlockSettings(), squashSeed));
        strawberryCropBlock = registerBlock("strawberry_crop", new CroptopiaCropBlock(createBlockSettings(), strawberrySeed));
        sweetPotatoCropBlock = registerBlock("sweetpotato_crop", new CroptopiaCropBlock(createBlockSettings(), sweetPotatoSeed));
        tomatilloCropBlock = registerBlock("tomatillo_crop", new CroptopiaCropBlock(createBlockSettings(), tomatilloSeed));
        tomatoCropBlock = registerBlock("tomato_crop", new CroptopiaCropBlock(createBlockSettings(), tomatoSeed));
        turmericCropBlock = registerBlock("turmeric_crop", new CroptopiaCropBlock(createBlockSettings(), turmericSeed));
        turnipCropBlock = registerBlock("turnip_crop", new CroptopiaCropBlock(createBlockSettings(), turnipSeed));
        yamCropBlock = registerBlock("yam_crop", new CroptopiaCropBlock(createBlockSettings(), yamSeed));
        zucchiniCropBlock = registerBlock("zucchini_crop", new CroptopiaCropBlock(createBlockSettings(), zucchiniSeed));
    }

    public static Block registerBlock(String itemName, Block item) {
        cropBlocks.add(item);
        Registry.register(Registry.BLOCK, Croptopia.createIdentifier(itemName), item);
        return item;
    }

    public static FabricBlockSettings createBlockSettings() {
        return FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP);
    }

}
