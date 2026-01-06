package com.adex.entity;

import com.adex.CoreAdventures;
import com.adex.mixin.client.ModelLayersAccessor;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.Identifier;

public class ModModelLayers {

    public static final ModelLayerLocation CHALCEDONY_GOLEM = register("chalcedony_golem");

    private static ModelLayerLocation register(String name) {
        ModelLayerLocation modelLayerLocation = new ModelLayerLocation(Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name), "main");
        if (!ModelLayersAccessor.getAllModels().add(modelLayerLocation)) {
            throw new IllegalStateException("Duplicate registration for " + modelLayerLocation);
        } else {
            return modelLayerLocation;
        }
    }

    public static void initialize() {
        EntityModelLayerRegistry.registerModelLayer(CHALCEDONY_GOLEM, GolemModel::createBodyLayer);
    }
}
