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

public class EndPointPiece extends RefugePiece {

    /**
     * Direction is on the axis of the wall, can be left or right
     */
    public EndPointPiece(Direction direction, BlockPos startPos, int depth) {
        super(RefugePieces.REFUGE_END_POINT, depth, BoundingBox.fromCorners(startPos.relative(direction, -1), startPos.above(2).relative(direction, 1)), direction, startPos);
    }

    public EndPointPiece(int depth, int x, int y, int z, Direction direction) {
        this(direction.getClockWise(), new BlockPos(x, y+1, z), depth);
    }

    public EndPointPiece(CompoundTag compoundTag) {
        super(RefugePieces.REFUGE_END_POINT, compoundTag);
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
