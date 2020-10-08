package me.thonk.croptopia.recipe;

import com.google.gson.JsonObject;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.util.Identifier;

public class DamageDurabilitySerializer extends ShapelessRecipe.Serializer {


    @Override
    public ShapelessRecipe read(Identifier identifier, JsonObject jsonObject) {
        return new DamageDurabilityRecipe(super.read(identifier, jsonObject));
    }

    @Override
    public ShapelessRecipe read(Identifier identifier, PacketByteBuf packetByteBuf) {
        return new DamageDurabilityRecipe(super.read(identifier, packetByteBuf));
    }
}
