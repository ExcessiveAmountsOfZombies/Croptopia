package com.epherical.croptopia.register.helpers;

import com.epherical.croptopia.CroptopiaMod;
import com.epherical.croptopia.items.Drink;
import com.epherical.croptopia.util.ItemConvertibleWithPlural;
import com.epherical.croptopia.util.RegisterFunction;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;

import java.util.ArrayList;
import java.util.List;

import static com.epherical.croptopia.CroptopiaMod.createGroup;
import static com.epherical.croptopia.util.FoodConstructor.REG_3;
import static com.epherical.croptopia.util.FoodConstructor.createBuilder;

public class Jam implements IItemProvider {
    private static final List<Jam> INSTANCES = new ArrayList<>();

    private final String name;
    private final ItemConvertibleWithPlural crop;
    private final Item item;

    public Jam(String name, ItemConvertibleWithPlural crop) {
        this.name = name;
        this.crop = crop;
        item = new Drink(createGroup().food(createBuilder(REG_3).alwaysEat().build()));
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
        for (Jam item : INSTANCES) {
            register.register(CroptopiaMod.createIdentifier(item.name), item.item);
        }
    }

    public static List<Jam> copy() {
        return INSTANCES;
    }
}
