package com.adex.enchantment.effect;

import com.adex.CoreAdventures;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;

public class ModEnchantmentEffects {

    public static final ResourceKey<Enchantment> CHAINING = createRegistryKey("chaining");
    public static MapCodec<BreakMultipleEnchantmentEffect> BREAK_MULTIPLE_EFFECT = register("break_multiple_effect", BreakMultipleEnchantmentEffect.CODEC);

    public static final ResourceKey<Enchantment> HEAT_PROTECTION = createRegistryKey("heat_protection");
    public static MapCodec<HeatProtectionEnchantmentEffect> HEAT_PROTECTION_EFFECT = register("heat_protection", HeatProtectionEnchantmentEffect.CODEC);

    private static ResourceKey<Enchantment> createRegistryKey(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name);
        return ResourceKey.create(Registries.ENCHANTMENT, id);
    }

    private static <T extends EnchantmentValueEffect> MapCodec<T> register(String name, MapCodec<T> codec) {
        return Registry.register(BuiltInRegistries.ENCHANTMENT_VALUE_EFFECT_TYPE, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name), codec);
    }

    public static void initialize() {
    }
}
