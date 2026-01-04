package com.adex.overlay;

import com.adex.entity.attribute.ModAttributes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;

public class HeatHud {

    public static int TICK_COUNT = 0;

    private static final Identifier HEAT_WARMED_SPRITE = Identifier.withDefaultNamespace("hud/air");
    private static final Identifier HEAT_UNWARMED_SPRITE = Identifier.withDefaultNamespace("hud/air_empty");
    private static final Identifier HEAT_CRITICAL_SPRITE = Identifier.withDefaultNamespace("hud/air_burstingy");

    public final static int BAR_WIDTH = 9;
    public final static int BAR_HEIGHT = 9;

    public static void render(GuiGraphics guiGraphics, Player player, int startY, int rows, int rowHeight, int startX) {
        double heat = player.getAttributeValue(ModAttributes.HEAT);
        if (heat <= 0.0d) return;

        int warmedCount = getWarmedCount(heat);
        boolean critical = isCritical(heat);

        int y = startY - (rows) * rowHeight - 10;

        for (int i = 0; i < 9; i++) {
            int x = startX + i * BAR_WIDTH;
            Identifier texture = critical ? HEAT_CRITICAL_SPRITE : i < warmedCount ? HEAT_WARMED_SPRITE : HEAT_UNWARMED_SPRITE;
            guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, texture, x, y, BAR_WIDTH, BAR_HEIGHT);
        }
    }

    public static int getWarmedCount(double heat) {
        return Math.min(10, (int) (heat / 10));
    }

    /**
     * Returns true if the heat is so high the player will soon take damage
     *
     * @param heat Heat attribute value of the player
     * @return True if heat is equal or higher than 100
     */
    public static boolean isCritical(double heat) {
        return heat >= 100.0d;
    }

    @SuppressWarnings("unused")
    public static void onClientTick(Minecraft minecraft) {
        TICK_COUNT++;
        if (TICK_COUNT >= 1200) TICK_COUNT -= 1200;
    }

}
