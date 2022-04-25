package com.epherical.croptopia.register.helpers;

import com.epherical.croptopia.CroptopiaMod;
import com.epherical.croptopia.items.Drink;
import com.epherical.croptopia.util.ItemConvertibleWithPlural;
import com.epherical.croptopia.util.RegisterFunction;
import com.google.common.collect.ImmutableSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import java.util.HashSet;
import java.util.Set;

import static com.epherical.croptopia.CroptopiaMod.createGroup;
import static com.epherical.croptopia.util.FoodConstructor.*;

public class Smoothie implements ItemLike {
    private static final Set<Smoothie> INSTANCES = new HashSet<>();

    private final String name;
    private final ItemConvertibleWithPlural crop;
    private final boolean sweet;
    private final Item item;

    public Smoothie(String name, ItemConvertibleWithPlural cropItemName, boolean sweet) {
        this.sweet = sweet; // property not yet used, will be used in upcoming saturation overhaul
        this.name = name;
        this.crop = cropItemName;
        item = new Drink(createGroup().food(createBuilder(REG_7).alwaysEat().build()));
        INSTANCES.add(this);
    }

    public Smoothie(String name, ItemConvertibleWithPlural cropItemName) {
        this(name, cropItemName, true);
    }

    public ItemConvertibleWithPlural getCrop() {
        return crop;
    }

    public String name() {
        return name;
    }

    @Override
    public Item asItem() {
        return item;
    }

    public static void registerItems(RegisterFunction<Item> register) {
        for (Smoothie item : INSTANCES) {
            register.register(CroptopiaMod.createIdentifier(item.name), item.item);
        }
    }

    public static Set<Smoothie> copy() {
        return ImmutableSet.copyOf(INSTANCES);
    }
}
