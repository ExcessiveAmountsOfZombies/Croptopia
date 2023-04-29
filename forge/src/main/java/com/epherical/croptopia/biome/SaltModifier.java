package com.epherical.croptopia.biome;

import com.epherical.croptopia.common.MiscNames;
import com.epherical.croptopia.config.Config;
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
import net.minecraftforge.common.data.ForgeBiomeTagsProvider;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


import java.util.Locale;

import static com.epherical.croptopia.CroptopiaForge.createIdentifier;

public record SaltModifier(GenerationStep.Decoration step, Holder<PlacedFeature> features) implements BiomeModifier {

    public static final RegistryObject<Codec<? extends BiomeModifier>> SERIALIZER =
            RegistryObject.create(createIdentifier("salt"), ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, MiscNames.MOD_ID);

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase == Phase.ADD && !Config.isSaltDisabled && !biome.is(Tags.Biomes.IS_SWAMP)) {
            BiomeGenerationSettingsBuilder generation = builder.getGenerationSettings();
            generation.addFeature(step, features);
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return SERIALIZER.get();
    }

    public static Codec<SaltModifier> makeCodec() {
        return RecordCodecBuilder.create(builder -> builder.group(
                Codec.STRING.comapFlatMap(SaltModifier::generationStageFromString,
                        GenerationStep.Decoration::getName).fieldOf("generation_stage").forGetter(SaltModifier::step),
                PlacedFeature.CODEC.fieldOf("feature").forGetter(SaltModifier::features)
        ).apply(builder, SaltModifier::new));
    }

    private static DataResult<GenerationStep.Decoration> generationStageFromString(String name) {
        try {
            return DataResult.success(GenerationStep.Decoration.valueOf(name.toUpperCase(Locale.ROOT)));
        } catch (Exception e) {
            return DataResult.error(() ->  "Not a decoration stage: " + name);
        }
    }

    public static void register(DeferredRegister<BiomeModifier> biomeSerializer) {
        biomeSerializer.register("salt_disk", () -> new SaltModifier(GenerationStep.Decoration.UNDERGROUND_ORES, GeneratorRegistry.DISK_SALT_CONFIGURED));
    }
}
