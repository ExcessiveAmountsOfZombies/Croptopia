package com.epherical.croptopia;

import com.epherical.croptopia.blocks.CroptopiaCropBlock;
import com.epherical.croptopia.common.ItemNamesV2;
import com.epherical.croptopia.common.MiscNames;
import com.epherical.croptopia.config.CroptopiaConfig;
import com.epherical.croptopia.config.TreeConfiguration;
import com.epherical.croptopia.datagen.CroptopiaBiomeTagProvider;
import com.epherical.croptopia.datagen.CroptopiaAdvancementProvider;
import com.epherical.croptopia.datagen.CroptopiaBlockTagProvider;
import com.epherical.croptopia.datagen.CroptopiaItemModelProvider;
import com.epherical.croptopia.datagen.CroptopiaItemTagProvider;
import com.epherical.croptopia.datagen.CroptopiaLootTableProvider;
import com.epherical.croptopia.datagen.CroptopiaRecipeProvider;
import com.epherical.croptopia.datagen.CroptopiaWorldBiomeSelection;
import com.epherical.croptopia.datagen.CroptopiaWorldGeneration;
import com.epherical.croptopia.items.CookingUtensil;
import com.epherical.croptopia.items.SeedItem;
import com.epherical.croptopia.listeners.BlockBreakEvent;
import com.epherical.croptopia.mixin.CraftingRemainingItemBypassMixin;
import com.epherical.croptopia.register.Content;
import com.epherical.croptopia.register.helpers.FarmlandCrop;
import com.epherical.croptopia.register.helpers.TreeCrop;
import com.epherical.croptopia.register.helpers.Utensil;
import com.epherical.epherolib.libs.org.spongepowered.configurate.hocon.HoconConfigurationLoader;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.component.DataComponentInitializers;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.internal.RegistrationEvents;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import org.slf4j.Logger;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.epherical.croptopia.CroptopiaCommon.createGroup;
import static com.epherical.croptopia.common.MiscNames.MOD_ID;
import static net.minecraft.SharedConstants.IS_RUNNING_IN_IDE;

@Mod(CroptopiaNeoForge.MODID)
public class CroptopiaNeoForge {
    public static final String MODID = "croptopia";
    private static final Logger LOGGER = LogUtils.getLogger();


    public static CroptopiaNeoForge mod;

    public static CroptopiaConfig config = new CroptopiaConfig(HoconConfigurationLoader.builder(), "croptopia_v3.conf");


    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = CREATIVE_MODE_TABS.register("croptopia", () ->
            CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.croptopia"))
                    .displayItems((featureFlagSet, output) ->
                            BuiltInRegistries.ITEM.entrySet().stream()
                                    .filter(entry -> entry.getKey().identifier().getNamespace().equals(MOD_ID))
                                    .sorted(Comparator.comparing(entry -> BuiltInRegistries.ITEM.getId(entry.getValue())))
                                    .forEach(entry -> output.accept(entry.getValue())))
                    .icon(() -> new ItemStack(Content.COFFEE)).build());

    public static final DeferredRegister<MapCodec<? extends BiomeModifier>> BIOME_SERIALIZER =
            DeferredRegister.create(NeoForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, MiscNames.MOD_ID);
    public static final DeferredRegister<BiomeModifier> BIOME_MODIFIER =
            DeferredRegister.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, MiscNames.MOD_ID);
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> GLM =
            DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, MiscNames.MOD_ID);


    public CroptopiaNeoForge(IEventBus modEventBus, ModContainer modContainer) {
        CroptopiaCommon.init();

        //config.addSerializer(TreeConfiguration.class, TreeConfiguration.Serializer.INSTANCE);
        //config.addSerializer(Identifier.class, IdentifierSerializer.INSTANCE);
        //config.loadConfig(MiscNames.MOD_ID);


        modEventBus.addListener(this::commonSetup);
        CREATIVE_MODE_TABS.register(modEventBus);
        NeoForge.EVENT_BUS.register(new BlockBreakEvent());

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (Croptopia) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        mod = this;
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.insertAfter(new ItemStack(Items.MANGROVE_PROPAGULE), new ItemStack(Content.CINNAMON.getSapling()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            TreeCrop.TREE_CROPS.stream().map(TreeCrop::getSaplingItem).map(ItemStack::new).forEachOrdered(stack -> {
                event.insertAfter(new ItemStack(Items.FLOWERING_AZALEA), stack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            });
            FarmlandCrop.FARMLAND_CROPS.stream().map(FarmlandCrop::getSeedItem).map(ItemStack::new).forEachOrdered(stack -> {
                event.insertAfter(new ItemStack(Items.NETHER_WART), stack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            });
            event.insertBefore(new ItemStack(Items.COAL_ORE), new ItemStack(Content.SALT_ORE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        } else if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            Utensil.copy().stream().map(ItemStack::new).forEachOrdered(stack -> {
                event.insertAfter(new ItemStack(Items.FLINT_AND_STEEL), stack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            });
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onColorSetup(RegisterColorHandlersEvent.BlockTintSources event) {
            event.register(List.of(BlockTintSources.foliage()), CroptopiaCommon.leafBlocks.toArray(Block[]::new));
        }
    }

    @EventBusSubscriber(modid = MODID)
    public static class DataHandler {
        @SubscribeEvent
        public static void gatherClientData(GatherDataEvent.Client event) {
            DataGenerator generator = event.getGenerator();
            PackOutput output = generator.getPackOutput();

            generator.addProvider(true, new CroptopiaItemModelProvider(output));

            config.addSerializer(TreeConfiguration.class, TreeConfiguration.Serializer.INSTANCE);
            config.loadConfig(MiscNames.MOD_ID);

            RegistrySetBuilder builder = new RegistrySetBuilder();
            builder.add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, CroptopiaWorldBiomeSelection::new);
            CroptopiaWorldGeneration generation = new CroptopiaWorldGeneration();
            builder.add(Registries.FEATURE, generation::addConfiguredFeatures);
            builder.add(Registries.PLACED_FEATURE, generation::addPlacedFeatures);
            CompletableFuture<HolderLookup.Provider> lookupProvider = event.getWorldLookupProvider();

            try {
                IS_RUNNING_IN_IDE = false;
                //collectComponentModifiers();
                BuiltInRegistries.DATA_COMPONENT_INITIALIZERS.build(lookupProvider.get())
                        .forEach(pendingComponents -> pendingComponents.apply());
                IS_RUNNING_IN_IDE = true;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }

            generator.addProvider(true, new CroptopiaItemTagProvider(output, Registries.ITEM, lookupProvider));
            DatapackBuiltinEntriesProvider worldProvider = DatapackBuiltinEntriesProvider.forWorldLayer(
                    output, "Croptopia world generation", lookupProvider, builder, Set.of(MODID));
            generator.addProvider(true, worldProvider);

            generator.addProvider(true,
                    new CroptopiaBiomeTagProvider(output, lookupProvider));
            generator.addProvider(true,
                    new CroptopiaBlockTagProvider(output, Registries.BLOCK, lookupProvider));

            generator.addProvider(true, new CroptopiaLootTableProvider.GlobalLootProvider(output, lookupProvider));

            LootTableProvider.SubProviderEntry blocks = new LootTableProvider.SubProviderEntry(CroptopiaLootTableProvider::new, LootContextParamSets.BLOCK);
            LootTableProvider.SubProviderEntry chests = new LootTableProvider.SubProviderEntry(CroptopiaLootTableProvider.ChestLoot::new, LootContextParamSets.CHEST);
            LootTableProvider.SubProviderEntry entity = new LootTableProvider.SubProviderEntry(CroptopiaLootTableProvider.EntityLoot::new, LootContextParamSets.ENTITY);
            LootTableProvider.SubProviderEntry fishing = new LootTableProvider.SubProviderEntry(CroptopiaLootTableProvider.FishingLoot::new, LootContextParamSets.FISHING);
            RegistrySetBuilder reloadableBuilder = new RegistrySetBuilder()
                    .add(CroptopiaRecipeProvider.create())
                    .add(Registries.ADVANCEMENT, CroptopiaAdvancementProvider.create())
                    .add(Registries.LOOT_TABLE, new LootTableProvider(Collections.emptySet(), List.of(blocks, chests, entity, fishing)));
            generator.addProvider(true, DatapackBuiltinEntriesProvider.forReloadableLayer(
                    output, "Croptopia recipes, advancements and loot", worldProvider.getRegistryProvider(),
                    event.getReloadableLookupProvider(), reloadableBuilder, Set.of(MODID)));

        }
    }

    @EventBusSubscriber(modid = MODID)
    public static class RegisterHandler {
        @SubscribeEvent
        public static void onRegister(RegisterEvent event) {
            if (event.getRegistryKey() == Registries.ITEM) {
                Content.GUIDE = new GuideBookItem(createGroup(createIdentifier(ItemNamesV2.GUIDE)));
                event.register(Registries.ITEM, createIdentifier(ItemNamesV2.GUIDE), () -> Content.GUIDE);
                Content.registerItems((id, itemSupplier) -> {
                    Item item = itemSupplier.apply(id);
                    /*if (Content.ITEM_REGISTER.getManipulations().containsKey(id)) {
                        apply = Content.ITEM_REGISTER.getManipulations().get(id);
                    }*/
                    event.register(Registries.ITEM, id, () -> item);
                    if (item instanceof CookingUtensil cookingUtensil) {
                        CraftingRemainingItemBypassMixin bypass = (CraftingRemainingItemBypassMixin) cookingUtensil;
                        bypass.croptopia$setCraftingRemainingItem(new ItemStackTemplate(item));
                    }

                    if (item instanceof BlockItem blockItem) {
                        blockItem.registerBlocks(Item.BY_BLOCK, item);
                    }
                    if (item instanceof SeedItem it) {
                        // maybe not needed anymore
                        CroptopiaCropBlock block = (CroptopiaCropBlock) (it).getBlock();
                        block.setSeed(it);
                    }
                    return item;
                });
            } else if (event.getRegistryKey() == Registries.BLOCK) {
                Content.registerBlocks((id, supplier) -> {
                    Block block = supplier.apply(id);
                    /*if (Content.BLOCK_REGISTER.getManipulations().containsKey(id)) {
                        supplier = Content.BLOCK_REGISTER.getManipulations().get(id);
                    }*/
                    event.register(Registries.BLOCK, id, () -> block);
                    return block;
                });
            }
        }
    }

    public static Identifier createIdentifier(String name) {
        return Identifier.fromNamespaceAndPath(MiscNames.MOD_ID, name);
    }
}
