package com.epherical.croptopia.datagen;

import com.epherical.croptopia.register.Content;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.criterion.ConsumeItemTrigger;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import java.util.Objects;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public final class CroptopiaAdvancementProvider {
    private static final String MODID = "croptopia";
    private static final Identifier ROOT_BACKGROUND = Identifier.fromNamespaceAndPath(MODID, "block/salt_ore");

    private CroptopiaAdvancementProvider() {
    }

    public static AdvancementProvider create(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        return new AdvancementProvider(output, registries, List.of(new CroptopiaAdvancements()));
    }

    private static final class CroptopiaAdvancements implements AdvancementSubProvider {
        @Override
        public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> output) {
            HolderGetter<Item> items = registries.lookupOrThrow(Registries.ITEM);

            AdvancementHolder root = advancement(
                    Content.COFFEE,
                    Component.literal("Croptopia"),
                    description("root"),
                    ROOT_BACKGROUND,
                    AdvancementType.TASK,
                    false,
                    false,
                    false)
                    .addCriterion("root", hasTaggedItem(items, "minecraft", "hoes"))
                    .save(output, id("root"));

            AdvancementHolder salt = advancement(Content.SALT, title("salt"), description("salt"), null, AdvancementType.TASK, true, true, false)
                    .parent(root)
                    .addCriterion("salt", has(Content.SALT))
                    .save(output, id("salt"));

            advancement(Content.CINNAMON, title("cinnamon"), description("cinnamon"), null, AdvancementType.TASK, true, true, false)
                    .parent(salt)
                    .addCriterion("cinnamon", has(Content.CINNAMON))
                    .save(output, id("cinnamon"));

            AdvancementHolder mortarAndPestle = advancement(
                    Content.MORTAR_AND_PESTLE,
                    title("mortar_and_pestle"),
                    description("mortar_and_pestle"),
                    null,
                    AdvancementType.TASK,
                    true,
                    true,
                    false
            )
                    .parent(root)
                    .addCriterion("mortar_and_pestle", has(Content.MORTAR_AND_PESTLE))
                    .save(output, id("mortar_and_pestle"));

            AdvancementHolder knife = advancement(Content.KNIFE, title("knife"), description("knife"), null, AdvancementType.TASK, true, true, false)
                    .parent(mortarAndPestle)
                    .addCriterion("knife", hasTaggedItem(items, "c", "tools/knives"))
                    .save(output, id("knife"));

            AdvancementHolder cookingPot = advancement(Content.COOKING_POT, title("pot"), description("pot"), null, AdvancementType.TASK, true, true, false)
                    .parent(knife)
                    .addCriterion("cooking_pot", has(Content.COOKING_POT))
                    .save(output, id("pot"));

            AdvancementHolder fryingPan = advancement(Content.FRYING_PAN, title("frying_pan"), description("frying_pan"), null, AdvancementType.TASK, true, true, false)
                    .parent(cookingPot)
                    .addCriterion("frying_pan", has(Content.FRYING_PAN))
                    .save(output, id("frying_pan"));

            advancement(Content.FOOD_PRESS, title("food_press"), description("food_press"), null, AdvancementType.TASK, true, true, false)
                    .parent(fryingPan)
                    .addCriterion("food_press", has(Content.FOOD_PRESS))
                    .save(output, id("food_press"));

            AdvancementHolder getSeeds = advancement(Content.BASIL.getSeedItem(), title("getseed"), description("getseed"), null, AdvancementType.TASK, true, true, false)
                    .parent(root)
                    .addCriterion("has_seeds", hasTaggedItem(items, MODID, "advancements_seeds"))
                    .save(output, id("getseeds"));

            AdvancementHolder getSapling = advancement(Content.MANGO.getSaplingItem(), title("getsapling"), description("getsapling"), null, AdvancementType.TASK, true, true, false)
                    .parent(root)
                    .addCriterion("has_sapling", hasTaggedItem(items, MODID, "advancements_saplings"))
                    .save(output, id("getsapling"));

            AdvancementHolder getDrinks = advancement(Content.APPLE_JUICE, title("getdrinks"), description("getdrinks"), null, AdvancementType.TASK, true, true, false)
                    .parent(getSapling)
                    .addCriterion("has_drink", consumeTaggedItem(items, "advancements_drinks"))
                    .save(output, id("getdrinks"));

            Advancement.Builder gatherDrinks = advancement(Content.BEER, title("gather_drinks"), description("gather_drinks"), null, AdvancementType.GOAL, true, true, false)
                    .parent(getDrinks);
            addHasCriteria(gatherDrinks, Content.GRAPE_JUICE, Content.ORANGE_JUICE, Content.APPLE_JUICE, Content.CRANBERRY_JUICE, Content.SAGUARO_JUICE, Content.TOMATO_JUICE, Content.MELON_JUICE, Content.PINEAPPLE_JUICE, Content.WINE, Content.COFFEE, Content.LEMONADE, Content.LIMEADE, Content.SOY_MILK, Content.STRAWBERRY_SMOOTHIE, Content.BANANA_SMOOTHIE, Content.KALE_SMOOTHIE, Content.RUM, Content.PUMPKIN_SPICE_LATTE, Content.TEA, Content.FRUIT_SMOOTHIE, Content.MEAD, Content.BEER)
                    .save(output, id("gather_drinks"));

            AdvancementHolder eatCrafted = advancement(Content.TOFUBURGER, title("eatcrafted"), description("eatcrafted"), null, AdvancementType.TASK, true, true, false)
                    .parent(root)
                    .addCriterion("eat_crafted", consumeTaggedItem(items, "advancements_food_crafted"))
                    .save(output, id("eatcrafted"));

            AdvancementHolder eatBig = advancement(Content.LEMON_CHICKEN, title("eatbig"), description("eatbig"), null, AdvancementType.GOAL, true, true, false)
                    .parent(eatCrafted)
                    .addCriterion("eat_big", consumeTaggedItem(items, "advancements_food_big"))
                    .save(output, id("eatbig"));

            Advancement.Builder gatherPlains = advancement(Content.BROCCOLI.getSeedItem(), title("gather_plains"), description("gather_plains"), null, AdvancementType.TASK, true, true, false)
                    .parent(getSeeds);
            AdvancementHolder gatherPlainsHolder = addHasCriteria(gatherPlains, Content.BELLPEPPER.getSeedItem(), Content.BROCCOLI.getSeedItem(), Content.CABBAGE.getSeedItem(), Content.CORN.getSeedItem(), Content.CUCUMBER.getSeedItem(), Content.GREENBEAN.getSeedItem(), Content.KALE.getSeedItem(), Content.LETTUCE.getSeedItem(), Content.SWEETPOTATO.getSeedItem(), Content.MUSTARD.getSeedItem(), Content.CHILE_PEPPER.getSeedItem(), Content.OAT.getSeedItem(), Content.BARLEY.getSeedItem(), Content.SOYBEAN.getSeedItem(), Content.PEPPER.getSeedItem())
                    .save(output, id("gather_plains"));

            Advancement.Builder gatherDesert = advancement(Content.SAGUARO.getSeedItem(), title("gather_desert"), description("gather_desert"), null, AdvancementType.TASK, true, true, false)
                    .parent(gatherPlainsHolder)
                    .addCriterion("saguaro_seed", has(Content.SAGUARO.getSeedItem()));
            AdvancementHolder gatherDesertHolder = gatherDesert.save(output, id("gather_desert"));

            Advancement.Builder gatherForest = advancement(Content.CELERY.getSeedItem(), title("gather_forest"), description("gather_forest"), null, AdvancementType.TASK, true, true, false)
                    .parent(gatherDesertHolder);
            AdvancementHolder gatherForestHolder = addHasCriteria(gatherForest, Content.BLACKBEAN.getSeedItem(), Content.BLACKBERRY.getSeedItem(), Content.BLUEBERRY.getSeedItem(), Content.CANTALOUPE.getSeedItem(), Content.CAULIFLOWER.getSeedItem(), Content.CELERY.getSeedItem(), Content.ELDERBERRY.getSeedItem(), Content.GRAPE.getSeedItem(), Content.RASPBERRY.getSeedItem(), Content.RADISH.getSeedItem(), Content.SPINACH.getSeedItem(), Content.STRAWBERRY.getSeedItem(), Content.TOMATILLO.getSeedItem(), Content.TOMATO.getSeedItem(), Content.TEA_LEAVES.getSeedItem())
                    .save(output, id("gather_forest"));

            Advancement.Builder gatherSavanna = advancement(Content.YAM.getSeedItem(), title("gather_savanna"), description("gather_savanna"), null, AdvancementType.TASK, true, true, false)
                    .parent(gatherForestHolder);
            AdvancementHolder gatherSavannaHolder = addHasCriteria(gatherSavanna, Content.HOPS.getSeedItem(), Content.KIWI.getSeedItem(), Content.LEEK.getSeedItem(), Content.OLIVE.getSeedItem(), Content.RUTABAGA.getSeedItem(), Content.SQUASH.getSeedItem(), Content.YAM.getSeedItem(), Content.ZUCCHINI.getSeedItem(), Content.TURMERIC.getSeedItem(), Content.GINGER.getSeedItem())
                    .save(output, id("gather_savanna"));

            Advancement.Builder gatherJungle = advancement(Content.COFFEE_BEANS.getSeedItem(), title("gather_jungle"), description("gather_jungle"), null, AdvancementType.TASK, true, true, false)
                    .parent(gatherSavannaHolder);
            AdvancementHolder gatherJungleHolder = addHasCriteria(gatherJungle, Content.COFFEE_BEANS.getSeedItem(), Content.EGGPLANT.getSeedItem(), Content.GARLIC.getSeedItem(), Content.GREENONION.getSeedItem(), Content.HONEYDEW.getSeedItem(), Content.ONION.getSeedItem(), Content.RHUBARB.getSeedItem(), Content.TURNIP.getSeedItem(), Content.BASIL.getSeedItem(), Content.PEANUT.getSeedItem(), Content.PINEAPPLE.getSeedItem(), Content.RICE.getSeedItem(), Content.VANILLA.getSeedItem())
                    .save(output, id("gather_jungle"));

            Advancement.Builder gatherSwamp = advancement(Content.CRANBERRY.getSeedItem(), title("gather_swamp"), description("gather_swamp"), null, AdvancementType.TASK, true, true, false)
                    .parent(gatherJungleHolder);
            AdvancementHolder gatherSwampHolder = addHasCriteria(gatherSwamp, Content.ASPARAGUS.getSeedItem(), Content.ARTICHOKE.getSeedItem(), Content.CRANBERRY.getSeedItem(), Content.CURRANT.getSeedItem())
                    .save(output, id("gather_swamp"));

            Advancement.Builder gatherAll = advancement(Content.TURNIP.getSeedItem(), title("gather_all"), description("gather_all"), null, AdvancementType.GOAL, true, true, false)
                    .parent(gatherSwampHolder);
            addHasCriteria(gatherAll, Content.SAGUARO.getSeedItem(), Content.BLACKBEAN.getSeedItem(), Content.BLACKBERRY.getSeedItem(), Content.BLUEBERRY.getSeedItem(), Content.CANTALOUPE.getSeedItem(), Content.CAULIFLOWER.getSeedItem(), Content.CELERY.getSeedItem(), Content.ELDERBERRY.getSeedItem(), Content.GRAPE.getSeedItem(), Content.RASPBERRY.getSeedItem(), Content.SPINACH.getSeedItem(), Content.STRAWBERRY.getSeedItem(), Content.TOMATILLO.getSeedItem(), Content.TOMATO.getSeedItem(), Content.TEA_LEAVES.getSeedItem(), Content.COFFEE_BEANS.getSeedItem(), Content.EGGPLANT.getSeedItem(), Content.GARLIC.getSeedItem(), Content.GREENONION.getSeedItem(), Content.HONEYDEW.getSeedItem(), Content.ONION.getSeedItem(), Content.RHUBARB.getSeedItem(), Content.TURNIP.getSeedItem(), Content.BASIL.getSeedItem(), Content.VANILLA.getSeedItem(), Content.BELLPEPPER.getSeedItem(), Content.BROCCOLI.getSeedItem(), Content.CABBAGE.getSeedItem(), Content.CORN.getSeedItem(), Content.CUCUMBER.getSeedItem(), Content.GREENBEAN.getSeedItem(), Content.KALE.getSeedItem(), Content.LETTUCE.getSeedItem(), Content.SWEETPOTATO.getSeedItem(), Content.MUSTARD.getSeedItem(), Content.CHILE_PEPPER.getSeedItem(), Content.OAT.getSeedItem(), Content.BARLEY.getSeedItem(), Content.SOYBEAN.getSeedItem(), Content.PEPPER.getSeedItem(), Content.HOPS.getSeedItem(), Content.KIWI.getSeedItem(), Content.LEEK.getSeedItem(), Content.OLIVE.getSeedItem(), Content.RUTABAGA.getSeedItem(), Content.SQUASH.getSeedItem(), Content.YAM.getSeedItem(), Content.ZUCCHINI.getSeedItem(), Content.TURMERIC.getSeedItem(), Content.GINGER.getSeedItem(), Content.ASPARAGUS.getSeedItem(), Content.ARTICHOKE.getSeedItem(), Content.CRANBERRY.getSeedItem(), Content.CURRANT.getSeedItem())
                    .save(output, id("gather_all"));

            Advancement.Builder gatherTreeDarkForest = advancement(Content.CASHEW.getSaplingItem(), title("gather_tree_dark_forest"), description("gather_tree_dark_forest"), null, AdvancementType.TASK, true, true, false)
                    .parent(getSapling);
            AdvancementHolder gatherTreeDarkForestHolder = addHasCriteria(gatherTreeDarkForest, Content.ALMOND.getSaplingItem(), Content.CASHEW.getSaplingItem(), Content.PECAN.getSaplingItem(), Content.WALNUT.getSaplingItem())
                    .save(output, id("gather_tree_dark_forest"));

            Advancement.Builder gatherTreeJungle = advancement(Content.BANANA.getSaplingItem(), title("gather_tree_jungle"), description("gather_tree_jungle"), null, AdvancementType.TASK, true, true, false)
                    .parent(gatherTreeDarkForestHolder);
            AdvancementHolder gatherTreeJungleHolder = addHasCriteria(gatherTreeJungle, Content.DATE.getSaplingItem(), Content.DRAGONFRUIT.getSaplingItem(), Content.MANGO.getSaplingItem(), Content.NUTMEG.getSaplingItem(), Content.COCONUT.getSaplingItem(), Content.KUMQUAT.getSaplingItem(), Content.FIG.getSaplingItem(), Content.GRAPEFRUIT.getSaplingItem(), Content.BANANA.getSaplingItem(), Content.CINNAMON.getSapling())
                    .save(output, id("gather_tree_jungle"));

            Advancement.Builder gatherTreePlains = advancement(Content.APPLE.getSaplingItem(), title("gather_tree_plains"), description("gather_tree_plains"), null, AdvancementType.TASK, true, true, false)
                    .parent(gatherTreeJungleHolder);
            AdvancementHolder gatherTreePlainsHolder = addHasCriteria(gatherTreePlains, Content.APPLE.getSaplingItem(), Content.ORANGE.getSaplingItem(), Content.PEACH.getSaplingItem())
                    .save(output, id("gather_tree_plains"));

            Advancement.Builder gatherTreeForest = advancement(Content.CHERRY.getSaplingItem(), title("gather_tree_forest"), description("gather_tree_forest"), null, AdvancementType.TASK, true, true, false)
                    .parent(gatherTreePlainsHolder);
            AdvancementHolder gatherTreeForestHolder = addHasCriteria(gatherTreeForest, Content.APRICOT.getSaplingItem(), Content.AVOCADO.getSaplingItem(), Content.CHERRY.getSaplingItem(), Content.LEMON.getSaplingItem(), Content.LIME.getSaplingItem(), Content.NECTARINE.getSaplingItem(), Content.ORANGE.getSaplingItem(), Content.PERSIMMON.getSaplingItem(), Content.PLUM.getSaplingItem(), Content.STARFRUIT.getSaplingItem(), Content.PEAR.getSaplingItem())
                    .save(output, id("gather_tree_forest"));

            Advancement.Builder gatherTreeAll = advancement(Content.COCONUT.getSaplingItem(), title("gather_tree_all"), description("gather_tree_all"), null, AdvancementType.GOAL, true, true, false)
                    .parent(gatherTreeForestHolder);
            addHasCriteria(gatherTreeAll, Content.ALMOND.getSaplingItem(), Content.APPLE.getSaplingItem(), Content.APRICOT.getSaplingItem(), Content.AVOCADO.getSaplingItem(), Content.BANANA.getSaplingItem(), Content.CASHEW.getSaplingItem(), Content.CHERRY.getSaplingItem(), Content.CINNAMON.getSapling(), Content.COCONUT.getSaplingItem(), Content.DATE.getSaplingItem(), Content.DRAGONFRUIT.getSaplingItem(), Content.FIG.getSaplingItem(), Content.GRAPEFRUIT.getSaplingItem(), Content.KUMQUAT.getSaplingItem(), Content.LEMON.getSaplingItem(), Content.LIME.getSaplingItem(), Content.MANGO.getSaplingItem(), Content.NECTARINE.getSaplingItem(), Content.NUTMEG.getSaplingItem(), Content.ORANGE.getSaplingItem(), Content.PEACH.getSaplingItem(), Content.PEAR.getSaplingItem(), Content.PECAN.getSaplingItem(), Content.PERSIMMON.getSaplingItem(), Content.PLUM.getSaplingItem(), Content.WALNUT.getSaplingItem())
                    .save(output, id("gather_tree_all"));

            Advancement.Builder gatherFood = advancement(Content.SUPREME_PIZZA, title("gather_food"), description("gather_food"), null, AdvancementType.CHALLENGE, true, true, false)
                    .parent(eatBig)
                    .requirements(AdvancementRequirements.Strategy.AND);
            AdvancementHolder gatherFoodHolder = addHasCriteria(gatherFood, Content.CHEESE, Content.BUTTER, Content.TOFU, Content.CHOCOLATE, Content.TORTILLA, Content.SALSA, Content.ARTICHOKE_DIP, Content.PEPPERONI, Content.GRAPE_JAM, Content.STRAWBERRY_JAM, Content.PEACH_JAM, Content.APRICOT_JAM, Content.BLACKBERRY_JAM, Content.BLUEBERRY_JAM, Content.CHERRY_JAM, Content.ELDERBERRY_JAM, Content.RASPBERRY_JAM, Content.BEEF_JERKY, Content.PORK_JERKY, Content.KALE_CHIPS, Content.POTATO_CHIPS, Content.STEAMED_RICE, Content.FRENCH_FRIES, Content.SWEET_POTATO_FRIES, Content.ONION_RINGS, Content.RAISINS, Content.POPCORN, Content.DOUGHNUT, Content.BAKED_BEANS, Content.TOAST, Content.CUCUMBER_SALAD, Content.CAESAR_SALAD, Content.LEAFY_SALAD, Content.FRUIT_SALAD, Content.VEGGIE_SALAD, Content.PORK_AND_BEANS, Content.OATMEAL, Content.LEEK_SOUP, Content.YOGHURT, Content.SAUCY_CHIPS, Content.ROASTED_NUTS, Content.TRAIL_MIX, Content.NOUGAT, Content.SCRAMBLED_EGGS, Content.BUTTERED_TOAST, Content.TOAST_WITH_JAM, Content.HAM_SANDWICH, Content.PEANUT_BUTTER_AND_JAM, Content.BLT, Content.GRILLED_CHEESE, Content.CHEESEBURGER, Content.HAMBURGER, Content.TOFUBURGER, Content.PIZZA, Content.SUPREME_PIZZA, Content.CHEESE_PIZZA, Content.PINEAPPLE_PEPPERONI_PIZZA, Content.LEMON_CHICKEN, Content.FRIED_CHICKEN, Content.CHICKEN_AND_NOODLES, Content.CHICKEN_AND_DUMPLINGS, Content.TOFU_AND_DUMPLINGS, Content.SPAGHETTI_SQUASH, Content.CHICKEN_AND_RICE, Content.TACO, Content.EGG_ROLL, Content.CASHEW_CHICKEN, Content.APPLE_PIE, Content.YAM_JAM, Content.BANANA_CREAM_PIE, Content.CANDY_CORN, Content.VANILLA_ICE_CREAM, Content.STRAWBERRY_ICE_CREAM, Content.MANGO_ICE_CREAM, Content.RUM_RAISIN_ICE_CREAM, Content.PECAN_ICE_CREAM, Content.CHERRY_PIE, Content.BROWNIES, Content.SNICKER_DOODLE, Content.BANANA_NUT_BREAD, Content.PECAN_PIE, Content.CANDIED_NUTS, Content.ALMOND_BRITTLE, Content.OATMEAL_COOKIE, Content.NUTTY_COOKIE, Content.BURRITO, Content.TOSTADA, Content.HORCHATA, Content.CARNITAS, Content.FAJITAS, Content.ENCHILADA, Content.CHURROS, Content.TAMALES, Content.TRES_LECHE_CAKE, Content.STUFFED_POBLANOS, Content.CHILI_RELLENO, Content.CREMA, Content.REFRIED_BEANS, Content.CHIMICHANGA, Content.QUESADILLA, Content.SHEPHERDS_PIE, Content.BEEF_WELLINGTON, Content.FISH_AND_CHIPS, Content.ETON_MESS, Content.CORNISH_PASTY, Content.SCONES, Content.FIGGY_PUDDING, Content.TREACLE_TART, Content.STICKY_TOFFEE_PUDDING, Content.TRIFLE, Content.AJVAR, Content.AJVAR_TOAST, Content.AVOCADO_TOAST, Content.RAW_BACON, Content.BAKED_SWEET_POTATO, Content.BAKED_YAM, Content.BEEF_STEW, Content.BEEF_STIR_FRY, Content.BUTTERED_GREEN_BEANS, Content.CHEESY_ASPARAGUS, Content.CHOCOLATE_ICE_CREAM, Content.COOKED_BACON, Content.EGGPLANT_PARMESAN, Content.FRUIT_CAKE, Content.GRILLED_EGGPLANT, Content.KIWI_SORBET, Content.LEMON_COCONUT_BAR, Content.NETHER_WART_STEW, Content.PEANUT_BUTTER, Content.PEANUT_BUTTER_W_CELERY, Content.POTATO_SOUP, Content.RATATOUILLE, Content.RHUBARB_CRISP, Content.ROASTED_ASPARAGUS, Content.ROASTED_RADISHES, Content.ROASTED_SQUASH, Content.ROASTED_TURNIPS, Content.STEAMED_BROCCOLI, Content.STEAMED_GREEN_BEANS, Content.STIR_FRY, Content.STUFFED_ARTICHOKE, Content.TOAST_SANDWICH, Content.ROASTED_PUMPKIN_SEEDS, Content.ROASTED_SUNFLOWER_SEEDS, Content.PUMPKIN_BARS, Content.CORN_BREAD, Content.PUMPKIN_SOUP, Content.MERINGUE, Content.CABBAGE_ROLL, Content.BORSCHT, Content.GOULASH, Content.BEETROOT_SALAD, Content.CANDIED_KUMQUATS, Content.TUNA, Content.CALAMARI, Content.CRAB, Content.CLAM, Content.OYSTER, Content.COOKED_CALAMARI, Content.STEAMED_CRAB, Content.GLOWING_CALAMARI, Content.SEA_LETTUCE, Content.DEEP_FRIED_SHRIMP, Content.FRIED_CALAMARI, Content.CRAB_LEGS, Content.STEAMED_CLAMS, Content.GRILLED_OYSTERS, Content.ANCHOVY, Content.ANCHOVY_PIZZA, Content.MASHED_POTATOES, Content.COOKED_SHRIMP, Content.COOKED_TUNA)
                    .save(output, id("gather_food"));

            Advancement.Builder transcendentalBreakfast = advancement(Content.TRANSCENDENTAL_BREAKFAST, title("transcendental_breakfast"), description("transcendental_breakfast"), null, AdvancementType.CHALLENGE, true, true, false)
                    .parent(gatherFoodHolder)
                    .requirements(AdvancementRequirements.Strategy.AND);
            addHasCriteria(transcendentalBreakfast, Content.MOUNTAIN_SALT, Content.NETHER_STAR_CAKE, Content.TUNA_SANDWICH, Content.DRAGON_EGG_OMELETTE, Content.COOKED_RAVAGER_MEAT, Content.THE_BIG_BREAKFAST)
                    .save(output, id("transcendental_breakfast"));


        }
    }

    private static Advancement.Builder advancement(
            ItemLike icon,
            Component title,
            Component description,
            Identifier background,
            AdvancementType frame,
            boolean showToast,
            boolean announceChat,
            boolean hidden
    ) {
        return Advancement.Builder.recipeAdvancement().display(icon, title, description, background, frame, showToast, announceChat, hidden);
    }

    private static Component title(String advancement) {
        return Component.translatable("advancements.croptopia." + advancement + ".title");
    }

    private static Component description(String advancement) {
        return Component.translatable("advancements.croptopia." + advancement + ".description");
    }

    private static String id(String path) {
        return Identifier.fromNamespaceAndPath(MODID, path).toString();
    }

    private static TagKey<Item> itemTag(String namespace, String path) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(namespace, path));
    }

    private static Criterion<?> has(ItemLike item) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(item);
    }

    private static Criterion<?> hasTaggedItem(HolderGetter<Item> items, String namespace, String path) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, itemTag(namespace, path)));
    }

    private static Criterion<?> consumeTaggedItem(HolderGetter<Item> items, String path) {
        return ConsumeItemTrigger.TriggerInstance.usedItem(ItemPredicate.Builder.item().of(items, itemTag(MODID, path)));
    }

    private static Advancement.Builder addHasCriteria(Advancement.Builder builder, ItemLike... items) {
        for (ItemLike item : items) {
            String criterionName = Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item.asItem())).getPath();
            builder.addCriterion(criterionName, has(item));
        }
        return builder;
    }

}
