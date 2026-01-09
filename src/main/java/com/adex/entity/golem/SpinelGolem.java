package com.adex.entity.golem;

import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class SpinelGolem extends Golem {

    public SpinelGolem(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level, BossEvent.BossBarColor.PINK);
    }

    @Override
    public Goal getSpecialGoal() {
        return new Goal() {
            @Override
            public boolean canUse() {
                return false;
            }
        };
    }
}
