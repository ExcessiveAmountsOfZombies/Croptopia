package com.epherical.croptopia.biome;

import com.epherical.croptopia.CroptopiaNeoForge;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeGenerationSettingsBuilder;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Locale;

public record CropModifier(GenerationStep.Decoration step, Holder<PlacedFeature> feature) implements BiomeModifier {
    public static final DeferredHolder<MapCodec<? extends BiomeModifier>, MapCodec<CropModifier>> SERIALIZER =
            CroptopiaNeoForge.BIOME_SERIALIZER.register("crops", CropModifier::makeCodec);

    @Override
    public void modify(RegistryAccess registries, Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase == Phase.ADD) {
            BiomeGenerationSettingsBuilder generation = builder.getGenerationSettings();
            generation.addFeature(step, feature);
        }
    }

    @Override
    public MapCodec<? extends BiomeModifier> codec() {
        return SERIALIZER.get();
    }

    public static MapCodec<CropModifier> makeCodec() {
        return RecordCodecBuilder.mapCodec(builder -> builder.group(
                Codec.STRING.comapFlatMap(CropModifier::generationStageFromString,
                        GenerationStep.Decoration::getName).fieldOf("generation_stage").forGetter(CropModifier::step),
                PlacedFeature.CODEC.fieldOf("feature").forGetter(CropModifier::feature)
        ).apply(builder, CropModifier::new));
    }

    private static DataResult<GenerationStep.Decoration> generationStageFromString(String name) {
        try {
            return DataResult.success(GenerationStep.Decoration.valueOf(name.toUpperCase(Locale.ROOT)));
        } catch (Exception e) {
            return DataResult.error(() -> "Not a decoration stage: " + name);
        }
    }

    /*public static void register(DeferredRegister<BiomeModifier> biomeSerializer) {
        biomeSerializer.register("random_crops", () -> new CropModifier(GenerationStep.Decoration.VEGETAL_DECORATION, GeneratorRegistry.RANDOM_CROP_PLACED));
    }*/
}
