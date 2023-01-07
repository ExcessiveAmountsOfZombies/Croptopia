package com.epherical.croptopia.biome;

import com.epherical.croptopia.common.MiscNames;
import com.epherical.croptopia.registry.GeneratorRegistry;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.epherical.croptopia.CroptopiaForge.createIdentifier;

public record CropModifier(GenerationStep.Decoration step, Holder<PlacedFeature> feature) implements BiomeModifier {

    public static final RegistryObject<Codec<? extends BiomeModifier>> SERIALIZER =
            RegistryObject.create(createIdentifier("crops"), ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, MiscNames.MOD_ID);

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (!biome.is(BiomeTags.IS_OCEAN) && !biome.is(Tags.Biomes.IS_UNDERGROUND) && phase == Phase.ADD) {
            BiomeGenerationSettingsBuilder generation = builder.getGenerationSettings();
            generation.addFeature(step, feature);
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return SERIALIZER.get();
    }

    public static Codec<CropModifier> makeCodec() {
        return RecordCodecBuilder.create(builder -> builder.group(
                Codec.STRING.comapFlatMap(CropModifier::generationStageFromString,
                        GenerationStep.Decoration::toString).fieldOf("generation_stage").forGetter(CropModifier::step),
                PlacedFeature.CODEC.fieldOf("feature").forGetter(CropModifier::feature)
        ).apply(builder, CropModifier::new));
    }

    private static DataResult<GenerationStep.Decoration> generationStageFromString(String name) {
        try {
            return DataResult.success(GenerationStep.Decoration.valueOf(name));
        } catch (Exception e) {
            return DataResult.error("Not a decoration stage: " + name);
        }
    }

    public static void register(DeferredRegister<BiomeModifier> biomeSerializer) {
        BiomeModifier modifier = new CropModifier(
                GenerationStep.Decoration.VEGETAL_DECORATION,
                GeneratorRegistry.RANDOM_CROP_PLACED);
        biomeSerializer.register("random_crops", () -> modifier);
    }
}
