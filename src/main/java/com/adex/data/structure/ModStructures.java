package com.adex.data.structure;

import com.adex.CoreAdventures;
import com.adex.data.structure.refuge.Refuge;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;

public class ModStructures {
    public static final StructureType<Refuge> REFUGE_TYPE = register("refuge", Refuge.CODEC);

    private static <S extends Structure> StructureType<S> register(String name, MapCodec<S> codec) {
        return Registry.register(BuiltInRegistries.STRUCTURE_TYPE, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name), () -> codec);
    }

    public static void initialize() {
    }
}
