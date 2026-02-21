package com.adex.entity.ai;

import com.adex.entity.golem.Golem;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

import java.util.EnumSet;
import java.util.List;

public class RemoveEffectGoal extends Goal {

    private final Golem golem;
    private final int cooldown;
    private final float chance;
    private final float maxDistanceSqr;

    private long lastTry;

    public RemoveEffectGoal(Golem golem, int cooldown, float chance, float maxDistance) {
        this.golem = golem;
        this.cooldown = cooldown;
        this.chance = chance;
        this.maxDistanceSqr = maxDistance * maxDistance;

        lastTry = 0;

        setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        long currentTime = golem.level().getGameTime();
        if (lastTry + cooldown > currentTime) return false;
        lastTry = currentTime;

        if (!isValidTarget()) return false;

        if (getRemovableEffects().isEmpty()) return false;

        return golem.getRandom().nextFloat() < chance;
    }

    @Override
    public boolean canContinueToUse() {
        return false;
    }

    /**
     * Returns true if the target is not null,
     * the target is alive,
     * and if the target is a player, the player is not in creative or spectator.
     */
    public boolean isValidTarget() {
        LivingEntity target = golem.getTarget();
        if (target == null) return false;

        if (!target.isAlive()) return false;

        if (target.distanceToSqr(golem) > maxDistanceSqr) return false;

        if (!(target instanceof Player player)) return true;
        return !player.isCreative() || !player.isSpectator();
    }

    @SuppressWarnings("DataFlowIssue")
    public List<MobEffectInstance> getRemovableEffects() {
        return golem.getTarget().getActiveEffects().stream()
                .filter(effect -> effect.getEffect().value().isBeneficial()).toList();
    }

    @Override
    public void start() {
        LivingEntity target = golem.getTarget();
        if (target == null) return;

        List<MobEffectInstance> effects = getRemovableEffects();
        Holder<MobEffect> toRemove = effects.get(golem.getRandom().nextInt(effects.size())).getEffect();

        if (target.removeEffect(toRemove)) golem.attacked();
    }
}
