package me.thonk.croptopia.items;

import me.thonk.croptopia.Croptopia;
import me.thonk.croptopia.config.ConfigurableSeed;
import me.thonk.croptopia.registry.Content;
import me.thonk.croptopia.registry.ItemRegistry;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.fabric.mixin.loot.table.LootSupplierBuilderHooks;
import net.minecraft.loot.LootManager;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
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
                        builder.withEntry(ItemEntry.builder(Content.Seafood.ROE).build());
                        fabricLootSupplierBuilder.withPool(builder.build());
                    }
                    case "gameplay/fishing/fish" -> {
                        List<LootPool> pools = ((LootSupplierBuilderHooks) fabricLootSupplierBuilder).getPools();
                        if (pools.isEmpty()) {
                            LOGGER.warn("Can not inject into gameplay/fishing/fish as it is empty");
                        } else {
                            // todo; make this configurable
                            FabricLootPoolBuilder builder = FabricLootPoolBuilder.of(pools.get(0));
                            builder.withEntry(ItemEntry.builder(Content.Seafood.TUNA)
                                            .weight(15).build())
                                    .withEntry(ItemEntry.builder(Content.Seafood.ANCHOVY)
                                            .weight(15).build())
                                    .withEntry(ItemEntry.builder(Content.Seafood.SHRIMP)
                                            .weight(15).build())
                                    .withEntry(ItemEntry.builder(Content.Seafood.CRAB)
                                            .weight(15).build())
                                    .withEntry(ItemEntry.builder(Content.Seafood.CLAM)
                                            .weight(10).build())
                                    .withEntry(ItemEntry.builder(Content.Seafood.OYSTER)
                                            .weight(10).build())
                                    .withEntry(ItemEntry.builder(ItemRegistry.seaLettuce)
                                            .weight(10).build());
                            pools.set(0, builder.build());
                        }
                    }
                    case "entities/squid" -> {
                        FabricLootPoolBuilder builder = FabricLootPoolBuilder.builder();
                        builder.withEntry(ItemEntry.builder(Content.Seafood.CALAMARI).build());
                        fabricLootSupplierBuilder.withPool(builder.build());
                    }
                    case "entities/glow_squid" -> {
                        FabricLootPoolBuilder builder = FabricLootPoolBuilder.builder();
                        builder.withEntry(ItemEntry.builder(Content.Seafood.GLOWING_CALAMARI).build());
                        fabricLootSupplierBuilder.withPool(builder.build());
                    }
                    case "chests/spawn_bonus_chest" -> {
                        FabricLootPoolBuilder builder = FabricLootPoolBuilder.builder();
                        builder.rolls(ConstantLootNumberProvider.create(1));
                        builder.bonusRolls(ConstantLootNumberProvider.create(0));
                        for (ConfigurableSeed seed : Croptopia.getSeeds()) {
                            builder.withEntry(
                                    ItemEntry.builder(seed.getSeedItem())
                                            .weight(5)
                                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3, 8), false)).build()
                            );
                        }
                        fabricLootSupplierBuilder.pool(builder);
                    }
                }
            }
        });
    }
}
