package com.adex.data.structure.refuge.piece;

import com.adex.data.structure.ModStructures;
import com.adex.data.structure.refuge.ContinuationPoint;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;

import java.util.List;

public class TurnLeft extends RefugePiece {

    private static final int WIDTH = 10;
    private static final int HEIGHT = 5;
    private static final int DEPTH = 10;

    private static final int OFFSET_X = -7;
    private static final int OFFSET_Y = 0;
    private static final int OFFSET_Z = 0;

    public TurnLeft(int index, BoundingBox boundingBox, Direction direction, BlockPos pos) {
        super(ModStructures.REFUGE_TURN_LEFT, index, boundingBox, direction, pos);
    }

    public TurnLeft(int index, int x, int y, int z, Direction direction) {
        this(index, getBaseBoundingBox(direction).moved(x, y, z), direction, new BlockPos(x, y, z));
    }

    public TurnLeft(CompoundTag compoundTag) {
        super(ModStructures.REFUGE_TURN_LEFT, compoundTag);
    }

    public static BoundingBox getBaseBoundingBox(Direction direction) {
        return RefugePiece.boundingBox(0, 0, 0, OFFSET_X, OFFSET_Y, OFFSET_Z, WIDTH, HEIGHT, DEPTH, direction);
    }

    @Override
    public List<ContinuationPoint> getContinuationPoints(BlockPos pos, Direction direction, int depth) {
        return List.of(ContinuationPoint.of(pos, direction, 7, -8, direction.getCounterClockWise(), depth));
    }

    @Override
    public void createBlocks(WorldGenLevel level, RandomSource random) {
        Direction counterClockWise = direction.getCounterClockWise();
        createWalls(level, random, startPos, direction, 9); // first corridor
        createWalls(level, random, startPos.relative(direction, 7).relative(counterClockWise, 2), counterClockWise, 6); // second corridor
        fill(level, random, startPos.relative(direction, 9).relative(counterClockWise, 2), counterClockWise, 5, 5, this::getWallBlock); // end of first corridor
    }
}
