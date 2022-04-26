package com.epherical.croptopia.mixin;

import com.epherical.croptopia.items.CookingUtensil;
import net.minecraft.world.item.Item;
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
    @Shadow @Final private Item craftingRemainingItem;

    @Inject(method = "<init>", at = {@At("TAIL")})
    public void overrideConstructor(Item.Properties settings, CallbackInfo ci) {
        Item thisInstance = (Item)(Object)this;
        if (thisInstance instanceof CookingUtensil) {
            this.craftingRemainingItem = thisInstance;
        }
    }
}
