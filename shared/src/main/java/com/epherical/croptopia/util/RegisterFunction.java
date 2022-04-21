package com.epherical.croptopia.util;


import net.minecraft.resources.ResourceLocation;

public interface RegisterFunction<T> {
    void register(ResourceLocation id, T object);
}
