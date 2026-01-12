package com.adex.entity.ai;

import com.adex.entity.golem.Golem;
import com.adex.entity.projectile.projectile.HeatBall;
import net.minecraft.server.level.ServerLevel;
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
        Vec3 direction = createDirectionVector(target, level.getRandom());
        HeatBall heatBall = new HeatBall(golem, direction, level, damage, heatIncrease, 1.0d);
        level.addFreshEntity(heatBall);
    }
}
