package com.epherical.croptopia;

import com.epherical.croptopia.common.ItemNamesV2;
import com.epherical.croptopia.config.CroptopiaConfig;
import com.epherical.croptopia.dependencies.Patchouli;
import com.epherical.croptopia.generator.BiomeModifiers;
import com.epherical.croptopia.items.CropLootTableModifier;
import com.epherical.croptopia.items.GuideBookItem;
import com.epherical.croptopia.register.Content;
import com.epherical.croptopia.register.helpers.FarmlandCrop;
import com.epherical.croptopia.register.helpers.Tree;
import com.epherical.croptopia.register.helpers.TreeCrop;
import com.epherical.croptopia.register.helpers.Utensil;
import com.epherical.epherolib.libs.org.spongepowered.configurate.hocon.HoconConfigurationLoader;
import com.mojang.logging.LogUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fabricmc.fabric.api.registry.VillagerInteractionRegistries;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.slf4j.Logger;

import java.util.Comparator;
import java.util.List;

import static com.epherical.croptopia.CroptopiaMod.createGroup;
import static com.epherical.croptopia.common.MiscNames.MOD_ID;


public class Croptopia implements ModInitializer {
    private static final Logger LOGGER = LogUtils.getLogger();
    private final boolean devEnvironment = Boolean.getBoolean(MOD_ID + ".dev");

    public static CroptopiaMod mod;


    public static final CreativeModeTab CROPTOPIA_ITEM_GROUP = FabricItemGroup.builder()
            .title(Component.translatable("itemGroup.croptopia"))
            .displayItems((featureFlagSet, output) ->
                    BuiltInRegistries.ITEM.entrySet().stream()
                            .filter(entry -> entry.getKey().location().getNamespace().equals(MOD_ID))
                            .sorted(Comparator.comparing(entry -> BuiltInRegistries.ITEM.getId(entry.getValue())))
                            .forEach(entry -> output.accept(entry.getValue())))
            .icon(() -> new ItemStack(Content.COFFEE))
            .build();
    public static Patchouli patchouli;

    @Override
    public void onInitialize() {
        mod = new CroptopiaMod(new FabricAdapter(), new CroptopiaConfig(HoconConfigurationLoader.builder(), "croptopia_v3.conf"));

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(MOD_ID, "croptopia"), CROPTOPIA_ITEM_GROUP);



        Content.registerBlocks((id, fn) -> Registry.register(BuiltInRegistries.BLOCK, id, fn.apply(id)));
        mod.platform().registerFlammableBlocks();
        final ResourceLocation guideBookId = CroptopiaMod.createIdentifier(ItemNamesV2.GUIDE);
        Content.GUIDE = new GuideBookItem(createGroup(guideBookId));
        Registry.register(BuiltInRegistries.ITEM, guideBookId, Content.GUIDE);

        Content.registerItems((id, fn) -> Registry.register(BuiltInRegistries.ITEM, id, fn.apply(id)));

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> {
            entries.addAfter(Items.MANGROVE_PROPAGULE, Content.CINNAMON.getSapling());
            // TODO; refactor out the TREE_CROPS/FARMLAND_CROPS
            List<ItemStack> collect = TreeCrop.TREE_CROPS.stream().map(TreeCrop::getSaplingItem).map(ItemStack::new).toList();
            entries.addAfter(Items.FLOWERING_AZALEA, collect);
            entries.addAfter(Items.NETHER_WART, FarmlandCrop.FARMLAND_CROPS.stream().map(FarmlandCrop::getSeedItem).map(ItemStack::new).toList());
            entries.addBefore(Items.COAL_ORE, Content.SALT_ORE);
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> {
            entries.addAfter(Items.FLINT_AND_STEEL, Utensil.copy().toArray(new Utensil[0]));
            if (FabricLoader.getInstance().isModLoaded("patchouli")) {
                entries.addAfter(Items.WRITABLE_BOOK, Content.GUIDE);
            }
        });


        patchouli = new Patchouli();
        mod.registerCompost();

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
        modifyVillagers();
    }

    public static ResourceLocation createIdentifier(String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }

    private void modifyAxeBlockStripping() {
        for (Tree crop : Tree.copy()) {
            StrippableBlockRegistry.register(crop.getLog(), crop.getStrippedLog());
            StrippableBlockRegistry.register(crop.getWood(), crop.getStrippedWood());
        }
    }



    private void modifyVillagers() {
        // Allow villagers to compost croptopia seeds.
        for (Item seed : CroptopiaMod.seeds) {
            VillagerInteractionRegistries.registerCompostable(seed);
        }
        // Allow villagers to consume(?) harvested croptopia foods.
        Content.createCropStream().filter(item -> item.components().has(DataComponents.FOOD))
                .forEach(item -> {
                    VillagerInteractionRegistries.registerFood(item, item.components().get(DataComponents.FOOD).nutrition());
                    VillagerInteractionRegistries.registerCollectable(item);
                });
        // this is the "wanted" items for villagers.
        CroptopiaMod.seeds.forEach(VillagerInteractionRegistries::registerCollectable);
    }
}
