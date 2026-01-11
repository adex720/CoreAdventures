package com.adex.entity.golem;

import com.adex.entity.ModModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class SpinelGolemRenderer extends GolemRenderer<SpinelGolem> {

    public SpinelGolemRenderer(EntityRendererProvider.Context context) {
        super(context, ModModelLayers.SPINEL_GOLEM, "textures/entity/spinel_golem.png");
    }
}
