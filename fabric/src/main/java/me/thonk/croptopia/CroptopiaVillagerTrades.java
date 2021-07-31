package me.thonk.croptopia;

import me.thonk.croptopia.registry.ItemRegistry;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;

import java.util.List;

public class CroptopiaVillagerTrades {


    public static void init() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1, factories -> {
            buyFromUser(factories, ItemRegistry.artichoke, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.asparagus, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.barley, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.bellPepper, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.blackBean, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.blackberry, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.blueberry, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.broccoli, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.cabbage, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.cantaloupe, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.cauliflower, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.celery, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.coffeeBeans, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.corn, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.cranberry, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.cucumber, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.currant, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.eggplant, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.elderberry, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.garlic, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.grape, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.greenBean, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.greenOnion, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.honeydew, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.hops, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.kale, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.kiwi, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.leek, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.lettuce, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.oat, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.olive, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.onion, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.peanut, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.pineapple, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.radish, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.raspberry, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.rhubarb, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.rice, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.rutabaga, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.saguaro, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.soybean, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.spinach, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.squash, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.strawberry, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.sweetPotato, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.tomatillo, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.tomato, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.turnip, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.yam, 26, 2, 16, 0.1f);
            buyFromUser(factories, ItemRegistry.zucchini, 26, 2, 16, 0.1f);
        });

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 4, factories -> {
            sellToUser(factories, ItemRegistry.appleSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(factories, ItemRegistry.bananaSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(factories, ItemRegistry.orangeSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(factories, ItemRegistry.persimmonSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(factories, ItemRegistry.plumSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(factories, ItemRegistry.cherrySapling, 1, 4, 12, 15, 0.1f);
            sellToUser(factories, ItemRegistry.lemonSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(factories, ItemRegistry.grapefruitSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(factories, ItemRegistry.kumquatSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(factories, ItemRegistry.peachSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(factories, ItemRegistry.coconutSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(factories, ItemRegistry.nutmegSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(factories, ItemRegistry.figSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(factories, ItemRegistry.nectarineSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(factories, ItemRegistry.mangoSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(factories, ItemRegistry.dragonFruitSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(factories, ItemRegistry.starFruitSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(factories, ItemRegistry.avocadoSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(factories, ItemRegistry.apricotSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(factories, ItemRegistry.pearSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(factories, ItemRegistry.limeSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(factories, ItemRegistry.dateSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(factories, ItemRegistry.almondSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(factories, ItemRegistry.cashewSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(factories, ItemRegistry.pecanSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(factories, ItemRegistry.walnutSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(factories, ItemRegistry.cinnamonSapling, 1, 4, 12, 15, 0.1f);
        });

        TradeOfferHelper.registerWanderingTraderOffers(2, factories -> {
            sellToUser(factories, ItemRegistry.artichokeSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.asparagusSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.bellPepperSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.blackBeanSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.blackberrySeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.blueberrySeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.broccoliSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.cabbageSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.cantaloupeSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.cauliflowerSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.celerySeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.coffeeSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.cornSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.cranberrySeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.cucumberSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.currantSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.eggplantSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.elderberrySeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.garlicSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.grapeSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.greenBeanSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.greenOnionSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.honeydewSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.hopsSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.kaleSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.kiwiSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.leekSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.lettuceSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.oliveSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.onionSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.peanutSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.pineappleSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.radishSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.raspberrySeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.rhubarbSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.riceSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.rutabagaSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.saguaroSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.spinachSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.squashSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.strawberrySeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.sweetPotatoSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.tomatilloSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.tomatoSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.turnipSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.yamSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.zucchiniSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.mustardSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.chilePepperSeed, 4, 2, 16, 1, 0.5f);
            sellToUser(factories, ItemRegistry.turmericSeed, 4, 2, 16, 1, 0.5f);
        });
    }

    private static void buyFromUser(List<TradeOffers.Factory> factory, Item item, int itemCount, int tradeXP, int maxTrades, float priceMultiplier) {
        factory.add((entity, random) -> new TradeOffer(new ItemStack(item, itemCount), new ItemStack(Items.EMERALD), maxTrades, tradeXP, priceMultiplier));
    }

    private static void sellToUser(List<TradeOffers.Factory> factory, Item item, int itemCount, int purchaseAmount, int maxTrades, int tradeXP, float priceMultiplier) {
        factory.add((entity, random) -> new TradeOffer(new ItemStack(Items.EMERALD, purchaseAmount), new ItemStack(item, itemCount), maxTrades, tradeXP, priceMultiplier));
    }
}
