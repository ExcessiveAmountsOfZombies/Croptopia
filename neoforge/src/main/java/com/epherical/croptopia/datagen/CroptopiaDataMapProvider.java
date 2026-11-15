package com.epherical.croptopia.datagen;

import com.epherical.croptopia.register.Content;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.BlockTransformer;
import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.stateproviders.CopyPropertiesProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedStateProvider;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Transformable;

import java.util.concurrent.CompletableFuture;

import static net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps.TRANSFORMABLES;

public class CroptopiaDataMapProvider extends DataMapProvider {
    /**
     * Create a new provider.
     *
     * @param packOutput     the output location
     * @param lookupProvider a {@linkplain CompletableFuture} supplying the registries
     */
    public CroptopiaDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        this.builder(TRANSFORMABLES)
                .add(Content.CINNAMON.getLog().builtInRegistryHolder(),
                        stripping(Content.CINNAMON.getLog(), Content.CINNAMON.getStrippedLog()), false)
                .add(Content.CINNAMON.getWood().builtInRegistryHolder(),
                        stripping(Content.CINNAMON.getWood(), Content.CINNAMON.getStrippedWood()), false);
    }

    public static Transformable stripping(Block original, Block stripped) {
        RuleBasedStateProvider provider = RuleBasedStateProvider.builder()
                .ifTrueThenProvide(BlockPredicate.matchesBlocks(original), new CopyPropertiesProvider(stripped))
                .build();
        return Transformable.axe(BlockTransformer.BlockTransformData.builder(provider)
                .dropStrategy(BlockTransformer.DropStrategy.FROM_MIDDLE)
                        .loot(CroptopiaLootTableProvider.CINNAMON_DROP)
                .sound(SoundEvents.AXE_STRIP).build());
    }
}
