package com.epherical.croptopia.items;


import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class CookingUtensil extends Item {


    public CookingUtensil(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        return new ItemStack(itemStack.getItem());
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }
}
