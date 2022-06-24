package com.epherical.croptopia.items;

import com.epherical.croptopia.CroptopiaMod;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;


public class Drink extends Item {


    public Drink(Properties settings) {
        super(settings);
    }

    @Override
    public UseAction getUseAnimation(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public SoundEvent getEatingSound() {
        return getDrinkingSound();
    }


    @Override
    public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity user) {
        PlayerEntity playerEntity = user instanceof PlayerEntity ? (PlayerEntity)user : null;
        if (playerEntity instanceof ServerPlayerEntity) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayerEntity)playerEntity, stack);
        }

        if (playerEntity != null) {
            playerEntity.awardStat(Stats.ITEM_USED.get(this));
            if (!playerEntity.abilities.instabuild) {
                if (isEdible()) {
                    //CroptopiaMod.getInstance().platform().invokeDrinkEvent(stack, playerEntity);
                    user.eat(world, stack);
                }
            }
        }

        if (playerEntity == null || !playerEntity.abilities.instabuild) {
            if (stack.isEmpty()) {
                return new ItemStack(getCraftingRemainingItem());
            }

            if (playerEntity != null) {
                playerEntity.inventory.add(new ItemStack(getCraftingRemainingItem()));
            }
        }

        return stack;
    }
}
