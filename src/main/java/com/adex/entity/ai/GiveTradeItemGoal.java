package com.adex.entity.ai;

import com.adex.entity.sentry.TraderSentry;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class GiveTradeItemGoal extends Goal {

    private final TraderSentry sentry;

    public GiveTradeItemGoal(TraderSentry sentry) {
        this.sentry = sentry;

        setFlags(EnumSet.of(Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        return sentry.isHoldingItem() && sentry.lookedTicks > 0;
    }

    @Override
    public boolean canContinueToUse() {
        return false;
    }

    @Override
    public void start() {
        sentry.finishTrade();
    }
}
