package com.adex.mixin.client;

import com.adex.CoreAdventures;
import com.adex.item.armor.ModTrimMaterials;
import com.adex.util.Util;
import net.minecraft.client.renderer.texture.atlas.sources.PalettedPermutations;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Map;

@Mixin(PalettedPermutations.class)
public class PalettedPermutationsMixin {

    @ModifyVariable(at = @At("HEAD"), method = "<init>(Ljava/util/List;Lnet/minecraft/resources/Identifier;Ljava/util/Map;Ljava/lang/String;)V", argsOnly = true)
    private static Map<String, Identifier> addModTrimMaterials(Map<String, Identifier> permutations) {
        if (CoreAdventures.GENERATE_ARMOR_TRIMS) {
            return Util.combine(permutations, ModTrimMaterials.BY_NAME);
        }

        return permutations;
    }
}
