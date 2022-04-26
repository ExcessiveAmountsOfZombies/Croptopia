package com.epherical.croptopia.datagen;

import com.epherical.croptopia.common.Tags;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.biome.Biome;

public class CroptopiaBiomeTagProvider extends TagsProvider<Biome> {

    protected CroptopiaBiomeTagProvider(DataGenerator dataGenerator) {
        super(dataGenerator, BuiltinRegistries.BIOME);
    }

    @Override
    protected void addTags() {
        this.tag(Tags.HAS_ARTICHOKE).add

    }

    @Override
    public String getName() {
        return "Croptopia Biome Tags";
    }
}
