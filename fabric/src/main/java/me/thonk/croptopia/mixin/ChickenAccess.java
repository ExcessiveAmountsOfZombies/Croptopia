package me.thonk.croptopia.mixin;

import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.recipe.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;


@Mixin(ChickenEntity.class)
public interface ChickenAccess {

    @Accessor("BREEDING_INGREDIENT")
    static Ingredient getBreedingIngredients() {
        throw new AssertionError();
    }

    @Accessor("BREEDING_INGREDIENT")
    static void setBreedingIngredients(Ingredient items) {
        throw new AssertionError();
    }



}
