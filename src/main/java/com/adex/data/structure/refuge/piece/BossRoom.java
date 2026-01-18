package com.adex.data.structure.refuge.piece;

import com.adex.block.ModBlocks;
import com.adex.data.structure.refuge.RefugePieces;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BossRoom extends ElevenWideRoom {

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

    public BossRoom(int index, int x, int y, int z, Direction direction) {
        super(RefugePieces.REFUGE_BOSS_ROOM, index, getBaseBoundingBox(direction).moved(x, y, z), direction, new BlockPos(x, y, z));
    }

    public BossRoom(CompoundTag compoundTag) {
        super(RefugePieces.REFUGE_BOSS_ROOM, compoundTag);
    }

    @Override
    public void createBlocks(WorldGenLevel level, RandomSource random) {
        super.createBlocks(level, random);
        level.setBlock(startPos.above().relative(direction, 5), getGolemBlock(random), 2);
    }

    public BlockState getGolemBlock(RandomSource random) {
        return GOLEM_BLOCKS[random.nextInt(GOLEM_BLOCKS.length)].defaultBlockState();
    }
}
