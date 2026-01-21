package com.adex;

import com.adex.entity.ModEntityRenderers;
import com.adex.entity.ModModelLayers;
import com.adex.overlay.HeatHud;
import com.adex.packet.ModReceivers;
import com.adex.render.CustomBlockRenderLayers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class CoreAdventuresClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModModelLayers.initialize();
        ModEntityRenderers.initialize();
        CustomBlockRenderLayers.initialize();

        ModReceivers.initialize();

        ClientTickEvents.END_CLIENT_TICK.register(HeatHud::onClientTick);
    }
}
