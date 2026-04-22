package com.epherical.croptopia.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ReferenceInfiniteItem extends ReferenceItem {

    public ReferenceInfiniteItem(Properties properties, Component component) {
        super(properties, component);
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity livingEntity) {
        ItemStack remainder = stack.copyWithCount(1);
        ItemStack result = super.finishUsingItem(stack, level, livingEntity);
        if (livingEntity instanceof Player player && player.getAbilities().instabuild) {
            return result;
        }
        if (result.isEmpty()) {
            return remainder;
        }
        result.grow(1);
        return result;
    }
}
