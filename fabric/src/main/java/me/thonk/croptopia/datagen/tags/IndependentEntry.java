package me.thonk.croptopia.datagen.tags;

import com.google.gson.JsonArray;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class IndependentEntry implements Tag.Entry {
    private final String prefix = "${dependent}:";
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
        json.add("#" + prefix + suffix);

    }

    @Override
    public boolean canAdd(Predicate<Identifier> objectExistsTest, Predicate<Identifier> tagExistsTest) {
        return true;
    }
}
