package com.epherical.croptopia.listeners;

import com.epherical.croptopia.register.Content;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Random;

public class BlockBreakEvent {

    private static final Random random = new Random();


    @SubscribeEvent
    public void onInteractionWithTool(BlockEvent.BlockToolInteractEvent event) {
        BlockState state = event.getState();
        if (state.is(Content.CINNAMON.getLog()) || state.is(Content.CINNAMON.getWood())) {
            if (!event.getPlayer().isCreative()) {
                Block.popResource(event.getPlayer().level, event.getPos(), new ItemStack(Content.CINNAMON));
            }
            if (state.is((Content.CINNAMON.getLog()))) {
                event.setFinalState(Content.CINNAMON.getStrippedLog().withPropertiesOf(state));
            } else {
                event.setFinalState(Content.CINNAMON.getStrippedWood().withPropertiesOf(state));
            }
        }
    }
}
