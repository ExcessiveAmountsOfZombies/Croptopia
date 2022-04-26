package com.epherical.croptopia.mixin.datagen;

import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.Tag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;


@Mixin(TagsProvider.TagAppender.class)
public interface ObjectBuilderAccessor {

    @Accessor("builder")
    Tag.Builder getBuilder();

    @Accessor("source")
    String getSource();
}
