package com.epherical.croptopia.register.helpers;

import com.epherical.croptopia.CroptopiaMod;
import com.epherical.croptopia.items.Drink;
import com.epherical.croptopia.util.RegisterFunction;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import java.util.HashSet;
import java.util.Set;

import static com.epherical.croptopia.CroptopiaMod.createGroup;
import static com.epherical.croptopia.util.FoodConstructor.*;

public class Smoothie implements ItemLike {
    private static final Set<Smoothie> ITEMS = new HashSet<>();

    private final String name;
    private final boolean sweet;
    private final Item item;

    public Smoothie(String name, boolean sweet) {
        this.sweet = sweet; // property not yet used, will be used in upcoming saturation overhaul
        this.name = name;
        item = new Drink(createGroup().food(createBuilder(REG_7).alwaysEat().build()));
        ITEMS.add(this);
    }

    public Smoothie(String name) {
        this(name, true);
    }

    @Override
    public Item asItem() {
        return item;
    }

    public static void registerItems(RegisterFunction<Item> register) {
        for (Smoothie item : ITEMS) {
            register.register(CroptopiaMod.createIdentifier(item.name), item.item);
        }
    }
}
