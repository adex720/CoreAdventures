package com.adex.entity;

import com.adex.entity.golem.JadeGolem;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class JadeGolemRenderer extends GolemRenderer<JadeGolem> {

    public JadeGolemRenderer(EntityRendererProvider.Context context) {
        super(context, ModModelLayers.JADE_GOLEM, "textures/entity/jade_golem.png");
    }
}
