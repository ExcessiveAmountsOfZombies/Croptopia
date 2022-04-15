package me.thonk.croptopia.registry;

import me.thonk.common.BlockNames;
import me.thonk.croptopia.blocks.CroptopiaCropBlock;
import me.thonk.croptopia.blocks.CroptopiaSaplingBlock;
import me.thonk.croptopia.blocks.TallCropBlock;
import me.thonk.croptopia.generator.CroptopiaSaplingGenerator;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.sound.BlockSoundGroup;

import static me.thonk.croptopia.Croptopia.*;

public class BlockRegistry {
    public static Block basilCropBlock = new CroptopiaCropBlock(createCropSettings());
    public static Block gingerCropBlock = new CroptopiaCropBlock(createCropSettings());
    public static Block mustardCropBlock = new CroptopiaCropBlock(createCropSettings());
    public static Block chilePepperCropBlock = new CroptopiaCropBlock(createCropSettings());
    public static Block turmericCropBlock = new CroptopiaCropBlock(createCropSettings());
    public static Block vanillaCropBlock = new CroptopiaCropBlock(createCropSettings());
    public static Block pepperCropBlock = new CroptopiaCropBlock(createCropSettings());
    public static Block teaCropBlock = new CroptopiaCropBlock(createCropSettings());


    // real block
    public static Block salt = new Block(FabricBlockSettings.of(Material.AGGREGATE).strength(0.5F).sounds(BlockSoundGroup.SAND));

    public static Block cinnamonLog = new PillarBlock(FabricBlockSettings.of(Material.WOOD, MapColor.BROWN).sounds(BlockSoundGroup.WOOD).strength(2.0F));
    public static Block strippedCinnamonLog = new PillarBlock(FabricBlockSettings.of(Material.WOOD, MapColor.BROWN).sounds(BlockSoundGroup.WOOD).strength(2.0F));
    public static Block cinnamonWood = new PillarBlock(FabricBlockSettings.of(Material.WOOD, MapColor.BROWN).sounds(BlockSoundGroup.WOOD).strength(2.0F));
    public static Block strippedCinnamonWood = new PillarBlock(FabricBlockSettings.of(Material.WOOD, MapColor.BROWN).sounds(BlockSoundGroup.WOOD).strength(2.0F));

    public static Block cinnamonSaplingBlock = new CroptopiaSaplingBlock(new CroptopiaSaplingGenerator(() -> GeneratorRegistry.CINNAMON_TREE), Content.createSaplingSettings());

    public static void init() {
        registerBlock(BlockNames.SALT_ORE, salt);
        registerBlock(BlockNames.CINNAMON_LOG, cinnamonLog);
        registerBlock(BlockNames.STRIPPED_CINNAMON_LOG, strippedCinnamonLog);
        registerBlock(BlockNames.CINNAMON_WOOD, cinnamonWood);
        registerBlock(BlockNames.STRIPPED_CINNAMON_WOOD, strippedCinnamonWood);


        registerBlock(BlockNames.BASIL_CROP, basilCropBlock);
        registerBlock(BlockNames.GINGER_CROP, gingerCropBlock);
        registerBlock(BlockNames.MUSTARD_CROP, mustardCropBlock);
        registerBlock(BlockNames.CHILE_PEPPER_CROP, chilePepperCropBlock);
        registerBlock(BlockNames.TURMERIC_CROP, turmericCropBlock);
        registerBlock(BlockNames.VANILLA_CROP, vanillaCropBlock);
        registerBlock(BlockNames.PEPPER_CROP, pepperCropBlock);
        registerBlock(BlockNames.TEA_CROP, teaCropBlock);

        registerBlock(BlockNames.CINNAMON_SAPLING, cinnamonSaplingBlock);

    }
}
