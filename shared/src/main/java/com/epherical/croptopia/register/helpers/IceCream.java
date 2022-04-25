package com.epherical.croptopia.register.helpers;

import com.epherical.croptopia.CroptopiaMod;
import com.epherical.croptopia.util.ItemConvertibleWithPlural;
import com.epherical.croptopia.util.RegisterFunction;
import com.google.common.collect.ImmutableSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import java.util.HashSet;
import java.util.Set;

import static com.epherical.croptopia.CroptopiaMod.createGroup;
import static com.epherical.croptopia.util.FoodConstructor.REG_10;
import static com.epherical.croptopia.util.FoodConstructor.createFood;

public class IceCream implements ItemLike {
    private static final Set<IceCream> INSTANCES = new HashSet<>();

    private final String name;
    private final ItemConvertibleWithPlural crop;
    private final Item item;

    public IceCream(String name, ItemConvertibleWithPlural crop) {
        this.name = name;
        this.crop = crop;
        this.item = new Item(createGroup().food(createFood(REG_10)));
        INSTANCES.add(this);
    }

    @Override
    public Item asItem() {
        return item;
    }

    public ItemConvertibleWithPlural getCrop() {
        return crop;
    }

    public String name() {
        return name;
    }

    public static void registerItems(RegisterFunction<Item> register) {
        for (IceCream item : INSTANCES) {
            register.register(CroptopiaMod.createIdentifier(item.name), item.item);
        }
    }

    public static Set<IceCream> copy() {
        return ImmutableSet.copyOf(INSTANCES);
    }

}
