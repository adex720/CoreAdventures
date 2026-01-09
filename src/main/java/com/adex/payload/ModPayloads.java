package com.adex.payload;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public class ModPayloads {

    public static void initialize() {
        PayloadTypeRegistry.playS2C().register(AddExplosionParticlesS2C.ID, AddExplosionParticlesS2C.CODEC);
    }

}
