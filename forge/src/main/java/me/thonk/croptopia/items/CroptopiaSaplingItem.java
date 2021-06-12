package me.thonk.croptopia.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;

public class CroptopiaSaplingItem extends BlockNamedItem {

    private final Block saplingFruitLeafBlock;
    private final Block vanillaLeafBlock;

    public CroptopiaSaplingItem(Block blockIn, Block saplingFruitLeafBlock, Block vanillaLeafBlock, Properties properties) {
        super(blockIn, properties);
        this.saplingFruitLeafBlock = saplingFruitLeafBlock;
        this.vanillaLeafBlock = vanillaLeafBlock;
    }


    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        BlockState atPos = context.getWorld().getBlockState(context.getPos());
        if (atPos.getBlock() == vanillaLeafBlock) {
            if (!context.getPlayer().isCreative()) {
                context.getItem().shrink(1);
            }
            context.getWorld().setBlockState(context.getPos(), saplingFruitLeafBlock.getDefaultState());
            return ActionResultType.CONSUME;
        }
        return super.onItemUse(context);
    }
}
