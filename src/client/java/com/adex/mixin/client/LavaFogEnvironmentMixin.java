package com.adex.mixin.client;

import com.adex.item.ModItems;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.fog.environment.LavaFogEnvironment;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Makes a player wearing lava goggles have same vision in lava as one in spectator game mode
 */
@Mixin(LavaFogEnvironment.class)
public class LavaFogEnvironmentMixin {

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;isSpectator()Z"), method = "setupFog")
    private boolean checkForLavaGoggles(Entity entity) {
        if (entity.isSpectator()) return true;
        if (!(entity instanceof LocalPlayer player)) return false;

        return player.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.LAVA_GOGGLES);
    }

}
