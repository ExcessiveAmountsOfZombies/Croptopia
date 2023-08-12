package com.epherical.croptopia.loot;

import com.epherical.croptopia.CroptopiaMod;
import com.epherical.croptopia.items.SeedItem;
import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;


public class SpawnChestModifier extends LootModifier {
    public static final Supplier<Codec<SpawnChestModifier>> CODEC = Suppliers.memoize(() -> {
        return RecordCodecBuilder.create(instance -> {
            return codecStart(instance).apply(instance, SpawnChestModifier::new);
        });
    });


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
        for (Item seed : CroptopiaMod.seeds) {
            builder.add(LootItem.lootTableItem(seed)
                    .setWeight(2)
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 8), false))
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
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
