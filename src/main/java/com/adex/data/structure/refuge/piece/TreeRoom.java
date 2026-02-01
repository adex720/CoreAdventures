package com.adex.data.structure.refuge.piece;

import com.adex.block.ModBlocks;
import com.adex.data.structure.refuge.RefugePieces;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

public class TreeRoom extends ElevenWideRoom {

    public TreeRoom(int index, int x, int y, int z, Direction direction) {
        super(RefugePieces.REFUGE_TREE_ROOM, index, getBaseBoundingBox(direction).moved(x, y, z), direction, new BlockPos(x, y, z));
    }

    public TreeRoom(CompoundTag compoundTag) {
        super(RefugePieces.REFUGE_TREE_ROOM, compoundTag);
    }

    @Override
    public void createBlocks(WorldGenLevel level, RandomSource random) {
        super.createBlocks(level, random);
        Direction clockwise = direction.getClockWise();
        Direction counterClockwise = direction.getCounterClockWise();
        BlockPos frontLeft = startPos.above().relative(direction, 1).relative(counterClockwise, 4);
        BlockPos frontRight = frontLeft.relative(clockwise, 8);
        BlockPos backLeft = frontLeft.relative(direction, 8);
        BlockPos backRight = frontRight.relative(direction, 8);
        BlockPos treePos = frontLeft.relative(direction, 4).relative(clockwise, 4);

        BlockState log = ModBlocks.JUNIPER_LOG.defaultBlockState();
        BlockState leaves = ModBlocks.JUNIPER_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true);
        BlockState flower = Blocks.ROSE_BUSH.defaultBlockState();
        BlockState grass = Blocks.SHORT_GRASS.defaultBlockState();

        fill(level, random, frontLeft.below(1), direction, clockwise, 9, 9, _ -> Blocks.GRASS_BLOCK.defaultBlockState()); // grass floor

        fill(level, random, treePos, direction, 1, 4, _ -> log); // tree log
        // tree leaves
        setBlock(level, treePos.above(1).relative(direction, -1).relative(clockwise, -1), leaves);
        setBlock(level, treePos.above(1).relative(direction, -1), leaves);
        setBlock(level, treePos.above(1).relative(direction, -1).relative(clockwise, 1), leaves);
        setBlock(level, treePos.above(1).relative(clockwise, -1), leaves);
        setBlock(level, treePos.above(1).relative(clockwise, 1), leaves);
        setBlock(level, treePos.above(1).relative(direction, 1).relative(clockwise, -1), leaves);
        setBlock(level, treePos.above(1).relative(direction, 1), leaves);
        setBlock(level, treePos.above(1).relative(direction, 1).relative(clockwise, 1), leaves);

        setBlock(level, treePos.above(1).relative(direction, -2).relative(clockwise, 1), leaves);
        setBlock(level, treePos.above(1).relative(direction, -2), leaves);
        setBlock(level, treePos.above(1).relative(direction, -2).relative(clockwise, -1), leaves);
        setBlock(level, treePos.above(1).relative(direction, -1).relative(clockwise, -2), leaves);
        setBlock(level, treePos.above(1).relative(clockwise, -2), leaves);
        setBlock(level, treePos.above(1).relative(direction, 1).relative(clockwise, -2), leaves);
        setBlock(level, treePos.above(1).relative(direction, 2).relative(clockwise, -1), leaves);
        setBlock(level, treePos.above(1).relative(direction, 2), leaves);
        setBlock(level, treePos.above(1).relative(direction, 2).relative(clockwise, 1), leaves);
        setBlock(level, treePos.above(1).relative(direction, 1).relative(clockwise, 2), leaves);
        setBlock(level, treePos.above(1).relative(clockwise, 2), leaves);
        setBlock(level, treePos.above(1).relative(direction, -1).relative(clockwise, 2), leaves);

        setBlock(level, treePos.above(2).relative(direction, -1).relative(clockwise, -1), leaves);
        setBlock(level, treePos.above(2).relative(direction, -1), leaves);
        setBlock(level, treePos.above(2).relative(direction, -1).relative(clockwise, 1), leaves);
        setBlock(level, treePos.above(2).relative(clockwise, -1), leaves);
        setBlock(level, treePos.above(2).relative(clockwise, 1), leaves);
        setBlock(level, treePos.above(2).relative(direction, 1).relative(clockwise, -1), leaves);
        setBlock(level, treePos.above(2).relative(direction, 1), leaves);
        setBlock(level, treePos.above(2).relative(direction, 1).relative(clockwise, 1), leaves);

        setBlock(level, treePos.above(2).relative(direction, -2).relative(clockwise, 1), leaves);
        setBlock(level, treePos.above(2).relative(direction, -2), leaves);
        setBlock(level, treePos.above(2).relative(direction, -2).relative(clockwise, -1), leaves);
        setBlock(level, treePos.above(2).relative(direction, -1).relative(clockwise, -2), leaves);
        setBlock(level, treePos.above(2).relative(clockwise, -2), leaves);
        setBlock(level, treePos.above(2).relative(direction, 1).relative(clockwise, -2), leaves);
        setBlock(level, treePos.above(2).relative(direction, 2).relative(clockwise, -1), leaves);
        setBlock(level, treePos.above(2).relative(direction, 2), leaves);
        setBlock(level, treePos.above(2).relative(direction, 2).relative(clockwise, 1), leaves);
        setBlock(level, treePos.above(2).relative(direction, 1).relative(clockwise, 2), leaves);
        setBlock(level, treePos.above(2).relative(clockwise, 2), leaves);
        setBlock(level, treePos.above(2).relative(direction, -1).relative(clockwise, 2), leaves);

        setBlock(level, treePos.above(3).relative(direction, -1).relative(clockwise, -1), leaves);
        setBlock(level, treePos.above(3).relative(direction, -1), leaves);
        setBlock(level, treePos.above(3).relative(direction, -1).relative(clockwise, 1), leaves);
        setBlock(level, treePos.above(3).relative(clockwise, -1), leaves);
        setBlock(level, treePos.above(3).relative(clockwise, 1), leaves);
        setBlock(level, treePos.above(3).relative(direction, 1).relative(clockwise, -1), leaves);
        setBlock(level, treePos.above(3).relative(direction, 1), leaves);
        setBlock(level, treePos.above(3).relative(direction, 1).relative(clockwise, 1), leaves);

        setBlock(level, treePos.above(4).relative(direction, -1), leaves);
        setBlock(level, treePos.above(4).relative(clockwise, -1), leaves);
        setBlock(level, treePos.above(4).relative(clockwise, 1), leaves);
        setBlock(level, treePos.above(4).relative(direction, 1), leaves);
        setBlock(level, treePos.above(4), leaves);

        // front left bush
        setBlock(level, frontLeft, leaves);
        setBlock(level, frontLeft.above(1), leaves);

        // back left bush
        setBlock(level, backLeft, log);
        setBlock(level, backLeft.relative(direction, -1), leaves);
        setBlock(level, backLeft.relative(direction, -2), leaves);
        setBlock(level, backLeft.relative(direction, -2), leaves);
        setBlock(level, backLeft.relative(clockwise, 1), leaves);
        setBlock(level, backLeft.relative(clockwise, 1).relative(direction, -1), leaves);
        setBlock(level, backLeft.above(1), leaves);
        setBlock(level, backLeft.above(1).relative(direction, -1), leaves);
        setBlock(level, backLeft.above(1).relative(clockwise, 1), leaves);

        // back right bush
        setBlock(level, backRight, leaves);
        setBlock(level, backRight.relative(direction, -1), leaves);
        setBlock(level, backRight.relative(direction, -2), leaves);
        setBlock(level, backRight.relative(counterClockwise, 1), leaves);
        setBlock(level, backRight.above(1), leaves);

        // front right bush
        setBlock(level, frontRight, leaves);
        setBlock(level, frontRight.above(1), leaves);

        // flowers
        setBlock(level, frontLeft.relative(direction, 2).relative(clockwise, 1), flower);
        setBlock(level, backLeft.relative(direction, -1).relative(clockwise, 3), flower);
        setBlock(level, backRight.relative(direction, -2).relative(counterClockwise, 1), flower);
        setBlock(level, frontRight.relative(direction, 2).relative(counterClockwise, 1), flower);
        setBlock(level, treePos.relative(clockwise, 1), flower);

        // grass
        fill(level, random, BoundingBox.fromCorners(frontLeft, backRight), either(grass, Blocks.AIR.defaultBlockState()), true);
    }
}
