package com.adex.packet;

import com.adex.payload.AddExplosionParticlesS2C;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.Level;


public class AddExplosionParticlesReceiver {

    public static void received(AddExplosionParticlesS2C payload, ClientPlayNetworking.Context context) {
        Level level = context.player().level();
        BlockPos pos = payload.pos();

        ParticleUtils.spawnParticlesOnBlockFaces(level, pos, ParticleTypes.EXPLOSION, UniformInt.of(3, 5));
        level.playLocalSound(pos, SoundEvents.GENERIC_EXPLODE.value(), SoundSource.HOSTILE, 1.0f, 1.0f, true);
    }

}
