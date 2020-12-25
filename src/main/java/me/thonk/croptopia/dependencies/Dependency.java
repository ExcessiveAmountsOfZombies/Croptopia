package me.thonk.croptopia.dependencies;


import net.fabricmc.loader.api.FabricLoader;


public class Dependency {

    private boolean loaded;

    public Dependency(String modID) {
        this.loaded = FabricLoader.getInstance().isModLoaded(modID);
    }

    public boolean isLoaded() {
        return loaded;
    }
}
