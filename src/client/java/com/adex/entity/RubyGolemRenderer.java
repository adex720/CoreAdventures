package com.adex.entity;

import com.adex.entity.golem.RubyGolem;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class RubyGolemRenderer extends GolemRenderer<RubyGolem> {

    public RubyGolemRenderer(EntityRendererProvider.Context context) {
        super(context, ModModelLayers.RUBY_GOLEM, "textures/entity/ruby_golem.png");
    }
}
