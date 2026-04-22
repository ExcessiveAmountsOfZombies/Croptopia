package com.epherical.croptopia;

import com.epherical.croptopia.register.helpers.Tree;
import com.epherical.croptopia.register.helpers.TreeCrop;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;

import java.util.function.Consumer;
import java.util.stream.Stream;

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

    public Item[] leavesItem() {
        return Stream.concat(
                TreeCrop.TREE_CROPS.stream().map(TreeCrop::getLeavesItem),
                Tree.copy().stream().map(Tree::getLeavesItem)
        ).toArray(Item[]::new);
    }

    public void registerBlockLayers(Consumer<Block> blockConsumer) {
        CroptopiaMod.cropBlocks.forEach(blockConsumer);
    }
}
