package com.epherical.croptopia.items;


import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CookingUtensil extends Item {


    public CookingUtensil(Properties properties) {
        super(properties);
    }


    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        return new ItemStack(itemStack.getItem());
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }
}
