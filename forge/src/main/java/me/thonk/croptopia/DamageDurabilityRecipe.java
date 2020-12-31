package me.thonk.croptopia;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mojang.realmsclient.util.JsonUtils;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.item.crafting.ShapelessRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public class DamageDurabilityRecipe extends ShapelessRecipe {

    public DamageDurabilityRecipe(ResourceLocation id, String group, ItemStack output, NonNullList<Ingredient> list) {
        super(id, group, output, list);
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInventory inventory) {
        NonNullList<ItemStack> defaultedList = NonNullList.withSize(inventory.getSizeInventory(), ItemStack.EMPTY);

        for(int i = 0; i < defaultedList.size(); ++i) {
            ItemStack itemStack = inventory.getStackInSlot(i);
            if (itemStack.isDamageable()) {
                int newDamage = itemStack.getDamage() + 1;
                if (newDamage < itemStack.getMaxDamage()) {
                    itemStack = itemStack.copy();
                    itemStack.setDamage(newDamage);
                    defaultedList.set(i, itemStack);
                }
            }
            if (itemStack.hasContainerItem()) {
                defaultedList.set(i, itemStack.getContainerItem());
            }
        }

        return defaultedList;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return CroptopiaForge.DAMAGE_DURABILITY;
    }

    public static class DamageDurabilitySerializer extends ShapelessRecipe.Serializer {


        @Override
        public DamageDurabilityRecipe read(ResourceLocation identifier, JsonObject jsonObject) {
            String string = JSONUtils.getString(jsonObject, "group", "");
            NonNullList<Ingredient> defaultedList = getIngredients(JSONUtils.getJsonArray(jsonObject, "ingredients"));
            if (defaultedList.isEmpty()) {
                throw new JsonParseException("No ingredients for shapeless recipe");
            } else if (defaultedList.size() > 9) {
                throw new JsonParseException("Too many ingredients for shapeless recipe");
            } else {
                ItemStack itemStack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(jsonObject, "result"));
                return new DamageDurabilityRecipe(identifier, string, itemStack, defaultedList);
            }
        }

        private static NonNullList<Ingredient> getIngredients(JsonArray json) {
            NonNullList<Ingredient> defaultedList = NonNullList.create();

            for(int i = 0; i < json.size(); ++i) {
                Ingredient ingredient = Ingredient.deserialize(json.get(i));
                if (!ingredient.hasNoMatchingItems()) {
                    defaultedList.add(ingredient);
                }
            }

            return defaultedList;
        }

        @Override
        public DamageDurabilityRecipe read(ResourceLocation identifier, PacketBuffer packetByteBuf) {
            String string = packetByteBuf.readString(32767);
            int i = packetByteBuf.readVarInt();
            NonNullList<Ingredient> defaultedList = NonNullList.withSize(i, Ingredient.EMPTY);

            for(int j = 0; j < defaultedList.size(); ++j) {
                defaultedList.set(j, Ingredient.read(packetByteBuf));
            }

            ItemStack itemStack = packetByteBuf.readItemStack();
            return new DamageDurabilityRecipe(identifier, string, itemStack, defaultedList);
        }
    }

}
