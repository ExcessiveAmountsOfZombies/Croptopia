package com.epherical.croptopia.register.helpers;

import com.epherical.croptopia.CroptopiaMod;
import com.epherical.croptopia.util.FoodConstructor;
import com.epherical.croptopia.util.ItemConvertibleWithPlural;
import com.epherical.croptopia.util.RegisterFunction;
import net.minecraft.world.item.Item;

import java.util.HashSet;
import java.util.Set;

import static com.epherical.croptopia.CroptopiaMod.createGroup;

public class Furnace extends AbstractContent implements ItemConvertibleWithPlural {
    private static final Set<Furnace> ITEMS = new HashSet<>();

    private final String name;
    private final boolean plural;
    private final Item item;


    public Furnace(String name, boolean plural, FoodConstructor foodConstructor) {
        super();
        this.name = name;
        this.plural = plural;
        if (foodConstructor == null) {
            this.item = new Item(createGroup());
        } else {
            this.item = new Item(createGroup().food(FoodConstructor.createFood(foodConstructor)));
        }
        ITEMS.add(this);
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public boolean hasPlural() {
        return plural;
    }

    @Override
    public Item asItem() {
        return item;
    }

    public static void registerItems(RegisterFunction<Item> register) {
        for (Furnace item : ITEMS) {
            register.register(CroptopiaMod.createIdentifier(item.name), item.item);
        }
    }
}
