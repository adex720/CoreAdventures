package com.adex.entity.ai;

import com.adex.entity.golem.Golem;
import com.adex.entity.projectile.projectile.DynamiteProjectile;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class DynamiteAttackGoal extends EyeOfSightAttackGoal {

    private final float damage;
    private final float explosionPower;

    public DynamiteAttackGoal(Golem golem, float minDistance, float maxDistance, int cooldown, float damage, float explosionPower) {
        super(golem, minDistance, maxDistance, cooldown, 1.0d);
        this.damage = damage;
        this.explosionPower = explosionPower;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void attack() {
        createDynamite((ServerLevel) golem.level(), golem.getTarget());
    }

    public void createDynamite(ServerLevel level, LivingEntity target) {
        Vec3 direction = createDirectionVector(target, level.getRandom());
        DynamiteProjectile dynamite = new DynamiteProjectile(golem, direction, level, damage, explosionPower, 1.0d);
        level.addFreshEntity(dynamite);
    }
}
