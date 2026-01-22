package com.adex.payload;

import com.adex.CoreAdventures;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public record LocateRefugeC2S(BlockPos pos)  implements CustomPacketPayload {

    public static final Identifier LOCATE_REFUGE_PAYLOAD_ID = Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "locate_refuge");
    public static final CustomPacketPayload.Type<LocateRefugeC2S> ID = new CustomPacketPayload.Type<>(LOCATE_REFUGE_PAYLOAD_ID);
    public static final StreamCodec<RegistryFriendlyByteBuf, LocateRefugeC2S> CODEC = StreamCodec.composite(BlockPos.STREAM_CODEC, LocateRefugeC2S::pos, LocateRefugeC2S::new);

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
