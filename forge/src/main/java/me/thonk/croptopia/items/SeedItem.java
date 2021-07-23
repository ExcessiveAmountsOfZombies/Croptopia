package me.thonk.croptopia.items;

import me.thonk.croptopia.blocks.CroptopiaCropBlock;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Locale;

public class SeedItem extends BlockNamedItem {

    private Category category;

    public SeedItem(Block block, Properties settings, Category category) {
        super(block, settings);
        ((CroptopiaCropBlock) block).setSeed(this);
        this.category = category;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("info.croptopia.seed").setStyle(Style.EMPTY.setFormatting(TextFormatting.GRAY)).appendString(" ").appendString(category.getString().toLowerCase(Locale.ROOT)));
    }

    public Category getCategory() {
        return category;
    }
}
