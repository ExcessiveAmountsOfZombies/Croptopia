package com.epherical.croptopia.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * Add an additional LootTable as the modifier.
 */
public class AdditionalTableModifier extends LootModifier {
    public static final Supplier<Codec<AdditionalTableModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(instance -> {
        return codecStart(instance).and(
                instance.group(
                        Codec.STRING.fieldOf("tableRef").forGetter(o -> o.tableID),
                        Codec.FLOAT.fieldOf("referChance").forGetter(o -> o.referChance)
                )
        ).apply(instance, AdditionalTableModifier::new);
    }));

    private String tableID;
    private final LootTableReference reference;
    private final float referChance;

    /**
     * Constructs a LootModifier.
     *
     * @param conditionsIn the ILootConditions that need to be matched before the loot is modified.
     */
    protected AdditionalTableModifier(LootItemCondition[] conditionsIn, String tableID, float chanceToRefer) {
        super(conditionsIn);
        this.referChance = chanceToRefer;
        this.tableID = tableID;
        this.reference = (LootTableReference) LootTableReference.lootTableReference(new ResourceLocation(tableID)).build();
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if (context.getRandom().nextFloat() <= referChance) {
            ObjectArrayList<ItemStack> items = new ObjectArrayList<>();
            reference.createItemStack(items::add, context);
            return items;
        }

        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
