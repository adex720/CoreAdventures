package com.adex.data.structure.refuge.piece;

import com.adex.data.structure.refuge.RefugePieces;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

public class MusicRoom extends NineWideRoom {

    // Note values of C major scale
    private static final int[] PITCHES = {6, 8, 10, 11, 13, 15, 17, 18};

    public MusicRoom(int index, BoundingBox boundingBox, Direction direction, BlockPos pos) {
        super(RefugePieces.REFUGE_MUSIC_ROOM, index, boundingBox, direction, pos);
    }

    public MusicRoom(int index, int x, int y, int z, Direction direction) {
        this(index, getBaseBoundingBox(direction).moved(x, y, z), direction, new BlockPos(x, y, z));
    }

    public MusicRoom(CompoundTag compoundTag) {
        super(RefugePieces.REFUGE_MUSIC_ROOM, compoundTag);
    }

    @Override
    public void createBlocks(WorldGenLevel level, RandomSource random) {
        super.createBlocks(level, random);
        Direction clockWise = direction.getClockWise();
        Direction counterClockWise = direction.getCounterClockWise();
        Direction opposite = direction.getOpposite();

        BlockState jukebox = Blocks.JUKEBOX.defaultBlockState();

        // jukeboxes
        fill(level, random, startPos.above(1).relative(direction, 7).relative(counterClockWise, 1), clockWise, 3, 1, _ -> jukebox);

        // left noteBlocks
        createNoteBlocks(level, startPos.relative(counterClockWise, 3).relative(direction, 1).above(1), direction, 7, Blocks.CLAY);
        // right noteBlocks
        createNoteBlocks(level, startPos.relative(clockWise, 3).relative(direction, 7).above(1), opposite, 7, Blocks.BONE_BLOCK);
    }

    /**
     * Creates a row of note blocks with note values set according to {@link MusicRoom#PITCHES}
     *
     * @param level     WorldGenLevel
     * @param pos       {@link BlockPos} of the block below the first note block
     * @param direction Direction to generate note blocks
     * @param length    Amount of note blocks to generate
     * @param base      Block to place under each note block
     */
    public void createNoteBlocks(WorldGenLevel level, BlockPos pos, Direction direction, int length, Block base) {
        BlockState state = base.defaultBlockState();
        for (int i = 0; i < length; i++) {
            level.setBlock(pos.relative(direction, i), state, 2);
            level.setBlock(pos.relative(direction, i).above(1), getNoteBlock(PITCHES[i]), 2);
        }
    }

    /**
     * Returns the {@link BlockState} of {@link Blocks#NOTE_BLOCK} with NOTE value set to the given value
     */
    public BlockState getNoteBlock(int note) {
        return Blocks.NOTE_BLOCK.defaultBlockState().setValue(NoteBlock.NOTE, note);
    }
}
