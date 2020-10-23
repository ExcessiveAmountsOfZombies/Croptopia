package me.thonk.croptopia.items;

import me.thonk.croptopia.Croptopia;
import me.thonk.croptopia.table.BiomeLootCondition;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.item.Item;
import net.minecraft.loot.UniformLootTableRange;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.AlternativeEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.world.biome.Biome;

import java.util.ArrayList;

public class CropLootTableModifier {

    public static void init() {
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, identifier, fabricLootSupplierBuilder, lootTableSetter) -> {
            if (identifier.getPath().equalsIgnoreCase("blocks/grass")) {
                FabricLootPoolBuilder builder = FabricLootPoolBuilder.builder();
                builder.rolls(UniformLootTableRange.between(0, 1));

                builder.withCondition(new MatchToolLootCondition(ItemPredicate.Builder.create().tag(FabricToolTags.HOES).build()));
                ArrayList<LootPoolEntry.Builder> builders = new ArrayList<>();
                for (Item seed : Croptopia.getSeeds()) {
                    builders.add(ItemEntry.builder(seed)
                            .conditionally(() -> BiomeLootCondition.builder(Biome.Category.PLAINS).build())
                            .conditionally(() -> RandomChanceLootCondition.builder(0.0125f).build()));
                }
                builder.with(AlternativeEntry.builder(builders.toArray(builders.toArray(new LootPoolEntry.Builder[0]))));
                fabricLootSupplierBuilder.withPool(builder.build());
               // System.out.println(LootManager.toJson(fabricLootSupplierBuilder.build()));
            }
        });
    }
}
