package com.adex.data.structure;

import com.adex.CoreAdventures;
import com.adex.data.structure.refuge.piece.BossRoom;
import com.adex.data.structure.refuge.Refuge;
import com.adex.data.structure.refuge.piece.EndPointPiece;
import com.adex.data.structure.refuge.piece.RootPiece;
import com.adex.data.structure.refuge.piece.ThreeWayRoom;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;

public class ModStructures {

    public static final StructureType<Refuge> REFUGE = registerStructure("refuge", Refuge.CODEC);


    public static final StructurePieceType REFUGE_ROOT_PIECE = registerPiece(RootPiece::new, "rfroot");
    public static final StructurePieceType REFUGE_END_POINT = registerPiece(EndPointPiece::new, "rfend");
    public static final StructurePieceType REFUGE_BOSS_ROOM = registerPiece(BossRoom::new, "rfboss");
    public static final StructurePieceType REFUGE_THREE_WAY = registerPiece(ThreeWayRoom::new, "rfthree");

    private static <S extends Structure> StructureType<S> registerStructure(String name, MapCodec<S> codec) {
        return Registry.register(BuiltInRegistries.STRUCTURE_TYPE, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name), () -> codec);
    }

    private static StructurePieceType registerPiece(StructurePieceType.ContextlessType structurePieceType, String name) {
        return Registry.register(BuiltInRegistries.STRUCTURE_PIECE, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name), structurePieceType);
    }

    public static void initialize() {
    }
}
