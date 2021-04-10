package me.thonk.croptopia.mixin;

import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;
import java.util.Set;

@Mixin(VillagerEntity.class)
public interface VillagerAccess {


    @Accessor("ALLOWED_INVENTORY_ITEMS")
    static Set<Item> getGatherableItems() {
        throw new AssertionError();
    }

    @Accessor("ALLOWED_INVENTORY_ITEMS")
    static void setGatherableItems(Set<Item> items) {
        throw new AssertionError();
    }


    @Accessor("FOOD_VALUES")
    static Map<Item, Integer> getItemFoodValues() {
        throw new AssertionError();
    }

    @Accessor("FOOD_VALUES")
    static void setItemFoodValues(Map<Item, Integer> food) {
        throw new AssertionError();
    }

}
