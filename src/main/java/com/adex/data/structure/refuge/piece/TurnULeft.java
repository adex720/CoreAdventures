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

public class TurnULeft extends RefugePiece {

    private static final int WIDTH = 15;
    private static final int HEIGHT = 5;
    private static final int DEPTH = 9;

    private static final int OFFSET_X = -2;
    private static final int OFFSET_Y = 0;
    private static final int OFFSET_Z = 0;

    public TurnULeft(int index, BoundingBox boundingBox, Direction direction, BlockPos pos) {
        super(RefugePieces.REFUGE_TURN_U_LEFT, index, boundingBox, direction, pos);
    }

    public TurnULeft(int index, int x, int y, int z, Direction direction) {
        this(index, getBaseBoundingBox(direction).moved(x, y, z), direction, new BlockPos(x, y, z));
    }

    public TurnULeft(CompoundTag compoundTag) {
        super(RefugePieces.REFUGE_TURN_U_LEFT, compoundTag);
    }

    public static BoundingBox getBaseBoundingBox(Direction direction) {
        return RefugePiece.boundingBox(0, 0, 0, OFFSET_X, OFFSET_Y, OFFSET_Z, WIDTH, HEIGHT, DEPTH, direction);
    }

    @Override
    public List<ContinuationPoint> getContinuationPoints(BlockPos pos, Direction direction, int depth) {
        return List.of(ContinuationPoint.of(pos, direction, -1, 10, direction.getOpposite(), depth));
    }

    @Override
    public void createBlocks(WorldGenLevel level, RandomSource random) {
        Direction clockWise = direction.getClockWise();
        // back corridor
        createWalls(level, random, startPos.relative(direction, 6).relative(clockWise, -1), clockWise, 13);
        // left end of back corridor
        fill(level, random, startPos.relative(direction, 5).relative(clockWise, -2), direction, 4, 5, this::getWallBlock);
        // right end of back corridor
        fill(level, random, startPos.relative(direction, 5).relative(clockWise, 12), direction, 4, 5, this::getWallBlock);
        // left corridor
        createWalls(level, random, startPos, direction, 5);
        // right corridor
        createWalls(level, random, startPos.relative(clockWise, 10), direction, 5);
    }
}
