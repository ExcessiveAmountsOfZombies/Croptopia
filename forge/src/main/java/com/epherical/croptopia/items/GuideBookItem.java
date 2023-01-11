package com.epherical.croptopia.items;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;
import vazkii.patchouli.api.PatchouliAPI;

import javax.annotation.Nullable;
import java.util.List;

public class GuideBookItem extends Item {


    public GuideBookItem(Item.Properties properties) {
        super(properties);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
        ItemStack stack = user.getItemInHand(hand);

        if (user instanceof ServerPlayer player && ModList.get().isLoaded("patchouli")) {
           PatchouliAPI.get().openBookGUI(player, ForgeRegistries.ITEMS.getKey(this));
        }

        return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> tooltip, TooltipFlag p_41424_) {
        if (!ModList.get().isLoaded("patchouli")) {
            tooltip.add(Component.nullToEmpty("Patchouli is not installed."));
        } else {
            tooltip.add(Component.nullToEmpty("let me know patchouli is updated either in my discord or in a Github Issue!"));
        }
    }
}
