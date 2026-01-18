package com.adex.data.structure.refuge.piece;

import com.adex.data.structure.refuge.ContinuationPoint;
import com.adex.data.structure.refuge.RefugePieces;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

import java.util.List;

public class RisingThreeWayMiddle extends RefugePiece {

    private static final int WIDTH = 15;
    private static final int HEIGHT = 9;
    private static final int DEPTH = 7;

    private static final int OFFSET_X = -7;
    private static final int OFFSET_Y = -2;
    private static final int OFFSET_Z = 0;

    public RisingThreeWayMiddle(int index, BoundingBox boundingBox, Direction direction, BlockPos pos) {
        super(RefugePieces.REFUGE_RISING_THREE_WAY_MIDDLE, index, boundingBox, direction, pos);
    }

    public RisingThreeWayMiddle(int index, int x, int y, int z, Direction direction) {
        this(index, getBaseBoundingBox(direction).moved(x, y, z), direction, new BlockPos(x, y, z));
    }

    public RisingThreeWayMiddle(CompoundTag compoundTag) {
        super(RefugePieces.REFUGE_RISING_THREE_WAY_MIDDLE, compoundTag);
    }

    public static BoundingBox getBaseBoundingBox(Direction direction) {
        return RefugePiece.boundingBox(0, 0, 0, OFFSET_X, OFFSET_Y, OFFSET_Z, WIDTH, HEIGHT, DEPTH, direction);
    }

    @Override
    public List<ContinuationPoint> getContinuationPoints(BlockPos pos, Direction direction, int depth) {
        return List.of(ContinuationPoint.of(pos, direction, 4, -8, -2, direction.getCounterClockWise(), depth),
                ContinuationPoint.of(pos, direction, 4, 8, 2, direction.getClockWise(), depth));
    }

    @Override
    public void createBlocks(WorldGenLevel level, RandomSource random) {
        Direction clockWise = direction.getClockWise();
        Direction counterClockWise = direction.getCounterClockWise();
        BlockPos stairsStartPos = startPos.relative(direction, 4).relative(counterClockWise, 7).below(2);

        createWalls(level, random, stairsStartPos, clockWise, 1); // right door
        createRisingSlabs(level, random, stairsStartPos.relative(clockWise, 1), clockWise, 4); // right stairs
        createWalls(level, random, stairsStartPos.relative(clockWise, 5).above(2), clockWise, 4); // middle corridor
        createRisingSlabs(level, random, stairsStartPos.relative(clockWise, 9).above(2), clockWise, 4); // left stairs
        createWalls(level, random, stairsStartPos.relative(clockWise, 13).above(4), clockWise, 2); // left corridor

        createWalls(level, random, startPos, direction, 3); // entry corridor
    }
}
