package com.epherical.croptopia.mixin.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(TagsProvider.class)
public interface TagProviderAccessor {

    @Accessor("pathProvider") @Mutable
    DataGenerator.PathProvider getPathProvider();

    @Accessor("pathProvider") @Mutable
    void setPathProvider(DataGenerator.PathProvider pathProvider);
}
