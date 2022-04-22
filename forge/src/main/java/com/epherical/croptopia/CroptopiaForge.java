package com.epherical.croptopia;

import com.epherical.croptopia.blocks.CroptopiaCropBlock;
import com.epherical.croptopia.blocks.LeafCropBlock;
import com.epherical.croptopia.common.ItemNamesV2;
import com.epherical.croptopia.common.MiscNames;
import com.epherical.croptopia.config.Config;
import com.epherical.croptopia.events.BiomeModification;
import com.epherical.croptopia.events.BlockBreakEvent;
import com.epherical.croptopia.events.EntitySpawn;
import com.epherical.croptopia.events.Harvest;
import com.epherical.croptopia.events.LootTableModification;
import com.epherical.croptopia.items.GuideBookItem;
import com.epherical.croptopia.items.SeedItem;
import com.epherical.croptopia.loot.EntityModifier;
import com.epherical.croptopia.loot.SpawnChestModifier;
import com.epherical.croptopia.register.Content;
import com.epherical.croptopia.registry.ItemRegistry;
import com.google.common.collect.Sets;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.ItemLike;
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
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.epherical.croptopia.common.ItemNames.GUIDE;
import static com.epherical.croptopia.registry.ItemRegistry.createGroup;


// The value here should match an entry in the META-INF/mods.toml file
@Mod("croptopia")
public class CroptopiaForge {
    private static final Logger LOGGER = LogManager.getLogger();

    /*public static ArrayList<Block> cropBlocks = new ArrayList<>();
    public static ArrayList<Block> leafBlocks = new ArrayList<>();
    public static ArrayList<Item> cropItems = new ArrayList<>();*/

    // todo: there might be a different way i'm supposed to do this in forge.
    public static LootItemConditionType BIOME_CHECK;
    private static final SpawnChestModifier.Serializer SPAWN_CHEST_MODIFIER = new SpawnChestModifier.Serializer();
    private static final EntityModifier.Serializer ENTITY_MODIFIER = new EntityModifier.Serializer();

    public static Config config;

    public static CreativeModeTab CROPTOPIA_ITEM_GROUP;

    public static CroptopiaMod mod;

    public CroptopiaForge() {
        config = new Config();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(config::initConfig);

        MinecraftForge.EVENT_BUS.addListener(CroptopiaForge::onWorldLoad);
        MinecraftForge.EVENT_BUS.register(new BiomeModification());
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
                return new ItemStack(ItemRegistry.coffee);
            }
        };
        mod = new CroptopiaMod(new ForgeAdapter());
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        Composter.init();
        BIOME_CHECK = registerLootCondition(MiscNames.BIOME_CHECK_LOOT_CONDITION, new BiomeLootCondition.Serializer());
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
        InterModComms.sendTo("cookingforblockheads", "RegisterTool", () -> new ItemStack(ItemRegistry.cookingPot));
        InterModComms.sendTo("cookingforblockheads", "RegisterTool", () -> new ItemStack(ItemRegistry.foodPress));
        InterModComms.sendTo("cookingforblockheads", "RegisterTool", () -> new ItemStack(ItemRegistry.fryingPan));
        InterModComms.sendTo("cookingforblockheads", "RegisterTool", () -> new ItemStack(ItemRegistry.mortarAndPestle));

        InterModComms.sendTo("cookingforblockheads", "RegisterWaterItem", () -> new ItemStack(ItemRegistry.waterBottle));
        InterModComms.sendTo("cookingforblockheads", "RegisterMilkItem", () -> new ItemStack(ItemRegistry.milkBottle));
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
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            Content.registerBlocks((id, object) -> {
                object.setRegistryName(id);
                blockRegistryEvent.getRegistry().register(object);
            });
            //LeavesRegistry.init();
            //BlockRegistry.init();
            //GeneratorRegistry.init();
        }

        @SubscribeEvent
        public static void onItemRegister(final RegistryEvent.Register<Item> itemRegister) {
            Content.GUIDE = new GuideBookItem(createGroup());
            Content.GUIDE.setRegistryName(createIdentifier(ItemNamesV2.GUIDE));
            itemRegister.getRegistry().register(Content.GUIDE);
            Content.registerItems((id, item) -> {
                item.setRegistryName(id);
                itemRegister.getRegistry().register(item);
                if (item instanceof ItemNameBlockItem) {
                    ((ItemNameBlockItem) item).registerBlocks(Item.BY_BLOCK, item);
                }
                if (item instanceof SeedItem it) {
                    // maybe not needed anymore
                    CroptopiaCropBlock block = (CroptopiaCropBlock) (it).getBlock();
                    block.setSeed(it);
                }
            });
            //ItemRegistry.init(itemRegister);
            List<ItemLike> chickenItems = new ArrayList<>(CroptopiaMod.seeds);
            chickenItems.addAll(Arrays.stream(Chicken.FOOD_ITEMS.getItems()).map(ItemStack::getItem).collect(Collectors.toList()));
            Chicken.FOOD_ITEMS = Ingredient.of(chickenItems.toArray(new ItemLike[0]));
            List<Item> parrotItems = new ArrayList<>(Parrot.TAME_FOOD);
            parrotItems.addAll(CroptopiaMod.seeds);
            Parrot.TAME_FOOD = Sets.newHashSet(parrotItems);

            List<ItemLike> pigItems = new ArrayList<>(Arrays.asList(ItemRegistry.yam, ItemRegistry.sweetPotato));
            pigItems.addAll(Arrays.stream(Pig.FOOD_ITEMS.getItems()).map(ItemStack::getItem).collect(Collectors.toList()));
            Pig.FOOD_ITEMS = Ingredient.of(pigItems.toArray(new ItemLike[0]));

        }

        @SubscribeEvent
        public static void onLootRegister(final RegistryEvent.Register<GlobalLootModifierSerializer<?>> register) {
            // lazy
            SPAWN_CHEST_MODIFIER.setRegistryName(new ResourceLocation(MiscNames.MOD_ID, "spawn_loot"));
            ENTITY_MODIFIER.setRegistryName(new ResourceLocation(MiscNames.MOD_ID, "entity_modifier"));
            register.getRegistry().register(SPAWN_CHEST_MODIFIER);
            register.getRegistry().register(ENTITY_MODIFIER);
        }
    }

    public static ResourceLocation createIdentifier(String name) {
        return new ResourceLocation(MiscNames.MOD_ID, name);
    }

    public static Block registerBlock(String blockName, Block block) {
        /*cropBlocks.add(block);

        if (block instanceof LeafCropBlock || block instanceof LeavesBlock) {
            leafBlocks.add(block);
            //System.out.println("\"" + blockName + "\",");
        } else {
            //System.out.println("\"" + blockName + "\",");
        }
        block.setRegistryName(createIdentifier(blockName));
        ForgeRegistries.BLOCKS.register(block);*/
        return block;
    }

    public static LootItemConditionType registerLootCondition(String id, Serializer<? extends LootItemCondition> serializer) {
        return Registry.register(Registry.LOOT_CONDITION_TYPE, new ResourceLocation(MiscNames.MOD_ID, id), new LootItemConditionType(serializer));
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
