package com.adex.data.structure.refuge.piece;

import com.adex.block.ModBlocks;
import com.adex.data.loottable.ModLootTables;
import com.adex.data.structure.refuge.RefugePieces;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.StairsShape;

public class PortalRoom extends ElevenWideRoom {

    public PortalRoom(int index, int x, int y, int z, Direction direction) {
        super(RefugePieces.REFUGE_PORTAL_ROOM, index, x, y, z, direction);
    }

    public PortalRoom(CompoundTag compoundTag) {
        super(RefugePieces.REFUGE_PORTAL_ROOM, compoundTag);
    }

    /**
     * A portal room has a 5x6 core portal whose frame blocks have a 75% chance of being reinforced ancient debris.
     */
    @Override
    public void createBlocks(WorldGenLevel level, RandomSource random) {
        super.createBlocks(level, random);
        addRandomTreasureChest(level, random, ModLootTables.REFUGE_PORTAL_ROOM);

        Direction clockWise = direction.getClockWise();
        BlockPos portalLeftBottom = startPos.relative(direction, 9).relative(clockWise, -2).above(1);
        BlockPos portalRightBottom = portalLeftBottom.relative(clockWise, 4);
        BlockPos portalLeftTop = portalLeftBottom.above(5);

        // base
        fill(level, random, portalLeftBottom, clockWise, 5, 1, this::getPortalBlock);
        // left side
        fill(level, random, portalLeftBottom.above(1), clockWise, 1, 4, this::getPortalBlock);
        // right side
        fill(level, random, portalRightBottom.above(1), clockWise, 1, 4, this::getPortalBlock);
        // top
        fill(level, random, portalLeftTop, clockWise, 5, 1, this::getPortalBlock);

        // stairs around
        fill(level, random, portalLeftBottom.relative(direction, -1), clockWise, 5, 1, _ -> getStairBlock(direction, false));
        level.setBlock(portalLeftBottom.relative(direction, -1).relative(clockWise, -1), getStairBlock(direction, false, StairsShape.OUTER_RIGHT), 2);
        level.setBlock(portalLeftBottom.relative(clockWise, -1), getStairBlock(clockWise, false), 2);
        level.setBlock(portalRightBottom.relative(direction, -1).relative(clockWise, 1), getStairBlock(direction, false, StairsShape.OUTER_LEFT), 2);
        level.setBlock(portalRightBottom.relative(clockWise, 1), getStairBlock(direction.getCounterClockWise(), false), 2);
    }

    /**
     * 75% chance to return {@link ModBlocks#REINFORCED_ANCIENT_DEBRIS}
     * 20% chance to return {@link Blocks#AIR}
     * 5% chance to return {@link ModBlocks#HARDENED_STONE_BRICKS}
     *
     * @param random RandomSource
     * @return BlockState to be placed at portal
     */
    public BlockState getPortalBlock(RandomSource random) {
        float f = random.nextFloat();
        if (f < 0.05f) return ModBlocks.HARDENED_STONE_BRICKS.defaultBlockState();
        if (f < 0.25f) return Blocks.AIR.defaultBlockState();
        return ModBlocks.REINFORCED_ANCIENT_DEBRIS.defaultBlockState();
    }
}
