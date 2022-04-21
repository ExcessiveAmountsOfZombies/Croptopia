package com.epherical.croptopia.common;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public interface PlatformAdapter<T> {

    void invokeDrinkEvent(ItemStack stack, Player player);

    void afterBlockBroken(Level level, Player player, BlockPos pos, BlockState state, BlockEntity entity);

    CreativeModeTab getTab();
}
