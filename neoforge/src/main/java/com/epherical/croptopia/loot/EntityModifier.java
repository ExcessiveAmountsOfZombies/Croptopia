package com.epherical.croptopia.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;
import java.util.Optional;
import net.minecraft.core.Holder;

public class EntityModifier extends LootModifier {
    public static final Supplier<MapCodec<EntityModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.mapCodec(instance -> {
        return codecStart(instance).and(
                instance.group(
                        BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(o -> o.item),
                        Codec.INT.fieldOf("weight").forGetter(o -> o.weight),
                        Codec.INT.fieldOf("min").forGetter(o -> o.min),
                        Codec.INT.fieldOf("max").forGetter(o -> o.max)
                )
        ).apply(instance, EntityModifier::new);
    }));

    private final LootPool pool;
    private final Item item;
    private final int weight;
    private final int min;
    private final int max;

    /**
     * Constructs a LootModifier.
     *
     * @param condition the ILootConditions that need to be matched before the loot is modified.
     */
    protected EntityModifier(Optional<Holder<LootItemCondition>> condition, int prio, Item item, int weight, int min, int max) {
        super(condition, prio);
        this.item = item;
        this.weight = weight;
        this.min = min;
        this.max = max;
        LootPool.Builder builder = LootPool.lootPool();
        builder.setRolls(ContextIntProviders.exactly(1));
        builder.setBonusRolls(net.minecraft.world.level.storage.loot.providers.number.floats.ContextFloatProviders.exactly(0));
        builder.add(LootItem.lootTableItem(item)
                .setWeight(weight)
                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(min, max), false)));
        pool = builder.build();
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        pool.addRandomItems(generatedLoot::add, context);
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
