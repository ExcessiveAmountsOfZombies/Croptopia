package com.epherical.croptopia.events;

import com.epherical.croptopia.mixin.LootPoolAccessor;
import com.epherical.croptopia.mixin.LootPoolBuilderAccessor;
import com.epherical.croptopia.mixin.LootTableAccessor;
import com.epherical.croptopia.registry.ItemRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;


public class LootTableModification {


    @SubscribeEvent
    public void onLootTableLoad(LootTableLoadEvent event) {
        ResourceLocation location = event.getName();
        if (location != null && location.getNamespace().equals("minecraft")) {
            String path = location.getPath();
            switch (path) {
                case "gameplay/fishing/fish" -> {
                    LootPool.Builder builder = LootPool.lootPool();
                    List<LootPool> poolList = ((LootTableAccessor) event.getTable()).getPools();
                    if (!poolList.isEmpty()) {
                        LootPoolBuilderAccessor accessor = (LootPoolBuilderAccessor) builder;
                        LootPoolEntryContainer[] entries = ((LootPoolAccessor) poolList.get(0)).getEntries();
                        accessor.getEntries().addAll(List.of(entries));
                        builder.add(LootItem.lootTableItem(ItemRegistry.tuna).setWeight(20))
                                .add(LootItem.lootTableItem(ItemRegistry.anchovy).setWeight(30))
                                .add(LootItem.lootTableItem(ItemRegistry.shrimp).setWeight(20))
                                .add(LootItem.lootTableItem(ItemRegistry.crab).setWeight(15))
                                .add(LootItem.lootTableItem(ItemRegistry.clam).setWeight(10))
                                .add(LootItem.lootTableItem(ItemRegistry.oyster).setWeight(10));
                        poolList.set(0, builder.build());
                    }
                }
            }
        }
        /*
        if (location != null) {
            if (location.getPath().equalsIgnoreCase("blocks/grass")) {
                LootPool.Builder builder = LootPool.builder();
                builder.rolls(BinomialRange.of(0, 1));

                ArrayList<LootEntry.Builder<?>> builders = new ArrayList<>();
                for (SeedItem seed : CroptopiaForge.seeds) {
                    builders.add(ItemLootEntry.builder(seed)
                            .acceptCondition(() -> BiomeLootCondition.builder(seed.getCategory()).build())
                            .acceptCondition(() -> RandomChance.builder(0.0125f).build()));
                }
                builder.addEntry(AlternativesLootEntry.builder(builders.toArray(builders.toArray(new LootEntry.Builder[0]))));
                event.getTable().addPool(builder.name("croptopia_seeds").build());
                LootTable.Builder lootTable = LootTable.builder().addLootPool(builder.name("croptopia_seeds"));
            }
        }*/
    }
}
