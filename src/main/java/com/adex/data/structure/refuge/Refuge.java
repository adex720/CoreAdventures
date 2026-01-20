package com.adex.data.structure.refuge;

import com.adex.data.structure.ModStructures;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import org.jspecify.annotations.NonNull;

import java.util.Optional;

public class Refuge extends Structure {

    public static final MapCodec<Refuge> CODEC = simpleCodec(Refuge::new);

    protected Refuge(StructureSettings structureSettings) {
        super(structureSettings);
    }

    @Override
    protected @NonNull Optional<GenerationStub> findGenerationPoint(GenerationContext context) {
        return Optional.of(new Structure.GenerationStub(context.chunkPos().getWorldPosition(),
                builder -> new RefugeBuilder(builder, context, 50).generate(10)));
    }


    @Override
    public @NonNull StructureType<?> type() {
        return ModStructures.REFUGE;
    }
}
