package com.epherical.croptopia.loot;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;

import java.util.Map;

public class FabricLootModifiers {
    private static final ResourceKey<LootTable> TUNA_SANDWICH_LOOT = createKey("chests/tuna_sandwich_loot");
    private static final ResourceKey<LootTable> MOUNTAIN_SALT_LOOT = createKey("chests/mountain_salt_loot");
    private static final ResourceKey<LootTable> COD_ROE_DROP = createKey("entities/cod_roe_drop");
    private static final ResourceKey<LootTable> SALMON_ROE_DROP = createKey("entities/salmon_roe_drop");
    private static final ResourceKey<LootTable> TROPICAL_ROE_DROP = createKey("entities/tropical_roe_drop");
    private static final ResourceKey<LootTable> GLOWING_SQUID_GLOWING_CALAMARI_DROP = createKey("entities/glowing_squid_glowing_calamari_drop");
    private static final ResourceKey<LootTable> SQUID_CALAMARI_DROP = createKey("entities/squid_calamari_drop");
    private static final ResourceKey<LootTable> RAVAGER_MEAT_DROP = createKey("entities/ravager_meat_drop");
    private static final ResourceKey<LootTable> CROPTOPIA_FISHING_TABLE = createKey("gameplay/fishing/fish");
    private static final ResourceKey<LootTable> AQUACULTURE_FISHING_TABLE = createKey("aquaculture", "gameplay/fishing/fish");
    private static final Map<ResourceKey<LootTable>, ResourceKey<LootTable>> ADDITIONS_BY_TARGET = Map.ofEntries(
            Map.entry(BuiltInLootTables.SIMPLE_DUNGEON, TUNA_SANDWICH_LOOT),
            Map.entry(BuiltInLootTables.SHIPWRECK_TREASURE, MOUNTAIN_SALT_LOOT),
            Map.entry(createKey("minecraft", "entities/cod"), COD_ROE_DROP),
            Map.entry(createKey("minecraft", "entities/salmon"), SALMON_ROE_DROP),
            Map.entry(createKey("minecraft", "entities/tropical_fish"), TROPICAL_ROE_DROP),
            Map.entry(createKey("minecraft", "entities/squid"), SQUID_CALAMARI_DROP),
            Map.entry(createKey("minecraft", "entities/ravager"), RAVAGER_MEAT_DROP),
            Map.entry(createKey("minecraft", "entities/glow_squid"), GLOWING_SQUID_GLOWING_CALAMARI_DROP),
            Map.entry(BuiltInLootTables.FISHING_FISH, CROPTOPIA_FISHING_TABLE),
            Map.entry(AQUACULTURE_FISHING_TABLE, CROPTOPIA_FISHING_TABLE)
    );

    public static void register() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            ResourceKey<LootTable> addition = ADDITIONS_BY_TARGET.get(key);
            if (addition != null) {
                tableBuilder.withPool(LootPool.lootPool().add(NestedLootTable.lootTableReference(registries.getOrThrow(addition))));
            }
        });
    }

    private static ResourceKey<LootTable> createKey(String path) {
        return createKey("croptopia", path);
    }

    private static ResourceKey<LootTable> createKey(String namespace, String path) {
        return ResourceKey.create(Registries.LOOT_TABLE, Identifier.fromNamespaceAndPath(namespace, path));
    }
}
