package com.adex.data.structure.refuge;

import com.adex.CoreAdventures;
import com.adex.data.structure.refuge.piece.*;
import com.adex.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * An instance of this class generates one refuge structure when {@link RefugeBuilder#generate(int)} is called.
 * If a valid refuge is generated, the blocks are automatically placeCount via
 * {@link net.minecraft.world.level.levelgen.structure.StructurePiece#postProcess(WorldGenLevel, StructureManager, ChunkGenerator, RandomSource, BoundingBox, ChunkPos, BlockPos)}
 * which gets called by vanilla code.
 * <p>
 * See {@link RefugeBuilder#isValid()} for conditions of a valid refuge.
 */
public class RefugeBuilder {

    private final StructurePiecesBuilder builder;
    private final Structure.GenerationContext context;

    private final BoundingBox validArea;

    private final PieceCreator[] pieceCreators;
    private final Set<BoundingBox> boundingBoxes;

    private final WorldgenRandom random;

    private boolean hasBossRoom;
    private int tryCount;

    private final int minRoomCount;
    private int roomCount;

    private final ArrayList<ContinuationPoint> openPoints;

    /**
     * Initializes a new RefugeBuilder.
     * Generation is started by calling {@link RefugeBuilder#generate(int)}.
     *
     * @param builder      StructurePiecesBuilder used for saving pieces data.
     * @param context      Generation context
     * @param minRoomCount Minimum number of pieces in a refuge for it to be valid. Not including EndPointPieces
     */
    public RefugeBuilder(StructurePiecesBuilder builder, Structure.GenerationContext context, int minRoomCount) {
        this.builder = builder;
        this.context = context;
        this.random = context.random();
        this.minRoomCount = minRoomCount;

        // Minecraft allows a structure to be at maximum in a 256 x 256 x 256 area.
        // The valid area being a bit larger makes it so that some pieces can be generated outside that area.
        // The blocks for these pieces won't be placeCount, but if the path comes back to the allowed area,
        // the pieces back on the allowed area will be generated normally.
        // This will act as a far away part of the refuge been abandoned/collapsed.
        validArea = new BoundingBox(context.chunkPos().getMinBlockX() - 137, 10, context.chunkPos().getMinBlockZ() - 137, context.chunkPos().getMinBlockX() + 138, 200, context.chunkPos().getMinBlockZ() + 138);

        pieceCreators = createPieceCreator();
        boundingBoxes = new HashSet<>();

        hasBossRoom = false;
        tryCount = 0;
        roomCount = 0;

        openPoints = new ArrayList<>();
    }

    /**
     * Creates and returns a new array of {@link PieceCreator}s
     * containing information about generation rules of every possible refuge piece.
     * {@link RefugeBuilder#clear()} must be called between every generation try to reset {@link PieceCreator#placeCount}
     */
    private static PieceCreator[] createPieceCreator() {
        return new PieceCreator[]{
                new PieceCreator(BossRoom::new, 5, 1, 5, BossRoom::getBaseBoundingBox),
                new PieceCreator(EndPointPiece::new, 1, 2, 4, EndPointPiece::getBoundingBoxForPlacement),
                new PieceCreator(EmptyRoom::new, 5, 1, EmptyRoom::getBaseBoundingBox),
                new PieceCreator(TreasureRoom::new, 5, 1, 2, TreasureRoom::getBaseBoundingBox),
                new PieceCreator(TreeRoom::new, 5, 2, 2, TreeRoom::getBaseBoundingBox),
                new PieceCreator(PortalRoom::new, 5, 1, 2, PortalRoom::getBaseBoundingBox),
                new PieceCreator(FarmRoom::new, 5, 1, 2, FarmRoom::getBaseBoundingBox),
                new PieceCreator(BedRoom::new, 2, 5, 6, BedRoom::getBaseBoundingBox),
                new PieceCreator(EnchantingRoom::new, 5, 1, 2, EnchantingRoom::getBaseBoundingBox),
                new PieceCreator(BrewingRoom::new, 5, 1, 2, BrewingRoom::getBaseBoundingBox),
                new PieceCreator(MusicRoom::new, 5, 1, 2, MusicRoom::getBaseBoundingBox),
                new PieceCreator(EndChestRoom::new, 3, 2, 3, EndChestRoom::getBaseBoundingBox),
                new PieceCreator(EndTrapChestRoom::new, 2, 1, 3, EndTrapChestRoom::getBaseBoundingBox),
                new PieceCreator(CorridorShort::new, 5, 10, CorridorShort::getBaseBoundingBox),
                new PieceCreator(CorridorLong::new, 10, 10, CorridorLong::getBaseBoundingBox),
                new PieceCreator(CorridorChest::new, 3, 5, CorridorChest::getBaseBoundingBox),
                new PieceCreator(CorridorLeft::new, 5, 5, CorridorLeft::getBaseBoundingBox),
                new PieceCreator(CorridorRight::new, 5, 5, CorridorRight::getBaseBoundingBox),
                new PieceCreator(TurnLeft::new, 5, 5, TurnLeft::getBaseBoundingBox),
                new PieceCreator(TurnRight::new, 5, 5, TurnRight::getBaseBoundingBox),
                new PieceCreator(TurnULeft::new, 5, 3, TurnULeft::getBaseBoundingBox),
                new PieceCreator(TurnURight::new, 5, 3, TurnURight::getBaseBoundingBox),
                new PieceCreator(StairsUp::new, 10, 5, StairsUp::getBaseBoundingBox),
                new PieceCreator(StairsDown::new, 10, 5, StairsDown::getBaseBoundingBox),
                new PieceCreator(StairsChest::new, 2, 1, StairsChest::getBaseBoundingBox),
                new PieceCreator(StairsEight::new, 10, 1, StairsEight::getBaseBoundingBox),
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
                new PieceCreator(RedstoneThreeWay::new, 10, 1, 5, RedstoneThreeWay::getBaseBoundingBox),
                new PieceCreator(EightWay::new, 10, 1, 2, EightWay::getBaseBoundingBox)
        };
    }

    /**
     * Tries to generate a valid refuge.
     * No RefugeBuilder instance should call this method twice,
     * because every builder has a specific position defined in its {@link RefugeBuilder#context}.
     * The generation progress is deterministic so that the same {@link Structure.GenerationContext#seed()} and
     * {@link Structure.GenerationContext#chunkPos()} values always result in identical refuge pieces.
     * The only exception to this is if maxTries has a different value so that the lower maxTries value results in no
     * valid refuge and the higher maxTries value results in a valid refuge.
     *
     * @param maxTries Maximum amount of tries for this builder
     */
    public void generate(int maxTries) {
        if (tryCount > 0) {
            CoreAdventures.LOGGER.warn("Duplicate refuge generation started at {}", context.chunkPos().getWorldPosition());
        }

        for (tryCount = 0; tryCount < maxTries; tryCount++) {
            tryGenerate();
            if (isValid()) return;
        }

        clear();
    }

    /**
     * Tries to generate a refuge exactly once.
     * This includes preparing the builder for a new generation try.
     */
    private void tryGenerate() {
        prepareGeneration();
        createLayout();
    }

    /**
     * Resets variables used in generation progress to their values before one starts.
     * Does not update {@link RefugeBuilder#random} seed.
     */
    public void clear() {
        builder.clear();
        openPoints.clear();
        roomCount = 0;
        hasBossRoom = false;

        for (PieceCreator pieceCreator : pieceCreators) pieceCreator.placeCount = 0;
    }

    /**
     * Resets variables used in generation progress to their values before one starts.
     * Also updates {@link RefugeBuilder#random} seed.
     */
    private void prepareGeneration() {
        clear();
        random.setLargeFeatureSeed(context.seed() + tryCount, context.chunkPos().x, context.chunkPos().z);
    }

    /**
     * Tries to generate a refuge exactly once.
     * This should only be called after {@link RefugeBuilder#prepareGeneration()} is called.
     */
    @SuppressWarnings("DataFlowIssue")
    private void createLayout() {
        // Floor y for the first piece
        int y = 64;
        // Start pos of the first piece
        BlockPos pos = new BlockPos(context.chunkPos().getBlockX(0), y, context.chunkPos().getBlockZ(0));
        // Add first piece to the refuge
        addPiece(new RootPiece(pos.getX(), pos.getY(), pos.getZ(), Util.randomCardinalDirection(random)));

        // Pick next piece for a random open point until there are no open points
        while (!openPoints.isEmpty()) {
            ContinuationPoint point = Util.removeRandomElement(openPoints, random);
            RefugePiece piece = getValidPiece(point.pos(), point.direction(), point.depth());

            // Add an EndPointPiece if no other piece can be placed
            if (piece == null) {
                addEndPoint(point.pos().relative(point.direction().getOpposite()), point.direction().getClockWise(), point.depth());
                continue;
            }

            // Add new piece
            addPiece(piece);
        }
    }

    /**
     * Loops trough every {@link PieceCreator} and checks if it is valid.
     * Picks a random valid {@link PieceCreator} based on their weights and creates its piece.
     * The amount of {@link PieceCreator#placeCount} is increased, so the returned piece should always be placed.
     *
     * @param pos       Start position of the new piece
     * @param direction Direction of entrypoint of the new piece
     * @param depth     Amount of pieces from generation start to this piece (=generation depth)
     * @return null if there are no valid pieces, otherwise a random valid piece
     */
    @Nullable
    private RefugePiece getValidPiece(BlockPos pos, Direction direction, int depth) {
        int totalWeight = 0;

        ArrayList<PieceCreator> validPieces = new ArrayList<>();

        // Loop through all possible pieces and filter out ones which can't be placeCount here
        pieceLoop:
        for (PieceCreator pieceCreator : pieceCreators) {
            if (!pieceCreator.canPlace(depth)) continue;

            // Bounding box of the piece to test.
            // For the piece to be valid, this cannot intersect any bounding box of an already existing piece.
            BoundingBox boundingBox = pieceCreator.getBoundingBox(direction).moved(pos.getX(), pos.getY(), pos.getZ());

            if (!Util.isCompletelyInside(validArea, boundingBox)) {
                // This piece would go too far from the structure start
                continue;
            }

            // Check if the piece to test would collide with any of the existing pieces
            for (BoundingBox existing : boundingBoxes) {
                if (existing.intersects(boundingBox)) continue pieceLoop;
            }

            // The piece to test is valid.
            validPieces.add(pieceCreator);
            totalWeight += pieceCreator.weight;
        }

        if (totalWeight == 0) return null; // No piece can generate here

        // Pick a weighted random piece
        int randomNumber = random.nextInt(totalWeight);
        for (PieceCreator creator : validPieces) {
            if (randomNumber > creator.weight) {
                // This was not the randomly picked piece
                randomNumber -= creator.weight;
                continue;
            }

            // Create new piece
            RefugePiece piece = creator.creator.create(depth, pos.getX(), pos.getY(), pos.getZ(), direction);
            creator.placeCount++;
            return piece;
        }

        return null;
    }

    /**
     * Adds a new piece to the refuge. This includes updating {@link RefugeBuilder#builder},
     * {@link RefugeBuilder#openPoints}, {@link RefugeBuilder#boundingBoxes}, {@link RefugeBuilder#roomCount} and
     * {@link RefugeBuilder#hasBossRoom},
     *
     * @param piece Piece to add
     */
    private void addPiece(RefugePiece piece) {
        builder.addPiece(piece);
        openPoints.addAll(piece.getContinuationPoints(piece.getStartPos(), piece.getDirection(), piece.getGenDepth() + 1));
        boundingBoxes.add(piece.getBoundingBox());

        if (!(piece instanceof EndPointPiece)) roomCount++;
        if (piece instanceof BossRoom) hasBossRoom = true;
    }

    /**
     * Adds an EndPointPiece to the refuge
     *
     * @param pos       Position of the EndPointPiece
     * @param direction Either direction on the horizontal axis of the wall
     * @param depth     Generation depth of the piece
     */
    private void addEndPoint(BlockPos pos, Direction direction, int depth) {
        addPiece(new EndPointPiece(direction, pos.above(), depth));
    }

    /**
     * A refuge is considered valid if it has a {@link BossRoom} and at least {@link RefugeBuilder#minRoomCount} pieces,
     * not including {@link EndPointPiece}s.
     *
     * @return true if the generated refuge is valid
     */
    public boolean isValid() {
        return roomCount >= minRoomCount && hasBossRoom;
    }
}
