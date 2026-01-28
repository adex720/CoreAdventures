package com.adex.entity.ai;

import com.adex.entity.sentry.Sentry;
import com.adex.util.Util;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.AABB;

import java.util.List;

/**
 * Makes a sentry spread its anger on sentries within {@link SpreadAngerGoal#spreadRadius} in any direction.
 * The anger value of the targets will be set to one lower than the spreaders.
 * Negative anger values can't be spread.
 * If there are multiple Sentries within range, all of them will be spread once in a random order
 * with {@link SpreadAngerGoal#spreadCooldown} ticks between each spread.
 * The list of sentries to spread to is calculated when the goal is started.
 */
public class SpreadAngerGoal extends Goal {

    private final double spreadRadius;
    private final int spreadCooldown;

    private final Sentry sentry;
    private List<Sentry> cachedSentries;

    private int timer;

    public SpreadAngerGoal(Sentry sentry, double spreadRadius, int spreadCooldown) {
        this.sentry = sentry;
        this.spreadRadius = spreadRadius;
        this.spreadCooldown = spreadCooldown;

        cachedSentries = null;
        timer = 0;
    }

    @Override
    public boolean canUse() {
        return sentry.getAnger() > 0 && !getNearbySentries().isEmpty();
    }

    @Override
    public boolean canContinueToUse() {
        return sentry.getAnger() > 0 && cachedSentries != null && !cachedSentries.isEmpty();
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    // null pointer exception can't happen, because canContinueToUse() is always called before tick()
    @SuppressWarnings("DataFlowIssue")
    @Override
    public void tick() {
        if (timer % spreadCooldown > 0) return;
        timer++;

        Sentry target = Util.removeRandomElement(cachedSentries, sentry.getRandom());
        target.applyAnger(sentry);

        sentry.lookAtIfPossible(target);
    }

    private AABB getSearchArea() {
        return sentry.getBoundingBox().inflate(spreadRadius);
    }

    private List<Sentry> getNearbySentries() {
        if (cachedSentries == null) {
            cachedSentries = sentry.level().getEntitiesOfClass(Sentry.class, getSearchArea(), target -> target.getAnger() < sentry.getAnger());
        }

        return cachedSentries;
    }

    @Override
    public void start() {
        super.start();
        timer = 0;
    }

    @Override
    public void stop() {
        super.stop();
        cachedSentries = null;
        timer = 0;
    }
}
