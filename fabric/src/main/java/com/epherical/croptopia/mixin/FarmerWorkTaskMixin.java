package com.epherical.croptopia.mixin;

import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import com.epherical.croptopia.registry.Content;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.ai.behavior.WorkAtComposter;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(value = WorkAtComposter.class, priority = 999)
public class FarmerWorkTaskMixin {

    @Unique
    private Object2IntArrayMap<Item> heldCrops = new Object2IntArrayMap<>();

    @Redirect(method = "makeBread", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/SimpleContainer;countItem(Lnet/minecraft/world/item/Item;)I"))
    public int redirectCraftAndDropBread(SimpleContainer simpleInventory, Item item) {
        int count = simpleInventory.countItem(Items.WHEAT);
        List<Item> crops = Content.createCropStream().toList();
        for (int i = 0; i < simpleInventory.getContainerSize(); ++i) {
            ItemStack inventoryItem = simpleInventory.getItem(i);
            Item regularItem = inventoryItem.getItem();
            if (crops.contains(regularItem) && inventoryItem.getCount() > 0) {
                if (heldCrops.containsKey(regularItem)) {
                    heldCrops.put(regularItem,heldCrops.getInt(regularItem) + inventoryItem.getCount());
                } else {
                    heldCrops.put(regularItem, inventoryItem.getCount());
                }
                count += inventoryItem.getCount();
            }
        }
        return count;
    }

    @Redirect(method = "makeBread", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/SimpleContainer;removeItemType(Lnet/minecraft/world/item/Item;I)Lnet/minecraft/world/item/ItemStack;"))
    public ItemStack removeHeldCrops(SimpleContainer simpleInventory, Item item, int count) {
        // TODO: make this better, absolutely a bug and will just end up with deleted crops,
        //  I just need a way to remove crops from the villager inventory right now.
        for (Object2IntMap.Entry<Item> itemEntry : heldCrops.object2IntEntrySet()) {
            simpleInventory.removeItemType(itemEntry.getKey(), count);
        }
        heldCrops.clear();
        return new ItemStack(item, count);
    }
}
