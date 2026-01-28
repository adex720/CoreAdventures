package com.adex.entity.sentry;

import com.adex.entity.ModModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class WarriorSentryRenderer extends SentryRenderer<WarriorSentry> {

    public WarriorSentryRenderer(EntityRendererProvider.Context context) {
        super(context, ModModelLayers.WARRIOR_SENTRY, "textures/entity/warrior_sentry.png");
    }

}
