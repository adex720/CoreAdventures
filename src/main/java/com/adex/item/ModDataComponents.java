package com.adex.item;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.util.ExtraCodecs;

import java.util.function.UnaryOperator;

public class ModDataComponents {

    public static final DataComponentType<Integer> HEAT_RESISTANCE = register("heat_resistance",
            builder -> builder.persistent(ExtraCodecs.intRange(-10, 10)).networkSynchronized(ByteBufCodecs.VAR_INT));

    private static <T> DataComponentType<T> register(String string, UnaryOperator<DataComponentType.Builder<T>> unaryOperator) {
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, string, unaryOperator.apply(DataComponentType.builder()).build());
    }

    public static void initialize() {

    }
}
