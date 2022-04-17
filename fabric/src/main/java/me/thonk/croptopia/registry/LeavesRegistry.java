package me.thonk.croptopia.registry;

import me.thonk.common.BlockNames;
import net.minecraft.block.Block;

import static me.thonk.croptopia.Croptopia.*;


public class LeavesRegistry {

    public static Block cinnamonLeaves = createRegularLeavesBlock();

    public static void init() {
        registerBlock(BlockNames.CINNAMON_LEAVES, cinnamonLeaves);
    }

}
