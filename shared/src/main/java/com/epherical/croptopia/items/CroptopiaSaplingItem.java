package com.epherical.croptopia.items;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class CroptopiaSaplingItem extends ItemNameBlockItem {

    private final Block saplingFruitLeafBlock;
    private final Block vanillaLeafBlock;

    public CroptopiaSaplingItem(Block block, Block saplingFruitLeafBlock, Block vanillaLeafBlock, Properties settings) {
        super(block, settings);
        this.saplingFruitLeafBlock = saplingFruitLeafBlock;
        this.vanillaLeafBlock = vanillaLeafBlock;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockState atPos = context.getLevel().getBlockState(context.getClickedPos());
        if (atPos.getBlock() == vanillaLeafBlock) {
            if (!context.getPlayer().isCreative()) {
                context.getItemInHand().shrink(1);
            }
            context.getLevel().setBlockAndUpdate(context.getClickedPos(), saplingFruitLeafBlock.defaultBlockState());
            return InteractionResult.CONSUME;
        }
        return super.useOn(context);
    }
}
