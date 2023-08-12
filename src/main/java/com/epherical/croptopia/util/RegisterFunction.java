package com.epherical.croptopia.util;


import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public interface RegisterFunction<T> {
    T register(ResourceLocation id, Supplier<T> object);
}
