package me.thonk.croptopia;

import me.thonk.common.MiscNames;
import me.thonk.croptopia.blocks.CroptopiaCropBlock;
import me.thonk.croptopia.blocks.LeafCropBlock;
import me.thonk.croptopia.config.Config;
import me.thonk.croptopia.dependencies.Patchouli;
import me.thonk.croptopia.events.BiomeModification;
import me.thonk.croptopia.events.BlockBreakEvent;
import me.thonk.croptopia.events.CroptopiaVillagerTrades;
import me.thonk.croptopia.events.Harvest;
import me.thonk.croptopia.events.LootTableModification;
import me.thonk.croptopia.items.CropItem;
import me.thonk.croptopia.items.SeedItem;
//import me.thonk.croptopia.mixin.VillagerAccess;
import me.thonk.croptopia.loot.SpawnChestModifier;
import me.thonk.croptopia.registry.BlockRegistry;
import me.thonk.croptopia.registry.GeneratorRegistry;
import me.thonk.croptopia.registry.ItemRegistry;
import me.thonk.croptopia.registry.LeavesRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventListenerHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmlserverevents.FMLServerStartingEvent;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;


// The value here should match an entry in the META-INF/mods.toml file
@Mod("croptopia")
public class CroptopiaForge {

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public static ArrayList<Block> cropBlocks = new ArrayList<>();
    public static ArrayList<Block> leafBlocks = new ArrayList<>();
    public static ArrayList<SeedItem> seeds = new ArrayList<>();
    public static ArrayList<Item> cropItems = new ArrayList<>();

    // todo: there might be a different way i'm supposed to do this in forge.
    public static LootItemConditionType BIOME_CHECK;
    private static final SpawnChestModifier.Serializer SPAWN_CHEST_MODIFIER = new SpawnChestModifier.Serializer();

    public static Config config;


    public static Patchouli patchouli;

    public static CreativeModeTab CROPTOPIA_ITEM_GROUP;

    public CroptopiaForge() {
        config = new Config();
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(config::initConfig);

        MinecraftForge.EVENT_BUS.addListener(CroptopiaForge::onWorldLoad);
        MinecraftForge.EVENT_BUS.register(new BiomeModification());
        MinecraftForge.EVENT_BUS.register(new LootTableModification());
        MinecraftForge.EVENT_BUS.register(new Harvest());
        MinecraftForge.EVENT_BUS.register(new BlockBreakEvent());
        MinecraftForge.EVENT_BUS.register(new CroptopiaVillagerTrades());
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, config.config);
        EventListenerHelper.getListenerList(PlayerInteractEvent.RightClickBlock.class);

        // Register ourselves for server and other game events we are interested in
        CROPTOPIA_ITEM_GROUP = new CreativeModeTab("croptopia") {
            @Override
            public ItemStack makeIcon() {
                return new ItemStack(ItemRegistry.coffee);
            }
        };
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        Composter.init();
        BIOME_CHECK = registerLootCondition(MiscNames.BIOME_CHECK_LOOT_CONDITION, new BiomeLootCondition.Serializer());
        patchouli = new Patchouli();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        cropBlocks.forEach(block -> {
            ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutoutMipped());
        });

        BlockColors colors = Minecraft.getInstance().getBlockColors();
        colors.register((state, world, pos, tintIndex) ->
                world != null && pos != null
                        ? BiomeColors.getAverageFoliageColor(world, pos)
                        : FoliageColor.getDefaultColor(), leafBlocks.toArray(new Block[]{}));
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        InterModComms.sendTo("cookingforblockheads", "RegisterTool", () -> new ItemStack(ItemRegistry.cookingPot));
        InterModComms.sendTo("cookingforblockheads", "RegisterTool", () -> new ItemStack(ItemRegistry.foodPress));
        InterModComms.sendTo("cookingforblockheads", "RegisterTool", () -> new ItemStack(ItemRegistry.fryingPan));
        InterModComms.sendTo("cookingforblockheads", "RegisterTool", () -> new ItemStack(ItemRegistry.mortarAndPestle));

        InterModComms.sendTo("cookingforblockheads", "RegisterWaterItem", () -> new ItemStack(ItemRegistry.waterBottle));
        InterModComms.sendTo("cookingforblockheads", "RegisterMilkItem", () -> new ItemStack(ItemRegistry.milkBottle));

        // some example code to dispatch IMC to another mod
    }

    private void processIMC(final InterModProcessEvent event) {
        // some example code to receive and process InterModComms from other mods
    }


    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LeavesRegistry.init();
            BlockRegistry.init();
            GeneratorRegistry.init();

            // todo: implement again
            /*Map<Block, Block> stripMap = new HashMap<>(AxeItem.BLOCK_STRIPPING_MAP);
            stripMap.put(BlockRegistry.cinnamonLog, BlockRegistry.strippedCinnamonLog);
            stripMap.put(BlockRegistry.cinnamonWood, BlockRegistry.strippedCinnamonWood);
            AxeItem.BLOCK_STRIPPING_MAP = stripMap;*/
        }

        @SubscribeEvent
        public static void onItemRegister(final RegistryEvent.Register<Item> itemRegister) {
            ItemRegistry.init(itemRegister);
        }

        @SubscribeEvent
        public static void onLootRegister(final RegistryEvent.Register<GlobalLootModifierSerializer<?>> register) {
            SPAWN_CHEST_MODIFIER.setRegistryName(new ResourceLocation(MiscNames.MOD_ID, "spawn_loot"));
            register.getRegistry().register(SPAWN_CHEST_MODIFIER);
        }
    }

    public static ResourceLocation createIdentifier(String name) {
        //System.out.println("\"" + MOD_ID + ":" + name + "\",");
        return new ResourceLocation(MiscNames.MOD_ID, name);
    }

    public static Item registerItem(RegistryEvent.Register<Item> itemRegister, String itemName, Item item) {
        item.setRegistryName(createIdentifier(itemName));
        itemRegister.getRegistry().register(item);
        if (item instanceof ItemNameBlockItem) {
            ((ItemNameBlockItem) item).registerBlocks(Item.BY_BLOCK, item);
        }

        if (item instanceof CropItem) {
            cropItems.add(item);
        }

        if (item instanceof SeedItem it) {
            CroptopiaCropBlock block = (CroptopiaCropBlock) (it).getBlock();
            block.setSeed(it);
        }

        if (item instanceof SeedItem) {
            seeds.add((SeedItem) item);
        }
        return item;
    }

    public static Block registerBlock(String blockName, Block block) {
        cropBlocks.add(block);

        if (block instanceof LeafCropBlock || block instanceof LeavesBlock) {
            leafBlocks.add(block);
            //System.out.println("\"" + blockName + "\",");
        } else {
            //System.out.println("\"" + blockName + "\",");
        }
        block.setRegistryName(createIdentifier(blockName));
        ForgeRegistries.BLOCKS.register(block);
        return block;
    }

    public static LootItemConditionType registerLootCondition(String id, Serializer<? extends LootItemCondition> serializer) {
        return Registry.register(Registry.LOOT_CONDITION_TYPE, new ResourceLocation(MiscNames.MOD_ID, id), new LootItemConditionType(serializer));
    }

    public static BlockBehaviour.Properties createCropSettings() {
        return BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP);
    }

    public static BlockBehaviour.Properties createSaplingSettings() {
        return BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS);
    }

    public static LeafCropBlock createLeavesBlock() {
        return new LeafCropBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.CROP).noOcclusion()
                .isValidSpawn(CroptopiaForge::canSpawnOnLeaves).isSuffocating(CroptopiaForge::never).isViewBlocking(CroptopiaForge::never));
    }

    public static LeavesBlock createRegularLeavesBlock() {
        return new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.CROP).noOcclusion()
                .isValidSpawn(CroptopiaForge::canSpawnOnLeaves).isSuffocating(CroptopiaForge::never).isViewBlocking(CroptopiaForge::never));
    }

    private static Boolean canSpawnOnLeaves(BlockState state, BlockGetter world, BlockPos pos, EntityType<?> type) {
        return type == EntityType.OCELOT || type == EntityType.PARROT;
    }

    private static boolean never(BlockState state, BlockGetter world, BlockPos pos) {
        return false;
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
