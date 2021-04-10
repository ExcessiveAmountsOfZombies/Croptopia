package me.thonk.croptopia;

import me.thonk.croptopia.registry.ItemRegistry;
import net.minecraft.util.IItemProvider;

import static net.minecraft.block.ComposterBlock.CHANCES;

public class Composter {

    public static void init() {
        CroptopiaForge.cropItems.forEach(item -> {
            registerCompostableItem(0.65F, item);
        });
        CroptopiaForge.seeds.forEach(seed -> {
            registerCompostableItem(0.3F, seed);
        });
        registerCompostableItem(0.5F, ItemRegistry.almondSapling);
        registerCompostableItem(0.5F, ItemRegistry.appleSapling);
        registerCompostableItem(0.5F, ItemRegistry.starFruitSapling);
        registerCompostableItem(0.5F, ItemRegistry.apricotSapling);
        registerCompostableItem(0.5F, ItemRegistry.avocadoSapling);
        registerCompostableItem(0.5F, ItemRegistry.bananaSapling);
        registerCompostableItem(0.5F, ItemRegistry.cashewSapling);
        registerCompostableItem(0.5F, ItemRegistry.cherrySapling);
        registerCompostableItem(0.5F, ItemRegistry.coconutSapling);
        registerCompostableItem(0.5F, ItemRegistry.dateSapling);
        registerCompostableItem(0.5F, ItemRegistry.dragonFruitSapling);
        registerCompostableItem(0.5F, ItemRegistry.figSapling);
        registerCompostableItem(0.5F, ItemRegistry.grapefruitSapling);
        registerCompostableItem(0.5F, ItemRegistry.kumquatSapling);
        registerCompostableItem(0.5F, ItemRegistry.lemonSapling);
        registerCompostableItem(0.5F, ItemRegistry.limeSapling);
        registerCompostableItem(0.5F, ItemRegistry.mangoSapling);
        registerCompostableItem(0.5F, ItemRegistry.nectarineSapling);
        registerCompostableItem(0.5F, ItemRegistry.nutmegSapling);
        registerCompostableItem(0.5F, ItemRegistry.orangeSapling);
        registerCompostableItem(0.5F, ItemRegistry.peachSapling);
        registerCompostableItem(0.5F, ItemRegistry.pearSapling);
        registerCompostableItem(0.5F, ItemRegistry.pecanSapling);
        registerCompostableItem(0.5F, ItemRegistry.persimmonSapling);
        registerCompostableItem(0.5F, ItemRegistry.plumSapling);
        registerCompostableItem(0.5F, ItemRegistry.walnutSapling);
    }

    public static void registerCompostableItem(float levelIncreaseChance, IItemProvider item) {
        CHANCES.put(item.asItem(), levelIncreaseChance);
    }
}
