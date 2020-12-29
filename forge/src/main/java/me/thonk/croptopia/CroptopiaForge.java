package me.thonk.croptopia;

import me.thonk.common.MiscNames;
import me.thonk.croptopia.blocks.CroptopiaCropBlock;
import me.thonk.croptopia.blocks.LeafCropBlock;
import me.thonk.croptopia.generator.BiomeModification;
import me.thonk.croptopia.items.SeedItem;
import me.thonk.croptopia.registry.BlockRegistry;
import me.thonk.croptopia.registry.ItemRegistry;
import me.thonk.croptopia.registry.LeavesRegistry;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.biome.BiomeColors;
import net.minecraft.world.level.ColorResolver;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
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


// The value here should match an entry in the META-INF/mods.toml file
@Mod("croptopia")
public class CroptopiaForge {

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public static ArrayList<Block> cropBlocks = new ArrayList<>();
    public static ArrayList<Block> leafBlocks = new ArrayList<>();

    public static final ItemGroup CROPTOPIA_ITEM_GROUP = new ItemGroup("croptopia") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemRegistry.onion);
        }
    };

    public CroptopiaForge() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(new BiomeModification());

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code


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



    public static Item.Properties createGroup() {
        return new Item.Properties().group(CROPTOPIA_ITEM_GROUP);
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
        }

        @SubscribeEvent
        public static void onItemRegister(final RegistryEvent.Register<Item> itemRegister) {
            ItemRegistry.init();
        }
    }

    public static ResourceLocation createIdentifier(String name) {
        //System.out.println("\"" + MOD_ID + ":" + name + "\",");
        return new ResourceLocation(MiscNames.MOD_ID, name);
    }

    public static Item registerItem(String itemName, Item item) {
        item.setRegistryName(createIdentifier(itemName));
        ForgeRegistries.ITEMS.register(item);
        if (item instanceof BlockNamedItem) {
            ((BlockNamedItem) item).addToBlockToItemMap(Item.BLOCK_TO_ITEM, item);
        }

        if (item instanceof SeedItem) {
            CroptopiaCropBlock block = (CroptopiaCropBlock) ((SeedItem) item).getBlock();
            block.setSeed(item);
        }

        // \bregisterItem\b..[A-Z]\w+",
        //System.out.println( "\"" + itemName + "\",");
        // TODO: maybe
        /*if (item instanceof SeedItem) {
            seeds.add(new ConfigurableSeed(itemName, item, ((SeedItem) item).getCategory(), 0.0125f));
        }*/
        return item;
    }

    public static Block registerBlock(String blockName, Block block) {
        cropBlocks.add(block);

        if (block instanceof LeafCropBlock) {
            leafBlocks.add(block);
            //System.out.println("\"" + blockName + "\",");
        } else {
            //System.out.println("\"" + blockName + "\",");
        }
        block.setRegistryName(createIdentifier(blockName));
        ForgeRegistries.BLOCKS.register(block);
        return block;
    }

    public static AbstractBlock.Properties createCropSettings() {
        return AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.CROP);
    }

    public static AbstractBlock.Properties createSaplingSettings() {
        return AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.GROUND);
    }

    public static LeafCropBlock createLeavesBlock() {
        return new LeafCropBlock(AbstractBlock.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.GROUND).doesNotBlockMovement()
                .setAllowsSpawn(CroptopiaForge::canSpawnOnLeaves).setSuffocates(CroptopiaForge::never).setBlocksVision(CroptopiaForge::never));
    }

    private static Boolean canSpawnOnLeaves(BlockState state, IBlockReader world, BlockPos pos, EntityType<?> type) {
        return type == EntityType.OCELOT || type == EntityType.PARROT;
    }

    private static boolean never(BlockState state, IBlockReader world, BlockPos pos) {
        return false;
    }
}
