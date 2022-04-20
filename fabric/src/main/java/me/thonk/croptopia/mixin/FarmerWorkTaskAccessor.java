package me.thonk.croptopia.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;
import net.minecraft.world.entity.ai.behavior.WorkAtComposter;
import net.minecraft.world.item.Item;

@Mixin(WorkAtComposter.class)
public interface FarmerWorkTaskAccessor {

    @Accessor(value = "COMPOSTABLE_ITEMS")
    static List<Item> getCompostables() {
        throw new AssertionError();
    }

    @Accessor(value = "COMPOSTABLE_ITEMS")
    static void setCompostables(List<Item> items) {
        throw new AssertionError();
    }
}

