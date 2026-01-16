package com.adex.data.structure.refuge.piece;

import com.adex.data.structure.ModStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;

import java.util.List;

public class ThreeWayRoom extends RefugePiece {

    private static final int WIDTH = 15;
    private static final int HEIGHT = 5;
    private static final int DEPTH = 15;

    private static final int OFFSET_X = -7;
    private static final int OFFSET_Y = 0;
    private static final int OFFSET_Z = 0;

    public ThreeWayRoom(int index, BoundingBox boundingBox, Direction direction, BlockPos pos) {
        super(ModStructures.REFUGE_THREE_WAY, index, boundingBox, direction, pos);
    }

    public ThreeWayRoom(int index, int x, int y, int z, Direction direction) {
        this(index, getBaseBoundingBox(direction).moved(x, y, z), direction, new BlockPos(x, y, z));
    }

    public ThreeWayRoom(CompoundTag compoundTag) {
        super(ModStructures.REFUGE_THREE_WAY, compoundTag);
    }

    public ThreeWayRoom(StructurePieceType type, CompoundTag compoundTag) {
        super(type, compoundTag);
    }

    public ThreeWayRoom(StructurePieceType type, int index, int x, int y, int z, Direction direction) {
        this(type, index, getBaseBoundingBox(direction).moved(x, y, z), direction, new BlockPos(x, y, z));
    }

    public ThreeWayRoom(StructurePieceType type, int index, BoundingBox boundingBox, Direction direction, BlockPos pos) {
        super(type, index, boundingBox, direction, pos);
    }

    public static BoundingBox getBaseBoundingBox(Direction direction) {
        return RefugePiece.boundingBox(0, 0, 0, OFFSET_X, OFFSET_Y, OFFSET_Z, WIDTH, HEIGHT, DEPTH, direction);
    }

    @Override
    public List<ContinuationPoint> getContinuationPoints(BlockPos pos, Direction direction, int depth) {
        return List.of(ContinuationPoint.of(pos, direction, 7, -8, direction.getCounterClockWise(), depth),
                ContinuationPoint.of(pos, direction, 7, 8, direction.getClockWise(), depth));
    }

    @Override
    public void createBlocks(WorldGenLevel level, RandomSource random) {
        Direction side = direction.getClockWise();
        createWalls(level, random, startPos.relative(direction, 7).relative(side, -7), side, 15);
        createWalls(level, random, startPos, direction, 6);
    }
}
