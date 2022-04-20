package me.thonk.croptopia.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;
import java.util.Set;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.Item;

@Mixin(Villager.class)
public interface VillagerAccess {

    @Accessor("WANTED_ITEMS")
    static Set<Item> getGatherableItems() {
        throw new AssertionError();
    }

    @Accessor("WANTED_ITEMS")
    static void setGatherableItems(Set<Item> items) {
        throw new AssertionError();
    }


    @Accessor("FOOD_POINTS")
    static Map<Item, Integer> getItemFoodValues() {
        throw new AssertionError();
    }

    @Accessor("FOOD_POINTS")
    static void setItemFoodValues(Map<Item, Integer> food) {
        throw new AssertionError();
    }
}
