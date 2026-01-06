package com.adex.entity;

import com.adex.entity.golem.ChalcedonyGolem;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class ChalcedonyGolemRenderer extends GolemRenderer<ChalcedonyGolem> {

    public ChalcedonyGolemRenderer(EntityRendererProvider.Context context) {
        super(context, ModModelLayers.CHALCEDONY_GOLEM, "textures/entity/chalcedony_golem.png");
    }
}
