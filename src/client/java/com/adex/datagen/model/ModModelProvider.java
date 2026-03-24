package com.adex.datagen.model;

import com.adex.CoreAdventures;
import com.adex.block.HeatStabilizerBlock;
import com.adex.block.ModBlocks;
import com.adex.item.ModItems;
import com.adex.item.armor.ModArmorMaterials;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.color.item.Dye;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.SelectItemModel;
import net.minecraft.client.renderer.item.properties.numeric.CompassAngle;
import net.minecraft.client.renderer.item.properties.numeric.CompassAngleState;
import net.minecraft.client.renderer.item.properties.select.TrimMaterialProperty;
import net.minecraft.data.BlockFamilies;
import net.minecraft.data.BlockFamily;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.family(ModBlocks.HARDENED_STONE).slab(ModBlocks.HARDENED_STONE_SLAB).stairs(ModBlocks.HARDENED_STONE_STAIRS).wall(ModBlocks.HARDENED_STONE_WALL);
        blockStateModelGenerator.family(ModBlocks.HARDENED_STONE_BRICKS).slab(ModBlocks.HARDENED_STONE_BRICKS_SLAB).stairs(ModBlocks.HARDENED_STONE_BRICKS_STAIRS).wall(ModBlocks.HARDENED_STONE_BRICKS_WALL);
        blockStateModelGenerator.family(ModBlocks.GABBRO).slab(ModBlocks.POLISHED_GABBRO_SLAB).stairs(ModBlocks.POLISHED_GABBRO_STAIRS).wall(ModBlocks.POLISHED_GABBRO_WALL);
        blockStateModelGenerator.family(ModBlocks.POLISHED_GABBRO).slab(ModBlocks.GABBRO_SLAB).stairs(ModBlocks.GABBRO_STAIRS).wall(ModBlocks.GABBRO_WALL);
        blockStateModelGenerator.family(ModBlocks.POLISHED_LARVIKITE).slab(ModBlocks.POLISHED_LARVIKITE_SLAB).stairs(ModBlocks.POLISHED_LARVIKITE_STAIRS).wall(ModBlocks.POLISHED_LARVIKITE_WALL);
        blockStateModelGenerator.family(ModBlocks.LARVIKITE).slab(ModBlocks.LARVIKITE_SLAB).stairs(ModBlocks.LARVIKITE_STAIRS).wall(ModBlocks.LARVIKITE_WALL);
        blockStateModelGenerator.family(ModBlocks.POLISHED_SERPENTINITE).slab(ModBlocks.POLISHED_SERPENTINITE_SLAB).stairs(ModBlocks.POLISHED_SERPENTINITE_STAIRS).wall(ModBlocks.POLISHED_SERPENTINITE_WALL);
        blockStateModelGenerator.family(ModBlocks.SERPENTINITE).slab(ModBlocks.SERPENTINITE_SLAB).stairs(ModBlocks.SERPENTINITE_STAIRS).wall(ModBlocks.SERPENTINITE_WALL);
        blockStateModelGenerator.family(ModBlocks.POLISHED_SLATE).slab(ModBlocks.POLISHED_SLATE_SLAB).stairs(ModBlocks.POLISHED_SLATE_STAIRS).wall(ModBlocks.POLISHED_SLATE_WALL);
        blockStateModelGenerator.family(ModBlocks.SLATE).slab(ModBlocks.SLATE_SLAB).stairs(ModBlocks.SLATE_STAIRS).wall(ModBlocks.SLATE_WALL);
        blockStateModelGenerator.family(ModBlocks.POLISHED_TRAVERTINE).slab(ModBlocks.POLISHED_TRAVERTINE_SLAB).stairs(ModBlocks.POLISHED_TRAVERTINE_STAIRS).wall(ModBlocks.POLISHED_TRAVERTINE_WALL);
        blockStateModelGenerator.family(ModBlocks.TRAVERTINE).slab(ModBlocks.TRAVERTINE_SLAB).stairs(ModBlocks.TRAVERTINE_STAIRS).wall(ModBlocks.TRAVERTINE_WALL);

        blockStateModelGenerator.createTrivialCube(ModBlocks.CRACKED_HARDENED_STONE_BRICKS);
        blockStateModelGenerator.createTrivialCube(ModBlocks.HEAVY_HARDENED_STONE_BRICKS);

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

        blockStateModelGenerator.woodProvider(ModBlocks.JUNIPER_LOG).logWithHorizontal(ModBlocks.JUNIPER_LOG).wood(ModBlocks.JUNIPER_WOOD);
        blockStateModelGenerator.woodProvider(ModBlocks.STRIPPED_JUNIPER_LOG).logWithHorizontal(ModBlocks.STRIPPED_JUNIPER_LOG).wood(ModBlocks.STRIPPED_JUNIPER_WOOD);
        BlockFamily juniperFamily = BlockFamilies.familyBuilder(ModBlocks.JUNIPER_PLANKS)
                .slab(ModBlocks.JUNIPER_SLAB)
                .stairs(ModBlocks.JUNIPER_STAIRS)
                .fence(ModBlocks.JUNIPER_FENCE)
                .fenceGate(ModBlocks.JUNIPER_FENCE_GATE)
                .pressurePlate(ModBlocks.JUNIPER_PRESSURE_PLATE)
                .trapdoor(ModBlocks.JUNIPER_TRAPDOOR)
                .door(ModBlocks.JUNIPER_DOOR)
                .button(ModBlocks.JUNIPER_BUTTON)
                .sign(ModBlocks.JUNIPER_SIGN, ModBlocks.JUNIPER_WALL_SIGN).getFamily();
        blockStateModelGenerator.family(ModBlocks.JUNIPER_PLANKS).generateFor(juniperFamily);
        blockStateModelGenerator.createShelf(ModBlocks.JUNIPER_SHELF, ModBlocks.STRIPPED_JUNIPER_LOG);
        blockStateModelGenerator.createHangingSign(ModBlocks.STRIPPED_JUNIPER_LOG, ModBlocks.JUNIPER_HANGING_SIGN, ModBlocks.JUNIPER_WALL_HANGING_SIGN);
        blockStateModelGenerator.createPlantWithDefaultItem(ModBlocks.JUNIPER_SAPLING, ModBlocks.POTTED_JUNIPER_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        blockStateModelGenerator.createTrivialBlock(ModBlocks.JUNIPER_LEAVES, TexturedModel.LEAVES);

        blockStateModelGenerator.createCrossBlockWithDefaultItem(ModBlocks.CHANDELIER, BlockModelGenerators.PlantType.NOT_TINTED);

        blockStateModelGenerator.createTrivialCube(ModBlocks.CHALCEDONY_FLOOR);

        createBooleanPropertyCube(blockStateModelGenerator, ModBlocks.HEAT_STABILIZER, HeatStabilizerBlock.LIT, "lit");
        blockStateModelGenerator.createTrivialCube(ModBlocks.CHUNK_LOADER);

        blockStateModelGenerator.createTrivialBlock(ModBlocks.RED_TNT, TexturedModel.CUBE_TOP_BOTTOM);
        blockStateModelGenerator.createTrivialBlock(ModBlocks.ORANGE_TNT, TexturedModel.CUBE_TOP_BOTTOM);
        blockStateModelGenerator.createTrivialBlock(ModBlocks.YELLOW_TNT, TexturedModel.CUBE_TOP_BOTTOM);
        blockStateModelGenerator.createTrivialBlock(ModBlocks.GREEN_TNT, TexturedModel.CUBE_TOP_BOTTOM);
        blockStateModelGenerator.createTrivialBlock(ModBlocks.BLUE_TNT, TexturedModel.CUBE_TOP_BOTTOM);
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

        itemModelGenerator.generateFlatItem(ModItems.GEM_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GEM_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GEM_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GEM_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GEM_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateSpear(ModItems.GEM_SPEAR);

        if (CoreAdventures.GENERATE_ARMOR_TRIMS) {
            itemModelGenerator.generateTrimmableItem(ModItems.CHALCEDONY_HELMET, ModArmorMaterials.CHALCEDONY_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
            itemModelGenerator.generateTrimmableItem(ModItems.CHALCEDONY_CHESTPLATE, ModArmorMaterials.CHALCEDONY_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
            itemModelGenerator.generateTrimmableItem(ModItems.CHALCEDONY_LEGGINGS, ModArmorMaterials.CHALCEDONY_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
            itemModelGenerator.generateTrimmableItem(ModItems.CHALCEDONY_BOOTS, ModArmorMaterials.CHALCEDONY_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);

            itemModelGenerator.generateTrimmableItem(ModItems.GARNET_HELMET, ModArmorMaterials.GARNET_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
            itemModelGenerator.generateTrimmableItem(ModItems.GARNET_CHESTPLATE, ModArmorMaterials.GARNET_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
            itemModelGenerator.generateTrimmableItem(ModItems.GARNET_LEGGINGS, ModArmorMaterials.GARNET_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
            itemModelGenerator.generateTrimmableItem(ModItems.GARNET_BOOTS, ModArmorMaterials.GARNET_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);

            itemModelGenerator.generateTrimmableItem(ModItems.JADE_HELMET, ModArmorMaterials.JADE_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
            itemModelGenerator.generateTrimmableItem(ModItems.JADE_CHESTPLATE, ModArmorMaterials.JADE_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
            itemModelGenerator.generateTrimmableItem(ModItems.JADE_LEGGINGS, ModArmorMaterials.JADE_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
            itemModelGenerator.generateTrimmableItem(ModItems.JADE_BOOTS, ModArmorMaterials.JADE_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);

            itemModelGenerator.generateTrimmableItem(ModItems.JASPER_HELMET, ModArmorMaterials.JASPER_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
            itemModelGenerator.generateTrimmableItem(ModItems.JASPER_CHESTPLATE, ModArmorMaterials.JASPER_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
            itemModelGenerator.generateTrimmableItem(ModItems.JASPER_LEGGINGS, ModArmorMaterials.JASPER_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
            itemModelGenerator.generateTrimmableItem(ModItems.JASPER_BOOTS, ModArmorMaterials.JASPER_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);

            itemModelGenerator.generateTrimmableItem(ModItems.ONYX_HELMET, ModArmorMaterials.ONYX_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
            itemModelGenerator.generateTrimmableItem(ModItems.ONYX_CHESTPLATE, ModArmorMaterials.ONYX_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
            itemModelGenerator.generateTrimmableItem(ModItems.ONYX_LEGGINGS, ModArmorMaterials.ONYX_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
            itemModelGenerator.generateTrimmableItem(ModItems.ONYX_BOOTS, ModArmorMaterials.ONYX_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);

            itemModelGenerator.generateTrimmableItem(ModItems.OPAL_HELMET, ModArmorMaterials.OPAL_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
            itemModelGenerator.generateTrimmableItem(ModItems.OPAL_CHESTPLATE, ModArmorMaterials.OPAL_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
            itemModelGenerator.generateTrimmableItem(ModItems.OPAL_LEGGINGS, ModArmorMaterials.OPAL_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
            itemModelGenerator.generateTrimmableItem(ModItems.OPAL_BOOTS, ModArmorMaterials.OPAL_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);

            itemModelGenerator.generateTrimmableItem(ModItems.RUBY_HELMET, ModArmorMaterials.RUBY_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
            itemModelGenerator.generateTrimmableItem(ModItems.RUBY_CHESTPLATE, ModArmorMaterials.RUBY_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
            itemModelGenerator.generateTrimmableItem(ModItems.RUBY_LEGGINGS, ModArmorMaterials.RUBY_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
            itemModelGenerator.generateTrimmableItem(ModItems.RUBY_BOOTS, ModArmorMaterials.RUBY_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);

            itemModelGenerator.generateTrimmableItem(ModItems.SAPPHIRE_HELMET, ModArmorMaterials.SAPPHIRE_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
            itemModelGenerator.generateTrimmableItem(ModItems.SAPPHIRE_CHESTPLATE, ModArmorMaterials.SAPPHIRE_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
            itemModelGenerator.generateTrimmableItem(ModItems.SAPPHIRE_LEGGINGS, ModArmorMaterials.SAPPHIRE_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
            itemModelGenerator.generateTrimmableItem(ModItems.SAPPHIRE_BOOTS, ModArmorMaterials.SAPPHIRE_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);

            itemModelGenerator.generateTrimmableItem(ModItems.SPINEL_HELMET, ModArmorMaterials.SPINEL_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
            itemModelGenerator.generateTrimmableItem(ModItems.SPINEL_CHESTPLATE, ModArmorMaterials.SPINEL_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
            itemModelGenerator.generateTrimmableItem(ModItems.SPINEL_LEGGINGS, ModArmorMaterials.SPINEL_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
            itemModelGenerator.generateTrimmableItem(ModItems.SPINEL_BOOTS, ModArmorMaterials.SPINEL_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);

            itemModelGenerator.generateTrimmableItem(ModItems.TIGERS_EYE_HELMET, ModArmorMaterials.TIGERS_EYE_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
            itemModelGenerator.generateTrimmableItem(ModItems.TIGERS_EYE_CHESTPLATE, ModArmorMaterials.TIGERS_EYE_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
            itemModelGenerator.generateTrimmableItem(ModItems.TIGERS_EYE_LEGGINGS, ModArmorMaterials.TIGERS_EYE_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
            itemModelGenerator.generateTrimmableItem(ModItems.TIGERS_EYE_BOOTS, ModArmorMaterials.TIGERS_EYE_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);

            itemModelGenerator.generateTrimmableItem(ModItems.GEM_HELMET, ModArmorMaterials.GEM_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
            itemModelGenerator.generateTrimmableItem(ModItems.GEM_CHESTPLATE, ModArmorMaterials.GEM_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
            itemModelGenerator.generateTrimmableItem(ModItems.GEM_LEGGINGS, ModArmorMaterials.GEM_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
            itemModelGenerator.generateTrimmableItem(ModItems.GEM_BOOTS, ModArmorMaterials.GEM_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);

            itemModelGenerator.generateTrimmableItem(ModItems.LAVA_GOGGLES, ModArmorMaterials.LAVA_GOGGLES_ARMOR_MATERIAL_KEY, ItemModelGenerators.TRIM_PREFIX_HELMET, false);

            generateTrimModelsForVanillaArmor(itemModelGenerator);
        } else {
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

            itemModelGenerator.generateFlatItem(ModItems.LAVA_GOGGLES, ModelTemplates.FLAT_ITEM);
        }

        itemModelGenerator.generateFlatItem(ModItems.JUNIPER_BOAT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.JUNIPER_CHEST_BOAT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SPEED_BOAT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SPEED_CHEST_BOAT, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.CHALCEDONY_SHARD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GARNET_FRAGMENT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.JASPER_FRAGMENT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ONYX_FRAGMENT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.OPAL_SHARD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SAPPHIRE_FRAGMENT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.DYNAMITE, ModelTemplates.FLAT_ITEM);

        createRefugeCompass(itemModelGenerator, ModItems.REFUGE_COMPASS);

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

        itemModelGenerator.generateFlatItem(ModItems.TRADER_SENTRY_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.WARRIOR_SENTRY_SPAWN_EGG, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.MUSIC_DISC_TRUMPET1, ModelTemplates.MUSIC_DISC);
        itemModelGenerator.generateFlatItem(ModItems.MUSIC_DISC_TROMBONE1, ModelTemplates.MUSIC_DISC);
        itemModelGenerator.generateFlatItem(ModItems.MUSIC_DISC_TUBA1, ModelTemplates.MUSIC_DISC);
        itemModelGenerator.generateFlatItem(ModItems.MUSIC_DISC_ALTO_SAXOPHONE1, ModelTemplates.MUSIC_DISC);
        itemModelGenerator.generateFlatItem(ModItems.MUSIC_DISC_BASS_SAXOPHONE1, ModelTemplates.MUSIC_DISC);
        itemModelGenerator.generateFlatItem(ModItems.MUSIC_DISC_CONTRABASS1, ModelTemplates.MUSIC_DISC);
        itemModelGenerator.generateFlatItem(ModItems.MUSIC_DISC_TIMPANI1, ModelTemplates.MUSIC_DISC);

        itemModelGenerator.generateTippedArrow(ModItems.SPLASH_ARROW);
    }

    private void generateTrimModelsForVanillaArmor(ItemModelGenerators itemModelGenerator) {
        generateTrimModelsForVanillaArmor(itemModelGenerator, Items.TURTLE_HELMET, EquipmentAssets.TURTLE_SCUTE, ItemModelGenerators.TRIM_PREFIX_HELMET, false);

        generateTrimModelsForVanillaArmor(itemModelGenerator, Items.LEATHER_HELMET, EquipmentAssets.LEATHER, ItemModelGenerators.TRIM_PREFIX_HELMET, true);
        generateTrimModelsForVanillaArmor(itemModelGenerator, Items.LEATHER_CHESTPLATE, EquipmentAssets.LEATHER, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, true);
        generateTrimModelsForVanillaArmor(itemModelGenerator, Items.LEATHER_LEGGINGS, EquipmentAssets.LEATHER, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, true);
        generateTrimModelsForVanillaArmor(itemModelGenerator, Items.LEATHER_BOOTS, EquipmentAssets.LEATHER, ItemModelGenerators.TRIM_PREFIX_BOOTS, true);

        generateTrimModelsForVanillaArmor(itemModelGenerator, Items.COPPER_HELMET, EquipmentAssets.COPPER, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        generateTrimModelsForVanillaArmor(itemModelGenerator, Items.COPPER_CHESTPLATE, EquipmentAssets.COPPER, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        generateTrimModelsForVanillaArmor(itemModelGenerator, Items.COPPER_LEGGINGS, EquipmentAssets.COPPER, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        generateTrimModelsForVanillaArmor(itemModelGenerator, Items.COPPER_BOOTS, EquipmentAssets.COPPER, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);

        generateTrimModelsForVanillaArmor(itemModelGenerator, Items.CHAINMAIL_HELMET, EquipmentAssets.CHAINMAIL, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        generateTrimModelsForVanillaArmor(itemModelGenerator, Items.CHAINMAIL_CHESTPLATE, EquipmentAssets.CHAINMAIL, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        generateTrimModelsForVanillaArmor(itemModelGenerator, Items.CHAINMAIL_LEGGINGS, EquipmentAssets.CHAINMAIL, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        generateTrimModelsForVanillaArmor(itemModelGenerator, Items.CHAINMAIL_BOOTS, EquipmentAssets.CHAINMAIL, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);

        generateTrimModelsForVanillaArmor(itemModelGenerator, Items.IRON_HELMET, EquipmentAssets.IRON, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        generateTrimModelsForVanillaArmor(itemModelGenerator, Items.IRON_CHESTPLATE, EquipmentAssets.IRON, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        generateTrimModelsForVanillaArmor(itemModelGenerator, Items.IRON_LEGGINGS, EquipmentAssets.IRON, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        generateTrimModelsForVanillaArmor(itemModelGenerator, Items.IRON_BOOTS, EquipmentAssets.IRON, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);

        generateTrimModelsForVanillaArmor(itemModelGenerator, Items.DIAMOND_HELMET, EquipmentAssets.DIAMOND, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        generateTrimModelsForVanillaArmor(itemModelGenerator, Items.DIAMOND_CHESTPLATE, EquipmentAssets.DIAMOND, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        generateTrimModelsForVanillaArmor(itemModelGenerator, Items.DIAMOND_LEGGINGS, EquipmentAssets.DIAMOND, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        generateTrimModelsForVanillaArmor(itemModelGenerator, Items.DIAMOND_BOOTS, EquipmentAssets.DIAMOND, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);

        generateTrimModelsForVanillaArmor(itemModelGenerator, Items.GOLDEN_HELMET, EquipmentAssets.GOLD, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        generateTrimModelsForVanillaArmor(itemModelGenerator, Items.GOLDEN_CHESTPLATE, EquipmentAssets.GOLD, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        generateTrimModelsForVanillaArmor(itemModelGenerator, Items.GOLDEN_LEGGINGS, EquipmentAssets.GOLD, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        generateTrimModelsForVanillaArmor(itemModelGenerator, Items.GOLDEN_BOOTS, EquipmentAssets.GOLD, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);

        generateTrimModelsForVanillaArmor(itemModelGenerator, Items.NETHERITE_HELMET, EquipmentAssets.NETHERITE, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        generateTrimModelsForVanillaArmor(itemModelGenerator, Items.NETHERITE_CHESTPLATE, EquipmentAssets.NETHERITE, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        generateTrimModelsForVanillaArmor(itemModelGenerator, Items.NETHERITE_LEGGINGS, EquipmentAssets.NETHERITE, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        generateTrimModelsForVanillaArmor(itemModelGenerator, Items.NETHERITE_BOOTS, EquipmentAssets.NETHERITE, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);
    }

    private void generateTrimModelsForVanillaArmor(ItemModelGenerators itemModelGenerators, Item item, ResourceKey<EquipmentAsset> equipmentAsset, Identifier trimPrefix, boolean dyeable) {
        Identifier modelLocation = ModelLocationUtils.getModelLocation(item);
        Identifier textureLocation = TextureMapping.getItemTexture(item);
        Identifier textureOverlayLocation = dyeable ? TextureMapping.getItemTexture(item, "_overlay") : null;
        List<SelectItemModel.SwitchCase<ResourceKey<TrimMaterial>>> materialModels = new ArrayList<>(ItemModelGenerators.TRIM_MATERIAL_MODELS.size());

        for (ItemModelGenerators.TrimMaterialData materialData : ItemModelGenerators.TRIM_MATERIAL_MODELS) {
            Identifier itemTrim = modelLocation.withSuffix("_" + materialData.assets().base().suffix() + "_trim");
            Identifier armorTrim = trimPrefix.withSuffix("_" + materialData.assets().assetId(equipmentAsset).suffix());
            ItemModel.Unbaked withTrim = createConditional(itemModelGenerators, itemTrim, textureLocation, armorTrim,
                    textureOverlayLocation, isTrimMaterialFromCoread(materialData));

            materialModels.add(ItemModelUtils.when(materialData.materialKey(), withTrim));
        }

        itemModelGenerators.itemModelOutput.accept(item, ItemModelUtils.select(
                new TrimMaterialProperty(), getBaseModel(modelLocation, dyeable), materialModels));
    }

    private ItemModel.Unbaked createConditional(ItemModelGenerators itemModelGenerators, Identifier itemTrim, Identifier textureLocation, Identifier armorTrim, Identifier textureOverlayLocation, boolean createTrimModel) {
        if (textureOverlayLocation != null) {
            if (createTrimModel) {
                itemModelGenerators.generateLayeredItem(itemTrim, textureLocation, textureOverlayLocation, armorTrim);
            }
            return ItemModelUtils.tintedModel(itemTrim, new Dye(DyedItemColor.LEATHER_COLOR));
        }

        if (createTrimModel) {
            itemModelGenerators.generateLayeredItem(itemTrim, textureLocation, armorTrim);
        }
        return ItemModelUtils.plainModel(itemTrim);

    }

    private ItemModel.Unbaked getBaseModel(Identifier modelLocation, boolean dyeable) {
        if (dyeable) {
            return ItemModelUtils.tintedModel(modelLocation, new Dye(DyedItemColor.LEATHER_COLOR));
        }

        return ItemModelUtils.plainModel(modelLocation);

    }

    public static boolean isTrimMaterialFromCoread(ItemModelGenerators.TrimMaterialData material) {
        return material.materialKey().identifier().getNamespace().equals(CoreAdventures.MOD_ID);
    }

    private void createRefugeCompass(ItemModelGenerators itemModelGenerator, Item compass) {
        // Creating a custom CompassTarget required overriding an abstract package-private method and thus is not possible.
        // Therefore, refuge compass is given the CompassTarget NONE and the targetting is handled via mixin.
        itemModelGenerator.itemModelOutput.accept(compass, ItemModelUtils.rangeSelect(new CompassAngle(true, CompassAngleState.CompassTarget.NONE), 32.0f, itemModelGenerator.createCompassModels(compass)));
    }

    public void createBooleanPropertyCube(BlockModelGenerators generator, Block block, BooleanProperty property, String name) {
        MultiVariant falseVariant = BlockModelGenerators.plainVariant(TexturedModel.CUBE.create(block, generator.modelOutput));
        Identifier identifier = TextureMapping.getBlockTexture(block, "_" + name);
        MultiVariant trueVariant = BlockModelGenerators.plainVariant(TexturedModel.CUBE.get(block).updateTextures(
                textureMapping -> textureMapping.put(TextureSlot.ALL, identifier)).createWithSuffix(block, "_" + name, generator.modelOutput));
        generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(block).with(BlockModelGenerators.createBooleanModelDispatch(property, trueVariant, falseVariant)));
    }

    @Override
    public @NonNull String getName() {
        return "Model provider";
    }
}
