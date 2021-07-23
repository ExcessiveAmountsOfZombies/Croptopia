package me.thonk.croptopia.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public class LeafCropBlock extends CroptopiaCropBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    public static final IntegerProperty DISTANCE = BlockStateProperties.DISTANCE;

    public LeafCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState p_52297_, BlockGetter p_52298_, BlockPos p_52299_, CollisionContext p_52300_) {
        return Shapes.block();
    }

    @Override
    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
        return new ItemStack(this);
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return true;
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        return true;
    }

    @Override
    public BlockState updatePostPlacement(BlockState state, Direction direction, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos) {
        int distance = getDistance(neighborState) + 1;
        if (distance != 1 || state.get(DISTANCE) != distance) {
            world.getPendingBlockTicks().scheduleTick(pos, this, 1);
        }

        return state;
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
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE, DISTANCE);
    }

    @Override
    public boolean ticksRandomly(BlockState state) {
        return state.get(DISTANCE) == 7;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(DISTANCE) == 7) {
            spawnDrops(state, world, pos);
            world.removeBlock(pos, false);
        }
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        worldIn.setBlockState(pos, updateDistance(state, worldIn, pos), 3);
    }

    private static BlockState updateDistance(BlockState state, IWorld world, BlockPos pos) {
        int distance = 7;
        BlockPos.Mutable mutablePos = new BlockPos.Mutable();
        Direction[] directions = Direction.values();

        for (Direction direction : directions) {
            mutablePos.setAndMove(pos, direction);
            distance = Math.min(distance, getDistance(world.getBlockState(mutablePos)) + 1);
            if (distance == 1) {
                break;
            }
        }

        return state.with(DISTANCE, distance);
    }

    private static int getDistance(BlockState state) {
        if (state.isIn(BlockTags.LOGS)) {
            return 0;
        } else {
            return state.getBlock() instanceof LeafCropBlock || state.getBlock() instanceof LeavesBlock ? state.get(DISTANCE) : 7;
        }
    }
}
