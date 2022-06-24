package com.epherical.croptopia.loot;

import com.epherical.croptopia.CroptopiaMod;
import com.epherical.croptopia.items.SeedItem;
import com.google.gson.JsonObject;
import com.sun.org.apache.bcel.internal.classfile.ConstantValue;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.BinomialRange;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

import javax.annotation.Nonnull;
import java.util.List;

public class SpawnChestModifier extends LootModifier {


    private final LootPool table;

    /**
     * Constructs a LootModifier.
     *
     * @param conditionsIn the LootItemCondition that need to be matched before the loot is modified.
     */
    protected SpawnChestModifier(ILootCondition[] conditionsIn) {
        super(conditionsIn);
        LootPool.Builder builder = new LootPool.Builder();
        builder.setRolls(ConstantRange.exactly(1));
        for (SeedItem seed : CroptopiaMod.seeds) {
            builder.add(ItemLootEntry.lootTableItem(seed)
                    .setWeight(2)
                    .apply(SetCount.setCount(BinomialRange.binomial(3, 8)))
            );
        }
        table = builder.build();
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        table.addRandomItems(generatedLoot::add, context);
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<SpawnChestModifier> {

        @Override
        public SpawnChestModifier read(ResourceLocation location, JsonObject object, ILootCondition[] ailootcondition) {
            return new SpawnChestModifier(ailootcondition);
        }

        @Override
        public JsonObject write(SpawnChestModifier instance) {
            return makeConditions(instance.conditions);
        }
    }
}
