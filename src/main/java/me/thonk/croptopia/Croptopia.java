package me.thonk.croptopia;

import me.thonk.croptopia.blocks.BlockRegistry;
import me.thonk.croptopia.items.ItemRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;


public class Croptopia implements ModInitializer {

    public static final String MOD_ID = "croptopia";
    public static final ItemGroup CROPTOPIA_ITEM_GROUP = FabricItemGroupBuilder.create(new Identifier(MOD_ID, "croptopia"))
            .icon(() -> new ItemStack(ItemRegistry.onion))
            .build();



    @Override
    public void onInitialize() {
        BlockRegistry.init();
        ItemRegistry.init();
    }

    public static Identifier createIdentifier(String name) {
        return new Identifier(MOD_ID, name);
    }
}
