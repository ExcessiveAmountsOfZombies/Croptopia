package me.thonk.croptopia.items;


import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

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
