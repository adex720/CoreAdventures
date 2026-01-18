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

public class LoweringThreeWayRight extends RefugePiece {

    private static final int WIDTH = 7;
    private static final int HEIGHT = 9;
    private static final int DEPTH = 15;

    private static final int OFFSET_X = -2;
    private static final int OFFSET_Y = -4;
    private static final int OFFSET_Z = 0;

    public LoweringThreeWayRight(int index, BoundingBox boundingBox, Direction direction, BlockPos pos) {
        super(ModStructures.REFUGE_LOWERING_THREE_WAY_RIGHT, index, boundingBox, direction, pos);
    }

    public LoweringThreeWayRight(int index, int x, int y, int z, Direction direction) {
        this(index, getBaseBoundingBox(direction).moved(x, y, z), direction, new BlockPos(x, y, z));
    }

    public LoweringThreeWayRight(CompoundTag compoundTag) {
        super(ModStructures.REFUGE_LOWERING_THREE_WAY_RIGHT, compoundTag);
    }

    public LoweringThreeWayRight(StructurePieceType type, int index, BoundingBox boundingBox, Direction direction, BlockPos pos) {
        super(type, index, boundingBox, direction, pos);
    }

    public static BoundingBox getBaseBoundingBox(Direction direction) {
        return RefugePiece.boundingBox(0, 0, 0, OFFSET_X, OFFSET_Y, OFFSET_Z, WIDTH, HEIGHT, DEPTH, direction);
    }

    @Override
    public List<ContinuationPoint> getContinuationPoints(BlockPos pos, Direction direction, int depth) {
        return List.of(ContinuationPoint.of(pos, direction, 7, 5, -2, direction.getClockWise(), depth),
                ContinuationPoint.of(pos, direction, 15, 0, -4, direction, depth));
    }

    @Override
    public void createBlocks(WorldGenLevel level, RandomSource random) {
        Direction clockWise = direction.getClockWise();
        Direction opposite = direction.getOpposite();
        createWalls(level, random, startPos, direction, 1); // entry door
        createRisingSlabs(level, random, startPos.relative(direction, 4).below(2), opposite, 4); // first stairs
        createWalls(level, random, startPos.relative(direction, 5).below(2), direction, 4); // middle corridor
        createRisingSlabs(level, random, startPos.relative(direction, 12).below(4), opposite, 4); // second stairs
        createWalls(level, random, startPos.relative(direction, 13).below(4), direction, 2); // end corridor

        createWalls(level, random, startPos.relative(direction, 7).relative(clockWise, 2).below(2), clockWise, 3); // left opening
    }
}
