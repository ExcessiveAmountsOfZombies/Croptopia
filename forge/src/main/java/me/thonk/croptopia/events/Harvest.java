package me.thonk.croptopia.events;

import me.thonk.croptopia.config.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;


public class Harvest {


    @SubscribeEvent
    public void onHarvest(PlayerInteractEvent.RightClickBlock event) {
        /*if (Config.canRightClickHarvest) {
            if (!(event.getPlayer().getHeldItemMainhand().getItem() instanceof BoneMealItem)) {
                if (!event.getWorld().isRemote) {
                    World world = event.getWorld();
                    BlockPos pos = event.getPos();
                    BlockState blockClicked = event.getWorld().getBlockState(pos);
                    if (blockClicked.getBlock() instanceof CropsBlock) {
                        if (!event.getPlayer().getHeldItemMainhand().isEmpty()) {
                            event.setCanceled(true);
                        }
                        CropsBlock block = (CropsBlock) blockClicked.getBlock();
                        IntegerProperty property = block.getAgeProperty();
                        int age = blockClicked.get(block.getAgeProperty());
                        if (age == block.getMaxAge()) {
                            world.setBlockState(pos, withAge(blockClicked, property, 0), 2);
                            Block.spawnDrops(blockClicked, world, event.getPos());
                            event.setResult(Event.Result.ALLOW);
                        }
                    }
                }
            }
        }*/
    }

    public BlockState withAge(BlockState state, IntegerProperty property, int age) {
        return state.setValue(property, Integer.valueOf(age));
    }
}
