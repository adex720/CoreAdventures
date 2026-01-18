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

public class EightWay extends RefugePiece {

    private static final int WIDTH = 15;
    private static final int HEIGHT = 5;
    private static final int DEPTH = 15;

    private static final int OFFSET_X = -3;
    private static final int OFFSET_Y = 0;
    private static final int OFFSET_Z = 0;

    public EightWay(int index, BoundingBox boundingBox, Direction direction, BlockPos pos) {
        super(RefugePieces.REFUGE_EIGHT_WAY, index, boundingBox, direction, pos);
    }

    public EightWay(int index, int x, int y, int z, Direction direction) {
        this(index, getBaseBoundingBox(direction).moved(x, y, z), direction, new BlockPos(x, y, z));
    }

    public EightWay(CompoundTag compoundTag) {
        super(RefugePieces.REFUGE_EIGHT_WAY, compoundTag);
    }

    public static BoundingBox getBaseBoundingBox(Direction direction) {
        return RefugePiece.boundingBox(0, 0, 0, OFFSET_X, OFFSET_Y, OFFSET_Z, WIDTH, HEIGHT, DEPTH, direction);
    }

    @Override
    public List<ContinuationPoint> getContinuationPoints(BlockPos pos, Direction direction, int depth) {
        return List.of(ContinuationPoint.of(pos, direction, 3, -4, direction.getCounterClockWise(), depth),
                ContinuationPoint.of(pos, direction, 11, -4, direction.getCounterClockWise(), depth),
                ContinuationPoint.of(pos, direction, 15, 0, direction, depth),
                ContinuationPoint.of(pos, direction, 15, 8, direction, depth),
                ContinuationPoint.of(pos, direction, 11, 12, direction.getClockWise(), depth),
                ContinuationPoint.of(pos, direction, 3, 12, direction.getClockWise(), depth),
                ContinuationPoint.of(pos, direction, -1, 8, direction.getOpposite(), depth));
    }

    @Override
    public void createBlocks(WorldGenLevel level, RandomSource random) {
        Direction clockWise = direction.getClockWise();
        createWalls(level, random, startPos, direction, 15);
        createWalls(level, random, startPos.relative(clockWise, 8), direction, 15);

        createWalls(level, random, startPos.relative(clockWise, 2).relative(direction, 7), clockWise, 5);

        createWalls(level, random, startPos.relative(clockWise, -3).relative(direction, 3), clockWise, 2);
        createWalls(level, random, startPos.relative(clockWise, -3).relative(direction, 11), clockWise, 2);
        createWalls(level, random, startPos.relative(clockWise, 10).relative(direction, 11), clockWise, 2);
        createWalls(level, random, startPos.relative(clockWise, 10).relative(direction, 3), clockWise, 2);
    }
}
