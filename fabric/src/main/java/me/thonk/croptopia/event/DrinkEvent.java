package me.thonk.croptopia.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class DrinkEvent {

    public static final Event<Drink> DRINK = EventFactory.createArrayBacked(Drink.class, drinks -> (item, entity) -> {
        for (Drink drink : drinks) {
            drink.onDrink(item, entity);
        }
    });


    public interface Drink {

        void onDrink(ItemStack item, PlayerEntity entity);
    }
}
