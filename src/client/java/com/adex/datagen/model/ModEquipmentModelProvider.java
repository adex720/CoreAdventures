package com.adex.datagen.model;

import com.adex.item.armor.ModArmorMaterials;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;

public class ModEquipmentModelProvider extends AbstractEquipmentModelProvider {

    public ModEquipmentModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildTextures() {
        generateEquipment(ModArmorMaterials.CHALCEDONY_ARMOR_MATERIAL);
        generateEquipment(ModArmorMaterials.GARNET_ARMOR_MATERIAL);
        generateEquipment(ModArmorMaterials.JADE_ARMOR_MATERIAL);
        generateEquipment(ModArmorMaterials.JASPER_ARMOR_MATERIAL);
        generateEquipment(ModArmorMaterials.ONYX_ARMOR_MATERIAL);
        generateEquipment(ModArmorMaterials.OPAL_ARMOR_MATERIAL);
        generateEquipment(ModArmorMaterials.RUBY_ARMOR_MATERIAL);
        generateEquipment(ModArmorMaterials.SAPPHIRE_ARMOR_MATERIAL);
        generateEquipment(ModArmorMaterials.SPINEL_ARMOR_MATERIAL);
        generateEquipment(ModArmorMaterials.TIGERS_EYE_ARMOR_MATERIAL);
        generateEquipment(ModArmorMaterials.GEM_ARMOR_MATERIAL);
        generateEquipment(ModArmorMaterials.LAVA_GOGGLES_ARMOR_MATERIAL);
    }
}
