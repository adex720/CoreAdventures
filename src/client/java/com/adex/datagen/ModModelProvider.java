package com.adex.datagen;

import com.adex.block.ModBlocks;
import com.adex.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

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
    }

    @Override
    public String getName() {
        return "Core Adventures Model Provider";
    }
}
