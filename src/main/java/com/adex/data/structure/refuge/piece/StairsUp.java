package com.adex.data.structure.refuge.piece;

import com.adex.block.ModBlocks;
import com.adex.data.structure.ModStructures;
import com.adex.data.structure.refuge.ContinuationPoint;
import com.adex.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;

import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class StairsUp extends RefugePiece {

    private static final int WIDTH = 5;
    private static final int HEIGHT = 14;
    private static final int DEPTH = 15;

    private static final int OFFSET_X = -2;
    private static final int OFFSET_Y = 0;
    private static final int OFFSET_Z = 0;

    public StairsUp(int index, BoundingBox boundingBox, Direction direction, BlockPos pos) {
        super(ModStructures.REFUGE_STAIRS_UP, index, boundingBox, direction, pos);
    }

    public StairsUp(int index, int x, int y, int z, Direction direction) {
        this(index, getBaseBoundingBox(direction).moved(x, y, z), direction, new BlockPos(x, y, z));
    }

    public StairsUp(CompoundTag compoundTag) {
        super(ModStructures.REFUGE_STAIRS_UP, compoundTag);
    }

    public StairsUp(StructurePieceType type, int index, int x, int y, int z, Direction direction) {
        this(type, index, getBaseBoundingBox(direction).moved(x, y, z), direction, new BlockPos(x, y, z));
    }

    public StairsUp(StructurePieceType type, int index, BoundingBox boundingBox, Direction direction, BlockPos pos) {
        super(type, index, boundingBox, direction, pos);
    }

    public StairsUp(StructurePieceType type, CompoundTag compoundTag) {
        super(type, compoundTag);
    }

    public static BoundingBox getBaseBoundingBox(Direction direction) {
        return RefugePiece.boundingBox(0, 0, 0, OFFSET_X, OFFSET_Y, OFFSET_Z, WIDTH, HEIGHT, DEPTH, direction);
    }

    @Override
    public List<ContinuationPoint> getContinuationPoints(BlockPos pos, Direction direction, int depth) {
        return List.of(ContinuationPoint.of(pos, direction, 15, 0, 9, direction, depth));
    }

    @Override
    public void createBlocks(WorldGenLevel level, RandomSource random) {
        createWalls(level, random, startPos, direction, 3); // lower corridor
        createWalls(level, random, startPos.relative(direction, 12).above(9), direction, 3); // upper corridor
        createStairs(level, random, startPos.relative(direction, 3).above(1), direction, 9, this::getWallBlock); // stairs

        Direction clockWise = direction.getClockWise();
        fill(level, random, startPos.relative(clockWise, -2).relative(direction, 11).above(13), clockWise, 5, 1, this::getWallBlock); // first row of normal ceiling on upper floor

        fill(level, random, startPos.relative(direction, 2).relative(clockWise, -1).above(4), clockWise, 3, 1, _ -> getStairBlock(direction.getOpposite(), true)); // lowest row of ceiling stairs
    }
}
