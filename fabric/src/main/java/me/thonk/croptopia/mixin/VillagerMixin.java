package com.epherical.croptopia.mixin;

import com.google.common.collect.ImmutableSet;
import com.epherical.croptopia.Croptopia;
import com.epherical.croptopia.config.ConfigurableSeed;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.Set;
import java.util.stream.Collectors;

@Mixin(VillagerEntity.class)
public class VillagerMixin {


    @ModifyArg(method = "hasSeedToPlant", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/inventory/SimpleInventory;containsAny(Ljava/util/Set;)Z"))
    public Set<Item> addSeedToPlant(Set<Item> set) {
        return ImmutableSet.<Item>builder()
                .addAll(set)
                .addAll(Croptopia.getSeeds().stream().map(ConfigurableSeed::getSeedItem).collect(Collectors.toSet()))
                .build();
    }
}
