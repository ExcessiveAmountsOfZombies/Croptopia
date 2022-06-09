package com.epherical.croptopia.loot;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Add an additional LootTable as the modifier.
 */
public class AdditionalTableModifier extends LootModifier {

    private String tableID;
    private final LootTableReference reference;
    private final float chanceToRefer;

    /**
     * Constructs a LootModifier.
     *
     * @param conditionsIn the ILootConditions that need to be matched before the loot is modified.
     */
    protected AdditionalTableModifier(LootItemCondition[] conditionsIn, LootTableReference reference, String tableID, float chanceToRefer) {
        super(conditionsIn);
        this.chanceToRefer = chanceToRefer;
        this.tableID = tableID;
        this.reference = reference;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if (context.getRandom().nextFloat() <= chanceToRefer) {
            ObjectArrayList<ItemStack> items = new ObjectArrayList<>();
            reference.createItemStack(items::add, context);
            return items;
        }

        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<AdditionalTableModifier> {

        @Override
        public AdditionalTableModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] ailootcondition) {
            String tableID = GsonHelper.getAsString(object, "tableRef");
            LootTableReference reference = (LootTableReference) LootTableReference.lootTableReference(new ResourceLocation(tableID)).build();
            float referChance = GsonHelper.getAsFloat(object, "referChance");
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
