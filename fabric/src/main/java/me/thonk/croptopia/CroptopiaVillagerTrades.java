package me.thonk.croptopia;

import me.thonk.croptopia.registry.Content;
import me.thonk.croptopia.registry.ItemRegistry;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;

import java.util.List;

public class CroptopiaVillagerTrades {


    public static void init() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1, factories -> {
            for (Content.Farmland crop : Content.Farmland.values()) {
                buyFromUser(factories, crop.asItem(), 26, 2, 16, 0.1f);
            }
        });

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 4, factories -> {
            for (Content.Tree crop : Content.Tree.values()) {
                sellToUser(factories, crop.getSapling(), 1, 4, 12, 15, 0.1f);
            }
            sellToUser(factories, ItemRegistry.cinnamonSapling, 1, 4, 12, 15, 0.1f);
        });

        TradeOfferHelper.registerWanderingTraderOffers(2, factories -> {
            for (Content.Farmland crop : Content.Farmland.values()) {
                sellToUser(factories, crop.getSeed(), 4, 2, 16, 1, 0.5f);
            }
        });
    }

    private static void buyFromUser(List<TradeOffers.Factory> factory, Item item, int itemCount, int tradeXP, int maxTrades, float priceMultiplier) {
        factory.add((entity, random) -> new TradeOffer(new ItemStack(item, itemCount), new ItemStack(Items.EMERALD), maxTrades, tradeXP, priceMultiplier));
    }

    private static void sellToUser(List<TradeOffers.Factory> factory, Item item, int itemCount, int purchaseAmount, int maxTrades, int tradeXP, float priceMultiplier) {
        factory.add((entity, random) -> new TradeOffer(new ItemStack(Items.EMERALD, purchaseAmount), new ItemStack(item, itemCount), maxTrades, tradeXP, priceMultiplier));
    }
}
