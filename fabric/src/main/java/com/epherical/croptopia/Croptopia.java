package com.epherical.croptopia;

import com.epherical.croptopia.blocks.CroptopiaCropBlock;
import com.epherical.croptopia.blocks.LeafCropBlock;
import com.epherical.croptopia.items.SeedItem;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.ints.IntList;
import com.epherical.croptopia.common.MiscNames;
import com.epherical.croptopia.config.ConfigurableSeed;
import com.epherical.croptopia.config.CroptopiaConfig;
import com.epherical.croptopia.config.IdentifierSerializer;
import com.epherical.croptopia.config.TreeConfiguration;
import com.epherical.croptopia.dependencies.Patchouli;
import com.epherical.croptopia.generator.BiomeModifiers;
import com.epherical.croptopia.items.CropLootTableModifier;
import com.epherical.croptopia.loottables.BiomeLootCondition;
import com.epherical.croptopia.mixin.AxeAccess;
import com.epherical.croptopia.mixin.ChickenAccess;
import com.epherical.croptopia.mixin.FarmerWorkTaskAccessor;
import com.epherical.croptopia.mixin.ParrotAccess;
import com.epherical.croptopia.mixin.VillagerAccess;
import com.epherical.croptopia.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import org.spongepowered.configurate.serialize.SerializationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;



public class Croptopia implements ModInitializer {

    private final boolean devEnvironment = Boolean.getBoolean(MiscNames.MOD_ID + ".dev");

    public static ArrayList<Block> cropBlocks = new ArrayList<>();
    private static List<ConfigurableSeed> seeds = new ArrayList<>();


    public CroptopiaConfig config;

    public static final CreativeModeTab CROPTOPIA_ITEM_GROUP = FabricItemGroupBuilder.create(new ResourceLocation(MiscNames.MOD_ID, "croptopia"))
            .icon(() -> new ItemStack(Content.Farmland.ONION.asItem()))
            .build();
    public static final LootItemConditionType BIOME_CHECK =  registerLootCondition(MiscNames.BIOME_CHECK_LOOT_CONDITION, new BiomeLootCondition.Serializer());
    public static Patchouli patchouli;

    private static final String TECH_REBORN_MOD_ID = "techreborn";

    @Override
    public void onInitialize() {
        FabricLoader.getInstance().getModContainer(TECH_REBORN_MOD_ID)
                .map(modContainer -> ResourceManagerHelper.registerBuiltinResourcePack(new ResourceLocation("croptopia", "treborn"),  modContainer, ResourcePackActivationType.DEFAULT_ENABLED))
                .filter(success -> !success);
        CroptopiaMod mod = new CroptopiaMod(new FabricAdapter());


        patchouli = new Patchouli();

        Content.init();
        Composter.init();

        this.config = new CroptopiaConfig(devEnvironment, "croptopia.conf");
        config.addSerializer(ConfigurableSeed.class, ConfigurableSeed.Serializer.INSTANCE);
        config.addSerializer(TreeConfiguration.class, TreeConfiguration.Serializer.INSTANCE);
        config.addSerializer(ResourceLocation.class, IdentifierSerializer.INSTANCE);
        config.loadConfig();
        try {
            seeds = config.getRootNode().node("configuredSeeds").getList(ConfigurableSeed.class);
        } catch (SerializationException e) {
            e.printStackTrace();
        }

        BiomeModifiers.init(this);
        CropLootTableModifier.init();

        CommandRegistrationCallback.EVENT.register((commandDispatcher, b) -> {
            SetupCommand.register(commandDispatcher);
        });

        //CroptopiaVillagerTrades.init();

        modifyVillagerFoodItems();
        modifyVillagerGatherables();
        modifyAxeBlockStripping();
        modifyChickenBreeds();
        modifyParrotBreeds();
        modifyVillagerFarmerTaskCompostables();
    }

    public static ResourceLocation createIdentifier(String name) {
        //System.out.println("\"" + MOD_ID + ":" + name + "\",");
        return new ResourceLocation(MiscNames.MOD_ID, name);
    }

    public static LootItemConditionType registerLootCondition(String id, Serializer<? extends LootItemCondition> serializer) {
        return Registry.register(Registry.LOOT_CONDITION_TYPE, new ResourceLocation(MiscNames.MOD_ID, id), new LootItemConditionType(serializer));
    }

    public static <S extends RecipeSerializer<T>, T extends Recipe<?>> S registerSerializer(String id, S serializer) {
        return Registry.register(Registry.RECIPE_SERIALIZER, new ResourceLocation(MiscNames.MOD_ID, id), serializer);
    }

    public static Item registerItem(String itemName, Item item) {
        Registry.register(Registry.ITEM, Croptopia.createIdentifier(itemName), item);
        if (item instanceof ItemNameBlockItem) {
            ((ItemNameBlockItem) item).registerBlocks(Item.BY_BLOCK, item);
        }

        if (item instanceof SeedItem seedItem) {
            CroptopiaCropBlock block = (CroptopiaCropBlock) ((SeedItem) item).getBlock();
            block.setSeedsItem(seedItem);
            //runner.getTagger().addSeedTag(item, Croptopia.createIdentifier(itemName));
        }

        // \bregisterItem\b..[A-Z]\w+",
        //System.out.println( "\"" + itemName + "\",");
        if (item instanceof SeedItem) {
            seeds.add(new ConfigurableSeed(itemName, item, ((SeedItem) item).getCategory()));
        }

        // data generation
        //runner.getTagger().addTag(item, Croptopia.createIdentifier(itemName));
        return item;
    }

    public static Item.Properties createGroup() {
        return new Item.Properties().tab(CROPTOPIA_ITEM_GROUP);
    }

    public static Block registerBlock(String blockName, Block item) {
        cropBlocks.add(item);

        Registry.register(Registry.BLOCK, Croptopia.createIdentifier(blockName), item);
        return item;
    }

    public static FabricBlockSettings createCropSettings() {
        return FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(SoundType.CROP);
    }

    public static LeafCropBlock createLeavesBlock() {
        return new LeafCropBlock(FabricBlockSettings.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(Croptopia::canSpawnOnLeaves).isSuffocating(Croptopia::never).isViewBlocking(Croptopia::never));
    }

    public static LeavesBlock createRegularLeavesBlock() {
        return new LeavesBlock(FabricBlockSettings.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(Croptopia::canSpawnOnLeaves).isSuffocating(Croptopia::never).isViewBlocking(Croptopia::never));
    }

    public static boolean canSpawnOnLeaves(BlockState state, BlockGetter world, BlockPos pos, EntityType<?> type) {
        return type == EntityType.OCELOT || type == EntityType.PARROT;
    }

    private static boolean never(BlockState state, BlockGetter world, BlockPos pos) {
        return false;
    }


    public static List<ConfigurableSeed> getSeeds() {
        return seeds;
    }


    private void modifyVillagerFoodItems() {
        Builder<Item, Integer> villagerFoodItems = new Builder<Item, Integer>()
                .putAll(VillagerAccess.getItemFoodValues());
        Content.createCropStream().filter(item -> item.getFoodProperties() != null)
                .forEach(item -> villagerFoodItems.put(item, item.getFoodProperties().getNutrition()));
        VillagerAccess.setItemFoodValues(villagerFoodItems.build());
    }

    private void modifyVillagerGatherables() {
        ImmutableSet.Builder<Item> villagerGatherables = new ImmutableSet.Builder<Item>().addAll(VillagerAccess.getGatherableItems());
        seeds.forEach(configurableSeed -> villagerGatherables.add(configurableSeed.getSeedItem()));
        Content.createCropStream().forEach(villagerGatherables::add);
        VillagerAccess.setGatherableItems(villagerGatherables.build());
    }

    private void modifyAxeBlockStripping() {
        Map<Block, Block> immutableBlocks = AxeAccess.getStrippables();
        var axeMap = new Builder<Block, Block>().putAll(immutableBlocks);
        for (Content.Bark crop : Content.Bark.values()) {
            axeMap.put(crop.getLog(), crop.getStrippedLog());
            axeMap.put(crop.getWood(), crop.getStrippedWood());
        }
        AxeAccess.setStrippables(axeMap.build());
    }

    private void modifyChickenBreeds() {
        IntList stacks = ChickenAccess.getFoodItems().getStackingIds();
        List<Item> baseItems = new ArrayList<>();

        for (Integer stack : stacks) {
            baseItems.add(Item.byId(stack));
        }
        // TODO iterate over farmland
        baseItems.addAll(seeds.stream().map(ConfigurableSeed::getSeedItem).collect(Collectors.toList()));
        ChickenAccess.setFoodItems(Ingredient.of(baseItems.toArray(new Item[0])));
    }

    private void modifyParrotBreeds() {
        Set<Item> baseItems = ParrotAccess.getTamingIngredients();
        Set<Item> newItems = Sets.newHashSet(baseItems);
        newItems.addAll(seeds.stream().map(ConfigurableSeed::getSeedItem).collect(Collectors.toList()));
        ParrotAccess.setTamingIngredients(newItems);
    }

    private void modifyVillagerFarmerTaskCompostables() {
        List<Item> baseItems = FarmerWorkTaskAccessor.getCompostables();
        List<Item> newItems = Lists.newArrayList(baseItems);
        newItems.addAll(seeds.stream().map(ConfigurableSeed::getSeedItem).collect(Collectors.toList()));
        FarmerWorkTaskAccessor.setCompostables(newItems);
    }
}
