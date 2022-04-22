package com.epherical.croptopia;

import com.epherical.croptopia.common.PlatformAdapter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ForgeAdapter implements PlatformAdapter<ForgeAdapter> {
    @Override
    public void invokeDrinkEvent(ItemStack stack, Player player) {

    }

    @Override
    public void afterBlockBroken(Level level, Player player, BlockPos pos, BlockState state, BlockEntity entity) {

    }

    @Override
    public CreativeModeTab getTab() {
        return CroptopiaForge.CROPTOPIA_ITEM_GROUP;
    }

    @Override
    public boolean skipHarvest() {
        return true;
    }
}
