package com.adex.data.structure.refuge.piece;

import com.adex.data.structure.refuge.ContinuationPoint;
import com.adex.data.structure.refuge.RefugePieces;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

import java.util.List;

public class JailRoom extends RefugePiece {

    private static final int WIDTH = 12;
    private static final int HEIGHT = 5;
    private static final int DEPTH = 11;

    private static final int OFFSET_X = -5;
    private static final int OFFSET_Y = 0;
    private static final int OFFSET_Z = 0;

    public JailRoom(int index, BoundingBox boundingBox, Direction direction, BlockPos pos) {
        super(RefugePieces.REFUGE_JAIL_ROOM, index, boundingBox, direction, pos);
    }

    public JailRoom(int index, int x, int y, int z, Direction direction) {
        this(index, getBaseBoundingBox(direction).moved(x, y, z), direction, new BlockPos(x, y, z));
    }

    public JailRoom(CompoundTag compoundTag) {
        super(RefugePieces.REFUGE_JAIL_ROOM, compoundTag);
    }

    public static BoundingBox getBaseBoundingBox(Direction direction) {
        return RefugePiece.boundingBox(0, 0, 0, OFFSET_X, OFFSET_Y, OFFSET_Z, WIDTH, HEIGHT, DEPTH, direction);
    }

    @Override
    public List<ContinuationPoint> getContinuationPoints(BlockPos pos, Direction direction, int depth) {
        return List.of();
    }

    @Override
    public void createBlocks(WorldGenLevel level, RandomSource random) {
        Direction clockWise = direction.getClockWise();
        Direction counterClockWise = direction.getCounterClockWise();
        Direction opposite = direction.getOpposite();

        BlockState ironBarsSide = getIronBar(direction, opposite);
        BlockState ironBarsBack = getIronBar(clockWise, counterClockWise);

        BlockState buttonRight = Blocks.STONE_BUTTON.defaultBlockState().setValue(ButtonBlock.FACING, counterClockWise).setValue(ButtonBlock.FACE, AttachFace.WALL);
        BlockState buttonLeft = Blocks.STONE_BUTTON.defaultBlockState().setValue(ButtonBlock.FACING, clockWise).setValue(ButtonBlock.FACE, AttachFace.WALL);
        BlockState buttonBack = Blocks.STONE_BUTTON.defaultBlockState().setValue(ButtonBlock.FACING, opposite).setValue(ButtonBlock.FACE, AttachFace.WALL);

        BlockState doorBottomLeft = Blocks.IRON_DOOR.defaultBlockState().setValue(DoorBlock.FACING, counterClockWise).setValue(DoorBlock.HALF, DoubleBlockHalf.LOWER).setValue(DoorBlock.HINGE, DoorHingeSide.RIGHT);
        BlockState doorUpperLeft = Blocks.IRON_DOOR.defaultBlockState().setValue(DoorBlock.FACING, counterClockWise).setValue(DoorBlock.HALF, DoubleBlockHalf.UPPER).setValue(DoorBlock.HINGE, DoorHingeSide.RIGHT);
        BlockState doorBottomRight = Blocks.IRON_DOOR.defaultBlockState().setValue(DoorBlock.FACING, clockWise).setValue(DoorBlock.HALF, DoubleBlockHalf.LOWER).setValue(DoorBlock.HINGE, DoorHingeSide.LEFT);
        BlockState doorUpperRight = Blocks.IRON_DOOR.defaultBlockState().setValue(DoorBlock.FACING, clockWise).setValue(DoorBlock.HALF, DoubleBlockHalf.UPPER).setValue(DoorBlock.HINGE, DoorHingeSide.LEFT);
        BlockState doorBottomBack = Blocks.IRON_DOOR.defaultBlockState().setValue(DoorBlock.FACING, direction).setValue(DoorBlock.HALF, DoubleBlockHalf.LOWER).setValue(DoorBlock.HINGE, DoorHingeSide.RIGHT);
        BlockState doorUpperBack = Blocks.IRON_DOOR.defaultBlockState().setValue(DoorBlock.FACING, direction).setValue(DoorBlock.HALF, DoubleBlockHalf.UPPER).setValue(DoorBlock.HINGE, DoorHingeSide.RIGHT);

        // entry corridor
        createWalls(level, random, startPos, direction, 2);

        // floor
        fill(level, random, startPos.relative(counterClockWise, 5).relative(direction, 2), clockWise, direction, 12, 6, this::getWallBlock);
        fill(level, random, startPos.relative(counterClockWise, 2).relative(direction, 8), clockWise, direction, 6, 3, this::getWallBlock);

        // ceiling
        fill(level, random, startPos.relative(counterClockWise, 5).relative(direction, 2).above(3), clockWise, direction, 4, 6, this::getWallBlock);
        fill(level, random, startPos.relative(clockWise, 3).relative(direction, 2).above(3), clockWise, direction, 4, 6, this::getWallBlock);
        fill(level, random, startPos.relative(counterClockWise, 2).relative(direction, 8).above(3), clockWise, direction, 6, 3, this::getWallBlock);
        fill(level, random, startPos.relative(counterClockWise, 1).relative(direction, 7).above(3), clockWise, 4, 1, this::getWallBlock);
        fill(level, random, startPos.relative(counterClockWise, 2).relative(direction, 2).above(4), clockWise, direction, 6, 6, this::getWallBlock);

        // air inside hall
        fill(level, random, startPos.relative(counterClockWise, 1).relative(direction, 2).above(1), clockWise, 3, 3, this::air);
        fill(level, random, startPos.relative(counterClockWise, 1).relative(direction, 3).above(1), clockWise, direction, 4, 4, 3, this::air);

        // left jail walls
        fill(level, random, startPos.above(1).relative(counterClockWise, 2).relative(direction, 2), counterClockWise, 3, 2, this::getWallBlock);
        fill(level, random, startPos.above(1).relative(counterClockWise, 2).relative(direction, 7), counterClockWise, 3, 2, this::getWallBlock);
        fill(level, random, startPos.above(1).relative(counterClockWise, 2).relative(direction, 5), direction, 1, 2, this::getWallBlock);
        fill(level, random, startPos.above(1).relative(counterClockWise, 5).relative(direction, 2), direction, 6, 2, this::getWallBlock);

        // left jail iron bars
        fill(level, random, startPos.above(1).relative(counterClockWise, 2).relative(direction, 3), direction, 2, 2, _ -> ironBarsSide);

        // left jail door and button
        setBlock(level, startPos.above(1).relative(counterClockWise, 2).relative(direction, 6), doorBottomLeft);
        setBlock(level, startPos.above(2).relative(counterClockWise, 2).relative(direction, 6), doorUpperLeft);
        setBlock(level, startPos.above(2).relative(counterClockWise, 1).relative(direction, 5), buttonLeft);

        // left jail air
        fill(level, random, startPos.above(1).relative(counterClockWise, 3).relative(direction, 3), counterClockWise, direction, 2, 4, 2, this::air);

        // right jail walls
        fill(level, random, startPos.above(1).relative(clockWise, 2).relative(direction, 2), clockWise, 4, 2, this::getWallBlock);
        fill(level, random, startPos.above(1).relative(clockWise, 3).relative(direction, 7), clockWise, 3, 2, this::getWallBlock);
        fill(level, random, startPos.above(1).relative(clockWise, 3).relative(direction, 5), direction, 1, 2, this::getWallBlock);
        fill(level, random, startPos.above(1).relative(clockWise, 6).relative(direction, 2), direction, 6, 2, this::getWallBlock);
        setBlock(level, startPos.above(3).relative(clockWise, 2).relative(direction, 2), getWallBlock(random));

        // right jail iron bars
        fill(level, random, startPos.above(1).relative(clockWise, 3).relative(direction, 3), direction, 2, 2, _ -> ironBarsSide);

        // right jail door and button
        setBlock(level, startPos.above(1).relative(clockWise, 3).relative(direction, 6), doorBottomRight);
        setBlock(level, startPos.above(2).relative(clockWise, 3).relative(direction, 6), doorUpperRight);
        setBlock(level, startPos.above(2).relative(clockWise, 2).relative(direction, 5), buttonRight);

        // right jail air
        fill(level, random, startPos.above(1).relative(clockWise, 4).relative(direction, 3), clockWise, direction, 2, 4, 2, this::air);

        // back jail walls
        fill(level, random, startPos.above(1).relative(counterClockWise, 2).relative(direction, 8), direction, 2, 2, this::getWallBlock);
        fill(level, random, startPos.above(1).relative(clockWise, 3).relative(direction, 8), direction, 2, 2, this::getWallBlock);
        fill(level, random, startPos.above(1).relative(clockWise, 1).relative(direction, 7), direction, 1, 2, this::getWallBlock);
        fill(level, random, startPos.above(1).relative(counterClockWise, 2).relative(direction, 10), clockWise, 6, 2, this::getWallBlock);

        // back jail iron bars
        fill(level, random, startPos.above(1).relative(direction, 7), counterClockWise, 2, 2, _ -> ironBarsBack);

        // back jail door and button
        setBlock(level, startPos.above(1).relative(clockWise, 2).relative(direction, 7), doorBottomBack);
        setBlock(level, startPos.above(2).relative(clockWise, 2).relative(direction, 7), doorUpperBack);
        setBlock(level, startPos.above(2).relative(clockWise, 1).relative(direction, 6), buttonBack);

        // back jail air
        fill(level, random, startPos.above(1).relative(counterClockWise, 1).relative(direction, 8), clockWise, direction, 4, 2, 2, this::air);


        // spawn prisoners
        BoundingBox leftCell = BoundingBox.fromCorners(
                startPos.above(1).relative(counterClockWise, 3).relative(direction, 3),
                startPos.above(1).relative(counterClockWise, 4).relative(direction, 6));
        BoundingBox rightCell = BoundingBox.fromCorners(
                startPos.above(1).relative(clockWise, 4).relative(direction, 3),
                startPos.above(1).relative(clockWise, 5).relative(direction, 6));
        BoundingBox backCell = BoundingBox.fromCorners(
                startPos.above(1).relative(counterClockWise, 1).relative(direction, 8),
                startPos.above(1).relative(clockWise, 2).relative(direction, 9));

        addEntity(level, random, leftCell, EntityType.VILLAGER, 0.1f);
        addEntity(level, random, rightCell, EntityType.VILLAGER, 0.1f);
        addEntity(level, random, backCell, EntityType.VILLAGER, 0.1f);
    }
}
