package com.epherical.croptopia.biome;

import com.epherical.croptopia.CroptopiaNeoForge;
import com.epherical.croptopia.config.TreeConfiguration;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeGenerationSettingsBuilder;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Collection;
import java.util.Locale;


public record TreeModifier(GenerationStep.Decoration step, HolderSet<PlacedFeature> placedFeatures) implements BiomeModifier {


    public static final DeferredHolder<MapCodec<? extends BiomeModifier>, MapCodec<TreeModifier>> SERIALIZER =
            CroptopiaNeoForge.BIOME_SERIALIZER.register("trees", TreeModifier::makeCodec);

    @Override
    public void modify(RegistryAccess registries, Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase == Phase.ADD) {
            biome.unwrapKey().ifPresent(biomeResourceKey -> {
                BiomeGenerationSettingsBuilder generation = builder.getGenerationSettings();
                Collection<TreeConfiguration> strings = CroptopiaNeoForge.config.treeMap.get(biomeResourceKey);
                for (TreeConfiguration config : strings) {
                    HolderLookup.RegistryLookup<PlacedFeature> placedFeatureRegistryLookup = registries.lookupOrThrow(Registries.PLACED_FEATURE);
                    generation.addFeature(step, placedFeatureRegistryLookup.getOrThrow(config.getFeatureKey()));
                }
            });
        }
    }

    @Override
    public MapCodec<? extends BiomeModifier> codec() {
        return SERIALIZER.get();
    }

    public static MapCodec<TreeModifier> makeCodec() {
        return RecordCodecBuilder.mapCodec(builder -> builder.group(
                Codec.STRING.comapFlatMap(TreeModifier::generationStageFromString,
                        GenerationStep.Decoration::getName).fieldOf("generation_stage").forGetter(TreeModifier::step),
                PlacedFeature.LIST_CODEC.fieldOf("features").forGetter(treeModifier -> treeModifier.placedFeatures)
        ).apply(builder, TreeModifier::new));
    }

    private static DataResult<GenerationStep.Decoration> generationStageFromString(String name) {
        try {
            return DataResult.success(GenerationStep.Decoration.valueOf(name.toUpperCase(Locale.ROOT)));
        } catch (Exception e) {
            return DataResult.error(() -> "Not a decoration stage: " + name);
        }
    }

    /*public static void register(DeferredRegister<BiomeModifier> biomeSerializer) {
        BiomeModifier modifier = new TreeModifier(GenerationStep.Decoration.VEGETAL_DECORATION,);
        biomeSerializer.register("tree_modifiers", () -> modifier);
    }*/
}
