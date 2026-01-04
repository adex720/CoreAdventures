package com.adex.enchantment.effect;

import com.adex.CoreAdventures;
import net.minecraft.core.*;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.enchantment.ConditionalEffect;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.function.UnaryOperator;

@SuppressWarnings("SameParameterValue")
public class ModEnchantmentEffectComponents {

    public static final DataComponentType<List<ConditionalEffect<EnchantmentValueEffect>>> BREAK_MULTIPLE = register("break_multiple",
            builder -> builder.persistent(ConditionalEffect.codec(EnchantmentValueEffect.CODEC, LootContextParamSets.ENCHANTED_ITEM).listOf()));

    private static <T> DataComponentType<T> register(String name, UnaryOperator<DataComponentType.Builder<T>> unaryOperator) {
        return Registry.register(BuiltInRegistries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name), unaryOperator.apply(DataComponentType.builder()).build());
    }

    public static void initialize() {
    }
}
