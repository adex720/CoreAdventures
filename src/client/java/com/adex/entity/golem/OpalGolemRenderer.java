package com.adex.entity.golem;

import com.adex.entity.ModModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class OpalGolemRenderer extends GolemRenderer<OpalGolem> {

    public OpalGolemRenderer(EntityRendererProvider.Context context) {
        super(context, ModModelLayers.OPAL_GOLEM, "textures/entity/opal_golem.png");
    }
}
