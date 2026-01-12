package com.adex.entity.golem;

import com.adex.entity.ai.PotionAttackGoal;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class OpalGolem extends Golem {

    public OpalGolem(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level, BossEvent.BossBarColor.WHITE);
    }

    @Override
    public Goal getSpecialGoal() {
        return new PotionAttackGoal(this, getMeleeRange(), getRangedRange(), 20, 1, 1.5d, 0.5f, 3.0f);
    }

    @Override
    public boolean isAffectedByPotions() {
        return false;
    }
}
