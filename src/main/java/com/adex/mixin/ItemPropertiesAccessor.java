package com.adex.mixin;

import net.minecraft.core.component.DataComponentInitializers;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Item.Properties.class)
public interface ItemPropertiesAccessor {

    @Accessor("componentInitializer")
    DataComponentInitializers.Initializer<Item> coread$getComponents();
}
