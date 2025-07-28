package com.epherical.croptopia.client;

import com.epherical.croptopia.CroptopiaMod;
import com.epherical.croptopia.register.helpers.TreeCrop;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Consumer;

public class ClientFunctions {

    public BlockColor registerLeafColors() {
        return (state, world, pos, tintIndex) ->
                world != null && pos != null
                        ? BiomeColors.getAverageFoliageColor(world, pos)
                        : FoliageColor.getDefaultColor();
    }

    public ItemColor registerItemColors(BlockColors colors) {
        return (stack, tintIndex) -> {
            BlockState blockstate = ((BlockItem)stack.getItem()).getBlock().defaultBlockState();
            return colors.getColor(blockstate, null, null, tintIndex);
        };
    }

    public Block[] leaves() {
        return CroptopiaMod.leafBlocks.toArray(Block[]::new);
    }

    public Item[] items() {
        return TreeCrop.TREE_CROPS.stream().map(TreeCrop::getLeavesItem).toArray(Item[]::new);
    }

    public void registerBlockLayers(Consumer<Block> blockConsumer) {
        CroptopiaMod.cropBlocks.forEach(blockConsumer);
    }
}
