package com.epherical.croptopia.items;

import com.epherical.croptopia.Croptopia;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import vazkii.patchouli.api.PatchouliAPI;

import java.util.List;
import java.util.function.Consumer;

public class GuideBookItem extends Item {


    public GuideBookItem(Properties settings) {
        super(settings);
    }


    @Override
    public void appendHoverText(ItemStack item, TooltipContext context, TooltipDisplay display, Consumer<Component> list, TooltipFlag flag) {
        if (!Croptopia.patchouli.isLoaded()) {
            list.accept(Component.nullToEmpty("Patchouli is not installed."));
        }
    }

    @Override
    public InteractionResult use(Level world, Player user, InteractionHand hand) {

        ItemStack stack = user.getItemInHand(hand);

        if (user instanceof ServerPlayer && Croptopia.patchouli.isLoaded()) {
            ServerPlayer player = (ServerPlayer) user;
            PatchouliAPI.get().openBookGUI(player, BuiltInRegistries.ITEM.getKey(this));
        }

        return InteractionResult.SUCCESS.heldItemTransformedTo(stack);
    }
}
