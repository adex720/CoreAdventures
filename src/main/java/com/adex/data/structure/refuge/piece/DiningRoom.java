package com.adex.data.structure.refuge.piece;

import com.adex.block.ModBlocks;
import com.adex.data.structure.refuge.RefugePieces;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;

public class DiningRoom extends ElevenWideRoom {

    public DiningRoom(int index, int x, int y, int z, Direction direction) {
        super(RefugePieces.REFUGE_DINING_ROOM, index, getBaseBoundingBox(direction).moved(x, y, z), direction, new BlockPos(x, y, z));
    }

    public DiningRoom(CompoundTag compoundTag) {
        super(RefugePieces.REFUGE_DINING_ROOM, compoundTag);
    }

    @Override
    public void createBlocks(WorldGenLevel level, RandomSource random) {
        super.createBlocks(level, random);

        Direction clockWise = direction.getClockWise();
        Direction counterClockWise = direction.getCounterClockWise();

        BlockState stairFront = ModBlocks.JUNIPER_STAIRS.defaultBlockState().setValue(StairBlock.FACING, direction.getOpposite());
        BlockState stairBack = ModBlocks.JUNIPER_STAIRS.defaultBlockState().setValue(StairBlock.FACING, direction);
        BlockState slab = ModBlocks.JUNIPER_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM);

        // chandeliers
        generateChandelier(level, startPos.relative(counterClockWise, 2).relative(direction, 3).above(6), 2);
        generateChandelier(level, startPos.relative(clockWise, 2).relative(direction, 3).above(6), 2);
        generateChandelier(level, startPos.relative(counterClockWise, 2).relative(direction, 7).above(6), 2);
        generateChandelier(level, startPos.relative(clockWise, 2).relative(direction, 7).above(6), 2);

        // tables
        createTable(level, startPos.relative(counterClockWise, 2).relative(direction, 1).above(1), counterClockWise, direction, 3, 2, stairFront, stairBack, slab);
        createTable(level, startPos.relative(counterClockWise, 2).relative(direction, 6).above(1), counterClockWise, direction, 3, 2, stairFront, stairBack, slab);
        createTable(level, startPos.relative(clockWise, 2).relative(direction, 1).above(1), clockWise, direction, 3, 2, stairFront, stairBack, slab);
        createTable(level, startPos.relative(clockWise, 2).relative(direction, 6).above(1), clockWise, direction, 3, 2, stairFront, stairBack, slab);
    }

    /**
     * Creates a table from the specified blocks.
     * The table will be one block higher than the seats.
     * The seats will be one block int front and behind the table.
     *
     * @param level          WorldGenLevel
     * @param frontLeft      {@link BlockPos} of the front left seat
     * @param rightDirection {@link Direction} the table and seats continue to
     * @param backDirection  {@link  Direction} the back seat is from the front seat
     * @param tableWidth     Width of the table and the seats
     * @param tableDepth     Depth of the table
     * @param frontSeat      {@link BlockState} of the front seat
     * @param backSeat       {@link BlockState} of the back seat
     * @param table          {@link BlockState} of the table
     */
    public void createTable(WorldGenLevel level, BlockPos frontLeft, Direction rightDirection, Direction backDirection, int tableWidth, int tableDepth, BlockState frontSeat, BlockState backSeat, BlockState table) {
        fill(level, null, frontLeft, rightDirection, tableWidth, 1, _ -> frontSeat);
        fill(level, null, frontLeft.relative(backDirection, tableDepth + 1), rightDirection, tableWidth, 1, _ -> backSeat);
        fill(level, null, frontLeft.relative(backDirection, 1).above(1), rightDirection, backDirection, tableWidth, tableDepth, 1, _ -> table);
    }
}
