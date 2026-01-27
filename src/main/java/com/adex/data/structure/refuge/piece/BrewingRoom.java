package com.adex.data.structure.refuge.piece;

import com.adex.data.structure.refuge.RefugePieces;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.LecternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

public class BrewingRoom extends FiveByFiveRoom {

    public BrewingRoom(int index, BoundingBox boundingBox, Direction direction, BlockPos pos) {
        super(RefugePieces.REFUGE_BREWING_ROOM, index, boundingBox, direction, pos);
    }

    public BrewingRoom(int index, int x, int y, int z, Direction direction) {
        this(index, getBaseBoundingBox(direction).moved(x, y, z), direction, new BlockPos(x, y, z));
    }

    public BrewingRoom(CompoundTag compoundTag) {
        super(RefugePieces.REFUGE_BREWING_ROOM, compoundTag);
    }

    @Override
    public void createBlocks(WorldGenLevel level, RandomSource random) {
        super.createBlocks(level, random);
        Direction clockWise = direction.getClockWise();
        Direction counterClockWise = direction.getCounterClockWise();

        BlockState lectern = Blocks.LECTERN.defaultBlockState().setValue(LecternBlock.FACING, clockWise);
        BlockState brewingStand = Blocks.BREWING_STAND.defaultBlockState();
        BlockState chest = Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, counterClockWise);

        // place blocks
        level.setBlock(startPos.above(1).relative(direction, 2).relative(counterClockWise, 1), lectern, 2);
        level.setBlock(startPos.above(1).relative(direction, 3).relative(counterClockWise, 1), getWallBlock(random), 2);
        level.setBlock(startPos.above(2).relative(direction, 3).relative(counterClockWise, 1), brewingStand, 2);
        level.setBlock(startPos.above(1).relative(direction, 3).relative(clockWise, 1), chest, 2);
    }
}
