package com.adex.entity.golem;

import com.adex.entity.ModModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class RubyGolemRenderer extends GolemRenderer<RubyGolem> {

    public RubyGolemRenderer(EntityRendererProvider.Context context) {
        super(context, ModModelLayers.RUBY_GOLEM, "textures/entity/ruby_golem.png");
    }
}
