package com.epherical.croptopia.mixin;

import com.epherical.croptopia.items.SeedItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.brain.task.FarmerVillagerTask;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(FarmerVillagerTask.class)
public class FarmerVillagerTaskMixin {

    @Shadow @Nullable private BlockPos currentTarget;
    @Unique boolean planted = false;

    @Inject(method = "keepRunning", at = @At(value = "INVOKE_ASSIGN",
            target = "Lnet/minecraft/item/ItemStack;isEmpty()Z", opcode = 0),
            locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    public void running(ServerWorld serverWorld, VillagerEntity villagerEntity, long l, CallbackInfo ci,
                        BlockState blockState, Block block, Block block2,
                        SimpleInventory inventory, int i, ItemStack itemStack) {
        if (itemStack.getItem() instanceof SeedItem) {
            serverWorld.setBlockState(this.currentTarget, ((SeedItem) itemStack.getItem()).getBlock().getDefaultState(), 3);
            planted = true;
        }
    }

    @ModifyVariable(method = "keepRunning", at = @At(value = "LOAD", ordinal = 0))
    public boolean planted(boolean bl) {
        if (planted) {
            planted = false;
            return true;
        }

        return bl;
    }
}
