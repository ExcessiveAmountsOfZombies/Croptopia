package com.epherical.croptopia.datagen;

import com.epherical.croptopia.common.Tags;
import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static net.minecraft.world.level.biome.Biome.BiomeCategory.*;

public class CroptopiaBiomeTagProvider extends TagsProvider<Biome> {

    protected CroptopiaBiomeTagProvider(DataGenerator dataGenerator) {
        super(dataGenerator, BuiltinRegistries.BIOME);
    }

    @Override
    protected void addTags() {
        Set<BiomeTagHolding> holdingArrayList = new HashSet<>();
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_ARTICHOKE, SWAMP));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_ASPARAGUS, SWAMP));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_BELLPEPPER, PLAINS));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_BLACKBEAN, FOREST));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_BLACKBERRY, FOREST, TAIGA));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_BLUEBERRY, FOREST, TAIGA));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_BROCCOLI, PLAINS));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_CABBAGE, PLAINS));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_CANTALOUPE, FOREST));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_CAULIFLOWER, FOREST));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_CELERY, FOREST));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_COFFEE_BEANS, JUNGLE));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_CORN, PLAINS));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_CRANBERRY, SWAMP));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_CUCUMBER, PLAINS));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_CURRANT, SWAMP));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_EGGPLANT, JUNGLE));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_ELDERBERRY, FOREST));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_GARLIC, JUNGLE));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_GRAPE, FOREST));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_GREENBEAN, PLAINS));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_GREENONION, JUNGLE));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_HONEYDEW, JUNGLE));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_HOPS, SAVANNA));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_KALE, PLAINS));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_KIWI, SAVANNA));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_LEEK, SAVANNA));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_LETTUCE, PLAINS));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_OLIVE, SAVANNA));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_ONION, JUNGLE));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_PEANUT, JUNGLE));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_PINEAPPLE, JUNGLE));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_RADISH, FOREST));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_RASPBERRY, FOREST, TAIGA));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_RHUBARB, JUNGLE));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_RICE, JUNGLE));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_RUTABAGA, SAVANNA, TAIGA));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_SAGUARO, DESERT));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_SPINACH, FOREST));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_SQUASH, SAVANNA, TAIGA));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_STRAWBERRY, FOREST, TAIGA));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_SWEETPOTATO, PLAINS));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_TOMATILLO, FOREST));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_TOMATO, FOREST));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_TURNIP, JUNGLE));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_YAM, SAVANNA));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_ZUCCHINI, SAVANNA));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_MUSTARD, PLAINS));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_CHILE_PEPPER, PLAINS));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_TURMERIC, SAVANNA));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_GINGER, SAVANNA));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_BASIL, JUNGLE));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_OAT, PLAINS));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_BARLEY, PLAINS, TAIGA));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_SOYBEAN, PLAINS));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_VANILLA, JUNGLE));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_PEPPER, PLAINS));
        holdingArrayList.add(new BiomeTagHolding(Tags.HAS_TEA_LEAVES, FOREST));


        for (Holder<Biome> biomeHolder : BuiltinRegistries.BIOME.asHolderIdMap()) {
            for (BiomeTagHolding biomeTagHolding : holdingArrayList) {
                Biome.BiomeCategory category = Biome.getBiomeCategory(biomeHolder);
                if (biomeTagHolding.biomeCategory.contains(category)) {
                    this.tag(biomeTagHolding.biomeTag).add(biomeHolder.value());
                }
            }
        }
/*
        this.tag(Tags.HAS_ARTICHOKE).add(Biomes.SWAMP)
        this.tag(Tags.HAS_ASPARAGUS).add(Biomes.SWAMP)
        this.tag(Tags.HAS_BELLPEPPER).add(Biomes.PLAINS)
        this.tag(Tags.HAS_BLACKBEAN).add(Biomes.FOREST)
        this.tag(Tags.HAS_BLACKBERRY).add(Biomes.FOREST)
        this.tag(Tags.HAS_BLUEBERRY).add(Biomes.FOREST)
        this.tag(Tags.HAS_BROCCOLI).add(Biomes.PLAINS)
        this.tag(Tags.HAS_CABBAGE).add(Biomes.PLAINS)
        this.tag(Tags.HAS_CANTALOUPE).add(Biomes.FOREST)
        this.tag(Tags.HAS_CAULIFLOWER).add(Biomes.FOREST)
        this.tag(Tags.HAS_CELERY).add(Biomes.FOREST)
        this.tag(Tags.HAS_COFFEE_BEANS).add(Biomes.JUNGLE)
        this.tag(Tags.HAS_CORN).add(Biomes.PLAINS)
        this.tag(Tags.HAS_CRANBERRY).add(Biomes.SWAMP)
        this.tag(Tags.HAS_CUCUMBER).add(Biomes.PLAINS)
        this.tag(Tags.HAS_CURRANT).add(Biomes.SWAMP)
        this.tag(Tags.HAS_EGGPLANT).add(Biomes.JUNGLE)
        this.tag(Tags.HAS_ELDERBERRY).add(Biomes.FOREST)
        this.tag(Tags.HAS_GARLIC).add(Biomes.JUNGLE)
        this.tag(Tags.HAS_GRAPE).add(Biomes.FOREST)
        this.tag(Tags.HAS_GREENBEAN).add(Biomes.PLAINS)
        this.tag(Tags.HAS_GREENONION).add(Biomes.JUNGLE)
        this.tag(Tags.HAS_HONEYDEW).add(Biomes.JUNGLE)
        this.tag(Tags.HAS_HOPS).add(Biomes.SAVANNA)
        this.tag(Tags.HAS_KALE).add(Biomes.PLAINS)
        this.tag(Tags.HAS_KIWI).add(Biomes.SAVANNA)
        this.tag(Tags.HAS_LEEK).add(Biomes.SAVANNA)
        this.tag(Tags.HAS_LETTUCE).add(Biomes.PLAINS)
        this.tag(Tags.HAS_OLIVE).add(Biomes.SAVANNA)
        this.tag(Tags.HAS_ONION).add(Biomes.JUNGLE)
        this.tag(Tags.HAS_PEANUT).add(Biomes.JUNGLE)
        this.tag(Tags.HAS_PINEAPPLE).add(Biomes.JUNGLE)
        this.tag(Tags.HAS_RADISH).add(Biomes.FOREST)
        this.tag(Tags.HAS_RASPBERRY).add(Biomes.FOREST)
        this.tag(Tags.HAS_RHUBARB).add(Biomes.JUNGLE)
        this.tag(Tags.HAS_RICE).add(Biomes.JUNGLE)
        this.tag(Tags.HAS_RUTABAGA).add(Biomes.SAVANNA)
        this.tag(Tags.HAS_SAGUARO).add(Biomes.DESERT)
        this.tag(Tags.HAS_SPINACH).add(Biomes.FOREST)
        this.tag(Tags.HAS_SQUASH).add(Biomes.SAVANNA)
        this.tag(Tags.HAS_STRAWBERRY).add(Biomes.FOREST)
        this.tag(Tags.HAS_SWEETPOTATO).add(Biomes.PLAINS)
        this.tag(Tags.HAS_TOMATILLO).add(Biomes.FOREST)
        this.tag(Tags.HAS_TOMATO).add(Biomes.FOREST)
        this.tag(Tags.HAS_TURNIP).add(Biomes.JUNGLE)
        this.tag(Tags.HAS_YAM).add(Biomes.SAVANNA)
        this.tag(Tags.HAS_ZUCCHINI).add(Biomes.SAVANNA)
        this.tag(Tags.HAS_MUSTARD).add(Biomes.PLAINS)
        this.tag(Tags.HAS_CHILE_PEPPER).add(Biomes.PLAINS)
        this.tag(Tags.HAS_TURMERIC).add(Biomes.SAVANNA)
        this.tag(Tags.HAS_GINGER).add(Biomes.SAVANNA)
        this.tag(Tags.HAS_BASIL).add(Biomes.JUNGLE)
        this.tag(Tags.HAS_OAT).add(Biomes.PLAINS)
        this.tag(Tags.HAS_BARLEY).add(Biomes.PLAINS)
        this.tag(Tags.HAS_SOYBEAN).add(Biomes.PLAINS)
        this.tag(Tags.HAS_VANILLA).add(Biomes.JUNGLE)
        this.tag(Tags.HAS_PEPPER).add(Biomes.PLAINS)
        this.tag(Tags.HAS_TEA_LEAVES).add(Biomes.FOREST)*/
    }

    @Override
    public String getName() {
        return "Croptopia Biome Tags";
    }

    public static class BiomeTagHolding {
        private final List<Biome.BiomeCategory> biomeCategory;
        private final TagKey<Biome> biomeTag;

        public BiomeTagHolding(TagKey<Biome> biome, Biome.BiomeCategory... category) {
            this.biomeCategory = List.of(category);
            this.biomeTag = biome;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BiomeTagHolding that = (BiomeTagHolding) o;
            return Objects.equals(biomeCategory, that.biomeCategory) && Objects.equals(biomeTag, that.biomeTag);
        }

        @Override
        public int hashCode() {
            return Objects.hash(biomeCategory, biomeTag);
        }
    }


}
