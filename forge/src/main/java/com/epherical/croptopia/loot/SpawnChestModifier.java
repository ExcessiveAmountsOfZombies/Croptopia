package com.epherical.croptopia.loot;

import com.epherical.croptopia.CroptopiaMod;
import com.epherical.croptopia.items.SeedItem;
import com.google.gson.JsonObject;
import com.epherical.croptopia.CroptopiaForge;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
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
    protected SpawnChestModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
        LootPool.Builder builder = new LootPool.Builder();
        builder.setRolls(ConstantValue.exactly(1));
        builder.setBonusRolls(ConstantValue.exactly(0));
        for (SeedItem seed : CroptopiaMod.seeds) {
            builder.add(LootItem.lootTableItem(seed)
                    .setWeight(2)
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 8), false))
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
        public SpawnChestModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] ailootcondition) {
            return new SpawnChestModifier(ailootcondition);
        }

        @Override
        public JsonObject write(SpawnChestModifier instance) {
            return makeConditions(instance.conditions);
        }
    }
}
