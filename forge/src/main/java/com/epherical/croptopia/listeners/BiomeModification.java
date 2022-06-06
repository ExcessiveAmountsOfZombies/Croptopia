package com.epherical.croptopia.listeners;


import com.epherical.croptopia.CroptopiaForge;
import com.epherical.croptopia.config.Config;
import com.epherical.croptopia.registry.GeneratorRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Collection;

import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.UNDERGROUND_ORES;
import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.VEGETAL_DECORATION;

public class BiomeModification {

    boolean loaded = false;

    @SubscribeEvent
    public void onBiomeLoad(BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder settings = event.getGeneration();
        if (event.getCategory() != Biome.BiomeCategory.OCEAN) {
            settings.addFeature(VEGETAL_DECORATION, GeneratorRegistry.RANDOM_CROP_PLACED);
        }
        ResourceLocation location = event.getName();
        if (location == null) {
            return;
        }
        Collection<String> features = CroptopiaForge.config.getFeatures().get(location);
        if (!features.isEmpty()) {
            for (String feature : features) {
                settings.addFeature(VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey(feature));
            }
        }

        if (!Config.isSaltDisabled) {
            settings.addFeature(UNDERGROUND_ORES, GeneratorRegistry.DISK_SALT_CONFIGURED);
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
