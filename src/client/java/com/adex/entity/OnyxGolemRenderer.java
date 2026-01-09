package com.adex.entity;

import com.adex.entity.golem.OnyxGolem;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class OnyxGolemRenderer extends GolemRenderer<OnyxGolem> {

    public OnyxGolemRenderer(EntityRendererProvider.Context context) {
        super(context, ModModelLayers.ONYX_GOLEM, "textures/entity/onyx_golem.png");
    }
}
