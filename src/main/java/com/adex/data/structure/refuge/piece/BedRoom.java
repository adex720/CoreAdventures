package com.adex.data.structure.refuge.piece;

import com.adex.block.ModBlocks;
import com.adex.data.structure.refuge.ContinuationPoint;
import com.adex.data.structure.refuge.RefugePieces;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

import java.util.List;

public class BedRoom extends RefugePiece {

    public static final List<Block> POSSIBLE_CARPETS = List.of(Blocks.BLACK_CARPET, Blocks.BLUE_CARPET, Blocks.GRAY_CARPET, Blocks.GREEN_CARPET, Blocks.ORANGE_CARPET, Blocks.RED_CARPET);

    private static final int WIDTH = 9;
    private static final int HEIGHT = 6;
    private static final int DEPTH = 15;

    private static final int OFFSET_X = -4;
    private static final int OFFSET_Y = 0;
    private static final int OFFSET_Z = 0;

    public BedRoom(int index, BoundingBox boundingBox, Direction direction, BlockPos pos) {
        super(RefugePieces.REFUGE_BED_ROOM, index, boundingBox, direction, pos);
    }

    public BedRoom(int index, int x, int y, int z, Direction direction) {
        this(index, getBaseBoundingBox(direction).moved(x, y, z), direction, new BlockPos(x, y, z));
    }

    public BedRoom(CompoundTag compoundTag) {
        super(RefugePieces.REFUGE_BED_ROOM, compoundTag);
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
        Direction counterClockWise = direction.getCounterClockWise();

        BlockPos frontLeft = startPos.relative(counterClockWise, 4);
        BlockPos frontRight = startPos.relative(clockWise, 4);
        BlockPos backRight = frontRight.relative(direction, 13);

        BlockState stoneSlab = getSlabBlock(false);
        BlockState stoneStairRight = getStairBlock(clockWise, false);
        BlockState stoneStairLeft = getStairBlock(counterClockWise, false);

        BlockState campfire = Blocks.CAMPFIRE.defaultBlockState().setValue(CampfireBlock.LIT, false);
        BlockState lantern = Blocks.LANTERN.defaultBlockState().setValue(LanternBlock.HANGING, true);

        // walls
        createOneWideWalls(level, random, BoundingBox.fromCorners(frontLeft, backRight.above(5)));
        // entry hole
        fill(level, random, startPos.relative(counterClockWise).above(1), clockWise, 3, 3, this::air);

        // fireplace
        setBlock(level, backRight.above(1).relative(counterClockWise, 4).relative(direction.getOpposite(), 1), stoneSlab);
        setBlock(level, backRight.above(1).relative(counterClockWise, 3), stoneStairRight);
        setBlock(level, backRight.above(1).relative(counterClockWise, 4), campfire);
        setBlock(level, backRight.above(1).relative(counterClockWise, 5), stoneStairLeft);
        fill(level, random, backRight.above(2).relative(counterClockWise, 3), counterClockWise, 3, 1, this::air);
        // fireplace back wall
        fill(level, random, backRight.above(1).relative(counterClockWise, 3).relative(direction, 1), counterClockWise, 3, 2, this::getWallBlock);

        // beds and chest
        generateBedsAndChests(level, random, frontLeft.relative(direction, 1).relative(clockWise, 1).above(1), frontRight.relative(direction, 1).relative(counterClockWise, 1).above(1), direction, 4);

        // lanterns
        setBlock(level, startPos.relative(direction, 3).above(4), lantern);
        setBlock(level, startPos.relative(direction, 9).above(4), lantern);
    }

    /**
     * Generates 2 double chests and
     *
     * @param level     WorldGenLevel
     * @param random    RandomSource
     * @param leftPos   Bottom left {@link BlockPos} of first chest on the left side
     * @param rightPos  Bottom right {@link BlockPos} of first chest on the left side
     * @param direction Direction to continue generation from the starting poses.
     * @param count     Count of double bed-chest sets to generate on each side.
     */
    public static void generateBedsAndChests(WorldGenLevel level, RandomSource random, BlockPos leftPos, BlockPos rightPos, Direction direction, int count) {
        Direction clockWise = direction.getClockWise();
        Direction counterClockWise = direction.getCounterClockWise();

        BlockState chestLeftLeft = Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, clockWise).setValue(ChestBlock.TYPE, ChestType.RIGHT);
        BlockState chestLeftRight = Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, clockWise).setValue(ChestBlock.TYPE, ChestType.LEFT);
        BlockState chestRightLeft = Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, counterClockWise).setValue(ChestBlock.TYPE, ChestType.RIGHT);
        BlockState chestRightRight = Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, counterClockWise).setValue(ChestBlock.TYPE, ChestType.LEFT);

        BlockState carpet = getCarpet(random); // Using same random carpet for every bed in one room
        BlockState bedSlab = ModBlocks.JUNIPER_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP);

        for (int i = 0; i < count; i++) {
            // left chests
            setBlock(level, leftPos.relative(direction, i * 3), chestLeftLeft);
            setBlock(level, leftPos.relative(direction, i * 3 + 1), chestLeftRight);
            setBlock(level, leftPos.above(1).relative(direction, i * 3), chestLeftLeft);
            setBlock(level, leftPos.above(1).relative(direction, i * 3 + 1), chestLeftRight);

            // left beds
            setBlock(level, leftPos.relative(direction, i * 3 + 2), carpet);
            setBlock(level, leftPos.relative(direction, i * 3 + 2).relative(clockWise, 1), carpet);
            setBlock(level, leftPos.above(1).relative(direction, i * 3 + 2), bedSlab);
            setBlock(level, leftPos.above(1).relative(direction, i * 3 + 2).relative(clockWise, 1), bedSlab);
            setBlock(level, leftPos.above(2).relative(direction, i * 3 + 2), carpet);
            setBlock(level, leftPos.above(2).relative(direction, i * 3 + 2).relative(clockWise, 1), carpet);

            // right chests
            setBlock(level, rightPos.relative(direction, i * 3), chestRightRight);
            setBlock(level, rightPos.relative(direction, i * 3 + 1), chestRightLeft);
            setBlock(level, rightPos.above(1).relative(direction, i * 3), chestRightRight);
            setBlock(level, rightPos.above(1).relative(direction, i * 3 + 1), chestRightLeft);

            // right beds
            setBlock(level, rightPos.relative(direction, i * 3 + 2), carpet);
            setBlock(level, rightPos.relative(direction, i * 3 + 2).relative(counterClockWise, 1), carpet);
            setBlock(level, rightPos.above(1).relative(direction, i * 3 + 2), bedSlab);
            setBlock(level, rightPos.above(1).relative(direction, i * 3 + 2).relative(counterClockWise, 1), bedSlab);
            setBlock(level, rightPos.above(2).relative(direction, i * 3 + 2), carpet);
            setBlock(level, rightPos.above(2).relative(direction, i * 3 + 2).relative(counterClockWise, 1), carpet);
        }
    }

    public static BlockState getCarpet(RandomSource random) {
        return POSSIBLE_CARPETS.get(random.nextInt(POSSIBLE_CARPETS.size())).defaultBlockState();
    }
}
