package com.adex.data.structure.refuge.piece;

import com.adex.data.structure.refuge.RefugePieces;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;

public class ToolRoom extends ElevenWideRoom {

    public ToolRoom(int index, int x, int y, int z, Direction direction) {
        super(RefugePieces.REFUGE_TOOL_ROOM, index, x, y, z, direction);
    }

    public ToolRoom(CompoundTag compoundTag) {
        super(RefugePieces.REFUGE_TOOL_ROOM, compoundTag);
    }

    @Override
    public void createBlocks(WorldGenLevel level, RandomSource random) {
        super.createBlocks(level, random);

        Direction clockWise = direction.getClockWise();
        Direction counterClockWise = direction.getCounterClockWise();

        BlockState smithingTable = Blocks.SMITHING_TABLE.defaultBlockState();
        BlockState stonecutter = Blocks.STONECUTTER.defaultBlockState().setValue(StonecutterBlock.FACING, direction);
        BlockState grindstone = Blocks.GRINDSTONE.defaultBlockState().setValue(GrindstoneBlock.FACING, counterClockWise).setValue(GrindstoneBlock.FACE, AttachFace.FLOOR);
        BlockState cauldron = Blocks.CAULDRON.defaultBlockState();
        BlockState craftingTable = Blocks.CRAFTING_TABLE.defaultBlockState();
        BlockState cartographyTable = Blocks.CARTOGRAPHY_TABLE.defaultBlockState();
        BlockState loom = Blocks.LOOM.defaultBlockState().setValue(LoomBlock.FACING, counterClockWise);
        BlockState fletchingTable = Blocks.FLETCHING_TABLE.defaultBlockState();
        BlockState barrel = Blocks.BARREL.defaultBlockState().setValue(BarrelBlock.FACING, Direction.UP);

        // left blocks
        fill(level, random, startPos.above(1).relative(counterClockWise, 4).relative(direction, 1), direction, 2, 1, _ -> smithingTable);
        fill(level, random, startPos.above(1).relative(counterClockWise, 4).relative(direction, 3), direction, 2, 1, _ -> stonecutter);
        fill(level, random, startPos.above(1).relative(counterClockWise, 4).relative(direction, 5), direction, 2, 1, _ -> grindstone);
        fill(level, random, startPos.above(1).relative(counterClockWise, 4).relative(direction, 7), direction, 2, 1, _ -> cauldron);

        // right blocks
        fill(level, random, startPos.above(1).relative(clockWise, 4).relative(direction, 1), direction, 2, 1, _ -> craftingTable);
        fill(level, random, startPos.above(1).relative(clockWise, 4).relative(direction, 3), direction, 2, 1, _ -> cartographyTable);
        fill(level, random, startPos.above(1).relative(clockWise, 4).relative(direction, 5), direction, 2, 1, _ -> loom);
        fill(level, random, startPos.above(1).relative(clockWise, 4).relative(direction, 7), direction, 2, 1, _ -> fletchingTable);

        // barrels
        fill(level, random, startPos.above(1).relative(counterClockWise, 4).relative(direction, 9), clockWise, 9, 1, _ -> barrel);
    }
}
