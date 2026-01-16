package com.adex.data.structure.refuge.piece;

import com.adex.data.structure.ModStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

import java.util.List;

public class EndPointPiece extends RefugePiece {

    /**
     * Direction is on the axis of the wall, can be left or right
     */
    public EndPointPiece(Direction direction, BlockPos startPos, int depth) {
        super(ModStructures.REFUGE_END_POINT, depth, BoundingBox.fromCorners(startPos.relative(direction, -1), startPos.above(3).relative(direction, 1)), direction, startPos);
    }

    public EndPointPiece(CompoundTag compoundTag) {
        super(ModStructures.REFUGE_END_POINT, compoundTag);
    }

    @Override
    public List<ContinuationPoint> getContinuationPoints(BlockPos pos, Direction direction, int depth) {
        return List.of();
    }

    @Override
    public void createBlocks(WorldGenLevel level, RandomSource random) {
        fill(level, random, boundingBox, this::getWallBlock);
    }
}
