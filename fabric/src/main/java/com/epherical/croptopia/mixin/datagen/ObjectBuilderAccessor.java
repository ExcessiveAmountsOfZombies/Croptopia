package com.epherical.croptopia.mixin.datagen;

import net.minecraft.core.Registry;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.TagBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;


@Mixin(TagsProvider.TagAppender.class)
public interface ObjectBuilderAccessor<T> {

    @Accessor("builder")
    TagBuilder getBuilder();

    @Accessor("registry")
    Registry<?> getSource();
}
