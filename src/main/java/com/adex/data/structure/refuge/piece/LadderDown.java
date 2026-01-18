package com.adex.data.structure.refuge.piece;

import com.adex.data.structure.ModStructures;
import com.adex.data.structure.refuge.ContinuationPoint;
import com.adex.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;

import java.util.List;

public class LadderDown extends RefugePiece {

    private static final int WIDTH = 5;
    private static final int HEIGHT = 11;
    private static final int DEPTH = 8;

    private static final int OFFSET_X = -2;
    private static final int OFFSET_Y = -6;
    private static final int OFFSET_Z = 0;

    public LadderDown(int index, BoundingBox boundingBox, Direction direction, BlockPos pos) {
        super(ModStructures.REFUGE_LADDER_DOWN, index, boundingBox, direction, pos);
    }

    public LadderDown(int index, int x, int y, int z, Direction direction) {
        this(index, getBaseBoundingBox(direction).moved(x, y, z), direction, new BlockPos(x, y, z));
    }

    public LadderDown(CompoundTag compoundTag) {
        super(ModStructures.REFUGE_LADDER_DOWN, compoundTag);
    }

    public static BoundingBox getBaseBoundingBox(Direction direction) {
        return RefugePiece.boundingBox(0, 0, 0, OFFSET_X, OFFSET_Y, OFFSET_Z, WIDTH, HEIGHT, DEPTH, direction);
    }

    @Override
    public List<ContinuationPoint> getContinuationPoints(BlockPos pos, Direction direction, int depth) {
        return List.of(ContinuationPoint.of(pos, direction, 8, 0, -6, direction, depth));
    }

    @Override
    public void createBlocks(WorldGenLevel level, RandomSource random) {
        createWalls(level, random, startPos, direction, 6); // upper corridor
        createWalls(level, random, startPos.relative(direction, 3).below(6), direction, 5); // lower corridor
        createWalls(level, random, startPos.relative(direction, 4), Direction.DOWN, 3); // vertical path

        Direction clockWise = direction.getClockWise();
        fill(level, random, startPos.relative(clockWise, -2).relative(direction, 6).above(), clockWise, 5, 4, this::getWallBlock); // end of upper corridor
        fill(level, random, startPos.relative(clockWise, -2).relative(direction, 2).below(6), clockWise, 5, 4, this::getWallBlock); // start of lower corridor

        LadderUp.createLadders(level, startPos.relative(direction, 3).below(5), 6, Util.getDirectionDifference(Direction.NORTH, direction)); // ladders
    }
}
