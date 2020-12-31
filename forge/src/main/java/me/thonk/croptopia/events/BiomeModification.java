package me.thonk.croptopia.events;


import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static me.thonk.croptopia.registry.GeneratorRegistry.*;
import static net.minecraft.world.gen.GenerationStage.Decoration.UNDERGROUND_ORES;
import static net.minecraft.world.gen.GenerationStage.Decoration.VEGETAL_DECORATION;

public class BiomeModification {

    @SubscribeEvent
    public void onBiomeLoad(BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder settings = event.getGeneration();
        if (event.getCategory() == Biome.Category.FOREST) {
            if (event.getName() != null) {
                // TODO: find a better way to do this, surely there is a cleaner way.
                // this also isn't very compatible with modded biomes that might add their own dark oak forest esque biomes.
                if (event.getName().compareTo(Biomes.DARK_FOREST.getLocation()) == 0 ||
                    event.getName().compareTo(Biomes.DARK_FOREST_HILLS.getLocation()) == 0) {
                    settings.withFeature(VEGETAL_DECORATION, ALMOND_TREE_CONFIGURED);
                    settings.withFeature(VEGETAL_DECORATION, CASHEW_TREE_CONFIGURED);
                    settings.withFeature(VEGETAL_DECORATION, PECAN_TREE_CONFIGURED);
                    settings.withFeature(VEGETAL_DECORATION, WALNUT_TREE_CONFIGURED);
                }
            }

            settings.withFeature(VEGETAL_DECORATION, LIME_TREE_CONFIGURED);
            settings.withFeature(VEGETAL_DECORATION, PEAR_TREE_CONFIGURED);
            settings.withFeature(VEGETAL_DECORATION, APRICOT_TREE_CONFIGURED);
            settings.withFeature(VEGETAL_DECORATION, AVOCADO_TREE_CONFIGURED);
            settings.withFeature(VEGETAL_DECORATION, STAR_FRUIT_TREE_CONFIGURED);
            settings.withFeature(VEGETAL_DECORATION, LEMON_TREE_CONFIGURED);
            settings.withFeature(VEGETAL_DECORATION, CHERRY_TREE_CONFIGURED);
            settings.withFeature(VEGETAL_DECORATION, PLUM_TREE_CONFIGURED);
            settings.withFeature(VEGETAL_DECORATION, PERSIMMON_TREE_CONFIGURED);
            settings.withFeature(VEGETAL_DECORATION, ORANGE_TREE_CONFIGURED);
            settings.withFeature(VEGETAL_DECORATION, NECTARINE_TREE_CONFIGURED);
        } else if (event.getCategory() == Biome.Category.JUNGLE) {
            settings.withFeature(VEGETAL_DECORATION, DATE_TREE_CONFIGURED);
            settings.withFeature(VEGETAL_DECORATION, DRAGON_FRUIT_TREE_CONFIGURED);
            settings.withFeature(VEGETAL_DECORATION, MANGO_TREE_CONFIGURED);
            settings.withFeature(VEGETAL_DECORATION, NUTMEG_TREE_CONFIGURED);
            settings.withFeature(VEGETAL_DECORATION, COCONUT_TREE_CONFIGURED);
            settings.withFeature(VEGETAL_DECORATION, KUMQUAT_TREE_CONFIGURED);
            settings.withFeature(VEGETAL_DECORATION, GRAPEFRUIT_TREE_CONFIGURED);
            settings.withFeature(VEGETAL_DECORATION, GRAPEFRUIT_TREE_CONFIGURED);
            settings.withFeature(VEGETAL_DECORATION, BANANA_TREE_CONFIGURED);
            settings.withFeature(VEGETAL_DECORATION, FIG_TREE_CONFIGURED);
        } else if (event.getCategory() == Biome.Category.PLAINS) {
            settings.withFeature(VEGETAL_DECORATION, APPLE_TREE_CONFIGURED);
            settings.withFeature(VEGETAL_DECORATION, ORANGE_TREE_CONFIGURED);
            settings.withFeature(VEGETAL_DECORATION, PEACH_TREE_CONFIGURED);
        } else if (event.getCategory() == Biome.Category.RIVER) {
            settings.withFeature(UNDERGROUND_ORES, DISK_SALT_CONFIGURED);
        }
    }

}
