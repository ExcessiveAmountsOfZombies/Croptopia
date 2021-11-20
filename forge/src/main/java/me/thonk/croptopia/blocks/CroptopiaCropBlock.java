package me.thonk.croptopia.blocks;


import me.thonk.croptopia.items.SeedItem;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CroptopiaCropBlock extends CropBlock {
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


    public CroptopiaCropBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState floor, BlockGetter getter, BlockPos pos) {
        return (super.mayPlaceOn(floor, getter, pos) || floor.is(BlockTags.DIRT) || floor.is(BlockTags.SAND)) && !floor.is(Blocks.DIRT);
    }

    @Override // JANK
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Biome.BiomeCategory category = level.getBiome(pos).getBiomeCategory();
        if (level.getChunk(pos).getStatus().getIndex() < ChunkStatus.FULL.getIndex()) {
            // ON WORLD GENERATION
            if (category.equals(seed.getCategory())) {
                return super.canSurvive(state, level, pos);
            }
        } else if (level.getChunk(pos).getStatus().getIndex() == ChunkStatus.FULL.getIndex()) {
            // ON PLAYER PLACEMENT
            return super.canSurvive(state, level, pos);
        }
        return false;

    }

    @Override
    public VoxelShape getShape(BlockState p_52297_, BlockGetter p_52298_, BlockPos p_52299_, CollisionContext p_52300_) {
        return SHAPE_BY_AGE[p_52297_.getValue(this.getAgeProperty())];
    }

    public void setSeed(SeedItem seed) {
        this.seed = seed;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return seed;
    }
}
