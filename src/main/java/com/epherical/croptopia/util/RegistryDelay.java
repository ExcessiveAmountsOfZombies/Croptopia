package com.epherical.croptopia.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class RegistryDelay<E extends T, T> {

    private final String modId;
    private final List<Consumer<RegisterFunction<E>>> entries = new ArrayList<>();

    public RegistryDelay(String modId) {
        this.modId = modId;
    }


    public void reg(Consumer<RegisterFunction<E>> function) {
        entries.add(function);
    }

    public List<Consumer<RegisterFunction<E>>> getEntries() {
        return entries;
    }

    public String getModId() {
        return modId;
    }

    public interface Instantiate<T> {
        T create();
    }
}
