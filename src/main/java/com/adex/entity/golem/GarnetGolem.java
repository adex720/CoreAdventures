package com.adex.entity.golem;

import com.adex.entity.ai.HeatAttackGoal;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class GarnetGolem extends Golem {

    public GarnetGolem(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level, BossEvent.BossBarColor.RED);
    }

    @Override
    public Goal getSpecialGoal() {
        return new HeatAttackGoal(this, getMeleeRange(), getRangedRange(), 20, 12.0f, 5);
    }
}
