package com.adex.entity;

import com.adex.CoreAdventures;
import com.adex.entity.golem.Golem;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class GolemRenderer<T extends Golem> extends MobRenderer<T, GolemRenderState, GolemModel> {

    private final Identifier layerLocation;

    public GolemRenderer(EntityRendererProvider.Context context, ModelLayerLocation layerLocation, String texturePath) {
        super(context, new GolemModel(context.bakeLayer(layerLocation)), 0.8f);
        this.layerLocation = Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, texturePath);
    }

    @Override
    public @NonNull Identifier getTextureLocation(GolemRenderState livingEntityRenderState) {
        return layerLocation;
    }

    @Override
    public GolemRenderState createRenderState() {
        return new GolemRenderState();
    }
}
