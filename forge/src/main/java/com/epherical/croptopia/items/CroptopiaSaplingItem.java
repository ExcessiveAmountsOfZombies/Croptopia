package com.epherical.croptopia.items;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;

public class CroptopiaSaplingItem extends BlockNamedItem {

    private final Block saplingFruitLeafBlock;
    private final Block vanillaLeafBlock;

    public CroptopiaSaplingItem(Block block, Block saplingFruitLeafBlock, Block vanillaLeafBlock, Properties settings) {
        super(block, settings);
        this.saplingFruitLeafBlock = saplingFruitLeafBlock;
        this.vanillaLeafBlock = vanillaLeafBlock;
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        BlockState atPos = context.getLevel().getBlockState(context.getClickedPos());
        if (atPos.getBlock() == vanillaLeafBlock) {
            if (!context.getPlayer().isCreative()) {
                context.getItemInHand().shrink(1);
            }
            context.getLevel().setBlockAndUpdate(context.getClickedPos(), saplingFruitLeafBlock.defaultBlockState());
            return ActionResultType.CONSUME;
        }
        return super.useOn(context);
    }
}
