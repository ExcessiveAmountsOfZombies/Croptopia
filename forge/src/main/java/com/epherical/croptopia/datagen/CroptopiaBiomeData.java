package com.epherical.croptopia.datagen;

import com.epherical.croptopia.common.MiscNames;
import com.mojang.serialization.Codec;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.epherical.croptopia.CroptopiaForge.createIdentifier;

public class CroptopiaBiomeData {

    static final ResourceLocation ADD_TREES_TO_BIOMES = createIdentifier("trees");

    public CroptopiaBiomeData(IEventBus bus) {
        DeferredRegister<Codec<? extends BiomeModifier>> biomeModSerializer =
                DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, MiscNames.MOD_ID);
        biomeModSerializer.register(bus);
        //biomeModSerializer.register(ADD_TREES_TO_BIOMES, TreeModifier::)


    }

    public void getData(GatherDataEvent event) {

    }


}
