package me.thonk.croptopia.config;

import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;

public class ConfigurableSeed {

    private String seed;
    private Item seedItem;
    private Biome.Category biomeCategory;
    private float chanceToDrop;

    public ConfigurableSeed(String seed, Item seedItem, Biome.Category biomeCategory, float chanceToDrop) {
        this.seed = seed;
        this.seedItem = seedItem;
        this.biomeCategory = biomeCategory;
        this.chanceToDrop = chanceToDrop;
    }

    public Biome.Category getBiomeCategory() {
        return biomeCategory;
    }

    public float getChanceToDrop() {
        return chanceToDrop;
    }

    public String getSeed() {
        return seed;
    }

    public Item getSeedItem() {
        return seedItem;
    }
}
