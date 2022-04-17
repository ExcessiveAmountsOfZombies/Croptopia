package me.thonk.croptopia.client;

import me.thonk.croptopia.blocks.LeafCropBlock;
import me.thonk.croptopia.registry.Content;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.render.RenderLayer;

import static me.thonk.croptopia.Croptopia.cropBlocks;

@Environment(EnvType.CLIENT)
public class CroptopiaClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        cropBlocks.forEach(this::registerCropBlockLayer);
        registerColorProvider();
    }

    public void registerCropBlockLayer(Block block) {
        if (block instanceof LeafCropBlock) {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutoutMipped());
            return;
        }
        BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutoutMipped());
    }

    public void registerColorProvider() {
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) ->
                world != null && pos != null
                        ? BiomeColors.getFoliageColor(world, pos)
                        : FoliageColors.getDefaultColor(), Content.createLeafStream().toArray(Block[]::new));
    }
}
