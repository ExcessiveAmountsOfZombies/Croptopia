package me.thonk.croptopia.events;


import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static me.thonk.croptopia.registry.GeneratorRegistry.*;
import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.UNDERGROUND_ORES;
import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.VEGETAL_DECORATION;

public class BiomeModification {

    boolean loaded = false;

    @SubscribeEvent
    public void onBiomeLoad(BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder settings = event.getGeneration();
        if (event.getCategory() != Biome.BiomeCategory.OCEAN) {
            settings.addFeature(VEGETAL_DECORATION, RANDOM_CROP);
        }

        if (event.getCategory() == Biome.BiomeCategory.FOREST) {
            if (event.getName() != null) {
                // TODO: find a better way to do this, surely there is a cleaner way.
                // this also isn't very compatible with modded biomes that might add their own dark oak forest esque biomes.
                if (event.getName().compareTo(Biomes.DARK_FOREST.location()) == 0 ||
                    event.getName().compareTo(Biomes.DARK_FOREST_HILLS.location()) == 0) {
                    settings.addFeature(VEGETAL_DECORATION, ALMOND_TREE_CONFIGURED);
                    settings.addFeature(VEGETAL_DECORATION, CASHEW_TREE_CONFIGURED);
                    settings.addFeature(VEGETAL_DECORATION, PECAN_TREE_CONFIGURED);
                    settings.addFeature(VEGETAL_DECORATION, WALNUT_TREE_CONFIGURED);
                }
            }

            settings.addFeature(VEGETAL_DECORATION, LIME_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, PEAR_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, APRICOT_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, AVOCADO_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, STAR_FRUIT_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, LEMON_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, CHERRY_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, PLUM_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, PERSIMMON_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, ORANGE_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, NECTARINE_TREE_CONFIGURED);
        } else if (event.getCategory() == Biome.BiomeCategory.JUNGLE) {
            settings.addFeature(VEGETAL_DECORATION, DATE_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, DRAGON_FRUIT_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, MANGO_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, NUTMEG_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, COCONUT_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, KUMQUAT_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, GRAPEFRUIT_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, BANANA_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, FIG_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, CINNAMON_TREE_CONFIGURED);
        } else if (event.getCategory() == Biome.BiomeCategory.PLAINS) {
            settings.addFeature(VEGETAL_DECORATION, APPLE_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, ORANGE_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, PEACH_TREE_CONFIGURED);
        } else if (event.getCategory() == Biome.BiomeCategory.RIVER) {
            settings.addFeature(UNDERGROUND_ORES, DISK_SALT_CONFIGURED);
        }

        /*if (!loaded) { LAZY WAY OF OUTPUTTING ALL MY CONFIGURED FEATURES TO JSON
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            for (Map.Entry<ResourceKey<ConfiguredFeature<?, ?>>, ConfiguredFeature<?, ?>> resourceKeyConfiguredFeatureEntry : BuiltinRegistries.CONFIGURED_FEATURE.entrySet()) {
                ResourceLocation location = resourceKeyConfiguredFeatureEntry.getKey().location();
                if (location.getNamespace().equalsIgnoreCase("minecraft")) {
                    continue;
                }
                JsonObject object = new JsonObject();
                //BuiltinRegistries.CONFIGURED_FEATURE.encode(resourceKeyConfiguredFeatureEntry.getValue(), JsonOps.INSTANCE, object);
                File file = new File("D:\\Programming\\FabricProjects\\croptopia2\\shared\\src\\main\\resources\\data", resourceKeyConfiguredFeatureEntry.getKey().location().getPath() + ".json");
                try {
                    Optional<JsonElement> element = ConfiguredFeature.CODEC.encode(resourceKeyConfiguredFeatureEntry::getValue, JsonOps.INSTANCE, object).result();
                    JsonWriter writer = gson.newJsonWriter(new FileWriter(file));
                    gson.toJson(element.get(), writer);
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            loaded = true;
        }*/
    }

}
