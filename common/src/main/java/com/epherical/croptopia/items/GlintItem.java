package com.epherical.croptopia.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class GlintItem extends Item {

    public GlintItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}
