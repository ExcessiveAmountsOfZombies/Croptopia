package me.thonk.croptopia.events;

import com.mojang.math.Vector3d;
import me.thonk.croptopia.CroptopiaForge;
import me.thonk.croptopia.items.SeedItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;
import java.util.Random;

public class BlockBreakEvent {

    private static final Random random = new Random();

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        if (event.getState().isIn(Blocks.GRASS) || event.getState().isIn(Blocks.TALL_GRASS) ||
            event.getState().isIn(Blocks.FERN)  || event.getState().isIn(Blocks.LARGE_FERN)) {
            // i hate this, why won't it give me the blocks/grass in the damn loading loot table event when it clearly exists here.
            // it just works of course in fabric but no, we have to do stupid trash like this. WEEEEEEEEEEEEEEEE of course there is
            // nothing that tells me WHY the the ONLY FOUR LOOT TABLES I WANT don't appear but ok, you can have peony, poppy
            // and other similar blocks just fine!
            if (event.getWorld() instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld) event.getWorld();
                BlockPos pos = event.getPos();
                List<ItemStack> stack = event.getState().getDrops((new LootContext.Builder(serverWorld)).withRandom(serverWorld.rand).withParameter(LootParameters.field_237457_g_, Vector3d.copyCentered(pos)).withParameter(LootParameters.TOOL, ItemStack.EMPTY));

                Biome biome = event.getWorld().getBiome(event.getPos());
                for (SeedItem seed : CroptopiaForge.seeds) {
                    float value = random.nextFloat();
                    if (biome.getCategory().equals(seed.getCategory())) {
                        if (value > 0.99f) {
                            Block.spawnAsEntity(serverWorld, pos, new ItemStack(seed.asItem()));
                        }
                    }
                }
            }
        }

    }
}
