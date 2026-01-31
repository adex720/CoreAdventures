package com.adex.mixin.client;

import com.adex.option.ModOptions;
import com.adex.overlay.HeatHud;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class GuiMixin {

    @Inject(at = @At("HEAD"), method = "renderArmor")
    private static void renderArmor(GuiGraphics guiGraphics, Player player, int startY, int rows, int rowHeight, int startX, CallbackInfo ci) {
        int yOffsetRows = ModOptions.getModOptions().heatBarOffset().get();
        if (player.getArmorValue() > 0) yOffsetRows++;

        HeatHud.render(guiGraphics, player, startY + (1 - yOffsetRows) * rowHeight, rows, rowHeight, startX);
    }
}
