package com.epherical.croptopia.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public class GlintReferenceItem extends ReferenceItem {

    public GlintReferenceItem(Properties properties, Component component) {
        super(properties, component);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}
