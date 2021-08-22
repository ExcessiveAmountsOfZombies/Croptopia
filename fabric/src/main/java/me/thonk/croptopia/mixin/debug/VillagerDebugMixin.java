package me.thonk.croptopia.mixin.debug;

import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VillagerEntity.class)
public abstract class VillagerDebugMixin {



    @Inject(method = "interactMob", at = @At("HEAD"))
    public void ugh(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        /*VillagerEntity thisInstance = (VillagerEntity) (Object)this;
        System.out.println(thisInstance.getInventory());
        System.out.println(thisInstance.getInventory().size());*/
    }

}
