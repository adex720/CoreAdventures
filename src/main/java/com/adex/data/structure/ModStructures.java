package com.adex.data.structure;

import com.adex.CoreAdventures;
import com.adex.data.structure.refuge.Refuge;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;

public class ModStructures {

    public static final ResourceKey<Structure > REFUGE_KEY = createKey("refuge");
    public static final Refuge REFUGE_DUMMY = new Refuge(new Structure.StructureSettings.Builder(HolderSet.empty()).build());
    public static final StructureType<Refuge> REFUGE_TYPE = register("refuge", Refuge.CODEC);

    private static <S extends Structure> StructureType<S> register(String name, MapCodec<S> codec) {
        return Registry.register(BuiltInRegistries.STRUCTURE_TYPE, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name), () -> codec);
    }

    private static ResourceKey<Structure> createKey(String name) {
        return ResourceKey.create(Registries.STRUCTURE, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name));
    }

    public static void initialize() {
    }
}
