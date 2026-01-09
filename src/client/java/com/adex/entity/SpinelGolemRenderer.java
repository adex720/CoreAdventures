package com.adex.entity;

import com.adex.entity.golem.SpinelGolem;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class SpinelGolemRenderer extends GolemRenderer<SpinelGolem> {

    public SpinelGolemRenderer(EntityRendererProvider.Context context) {
        super(context, ModModelLayers.SPINEL_GOLEM, "textures/entity/spinel_golem.png");
    }
}
