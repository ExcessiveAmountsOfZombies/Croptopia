package com.epherical.croptopia.datagen;

import com.epherical.croptopia.CroptopiaMod;
import com.epherical.croptopia.register.Content;
import com.epherical.croptopia.register.helpers.FarmlandCrop;
import com.epherical.croptopia.register.helpers.Tree;
import com.epherical.croptopia.register.helpers.TreeCrop;
import com.epherical.croptopia.util.RegisterFunction;
import com.ibm.icu.text.Normalizer2.Mode;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry.TexturedModelDataProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.item.BlockModelWrapper;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.ItemModels;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CroptopiaModelProvider extends FabricModelProvider {


    public CroptopiaModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        //blockStateModelGenerator.createFlatItemModelWithBlockTexture(Content.ALMOND.getSaplingItem(), Content.ALMOND.asBlock());
        // REVIEW 1.21.5
        //for(final Block block : CroptopiaMod.leafBlocks) blockStateModelGenerator.createTintedLeaves(block, TexturedModel.LEAVES, 0);
        //for(final Block block : CroptopiaMod.cropBlocks) blockStateModelGenerator.createTintedLeaves(block, TexturedModel.LEAVES, 0);
    }


    @Override
    public void generateItemModels(ItemModelGenerators generator) {
        // REVIEW 1.21.5
        // As of minecraft 1.21.4, it apparently requires an item file json file for ALL of the models.
        // Without this, the items appear with the default placeholder texture.
        // https://forums.minecraftforge.net/topic/154686-1214-item-textures-not-displaying/




        //generator.generateFlatItem(Item.);


        // So here we just go and reflectively grab all of the item constants from the Content class.
        // It would be a bit nicer if this were either an enum or a registrar of some sort so we
        // didn't have to rely on refelction.
        for (Field field : Content.class.getDeclaredFields()) {
            if (field.getName().startsWith("SALT_ORE") || field.getName().equals("GUIDE")) {
                continue; // these are handled by hand in resources
            }
            if (Modifier.isStatic(field.getModifiers())) {
                try {
                    final Object value = field.get(null);
                    if (value instanceof Tree) {
                        continue; // handled by hand
                    } else if (value instanceof Item item) {
                        generator.generateFlatItem(item, ModelTemplates.FLAT_ITEM);
                    } else if (value instanceof FarmlandCrop farmlandCrop) {
                        generator.generateFlatItem(farmlandCrop.asItem(), ModelTemplates.FLAT_ITEM);
                        generator.generateFlatItem(farmlandCrop.getSeedItem(), ModelTemplates.FLAT_ITEM);
                    } else if (value instanceof TreeCrop treeCrop) {
                        generator.generateFlatItem(treeCrop.asItem(), ModelTemplates.FLAT_ITEM);
                        // the sapling items reuse the block textures, so we have to:
                        final ResourceLocation saplingModel = ModelTemplates.FLAT_ITEM.create(
                                ModelLocationUtils.getModelLocation(treeCrop.getSaplingItem()),
                                TextureMapping.layer0(TextureMapping.getBlockTexture(treeCrop.getSaplingBlock())), generator.modelOutput
                        );
                        generator.itemModelOutput.accept(treeCrop.getSaplingItem(), ItemModelUtils.plainModel(saplingModel));
                    } else if (value instanceof ItemLike itemLike) {
                        generator.generateFlatItem(itemLike.asItem(), ModelTemplates.FLAT_ITEM);
                    }
                } catch (IllegalAccessException ohWell) {}
            }
        }
    }

    /**
     * For items that reuse the block textures.
     */
    private static void generateFlatItemWithBlockTexture(ItemModelGenerators generator, Item item, Block block) {
        final ResourceLocation saplingModel = ModelTemplates.FLAT_ITEM.create(
                ModelLocationUtils.getModelLocation(item),
                TextureMapping.layer0(TextureMapping.getBlockTexture(block)), generator.modelOutput
        );
        generator.itemModelOutput.accept(item, ItemModelUtils.plainModel(saplingModel));
    }

}
