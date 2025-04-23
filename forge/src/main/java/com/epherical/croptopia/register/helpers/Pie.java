package com.epherical.croptopia.register.helpers;

import com.epherical.croptopia.CroptopiaMod;
import com.epherical.croptopia.register.Content;
import com.epherical.croptopia.util.ItemConvertibleWithPlural;
import com.epherical.croptopia.util.RegisterFunction;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.List;

import static com.epherical.croptopia.CroptopiaMod.createGroup;
import static com.epherical.croptopia.util.FoodConstructor.PIE_10;
import static com.epherical.croptopia.util.FoodConstructor.createFood;

public class Pie implements ItemLike {
    private static final List<Pie> INSTANCES = new ArrayList<>();

    private final String name;
    private final ItemConvertibleWithPlural crop;
    private Item item;

    public Pie(String name, ItemConvertibleWithPlural crop) {
        Content.ITEM_REGISTER.reg(this::registerItem);
        this.name = name;
        this.crop = crop;
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

    public void registerItem(RegisterFunction<Item> register) {
        this.item = register.register(CroptopiaMod.createIdentifier(name), () -> new Item(createGroup().food(createFood(PIE_10))));
    }

    public static List<Pie> copy() {
        return INSTANCES;
    }
}
