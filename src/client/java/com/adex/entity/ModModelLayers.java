package com.adex.entity;

import com.adex.CoreAdventures;
import com.adex.entity.golem.GolemModel;
import com.adex.entity.sentry.SentryModel;
import com.adex.mixin.client.ModelLayersAccessor;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.model.object.cart.MinecartModel;
import net.minecraft.resources.Identifier;

public class ModModelLayers {

    public static final ModelLayerLocation CHALCEDONY_GOLEM = register("chalcedony_golem");
    public static final ModelLayerLocation GARNET_GOLEM = register("garnet_golem");
    public static final ModelLayerLocation JADE_GOLEM = register("jade_golem");
    public static final ModelLayerLocation JASPER_GOLEM = register("jasper_golem");
    public static final ModelLayerLocation ONYX_GOLEM = register("onyx_golem");
    public static final ModelLayerLocation OPAL_GOLEM = register("opal_golem");
    public static final ModelLayerLocation RUBY_GOLEM = register("ruby_golem");
    public static final ModelLayerLocation SAPPHIRE_GOLEM = register("sapphire_golem");
    public static final ModelLayerLocation SPINEL_GOLEM = register("spinel_golem");
    public static final ModelLayerLocation TIGERS_EYE_GOLEM = register("tigers_eye_golem");

    public static final ModelLayerLocation TRADER_SENTRY = register("trader_sentry");
    public static final ModelLayerLocation WARRIOR_SENTRY = register("warrior_sentry");

    public static final ModelLayerLocation JUNIPER_BOAT = register("boat/juniper");
    public static final ModelLayerLocation JUNIPER_CHEST_BOAT = register("chest_boat/juniper");
    public static final ModelLayerLocation SPEED_BOAT = register("boat/speed");
    public static final ModelLayerLocation SPEED_CHEST_BOAT = register("chest_boat/speed");

    public static final ModelLayerLocation RED_TNT = register("tnt/red");
    public static final ModelLayerLocation ORANGE_TNT = register("tnt/orange");
    public static final ModelLayerLocation YELLOW_TNT = register("tnt/yellow");
    public static final ModelLayerLocation GREEN_TNT = register("tnt/green");
    public static final ModelLayerLocation BLUE_TNT = register("tnt/blue");

    private static ModelLayerLocation register(String name) {
        ModelLayerLocation modelLayerLocation = new ModelLayerLocation(Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name), "main");
        ModelLayersAccessor.getAllModels().add(modelLayerLocation);
        return modelLayerLocation;
    }

    public static void initialize() {
        ModelLayerRegistry.registerModelLayer(CHALCEDONY_GOLEM, GolemModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(GARNET_GOLEM, GolemModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(JADE_GOLEM, GolemModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(JASPER_GOLEM, GolemModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(ONYX_GOLEM, GolemModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(OPAL_GOLEM, GolemModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(RUBY_GOLEM, GolemModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(SAPPHIRE_GOLEM, GolemModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(SPINEL_GOLEM, GolemModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(TIGERS_EYE_GOLEM, GolemModel::createBodyLayer);

        ModelLayerRegistry.registerModelLayer(TRADER_SENTRY, SentryModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(WARRIOR_SENTRY, SentryModel::createBodyLayer);

        ModelLayerRegistry.registerModelLayer(JUNIPER_BOAT, BoatModel::createBoatModel);
        ModelLayerRegistry.registerModelLayer(JUNIPER_CHEST_BOAT, BoatModel::createChestBoatModel);
        ModelLayerRegistry.registerModelLayer(SPEED_BOAT, BoatModel::createBoatModel);
        ModelLayerRegistry.registerModelLayer(SPEED_CHEST_BOAT, BoatModel::createChestBoatModel);

        ModelLayerRegistry.registerModelLayer(RED_TNT, MinecartModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(ORANGE_TNT, MinecartModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(YELLOW_TNT, MinecartModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(GREEN_TNT, MinecartModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(BLUE_TNT, MinecartModel::createBodyLayer);
    }
}
