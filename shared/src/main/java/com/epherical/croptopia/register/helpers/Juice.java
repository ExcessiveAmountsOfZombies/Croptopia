package com.epherical.croptopia.register.helpers;

import com.epherical.croptopia.CroptopiaMod;
import com.epherical.croptopia.items.Drink;
import com.epherical.croptopia.util.RegisterFunction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.HashSet;
import java.util.Set;

import static com.epherical.croptopia.CroptopiaMod.createGroup;
import static com.epherical.croptopia.util.FoodConstructor.REG_5;
import static com.epherical.croptopia.util.FoodConstructor.createBuilder;

public class Juice implements ItemLike {
    private static final Set<Juice> ITEMS = new HashSet<>();

    private final String name;
    private final boolean sweet;
    private final Item item;

    public Juice(String name, boolean sweet) {
        this.sweet = sweet; // property not yet used, will be used in upcoming saturation overhaul
        this.name = name;
        item = new Drink(createGroup().food(createBuilder(REG_5).alwaysEat().build()).craftRemainder(Items.GLASS_BOTTLE));
        ITEMS.add(this);
    }

    public Juice(String name) {
        this(name, true);
    }

    @Override
    public Item asItem() {
        return item;
    }

    public static void registerItems(RegisterFunction<Item> register) {
        for (Juice item : ITEMS) {
            register.register(CroptopiaMod.createIdentifier(item.name), item.item);
        }
    }
}
