package com.epherical.croptopia.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;

import java.nio.file.Path;

public class DependentPathProvider extends DataGenerator.PathProvider {
    private final Path root;
    private final String kindCopy;

    public DependentPathProvider(DataGenerator dataGenerator, DataGenerator.Target target, String string) {
        super(dataGenerator, target, string);
        this.root = dataGenerator.getOutputFolder().resolve("dependents/platform/");
        this.kindCopy = string;
    }

    @Override
    public Path file(ResourceLocation resourceLocation, String string) {
        Path type = this.root.resolve(this.kindCopy);
        String path = resourceLocation.getPath();
        return type.resolve(path + "." + string);
    }

    @Override
    public Path json(ResourceLocation resourceLocation) {
        return this.root.resolve(this.kindCopy).resolve(resourceLocation.getPath() + ".json");
    }
}
