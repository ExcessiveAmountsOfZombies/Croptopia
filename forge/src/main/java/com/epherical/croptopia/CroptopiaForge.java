package com.epherical.croptopia;

import com.epherical.croptopia.biome.CropModifier;
import com.epherical.croptopia.biome.SaltModifier;
import com.epherical.croptopia.biome.TreeModifier;
import com.epherical.croptopia.blocks.CroptopiaCropBlock;
import com.epherical.croptopia.common.ItemNamesV2;
import com.epherical.croptopia.common.MiscNames;
import com.epherical.croptopia.config.Config;
import com.epherical.croptopia.datagen.CroptopiaBiomeData;
import com.epherical.croptopia.listeners.BlockBreakEvent;
import com.epherical.croptopia.listeners.EntitySpawn;
import com.epherical.croptopia.listeners.Harvest;
import com.epherical.croptopia.listeners.LootTableModification;
import com.epherical.croptopia.items.GuideBookItem;
import com.epherical.croptopia.items.SeedItem;
import com.epherical.croptopia.loot.AdditionalTableModifier;
import com.epherical.croptopia.loot.EntityModifier;
import com.epherical.croptopia.loot.SpawnChestModifier;
import com.epherical.croptopia.register.Content;
import com.epherical.croptopia.registry.GeneratorRegistry;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventListenerHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
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
import java.util.List;
import java.util.stream.Collectors;

import static com.epherical.croptopia.CroptopiaMod.createGroup;


// The value here should match an entry in the META-INF/mods.toml file
@Mod("croptopia")
public class CroptopiaForge {
    private static final Logger LOGGER = LogManager.getLogger();

    // todo: there might be a different way i'm supposed to do this in forge.
    private static final SpawnChestModifier.Serializer SPAWN_CHEST_MODIFIER = new SpawnChestModifier.Serializer();
    private static final EntityModifier.Serializer ENTITY_MODIFIER = new EntityModifier.Serializer();
    private static final AdditionalTableModifier.Serializer ADDTIONAL_TABLE_MODIFIER = new AdditionalTableModifier.Serializer();

    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_SERIALIZER =
            DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, MiscNames.MOD_ID);
    public static final DeferredRegister<BiomeModifier> BIOME_MODIFIER =
            DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIERS, MiscNames.MOD_ID);

    public static Config config;

    public static CreativeModeTab CROPTOPIA_ITEM_GROUP;

    public static CroptopiaMod mod;

    public CroptopiaForge() {
        config = new Config();
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        bus.addListener(this::enqueueIMC);
        bus.addListener(this::processIMC);
        bus.addListener(this::doClientStuff);
        CroptopiaBiomeData data = new CroptopiaBiomeData(bus);
        BIOME_MODIFIER.register(bus);
        BIOME_SERIALIZER.register(bus);
        BIOME_SERIALIZER.register("trees", TreeModifier::makeCodec);
        BIOME_SERIALIZER.register("crops", CropModifier::makeCodec);
        BIOME_SERIALIZER.register("salt", SaltModifier::makeCodec);

        bus.addListener(data::getData);

        bus.addListener(config::initConfig);

        MinecraftForge.EVENT_BUS.addListener(CroptopiaForge::onWorldLoad);
        MinecraftForge.EVENT_BUS.register(new LootTableModification());
        MinecraftForge.EVENT_BUS.register(new Harvest());
        MinecraftForge.EVENT_BUS.register(new BlockBreakEvent());
        //MinecraftForge.EVENT_BUS.register(new CroptopiaVillagerTrades());
        MinecraftForge.EVENT_BUS.register(new EntitySpawn());
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, config.config);
        EventListenerHelper.getListenerList(PlayerInteractEvent.RightClickBlock.class);

        // Register ourselves for server and other game events we are interested in
        CROPTOPIA_ITEM_GROUP = new CreativeModeTab("croptopia") {
            @Override
            public ItemStack makeIcon() {
                return new ItemStack(Content.COFFEE);
            }
        };
        mod = new CroptopiaMod(new ForgeAdapter());
    }

    private void setup(final FMLCommonSetupEvent event) {
        Composter.init();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client

        CroptopiaMod.cropBlocks.forEach(block -> {
            ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutoutMipped());
        });

        BlockColors colors = Minecraft.getInstance().getBlockColors();
        colors.register((state, world, pos, tintIndex) ->
                world != null && pos != null
                        ? BiomeColors.getAverageFoliageColor(world, pos)
                        : FoliageColor.getDefaultColor(), CroptopiaMod.leafBlocks.toArray(new Block[]{}));
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        InterModComms.sendTo("cookingforblockheads", "RegisterTool", () -> new ItemStack(Content.COOKING_POT));
        InterModComms.sendTo("cookingforblockheads", "RegisterTool", () -> new ItemStack(Content.FOOD_PRESS));
        InterModComms.sendTo("cookingforblockheads", "RegisterTool", () -> new ItemStack(Content.FRYING_PAN));
        InterModComms.sendTo("cookingforblockheads", "RegisterTool", () -> new ItemStack(Content.MORTAR_AND_PESTLE));

        InterModComms.sendTo("cookingforblockheads", "RegisterWaterItem", () -> new ItemStack(Content.WATER_BOTTLE));
        InterModComms.sendTo("cookingforblockheads", "RegisterMilkItem", () -> new ItemStack(Content.MILK_BOTTLE));
    }

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
        public static void onRegister(RegisterEvent event) {
            if (event.getRegistryKey().equals(ForgeRegistries.Keys.ITEMS)) {
                Content.GUIDE = new GuideBookItem(createGroup());
                event.register(ForgeRegistries.Keys.ITEMS, createIdentifier(ItemNamesV2.GUIDE), () -> Content.GUIDE);
                Content.registerItems((id, item) -> {
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

                GeneratorRegistry.init();
                Config.setFeatures(config);
                TreeModifier.register(BIOME_MODIFIER);
                SaltModifier.register(BIOME_MODIFIER);
                CropModifier.register(BIOME_MODIFIER);
            }
            if (event.getRegistryKey().equals(ForgeRegistries.Keys.BLOCKS)) {
                Content.registerBlocks((id, object) -> {
                    event.register(ForgeRegistries.Keys.BLOCKS, blockRegisterHelper -> blockRegisterHelper.register(id, object));
                    return object;
                });
            }

            onLootRegister(event);
        }

        public static void onLootRegister(RegisterEvent register) {
            // lazy
            // todo: rewrite this so it's cleaner, only passing in resourcelocation and the GLM
            register.register(ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, helper -> {
                helper.register(createIdentifier("spawn_loot"), SPAWN_CHEST_MODIFIER);
            });
            register.register(ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, helper -> {
                helper.register(createIdentifier("entity_modifier"), ENTITY_MODIFIER);
            });
            register.register(ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, helper -> {
                helper.register(createIdentifier("table_adder"), ADDTIONAL_TABLE_MODIFIER);
            });
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

    public static void onWorldLoad(WorldEvent.Load event) {
        if (!hasRun) {
            modifyVillagerFoodItems();
            modifyVillagerGatherables();
            hasRun = true;
        }
    }
}
