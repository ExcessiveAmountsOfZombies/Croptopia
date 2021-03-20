package me.thonk.croptopia.items;

import me.thonk.croptopia.CroptopiaForge;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import vazkii.patchouli.api.PatchouliAPI;

public class GuideBookItem extends Item {


    public GuideBookItem(Properties properties) {
        super(properties);
    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getHeldItem(hand);

        if (user instanceof ServerPlayerEntity && CroptopiaForge.patchouli.isLoaded()) {
            ServerPlayerEntity player = (ServerPlayerEntity) user;
            PatchouliAPI.get().openBookGUI(player, ForgeRegistries.ITEMS.getKey(this));
        }

        return new ActionResult<>(ActionResultType.SUCCESS, stack);
    }

}
