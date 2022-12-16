package com.epherical.croptopia.register.helpers;

import com.epherical.croptopia.CroptopiaMod;
import com.epherical.croptopia.util.ItemConvertibleWithPlural;
import com.epherical.croptopia.util.RegisterFunction;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.List;

import static com.epherical.croptopia.CroptopiaMod.createGroup;
import static com.epherical.croptopia.util.FoodConstructor.*;

public class IceCream implements ItemLike {
    private static final List<IceCream> INSTANCES = new ArrayList<>();

    private final String name;
    private final ItemConvertibleWithPlural crop;
    private final Item item;

    public IceCream(String name, ItemConvertibleWithPlural crop) {
        this.name = name;
        this.crop = crop;
        this.item = new Item(createGroup().food(createFood(ICE_CREAM_7)));
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

    public static List<IceCream> copy() {
        return INSTANCES;
    }

}
