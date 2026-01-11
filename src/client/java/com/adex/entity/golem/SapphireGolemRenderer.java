package com.adex.entity.golem;

import com.adex.entity.ModModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class SapphireGolemRenderer extends GolemRenderer<SapphireGolem> {

    public SapphireGolemRenderer(EntityRendererProvider.Context context) {
        super(context, ModModelLayers.SAPPHIRE_GOLEM, "textures/entity/sapphire_golem.png");
    }
}
