package com.adex.packet;

import com.adex.payload.AddExplosionParticlesS2C;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class ModReceivers {

    public static void initialize() {
        ClientPlayNetworking.registerGlobalReceiver(AddExplosionParticlesS2C.ID, AddExplosionParticlesReceiver::received);
    }
}
