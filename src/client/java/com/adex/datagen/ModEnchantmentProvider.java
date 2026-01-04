package com.adex.datagen;

import com.adex.enchantment.effect.BreakMultipleEnchantmentEffect;
import com.adex.enchantment.effect.ModEnchantmentEffectComponents;
import com.adex.enchantment.effect.ModEnchantmentEffects;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.LevelBasedValue;

import java.util.concurrent.CompletableFuture;

public class ModEnchantmentProvider extends FabricDynamicRegistryProvider {

    public ModEnchantmentProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        register(entries, ModEnchantmentEffects.CHAINING, Enchantment.enchantment(Enchantment.definition(
                        registries.lookupOrThrow(Registries.ITEM).getOrThrow(ItemTags.PICKAXES),
                        2,
                        3,
                        Enchantment.dynamicCost(15, 9),
                        Enchantment.dynamicCost(65, 9),
                        4,
                        EquipmentSlotGroup.HAND))
                .withEffect(ModEnchantmentEffectComponents.BREAK_MULTIPLE, new BreakMultipleEnchantmentEffect(LevelBasedValue.perLevel(1.0f))));
    }

    private void register(Entries entries, ResourceKey<Enchantment> key, Enchantment.Builder builder, ResourceCondition... resourceConditions) {
        entries.add(key, builder.build(key.identifier()), resourceConditions);
    }

    @Override
    public String getName() {
        return "Enchantment provider";
    }
}
