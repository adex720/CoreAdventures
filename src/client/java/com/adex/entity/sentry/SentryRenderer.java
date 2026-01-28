package com.adex.entity.sentry;

import com.adex.CoreAdventures;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class SentryRenderer<T extends Sentry> extends HumanoidMobRenderer<T, SentryRenderState, SentryModel> {

    private final Identifier layerLocation;

    public SentryRenderer(EntityRendererProvider.Context context, ModelLayerLocation layerLocation, String path) {
        super(context, new SentryModel(context.bakeLayer(layerLocation)), 0.4f);
        this.layerLocation = Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, path);
    }

    @Override
    public @NonNull Identifier getTextureLocation(SentryRenderState livingEntityRenderState) {
        return layerLocation;
    }

    @Override
    public SentryRenderState createRenderState() {
        return new SentryRenderState();
    }
}
