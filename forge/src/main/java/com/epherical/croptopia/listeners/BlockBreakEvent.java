package com.epherical.croptopia.listeners;

import com.epherical.croptopia.register.Content;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BlockBreakEvent {

    @SubscribeEvent
    public void onInteractionWithTool(BlockEvent.BlockToolModificationEvent event) {
        Player player = event.getPlayer();
        if (!event.isSimulated() && event.getHeldItemStack().getItem() instanceof AxeItem) {
            BlockState state = event.getState();
            if (state.is(Content.CINNAMON.getLog()) || state.is(Content.CINNAMON.getWood())) {
                if (player != null && !player.isCreative()) {
                    Block.popResource(player.level(), event.getPos(), new ItemStack(Content.CINNAMON));
                }
                if (state.is((Content.CINNAMON.getLog()))) {
                    event.setFinalState(Content.CINNAMON.getStrippedLog().withPropertiesOf(state));
                } else {
                    event.setFinalState(Content.CINNAMON.getStrippedWood().withPropertiesOf(state));
                }
            }
        }

    }
}
