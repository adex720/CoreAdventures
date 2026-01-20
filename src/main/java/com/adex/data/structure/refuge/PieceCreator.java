package com.adex.data.structure.refuge;

import com.adex.data.structure.refuge.piece.RefugePiece;
import net.minecraft.core.Direction;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

import java.util.function.Function;

public class PieceCreator {

    public final Creator creator;
    public final int weight;
    public final int minDepth;
    public final int maxCount;
    public int placeCount;

    public final Function<Direction, BoundingBox> boundingBox;

    /**
     * Creates a new PieceCreator.
     * PieceCreators are used for choosing and generating pieces in a refuge structure.
     *
     * @param creator     RefugePiece creator
     * @param weight      Weight of this PieceCreator being chosen when picking a random new piece
     * @param maxCount    Maximum amount of rooms this PieceCreator can create in one structure
     * @param minDepth    Minimum generation depth required for this PieceCreator to create a piece
     * @param boundingBox BoundingBox which must not collide with any BoundingBox of an existing piece in the refuge for
     *                    this PieceCreator to create a new piece. The bounding box is relative to the start position of
     *                    the new generation place
     */
    public PieceCreator(Creator creator, int weight, int maxCount, int minDepth, Function<Direction, BoundingBox> boundingBox) {
        this.creator = creator;
        this.weight = weight;
        this.minDepth = minDepth;
        this.maxCount = maxCount;
        this.boundingBox = boundingBox;
        placeCount = 0;
    }

    /**
     * Creates a new PieceCreator without a limit on generation depth.
     * PieceCreators are used for choosing and generating pieces in a refuge structure.
     *
     * @param creator     RefugePiece creator
     * @param weight      Weight of this PieceCreator being chosen when picking a random new piece
     * @param maxCount    Maximum amount of rooms this PieceCreator can create in one structure
     * @param boundingBox BoundingBox which must not collide with any BoundingBox of an existing piece in the refuge for
     *                    this PieceCreator to create a new piece. The bounding box is relative to the start position of
     *                    the new generation place
     */
    public PieceCreator(Creator creator, int weight, int maxCount, Function<Direction, BoundingBox> boundingBox) {
        this(creator, weight, maxCount, 0, boundingBox);
    }

    /**
     * @param depth Generation depth of the place
     * @return true if this creator has not yet created its maximum number of pieces and the generation depth is valid
     */
    public boolean canPlace(int depth) {
        return canPlace() && depth >= minDepth;
    }

    /**
     * @return true if this creator has not yet created its maximum number of pieces
     */
    public boolean canPlace() {
        return maxCount == 0 || placeCount < maxCount;
    }

    public BoundingBox getBoundingBox(Direction direction) {
        return boundingBox.apply(direction);
    }

    /**
     * Creates a new {@link RefugePiece} with given coordinates, direction and generation depth.
     */
    @FunctionalInterface
    public interface Creator {
        RefugePiece create(int depth, int x, int y, int z, Direction direction);
    }
}
