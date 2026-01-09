package com.adex.payload;

import com.adex.CoreAdventures;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public record AddRegenerationParticlesS2C(BlockPos pos) implements CustomPacketPayload {

    public static final Identifier ADD_REGENERATION_PARTICLES_PAYLOAD_ID = Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "add_regeneration_particles");
    public static final Type<AddRegenerationParticlesS2C> ID = new Type<>(ADD_REGENERATION_PARTICLES_PAYLOAD_ID);
    public static final StreamCodec<RegistryFriendlyByteBuf, AddRegenerationParticlesS2C> CODEC = StreamCodec.composite(BlockPos.STREAM_CODEC, AddRegenerationParticlesS2C::pos, AddRegenerationParticlesS2C::new);
    
    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
