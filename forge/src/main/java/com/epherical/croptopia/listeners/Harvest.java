package com.epherical.croptopia.listeners;

import com.epherical.croptopia.blocks.LeafCropBlock;
import com.epherical.croptopia.config.Config;
import com.epherical.croptopia.events.HarvestEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;


public class Harvest {


    @SubscribeEvent
    public void onHarvest(PlayerInteractEvent.RightClickBlock event) {
        if (Config.canRightClickHarvest && !event.getPlayer().isSpectator()) {
            if (!(event.getPlayer().getMainHandItem().getItem() instanceof BoneMealItem)) {
                if (!event.getWorld().isClientSide) {
                    World world = event.getWorld();
                    BlockPos pos = event.getPos();
                    BlockState blockClicked = event.getWorld().getBlockState(pos);
                    if (blockClicked.getBlock() instanceof CropsBlock) {
                        CropsBlock block = (CropsBlock) blockClicked.getBlock();
                        IntegerProperty property = block.getAgeProperty();
                        int age = blockClicked.getValue(block.getAgeProperty());
                        if (age == block.getMaxAge()) {
                            HarvestEvent harvestedCropEvent = new HarvestEvent(event.getPlayer(), blockClicked, withAge(blockClicked, property, 0));
                            MinecraftForge.EVENT_BUS.post(harvestedCropEvent);
                            world.setBlock(pos, harvestedCropEvent.getTurnedState(), 2);
                            if (blockClicked.getBlock() instanceof LeafCropBlock) {
                                for (ItemStack drop : Block.getDrops(blockClicked, (ServerWorld) world, pos, null)) {
                                    Block.popResource(world, pos, drop);
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
