package com.adex.data.structure.refuge.piece;

import com.adex.data.structure.ModStructures;
import com.adex.data.structure.refuge.ContinuationPoint;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

import java.util.List;

public class RisingThreeWayLeft extends RefugePiece {

    private static final int WIDTH = 7;
    private static final int HEIGHT = 9;
    private static final int DEPTH = 15;

    private static final int OFFSET_X = -4;
    private static final int OFFSET_Y = -4;
    private static final int OFFSET_Z = 0;

    public RisingThreeWayLeft(int index, BoundingBox boundingBox, Direction direction, BlockPos pos) {
        super(ModStructures.REFUGE_RISING_THREE_WAY_LEFT, index, boundingBox, direction, pos);
    }

    public RisingThreeWayLeft(int index, int x, int y, int z, Direction direction) {
        this(index, getBaseBoundingBox(direction).moved(x, y, z), direction, new BlockPos(x, y, z));
    }

    public RisingThreeWayLeft(CompoundTag compoundTag) {
        super(ModStructures.REFUGE_RISING_THREE_WAY_LEFT, compoundTag);
    }

    public static BoundingBox getBaseBoundingBox(Direction direction) {
        return RefugePiece.boundingBox(0, 0, 0, OFFSET_X, OFFSET_Y, OFFSET_Z, WIDTH, HEIGHT, DEPTH, direction);
    }

    @Override
    public List<ContinuationPoint> getContinuationPoints(BlockPos pos, Direction direction, int depth) {
        return List.of(ContinuationPoint.of(pos, direction, 7, -5, -2, direction.getCounterClockWise(), depth),
                ContinuationPoint.of(pos, direction, 15, 0, -4, direction, depth));
    }

    @Override
    public void createBlocks(WorldGenLevel level, RandomSource random) {
        Direction counterClockWise = direction.getCounterClockWise();
        Direction opposite = direction.getOpposite();
        createWalls(level, random, startPos, direction, 1); // entry door
        createRisingSlabs(level, random, startPos.relative(direction, 4).below(2), opposite, 4); // first stairs
        createWalls(level, random, startPos.relative(direction, 5).below(2), direction, 4); // middle corridor
        createRisingSlabs(level, random, startPos.relative(direction, 12).below(4), opposite, 4); // second stairs
        createWalls(level, random, startPos.relative(direction, 13).below(4), direction, 2); // end corridor

        createWalls(level, random, startPos.relative(direction, 7).relative(counterClockWise, 2).below(2), counterClockWise, 3); // left opening
    }
}
