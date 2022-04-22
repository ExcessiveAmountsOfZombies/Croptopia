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

public class Jam implements ItemLike {
    private static final Set<Jam> ITEMS = new HashSet<>();

    private final String name;
    private final Item item;

    public Jam(String name) {
        this.name = name;
        item = new Drink(createGroup().food(createBuilder(REG_3).alwaysEat().build()));
        ITEMS.add(this);
    }

    @Override
    public Item asItem() {
        return item;
    }

    public static void registerItems(RegisterFunction<Item> register) {
        for (Jam item : ITEMS) {
            register.register(CroptopiaMod.createIdentifier(item.name), item.item);
        }
    }
}
