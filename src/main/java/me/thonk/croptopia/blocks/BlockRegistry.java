package me.thonk.croptopia.blocks;

import me.thonk.croptopia.Croptopia;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;

public class BlockRegistry {

    public static ArrayList<Block> cropBlocks = new ArrayList<>();

    public static Block onionCropBlock;
    public static Block cornCropBlock;
    public static Block hopsCropBlock;
    public static Block riceCropBlock;
    public static Block lettuceCropBlock;
    public static Block cucumberCropBlock;
    public static Block grapeCropBlock;
    public static Block strawberryCropBlock;
    public static Block tomatoCropBlock;
    public static Block garlicCropBlock;
    public static Block peanutCropBlock;


    public static void init() {
        onionCropBlock = registerBlock("onion_crop", new CroptopiaCropBlock(createBlockSettings(), "onion_seed"));
        cornCropBlock = registerBlock("corn_crop", new CroptopiaCropBlock(createBlockSettings(), "corn_seed"));
        hopsCropBlock = registerBlock("hops_crop", new CroptopiaCropBlock(createBlockSettings(), "hops_seed"));
        riceCropBlock = registerBlock("rice_crop", new CroptopiaCropBlock(createBlockSettings(), "rice_seed"));
        lettuceCropBlock = registerBlock("lettuce_crop", new CroptopiaCropBlock(createBlockSettings(), "lettuce_seed"));
        cucumberCropBlock = registerBlock("cucumber_crop", new CroptopiaCropBlock(createBlockSettings(), "cucumber_seed"));
        grapeCropBlock = registerBlock("grape_crop", new CroptopiaCropBlock(createBlockSettings(), "grape_seed"));
        strawberryCropBlock = registerBlock("strawberry_crop", new CroptopiaCropBlock(createBlockSettings(), "strawberry_seed"));
        tomatoCropBlock = registerBlock("tomato_crop", new CroptopiaCropBlock(createBlockSettings(), "tomato_seed"));
        garlicCropBlock = registerBlock("garlic_crop", new CroptopiaCropBlock(createBlockSettings(), "garlic_seed"));
        peanutCropBlock = registerBlock("peanut_crop", new CroptopiaCropBlock(createBlockSettings(), "peanut_seed"));
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
