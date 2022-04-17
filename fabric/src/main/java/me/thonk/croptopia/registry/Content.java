package me.thonk.croptopia.registry;

import me.thonk.common.FeatureNames;
import me.thonk.croptopia.Croptopia;
import me.thonk.croptopia.blocks.CroptopiaCropBlock;
import me.thonk.croptopia.blocks.CroptopiaSaplingBlock;
import me.thonk.croptopia.blocks.LeafCropBlock;
import me.thonk.croptopia.generator.CroptopiaSaplingGenerator;
import me.thonk.croptopia.items.CropItem;
import me.thonk.croptopia.items.CroptopiaSaplingItem;
import me.thonk.croptopia.items.Drink;
import me.thonk.croptopia.items.SeedItem;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static me.thonk.croptopia.Croptopia.createGroup;
import static me.thonk.croptopia.Croptopia.createIdentifier;
import static me.thonk.croptopia.registry.FoodRegistry.*;
import static net.minecraft.world.biome.Biome.Category.*;

/**
 * Contains the items and blocks we want and need.
 */
public class Content {

    /**
     * Enum for all (Croptopia) farmland crops.
     */
    public enum Farmland implements ItemConvertible {
        ARTICHOKE(EDIBLE_1, SWAMP),
        ASPARAGUS(EDIBLE_3, SWAMP),
        BARLEY(EDIBLE_1, PLAINS, TAIGA),
        BASIL(EDIBLE_1, JUNGLE),
        BELLPEPPER(EDIBLE_3, PLAINS),
        BLACKBEAN(EDIBLE_3, FOREST),
        BLACKBERRY(EDIBLE_3, FOREST, TAIGA),
        BLUEBERRY(EDIBLE_3, FOREST, TAIGA),
        BROCCOLI(EDIBLE_3, PLAINS),
        CABBAGE(EDIBLE_1, PLAINS),
        CANTALOUPE(EDIBLE_3, FOREST),
        CAULIFLOWER(EDIBLE_3, FOREST),
        CELERY(EDIBLE_3, FOREST),
        CHILE_PEPPER(EDIBLE_3, PLAINS),
        COFFEE_BEANS(EDIBLE_3, "coffee", JUNGLE),
        CORN(EDIBLE_3, PLAINS),
        CRANBERRY(EDIBLE_3, SWAMP),
        CUCUMBER(EDIBLE_3, PLAINS),
        CURRANT(EDIBLE_3, SWAMP),
        EGGPLANT(EDIBLE_3, JUNGLE),
        ELDERBERRY(EDIBLE_3, FOREST),
        GARLIC(EDIBLE_1, JUNGLE),
        GINGER(SAVANNA),
        GRAPE(EDIBLE_3, FOREST),
        GREENBEAN(EDIBLE_3, PLAINS),
        GREENONION(EDIBLE_1, JUNGLE),
        HONEYDEW(EDIBLE_3, JUNGLE),
        HOPS(SAVANNA),
        KALE(EDIBLE_3, PLAINS),
        KIWI(EDIBLE_3, SAVANNA),
        LEEK(EDIBLE_3, SAVANNA),
        LETTUCE(EDIBLE_3, PLAINS),
        MUSTARD(PLAINS),
        OAT(EDIBLE_1, PLAINS),
        OLIVE(EDIBLE_3, SAVANNA),
        ONION(EDIBLE_3, JUNGLE),
        PEANUT(EDIBLE_1, JUNGLE),
        PEPPER(PLAINS),
        PINEAPPLE(EDIBLE_3, JUNGLE),
        RADISH(EDIBLE_3, FOREST),
        RASPBERRY(EDIBLE_3, FOREST, TAIGA),
        RHUBARB(EDIBLE_3, JUNGLE),
        RICE(EDIBLE_1, JUNGLE),
        RUTABAGA(EDIBLE_3, SAVANNA, TAIGA),
        SAGUARO(EDIBLE_3, DESERT),
        SOYBEAN(EDIBLE_1, PLAINS),
        SPINACH(EDIBLE_3, FOREST),
        SQUASH(EDIBLE_3, SAVANNA, TAIGA),
        STRAWBERRY(EDIBLE_3, FOREST, TAIGA),
        SWEETPOTATO(EDIBLE_3, PLAINS),
        TEA_LEAVES("tea", FOREST),
        TOMATILLO(EDIBLE_3, FOREST),
        TOMATO(EDIBLE_3, FOREST),
        TURMERIC(SAVANNA),
        TURNIP(EDIBLE_3, JUNGLE),
        VANILLA(JUNGLE),
        YAM(EDIBLE_3, SAVANNA),
        ZUCCHINI(EDIBLE_3, SAVANNA);

        private Item item;
        private Block block;
        private SeedItem seed;

        Farmland(FoodComponent foodComponent, String shortNameVariant, Biome.Category... biomes) {
            String name = name().toLowerCase();
            if (foodComponent == null) {
                item = new CropItem(createGroup());
            }
            else {
                item = new CropItem(createGroup().food(foodComponent));
            }
            Registry.register(Registry.ITEM, Croptopia.createIdentifier(name), item);
            block = new CroptopiaCropBlock(createCropSettings());
            Croptopia.registerBlock((shortNameVariant != null ? shortNameVariant : name) + "_crop", block);
            seed = new SeedItem(block, createGroup(), biomes);
            Croptopia.registerItem((shortNameVariant != null ? shortNameVariant : name) + "_seed", seed);
        }

        Farmland(FoodComponent foodComponent, Biome.Category... biomes) {
            this(foodComponent, null, biomes);
        }

        Farmland(String shortNameVariant, Biome.Category... biomes) {
            this(null, shortNameVariant, biomes);
        }

        Farmland(Biome.Category... biomes) {
            this(null, null, biomes);
        }

        @Override
        public Item asItem() {
            return item;
        }

        public Block asBlock() {
            return block;
        }

        public SeedItem getSeed() {
            return seed;
        }
    }

    /**
     * Enum for all (Croptopia) tree crops.
     * <p>
     * Does include {@link Items#APPLE} as {@link Tree#APPLE} (access via {@link Tree#asItem()}.
     * Does not include cinnamon.
     * </p>
     */
    public enum Tree implements ItemConvertible {
        ALMOND(Blocks.DARK_OAK_LEAVES, EDIBLE_3, 4, 3, 0),
        // coding for apple requires null here
        APPLE(Blocks.OAK_LEAVES, null, 5, 3, 0),
        APRICOT(Blocks.OAK_LEAVES, EDIBLE_3, 5, 2, 0),
        AVOCADO(Blocks.OAK_LEAVES, EDIBLE_3, 5, 3, 0),
        BANANA(Blocks.JUNGLE_LEAVES, EDIBLE_3, 4, 8, 0),
        CASHEW(Blocks.DARK_OAK_LEAVES, EDIBLE_1, 4, 3, 0),
        CHERRY(Blocks.OAK_LEAVES, EDIBLE_3, 5, 3, 0),
        COCONUT(Blocks.JUNGLE_LEAVES, EDIBLE_1, 5, 2, 3),
        DATE(Blocks.JUNGLE_LEAVES, EDIBLE_3, 5, 8, 0),
        DRAGONFRUIT(Blocks.JUNGLE_LEAVES, EDIBLE_3, 5, 7, 0),
        FIG(Blocks.JUNGLE_LEAVES, EDIBLE_3, 4, 8, 0),
        GRAPEFRUIT(Blocks.JUNGLE_LEAVES, EDIBLE_3, 4, 8, 0),
        KUMQUAT(Blocks.JUNGLE_LEAVES, EDIBLE_3, 4, 8, 0),
        LEMON(Blocks.OAK_LEAVES, EDIBLE_3, 5, 3, 0),
        LIME(Blocks.OAK_LEAVES, EDIBLE_3, 5, 2, 0),
        MANGO(Blocks.JUNGLE_LEAVES, EDIBLE_3, 5, 8, 0),
        NECTARINE(Blocks.OAK_LEAVES, EDIBLE_3, 4, 4, 0),
        NUTMEG(Blocks.JUNGLE_LEAVES, EDIBLE_1, 4, 8, 0),
        ORANGE(Blocks.OAK_LEAVES, EDIBLE_3, 4, 4, 0),
        PEACH(Blocks.OAK_LEAVES, EDIBLE_3, 5, 3, 0),
        PEAR(Blocks.OAK_LEAVES, EDIBLE_3, 5, 2, 0),
        PECAN(Blocks.DARK_OAK_LEAVES, EDIBLE_3, 4, 3, 0),
        PERSIMMON(Blocks.OAK_LEAVES, EDIBLE_3, 5, 3, 0),
        PLUM(Blocks.OAK_LEAVES, EDIBLE_3, 5, 3, 0),
        STARFRUIT(Blocks.OAK_LEAVES, EDIBLE_3, 5, 3, 0),
        WALNUT(Blocks.DARK_OAK_LEAVES, EDIBLE_3, 4, 3, 0);

        private Item item;
        private Block leaves;
        private RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> treeGen;
        private CroptopiaSaplingItem sapling;
        private CroptopiaSaplingBlock saplingBlock;

        Tree(Block leafType, FoodComponent foodComponent, int iTreeGen, int jTreeGen, int kTreeGen) {
            String name = name().toLowerCase();
            if (foodComponent == null) {
                item = Items.APPLE;
            }
            else {
                item = new CropItem(createGroup().food(foodComponent));
                Registry.register(Registry.ITEM, Croptopia.createIdentifier(name), item);
            }
            leaves = createLeavesBlock();
            Croptopia.registerBlock(name + "_crop", leaves);
            treeGen = createTreeGen(name + "_tree", iTreeGen, jTreeGen, kTreeGen, leafType, leaves);
            saplingBlock = new CroptopiaSaplingBlock(new CroptopiaSaplingGenerator(() -> treeGen), createSaplingSettings());
            Croptopia.registerBlock(name + "_sapling", saplingBlock);
            sapling = new CroptopiaSaplingItem(saplingBlock, leaves, leafType, createGroup());
            Registry.register(Registry.ITEM, Croptopia.createIdentifier(name + "_sapling"), sapling);
        }

        @Override
        public Item asItem() {
            return item;
        }

        public Block getLeaves() {
            return leaves;
        }

        public RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> getTreeGen() {
            return treeGen;
        }

        public CroptopiaSaplingItem getSapling() {
            return sapling;
        }

        public CroptopiaSaplingBlock getSaplingBlock() {
            return saplingBlock;
        }

    }

    public enum Juice implements ItemConvertible {
        APPLE,
        CRANBERRY,
        GRAPE,
        MELON,
        ORANGE,
        PINEAPPLE,
        SAGUARO,
        TOMATO(false);

        private boolean sweet;
        private Item item;
        private ItemConvertible crop;

        Juice(boolean sweet) {
            this.sweet = sweet; // property not yet used, will be used in upcoming saturation overhaul
            item = new Drink(createGroup().food(EDIBLE_5_BUILDER.alwaysEdible().build()).recipeRemainder(Items.GLASS_BOTTLE));
            Registry.register(Registry.ITEM, Croptopia.createIdentifier(name().toLowerCase() + "_juice"), item);
            crop = findCrop(name());
            if (crop == null) {
                throw new IllegalStateException("Unknown crop source");
            }
        }

        Juice() {
            this(true);
        }

        @Override
        public Item asItem() {
            return item;
        }

        public boolean isSweet() {
            return sweet;
        }

        public ItemConvertible getCrop() {
            return crop;
        }
    }

    public enum Jam implements ItemConvertible {
        APRICOT,
        BLACKBERRY,
        BLUEBERRY,
        CHERRY,
        ELDERBERRY,
        GRAPE,
        PEACH,
        RASPBERRY,
        STRAWBERRY;

        private Item item;
        private ItemConvertible crop;

        Jam() {
            item = new Drink(createGroup().food(EDIBLE_3_BUILDER.alwaysEdible().build()));
            Registry.register(Registry.ITEM, Croptopia.createIdentifier(name().toLowerCase() + "_jam"), item);
            crop = findCrop(name());
            if (crop == null) {
                throw new IllegalStateException("Unknown crop source");
            }
        }

        @Override
        public Item asItem() {
            return item;
        }

        public ItemConvertible getCrop() {
            return crop;
        }
    }

    public enum Smoothie implements ItemConvertible {
        BANANA,
        STRAWBERRY;

        private boolean sweet;
        private Item item;
        private ItemConvertible crop;

        Smoothie(boolean sweet) {
            this.sweet = sweet;
            item = new Drink(createGroup().food(EDIBLE_7_BUILDER.alwaysEdible().build()));
            Registry.register(Registry.ITEM, Croptopia.createIdentifier(name().toLowerCase() + "_smoothie"), item);
            crop = findCrop(name());
            if (crop == null) {
                throw new IllegalStateException("Unknown crop source");
            }
        }

        Smoothie() {
            this(true);
        }

        public boolean isSweet() {
            return sweet;
        }

        @Override
        public Item asItem() {
            return item;
        }

        public ItemConvertible getCrop() {
            return crop;
        }
    }

    public enum IceCream implements ItemConvertible {
        MANGO,
        PECAN,
        STRAWBERRY,
        VANILLA;

        private Item item;
        private ItemConvertible crop;

        IceCream() {
            item = new Item(createGroup().food(EDIBLE_10));
            Registry.register(Registry.ITEM, Croptopia.createIdentifier(name().toLowerCase() + "_ice_cream"), item);
            crop = findCrop(name());
            if (crop == null) {
                throw new IllegalStateException("Unknown crop source");
            }
        }

        @Override
        public Item asItem() {
            return item;
        }

        public ItemConvertible getCrop() {
            return crop;
        }
    }

    public static Stream<Item> createCropStream() {
        return Stream.concat(
                Arrays.stream(Farmland.values()),
                Arrays.stream(Tree.values())
        ).map(ItemConvertible::asItem);
    }

    public static Stream<Block> createLeafStream() {
        return Stream.concat(
                Arrays.stream(Tree.values()).map(Tree::getLeaves),
                Stream.of(LeavesRegistry.cinnamonLeaves)
        );
    }

    public static Item.Settings createGroup() {
        return new Item.Settings().group(Croptopia.CROPTOPIA_ITEM_GROUP);
    }

    public static FabricBlockSettings createCropSettings() {
        return FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP);
    }

    public static FabricBlockSettings createSaplingSettings() {
        return FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS);
    }

    public static LeafCropBlock createLeavesBlock() {
        return new LeafCropBlock(FabricBlockSettings.of(Material.LEAVES).strength(0.2F).ticksRandomly().sounds(BlockSoundGroup.GRASS).nonOpaque().allowsSpawning(Croptopia::canSpawnOnLeaves).suffocates((a,b,c) -> false).blockVision((a,b,c) -> false));
    }

    /**
     * Returns the crop of a specified name.
     * <p>Search order is
     * <ul>
     *     <li>{@link Farmland}</li>
     *     <li>{@link Tree}</li>
     *     <li>Vanilla crops in alphabetical order save for {@link Items#APPLE} because of {@link Tree#APPLE}</li>
     * </ul>
     * </p>
     * @param name the name of the crop to find, in CAPSLOCK with _ separation
     * @return the crop with the given name or <code>null</code> if there is none.
     */
    public static ItemConvertible findCrop(String name) {
        try {
            return Farmland.valueOf(name);
        } catch (IllegalArgumentException ex) {/* try next */}
        try {
            return Tree.valueOf(name);
        } catch (IllegalArgumentException ex) {/* try next */}
        // test vanilla crops
        return switch (name) {
            // note that apple has already been tested for
            case "BEETROOT" -> Items.BEETROOT;
            case "CARROT" -> Items.CARROT;
            case "MELON" -> Items.MELON_SLICE;
            case "POTATO" -> Items.POTATO;
            case "PUMPKIN" -> Items.PUMPKIN;
            case "WHEAT" -> Items.WHEAT;
            default -> null;
        };
    }

    public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> createTreeGen(String name, int i, int j, int k, Block leafType, Block leafCrop) {
        return register(createIdentifier(name),
                Feature.TREE, ((new TreeFeatureConfig.Builder(
                        SimpleBlockStateProvider.of(leafType.getDefaultState()),
                        new StraightTrunkPlacer(i, j, k),
                        new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(leafType.getDefaultState(), 90).add(leafCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                        new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                        new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));
    }

    public static <FC extends FeatureConfig, F extends Feature<FC>> RegistryEntry<ConfiguredFeature<FC, ?>> register(Identifier id, F feature, FC config) {
        return badRegister(BuiltinRegistries.CONFIGURED_FEATURE, id, new ConfiguredFeature<>(feature, config));
    }

    public static <V extends T, T> RegistryEntry<V> badRegister(Registry<T> registry, Identifier id, V value) {
        return BuiltinRegistries.add((Registry<V>) registry, id, value);
    }

    /**
     * {@link Content} is a static class.
     */
    private Content() {/* No instantiation. */}

}
