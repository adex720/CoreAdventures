package com.adex.datagen.texture;

import com.adex.item.ModItems;
import com.adex.item.armor.ModMaterialAssetGroups;
import com.adex.item.armor.ModTrimMaterials;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import org.jspecify.annotations.NonNull;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModArmorTrimTextureProvider extends ModTextureProvider<Item> {

    // This class is never used, if CoreAdventures#GENERATE_ARMOR_TRIMS is false,
    // and that is the only situation where these fields are null.
    @SuppressWarnings("DataFlowIssue")
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

    public ModArmorTrimTextureProvider(FabricPackOutput packOutput) {
        super(packOutput);

        this.itemPathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "textures/trims/color_palettes");

        this.generator = new HashMap<>();
        addGenerator(generator);
    }

    @Override
    public Path getPath(Item key, int id) {
        return itemPathProvider.file(BuiltInRegistries.ITEM.getKey(key), "png");
    }

    @Override
    public void buildTextures() {
        create(ModItems.CHALCEDONY, ColorPalette.CHALCEDONY_TRIM);
        create(ModItems.GARNET, ColorPalette.GARNET_TRIM);
        create(ModItems.JADE, ColorPalette.JADE_TRIM);
        create(ModItems.JASPER, ColorPalette.JASPER_TRIM);
        create(ModItems.ONYX, ColorPalette.ONYX_TRIM);
        create(ModItems.OPAL, ColorPalette.OPAL_TRIM);
        create(ModItems.RUBY, ColorPalette.RUBY_TRIM);
        create(ModItems.SAPPHIRE, ColorPalette.SAPPHIRE_TRIM);
        create(ModItems.SPINEL, ColorPalette.SPINEL_TRIM);
        create(ModItems.TIGERS_EYE, ColorPalette.TIGERS_EYE_TRIM);
        create(ModItems.BLUE_GEM_MIXTURE, ColorPalette.BLUE_GEM_MIXTURE_TRIM);
        create(ModItems.RED_GEM_MIXTURE, ColorPalette.RED_GEM_MIXTURE_TRIM);
        create(ModItems.SHINY_GEM_MIXTURE, ColorPalette.SHINY_GEM_MIXTURE_TRIM);
    }

    private void create(Item item, ColorPalette colorPalette) {
        generator.put(item, createImage(colorPalette));
    }

    private BufferedImage createImage(ColorPalette colorPalette) {
        return colorPalette.getColorImage();
    }

    @Override
    public @NonNull String getName() {
        return "Armor trim material texture provider";
    }
}
