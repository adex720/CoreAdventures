package com.adex.entity.ai;

import com.adex.entity.golem.Golem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public abstract class EyeOfSightAttackGoal extends Goal {

    protected final Golem golem;
    protected final float minDistanceSqr;
    protected final float maxDistance;
    protected final int cooldown;
    protected final double speedMultiplier;

    protected int cooldownTimer;

    protected EyeOfSightAttackGoal(Golem golem, float minDistance, float maxDistance, int cooldown, double speedMultiplier) {
        this.golem = golem;
        this.minDistanceSqr = minDistance * minDistance;
        this.maxDistance = maxDistance;
        this.cooldown = cooldown;
        this.speedMultiplier = speedMultiplier;

        cooldownTimer = 0;

        setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity target = golem.getTarget();
        if (target == null || !target.isAlive()) return false;

        double distanceSqr = golem.distanceToSqr(target);
        return distanceSqr > minDistanceSqr && distanceSqr <= maxDistance * maxDistance;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        LivingEntity target = golem.getTarget();
        cooldownTimer++;
        if (target == null) return;

        if (golem.getSensing().hasLineOfSight(target)) {
            golem.getNavigation().stop();
            if (cooldownTimer >= cooldown) {
                attack();
                golem.attacked();
                cooldownTimer = 0;
            }
            return;
        }

        if (golem.getNavigation().isInProgress()) return;
        golem.getNavigation().moveTo(target, speedMultiplier);
    }

    public abstract void attack();

    @Override
    public void start() {
        cooldownTimer = cooldown;
    }

    @Override
    public void stop() {
        golem.getNavigation().stop();
    }

}
