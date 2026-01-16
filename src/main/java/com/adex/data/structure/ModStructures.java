package com.adex.data.structure;

import com.adex.CoreAdventures;
import com.adex.data.structure.refuge.piece.BossRoom;
import com.adex.data.structure.refuge.Refuge;
import com.adex.data.structure.refuge.piece.RootPiece;
import com.adex.data.structure.refuge.piece.ThreeWayRoom;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;

public class ModStructures {

    public static final StructureType<Refuge> REFUGE = registerStructure("refuge", Refuge.CODEC);
    public static final ResourceKey<StructureSet> REFUGES_SET = registerSet("refuges");


    public static final StructurePieceType REFUGE_BOSS_ROOM = registerPiece(BossRoom::new, "rfbos");
    public static final StructurePieceType REFUGE_THREE_WAY = registerPiece(ThreeWayRoom::new, "rfthr");
    public static final StructurePieceType ROOT_PIECE = registerPiece(RootPiece::new, "rfroot");

    private static <S extends Structure> StructureType<S> registerStructure(String name, MapCodec<S> codec) {
        return Registry.register(BuiltInRegistries.STRUCTURE_TYPE, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name), () -> codec);
    }

    private static ResourceKey<StructureSet> registerSet(String name) {
        return ResourceKey.create(Registries.STRUCTURE_SET, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name));
    }

    private static StructurePieceType registerPiece(StructurePieceType.ContextlessType structurePieceType, String name) {
        return Registry.register(BuiltInRegistries.STRUCTURE_PIECE, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name), structurePieceType);
    }

    public static void initialize() {
    }
}
