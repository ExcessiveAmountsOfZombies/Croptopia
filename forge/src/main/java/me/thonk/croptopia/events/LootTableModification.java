package me.thonk.croptopia.events;

import me.thonk.croptopia.BiomeLootCondition;
import me.thonk.croptopia.CroptopiaForge;
import me.thonk.croptopia.items.SeedItem;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.RandomChance;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;


public class LootTableModification {


    @SubscribeEvent
    public void onLootTableLoad(LootTableLoadEvent event) {
        ResourceLocation location = event.getName();
        if (location != null) {
            if (location.getPath().equalsIgnoreCase("blocks/grass") ||
                location.getPath().equalsIgnoreCase("blocks/tall_grass") ||
                location.getPath().equalsIgnoreCase("blocks/fern") ||
                location.getPath().equalsIgnoreCase("blocks/large_fern")) {
                LootPool.Builder builder = LootPool.builder();
                builder.rolls(BinomialRange.of(0, 1));

                ArrayList<LootEntry.Builder<?>> builders = new ArrayList<>();
                for (SeedItem seed : CroptopiaForge.seeds) {
                    builders.add(ItemLootEntry.builder(seed)
                            .acceptCondition(() -> BiomeLootCondition.builder(seed.getCategory()).build())
                            .acceptCondition(() -> RandomChance.builder(0.0125f).build()));
                }
                builder.addEntry(AlternativesLootEntry.builder(builders.toArray(builders.toArray(new LootEntry.Builder[0]))));
                event.getTable().addPool(builder.build());
            }
        }
    }
}
