package com.epherical.croptopia.listeners;

import com.epherical.croptopia.blocks.LeafCropBlock;
import com.epherical.croptopia.config.Config;
import com.epherical.croptopia.events.HarvestEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.world.entity.ExperienceOrb;


public class Harvest {


    @SubscribeEvent
    public void onHarvest(PlayerInteractEvent.RightClickBlock event) {
        if (Config.canRightClickHarvest && !event.getEntity().isSpectator()) {
            if (!(event.getEntity().getMainHandItem().getItem() instanceof BoneMealItem)) {
                if (!event.getLevel().isClientSide) {
                    Level world = event.getLevel();
                    BlockPos pos = event.getPos();
                    BlockState blockClicked = event.getLevel().getBlockState(pos);
                    if (blockClicked.getBlock() instanceof CropBlock) {
                        CropBlock block = (CropBlock) blockClicked.getBlock();
                        IntegerProperty property = block.getAgeProperty();
                        int age = blockClicked.getValue(block.getAgeProperty());
                        if (age == block.getMaxAge()) {
                            HarvestEvent harvestedCropEvent = new HarvestEvent(event.getEntity(), blockClicked, withAge(blockClicked, property, 0));
                            MinecraftForge.EVENT_BUS.post(harvestedCropEvent);
                            world.setBlock(pos, harvestedCropEvent.getTurnedState(), 2);

                            // Drop experience orbs on the ground
                            int xpAmount = 1; // Set the amount of XP you want to drop
                            BlockPos orbPosition = pos.above();
                            ExperienceOrb orb = new ExperienceOrb(world, orbPosition.getX() + 0.5, orbPosition.getY(), orbPosition.getZ() + 0.5, xpAmount);
                            world.addFreshEntity(orb);

                            if (blockClicked.getBlock() instanceof LeafCropBlock) {
                                for (ItemStack drop : Block.getDrops(blockClicked, (ServerLevel) world, pos, null)) {
                                    Block.popResourceFromFace(world, pos, event.getFace(), drop);
                                }
                            } else {
                                Block.dropResources(blockClicked, world, event.getPos());
                            }
                            event.setResult(Event.Result.ALLOW);
                        }
                    }
                }
            }
        }
    }

    public BlockState withAge(BlockState state, IntegerProperty property, int age) {
        return state.setValue(property, Integer.valueOf(age));
    }
}
