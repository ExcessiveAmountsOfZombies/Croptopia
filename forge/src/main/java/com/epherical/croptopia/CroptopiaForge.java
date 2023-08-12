package com.epherical.croptopia;

import com.epherical.croptopia.biome.CropModifier;
import com.epherical.croptopia.biome.SaltModifier;
import com.epherical.croptopia.biome.TreeModifier;
import com.epherical.croptopia.blocks.CroptopiaCropBlock;
import com.epherical.croptopia.common.ItemNamesV2;
import com.epherical.croptopia.common.MiscNames;
import com.epherical.croptopia.config.CroptopiaConfig;
import com.epherical.croptopia.items.GuideBookItem;
import com.epherical.croptopia.items.SeedItem;
import com.epherical.croptopia.listeners.BlockBreakEvent;
import com.epherical.croptopia.listeners.EntitySpawn;
import com.epherical.croptopia.listeners.Harvest;
import com.epherical.croptopia.listeners.LootTableModification;
import com.epherical.croptopia.loot.AdditionalTableModifier;
import com.epherical.croptopia.loot.EntityModifier;
import com.epherical.croptopia.loot.SpawnChestModifier;
import com.epherical.croptopia.register.Content;
import com.epherical.croptopia.register.helpers.FarmlandCrop;
import com.epherical.croptopia.register.helpers.TreeCrop;
import com.epherical.croptopia.register.helpers.Utensil;
import com.epherical.epherolib.libs.org.spongepowered.configurate.hocon.HoconConfigurationLoader;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.EventListenerHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.epherical.croptopia.CroptopiaMod.createGroup;
import static com.epherical.croptopia.common.MiscNames.MOD_ID;


// The value here should match an entry in the META-INF/mods.toml file
@Mod("croptopia")
public class CroptopiaForge {
    private static final Logger LOGGER = LogManager.getLogger();

    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_SERIALIZER =
            DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, MiscNames.MOD_ID);
    public static final DeferredRegister<BiomeModifier> BIOME_MODIFIER =
            DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIERS, MiscNames.MOD_ID);
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> GLM = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, MiscNames.MOD_ID);

    public static CreativeModeTab CROPTOPIA_ITEM_GROUP = null;




    public static CroptopiaMod mod;

    public static MinecraftServer server;

    public CroptopiaForge() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::enqueueIMC);
        bus.addListener(this::processIMC);
        bus.addListener(this::doClientStuff);
        BIOME_MODIFIER.register(bus);
        BIOME_SERIALIZER.register(bus);
        BIOME_SERIALIZER.register("trees", TreeModifier::makeCodec);
        BIOME_SERIALIZER.register("crops", CropModifier::makeCodec);
        BIOME_SERIALIZER.register("salt", SaltModifier::makeCodec);
        GLM.register(bus);
       /* GLM.register("spawn_loot", SpawnChestModifier.CODEC);
        GLM.register("entity_modifier", EntityModifier.CODEC);
        GLM.register("table_adder", AdditionalTableModifier.CODEC);*/
        // todo: forge bug >>> will probably need to change this back later
        GLM.register("spawn_loot", SpawnChestModifier.CODEC);
        GLM.register("entity_modifier", EntityModifier.CODEC);
        GLM.register("table_adder", AdditionalTableModifier.CODEC);


        MinecraftForge.EVENT_BUS.addListener(CroptopiaForge::onWorldLoad);
        MinecraftForge.EVENT_BUS.register(new LootTableModification());
        MinecraftForge.EVENT_BUS.register(new Harvest());
        MinecraftForge.EVENT_BUS.register(new BlockBreakEvent());
        //MinecraftForge.EVENT_BUS.addListener(this::onDatapackRegister);
        //MinecraftForge.EVENT_BUS.register(new CroptopiaVillagerTrades());
        MinecraftForge.EVENT_BUS.register(new EntitySpawn());
        //ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, config.config);
        EventListenerHelper.getListenerList(PlayerInteractEvent.RightClickBlock.class);

        // Register ourselves for server and other game events we are interested in
        mod = new CroptopiaMod(new ForgeAdapter(), new CroptopiaConfig(HoconConfigurationLoader.builder(), "croptopia_v3.conf"));
        mod.registerCompost();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        ClientFunctions functions = new ClientFunctions();
        functions.registerBlockLayers(block -> ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutoutMipped()));
        BlockColors colors = Minecraft.getInstance().getBlockColors();
        colors.register(functions.registerLeafColors(), functions.leaves());
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        /*InterModComms.sendTo("cookingforblockheads", "RegisterTool", () -> new ItemStack(Content.COOKING_POT));
        InterModComms.sendTo("cookingforblockheads", "RegisterTool", () -> new ItemStack(Content.FOOD_PRESS));
        InterModComms.sendTo("cookingforblockheads", "RegisterTool", () -> new ItemStack(Content.FRYING_PAN));
        InterModComms.sendTo("cookingforblockheads", "RegisterTool", () -> new ItemStack(Content.MORTAR_AND_PESTLE));*/

        InterModComms.sendTo("cookingforblockheads", "RegisterWaterItem", () -> new ItemStack(Content.WATER_BOTTLE));
        InterModComms.sendTo("cookingforblockheads", "RegisterMilkItem", () -> new ItemStack(Content.MILK_BOTTLE));
    }

    /*public void onDatapackRegister(ServerAboutToStartEvent event) {
        server = event.getServer();
    }*/

    private void processIMC(final InterModProcessEvent event) {
    }


    @SubscribeEvent // You can use SubscribeEvent and let the Event Bus discover methods to call
    public void onServerStarting(FMLDedicatedServerSetupEvent event) {

    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

        @SubscribeEvent
        public static void registerTag(BuildCreativeModeTabContentsEvent event) {
            // not a fan of forges event compared to fabrics.
            if (event.getTab().equals(CreativeModeTabs.NATURAL_BLOCKS)) {
                event.getEntries().putAfter(new ItemStack(Items.MANGROVE_PROPAGULE), new ItemStack(Content.CINNAMON.getSapling()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                TreeCrop.TREE_CROPS.stream().map(TreeCrop::getSaplingItem).map(ItemStack::new).forEachOrdered(stack -> {
                    event.getEntries().putAfter(new ItemStack(Items.FLOWERING_AZALEA), stack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                });
                FarmlandCrop.FARMLAND_CROPS.stream().map(FarmlandCrop::getSeedItem).map(ItemStack::new).forEachOrdered(stack -> {
                    event.getEntries().putAfter(new ItemStack(Items.NETHER_WART), stack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                });
                event.getEntries().putBefore(new ItemStack(Items.COAL_ORE), new ItemStack(Content.SALT_ORE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            } else if (event.getTab().equals(CreativeModeTabs.TOOLS_AND_UTILITIES)) {
                Utensil.copy().stream().map(ItemStack::new).forEachOrdered(stack -> {
                    event.getEntries().putAfter(new ItemStack(Items.FLINT_AND_STEEL), stack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                });
            }
        }

        @SubscribeEvent
        public static void onRegister(RegisterEvent event) {
            if (event.getRegistryKey().equals(ForgeRegistries.Keys.ITEMS)) {
                CROPTOPIA_ITEM_GROUP = CreativeModeTab.builder()
                        .title(Component.translatable("itemGroup.croptopia"))
                        .displayItems((featureFlagSet, output) ->
                                BuiltInRegistries.ITEM.entrySet().stream()
                                        .filter(entry -> entry.getKey().location().getNamespace().equals(MOD_ID))
                                        .sorted(Comparator.comparing(entry -> BuiltInRegistries.ITEM.getId(entry.getValue())))
                                        .forEach(entry -> output.accept(entry.getValue())))
                        .icon(() -> new ItemStack(Content.COFFEE)).build();
                Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, new ResourceLocation(MOD_ID, "croptopia"), CROPTOPIA_ITEM_GROUP);
                Content.GUIDE = new GuideBookItem(createGroup());
                event.register(ForgeRegistries.Keys.ITEMS, createIdentifier(ItemNamesV2.GUIDE), () -> Content.GUIDE);


                Content.registerItems((id, itemSupplier) -> {
                    Item item = itemSupplier.get();
                    event.register(ForgeRegistries.Keys.ITEMS, id, () -> item);
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

                List<ItemLike> chickenItems = new ArrayList<>(CroptopiaMod.seeds);
                chickenItems.addAll(Arrays.stream(Chicken.FOOD_ITEMS.getItems()).map(ItemStack::getItem).collect(Collectors.toList()));
                Chicken.FOOD_ITEMS = Ingredient.of(chickenItems.toArray(new ItemLike[0]));
                List<Item> parrotItems = new ArrayList<>(Parrot.TAME_FOOD);
                parrotItems.addAll(CroptopiaMod.seeds);
                Parrot.TAME_FOOD = Sets.newHashSet(parrotItems);

                List<ItemLike> pigItems = new ArrayList<>(Arrays.asList(Content.YAM, Content.SWEETPOTATO));
                pigItems.addAll(Arrays.stream(Pig.FOOD_ITEMS.getItems()).map(ItemStack::getItem).collect(Collectors.toList()));
                Pig.FOOD_ITEMS = Ingredient.of(pigItems.toArray(new ItemLike[0]));

            }
            if (event.getRegistryKey().equals(ForgeRegistries.Keys.BLOCKS)) {
                Content.registerBlocks((id, object) -> {
                    Block block = object.get();
                    event.register(ForgeRegistries.Keys.BLOCKS, blockRegisterHelper -> blockRegisterHelper.register(id, block));
                    return block;
                });
                mod.platform().registerFlammableBlocks();
            }
        }
    }

    public static ResourceLocation createIdentifier(String name) {
        return new ResourceLocation(MiscNames.MOD_ID, name);
    }

    private static void modifyVillagerFoodItems() {
        // todo: implement again
        /*ImmutableMap.Builder<Item, Integer> villagerFoodItems = new ImmutableMap.Builder<Item, Integer>()
                .putAll(VillagerAccess.getItemFoodValues());
        cropItems.forEach(item -> villagerFoodItems.put(item, item.getFood().getHealing()));
        VillagerAccess.setItemFoodValues(villagerFoodItems.build());*/
    }

    private static void modifyVillagerGatherables() {
        // todo: implement again
        /*ImmutableSet.Builder<Item> villagerGatherables = new ImmutableSet.Builder<Item>().addAll(VillagerAccess.getGatherableItems());
        seeds.forEach(villagerGatherables::add);
        cropItems.forEach(villagerGatherables::add);
        VillagerAccess.setGatherableItems(villagerGatherables.build());*/
    }

    private static boolean hasRun;

    public static void onWorldLoad(LevelEvent.Load event) {
        if (!hasRun) {
            modifyVillagerFoodItems();
            modifyVillagerGatherables();
            hasRun = true;
        }
    }

    public MinecraftServer getServer() {
        return server;
    }

    public void setServer(MinecraftServer server) {
        this.server = server;
    }
}
