package com.epherical.croptopia.client;

import com.epherical.croptopia.ClientFunctions;
import com.epherical.croptopia.CroptopiaMod;
import com.epherical.croptopia.blocks.LeafCropBlock;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;


@Environment(EnvType.CLIENT)
public class CroptopiaClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientFunctions functions = new ClientFunctions();
        ColorProviderRegistry.BLOCK.register(functions.registerLeafColors(), functions.leaves());
        functions.registerBlockLayers(this::registerCropBlockLayer);
    }

    public void registerCropBlockLayer(Block block) {
        BlockRenderLayerMap.INSTANCE.putBlock(block, RenderType.cutoutMipped());
    }
}
