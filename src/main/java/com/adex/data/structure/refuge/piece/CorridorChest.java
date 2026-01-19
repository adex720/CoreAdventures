package com.adex.data.structure.refuge.piece;

import com.adex.data.loottable.ModLootTables;
import com.adex.data.structure.refuge.RefugePieces;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

public class CorridorChest extends CorridorLong {

    private static final int WIDTH = 5;
    private static final int HEIGHT = 5;
    private static final int DEPTH = 15;

    private static final int OFFSET_X = -2;
    private static final int OFFSET_Y = 0;
    private static final int OFFSET_Z = 0;

    public CorridorChest(int index, BoundingBox boundingBox, Direction direction, BlockPos pos) {
        super(RefugePieces.REFUGE_CORRIDOR_CHEST, index, boundingBox, direction, pos);
    }

    public CorridorChest(int index, int x, int y, int z, Direction direction) {
        this(index, getBaseBoundingBox(direction).moved(x, y, z), direction, new BlockPos(x, y, z));
    }

    public CorridorChest(CompoundTag compoundTag) {
        super(RefugePieces.REFUGE_CORRIDOR_CHEST, compoundTag);
    }

    public static BoundingBox getBaseBoundingBox(Direction direction) {
        return RefugePiece.boundingBox(0, 0, 0, OFFSET_X, OFFSET_Y, OFFSET_Z, WIDTH, HEIGHT, DEPTH, direction);
    }

    @Override
    public void createBlocks(WorldGenLevel level, RandomSource random) {
        super.createBlocks(level, random);
        BlockPos chestPos = startPos.relative(direction, 7).relative(direction.getClockWise(), -1).above();
        createChest(level, random, chestPos, direction.getClockWise(), ModLootTables.REFUGE_BASIC);
    }
}
