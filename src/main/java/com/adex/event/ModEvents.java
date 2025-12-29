package com.adex.event;

import com.adex.entity.attribute.HeatManager;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;

public class ModEvents {

    public static void initialize() {
        ServerTickEvents.START_SERVER_TICK.register(ModEvents::heatTick);
    }

    public static void heatTick(MinecraftServer server) {
        server.getPlayerList().getPlayers().forEach(player -> HeatManager.serverHeatTick(player, server));
    }
}
