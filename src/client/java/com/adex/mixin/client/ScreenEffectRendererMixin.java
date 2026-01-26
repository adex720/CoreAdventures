package com.adex.mixin.client;

import com.adex.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import net.minecraft.world.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ScreenEffectRenderer.class)
public class ScreenEffectRendererMixin {

    @ModifyArg(at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(FFF)V"), method = "renderFire", index = 1)
    private static float onRenderFire(float y) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) return y;
        if (!player.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.LAVA_GOGGLES)) return y;

        return y - 0.35f;
    }
}
