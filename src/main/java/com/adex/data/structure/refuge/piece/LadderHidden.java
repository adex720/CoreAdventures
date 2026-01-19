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

public class LadderHidden extends RefugePiece {

    private static final int WIDTH = 17;
    private static final int HEIGHT = 25;
    private static final int DEPTH = 17;

    private static final int OFFSET_X = -8;
    private static final int OFFSET_Y = 0;
    private static final int OFFSET_Z = 0;

    public LadderHidden(int index, BoundingBox boundingBox, Direction direction, BlockPos pos) {
        super(RefugePieces.REFUGE_LADDER_HIDDEN, index, boundingBox, direction, pos);
    }

    public LadderHidden(int index, int x, int y, int z, Direction direction) {
        this(index, getBaseBoundingBox(direction).moved(x, y, z), direction, new BlockPos(x, y, z));
    }

    public LadderHidden(CompoundTag compoundTag) {
        super(RefugePieces.REFUGE_LADDER_HIDDEN, compoundTag);
    }

    public static BoundingBox getBaseBoundingBox(Direction direction) {
        return RefugePiece.boundingBox(0, 0, 0, OFFSET_X, OFFSET_Y, OFFSET_Z, WIDTH, HEIGHT, DEPTH, direction);
    }

    @Override
    public List<ContinuationPoint> getContinuationPoints(BlockPos pos, Direction direction, int depth) {
        return List.of(ContinuationPoint.of(pos, direction, 17, 0, 20, direction, depth),
                ContinuationPoint.of(pos, direction, 17, 0, 10, direction, depth),
                ContinuationPoint.of(pos, direction, 8, 9, 10, direction.getClockWise(), depth),
                ContinuationPoint.of(pos, direction, -1, 0, 10, direction.getOpposite(), depth),
                ContinuationPoint.of(pos, direction, 8, -9, 10, direction.getCounterClockWise(), depth));
    }

    @Override
    public void createBlocks(WorldGenLevel level, RandomSource random) {
        Direction clockWise = direction.getClockWise();

        createWalls(level, random, startPos, direction, 10); // lower corridor
        fill(level, random, startPos.relative(direction, 10).relative(clockWise, -2), clockWise, 5, 4, this::getWallBlock); // end of lower corridor

        createWalls(level, random, startPos.relative(direction, 7).above(20), direction, 10); // upper corridor
        fill(level, random, startPos.relative(direction, 6).relative(clockWise, -2).above(21), clockWise, 5, 4, this::getWallBlock); // start of upper corridor

        createWalls(level, random, startPos.relative(direction, 3).relative(clockWise, -4).above(10), direction, 11); // left corridor
        fill(level, random, startPos.relative(direction, 2).relative(clockWise, -6).above(10), clockWise, 4, 5, this::getWallBlock); // start of left corridor
        fill(level, random, startPos.relative(direction, 14).relative(clockWise, -6).above(10), clockWise, 4, 5, this::getWallBlock); // end of left corridor

        createWalls(level, random, startPos.relative(direction, 3).relative(clockWise, 4).above(10), direction, 11); // right corridor
        fill(level, random, startPos.relative(direction, 2).relative(clockWise, 3).above(10), clockWise, 4, 5, this::getWallBlock); // start of right corridor
        fill(level, random, startPos.relative(direction, 14).relative(clockWise, 3).above(10), clockWise, 4, 5, this::getWallBlock); // end of right corridor

        createWalls(level, random, startPos.relative(direction, 4).relative(clockWise, -2).above(10), clockWise, 5); // front corridor
        createWalls(level, random, startPos.relative(direction, 12).relative(clockWise, -2).above(10), clockWise, 5); // end corridor

        createWalls(level, random, startPos.above(10), direction, 3); // front entrance
        createWalls(level, random, startPos.relative(direction, 14).above(10), direction, 3); // end entrance
        createWalls(level, random, startPos.relative(direction, 8).relative(clockWise, -8).above(10), clockWise, 3); // left entrance
        createWalls(level, random, startPos.relative(direction, 8).relative(clockWise, 6).above(10), clockWise, 3); // right entrance

        createHole(level, random, startPos.relative(direction, 8).above(4), 17); // ladder hole
        LadderUp.createLadders(level, startPos.relative(direction, 9).above(1), 20, Util.getDirectionDifference(Direction.SOUTH, direction)); // ladders
    }
}
