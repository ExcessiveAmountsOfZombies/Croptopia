package me.thonk.croptopia.items;

import me.thonk.croptopia.blocks.CroptopiaCropBlock;
import net.minecraft.block.Block;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.world.biome.Biome.Category;

public class SeedItem extends AliasedBlockItem {

    private Category category;

    public SeedItem(Block block, Settings settings, Category category) {
        super(block, settings);
        ((CroptopiaCropBlock) block).setSeedsItem(this);
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }
}
