package com.epherical.croptopia.events;


import com.epherical.croptopia.CroptopiaForge;
import com.epherical.croptopia.registry.GeneratorRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

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
        String biomeName = location.toString();
        ForgeConfigSpec.ConfigValue<List<? extends String>> features = CroptopiaForge.config.config.getValues().get("worldGeneration." + biomeName + ".trees");
        if (features != null) {
            for (String s : features.get()) {
                settings.addFeature(VEGETAL_DECORATION, GeneratorRegistry.getFeatureKey(s));
            }
        }

        if (event.getCategory() == Biome.BiomeCategory.RIVER) {
            settings.addFeature(UNDERGROUND_ORES, GeneratorRegistry.DISK_SALT_CONFIGURED);
        }





        /*if (event.getCategory() == Biome.BiomeCategory.FOREST) {
            if (event.getName() != null) {
                // TODO: find a better way to do this, surely there is a cleaner way.
                // this also isn't very compatible with modded biomes that might add their own dark oak forest esque biomes.
                if (event.getName().compareTo(Biomes.DARK_FOREST.location()) == 0) {
                    settings.addFeature(VEGETAL_DECORATION, GeneratorRegistry.ALMOND_TREE_CONFIGURED);
                    settings.addFeature(VEGETAL_DECORATION, GeneratorRegistry.CASHEW_TREE_CONFIGURED);
                    settings.addFeature(VEGETAL_DECORATION, GeneratorRegistry.PECAN_TREE_CONFIGURED);
                    settings.addFeature(VEGETAL_DECORATION, GeneratorRegistry.WALNUT_TREE_CONFIGURED);
                }
            }

            settings.addFeature(VEGETAL_DECORATION, GeneratorRegistry.LIME_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, GeneratorRegistry.PEAR_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, GeneratorRegistry.APRICOT_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, GeneratorRegistry.AVOCADO_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, GeneratorRegistry.STAR_FRUIT_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, GeneratorRegistry.LEMON_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, GeneratorRegistry.CHERRY_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, GeneratorRegistry.PLUM_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, GeneratorRegistry.PERSIMMON_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, GeneratorRegistry.ORANGE_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, GeneratorRegistry.NECTARINE_TREE_CONFIGURED);
        } else if (event.getCategory() == Biome.BiomeCategory.JUNGLE) {
            settings.addFeature(VEGETAL_DECORATION, GeneratorRegistry.DATE_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, GeneratorRegistry.DRAGON_FRUIT_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, GeneratorRegistry.MANGO_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, GeneratorRegistry.NUTMEG_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, GeneratorRegistry.COCONUT_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, GeneratorRegistry.KUMQUAT_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, GeneratorRegistry.GRAPEFRUIT_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, GeneratorRegistry.BANANA_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, GeneratorRegistry.FIG_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, GeneratorRegistry.CINNAMON_TREE_CONFIGURED);
        } else if (event.getCategory() == Biome.BiomeCategory.PLAINS) {
            settings.addFeature(VEGETAL_DECORATION, GeneratorRegistry.APPLE_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, GeneratorRegistry.ORANGE_TREE_CONFIGURED);
            settings.addFeature(VEGETAL_DECORATION, GeneratorRegistry.PEACH_TREE_CONFIGURED);
        } */

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
