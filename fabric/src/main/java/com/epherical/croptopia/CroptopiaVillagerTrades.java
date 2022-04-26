package com.epherical.croptopia;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import java.util.List;

public class CroptopiaVillagerTrades {


    public static void init() {
        /*TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1, factories -> {
            for (Content.Farmland crop : Content.Farmland.values()) {
                buyFromUser(factories, crop.asItem(), 26, 2, 16, 0.1f);
            }
        });

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 4, factories -> {
            for (Content.Tree crop : Content.Tree.values()) {
                sellToUser(factories, crop.getSapling(), 1, 4, 12, 15, 0.1f);
            }
            for (Content.Bark crop : Content.Bark.values()) {
                sellToUser(factories, crop.getSapling(), 1, 4, 12, 15, 0.1f);
            }
        });

        TradeOfferHelper.registerWanderingTraderOffers(2, factories -> {
            for (Content.Farmland crop : Content.Farmland.values()) {
                sellToUser(factories, crop.getSeed(), 4, 2, 16, 1, 0.5f);
            }
        });*/
    }

    private static void buyFromUser(List<VillagerTrades.ItemListing> factory, Item item, int itemCount, int tradeXP, int maxTrades, float priceMultiplier) {
        factory.add((entity, random) -> new MerchantOffer(new ItemStack(item, itemCount), new ItemStack(Items.EMERALD), maxTrades, tradeXP, priceMultiplier));
    }

    private static void sellToUser(List<VillagerTrades.ItemListing> factory, Item item, int itemCount, int purchaseAmount, int maxTrades, int tradeXP, float priceMultiplier) {
        factory.add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, purchaseAmount), new ItemStack(item, itemCount), maxTrades, tradeXP, priceMultiplier));
    }
}
