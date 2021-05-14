package me.thonk.croptopia.mixin;

import me.thonk.croptopia.items.CookingUtensil;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.class)
public abstract class ItemMixin {


    @Mutable
    @Shadow @Final private Item recipeRemainder;

    @Inject(method = "<init>", at = {@At("TAIL")})
    public void overrideConstructor(Item.Settings settings, CallbackInfo ci) {
        Item thisInstance = (Item)(Object)this;
        if (thisInstance instanceof CookingUtensil) {
            this.recipeRemainder = thisInstance;
        }
    }
}
