package me.thonk.croptopia.registry;

import me.thonk.common.BlockNames;
import me.thonk.croptopia.blocks.CroptopiaCropBlock;
import me.thonk.croptopia.blocks.CroptopiaSaplingBlock;
import me.thonk.croptopia.blocks.TallCropBlock;
import me.thonk.croptopia.generator.CroptopiaSaplingGenerator;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;

import static me.thonk.croptopia.Croptopia.*;

public class BlockRegistry {
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

        registerBlock(BlockNames.CINNAMON_SAPLING, cinnamonSaplingBlock);

    }
}
