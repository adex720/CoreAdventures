package com.adex.mixin;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.EnumSet;
import java.util.Map;

@Mixin(GoalSelector.class)
public interface GoalSelectorAccessor {

    @Accessor("lockedFlags")
    Map<Goal.Flag, WrappedGoal> coread$getLockedFlags();

    @Accessor("disabledFlags")
    EnumSet<Goal.Flag> coread$getDisabledFlags();

}
