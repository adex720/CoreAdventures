package com.adex.entity.golem;

import com.adex.entity.ai.ArrowAttackGoal;
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
        return new ArrowAttackGoal(this, getMeleeRange(), getRangedRange(), 10, 6.0f, 3.0f);
    }
}
