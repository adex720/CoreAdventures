package com.adex.entity;

import net.minecraft.client.renderer.entity.EntityRenderers;

public class ModEntityRenderers {

    public static void initialize() {
        EntityRenderers.register(ModEntities.CHALCEDONY_GOLEM, ChalcedonyGolemRenderer::new);
    }
}
