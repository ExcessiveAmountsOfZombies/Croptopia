package com.epherical.croptopia.items;

import com.epherical.croptopia.Croptopia;
import com.epherical.croptopia.config.ConfigurableSeed;
import com.epherical.croptopia.register.Content;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.fabric.mixin.loot.table.LootSupplierBuilderHooks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class CropLootTableModifier {
    private static final Logger LOGGER = LoggerFactory.getLogger(CropLootTableModifier.class);

    public static void init() {
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, fabricLootSupplierBuilder, lootTableSetter) -> {
            /*if (id.getPath().equalsIgnoreCase("blocks/grass") ||
                    id.getPath().equalsIgnoreCase("blocks/tall_grass") ||
                    id.getPath().equalsIgnoreCase("blocks/fern") ||
                    id.getPath().equalsIgnoreCase("blocks/large_fern")) {
                FabricLootPoolBuilder builder = FabricLootPoolBuilder.builder();
                builder.rolls(UniformLootNumberProvider.create(0, 1));
                builder.withFunction(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE).build());

                if (Constants.OPTIONS.useHoeToCollectSeeds()) {
                    builder.withCondition(new MatchToolLootCondition(ItemPredicate.Builder.create().tag(FabricToolTags.HOES).build()));
                }
                ArrayList<LootPoolEntry.Builder> builders = new ArrayList<>();
                for (ConfigurableSeed seed : Croptopia.getSeeds()) {
                    builders.add(ItemEntry.builder(seed.getSeedItem())
                            .conditionally(() -> BiomeLootCondition.builder(seed.getBiomeCategory()).build()));
                }
                builder.with(AlternativeEntry.builder(builders.toArray(builders.toArray(new LootPoolEntry.Builder[0]))));
                fabricLootSupplierBuilder.withPool(builder.build());
                //System.out.println(LootManager.toJson(fabricLootSupplierBuilder.build()));
            }*/
            if (id.getNamespace().equalsIgnoreCase("minecraft")) {
                String path = id.getPath();
                switch (path) {
                    case "entities/cod", "entities/salmon", "entities/tropical_fish" -> {
                        FabricLootPoolBuilder builder = FabricLootPoolBuilder.builder();
                        builder.withEntry(LootItem.lootTableItem(Content.ROE).build());
                        fabricLootSupplierBuilder.withPool(builder.build());
                    }
                    case "gameplay/fishing/fish" -> {
                        List<LootPool> pools = ((LootSupplierBuilderHooks) fabricLootSupplierBuilder).getPools();
                        if (pools.isEmpty()) {
                            LOGGER.warn("Can not inject into gameplay/fishing/fish as it is empty");
                        } else {
                            // todo; make this configurable
                            FabricLootPoolBuilder builder = FabricLootPoolBuilder.of(pools.get(0));
                            builder.withEntry(LootItem.lootTableItem(Content.TUNA)
                                            .setWeight(15).build())
                                    .withEntry(LootItem.lootTableItem(Content.ANCHOVY)
                                            .setWeight(15).build())
                                    .withEntry(LootItem.lootTableItem(Content.SHRIMP)
                                            .setWeight(15).build())
                                    .withEntry(LootItem.lootTableItem(Content.CRAB)
                                            .setWeight(15).build())
                                    .withEntry(LootItem.lootTableItem(Content.CLAM)
                                            .setWeight(10).build())
                                    .withEntry(LootItem.lootTableItem(Content.OYSTER)
                                            .setWeight(10).build())
                                    .withEntry(LootItem.lootTableItem(Content.SEA_LETTUCE)
                                            .setWeight(10).build());
                            pools.set(0, builder.build());
                        }
                    }
                    case "entities/squid" -> {
                        FabricLootPoolBuilder builder = FabricLootPoolBuilder.builder();
                        builder.withEntry(LootItem.lootTableItem(Content.CALAMARI).build());
                        fabricLootSupplierBuilder.withPool(builder.build());
                    }
                    case "entities/glow_squid" -> {
                        FabricLootPoolBuilder builder = FabricLootPoolBuilder.builder();
                        builder.withEntry(LootItem.lootTableItem(Content.GLOWING_CALAMARI).build());
                        fabricLootSupplierBuilder.withPool(builder.build());
                    }
                    case "chests/spawn_bonus_chest" -> {
                        FabricLootPoolBuilder builder = FabricLootPoolBuilder.builder();
                        builder.setRolls(ConstantValue.exactly(1));
                        builder.setBonusRolls(ConstantValue.exactly(0));
                        for (ConfigurableSeed seed : Croptopia.getSeeds()) {
                            builder.withEntry(
                                    LootItem.lootTableItem(seed.getSeedItem())
                                            .setWeight(5)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 8), false)).build()
                            );
                        }
                        fabricLootSupplierBuilder.withPool(builder);
                    }
                }
            }
        });
    }
}
