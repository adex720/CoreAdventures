package com.adex.entity.golem;

import com.adex.entity.ModModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class JadeGolemRenderer extends GolemRenderer<JadeGolem> {

    public JadeGolemRenderer(EntityRendererProvider.Context context) {
        super(context, ModModelLayers.JADE_GOLEM, "textures/entity/jade_golem.png");
    }
}
