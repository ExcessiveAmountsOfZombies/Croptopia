package com.epherical.croptopia.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class LeafCropBlock extends CroptopiaCropBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    public static final IntegerProperty DISTANCE = BlockStateProperties.DISTANCE;
    public static final BooleanProperty PERSISTENT = BlockStateProperties.PERSISTENT;

    public LeafCropBlock(Properties settings) {
        super(settings);
        registerDefaultState(stateDefinition.any().setValue(AGE, 0)
                .setValue(DISTANCE, 1)
                .setValue(PERSISTENT, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.block();
    }


    @Override
    public ItemStack getCloneItemStack(LevelReader $$0, BlockPos $$1, BlockState $$2) {
        return new ItemStack(this);
    }

    @Override
    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return true;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return true;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
        int distance = getDistanceFromLog(neighborState) + 1;
        if (distance != 1 || state.getValue(DISTANCE) != distance) {
            world.scheduleTick(pos, this, 1);
        }

        return state;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        if (state.getValue(PERSISTENT)) {
            return false;
        } else {
            return super.isValidBonemealTarget(level, pos, state);
        }
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(PERSISTENT, true)
                .setValue(AGE, 0);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        if (getAge(state) == getMaxAge() && !state.getValue(PERSISTENT)) {
            player.awardStat(Stats.BLOCK_MINED.get(this));
            player.causeFoodExhaustion(0.005f);
            world.setBlock(pos, this.getStateForAge(0), 2);
            world.gameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Context.of(player, state));
            if (world instanceof ServerLevel) {
                for (ItemStack droppedStack : getDrops(state, (ServerLevel) world, pos, null)) {
                    popResourceFromFace(world, pos, hit.getDirection(), droppedStack);
                }
                return InteractionResult.CONSUME;
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
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
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE, DISTANCE, PERSISTENT);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return !state.getValue(PERSISTENT);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (!state.getValue(PERSISTENT)) {
            if (world.getRawBrightness(pos, 0) >= 9) {
                int i = this.getAge(state);
                if (i < this.getMaxAge()) {
                    if (random.nextInt(100) % 20 == 0) {
                        world.setBlock(pos, this.getStateForAge(i + 1), Block.UPDATE_CLIENTS);
                    }
                }
            }
            if (state.getValue(DISTANCE) == 7) {
                dropResources(state, world, pos);
                world.removeBlock(pos, false);
            }
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        world.setBlock(pos, updateDistanceFromLogs(state, world, pos), Block.UPDATE_ALL);
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter world, BlockPos pos) {
        return 1;
    }

    private static BlockState updateDistanceFromLogs(BlockState state, LevelAccessor world, BlockPos pos) {
        int distance = 7;
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
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
