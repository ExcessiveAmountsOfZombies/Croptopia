package me.thonk.croptopia.util;

import net.minecraft.tag.Tag;
import net.minecraft.tag.TagKey;

public interface TagConvertible<T> {

    TagKey<T> asTag();

}
