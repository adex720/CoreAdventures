package com.adex.util;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class Util {

    public static Identifier getIdentifier(Item item) {
        return BuiltInRegistries.ITEM.wrapAsHolder(item).unwrapKey().map(ResourceKey::identifier).orElse(null);
    }

}
