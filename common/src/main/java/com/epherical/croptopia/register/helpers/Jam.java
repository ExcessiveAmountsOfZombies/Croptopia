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

public class Jam implements ItemLike {
    private static final List<Jam> INSTANCES = new ArrayList<>();

    private final String name;
    private final ItemConvertibleWithPlural crop;
    private Item item;

    public Jam(String name, ItemConvertibleWithPlural crop) {
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
        item = register.register(CroptopiaMod.createIdentifier(name), () ->
                new Drink(createGroup().craftRemainder(Items.GLASS_BOTTLE).food(createBuilder(JAM_3).alwaysEat().build())));
    }

    public static List<Jam> copy() {
        return INSTANCES;
    }
}
