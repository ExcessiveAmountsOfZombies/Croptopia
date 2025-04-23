package com.epherical.croptopia;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;

import java.util.function.Consumer;

public class ClientFunctions {

    public BlockColor registerLeafColors() {
        return (state, world, pos, tintIndex) ->
                world != null && pos != null
                        ? BiomeColors.getAverageFoliageColor(world, pos)
                        : FoliageColor.getDefaultColor();
    }

    public Block[] leaves() {
        return CroptopiaMod.leafBlocks.toArray(Block[]::new);
    }

    public void registerBlockLayers(Consumer<Block> blockConsumer) {
        CroptopiaMod.cropBlocks.forEach(blockConsumer);
    }
}
