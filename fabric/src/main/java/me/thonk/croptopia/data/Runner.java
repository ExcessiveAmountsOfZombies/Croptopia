package me.thonk.croptopia.data;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.server.BlockTagsProvider;
import net.minecraft.item.Item;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Runner {

    private ItemTagger<Item> tagger;
    private DataGenerator dataGenerator;

    public Runner() {
        OptionParser optionParser = new OptionParser();
        OptionSpec<String> optionOutput = optionParser.accepts("output", "Output folder").withRequiredArg().defaultsTo("generated");
        OptionSet optionSet = optionParser.parse("");
        Path path = Paths.get(optionOutput.value(optionSet));
        this.dataGenerator = new DataGenerator(path, null);
        BlockTagsProvider provider = new BlockTagsProvider(dataGenerator);
        this.tagger = new ItemTagger<>(dataGenerator, provider);
        dataGenerator.install(tagger);
    }

    public ItemTagger<Item> getTagger() {
        return tagger;
    }

    public void init() {
        try {
            dataGenerator.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
