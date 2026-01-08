package com.adex.entity.ai;

import com.adex.entity.golem.Golem;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import org.jspecify.annotations.NonNull;

public class ChargeGoal extends MeleeAttackGoal {

    private final float minDistance; // exclusive
    private final float maxDistance; // inclusive
    private final float extraDamage;
    private boolean done;

    public ChargeGoal(Golem golem, float minDistance, float maxDistance, double speedMultiplier, float extraDamage) {
        super(golem, speedMultiplier, true);
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
        this.extraDamage = extraDamage;
        done = false;
    }

    @Override
    public boolean canUse() {
        LivingEntity target = mob.getTarget();
        if (target == null) return false;
        if (target.distanceToSqr(mob) <= minDistance * minDistance) return false;

        mob.getNavigation().setRequiredPathLength(maxDistance * 1.5f);
        return super.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        return !done && super.canContinueToUse();
    }

    @Override
    protected void checkAndPerformAttack(@NonNull LivingEntity target) {
        if (canPerformAttack(target)) attack(target);
    }

    public void attack(@NonNull LivingEntity target) {
        ((Golem) mob).addTemporaryAttackDamage(extraDamage);
        mob.swing(InteractionHand.MAIN_HAND);
        mob.doHurtTarget(getServerLevel(this.mob), target);
        ((Golem) mob).clearTemporaryAttackDamage();

        done = true;
    }

    @Override
    protected boolean canPerformAttack(@NonNull LivingEntity target) {
        return !done && this.mob.isWithinMeleeAttackRange(target) && this.mob.getSensing().hasLineOfSight(target);
    }

    @Override
    public void start() {
        super.start();
        done = false;
    }

    @Override
    public void stop() {
        super.stop();
    }
}