package com.adex.packet;

import com.adex.item.RefugeCompassHelper;
import com.adex.payload.AddExplosionParticlesS2C;
import com.adex.payload.AddRegenerationParticlesS2C;
import com.adex.payload.UpdateRefugeLocationS2C;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class ModReceivers {

    public static void initialize() {
        ClientPlayNetworking.registerGlobalReceiver(AddExplosionParticlesS2C.ID, AddExplosionParticlesReceiver::received);
        ClientPlayNetworking.registerGlobalReceiver(AddRegenerationParticlesS2C.ID, AddRegenerationParticlesReceiver::received);
        ClientPlayNetworking.registerGlobalReceiver(UpdateRefugeLocationS2C.ID, RefugeCompassHelper::received);
    }
}
