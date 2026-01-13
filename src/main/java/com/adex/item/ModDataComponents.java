package com.adex.item;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.util.ExtraCodecs;

import java.util.function.UnaryOperator;

public class ModDataComponents {

    public static final DataComponentType<Float> HEAT_RESISTANCE = register("heat_resistance",
            builder -> builder.persistent(ExtraCodecs.floatRange(-10.0f, 10.0f)).networkSynchronized(ByteBufCodecs.FLOAT));
    public static final DataComponentType<Float> POTION_RESISTANCE = register("potion_resistance",
            builder -> builder.persistent(ExtraCodecs.floatRange(0.0f, 10.0f)).networkSynchronized(ByteBufCodecs.FLOAT));
    public static final DataComponentType<Float> REGENERATION_BOOST = register("regeneration_boost",
            builder -> builder.persistent(ExtraCodecs.floatRange(0.0f, 10.0f)).networkSynchronized(ByteBufCodecs.FLOAT));

    private static <T> DataComponentType<T> register(String string, UnaryOperator<DataComponentType.Builder<T>> unaryOperator) {
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, string, unaryOperator.apply(DataComponentType.builder()).build());
    }

    public static void initialize() {
    }
}
