package me.thonk.croptopia.loot;

import com.google.gson.JsonObject;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
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
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

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
    protected EntityModifier(LootItemCondition[] conditionsIn, Item item, int weight, int min, int max) {
        super(conditionsIn);
        this.item = item;
        this.weight = weight;
        this.min = min;
        this.max = max;
        LootPool.Builder builder = LootPool.lootPool();
        builder.setRolls(ConstantValue.exactly(1));
        builder.setBonusRolls(ConstantValue.exactly(0));
        builder.add(LootItem.lootTableItem(item)
                .setWeight(weight)
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max), false)));
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
        public EntityModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] ailootcondition) {
            Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(GsonHelper.getAsString(object, "item", "minecraft:air")));
            int weight = GsonHelper.getAsInt(object, "weight", 1);
            int min = GsonHelper.getAsInt(object, "min", 0);
            int max = GsonHelper.getAsInt(object, "max", 1);
            return new EntityModifier(ailootcondition, item, weight, min, max);
        }

        @Override
        public JsonObject write(EntityModifier instance) {
            return makeConditions(instance.conditions);
        }
    }
}
