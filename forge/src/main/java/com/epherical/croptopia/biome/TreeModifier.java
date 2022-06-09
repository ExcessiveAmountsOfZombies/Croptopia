package com.epherical.croptopia.biome;

import com.epherical.croptopia.CroptopiaForge;
import com.epherical.croptopia.common.MiscNames;
import com.epherical.croptopia.registry.GeneratorRegistry;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

import static com.epherical.croptopia.CroptopiaForge.createIdentifier;

public record TreeModifier(ResourceLocation biomes, GenerationStep.Decoration step, HolderSet<PlacedFeature> features) implements BiomeModifier {

    public static final RegistryObject<Codec<? extends BiomeModifier>> SERIALIZER =
            RegistryObject.create(createIdentifier("trees"), ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, MiscNames.MOD_ID);

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase == Phase.ADD && biome.is(biomes)) {
            BiomeGenerationSettingsBuilder generation = builder.getGenerationSettings();
            for (Holder<PlacedFeature> feature : this.features) {
                generation.addFeature(step, feature);
            }
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return SERIALIZER.get();
    }

    public static Codec<TreeModifier> makeCodec() {
        return RecordCodecBuilder.create(builder -> builder.group(
                ResourceLocation.CODEC.fieldOf("biomes").forGetter(TreeModifier::biomes),
                Codec.STRING.comapFlatMap(TreeModifier::generationStageFromString,
                        GenerationStep.Decoration::toString).fieldOf("generation_stage").forGetter(TreeModifier::step),
                PlacedFeature.LIST_CODEC.fieldOf("feature").forGetter(TreeModifier::features)
        ).apply(builder, TreeModifier::new));
    }

    private static DataResult<GenerationStep.Decoration> generationStageFromString(String name) {
        try {
            return DataResult.success(GenerationStep.Decoration.valueOf(name));
        } catch (Exception e) {
            return DataResult.error("Not a decoration stage: " + name);
        }
    }

    public static void register(DeferredRegister<BiomeModifier> biomeSerializer) {
        CroptopiaForge.config.getFeatures().asMap().forEach((location, strings) -> {
            List<Holder<PlacedFeature>> features = new ArrayList<>();
            if (!strings.isEmpty()) {
                for (String string : strings) {
                    features.add(GeneratorRegistry.getFeatureKey(string));
                }
            }
            BiomeModifier modifier = new TreeModifier(
                    location,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HolderSet.direct(features));
            biomeSerializer.register(location.getNamespace() + "_" + location.getPath(), () -> modifier);
        });

    }
}
