package com.epherical.croptopia.common;

import net.minecraft.item.ItemGroup;

public interface PlatformAdapter<T> {

    ItemGroup getTab();

    /**
     * @return Returns true only on forge, should be false otherwise. We use an event for forge, and handle it on the block on fabric.
     */
    boolean skipHarvest();
}
