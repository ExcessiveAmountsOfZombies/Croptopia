package com.epherical.croptopia.items;

import com.epherical.croptopia.blocks.CroptopiaCropBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.Tags;

import java.util.List;

import static com.epherical.croptopia.common.Tags.*;
import static com.epherical.croptopia.common.Tags.FARMLANDS;
import static net.neoforged.neoforge.common.Tags.Blocks.VILLAGER_FARMLANDS;

public class SeedItem extends ItemNameBlockItem {

    private TagKey<Biome> category;

    public SeedItem(Block block, Properties settings, TagKey<Biome> category) {
        super(block, settings);
        if (block instanceof CroptopiaCropBlock crop) {
            crop.setSeed(this);
        }
        this.category = category;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockPos hitPos = context.getClickedPos();
        Level world = context.getLevel();
        BlockState state = world.getBlockState(hitPos);
        if (state.is(VILLAGER_FARMLANDS) ||
            state.is(FARMLANDS) ||
            state.is(FARMLANDS)) {
            return super.useOn(context);
        }
        return InteractionResult.FAIL;
    }

    public TagKey<Biome> getCategory() {
        return category;
    }
}
