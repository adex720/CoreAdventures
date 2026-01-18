package com.adex.data.structure.refuge.piece;

import com.adex.block.ModBlocks;
import com.adex.data.structure.refuge.ContinuationPoint;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.function.Function;

public abstract class RefugePiece extends StructurePiece {

    public static final boolean PLACE_BOUNDING_BOX_DEBUG_END_RODS = false;

    protected final Direction direction;
    protected final BlockPos startPos;

    public RefugePiece(StructurePieceType type, int index, BoundingBox boundingBox, Direction direction, BlockPos startPos) {
        super(type, index, boundingBox);
        this.direction = direction;
        this.startPos = startPos;
        this.setOrientation(direction);
    }

    public RefugePiece(StructurePieceType structurePieceType, CompoundTag compoundTag) {
        super(structurePieceType, compoundTag);
        this.direction = Direction.from3DDataValue(compoundTag.getIntOr("Direction", 2));
        this.startPos = getPos(compoundTag);
    }

    public BlockPos getPos(CompoundTag compoundTag) {
        return new BlockPos(compoundTag.getIntOr("X", 0), compoundTag.getIntOr("Y", 0), compoundTag.getIntOr("Z", 0));
    }

    public Direction getDirection() {
        return direction;
    }

    public BlockPos getStartPos() {
        return startPos;
    }

    public static BoundingBox boundingBox(int startX, int startY, int startZ, int offsetX, int offsetY, int offsetZ, int width, int height, int depth, Direction direction) {
        BlockPos leftBottom = new BlockPos(startX, startY, startZ).relative(direction.getClockWise(), offsetX).relative(direction, offsetZ).above(offsetY);
        BlockPos rightTop = leftBottom.relative(direction.getClockWise(), width - 1).relative(direction, depth - 1).above(height - 1);
        return BoundingBox.fromCorners(leftBottom, rightTop);

        // BoundingBox#orientBox() doesn't work for 90 degree turns
    }

    @Override
    protected void addAdditionalSaveData(@NonNull StructurePieceSerializationContext structurePieceSerializationContext, @NonNull CompoundTag compoundTag) {
        compoundTag.putInt("Direction", direction.get3DDataValue());
        compoundTag.putInt("X", startPos.getX());
        compoundTag.putInt("Y", startPos.getY());
        compoundTag.putInt("Z", startPos.getZ());
    }

    @Override
    public void postProcess(@NonNull WorldGenLevel worldGenLevel, @NonNull StructureManager structureManager, @NonNull ChunkGenerator chunkGenerator, @NonNull RandomSource randomSource, @NonNull BoundingBox writeArea, @NonNull ChunkPos chunkPos, @NonNull BlockPos blockPos) {
        createBlocks(worldGenLevel, randomSource);

        if (PLACE_BOUNDING_BOX_DEBUG_END_RODS) {
            this.boundingBox.forAllCorners(pos -> worldGenLevel.setBlock(pos, Blocks.END_ROD.defaultBlockState(), 2));
        }
    }

    public abstract List<ContinuationPoint> getContinuationPoints(BlockPos pos, Direction direction, int depth);

    public abstract void createBlocks(WorldGenLevel level, RandomSource random);

    public void createWalls(WorldGenLevel level, RandomSource random, BlockPos startPos, Direction direction, int length) {
        if (direction.getAxis() == Direction.Axis.Y) {
            createHole(level, random, direction == Direction.UP ? startPos : startPos.below(length - 1), length);
            return;
        }

        List<BlockPos> walls = getWallLayer(startPos, direction.getClockWise());
        List<BlockPos> air = getInsideLayer(startPos, direction.getClockWise());

        for (int i = 0; i < length; i++) {
            for (BlockPos pos : walls) {
                level.setBlock(pos.relative(direction, i), getWallBlock(random), 2);
            }
            for (BlockPos pos : air) {
                level.setBlock(pos.relative(direction, i), Blocks.AIR.defaultBlockState(), 2);
            }
        }
    }

    public void createHole(WorldGenLevel level, RandomSource random, BlockPos startPos, int length) {
        List<BlockPos> walls = getHoleSideLayer(startPos);
        List<BlockPos> air = getHoleInsideLayer(startPos);

        for (int i = 0; i < length; i++) {
            for (BlockPos pos : walls) {
                level.setBlock(pos.above(i), getWallBlock(random), 2);
            }
            for (BlockPos pos : air) {
                level.setBlock(pos.above(i), Blocks.AIR.defaultBlockState(), 2);
            }
        }
    }

    public void createOneWideWalls(WorldGenLevel level, RandomSource random, BoundingBox boundingBox) {
        fill(level, random, new BoundingBox(boundingBox.minX(), boundingBox.minY(), boundingBox.minZ(), boundingBox.minX(), boundingBox.maxY(), boundingBox.maxZ()), this::getWallBlock);
        fill(level, random, new BoundingBox(boundingBox.maxX(), boundingBox.minY(), boundingBox.minZ(), boundingBox.maxX(), boundingBox.maxY(), boundingBox.maxZ()), this::getWallBlock);
        fill(level, random, new BoundingBox(boundingBox.minX() + 1, boundingBox.minY(), boundingBox.minZ(), boundingBox.maxX() - 1, boundingBox.maxY(), boundingBox.minZ()), this::getWallBlock);
        fill(level, random, new BoundingBox(boundingBox.minX() + 1, boundingBox.minY(), boundingBox.maxZ(), boundingBox.maxX() - 1, boundingBox.maxY(), boundingBox.maxZ()), this::getWallBlock);
        fill(level, random, new BoundingBox(boundingBox.minX() + 1, boundingBox.minY(), boundingBox.minZ() + 1, boundingBox.maxX() - 1, boundingBox.minY(), boundingBox.maxZ() - 1), this::getWallBlock);
        fill(level, random, new BoundingBox(boundingBox.minX() + 1, boundingBox.maxY(), boundingBox.minZ() + 1, boundingBox.maxX() - 1, boundingBox.maxY(), boundingBox.maxZ() - 1), this::getWallBlock);
        fill(level, random, boundingBox.inflatedBy(-1), this::air);
    }

    public void fill(WorldGenLevel level, RandomSource random, BoundingBox boundingBox, Function<RandomSource, BlockState> block) {
        for (int x = boundingBox.minX(); x <= boundingBox.maxX(); x++) {
            for (int y = boundingBox.minY(); y <= boundingBox.maxY(); y++) {
                for (int z = boundingBox.minZ(); z <= boundingBox.maxZ(); z++) {
                    level.setBlock(new BlockPos(x, y, z), block.apply(random), 2);
                }
            }
        }
    }

    /**
     * @param direction cardinal
     * @param offset    non-negative
     * @param yOffset   non-negative
     */
    public void fill(WorldGenLevel level, RandomSource random, BlockPos startPos, Direction direction, int offset, int yOffset, Function<RandomSource, BlockState> block) {
        for (int x = 0; x < offset; x++) {
            for (int y = 0; y < yOffset; y++) {
                level.setBlock(startPos.relative(direction, x).above(y), block.apply(random), 2);
            }
        }
    }

    public List<BlockPos> getWallLayer(BlockPos pos, Direction side) {
        return List.of(
                pos.relative(side, -2),
                pos.relative(side, -1),
                pos,
                pos.relative(side, 1),
                pos.relative(side, 2),
                pos.relative(side, -2).above(1),
                pos.relative(side, -2).above(2),
                pos.relative(side, -2).above(3),
                pos.relative(side, 2).above(1),
                pos.relative(side, 2).above(2),
                pos.relative(side, 2).above(3),
                pos.relative(side, -2).above(4),
                pos.relative(side, -1).above(4),
                pos.above(4),
                pos.relative(side, 1).above(4),
                pos.relative(side, 2).above(4));
    }

    public List<BlockPos> getInsideLayer(BlockPos pos, Direction side) {
        return List.of(
                pos.relative(side, -1).above(1),
                pos.above(1),
                pos.relative(side, 1).above(1),
                pos.relative(side, -1).above(2),
                pos.above(2),
                pos.relative(side, 1).above(2),
                pos.relative(side, -1).above(3),
                pos.above(3),
                pos.relative(side, 1).above(3));
    }

    public List<BlockPos> getHoleSideLayer(BlockPos pos) {
        return List.of(
                pos.relative(Direction.NORTH, -2).relative(Direction.EAST, -2),
                pos.relative(Direction.NORTH, -2).relative(Direction.EAST, -1),
                pos.relative(Direction.NORTH, -2),
                pos.relative(Direction.NORTH, -2).relative(Direction.EAST, 1),
                pos.relative(Direction.NORTH, -2).relative(Direction.EAST, 2),
                pos.relative(Direction.NORTH, -1).relative(Direction.EAST, 2),
                pos.relative(Direction.EAST, 2),
                pos.relative(Direction.NORTH, 1).relative(Direction.EAST, 2),
                pos.relative(Direction.NORTH, 2).relative(Direction.EAST, 2),
                pos.relative(Direction.NORTH, 2).relative(Direction.EAST, 1),
                pos.relative(Direction.NORTH, 2),
                pos.relative(Direction.NORTH, 2).relative(Direction.EAST, -1),
                pos.relative(Direction.NORTH, 2).relative(Direction.EAST, -2),
                pos.relative(Direction.NORTH, 1).relative(Direction.EAST, -2),
                pos.relative(Direction.EAST, -2),
                pos.relative(Direction.NORTH, -1).relative(Direction.EAST, -2));
    }

    public List<BlockPos> getHoleInsideLayer(BlockPos pos) {
        return List.of(
                pos.relative(Direction.NORTH, -1).relative(Direction.EAST, -1),
                pos.relative(Direction.NORTH, -1),
                pos.relative(Direction.NORTH, -1).relative(Direction.EAST, 1),
                pos.relative(Direction.EAST, -1),
                pos,
                pos.relative(Direction.EAST, 1),
                pos.relative(Direction.NORTH, 1).relative(Direction.EAST, -1),
                pos.relative(Direction.NORTH, 1),
                pos.relative(Direction.NORTH, 1).relative(Direction.EAST, 1));
    }

    /**
     * startPos = BlockPos of the middle lowest stair
     */
    public void createStairs(WorldGenLevel level, RandomSource random, BlockPos startPos, Direction direction, int length, Function<RandomSource, BlockState> blockGetter) {
        Direction clockWise = direction.getClockWise();
        for (int i = 0; i < length; i++) {
            // stairs
            level.setBlock(startPos.relative(direction.getCounterClockWise()).relative(direction, i).above(i), getStairBlock(direction, false), 2);
            level.setBlock(startPos.relative(direction, i).above(i), getStairBlock(direction, false), 2);
            level.setBlock(startPos.relative(clockWise).relative(direction, i).above(i), getStairBlock(direction, false), 2);

            // floor
            level.setBlock(startPos.relative(clockWise, -2).relative(direction, i).above(i - 1), blockGetter.apply(random), 2);
            level.setBlock(startPos.relative(clockWise, -1).relative(direction, i).above(i - 1), blockGetter.apply(random), 2);
            level.setBlock(startPos.relative(direction, i).above(i - 1), blockGetter.apply(random), 2);
            level.setBlock(startPos.relative(clockWise).relative(direction, i).above(i - 1), blockGetter.apply(random), 2);
            level.setBlock(startPos.relative(clockWise, 2).relative(direction, i).above(i - 1), blockGetter.apply(random), 2);

            // left wall
            level.setBlock(startPos.relative(clockWise, -2).relative(direction, i).above(i), blockGetter.apply(random), 2);
            level.setBlock(startPos.relative(clockWise, -2).relative(direction, i).above(i + 1), blockGetter.apply(random), 2);
            level.setBlock(startPos.relative(clockWise, -2).relative(direction, i).above(i + 2), blockGetter.apply(random), 2);
            level.setBlock(startPos.relative(clockWise, -2).relative(direction, i).above(i + 3), blockGetter.apply(random), 2);
            level.setBlock(startPos.relative(clockWise, -2).relative(direction, i).above(i + 4), blockGetter.apply(random), 2);

            // right wall
            level.setBlock(startPos.relative(clockWise, 2).relative(direction, i).above(i), blockGetter.apply(random), 2);
            level.setBlock(startPos.relative(clockWise, 2).relative(direction, i).above(i + 1), blockGetter.apply(random), 2);
            level.setBlock(startPos.relative(clockWise, 2).relative(direction, i).above(i + 2), blockGetter.apply(random), 2);
            level.setBlock(startPos.relative(clockWise, 2).relative(direction, i).above(i + 3), blockGetter.apply(random), 2);
            level.setBlock(startPos.relative(clockWise, 2).relative(direction, i).above(i + 4), blockGetter.apply(random), 2);

            // air
            level.setBlock(startPos.relative(clockWise, -1).relative(direction, i).above(i + 1), Blocks.AIR.defaultBlockState(), 2);
            level.setBlock(startPos.relative(clockWise, -1).relative(direction, i).above(i + 2), Blocks.AIR.defaultBlockState(), 2);
            level.setBlock(startPos.relative(clockWise, -1).relative(direction, i).above(i + 3), Blocks.AIR.defaultBlockState(), 2);
            level.setBlock(startPos.relative(direction, i).above(i + 1), Blocks.AIR.defaultBlockState(), 2);
            level.setBlock(startPos.relative(direction, i).above(i + 2), Blocks.AIR.defaultBlockState(), 2);
            level.setBlock(startPos.relative(direction, i).above(i + 3), Blocks.AIR.defaultBlockState(), 2);
            level.setBlock(startPos.relative(clockWise, 1).relative(direction, i).above(i + 1), Blocks.AIR.defaultBlockState(), 2);
            level.setBlock(startPos.relative(clockWise, 1).relative(direction, i).above(i + 2), Blocks.AIR.defaultBlockState(), 2);
            level.setBlock(startPos.relative(clockWise, 1).relative(direction, i).above(i + 3), Blocks.AIR.defaultBlockState(), 2);

            // ceiling
            level.setBlock(startPos.relative(clockWise, -1).relative(direction, i).above(i + 4), getStairBlock(direction.getOpposite(), true), 2);
            level.setBlock(startPos.relative(direction, i).above(i + 4), getStairBlock(direction.getOpposite(), true), 2);
            level.setBlock(startPos.relative(clockWise).relative(direction, i).above(i + 4), getStairBlock(direction.getOpposite(), true), 2);
        }
    }

    /**
     * @param startPos {@link BlockPos} of the middle normal floor block before the first slab, because the ceiling starts to rise there
     */
    public void createRisingSlabs(WorldGenLevel level, RandomSource random, BlockPos startPos, Direction direction, int length) {
        Direction clockWise = direction.getClockWise();

        for (int i = 0; i < length; i++) {
            if ((i & 1) == 1) {
                startPos = startPos.above(1);

                // floor
                level.setBlock(startPos.relative(clockWise, -1), getSlabBlock(false), 2);
                level.setBlock(startPos, getSlabBlock(false), 2);
                level.setBlock(startPos.relative(clockWise, 1), getSlabBlock(false), 2);

                // ceiling
                level.setBlock(startPos.relative(clockWise, -1).above(4), getWallBlock(random), 2);
                level.setBlock(startPos.above(4), getWallBlock(random), 2);
                level.setBlock(startPos.relative(clockWise, 1).above(4), getWallBlock(random), 2);
            } else {
                // floor
                level.setBlock(startPos.relative(clockWise, -1), getWallBlock(random), 2);
                level.setBlock(startPos, getWallBlock(random), 2);
                level.setBlock(startPos.relative(clockWise, 1), getWallBlock(random), 2);

                // ceiling
                level.setBlock(startPos.relative(clockWise, -1).above(4), getSlabBlock(true), 2);
                level.setBlock(startPos.above(4), getSlabBlock(true), 2);
                level.setBlock(startPos.relative(clockWise, 1).above(4), getSlabBlock(true), 2);
            }

            // walls
            level.setBlock(startPos.relative(clockWise, -2), getWallBlock(random), 2);
            level.setBlock(startPos.relative(clockWise, -2).above(1), getWallBlock(random), 2);
            level.setBlock(startPos.relative(clockWise, -2).above(2), getWallBlock(random), 2);
            level.setBlock(startPos.relative(clockWise, -2).above(3), getWallBlock(random), 2);
            level.setBlock(startPos.relative(clockWise, -2).above(4), getWallBlock(random), 2);
            level.setBlock(startPos.relative(clockWise, 2), getWallBlock(random), 2);
            level.setBlock(startPos.relative(clockWise, 2).above(1), getWallBlock(random), 2);
            level.setBlock(startPos.relative(clockWise, 2).above(2), getWallBlock(random), 2);
            level.setBlock(startPos.relative(clockWise, 2).above(3), getWallBlock(random), 2);
            level.setBlock(startPos.relative(clockWise, 2).above(4), getWallBlock(random), 2);

            // air
            level.setBlock(startPos.relative(clockWise, -1).above(1), Blocks.AIR.defaultBlockState(), 2);
            level.setBlock(startPos.relative(clockWise, -1).above(2), Blocks.AIR.defaultBlockState(), 2);
            level.setBlock(startPos.relative(clockWise, -1).above(3), Blocks.AIR.defaultBlockState(), 2);
            level.setBlock(startPos.above(1), Blocks.AIR.defaultBlockState(), 2);
            level.setBlock(startPos.above(2), Blocks.AIR.defaultBlockState(), 2);
            level.setBlock(startPos.above(3), Blocks.AIR.defaultBlockState(), 2);
            level.setBlock(startPos.relative(clockWise, 1).above(1), Blocks.AIR.defaultBlockState(), 2);
            level.setBlock(startPos.relative(clockWise, 1).above(2), Blocks.AIR.defaultBlockState(), 2);
            level.setBlock(startPos.relative(clockWise, 1).above(3), Blocks.AIR.defaultBlockState(), 2);

            startPos = startPos.relative(direction);
        }
    }

    public BlockState getWallBlock(RandomSource random) {
        return random.nextFloat() < 0.1f ? ModBlocks.CRACKED_HARDENED_STONE_BRICKS.defaultBlockState()
                : ModBlocks.HARDENED_STONE_BRICKS.defaultBlockState();
    }

    public BlockState getStairBlock(Direction direction, boolean top) {
        return ModBlocks.HARDENED_STONE_BRICKS_STAIRS.defaultBlockState()
                .setValue(StairBlock.FACING, direction)
                .setValue(StairBlock.HALF, top ? Half.TOP : Half.BOTTOM);
    }

    public BlockState getSlabBlock(boolean top) {
        return ModBlocks.HARDENED_STONE_BRICKS_SLAB.defaultBlockState()
                .setValue(SlabBlock.TYPE, top ? SlabType.TOP : SlabType.BOTTOM);
    }

    public BlockState air(RandomSource random) {
        return Blocks.AIR.defaultBlockState();
    }

}
