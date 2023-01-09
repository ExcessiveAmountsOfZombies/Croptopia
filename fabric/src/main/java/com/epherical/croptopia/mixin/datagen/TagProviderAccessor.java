package com.epherical.croptopia.mixin.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(TagsProvider.class)
public interface TagProviderAccessor {

    @Accessor("pathProvider")
    @Mutable
    PackOutput.PathProvider getPathProvider();

    @Accessor("pathProvider")
    @Mutable
    void setPathProvider(PackOutput.PathProvider pathProvider);
}
