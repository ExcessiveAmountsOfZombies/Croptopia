package com.epherical.croptopia.mixin;

import com.epherical.croptopia.items.SeedItem;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.ai.behavior.HarvestFarmland;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(HarvestFarmland.class)
public class FarmerVillagerTaskMixin {

    @Shadow private @Nullable BlockPos aboveFarmlandPos;
    @Unique boolean planted = false;

    @Inject(method = "tick", at = @At(value = "INVOKE_ASSIGN",
            target = "Lnet/minecraft/world/item/ItemStack;isEmpty()Z", opcode = 0),
            locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    public void running(ServerLevel serverWorld, Villager villagerEntity, long l, CallbackInfo ci,
                        BlockState blockState, Block block, Block block2,
                        SimpleContainer inventory, int i, ItemStack itemStack) {
        if (itemStack.getItem() instanceof SeedItem) {
            serverWorld.setBlock(this.aboveFarmlandPos, ((SeedItem) itemStack.getItem()).getBlock().defaultBlockState(), 3);
            planted = true;
        }
    }

    @ModifyVariable(method = "tick", at = @At(value = "LOAD", ordinal = 0))
    public boolean planted(boolean bl) {
        if (planted) {
            planted = false;
            return true;
        }

        return bl;
    }
}
