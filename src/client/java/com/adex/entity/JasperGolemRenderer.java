package com.adex.entity;

import com.adex.entity.golem.JasperGolem;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class JasperGolemRenderer extends GolemRenderer<JasperGolem> {

    public JasperGolemRenderer(EntityRendererProvider.Context context) {
        super(context, ModModelLayers.JASPER_GOLEM, "textures/entity/jasper_golem.png");
    }
}
