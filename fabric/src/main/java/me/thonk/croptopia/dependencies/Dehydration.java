package me.thonk.croptopia.dependencies;

import net.dehydration.access.ThristManagerAccess;
import net.dehydration.thirst.ThirstManager;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class Dehydration extends Dependency {

    private final Tag<Item> HYDRATING_FOOD = TagRegistry.item(new Identifier("dehydration", "hydrating_food"));
    private final Tag<Item> HYDRATING_STEW = TagRegistry.item(new Identifier("dehydration", "hydrating_stew"));
    private final Tag<Item> HYDRATING_DRINKS = TagRegistry.item(new Identifier("dehydration", "hydrating_drinks"));


    public Dehydration() {
        super("dehydration");
    }

    public void onUse(ItemStack stack, PlayerEntity player) {
        if (isLoaded()) {
            ThirstManager thirstManager = ((ThristManagerAccess) player).getThirstManager(player);
            int thirst = 0;
            if (stack.getItem().isIn(HYDRATING_STEW))
                thirst = 2;
            if (stack.getItem().isIn(HYDRATING_FOOD))
                thirst = 1;
            if (stack.getItem().isIn(HYDRATING_DRINKS))
                thirst = 4;
            thirstManager.add(thirst);
        }

    }
}
