package com.epherical.croptopia.datagen.tags;

import com.google.gson.JsonArray;
import com.epherical.croptopia.common.MiscNames;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.Tag;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class IndependentEntry implements Tag.Entry {
    private final String suffix;

    public IndependentEntry(String something) {
        this.suffix = something;
    }

    @Override
    public <T> boolean build(Function<ResourceLocation, Tag<T>> tagGetter, Function<ResourceLocation, T> objectGetter, Consumer<T> collector) {
        return true;
    }

    @Override
    public void serializeTo(JsonArray json) {
        json.add("#" + MiscNames.INDEPENDENT_TAG + ":" + suffix);

    }

    @Override
    public boolean verifyIfPresent(Predicate<ResourceLocation> objectExistsTest, Predicate<ResourceLocation> tagExistsTest) {
        return true;
    }
}
