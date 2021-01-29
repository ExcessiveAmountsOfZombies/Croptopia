package me.thonk.croptopia.events;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;


public class Harvest {


    @SubscribeEvent
    public void onHarvest(PlayerInteractEvent.RightClickBlock event) {


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

    public BlockState withAge(BlockState state, IntegerProperty property, int age) {
        return state.getBlockState().with(property, Integer.valueOf(age));
    }
}
