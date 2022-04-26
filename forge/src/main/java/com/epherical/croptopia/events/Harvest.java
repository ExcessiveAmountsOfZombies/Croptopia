package com.epherical.croptopia.events;

import com.epherical.croptopia.blocks.LeafCropBlock;
import com.epherical.croptopia.config.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;


public class Harvest {


    @SubscribeEvent
    public void onHarvest(PlayerInteractEvent.RightClickBlock event) {
        if (Config.canRightClickHarvest && !event.getPlayer().isSpectator()) {
            if (!(event.getPlayer().getMainHandItem().getItem() instanceof BoneMealItem)) {
                if (!event.getWorld().isClientSide) {
                    Level world = event.getWorld();
                    BlockPos pos = event.getPos();
                    BlockState blockClicked = event.getWorld().getBlockState(pos);
                    if (blockClicked.getBlock() instanceof CropBlock) {
                        CropBlock block = (CropBlock) blockClicked.getBlock();
                        IntegerProperty property = block.getAgeProperty();
                        int age = blockClicked.getValue(block.getAgeProperty());
                        if (age == block.getMaxAge()) {
                            world.setBlock(pos, withAge(blockClicked, property, 0), 2);
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
