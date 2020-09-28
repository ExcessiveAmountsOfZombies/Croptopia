package me.thonk.croptopia;

import me.thonk.croptopia.blocks.BlockRegistry;
import me.thonk.croptopia.items.CropLootTableModifier;
import me.thonk.croptopia.items.ItemRegistry;
import me.thonk.croptopia.table.BiomeLootCondition;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.block.FlowerBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.registry.Registry;


public class Croptopia implements ModInitializer {

    public static final String MOD_ID = "croptopia";
    public static final ItemGroup CROPTOPIA_ITEM_GROUP = FabricItemGroupBuilder.create(new Identifier(MOD_ID, "croptopia"))
            .icon(() -> new ItemStack(ItemRegistry.onion))
            .build();
    public static final LootConditionType BIOME_CHECK =  registerLootCondition("biome_check", new BiomeLootCondition.Serializer());


    @Override
    public void onInitialize() {
        BlockRegistry.init();
        ItemRegistry.init();
        CropLootTableModifier.init();

        CommandRegistrationCallback.EVENT.register((commandDispatcher, b) -> {
            SetupCommand.register(commandDispatcher);
        });
    }

    public static Identifier createIdentifier(String name) {
        return new Identifier(MOD_ID, name);
    }

    public static LootConditionType registerLootCondition(String id, JsonSerializer<? extends LootCondition> serializer) {
        return Registry.register(Registry.LOOT_CONDITION_TYPE, new Identifier(id), new LootConditionType(serializer));
    }
}
