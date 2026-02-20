package com.adex.mixin.client;

import com.adex.CoreAdventures;
import com.adex.datagen.texture.ModArmorTrimTextureProvider;
import com.adex.util.Util;
import net.minecraft.client.data.models.ItemModelGenerators;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ItemModelGenerators.class)
public class ItemModelGeneratorsMixin {

    @Mutable
    @Shadow
    @Final
    public static List<ItemModelGenerators.TrimMaterialData> TRIM_MATERIAL_MODELS;

    @Inject(at = @At("TAIL"), method = "<clinit>")
    private static void addModTrimMaterials(CallbackInfo ci) {
        if (CoreAdventures.GENERATE_ARMOR_TRIMS) {
            TRIM_MATERIAL_MODELS = Util.combine(TRIM_MATERIAL_MODELS, ModArmorTrimTextureProvider.MOD_TRIM_MATERIALS);
        }
    }

}
