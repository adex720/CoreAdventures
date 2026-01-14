package com.adex.mixin.client;

import com.mojang.serialization.Lifecycle;
import net.minecraft.client.gui.screens.worldselection.WorldOpenFlows;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/**
 * Without this mixin Minecraft would ask if we are sure every time when loading or creating a world.
 * This is because custom biomes are experimental features.
 * The actual value of the LifeCycle is not modified.
 */
@Mixin(WorldOpenFlows.class)
public class WorldOpenFlowsMixin {

    @ModifyVariable(at = @At("STORE"), method = "openWorldCheckWorldStemCompatibility", ordinal = 1)
    private boolean skipAskingWhenLoadingWorld(boolean bl) {
        return false;
    }

    @ModifyVariable(at = @At("HEAD"), method = "confirmWorldCreation", argsOnly = true)
    private static Lifecycle skipAskingWhenCreatingWorld(Lifecycle lifecycle) {
        return lifecycle == Lifecycle.experimental() ? Lifecycle.stable() : lifecycle;
    }
}
