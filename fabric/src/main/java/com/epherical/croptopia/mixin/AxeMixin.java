package com.epherical.croptopia.mixin;

import com.epherical.croptopia.register.helpers.Tree;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin for the AxeItem class so that axes can strip more blocks.
 * Supremely cracked, just remove the @Final and you can modify final variables! very cool.
 */
@Mixin(AxeItem.class)
public class AxeMixin {

    @Inject(method = "useOn", at = @At("HEAD"), slice =
    @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/context/UseOnContext;getPlayer()Lnet/minecraft/world/entity/player/Player;"), to = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/level/Level;getBlockState(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;")))
    public void croptopia$use(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        if (context.getLevel().isClientSide)
            return;
        BlockPos pos = context.getClickedPos();
        BlockState state = context.getLevel().getBlockState(pos);
        Tree.attemptPop(state, context, pos);
    }
}
