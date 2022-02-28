package me.thonk.croptopia.items;

import me.thonk.croptopia.Constants;
import me.thonk.croptopia.Croptopia;
import me.thonk.croptopia.config.ConfigurableSeed;
import me.thonk.croptopia.loottables.BiomeLootCondition;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.AlternativeEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.item.ItemPredicate;

import java.util.ArrayList;

public class CropLootTableModifier {

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

            if (id.getNamespace().equalsIgnoreCase("minecraft") && id.getPath().equalsIgnoreCase("chests/spawn_bonus_chest")) {
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

        });
    }
}
