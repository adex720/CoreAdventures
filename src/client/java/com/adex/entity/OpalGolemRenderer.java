package com.adex.entity;

import com.adex.entity.golem.OpalGolem;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class OpalGolemRenderer extends GolemRenderer<OpalGolem> {

    public OpalGolemRenderer(EntityRendererProvider.Context context) {
        super(context, ModModelLayers.OPAL_GOLEM, "textures/entity/opal_golem.png");
    }
}
