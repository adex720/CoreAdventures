package com.adex.entity.sentry;

import com.adex.CoreAdventures;
import com.adex.entity.ModModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class SentryRenderer extends MobRenderer<Sentry, SentryRenderState, SentryModel> {

    private final Identifier layerLocation;

    public SentryRenderer(EntityRendererProvider.Context context) {
        super(context, new SentryModel(context.bakeLayer(ModModelLayers.SENTRY)), 0.4f);
        this.layerLocation = Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "textures/entity/sentry.png");
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
