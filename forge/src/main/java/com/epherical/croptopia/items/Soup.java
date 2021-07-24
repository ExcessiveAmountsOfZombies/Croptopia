package com.epherical.croptopia.items;


import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SoupItem;

public class Soup extends SoupItem {


    public Soup(Properties settings) {
        super(settings);
    }

    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
        Player playerEntity = user instanceof Player ? (Player)user : null;
        if (playerEntity != null) {
            if (!playerEntity.getAbilities().instabuild) {
                if (isEdible()) {
                    user.eat(world, stack);
                }
            }
        }

        if (playerEntity == null || !playerEntity.getAbilities().instabuild) {
            if (stack.isEmpty()) {
                return new ItemStack(Items.BOWL);
            }

            if (playerEntity != null) {
                playerEntity.getInventory().add(new ItemStack(Items.BOWL));
            }
        }

        return stack;
    }
}
