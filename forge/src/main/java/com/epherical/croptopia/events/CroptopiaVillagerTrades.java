package com.epherical.croptopia.events;

import com.epherical.croptopia.registry.ItemRegistry;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class CroptopiaVillagerTrades {
    
    @SubscribeEvent
    public void initVillager(VillagerTradesEvent event) {
        List<VillagerTrades.ItemListing> crops = NonNullList.create();
        List<VillagerTrades.ItemListing> saplings = NonNullList.create();
        if (event.getType() == VillagerProfession.FARMER) {
            buyFromUser(crops, ItemRegistry.artichoke, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.asparagus, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.barley, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.bellPepper, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.blackBean, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.blackberry, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.blueberry, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.broccoli, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.cabbage, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.cantaloupe, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.cauliflower, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.celery, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.coffeeBeans, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.corn, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.cranberry, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.cucumber, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.currant, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.eggplant, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.elderberry, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.garlic, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.grape, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.greenBean, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.greenOnion, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.honeydew, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.hops, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.kale, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.kiwi, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.leek, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.lettuce, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.oat, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.olive, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.onion, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.peanut, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.pineapple, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.radish, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.raspberry, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.rhubarb, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.rice, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.rutabaga, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.saguaro, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.soybean, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.spinach, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.squash, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.strawberry, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.sweetPotato, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.tomatillo, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.tomato, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.turnip, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.yam, 26, 2, 16, 0.1f);
            buyFromUser(crops, ItemRegistry.zucchini, 26, 2, 16, 0.1f);
            //event.getTrades().put(1, crops);
            
            sellToUser(saplings, ItemRegistry.appleSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(saplings, ItemRegistry.bananaSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(saplings, ItemRegistry.orangeSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(saplings, ItemRegistry.persimmonSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(saplings, ItemRegistry.plumSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(saplings, ItemRegistry.cherrySapling, 1, 4, 12, 15, 0.1f);
            sellToUser(saplings, ItemRegistry.lemonSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(saplings, ItemRegistry.grapefruitSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(saplings, ItemRegistry.kumquatSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(saplings, ItemRegistry.peachSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(saplings, ItemRegistry.coconutSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(saplings, ItemRegistry.nutmegSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(saplings, ItemRegistry.figSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(saplings, ItemRegistry.nectarineSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(saplings, ItemRegistry.mangoSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(saplings, ItemRegistry.dragonFruitSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(saplings, ItemRegistry.starFruitSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(saplings, ItemRegistry.avocadoSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(saplings, ItemRegistry.apricotSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(saplings, ItemRegistry.pearSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(saplings, ItemRegistry.limeSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(saplings, ItemRegistry.dateSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(saplings, ItemRegistry.almondSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(saplings, ItemRegistry.cashewSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(saplings, ItemRegistry.pecanSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(saplings, ItemRegistry.walnutSapling, 1, 4, 12, 15, 0.1f);
            sellToUser(saplings, ItemRegistry.cinnamonSapling, 1, 4, 12, 15, 0.1f);
            //event.getTrades().put(4, crops);
            
        }
    }

    @SubscribeEvent
    public void initWandering(WandererTradesEvent event) {
        List<VillagerTrades.ItemListing> seeds = NonNullList.create();
        sellToUser(seeds, ItemRegistry.asparagusSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.bellPepperSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.blackBeanSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.blackberrySeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.blueberrySeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.broccoliSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.cabbageSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.cantaloupeSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.cauliflowerSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.celerySeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.coffeeSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.cornSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.cranberrySeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.cucumberSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.currantSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.eggplantSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.elderberrySeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.garlicSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.grapeSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.greenBeanSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.greenOnionSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.honeydewSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.hopsSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.kaleSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.kiwiSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.leekSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.lettuceSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.oliveSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.onionSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.peanutSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.pineappleSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.radishSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.raspberrySeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.rhubarbSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.riceSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.rutabagaSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.saguaroSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.spinachSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.squashSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.strawberrySeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.sweetPotatoSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.tomatilloSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.tomatoSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.turnipSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.yamSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.zucchiniSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.mustardSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.chilePepperSeed, 4, 2, 16, 1, 0.5f);
        sellToUser(seeds, ItemRegistry.turmericSeed, 4, 2, 16, 1, 0.5f);
        //event.getGenericTrades().addAll(seeds);
    }

    private static void buyFromUser(List<VillagerTrades.ItemListing> factory, ItemLike item, int itemCount, int tradeXP, int maxTrades, float priceMultiplier) {
        //factory.add((entity, random) -> new MerchantOffer(new ItemStack(item, itemCount), new ItemStack(Items.EMERALD), maxTrades, tradeXP, priceMultiplier));
    }

    private static void sellToUser(List<VillagerTrades.ItemListing> factory, ItemLike item, int itemCount, int purchaseAmount, int maxTrades, int tradeXP, float priceMultiplier) {
        //factory.add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, purchaseAmount), new ItemStack(item, itemCount), maxTrades, tradeXP, priceMultiplier));
    }
}
