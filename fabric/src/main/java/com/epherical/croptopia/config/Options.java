package com.epherical.croptopia.config;

import com.google.gson.*;
import com.epherical.croptopia.Croptopia;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Options {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private File optionsFile;


    public boolean useHoeToCollectSeeds = true;
    public boolean disableSaltOre = false;

    public Options(String modID) {
        File configDirectory = new File(FabricLoader.getInstance().getConfigDir().toFile(), modID);

        if (!configDirectory.exists()) {
            configDirectory.mkdirs();
        }

        optionsFile = new File(configDirectory, "options.json");

        /*if (!optionsFile.exists()) { Using a config library now.
            try (Writer writer = new FileWriter(optionsFile)) {
                GSON.toJson(new JsonObject(), writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

        //checkForDefaults(optionsFile);
        //readOptionsFile(optionsFile);
    }

    private void checkForDefaults(File file) {
        try (Reader reader = new FileReader(file)) {
            JsonObject mainObject = GSON.fromJson(reader, JsonObject.class);

            JsonObject options = new JsonObject();
            if (!mainObject.has("options")) {
                options.add("_comment", new JsonPrimitive("Set this to false if you want to get seeds anytime you break tall grass."));
                options.add("use-hoe-to-collect-seeds", new JsonPrimitive(true));
            } else {
                options = mainObject.getAsJsonObject("options");
            }

            // Added in 1.0.3
            if (!options.has("disable-salt-ore")) {
                options.add("disable-salt-ore", new JsonPrimitive(false));
            }

            mainObject.add("options", options);

            try (Writer writer = new FileWriter(file)) {
                GSON.toJson(mainObject, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public void addSeedDefaults(List<ConfigurableSeed> seeds, File file) {
        try (Reader reader = new FileReader(file)) {
            JsonObject mainObject = GSON.fromJson(reader, JsonObject.class);

            JsonObject seedCategory = new JsonObject();
            if (!mainObject.has("seeds")) {
                mainObject.add("seeds", seedCategory);
                seedCategory.add("_comment", new JsonPrimitive("You can configure the biome category that seeds " +
                        "will drop in and the chance to drop. "  +
                        "Acceptable biome categories are: taiga, extreme_hills, jungle, mesa, plains, savanna, icy, the_end, " +
                        "beach, forest, ocean, desert, river, swamp, mushroom, nether"));
                seedCategory.add("__comment", new JsonPrimitive("Set this to none if you want them to drop from any biome category."));
            } else {
                seedCategory = (JsonObject) mainObject.get("seeds");
            }

            for (ConfigurableSeed configurableSeed : seeds) {
                if (!seedCategory.has(configurableSeed.getSeed())) {
                    JsonObject seed = new JsonObject();
                    seed.add("biome-category", new JsonPrimitive(configurableSeed.getBiomeCategory().get(0).asString()));
                    seed.add("drop-chance", new JsonPrimitive(configurableSeed.getChanceToDrop()));
                    seedCategory.add(configurableSeed.getSeed(), seed);
                }
            }

            try (Writer writer = new FileWriter(file)) {
                GSON.toJson(mainObject, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public List<ConfigurableSeed> readConfiguredSeeds(File file) {
        List<ConfigurableSeed> seeds = new ArrayList<>();
        try(FileReader reader = new FileReader(file)) {
            JsonObject mainObject = GSON.fromJson(reader, JsonObject.class);
            JsonObject seedsCategory = mainObject.getAsJsonObject("seeds");
            for (Map.Entry<String, JsonElement> entry : seedsCategory.entrySet()) {
                if (entry.getKey().contains("comment")) {
                    continue;
                }
                JsonObject seedObject = (JsonObject) entry.getValue();
                String category = seedObject.getAsJsonPrimitive("biome-category").getAsString();
                float chance = seedObject.getAsJsonPrimitive("drop-chance").getAsFloat();
                Item item = Registry.ITEM.get(Croptopia.createIdentifier(entry.getKey()));
                Biome.BiomeCategory biomeCategory = Biome.BiomeCategory.byName(category);
                if (biomeCategory == null) {
                    biomeCategory = Biome.BiomeCategory.PLAINS;
                }
                seeds.add(new ConfigurableSeed(entry.getKey(), item, List.of(biomeCategory)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return seeds;
    }

    public void readOptionsFile(File file) {
        try (FileReader reader = new FileReader(file)) {
            JsonObject mainObject = GSON.fromJson(reader, JsonObject.class);
            JsonObject options = mainObject.getAsJsonObject("options");
            useHoeToCollectSeeds = options.getAsJsonPrimitive("use-hoe-to-collect-seeds").getAsBoolean();
            disableSaltOre = options.getAsJsonPrimitive("disable-salt-ore").getAsBoolean();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean useHoeToCollectSeeds() {
        return useHoeToCollectSeeds;
    }

    public boolean disableSaltOre() {
        return disableSaltOre;
    }

    public File getOptionsFile() {
        return optionsFile;
    }
}
