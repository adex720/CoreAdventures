package com.adex.data.structure.refuge.piece;

import com.adex.data.loottable.ModLootTables;
import com.adex.data.structure.refuge.ContinuationPoint;
import com.adex.data.structure.refuge.RefugePieces;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

import java.util.List;

public class EndTrapChestRoom extends RefugePiece {

    private static final int WIDTH = 5;
    private static final int HEIGHT = 6;
    private static final int DEPTH = 3;

    private static final int OFFSET_X = -2;
    private static final int OFFSET_Y = -1;
    private static final int OFFSET_Z = 0;

    public EndTrapChestRoom(int index, BoundingBox boundingBox, Direction direction, BlockPos pos) {
        super(RefugePieces.REFUGE_END_TRAP_CHEST_ROOM, index, boundingBox, direction, pos);
    }

    public EndTrapChestRoom(int index, int x, int y, int z, Direction direction) {
        this(index, getBaseBoundingBox(direction).moved(x, y, z), direction, new BlockPos(x, y, z));
    }

    public EndTrapChestRoom(CompoundTag compoundTag) {
        super(RefugePieces.REFUGE_END_TRAP_CHEST_ROOM, compoundTag);
    }

    public static BoundingBox getBaseBoundingBox(Direction direction) {
        return RefugePiece.boundingBox(0, 0, 0, OFFSET_X, OFFSET_Y, OFFSET_Z, WIDTH, HEIGHT, DEPTH, direction);
    }

    @Override
    public List<ContinuationPoint> getContinuationPoints(BlockPos pos, Direction direction, int depth) {
        return List.of();
    }

    /**
     * A portal room has a 5x6 core portal whose frame blocks have a 75% chance of being reinforced ancient debris.
     */
    @Override
    public void createBlocks(WorldGenLevel level, RandomSource random) {
        Direction clockWise = direction.getClockWise();
        BlockState tnt = Blocks.TNT.defaultBlockState();

        // side walls
        createWalls(level, random, startPos, direction, 2);
        // end wall
        fill(level, random, startPos.relative(direction, 2).relative(clockWise, -2), clockWise, 5, 5, this::getWallBlock);
        // chest
        createTrapChest(level, random, startPos.relative(direction, 1).above(1), direction.getOpposite(), ModLootTables.REFUGE_BASIC);

        // tnt
        fill(level, random, startPos.relative(clockWise, -1).relative(direction, 1).below(1), direction, clockWise, 3, 3, _ -> tnt);
    }
}
