package com.epherical.croptopia.listeners;

import com.epherical.croptopia.register.Content;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EntitySpawn {


    @SubscribeEvent
    public void onEntitySpawn(LivingSpawnEvent.SpecialSpawn event) {
        LivingEntity livingEntity = event.getEntityLiving();
        if (livingEntity instanceof MobEntity) {
            if (livingEntity instanceof PigEntity) {
                ((PigEntity) livingEntity).goalSelector.addGoal(4, new TemptGoal((CreatureEntity) livingEntity, 1.2D, Ingredient.of(Content.YAM, Content.SWEETPOTATO), false));
            }

            if (livingEntity instanceof CowEntity) {
                ((CowEntity) livingEntity).goalSelector.addGoal(3, new TemptGoal((CreatureEntity) livingEntity, 1.25D, Ingredient.of(Content.BARLEY, Content.CORN), false));
            }
        }
    }
}
