package com.epherical.croptopia.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
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
    public ActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getItemInHand(hand);

        if (user instanceof ServerPlayerEntity && ModList.get().isLoaded("patchouli")) {
            PatchouliAPI.get().openBookGUI((ServerPlayerEntity) user, ForgeRegistries.ITEMS.getKey(this));
        }

        return new ActionResult<>(ActionResultType.SUCCESS, stack);
    }

    @Override
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
        if (!ModList.get().isLoaded("patchouli")) {
            p_77624_3_.add(ITextComponent.nullToEmpty("Patchouli is not installed."));
        } else {
            p_77624_3_.add(ITextComponent.nullToEmpty("let me know patchouli is updated either in my discord or in a Github Issue!"));
        }
    }
}
