package com.epherical.croptopia;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.ExperienceOrb;

public class FabricHarvest {

    public static void register() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (!world.isClientSide()) {
                BlockPos pos = hitResult.getBlockPos();
                BlockState blockState = world.getBlockState(pos);

                if (blockState.getBlock() instanceof CropBlock) {
                    CropBlock cropBlock = (CropBlock) blockState.getBlock();
                    IntegerProperty ageProperty = cropBlock.getAgeProperty();
                    int age = blockState.getValue(ageProperty);

                    if (age == cropBlock.getMaxAge()) {
                        // Harvest the crop
                        Block.dropResources(blockState, world, pos);

                        // Drop experience orbs on the ground
                        int xpAmount = 5; // Set the amount of XP you want to drop
                        BlockPos orbPosition = pos.above();
                        ExperienceOrb orb = new ExperienceOrb(world, orbPosition.getX() + 0.5, orbPosition.getY(), orbPosition.getZ() + 0.5, xpAmount);
                        world.addFreshEntity(orb);

                        // Reset the crop to its initial state
                        BlockState newState = blockState.setValue(ageProperty, 0);
                        world.setBlock(pos, newState, 2);

                        return InteractionResult.SUCCESS;
                    }
                }
            }

            return InteractionResult.PASS;
        });
    }
}
