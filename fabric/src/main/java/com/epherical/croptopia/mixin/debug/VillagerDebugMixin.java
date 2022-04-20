package com.epherical.croptopia.mixin.debug;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Villager.class)
public abstract class VillagerDebugMixin {



    @Inject(method = "mobInteract", at = @At("HEAD"))
    public void ugh(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        /*VillagerEntity thisInstance = (VillagerEntity) (Object)this;
        System.out.println(thisInstance.getInventory());
        System.out.println(thisInstance.getInventory().size());*/
    }

}
