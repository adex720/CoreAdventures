package com.adex.mixin;

import com.adex.data.dimension.ModDimensions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BaseFireBlock.class)
public class PortalDimensionMixin {
    @Inject(at = @At("HEAD"), method = "inPortalDimension", cancellable = true)
    private static void inCustomPortalDimension(Level level, CallbackInfoReturnable<Boolean> cir) {
        if (level.dimension() == ModDimensions.CORE) {
            cir.setReturnValue(true);
            cir.cancel();
        }
    }
}
