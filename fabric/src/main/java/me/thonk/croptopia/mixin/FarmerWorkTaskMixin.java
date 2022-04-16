package me.thonk.croptopia.mixin;

import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import me.thonk.croptopia.Croptopia;
import me.thonk.croptopia.registry.Content;
import net.minecraft.entity.ai.brain.task.FarmerWorkTask;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(value = FarmerWorkTask.class, priority = 999)
public class FarmerWorkTaskMixin {

    @Unique
    private Object2IntArrayMap<Item> heldCrops = new Object2IntArrayMap<>();

    @Redirect(method = "craftAndDropBread", at = @At(value = "INVOKE", target = "Lnet/minecraft/inventory/SimpleInventory;count(Lnet/minecraft/item/Item;)I"))
    public int redirectCraftAndDropBread(SimpleInventory simpleInventory, Item item) {
        int count = simpleInventory.count(Items.WHEAT);
        List<Item> crops = Content.createCropStream().toList();
        for (int i = 0; i < simpleInventory.size(); ++i) {
            ItemStack inventoryItem = simpleInventory.getStack(i);
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

    @Redirect(method = "craftAndDropBread", at = @At(value = "INVOKE", target = "Lnet/minecraft/inventory/SimpleInventory;removeItem(Lnet/minecraft/item/Item;I)Lnet/minecraft/item/ItemStack;"))
    public ItemStack removeHeldCrops(SimpleInventory simpleInventory, Item item, int count) {
        // TODO: make this better, absolutely a bug and will just end up with deleted crops,
        //  I just need a way to remove crops from the villager inventory right now.
        for (Object2IntMap.Entry<Item> itemEntry : heldCrops.object2IntEntrySet()) {
            simpleInventory.removeItem(itemEntry.getKey(), count);
        }
        heldCrops.clear();
        return new ItemStack(item, count);
    }
}
