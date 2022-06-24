package com.epherical.croptopia.blocks;

import com.epherical.croptopia.CroptopiaMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.Constants;

import java.util.Random;

public class LeafCropBlock extends CroptopiaCropBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    public static final IntegerProperty DISTANCE = BlockStateProperties.DISTANCE;

    public LeafCropBlock(Properties settings) {
        super(settings);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.block();
    }

    @Override
    public ItemStack getCloneItemStack(IBlockReader world, BlockPos pos, BlockState state) {
        return new ItemStack(this);
    }

    @Override
    protected boolean mayPlaceOn(BlockState floor, IBlockReader world, BlockPos pos) {
        return true;
    }

    @Override
    public boolean canSurvive(BlockState state, IWorldReader world, BlockPos pos) {
        return true;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos) {
        int distance = getDistanceFromLog(neighborState) + 1;
        if (distance != 1 || state.getValue(DISTANCE) != distance) {
            world.getBlockTicks().scheduleTick(pos, this, 1);
        }

        return state;
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        if (CroptopiaMod.getInstance().platform().skipHarvest()) {
            // this is so we just use a forge event. only fabric will proceed beyond this point
            return super.use(state, world, pos, player, hand, hit);
        }
        if (getAge(state) == getMaxAge()) {
            player.awardStat(Stats.BLOCK_MINED.get(this));
            player.causeFoodExhaustion(0.005f);
            world.setBlock(pos, this.getStateForAge(0), 2);
            if (world instanceof ServerWorld) {
                for (ItemStack droppedStack : getDrops(state, (ServerWorld) world, pos, null)) {
                    popResource(world, pos, droppedStack);
                }
                return ActionResultType.CONSUME;
            }
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }

    @Override
    public int getMaxAge() {
        return 3;
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE, DISTANCE);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getRawBrightness(pos, 0) >= 9) {
            int i = this.getAge(state);
            if (i < this.getMaxAge()) {
                if (random.nextInt(100) % 20 == 0) {

                    world.setBlock(pos, this.getStateForAge(i + 1), Constants.BlockFlags.DEFAULT);
                }
            }
        }
        if (state.getValue(DISTANCE) == 7) {
            dropResources(state, world, pos);
            world.removeBlock(pos, false);
        }
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        worldIn.setBlock(pos, updateDistanceFromLogs(state, worldIn, pos), 3);
    }

    @Override
    public int getLightBlock(BlockState state, IBlockReader world, BlockPos pos) {
        return 1;
    }

    private static BlockState updateDistanceFromLogs(BlockState state, IWorld world, BlockPos pos) {
        int distance = 7;
        BlockPos.Mutable mutablePos = new BlockPos.Mutable();
        Direction[] directions = Direction.values();

        for (Direction direction : directions) {
            mutablePos.setWithOffset(pos, direction);
            distance = Math.min(distance, getDistanceFromLog(world.getBlockState(mutablePos)) + 1);
            if (distance == 1) {
                break;
            }
        }

        return state.setValue(DISTANCE, distance);
    }

    private static int getDistanceFromLog(BlockState state) {
        if (state.is(BlockTags.LOGS)) {
            return 0;
        } else {
            return state.getBlock() instanceof LeafCropBlock || state.getBlock() instanceof LeavesBlock ? state.getValue(DISTANCE) : 7;
        }
    }
}
