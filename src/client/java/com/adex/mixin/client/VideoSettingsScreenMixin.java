package com.adex.mixin.client;

import com.adex.option.ModOptions;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.client.gui.screens.options.VideoSettingsScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;

@Mixin(VideoSettingsScreen.class)
public class VideoSettingsScreenMixin {

    @Inject(at = @At("RETURN"), method = "preferenceOptions", cancellable = true)
    private static void addCoreAdventuresSettings(Options options, CallbackInfoReturnable<OptionInstance<?>[]> cir) {
        int baseLength = cir.getReturnValue().length;
        OptionInstance<?>[] optionArray = Arrays.copyOf(cir.getReturnValue(), baseLength + 1);
        optionArray[baseLength] = ModOptions.getModOptions().heatBarOffset();
        cir.setReturnValue(optionArray);
    }

}
