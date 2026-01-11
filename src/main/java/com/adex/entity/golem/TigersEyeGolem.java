package com.adex.entity.golem;

import com.adex.entity.ai.DynamiteAttackGoal;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class TigersEyeGolem extends Golem {

    public TigersEyeGolem(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level, BossEvent.BossBarColor.YELLOW);
    }

    @Override
    public Goal getSpecialGoal() {
        return new DynamiteAttackGoal(this, getMeleeRange(), getRangedRange(), 20, 12.0f, 2.0f);
    }
}
