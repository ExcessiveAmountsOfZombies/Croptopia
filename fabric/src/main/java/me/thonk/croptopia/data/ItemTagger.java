package me.thonk.croptopia.data;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.minecraft.data.DataCache;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.server.BlockTagsProvider;
import net.minecraft.data.server.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tag.*;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ItemTagger<T> extends ItemTagsProvider {
    protected static final RequiredTagList<Item> REQUIRED_TAGS = RequiredTagListRegistry.register(new Identifier("croptopia", "etem"), TagManager::getItems);
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
    private final Map<Identifier, Tag.Builder> tagBuilders = Maps.newLinkedHashMap();
    private static final Logger LOGGER = LogManager.getLogger();


    public ItemTagger(DataGenerator dataGenerator, BlockTagsProvider blockTagsProvider) {
        super(dataGenerator, blockTagsProvider);
    }

    public void addSeedTag(Item item, Identifier itemName) {
        Tag.Identified<Item> identifier = REQUIRED_TAGS.add(new Identifier("croptopia", "seeds").toString());
        createTag(identifier, item);
    }


    public void addTag(Item item, Identifier itemName) {
        Identifier pluralID = new Identifier(itemName.getNamespace(), itemName.getPath() + "s");
        Tag.Identified<Item> identifier = REQUIRED_TAGS.add(pluralID.toString());
        createTag(identifier, item);
    }


    protected void createTag(Tag.Identified<Item> identified, Item item) {
        this.tagBuilders.computeIfAbsent(identified.getId(), (identifier) -> {
            return new Tag.Builder();
        }).add(registry.getId(item), "");
    }

    protected void createTaggedItem(Tag.Identified<Item> identified, Item item) {
        this.tagBuilders.computeIfAbsent(identified.getId(), (identifier) -> {
            return new Tag.Builder();
        }).addTag(registry.getId(item), "");
    }

    @Override
    public void run(DataCache cache) {
        Tag<T> tag = SetTag.empty();
        Function<Identifier, Tag<T>> function = (identifier) -> {
            return this.tagBuilders.containsKey(identifier) ? tag : null;
        };
        Function<Identifier, T> function2 = (identifier) -> {
            return (T) this.registry.getOrEmpty(identifier).orElse(null);
        };
        this.tagBuilders.forEach((identifier, builder) -> {
            List<Tag.TrackedEntry> list = builder.streamUnresolvedEntries(function, function2).collect(Collectors.toList());
            if (!list.isEmpty()) {
                throw new IllegalArgumentException(String.format("Couldn't define tag %s as it is missing following references: %s", identifier, list.stream().map(Objects::toString).collect(Collectors.joining(","))));
            } else {
                JsonObject jsonObject = builder.toJson();
                Path path = this.getOutput(identifier);

                try {
                    String string = GSON.toJson(jsonObject);
                    String string2 = SHA1.hashUnencodedChars(string).toString();
                    if (!Objects.equals(cache.getOldSha1(path), string2) || !Files.exists(path, new LinkOption[0])) {
                        Files.createDirectories(path.getParent());
                        BufferedWriter bufferedWriter = Files.newBufferedWriter(path);
                        Throwable var12 = null;

                        try {
                            bufferedWriter.write(string);
                        } catch (Throwable var22) {
                            var12 = var22;
                            throw var22;
                        } finally {
                            if (bufferedWriter != null) {
                                if (var12 != null) {
                                    try {
                                        bufferedWriter.close();
                                    } catch (Throwable var21) {
                                        var12.addSuppressed(var21);
                                    }
                                } else {
                                    bufferedWriter.close();
                                }
                            }

                        }
                    }

                    cache.updateSha1(path, string2);
                } catch (IOException var24) {
                    LOGGER.error("Couldn't save tags to {}", path, var24);
                }

            }
        });
    }
}
