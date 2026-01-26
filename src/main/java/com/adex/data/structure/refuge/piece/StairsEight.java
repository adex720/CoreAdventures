package com.adex.data.structure.refuge.piece;

import com.adex.data.structure.refuge.ContinuationPoint;
import com.adex.data.structure.refuge.RefugePieces;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

import java.util.List;

public class StairsEight extends RefugePiece {

    private static final int WIDTH = 15;
    private static final int HEIGHT = 25;
    private static final int DEPTH = 15;

    private static final int OFFSET_X = -7;
    private static final int OFFSET_Y = -12;
    private static final int OFFSET_Z = 0;

    public StairsEight(int index, BoundingBox boundingBox, Direction direction, BlockPos pos) {
        super(RefugePieces.REFUGE_STAIRS_EIGHT, index, boundingBox, direction, pos);
    }

    public StairsEight(int index, int x, int y, int z, Direction direction) {
        this(index, getBaseBoundingBox(direction).moved(x, y, z), direction, new BlockPos(x, y, z));
    }

    public StairsEight(CompoundTag compoundTag) {
        super(RefugePieces.REFUGE_STAIRS_EIGHT, compoundTag);
    }

    public static BoundingBox getBaseBoundingBox(Direction direction) {
        return RefugePiece.boundingBox(0, 0, 0, OFFSET_X, OFFSET_Y, OFFSET_Z, WIDTH, HEIGHT, DEPTH, direction);
    }

    @Override
    public List<ContinuationPoint> getContinuationPoints(BlockPos pos, Direction direction, int depth) {
        return List.of(ContinuationPoint.of(pos, direction, -1, 0, -12, direction.getOpposite(), depth),
                ContinuationPoint.of(pos, direction, 7, 8, -9, direction.getClockWise(), depth),
                ContinuationPoint.of(pos, direction, 15, 0, -6, direction, depth),
                ContinuationPoint.of(pos, direction, 7, -8, -3, direction.getCounterClockWise(), depth),
                ContinuationPoint.of(pos, direction, 7, 8, 3, direction.getClockWise(), depth),
                ContinuationPoint.of(pos, direction, 15, 0, 6, direction, depth),
                ContinuationPoint.of(pos, direction, 7, -8, 9, direction.getCounterClockWise(), depth));
    }

    @Override
    public void createBlocks(WorldGenLevel level, RandomSource random) {
        Direction clockWise = direction.getClockWise();
        Direction counterClockWise = direction.getCounterClockWise();

        // stairs
        BlockPos bottomStart = startPos.below(12).relative(direction, 2).relative(counterClockWise, 1);
        Direction stairDirection = clockWise;
        for (int i = 0; i < 7; i++) {
            createOneFloor(bottomStart, stairDirection, level, random);
            bottomStart = bottomStart.relative(stairDirection, 6).relative(stairDirection.getCounterClockWise(), 4).above(3);
            stairDirection = stairDirection.getCounterClockWise();
        }

        // left wall of lowest entry
        fill(level, random, startPos.below(12).relative(counterClockWise, 2), direction, 5, 5, this::getWallBlock);

        // top entry
        createWalls(level, random, bottomStart, stairDirection, 3);
        // top right
        fill(level, random, bottomStart.relative(stairDirection, 3).relative(stairDirection.getCounterClockWise(), 2), stairDirection.getClockWise(), 5, 5, this::getWallBlock);
        // top entry
        fill(level, random, bottomStart.relative(stairDirection.getClockWise(), 2).above(1), stairDirection, 3, 3, this::air);
    }

    /**
     * Generates an entry and stairs until the next entry.
     *
     * @param pos       {@link BlockPos} of the left middle of the entry deck
     * @param direction Direction the stairs continue from the deck
     * @param level     WorldGenLevel
     * @param random    RandomSource
     */
    public void createOneFloor(BlockPos pos, Direction direction, WorldGenLevel level, RandomSource random) {
        Direction clockWise = direction.getClockWise();
        Direction counterClockWise = direction.getCounterClockWise();

        BlockState slabBottom = getSlabBlock(false);
        BlockState slabTop = getSlabBlock(true);

        // deck
        createWalls(level, random, pos, direction, 3);
        // entry door
        fill(level, random, pos.relative(clockWise, 2).above(1), direction, 3, 3, this::air);
        // entry door frame
        level.setBlock(pos.relative(direction, 3).relative(clockWise, 2), getWallBlock(random), 2);

        //stairs
        fill(level, random, pos.relative(direction, 3).relative(counterClockWise, 1).above(1), clockWise, 3, 1, _ -> slabBottom);
        fill(level, random, pos.relative(direction, 4).relative(counterClockWise, 1).above(1), clockWise, 3, 1, this::getWallBlock);
        fill(level, random, pos.relative(direction, 5).relative(counterClockWise, 1).above(2), direction, clockWise, 3, 3, _ -> slabBottom);
        fill(level, random, pos.relative(direction, 5).relative(counterClockWise, 2).above(2), direction, 3, 1, this::getWallBlock);
        fill(level, random, pos.relative(direction, 5).relative(counterClockWise, 3).above(3), direction, 3, 1, _ -> slabBottom);

        // inner walls
        fill(level, random, pos.relative(direction, 3).relative(counterClockWise, 2).above(1), direction, 2, 5, this::getWallBlock);
        level.setBlock(pos.relative(direction, 3).relative(counterClockWise, 2), getWallBlock(random), 2);
        level.setBlock(pos.relative(direction, 4).relative(counterClockWise, 2).above(6), getWallBlock(random), 2);
        fill(level, random, pos.relative(direction, 4).relative(counterClockWise, 3).above(2), counterClockWise, 1, 5, this::getWallBlock);

        // outer walls
        fill(level, random, pos.relative(direction, 3).relative(clockWise, 2).above(1), direction, 2, 5, this::getWallBlock);
        level.setBlock(pos.relative(direction, 3).relative(clockWise, 2), getWallBlock(random), 2);
        fill(level, random, pos.relative(direction, 5).relative(clockWise, 2).above(2), direction, 4, 5, this::getWallBlock);
        fill(level, random, pos.relative(direction, 8).relative(clockWise, 1).above(2), counterClockWise, 4, 5, this::getWallBlock);
        fill(level, random, pos.relative(direction, 8).relative(counterClockWise, 3).above(3), counterClockWise, 1, 5, this::getWallBlock);

        // ceiling
        fill(level, random, pos.relative(direction, 2).relative(counterClockWise, 1).above(4), clockWise, 3, 1, _ -> slabTop);
        fill(level, random, pos.relative(direction, 3).relative(counterClockWise, 1).above(5), clockWise, 3, 1, this::getWallBlock);
        fill(level, random, pos.relative(direction, 4).relative(counterClockWise, 1).above(5), clockWise, 3, 1, _ -> slabTop);
        fill(level, random, pos.relative(direction, 5).relative(counterClockWise, 1).above(6), direction, clockWise, 3, 3, this::getWallBlock);
        fill(level, random, pos.relative(direction, 5).relative(counterClockWise, 2).above(6), direction, 3, 1, _ -> slabTop);
        fill(level, random, pos.relative(direction, 5).relative(counterClockWise, 3).above(7), direction, 3, 1, this::getWallBlock);

        // air
        fill(level, random, pos.relative(direction, 3).relative(counterClockWise, 1).above(2), clockWise, direction, 3, 2, 3, this::air);
        fill(level, random, pos.relative(direction, 5).relative(counterClockWise, 2).above(3), clockWise, direction, 4, 3, 3, this::air);
        fill(level, random, pos.relative(direction, 5).relative(counterClockWise, 3).above(4), direction, 3, 3, this::air);
    }
}
