package com.epherical.croptopia.events;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class HarvestEvent extends PlayerEvent {

    private final BlockState currentState;
    private BlockState turnedState;

    public HarvestEvent(Player player, BlockState currentState, BlockState turnedState) {
        super(player);
        this.currentState = currentState;
        this.turnedState = turnedState;
    }

    public BlockState getCurrentState() {
        return currentState;
    }

    public BlockState getTurnedState() {
        return turnedState;
    }

    public void setTurnedState(BlockState turnedState) {
        this.turnedState = turnedState;
    }
}
