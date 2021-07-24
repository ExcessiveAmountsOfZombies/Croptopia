package com.epherical.croptopia.mixin;

import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;
import java.util.Set;

@Mixin(VillagerEntity.class)
public interface VillagerAccess {

    @Accessor("GATHERABLE_ITEMS")
    static Set<Item> getGatherableItems() {
        throw new AssertionError();
    }

    @Accessor("GATHERABLE_ITEMS")
    static void setGatherableItems(Set<Item> items) {
        throw new AssertionError();
    }


    @Accessor("ITEM_FOOD_VALUES")
    static Map<Item, Integer> getItemFoodValues() {
        throw new AssertionError();
    }

    @Accessor("ITEM_FOOD_VALUES")
    static void setItemFoodValues(Map<Item, Integer> food) {
        throw new AssertionError();
    }
}
