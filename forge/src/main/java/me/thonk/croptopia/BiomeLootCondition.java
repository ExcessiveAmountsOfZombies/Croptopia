package me.thonk.croptopia;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.mojang.math.Vector3d;
import net.minecraft.core.BlockPos;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraft.world.phys.Vec3;


public class BiomeLootCondition implements LootItemCondition {

    private final Biome.BiomeCategory biomeCategory;

    private BiomeLootCondition(Biome.BiomeCategory category) {
        this.biomeCategory = category;
    }


    @Override
    public LootItemConditionType getType() {
        return CroptopiaForge.BIOME_CHECK;
    }

    @Override
    public boolean test(LootContext lootContext) {
        if (biomeCategory == Biome.BiomeCategory.NONE) {
            return true;
        }
        Vec3 vec3d = lootContext.getParam(LootContextParams.ORIGIN);
        if (vec3d != null) {
            //BuiltinRegistries.BIOME.get("key");
            Biome biome = lootContext.getLevel().getBiome(new BlockPos(vec3d));
            return biome.getBiomeCategory() == biomeCategory;
        }
        return false;
    }

    public static Builder builder(Biome.BiomeCategory category) {
        return new Builder(category);
    }


    public static class Serializer implements net.minecraft.world.level.storage.loot.Serializer<BiomeLootCondition> {
        public Serializer() {

        }

        @Override
        public void serialize(JsonObject json, BiomeLootCondition object, JsonSerializationContext context) {
            json.addProperty("biome_category", object.biomeCategory.getName());
        }

        @Override
        public BiomeLootCondition deserialize(JsonObject json, JsonDeserializationContext context) {
            Biome.BiomeCategory category = Biome.BiomeCategory.byName(GsonHelper.convertToString(json, "biome_category"));
            return new BiomeLootCondition(category);
        }
    }

    public static class Builder implements LootItemCondition.Builder {
        private final Biome.BiomeCategory biomeCategory;

        public Builder(Biome.BiomeCategory biome) {
            this.biomeCategory = biome;
        }

        @Override
        public LootItemCondition build() {
            return new BiomeLootCondition(biomeCategory);
        }
    }
}
