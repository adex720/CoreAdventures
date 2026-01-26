package com.adex.datagen.texture;

import com.adex.datagen.ModDataGenerator;
import com.adex.entity.ModEntities;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import org.jspecify.annotations.NonNull;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ModEntityTextureProvider extends ModTextureProvider<EntityType<?>> {

    private final PackOutput.PathProvider entityTextureProvider;
    private final PackOutput.PathProvider boatTextureProvider;
    private final PackOutput.PathProvider chestBoatTextureProvider;

    private final Map<EntityType<?>, BufferedImage> golems;
    private final Map<EntityType<?>, BufferedImage> boats;
    private final Map<EntityType<?>, BufferedImage> chestBoats;

    public ModEntityTextureProvider(FabricDataOutput packOutput) {
        super(packOutput);
        entityTextureProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "textures\\entity");
        golems = new HashMap<>();
        addGenerator(golems);

        boatTextureProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "textures\\entity\\boat");
        boats = new HashMap<>();
        addGenerator(boats);

        chestBoatTextureProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "textures\\entity\\chest_boat");
        chestBoats = new HashMap<>();
        addGenerator(chestBoats);
    }

    @Override
    public Path getPath(EntityType<?> key, int id) {
        if (id == 0) return entityTextureProvider.file(EntityType.getKey(key), "png");
        if (id == 1) return boatTextureProvider.file(boatIdentifier(key), "png");
        return chestBoatTextureProvider.file(boatIdentifier(key), "png");
    }

    /**
     * Removes everything after the first _ from the identifier path
     */
    public Identifier boatIdentifier(EntityType<?> entity) {
        Identifier identifier = EntityType.getKey(entity);
        String path = identifier.getPath();
        String newPath = path.substring(0, path.indexOf('_'));
        return identifier.withPath(newPath);
    }

    @Override
    public void buildTextures() {
        BufferedImage golem;
        BufferedImage boat;
        BufferedImage chestBoat;
        try {
            golem = ModTextureProvider.getTexture("entity\\chalcedony_golem.png");
            boat = ModTextureProvider.getTexture("entity\\boat\\juniper.png");
            chestBoat = ModTextureProvider.getTexture("entity\\chest_boat\\juniper.png");
        } catch (IOException e) {
            ModDataGenerator.LOGGER.error("Failed to load base entity textures: {}\n{}", e.getMessage(), Arrays.toString(e.getStackTrace()));
            return;
        }

        recolorGolem(ModEntities.CHALCEDONY_GOLEM, golem, ColorPalette.CHALCEDONY_ARMOR);
        recolorGolem(ModEntities.GARNET_GOLEM, golem, ColorPalette.GARNET_ARMOR);
        recolorGolem(ModEntities.JADE_GOLEM, golem, ColorPalette.JADE_ARMOR);
        recolorGolem(ModEntities.JASPER_GOLEM, golem, ColorPalette.JASPER_ARMOR);
        recolorGolem(ModEntities.ONYX_GOLEM, golem, ColorPalette.ONYX_ARMOR);
        //recolorGolem(ModEntities.OPAL_GOLEM, golems, ColorPalette.OPAL_ARMOR);
        recolorGolem(ModEntities.RUBY_GOLEM, golem, ColorPalette.RUBY_ARMOR);
        recolorGolem(ModEntities.SAPPHIRE_GOLEM, golem, ColorPalette.SAPPHIRE_ARMOR);
        recolorGolem(ModEntities.SPINEL_GOLEM, golem, ColorPalette.SPINEL_ARMOR);
        //recolorGolem(ModEntities.TIGERS_EYE_GOLEM, golems, ColorPalette.TIGERS_EYE_ARMOR);

        recolorBoat(ModEntities.SPEED_BOAT, boat, ColorPalette.CHALCEDONY_BOAT);
        recolorChestBoat(ModEntities.SPEED_CHEST_BOAT, chestBoat, ColorPalette.CHALCEDONY_BOAT);
    }

    public void recolorGolem(EntityType<?> entityType, BufferedImage humanoidImage, ColorPalette palette) {
        golems.put(entityType, replaceColorPalette(humanoidImage, ColorPalette.CHALCEDONY_ARMOR, palette));
    }

    public void recolorBoat(EntityType<?> entityType, BufferedImage humanoidImage, ColorPalette palette) {
        boats.put(entityType, replaceColorPalette(humanoidImage, ColorPalette.JUNIPER, palette));
    }

    public void recolorChestBoat(EntityType<?> entityType, BufferedImage humanoidImage, ColorPalette palette) {
        chestBoats.put(entityType, replaceColorPalette(humanoidImage, ColorPalette.JUNIPER, palette));
    }

    @Override
    public @NonNull String getName() {
        return "Entity texture provider";
    }
}
