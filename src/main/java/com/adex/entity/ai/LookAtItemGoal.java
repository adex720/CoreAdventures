package com.adex.entity.ai;

import com.adex.entity.sentry.TraderSentry;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class LookAtItemGoal extends Goal {

    private final TraderSentry sentry;
    private final int tickCount;

    public LookAtItemGoal(TraderSentry sentry, int tickCount) {
        this.sentry = sentry;
        this.tickCount = tickCount;

        setFlags(EnumSet.of(Goal.Flag.LOOK, Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        return sentry.isHoldingItem() && sentry.lookedTicks < tickCount;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void start() {
        sentry.lookedTicks = 0;
        sentry.getNavigation().stop();
    }

    @Override
    public void tick() {
        sentry.lookedTicks++;
    }
}
