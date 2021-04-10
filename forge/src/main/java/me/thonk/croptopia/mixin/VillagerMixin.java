package me.thonk.croptopia.mixin;

import com.google.common.collect.ImmutableSet;
import me.thonk.croptopia.CroptopiaForge;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.Set;

@Mixin(VillagerEntity.class)
public class VillagerMixin {

    @ModifyArg(method = "isFarmItemInInventory", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/inventory/Inventory;hasAny(Ljava/util/Set;)Z"))
    public Set<Item> addSeedToPlant(Set<Item> set) {
        return ImmutableSet.<Item>builder()
                .addAll(set)
                .addAll(CroptopiaForge.seeds)
                .build();
    }

}
