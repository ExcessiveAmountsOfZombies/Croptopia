package me.thonk.croptopia.dependencies;

import net.dehydration.access.ThristManagerAccess;
import net.dehydration.init.ConfigInit;
import net.dehydration.init.TagInit;
import net.dehydration.thirst.ThirstManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class Dehydration extends Dependency {


    public Dehydration() {
        super("dehydration");
    }

    public void onUse(ItemStack stack, PlayerEntity player) {
        if (isLoaded()) {
            ThirstManager thirstManager = ((ThristManagerAccess) player).getThirstManager(player);
            int thirst = 0;
            if (stack.getItem().isIn(TagInit.HYDRATING_STEW)) {
                thirst = ConfigInit.CONFIG.stew_thirst_quench;
            }
            if (stack.getItem().isIn(TagInit.HYDRATING_FOOD)) {
                thirst = ConfigInit.CONFIG.food_thirst_quench;
            }
            if (stack.getItem().isIn(TagInit.HYDRATING_DRINKS)) {
                thirst = ConfigInit.CONFIG.potion_thirst_quench;
            }
            thirstManager.add(thirst);
        }

    }
}
