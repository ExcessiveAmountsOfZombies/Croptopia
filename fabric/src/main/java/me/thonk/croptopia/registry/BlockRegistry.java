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

    public static void init() {
        registerBlock(BlockNames.SALT_ORE, salt);

    }
}
