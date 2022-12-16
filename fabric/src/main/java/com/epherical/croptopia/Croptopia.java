package com.epherical.croptopia;

import com.epherical.croptopia.common.ItemNamesV2;
import com.epherical.croptopia.common.MiscNames;
import com.epherical.croptopia.config.CroptopiaConfig;
import com.epherical.croptopia.config.IdentifierSerializer;
import com.epherical.croptopia.config.TreeConfiguration;
import com.epherical.croptopia.dependencies.Patchouli;
import com.epherical.croptopia.generator.BiomeModifiers;
import com.epherical.croptopia.items.CropLootTableModifier;
import com.epherical.croptopia.items.GuideBookItem;
import com.epherical.croptopia.items.SeedItem;
import com.epherical.croptopia.mixin.ChickenAccess;
import com.epherical.croptopia.mixin.ParrotAccess;
import com.epherical.croptopia.register.Content;
import com.epherical.croptopia.register.helpers.Tree;
import com.epherical.croptopia.registry.Composter;
import com.google.common.collect.Sets;
import com.mojang.logging.LogUtils;
import it.unimi.dsi.fastutil.ints.IntList;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fabricmc.fabric.api.registry.VillagerInteractionRegistries;
import net.fabricmc.fabric.api.registry.VillagerPlantableRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.epherical.croptopia.CroptopiaMod.createGroup;


public class Croptopia implements ModInitializer {


    private static final Logger LOGGER = LogUtils.getLogger();

    private final boolean devEnvironment = Boolean.getBoolean(MiscNames.MOD_ID + ".dev");

    public CroptopiaConfig config;

    public static final CreativeModeTab CROPTOPIA_ITEM_GROUP = FabricItemGroupBuilder.create(new ResourceLocation(MiscNames.MOD_ID, "croptopia"))
            .icon(() -> new ItemStack(Content.COFFEE))
            .build();
    public static Patchouli patchouli;

    @Override
    public void onInitialize() {
        FabricLoader.getInstance().getModContainer("seedfix")
                .map(modContainer -> ResourceManagerHelper.registerBuiltinResourcePack(new ResourceLocation("croptopia", "terralith"), modContainer, ResourcePackActivationType.DEFAULT_ENABLED))
                .filter(success -> !success);
        CroptopiaMod mod = new CroptopiaMod(new FabricAdapter());
        Content.registerBlocks((id, object) -> Registry.register(Registry.BLOCK, id, object));
        Content.GUIDE = new GuideBookItem(createGroup());
        Registry.register(Registry.ITEM, CroptopiaMod.createIdentifier(ItemNamesV2.GUIDE), Content.GUIDE);
        Content.registerItems((id, object) -> Registry.register(Registry.ITEM, id, object));


        patchouli = new Patchouli();

        Composter.init();

        this.config = new CroptopiaConfig(devEnvironment, "croptopia.conf");
        config.addSerializer(TreeConfiguration.class, TreeConfiguration.Serializer.INSTANCE);
        config.addSerializer(ResourceLocation.class, IdentifierSerializer.INSTANCE);
        config.loadConfig();

        BiomeModifiers.init(this);
        CropLootTableModifier.init();

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            SetupCommand.register(dispatcher, registryAccess);
            /*dispatcher.register(Commands.literal("croptopia")
                    .requires(commandSourceStack -> commandSourceStack.hasPermission(4))
                    .then(Commands.literal("wiki"))
                    .executes(context -> {
                        LOGGER.info("| Crop | Biomes |");
                        LOGGER.info("| ---- | ---- |");
                        for (TagKey<Biome> croptopiaBiomeTag : Tags.getCroptopiaBiomeTags()) {
                            context.getSource().registryAccess().registry(Registry.BIOME_REGISTRY).ifPresent(biomes -> {
                                List<String> biomeNames = new ArrayList<>();
                                for (Holder<Biome> biomeHolder : biomes.getTagOrEmpty(croptopiaBiomeTag)) {
                                    biomeHolder.unwrapKey().ifPresent(biomeResourceKey -> biomeNames.add(biomeResourceKey.location().toString()));
                                }
                                LOGGER.info("| {} | {} |", croptopiaBiomeTag.location().getPath(), biomeNames.toString());
                            });
                        }
                        return 1;
                    }));*/
        });

        //CroptopiaVillagerTrades.init();
        modifyAxeBlockStripping();
        modifyChickenBreeds();
        modifyParrotBreeds();
        modifyVillagers();
    }

    public static ResourceLocation createIdentifier(String name) {
        return new ResourceLocation(MiscNames.MOD_ID, name);
    }

    private void modifyAxeBlockStripping() {
        for (Tree crop : Tree.copy()) {
            StrippableBlockRegistry.register(crop.getLog(), crop.getStrippedLog());
            StrippableBlockRegistry.register(crop.getWood(), crop.getStrippedWood());
        }
    }

    private void modifyChickenBreeds() {
        IntList stacks = ChickenAccess.getFoodItems().getStackingIds();
        List<Item> baseItems = new ArrayList<>();

        for (Integer stack : stacks) {
            baseItems.add(Item.byId(stack));
        }
        // TODO iterate over farmland
        baseItems.addAll(CroptopiaMod.seeds);
        ChickenAccess.setFoodItems(Ingredient.of(baseItems.toArray(new Item[0])));
    }

    private void modifyParrotBreeds() {
        Set<Item> baseItems = ParrotAccess.getTamingIngredients();
        Set<Item> newItems = Sets.newHashSet(baseItems);
        newItems.addAll(CroptopiaMod.seeds);
        ParrotAccess.setTamingIngredients(newItems);
    }

    private void modifyVillagers() {
        // Allow villagers to plant croptopia seeds
        for (SeedItem seed : CroptopiaMod.seeds) {
            VillagerPlantableRegistry.register(seed);
        }
        // Allow villagers to compost croptopia seeds.
        for (SeedItem seed : CroptopiaMod.seeds) {
            VillagerInteractionRegistries.registerCompostable(seed);
        }
        // Allow villagers to consume(?) harvested croptopia foods.
        Content.createCropStream().filter(item -> item.getFoodProperties() != null)
                .forEach(item -> {
                    VillagerInteractionRegistries.registerFood(item, item.getFoodProperties().getNutrition());
                    VillagerInteractionRegistries.registerCollectable(item);
                });
        // this is the "wanted" items for villagers.
        CroptopiaMod.seeds.forEach(VillagerInteractionRegistries::registerCollectable);
    }
}
