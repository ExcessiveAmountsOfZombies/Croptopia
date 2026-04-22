package com.epherical.croptopia.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public class GlintReferenceInfiniteItem extends ReferenceInfiniteItem {

    public GlintReferenceInfiniteItem(Properties properties, Component component) {
        super(properties, component);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}
