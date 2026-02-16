package com.adex.entity.ai;

import com.adex.entity.golem.Golem;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

/**
 * A throw attack deals some damage to the target and throws the target up.
 * Knockback resistance is ignored.
 */
public class ThrowAttackGoal extends Goal {

    public static final int TRY_COOLDOWN = 60; // in ticks
    public static final int ATTACK_COOLDOWN = 18; // in ticks

    private final Golem golem;
    private final float horizontalRangeSqr;
    private final float yRange;
    private final double damageModifier;
    private final float throwStrength;
    private final float chance;
    private final int maxAttackCount;

    private int attackCount;
    private long lastTry;
    private long lastAttack;

    public ThrowAttackGoal(Golem golem, float range, float damage, float throwStrength, float chance, int maxAttackCount) {
        this(golem, range, range, damage, throwStrength, chance, maxAttackCount);
    }

    /**
     * Creates a new ThrowAttackGoal
     *
     * @param golem           Golem who has the goal
     * @param horizontalRange Maximum horizontal range between the center of the golem and the center of the target
     * @param yRange          Maximum y range between the bounding box of the target and the middle of the golem
     * @param damage          Amount of damage dealt when throwing
     * @param throwStrength   Initial Y velocity given to the target when throwing
     * @param chance          Chance of starting this goal
     * @param maxAttackCount  Maximum amount of throws performed when this goal is chosen
     */
    public ThrowAttackGoal(Golem golem, float horizontalRange, float yRange, float damage, float throwStrength, float chance, int maxAttackCount) {
        this.golem = golem;
        this.horizontalRangeSqr = horizontalRange * horizontalRange;
        this.yRange = yRange;
        this.damageModifier = getDamageModifier(damage);
        this.throwStrength = throwStrength;
        this.chance = chance;
        this.maxAttackCount = maxAttackCount;

        attackCount = 0;
        lastTry = 0;
        lastAttack = 0;

        setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    public double getDamageModifier(float wantedDamage) {
        AttributeInstance attackDamageAttribute = golem.getAttribute(Attributes.ATTACK_DAMAGE);
        if (attackDamageAttribute == null) return wantedDamage;

        return wantedDamage - attackDamageAttribute.getBaseValue();
    }

    @Override
    public boolean canUse() {
        long currentTime = golem.level().getGameTime();
        if (lastTry + TRY_COOLDOWN > currentTime) return false;
        lastTry = currentTime;

        if (!isCloseEnough(false)) return false;

        return golem.getRandom().nextFloat() < chance;
    }

    @Override
    public boolean canContinueToUse() {
        return attackCount < maxAttackCount && isCloseEnough(true);
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

        if (!(target instanceof Player player)) return true;
        return !player.isCreative() || !player.isSpectator();
    }

    /**
     * Returns true if the target is within {@link ThrowAttackGoal#horizontalRangeSqr} and {@link ThrowAttackGoal#yRange}.
     *
     * @param accountForThrow If true, the target is allowed to be any amount above the golem to be in range
     * @return True if the target is not too far away
     */
    @SuppressWarnings("DataFlowIssue")
    public boolean isCloseEnough(boolean accountForThrow) {
        if (!isValidTarget()) return false;
        LivingEntity target = golem.getTarget();

        Vec3 targetPos = target.position();
        Vec3 ownPos = new Vec3(golem.getX(), golem.getY(0.5d), golem.getZ());
        if (ownPos.subtract(targetPos).horizontalDistanceSqr() > horizontalRangeSqr) return false;

        if (target.getY(1.0d) < ownPos.y - yRange) return false;

        return accountForThrow || (targetPos.y - yRange <= ownPos.y);
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        if (!isCloseEnough(false)) return;

        LivingEntity target = golem.getTarget();
        if (target == null || !golem.getSensing().hasLineOfSight(target)) return;

        long currentTime = golem.level().getGameTime();
        if (lastAttack + ATTACK_COOLDOWN > currentTime) return;
        lastAttack = currentTime;

        attack();
    }

    @SuppressWarnings("DataFlowIssue")
    public void attack() {
        attackCount++;

        LivingEntity target = golem.getTarget();
        if (golem.level() instanceof ServerLevel level) {
            // damage target
            golem.addTemporaryAttackDamage(damageModifier);
            golem.doHurtTarget(level, target);
            golem.clearTemporaryAttackDamage();
        }

        // throw target
        target.setDeltaMovement(target.getDeltaMovement().with(Direction.Axis.Y, throwStrength));
    }

    @Override
    public void start() {
        attackCount = 0;
    }
}
