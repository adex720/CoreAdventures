package com.adex.mixin.client;

import com.adex.HolderReference;
import com.adex.data.structure.ModStructures;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

/**
 * For some reason the current Minecraft version doesn't allow mods to register custom structures, only structure types.
 * However, advancement generation requires the structure to be registered for it to be used.
 * Therefore, this mixin will return a holder for {@link ModStructures#REFUGE_DUMMY} when its key is looked for.
 * If in a newer minecraft version it becomes possible to register custom structures,
 * that should be done and this mixin should be deleted.
 */
@Mixin(HolderGetter.class)
public interface HolderGetterMixin {

    @Shadow
    <T> Optional<HolderSet.Named<T>> get(ResourceKey<T> resourceKey);

    @Inject(at = @At("HEAD"), method = "getOrThrow(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/core/Holder$Reference;", cancellable = true)
    private <T> void getOrThrowRefuge(ResourceKey<T> resourceKey, CallbackInfoReturnable<Holder.Reference<T>> cir) {
        if (get(resourceKey).isEmpty() && resourceKey == ModStructures.REFUGE_KEY)
            cir.setReturnValue(new HolderReference(resourceKey, ModStructures.REFUGE_DUMMY));
    }
}
