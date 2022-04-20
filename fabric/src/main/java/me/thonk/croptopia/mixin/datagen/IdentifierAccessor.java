package me.thonk.croptopia.mixin.datagen;

import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;


@Mixin(ResourceLocation.class)
public interface IdentifierAccessor {

    @Accessor("namespace") @Mutable
    void setNamespace(String name);

    @Accessor("path") @Mutable
    void setPath(String path);

}
