package com.epherical.croptopia.loot;

import com.google.gson.JsonObject;
import com.sun.org.apache.bcel.internal.classfile.ConstantValue;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.BinomialRange;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.functions.ILootFunction;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

public class EntityModifier extends LootModifier {

    private final LootPool pool;
    private final Item item;
    private final int weight;
    private final int min;
    private final int max;

    /**
     * Constructs a LootModifier.
     *
     * @param conditionsIn the ILootConditions that need to be matched before the loot is modified.
     */
    protected EntityModifier(ILootCondition[] conditionsIn, Item item, int weight, int min, int max) {
        super(conditionsIn);
        this.item = item;
        this.weight = weight;
        this.min = min;
        this.max = max;
        LootPool.Builder builder = LootPool.lootPool();
        builder.setRolls(ConstantRange.exactly(1));
        builder.add(ItemLootEntry.lootTableItem(item)
                .setWeight(weight)
                .apply(SetCount.setCount(BinomialRange.binomial(min, max))));
        pool = builder.build();
    }

    @NotNull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        pool.addRandomItems(generatedLoot::add, context);
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<EntityModifier> {

        @Override
        public EntityModifier read(ResourceLocation location, JsonObject object, ILootCondition[] ailootcondition) {
            Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(JSONUtils.getAsString(object, "item", "minecraft:air")));
            int weight = JSONUtils.getAsInt(object, "weight", 1);
            int min = JSONUtils.getAsInt(object, "min", 0);
            int max = JSONUtils.getAsInt(object, "max", 1);
            return new EntityModifier(ailootcondition, item, weight, min, max);
        }

        @Override
        public JsonObject write(EntityModifier instance) {
            return makeConditions(instance.conditions);
        }
    }
}
