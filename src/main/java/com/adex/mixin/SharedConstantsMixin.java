package com.adex.mixin;

import com.adex.CoreAdventures;
import net.minecraft.SharedConstants;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SharedConstants.class)
public class SharedConstantsMixin {

    @Shadow
    @Mutable
    @Final
    public static boolean DEBUG_DEV_COMMANDS;

    @Inject(at = @At("TAIL"), method = "<clinit>")
    private static void updateConstants(CallbackInfo ci) {
        if (CoreAdventures.ENABLE_DEV_COMMANDS) DEBUG_DEV_COMMANDS = true;
    }
}
