package com.adex.data.structure.refuge;

import com.adex.data.structure.refuge.piece.*;
import com.adex.util.Util;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RefugeBuilder {

    private final StructurePiecesBuilder builder;
    private final Structure.GenerationContext context;

    private final PieceCreator[] pieceCreators;
    private final Set<BoundingBox> boundingBoxes;
    private final Set<Pair<BlockPos, Direction.Axis>> endPoints; // Pos is bottom middle of air, axis is that of opening
    //TODO: actually fill these

    private final RandomSource random;

    private boolean hasBossRoom;
    private RootPiece rootPiece;
    private int tryCount;

    private final int minRoomCount;
    private int roomCount;

    private final ArrayList<ContinuationPoint> openPoints;

    public RefugeBuilder(StructurePiecesBuilder builder, Structure.GenerationContext context, RandomSource random, int minRoomCount) {
        this.builder = builder;
        this.context = context;
        this.random = random;
        this.minRoomCount = minRoomCount;

        pieceCreators = createPieceCreator();
        boundingBoxes = new HashSet<>();
        endPoints = new HashSet<>();

        hasBossRoom = false;
        rootPiece = null;
        tryCount = 0;
        roomCount = 0;

        openPoints = new ArrayList<>();
    }

    private static PieceCreator[] createPieceCreator() {
        return new PieceCreator[]{
                new PieceCreator(BossRoom::new, 5, 1, 2, BossRoom::getBaseBoundingBox), //TODO: min distance to 5
                new PieceCreator(ThreeWayRoom::new, 10, 5, ThreeWayRoom::getBaseBoundingBox)
        };
    }

    public void generate(int maxTries) {
        for (int i = 0; i < maxTries; i++) {
            tryGenerate();
            if (isValid()) return;
        }

        clear();
    }

    private void tryGenerate() {
        prepareGeneration();
        createLayout();
    }

    public void clear() {
        builder.clear();
        openPoints.clear();
        endPoints.clear();

        for (PieceCreator pieceCreator : pieceCreators) pieceCreator.placed = 0;
    }

    private void prepareGeneration() {
        clear();
        context.random().setLargeFeatureSeed(context.seed() + tryCount, context.chunkPos().x, context.chunkPos().z);
        tryCount++;
    }

    @SuppressWarnings("DataFlowIssue")
    private void createLayout() {
        int y = 64;
        BlockPos pos = new BlockPos(context.chunkPos().getBlockX(7), y, context.chunkPos().getBlockZ(7));
        rootPiece = new RootPiece(context.random(), pos.getX(), pos.getY(), pos.getZ());
        addPiece(rootPiece, pos);

        while (!openPoints.isEmpty()) {
            ContinuationPoint point = Util.removeRandomElement(openPoints, random);
            RefugePiece piece = getValidPiece(point.pos(), point.direction(), point.depth());

            if (piece == null) {
                addEndPoint(point.pos(), point.direction());
                continue;
            }

            addPiece(piece, point.pos());
        }
    }

    @Nullable
    private RefugePiece getValidPiece(BlockPos pos, Direction direction, int distance) {
        int totalWeight = 0;

        ArrayList<PieceCreator> validPieces = new ArrayList<>();

        pieceLoop:
        for (PieceCreator pieceCreator : pieceCreators) {
            if (!pieceCreator.canPlace(distance)) continue;

            BoundingBox boundingBox = pieceCreator.getBoundingBox(direction).moved(pos.getX(), pos.getY(), pos.getZ());
            for (BoundingBox existing : boundingBoxes) {
                if (existing.intersects(boundingBox)) continue pieceLoop;
            }

            validPieces.add(pieceCreator);
            totalWeight += pieceCreator.weight;
        }

        if (totalWeight == 0) return null;

        int randomNumber = random.nextInt(totalWeight);
        for (PieceCreator creator : validPieces) {
            if (randomNumber > creator.weight) {
                randomNumber -= creator.weight;
                continue;
            }

            RefugePiece piece = creator.creator.create(distance, pos.getX(), pos.getY(), pos.getZ(), direction);
            if (piece != null) {
                creator.placed++;
                return piece;
            }
        }

        return null;
    }

    private void addPiece(RefugePiece piece, BlockPos pos) {
        builder.addPiece(piece);
        openPoints.addAll(piece.getContinuationPoints(pos, piece.getDirection(), piece.getGenDepth() + 1));
        boundingBoxes.add(piece.getBoundingBox());
        roomCount++;

        if (piece instanceof BossRoom) hasBossRoom = true;
    }

    private void addEndPoint(BlockPos pos, Direction normal) {
        endPoints.add(new Pair<>(pos.above(), normal.getClockWise().getAxis()));
    }

    private void updateBlocks(WorldGenLevel level, RandomSource random) {
        // TODO: endPoints as own pieces
    }

    public boolean isValid() {
        return roomCount >= minRoomCount && hasBossRoom;
    }
}
