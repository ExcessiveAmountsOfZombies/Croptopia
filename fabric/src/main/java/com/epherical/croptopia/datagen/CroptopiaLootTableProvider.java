package com.epherical.croptopia.datagen;

import com.epherical.croptopia.blocks.LeafCropBlock;
import com.epherical.croptopia.register.Content;
import com.epherical.croptopia.register.helpers.FarmlandCrop;
import com.epherical.croptopia.register.helpers.Tree;
import com.epherical.croptopia.register.helpers.TreeCrop;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.CopyBlockState;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class CroptopiaLootTableProvider extends FabricBlockLootTableProvider {

    public CroptopiaLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        for (FarmlandCrop farmlandCrop : FarmlandCrop.FARMLAND_CROPS) {
            LootItemCondition.Builder ageCondition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(farmlandCrop.asBlock())
                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlockStateProperties.AGE_7, 7));
            add(farmlandCrop.asBlock(),
                    applyExplosionDecay(farmlandCrop.asBlock(),
                            LootTable.lootTable()
                                    .withPool(LootPool.lootPool().when(ageCondition)
                                            .add(LootItem.lootTableItem(farmlandCrop.asItem())
                                                    .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))))));
        }

        LootItemCondition.Builder shearsCondition = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));
        LootItemCondition.Builder notShearsCondition = shearsCondition.invert();
        for (TreeCrop treeCrop : TreeCrop.TREE_CROPS) {
            LootItemCondition.Builder ageCondition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(treeCrop.asBlock())
                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LeafCropBlock.AGE, 3));

            add(treeCrop.asBlock(),
                    applyExplosionDecay(treeCrop.asBlock(),
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
                                                    .apply(CopyBlockState.copyState(treeCrop.asBlock()).copy(LeafCropBlock.AGE))))));
            dropSelf(treeCrop.getSaplingBlock());
        }

        for (Tree tree : Tree.copy()) {
            dropSelf(tree.asBlock());
            dropSelf(tree.getSaplingBlock());
            dropSelf(tree.getWood());
            dropSelf(tree.getStrippedLog());
            dropSelf(tree.getStrippedWood());
            dropSelf(tree.getLog());
            add(tree.getLeaves(), block -> createLeavesDrops(block, tree.getSaplingBlock(), NORMAL_LEAVES_SAPLING_CHANCES));
        }

        add(Content.SALT_ORE_BLOCK,
                block -> createSilkTouchDispatchTable(block,
                        applyExplosionCondition(block,
                                LootItem.lootTableItem(Content.SALT)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
                                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 2)))));
    }
}
