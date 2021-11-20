package me.thonk.croptopia.config;

import me.thonk.croptopia.Croptopia;
import me.thonk.croptopia.items.SeedItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;

import java.lang.reflect.Type;
import java.util.List;

public class ConfigurableSeed {


    private String seed;
    private Item seedItem;
    private List<Biome.Category> biomeCategory;
    //private float chanceToDrop;

    public ConfigurableSeed(String seed, Item seedItem, List<Biome.Category> biomeCategory) {
        this.seed = seed;
        this.seedItem = seedItem;
        this.biomeCategory = biomeCategory;
        //this.chanceToDrop = chanceToDrop;
    }

    public List<Biome.Category> getBiomeCategory() {
        return biomeCategory;
    }

    /*public float getChanceToDrop() {
        return chanceToDrop;
    }*/

    public String getSeed() {
        return seed;
    }

    public Item getSeedItem() {
        return seedItem;
    }


    public static class Serializer implements TypeSerializer<ConfigurableSeed> {
        public static final Serializer INSTANCE = new Serializer();

        private final String KEY_SEED = "seed";
        private final String KEY_BIOME_CATEGORIES = "biomeCategories";
        private final String KEY_DROP_CHANCE = "dropChance";


        @Override
        public ConfigurableSeed deserialize(Type type, ConfigurationNode node) throws SerializationException {
            String seed = node.node(KEY_SEED).getString();
            Item item = Registry.ITEM.get(Croptopia.createIdentifier(seed));
            List<Biome.Category> categories = node.node(KEY_BIOME_CATEGORIES).getList(Biome.Category.class);
            if (item instanceof SeedItem) {
                ((SeedItem) item).setCategory(categories);
            }
            /*float chanceToDrop = node.node(KEY_DROP_CHANCE).getFloat();*/
            return new ConfigurableSeed(seed, item, categories);
        }

        @Override
        public void serialize(Type type, ConfigurableSeed obj, ConfigurationNode node) throws SerializationException {
            if (obj == null) {
                node.raw(null);
                return;
            }

            node.node(KEY_SEED).set(obj.seed);
            node.node(KEY_BIOME_CATEGORIES).setList(Biome.Category.class, obj.biomeCategory);
            /*node.node(KEY_DROP_CHANCE).set(obj.chanceToDrop);*/
        }
    }
}
