package com.epherical.croptopia.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class DrinkEvent {

    public static final Event<Drink> DRINK = EventFactory.createArrayBacked(Drink.class, drinks -> (item, entity) -> {
        for (Drink drink : drinks) {
            drink.onDrink(item, entity);
        }
    });


    public interface Drink {

        void onDrink(ItemStack item, Player entity);
    }
}
