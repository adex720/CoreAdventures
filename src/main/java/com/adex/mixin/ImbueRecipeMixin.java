package com.adex.mixin;

import com.adex.data.recipe.SplashArrowRecipe;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.ImbueRecipe;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ImbueRecipe.class)
public class ImbueRecipeMixin {

    @Inject(at = @At("RETURN"), method = "matches(Lnet/minecraft/world/item/crafting/CraftingInput;Lnet/minecraft/world/level/Level;)Z", cancellable = true)
    private void ensureArrowsAreOfSamePotion(CraftingInput input, Level level, CallbackInfoReturnable<Boolean> cir) {
        // Skip checks if recipe is already invalid
        if (!cir.getReturnValue()) return;

        // Skip checks if source has potion contents
        if (input.getItem(1, 1).get(DataComponents.POTION_CONTENTS) != PotionContents.EMPTY) return;

        // Compare potion contents of first material item to others
        ItemStack first = input.getItem(0, 0);
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                // Skip checking on first arrow and on source item
                if (y == x && (x == 0 || x == 1)) continue;

                if (!SplashArrowRecipe.doArrowsHaveSameEffects(first, input.getItem(x, y))) {
                    // Invalidate current input
                    cir.setReturnValue(false);
                    return;
                }
            }
        }
    }
}
