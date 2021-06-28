package me.thonk.croptopia.items;

import me.thonk.croptopia.blocks.CroptopiaCropBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome.Category;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Locale;

public class SeedItem extends AliasedBlockItem {

    private Category category;

    public SeedItem(Block block, Settings settings, Category category) {
        super(block, settings);
        ((CroptopiaCropBlock) block).setSeedsItem(this);
        this.category = category;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos hitPos = context.getBlockPos();
        World world = context.getWorld();
        BlockState state = world.getBlockState(hitPos);
        if (state.isOf(Blocks.FARMLAND)) {
            return super.useOnBlock(context);
        }
        return ActionResult.FAIL;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("info.croptopia.seed").setStyle(Style.EMPTY.withColor(Formatting.GRAY)).append(" ").append(category.asString().toLowerCase(Locale.ROOT)));
    }

    public Category getCategory() {
        return category;
    }
}
