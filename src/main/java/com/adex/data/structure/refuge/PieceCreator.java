package com.adex.data.structure.refuge;

import com.adex.data.structure.refuge.piece.RefugePiece;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import org.jspecify.annotations.Nullable;

import java.util.function.Function;

public class PieceCreator {

    public final Creator creator;
    public final int weight;
    public final int minDistance;
    public final int maxCount;
    public int placed;

    public final Function<Direction, BoundingBox> boundingBox;

    public PieceCreator(Creator creator, int weight, int maxCount, int minDistance, Function<Direction, BoundingBox> boundingBox) {
        this.creator = creator;
        this.weight = weight;
        this.minDistance = minDistance;
        this.maxCount = maxCount;
        this.boundingBox = boundingBox;
        placed = 0;
    }

    public PieceCreator(Creator creator, int weight, int maxCount, Function<Direction, BoundingBox> boundingBox) {
        this(creator, weight, maxCount, 0, boundingBox);
    }

    public boolean canPlace(int distance) {
        return canPlace() && distance >= minDistance;
    }

    public boolean canPlace() {
        return maxCount == 0 || placed < maxCount;
    }

    public BoundingBox getBoundingBox(Direction direction) {
        return boundingBox.apply(direction);
    }

    @FunctionalInterface
    public interface Creator {
        @Nullable
        RefugePiece create(int index,int x, int y, int z, Direction direction);
    }
}
