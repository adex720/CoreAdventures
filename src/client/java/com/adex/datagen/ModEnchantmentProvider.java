package com.adex.datagen;

import com.adex.enchantment.effect.BreakMultipleEnchantmentEffect;
import com.adex.enchantment.effect.ModEnchantmentEffectComponents;
import com.adex.enchantment.effect.ModEnchantmentEffects;
import com.adex.entity.attribute.ModAttributes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentAttributeEffect;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModEnchantmentProvider extends FabricDynamicRegistryProvider {

    public ModEnchantmentProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, @NonNull Entries entries) {
        HolderGetter<Item> itemRegistry = registries.lookupOrThrow(Registries.ITEM);
        HolderGetter<Enchantment> enchantmentRegistry = registries.lookupOrThrow(Registries.ENCHANTMENT);

        register(entries, ModEnchantmentEffects.CHAINING, Enchantment.enchantment(Enchantment.definition(
                        itemRegistry.getOrThrow(ItemTags.PICKAXES),
                        2,
                        3,
                        Enchantment.dynamicCost(15, 9),
                        Enchantment.dynamicCost(65, 9),
                        4,
                        EquipmentSlotGroup.HAND))
                .exclusiveWith(enchantmentRegistry.getOrThrow(EnchantmentTags.MINING_EXCLUSIVE))
                .withEffect(ModEnchantmentEffectComponents.BREAK_MULTIPLE, new BreakMultipleEnchantmentEffect(LevelBasedValue.perLevel(1.0f))));

        register(entries, ModEnchantmentEffects.HEAT_PROTECTION, Enchantment.enchantment(Enchantment.definition(
                        itemRegistry.getOrThrow(ItemTags.ARMOR_ENCHANTABLE),
                        5,
                        4,
                        Enchantment.dynamicCost(10, 8),
                        Enchantment.dynamicCost(18, 8),
                        2,
                        EquipmentSlotGroup.ARMOR))
                .exclusiveWith(enchantmentRegistry.getOrThrow(EnchantmentTags.ARMOR_EXCLUSIVE))
                .withEffect(EnchantmentEffectComponents.ATTRIBUTES, new EnchantmentAttributeEffect(
                        Identifier.fromNamespaceAndPath(ModDataGenerator.MOD_ID, "enchantment.heat_protection"),
                        ModAttributes.HEAT_PROTECTION,
                        LevelBasedValue.perLevel(0.2f),
                        AttributeModifier.Operation.ADD_VALUE)));
    }

    private void register(Entries entries, ResourceKey<Enchantment> key, Enchantment.Builder builder, ResourceCondition... resourceConditions) {
        entries.add(key, builder.build(key.identifier()), resourceConditions);
    }

    @Override
    public String getName() {
        return "Enchantment provider";
    }
}
