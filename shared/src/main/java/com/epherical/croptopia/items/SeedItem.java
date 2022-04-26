package com.epherical.croptopia.items;

import com.epherical.croptopia.blocks.CroptopiaCropBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class SeedItem extends ItemNameBlockItem {

    private TagKey<Biome> category;

    public SeedItem(Block block, Properties settings, TagKey<Biome> category) {
        super(block, settings);
        ((CroptopiaCropBlock) block).setSeed(this);
        this.category = category;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockPos hitPos = context.getClickedPos();
        Level world = context.getLevel();
        BlockState state = world.getBlockState(hitPos);
        if (state.getBlock() instanceof FarmBlock && context.getClickedFace() == Direction.UP) {
            return super.useOn(context);
        }
        return InteractionResult.FAIL;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag context) {
        TranslatableComponent text = new TranslatableComponent("info.croptopia.seed");
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

    public TagKey<Biome> getCategory() {
        return category;
    }
}
