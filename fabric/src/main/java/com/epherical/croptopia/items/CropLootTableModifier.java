package com.epherical.croptopia.items;

import com.epherical.croptopia.CroptopiaMod;
import com.epherical.croptopia.mixin.accessor.LootTableBuilderAccessor;
import com.epherical.croptopia.register.Content;
import net.fabricmc.fabric.api.loot.v2.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class CropLootTableModifier {
    private static final Logger LOGGER = LoggerFactory.getLogger(CropLootTableModifier.class);

    public static void init() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (id.getNamespace().equalsIgnoreCase("minecraft")) {
                String path = id.getPath();
                switch (path) {
                    case "entities/cod", "entities/salmon", "entities/tropical_fish" -> {
                        LootPool.Builder builder = LootPool.lootPool();
                        builder.add(LootItem.lootTableItem(Content.ROE));
                        tableBuilder.withPool(builder);
                    }
                    case "gameplay/fishing" -> {
                        List<LootPool> pools = ((LootTableBuilderAccessor) tableBuilder).getPools();
                        if (pools.isEmpty()) {
                            LOGGER.warn("Can not inject into gameplay/fishing/fish as it is empty");
                        } else {
                            LootPool.Builder builder = FabricLootPoolBuilder.copyOf(pools.get(0));
                            builder.add(LootTableReference.lootTableReference(new ResourceLocation("croptopia", "gameplay/fishing/fish"))
                                    .setWeight(30));
                            pools.set(0, builder.build());
                        }
                    }
                    case "entities/squid" -> {
                        LootPool.Builder builder = LootPool.lootPool();
                        builder.add(LootItem.lootTableItem(Content.CALAMARI));
                        tableBuilder.withPool(builder);
                    }
                    case "entities/glow_squid" -> {
                        LootPool.Builder builder = LootPool.lootPool();
                        builder.add(LootItem.lootTableItem(Content.GLOWING_CALAMARI));
                        tableBuilder.withPool(builder);
                    }
                    case "chests/spawn_bonus_chest" -> {
                        LootPool.Builder builder = LootPool.lootPool();
                        builder.setRolls(ConstantValue.exactly(1));
                        builder.setBonusRolls(ConstantValue.exactly(0));
                        for (Item seed : CroptopiaMod.seeds) {
                            builder.add(
                                    LootItem.lootTableItem(seed)
                                            .setWeight(5)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 8), false))
                            );
                        }
                        tableBuilder.withPool(builder);
                    }
                }
            }
        });
    }
}
