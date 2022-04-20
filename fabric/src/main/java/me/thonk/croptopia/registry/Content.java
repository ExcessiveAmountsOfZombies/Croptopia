package me.thonk.croptopia.registry;

import me.thonk.common.BlockNames;
import me.thonk.common.ItemNames;
import me.thonk.common.MiscNames;
import me.thonk.croptopia.Croptopia;
import me.thonk.croptopia.blocks.CroptopiaCropBlock;
import me.thonk.croptopia.blocks.CroptopiaSaplingBlock;
import me.thonk.croptopia.blocks.LeafCropBlock;
import me.thonk.croptopia.generator.CroptopiaSaplingGenerator;
import me.thonk.croptopia.items.*;
import me.thonk.croptopia.util.BlockConvertible;
import me.thonk.croptopia.util.ItemConvertibleWithPlural;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
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
import static me.thonk.croptopia.Croptopia.createGroup;
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
            if (this.name().equals("VANILLA")) {
                Croptopia.registerItem((shortNameVariant != null ? shortNameVariant : lowerCaseName) + "_seeds", seed);
            } else {
                Croptopia.registerItem((shortNameVariant != null ? shortNameVariant : lowerCaseName) + "_seed", seed);
            }
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
     * Enum of all vanilla crops for search and automation purposes.
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
     * Enum for all (Croptopia) raw seafood.
     */
    public enum Seafood implements ItemConvertibleWithPlural {
        ANCHOVY(true, REG_1),
        CALAMARI(false, REG_1),
        CLAM(true, REG_3),
        CRAB(true, REG_1),
        GLOWING_CALAMARI(false, REG_3),
        OYSTER(true, REG_3),
        ROE(false, REG_1),
        SHRIMP(false, REG_1),
        TUNA(false, REG_3);

        private boolean hasPlural;
        private Item item;

        Seafood(boolean hasPlural, FoodRegistry foodRegistry) {
            this.hasPlural = hasPlural;
            if (name().contains("GLOWING")) {
                item = new Item(createGroup().food(FoodRegistry.createBuilder(foodRegistry)
                        .statusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 4000, 1), 1.0F).build()));
            }
            else {
                item = new Item(createGroup().food(FoodRegistry.createComponent(foodRegistry)));
            }
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

    /**
     * Enum for all furnace products of Croptopia except salt.
     */
    public enum Furnace implements ItemConvertibleWithPlural {
        BAKED_BEANS(false, REG_5),
        BAKED_SWEET_POTATO(true, REG_7),
        BAKED_YAM(true, REG_7),
        CARAMEL(false, null),
        COOKED_ANCHOVY(true, REG_4),
        COOKED_BACON(false, REG_7),
        COOKED_CALAMARI(false, REG_5),
        COOKED_SHRIMP(false, REG_5),
        COOKED_TUNA(false, REG_6),
        MOLASSES(false, null),
        POPCORN(false, REG_3),
        RAISINS(false, REG_5),
        TOAST(true, REG_7);

        private boolean hasPlural;
        private Item item;

        Furnace(boolean hasPlural, FoodRegistry foodRegistry) {
            this.hasPlural = hasPlural;
            if (foodRegistry == null) {
                item = new Item(createGroup());
            }
            else {
                item = new Item(createGroup().food(FoodRegistry.createComponent(foodRegistry)));
            }
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

    // Spices
    public static final Item PAPRIKA = new Item(createGroup()); // TODO need recipe to make paprika in future update
    public static final Item SALT = new Item(createGroup());

    // secondary ingredients?
    public static final Item OLIVE_OIL = new Item(createGroup());
    public static final Item CHEESE = new Item(createGroup().food(FoodRegistry.createComponent(REG_5)));
    public static final Item FLOUR = new Item(createGroup());
    public static final Item BUTTER = new Item(createGroup().food(FoodRegistry.createComponent(REG_3)));
    public static final Item NOODLE = new Item(createGroup());
    public static final Item TOFU = new Item(createGroup().food(FoodRegistry.createComponent(REG_5)));
    public static final Item CHOCOLATE = new Item(createGroup().food(FoodRegistry.createComponent(REG_5)));
    public static final Item TORTILLA = new Item(createGroup().food(FoodRegistry.createComponent(REG_5)));
    public static final Item SOY_SAUCE = new Item(createGroup());
    public static final Item DOUGH = new Item(createGroup());
    public static final Item RAVIOLI = new Item(createGroup());
    public static final Item SALSA = new Item(createGroup().food(FoodRegistry.createComponent(REG_5)));
    public static final Item ARTICHOKE_DIP = new Item(createGroup().food(FoodRegistry.createComponent(REG_5)));
    public static final Item PEPPERONI = new Item(createGroup().food(FoodRegistry.createComponent(REG_5)));

    // drinks
    public static final Item COFFEE = new Drink(createGroup().food(FoodRegistry.createBuilder(REG_5).alwaysEdible().build()).recipeRemainder(Items.GLASS_BOTTLE));
    public static final Item LEMONADE = new Drink(createGroup().food(FoodRegistry.createBuilder(REG_5).alwaysEdible().build()).recipeRemainder(Items.GLASS_BOTTLE));
    public static final Item LIMEADE = new Drink(createGroup().food(FoodRegistry.createBuilder(REG_5).alwaysEdible().build()).recipeRemainder(Items.GLASS_BOTTLE));
    public static final Item SOY_MILK = new Drink(createGroup().food(FoodRegistry.createBuilder(REG_5).alwaysEdible().build()).recipeRemainder(Items.GLASS_BOTTLE));

    public static final Item KALE_SMOOTHIE = new Drink(createGroup().food(FoodRegistry.createBuilder(REG_14).alwaysEdible().build()));
    public static final Item FRUIT_SMOOTHIE = new Drink(createGroup().food(FoodRegistry.createBuilder(REG_7).alwaysEdible().build()));

    public static final Item CHOCOLATE_MILKSHAKE = new Drink(createGroup().food(FoodRegistry.createBuilder(REG_7).alwaysEdible().build()));

    public static final Item BEER = new Drink(createGroup().food(FoodRegistry.createBuilder(REG_7).alwaysEdible().build()));
    public static final Item WINE = new Drink(createGroup().food(FoodRegistry.createBuilder(REG_7).alwaysEdible().build()));
    public static final Item MEAD = new Drink(createGroup().food(FoodRegistry.createBuilder(REG_7).alwaysEdible().build()));
    public static final Item RUM = new Drink(createGroup().food(FoodRegistry.createBuilder(REG_7).alwaysEdible().build()).recipeRemainder(Items.GLASS_BOTTLE));
    public static final Item PUMPKIN_SPICE_LATTE = new Drink(createGroup().food(FoodRegistry.createBuilder(REG_14).alwaysEdible().build()));

    // snacks?
    public static final Item BEEF_JERKY = new Item(createGroup().food(FoodRegistry.createComponent(REG_5)));
    public static final Item PORK_JERKY = new Item(createGroup().food(FoodRegistry.createComponent(REG_5)));
    public static final Item KALE_CHIPS = new Item(createGroup().food(FoodRegistry.createComponent(REG_5)));
    public static final Item POTATO_CHIPS = new Item(createGroup().food(FoodRegistry.createComponent(REG_5)));
    public static final Item STEAMED_RICE = new Item(createGroup().food(FoodRegistry.createComponent(REG_5)));
    public static final Item FRENCH_FRIES = new Item(createGroup().food(FoodRegistry.createComponent(REG_5)));
    public static final Item SWEET_POTATO_FRIES = new Item(createGroup().food(FoodRegistry.createComponent(REG_5)));
    public static final Item ONION_RINGS = new Item(createGroup().food(FoodRegistry.createComponent(REG_5)));
    public static final Item DOUGHNUT = new Item(createGroup().food(FoodRegistry.createComponent(REG_5)));
    public static final Item CUCUMBER_SALAD = new SoupItem(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item CAESAR_SALAD = new SoupItem(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item LEAFY_SALAD = new SoupItem(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item FRUIT_SALAD = new SoupItem(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item VEGGIE_SALAD = new SoupItem(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item PORK_AND_BEANS = new SoupItem(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item OATMEAL = new SoupItem(createGroup().food(FoodRegistry.createComponent(REG_7)));
    public static final Item LEEK_SOUP = new SoupItem(createGroup().food(FoodRegistry.createComponent(REG_7)));
    public static final Item YOGHURT = new SoupItem(createGroup().food(FoodRegistry.createComponent(REG_5)));
    public static final Item SAUCY_CHIPS = new SoupItem(createGroup().food(FoodRegistry.createComponent(REG_7)));
    public static final Item ROASTED_NUTS = new Item(createGroup().food(FoodRegistry.createComponent(REG_5)));
    public static final Item TRAIL_MIX = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item PROTEIN_BAR = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item NOUGAT = new Item(createGroup().food(FoodRegistry.createComponent(REG_7)));

    // breakfast
    public static final Item SCRAMBLED_EGGS = new Item(createGroup().food(FoodRegistry.createComponent(REG_7)));
    public static final Item BUTTERED_TOAST = new Item(createGroup().food(FoodRegistry.createComponent(REG_9)));
    public static final Item TOAST_WITH_JAM = new Item(createGroup().food(FoodRegistry.createComponent(REG_9)));


    // meals
    public static final Item HAM_SANDWICH = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item PEANUT_BUTTER_AND_JAM = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item BLT = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item GRILLED_CHEESE = new Item(createGroup().food(FoodRegistry.createComponent(REG_7)));
    public static final Item TUNA_SANDWICH = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item CHEESEBURGER = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item HAMBURGER = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item TOFU_BURGER = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item PIZZA = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item SUPREME_PIZZA = new Item(createGroup().food(FoodRegistry.createComponent(REG_18)));
    public static final Item CHEESE_PIZZA = new Item(createGroup().food(FoodRegistry.createComponent(REG_14)));
    public static final Item PINEAPPLE_PEPPERONI_PIZZA = new Item(createGroup().food(FoodRegistry.createComponent(REG_18)));
    public static final Item LEMON_CHICKEN = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item FRIED_CHICKEN = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item CHICKEN_AND_NOODLES = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item CHICKEN_AND_DUMPLINGS = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item TOFU_AND_DUMPLINGS = new Item(createGroup().food(FoodRegistry.createComponent(REG_14)));
    public static final Item SPAGHETTI_SQUASH = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item CHICKEN_AND_RICE = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item TACO = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item SUSHI = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item EGG_ROLL = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item CASHEW_CHICKEN = new Item(createGroup().food(FoodRegistry.createComponent(REG_14)));

    // desert block?
    //public static final Item coffeeCake;
    //public static final Item chocolateCake;
    //public static final Item strawberryShortCake;
    //public static final Item carrotCake;
    //public static final Item turtleCake;

    // desert item
    public static final Item YAM_JAM = new Item(createGroup().food(FoodRegistry.createComponent(REG_14)));
    public static final Item BANANA_CREAM_PIE = new Item(createGroup().food(FoodRegistry.createComponent(REG_14)));
    public static final Item CANDY_CORN = new Item(createGroup().food(FoodRegistry.createComponent(REG_5)));
    public static final Item RUM_RAISIN_ICE_CREAM = new Item(createGroup().food(FoodRegistry.createComponent(REG_14)));
    public static final Item CHEESE_CAKE = new Item(createGroup().food(FoodRegistry.createComponent(REG_14)));
    public static final Item BROWNIES = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item SNICKER_DOODLE = new Item(createGroup().food(FoodRegistry.createComponent(REG_7)));
    public static final Item BANANA_NUT_BREAD = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item CANDIED_NUTS = new Item(createGroup().food(FoodRegistry.createComponent(REG_5)));
    public static final Item ALMOND_BRITTLE = new Item(createGroup().food(FoodRegistry.createComponent(REG_7)));
    public static final Item OATMEAL_COOKIE = new Item(createGroup().food(FoodRegistry.createComponent(REG_5)));
    public static final Item NUTTY_COOKIE = new Item(createGroup().food(FoodRegistry.createComponent(REG_5)));
    //public static final Item praline = new Item(createGroup().food(EDIBLE_5));

    public static final Item BURRITO = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item TOSTADA = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item HORCHATA = new Drink(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item CARNITAS = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item FAJITAS = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item ENCHILADA = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item CHURROS = new Item(createGroup().food(FoodRegistry.createComponent(REG_5)));
    public static final Item TAMALES = new Item(createGroup().food(FoodRegistry.createComponent(REG_14)));
    public static final Item TRES_LECHE_CAKE = new Item(createGroup().food(FoodRegistry.createComponent(REG_18)));
    public static final Item STUFFED_POBLANOS = new Item(createGroup().food(FoodRegistry.createComponent(REG_14)));
    public static final Item CHILI_RELLENO = new Item(createGroup().food(FoodRegistry.createComponent(REG_14)));
    public static final Item CREMA = new Item(createGroup().food(FoodRegistry.createComponent(REG_3)));
    public static final Item REFRIED_BEANS = new Item(createGroup().food(FoodRegistry.createComponent(REG_7)));
    public static final Item CHIMICHANGA = new Item(createGroup().food(FoodRegistry.createComponent(REG_14)));
    public static final Item QUESADILLA = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));

    public static final Item CORN_HUSK = new Item(createGroup());
    public static final Item WHIPPING_CREAM = new Item(createGroup());

    // 1.4.0
    public static final Item SHEPHERDS_PIE = new Item(createGroup().food(FoodRegistry.createComponent(REG_18)));
    public static final Item BEEF_WELLINGTON = new Item(createGroup().food(FoodRegistry.createComponent(REG_18)));
    public static final Item FISH_AND_CHIPS = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item ETON_MESS = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item TEA = new Drink(createGroup().food(FoodRegistry.createBuilder(REG_5).alwaysEdible().build()));
    public static final Item CORNISH_PASTY = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item SCONES = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item FIGGY_PUDDING = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item TREACLE_TART = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item STICKY_TOFFEE_PUDDING = new Item(createGroup().food(FoodRegistry.createComponent(REG_14)));
    public static final Item TRIFLE = new Item(createGroup().food(FoodRegistry.createComponent(REG_14)));
    public static final Item WATER_BOTTLE = new Item(createGroup());
    public static final Item MILK_BOTTLE = new Item(createGroup());

    // 1.7.0
    public static final Item AJVAR = new Item(createGroup().food(FoodRegistry.createComponent(REG_7)));
    public static final Item AJVAR_TOAST = new Item(createGroup().food(FoodRegistry.createComponent(REG_14)));
    public static final Item AVOCADO_TOAST = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item BEEF_STEW = new Item(createGroup().food(FoodRegistry.createComponent(REG_14)));
    public static final Item BEEF_STIR_FRY = new Item(createGroup().food(FoodRegistry.createComponent(REG_14)));
    public static final Item BUTTERED_GREEN_BEANS = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item CHEESY_ASPARAGUS = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item CHOCOLATE_ICE_CREAM = new Item(createGroup().food(FoodRegistry.createComponent(REG_14)));
    public static final Item EGGPLANT_PARMESAN = new Item(createGroup().food(FoodRegistry.createComponent(REG_18)));
    public static final Item FRUIT_CAKE = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item GRILLED_EGGPLANT = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item KIWI_SORBET = new Item(createGroup().food(FoodRegistry.createComponent(REG_7)));
    public static final Item LEMON_COCONUT_BAR = new Item(createGroup().food(FoodRegistry.createComponent(REG_7)));
    public static final Item NETHER_WART_STEW = new Item(createGroup().food(FoodRegistry.createComponent(REG_7)));
    public static final Item PEANUT_BUTTER = new Item(createGroup().food(FoodRegistry.createComponent(REG_5)));
    public static final Item PEANUT_BUTTER_W_CELERY = new Item(createGroup().food(FoodRegistry.createComponent(REG_7)));
    public static final Item POTATO_SOUP = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item RATATOUILLE = new Item(createGroup().food(FoodRegistry.createComponent(REG_18)));
    public static final Item RAW_BACON = new Item(createGroup().food(FoodRegistry.createComponent(REG_1)));
    public static final Item RHUBARB_CRISP = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item ROASTED_ASPARAGUS = new Item(createGroup().food(FoodRegistry.createComponent(REG_7)));
    public static final Item ROASTED_RADISHES = new Item(createGroup().food(FoodRegistry.createComponent(REG_7)));
    public static final Item ROASTED_SQUASH = new Item(createGroup().food(FoodRegistry.createComponent(REG_7)));
    public static final Item ROASTED_TURNIPS = new Item(createGroup().food(FoodRegistry.createComponent(REG_7)));
    public static final Item STEAMED_BROCCOLI = new Item(createGroup().food(FoodRegistry.createComponent(REG_7)));
    public static final Item STEAMED_GREEN_BEANS = new Item(createGroup().food(FoodRegistry.createComponent(REG_7)));
    public static final Item STIR_FRY = new Item(createGroup().food(FoodRegistry.createComponent(REG_18)));
    public static final Item STUFFED_ARTICHOKE = new Item(createGroup().food(FoodRegistry.createComponent(REG_18)));
    public static final Item TOAST_SANDWICH = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));

    // 2.0.0
    public static final Item ROASTED_PUMPKIN_SEEDS = new Item(createGroup().food(FoodRegistry.createComponent(REG_4)));
    public static final Item ROASTED_SUNFLOWER_SEEDS = new Item(createGroup().food(FoodRegistry.createComponent(REG_4)));
    public static final Item PUMPKIN_BARS = new Item(createGroup().food(FoodRegistry.createComponent(REG_6)));
    public static final Item CORN_BREAD = new Item(createGroup().food(FoodRegistry.createComponent(REG_5)));
    public static final Item PUMPKIN_SOUP = new SoupItem(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item MERINGUE = new Item(createGroup().food(FoodRegistry.createComponent(REG_6)));
    public static final Item CABBAGE_ROLL = new Item(createGroup().food(FoodRegistry.createComponent(REG_14)));
    public static final Item BORSCHT = new Item(createGroup().food(FoodRegistry.createComponent(REG_12)));
    public static final Item GOULASH = new Item(createGroup().food(FoodRegistry.createComponent(REG_16)));
    public static final Item BEETROOT_SALAD = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item CANDIED_KUMQUATS = new Item(createGroup().food(FoodRegistry.createComponent(REG_6)));
    public static final Item STEAMED_CRAB = new Item(createGroup().food(FoodRegistry.createComponent(REG_6)));
    public static final Item SEA_LETTUCE = new Item(createGroup().food(FoodRegistry.createComponent(REG_1)));
    public static final Item DEEP_FRIED_SHRIMP = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item TUNA_ROLL = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item FRIED_CALAMARI = new Item(createGroup().food(FoodRegistry.createComponent(REG_10)));
    public static final Item CRAB_LEGS = new Item(createGroup().food(FoodRegistry.createComponent(REG_11)));
    public static final Item STEAMED_CLAMS = new Item(createGroup().food(FoodRegistry.createComponent(REG_11)));
    public static final Item GRILLED_OYSTERS = new Item(createGroup().food(FoodRegistry.createComponent(REG_11)));
    public static final Item ANCHOVY_PIZZA = new Item(createGroup().food(FoodRegistry.createComponent(REG_15)));
    public static final Item MASHED_POTATOES = new Item(createGroup().food(FoodRegistry.createComponent(REG_9)));

    public static Block SALT_ORE_BLOCK = new Block(FabricBlockSettings.of(Material.AGGREGATE).strength(0.5F).sounds(BlockSoundGroup.SAND));
    public static final Item SALT_ORE = new AliasedBlockItem(SALT_ORE_BLOCK, createGroup());

    public static final Item GUIDE = new GuideBookItem(createGroup());

    // force loading of the enums
    static {
        // the following four will be loaded by finding the source in Juice etc
        // Farmland.values();
        // Tree.values();
        // Bark.values();
        // VanillaCrops.values();
        //noinspection ResultOfMethodCallIgnored
        Juice.values();
        //noinspection ResultOfMethodCallIgnored
        Jam.values();
        //noinspection ResultOfMethodCallIgnored
        Smoothie.values();
        //noinspection ResultOfMethodCallIgnored
        IceCream.values();
        //noinspection ResultOfMethodCallIgnored
        Seafood.values();
        //noinspection ResultOfMethodCallIgnored
        Furnace.values();
        //noinspection ResultOfMethodCallIgnored
        Pie.values();
        //noinspection ResultOfMethodCallIgnored
        Utensil.values();
    }

    public static void init() {
        registerItem(ItemNames.GUIDE, GUIDE);

        registerItem(ItemNames.PAPRIKA, PAPRIKA);

        registerItem(ItemNames.OLIVE_OIL, OLIVE_OIL);
        registerItem(ItemNames.CHEESE, CHEESE);
        registerItem(ItemNames.FLOUR, FLOUR);
        registerItem(ItemNames.BUTTER, BUTTER);
        registerItem(ItemNames.NOODLE, NOODLE);
        registerItem(ItemNames.TOFU, TOFU);
        registerItem(ItemNames.CHOCOLATE, CHOCOLATE);
        registerItem(ItemNames.TORTILLA, TORTILLA);
        registerItem(ItemNames.SOY_SAUCE, SOY_SAUCE);
        registerItem(ItemNames.DOUGH, DOUGH);
        registerItem(ItemNames.RAVIOLI, RAVIOLI);
        registerItem(ItemNames.SALSA, SALSA);
        registerItem(ItemNames.ARTICHOKE_DIP, ARTICHOKE_DIP);
        registerItem(ItemNames.PEPPERONI, PEPPERONI);

        registerItem(ItemNames.COFFEE, COFFEE);
        registerItem(ItemNames.LEMONADE, LEMONADE);
        registerItem(ItemNames.LIMEADE, LIMEADE);
        registerItem(ItemNames.SOY_MILK, SOY_MILK);
        registerItem(ItemNames.KALE_SMOOTHIE, KALE_SMOOTHIE);
        registerItem(ItemNames.FRUIT_SMOOTHIE, FRUIT_SMOOTHIE);
        registerItem(ItemNames.CHOCOLATE_MILKSHAKE, CHOCOLATE_MILKSHAKE);
        registerItem(ItemNames.BEER, BEER);
        registerItem(ItemNames.WINE, WINE);
        registerItem(ItemNames.MEAD, MEAD);
        registerItem(ItemNames.RUM, RUM);
        registerItem(ItemNames.PUMPKIN_SPICE_LATTE, PUMPKIN_SPICE_LATTE);

        registerItem(ItemNames.BEEF_JERKY, BEEF_JERKY); // todo no recipe
        registerItem(ItemNames.PORK_JERKY, PORK_JERKY); // todo no recipe
        registerItem(ItemNames.KALE_CHIPS, KALE_CHIPS);
        registerItem(ItemNames.POTATO_CHIPS, POTATO_CHIPS);
        registerItem(ItemNames.STEAMED_RICE, STEAMED_RICE);
        registerItem(ItemNames.FRENCH_FRIES, FRENCH_FRIES);
        registerItem(ItemNames.SWEET_POTATO_FRIES, SWEET_POTATO_FRIES);
        registerItem(ItemNames.ONION_RINGS, ONION_RINGS);
        registerItem(ItemNames.DOUGHNUT, DOUGHNUT);
        registerItem(ItemNames.CUCUMBER_SALAD, CUCUMBER_SALAD);
        registerItem(ItemNames.CAESAR_SALAD, CAESAR_SALAD);
        registerItem(ItemNames.LEAFY_SALAD, LEAFY_SALAD);
        registerItem(ItemNames.FRUIT_SALAD, FRUIT_SALAD);
        registerItem(ItemNames.VEGGIE_SALAD, VEGGIE_SALAD);
        registerItem(ItemNames.PORK_AND_BEANS, PORK_AND_BEANS);
        registerItem(ItemNames.OATMEAL, OATMEAL);
        registerItem(ItemNames.LEEK_SOUP, LEEK_SOUP);
        registerItem(ItemNames.YOGHURT, YOGHURT);
        registerItem(ItemNames.SAUCY_CHIPS, SAUCY_CHIPS);
        registerItem(ItemNames.ROASTED_NUTS, ROASTED_NUTS);
        registerItem(ItemNames.TRAIL_MIX, TRAIL_MIX);
        registerItem(ItemNames.PROTEIN_BAR, PROTEIN_BAR);
        registerItem(ItemNames.NOUGAT, NOUGAT);

        registerItem(ItemNames.SCRAMBLED_EGGS, SCRAMBLED_EGGS);
        registerItem(ItemNames.BUTTERED_TOAST, BUTTERED_TOAST);
        registerItem(ItemNames.TOAST_WITH_JAM, TOAST_WITH_JAM);

        registerItem(ItemNames.HAM_SANDWICH, HAM_SANDWICH);
        registerItem(ItemNames.PEANUT_BUTTER_AND_JAM, PEANUT_BUTTER_AND_JAM);
        registerItem(ItemNames.BLT, BLT);
        registerItem(ItemNames.GRILLED_CHEESE, GRILLED_CHEESE);
        registerItem(ItemNames.TUNA_SANDWICH, TUNA_SANDWICH); // todo no recipe
        registerItem(ItemNames.CHEESEBURGER, CHEESEBURGER);
        registerItem(ItemNames.HAMBURGER, HAMBURGER);
        registerItem(ItemNames.TOFUBURGER, TOFU_BURGER);
        registerItem(ItemNames.PIZZA, PIZZA);
        registerItem(ItemNames.SUPREME_PIZZA, SUPREME_PIZZA);
        registerItem(ItemNames.CHEESE_PIZZA, CHEESE_PIZZA);
        registerItem(ItemNames.PINEAPPLE_PEPPERONI_PIZZA, PINEAPPLE_PEPPERONI_PIZZA);
        registerItem(ItemNames.LEMON_CHICKEN, LEMON_CHICKEN);
        registerItem(ItemNames.FRIED_CHICKEN, FRIED_CHICKEN);
        registerItem(ItemNames.CHICKEN_AND_NOODLES, CHICKEN_AND_NOODLES);
        registerItem(ItemNames.CHICKEN_AND_DUMPLINGS, CHICKEN_AND_DUMPLINGS);
        registerItem(ItemNames.TOFU_AND_DUMPLINGS, TOFU_AND_DUMPLINGS);
        registerItem(ItemNames.SPAGHETTI_SQUASH, SPAGHETTI_SQUASH);
        registerItem(ItemNames.CHICKEN_AND_RICE, CHICKEN_AND_RICE);
        registerItem(ItemNames.TACO, TACO);
        registerItem(ItemNames.SUSHI, SUSHI);
        registerItem(ItemNames.EGG_ROLL, EGG_ROLL);
        registerItem(ItemNames.CASHEW_CHICKEN, CASHEW_CHICKEN);

        registerItem(ItemNames.YAM_JAM, YAM_JAM);
        registerItem(ItemNames.BANANA_CREAM_PIE, BANANA_CREAM_PIE);
        registerItem(ItemNames.CANDY_CORN, CANDY_CORN);
        registerItem(ItemNames.RUM_RAISIN_ICE_CREAM, RUM_RAISIN_ICE_CREAM);
        registerItem(ItemNames.CHEESE_CAKE, CHEESE_CAKE);
        registerItem(ItemNames.BROWNIES, BROWNIES);
        registerItem(ItemNames.SNICKER_DOODLE, SNICKER_DOODLE);
        registerItem(ItemNames.BANANA_NUT_BREAD, BANANA_NUT_BREAD);
        registerItem(ItemNames.CANDIED_NUTS, CANDIED_NUTS);
        registerItem(ItemNames.ALMOND_BRITTLE, ALMOND_BRITTLE);
        registerItem(ItemNames.RAISIN_OATMEAL_COOKIE, OATMEAL_COOKIE);
        registerItem(ItemNames.NUTTY_COOKIE, NUTTY_COOKIE);

        registerItem(ItemNames.BURRITO, BURRITO);
        registerItem(ItemNames.TOSTADA, TOSTADA);
        registerItem(ItemNames.HORCHATA, HORCHATA);
        registerItem(ItemNames.CARNITAS, CARNITAS);
        registerItem(ItemNames.FAJITAS, FAJITAS);
        registerItem(ItemNames.ENCHILADA, ENCHILADA);
        registerItem(ItemNames.CHURROS, CHURROS);
        registerItem(ItemNames.TAMALES, TAMALES);
        registerItem(ItemNames.TRES_LECHE_CAKE, TRES_LECHE_CAKE);
        registerItem(ItemNames.STUFFED_POBLANOS, STUFFED_POBLANOS);
        registerItem(ItemNames.CHILI_RELLENO, CHILI_RELLENO);
        registerItem(ItemNames.CREMA, CREMA);
        registerItem(ItemNames.REFRIED_BEANS, REFRIED_BEANS);
        registerItem(ItemNames.CHIMICHANGA, CHIMICHANGA);
        registerItem(ItemNames.QUESADILLA, QUESADILLA);

        registerItem(ItemNames.AJVAR, AJVAR);
        registerItem(ItemNames.AJVAR_TOAST, AJVAR_TOAST);
        registerItem(ItemNames.AVOCADO_TOAST, AVOCADO_TOAST);
        registerItem(ItemNames.BEEF_STEW, BEEF_STEW);
        registerItem(ItemNames.BEEF_STIR_FRY, BEEF_STIR_FRY);
        registerItem(ItemNames.BUTTERED_GREEN_BEANS, BUTTERED_GREEN_BEANS);
        registerItem(ItemNames.CHEESY_ASPARAGUS, CHEESY_ASPARAGUS);
        registerItem(ItemNames.CHOCOLATE_ICE_CREAM, CHOCOLATE_ICE_CREAM);
        registerItem(ItemNames.EGGPLANT_PARMESAN, EGGPLANT_PARMESAN);
        registerItem(ItemNames.FRUIT_CAKE, FRUIT_CAKE);
        registerItem(ItemNames.GRILLED_EGGPLANT, GRILLED_EGGPLANT);
        registerItem(ItemNames.KIWI_SORBET, KIWI_SORBET);
        registerItem(ItemNames.LEMON_COCONUT_BAR, LEMON_COCONUT_BAR);
        registerItem(ItemNames.NETHER_WART_STEW, NETHER_WART_STEW);
        registerItem(ItemNames.PEANUT_BUTTER, PEANUT_BUTTER);
        registerItem(ItemNames.PEANUT_BUTTER_W_CELERY, PEANUT_BUTTER_W_CELERY);
        registerItem(ItemNames.POTATO_SOUP, POTATO_SOUP);
        registerItem(ItemNames.RATATOUILLE, RATATOUILLE);
        registerItem(ItemNames.RAW_BACON, RAW_BACON);
        registerItem(ItemNames.RHUBARB_CRISP, RHUBARB_CRISP);
        registerItem(ItemNames.ROASTED_ASPARAGUS, ROASTED_ASPARAGUS);
        registerItem(ItemNames.ROASTED_RADISHES, ROASTED_RADISHES);
        registerItem(ItemNames.ROASTED_SQUASH, ROASTED_SQUASH);
        registerItem(ItemNames.ROASTED_TURNIPS, ROASTED_TURNIPS);
        registerItem(ItemNames.STEAMED_BROCCOLI, STEAMED_BROCCOLI);
        registerItem(ItemNames.STEAMED_GREEN_BEANS, STEAMED_GREEN_BEANS);
        registerItem(ItemNames.STIR_FRY, STIR_FRY);
        registerItem(ItemNames.STUFFED_ARTICHOKE, STUFFED_ARTICHOKE);
        registerItem(ItemNames.TOAST_SANDWICH, TOAST_SANDWICH);

        registerItem(ItemNames.SHEPHERDS_PIE, SHEPHERDS_PIE);
        registerItem(ItemNames.BEEF_WELLINGTON, BEEF_WELLINGTON);
        registerItem(ItemNames.FISH_AND_CHIPS, FISH_AND_CHIPS);
        registerItem(ItemNames.ETON_MESS, ETON_MESS);
        registerItem(ItemNames.TEA, TEA);
        registerItem(ItemNames.CORNISH_PASTY, CORNISH_PASTY);
        registerItem(ItemNames.SCONES, SCONES);
        registerItem(ItemNames.FIGGY_PUDDING, FIGGY_PUDDING);
        registerItem(ItemNames.TREACLE_TART, TREACLE_TART);
        registerItem(ItemNames.STICKY_TOFFEE_PUDDING, STICKY_TOFFEE_PUDDING);
        registerItem(ItemNames.TRIFLE, TRIFLE);
        registerItem(ItemNames.WATER_BOTTLE, WATER_BOTTLE);
        registerItem(ItemNames.MILK_BOTTLE, MILK_BOTTLE);

        registerItem(ItemNames.ROASTED_PUMPKIN_SEEDS, ROASTED_PUMPKIN_SEEDS);
        registerItem(ItemNames.ROASTED_SUNFLOWER_SEEDS, ROASTED_SUNFLOWER_SEEDS);
        registerItem(ItemNames.PUMPKIN_BARS, PUMPKIN_BARS);
        registerItem(ItemNames.CORN_BREAD, CORN_BREAD);
        registerItem(ItemNames.PUMPKIN_SOUP, PUMPKIN_SOUP);
        registerItem(ItemNames.MERINGUE, MERINGUE);
        registerItem(ItemNames.CABBAGE_ROLL, CABBAGE_ROLL);
        registerItem(ItemNames.BORSCHT, BORSCHT);
        registerItem(ItemNames.GOULASH, GOULASH);
        registerItem(ItemNames.BEETROOT_SALAD, BEETROOT_SALAD);
        registerItem(ItemNames.CANDIED_KUMQUATS, CANDIED_KUMQUATS);
        registerItem(ItemNames.STEAMED_CRAB, STEAMED_CRAB);
        registerItem(ItemNames.SEA_LETTUCE, SEA_LETTUCE);
        registerItem(ItemNames.DEEP_FRIED_SHRIMP, DEEP_FRIED_SHRIMP);
        registerItem(ItemNames.TUNA_ROLL, TUNA_ROLL);
        registerItem(ItemNames.FRIED_CALAMARI, FRIED_CALAMARI);
        registerItem(ItemNames.CRAB_LEGS, CRAB_LEGS);
        registerItem(ItemNames.STEAMED_CLAMS, STEAMED_CLAMS);
        registerItem(ItemNames.GRILLED_OYSTERS, GRILLED_OYSTERS);
        registerItem(ItemNames.ANCHOVY_PIZZA, ANCHOVY_PIZZA);
        registerItem(ItemNames.MASHED_POTATOES, MASHED_POTATOES);

        registerItem(ItemNames.CORN_HUSK, CORN_HUSK);
        registerItem(ItemNames.WHIPPING_CREAM, WHIPPING_CREAM);

        registerItem(ItemNames.SALT, SALT);
        registerBlock(BlockNames.SALT_ORE, SALT_ORE_BLOCK);
        registerItem(ItemNames.SALT_ORE, SALT_ORE);
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
