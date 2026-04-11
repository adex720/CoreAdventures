package com.adex.mixin;

import com.adex.data.recipe.SplashArrowRecipe;
import com.adex.item.ModItems;
import com.adex.util.Util;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.ImbueRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.ShapedCraftingRecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ImbueRecipe.class)
public class ImbueRecipeMixin {

    @Shadow
    @Final
    private Ingredient source;

    @Shadow
    @Final
    private Ingredient material;

    @Shadow
    @Final
    private ItemStackTemplate result;

    @Inject(at = @At("RETURN"), method = "matches(Lnet/minecraft/world/item/crafting/CraftingInput;Lnet/minecraft/world/level/Level;)Z", cancellable = true)
    private void ensureArrowsAreOfSamePotion(CraftingInput input, Level level, CallbackInfoReturnable<Boolean> cir) {
        // Skip checks if recipe is already invalid
        if (!cir.getReturnValue()) return;

        // Skip checks if source has potion contents
        if (!Util.isPotionContentsEmpty(input.getItem(1, 1))) return;

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

    @ModifyVariable(at = @At(value = "STORE", ordinal = 0), method = "assemble(Lnet/minecraft/world/item/crafting/CraftingInput;)Lnet/minecraft/world/item/ItemStack;", name = "source")
    private ItemStack getPotionContentsFromArrows(ItemStack source, CraftingInput input) {
        // return first material item, if source has no potion contents
        if (Util.isPotionContentsEmpty(source))
            return input.getItem(0, 0);

        return source;
    }

    @Inject(at = @At("RETURN"), method = "display", cancellable = true)
    private void fixPotionContentsHolder(CallbackInfoReturnable<List<RecipeDisplay>> cir) {
        // Only fix potion contents if recipe source item is opal shard
        if (!source.test(ModItems.OPAL_SHARD.getDefaultInstance())) return;

        SlotDisplay.WithAnyPotion arrow = new SlotDisplay.WithAnyPotion(material.display());
        SlotDisplay middle = source.display();
        cir.setReturnValue(List.of(new ShapedCraftingRecipeDisplay(3, 3,
                List.of(arrow, arrow, arrow, arrow, middle, arrow, arrow, arrow, arrow), new SlotDisplay.WithAnyPotion(
                new SlotDisplay.ItemStackSlotDisplay(result)), new SlotDisplay.ItemSlotDisplay(Items.CRAFTING_TABLE))));
    }
}
