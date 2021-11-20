package me.thonk.croptopia.items;

import me.thonk.croptopia.Croptopia;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
//import vazkii.patchouli.api.PatchouliAPI;

import java.util.List;

public class GuideBookItem extends Item {


    public GuideBookItem(Settings settings) {
        super(settings);
    }


    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (!Croptopia.patchouli.isLoaded()) {
            tooltip.add(Text.of("Patchouli is not installed."));
        }

    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        ItemStack stack = user.getStackInHand(hand);

        if (user instanceof ServerPlayerEntity && Croptopia.patchouli.isLoaded()) {
            ServerPlayerEntity player = (ServerPlayerEntity) user;
            //PatchouliAPI.get().openBookGUI(player, Registry.ITEM.getId(this));
        }

        return new TypedActionResult<>(ActionResult.SUCCESS, stack);
    }
}
