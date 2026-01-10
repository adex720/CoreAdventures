package com.adex.entity.golem;

import com.adex.entity.ai.RegenerateWhenFarAwayGoal;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class RubyGolem extends Golem {

    public RubyGolem(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level, BossEvent.BossBarColor.RED);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        Optional<WrappedGoal> old = goalSelector.getAvailableGoals().stream()
                .filter(goal -> goal.getGoal() instanceof RegenerateWhenFarAwayGoal).findFirst();

        if (old.isEmpty()) return;

        WrappedGoal goal = old.get();
        goalSelector.removeGoal(goal.getGoal());
        goalSelector.addGoal(goal.getPriority(), new RegenerateWhenFarAwayGoal(this, getRangedRange(), 0.0f, 5, 60));
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

    @Override
    public float getMeleeRange() {
        return getRangedRange();
    }
}
