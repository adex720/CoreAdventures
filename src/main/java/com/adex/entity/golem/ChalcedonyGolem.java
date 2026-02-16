package com.adex.entity.golem;

import com.adex.entity.ai.ChargeGoal;
import com.adex.entity.ai.ThrowAttackGoal;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class ChalcedonyGolem extends Golem {

    public ChalcedonyGolem(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level, BossEvent.BossBarColor.BLUE);
    }

    @Override
    public Goal getSpecialGoal() {
        return new ChargeGoal(this, getMeleeRange(), getRangedRange(), 4.0d, 12.0f);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        goalSelector.addGoal(2, new ThrowAttackGoal(this, 3.0f, 5.0f, 0.75f, 0.2f, 3));
    }
}
