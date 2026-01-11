package com.adex.entity.golem;

import com.adex.entity.ModModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class ChalcedonyGolemRenderer extends GolemRenderer<ChalcedonyGolem> {

    public ChalcedonyGolemRenderer(EntityRendererProvider.Context context) {
        super(context, ModModelLayers.CHALCEDONY_GOLEM, "textures/entity/chalcedony_golem.png");
    }
}
