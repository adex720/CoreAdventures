package com.adex.data.structure.refuge.piece;

import com.adex.data.structure.refuge.ContinuationPoint;
import com.adex.data.structure.refuge.RefugePieces;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeverBlock;
import net.minecraft.world.level.block.piston.PistonBaseBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

import java.util.List;

public class RedstoneThreeWayTrap extends RefugePiece {

    private static final int WIDTH = 14;
    private static final int HEIGHT = 11;
    private static final int DEPTH = 10;

    private static final int OFFSET_X = -6;
    private static final int OFFSET_Y = -6;
    private static final int OFFSET_Z = 0;

    public RedstoneThreeWayTrap(int index, BoundingBox boundingBox, Direction direction, BlockPos pos) {
        super(RefugePieces.REFUGE_REDSTONE_THREE_WAY_TRAP, index, boundingBox, direction, pos);
    }

    public RedstoneThreeWayTrap(int index, int x, int y, int z, Direction direction) {
        this(index, getBaseBoundingBox(direction).moved(x, y, z), direction, new BlockPos(x, y, z));
    }

    public RedstoneThreeWayTrap(CompoundTag compoundTag) {
        super(RefugePieces.REFUGE_REDSTONE_THREE_WAY_TRAP, compoundTag);
    }

    public static BoundingBox getBaseBoundingBox(Direction direction) {
        return RefugePiece.boundingBox(0, 0, 0, OFFSET_X, OFFSET_Y, OFFSET_Z, WIDTH, HEIGHT, DEPTH, direction);
    }

    @Override
    public List<ContinuationPoint> getContinuationPoints(BlockPos pos, Direction direction, int depth) {
        return List.of(ContinuationPoint.of(pos, direction, 2, 8, direction.getClockWise(), depth),
                ContinuationPoint.of(pos, direction, 10, -4, direction, depth));
    }

    @Override
    public void createBlocks(WorldGenLevel level, RandomSource random) {
        Direction clockWise = direction.getClockWise();
        Direction counterClockWise = direction.getCounterClockWise();
        Direction opposite = direction.getOpposite();

        BlockState leverRight = Blocks.LEVER.defaultBlockState().setValue(LeverBlock.FACING, counterClockWise).setValue(LeverBlock.FACE, AttachFace.WALL);
        BlockState leverBack = Blocks.LEVER.defaultBlockState().setValue(LeverBlock.FACING, opposite).setValue(LeverBlock.FACE, AttachFace.WALL);
        BlockState air = Blocks.AIR.defaultBlockState();
        BlockState slab = getSlabBlock(true);

        BlockState redstoneForward = getRedstone(direction, opposite);
        BlockState redstoneSideways = getRedstone(clockWise, counterClockWise);

        BlockState redstoneLeftFront = getRedstone(counterClockWise, opposite);
        BlockState redstoneLeftFrontRight = getRedstone(counterClockWise, opposite, clockWise);
        BlockState redstoneFrontRightBack = getRedstone(opposite, clockWise, direction);
        BlockState redstoneAll = getRedstone(opposite, clockWise, direction, counterClockWise);

        BlockState redstoneUpRight = getUpRedstone(clockWise);
        BlockState redstoneUpLeft = getUpRedstone(counterClockWise);
        BlockState redstoneUpBack = getUpRedstone(direction);

        BlockState redstoneUpLeftFront = getUpRedstone(counterClockWise, opposite);
        BlockState redstoneUpRightFront = getUpRedstone(clockWise, opposite);

        BlockState redstone2UpRightBackLeft = get2UpRedstone(clockWise, direction, counterClockWise);
        BlockState redstoneUpRightFrontLeft = getUpRedstone(clockWise, opposite, counterClockWise);

        BlockState repeater1 = repeater(opposite, 1);
        BlockState repeater2 = repeater(opposite, 2);
        BlockState repeater3 = repeater(opposite, 3);

        BlockState piston = Blocks.PISTON.defaultBlockState().setValue(PistonBaseBlock.FACING, Direction.UP);

        // main corridor
        createWalls(level, random, startPos.relative(counterClockWise, 5).relative(direction, 2), clockWise, 13);
        // left end of main corridor
        fill(level, random, startPos.relative(counterClockWise, 6), direction, 5, 5, this::getWallBlock);
        // entry door
        fill(level, random, startPos.relative(counterClockWise, 1).above(1), clockWise, 3, 3, this::air);
        // left corridor
        createWalls(level, random, startPos.relative(counterClockWise, 4).relative(direction, 4), direction, 6);

        // wall trap
        fill(level, random, startPos.relative(counterClockWise, 1).below(2), clockWise, 3, 2, this::getWallBlock);
        // pistons
        fill(level, random, startPos.relative(counterClockWise, 1).below(5), clockWise, 3, 3, _ -> piston);

        // bottom piston redstone
        fill(level, random, startPos.below(5).relative(direction, 1).relative(counterClockWise, 1), clockWise, 3, 1, _ -> repeater1);
        level.setBlock(startPos.below(5).relative(direction, 2).relative(clockWise, 1), redstoneLeftFront, 2);
        level.setBlock(startPos.below(5).relative(direction, 2), redstoneLeftFrontRight, 2);
        level.setBlock(startPos.below(5).relative(direction, 2).relative(counterClockWise, 1), redstoneFrontRightBack, 2);

        // middle piston redstone
        fill(level, random, startPos.below(3).relative(direction, 1).relative(counterClockWise, 1), clockWise, 3, 1, _ -> repeater1);
        level.setBlock(startPos.below(3).relative(direction, 2).relative(clockWise, 1), redstoneLeftFront, 2);
        level.setBlock(startPos.below(3).relative(direction, 2), redstoneAll, 2);
        level.setBlock(startPos.below(3).relative(direction, 2).relative(counterClockWise, 1), redstoneLeftFrontRight, 2);

        // top piston redstone
        fill(level, random, startPos.below(1).relative(direction, 1).relative(counterClockWise, 1), clockWise, 3, 1, _ -> repeater3);
        level.setBlock(startPos.below(2).relative(direction, 2).relative(counterClockWise, 1), slab, 2);

        level.setBlock(startPos.below(1).relative(direction, 2).relative(clockWise, 1), redstoneLeftFront, 2);
        level.setBlock(startPos.below(1).relative(direction, 2), redstoneLeftFrontRight, 2);
        level.setBlock(startPos.below(1).relative(direction, 2).relative(counterClockWise, 1), redstoneLeftFrontRight, 2);

        // middle to top wire
        level.setBlock(startPos.below(1).relative(direction, 2).relative(counterClockWise, 2), air, 2);
        level.setBlock(startPos.below(2).relative(direction, 2).relative(counterClockWise, 2), redstoneSideways, 2);

        // middle to bottom wire
        level.setBlock(startPos.below(3).relative(direction, 3), redstoneForward, 2);
        level.setBlock(startPos.below(3).relative(direction, 4), repeater2, 2);
        level.setBlock(startPos.below(3).relative(direction, 5), redstoneUpRightFrontLeft, 2);
        level.setBlock(startPos.below(3).relative(direction, 5).relative(counterClockWise, 1), air, 2);
        level.setBlock(startPos.below(4).relative(direction, 5).relative(counterClockWise, 1), redstoneUpRightFront, 2);
        level.setBlock(startPos.below(4).relative(direction, 4).relative(counterClockWise, 1), air, 2);
        level.setBlock(startPos.below(5).relative(direction, 4).relative(counterClockWise, 1), redstoneUpBack, 2);
        level.setBlock(startPos.below(5).relative(direction, 3).relative(counterClockWise, 1), redstoneForward, 2);

        // middle to right lever
        level.setBlock(startPos.below(2).relative(direction, 5), air, 2);
        level.setBlock(startPos.below(2).relative(clockWise, 1).relative(direction, 5), redstone2UpRightBackLeft, 2);
        level.setBlock(startPos.below(1).relative(clockWise, 1).relative(direction, 5), air, 2);
        level.setBlock(startPos.below(1).relative(clockWise, 2).relative(direction, 5), redstoneUpRight, 2);
        level.setBlock(startPos.relative(clockWise, 2).relative(direction, 5), air, 2);
        level.setBlock(startPos.relative(clockWise, 3).relative(direction, 5), redstoneUpRight, 2);
        level.setBlock(startPos.above(1).relative(clockWise, 3).relative(direction, 5), air, 2);
        level.setBlock(startPos.above(1).relative(clockWise, 4).relative(direction, 5), redstoneUpRight, 2);
        level.setBlock(startPos.above(2).relative(clockWise, 4).relative(direction, 5), air, 2);
        level.setBlock(startPos.above(2).relative(clockWise, 5).relative(direction, 5), redstoneSideways, 2);

        // middle to left lever
        level.setBlock(startPos.below(1).relative(clockWise, 1).relative(direction, 6), redstoneUpBack, 2);
        level.setBlock(startPos.relative(clockWise, 1).relative(direction, 6), air, 2);
        level.setBlock(startPos.relative(clockWise, 1).relative(direction, 7), redstoneUpLeftFront, 2);
        level.setBlock(startPos.above(1).relative(clockWise, 1).relative(direction, 7), air, 2);
        level.setBlock(startPos.above(1).relative(direction, 7), redstoneUpLeft, 2);
        level.setBlock(startPos.above(2).relative(direction, 7), air, 2);
        level.setBlock(startPos.above(2).relative(counterClockWise, 1).relative(direction, 7), redstoneSideways, 2);

        // levers
        level.setBlock(startPos.relative(clockWise, 5).relative(direction, 3).above(2), leverBack, 2);
        level.setBlock(startPos.relative(counterClockWise, 3).relative(direction, 7).above(2), leverRight, 2);

    }
}
