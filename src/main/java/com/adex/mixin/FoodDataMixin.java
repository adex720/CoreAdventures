package com.adex.mixin;

import com.adex.entity.attribute.HeatManager;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.food.FoodData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoodData.class)
public class FoodDataMixin {

    @Shadow
    private int foodLevel;

    /**
     * Prevent natural health generation when overheating
     */
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;getGameRules()Lnet/minecraft/world/level/gamerules/GameRules;"), method = "tick", cancellable = true)
    private void handleTick(ServerPlayer player, CallbackInfo ci) {
        // Cancelling earlier would skip hunger and saturation updates

        // Cancelling when foodLevel is less than 18 would skip starvation damage calculation and
        // not reset tickTimer when it should be reset
        if (foodLevel >= 18 && HeatManager.preventsNaturalRegeneration(player)) {
            ci.cancel();
        }
    }
}
