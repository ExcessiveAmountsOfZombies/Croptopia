package me.thonk.croptopia.events;

import com.mojang.math.Vector3d;
import me.thonk.croptopia.CroptopiaForge;
import me.thonk.croptopia.items.SeedItem;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;
import java.util.Random;

public class BlockBreakEvent {

    private static final Random random = new Random();

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        if (event.getState().is(Blocks.GRASS) || event.getState().is(Blocks.TALL_GRASS) ||
            event.getState().is(Blocks.FERN)  || event.getState().is(Blocks.LARGE_FERN)) {
            if (event.getWorld() instanceof ServerLevel) {
                ServerLevel serverWorld = (ServerLevel) event.getWorld();
                BlockPos pos = event.getPos();
                // todo: figure out why i was doing this?
                List<ItemStack> stack = event.getState().getDrops((new LootContext.Builder(serverWorld))
                        .withRandom(serverWorld.random)
                        .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
                        .withParameter(LootContextParams.TOOL, ItemStack.EMPTY));

                Biome biome = event.getWorld().getBiome(event.getPos());
                for (SeedItem seed : CroptopiaForge.seeds) {
                    float value = random.nextFloat();
                    if (biome.getBiomeCategory().equals(seed.getCategory())) {
                        if (value > 0.99f) {
                            Block.popResource(serverWorld, pos, new ItemStack(seed.asItem()));
                        }
                    }
                }
            }
        }

    }
}
