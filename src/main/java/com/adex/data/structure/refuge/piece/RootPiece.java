package com.adex.data.structure.refuge.piece;

import com.adex.data.structure.ModStructures;
import com.adex.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;

import java.util.List;

public class RootPiece extends ThreeWayRoom {

    public RootPiece(RandomSource random, int x, int y, int z) {
        Direction direction = Util.randomCardinalDirection(random);
        super(0, x, y, z, direction);
        isSource = true;
    }

    public RootPiece(CompoundTag compoundTag) {
        super(ModStructures.ROOT_PIECE, compoundTag);
    }

    @Override
    public List<ContinuationPoint> getContinuationPoints(BlockPos pos, Direction direction, int depth) {
        return List.of(ContinuationPoint.of(pos, direction, 7, -8, direction.getCounterClockWise(), depth),
                ContinuationPoint.of(pos, direction, 7, 8, direction.getClockWise(), depth),
                ContinuationPoint.of(pos, direction, -1, 0, direction.getOpposite(), depth));
    }
}
