package com.epherical.croptopia.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Random;

public class ReferenceInfiniteItem extends Item {

    private final Component[] component;
    private int index = 0;

    private final Random random = new Random();
    public ReferenceInfiniteItem(Properties properties, Component... component) {
        super(properties);
        this.component = component;
        this.index = random.nextInt(component.length);
    }


    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        FoodProperties foodproperties = stack.getFoodProperties(livingEntity);
        ItemStack itemStack = stack.transmuteCopy(stack.getItem());
        livingEntity.eat(level, itemStack, foodproperties);
        return stack;
    }

    @Override
    public void appendHoverText(ItemStack item, TooltipContext level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(item, level, tooltip, flag);
        tooltip.add(component[index]);
    }
}
