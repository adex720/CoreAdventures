package com.adex.entity.attribute;

import com.adex.CoreAdventures;
import com.adex.data.damagetype.ModDamageTypes;
import com.adex.data.dimension.ModDimensions;
import com.adex.item.ModDataComponents;
import com.adex.item.armor.ModArmorMaterials;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorMaterials;

public class HeatManager {

    public static final double DEFAULT_HEATING_RATE = 0.5d; // maybe 0.1d
    public static final int BASE_HEAT_RESISTANCE = 10;

    public static final Identifier HEAT_AMOUNT = Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "heat");

    public static void serverHeatTick(ServerPlayer player, MinecraftServer server) {
        if (player.level().dimension() != ModDimensions.CORE) {
            player.getAttribute(ModAttributes.HEAT).removeModifier(HEAT_AMOUNT);
            return;
        }

        double oldValue = player.getAttributeValue(ModAttributes.HEAT);
        double newValue = oldValue + DEFAULT_HEATING_RATE / getHeatResistance(player);

        int damage = Math.max(0, (int) ((newValue - 100d) / 10));
        if (damage > 0) {
            newValue -= damage * 10.0d;
            DamageSource damageSource = new DamageSource(server.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).get(ModDamageTypes.HEAT_DAMAGE.identifier()).get());
            player.hurtServer(player.level(), damageSource, damage);
        }

        AttributeModifier modifier = new AttributeModifier(HEAT_AMOUNT, newValue, AttributeModifier.Operation.ADD_VALUE);
        player.getAttribute(ModAttributes.HEAT).addOrReplacePermanentModifier(modifier);
    }

    public static int getHeatResistance(ServerPlayer player) {
        int resistance = BASE_HEAT_RESISTANCE;
        resistance += getHeatResistance(player.getItemBySlot(EquipmentSlot.FEET));
        resistance += getHeatResistance(player.getItemBySlot(EquipmentSlot.LEGS));
        resistance += getHeatResistance(player.getItemBySlot(EquipmentSlot.CHEST));
        resistance += getHeatResistance(player.getItemBySlot(EquipmentSlot.HEAD));
        return resistance;
    }

    public static int getHeatResistance(ItemStack itemStack) {
        if (itemStack.isEmpty()) return 0;
        Item item = itemStack.getItem();
        Integer resistance = item.components().get(ModDataComponents.HEAT_RESISTANCE);
        return resistance != null ? resistance : 0;
    }

    public static int getHeatResistance(ArmorMaterial armorMaterial) {
        if (armorMaterial == ArmorMaterials.COPPER || armorMaterial == ArmorMaterials.IRON || armorMaterial == ArmorMaterials.GOLD)
            return -1;

        if (armorMaterial == ArmorMaterials.LEATHER || armorMaterial == ArmorMaterials.CHAINMAIL) return 1;
        if (armorMaterial == ArmorMaterials.NETHERITE || armorMaterial == ArmorMaterials.ARMADILLO_SCUTE || armorMaterial == ArmorMaterials.TURTLE_SCUTE)
            return 2;
        if (armorMaterial == ArmorMaterials.DIAMOND) return 3;
        if (armorMaterial == ModArmorMaterials.CHALCEDONY_ARMOR_MATERIAL || armorMaterial == ModArmorMaterials.GARNET_ARMOR_MATERIAL
                || armorMaterial == ModArmorMaterials.JADE_ARMOR_MATERIAL || armorMaterial == ModArmorMaterials.JASPER_ARMOR_MATERIAL
                || armorMaterial == ModArmorMaterials.ONYX_ARMOR_MATERIAL || armorMaterial == ModArmorMaterials.OPAL_ARMOR_MATERIAL
                || armorMaterial == ModArmorMaterials.RUBY_ARMOR_MATERIAL || armorMaterial == ModArmorMaterials.SAPPHIRE_ARMOR_MATERIAL
                || armorMaterial == ModArmorMaterials.SPINEL_ARMOR_MATERIAL || armorMaterial == ModArmorMaterials.TIGERS_EYE_ARMOR_MATERIAL)
            return 4;

        return 0;
    }

}
