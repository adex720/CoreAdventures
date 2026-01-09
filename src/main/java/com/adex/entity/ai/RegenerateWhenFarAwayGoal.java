package com.adex.entity.ai;

import com.adex.entity.golem.Golem;
import com.adex.payload.AddRegenerationParticlesS2C;
import com.adex.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class RegenerateWhenFarAwayGoal extends Goal {

    protected final Golem golem;
    private final float minDistance;
    private final float minDamage; // How much damage away from maximum Entity has to be for
    private final int regenerationSpeed; // heal 1 every regenerationSpeed ticks
    private final int tickCount; // How many ticks to regenerate until goal is finished. A new RegenerateWhenFarAwayGoal can start immediately after one ends
    private int tickCounter;

    public RegenerateWhenFarAwayGoal(Golem golem, float minDistance, float minDamage, int regenerationSpeed, int tickCount) {
        this.golem = golem;
        this.minDistance = minDistance;
        this.minDamage = minDamage;
        this.regenerationSpeed = regenerationSpeed;
        this.tickCount = tickCount;

        tickCounter = 0;

        this.setFlags(EnumSet.of(Goal.Flag.MOVE)); // Prevent moving while healing
    }

    @Override
    public boolean canUse() {
        if (isTooClose()) return false;
        return golem.getMaxHealth() - golem.getHealth() >= minDamage;
    }

    @Override
    public boolean canContinueToUse() {
        if (isTooClose()) return false;
        return golem.getHealth() < golem.getMaxHealth() && tickCounter < tickCount;
    }

    public boolean isTooClose() {
        LivingEntity target = golem.getTarget();
        return target != null && golem.distanceToSqr(target) < minDistance;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        tickCounter++;
        if (tickCounter % regenerationSpeed == 1) {
            golem.heal(1.0f);
            golem.updateBossEventProgress();

            Vec3 pos = golem.getEyePosition();
            Util.sendPayloadS2C(new AddRegenerationParticlesS2C(BlockPos.containing(pos)), (ServerLevel) golem.level(), pos, 32.0f);
        }
    }

    @Override
    public void start() {
        tickCounter = 0;
    }

    @Override
    public void stop() {
        tickCounter = 0;
    }
}
