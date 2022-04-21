package com.epherical.croptopia.events;

import com.epherical.croptopia.registry.BlockRegistry;
import com.epherical.croptopia.registry.ItemRegistry;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Random;

public class BlockBreakEvent {

    private static final Random random = new Random();


    @SubscribeEvent
    public void onInteractionWithTool(BlockEvent.BlockToolInteractEvent event) {
        /*BlockState state = event.getState();
        if (state.is(BlockRegistry.cinnamonLog) || state.is(BlockRegistry.cinnamonWood)) {
            if (!event.getPlayer().isCreative()) {
                Block.popResource(event.getPlayer().level, event.getPos(), new ItemStack(ItemRegistry.cinnamon));
            }
            if (state.is(BlockRegistry.cinnamonLog)) {
                event.setFinalState(BlockRegistry.strippedCinnamonLog.withPropertiesOf(state));
            } else {
                event.setFinalState(BlockRegistry.strippedCinnamonWood.withPropertiesOf(state));
            }
        }*/
    }
}
