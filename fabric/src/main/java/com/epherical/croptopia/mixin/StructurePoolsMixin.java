package com.epherical.croptopia.mixin;

import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Pools.class)
public class StructurePoolsMixin {


    @Inject(method = "register", at = { @At("HEAD")})
    private static void register(BootstapContext<StructureTemplatePool> bootstapContext, String string, StructureTemplatePool structureTemplatePool, CallbackInfo ci) {
        //System.out.println(templatePool.getId());
    }
}
