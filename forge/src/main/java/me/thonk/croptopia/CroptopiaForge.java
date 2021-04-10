package me.thonk.croptopia;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import me.thonk.common.MiscNames;
import me.thonk.croptopia.blocks.CroptopiaCropBlock;
import me.thonk.croptopia.blocks.LeafCropBlock;
import me.thonk.croptopia.config.Config;
import me.thonk.croptopia.dependencies.Patchouli;
import me.thonk.croptopia.events.BiomeModification;
import me.thonk.croptopia.events.BlockBreakEvent;
import me.thonk.croptopia.events.Harvest;
import me.thonk.croptopia.events.LootTableModification;
import me.thonk.croptopia.items.CropItem;
import me.thonk.croptopia.items.SeedItem;
import me.thonk.croptopia.mixin.VillagerAccess;
import me.thonk.croptopia.registry.BlockRegistry;
import me.thonk.croptopia.registry.ItemRegistry;
import me.thonk.croptopia.registry.LeavesRegistry;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.item.*;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.loot.ILootSerializer;
import net.minecraft.loot.LootConditionType;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventListenerHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


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
    public static LootConditionType BIOME_CHECK;
    public static DamageDurabilityRecipe.DamageDurabilitySerializer DAMAGE_DURABILITY;

    public static Config config;


    public static Patchouli patchouli;

    public static ItemGroup CROPTOPIA_ITEM_GROUP;

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
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, config.config);
        EventListenerHelper.getListenerList(PlayerInteractEvent.RightClickBlock.class);

        // Register ourselves for server and other game events we are interested in
        CROPTOPIA_ITEM_GROUP = new ItemGroup("croptopia") {
            @Override
            public ItemStack createIcon() {
                return new ItemStack(ItemRegistry.onion);
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
            RenderTypeLookup.setRenderLayer(block, RenderType.getCutoutMipped());
        });
        BlockColors colors = event.getMinecraftSupplier().get().getBlockColors();
        colors.register((state, world, pos, tintIndex) ->
                world != null && pos != null
                        ? BiomeColors.getFoliageColor(world, pos)
                        : FoliageColors.getDefault(), leafBlocks.toArray(new Block[]{}));
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
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

            Map<Block, Block> stripMap = new HashMap<>(AxeItem.BLOCK_STRIPPING_MAP);
            stripMap.put(BlockRegistry.cinnamonLog, BlockRegistry.strippedCinnamonLog);
            stripMap.put(BlockRegistry.cinnamonWood, BlockRegistry.strippedCinnamonWood);
            AxeItem.BLOCK_STRIPPING_MAP = stripMap;
        }

        @SubscribeEvent
        public static void onItemRegister(final RegistryEvent.Register<Item> itemRegister) {
            ItemRegistry.init(itemRegister);
        }

        @SubscribeEvent
        public static void recipeRegister(final RegistryEvent.Register<IRecipeSerializer<?>> register) {
            DAMAGE_DURABILITY = new DamageDurabilityRecipe.DamageDurabilitySerializer();
            DAMAGE_DURABILITY.setRegistryName(MiscNames.MOD_ID, MiscNames.RECIPE_SERIALIZER_DAMAGE_DURABILITY);
            register.getRegistry().register(DAMAGE_DURABILITY);
        }
    }

    public static ResourceLocation createIdentifier(String name) {
        //System.out.println("\"" + MOD_ID + ":" + name + "\",");
        return new ResourceLocation(MiscNames.MOD_ID, name);
    }

    public static Item registerItem(RegistryEvent.Register<Item> itemRegister, String itemName, Item item) {
        item.setRegistryName(createIdentifier(itemName));
        itemRegister.getRegistry().register(item);
        if (item instanceof BlockNamedItem) {
            ((BlockNamedItem) item).addToBlockToItemMap(Item.BLOCK_TO_ITEM, item);
        }

        if (item instanceof CropItem) {
            cropItems.add(item);
            System.out.println("heop");
        }

        if (item instanceof SeedItem) {
            CroptopiaCropBlock block = (CroptopiaCropBlock) ((SeedItem) item).getBlock();
            block.setSeed(item);
        }

        // \bregisterItem\b..[A-Z]\w+",
        //System.out.println( "\"" + itemName + "\",");
        // TODO: maybe
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

    public static LootConditionType registerLootCondition(String id, ILootSerializer<? extends ILootCondition> serializer) {
        return Registry.register(Registry.LOOT_CONDITION_TYPE, new ResourceLocation(MiscNames.MOD_ID, id), new LootConditionType(serializer));
    }

    public static <S extends IRecipeSerializer<T>, T extends IRecipe<?>> S registerSerializer(String id, S serializer) {
        serializer.setRegistryName(new ResourceLocation(MiscNames.MOD_ID, id));
        ForgeRegistries.RECIPE_SERIALIZERS.register(serializer);
        return serializer;
    }

    public static AbstractBlock.Properties createCropSettings() {
        return AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.CROP);
    }

    public static AbstractBlock.Properties createSaplingSettings() {
        return AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT);
    }

    public static LeafCropBlock createLeavesBlock() {
        return new LeafCropBlock(AbstractBlock.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.CROP).notSolid()
                .setAllowsSpawn(CroptopiaForge::canSpawnOnLeaves).setSuffocates(CroptopiaForge::never).setBlocksVision(CroptopiaForge::never));
    }

    public static LeavesBlock createRegularLeavesBlock() {
        return new LeavesBlock(AbstractBlock.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.CROP).notSolid()
                .setAllowsSpawn(CroptopiaForge::canSpawnOnLeaves).setSuffocates(CroptopiaForge::never).setBlocksVision(CroptopiaForge::never));
    }

    private static Boolean canSpawnOnLeaves(BlockState state, IBlockReader world, BlockPos pos, EntityType<?> type) {
        return type == EntityType.OCELOT || type == EntityType.PARROT;
    }

    private static boolean never(BlockState state, IBlockReader world, BlockPos pos) {
        return false;
    }

    private static void modifyVillagerFoodItems() {
        ImmutableMap.Builder<Item, Integer> villagerFoodItems = new ImmutableMap.Builder<Item, Integer>()
                .putAll(VillagerAccess.getItemFoodValues());
        cropItems.forEach(item -> villagerFoodItems.put(item, item.getFood().getHealing()));
        VillagerAccess.setItemFoodValues(villagerFoodItems.build());
    }

    private static void modifyVillagerGatherables() {
        ImmutableSet.Builder<Item> villagerGatherables = new ImmutableSet.Builder<Item>().addAll(VillagerAccess.getGatherableItems());
        seeds.forEach(villagerGatherables::add);
        cropItems.forEach(villagerGatherables::add);
        VillagerAccess.setGatherableItems(villagerGatherables.build());
    }

    public static void onWorldLoad(WorldEvent.Load event) {
        modifyVillagerFoodItems();
        modifyVillagerGatherables();
    }
}
