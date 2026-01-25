package com.adex.data.structure.refuge;

import com.adex.CoreAdventures;
import com.adex.data.structure.refuge.piece.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;

public class RefugePieces {

    public static final StructurePieceType REFUGE_ROOT_PIECE = registerPiece(RootPiece::new, "rfroot");
    public static final StructurePieceType REFUGE_END_POINT = registerPiece(EndPointPiece::new, "rfend");

    public static final StructurePieceType REFUGE_BOSS_ROOM = registerPiece(BossRoom::new, "rfboss");
    public static final StructurePieceType REFUGE_EMPTY_ROOM = registerPiece(EmptyRoom::new, "rfempty");
    public static final StructurePieceType REFUGE_TREASURE_ROOM = registerPiece(TreasureRoom::new, "rftreasure");
    public static final StructurePieceType REFUGE_TREE_ROOM = registerPiece(TreeRoom::new, "rftree");
    public static final StructurePieceType REFUGE_PORTAL_ROOM = registerPiece(PortalRoom::new, "rfportal");
    public static final StructurePieceType REFUGE_BED_ROOM = registerPiece(BedRoom::new, "rfbed");

    public static final StructurePieceType REFUGE_END_CHEST_ROOM = registerPiece(EndChestRoom::new, "rfendchest");
    public static final StructurePieceType REFUGE_END_TRAP_CHEST_ROOM = registerPiece(EndTrapChestRoom::new, "rfendtrap");

    public static final StructurePieceType REFUGE_CORRIDOR_LONG = registerPiece(CorridorLong::new, "rfcorrlo");
    public static final StructurePieceType REFUGE_CORRIDOR_SHORT = registerPiece(CorridorShort::new, "rfcorrsh");
    public static final StructurePieceType REFUGE_CORRIDOR_LEFT = registerPiece(CorridorLeft::new, "rfcorrle");
    public static final StructurePieceType REFUGE_CORRIDOR_RIGHT = registerPiece(CorridorRight::new, "rfcorrri");
    public static final StructurePieceType REFUGE_CORRIDOR_CHEST = registerPiece(CorridorChest::new, "rfcorrch");
    public static final StructurePieceType REFUGE_TURN_LEFT = registerPiece(TurnLeft::new, "rfturnl");
    public static final StructurePieceType REFUGE_TURN_RIGHT = registerPiece(TurnRight::new, "rfturnr");
    public static final StructurePieceType REFUGE_TURN_U_RIGHT = registerPiece(TurnURight::new, "rfturnur");
    public static final StructurePieceType REFUGE_TURN_U_LEFT = registerPiece(TurnULeft::new, "rfturnul");

    public static final StructurePieceType REFUGE_STAIRS_UP = registerPiece(StairsUp::new, "rfstairu");
    public static final StructurePieceType REFUGE_STAIRS_DOWN = registerPiece(StairsDown::new, "rfstaird");
    public static final StructurePieceType REFUGE_STAIRS_CHEST = registerPiece(StairsChest::new, "rfstairc");
    public static final StructurePieceType REFUGE_LADDER_UP = registerPiece(LadderUp::new, "rfladderu");
    public static final StructurePieceType REFUGE_LADDER_DOWN = registerPiece(LadderDown::new, "rfladderd");
    public static final StructurePieceType REFUGE_LADDER_HIGH = registerPiece(LadderHigh::new, "rfladderh");
    public static final StructurePieceType REFUGE_LADDER_HIDDEN = registerPiece(LadderHidden::new, "rfladderhn");

    public static final StructurePieceType REFUGE_THREE_WAY_MIDDLE = registerPiece(ThreeWayMiddle::new, "rfthreem");
    public static final StructurePieceType REFUGE_THREE_WAY_LEFT = registerPiece(ThreeWayLeft::new, "rfthreel");
    public static final StructurePieceType REFUGE_THREE_WAY_RIGHT = registerPiece(ThreeWayRight::new, "rfthreer");
    public static final StructurePieceType REFUGE_RISING_THREE_WAY_MIDDLE = registerPiece(RisingThreeWayMiddle::new, "rfrthreem");
    public static final StructurePieceType REFUGE_RISING_THREE_WAY_LEFT = registerPiece(RisingThreeWayLeft::new, "rfrthreel");
    public static final StructurePieceType REFUGE_RISING_THREE_WAY_RIGHT = registerPiece(RisingThreeWayRight::new, "rfrthreer");
    public static final StructurePieceType REFUGE_LOWERING_THREE_WAY_MIDDLE = registerPiece(LoweringThreeWayMiddle::new, "rflthreem");
    public static final StructurePieceType REFUGE_LOWERING_THREE_WAY_LEFT = registerPiece(LoweringThreeWayLeft::new, "rflthreel");
    public static final StructurePieceType REFUGE_LOWERING_THREE_WAY_RIGHT = registerPiece(LoweringThreeWayRight::new, "rflthreer");
    public static final StructurePieceType REFUGE_REDSTONE_THREE_WAY = registerPiece(RedstoneThreeWay::new, "rfredthree");
    public static final StructurePieceType REFUGE_EIGHT_WAY = registerPiece(EightWay::new, "rfeight");

    private static StructurePieceType registerPiece(StructurePieceType.ContextlessType structurePieceType, String name) {
        return Registry.register(BuiltInRegistries.STRUCTURE_PIECE, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name), structurePieceType);
    }

    public static void initialize() {
    }
}
