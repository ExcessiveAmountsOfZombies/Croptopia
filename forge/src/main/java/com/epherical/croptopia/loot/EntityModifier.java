package com.epherical.croptopia.loot;

import com.google.common.base.Suppliers;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
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
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class EntityModifier extends LootModifier {
    public static final Supplier<Codec<EntityModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(instance -> {
        return codecStart(instance).and(
                instance.group(
                        ForgeRegistries.ITEMS.getCodec().fieldOf("item").forGetter(o -> o.item),
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
