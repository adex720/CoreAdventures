package com.adex.enchantment.effect;

import com.adex.CoreAdventures;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModEnchantmentEffects {

    public static final ResourceKey<Enchantment> CHAINING = createRegistryKey("chaining");
    public static MapCodec<BreakMultipleEnchantmentEffect> BREAK_MULTIPLE_EFFECT = register("break_multiple_effect", BreakMultipleEnchantmentEffect.CODEC);

    private static ResourceKey<Enchantment> createRegistryKey(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name);
        return ResourceKey.create(Registries.ENCHANTMENT, id);
    }

    private static <T extends BreakMultipleEnchantmentEffect> MapCodec<T> register(String name, MapCodec<T> codec) {
        return Registry.register(BuiltInRegistries.ENCHANTMENT_VALUE_EFFECT_TYPE, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name), codec);
    }

    public static void initialize() {
    }
}
