package com.epherical.croptopia.items;

import com.epherical.croptopia.blocks.CroptopiaCropBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import javax.annotation.Nullable;
import java.util.List;

public class SeedItem extends BlockNamedItem {

    private Biome.Category category;

    public SeedItem(Block block, Properties settings, Biome.Category category) {
        super(block, settings);
        ((CroptopiaCropBlock) block).setSeed(this);
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        BlockPos hitPos = context.getClickedPos();
        World world = context.getLevel();
        BlockState state = world.getBlockState(hitPos);
        if (state.getBlock() instanceof FarmlandBlock && context.getClickedFace() == Direction.UP) {
            return super.useOn(context);
        }
        return ActionResultType.FAIL;
    }


    @Override
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag context) {
        TranslationTextComponent text = new TranslationTextComponent("info.croptopia.seed");
        String[] translated = text.getString().split("\n");
        /*if (translated.length >= 2) {
            tooltip.add(new TextComponent(translated[0]).setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)));
            tooltip.add(new TextComponent(translated[1]).setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY))
                    .append(" ").append(category.get(0).getSerializedName().toLowerCase(Locale.ROOT)));
        } else {
            tooltip.add(new TranslatableComponent("info.croptopia.seed").setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY))
                    .append(" ").append(category.get(0).getSerializedName().toLowerCase(Locale.ROOT)));
        }*/
    }

    public Biome.Category getCategory() {
        return category;
    }
}
