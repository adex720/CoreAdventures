package com.adex.datagen.texture;

import com.adex.item.ModItems;
import com.adex.item.armor.ModMaterialAssetGroups;
import com.adex.item.armor.ModTrimMaterials;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import org.jspecify.annotations.NonNull;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModArmorTrimTextureProvider extends ModTextureProvider<Item> {

    public static final int WIDTH = 8;

    public static final List<ItemModelGenerators.TrimMaterialData> MOD_TRIM_MATERIALS = List.of(
            new ItemModelGenerators.TrimMaterialData(ModMaterialAssetGroups.CHALCEDONY, ModTrimMaterials.CHALCEDONY),
            new ItemModelGenerators.TrimMaterialData(ModMaterialAssetGroups.GARNET, ModTrimMaterials.GARNET),
            new ItemModelGenerators.TrimMaterialData(ModMaterialAssetGroups.JADE, ModTrimMaterials.JADE),
            new ItemModelGenerators.TrimMaterialData(ModMaterialAssetGroups.JASPER, ModTrimMaterials.JASPER),
            new ItemModelGenerators.TrimMaterialData(ModMaterialAssetGroups.ONYX, ModTrimMaterials.ONYX),
            new ItemModelGenerators.TrimMaterialData(ModMaterialAssetGroups.OPAL, ModTrimMaterials.OPAL),
            new ItemModelGenerators.TrimMaterialData(ModMaterialAssetGroups.RUBY, ModTrimMaterials.RUBY),
            new ItemModelGenerators.TrimMaterialData(ModMaterialAssetGroups.SAPPHIRE, ModTrimMaterials.SAPPHIRE),
            new ItemModelGenerators.TrimMaterialData(ModMaterialAssetGroups.SPINEL, ModTrimMaterials.SPINEL),
            new ItemModelGenerators.TrimMaterialData(ModMaterialAssetGroups.TIGERS_EYE, ModTrimMaterials.TIGERS_EYE),
            new ItemModelGenerators.TrimMaterialData(ModMaterialAssetGroups.BLUE_GEM_MIXTURE, ModTrimMaterials.BLUE_GEM_MIXTURE),
            new ItemModelGenerators.TrimMaterialData(ModMaterialAssetGroups.RED_GEM_MIXTURE, ModTrimMaterials.RED_GEM_MIXTURE),
            new ItemModelGenerators.TrimMaterialData(ModMaterialAssetGroups.SHINY_GEM_MIXTURE, ModTrimMaterials.SHINY_GEM_MIXTURE));

    private final PackOutput.PathProvider itemPathProvider;

    private final Map<Item, BufferedImage> generator;

    public ModArmorTrimTextureProvider(FabricDataOutput packOutput) {
        super(packOutput);

        this.itemPathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "textures\\trims\\color_palettes");

        this.generator = new HashMap<>();
        addGenerator(generator);
    }

    @Override
    public Path getPath(Item key, int id) {
        return itemPathProvider.file(BuiltInRegistries.ITEM.getKey(key), "png");
    }

    @Override
    public void buildTextures() {
        create(ModItems.CHALCEDONY, ColorPalette.CHALCEDONY);
        create(ModItems.GARNET, ColorPalette.GARNET);
        create(ModItems.JADE, ColorPalette.JADE);
        create(ModItems.JASPER, ColorPalette.JASPER);
        create(ModItems.ONYX, ColorPalette.ONYX);
        create(ModItems.OPAL, ColorPalette.OPAL);
        create(ModItems.RUBY, ColorPalette.RUBY);
        create(ModItems.SAPPHIRE, ColorPalette.SAPPHIRE);
        create(ModItems.SPINEL, ColorPalette.SPINEL);
        create(ModItems.TIGERS_EYE, ColorPalette.TIGERS_EYE);
        create(ModItems.BLUE_GEM_MIXTURE, ColorPalette.BLUE_GEM_MIXTURE);
        create(ModItems.RED_GEM_MIXTURE, ColorPalette.RED_GEM_MIXTURE);
        create(ModItems.SHINY_GEM_MIXTURE, ColorPalette.SHINY_GEM_MIXTURE);
    }

    private void create(Item item, ColorPalette colorPalette) {
        generator.put(item, createImage(colorPalette));
    }

    private BufferedImage createImage(ColorPalette colorPalette) {
        BufferedImage image = new BufferedImage(WIDTH, 1, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        for (int x = 0; x < WIDTH; x++) {
            if (x < colorPalette.colorCount) g.setColor(new Color(colorPalette.color(x)));
            g.fillRect(x, 0, 1, 1);
        }

        return image;
    }

    @Override
    public @NonNull String getName() {
        return "Armor trim material texture provider";
    }
}
