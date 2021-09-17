package me.thonk.croptopia.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

import java.util.Random;

public class LeafCropBlock extends CroptopiaCropBlock {
    public static final IntProperty AGE = Properties.AGE_3;
    public static final IntProperty DISTANCE = Properties.DISTANCE_1_7;

    public LeafCropBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.fullCube();
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return new ItemStack(this);
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return true;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return true;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        int distance = getDistanceFromLog(neighborState) + 1;
        if (distance != 1 || state.get(DISTANCE) != distance) {
            world.getBlockTickScheduler().schedule(pos, this, 1);
        }

        return state;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (getAge(state) == getMaxAge()) {
            world.setBlockState(pos, this.withAge(0), 2);
            dropStacks(state, world, player.getBlockPos());
            return ActionResult.CONSUME;
        }
        return ActionResult.PASS;
    }

    @Override
    public int getMaxAge() {
        return 3;
    }

    @Override
    public IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE, DISTANCE);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getBaseLightLevel(pos, 0) >= 9) {
            int i = this.getAge(state);
            if (i < this.getMaxAge()) {
                if (random.nextInt(100) % 20 == 0) {
                    world.setBlockState(pos, this.withAge(i + 1), Block.NOTIFY_LISTENERS);
                }
            }
        }
        if (state.get(DISTANCE) == 7) {
            dropStacks(state, world, pos);
            world.removeBlock(pos, false);
        }
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        world.setBlockState(pos, updateDistanceFromLogs(state, world, pos), Block.NOTIFY_ALL);
    }

    @Override
    public int getOpacity(BlockState state, BlockView world, BlockPos pos) {
        return 1;
    }

    private static BlockState updateDistanceFromLogs(BlockState state, WorldAccess world, BlockPos pos) {
        int distance = 7;
        BlockPos.Mutable mutablePos = new BlockPos.Mutable();
        Direction[] directions = Direction.values();

        for (Direction direction : directions) {
            mutablePos.set(pos, direction);
            distance = Math.min(distance, getDistanceFromLog(world.getBlockState(mutablePos)) + 1);
            if (distance == 1) {
                break;
            }
        }

        return state.with(DISTANCE, distance);
    }

    private static int getDistanceFromLog(BlockState state) {
        if (state.isIn(BlockTags.LOGS)) {
            return 0;
        } else {
            return state.getBlock() instanceof LeafCropBlock || state.getBlock() instanceof LeavesBlock ? state.get(DISTANCE) : 7;
        }
    }
}
