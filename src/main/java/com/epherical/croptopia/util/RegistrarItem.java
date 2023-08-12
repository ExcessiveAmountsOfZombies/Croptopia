package com.epherical.croptopia.util;

import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public interface RegistrarItem {

    <E extends Item> Supplier<E> registerItem();

}
