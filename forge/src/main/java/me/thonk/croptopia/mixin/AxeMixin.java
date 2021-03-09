package me.thonk.croptopia.mixin;

import com.google.common.collect.ImmutableMap;
import me.thonk.croptopia.registry.BlockRegistry;
import me.thonk.croptopia.registry.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
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
 * thank fuck forge has mixin support
 */
@Mixin(AxeItem.class)
public class AxeMixin {


    @Shadow protected static Map<Block, Block> BLOCK_STRIPPING_MAP;


    @Inject(method = "onItemUse",
            at = {@At("HEAD")},
            slice = @Slice(
                    from = @At(
                            value = "INVOKE",
                            target = "Lnet/minecraft/item/ItemUseContext;getPlayer()Lnet/minecraft/entity/player/PlayerEntity;"
                    ),
                    to = @At(
                            value = "INVOKE_ASSIGN",
                            target = "Lnet/minecraft/item/ItemUseContext;getPlayer()Lnet/minecraft/entity/player/PlayerEntity;"
                    )
            ))
    public void blockUse(ItemUseContext context, CallbackInfoReturnable<ActionResultType> cir) {
        BlockPos pos = context.getPos();
        BlockState state = context.getWorld().getBlockState(pos);
        if (state.getBlock().matchesBlock(BlockRegistry.cinnamonLog) && !context.getWorld().isRemote) {
            Block.spawnAsEntity(context.getWorld(), pos, new ItemStack(ItemRegistry.cinnamon));
        }
    }

    static {
        BLOCK_STRIPPING_MAP = (new ImmutableMap.Builder<Block, Block>())
                .put(Blocks.OAK_WOOD, Blocks.STRIPPED_OAK_WOOD)
                .put(Blocks.OAK_LOG, Blocks.STRIPPED_OAK_LOG)
                .put(Blocks.DARK_OAK_WOOD, Blocks.STRIPPED_DARK_OAK_WOOD)
                .put(Blocks.DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_LOG)
                .put(Blocks.ACACIA_WOOD, Blocks.STRIPPED_ACACIA_WOOD)
                .put(Blocks.ACACIA_LOG, Blocks.STRIPPED_ACACIA_LOG)
                .put(Blocks.BIRCH_WOOD, Blocks.STRIPPED_BIRCH_WOOD)
                .put(Blocks.BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG)
                .put(Blocks.JUNGLE_WOOD, Blocks.STRIPPED_JUNGLE_WOOD)
                .put(Blocks.JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_LOG)
                .put(Blocks.SPRUCE_WOOD, Blocks.STRIPPED_SPRUCE_WOOD)
                .put(Blocks.SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_LOG)
                .put(Blocks.WARPED_STEM, Blocks.STRIPPED_WARPED_STEM)
                .put(Blocks.WARPED_HYPHAE, Blocks.STRIPPED_WARPED_HYPHAE)
                .put(Blocks.CRIMSON_STEM, Blocks.STRIPPED_CRIMSON_STEM)
                .put(Blocks.CRIMSON_HYPHAE, Blocks.STRIPPED_CRIMSON_HYPHAE)
                .put(BlockRegistry.cinnamonLog, BlockRegistry.strippedCinnamonLog)
                .put(BlockRegistry.cinnamonWood, BlockRegistry.strippedCinnamonWood)
                .build();
    }
}
