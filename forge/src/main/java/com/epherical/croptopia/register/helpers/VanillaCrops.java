package com.epherical.croptopia.register.helpers;

import com.epherical.croptopia.util.ItemConvertibleWithPlural;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;

import java.util.Objects;

public enum VanillaCrops implements ItemConvertibleWithPlural {
    APPLE(Items.APPLE),
    BEETROOT(Items.BEETROOT),
    CARROT(Items.CARROT),
    MELON(Items.MELON_SLICE),
    POTATO(Items.POTATO),
    PUMPKIN(Items.PUMPKIN),
    WHEAT(Items.WHEAT);

    private Item item;

    /**
     * @param source the vanilla crop, not <code>null</code>
     */
    VanillaCrops(IItemProvider source) {
        Objects.requireNonNull(source);
        this.item = source.asItem();
    }

    @Override
    public Item asItem() {
        return item;
    }

    @Override
    public boolean hasPlural() {
        return true;
    }
}
