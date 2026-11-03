package com.epherical.croptopia.loot;

import com.epherical.croptopia.CroptopiaNeoForge;
import com.google.common.base.Suppliers;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
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

import static com.epherical.croptopia.CroptopiaCommon.seeds;


public class SpawnChestModifier extends LootModifier {
    public static final Supplier<MapCodec<SpawnChestModifier>> CODEC = Suppliers.memoize(() -> {
        return RecordCodecBuilder.mapCodec(instance -> {
            return codecStart(instance).apply(instance, SpawnChestModifier::new);
        });
    });


    private final LootPool table;

    /**
     * Constructs a LootModifier.
     *
     * @param condition the LootItemCondition that need to be matched before the loot is modified.
     */
    protected SpawnChestModifier(Optional<Holder<LootItemCondition>> condition, int prio) {
        super(condition, prio);
        LootPool.Builder builder = new LootPool.Builder();
        builder.setRolls(ContextIntProviders.exactly(1));
        builder.setBonusRolls(net.minecraft.world.level.storage.loot.providers.number.floats.ContextFloatProviders.exactly(0));
        for (Item seed : seeds) {
            builder.add(LootItem.lootTableItem(seed)
                    .setWeight(2)
                    .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 8), false))
            );
        }
        table = builder.build();
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        table.addRandomItems(generatedLoot::add, context);
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
