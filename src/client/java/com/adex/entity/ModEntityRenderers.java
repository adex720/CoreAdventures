package com.adex.entity;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

public class ModEntityRenderers {

    public static void initialize() {
        EntityRenderers.register(ModEntities.CHALCEDONY_GOLEM, ChalcedonyGolemRenderer::new);
        EntityRenderers.register(ModEntities.GARNET_GOLEM, GarnetGolemRenderer::new);
        EntityRenderers.register(ModEntities.JADE_GOLEM, JadeGolemRenderer::new);
        EntityRenderers.register(ModEntities.JASPER_GOLEM, JasperGolemRenderer::new);
        EntityRenderers.register(ModEntities.ONYX_GOLEM, OnyxGolemRenderer::new);
        EntityRenderers.register(ModEntities.OPAL_GOLEM, OpalGolemRenderer::new);
        EntityRenderers.register(ModEntities.RUBY_GOLEM, RubyGolemRenderer::new);
        EntityRenderers.register(ModEntities.SAPPHIRE_GOLEM, SapphireGolemRenderer::new);
        EntityRenderers.register(ModEntities.SPINEL_GOLEM, SpinelGolemRenderer::new);
        EntityRenderers.register(ModEntities.TIGERS_EYE_GOLEM, TigersEyeGolemRenderer::new);

        EntityRenderers.register(ModEntities.GOLEM_FIREBALL_ENTITY, context -> new ThrownItemRenderer<>(context, 3.0f, true));
        EntityRenderers.register(ModEntities.HEAT_BALL_ENTITY,
                context -> new ThrownItemRenderer<>(context, 3.0f, true));
    }
}
