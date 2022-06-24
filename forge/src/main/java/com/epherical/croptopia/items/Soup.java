package com.epherical.croptopia.items;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SoupItem;
import net.minecraft.world.World;

public class Soup extends SoupItem {


    public Soup(Properties settings) {
        super(settings);
    }

    public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity user) {
        PlayerEntity playerEntity = user instanceof PlayerEntity ? (PlayerEntity)user : null;
        if (playerEntity != null) {
            if (!playerEntity.abilities.instabuild) {
                if (isEdible()) {
                    user.eat(world, stack);
                }
            }
        }

        if (playerEntity == null || !playerEntity.abilities.instabuild) {
            if (stack.isEmpty()) {
                return new ItemStack(Items.BOWL);
            }

            if (playerEntity != null) {
                playerEntity.inventory.add(new ItemStack(Items.BOWL));
            }
        }

        return stack;
    }
}
