package com.adex.entity.sentry;

import com.adex.entity.ModModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class TraderSentryRenderer extends SentryRenderer<TraderSentry> {

    public TraderSentryRenderer(EntityRendererProvider.Context context) {
        super(context, ModModelLayers.TRADER_SENTRY, "textures/entity/trader_sentry.png");
    }

}
