package com.epherical.croptopia;

import com.epherical.croptopia.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import vazkii.patchouli.api.PatchouliAPI;

public class GuideBookItem extends Item {

    public GuideBookItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level world, Player user, InteractionHand hand) {

        if (user instanceof ServerPlayer player && Services.PLATFORM.isModLoaded("patchouli")) {
            PatchouliAPI.get().openBookGUI(player, BuiltInRegistries.ITEM.getKey(this));
            player.sendSystemMessage(Component.nullToEmpty("Sorry, As of the publishing date of this build, Patchouli isn't available for 26.1!" +
                    " When it's available ask croptopia dev (me) for an update."));
        }

        return InteractionResult.SUCCESS;
    }
}
