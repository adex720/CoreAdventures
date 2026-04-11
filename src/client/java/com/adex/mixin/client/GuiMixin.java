package com.adex.mixin.client;

import com.adex.option.ModOptions;
import com.adex.overlay.HeatHud;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class GuiMixin {

    @Inject(at = @At("HEAD"), method = "extractArmor")
    private static void renderArmor(GuiGraphicsExtractor guiGraphics, Player player, int yLineBase, int numHealthRows, int healthRowHeight, int xLeft, CallbackInfo ci) {
        int yOffsetRows = ModOptions.getModOptions().heatBarOffset().get();
        if (player.getArmorValue() > 0) yOffsetRows++;

        HeatHud.render(guiGraphics, player, yLineBase + (1 - yOffsetRows) * healthRowHeight, numHealthRows, healthRowHeight, xLeft);
    }
}
