package com.adex.data.structure.refuge.piece;

import com.adex.data.structure.refuge.ContinuationPoint;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;

import java.util.List;

public class ElevenWideRoom extends RefugePiece {

    private static final int WIDTH = 11;
    private static final int HEIGHT = 8;
    private static final int DEPTH = 11;

    private static final int OFFSET_X = -5;
    private static final int OFFSET_Y = 0;
    private static final int OFFSET_Z = 0;

    public ElevenWideRoom(StructurePieceType type, int index, BoundingBox boundingBox, Direction direction, BlockPos startPos) {
        super(type, index, boundingBox, direction, startPos);
    }

    public ElevenWideRoom(StructurePieceType type, int index, int x, int y, int z, Direction direction) {
        this(type, index, getBaseBoundingBox(direction).moved(x, y, z), direction, new BlockPos(x, y, z));
    }

    public ElevenWideRoom(StructurePieceType type, CompoundTag compoundTag) {
        super(type, compoundTag);
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
        createOneWideWalls(level, random, boundingBox);
        Direction side = direction.getClockWise();
        fill(level, random, BoundingBox.fromCorners(startPos.above(1).relative(side, -1), startPos.above(3).relative(side, 1)), this::air);
    }
}
