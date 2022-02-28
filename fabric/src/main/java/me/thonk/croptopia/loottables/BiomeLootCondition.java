package me.thonk.croptopia.loottables;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
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
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.Biome;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class BiomeLootCondition implements LootCondition {

    private final Collection<Biome.Category> biomeCategory;

    private BiomeLootCondition(Collection<Biome.Category> category) {
        this.biomeCategory = category;
    }


    @Override
    public LootConditionType getType() {
        return Croptopia.BIOME_CHECK;
    }

    @Override
    public boolean test(LootContext lootContext) {
        if (biomeCategory.size() == 0) {
            return true;
        }
        Vec3d vec3d = lootContext.get(LootContextParameters.ORIGIN);
        if (vec3d != null) {
            RegistryEntry<Biome> biome = lootContext.getWorld().getBiome(new BlockPos(vec3d));
            // TODO: this is probably going to go away
            return biomeCategory.contains(Biome.getCategory(biome));
        }
        return false;
    }

    public static BiomeLootCondition.Builder builder(Collection<Biome.Category> category) {
        return new BiomeLootCondition.Builder(category);
    }



    public static class Serializer implements JsonSerializer<BiomeLootCondition> {
        public Serializer() {

        }

        @Override
        public void toJson(JsonObject json, BiomeLootCondition object, JsonSerializationContext context) {
            JsonArray array = new JsonArray();
            for (Biome.Category category : object.biomeCategory) {
                array.add(category.asString());
            }
            json.add("biome_category", array);
        }

        @Override
        public BiomeLootCondition fromJson(JsonObject json, JsonDeserializationContext context) {
            JsonArray array = JsonHelper.getArray(json, "biome_category");
            Set<Biome.Category> categories = new HashSet<>();
            for (JsonElement jsonElement : array) {
                categories.add(Biome.Category.byName(json.getAsString()));
            }
            return new BiomeLootCondition(categories);
        }
    }

    public static class Builder implements LootCondition.Builder {
        private final Collection<Biome.Category> biomeCategory;

        public Builder(Collection<Biome.Category> biome) {
            this.biomeCategory = biome;
        }

        @Override
        public LootCondition build() {
            return new BiomeLootCondition(biomeCategory);
        }
    }
}
