package com.adex.entity.golem;

import com.adex.entity.ai.ChargeGoal;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.Path;

public class ChalcedonyGolem extends Golem {

    public ChalcedonyGolem(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level, BossEvent.BossBarColor.BLUE);
    }

    @Override
    public Goal getSpecialGoal() {
        return new ChargeGoal(this, getMeleeRange(), getRangedRange(), 4.0d, 12.0f);
    }

}
