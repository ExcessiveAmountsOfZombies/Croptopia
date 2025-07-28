package com.epherical.croptopia.datagen;

import com.epherical.croptopia.blocks.LeafCropBlock;
import com.epherical.croptopia.register.Content;
import com.epherical.croptopia.register.helpers.FarmlandCrop;
import com.epherical.croptopia.register.helpers.Tree;
import com.epherical.croptopia.register.helpers.TreeCrop;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.CopyBlockState;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.AddTableLootModifier;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import static com.epherical.croptopia.CroptopiaMod.MODID;
import static com.epherical.croptopia.CroptopiaMod.createIdentifier;

public class CroptopiaLootTableProvider extends BlockLootSubProvider {


    public static final ResourceKey<LootTable> TUNA_SANDWICH_LOOT = ResourceKey.create(Registries.LOOT_TABLE, createIdentifier("chests/tuna_sandwich_loot"));
    public static final ResourceKey<LootTable> MOUNTAIN_SALT_LOOT = ResourceKey.create(Registries.LOOT_TABLE, createIdentifier("chests/mountain_salt_loot"));

    public static final ResourceKey<LootTable> COD_ROE_DROP = ResourceKey.create(Registries.LOOT_TABLE, createIdentifier("entities/cod_roe_drop"));
    public static final ResourceKey<LootTable> SALMON_ROE_DROP = ResourceKey.create(Registries.LOOT_TABLE, createIdentifier("entities/salmon_roe_drop"));
    public static final ResourceKey<LootTable> TROPICAL_ROE_DROP = ResourceKey.create(Registries.LOOT_TABLE, createIdentifier("entities/tropical_roe_drop"));
    public static final ResourceKey<LootTable> GLOWING_SQUID_GLOW_CALAMARI = ResourceKey.create(Registries.LOOT_TABLE, createIdentifier("entities/glowing_squid_glowing_calamari_drop"));
    public static final ResourceKey<LootTable> SQUID_CALAMARI_DROP = ResourceKey.create(Registries.LOOT_TABLE, createIdentifier("entities/squid_calamari_drop"));
    public static final ResourceKey<LootTable> RAVAGER_MEAT_DROP = ResourceKey.create(Registries.LOOT_TABLE, createIdentifier("entities/ravager_meat_drop"));

    public static final ResourceKey<LootTable> CROPTOPIA_FISHING_TABLE = ResourceKey.create(Registries.LOOT_TABLE, createIdentifier("gameplay/fishing"));


    public CroptopiaLootTableProvider(HolderLookup.Provider registries) {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags(), registries);
    }


    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BuiltInRegistries.BLOCK.entrySet()
                .stream()
                .filter(entry -> entry.getKey().location().getNamespace().equals(MODID))
                .map(Map.Entry::getValue)
                .toList();
    }

    @Override
    protected void generate() {
        for (FarmlandCrop farmlandCrop : FarmlandCrop.FARMLAND_CROPS) {
            LootItemCondition.Builder ageCondition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(farmlandCrop.asBlock())
                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlockStateProperties.AGE_7, 7));
            this.add(farmlandCrop.asBlock(),
                    this.applyExplosionDecay(farmlandCrop.asBlock(),
                            LootTable.lootTable()
                                    .withPool(LootPool.lootPool().when(ageCondition)
                                            .add(LootItem.lootTableItem(farmlandCrop.asItem())
                                                    .apply(ApplyBonusCount.addBonusBinomialDistributionCount(registries.holderOrThrow(Enchantments.FORTUNE), 0.5714286F, 3))))));
        }
        for (TreeCrop treeCrop : TreeCrop.TREE_CROPS) {
            LootItemFunction.Builder copyState = CopyBlockState.copyState(treeCrop.asBlock())
                    .copy(LeafCropBlock.AGE);
            LootItemCondition.Builder ageCondition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(treeCrop.asBlock())
                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlockStateProperties.AGE_3, 3));

            LootItemCondition.Builder shearsCondition =
                    MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));

            LootItemCondition.Builder notShearsCondition = shearsCondition.invert();

            this.add(treeCrop.asBlock(),
                    this.applyExplosionDecay(treeCrop.asBlock(),
                            LootTable.lootTable()
                                    .withPool(LootPool.lootPool()
                                            .add(LootItem.lootTableItem(treeCrop.asItem())
                                                    .when(notShearsCondition)
                                                    .when(ageCondition)))
                                    .withPool(LootPool.lootPool()
                                            .add(LootItem.lootTableItem(treeCrop.getSaplingItem()).setWeight(5))
                                            .add(EmptyLootItem.emptyItem().setWeight(95)))
                                    .withPool(LootPool.lootPool()
                                            .add(LootItem.lootTableItem(treeCrop.getLeavesItem())
                                                    .when(shearsCondition)
                                                    .apply(copyState)))
                    )
            );
            this.dropSelf(treeCrop.getSaplingBlock());
        }
        for (Tree tree : Tree.copy()) {
            this.dropSelf(tree.asBlock());
            this.dropSelf(tree.getSaplingBlock());
            this.dropSelf(tree.getWood());
            this.dropSelf(tree.getStrippedLog());
            this.dropSelf(tree.getStrippedWood());
            this.dropSelf(tree.getLog());
            this.add(tree.getLeaves(), block -> this.createLeavesDrops(block, tree.getSaplingBlock(), NORMAL_LEAVES_SAPLING_CHANCES));
        }
        this.add(Content.SALT_ORE_BLOCK,
                block -> this.createSilkTouchDispatchTable(block,
                        this.applyExplosionCondition(block,
                                LootItem.lootTableItem(Content.SALT)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 5)))
                                        .apply(ApplyBonusCount.addUniformBonusCount(registries.holderOrThrow(Enchantments.FORTUNE), 2)))));
    }

    public record FishingLoot(HolderLookup.Provider reg) implements LootTableSubProvider {

        @Override
        public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
            output.accept(CROPTOPIA_FISHING_TABLE,
                    LootTable.lootTable()
                            .withPool(
                                    LootPool.lootPool()
                                            .setRolls(ConstantValue.exactly(1))
                                            .add(EmptyLootItem.emptyItem().setWeight(65))
                                            .add(LootItem.lootTableItem(Content.TUNA).setWeight(5))
                                            .add(LootItem.lootTableItem(Content.ANCHOVY).setWeight(5))
                                            .add(LootItem.lootTableItem(Content.SHRIMP).setWeight(5))
                                            .add(LootItem.lootTableItem(Content.CRAB).setWeight(5))
                                            .add(LootItem.lootTableItem(Content.CLAM).setWeight(5))
                                            .add(LootItem.lootTableItem(Content.OYSTER).setWeight(5))
                                            .add(LootItem.lootTableItem(Content.SEA_LETTUCE).setQuality(5))
                            ));
        }
    }

    public record EntityLoot(HolderLookup.Provider reg) implements LootTableSubProvider {

        @Override
        public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
            output.accept(COD_ROE_DROP,
                    LootTable.lootTable()
                            .withPool(
                                    LootPool.lootPool()
                                            .setRolls(ConstantValue.exactly(1))
                                            .add(LootItem.lootTableItem(Content.ROE)
                                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                            ));
            output.accept(SALMON_ROE_DROP,
                    LootTable.lootTable()
                            .withPool(
                                    LootPool.lootPool()
                                            .setRolls(ConstantValue.exactly(1))
                                            .add(LootItem.lootTableItem(Content.ROE)
                                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                            ));
            output.accept(TROPICAL_ROE_DROP,
                    LootTable.lootTable()
                            .withPool(
                                    LootPool.lootPool()
                                            .setRolls(ConstantValue.exactly(1))
                                            .add(LootItem.lootTableItem(Content.ROE)
                                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                            ));
            output.accept(GLOWING_SQUID_GLOW_CALAMARI,
                    LootTable.lootTable()
                            .withPool(
                                    LootPool.lootPool()
                                            .setRolls(ConstantValue.exactly(1))
                                            .add(LootItem.lootTableItem(Content.GLOWING_CALAMARI)
                                                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                            ));
            output.accept(SQUID_CALAMARI_DROP,
                    LootTable.lootTable()
                            .withPool(
                                    LootPool.lootPool()
                                            .setRolls(ConstantValue.exactly(1))
                                            .add(LootItem.lootTableItem(Content.CALAMARI)
                                                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                            ));
            output.accept(RAVAGER_MEAT_DROP,
                    LootTable.lootTable()
                            .withPool(
                                    LootPool.lootPool()
                                            .setRolls(ConstantValue.exactly(1))
                                            .add(LootItem.lootTableItem(Content.RAW_RAVAGER_MEAT)
                                                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.reg, UniformGenerator.between(0.0F, 1.0F)))
                                            )));
        }
    }


    public record ChestLoot(HolderLookup.Provider reg) implements LootTableSubProvider {

        @Override
        public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
            output.accept(TUNA_SANDWICH_LOOT,
                    LootTable.lootTable()
                            .withPool(
                                    LootPool.lootPool()
                                            .setRolls(UniformGenerator.between(0, 2))
                                            .add(LootItem.lootTableItem(Content.TUNA_SANDWICH).setWeight(10))
                                            .add(EmptyLootItem.emptyItem().setWeight(90))
                            ));
            output.accept(MOUNTAIN_SALT_LOOT,
                    LootTable.lootTable()
                            .withPool(
                                    LootPool.lootPool()
                                            .setRolls(UniformGenerator.between(0, 2))
                                            .add(LootItem.lootTableItem(Content.MOUNTAIN_SALT).setWeight(5))
                                            .add(EmptyLootItem.emptyItem().setWeight(95))
                            ));
        }
    }

    public static class GlobalLootProvider extends GlobalLootModifierProvider {

        public GlobalLootProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
            super(output, registries, MODID);
        }

        @Override
        protected void start() {
            add("add_tuna_to_dungeon", new AddTableLootModifier(
                    new LootItemCondition[]{
                            LootTableIdCondition.builder(BuiltInLootTables.SIMPLE_DUNGEON.location()).build()
                    }, TUNA_SANDWICH_LOOT));
            add("add_mountain_salt_to_shipwreck", new AddTableLootModifier(
                    new LootItemCondition[]{
                            LootTableIdCondition.builder(BuiltInLootTables.SHIPWRECK_TREASURE.location()).build()
                    }, MOUNTAIN_SALT_LOOT));
            add("add_roe_to_cod", new AddTableLootModifier(
                    new LootItemCondition[]{
                            LootTableIdCondition.builder(ResourceLocation.fromNamespaceAndPath("minecraft", "entities/cod")).build()
                    }, COD_ROE_DROP));
            add("add_roe_to_salmon", new AddTableLootModifier(
                    new LootItemCondition[]{
                            LootTableIdCondition.builder(ResourceLocation.fromNamespaceAndPath("minecraft", "entities/salmon")).build()
                    }, SALMON_ROE_DROP));
            add("add_roe_to_tropical_fish", new AddTableLootModifier(
                    new LootItemCondition[]{
                            LootTableIdCondition.builder(ResourceLocation.fromNamespaceAndPath("minecraft", "entities/tropical_fish")).build()
                    }, TROPICAL_ROE_DROP));
            add("add_calamari_to_squid", new AddTableLootModifier(
                    new LootItemCondition[]{
                            LootTableIdCondition.builder(ResourceLocation.fromNamespaceAndPath("minecraft", "entities/squid")).build()
                    }, SQUID_CALAMARI_DROP));
            add("add_ravager_meat_to_ravager", new AddTableLootModifier(
                    new LootItemCondition[]{
                            LootTableIdCondition.builder(ResourceLocation.fromNamespaceAndPath("minecraft", "entities/ravager")).build()
                    }, RAVAGER_MEAT_DROP));
            add("add_glowing_calamari_to_glowing_squid", new AddTableLootModifier(
                    new LootItemCondition[]{
                            LootTableIdCondition.builder(ResourceLocation.fromNamespaceAndPath("minecraft", "entities/glow_squid")).build()
                    }, GLOWING_SQUID_GLOW_CALAMARI));
            add("add_croptopia_fish_to_aquaculture", new AddTableLootModifier(
                    new LootItemCondition[]{
                            LootTableIdCondition.builder(ResourceLocation.fromNamespaceAndPath("aquaculture", "gameplay/fishing/fish")).build()
                    }, CROPTOPIA_FISHING_TABLE));
            add("add_croptopia_fish_to_minecraft", new AddTableLootModifier(
                    new LootItemCondition[]{
                            LootTableIdCondition.builder(BuiltInLootTables.FISHING.location()).build()
                    }, CROPTOPIA_FISHING_TABLE));
        }
    }
}
