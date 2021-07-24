package com.epherical.croptopia.util;


import net.minecraft.util.ResourceLocation;

public interface RegisterFunction<T> {
    T register(ResourceLocation id, T object);
}
