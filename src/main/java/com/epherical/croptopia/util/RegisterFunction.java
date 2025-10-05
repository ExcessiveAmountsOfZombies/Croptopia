package com.epherical.croptopia.util;


import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Function;

public interface RegisterFunction<T> {
    T register(ResourceLocation id, Function<ResourceLocation, T> object);
}
