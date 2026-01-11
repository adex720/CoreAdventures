package com.adex.entity.ai;

import com.adex.entity.golem.Golem;
import com.adex.entity.projectile.GolemFireball;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class FireAttackGoal extends EyeOfSightAttackGoal {

    private final float damage;
    private final int burningTime;
    private final float explosionPower;

    public FireAttackGoal(Golem golem, float minDistance, float maxDistance, float damage, int burningTime, int cooldown, float explosionPower) {
        super(golem, minDistance, maxDistance, cooldown, 1.0d);
        this.burningTime = burningTime;
        this.damage = damage;
        this.explosionPower = explosionPower;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void attack() {
        createFireball((ServerLevel) golem.level(), golem.getTarget());
    }

    public void createFireball(ServerLevel level, LivingEntity target) {
        double xDifference = target.getX() - golem.getX();
        double yDifference = target.getY(0.5) - golem.getY(0.5) - 1.0d;
        double zDifference = target.getZ() - golem.getZ();
        double distanceSqrtHalved = Math.sqrt(golem.distanceTo(target)) * 0.5;

        RandomSource random = level.getRandom();
        Vec3 direction = new Vec3(random.triangle(xDifference, 2.297 * distanceSqrtHalved), yDifference, random.triangle(zDifference, 2.297 * distanceSqrtHalved)).normalize();
        GolemFireball fireball = new GolemFireball(golem, direction, level, damage, burningTime, explosionPower, 1.0d);
        level.addFreshEntity(fireball);
    }
}
