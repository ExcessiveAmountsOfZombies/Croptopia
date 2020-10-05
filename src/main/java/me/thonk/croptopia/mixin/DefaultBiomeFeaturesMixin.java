package me.thonk.croptopia.mixin;

import me.thonk.croptopia.generator.TreeGeneratorRegistry;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DefaultBiomeFeatures.class)
public class DefaultBiomeFeaturesMixin {

    @Inject(method = "addPlainsFeatures", at = @At("TAIL"))
    private static void addPlainsFeatures(GenerationSettings.Builder builder, CallbackInfo info) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION,
                TreeGeneratorRegistry.APPLE_TREE_CONFIGURED);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION,
                TreeGeneratorRegistry.ORANGE_TREE_CONFIGURED);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION,
                TreeGeneratorRegistry.PEACH_TREE_CONFIGURED);
    }

    @Inject(method = "addJungleTrees", at = @At("TAIL"))
    private static void addJungleTrees(GenerationSettings.Builder builder, CallbackInfo info) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION,
                TreeGeneratorRegistry.DATE_TREE_CONFIGURED);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION,
                TreeGeneratorRegistry.DRAGON_FRUIT_TREE_CONFIGURED);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION,
                TreeGeneratorRegistry.MANGO_TREE_CONFIGURED);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION,
                TreeGeneratorRegistry.NUTMEG_TREE_CONFIGURED);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION,
                TreeGeneratorRegistry.COCONUT_TREE_CONFIGURED);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION,
                TreeGeneratorRegistry.KUMQUAT_TREE_CONFIGURED);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION,
                TreeGeneratorRegistry.GRAPEFRUIT_TREE_CONFIGURED);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION,
                TreeGeneratorRegistry.BANANA_TREE_CONFIGURED);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION,
                TreeGeneratorRegistry.GRAPEFRUIT_TREE_CONFIGURED);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION,
                TreeGeneratorRegistry.FIG_TREE_CONFIGURED);

    }

    @Inject(method = "addForestTrees", at = @At("TAIL"))
    private static void addForestTrees(GenerationSettings.Builder builder, CallbackInfo info) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION,
                TreeGeneratorRegistry.LIME_TREE_CONFIGURED);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION,
                TreeGeneratorRegistry.PEAR_TREE_CONFIGURED);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION,
                TreeGeneratorRegistry.APRICOT_TREE_CONFIGURED);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION,
                TreeGeneratorRegistry.AVOCADO_TREE_CONFIGURED);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION,
                TreeGeneratorRegistry.STAR_FRUIT_TREE_CONFIGURED);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION,
                TreeGeneratorRegistry.LEMON_TREE_CONFIGURED);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION,
                TreeGeneratorRegistry.CHERRY_TREE_CONFIGURED);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION,
                TreeGeneratorRegistry.PLUM_TREE_CONFIGURED);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION,
                TreeGeneratorRegistry.PERSIMMON_TREE_CONFIGURED);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION,
                TreeGeneratorRegistry.ORANGE_TREE_CONFIGURED);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION,
                TreeGeneratorRegistry.NECTARINE_TREE_CONFIGURED);
    }
}
