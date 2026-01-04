package com.adex.enchantment.effect;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;
import org.jspecify.annotations.NonNull;

public record HeatProtectionEnchantmentEffect(LevelBasedValue amount) implements EnchantmentValueEffect {

    public static final MapCodec<HeatProtectionEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(LevelBasedValue.CODEC.fieldOf("amount").forGetter(HeatProtectionEnchantmentEffect::amount)).apply(instance, HeatProtectionEnchantmentEffect::new));

    @Override
    public float process(int i, RandomSource randomSource, float f) {
        return i;
    }

    @Override
    public @NonNull MapCodec<? extends EnchantmentValueEffect> codec() {
        return CODEC;
    }
}
