package com.adex.entity;

import com.adex.entity.golem.GarnetGolem;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class GarnetGolemRenderer extends GolemRenderer<GarnetGolem> {

    public GarnetGolemRenderer(EntityRendererProvider.Context context) {
        super(context, ModModelLayers.GARNET_GOLEM, "textures/entity/garnet_golem.png");
    }
}
