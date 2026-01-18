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

public class LoweringThreeWayMiddle extends RefugePiece {

    private static final int WIDTH = 15;
    private static final int HEIGHT = 9;
    private static final int DEPTH = 7;

    private static final int OFFSET_X = -7;
    private static final int OFFSET_Y = -2;
    private static final int OFFSET_Z = 0;

    public LoweringThreeWayMiddle(int index, BoundingBox boundingBox, Direction direction, BlockPos pos) {
        super(ModStructures.REFUGE_LOWERING_THREE_WAY_MIDDLE, index, boundingBox, direction, pos);
    }

    public LoweringThreeWayMiddle(int index, int x, int y, int z, Direction direction) {
        this(index, getBaseBoundingBox(direction).moved(x, y, z), direction, new BlockPos(x, y, z));
    }

    public LoweringThreeWayMiddle(CompoundTag compoundTag) {
        super(ModStructures.REFUGE_LOWERING_THREE_WAY_MIDDLE, compoundTag);
    }

    public LoweringThreeWayMiddle(StructurePieceType type, int index, int x, int y, int z, Direction direction) {
        this(type, index, getBaseBoundingBox(direction).moved(x, y, z), direction, new BlockPos(x, y, z));
    }

    public LoweringThreeWayMiddle(StructurePieceType type, int index, BoundingBox boundingBox, Direction direction, BlockPos pos) {
        super(type, index, boundingBox, direction, pos);
    }

    public static BoundingBox getBaseBoundingBox(Direction direction) {
        return RefugePiece.boundingBox(0, 0, 0, OFFSET_X, OFFSET_Y, OFFSET_Z, WIDTH, HEIGHT, DEPTH, direction);
    }

    @Override
    public List<ContinuationPoint> getContinuationPoints(BlockPos pos, Direction direction, int depth) {
        return List.of(ContinuationPoint.of(pos, direction, 4, -8, 2, direction.getCounterClockWise(), depth),
                ContinuationPoint.of(pos, direction, 4, 8, -2, direction.getClockWise(), depth));
    }

    @Override
    public void createBlocks(WorldGenLevel level, RandomSource random) {
        Direction clockWise = direction.getClockWise();
        Direction counterClockWise = direction.getCounterClockWise();
        BlockPos stairsStartPos = startPos.relative(direction, 4).relative(clockWise, 7).below(2);

        createWalls(level, random, stairsStartPos, counterClockWise, 1); // right door
        createRisingSlabs(level, random, stairsStartPos.relative(counterClockWise, 1), counterClockWise, 4); // right stairs
        createWalls(level, random, stairsStartPos.relative(counterClockWise, 5).above(2), counterClockWise, 4); // middle corridor
        createRisingSlabs(level, random, stairsStartPos.relative(counterClockWise, 9).above(2), counterClockWise, 4); // left stairs
        createWalls(level, random, stairsStartPos.relative(counterClockWise, 13).above(4), counterClockWise, 2); // left corridor

        createWalls(level, random, startPos, direction, 3); // entry corridor
    }
}
