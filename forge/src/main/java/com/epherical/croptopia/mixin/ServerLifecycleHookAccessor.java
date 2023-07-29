package com.epherical.croptopia.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = ServerLifecycleHooks.class, remap = false)
public interface ServerLifecycleHookAccessor {

    @Accessor("currentServer")
    public static MinecraftServer getServer() {
        throw new RuntimeException();
    }
}
