package com.adex.data.structure.refuge.piece;

import com.adex.block.ModBlocks;
import com.adex.data.structure.ModStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.function.Function;

public abstract class RefugePiece extends StructurePiece {

    protected boolean isSource;
    protected final Direction direction;
    protected final BlockPos startPos;

    public RefugePiece(int index, BoundingBox boundingBox, Direction direction, BlockPos startPos) {
        super(ModStructures.ROOT_PIECE, index, boundingBox);
        this.isSource = false;
        this.direction = direction;
        this.startPos = startPos;
        this.setOrientation(direction);
    }

    public RefugePiece(StructurePieceType structurePieceType, CompoundTag compoundTag) {
        super(structurePieceType, compoundTag);
        this.isSource = compoundTag.getBooleanOr("Source", false);
        this.direction = Direction.from3DDataValue(compoundTag.getIntOr("Direction", 2));
        this.startPos = getPos(compoundTag);

        //TODO: add start pos
    }

    public BlockPos getPos(CompoundTag compoundTag) {
        return new BlockPos(compoundTag.getIntOr("X", 0), compoundTag.getIntOr("Y", 0), compoundTag.getIntOr("Z", 0));
    }

    public Direction getDirection() {
        return direction;
    }

    public static BoundingBox boundingBox(int startX, int startY, int startZ, int offsetX, int offsetY, int offsetZ, int width, int height, int depth, Direction direction) {
        return switch (direction) {
            case NORTH -> boundingBox(startX - offsetX, startY - offsetY, startZ - offsetZ, width, height, -depth);
            case SOUTH -> boundingBox(startX - offsetX, startY - offsetY, startZ - offsetZ, width, height, depth);
            case WEST -> boundingBox(startZ - offsetZ, startY - offsetY, startX - offsetX, -depth, height, width);
            case EAST -> boundingBox(startZ - offsetZ, startY - offsetY, startX - offsetX, depth, height, width);
            default -> throw new IllegalStateException("Piece direction can't be UP or DOWN");
        };
    }

    public static BoundingBox boundingBox(int startX, int startY, int startZ, int width, int height, int depth) {
        width--;
        height--;
        depth--;
        if (width < 0) {
            width = -width;
            startX -= width;
        }
        if (height < 0) {
            height = -height;
            startY -= height;
        }
        if (depth < 0) {
            depth = -depth;
            startZ -= depth;
        }

        return new BoundingBox(startX, startY, startZ, startX + width - 1, startY + height - 1, startZ + depth - 1);
    }

    @Override
    protected void addAdditionalSaveData(@NonNull StructurePieceSerializationContext structurePieceSerializationContext, @NonNull CompoundTag compoundTag) {
        compoundTag.putInt("Direction", direction.get3DDataValue());
        compoundTag.putInt("X", startPos.getX());
        compoundTag.putInt("Y", startPos.getY());
        compoundTag.putInt("Z", startPos.getZ());
    }

    @Override
    public void postProcess(@NonNull WorldGenLevel worldGenLevel, @NonNull StructureManager structureManager, @NonNull ChunkGenerator chunkGenerator, @NonNull RandomSource randomSource, @NonNull BoundingBox boundingBox, @NonNull ChunkPos chunkPos, @NonNull BlockPos blockPos) {
        createBlocks(worldGenLevel, randomSource);
    }

    public abstract List<ContinuationPoint> getContinuationPoints(BlockPos pos, Direction direction, int depth);

    public abstract void createBlocks(WorldGenLevel level, RandomSource random);

    public void createWalls(WorldGenLevel level, RandomSource random, BlockPos startPos, Direction direction, int length) {
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

    public BlockState getWallBlock(RandomSource random) {
        return ModBlocks.HARDENED_STONE_BRICKS.defaultBlockState();
    }

    public BlockState air(RandomSource random) {
        return Blocks.AIR.defaultBlockState();
    }

}
