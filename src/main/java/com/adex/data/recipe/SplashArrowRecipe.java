package com.adex.data.recipe;

import com.adex.item.SplashArrowItem;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

import java.util.List;

public class SplashArrowRecipe {

    /**
     * Returns true when and only when each {@link MobEffectInstance} of one arrow is also on the other arrow
     * with the same{@link MobEffect}, amplifier and duration.
     *
     * @param arrow1 {@link ItemStack} of the first arrow
     * @param arrow2 {@link ItemStack} of the second arrow
     * @return true if the arrows have the same effects
     */
    public static boolean doArrowsHaveSameEffects(ItemStack arrow1, ItemStack arrow2) {
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
    public static boolean areEffectInstancesSame(MobEffectInstance instance1, MobEffectInstance instance2) {
        if (!instance1.is(instance2.getEffect())) return false;
        if (instance1.getAmplifier() != instance2.getAmplifier()) return false;

        return instance1.getDuration() == instance2.getDuration();
    }
}
