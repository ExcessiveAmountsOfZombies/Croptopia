package me.thonk.croptopia.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerMixin {


    @Inject(method = "interact", at = {@At("HEAD")})
    public void interact(Entity entity, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        System.out.println(entity);
        if (entity instanceof VillagerEntity) {
            VillagerEntity entity1 = (VillagerEntity) entity;
            System.out.println(entity1.getInventory());
        }

    }
}
