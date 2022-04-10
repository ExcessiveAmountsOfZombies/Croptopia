package me.thonk.croptopia.mixin.datagen;

import net.minecraft.data.server.AbstractTagProvider;
import net.minecraft.tag.Tag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;


@Mixin(AbstractTagProvider.ObjectBuilder.class)
public interface ObjectBuilderAccessor {

    @Accessor("builder")
    Tag.Builder getBuilder();

    @Accessor("source")
    String getSource();
}
