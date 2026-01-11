package com.adex.entity.ai;

import com.adex.entity.golem.Golem;
import com.adex.entity.projectile.projectile.HeatBall;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class HeatAttackGoal extends EyeOfSightAttackGoal {

    private final float damage;
    private final int heatIncrease;

    public HeatAttackGoal(Golem golem, float minDistance, float maxDistance, int cooldown, float damage, int heatIncrease) {
        super(golem, minDistance, maxDistance, cooldown, 1.0d);
        this.damage = damage;
        this.heatIncrease = heatIncrease;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void attack() {
        createHeatBall((ServerLevel) golem.level(), golem.getTarget());
    }

    public void createHeatBall(ServerLevel level, LivingEntity target) {
        double xDifference = target.getX() - golem.getX();
        double yDifference = target.getY(0.5) - golem.getY(0.5) - 1.0d;
        double zDifference = target.getZ() - golem.getZ();
        double distanceSqrtHalved = Math.sqrt(golem.distanceTo(target)) * 0.5;

        RandomSource random = level.getRandom();
        Vec3 direction = new Vec3(random.triangle(xDifference, 2.297 * distanceSqrtHalved), yDifference, random.triangle(zDifference, 2.297 * distanceSqrtHalved)).normalize();
        HeatBall heatBall = new HeatBall(golem, direction, level, damage, heatIncrease, 1.0d);
        level.addFreshEntity(heatBall);
    }
}
