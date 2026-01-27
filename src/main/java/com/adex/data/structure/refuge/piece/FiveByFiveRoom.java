package com.adex.data.structure.refuge.piece;

import com.adex.data.structure.refuge.ContinuationPoint;
import com.adex.data.structure.refuge.RefugePieces;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;

import java.util.List;

public class FiveByFiveRoom extends RefugePiece {

    private static final int WIDTH = 5;
    private static final int HEIGHT = 5;
    private static final int DEPTH = 5;

    private static final int OFFSET_X = -2;
    private static final int OFFSET_Y = 0;
    private static final int OFFSET_Z = 0;

    public FiveByFiveRoom(StructurePieceType type, int index, BoundingBox boundingBox, Direction direction, BlockPos pos) {
        super(type, index, boundingBox, direction, pos);
    }

    public FiveByFiveRoom(StructurePieceType type, CompoundTag compoundTag) {
        super(type, compoundTag);
    }

    public static BoundingBox getBaseBoundingBox(Direction direction) {
        return RefugePiece.boundingBox(0, 0, 0, OFFSET_X, OFFSET_Y, OFFSET_Z, WIDTH, HEIGHT, DEPTH, direction);
    }

    @Override
    public List<ContinuationPoint> getContinuationPoints(BlockPos pos, Direction direction, int depth) {
        return List.of();
    }

    /**
     * Creates walls and air
     */
    @Override
    public void createBlocks(WorldGenLevel level, RandomSource random) {
        createOneWideWalls(level, random, boundingBox);
        fill(level, random, startPos.relative(direction.getCounterClockWise(), 1).above(1), direction.getClockWise(), 3, 3, this::air);
    }
}
