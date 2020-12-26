package me.thonk.croptopia.mixin;

import me.thonk.croptopia.Croptopia;
import net.minecraft.block.ComposterBlock;
import net.minecraft.item.ItemConvertible;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.block.ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE;

@Mixin(ComposterBlock.class)
public abstract class ComposterMixin {


    @Inject(method = "registerDefaultCompostableItems", at = @At("TAIL"))
    private static void registerCompostItems(CallbackInfo info) {
        Croptopia.getSeeds().forEach(configurableSeed -> ITEM_TO_LEVEL_INCREASE_CHANCE.put(configurableSeed.getSeedItem(), 0.3F));
    }

    private static void registerCompostableItem(float levelIncreaseChance, ItemConvertible item) {
        ITEM_TO_LEVEL_INCREASE_CHANCE.put(item.asItem(), levelIncreaseChance);
    }
}
