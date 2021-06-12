package me.thonk.croptopia.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;

public class CroptopiaSaplingItem extends AliasedBlockItem {

    private final Block saplingFruitLeafBlock;
    private final Block vanillaLeafBlock;

    public CroptopiaSaplingItem(Block block, Block saplingFruitLeafBlock, Block vanillaLeafBlock, Settings settings) {
        super(block, settings);
        this.saplingFruitLeafBlock = saplingFruitLeafBlock;
        this.vanillaLeafBlock = vanillaLeafBlock;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockState atPos = context.getWorld().getBlockState(context.getBlockPos());
        if (atPos.getBlock() == vanillaLeafBlock) {
            if (!context.getPlayer().isCreative()) {
                context.getStack().decrement(1);
            }
            context.getWorld().setBlockState(context.getBlockPos(), saplingFruitLeafBlock.getDefaultState());
            return ActionResult.CONSUME;
        }
        return super.useOnBlock(context);
    }
}
