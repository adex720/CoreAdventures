package com.adex.entity.golem;

import com.adex.entity.ModModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class GarnetGolemRenderer extends GolemRenderer<GarnetGolem> {

    public GarnetGolemRenderer(EntityRendererProvider.Context context) {
        super(context, ModModelLayers.GARNET_GOLEM, "textures/entity/garnet_golem.png");
    }
}
