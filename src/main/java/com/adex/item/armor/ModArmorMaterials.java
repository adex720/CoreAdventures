package com.adex.item.armor;

import com.adex.CoreAdventures;
import com.adex.data.tag.ModTags;
import com.adex.item.ModDataComponents;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ModArmorMaterials {

    public static final int GEM_ARMOR_BASE_DURABILITY = 33;
    public static final int GEM_ARMOR_ENCHANTABILITY = 30;
    public static final float GEM_ARMOR_TOUGHNESS = 3.0f;
    public static final float GEM_ARMOR_KNOCKBACK_RESISTANCE = 0.0f;


    public static final ResourceKey<EquipmentAsset> CHALCEDONY_ARMOR_MATERIAL_KEY = ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "chalcedony"));
    public static final ResourceKey<EquipmentAsset> GARNET_ARMOR_MATERIAL_KEY = ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "garnet"));
    public static final ResourceKey<EquipmentAsset> JADE_ARMOR_MATERIAL_KEY = ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "jade"));
    public static final ResourceKey<EquipmentAsset> JASPER_ARMOR_MATERIAL_KEY = ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "jasper"));
    public static final ResourceKey<EquipmentAsset> ONYX_ARMOR_MATERIAL_KEY = ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "onyx"));
    public static final ResourceKey<EquipmentAsset> OPAL_ARMOR_MATERIAL_KEY = ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "opal"));
    public static final ResourceKey<EquipmentAsset> RUBY_ARMOR_MATERIAL_KEY = ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "ruby"));
    public static final ResourceKey<EquipmentAsset> SAPPHIRE_ARMOR_MATERIAL_KEY = ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "sapphire"));
    public static final ResourceKey<EquipmentAsset> SPINEL_ARMOR_MATERIAL_KEY = ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "spinel"));
    public static final ResourceKey<EquipmentAsset> TIGERS_EYE_ARMOR_MATERIAL_KEY = ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "tigers_eye"));
    public static final ResourceKey<EquipmentAsset> GEM_ARMOR_MATERIAL_KEY = ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "gem"));

    public static final ArmorMaterial CHALCEDONY_ARMOR_MATERIAL = createGemArmorMaterial(CHALCEDONY_ARMOR_MATERIAL_KEY, ModTags.REPAIRS_CHALCEDONY_ARMOR);
    public static final ArmorMaterial GARNET_ARMOR_MATERIAL = createGemArmorMaterial(GARNET_ARMOR_MATERIAL_KEY, ModTags.REPAIRS_GARNET_ARMOR);
    public static final ArmorMaterial JADE_ARMOR_MATERIAL = createGemArmorMaterial(JADE_ARMOR_MATERIAL_KEY, ModTags.REPAIRS_JADE_ARMOR);
    public static final ArmorMaterial JASPER_ARMOR_MATERIAL = createGemArmorMaterial(JASPER_ARMOR_MATERIAL_KEY, ModTags.REPAIRS_JASPER_ARMOR);
    public static final ArmorMaterial ONYX_ARMOR_MATERIAL = createGemArmorMaterial(ONYX_ARMOR_MATERIAL_KEY, ModTags.REPAIRS_ONYX_ARMOR);
    public static final ArmorMaterial OPAL_ARMOR_MATERIAL = createGemArmorMaterial(OPAL_ARMOR_MATERIAL_KEY, ModTags.REPAIRS_OPAL_ARMOR);
    public static final ArmorMaterial RUBY_ARMOR_MATERIAL = createGemArmorMaterial(RUBY_ARMOR_MATERIAL_KEY, ModTags.REPAIRS_RUBY_ARMOR);
    public static final ArmorMaterial SAPPHIRE_ARMOR_MATERIAL = createGemArmorMaterial(SAPPHIRE_ARMOR_MATERIAL_KEY, ModTags.REPAIRS_SAPPHIRE_ARMOR);
    public static final ArmorMaterial SPINEL_ARMOR_MATERIAL = createGemArmorMaterial(SPINEL_ARMOR_MATERIAL_KEY, ModTags.REPAIRS_SPINEL_ARMOR);
    public static final ArmorMaterial TIGERS_EYE_ARMOR_MATERIAL = createGemArmorMaterial(TIGERS_EYE_ARMOR_MATERIAL_KEY, ModTags.REPAIRS_TIGERS_EYE_ARMOR);
    public static final ArmorMaterial GEM_ARMOR_MATERIAL = createGemArmorMaterial(GEM_ARMOR_MATERIAL_KEY, ModTags.REPAIRS_GEM_ARMOR);


    public static ArmorMaterial createGemArmorMaterial(ResourceKey<EquipmentAsset> material, TagKey<Item> repair) {
        return new ArmorMaterial(GEM_ARMOR_BASE_DURABILITY,
                Map.of(ArmorType.HELMET, 3, ArmorType.CHESTPLATE, 8, ArmorType.LEGGINGS, 6, ArmorType.BOOTS, 3),
                GEM_ARMOR_ENCHANTABILITY, SoundEvents.ARMOR_EQUIP_DIAMOND, GEM_ARMOR_TOUGHNESS, GEM_ARMOR_KNOCKBACK_RESISTANCE, repair, material);
    }

    public static void initialize() {
    }

    public static Function<Item.Properties, Item> chalcedonyProtectiveArmor() {
        return (properties) -> new ProtectiveArmor(properties, ModTags.CHALCEDONY_PROTECTIVE_ARMOR, 2.0f);
    }

    public static Function<Item.Properties, Item> jasperProtectiveArmor() {
        return (properties) -> new ProtectiveArmor(properties, ModTags.JASPER_PROTECTIVE_ARMOR, 1.5f);
    }

    public static Function<Item.Properties, Item> spinelProtectiveArmor() {
        return (properties) -> new ProtectiveArmor(properties, ModTags.SPINEL_PROTECTIVE_ARMOR, 1.5f);
    }

    public static Function<Item.Properties, Item> tigersEyeProtectiveArmor() {
        return (properties) -> new ProtectiveArmor(properties, ModTags.TIGERS_EYE_PROTECTIVE_ARMOR, 1.5f);
    }

    public static Function<Item.Properties, Item> gemProtectiveArmor() {
        return (properties) -> new ProtectiveArmor(properties, ModTags.GEM_PROTECTIVE_ARMOR, 0.3f, ModTags.CHALCEDONY_PROTECTIVE_ARMOR, 0.4f);
    }

    public static Function<Item.Properties, Item> opalPotionResistance() {
        return properties -> new Item(properties.component(ModDataComponents.POTION_RESISTANCE, 0.25f));
    }

    public static ItemAttributeModifiers.Entry createModifier(Holder<Attribute> attribute, double amount, ArmorType armorType) {
        Identifier identifier = Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "armor." + armorType.getName());
        EquipmentSlotGroup equipmentSlotGroup = EquipmentSlotGroup.bySlot(armorType.getSlot());
        return new ItemAttributeModifiers.Entry(attribute, new AttributeModifier(identifier, amount, AttributeModifier.Operation.ADD_VALUE), equipmentSlotGroup);
    }

    public static ItemAttributeModifiers jadeArmorAttributes(ArmorType armorType) {
        return new ItemAttributeModifiers(List.of(createModifier(Attributes.KNOCKBACK_RESISTANCE, 0.1d, armorType)));
    }

    public static ItemAttributeModifiers onyxArmorAttributes(ArmorType armorType) {
        return new ItemAttributeModifiers(List.of(createModifier(Attributes.ATTACK_DAMAGE, 0.05d, armorType)));
    }

    public static ItemAttributeModifiers sapphireArmorAttributes(ArmorType armorType) {
        return new ItemAttributeModifiers(List.of(
                createModifier(Attributes.MOVEMENT_SPEED, 0.02d, armorType),
                createModifier(Attributes.SNEAKING_SPEED, 0.01d, armorType)));
    }

    public static ItemAttributeModifiers gemArmorAttributes(ArmorType armorType) {
        return new ItemAttributeModifiers(List.of(
                createModifier(Attributes.KNOCKBACK_RESISTANCE, 0.02d, armorType),
                createModifier(Attributes.ATTACK_DAMAGE, 0.01d, armorType),
                createModifier(Attributes.MOVEMENT_SPEED, 0.004d, armorType),
                createModifier(Attributes.SNEAKING_SPEED, 0.002d, armorType)));
    }

}
