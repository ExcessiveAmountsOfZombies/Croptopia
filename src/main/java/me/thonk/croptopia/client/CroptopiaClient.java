package me.thonk.croptopia.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

import static me.thonk.croptopia.blocks.BlockRegistry.*;

@Environment(EnvType.CLIENT)
public class CroptopiaClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        cropBlocks.forEach(this::registerCropBlockLayer);
    }

    public void registerCropBlockLayer(Block block) {
        BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutoutMipped());
    }
}
