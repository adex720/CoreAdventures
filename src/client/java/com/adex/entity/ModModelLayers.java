package com.adex.entity;

import com.adex.CoreAdventures;
import com.adex.entity.golem.GolemModel;
import com.adex.mixin.client.ModelLayersAccessor;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
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
        EntityModelLayerRegistry.registerModelLayer(CHALCEDONY_GOLEM, GolemModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(GARNET_GOLEM, GolemModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(JADE_GOLEM, GolemModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(JASPER_GOLEM, GolemModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ONYX_GOLEM, GolemModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(OPAL_GOLEM, GolemModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(RUBY_GOLEM, GolemModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(SAPPHIRE_GOLEM, GolemModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(SPINEL_GOLEM, GolemModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(TIGERS_EYE_GOLEM, GolemModel::createBodyLayer);

        EntityModelLayerRegistry.registerModelLayer(JUNIPER_BOAT, BoatModel::createBoatModel);
        EntityModelLayerRegistry.registerModelLayer(JUNIPER_CHEST_BOAT, BoatModel::createChestBoatModel);
        EntityModelLayerRegistry.registerModelLayer(SPEED_BOAT, BoatModel::createBoatModel);
        EntityModelLayerRegistry.registerModelLayer(SPEED_CHEST_BOAT, BoatModel::createChestBoatModel);

        EntityModelLayerRegistry.registerModelLayer(RED_TNT, MinecartModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ORANGE_TNT, MinecartModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(YELLOW_TNT, MinecartModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(GREEN_TNT, MinecartModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(BLUE_TNT, MinecartModel::createBodyLayer);
    }
}
