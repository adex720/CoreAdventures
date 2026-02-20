package com.adex.mixin;

import net.minecraft.resources.ResourceKey;
import net.minecraft.server.commands.SpawnArmorTrimsCommand;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;
import java.util.function.ToIntFunction;

@Mixin(SpawnArmorTrimsCommand.class)
public interface SpawnArmorTrimsCommandAccessor {

    @Mutable
    @Accessor("VANILLA_TRIM_MATERIALS")
    static List<ResourceKey<TrimMaterial>> coread$getTrimMaterials() {
        throw new AssertionError();
    }

    @Mutable
    @Accessor("VANILLA_TRIM_MATERIALS")
    static void coread$setTrimMaterials(List<ResourceKey<TrimMaterial>> materials) {
        throw new AssertionError();
    }

    @Mutable
    @Accessor("TRIM_MATERIAL_ORDER")
    static void coread$setTrimMaterialOrder(ToIntFunction<ResourceKey<TrimMaterial>> materials) {
        throw new AssertionError();
    }

}
