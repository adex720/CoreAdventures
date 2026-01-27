package com.adex.data.structure.refuge.piece;

import com.adex.data.structure.refuge.RefugePieces;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

public class EnchantingRoom extends FiveWideRoom {

    public EnchantingRoom(int index, BoundingBox boundingBox, Direction direction, BlockPos pos) {
        super(RefugePieces.REFUGE_ENCHANTING_ROOM, index, boundingBox, direction, pos);
    }

    public EnchantingRoom(int index, int x, int y, int z, Direction direction) {
        this(index, getBaseBoundingBox(direction).moved(x, y, z), direction, new BlockPos(x, y, z));
    }

    public EnchantingRoom(CompoundTag compoundTag) {
        super(RefugePieces.REFUGE_ENCHANTING_ROOM, compoundTag);
    }

    @Override
    public void createBlocks(WorldGenLevel level, RandomSource random) {
        super.createBlocks(level, random);
        Direction clockWise = direction.getClockWise();
        Direction counterClockWise = direction.getCounterClockWise();

        BlockState bookshelf = Blocks.BOOKSHELF.defaultBlockState();
        BlockState enchantingTable = Blocks.ENCHANTING_TABLE.defaultBlockState();

        // bookshelves
        fill(level, random, startPos.relative(direction, 1).relative(counterClockWise, 2).above(1), direction, 3, 2, _ -> bookshelf);
        fill(level, random, startPos.relative(direction, 1).relative(clockWise, 2).above(1), direction, 3, 2, _ -> bookshelf);
        fill(level, random, startPos.relative(direction, 4).relative(counterClockWise, 1).above(1), clockWise, 3, 2, _ -> bookshelf);

        // enchanting table
        level.setBlock(startPos.relative(direction, 2).above(1), enchantingTable, 2);

    }
}
