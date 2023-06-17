package com.epherical.croptopia;

import com.epherical.croptopia.common.PlatformAdapter;
import com.epherical.croptopia.event.DrinkEvent;
import com.epherical.croptopia.register.Content;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.impl.content.registry.FlammableBlockRegistryImpl;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class FabricAdapter implements PlatformAdapter<FabricAdapter> {
    @Override
    public void invokeDrinkEvent(ItemStack stack, Player player) {
        DrinkEvent.DRINK.invoker().onDrink(stack, player);
    }

    @Override
    public void afterBlockBroken(Level level, Player player, BlockPos pos, BlockState state, BlockEntity entity) {
        PlayerBlockBreakEvents.AFTER.invoker().afterBlockBreak(level, player, pos, state, entity);
    }

    @Override
    public CreativeModeTab getTab() {
        return Croptopia.CROPTOPIA_ITEM_GROUP;
    }

    @Override
    public boolean skipHarvest() {
        return false;
    }

    @Override
    public void registerFlammableBlocks() {
        FlammableBlockRegistry.getInstance(Blocks.FIRE).add(Content.CINNAMON.getLog(), 5, 5);
        FlammableBlockRegistry.getInstance(Blocks.FIRE).add(Content.CINNAMON.getWood(), 5, 5);
        FlammableBlockRegistry.getInstance(Blocks.FIRE).add(Content.CINNAMON.getStrippedLog(), 5, 5);
        FlammableBlockRegistry.getInstance(Blocks.FIRE).add(Content.CINNAMON.getStrippedWood(), 5, 5);
    }
}
