package com.adex.datagen.texture;

import com.adex.datagen.ModDataGenerator;
import com.adex.item.ModItems;
import com.adex.util.Util;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ModItemTextureProvider extends ModTextureProvider<Item> {

    private final PackOutput.PathProvider itemPathProvider;

    private final Map<Item, BufferedImage> generator;

    public ModItemTextureProvider(FabricDataOutput packOutput) {
        super(packOutput);

        itemPathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "textures\\item");
        generator = new HashMap<>();

        addGenerator(generator);
    }

    @Override
    public Path getPath(Item key, int id) {
        return itemPathProvider.file(Util.getIdentifier(key), "png");
    }

    @Override
    public void buildTextures() {
        BufferedImage helmet;
        BufferedImage chestplate;
        BufferedImage leggings;
        BufferedImage boots;
        try {
            helmet = ModTextureProvider.getTexture("item\\diamond_helmet.png");
            chestplate = ModTextureProvider.getTexture("item\\diamond_chestplate.png");
            leggings = ModTextureProvider.getTexture("item\\diamond_leggings.png");
            boots = ModTextureProvider.getTexture("item\\diamond_boots.png");
        } catch (IOException e) {
            ModDataGenerator.LOGGER.error("Failed to load vanilla armor item textures: {}\n{}", e.getMessage(), Arrays.toString(e.getStackTrace()));
            return;
        }

        recolorVanilla(ModItems.CHALCEDONY_HELMET, ModItems.CHALCEDONY_CHESTPLATE, ModItems.CHALCEDONY_LEGGINGS, ModItems.CHALCEDONY_BOOTS, helmet, chestplate, leggings, boots, ColorPalette.CHALCEDONY_ARMOR);
        recolorVanilla(ModItems.GARNET_HELMET, ModItems.GARNET_CHESTPLATE, ModItems.GARNET_LEGGINGS, ModItems.GARNET_BOOTS, helmet, chestplate, leggings, boots, ColorPalette.GARNET_ARMOR);
        recolorVanilla(ModItems.JADE_HELMET, ModItems.JADE_CHESTPLATE, ModItems.JADE_LEGGINGS, ModItems.JADE_BOOTS, helmet, chestplate, leggings, boots, ColorPalette.JADE_ARMOR);
        recolorVanilla(ModItems.JASPER_HELMET, ModItems.JASPER_CHESTPLATE, ModItems.JASPER_LEGGINGS, ModItems.JASPER_BOOTS, helmet, chestplate, leggings, boots, ColorPalette.JASPER_ARMOR);
        recolorVanilla(ModItems.ONYX_HELMET, ModItems.ONYX_CHESTPLATE, ModItems.ONYX_LEGGINGS, ModItems.ONYX_BOOTS, helmet, chestplate, leggings, boots, ColorPalette.ONYX_ARMOR);
        //recolorVanilla(ModItems.OPAL_HELMET, ModItems.OPAL_CHESTPLATE, ModItems.OPAL_LEGGINGS, ModItems.OPAL_BOOTS, helmet, chestplate, leggings, boots, ColorPalette.OPAL);
        recolorVanilla(ModItems.RUBY_HELMET, ModItems.RUBY_CHESTPLATE, ModItems.RUBY_LEGGINGS, ModItems.RUBY_BOOTS, helmet, chestplate, leggings, boots, ColorPalette.RUBY_ARMOR);
        recolorVanilla(ModItems.SAPPHIRE_HELMET, ModItems.SAPPHIRE_CHESTPLATE, ModItems.SAPPHIRE_LEGGINGS, ModItems.SAPPHIRE_BOOTS, helmet, chestplate, leggings, boots, ColorPalette.SAPPHIRE_ARMOR);
        recolorVanilla(ModItems.SPINEL_HELMET, ModItems.SPINEL_CHESTPLATE, ModItems.SPINEL_LEGGINGS, ModItems.SPINEL_BOOTS, helmet, chestplate, leggings, boots, ColorPalette.SPINEL_ARMOR);
        recolorVanilla(ModItems.TIGERS_EYE_HELMET, ModItems.TIGERS_EYE_CHESTPLATE, ModItems.TIGERS_EYE_LEGGINGS, ModItems.TIGERS_EYE_BOOTS, helmet, chestplate, leggings, boots, ColorPalette.TIGERS_EYE_ARMOR);
    }

    public void recolorVanilla(Item helmet, Item chestplate, Item leggings, Item boots, BufferedImage helmetImage, BufferedImage chestplateImage, BufferedImage leggingsImage, BufferedImage bootsImage, ColorPalette palette) {
        generator.put(helmet, replaceColorPalette(helmetImage, ColorPalette.DIAMOND_ARMOR, palette));
        generator.put(chestplate, replaceColorPalette(chestplateImage, ColorPalette.DIAMOND_ARMOR, palette));
        generator.put(leggings, replaceColorPalette(leggingsImage, ColorPalette.DIAMOND_ARMOR, palette));
        generator.put(boots, replaceColorPalette(bootsImage, ColorPalette.DIAMOND_ARMOR, palette));
    }

    @Override
    public String getName() {
        return "Item texture provider";
    }
}
