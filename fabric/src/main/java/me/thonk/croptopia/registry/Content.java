package me.thonk.croptopia.registry;

import me.thonk.common.MiscNames;
import me.thonk.croptopia.Croptopia;
import me.thonk.croptopia.blocks.CroptopiaCropBlock;
import me.thonk.croptopia.blocks.CroptopiaSaplingBlock;
import me.thonk.croptopia.blocks.LeafCropBlock;
import me.thonk.croptopia.generator.CroptopiaSaplingGenerator;
import me.thonk.croptopia.items.*;
import me.thonk.croptopia.util.BlockConvertible;
import me.thonk.croptopia.util.ItemConvertibleWithPlural;
import me.thonk.croptopia.util.NamedPlural;
import me.thonk.croptopia.util.PluralInfo;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

import static me.thonk.croptopia.Croptopia.*;
import static me.thonk.croptopia.registry.FoodRegistry.*;
import static net.minecraft.world.biome.Biome.Category.*;

/**
 * Contains the items and blocks we want and need.
 */
public class Content {

    /**
     * Enum for all commonly used crop categories; always in plural form, if existent.
     */
    public enum TagCategory {

        NONE,
        CROPS,
        FRUITS,
        GRAIN,
        NUTS,
        VEGETABLES;

        String lowerCaseName;

        TagCategory() {
            lowerCaseName = name().toLowerCase();
        }

        public String getLowerCaseName() {
            return lowerCaseName;
        }
    }

    /**
     * Enum for all (Croptopia) farmland crops.
     */
    public enum Farmland implements ItemConvertibleWithPlural, BlockConvertible {
        ARTICHOKE(true, TagCategory.VEGETABLES, REG_1, SWAMP),
        ASPARAGUS(false, TagCategory.VEGETABLES, REG_3, SWAMP),
        BARLEY(false, TagCategory.GRAIN, REG_1, PLAINS, TAIGA),
        BASIL(false, TagCategory.CROPS, REG_1, JUNGLE),
        BELLPEPPER(true, TagCategory.FRUITS, REG_3, PLAINS),
        BLACKBEAN(true, TagCategory.CROPS, REG_3, FOREST),
        BLACKBERRY(true, TagCategory.FRUITS, REG_3, FOREST, TAIGA),
        BLUEBERRY(true, TagCategory.FRUITS, REG_3, FOREST, TAIGA),
        BROCCOLI(false, TagCategory.VEGETABLES, REG_3, PLAINS),
        CABBAGE(false, TagCategory.VEGETABLES, REG_1, PLAINS),
        CANTALOUPE(true, TagCategory.FRUITS, REG_3, FOREST),
        CAULIFLOWER(false, TagCategory.VEGETABLES, REG_3, FOREST),
        CELERY(false, TagCategory.VEGETABLES, REG_3, FOREST),
        CHILE_PEPPER(true, TagCategory.CROPS, REG_3, PLAINS),
        COFFEE_BEANS("coffee", false, TagCategory.CROPS, REG_3, JUNGLE),
        CORN(false, TagCategory.GRAIN, REG_3, PLAINS),
        CRANBERRY(true, TagCategory.FRUITS, REG_3, SWAMP),
        CUCUMBER(true, TagCategory.VEGETABLES, REG_3, PLAINS),
        CURRANT(true, TagCategory.FRUITS, REG_3, SWAMP),
        EGGPLANT(true, TagCategory.VEGETABLES, REG_3, JUNGLE),
        ELDERBERRY(true, TagCategory.FRUITS, REG_3, FOREST),
        GARLIC(false, TagCategory.VEGETABLES, REG_1, JUNGLE),
        GINGER(true, TagCategory.VEGETABLES, null, SAVANNA),
        GRAPE(true, TagCategory.FRUITS, REG_3, FOREST),
        GREENBEAN(true, TagCategory.VEGETABLES, REG_3, PLAINS),
        GREENONION(true, TagCategory.VEGETABLES, REG_1, JUNGLE),
        HONEYDEW(false, TagCategory.FRUITS, REG_3, JUNGLE),
        HOPS(false, TagCategory.CROPS, null, SAVANNA),
        KALE(false, TagCategory.VEGETABLES, REG_3, PLAINS),
        KIWI(true, TagCategory.FRUITS, REG_3, SAVANNA),
        LEEK(false, TagCategory.VEGETABLES, REG_3, SAVANNA),
        LETTUCE(false, TagCategory.VEGETABLES, REG_3, PLAINS),
        MUSTARD(false, TagCategory.VEGETABLES, null, PLAINS),
        OAT(false, TagCategory.GRAIN, REG_1, PLAINS),
        OLIVE(true, TagCategory.FRUITS, REG_3, SAVANNA),
        ONION(true, TagCategory.VEGETABLES, REG_3, JUNGLE),
        PEANUT(true, TagCategory.CROPS, REG_1, JUNGLE),
        PEPPER(false, TagCategory.CROPS, null, PLAINS),
        PINEAPPLE(true, TagCategory.FRUITS, REG_3, JUNGLE),
        RADISH(true, TagCategory.VEGETABLES, REG_3, FOREST),
        RASPBERRY(true, TagCategory.FRUITS, REG_3, FOREST, TAIGA),
        RHUBARB(false, TagCategory.VEGETABLES, REG_3, JUNGLE),
        RICE(false, TagCategory.GRAIN, REG_1, JUNGLE),
        RUTABAGA(true, TagCategory.VEGETABLES, REG_3, SAVANNA, TAIGA),
        SAGUARO(true, TagCategory.FRUITS, REG_3, DESERT),
        SOYBEAN(true, TagCategory.VEGETABLES, REG_1, PLAINS),
        SPINACH(false, TagCategory.VEGETABLES, REG_3, FOREST),
        SQUASH(true, TagCategory.VEGETABLES, REG_3, SAVANNA, TAIGA),
        STRAWBERRY(true, TagCategory.FRUITS, REG_3, FOREST, TAIGA),
        SWEETPOTATO(true, TagCategory.VEGETABLES, REG_3, PLAINS),
        TEA_LEAVES("tea", false, TagCategory.CROPS, null, FOREST),
        TOMATILLO(true, TagCategory.VEGETABLES, REG_3, FOREST),
        TOMATO(true, TagCategory.VEGETABLES, REG_3, FOREST),
        TURMERIC(false, TagCategory.CROPS, null, SAVANNA),
        TURNIP(true, TagCategory.VEGETABLES, REG_3, JUNGLE),
        VANILLA(false,TagCategory.CROPS, null, JUNGLE),
        YAM(true, TagCategory.VEGETABLES, REG_3, SAVANNA),
        ZUCCHINI(false, TagCategory.VEGETABLES, REG_3, SAVANNA);

        private String lowerCaseName;
        private boolean hasPlural;
        private TagCategory tagegory;
        private Item item;
        private Block block;
        private SeedItem seed;

        /**
         * Creates a new farmland crop enum instance.
         * @param shortNameVariant A variant of this entry's name that is used for suffixes instead of its name. Optional, see {@link #Farmland(boolean, TagCategory, FoodRegistry, Biome.Category...)}.
         * @param hasPlural <code>false</code> indicates that the plural of this enum entry is the same as singular
         * @param tagegory The {@link TagCategory} of this crop, not <code>null</code>.
         * @param foodRegistry Hunger/Saturation value for this crop. <code>null</code> means cannot be eaten.
         * @param biomes Which biomes this crop can be found in. Not supplying at least one biome leads to undefined behaviour.
         * @throws NullPointerException If <code>tagegory</code> refer to <code>null</code>.
         */
        Farmland(String shortNameVariant, boolean hasPlural, TagCategory tagegory, FoodRegistry foodRegistry, Biome.Category... biomes) {
            Objects.requireNonNull(tagegory);
            lowerCaseName = name().toLowerCase();
            this.hasPlural = hasPlural;
            this.tagegory = tagegory;
            if (foodRegistry == null) {
                item = new CropItem(createGroup());
            }
            else {
                item = new CropItem(createGroup().food(createComponent(foodRegistry)));
            }
            Registry.register(Registry.ITEM, Croptopia.createIdentifier(lowerCaseName), item);
            block = new CroptopiaCropBlock(createCropSettings());
            Croptopia.registerBlock((shortNameVariant != null ? shortNameVariant : lowerCaseName) + "_crop", block);
            seed = new SeedItem(block, createGroup(), biomes);
            Croptopia.registerItem((shortNameVariant != null ? shortNameVariant : lowerCaseName) + "_seed", seed);
        }

        /**
         * @see #Farmland(String, boolean, TagCategory, FoodRegistry, Biome.Category...)
         */
        Farmland(boolean hasPlural, TagCategory tagegory, FoodRegistry foodRegistry, Biome.Category... biomes) {
            this(null, hasPlural, tagegory, foodRegistry, biomes);
        }

        public String getLowerCaseName() {
            return lowerCaseName;
        }

        @Override
        public boolean hasPlural() {
            return hasPlural;
        }

        public TagCategory getTagegory() {
            return tagegory;
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
     * Enum for all (Croptopia) tree crops. Don't confuse with {@link Bark}.
     * <p>
     * Does include {@link Items#APPLE} as {@link Tree#APPLE} (access via {@link Tree#asItem()}).
     * Does not include cinnamon, that one is {@link Bark#CINNAMON}.
     * </p>
     */
    public enum Tree implements ItemConvertibleWithPlural, BlockConvertible {
        ALMOND(true,  Blocks.DARK_OAK_LOG, Blocks.DARK_OAK_LEAVES, TagCategory.NUTS, REG_3, 4, 3, 0),
        // coding for apple requires null for food registry here, other fruits must be eatable
        APPLE(true, Blocks.OAK_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, null, 5, 3, 0),
        APRICOT(true, Blocks.OAK_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, REG_3, 5, 2, 0),
        AVOCADO(true, Blocks.SPRUCE_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, REG_3, 5, 3, 0),
        BANANA(true, Blocks.JUNGLE_LOG, Blocks.JUNGLE_LEAVES, TagCategory.FRUITS, REG_3, 4, 8, 0),
        CASHEW(true, Blocks.DARK_OAK_LOG, Blocks.DARK_OAK_LEAVES, TagCategory.CROPS, REG_1, 4, 3, 0),
        CHERRY(true, Blocks.OAK_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, REG_3, 5, 3, 0),
        COCONUT(true, Blocks.JUNGLE_LOG, Blocks.JUNGLE_LEAVES, TagCategory.FRUITS, REG_1, 5, 2, 3),
        DATE(true, Blocks.JUNGLE_LOG, Blocks.JUNGLE_LEAVES, TagCategory.FRUITS, REG_3, 5, 8, 0),
        DRAGONFRUIT(true, Blocks.JUNGLE_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, REG_3, 5, 7, 0),
        FIG(true, Blocks.JUNGLE_LOG, Blocks.JUNGLE_LEAVES, TagCategory.FRUITS, REG_3, 4, 8, 0),
        GRAPEFRUIT(true, Blocks.JUNGLE_LOG, Blocks.JUNGLE_LEAVES, TagCategory.FRUITS, REG_3, 4, 8, 0),
        KUMQUAT(false, Blocks.JUNGLE_LOG, Blocks.JUNGLE_LEAVES, TagCategory.FRUITS, REG_3, 4, 8, 0),
        LEMON(true, Blocks.OAK_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, REG_3, 5, 3, 0),
        LIME(true, Blocks.OAK_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, REG_3, 5, 2, 0),
        MANGO(true, Blocks.JUNGLE_LOG, Blocks.JUNGLE_LEAVES, TagCategory.FRUITS, REG_3, 5, 8, 0),
        NECTARINE(true, Blocks.OAK_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, REG_3, 4, 4, 0),
        NUTMEG(true, Blocks.JUNGLE_LOG, Blocks.JUNGLE_LEAVES, TagCategory.CROPS, REG_1, 4, 8, 0),
        ORANGE(true, Blocks.OAK_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, REG_3, 4, 4, 0),
        PEACH(true, Blocks.OAK_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, REG_3, 5, 3, 0),
        PEAR(true, Blocks.OAK_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, REG_3, 5, 2, 0),
        PECAN(true, Blocks.DARK_OAK_LOG, Blocks.DARK_OAK_LEAVES, TagCategory.NUTS, REG_3, 4, 3, 0),
        PERSIMMON(true, Blocks.OAK_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, REG_3, 5, 3, 0),
        PLUM(true, Blocks.OAK_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, REG_3, 5, 3, 0),
        STARFRUIT(true, Blocks.OAK_LOG, Blocks.OAK_LEAVES, TagCategory.FRUITS, REG_3, 5, 3, 0),
        WALNUT(true, Blocks.DARK_OAK_LOG, Blocks.DARK_OAK_LEAVES, TagCategory.NUTS, REG_3, 4, 3, 0);

        private String lowerCaseName;
        private boolean hasPlural;
        private TagCategory tagegory;
        private Item item;
        private Block leaves;
        private RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> treeGen;
        private CroptopiaSaplingItem sapling;
        private CroptopiaSaplingBlock saplingBlock;

        /**
         * Creates a new tree crop enum instance.
         * @param hasPlural <code>false</code> indicates that the plural of this enum entry is the same as singular
         * @param leafType The type of leaves to use for this crop, not <code>null</code>. Beware that it isn't checked if the given block is really a leaf block.
         * @param tagegory The {@link TagCategory} of this crop, not <code>null</code>.
         * @param foodRegistry Hunger/Saturation value for this crop. <code>null</code> means this is the tree crop for {@link Items#APPLE}.
         * @param iTreeGen First param for {@link StraightTrunkPlacer#StraightTrunkPlacer(int, int, int)}.
         * @param jTreeGen Second param for {@link StraightTrunkPlacer#StraightTrunkPlacer(int, int, int)}.
         * @param kTreeGen Third param for {@link StraightTrunkPlacer#StraightTrunkPlacer(int, int, int)}.
         * @throws NullPointerException If <code>leafType</code> or <code>tagegory</code> refer to <code>null</code>.
         */
        Tree(boolean hasPlural, Block logType, Block leafType, TagCategory tagegory, FoodRegistry foodRegistry, int iTreeGen, int jTreeGen, int kTreeGen) {
            Objects.requireNonNull(leafType);
            Objects.requireNonNull(tagegory);
            lowerCaseName = name().toLowerCase();
            this.hasPlural = hasPlural;
            this.tagegory = tagegory;
            if (foodRegistry == null) {
                item = Items.APPLE;
            }
            else {
                item = new CropItem(createGroup().food(createComponent(foodRegistry)));
                Registry.register(Registry.ITEM, Croptopia.createIdentifier(lowerCaseName), item);
            }
            leaves = createLeavesBlock();
            Croptopia.registerBlock(lowerCaseName + "_crop", leaves);
            treeGen = createTreeGen(lowerCaseName + "_tree", iTreeGen, jTreeGen, kTreeGen, logType, leafType, leaves);
            saplingBlock = new CroptopiaSaplingBlock(new CroptopiaSaplingGenerator(() -> treeGen), createSaplingSettings());
            Croptopia.registerBlock(lowerCaseName + "_sapling", saplingBlock);
            sapling = new CroptopiaSaplingItem(saplingBlock, leaves, leafType, createGroup());
            Registry.register(Registry.ITEM, Croptopia.createIdentifier(lowerCaseName + "_sapling"), sapling);
        }

        public String getLowerCaseName() {
            return lowerCaseName;
        }

        @Override
        public boolean hasPlural() {
            return hasPlural;
        }

        public TagCategory getTagegory() {
            return tagegory;
        }

        @Override
        public Item asItem() {
            return item;
        }

        @Override
        public Block asBlock() {
            return leaves;
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

    /**
     * Enum for all (Croptopia) bark crops. Don't confuse with {@link Tree}.
     */
    public enum Bark implements ItemConvertibleWithPlural, BlockConvertible {
        CINNAMON(false, TagCategory.CROPS, 4, 3, 0);

        private String lowerCaseName;
        private boolean hasPlural;
        private TagCategory tagegory;
        private Item item;
        private Block log;
        private Block strippedLog;
        private Block wood;
        private Block strippedWood;
        private TagKey<Item> logItemTag;
        private TagKey<Block> logBlockTag;
        private Block leaves;
        private RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> treeGen;
        private Item sapling;
        private Block saplingBlock;

        Bark(boolean hasPlural, TagCategory tagegory, int iTreeGen, int jTreeGen, int kTreeGen) {
            Objects.requireNonNull(tagegory);
            this.hasPlural = hasPlural;
            this.tagegory = tagegory;
            lowerCaseName = name().toLowerCase();
            item = new Item(createGroup());
            Registry.register(Registry.ITEM, Croptopia.createIdentifier(lowerCaseName), item);
            // in the following we use registerItem because of AliasedBlockItem
            log = new PillarBlock(FabricBlockSettings.of(Material.WOOD, MapColor.BROWN).sounds(BlockSoundGroup.WOOD).strength(2.0F));
            registerBlock(lowerCaseName + "_log", log);
            registerItem(lowerCaseName + "_log", new AliasedBlockItem(log, createGroup()));
            strippedLog = new PillarBlock(FabricBlockSettings.of(Material.WOOD, MapColor.BROWN).sounds(BlockSoundGroup.WOOD).strength(2.0F));
            registerBlock("stripped_" + lowerCaseName + "_log", strippedLog);
            registerItem("stripped_" + lowerCaseName + "_log", new AliasedBlockItem(strippedLog, createGroup()));
            wood = new PillarBlock(FabricBlockSettings.of(Material.WOOD, MapColor.BROWN).sounds(BlockSoundGroup.WOOD).strength(2.0F));
            registerBlock(lowerCaseName + "_wood", wood);
            registerItem(lowerCaseName + "_wood", new AliasedBlockItem(wood, createGroup()));
            strippedWood = new PillarBlock(FabricBlockSettings.of(Material.WOOD, MapColor.BROWN).sounds(BlockSoundGroup.WOOD).strength(2.0F));
            registerBlock("stripped_" + lowerCaseName + "_wood", strippedWood);
            registerItem("stripped_" + lowerCaseName + "_wood", new AliasedBlockItem(strippedWood, createGroup()));
            // create the tags (will be filled by datagen)
            String tagName = lowerCaseName + "_logs";
            logItemTag = TagKey.of(Registry.ITEM_KEY, new Identifier(MiscNames.MOD_ID, tagName));
            logBlockTag = TagKey.of(Registry.BLOCK_KEY, new Identifier(MiscNames.MOD_ID, tagName));
            // left is leaves and saplings
            leaves = createRegularLeavesBlock();
            registerBlock(lowerCaseName + "_leaves", leaves);
            treeGen = createBarkGen(lowerCaseName + "_tree", iTreeGen, jTreeGen, kTreeGen, log, leaves);
            saplingBlock = new CroptopiaSaplingBlock(new CroptopiaSaplingGenerator(() -> treeGen), Content.createSaplingSettings());
            registerBlock(lowerCaseName + "_sapling", saplingBlock);
            sapling = new AliasedBlockItem(saplingBlock, createGroup());
            registerItem(lowerCaseName + "_sapling", sapling);
        }

        public String getLowerCaseName() {
            return lowerCaseName;
        }

        @Override
        public boolean hasPlural() {
            return hasPlural;
        }

        public TagCategory getTagegory() {
            return tagegory;
        }

        @Override
        public Item asItem() {
            return item;
        }

        @Override
        public Block asBlock() {
            return log;
        }

        public Block getLog() {
            return log;
        }

        public Block getStrippedLog() {
            return strippedLog;
        }

        public Block getWood() {
            return wood;
        }

        public Block getStrippedWood() {
            return strippedWood;
        }

        public TagKey<Item> getLogItemTag() {
            return logItemTag;
        }

        public TagKey<Block> getLogBlockTag() {
            return logBlockTag;
        }

        public Block getLeaves() {
            return leaves;
        }

        public Item getSapling() {
            return sapling;
        }

        public Block getSaplingBlock() {
            return saplingBlock;
        }

        public RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> getTreeGen() {
            return treeGen;
        }
    }

    /**
     * Creates a stream iterating through the item forms of {@link Farmland}, {@link Tree} and {@link Bark} in that order.
     * Does NOT include {@link VanillaCrops} safe for {@link Tree#APPLE}.
     * @return not <code>null</code>
     */
    public static Stream<Item> createCropStream() {
        return Stream.concat(
                Arrays.stream(Farmland.values()),
                Stream.concat(Arrays.stream(Tree.values()), Arrays.stream(Bark.values()))
        ).map(ItemConvertible::asItem);
    }

    /**
     * Creates a stream iterating through the leaf forms of {@link Tree} and {@link Bark} in that order.
     * @return not <code>null</code>
     */
    public static Stream<Block> createLeafStream() {
        return Stream.concat(
                Arrays.stream(Tree.values()).map(Tree::getLeaves),
                Arrays.stream(Bark.values()).map(Bark::getLeaves)
        );
    }

    /**
     * Enum of vanilla crops for search and automation purposes.
     */
    public enum VanillaCrops implements ItemConvertibleWithPlural {
        APPLE(Items.APPLE),
        BEETROOT(Items.BEETROOT),
        CARROT(Items.CARROT),
        MELON(Items.MELON_SLICE),
        POTATO(Items.POTATO),
        PUMPKIN(Items.PUMPKIN),
        WHEAT(Items.WHEAT);

        private Item item;

        /**
         * @param source the vanilla crop, not <code>null</code>
         */
        VanillaCrops(ItemConvertible source) {
            Objects.requireNonNull(source);
            this.item = source.asItem();
        }

        @Override
        public Item asItem() {
            return item;
        }

        @Override
        public boolean hasPlural() {
            return true;
        }
    }

    /**
     * Enum for all "generic" (Croptopia) juices.
     */
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
        private ItemConvertibleWithPlural crop;

        /**
         * @param sweet If this juice is "sweet" (i.e. sugary) or "not sweet" (i.e. healthy from vegetable or something).
         */
        Juice(boolean sweet) {
            this.sweet = sweet; // property not yet used, will be used in upcoming saturation overhaul
            item = new Drink(createGroup().food(createBuilder(REG_5).alwaysEdible().build()).recipeRemainder(Items.GLASS_BOTTLE));
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

        public ItemConvertibleWithPlural getCrop() {
            return crop;
        }
    }

    /**
     * Enum for all "generic" (Croptopia) jams.
     */
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
        private ItemConvertibleWithPlural crop;

        Jam() {
            item = new Drink(createGroup().food(createBuilder(REG_3).alwaysEdible().build()));
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

        public ItemConvertibleWithPlural getCrop() {
            return crop;
        }
    }

    /**
     * Enum for all "generic" (Croptopia) smoothies.
     */
    public enum Smoothie implements ItemConvertible {
        BANANA,
        STRAWBERRY;

        private boolean sweet;
        private Item item;
        private ItemConvertibleWithPlural crop;

        /**
         * @param sweet If this smoothie is "sweet" (i.e. sugary) or "not sweet" (i.e. healthy from vegetable or something). Defaults to <code>true</code>, see {@link #Smoothie()}.
         */
        Smoothie(boolean sweet) {
            this.sweet = sweet;
            item = new Drink(createGroup().food(createBuilder(REG_7).alwaysEdible().build()));
            Registry.register(Registry.ITEM, Croptopia.createIdentifier(name().toLowerCase() + "_smoothie"), item);
            crop = findCrop(name());
            if (crop == null) {
                throw new IllegalStateException("Unknown crop source");
            }
        }

        /**
         * Creates a sweet smoothie.
         */
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

        public ItemConvertibleWithPlural getCrop() {
            return crop;
        }
    }

    /**
     * Enum for all "generic" (Croptopia) ice creams.
     */
    public enum IceCream implements ItemConvertible {
        MANGO,
        PECAN,
        STRAWBERRY,
        VANILLA;

        private Item item;
        private ItemConvertibleWithPlural crop;

        IceCream() {
            item = new Item(createGroup().food(createComponent(REG_10)));
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

        public ItemConvertibleWithPlural getCrop() {
            return crop;
        }
    }

    /**
     * Enum for all "generic" (Croptopia) pies.
     */
    public enum Pie implements ItemConvertible {
        APPLE,
        CHERRY,
        PECAN,
        RHUBARB;

        private Item item;
        private ItemConvertibleWithPlural crop;

        Pie() {
            item = new Item(createGroup().food(createComponent(REG_14)));
            Registry.register(Registry.ITEM, Croptopia.createIdentifier(name().toLowerCase() + "_pie"), item);
            crop = findCrop(name());
            if (crop == null) {
                throw new IllegalStateException("Unknown crop source");
            }
        }

        @Override
        public Item asItem() {
            return item;
        }

        public ItemConvertibleWithPlural getCrop() {
            return crop;
        }
    }

    /**
     * Enum for all (Croptopia) cooking utensils
     */
    public enum Utensil implements ItemConvertibleWithPlural {
        COOKING_POT(true),
        FOOD_PRESS(false),
        FRYING_PAN(true),
        KNIFE(true),
        MORTAR_AND_PESTLE(true);

        private boolean hasPlural;
        private Item item;

        Utensil(boolean hasPlural) {
            this.hasPlural = hasPlural;
            item = new CookingUtensil(createGroup().maxCount(1));
            Registry.register(Registry.ITEM, Croptopia.createIdentifier(name().toLowerCase()), item);
        }

        @Override
        public boolean hasPlural() {
            return hasPlural;
        }

        @Override
        public Item asItem() {
            return item;
        }
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
     *     <li>{@link Bark}</li>
     *     <li>{@link VanillaCrops}</li>
     * </ul>
     * Note that {@link Items#APPLE} will always be matched via {@link Tree} and never via {@link VanillaCrops}.
     * </p>
     * @param name the name of the crop to find, in CAPSLOCK with _ separation
     * @return the crop with the given name or <code>null</code> if there is none.
     */
    public static ItemConvertibleWithPlural findCrop(String name) {
        try {
            return Farmland.valueOf(name);
        } catch (IllegalArgumentException ex) {/* try next */}
        try {
            return Tree.valueOf(name);
        } catch (IllegalArgumentException ex) {/* try next */}
        try {
            return Bark.valueOf(name);
        } catch (IllegalArgumentException ex) {/* try next */}
        try {
            return VanillaCrops.valueOf(name);
        } catch (IllegalArgumentException ex) {/* uhm... */}
        return null;
    }

    public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> createTreeGen(String name, int i, int j, int k, Block logType, Block leafType, Block leafCrop) {
        return register(createIdentifier(name),
                Feature.TREE, ((new TreeFeatureConfig.Builder(
                        SimpleBlockStateProvider.of(logType.getDefaultState()),
                        new StraightTrunkPlacer(i, j, k),
                        new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(leafType.getDefaultState(), 90).add(leafCrop.getDefaultState().with(LeafCropBlock.AGE, 3), 20).build()),
                        new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                        new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));
    }

    public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> createBarkGen(String name, int i, int j, int k, Block log, Block leaves) {
        return register(createIdentifier(name),
                Feature.TREE, ((new TreeFeatureConfig.Builder(
                        SimpleBlockStateProvider.of(log.getDefaultState()),
                        new StraightTrunkPlacer(i, j, k),
                        new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(leaves.getDefaultState(), 90).build()),
                        new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                        new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));
    }

    public static <FC extends FeatureConfig, F extends Feature<FC>> RegistryEntry<ConfiguredFeature<FC, ?>> register(Identifier id, F feature, FC config) {
        return badRegister(BuiltinRegistries.CONFIGURED_FEATURE, id, new ConfiguredFeature<>(feature, config));
    }

    public static <V extends T, T> RegistryEntry<V> badRegister(Registry<T> registry, Identifier id, V value) {
        //noinspection unchecked
        return BuiltinRegistries.add((Registry<V>) registry, id, value);
    }

    /**
     * {@link Content} is a static class.
     */
    private Content() {/* No instantiation. */}

}
