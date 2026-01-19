package com.adex.data.structure.refuge;

import com.adex.data.structure.refuge.piece.*;
import com.adex.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
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

    private final BoundingBox validArea;

    private final PieceCreator[] pieceCreators;
    private final Set<BoundingBox> boundingBoxes;

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

        // Minecraft allows a structure to be at maximum in a 256 x 256 x 256 area.
        // The valid area being a bit larger makes it so that some pieces can be generated outside that area.
        // The blocks for these pieces won't be placed, but if the path comes back to the allowed area,
        // the pieces back on the allowed area will be generated normally.
        // This will act as a far away part of the refuge been abandoned/collapsed.
        validArea = new BoundingBox(context.chunkPos().getMinBlockX() - 137, 10, context.chunkPos().getMinBlockZ() - 137, context.chunkPos().getMinBlockX() + 138, 200, context.chunkPos().getMinBlockZ() + 138);

        pieceCreators = createPieceCreator();
        boundingBoxes = new HashSet<>();

        hasBossRoom = false;
        rootPiece = null;
        tryCount = 0;
        roomCount = 0;

        openPoints = new ArrayList<>();
    }

    private static PieceCreator[] createPieceCreator() {
        return new PieceCreator[]{
                new PieceCreator(BossRoom::new, 5, 1, 5, BossRoom::getBaseBoundingBox),
                // This BlockPos is only used for collision detection.
                // Endpoints are placed inside existing corridors, so they can't have correct bounding box here
                new PieceCreator(EndPointPiece::new, 4, 2, 4, _ -> new BoundingBox(BlockPos.ZERO)), //TODO: make custom bounding box to prevent spawning where it is the only possible piece
                new PieceCreator(EmptyRoom::new, 5, 1, EmptyRoom::getBaseBoundingBox),
                new PieceCreator(TreasureRoom::new, 5, 1, 2, TreasureRoom::getBaseBoundingBox),
                new PieceCreator(CorridorShort::new, 3, 10, CorridorShort::getBaseBoundingBox),
                new PieceCreator(CorridorLong::new, 4, 10, CorridorLong::getBaseBoundingBox),
                new PieceCreator(CorridorChest::new, 1, 5, CorridorChest::getBaseBoundingBox),
                new PieceCreator(CorridorLeft::new, 5, 5, CorridorLeft::getBaseBoundingBox),
                new PieceCreator(CorridorRight::new, 5, 5, CorridorRight::getBaseBoundingBox),
                new PieceCreator(TurnLeft::new, 5, 5, TurnLeft::getBaseBoundingBox),
                new PieceCreator(TurnRight::new, 5, 5, TurnRight::getBaseBoundingBox),
                new PieceCreator(StairsUp::new, 10, 5, StairsUp::getBaseBoundingBox),
                new PieceCreator(StairsDown::new, 10, 5, StairsDown::getBaseBoundingBox),
                new PieceCreator(StairsChest::new, 2, 1, StairsChest::getBaseBoundingBox),
                new PieceCreator(LadderUp::new, 10, 5, LadderUp::getBaseBoundingBox),
                new PieceCreator(LadderDown::new, 10, 5, LadderDown::getBaseBoundingBox),
                new PieceCreator(LadderHigh::new, 1, 1, 3, LadderHigh::getBaseBoundingBox),
                new PieceCreator(LadderHidden::new, 10, 1, 3, LadderHidden::getBaseBoundingBox),
                new PieceCreator(ThreeWayLeft::new, 10, 2, ThreeWayLeft::getBaseBoundingBox),
                new PieceCreator(ThreeWayMiddle::new, 10, 2, ThreeWayMiddle::getBaseBoundingBox),
                new PieceCreator(ThreeWayRight::new, 10, 2, ThreeWayRight::getBaseBoundingBox),
                new PieceCreator(LoweringThreeWayLeft::new, 5, 1, LoweringThreeWayLeft::getBaseBoundingBox),
                new PieceCreator(LoweringThreeWayMiddle::new, 5, 1, LoweringThreeWayMiddle::getBaseBoundingBox),
                new PieceCreator(LoweringThreeWayRight::new, 5, 1, LoweringThreeWayRight::getBaseBoundingBox),
                new PieceCreator(RisingThreeWayLeft::new, 5, 1, RisingThreeWayLeft::getBaseBoundingBox),
                new PieceCreator(RisingThreeWayMiddle::new, 5, 1, RisingThreeWayMiddle::getBaseBoundingBox),
                new PieceCreator(RisingThreeWayRight::new, 5, 1, RisingThreeWayRight::getBaseBoundingBox),
                new PieceCreator(EightWay::new, 10, 1, 2, EightWay::getBaseBoundingBox)
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
        roomCount = 0;
        hasBossRoom = false;

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
        BlockPos pos = new BlockPos(context.chunkPos().getBlockX(0), y, context.chunkPos().getBlockZ(0));
        rootPiece = new RootPiece(context.random(), pos.getX(), pos.getY(), pos.getZ());
        addPiece(rootPiece);

        while (!openPoints.isEmpty()) {
            ContinuationPoint point = Util.removeRandomElement(openPoints, random);
            RefugePiece piece = getValidPiece(point.pos(), point.direction(), point.depth());

            if (piece == null) {
                addEndPoint(point.pos().relative(point.direction().getOpposite()), point.direction().getClockWise(), point.depth());
                continue;
            }

            addPiece(piece);
        }
    }

    @Nullable
    private RefugePiece getValidPiece(BlockPos pos, Direction direction, int distance) {
        int totalWeight = 0;

        ArrayList<PieceCreator> validPieces = new ArrayList<>();

        // Loop through all possible pieces and filter out ones which can't be placed here
        pieceLoop:
        for (PieceCreator pieceCreator : pieceCreators) {
            if (!pieceCreator.canPlace(distance)) continue;

            BoundingBox boundingBox = pieceCreator.getBoundingBox(direction).moved(pos.getX(), pos.getY(), pos.getZ());

            if (!Util.isCompletelyInside(validArea, boundingBox)) {
                // This piece would go too far from the structure start
                continue;
            }

            // Check if the current piece to test would collide with any of the existing pieces
            for (BoundingBox existing : boundingBoxes) {
                if (existing.intersects(boundingBox)) continue pieceLoop;
            }

            validPieces.add(pieceCreator);
            totalWeight += pieceCreator.weight;
        }

        if (totalWeight == 0) return null; // No piece can generate here

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

    private void addPiece(RefugePiece piece) {
        builder.addPiece(piece);
        openPoints.addAll(piece.getContinuationPoints(piece.getStartPos(), piece.getDirection(), piece.getGenDepth() + 1));
        boundingBoxes.add(piece.getBoundingBox());

        if (!(piece instanceof EndPointPiece)) roomCount++;
        if (piece instanceof BossRoom) hasBossRoom = true;
    }

    private void addEndPoint(BlockPos pos, Direction direction, int depth) {
        addPiece(new EndPointPiece(direction, pos.above(), depth));
    }

    public boolean isValid() {
        return roomCount >= minRoomCount && hasBossRoom;
    }
}
