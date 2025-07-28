package com.epherical.croptopia;

import com.epherical.croptopia.blocks.CroptopiaCropBlock;
import com.epherical.croptopia.blocks.LeafCropBlock;
import com.epherical.croptopia.client.ClientFunctions;
import com.epherical.croptopia.common.ItemNamesV2;
import com.epherical.croptopia.common.MiscNames;
import com.epherical.croptopia.config.CroptopiaConfig;
import com.epherical.croptopia.config.IdentifierSerializer;
import com.epherical.croptopia.config.TreeConfiguration;
import com.epherical.croptopia.datagen.CroptopiaBiomeTagProvider;
import com.epherical.croptopia.datagen.CroptopiaBlockTagProvider;
import com.epherical.croptopia.datagen.CroptopiaIndependentItemTagProvider;
import com.epherical.croptopia.datagen.CroptopiaItemModelProvider;
import com.epherical.croptopia.datagen.CroptopiaItemTagProvider;
import com.epherical.croptopia.datagen.CroptopiaLootTableProvider;
import com.epherical.croptopia.datagen.CroptopiaRecipeProvider;
import com.epherical.croptopia.datagen.CroptopiaWorldBiomeSelection;
import com.epherical.croptopia.datagen.CroptopiaWorldGeneration;
import com.epherical.croptopia.items.GuideBookItem;
import com.epherical.croptopia.items.SeedItem;
import com.epherical.croptopia.listeners.BlockBreakEvent;
import com.epherical.croptopia.register.Content;
import com.epherical.croptopia.register.helpers.FarmlandCrop;
import com.epherical.croptopia.register.helpers.TreeCrop;
import com.epherical.croptopia.register.helpers.Utensil;
import com.epherical.epherolib.libs.org.spongepowered.configurate.hocon.HoconConfigurationLoader;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static com.epherical.croptopia.common.MiscNames.MOD_ID;

@Mod(CroptopiaMod.MODID)
public class CroptopiaMod {
    public static final String MODID = "croptopia";
    private static final Logger LOGGER = LogUtils.getLogger();


    public static ArrayList<Item> cropItems = new ArrayList<>();
    public static ArrayList<Block> cropBlocks = new ArrayList<>();
    public static ArrayList<Block> leafBlocks = new ArrayList<>();
    public static ArrayList<Item> seeds = new ArrayList<>();

    public static CroptopiaMod mod;

    public static CroptopiaConfig config = new CroptopiaConfig(HoconConfigurationLoader.builder(), "croptopia_v3.conf");


    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = CREATIVE_MODE_TABS.register("croptopia", () ->
            CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.croptopia"))
                    .displayItems((featureFlagSet, output) ->
                            BuiltInRegistries.ITEM.entrySet().stream()
                                    .filter(entry -> entry.getKey().location().getNamespace().equals(MOD_ID))
                                    .sorted(Comparator.comparing(entry -> BuiltInRegistries.ITEM.getId(entry.getValue())))
                                    .forEach(entry -> output.accept(entry.getValue())))
                    .icon(() -> new ItemStack(Content.COFFEE)).build());

    public static final DeferredRegister<MapCodec<? extends BiomeModifier>> BIOME_SERIALIZER =
            DeferredRegister.create(NeoForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, MiscNames.MOD_ID);
    public static final DeferredRegister<BiomeModifier> BIOME_MODIFIER =
            DeferredRegister.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, MiscNames.MOD_ID);
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> GLM =
            DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, MiscNames.MOD_ID);


    public CroptopiaMod(IEventBus modEventBus, ModContainer modContainer) {
        config.addSerializer(TreeConfiguration.class, TreeConfiguration.Serializer.INSTANCE);
        config.addSerializer(ResourceLocation.class, IdentifierSerializer.INSTANCE);
        config.loadConfig(MiscNames.MOD_ID);


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
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        private static ClientFunctions functions = new ClientFunctions();

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            functions.registerBlockLayers(block -> ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutoutMipped()));
            BlockColors colors = Minecraft.getInstance().getBlockColors();
            ItemColors itemColors = Minecraft.getInstance().getItemColors();
            colors.register(functions.registerLeafColors(), functions.leaves());
            itemColors.register(functions.registerItemColors(colors), functions.items());
        }

        @SubscribeEvent
        public static void onColorSetup(RegisterColorHandlersEvent.Block event) {
            event.register(functions.registerLeafColors(), functions.leaves());
        }
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD)
    public static class DataHandler {
        @SubscribeEvent
        public static void gatherData(GatherDataEvent event) {

            config.addSerializer(TreeConfiguration.class, TreeConfiguration.Serializer.INSTANCE);
            config.addSerializer(ResourceLocation.class, IdentifierSerializer.INSTANCE);
            config.loadConfig(MiscNames.MOD_ID);

            RegistrySetBuilder builder = new RegistrySetBuilder();
            builder.add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, CroptopiaWorldBiomeSelection::new);
            CroptopiaWorldGeneration generation = new CroptopiaWorldGeneration();
            builder.add(Registries.CONFIGURED_FEATURE, generation::addConfiguredFeatures);
            builder.add(Registries.PLACED_FEATURE, generation::addPlacedFeatures);
            DataGenerator generator = event.getGenerator();
            PackOutput output = generator.getPackOutput();
            ExistingFileHelper helper = event.getExistingFileHelper();
            CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();


            generator.addProvider(event.includeClient(),
                    new CroptopiaItemModelProvider(output, helper));

            CroptopiaItemTagProvider itemProvider = generator.addProvider(event.includeServer(),
                    new CroptopiaItemTagProvider(output, Registries.ITEM, lookupProvider, helper));
            generator.addProvider(event.includeServer(),
                    new DatapackBuiltinEntriesProvider(output, lookupProvider, builder, Set.of(MODID)));
            generator.addProvider(event.includeServer(),
                    new CroptopiaRecipeProvider(output, lookupProvider));
            generator.addProvider(event.includeServer(),
                    new CroptopiaBiomeTagProvider(output, lookupProvider, helper));
            CroptopiaBlockTagProvider blockProvider = generator.addProvider(event.includeServer(),
                    new CroptopiaBlockTagProvider(output, Registries.BLOCK, lookupProvider, helper));

            generator.addProvider(event.includeServer(), new CroptopiaLootTableProvider.GlobalLootProvider(output, lookupProvider));

            LootTableProvider.SubProviderEntry blocks = new LootTableProvider.SubProviderEntry(CroptopiaLootTableProvider::new, LootContextParamSets.BLOCK);
            LootTableProvider.SubProviderEntry chests = new LootTableProvider.SubProviderEntry(CroptopiaLootTableProvider.ChestLoot::new, LootContextParamSets.CHEST);
            LootTableProvider.SubProviderEntry entity = new LootTableProvider.SubProviderEntry(CroptopiaLootTableProvider.EntityLoot::new, LootContextParamSets.ENTITY);
            LootTableProvider.SubProviderEntry fishing = new LootTableProvider.SubProviderEntry(CroptopiaLootTableProvider.FishingLoot::new, LootContextParamSets.FISHING);
            LootTableProvider lootProvider = new LootTableProvider(output, Collections.emptySet(), List.of(blocks, chests, entity, fishing), lookupProvider);

            generator.addProvider(event.includeServer(), lootProvider);

            CroptopiaIndependentItemTagProvider provider = new CroptopiaIndependentItemTagProvider(output, lookupProvider, itemProvider.contentsGetter(), blockProvider.contentsGetter(), helper);
            generator.addProvider(event.includeServer(), provider);

        }
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD)
    public static class RegisterHandler {
        @SubscribeEvent
        public static void onRegister(RegisterEvent event) {
            if (event.getRegistryKey() == Registries.ITEM) {
                Content.GUIDE = new GuideBookItem(createGroup());
                event.register(Registries.ITEM, createIdentifier(ItemNamesV2.GUIDE), () -> Content.GUIDE);
                Content.registerItems((id, itemSupplier) -> {
                    if (Content.ITEM_REGISTER.getManipulations().containsKey(id)) {
                        itemSupplier = Content.ITEM_REGISTER.getManipulations().get(id);
                    }
                    Item item = itemSupplier.get();
                    event.register(Registries.ITEM, id, () -> item);
                    if (item instanceof ItemNameBlockItem) {
                        ((ItemNameBlockItem) item).registerBlocks(Item.BY_BLOCK, item);
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
                    if (Content.BLOCK_REGISTER.getManipulations().containsKey(id)) {
                        supplier = Content.BLOCK_REGISTER.getManipulations().get(id);
                    }
                    Block block = supplier.get();
                    event.register(Registries.BLOCK, id, () -> block);
                    return block;
                });
                FireBlock fire = (FireBlock) Blocks.FIRE;
                fire.setFlammable(Content.CINNAMON.getLog(), 5, 5);
                fire.setFlammable(Content.CINNAMON.getWood(), 5, 5);
                fire.setFlammable(Content.CINNAMON.getStrippedLog(), 5, 5);
                fire.setFlammable(Content.CINNAMON.getStrippedWood(), 5, 5);
            }
        }
    }


    public static Item.Properties createGroup() {
        return new Item.Properties();
    }

    public static ResourceLocation createIdentifier(String name) {
        return ResourceLocation.fromNamespaceAndPath(MiscNames.MOD_ID, name);
    }

    public static BlockBehaviour.Properties createCropSettings() {
        return BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP);
    }

    public static LeafCropBlock createLeavesBlock() {
        return new LeafCropBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.2F).ignitedByLava().randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(CroptopiaMod::canSpawnOnLeaves).isSuffocating((a, b, c) -> false).isViewBlocking((a, b, c) -> false));
    }

    public static LeavesBlock createRegularLeavesBlock() {
        return new LeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.2F).ignitedByLava().randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(CroptopiaMod::canSpawnOnLeaves).isSuffocating(CroptopiaMod::never).isViewBlocking(CroptopiaMod::never));
    }

    public static BlockBehaviour.Properties createSaplingSettings() {
        return BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS);
    }

    private static boolean never(BlockState state, BlockGetter world, BlockPos pos) {
        return false;
    }

    public static boolean canSpawnOnLeaves(BlockState state, BlockGetter world, BlockPos pos, EntityType<?> type) {
        return type == EntityType.OCELOT || type == EntityType.PARROT;
    }
}
