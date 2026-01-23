package com.adex.data.structure.refuge.piece;

import com.adex.block.ModBlocks;
import com.adex.data.loottable.ModLootTables;
import com.adex.data.structure.refuge.ContinuationPoint;
import com.adex.data.structure.refuge.RefugePieces;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.piston.PistonBaseBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

import java.util.List;

public class RedstoneThreeWay extends RefugePiece {

    private static final int WIDTH = 13;
    private static final int HEIGHT = 8;
    private static final int DEPTH = 15;

    private static final int OFFSET_X = -2;
    private static final int OFFSET_Y = -1;
    private static final int OFFSET_Z = 0;

    public RedstoneThreeWay(int index, BoundingBox boundingBox, Direction direction, BlockPos pos) {
        super(RefugePieces.REFUGE_REDSTONE_THREE_WAY, index, boundingBox, direction, pos);
    }

    public RedstoneThreeWay(int index, int x, int y, int z, Direction direction) {
        this(index, getBaseBoundingBox(direction).moved(x, y, z), direction, new BlockPos(x, y, z));
    }

    public RedstoneThreeWay(CompoundTag compoundTag) {
        super(RefugePieces.REFUGE_REDSTONE_THREE_WAY, compoundTag);
    }

    public static BoundingBox getBaseBoundingBox(Direction direction) {
        return RefugePiece.boundingBox(0, 0, 0, OFFSET_X, OFFSET_Y, OFFSET_Z, WIDTH, HEIGHT, DEPTH, direction);
    }

    @Override
    public List<ContinuationPoint> getContinuationPoints(BlockPos pos, Direction direction, int depth) {
        return List.of(ContinuationPoint.of(pos, direction, 5, 11, -1, direction.getClockWise(), depth),
                ContinuationPoint.of(pos, direction, 15, 0, direction, depth));
    }

    @Override
    public void createBlocks(WorldGenLevel level, RandomSource random) {
        Direction clockWise = direction.getClockWise();
        Direction counterClockWise = direction.getCounterClockWise();
        Direction opposite = direction.getOpposite();
        BlockPos doorBottomLeft = startPos.relative(clockWise, 2).relative(direction, 6).above(1);

        BlockState lever = Blocks.LEVER.defaultBlockState().setValue(LeverBlock.FACING, counterClockWise);
        BlockState air = Blocks.AIR.defaultBlockState();
        BlockState slab = ModBlocks.HARDENED_STONE_BRICKS_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP);
        BlockState stair = ModBlocks.HARDENED_STONE_BRICKS_STAIRS.defaultBlockState().setValue(StairBlock.FACING, counterClockWise);

        // Here front = towards end of secret corridor
        // forward = back and front
        // sideways = left and right
        BlockState redstoneForward = getRedstone(clockWise, counterClockWise);
        BlockState redstoneSideways = getRedstone(direction, opposite);
        BlockState redstoneFrontRight = getRedstone(clockWise, opposite);
        BlockState redstoneFrontLeft = getRedstone(clockWise, direction);
        BlockState redstoneBackRight = getRedstone(counterClockWise, opposite);
        BlockState redstoneBackLeft = getRedstone(counterClockWise, direction);
        BlockState redstoneSidewaysFront = getRedstone(direction, opposite, clockWise);
        BlockState redstoneForwardLeft = getRedstone(clockWise, counterClockWise, direction);
        BlockState redstoneForwardRight = getRedstone(clockWise, counterClockWise, opposite);

        BlockState redstoneUpFront = getUpRedstone(clockWise);
        BlockState redstoneUpRight = getUpRedstone(opposite);
        BlockState redstoneUpLeft = getUpRedstone(direction);
        BlockState redstoneUpFrontRight = getUpRedstone(clockWise, opposite);

        BlockState repeaterLeft1 = repeater(direction, 1);
        BlockState repeaterRight1 = repeater(opposite, 1);
        BlockState repeaterRight4 = repeater(opposite, 4);

        BlockState observerFront = Blocks.OBSERVER.defaultBlockState().setValue(ObserverBlock.FACING, clockWise);
        BlockState observerUp = Blocks.OBSERVER.defaultBlockState().setValue(ObserverBlock.FACING, Direction.UP);

        BlockState pistonRight = Blocks.STICKY_PISTON.defaultBlockState().setValue(PistonBaseBlock.FACING, opposite);
        BlockState pistonLeft = Blocks.STICKY_PISTON.defaultBlockState().setValue(PistonBaseBlock.FACING, direction);
        BlockState pistonBack = Blocks.STICKY_PISTON.defaultBlockState().setValue(PistonBaseBlock.FACING, counterClockWise);

        // main corridor
        createWalls(level, random, startPos, direction, 15);

        // lever
        level.setBlock(doorBottomLeft.above(1).relative(direction, 6).relative(counterClockWise, 1), lever, 2);

        // end of hidden corridor
        createWalls(level, random, doorBottomLeft.relative(clockWise, 5).relative(opposite, 1).below(2), clockWise, 4);

        // start floor of hidden corridor
        fill(level, random, doorBottomLeft.relative(direction, 1).relative(clockWise, 1).below(1), clockWise, opposite, 3, 4, this::getWallBlock);

        // start ceiling of hidden corridor
        fill(level, random, doorBottomLeft.relative(clockWise, 1).relative(direction, 1).above(2), opposite, clockWise, 4, 3, this::getWallBlock);
        fill(level, random, doorBottomLeft.relative(clockWise, 4).relative(direction, 1).above(2), opposite, clockWise, 5, 2, this::getWallBlock);

        // left wall of start of hidden corridor
        fill(level, random, doorBottomLeft.relative(clockWise, 3).relative(direction, 1).below(1), clockWise, 2, 3, this::getWallBlock);

        // right wall of start of hidden corridor
        fill(level, random, doorBottomLeft.relative(clockWise, 3).relative(opposite, 3).below(1), clockWise, 2, 3, this::getWallBlock);
        level.setBlock(doorBottomLeft.relative(clockWise, 4).relative(opposite, 2).below(1), getWallBlock(random), 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 3).relative(opposite, 2).below(1), getWallBlock(random), 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 3).relative(opposite, 2), getWallBlock(random), 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 3).relative(opposite, 2).above(1), getWallBlock(random), 2);

        // air at start of hidden corridor
        fill(level, random, doorBottomLeft.relative(clockWise, 1), clockWise, opposite, 4, 2, 2, this::air);
        level.setBlock(doorBottomLeft.relative(clockWise, 4).relative(opposite, 2).above(1), air, 2);

        //stairs
        level.setBlock(doorBottomLeft.relative(clockWise, 4).relative(opposite, 1).below(1), stair, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 4).below(1), stair, 2);

        // chest
        createChest(level, random, doorBottomLeft.relative(clockWise, 4).relative(opposite, 2), direction, ModLootTables.REFUGE_TREASURE);

        // lever to observers
        level.setBlock(doorBottomLeft.relative(direction, 6).relative(clockWise, 1).above(1), redstoneUpFront, 2);
        level.setBlock(doorBottomLeft.relative(direction, 6).relative(clockWise, 1).above(2), air, 2);
        fill(level, random, doorBottomLeft.relative(direction, 6).relative(clockWise, 2).above(2), clockWise, 4, 1, _ -> redstoneForward);
        level.setBlock(doorBottomLeft.relative(direction, 6).relative(clockWise, 6).above(2), redstoneFrontRight, 2);
        fill(level, random, doorBottomLeft.relative(direction, 5).relative(clockWise, 6).above(2), opposite, 3, 1, _ -> redstoneSideways);

        // air and slabs
        level.setBlock(doorBottomLeft.relative(direction, 3).relative(clockWise, 6).above(3), slab, 2);
        level.setBlock(doorBottomLeft.relative(direction, 2).relative(clockWise, 6).above(4), slab, 2);
        level.setBlock(doorBottomLeft.relative(direction, 3).relative(clockWise, 6).above(5), air, 2);

        fill(level, random, doorBottomLeft.relative(direction, 2).relative(clockWise, 6).above(3), opposite, 3, 1, _ -> redstoneSideways);
        level.setBlock(doorBottomLeft.relative(direction, 3).relative(clockWise, 6).above(4), redstoneSideways, 2);
        level.setBlock(doorBottomLeft.relative(direction, 2).relative(clockWise, 6).above(5), redstoneSideways, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 6).above(5), redstoneSideways, 2);
        level.setBlock(doorBottomLeft.relative(opposite, 2).relative(clockWise, 6).above(5), redstoneSideways, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 6).above(3), redstoneSideways, 2);
        level.setBlock(doorBottomLeft.relative(opposite, 2).relative(clockWise, 6).above(3), redstoneSideways, 2);

        level.setBlock(doorBottomLeft.relative(direction, 1).relative(clockWise, 6).above(5), repeaterRight1, 2);
        level.setBlock(doorBottomLeft.relative(opposite, 1).relative(clockWise, 6).above(5), repeaterRight4, 2);
        level.setBlock(doorBottomLeft.relative(opposite, 1).relative(clockWise, 6).above(3), repeaterRight4, 2);

        // first observers
        level.setBlock(doorBottomLeft.relative(clockWise, 5).above(5), observerFront, 2);
        level.setBlock(doorBottomLeft.relative(opposite, 2).relative(clockWise, 5).above(5), observerFront, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 5).above(3), observerFront, 2);
        level.setBlock(doorBottomLeft.relative(opposite, 2).relative(clockWise, 5).above(3), observerFront, 2);

        // second observers
        level.setBlock(doorBottomLeft.relative(clockWise, 2).above(3), observerUp, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 2).above(3).relative(opposite, 1), observerUp, 2);

        // upper first observers to second observers
        level.setBlock(doorBottomLeft.relative(clockWise, 4).above(5).relative(opposite, 2), redstoneFrontLeft, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 4).above(5).relative(opposite, 1), redstoneSideways, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 4).above(5), redstoneForwardRight, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 3).above(5), redstoneForward, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 2).above(4), redstoneUpFrontRight, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 2).above(5), air, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 2).above(4).relative(opposite, 1), redstoneSideways, 2);

        // straight redstone after lower first observers
        level.setBlock(doorBottomLeft.relative(clockWise, 4).above(3).relative(direction, 2), redstoneSideways, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 4).above(3).relative(direction, 1), redstoneSideways, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 4).above(3), redstoneSidewaysFront, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 4).above(3).relative(opposite, 1), redstoneSideways, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 4).above(3).relative(opposite, 2), redstoneSidewaysFront, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 4).above(3).relative(opposite, 3), redstoneSideways, 2);

        // first observers to left pistons
        level.setBlock(doorBottomLeft.relative(clockWise, 4).above(3).relative(direction, 3), air, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 4).above(2).relative(direction, 3), redstoneUpRight, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 4).above(2).relative(direction, 4), redstoneBackRight, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 3).above(2).relative(direction, 4), air, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 3).above(1).relative(direction, 4), redstoneUpFront, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 2).above(1).relative(direction, 4), redstoneForwardRight, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 1).above(1).relative(direction, 4), redstoneFrontRight, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 2).above(1).relative(direction, 3), repeaterRight1, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 1).above(1).relative(direction, 3), repeaterRight1, 2);

        // first observers to right pistons
        level.setBlock(doorBottomLeft.relative(clockWise, 4).above(3).relative(opposite, 4), air, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 4).above(2).relative(opposite, 4), redstoneUpLeft, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 4).above(2).relative(opposite, 5), redstoneBackLeft, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 3).above(2).relative(opposite, 5), air, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 3).above(1).relative(opposite, 5), redstoneUpFront, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 2).above(1).relative(opposite, 5), redstoneForwardLeft, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 1).above(1).relative(opposite, 5), redstoneFrontLeft, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 2).above(1).relative(opposite, 4), repeaterLeft1, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 1).above(1).relative(opposite, 4), repeaterLeft1, 2);

        // left pistons
        level.setBlock(doorBottomLeft.relative(clockWise, 1).relative(direction, 2), pistonRight, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 1).relative(direction, 2).above(1), pistonRight, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 2).relative(direction, 2), pistonRight, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 2).relative(direction, 2).above(1), pistonRight, 2);

        level.setBlock(doorBottomLeft.relative(clockWise, 2).relative(direction, 1), pistonBack, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 2).relative(direction, 1).above(1), pistonBack, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 1).relative(direction, 1), air, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 1).relative(direction, 1).above(1), air, 2);

        // right pistons
        level.setBlock(doorBottomLeft.relative(clockWise, 1).relative(opposite, 3), pistonLeft, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 1).relative(opposite, 3).above(1), pistonLeft, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 2).relative(opposite, 3), pistonLeft, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 2).relative(opposite, 3).above(1), pistonLeft, 2);

        level.setBlock(doorBottomLeft.relative(clockWise, 2).relative(opposite, 2), pistonBack, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 2).relative(opposite, 2).above(1), pistonBack, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 1).relative(opposite, 2), air, 2);
        level.setBlock(doorBottomLeft.relative(clockWise, 1).relative(opposite, 2).above(1), air, 2);
    }
}
