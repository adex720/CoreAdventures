package com.adex.datagen.texture;

import com.adex.datagen.ModDataGenerator;
import com.adex.item.armor.ModArmorMaterials;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.equipment.ArmorMaterial;
import org.jspecify.annotations.NonNull;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ModHumanoidTextureProvider extends ModTextureProvider<ArmorMaterial> {

    private final PackOutput.PathProvider humanoidPathProvider;
    private final PackOutput.PathProvider humanoidLeggingsPathProvider;

    private final Map<ArmorMaterial, BufferedImage> humanoid;
    private final Map<ArmorMaterial, BufferedImage> humanoidLeggings;

    public ModHumanoidTextureProvider(FabricDataOutput packOutput) {
        super(packOutput);
        humanoidPathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "textures\\entity\\equipment\\humanoid");
        humanoidLeggingsPathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "textures\\entity\\equipment\\humanoid_leggings");

        humanoid = new HashMap<>();
        humanoidLeggings = new HashMap<>();

        addGenerator(humanoid);
        addGenerator(humanoidLeggings);
    }

    @Override
    public Path getPath(ArmorMaterial key, int id) {
        return (id == 0 ? humanoidPathProvider : humanoidLeggingsPathProvider).file(key.assetId().identifier(), "png");
    }

    @Override
    public void buildTextures() {
        BufferedImage humanoid;
        BufferedImage humanoidLeggings;
        try {
            humanoid = ModTextureProvider.getTexture("entity\\equipment\\humanoid\\diamond.png");
            humanoidLeggings = ModTextureProvider.getTexture("entity\\equipment\\humanoid_leggings\\diamond.png");
        } catch (IOException e) {
            ModDataGenerator.LOGGER.error("Failed to load base humanoid textures: {}\n{}", e.getMessage(), Arrays.toString(e.getStackTrace()));
            return;
        }

        recolorBase(ModArmorMaterials.CHALCEDONY_ARMOR_MATERIAL, humanoid, humanoidLeggings, ColorPalette.CHALCEDONY_HUMANOID);
        recolorBase(ModArmorMaterials.GARNET_ARMOR_MATERIAL, humanoid, humanoidLeggings, ColorPalette.GARNET_HUMANOID);
        recolorBase(ModArmorMaterials.JADE_ARMOR_MATERIAL, humanoid, humanoidLeggings, ColorPalette.JADE_HUMANOID);
        recolorBase(ModArmorMaterials.JASPER_ARMOR_MATERIAL, humanoid, humanoidLeggings, ColorPalette.JASPER_HUMANOID);
        recolorBase(ModArmorMaterials.ONYX_ARMOR_MATERIAL, humanoid, humanoidLeggings, ColorPalette.ONYX_HUMANOID);
        //recolorBaseArmor(ModArmorMaterials.OPAL_ARMOR_MATERIAL, humanoid, humanoidLeggings, ColorPalette.OPAL);
        recolorBase(ModArmorMaterials.RUBY_ARMOR_MATERIAL, humanoid, humanoidLeggings, ColorPalette.RUBY_HUMANOID);
        recolorBase(ModArmorMaterials.SAPPHIRE_ARMOR_MATERIAL, humanoid, humanoidLeggings, ColorPalette.SAPPHIRE_HUMANOID);
        recolorBase(ModArmorMaterials.SPINEL_ARMOR_MATERIAL, humanoid, humanoidLeggings, ColorPalette.SPINEL_HUMANOID);
        recolorBase(ModArmorMaterials.TIGERS_EYE_ARMOR_MATERIAL, humanoid, humanoidLeggings, ColorPalette.TIGERS_EYE_HUMANOID);
    }

    public void recolorBase(ArmorMaterial material, BufferedImage humanoidImage, BufferedImage humanoidLeggingsImage, ColorPalette palette) {
        humanoid.put(material, replaceColorPalette(humanoidImage, ColorPalette.DIAMOND_HUMANOID, palette));
        humanoidLeggings.put(material, replaceColorPalette(humanoidLeggingsImage, ColorPalette.DIAMOND_HUMANOID, palette));
    }

    @Override
    public @NonNull String getName() {
        return "Humanoid texture provider";
    }
}
