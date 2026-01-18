package com.adex.data.structure.refuge;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

public record ContinuationPoint(BlockPos pos, Direction direction, int depth) {

    public static ContinuationPoint of(BlockPos pos, Direction direction, int frontOffset, int depth) {
        return of(pos, direction, frontOffset, 0, direction, depth);
    }

    public static ContinuationPoint of(BlockPos pos, Direction originalDirection, int frontOffset, int rightOffset, Direction newDirection, int depth) {
        return of(pos, originalDirection, frontOffset, rightOffset, 0, newDirection, depth);
    }

    public static ContinuationPoint of(BlockPos pos, Direction originalDirection, int frontOffset, int rightOffset, int yOffset, Direction newDirection, int depth) {
        return new ContinuationPoint(pos.relative(originalDirection, frontOffset).relative(originalDirection.getClockWise(), rightOffset).above(yOffset), newDirection, depth);
    }
}
