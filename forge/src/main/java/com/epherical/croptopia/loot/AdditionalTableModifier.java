package com.epherical.croptopia.loot;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.TableLootEntry;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Add an additional LootTable as the modifier.
 */
public class AdditionalTableModifier extends LootModifier {

    private String tableID;
    private final TableLootEntry reference;
    private final float chanceToRefer;

    /**
     * Constructs a LootModifier.
     *
     * @param conditionsIn the ILootConditions that need to be matched before the loot is modified.
     */
    protected AdditionalTableModifier(ILootCondition[] conditionsIn, TableLootEntry reference, String tableID, float chanceToRefer) {
        super(conditionsIn);
        this.chanceToRefer = chanceToRefer;
        this.tableID = tableID;
        this.reference = reference;
    }

    @NotNull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        if (context.getRandom().nextFloat() <= chanceToRefer) {
            List<ItemStack> items = new ArrayList<>();
            reference.createItemStack(items::add, context);
            return items;
        }

        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<AdditionalTableModifier> {

        @Override
        public AdditionalTableModifier read(ResourceLocation location, JsonObject object, ILootCondition[] ailootcondition) {
            String tableID = JSONUtils.getAsString(object, "tableRef");
            TableLootEntry reference = (TableLootEntry) TableLootEntry.lootTableReference(new ResourceLocation(tableID)).build();
            float referChance = JSONUtils.getAsFloat(object, "referChance");
            return new AdditionalTableModifier(ailootcondition, reference, tableID, referChance);
        }

        @Override
        public JsonObject write(AdditionalTableModifier instance) {
            JsonObject object = makeConditions(instance.conditions);
            object.addProperty("tableRef", instance.tableID);
            object.addProperty("referChance", instance.chanceToRefer);
            return object;
        }
    }
}
