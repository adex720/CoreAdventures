package com.adex.data.structure.refuge.piece;

import com.adex.data.structure.refuge.RefugePieces;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;

public class EmptyRoom extends ElevenWideRoom{

    public EmptyRoom(int index, int x, int y, int z, Direction direction) {
        super(RefugePieces.REFUGE_EMPTY_ROOM, index, x, y, z, direction);
    }

    public EmptyRoom( CompoundTag compoundTag) {
        super(RefugePieces.REFUGE_EMPTY_ROOM, compoundTag);
    }
}
