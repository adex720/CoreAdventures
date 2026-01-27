package com.adex.data.structure.refuge.piece;

import com.adex.block.ModBlocks;
import com.adex.data.structure.refuge.RefugePieces;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;

public class FarmRoom extends ElevenWideRoom {

    public FarmRoom(int index, int x, int y, int z, Direction direction) {
        super(RefugePieces.REFUGE_FARM_ROOM, index, getBaseBoundingBox(direction).moved(x, y, z), direction, new BlockPos(x, y, z));
    }

    public FarmRoom(CompoundTag compoundTag) {
        super(RefugePieces.REFUGE_FARM_ROOM, compoundTag);
    }

    @Override
    public void createBlocks(WorldGenLevel level, RandomSource random) {
        super.createBlocks(level, random);
        Direction clockWise = direction.getClockWise();
        Direction counterClockWise = direction.getCounterClockWise();

        BlockState grass = Blocks.GRASS_BLOCK.defaultBlockState();
        BlockState soulSand = Blocks.SOUL_SAND.defaultBlockState();
        BlockState slab = ModBlocks.JUNIPER_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM);
        BlockState lantern = Blocks.LANTERN.defaultBlockState().setValue(LanternBlock.HANGING, true);

        // sweet berry farm
        fill(level, random, startPos.relative(counterClockWise, 2).relative(direction, 1), counterClockWise, direction, 3, 9, _ -> grass);
        fill(level, random, startPos.relative(counterClockWise, 2).relative(direction, 1).above(1), counterClockWise, direction, 3, 9, this::getSweetBerry);

        // nether wart farm
        fill(level, random, startPos.relative(clockWise, 2).relative(direction, 1), clockWise, direction, 3, 9, _ -> soulSand);
        fill(level, random, startPos.relative(clockWise, 2).relative(direction, 1).above(1), clockWise, direction, 3, 9, this::getNetherWart);

        // light
        level.setBlock(startPos.relative(direction, 5).relative(counterClockWise, 4).above(4), slab, 2);
        level.setBlock(startPos.relative(direction, 5).relative(counterClockWise, 3).above(4), slab, 2);
        level.setBlock(startPos.relative(direction, 5).relative(counterClockWise, 3).above(3), lantern, 2);
    }

    /**
     * Returns a random {@link BlockState} of {@link Blocks#SWEET_BERRY_BUSH}.
     * Chances for different ages are:
     * <p>0 (small) - 40%
     * <p>1 (large) - 50%
     * <p>2 (few berries) - 5%
     * <p>3 (many berries) - 5%
     */
    public BlockState getSweetBerry(RandomSource random) {
        float f = random.nextFloat();
        int age;
        if (f < 0.40f) age = 0;
        else if (f < 0.90f) age = 1;
        else if (f < 0.95f) age = 2;
        else age = 3;

        return Blocks.SWEET_BERRY_BUSH.defaultBlockState().setValue(SweetBerryBushBlock.AGE, age);
    }

    /**
     * Returns a random {@link BlockState} of {@link Blocks#NETHER_WART}.
     * Chances for different ages are:
     * <p>0 - 40%
     * <p>1 - 50%
     * <p>2 - 5%
     * <p>3 - 5%
     */
    public BlockState getNetherWart(RandomSource random) {
        float f = random.nextFloat();
        int age;
        if (f < 0.40f) age = 0;
        else if (f < 0.90f) age = 1;
        else if (f < 0.95f) age = 2;
        else age = 3;

        return Blocks.NETHER_WART.defaultBlockState().setValue(NetherWartBlock.AGE, age);
    }
}
