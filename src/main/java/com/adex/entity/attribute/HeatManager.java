package com.adex.entity.attribute;

import com.adex.CoreAdventures;
import com.adex.advancement.criterion.ModCriterionTriggers;
import com.adex.data.damagetype.ModDamageTypes;
import com.adex.data.dimension.ModDimensions;
import com.adex.item.ModDataComponents;
import com.adex.item.armor.ModArmorMaterials;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorMaterials;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.HashSet;
import java.util.Set;

public class HeatManager {

    public static final double DEFAULT_HEATING_RATE = 0.05d; // maybe 0.1d
    public static final int BASE_HEAT_RESISTANCE = 10;

    public static final double TOLERANCE = 100.0d;

    public static final Identifier HEAT_AMOUNT = Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "heat");

    private static final Set<String> IGNORE = new HashSet<>();

    @SuppressWarnings("ConstantConditions")
    public static void serverHeatTick(ServerPlayer player, MinecraftServer server) {
        if (player.getAttribute(ModAttributes.HEAT) == null) {
            if (IGNORE.add(player.getPlainTextName())) {
                CoreAdventures.LOGGER.error("Player {} has no attribute HEAT, ignoring\nPossible cause: another mod overriding player attribute registration", player.getName());
            }
            return;
        }

        if (player.gameMode() == GameType.CREATIVE || player.gameMode() == GameType.SPECTATOR) {
            player.getAttribute(ModAttributes.HEAT).removeModifier(HEAT_AMOUNT);
            return;
        }

        Level level = player.level();
        if (level.dimension() != ModDimensions.CORE) {
            addHeat(player, -DEFAULT_HEATING_RATE / BASE_HEAT_RESISTANCE);

            return;
        }

        double newValue = addHeat(player, DEFAULT_HEATING_RATE / getHeatResistance(player));
        double reduction = 0.0d; // How much heat to reduce for taken damage

        int damage = Math.max(0, (int) ((newValue - TOLERANCE) / 10));
        if (damage > 0) {
            reduction -= damage * 10.0d;
            DamageSource damageSource = new DamageSource(server.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).get(ModDamageTypes.HEAT_DAMAGE.identifier()).orElseThrow());
            player.hurtServer(player.level(), damageSource, damage);
        }

        addHeat(player, reduction);
    }

    /**
     * Apply heat for a player.
     * Takes heat resistance into account.
     * Deals required damage if needed.
     * Exact formula is amount * BASE_HEAT_RESISTANCE / getHeatResistance(player)
     *
     * @param player ServerPlayer
     * @param amount Amount of heat to add
     */
    public static void heat(ServerPlayer player, double amount) {
        AttributeInstance instance = player.getAttribute(ModAttributes.HEAT);
        if (instance == null) return;

        double newValue = addHeat(player, amount * BASE_HEAT_RESISTANCE / getHeatResistance(player));
        double reduction = 0.0d; // How much heat to reduce for taken damage

        int damage = Math.max(0, (int) ((newValue - TOLERANCE) / 10));
        if (damage > 0) {
            reduction -= damage * 10.0d;
            DamageSource damageSource = new DamageSource(player.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).get(ModDamageTypes.HEAT_DAMAGE.identifier()).orElseThrow());
            player.hurtServer(player.level(), damageSource, damage);
        }

        addHeat(player, reduction);
    }

    /**
     * Does not check for heat resistance
     */
    @SuppressWarnings("ConstantConditions")
    public static double addHeat(Player player, double amount) {
        double oldValue = player.getAttributeValue(ModAttributes.HEAT);
        double newValue = oldValue + amount;

        if (newValue <= 0.0d) {
            player.getAttribute(ModAttributes.HEAT).removeModifier(HEAT_AMOUNT);
        } else {
            AttributeModifier modifier = new AttributeModifier(HEAT_AMOUNT, newValue, AttributeModifier.Operation.ADD_VALUE);
            player.getAttribute(ModAttributes.HEAT).addOrReplacePermanentModifier(modifier);
        }

        return newValue;
    }

    public static double getHeatResistance(ServerPlayer player) {
        double resistance = BASE_HEAT_RESISTANCE + player.getAttributeValue(ModAttributes.HEAT_PROTECTION);
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
                || armorMaterial == ModArmorMaterials.SPINEL_ARMOR_MATERIAL || armorMaterial == ModArmorMaterials.TIGERS_EYE_ARMOR_MATERIAL
                || armorMaterial == ModArmorMaterials.GEM_ARMOR_MATERIAL)
            return 4;

        return 0;
    }

    public static void applyCooling(Level level, BlockPos pos, BlockState state) {
        Block block = state.getBlock();
        double cooling = -1.0d;
        if (block == Blocks.ICE) cooling = 2.0d;
        else if (block == Blocks.PACKED_ICE) cooling = 20.0d;
        else if (block == Blocks.BLUE_ICE) cooling = 200.0d;

        if (cooling < 0.0d) return;
        applyCooling(level, pos.getCenter(), cooling, state);
        level.destroyBlock(pos, false);
    }

    public static void applyCooling(Level level, Vec3 pos, double strength, BlockState state) {
        for (Player player : level.players()) {
            double distance = player.position().add(Direction.UP.getUnitVec3()).distanceTo(pos);
            if (distance >= 2.0d) distance = Math.pow(distance - 2.0d, 3.0d) + 2.0d;
            double cooling = strength / distance;
            if (cooling > 0.1d) {
                if (player.getAttributeValue(ModAttributes.HEAT) > 0.0d) {
                    addHeat(player, -cooling);
                    if (player instanceof ServerPlayer serverPlayer) {
                        ModCriterionTriggers.COOL_WITH_ICE.trigger(serverPlayer, state);
                    }
                }
            }
        }
    }

    public static boolean preventsNaturalRegeneration(ServerPlayer player) {
        return player.getAttributeValue(ModAttributes.HEAT) >= HeatManager.TOLERANCE;
    }

}
