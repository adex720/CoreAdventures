package com.adex.entity;

import com.adex.entity.golem.SapphireGolem;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class SapphireGolemRenderer extends GolemRenderer<SapphireGolem> {

    public SapphireGolemRenderer(EntityRendererProvider.Context context) {
        super(context, ModModelLayers.SAPPHIRE_GOLEM, "textures/entity/sapphire_golem.png");
    }
}
