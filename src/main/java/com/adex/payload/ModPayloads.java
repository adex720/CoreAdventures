package com.adex.payload;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public class ModPayloads {

    public static void initialize() {
        PayloadTypeRegistry.playS2C().register(AddExplosionParticlesS2C.ID, AddExplosionParticlesS2C.CODEC);
        PayloadTypeRegistry.playS2C().register(AddRegenerationParticlesS2C.ID, AddRegenerationParticlesS2C.CODEC);
        PayloadTypeRegistry.playS2C().register(UpdateRefugeLocationS2C.ID, UpdateRefugeLocationS2C.CODEC);

        PayloadTypeRegistry.playC2S().register(LocateRefugeC2S.ID, LocateRefugeC2S.CODEC);
    }

}
