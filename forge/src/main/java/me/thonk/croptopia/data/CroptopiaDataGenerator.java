package me.thonk.croptopia.data;


import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.LanguageProvider;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nullable;
import java.util.List;

import static me.thonk.croptopia.registry.BlockRegistry.*;
import static me.thonk.croptopia.registry.ItemRegistry.*;


public class CroptopiaDataGenerator {


    private static final List<Item> baseItems;
    private static final List<Item> blockItemGeneratedModels;
    private static final List<Block> cropBlocks;
    private static final List<Item> carbFoodsTag;
    private static final List<Item> fatsFoodTag;
    private static final List<Item> fruitFoodTag;
    private static final List<Item> proteinFoodTag;
    private static final List<Item> sweetsFoodTag;
    private static final List<Item> veggieFoodTag;

    static {
        // used for simple item/generated item models.
        baseItems = ImmutableList.of(shepherdsPie, beefWellington, fishAndChips,
                etonMess, tea, cornishPasty, scones, figgyPudding, treacleTart,
                stickyToffeePudding, trifle, pepperSeed, waterBottle, milkBottle, teaLeaves, teaSeed);

        // used for saplings
        blockItemGeneratedModels = ImmutableList.of(appleSapling);

        // any crop blocks we created during an update.
        cropBlocks = ImmutableList.of(teaCropBlock);

        // all for add nourish support.
        carbFoodsTag = ImmutableList.of(shepherdsPie);
        fatsFoodTag = ImmutableList.of(shepherdsPie);
        fruitFoodTag = ImmutableList.of(shepherdsPie);
        proteinFoodTag = ImmutableList.of(shepherdsPie);
        sweetsFoodTag = ImmutableList.of(shepherdsPie);
        veggieFoodTag = ImmutableList.of(shepherdsPie);
    }

    public static class BlockTagGenerator extends BlockTagsProvider {

        public BlockTagGenerator(DataGenerator generatorIn, String modId, @Nullable ExistingFileHelper existingFileHelper) {
            super(generatorIn, modId, existingFileHelper);
        }

        @Override
        protected void registerTags() {

        }

        @Override
        public String getName() {
            return "Croptopia Block Tags";
        }
    }

    public static class ItemTagGenerator extends ItemTagsProvider {

        private static final ITag.INamedTag<Item> CARBS = ItemTags.createOptional(new ResourceLocation("nourish", "carbohydrates"));
        private static final ITag.INamedTag<Item> FATS = ItemTags.createOptional(new ResourceLocation("nourish", "fats"));
        private static final ITag.INamedTag<Item> FRUIT = ItemTags.createOptional(new ResourceLocation("nourish", "fruit"));
        private static final ITag.INamedTag<Item> PROTEIN = ItemTags.createOptional(new ResourceLocation("nourish", "protein"));
        private static final ITag.INamedTag<Item> SWEETS = ItemTags.createOptional(new ResourceLocation("nourish", "sweets"));
        private static final ITag.INamedTag<Item> VEGGIE = ItemTags.createOptional(new ResourceLocation("nourish", "vegetables"));

        private static final ITag.INamedTag<Item> INDEPENDENT = ItemTags.createOptional(new ResourceLocation("dependants", "platform"));



        public ItemTagGenerator(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
            super(dataGenerator, blockTagProvider, modId, existingFileHelper);
        }

        @Override
        protected void registerTags() {
            this.getOrCreateBuilder(CARBS).add(carbFoodsTag.toArray(new Item[]{}));
            this.getOrCreateBuilder(FATS).add(fatsFoodTag.toArray(new Item[]{}));
            this.getOrCreateBuilder(FRUIT).add(fruitFoodTag.toArray(new Item[]{}));
            this.getOrCreateBuilder(PROTEIN).add(proteinFoodTag.toArray(new Item[]{}));
            this.getOrCreateBuilder(SWEETS).add(sweetsFoodTag.toArray(new Item[]{}));
            this.getOrCreateBuilder(VEGGIE).add(veggieFoodTag.toArray(new Item[]{}));


            for (Item item : baseItems) {
                this.getOrCreateBuilder(ItemTags.createOptional(new ResourceLocation("dependants", item.getRegistryName().getPath()))).add(item);
            }
            for (Item item : blockItemGeneratedModels) {
                this.getOrCreateBuilder(ItemTags.createOptional(new ResourceLocation("dependants", item.getRegistryName().getPath()))).add(item);
            }


        }


        @Override
        public String getName() {
            return "Croptopia Item Tags";
        }
    }

    public static class LanguageGenerator extends LanguageProvider {

        public LanguageGenerator(DataGenerator gen, String modid, String locale) {
            super(gen, modid, locale);
        }

        @Override
        protected void addTranslations() {
            for (Item item : baseItems) {
                ResourceLocation location = item.getRegistryName();
                String path = location.getPath();
                add(item, StringUtils.capitalize(path.replaceAll("_", " ")));
            }

            for (Item item : blockItemGeneratedModels) {
                ResourceLocation location = item.getRegistryName();
                String path = location.getPath();
                add(item, StringUtils.capitalize(path.replaceAll("_", " ")));
            }

            for (Block block : cropBlocks) {
                ResourceLocation location = block.getRegistryName();
                String path = location.getPath();
                add(block, StringUtils.capitalize(path.replaceAll("_", " ")));
            }
        }
    }


    public static class ItemGenerator extends ItemModelProvider {
        public ItemGenerator(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
            super(generator, modid, existingFileHelper);
        }

        @Override
        protected void registerModels() {
            for (Item cropItem : baseItems) {
                ResourceLocation resourceLocation = cropItem.getRegistryName();
                getBuilder(resourceLocation.getPath())
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", modLoc("item/" + resourceLocation.getPath()));
            }

            for (Item blockItemGeneratedModel : blockItemGeneratedModels) {
                ResourceLocation resourceLocation = blockItemGeneratedModel.getRegistryName();
                getBuilder(resourceLocation.getPath())
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", modLoc("block/" + resourceLocation.getPath()));
            }
        }
    }




}
