package com.epherical.croptopia.register.helpers;

import com.epherical.croptopia.CroptopiaMod;
import com.epherical.croptopia.items.Drink;
import com.epherical.croptopia.register.Content;
import com.epherical.croptopia.util.ItemConvertibleWithPlural;
import com.epherical.croptopia.util.RegisterFunction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.List;

import static com.epherical.croptopia.CroptopiaMod.createGroup;
import static com.epherical.croptopia.util.FoodConstructor.*;

public class Juice implements ItemLike {
    private static final List<Juice> INSTANCES = new ArrayList<>();

    private final String name;
    private final ItemConvertibleWithPlural crop;
    private final boolean sweet;
    private Item item;

    public Juice(String name, ItemConvertibleWithPlural crop, boolean sweet) {
        Content.ITEM_REGISTER.reg(this::registerItem);
        this.sweet = sweet; // property not yet used, will be used in upcoming saturation overhaul
        this.name = name;
        this.crop = crop;
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

    public void registerItem(RegisterFunction<Item> register) {
        this.item = register.register(CroptopiaMod.createIdentifier(name), () ->
                new Drink(createGroup().food(createBuilder(JUICE_5).alwaysEat().build()).craftRemainder(Items.GLASS_BOTTLE)));
    }

    public static List<Juice> copy() {
        return INSTANCES;
    }
}
