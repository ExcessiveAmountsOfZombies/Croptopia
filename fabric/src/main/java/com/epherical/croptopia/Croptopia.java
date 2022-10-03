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
import com.epherical.croptopia.mixin.AxeAccess;
import com.epherical.croptopia.mixin.ChickenAccess;
import com.epherical.croptopia.mixin.FarmerWorkTaskAccessor;
import com.epherical.croptopia.mixin.ParrotAccess;
import com.epherical.croptopia.mixin.VillagerAccess;
import com.epherical.croptopia.register.Content;
import com.epherical.croptopia.register.helpers.Tree;
import com.epherical.croptopia.registry.Composter;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.ints.IntList;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.epherical.croptopia.CroptopiaMod.createGroup;


public class Croptopia implements ModInitializer {

    private final boolean devEnvironment = Boolean.getBoolean(MiscNames.MOD_ID + ".dev");

    public CroptopiaConfig config;

    public static final CreativeModeTab CROPTOPIA_ITEM_GROUP = FabricItemGroupBuilder.create(new ResourceLocation(MiscNames.MOD_ID, "croptopia"))
            .icon(() -> new ItemStack(Content.COFFEE))
            .build();
    public static Patchouli patchouli;

    private static final String TECH_REBORN_MOD_ID = "techreborn";

    @Override
    public void onInitialize() {
        FabricLoader.getInstance().getModContainer(TECH_REBORN_MOD_ID)
                .map(modContainer -> ResourceManagerHelper.registerBuiltinResourcePack(new ResourceLocation("croptopia", "treborn"), modContainer, ResourcePackActivationType.DEFAULT_ENABLED))
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
        return new ResourceLocation(MiscNames.MOD_ID, name);
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
        CroptopiaMod.seeds.forEach(villagerGatherables::add);
        Content.createCropStream().forEach(villagerGatherables::add);
        VillagerAccess.setGatherableItems(villagerGatherables.build());
    }

    private void modifyAxeBlockStripping() {
        Map<Block, Block> immutableBlocks = AxeAccess.getStrippables();
        var axeMap = new Builder<Block, Block>().putAll(immutableBlocks);
        for (Tree crop : Tree.copy()) {
            axeMap.put(crop.getLog(), crop.getStrippedLog());
            axeMap.put(crop.getWood(), crop.getStrippedWood());
        }
        AxeAccess.setStrippables(axeMap.build());
    }

    private void modifyChickenBreeds() {
        IntList stacks = ChickenAccess.getFoodItems().getStackingIds();
        List<ItemStack> baseStacks = new ArrayList<>();

        for (Integer stack : stacks) {
            baseStacks.add(StackedContents.fromStackingIndex(stack));
        }
        // TODO iterate over farmland
        for (SeedItem seedItem : CroptopiaMod.seeds) {
            baseStacks.add(new ItemStack(seedItem));
        }
        ChickenAccess.setFoodItems(Ingredient.of(baseStacks.toArray(new ItemStack[0])));
    }

    private void modifyParrotBreeds() {
        Set<Item> baseItems = ParrotAccess.getTamingIngredients();
        Set<Item> newItems = Sets.newHashSet(baseItems);
        newItems.addAll(CroptopiaMod.seeds);
        ParrotAccess.setTamingIngredients(newItems);
    }

    private void modifyVillagerFarmerTaskCompostables() {
        List<Item> baseItems = FarmerWorkTaskAccessor.getCompostables();
        List<Item> newItems = Lists.newArrayList(baseItems);
        newItems.addAll(CroptopiaMod.seeds);
        FarmerWorkTaskAccessor.setCompostables(newItems);
    }
}
