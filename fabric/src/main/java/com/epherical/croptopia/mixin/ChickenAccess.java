package com.epherical.croptopia.mixin;

import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.item.crafting.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;


@Mixin(Chicken.class)
public interface ChickenAccess {

    @Accessor("FOOD_ITEMS")
    static Ingredient getFoodItems() {
        throw new AssertionError();
    }

    @Accessor("FOOD_ITEMS") @Mutable
    static void setFoodItems(Ingredient items) {
        throw new AssertionError();
    }



}
