package com.epherical.croptopia;

import com.epherical.croptopia.common.ItemNamesV2;
import com.epherical.croptopia.common.MiscNames;
import com.epherical.croptopia.items.CookingUtensil;
import com.epherical.croptopia.listeners.BlockBreakEvent;
import com.epherical.croptopia.loot.FabricLootModifiers;
import com.epherical.croptopia.mixin.CraftingRemainingItemBypassMixin;
import com.epherical.croptopia.register.Content;
import com.epherical.croptopia.register.helpers.FarmlandCrop;
import com.epherical.croptopia.register.helpers.TreeCrop;
import com.epherical.croptopia.register.helpers.Utensil;
import com.epherical.croptopia.world.FabricBiomeModifiers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

import java.util.Comparator;
import java.util.List;

import static com.epherical.croptopia.CroptopiaCommon.createGroup;
import static com.epherical.croptopia.CroptopiaCommon.createIdentifier;

public class CroptopiaFabric implements ModInitializer {
    //public static final CroptopiaConfig config = new CroptopiaConfig(HoconConfigurationLoader.builder(), "croptopia_v3.conf");


    @Override
    public void onInitialize() {
        CroptopiaCommon.init();
        loadConfig();
        registerBlocks();
        registerItems();
        registerCreativeTab();
        registerVanillaTabs();
        BlockBreakEvent.register();
        FabricBiomeModifiers.register();
        FabricLootModifiers.register();
    }

    private static void loadConfig() {
        //config.addSerializer(TreeConfiguration.class, TreeConfiguration.Serializer.INSTANCE);
        //config.addSerializer(Identifier.class, IdentifierSerializer.INSTANCE);
        //config.loadConfig(MiscNames.MOD_ID);
    }

    private static void registerBlocks() {
        Content.registerBlocks((id, supplier) -> {
            Block block = supplier.apply(id);
            /*if (Content.BLOCK_REGISTER.getManipulations().containsKey(id)) {
                supplier = Content.BLOCK_REGISTER.getManipulations().get(id);
            }*/

            Registry.register(BuiltInRegistries.BLOCK, id, block);
            return block;
        });

        FlammableBlockRegistry flammables = FlammableBlockRegistry.getDefaultInstance();
        flammables.add(Content.CINNAMON.getLog(), 5, 5);
        flammables.add(Content.CINNAMON.getWood(), 5, 5);
        flammables.add(Content.CINNAMON.getStrippedLog(), 5, 5);
        flammables.add(Content.CINNAMON.getStrippedWood(), 5, 5);
    }

    private static void registerItems() {
        Content.GUIDE = Registry.register(BuiltInRegistries.ITEM, createIdentifier(ItemNamesV2.GUIDE), new GuideBookItem(createGroup(createIdentifier(ItemNamesV2.GUIDE))));

        Content.registerItems((id, supplier) -> {
            Item item = supplier.apply(id);
            /*if (Content.ITEM_REGISTER.getManipulations().containsKey(id)) {
                supplier = Content.ITEM_REGISTER.getManipulations().get(id);
            }*/

            Registry.register(BuiltInRegistries.ITEM, id, item);
            if (item instanceof CookingUtensil cookingUtensil) {
                CraftingRemainingItemBypassMixin bypass = (CraftingRemainingItemBypassMixin) cookingUtensil;
                bypass.croptopia$setCraftingRemainingItem(new ItemStackTemplate(item));
            }


            if (item instanceof BlockItem blockItem) {
                blockItem.registerBlocks(Item.BY_BLOCK, item);
            }
            return item;
        });
    }

    private static void registerCreativeTab() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, createIdentifier(MiscNames.MOD_ID),
                FabricCreativeModeTab.builder()
                        .title(Component.translatable("itemGroup.croptopia"))
                        .displayItems((parameters, output) ->
                                BuiltInRegistries.ITEM.entrySet().stream()
                                        .filter(entry -> entry.getKey().identifier().getNamespace().equals(MiscNames.MOD_ID))
                                        .sorted(Comparator.comparing(entry -> BuiltInRegistries.ITEM.getId(entry.getValue())))
                                        .forEach(entry -> output.accept(entry.getValue())))
                        .icon(() -> new ItemStack(Content.COFFEE))
                        .build());
    }

    private static void registerVanillaTabs() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.NATURAL_BLOCKS).register(output -> {
            output.insertAfter(Items.MANGROVE_PROPAGULE, List.of(new ItemStack(Content.CINNAMON.getSapling())), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            output.insertAfter(Items.FLOWERING_AZALEA,
                    TreeCrop.TREE_CROPS.stream().map(TreeCrop::getSaplingItem).map(ItemStack::new).toList(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            output.insertAfter(Items.NETHER_WART,
                    FarmlandCrop.FARMLAND_CROPS.stream().map(FarmlandCrop::getSeedItem).map(ItemStack::new).toList(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            output.insertBefore(Items.COAL_ORE, List.of(new ItemStack(Content.SALT_ORE)), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        });

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries ->
                entries.insertAfter(Items.FLINT_AND_STEEL,
                        Utensil.copy().stream().map(ItemStack::new).toList(),
                        CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS));
    }
}
