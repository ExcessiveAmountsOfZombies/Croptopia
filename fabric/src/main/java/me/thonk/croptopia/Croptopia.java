package me.thonk.croptopia;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.ints.IntList;
import me.thonk.common.MiscNames;
import me.thonk.croptopia.blocks.CroptopiaCropBlock;
import me.thonk.croptopia.blocks.LeafCropBlock;
import me.thonk.croptopia.config.ConfigurableSeed;
import me.thonk.croptopia.config.CroptopiaConfig;
import me.thonk.croptopia.config.IdentifierSerializer;
import me.thonk.croptopia.config.TreeConfiguration;
import me.thonk.croptopia.dependencies.Patchouli;
import me.thonk.croptopia.generator.BiomeModifiers;
import me.thonk.croptopia.items.CropLootTableModifier;
import me.thonk.croptopia.items.CropItem;
import me.thonk.croptopia.items.SeedItem;
import me.thonk.croptopia.loottables.BiomeLootCondition;
import me.thonk.croptopia.mixin.AxeAccess;
import me.thonk.croptopia.mixin.ChickenAccess;
import me.thonk.croptopia.mixin.FarmerWorkTaskAccessor;
import me.thonk.croptopia.mixin.ParrotAccess;
import me.thonk.croptopia.mixin.VillagerAccess;
import me.thonk.croptopia.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.item.*;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
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

    public static final ItemGroup CROPTOPIA_ITEM_GROUP = FabricItemGroupBuilder.create(new Identifier(MiscNames.MOD_ID, "croptopia"))
            .icon(() -> new ItemStack(Content.Farmland.ONION.asItem()))
            .build();
    public static final LootConditionType BIOME_CHECK =  registerLootCondition(MiscNames.BIOME_CHECK_LOOT_CONDITION, new BiomeLootCondition.Serializer());
    public static Patchouli patchouli;

    private static final String TECH_REBORN_MOD_ID = "techreborn";

    @Override
    public void onInitialize() {
        FabricLoader.getInstance().getModContainer(TECH_REBORN_MOD_ID)
                .map(modContainer -> ResourceManagerHelper.registerBuiltinResourcePack(new Identifier("croptopia", "treborn"),  modContainer, ResourcePackActivationType.DEFAULT_ENABLED))
                .filter(success -> !success);

        patchouli = new Patchouli();

        Content.init();
        Composter.init();

        this.config = new CroptopiaConfig(devEnvironment, "croptopia.conf");
        config.addSerializer(ConfigurableSeed.class, ConfigurableSeed.Serializer.INSTANCE);
        config.addSerializer(TreeConfiguration.class, TreeConfiguration.Serializer.INSTANCE);
        config.addSerializer(Identifier.class, IdentifierSerializer.INSTANCE);
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

    public static Identifier createIdentifier(String name) {
        //System.out.println("\"" + MOD_ID + ":" + name + "\",");
        return new Identifier(MiscNames.MOD_ID, name);
    }

    public static LootConditionType registerLootCondition(String id, JsonSerializer<? extends LootCondition> serializer) {
        return Registry.register(Registry.LOOT_CONDITION_TYPE, new Identifier(MiscNames.MOD_ID, id), new LootConditionType(serializer));
    }

    public static <S extends RecipeSerializer<T>, T extends Recipe<?>> S registerSerializer(String id, S serializer) {
        return Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(MiscNames.MOD_ID, id), serializer);
    }

    public static Item registerItem(String itemName, Item item) {
        Registry.register(Registry.ITEM, Croptopia.createIdentifier(itemName), item);
        if (item instanceof AliasedBlockItem) {
            ((AliasedBlockItem) item).appendBlocks(Item.BLOCK_ITEMS, item);
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

    public static Item.Settings createGroup() {
        return new Item.Settings().group(CROPTOPIA_ITEM_GROUP);
    }

    public static Block registerBlock(String blockName, Block item) {
        cropBlocks.add(item);

        Registry.register(Registry.BLOCK, Croptopia.createIdentifier(blockName), item);
        return item;
    }

    public static FabricBlockSettings createCropSettings() {
        return FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP);
    }

    public static LeafCropBlock createLeavesBlock() {
        return new LeafCropBlock(FabricBlockSettings.of(Material.LEAVES).strength(0.2F).ticksRandomly().sounds(BlockSoundGroup.GRASS).nonOpaque().allowsSpawning(Croptopia::canSpawnOnLeaves).suffocates(Croptopia::never).blockVision(Croptopia::never));
    }

    public static LeavesBlock createRegularLeavesBlock() {
        return new LeavesBlock(FabricBlockSettings.of(Material.LEAVES).strength(0.2F).ticksRandomly().sounds(BlockSoundGroup.GRASS).nonOpaque().allowsSpawning(Croptopia::canSpawnOnLeaves).suffocates(Croptopia::never).blockVision(Croptopia::never));
    }

    public static boolean canSpawnOnLeaves(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        return type == EntityType.OCELOT || type == EntityType.PARROT;
    }

    private static boolean never(BlockState state, BlockView world, BlockPos pos) {
        return false;
    }


    public static List<ConfigurableSeed> getSeeds() {
        return seeds;
    }


    private void modifyVillagerFoodItems() {
        ImmutableMap.Builder<Item, Integer> villagerFoodItems = new ImmutableMap.Builder<Item, Integer>()
                .putAll(VillagerAccess.getItemFoodValues());
        Content.createCropStream().filter(item -> item.getFoodComponent() != null)
                .forEach(item -> villagerFoodItems.put(item, item.getFoodComponent().getHunger()));
        VillagerAccess.setItemFoodValues(villagerFoodItems.build());
    }

    private void modifyVillagerGatherables() {
        ImmutableSet.Builder<Item> villagerGatherables = new ImmutableSet.Builder<Item>().addAll(VillagerAccess.getGatherableItems());
        seeds.forEach(configurableSeed -> villagerGatherables.add(configurableSeed.getSeedItem()));
        Content.createCropStream().forEach(villagerGatherables::add);
        VillagerAccess.setGatherableItems(villagerGatherables.build());
    }

    private void modifyAxeBlockStripping() {
        Map<Block, Block> immutableBlocks = AxeAccess.getStrippedBlocks();
        var axeMap = new ImmutableMap.Builder<Block, Block>().putAll(immutableBlocks);
        for (Content.Bark crop : Content.Bark.values()) {
            axeMap.put(crop.getLog(), crop.getStrippedLog());
            axeMap.put(crop.getWood(), crop.getStrippedWood());
        }
        AxeAccess.setStrippedBlocks(axeMap.build());
    }

    private void modifyChickenBreeds() {
        IntList stacks = ChickenAccess.getBreedingIngredients().getMatchingItemIds();
        List<Item> baseItems = new ArrayList<>();

        for (Integer stack : stacks) {
            baseItems.add(Item.byRawId(stack));
        }
        // TODO iterate over farmland
        baseItems.addAll(seeds.stream().map(ConfigurableSeed::getSeedItem).collect(Collectors.toList()));
        ChickenAccess.setBreedingIngredients(Ingredient.ofItems(baseItems.toArray(new Item[0])));
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
