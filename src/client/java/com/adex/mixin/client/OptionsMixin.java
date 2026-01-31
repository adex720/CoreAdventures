package com.adex.mixin.client;

import com.adex.option.ModOptions;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.nbt.CompoundTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;
import java.io.PrintWriter;

@Mixin(Options.class)
public class OptionsMixin {

    @Inject(at = @At(value = "HEAD"), method = "<init>")
    private static void createCoreAdventuresSettings(Minecraft minecraft, File file, CallbackInfo ci) {
        ModOptions.createModOptions(minecraft);
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/KeyMapping;resetMapping()V"), method = "load")
    private void loadCoreAdventuresSettings(CallbackInfo ci, @Local(ordinal = 1) CompoundTag compoundTag) {
        ModOptions.getModOptions().load(compoundTag);
    }

    @Inject(at = @At(value = "INVOKE", target = "Ljava/io/PrintWriter;close()V"), method = "save")
    private void saveCoreAdventuresSettings(CallbackInfo ci, @Local PrintWriter printWriter) {
        ModOptions.getModOptions().save(printWriter);
    }
}
