package com.adex.mixin;

import com.adex.entity.attribute.HeatManager;
import com.mojang.authlib.GameProfile;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin extends Player {

    public ServerPlayerMixin(Level level, GameProfile gameProfile) {
        super(level, gameProfile);
    }

    /**
     * Reduce peaceful regeneration to 10% when overheating
     */
    @SuppressWarnings("ConstantValue")
    @Inject(at = @At("HEAD"), method = "tickRegeneration", cancellable = true)
    private void handleTickRegeneration(CallbackInfo ci) {
        if (this.tickCount % 200 > 0 && HeatManager.preventsNaturalRegeneration((ServerPlayer) (Object) this)) {
            ci.cancel();
        }
    }
}
