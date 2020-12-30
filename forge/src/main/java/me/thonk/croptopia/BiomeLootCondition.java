package me.thonk.croptopia;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.biome.Biome;


public class BiomeLootCondition implements ILootCondition {

    private final Biome.Category biomeCategory;

    private BiomeLootCondition(Biome.Category category) {
        this.biomeCategory = category;
    }


    @Override
    public LootConditionType func_230419_b_() {
        return CroptopiaForge.BIOME_CHECK;
    }

    @Override
    public boolean test(LootContext lootContext) {
        if (biomeCategory == Biome.Category.NONE) {
            return true;
        }
        Vector3d vec3d = lootContext.get(LootParameters.field_237457_g_);
        if (vec3d != null) {
            //BuiltinRegistries.BIOME.get("key");
            Biome biome = lootContext.getWorld().getBiome(new BlockPos(vec3d));
            return biome.getCategory() == biomeCategory;
        }
        return false;
    }

    public static Builder builder(Biome.Category category) {
        return new Builder(category);
    }



    public static class Serializer implements ILootSerializer<BiomeLootCondition> {
        public Serializer() {

        }

        @Override
        public void serialize(JsonObject json, BiomeLootCondition object, JsonSerializationContext context) {
            json.addProperty("biome_category", object.biomeCategory.getString());
        }

        @Override
        public BiomeLootCondition deserialize(JsonObject json, JsonDeserializationContext context) {
            Biome.Category category = Biome.Category.byName(JSONUtils.getString(json, "biome_category"));
            return new BiomeLootCondition(category);
        }
    }

    public static class Builder implements ILootCondition.IBuilder {
        private final Biome.Category biomeCategory;

        public Builder(Biome.Category biome) {
            this.biomeCategory = biome;
        }

        @Override
        public ILootCondition build() {
            return new BiomeLootCondition(biomeCategory);
        }
    }
}
