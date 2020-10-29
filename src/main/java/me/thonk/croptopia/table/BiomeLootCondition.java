package me.thonk.croptopia.table;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import me.thonk.croptopia.Croptopia;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.biome.Biome;


public class BiomeLootCondition implements LootCondition {

    private final Biome.Category biomeCategory;

    private BiomeLootCondition(Biome.Category category) {
        this.biomeCategory = category;
    }


    @Override
    public LootConditionType getType() {
        return Croptopia.BIOME_CHECK;
    }

    @Override
    public boolean test(LootContext lootContext) {
        if (biomeCategory == Biome.Category.NONE) {
            return true;
        }
        Vec3d vec3d = lootContext.get(LootContextParameters.ORIGIN);
        if (vec3d != null) {
            //BuiltinRegistries.BIOME.get("key");
            Biome biome = lootContext.getWorld().getBiome(new BlockPos(vec3d));
            return biome.getCategory() == biomeCategory;
        }
        return false;
    }

    public static BiomeLootCondition.Builder builder(Biome.Category category) {
        return new BiomeLootCondition.Builder(category);
    }



    public static class Serializer implements JsonSerializer<BiomeLootCondition> {
        public Serializer() {

        }

        @Override
        public void toJson(JsonObject json, BiomeLootCondition object, JsonSerializationContext context) {
            json.addProperty("biome_category", object.biomeCategory.asString());
        }

        @Override
        public BiomeLootCondition fromJson(JsonObject json, JsonDeserializationContext context) {
            Biome.Category category = Biome.Category.byName(JsonHelper.getString(json, "biome_category"));
            return new BiomeLootCondition(category);
        }
    }

    public static class Builder implements LootCondition.Builder {
        private final Biome.Category biomeCategory;

        public Builder(Biome.Category biome) {
            this.biomeCategory = biome;
        }

        @Override
        public LootCondition build() {
            return new BiomeLootCondition(biomeCategory);
        }
    }
}
