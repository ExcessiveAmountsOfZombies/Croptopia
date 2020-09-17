package me.thonk.croptopia.blocks;

import me.thonk.croptopia.Croptopia;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.registry.Registry;

public class CroptopiaCropBlock extends CropBlock {

    private final String seedName;

    protected CroptopiaCropBlock(Settings settings, String seedName) {
        super(settings);
        this.seedName = seedName;
    }


    @Override
    protected ItemConvertible getSeedsItem() {
        return Registry.ITEM.get(Croptopia.createIdentifier(seedName));
    }
}
