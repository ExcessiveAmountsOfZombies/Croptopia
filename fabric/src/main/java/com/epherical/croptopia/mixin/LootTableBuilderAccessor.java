package com.epherical.croptopia.mixin;

import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(LootTable.Builder.class)
public interface LootTableBuilderAccessor {

    @Accessor("pools")
    List<LootPool> getPools();

}
