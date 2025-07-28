package com.epherical.croptopia.datagen;


import com.epherical.croptopia.CroptopiaMod;
import com.epherical.croptopia.register.Content;
import com.epherical.croptopia.register.helpers.Tree;
import com.epherical.croptopia.register.helpers.TreeCrop;
import com.epherical.croptopia.register.helpers.Utensil;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static com.epherical.croptopia.CroptopiaMod.MODID;

public class CroptopiaItemTagProvider extends IntrinsicHolderTagsProvider<Item> {

    public CroptopiaItemTagProvider(PackOutput output, ResourceKey<? extends Registry<Item>> registryKey,
                                    CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, registryKey, lookupProvider, item -> item.builtInRegistryHolder().key(), MODID, existingFileHelper);
    }


    @Override
    protected void addTags(HolderLookup.Provider arg) {
        generateSaplings();
        generateBarkLogs();
        // currently, only generates air, but leaves item tag isn't used by vanilla anyway
        // generateLeaves();
        generateMisc();
        generateSeedsEatenByTag(ItemTags.CHICKEN_FOOD);
        generateSeedsEatenByTag(ItemTags.PARROT_FOOD);
        generateToolsTags(Tags.Items.TOOLS);
    }


    protected void generateToolsTags(TagKey<Item> key) {
        IntrinsicTagAppender<Item> tag = tag(key);
        for (Utensil utensil : Utensil.copy()) {
            tag.add(utensil.asItem());
        }
        IntrinsicTagAppender<Item> c = tag(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "tools/knife")));
        c.add(Content.KNIFE.asItem());
    }

    protected void generateSeedsEatenByTag(TagKey<Item> key) {
        IntrinsicTagAppender<Item> tag = tag(key);
        for (Item seed : CroptopiaMod.seeds) {
            tag.add(seed);
        }
    }


    protected void generateSaplings() {
        IntrinsicTagAppender<Item> saplings = tag(ItemTags.SAPLINGS);
        for (TreeCrop crop : TreeCrop.copy()) {
            saplings.add(crop.getSaplingItem());
        }
        for (Tree crop : Tree.copy()) {
            saplings.add(crop.getSapling());
        }
    }

    protected void generateBarkLogs() {
        IntrinsicTagAppender<Item> burnableLog = tag(ItemTags.LOGS_THAT_BURN);
        for (Tree crop : Tree.copy()) {
            // add different log types to log tag of this crop
            tag(crop.getLogItemTag())
                    .add((crop.getLog().asItem()))
                    .add((crop.getStrippedLog().asItem()))
                    .add((crop.getWood().asItem()))
                    .add((crop.getStrippedWood().asItem()));
            // make this crop log burnable
            burnableLog.addTag(crop.getLogItemTag());
        }
    }

    protected void generateLeaves() {
        IntrinsicTagAppender<Item> leaves = tag(ItemTags.LEAVES);
        for (TreeCrop crop : TreeCrop.copy()) {
            leaves.add(crop.getLeaves().asItem());
        }
        for (Tree crop : Tree.copy()) {
            leaves.add(crop.getLeaves().asItem());
        }
    }

    protected void generateMisc() {
        IntrinsicTagAppender<Item> crops = tag(ItemTags.VILLAGER_PLANTABLE_SEEDS);
        for (Item seed : CroptopiaMod.seeds) {
            crops.add(seed);
        }
        // explicitly used as dolphin food in vanilla
        IntrinsicTagAppender<Item> fishes = tag(ItemTags.FISHES);
        fishes.add(Content.ANCHOVY.asItem());
        fishes.add(Content.CALAMARI.asItem());
        fishes.add(Content.GLOWING_CALAMARI.asItem());
        fishes.add(Content.CLAM.asItem());
        fishes.add(Content.CRAB.asItem());
        fishes.add(Content.OYSTER.asItem());
        fishes.add(Content.ROE.asItem());
        fishes.add(Content.SHRIMP.asItem());
        fishes.add(Content.TUNA.asItem());
        // fox food: all berries added by croptopia
        IntrinsicTagAppender<Item> foxFood = tag(ItemTags.FOX_FOOD);
        foxFood.add(Content.BLACKBERRY.asItem());
        foxFood.add(Content.BLUEBERRY.asItem());
        foxFood.add(Content.CRANBERRY.asItem());
        foxFood.add(Content.ELDERBERRY.asItem());
        foxFood.add(Content.RASPBERRY.asItem());
        foxFood.add(Content.STRAWBERRY.asItem());
        // piglin food: more cannibalism (which already happens in vanilla)
        IntrinsicTagAppender<Item> piglinFood = tag(ItemTags.PIGLIN_FOOD);
        piglinFood.add(Content.HAM_SANDWICH);
        piglinFood.add(Content.PEPPERONI);
        piglinFood.add(Content.PORK_AND_BEANS);
        piglinFood.add(Content.PORK_JERKY);
        piglinFood.add(Content.RAW_BACON);
        piglinFood.add(Content.COOKED_BACON.asItem());
    }

}
