package com.epherical.croptopia;

import com.epherical.croptopia.common.PlatformAdapter;
import net.minecraft.item.ItemGroup;

public class ForgeAdapter implements PlatformAdapter<ForgeAdapter> {

    @Override
    public ItemGroup getTab() {
        return CroptopiaForge.CROPTOPIA_ITEM_GROUP;
    }

    @Override
    public boolean skipHarvest() {
        return true;
    }
}
