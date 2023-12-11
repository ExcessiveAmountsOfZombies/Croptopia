package com.epherical.croptopia;

import com.epherical.croptopia.common.PlatformAdapter;
import com.epherical.croptopia.register.Content;
import com.epherical.epherolib.ForgePlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ForgeAdapter extends ForgePlatform implements PlatformAdapter<ForgeAdapter> {
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

    @Override
    public void registerFlammableBlocks() {
        FireBlock fire = (FireBlock) Blocks.FIRE;
        fire.setFlammable(Content.CINNAMON.getLog(), 5, 5);
        fire.setFlammable(Content.CINNAMON.getWood(), 5, 5);
        fire.setFlammable(Content.CINNAMON.getStrippedLog(), 5, 5);
        fire.setFlammable(Content.CINNAMON.getStrippedWood(), 5, 5);
    }

    @Override
    public ForgeAdapter getPlatform() {
        return this;
    }
}
