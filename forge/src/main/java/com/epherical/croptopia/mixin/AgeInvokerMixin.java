package com.epherical.croptopia.mixin;

import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(CropBlock.class)
public interface AgeInvokerMixin {

    @Invoker(value = "getAgeProperty")
    IntegerProperty doGetAgeProperty();
}
