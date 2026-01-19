package com.adex.data.structure.refuge.piece;

import com.adex.block.ModBlocks;
import com.adex.data.structure.refuge.RefugePieces;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
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
        level.setBlock(treePos.above(1).relative(direction, -1).relative(clockwise, -1), leaves, 2);
        level.setBlock(treePos.above(1).relative(direction, -1), leaves, 2);
        level.setBlock(treePos.above(1).relative(direction, -1).relative(clockwise, 1), leaves, 2);
        level.setBlock(treePos.above(1).relative(clockwise, -1), leaves, 2);
        level.setBlock(treePos.above(1).relative(clockwise, 1), leaves, 2);
        level.setBlock(treePos.above(1).relative(direction, 1).relative(clockwise, -1), leaves, 2);
        level.setBlock(treePos.above(1).relative(direction, 1), leaves, 2);
        level.setBlock(treePos.above(1).relative(direction, 1).relative(clockwise, 1), leaves, 2);

        level.setBlock(treePos.above(1).relative(direction, -2).relative(clockwise, 1), leaves, 2);
        level.setBlock(treePos.above(1).relative(direction, -2), leaves, 2);
        level.setBlock(treePos.above(1).relative(direction, -2).relative(clockwise, -1), leaves, 2);
        level.setBlock(treePos.above(1).relative(direction, -1).relative(clockwise, -2), leaves, 2);
        level.setBlock(treePos.above(1).relative(clockwise, -2), leaves, 2);
        level.setBlock(treePos.above(1).relative(direction, 1).relative(clockwise, -2), leaves, 2);
        level.setBlock(treePos.above(1).relative(direction, 2).relative(clockwise, -1), leaves, 2);
        level.setBlock(treePos.above(1).relative(direction, 2), leaves, 2);
        level.setBlock(treePos.above(1).relative(direction, 2).relative(clockwise, 1), leaves, 2);
        level.setBlock(treePos.above(1).relative(direction, 1).relative(clockwise, 2), leaves, 2);
        level.setBlock(treePos.above(1).relative(clockwise, 2), leaves, 2);
        level.setBlock(treePos.above(1).relative(direction, -1).relative(clockwise, 2), leaves, 2);

        level.setBlock(treePos.above(2).relative(direction, -1).relative(clockwise, -1), leaves, 2);
        level.setBlock(treePos.above(2).relative(direction, -1), leaves, 2);
        level.setBlock(treePos.above(2).relative(direction, -1).relative(clockwise, 1), leaves, 2);
        level.setBlock(treePos.above(2).relative(clockwise, -1), leaves, 2);
        level.setBlock(treePos.above(2).relative(clockwise, 1), leaves, 2);
        level.setBlock(treePos.above(2).relative(direction, 1).relative(clockwise, -1), leaves, 2);
        level.setBlock(treePos.above(2).relative(direction, 1), leaves, 2);
        level.setBlock(treePos.above(2).relative(direction, 1).relative(clockwise, 1), leaves, 2);

        level.setBlock(treePos.above(2).relative(direction, -2).relative(clockwise, 1), leaves, 2);
        level.setBlock(treePos.above(2).relative(direction, -2), leaves, 2);
        level.setBlock(treePos.above(2).relative(direction, -2).relative(clockwise, -1), leaves, 2);
        level.setBlock(treePos.above(2).relative(direction, -1).relative(clockwise, -2), leaves, 2);
        level.setBlock(treePos.above(2).relative(clockwise, -2), leaves, 2);
        level.setBlock(treePos.above(2).relative(direction, 1).relative(clockwise, -2), leaves, 2);
        level.setBlock(treePos.above(2).relative(direction, 2).relative(clockwise, -1), leaves, 2);
        level.setBlock(treePos.above(2).relative(direction, 2), leaves, 2);
        level.setBlock(treePos.above(2).relative(direction, 2).relative(clockwise, 1), leaves, 2);
        level.setBlock(treePos.above(2).relative(direction, 1).relative(clockwise, 2), leaves, 2);
        level.setBlock(treePos.above(2).relative(clockwise, 2), leaves, 2);
        level.setBlock(treePos.above(2).relative(direction, -1).relative(clockwise, 2), leaves, 2);

        level.setBlock(treePos.above(3).relative(direction, -1).relative(clockwise, -1), leaves, 2);
        level.setBlock(treePos.above(3).relative(direction, -1), leaves, 2);
        level.setBlock(treePos.above(3).relative(direction, -1).relative(clockwise, 1), leaves, 2);
        level.setBlock(treePos.above(3).relative(clockwise, -1), leaves, 2);
        level.setBlock(treePos.above(3).relative(clockwise, 1), leaves, 2);
        level.setBlock(treePos.above(3).relative(direction, 1).relative(clockwise, -1), leaves, 2);
        level.setBlock(treePos.above(3).relative(direction, 1), leaves, 2);
        level.setBlock(treePos.above(3).relative(direction, 1).relative(clockwise, 1), leaves, 2);

        level.setBlock(treePos.above(4).relative(direction, -1), leaves, 2);
        level.setBlock(treePos.above(4).relative(clockwise, -1), leaves, 2);
        level.setBlock(treePos.above(4).relative(clockwise, 1), leaves, 2);
        level.setBlock(treePos.above(4).relative(direction, 1), leaves, 2);
        level.setBlock(treePos.above(4), leaves, 2);

        // front left bush
        level.setBlock(frontLeft, leaves, 2);
        level.setBlock(frontLeft.above(1), leaves, 2);

        // back left bush
        level.setBlock(backLeft, log, 2);
        level.setBlock(backLeft.relative(direction, -1), leaves, 2);
        level.setBlock(backLeft.relative(direction, -2), leaves, 2);
        level.setBlock(backLeft.relative(direction, -2), leaves, 2);
        level.setBlock(backLeft.relative(clockwise, 1), leaves, 2);
        level.setBlock(backLeft.relative(clockwise, 1).relative(direction, -1), leaves, 2);
        level.setBlock(backLeft.above(1), leaves, 2);
        level.setBlock(backLeft.above(1).relative(direction, -1), leaves, 2);
        level.setBlock(backLeft.above(1).relative(clockwise, 1), leaves, 2);

        // back right bush
        level.setBlock(backRight, leaves, 2);
        level.setBlock(backRight.relative(direction, -1), leaves, 2);
        level.setBlock(backRight.relative(direction, -2), leaves, 2);
        level.setBlock(backRight.relative(counterClockwise, 1), leaves, 2);
        level.setBlock(backRight.above(1), leaves, 2);

        // front right bush
        level.setBlock(frontRight, leaves, 2);
        level.setBlock(frontRight.above(1), leaves, 2);

        // flowers
        level.setBlock(frontLeft.relative(direction, 2).relative(clockwise, 1), flower, 2);
        level.setBlock(backLeft.relative(direction, -1).relative(clockwise, 3), flower, 2);
        level.setBlock(backRight.relative(direction, -2).relative(counterClockwise, 1), flower, 2);
        level.setBlock(frontRight.relative(direction, 2).relative(counterClockwise, 1), flower, 2);
        level.setBlock(treePos.relative(clockwise, 1), flower, 2);

        // grass
        fill(level, random, BoundingBox.fromCorners(frontLeft, backRight), either(grass, Blocks.AIR.defaultBlockState()), true);
    }
}
