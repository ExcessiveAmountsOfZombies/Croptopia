package com.epherical.croptopia.mixin;

import com.epherical.croptopia.CroptopiaMod;
import com.google.common.collect.ImmutableSet;
import com.epherical.croptopia.CroptopiaForge;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.Set;

@Mixin(VillagerEntity.class)
public class VillagerMixin {

    @ModifyArg(method = "hasFarmSeeds", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/inventory/Inventory;hasAnyOf(Ljava/util/Set;)Z"))
    public Set<Item> addSeedToPlant(Set<Item> set) {
        return ImmutableSet.<Item>builder()
                .addAll(set)
                .addAll(CroptopiaMod.seeds)
                .build();
    }

}
