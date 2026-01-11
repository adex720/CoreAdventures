package com.adex.entity.golem;

import com.adex.entity.ModModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class TigersEyeGolemRenderer extends GolemRenderer<TigersEyeGolem> {

    public TigersEyeGolemRenderer(EntityRendererProvider.Context context) {
        super(context, ModModelLayers.TIGERS_EYE_GOLEM, "textures/entity/tigers_eye_golem.png");
    }
}
