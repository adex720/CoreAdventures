package com.adex.datagen.texture;

import com.adex.datagen.ModDataGenerator;
import com.adex.item.ModItems;
import com.adex.util.Util;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import org.jspecify.annotations.NonNull;

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
        BufferedImage spawnEgg;
        BufferedImage boat;
        try {
            helmet = ModTextureProvider.getTexture("item\\diamond_helmet.png");
            chestplate = ModTextureProvider.getTexture("item\\diamond_chestplate.png");
            leggings = ModTextureProvider.getTexture("item\\diamond_leggings.png");
            boots = ModTextureProvider.getTexture("item\\diamond_boots.png");
            spawnEgg = ModTextureProvider.getTexture("item\\chalcedony_golem_spawn_egg.png");
            boat = ModTextureProvider.getTexture("item\\juniper_boat.png");
        } catch (IOException e) {
            ModDataGenerator.LOGGER.error("Failed to load base armor item textures: {}\n{}", e.getMessage(), Arrays.toString(e.getStackTrace()));
            return;
        }

        recolorBaseArmor(ModItems.CHALCEDONY_HELMET, ModItems.CHALCEDONY_CHESTPLATE, ModItems.CHALCEDONY_LEGGINGS, ModItems.CHALCEDONY_BOOTS, helmet, chestplate, leggings, boots, ColorPalette.CHALCEDONY_ARMOR);
        recolorBaseArmor(ModItems.GARNET_HELMET, ModItems.GARNET_CHESTPLATE, ModItems.GARNET_LEGGINGS, ModItems.GARNET_BOOTS, helmet, chestplate, leggings, boots, ColorPalette.GARNET_ARMOR);
        recolorBaseArmor(ModItems.JADE_HELMET, ModItems.JADE_CHESTPLATE, ModItems.JADE_LEGGINGS, ModItems.JADE_BOOTS, helmet, chestplate, leggings, boots, ColorPalette.JADE_ARMOR);
        recolorBaseArmor(ModItems.JASPER_HELMET, ModItems.JASPER_CHESTPLATE, ModItems.JASPER_LEGGINGS, ModItems.JASPER_BOOTS, helmet, chestplate, leggings, boots, ColorPalette.JASPER_ARMOR);
        recolorBaseArmor(ModItems.ONYX_HELMET, ModItems.ONYX_CHESTPLATE, ModItems.ONYX_LEGGINGS, ModItems.ONYX_BOOTS, helmet, chestplate, leggings, boots, ColorPalette.ONYX_ARMOR);
        //recolorBaseArmor(ModItems.OPAL_HELMET, ModItems.OPAL_CHESTPLATE, ModItems.OPAL_LEGGINGS, ModItems.OPAL_BOOTS, helmet, chestplate, leggings, boots, ColorPalette.OPAL);
        recolorBaseArmor(ModItems.RUBY_HELMET, ModItems.RUBY_CHESTPLATE, ModItems.RUBY_LEGGINGS, ModItems.RUBY_BOOTS, helmet, chestplate, leggings, boots, ColorPalette.RUBY_ARMOR);
        recolorBaseArmor(ModItems.SAPPHIRE_HELMET, ModItems.SAPPHIRE_CHESTPLATE, ModItems.SAPPHIRE_LEGGINGS, ModItems.SAPPHIRE_BOOTS, helmet, chestplate, leggings, boots, ColorPalette.SAPPHIRE_ARMOR);
        recolorBaseArmor(ModItems.SPINEL_HELMET, ModItems.SPINEL_CHESTPLATE, ModItems.SPINEL_LEGGINGS, ModItems.SPINEL_BOOTS, helmet, chestplate, leggings, boots, ColorPalette.SPINEL_ARMOR);
        recolorBaseArmor(ModItems.TIGERS_EYE_HELMET, ModItems.TIGERS_EYE_CHESTPLATE, ModItems.TIGERS_EYE_LEGGINGS, ModItems.TIGERS_EYE_BOOTS, helmet, chestplate, leggings, boots, ColorPalette.TIGERS_EYE_ARMOR);

        recolorBaseItem(ModItems.CHALCEDONY_GOLEM_SPAWN_EGG, spawnEgg, ColorPalette.CHALCEDONY_ARMOR, ColorPalette.CHALCEDONY_ARMOR);
        recolorBaseItem(ModItems.GARNET_GOLEM_SPAWN_EGG, spawnEgg, ColorPalette.CHALCEDONY_ARMOR, ColorPalette.GARNET_ARMOR);
        recolorBaseItem(ModItems.JADE_GOLEM_SPAWN_EGG, spawnEgg, ColorPalette.CHALCEDONY_ARMOR, ColorPalette.JADE_ARMOR);
        recolorBaseItem(ModItems.JASPER_GOLEM_SPAWN_EGG, spawnEgg, ColorPalette.CHALCEDONY_ARMOR, ColorPalette.JASPER_ARMOR);
        recolorBaseItem(ModItems.ONYX_GOLEM_SPAWN_EGG, spawnEgg, ColorPalette.CHALCEDONY_ARMOR, ColorPalette.ONYX_ARMOR);
        //recolorBaseItem(ModItems.OPAL_GOLEM_SPAWN_EGG, spawnEgg, ColorPalette.CHALCEDONY_ARMOR, ColorPalette.OPAL);
        recolorBaseItem(ModItems.RUBY_GOLEM_SPAWN_EGG, spawnEgg, ColorPalette.CHALCEDONY_ARMOR, ColorPalette.RUBY_ARMOR);
        recolorBaseItem(ModItems.SAPPHIRE_GOLEM_SPAWN_EGG, spawnEgg, ColorPalette.CHALCEDONY_ARMOR, ColorPalette.SAPPHIRE_ARMOR);
        recolorBaseItem(ModItems.SPINEL_GOLEM_SPAWN_EGG, spawnEgg, ColorPalette.CHALCEDONY_ARMOR, ColorPalette.SPINEL_ARMOR);
        recolorBaseItem(ModItems.TIGERS_EYE_GOLEM_SPAWN_EGG, spawnEgg, ColorPalette.CHALCEDONY_ARMOR, ColorPalette.TIGERS_EYE_ARMOR);

        recolorBaseItem(ModItems.SPEED_BOAT, boat, ColorPalette.JUNIPER, ColorPalette.CHALCEDONY_BOAT);
    }

    public void recolorBaseArmor(Item helmet, Item chestplate, Item leggings, Item boots, BufferedImage helmetImage, BufferedImage chestplateImage, BufferedImage leggingsImage, BufferedImage bootsImage, ColorPalette palette) {
        recolorBaseItem(helmet, helmetImage, ColorPalette.DIAMOND_ARMOR, palette);
        recolorBaseItem(chestplate, chestplateImage, ColorPalette.DIAMOND_ARMOR, palette);
        recolorBaseItem(leggings, leggingsImage, ColorPalette.DIAMOND_ARMOR, palette);
        recolorBaseItem(boots, bootsImage, ColorPalette.DIAMOND_ARMOR, palette);
    }

    public void recolorBaseItem(Item item, BufferedImage image, ColorPalette original, ColorPalette palette) {
        generator.put(item, replaceColorPalette(image, original, palette));
    }

    @Override
    public @NonNull String getName() {
        return "Item texture provider";
    }
}
