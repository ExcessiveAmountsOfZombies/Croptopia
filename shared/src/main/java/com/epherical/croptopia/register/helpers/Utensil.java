package com.epherical.croptopia.register.helpers;

import com.epherical.croptopia.CroptopiaMod;
import com.epherical.croptopia.items.CookingUtensil;
import com.epherical.croptopia.util.ItemConvertibleWithPlural;
import com.epherical.croptopia.util.RegisterFunction;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;

import static com.epherical.croptopia.CroptopiaMod.createGroup;

public class Utensil implements ItemConvertibleWithPlural {

    private static final List<Utensil> UTENSILS = new ArrayList<>();

    private final String name;
    private final boolean plural;
    private final Item utensil;


    public Utensil(String name, boolean plural) {
        this.name = name;
        this.plural = plural;
        this.utensil = new CookingUtensil(createGroup().stacksTo(1));
        UTENSILS.add(this);
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
        return utensil;
    }

    public static List<Utensil> copy() {
        return UTENSILS;
    }

    public static void registerItems(RegisterFunction<Item> register) {
        for (Utensil utensil : UTENSILS) {
            register.register(CroptopiaMod.createIdentifier(utensil.name), utensil.utensil);
        }
    }
}
