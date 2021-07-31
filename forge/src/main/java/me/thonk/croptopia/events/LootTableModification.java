package me.thonk.croptopia.events;

import me.thonk.croptopia.BiomeLootCondition;
import me.thonk.croptopia.CroptopiaForge;
import me.thonk.croptopia.items.SeedItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;


public class LootTableModification {


    @SubscribeEvent
    public void onLootTableLoad(LootTableLoadEvent event) {
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
