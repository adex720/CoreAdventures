package com.adex.entity.ai;

import com.adex.entity.golem.Golem;
import com.adex.entity.projectile.projectile.GolemFireball;
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
        Vec3 direction = createDirectionVector(target, level.getRandom());
        GolemFireball fireball = new GolemFireball(golem, direction, level, damage, burningTime, explosionPower, 1.0d);
        level.addFreshEntity(fireball);
    }
}
