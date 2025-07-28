package com.epherical.croptopia.blocks;

import com.epherical.croptopia.items.SeedItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.util.TriState;

public class CroptopiaCropBlock extends CropBlock {
    protected static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};

    private SeedItem seed;

    public CroptopiaCropBlock(Properties settings) {
        super(settings);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return AGE_TO_SHAPE[state.getValue(this.getAgeProperty())];
    }

    public void setSeed(SeedItem seed) {
        this.seed = seed;
    }

    @Override // JANK
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        if (level.getChunk(pos).getHighestGeneratedStatus().getIndex() < ChunkStatus.FULL.getIndex()) {
            // ON WORLD GENERATION
            if (seed.getCategory() != null && level.getBiome(pos).is(seed.getCategory())) {
                TriState soilDecision = level.getBlockState(pos.below()).canSustainPlant(level, pos.below(), Direction.UP, state);
                if (!soilDecision.isDefault()) {
                    return soilDecision.isTrue();
                } else {
                    return super.canSurvive(state, level, pos);
                }
            }
        } else if (level.getChunk(pos).getHighestGeneratedStatus().getIndex() == ChunkStatus.FULL.getIndex()) {
            // ON PLAYER PLACEMENT
            return super.canSurvive(state, level, pos);
        }
        return false;
    }

    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return super.mayPlaceOn(floor, world, pos)
                || floor.is(BlockTags.DIRT)
                || floor.is(BlockTags.SAND)
                || floor.is(Tags.Blocks.VILLAGER_FARMLANDS)
                || floor.is(com.epherical.croptopia.common.Tags.FARMLAND)
                || floor.is(com.epherical.croptopia.common.Tags.FARMLANDS);
    }


    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        if (getAge(state) == getMaxAge()) {
            player.awardStat(Stats.BLOCK_MINED.get(this));
            player.causeFoodExhaustion(0.005f);
            world.setBlock(pos, this.getStateForAge(0), 2);
            dropResources(state, world, pos);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void fallOn(Level world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        super.fallOn(world, state, pos, entity, fallDistance);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return seed != null ? seed : Items.AIR;
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return super.getAgeProperty();
    }
}
