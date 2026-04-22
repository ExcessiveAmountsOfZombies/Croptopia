package com.epherical.croptopia.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ChestModifier extends LootModifier {
    public static final Supplier<Codec<ChestModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(instance -> {
        return codecStart(instance).and(
                instance.group(
                        ForgeRegistries.ITEMS.getCodec().fieldOf("item").forGetter(o -> o.item),
                        Codec.INT.fieldOf("weight").forGetter(o -> o.weight),
                        Codec.INT.fieldOf("emptyWeight").forGetter(o -> o.emptyWeight),
                        Codec.INT.fieldOf("minRolls").forGetter(o -> o.minRolls),
                        Codec.INT.fieldOf("maxRolls").forGetter(o -> o.maxRolls)
                )
        ).apply(instance, ChestModifier::new);
    }));

    private final LootPool pool;
    private final Item item;
    private final int weight;
    private final int emptyWeight;
    private final int minRolls;
    private final int maxRolls;

    protected ChestModifier(LootItemCondition[] conditionsIn, Item item, int weight, int emptyWeight, int minRolls, int maxRolls) {
        super(conditionsIn);
        this.item = item;
        this.weight = weight;
        this.emptyWeight = emptyWeight;
        this.minRolls = minRolls;
        this.maxRolls = maxRolls;

        LootPool.Builder builder = LootPool.lootPool();
        if (minRolls == maxRolls) {
            builder.setRolls(ConstantValue.exactly(minRolls));
        } else {
            builder.setRolls(UniformGenerator.between(minRolls, maxRolls));
        }
        builder.setBonusRolls(ConstantValue.exactly(0));
        builder.add(LootItem.lootTableItem(item).setWeight(weight));
        builder.add(EmptyLootItem.emptyItem().setWeight(emptyWeight));
        pool = builder.build();
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        pool.addRandomItems(generatedLoot::add, context);
        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
