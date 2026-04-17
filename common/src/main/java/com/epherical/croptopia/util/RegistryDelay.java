package com.epherical.croptopia.util;

import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class RegistryDelay<E extends T, T> {

    private final String modId;
    private final List<Consumer<RegisterFunction<E>>> entries = new ArrayList<>();
    private final Map<ResourceLocation, Supplier<E>> manipulations = new HashMap<>();

    public RegistryDelay(String modId) {
        this.modId = modId;
    }


    public void reg(Consumer<RegisterFunction<E>> function) {
        entries.add(function);
    }

    public List<Consumer<RegisterFunction<E>>> getEntries() {
        return entries;
    }

    public void addOverride(ResourceLocation location, Supplier<E> newValue) {
        this.manipulations.put(location, newValue);
    }

    public Map<ResourceLocation, Supplier<E>> getManipulations() {
        return manipulations;
    }

    public String getModId() {
        return modId;
    }
}
