package com.adex.data.structure.refuge.piece;

import com.adex.data.structure.refuge.RefugePieces;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class TreasureRoom extends ElevenWideRoom {

    public TreasureRoom(int index, int x, int y, int z, Direction direction) {
        super(RefugePieces.REFUGE_TREASURE_ROOM, index, x, y, z, direction);
    }

    public TreasureRoom(CompoundTag compoundTag) {
        super(RefugePieces.REFUGE_TREASURE_ROOM, compoundTag);
    }

    @Override
    public void createBlocks(WorldGenLevel level, RandomSource random) {
        super.createBlocks(level, random);
        addRandomTreasureChest(level, random);

        BlockState state = (random.nextBoolean() ? Blocks.GOLD_BLOCK : Blocks.DIAMOND_BLOCK).defaultBlockState();
        level.setBlock(startPos.relative(direction, 6).above(1), state, 2);
        level.setBlock(startPos.relative(direction, 5).above(1), state, 2);
        level.setBlock(startPos.relative(direction, 6).relative(direction.getClockWise()).above(1), state, 2);
        level.setBlock(startPos.relative(direction, 7).above(1), state, 2);
        level.setBlock(startPos.relative(direction, 6).relative(direction.getCounterClockWise()).above(1), state, 2);
        level.setBlock(startPos.relative(direction, 6).above(2), state, 2);
    }
}
