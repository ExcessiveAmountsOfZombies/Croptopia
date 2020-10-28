package me.thonk.croptopia.config;

import com.google.gson.*;
import me.thonk.croptopia.Croptopia;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Options {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private File optionsFile;


    public boolean useHoeToCollectSeeds = true;

    public Options(String modID) {
        File configDirectory = new File(FabricLoader.getInstance().getConfigDir().toFile(), modID);

        if (!configDirectory.exists()) {
            configDirectory.mkdirs();
        }

        optionsFile = new File(configDirectory, "options.json");

        if (!optionsFile.exists()) {
            try (Writer writer = new FileWriter(optionsFile)) {
                GSON.toJson(new JsonObject(), writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        checkForDefaults(optionsFile);
        readOptionsFile(optionsFile);
    }

    private void checkForDefaults(File file) {
        try (Reader reader = new FileReader(file)) {
            JsonObject mainObject = GSON.fromJson(reader, JsonObject.class);

            JsonObject options = new JsonObject();
            if (!mainObject.has("options")) {
                mainObject.add("options", options);
                options.add("_comment", new JsonPrimitive("Set this to false if you want to get seeds anytime you break tall grass."));
                options.add("use-hoe-to-collect-seeds", new JsonPrimitive(true));
            }

            try (Writer writer = new FileWriter(file)) {
                GSON.toJson(mainObject, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addSeedDefaults(List<ConfigurableSeed> seeds, File file) {
        try (Reader reader = new FileReader(file)) {
            JsonObject mainObject = GSON.fromJson(reader, JsonObject.class);

            JsonObject seedCategory = new JsonObject();
            if (!mainObject.has("seeds")) {
                mainObject.add("seeds", seedCategory);
                seedCategory.add("_comment", new JsonPrimitive("You can configure the biome category that seeds " +
                        "will drop in and the chance to drop. "  +
                        "Acceptable biome categories are: taiga, extreme_hills, jungle, mesa, plains, savanna, icy, the_end, " +
                        "beach, forest, ocean, desert, river, swamp, mushroom, nether"));
            } else {
                seedCategory = (JsonObject) mainObject.get("seeds");
            }
            JsonObject seed = new JsonObject();
            for (ConfigurableSeed configurableSeed : seeds) {
                if (!seedCategory.has(configurableSeed.getSeed())) {
                    seed.add("biome-category", new JsonPrimitive(configurableSeed.getBiomeCategory().asString()));
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
    }

    public List<ConfigurableSeed> readConfiguredSeeds(File file) {
        List<ConfigurableSeed> seeds = new ArrayList<>();
        try {
            FileReader reader = new FileReader(file);
            JsonObject mainObject = GSON.fromJson(reader, JsonObject.class);
            JsonObject seedsCategory = mainObject.getAsJsonObject("seeds");
            for (Map.Entry<String, JsonElement> entry : seedsCategory.entrySet()) {
                if (entry.getKey().equalsIgnoreCase("_comment")) {
                    continue;
                }
                JsonObject seedObject = (JsonObject) entry.getValue();
                String category = seedObject.getAsJsonPrimitive("biome-category").getAsString();
                float chance = seedObject.getAsJsonPrimitive("drop-chance").getAsFloat();
                Item item = Registry.ITEM.get(Croptopia.createIdentifier(entry.getKey()));
                Biome.Category biomeCategory = Biome.Category.byName(category);
                if (biomeCategory == null) {
                    biomeCategory = Biome.Category.PLAINS;
                }
                seeds.add(new ConfigurableSeed(entry.getKey(), item, biomeCategory, chance));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return seeds;
    }

    private void readOptionsFile(File file) {
        try {
            FileReader reader = new FileReader(file);
            JsonObject mainObject = GSON.fromJson(reader, JsonObject.class);
            JsonObject options = mainObject.getAsJsonObject("options");
            useHoeToCollectSeeds = options.getAsJsonPrimitive("use-hoe-to-collect-seeds").getAsBoolean();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean useHoeToCollectSeeds() {
        return useHoeToCollectSeeds;
    }

    public File getOptionsFile() {
        return optionsFile;
    }
}
