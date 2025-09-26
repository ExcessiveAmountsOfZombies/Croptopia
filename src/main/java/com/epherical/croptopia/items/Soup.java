package com.epherical.croptopia.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class Soup extends Item {

    public Soup(Properties settings) {
        super(settings.usingConvertsTo(Items.BOWL));
    }

    // REVIEW 1.21.3 - with the new Consumable component, I don't think any of this is necessary any more.
    /**
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
     **/
}
