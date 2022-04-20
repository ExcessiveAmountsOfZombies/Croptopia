package com.epherical.croptopia.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.Block;

@Mixin(AxeItem.class)
public interface AxeAccess {

    @Accessor("STRIPPABLES")
    public static Map<Block, Block> getStrippables() {
        throw new AssertionError();
    }

    @Accessor("STRIPPABLES")
    public static void setStrippables(Map<Block, Block> map) {
        throw new AssertionError();
    }
}
