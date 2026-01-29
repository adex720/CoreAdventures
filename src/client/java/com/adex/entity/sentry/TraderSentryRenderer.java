package com.adex.entity.sentry;

import com.adex.entity.ModModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class TraderSentryRenderer extends SentryRenderer<TraderSentry> {

    public TraderSentryRenderer(EntityRendererProvider.Context context) {
        super(context, ModModelLayers.TRADER_SENTRY, "textures/entity/trader_sentry.png");
    }

    @Override
    public void extractRenderState(TraderSentry sentry, SentryRenderState renderState, float f) {
        super.extractRenderState(sentry, renderState, f);
        if (sentry.isHoldingItem()) renderState.lookingAtItem = true;
    }
}
