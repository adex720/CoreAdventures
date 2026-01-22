package com.adex.payload;

import com.adex.CoreAdventures;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public record UpdateRefugeLocationS2C(BlockPos pos) implements CustomPacketPayload {

    public static final Identifier UPDATE_REFUGE_LOCATION_PAYLOAD_ID = Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "update_refuge_location");
    public static final Type<UpdateRefugeLocationS2C> ID = new Type<>(UPDATE_REFUGE_LOCATION_PAYLOAD_ID);
    public static final StreamCodec<RegistryFriendlyByteBuf, UpdateRefugeLocationS2C> CODEC = StreamCodec.composite(BlockPos.STREAM_CODEC, UpdateRefugeLocationS2C::pos, UpdateRefugeLocationS2C::new);
    
    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
