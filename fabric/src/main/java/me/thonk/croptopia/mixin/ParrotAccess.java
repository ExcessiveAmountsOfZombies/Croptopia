package me.thonk.croptopia.mixin;

import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Set;

@Mixin(ParrotEntity.class)
public interface ParrotAccess {

    @Accessor("TAMING_INGREDIENTS")
    static Set<Item> getTamingIngredients() {
        throw new AssertionError();
    }

    @Accessor("TAMING_INGREDIENTS")
    static void setTamingIngredients(Set<Item> items) {
        throw new AssertionError();
    }

}
