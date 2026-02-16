package com.adex.data.recipe;

import com.adex.item.ModItems;
import com.adex.item.SplashArrowItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class SplashArrowRecipe extends CustomRecipe {

    public SplashArrowRecipe(CraftingBookCategory craftingBookCategory) {
        super(craftingBookCategory);
    }

    @Override
    public boolean matches(CraftingInput recipeInput, @NonNull Level level) {
        if (recipeInput.width() != 3 || recipeInput.height() != 3 || recipeInput.ingredientCount() != 9) return false;

        ItemStack arrow = recipeInput.getItem(0, 0);
        ItemStack middle = recipeInput.getItem(1, 1);

        if (!middle.is(ModItems.OPAL)) {
            return false;
        }

        if (!arrow.is(Items.TIPPED_ARROW)) return false;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if ((x == 0 && y == 0) || (x == 1 && y == 1)) continue;

                ItemStack checking = recipeInput.getItem(x, y);
                if (!checking.is(Items.TIPPED_ARROW) || !doArrowsHaveSameEffects(arrow, checking)) return false;
            }
        }

        return true;
    }

    /**
     * Returns true when and only when each {@link MobEffectInstance} of one arrow is also on the other arrow
     * with the same{@link MobEffect}, amplifier and duration.
     *
     * @param arrow1 {@link ItemStack} of the first arrow
     * @param arrow2 {@link ItemStack} of the second arrow
     * @return true if the arrows have the same effects
     */
    public boolean doArrowsHaveSameEffects(ItemStack arrow1, ItemStack arrow2) {
        List<MobEffectInstance> effectInstances1 = SplashArrowItem.getEffectInstances(arrow1);
        List<MobEffectInstance> effectInstances2 = SplashArrowItem.getEffectInstances(arrow2);

        for (MobEffectInstance instance1 : effectInstances1) {
            if (!effectInstances2.removeIf(instance2 -> areEffectInstancesSame(instance1, instance2))) {
                // First arrow has an effect which the second doesn't have
                return false;
            }
        }

        // effectInstances2 now only has effects which aren't present on the first arrow
        return effectInstances2.isEmpty();
    }

    /**
     * Returns true if the {@link MobEffectInstance} are of the same {@link MobEffect},
     * have the same amplifier
     * and have the same duration
     *
     * @param instance1 First {@link MobEffectInstance} to compare
     * @param instance2 Second {@link MobEffectInstance} to compare
     * @return True if the {@link MobEffectInstance} are of the same
     */
    public boolean areEffectInstancesSame(MobEffectInstance instance1, MobEffectInstance instance2) {
        if (!instance1.is(instance2.getEffect())) return false;
        if (instance1.getAmplifier() != instance2.getAmplifier()) return false;

        return instance1.getDuration() == instance2.getDuration();
    }

    @Override
    public @NonNull ItemStack assemble(CraftingInput recipeInput, HolderLookup.@NonNull Provider provider) {
        ItemStack arrow = recipeInput.getItem(0, 0);
        ItemStack middle = recipeInput.getItem(1, 1);

        if (!middle.is(ModItems.OPAL)) {
            return ItemStack.EMPTY;
        }

        ItemStack result = new ItemStack(ModItems.SPLASH_ARROW, 8);
        result.set(DataComponents.POTION_CONTENTS, arrow.get(DataComponents.POTION_CONTENTS));
        return result;

    }

    @Override
    public @NonNull RecipeSerializer<? extends CustomRecipe> getSerializer() {
        return ModRecipeSerializers.SPLASH_ARROW;
    }
}
