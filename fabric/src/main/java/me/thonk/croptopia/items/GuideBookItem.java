package me.thonk.croptopia.items;

import me.thonk.croptopia.Croptopia;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import vazkii.patchouli.api.PatchouliAPI;

import java.util.List;

public class GuideBookItem extends Item {


    public GuideBookItem(Properties settings) {
        super(settings);
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag context) {
        if (!Croptopia.patchouli.isLoaded()) {
            tooltip.add(Component.nullToEmpty("Patchouli is not installed."));
        }

    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {

        ItemStack stack = user.getItemInHand(hand);

        if (user instanceof ServerPlayer && Croptopia.patchouli.isLoaded()) {
            ServerPlayer player = (ServerPlayer) user;
            PatchouliAPI.get().openBookGUI(player, Registry.ITEM.getKey(this));
        }

        return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
    }
}
