package com.epherical.croptopia.events;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class HarvestEvent extends PlayerEvent {

    private final BlockState currentState;
    private BlockState turnedState;

    public HarvestEvent(PlayerEntity player, BlockState currentState, BlockState turnedState) {
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
