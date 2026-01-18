package com.adex.mixin;

import net.minecraft.server.level.WorldGenRegion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.function.Supplier;

/**
 * This mixin allows refuge structure pieces to not be limited inside a single chunk
 */
@Mixin(WorldGenRegion.class)
public class WorldGenRegionMixin {

    @Shadow
    private Supplier<String> currentlyGenerating;

    @ModifyVariable(at = @At(value = "STORE"), method = "ensureCanWrite", ordinal = 2)
    private int increaseXRange(int value) {
        if (currentlyGenerating == null || !currentlyGenerating.get().equals("ResourceKey[minecraft:worldgen/structure / coread:refuge]"))
            return value;
        return value <= 0 ? value : value - 0;
    }

    @ModifyVariable(at = @At(value = "STORE"), method = "ensureCanWrite", ordinal = 3)
    private int increaseZRange(int value) {
        if (currentlyGenerating == null || !currentlyGenerating.get().equals("ResourceKey[minecraft:worldgen/structure / coread:refuge]"))
            return value;
        return value <= 0 ? value : value - 0;
    }
}
