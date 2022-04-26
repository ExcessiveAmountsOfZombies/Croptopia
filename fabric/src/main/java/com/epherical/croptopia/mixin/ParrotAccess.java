package com.epherical.croptopia.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Set;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.item.Item;

@Mixin(Parrot.class)
public interface ParrotAccess {

    @Accessor("TAME_FOOD")
    static Set<Item> getTamingIngredients() {
        throw new AssertionError();
    }

    @Accessor("TAME_FOOD") @Mutable
    static void setTamingIngredients(Set<Item> items) {
        throw new AssertionError();
    }

}
