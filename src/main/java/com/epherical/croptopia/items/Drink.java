package com.epherical.croptopia.items;

import com.epherical.croptopia.CroptopiaMod;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class Drink extends Item {

    public Drink(Properties settings) {
        super(settings.usingConvertsTo(Items.GLASS_BOTTLE));
    }

    // REVIEW 1.21.3 - with the new Consumable component, I don't think any of this is necessary any more.
    /**
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
        Player playerEntity = user instanceof Player ? (Player)user : null;
        if (playerEntity instanceof ServerPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer)playerEntity, stack);
        }

        if (playerEntity != null) {
            playerEntity.awardStat(Stats.ITEM_USED.get(this));
            if (!playerEntity.getAbilities().instabuild) {
                if (isEdible()) {
                    CroptopiaMod.getInstance().platform().invokeDrinkEvent(stack, playerEntity);
                    user.eat(world, stack);
                }
            }
        }

        if (playerEntity == null || !playerEntity.getAbilities().instabuild) {
            if (stack.isEmpty()) {
                return new ItemStack(getCraftingRemainingItem());
            }

            if (playerEntity != null) {
                playerEntity.getInventory().add(new ItemStack(getCraftingRemainingItem()));
            }
        }

        return stack;
    }
    **/
}
