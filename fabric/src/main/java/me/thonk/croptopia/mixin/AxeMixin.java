package me.thonk.croptopia.mixin;

import me.thonk.croptopia.registry.BlockRegistry;
import me.thonk.croptopia.registry.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


import java.util.Map;

/**
 * Mixin for the AxeItem class so that axes can strip more blocks.
 * Supremely cracked, just remove the @Final and you can modify final variables! very cool.
 */
@Mixin(AxeItem.class)
public class AxeMixin {


    @Shadow protected static Map<Block, Block> STRIPPED_BLOCKS;


    @Inject(method = "useOnBlock",
            at = {@At("HEAD")},
            slice = @Slice(
                    from = @At(
                            value = "INVOKE",
                            target = "Lnet/minecraft/item/ItemUsageContext;getPlayer()Lnet/minecraft/entity/player/PlayerEntity;",
                            opcode = 1
                    ),
                    to = @At(
                            value = "INVOKE_ASSIGN",
                            target = "Lnet/minecraft/item/ItemUsageContext;getPlayer()Lnet/minecraft/entity/player/PlayerEntity;",
                            opcode = 1
                    )
            ))
    public void blockUse(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        BlockPos pos = context.getBlockPos();
        BlockState state = context.getWorld().getBlockState(pos);
        if (state.getBlock().equals(BlockRegistry.cinnamonLog) && !context.getWorld().isClient) {
            Block.dropStack(context.getWorld(), pos, new ItemStack(ItemRegistry.cinnamon));
        }
    }
}
