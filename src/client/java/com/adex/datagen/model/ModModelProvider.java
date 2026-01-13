package com.adex.datagen.model;

import com.adex.block.ModBlocks;
import com.adex.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;
import org.jspecify.annotations.NonNull;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.createTrivialCube(ModBlocks.HARDENED_STONE);

        blockStateModelGenerator.createTrivialCube(ModBlocks.GABBRO);
        blockStateModelGenerator.createTrivialCube(ModBlocks.LARVIKITE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.SERPENTINITE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.SLATE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.TRAVERTINE);

        blockStateModelGenerator.createTrivialCube(ModBlocks.CHALCEDONY_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.GARNET_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.JADE_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.JASPER_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.ONYX_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.OPAL_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.RUBY_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.SAPPHIRE_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.SPINEL_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.TIGERS_EYE_ORE);

        blockStateModelGenerator.createTrivialCube(ModBlocks.CHALCEDONY_BLOCK);
        blockStateModelGenerator.createTrivialCube(ModBlocks.GARNET_BLOCK);
        blockStateModelGenerator.createTrivialCube(ModBlocks.JADE_BLOCK);
        blockStateModelGenerator.createTrivialCube(ModBlocks.JASPER_BLOCK);
        blockStateModelGenerator.createTrivialCube(ModBlocks.ONYX_BLOCK);
        blockStateModelGenerator.createTrivialCube(ModBlocks.OPAL_BLOCK);
        blockStateModelGenerator.createTrivialCube(ModBlocks.RUBY_BLOCK);
        blockStateModelGenerator.createTrivialCube(ModBlocks.SAPPHIRE_BLOCK);
        blockStateModelGenerator.createTrivialCube(ModBlocks.SPINEL_BLOCK);
        blockStateModelGenerator.createTrivialCube(ModBlocks.TIGERS_EYE_BLOCK);

        blockStateModelGenerator.createTrivialBlock(ModBlocks.CHALCEDONY_GOLEM_BLOCK, TexturedModel.CUBE_TOP_BOTTOM);
        blockStateModelGenerator.createTrivialBlock(ModBlocks.GARNET_GOLEM_BLOCK, TexturedModel.CUBE_TOP_BOTTOM);
        blockStateModelGenerator.createTrivialBlock(ModBlocks.JADE_GOLEM_BLOCK, TexturedModel.CUBE_TOP_BOTTOM);
        blockStateModelGenerator.createTrivialBlock(ModBlocks.JASPER_GOLEM_BLOCK, TexturedModel.CUBE_TOP_BOTTOM);
        blockStateModelGenerator.createTrivialBlock(ModBlocks.ONYX_GOLEM_BLOCK, TexturedModel.CUBE_TOP_BOTTOM);
        blockStateModelGenerator.createTrivialBlock(ModBlocks.OPAL_GOLEM_BLOCK, TexturedModel.CUBE_TOP_BOTTOM);
        blockStateModelGenerator.createTrivialBlock(ModBlocks.RUBY_GOLEM_BLOCK, TexturedModel.CUBE_TOP_BOTTOM);
        blockStateModelGenerator.createTrivialBlock(ModBlocks.SAPPHIRE_GOLEM_BLOCK, TexturedModel.CUBE_TOP_BOTTOM);
        blockStateModelGenerator.createTrivialBlock(ModBlocks.SPINEL_GOLEM_BLOCK, TexturedModel.CUBE_TOP_BOTTOM);
        blockStateModelGenerator.createTrivialBlock(ModBlocks.TIGERS_EYE_GOLEM_BLOCK, TexturedModel.CUBE_TOP_BOTTOM);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(ModItems.CHALCEDONY, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GARNET, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.JADE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.JASPER, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ONYX, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.OPAL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.RUBY, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SAPPHIRE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SPINEL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.TIGERS_EYE, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.BLUE_GEM_MIXTURE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.RED_GEM_MIXTURE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SHINY_GEM_MIXTURE, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.CHALCEDONY_HELMET, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CHALCEDONY_CHESTPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CHALCEDONY_LEGGINGS, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CHALCEDONY_BOOTS, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.GARNET_HELMET, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GARNET_CHESTPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GARNET_LEGGINGS, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GARNET_BOOTS, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.JADE_HELMET, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.JADE_CHESTPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.JADE_LEGGINGS, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.JADE_BOOTS, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.JASPER_HELMET, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.JASPER_CHESTPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.JASPER_LEGGINGS, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.JASPER_BOOTS, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.ONYX_HELMET, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ONYX_CHESTPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ONYX_LEGGINGS, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ONYX_BOOTS, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.OPAL_HELMET, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.OPAL_CHESTPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.OPAL_LEGGINGS, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.OPAL_BOOTS, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.RUBY_HELMET, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.RUBY_CHESTPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.RUBY_LEGGINGS, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.RUBY_BOOTS, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.SAPPHIRE_HELMET, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SAPPHIRE_CHESTPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SAPPHIRE_LEGGINGS, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SAPPHIRE_BOOTS, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.SPINEL_HELMET, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SPINEL_CHESTPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SPINEL_LEGGINGS, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SPINEL_BOOTS, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.TIGERS_EYE_HELMET, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.TIGERS_EYE_CHESTPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.TIGERS_EYE_LEGGINGS, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.TIGERS_EYE_BOOTS, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.GEM_HELMET, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GEM_CHESTPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GEM_LEGGINGS, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GEM_BOOTS, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.DYNAMITE, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.CHALCEDONY_GOLEM_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GARNET_GOLEM_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.JADE_GOLEM_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.JASPER_GOLEM_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ONYX_GOLEM_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.OPAL_GOLEM_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.RUBY_GOLEM_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SAPPHIRE_GOLEM_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SPINEL_GOLEM_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.TIGERS_EYE_GOLEM_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
    }

    @Override
    public @NonNull String getName() {
        return "Model provider";
    }
}
