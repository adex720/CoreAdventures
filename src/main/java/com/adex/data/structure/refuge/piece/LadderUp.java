package com.adex.data.structure.refuge.piece;

import com.adex.data.structure.refuge.ContinuationPoint;
import com.adex.data.structure.refuge.RefugePieces;
import com.adex.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

import java.util.List;

public class LadderUp extends RefugePiece {

    private static final int WIDTH = 5;
    private static final int HEIGHT = 11;
    private static final int DEPTH = 8;

    private static final int OFFSET_X = -2;
    private static final int OFFSET_Y = 0;
    private static final int OFFSET_Z = 0;

    public LadderUp(int index, BoundingBox boundingBox, Direction direction, BlockPos pos) {
        super(RefugePieces.REFUGE_LADDER_UP, index, boundingBox, direction, pos);
    }

    public LadderUp(int index, int x, int y, int z, Direction direction) {
        this(index, getBaseBoundingBox(direction).moved(x, y, z), direction, new BlockPos(x, y, z));
    }

    public LadderUp(CompoundTag compoundTag) {
        super(RefugePieces.REFUGE_LADDER_UP, compoundTag);
    }

    public static BoundingBox getBaseBoundingBox(Direction direction) {
        return RefugePiece.boundingBox(0, 0, 0, OFFSET_X, OFFSET_Y, OFFSET_Z, WIDTH, HEIGHT, DEPTH, direction);
    }

    @Override
    public List<ContinuationPoint> getContinuationPoints(BlockPos pos, Direction direction, int depth) {
        return List.of(ContinuationPoint.of(pos, direction, 8, 0, 6, direction, depth));
    }

    @Override
    public void createBlocks(WorldGenLevel level, RandomSource random) {
        createWalls(level, random, startPos, direction, 5); // lower corridor
        createWalls(level, random, startPos.relative(direction, 2).above(6), direction, 6); // upper corridor
        createWalls(level, random, startPos.relative(direction, 3).above(4), Direction.UP, 3); // vertical path

        Direction clockWise = direction.getClockWise();
        fill(level, random, startPos.relative(clockWise, -2).relative(direction, 5), clockWise, 5, 4, this::getWallBlock); // end of lowed corridor
        fill(level, random, startPos.relative(clockWise, -2).relative(direction, 1).above(8), clockWise, 5, 4, this::getWallBlock); // start of upper corridor

        LadderUp.createLadders(level, startPos.relative(direction, 4).above(1), 6, Util.getDirectionDifference(Direction.SOUTH, direction)); // ladders
    }

    public static void createLadders(WorldGenLevel level, BlockPos bottomPos, int height, Rotation ladderRotation) {
        BlockState state = Blocks.LADDER.defaultBlockState().rotate(ladderRotation);
        for (int dy = 0; dy < height; dy++) {
            level.setBlock(bottomPos.above(dy), state, 2);
        }
    }
}
