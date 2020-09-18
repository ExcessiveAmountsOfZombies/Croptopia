package me.thonk.croptopia.blocks;

import net.minecraft.block.CropBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;

public class CroptopiaCropBlock extends CropBlock {

    private final Item seed;

    protected CroptopiaCropBlock(Settings settings, Item seed) {
        super(settings);
        this.seed = seed;
    }


    @Override
    protected ItemConvertible getSeedsItem() {
        return seed;
    }
}
