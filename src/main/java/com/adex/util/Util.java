package com.adex.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Collection;
import java.util.Set;

public class Util {

    public static Identifier getIdentifier(Item item) {
        return BuiltInRegistries.ITEM.wrapAsHolder(item).unwrapKey().map(ResourceKey::identifier).orElse(null);
    }

    public static <T> T removeRandomElement(Set<T> set, RandomSource random) {
        if (set.isEmpty()) return null;

        T element = set.stream().toList().get(random.nextInt(set.size()));
        set.remove(element);
        return element;
    }

}
