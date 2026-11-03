package com.epherical.croptopia.listeners;

import com.epherical.croptopia.register.Content;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.item.v1.BlockTransformerHelper;
import net.minecraft.world.InteractionResult;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BlockBreakEvent {

    public static void register() {
        BlockTransformerHelper.registerStripping(Content.CINNAMON.getLog(), Content.CINNAMON.getStrippedLog());
        BlockTransformerHelper.registerStripping(Content.CINNAMON.getWood(), Content.CINNAMON.getStrippedWood());

        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (world.isClientSide()) {
                return InteractionResult.PASS;
            }

            if (!(player.getItemInHand(hand).is(ItemTags.AXES))) {
                return InteractionResult.PASS;
            }

            BlockState state = world.getBlockState(hitResult.getBlockPos());
            if (!state.is(Content.CINNAMON.getLog()) && !state.is(Content.CINNAMON.getWood())) {
                return InteractionResult.PASS;
            }

            if (!player.isCreative()) {
                Block.popResourceFromFace(world, hitResult.getBlockPos(), hitResult.getDirection().getOpposite(), new ItemStack(Content.CINNAMON));
            }

            return InteractionResult.PASS;
        });
    }
}
