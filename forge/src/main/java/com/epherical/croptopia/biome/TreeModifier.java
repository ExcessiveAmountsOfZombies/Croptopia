package com.epherical.croptopia.biome;

import com.epherical.croptopia.CroptopiaForge;
import com.epherical.croptopia.common.MiscNames;
import com.epherical.croptopia.common.generator.PlacedFeatureKeys;
import com.epherical.croptopia.config.TreeConfiguration;
import com.epherical.croptopia.mixin.ServerLifecycleHookAccessor;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.minecraftforge.registries.DataPackRegistryEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

import static com.epherical.croptopia.CroptopiaForge.createIdentifier;
import static com.epherical.croptopia.CroptopiaForge.mod;

public record TreeModifier(GenerationStep.Decoration step, HolderSet<PlacedFeature> placedFeatures) implements BiomeModifier {

    public static final RegistryObject<Codec<? extends BiomeModifier>> SERIALIZER =
            RegistryObject.create(createIdentifier("trees"), ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, MiscNames.MOD_ID);

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase == Phase.ADD) {
            biome.unwrapKey().ifPresent(biomeResourceKey -> {
                BiomeGenerationSettingsBuilder generation = builder.getGenerationSettings();
                Collection<TreeConfiguration> strings = mod.config().treeMap.get(biomeResourceKey);
                MinecraftServer server = ServerLifecycleHookAccessor.getServer();
                for (TreeConfiguration config : strings) {
                    HolderLookup.RegistryLookup<PlacedFeature> placedFeatureRegistryLookup = server.registryAccess().lookupOrThrow(Registries.PLACED_FEATURE);
                    generation.addFeature(step, placedFeatureRegistryLookup.getOrThrow(config.getFeatureKey()));
                }
            });
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return SERIALIZER.get();
    }

    public static Codec<TreeModifier> makeCodec() {
        return RecordCodecBuilder.create(builder -> builder.group(
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
