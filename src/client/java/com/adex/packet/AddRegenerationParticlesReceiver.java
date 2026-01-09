package com.adex.packet;

import com.adex.payload.AddRegenerationParticlesS2C;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.ParticleUtils;


public class AddRegenerationParticlesReceiver {

    public static void received(AddRegenerationParticlesS2C payload, ClientPlayNetworking.Context context) {
        ParticleUtils.spawnParticleInBlock(context.player().level(), payload.pos(), 6, ParticleTypes.HEART);
    }

}
