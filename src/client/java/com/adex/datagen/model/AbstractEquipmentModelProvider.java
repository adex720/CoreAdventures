package com.adex.datagen.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.equipment.ArmorMaterial;
import org.jspecify.annotations.NonNull;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public abstract class AbstractEquipmentModelProvider implements DataProvider {

    private final PackOutput.PathProvider equpmentPathProvider;

    private final Map<ArmorMaterial, EquipmentModel> generator;

    public AbstractEquipmentModelProvider(FabricDataOutput output) {
        generator = new HashMap<>();
        equpmentPathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "equipment");
    }

    public abstract void buildTextures();

    public void generateEquipment(ArmorMaterial material) {
        Identifier identifier = material.assetId().identifier();
        generator.put(material, new EquipmentModel(identifier, identifier));
    }

    @Override
    public @NonNull CompletableFuture<?> run(@NonNull CachedOutput cachedOutput) {
        buildTextures();

        return DataProvider.saveAll(cachedOutput, this::toJson, this::toPath, generator);
    }

    public JsonObject toJson(EquipmentModel model) {
        JsonObject humanoidTexture = new JsonObject();
        humanoidTexture.addProperty("texture", (model.humanoid().toString()));

        JsonArray humanoid = new JsonArray();
        humanoid.add(humanoidTexture);

        JsonObject humanoidLeggingsTexture = new JsonObject();
        humanoidLeggingsTexture.addProperty("texture", (model.humanoidLeggings().toString()));

        JsonArray humanoidLeggings = new JsonArray();
        humanoidLeggings.add(humanoidLeggingsTexture);

        JsonObject layersJson = new JsonObject();
        layersJson.add("humanoid", humanoid);
        layersJson.add("humanoid_leggings", humanoidLeggings);

        JsonObject json = new JsonObject();
        json.add("layers", layersJson);

        return json;
    }

    public Path toPath(ArmorMaterial material) {
        return equpmentPathProvider.json(material.assetId().identifier());
    }

    @Override
    public @NonNull String getName() {
        return "Equipment model provider";
    }
}
