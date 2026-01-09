package com.adex.payload;

import com.adex.CoreAdventures;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public record AddExplosionParticlesS2C(BlockPos pos) implements CustomPacketPayload {

    public static final Identifier ADD_EXPLOSION_PARTICLES_PAYLOAD_ID = Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "add_explosion_particles");
    public static final CustomPacketPayload.Type<AddExplosionParticlesS2C> ID = new CustomPacketPayload.Type<>(ADD_EXPLOSION_PARTICLES_PAYLOAD_ID);
    public static final StreamCodec<RegistryFriendlyByteBuf, AddExplosionParticlesS2C> CODEC = StreamCodec.composite(BlockPos.STREAM_CODEC, AddExplosionParticlesS2C::pos, AddExplosionParticlesS2C::new);
    
    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
