package com.adex.data.structure.refuge.piece;

import com.adex.data.structure.refuge.RefugePieces;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StonecutterBlock;
import net.minecraft.world.level.block.state.BlockState;

public class SmeltingRoom extends ElevenWideRoom {

    public SmeltingRoom(int index, int x, int y, int z, Direction direction) {
        super(RefugePieces.REFUGE_SMELTING_ROOM, index, x, y, z, direction);
    }

    public SmeltingRoom(CompoundTag compoundTag) {
        super(RefugePieces.REFUGE_SMELTING_ROOM, compoundTag);
    }

    @Override
    public void createBlocks(WorldGenLevel level, RandomSource random) {
        super.createBlocks(level, random);

        Direction clockWise = direction.getClockWise();
        Direction counterClockWise = direction.getCounterClockWise();

        BlockState furnaceLeft = Blocks.BLAST_FURNACE.defaultBlockState().setValue(StonecutterBlock.FACING, clockWise);
        BlockState furnaceRight = Blocks.BLAST_FURNACE.defaultBlockState().setValue(StonecutterBlock.FACING, counterClockWise);
        BlockState slab = getSlabBlock(false);

        // left furnaces
        fill(level, random, startPos.above(1).relative(counterClockWise, 4).relative(direction, 1), direction, 9, 3, _ -> furnaceLeft);
        fill(level, random, startPos.above(4).relative(counterClockWise, 4).relative(direction, 1), direction, 9, 1, _ -> slab);

        // middle left furnaces
        fill(level, random, startPos.above(1).relative(counterClockWise, 1).relative(direction, 4), direction, 6, 3, _ -> furnaceRight);
        fill(level, random, startPos.above(4).relative(counterClockWise, 1).relative(direction, 4), direction, 6, 1, _ -> slab);

        // center bricks
        fill(level, random, startPos.above(1).relative(direction, 4), direction, 6, 3, this::getWallBlock);
        fill(level, random, startPos.above(4).relative(direction, 4), direction, 6, 1, _ -> slab);

        // middle right furnaces
        fill(level, random, startPos.above(1).relative(clockWise, 1).relative(direction, 4), direction, 6, 3, _ -> furnaceLeft);
        fill(level, random, startPos.above(4).relative(clockWise, 1).relative(direction, 4), direction, 6, 1, _ -> slab);

        // right furnaces
        fill(level, random, startPos.above(1).relative(clockWise, 4).relative(direction, 1), direction, 9, 3, _ -> furnaceRight);
        fill(level, random, startPos.above(4).relative(clockWise, 4).relative(direction, 1), direction, 9, 1, _ -> slab);
    }
}
