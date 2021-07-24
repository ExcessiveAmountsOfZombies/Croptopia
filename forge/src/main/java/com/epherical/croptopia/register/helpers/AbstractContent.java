package com.epherical.croptopia.register.helpers;


import com.epherical.croptopia.util.RegisterFunction;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractContent<T> {
    private final List<AbstractContent<?>> CONTENT = new ArrayList<>();


    public AbstractContent() {
        CONTENT.add(this);
    }


    public abstract void registerBlocks(RegisterFunction<Block> register);

    public abstract void registerItems(RegisterFunction<Item> register);

    public List<T> getContent() {
        return (List<T>) CONTENT;
    }


}
