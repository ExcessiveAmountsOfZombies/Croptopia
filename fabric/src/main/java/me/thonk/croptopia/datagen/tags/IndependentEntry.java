package me.thonk.croptopia.datagen.tags;

import com.google.gson.JsonArray;
import me.thonk.common.MiscNames;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class IndependentEntry implements Tag.Entry {
    private final String suffix;

    public IndependentEntry(String something) {
        this.suffix = something;
    }

    @Override
    public <T> boolean resolve(Function<Identifier, Tag<T>> tagGetter, Function<Identifier, T> objectGetter, Consumer<T> collector) {
        return true;
    }

    @Override
    public void addToJson(JsonArray json) {
        json.add("#" + MiscNames.INDEPENDENT_TAG + ":" + suffix);

    }

    @Override
    public boolean canAdd(Predicate<Identifier> objectExistsTest, Predicate<Identifier> tagExistsTest) {
        return true;
    }
}
