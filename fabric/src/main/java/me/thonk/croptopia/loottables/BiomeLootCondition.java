package me.thonk.croptopia.loottables;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import me.thonk.croptopia.Croptopia;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraft.world.phys.Vec3;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class BiomeLootCondition implements LootItemCondition {

    private final Collection<Biome.BiomeCategory> biomeCategory;

    private BiomeLootCondition(Collection<Biome.BiomeCategory> category) {
        this.biomeCategory = category;
    }


    @Override
    public LootItemConditionType getType() {
        return Croptopia.BIOME_CHECK;
    }

    @Override
    public boolean test(LootContext lootContext) {
        if (biomeCategory.size() == 0) {
            return true;
        }
        Vec3 vec3d = lootContext.getParamOrNull(LootContextParams.ORIGIN);
        if (vec3d != null) {
            Holder<Biome> biome = lootContext.getLevel().getBiome(new BlockPos(vec3d));
            // TODO: this is probably going to go away
            return biomeCategory.contains(Biome.getBiomeCategory(biome));
        }
        return false;
    }

    public static Builder builder(Collection<Biome.BiomeCategory> category) {
        return new Builder(category);
    }



    public static class Serializer implements net.minecraft.world.level.storage.loot.Serializer<BiomeLootCondition> {
        public Serializer() {

        }

        @Override
        public void serialize(JsonObject json, BiomeLootCondition object, JsonSerializationContext context) {
            JsonArray array = new JsonArray();
            for (Biome.BiomeCategory category : object.biomeCategory) {
                array.add(category.getSerializedName());
            }
            json.add("biome_category", array);
        }

        @Override
        public BiomeLootCondition deserialize(JsonObject json, JsonDeserializationContext context) {
            JsonArray array = GsonHelper.getAsJsonArray(json, "biome_category");
            Set<Biome.BiomeCategory> categories = new HashSet<>();
            for (JsonElement jsonElement : array) {
                categories.add(Biome.BiomeCategory.byName(json.getAsString()));
            }
            return new BiomeLootCondition(categories);
        }
    }

    public static class Builder implements LootItemCondition.Builder {
        private final Collection<Biome.BiomeCategory> biomeCategory;

        public Builder(Collection<Biome.BiomeCategory> biome) {
            this.biomeCategory = biome;
        }

        @Override
        public LootItemCondition build() {
            return new BiomeLootCondition(biomeCategory);
        }
    }
}
