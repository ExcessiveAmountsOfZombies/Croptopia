package me.thonk.croptopia.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;

public class DamageDurabilitySerializer extends ShapelessRecipe.Serializer {


    @Override
    public DamageDurabilityRecipe read(Identifier identifier, JsonObject jsonObject) {
        String string = JsonHelper.getString(jsonObject, "group", "");
        DefaultedList<Ingredient> defaultedList = getIngredients(JsonHelper.getArray(jsonObject, "ingredients"));
        if (defaultedList.isEmpty()) {
            throw new JsonParseException("No ingredients for shapeless recipe");
        } else if (defaultedList.size() > 9) {
            throw new JsonParseException("Too many ingredients for shapeless recipe");
        } else {
            ItemStack itemStack = ShapedRecipe.getItemStack(JsonHelper.getObject(jsonObject, "result"));
            return new DamageDurabilityRecipe(identifier, string, itemStack, defaultedList);
        }
    }

    private static DefaultedList<Ingredient> getIngredients(JsonArray json) {
        DefaultedList<Ingredient> defaultedList = DefaultedList.of();

        for(int i = 0; i < json.size(); ++i) {
            Ingredient ingredient = Ingredient.fromJson(json.get(i));
            if (!ingredient.isEmpty()) {
                defaultedList.add(ingredient);
            }
        }

        return defaultedList;
    }

    @Override
    public DamageDurabilityRecipe read(Identifier identifier, PacketByteBuf packetByteBuf) {
        String string = packetByteBuf.readString(32767);
        int i = packetByteBuf.readVarInt();
        DefaultedList<Ingredient> defaultedList = DefaultedList.ofSize(i, Ingredient.EMPTY);

        for(int j = 0; j < defaultedList.size(); ++j) {
            defaultedList.set(j, Ingredient.fromPacket(packetByteBuf));
        }

        ItemStack itemStack = packetByteBuf.readItemStack();
        return new DamageDurabilityRecipe(identifier, string, itemStack, defaultedList);
    }
}
