package com.adex.data.structure.refuge.piece;

import com.adex.block.ModBlocks;
import com.adex.data.structure.ModStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

import java.util.List;

public class BossRoom extends RefugePiece {

    private static final int WIDTH = 11;
    private static final int HEIGHT = 8;
    private static final int DEPTH = 11;

    private static final int OFFSET_X = -5;
    private static final int OFFSET_Y = 0;
    private static final int OFFSET_Z = 0;

    private static final Block[] GOLEM_BLOCKS = new Block[]{
            ModBlocks.CHALCEDONY_GOLEM_BLOCK,
            ModBlocks.GARNET_GOLEM_BLOCK,
            ModBlocks.JADE_GOLEM_BLOCK,
            ModBlocks.JASPER_GOLEM_BLOCK,
            ModBlocks.ONYX_GOLEM_BLOCK,
            ModBlocks.OPAL_GOLEM_BLOCK,
            ModBlocks.RUBY_GOLEM_BLOCK,
            ModBlocks.SAPPHIRE_GOLEM_BLOCK,
            ModBlocks.SPINEL_GOLEM_BLOCK,
            ModBlocks.TIGERS_EYE_GOLEM_BLOCK,
    };

    public BossRoom(int index, BoundingBox boundingBox, Direction direction, BlockPos startPos) {
        super(ModStructures.REFUGE_BOSS_ROOM, index, boundingBox, direction, startPos);
    }

    public BossRoom(int index, int x, int y, int z, Direction direction) {
        this(index, getBaseBoundingBox(direction).moved(x, y, z), direction, new BlockPos(x, y, z));
    }

    public BossRoom(CompoundTag compoundTag) {
        super(ModStructures.REFUGE_BOSS_ROOM, compoundTag);
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
        createOneWideWalls(level, random, boundingBox);
        Direction side = direction.getClockWise();
        fill(level, random, BoundingBox.fromCorners(startPos.above(1).relative(side, -1), startPos.above(3).relative(side, 1)), this::air);
        level.setBlock(startPos.above().relative(direction, 5), getGolemBlock(random), 2);
    }

    public BlockState getGolemBlock(RandomSource random) {
        return GOLEM_BLOCKS[random.nextInt(GOLEM_BLOCKS.length)].defaultBlockState();
    }
}
