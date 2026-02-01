package com.adex.data.structure.refuge.piece;

import com.adex.data.structure.refuge.ContinuationPoint;
import com.adex.data.structure.refuge.RefugePieces;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;

import java.util.List;

public class CorridorJailRight extends RefugePiece {

    private static final int WIDTH = 9;
    private static final int HEIGHT = 5;
    private static final int DEPTH = 15;

    private static final int OFFSET_X = -2;
    private static final int OFFSET_Y = 0;
    private static final int OFFSET_Z = 0;

    public CorridorJailRight(int index, BoundingBox boundingBox, Direction direction, BlockPos pos) {
        super(RefugePieces.REFUGE_CORRIDOR_JAIL_RIGHT, index, boundingBox, direction, pos);
    }

    public CorridorJailRight(int index, int x, int y, int z, Direction direction) {
        this(index, getBaseBoundingBox(direction).moved(x, y, z), direction, new BlockPos(x, y, z));
    }

    public CorridorJailRight(CompoundTag compoundTag) {
        super(RefugePieces.REFUGE_CORRIDOR_JAIL_RIGHT, compoundTag);
    }

    public static BoundingBox getBaseBoundingBox(Direction direction) {
        return RefugePiece.boundingBox(0, 0, 0, OFFSET_X, OFFSET_Y, OFFSET_Z, WIDTH, HEIGHT, DEPTH, direction);
    }

    @Override
    public List<ContinuationPoint> getContinuationPoints(BlockPos pos, Direction direction, int depth) {
        return List.of(ContinuationPoint.of(pos, direction, 15, depth));
    }

    @Override
    public void createBlocks(WorldGenLevel level, RandomSource random) {
        createWalls(level, random, startPos, direction, 15);

        Direction clockWise = direction.getClockWise();
        Direction counterClockWise = direction.getCounterClockWise();

        BlockState ironBars = Blocks.IRON_BARS.defaultBlockState();
        BlockState button = Blocks.STONE_BUTTON.defaultBlockState().setValue(ButtonBlock.FACING, clockWise).setValue(ButtonBlock.FACE, AttachFace.WALL);
        BlockState doorBottomLeft = Blocks.IRON_DOOR.defaultBlockState().setValue(DoorBlock.FACING, counterClockWise).setValue(DoorBlock.HALF, DoubleBlockHalf.LOWER).setValue(DoorBlock.HINGE, DoorHingeSide.LEFT);
        BlockState doorUpperLeft = Blocks.IRON_DOOR.defaultBlockState().setValue(DoorBlock.FACING, counterClockWise).setValue(DoorBlock.HALF, DoubleBlockHalf.UPPER).setValue(DoorBlock.HINGE, DoorHingeSide.LEFT);
        BlockState doorBottomRight = Blocks.IRON_DOOR.defaultBlockState().setValue(DoorBlock.FACING, counterClockWise).setValue(DoorBlock.HALF, DoubleBlockHalf.LOWER).setValue(DoorBlock.HINGE, DoorHingeSide.RIGHT);
        BlockState doorUpperRight = Blocks.IRON_DOOR.defaultBlockState().setValue(DoorBlock.FACING, counterClockWise).setValue(DoorBlock.HALF, DoubleBlockHalf.UPPER).setValue(DoorBlock.HINGE, DoorHingeSide.RIGHT);

        // floor for jails
        fill(level, random, startPos.relative(direction, 2).relative(counterClockWise, 3), direction, counterClockWise, 11, 4, this::getWallBlock);
        // ceiling for jails
        fill(level, random, startPos.relative(direction, 2).relative(counterClockWise, 3).above(3), direction, counterClockWise, 11, 4, this::getWallBlock);

        // back wall for jails
        fill(level, random, startPos.relative(direction, 2).relative(counterClockWise, 6).above(1), direction, 11, 2, this::getWallBlock);

        // side walls for jails
        fill(level, random, startPos.relative(direction, 2).relative(counterClockWise, 3).above(1), counterClockWise, 3, 2, this::getWallBlock);
        fill(level, random, startPos.relative(direction, 7).relative(counterClockWise, 3).above(1), counterClockWise, 3, 2, this::getWallBlock);
        fill(level, random, startPos.relative(direction, 12).relative(counterClockWise, 3).above(1), counterClockWise, 3, 2, this::getWallBlock);

        // air inside jails
        fill(level, random, startPos.relative(direction, 3).relative(counterClockWise, 3).above(1), direction, counterClockWise, 4, 3, 2, this::air);
        fill(level, random, startPos.relative(direction, 8).relative(counterClockWise, 3).above(1), direction, counterClockWise, 4, 3, 2, this::air);

        // iron bars
        fill(level, random, startPos.relative(direction, 3).relative(counterClockWise, 2).above(1), direction, 2, 2, _ -> ironBars);
        fill(level, random, startPos.relative(direction, 10).relative(counterClockWise, 2).above(1), direction, 2, 2, _ -> ironBars);

        // doors
        level.setBlock(startPos.relative(direction, 6).relative(counterClockWise, 2).above(1), doorBottomRight, 2);
        level.setBlock(startPos.relative(direction, 6).relative(counterClockWise, 2).above(2), doorUpperRight, 2);

        level.setBlock(startPos.relative(direction, 8).relative(counterClockWise, 2).above(1), doorBottomLeft, 2);
        level.setBlock(startPos.relative(direction, 8).relative(counterClockWise, 2).above(2), doorUpperLeft, 2);

        // buttons
        level.setBlock(startPos.relative(direction, 5).relative(counterClockWise, 2).above(2), button, 2);
        level.setBlock(startPos.relative(direction, 9).relative(counterClockWise, 2).above(2), button, 2);
    }
}
