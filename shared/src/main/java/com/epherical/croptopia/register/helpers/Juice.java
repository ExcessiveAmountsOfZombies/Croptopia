package com.epherical.croptopia.register.helpers;

import com.epherical.croptopia.CroptopiaMod;
import com.epherical.croptopia.items.Drink;
import com.epherical.croptopia.util.ItemConvertibleWithPlural;
import com.epherical.croptopia.util.RegisterFunction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.List;

import static com.epherical.croptopia.CroptopiaMod.createGroup;
import static com.epherical.croptopia.util.FoodConstructor.REG_5;
import static com.epherical.croptopia.util.FoodConstructor.createBuilder;

public class Juice implements ItemLike {
    private static final List<Juice> INSTANCES = new ArrayList<>();

    private final String name;
    private final ItemConvertibleWithPlural crop;
    private final boolean sweet;
    private final Item item;

    public Juice(String name, ItemConvertibleWithPlural crop, boolean sweet) {
        this.sweet = sweet; // property not yet used, will be used in upcoming saturation overhaul
        this.name = name;
        this.crop = crop;
        item = new Drink(createGroup().food(createBuilder(REG_5).alwaysEat().build()).craftRemainder(Items.GLASS_BOTTLE));
        INSTANCES.add(this);
    }

    public Juice(String name, ItemConvertibleWithPlural crop) {
        this(name, crop, true);
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
        for (Juice item : INSTANCES) {
            register.register(CroptopiaMod.createIdentifier(item.name), item.item);
        }
    }

    public static List<Juice> copy() {
        return INSTANCES;
    }
}
