package me.thonk.croptopia.recipe;

import me.thonk.croptopia.Croptopia;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;

public class DamageDurabilityRecipe extends ShapelessRecipe {


    public DamageDurabilityRecipe(Identifier id, String group, ItemStack output, DefaultedList<Ingredient> list) {
        super(id, group, output, list);
    }

    @Override
    public DefaultedList<ItemStack> getRemainingStacks(CraftingInventory inventory) {
        DefaultedList<ItemStack> defaultedList = DefaultedList.ofSize(inventory.size(), ItemStack.EMPTY);

        for(int i = 0; i < defaultedList.size(); ++i) {
            ItemStack itemStack = inventory.getStack(i);
            if (itemStack.isDamageable()) {
                int newDamage = itemStack.getDamage() + 1;
                if (newDamage < itemStack.getMaxDamage()) {
                    itemStack = itemStack.copy();
                    itemStack.setDamage(newDamage);
                    defaultedList.set(i, itemStack);
                }
            }
            if (itemStack.getItem().hasRecipeRemainder()) {
                defaultedList.set(i, new ItemStack(itemStack.getItem().getRecipeRemainder()));
            }
        }

        return defaultedList;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Croptopia.DAMAGE_DURABILITY;
    }
}
