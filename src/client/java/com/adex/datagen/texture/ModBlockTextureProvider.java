package com.adex.datagen.texture;

import com.adex.block.ModBlocks;
import com.adex.datagen.ModDataGenerator;
import com.adex.util.Util;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NonNull;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ModBlockTextureProvider extends ModTextureProvider<Identifier> {

    private final PackOutput.PathProvider itemPathProvider;

    private final Map<Identifier, BufferedImage> generator;

    public ModBlockTextureProvider(FabricDataOutput packOutput) {
        super(packOutput);

        itemPathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "textures\\block");
        generator = new HashMap<>();

        addGenerator(generator);
    }

    @Override
    public Path getPath(Identifier key, int id) {
        return itemPathProvider.file(key, "png");
    }

    @Override
    public void buildTextures() {
        BufferedImage golemBlockBottom;
        BufferedImage golemBlockSide;
        BufferedImage golemBlockTop;
        try {
            golemBlockBottom = ModTextureProvider.getTexture("block\\chalcedony_golem_block_bottom.png");
            golemBlockSide = ModTextureProvider.getTexture("block\\chalcedony_golem_block_side.png");
            golemBlockTop = ModTextureProvider.getTexture("block\\chalcedony_golem_block_top.png");
        } catch (IOException e) {
            ModDataGenerator.LOGGER.error("Failed to load base armor item textures: {}\n{}", e.getMessage(), Arrays.toString(e.getStackTrace()));
            return;
        }

        recolorTopBottom(ModBlocks.CHALCEDONY_GOLEM_BLOCK, golemBlockBottom, golemBlockSide, golemBlockTop, ColorPalette.CHALCEDONY_ARMOR);
        recolorTopBottom(ModBlocks.GARNET_GOLEM_BLOCK, golemBlockBottom, golemBlockSide, golemBlockTop, ColorPalette.GARNET_ARMOR);
        recolorTopBottom(ModBlocks.JADE_GOLEM_BLOCK, golemBlockBottom, golemBlockSide, golemBlockTop, ColorPalette.JADE_ARMOR);
        recolorTopBottom(ModBlocks.JASPER_GOLEM_BLOCK, golemBlockBottom, golemBlockSide, golemBlockTop, ColorPalette.JASPER_ARMOR);
        recolorTopBottom(ModBlocks.ONYX_GOLEM_BLOCK, golemBlockBottom, golemBlockSide, golemBlockTop, ColorPalette.ONYX_ARMOR);
        //recolorTopBottom(ModBlocks.OPAL_GOLEM_BLOCK, golemBlockBottom,golemBlockSide,golemBlockTop, ColorPalette.OPAL);
        recolorTopBottom(ModBlocks.RUBY_GOLEM_BLOCK, golemBlockBottom, golemBlockSide, golemBlockTop, ColorPalette.RUBY_ARMOR);
        recolorTopBottom(ModBlocks.SAPPHIRE_GOLEM_BLOCK, golemBlockBottom, golemBlockSide, golemBlockTop, ColorPalette.SAPPHIRE_ARMOR);
        recolorTopBottom(ModBlocks.SPINEL_GOLEM_BLOCK, golemBlockBottom, golemBlockSide, golemBlockTop, ColorPalette.SPINEL_ARMOR);
        //recolorTopBottom(ModBlocks.TIGERS_EYE_GOLEM_BLOCK, golemBlockBottom, golemBlockSide, golemBlockTop, ColorPalette.TIGERS_EYE_ARMOR);
    }

    public void recolorTopBottom(Block block, BufferedImage bottomImage, BufferedImage sideImage, BufferedImage topImage, ColorPalette palette) {
        recolorBaseItem(block, "_bottom", bottomImage, ColorPalette.CHALCEDONY_ARMOR, palette);
        recolorBaseItem(block, "_side", sideImage, ColorPalette.CHALCEDONY_ARMOR, palette);
        recolorBaseItem(block, "_top", topImage, ColorPalette.CHALCEDONY_ARMOR, palette);
    }

    public void recolorBaseItem(Block block, String name, BufferedImage image, ColorPalette original, ColorPalette palette) {
        generator.put(Util.getIdentifier(block).withSuffix(name), replaceColorPalette(image, original, palette));
    }

    public void recolorBaseItem(Block block, BufferedImage image, ColorPalette original, ColorPalette palette) {
        recolorBaseItem(block, "", image, original, palette);
    }

    @Override
    public @NonNull String getName() {
        return "Block texture provider";
    }
}
