package com.epherical.croptopia.blocks;

import com.epherical.croptopia.CroptopiaMod;
import com.epherical.croptopia.items.SeedItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraftforge.common.Tags;

public class CroptopiaCropBlock extends CropsBlock {
    protected static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D)};

    private SeedItem seed;

    public CroptopiaCropBlock(Properties settings) {
        super(settings);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE_BY_AGE[state.getValue(this.getAgeProperty())];
    }

    public void setSeed(SeedItem seed) {
        this.seed = seed;
    }

    @Override // JANK
    public boolean canSurvive(BlockState state, IWorldReader world, BlockPos pos) {
        if (world.getChunk(pos).getStatus().getIndex() < ChunkStatus.FULL.getIndex()) {
            // ON WORLD GENERATION
            if (world.getBiome(pos).getBiomeCategory().equals(seed.getCategory())) {
                return super.canSurvive(state, world, pos);
            }
        } else if (world.getChunk(pos).getStatus().getIndex() == ChunkStatus.FULL.getIndex()) {
            // ON PLAYER PLACEMENT
            return super.canSurvive(state, world, pos);
        }
        return false;
    }
    protected boolean mayPlaceOn(BlockState floor, IBlockReader world, BlockPos pos) {
        return super.mayPlaceOn(floor, world, pos) || floor.is(Tags.Blocks.DIRT) || floor.is(BlockTags.SAND);
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        if (CroptopiaMod.getInstance().platform().skipHarvest()) {
            return super.use(state, world, pos, player, hand, hit);
        }
        if (getAge(state) == getMaxAge()) {
            //CroptopiaMod.getInstance().platform().afterBlockBroken(world, player, pos, state, null);
            player.awardStat(Stats.BLOCK_MINED.get(this));
            player.causeFoodExhaustion(0.005f);
            world.setBlock(pos, this.getStateForAge(0), 2);
            dropResources(state, world, pos);
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }

    @Override
    public void fallOn(World p_180658_1_, BlockPos p_180658_2_, Entity p_180658_3_, float p_180658_4_) {
        super.fallOn(p_180658_1_, p_180658_2_, p_180658_3_, p_180658_4_);
    }

    @Override
    protected IItemProvider getBaseSeedId() {
        return seed;
    }
}
