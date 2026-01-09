package com.adex.datagen.texture;

import com.adex.datagen.ModDataGenerator;
import com.adex.entity.ModEntities;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.PackOutput;
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

    private final Map<EntityType<?>, BufferedImage> golem;

    public ModEntityTextureProvider(FabricDataOutput packOutput) {
        super(packOutput);
        entityTextureProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "textures\\entity");
        golem = new HashMap<>();
        addGenerator(golem);
    }

    @Override
    public Path getPath(EntityType<?> key, int id) {
        return entityTextureProvider.file(EntityType.getKey(key), "png");
    }

    @Override
    public void buildTextures() {
        BufferedImage golem;
        try {
            golem = ModTextureProvider.getTexture("entity\\chalcedony_golem.png");
        } catch (IOException e) {
            ModDataGenerator.LOGGER.error("Failed to load base entity textures: {}\n{}", e.getMessage(), Arrays.toString(e.getStackTrace()));
            return;
        }

        recolorGolem(ModEntities.CHALCEDONY_GOLEM, golem, ColorPalette.CHALCEDONY_ARMOR);
    }

    public void recolorGolem(EntityType<?> entityType, BufferedImage humanoidImage, ColorPalette palette) {
        golem.put(entityType, replaceColorPalette(humanoidImage, ColorPalette.CHALCEDONY_ARMOR, palette));
    }

    @Override
    public @NonNull String getName() {
        return "Entity texture provider";
    }
}
