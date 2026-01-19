package com.adex.data.structure.refuge.piece;

import com.adex.data.structure.refuge.ContinuationPoint;
import com.adex.data.structure.refuge.RefugePieces;
import com.adex.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

import java.util.List;

public class LadderHigh extends RefugePiece {

    private static final int WIDTH = 5;
    private static final int HEIGHT = 44;
    private static final int DEPTH = 7;

    private static final int OFFSET_X = -2;
    private static final int OFFSET_Y = 0;
    private static final int OFFSET_Z = 0;

    public LadderHigh(int index, BoundingBox boundingBox, Direction direction, BlockPos pos) {
        super(RefugePieces.REFUGE_LADDER_HIGH, index, boundingBox, direction, pos);
    }

    public LadderHigh(int index, int x, int y, int z, Direction direction) {
        this(index, getBaseBoundingBox(direction).moved(x, y, z), direction, new BlockPos(x, y, z));
    }

    public LadderHigh(CompoundTag compoundTag) {
        super(RefugePieces.REFUGE_LADDER_HIGH, compoundTag);
    }

    public static BoundingBox getBaseBoundingBox(Direction direction) {
        return RefugePiece.boundingBox(0, 0, 0, OFFSET_X, OFFSET_Y, OFFSET_Z, WIDTH, HEIGHT, DEPTH, direction);
    }

    @Override
    public List<ContinuationPoint> getContinuationPoints(BlockPos pos, Direction direction, int depth) {
        return List.of();
    }

    @Override
    public void createBlocks(WorldGenLevel level, RandomSource random) {
        createHole(level, random, startPos.relative(direction, 4).above(1), 42);
        createWalls(level, random, startPos, direction, 3); // start corridor

        BlockPos ceilingMiddle = startPos.relative(direction, 4).above(43);
        fill(level, random, new BoundingBox(ceilingMiddle).inflatedBy(2, 0, 2), this::getWallBlock); // ceiling
        fill(level, random, startPos.relative(direction, 3).relative(direction.getCounterClockWise(), 2), direction, direction.getClockWise(), 4, 5, this::getWallBlock); // floor under ladder

        LadderUp.createLadders(level, startPos.relative(direction, 5).above(1), 42, Util.getDirectionDifference(Direction.SOUTH, direction)); // ladders
    }
}
