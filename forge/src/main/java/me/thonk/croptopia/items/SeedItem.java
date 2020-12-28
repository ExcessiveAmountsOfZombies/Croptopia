package me.thonk.croptopia.items;

import me.thonk.croptopia.blocks.CroptopiaCropBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.world.biome.Biome.Category;

public class SeedItem extends BlockNamedItem {

    private Category category;

    public SeedItem(Block block, Properties settings, Category category) {
        super(block, settings);
        ((CroptopiaCropBlock) block).setSeed(this);
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }
}
