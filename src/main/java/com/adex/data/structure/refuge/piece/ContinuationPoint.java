package com.adex.data.structure.refuge.piece;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

public record ContinuationPoint(BlockPos pos, Direction direction, int depth) {

    public static ContinuationPoint of(BlockPos pos, Direction originalDirection, int frontOffset, int rightOffset, Direction newDirection, int depth) {
        return new ContinuationPoint(pos.relative(originalDirection, frontOffset).relative(originalDirection.getClockWise(), rightOffset), newDirection, depth);
    }
}
