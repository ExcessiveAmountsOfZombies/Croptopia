package com.epherical.croptopia.register.helpers;

import com.epherical.croptopia.CroptopiaMod;
import com.epherical.croptopia.util.RegisterFunction;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import java.util.HashSet;
import java.util.Set;

import static com.epherical.croptopia.CroptopiaMod.createGroup;
import static com.epherical.croptopia.util.FoodConstructor.REG_10;
import static com.epherical.croptopia.util.FoodConstructor.createFood;

public class IceCream implements ItemLike {
    private static final Set<IceCream> ITEMS = new HashSet<>();

    private final String name;
    private final Item item;

    public IceCream(String name) {
        this.name = name;
        this.item = new Item(createGroup().food(createFood(REG_10)));
        ITEMS.add(this);
    }

    @Override
    public Item asItem() {
        return item;
    }

    public static void registerItems(RegisterFunction<Item> register) {
        for (IceCream item : ITEMS) {
            register.register(CroptopiaMod.createIdentifier(item.name), item.item);
        }
    }

}
