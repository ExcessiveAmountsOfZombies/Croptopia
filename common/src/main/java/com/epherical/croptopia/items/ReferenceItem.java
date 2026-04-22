package com.epherical.croptopia.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.Arrays;
import java.util.List;

public class ReferenceItem extends Item {

    private final Component[] components;
    private final boolean randomize;
    private final int index;

    public ReferenceItem(Properties properties, Component... components) {
        super(properties);
        this.components = components;
        this.randomize = components.length > 1;
        this.index = randomize ? net.minecraft.util.RandomSource.create().nextInt(components.length) : 0;
    }

    @Override
    public void appendHoverText(ItemStack item, Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(item, level, tooltip, flag);
        if (randomize) {
            tooltip.add(components[index]);
        } else {
            tooltip.addAll(Arrays.asList(components));
        }
    }
}
