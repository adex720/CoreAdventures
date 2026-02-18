package com.adex.entity.sentry;

import com.adex.data.tag.ModTags;
import com.adex.entity.ai.MoveTowardsTradeItemGoal;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.Map;

public class WarriorSentry extends Sentry {

    public WarriorSentry(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected Map<Integer, Goal> getNeutralGoals() {
        HashMap<Integer, Goal> goals = new HashMap<>(super.getNeutralGoals());

        goals.put(10, new MeleeAttackGoal(this, 1.0d, true));
        goals.put(15, new MoveTowardsTargetGoal(this, 1.0d, 32.0f));
        return goals;
    }

    @Override
    protected Map<Integer, Goal> getNeutralTargets() {
        HashMap<Integer, Goal> goals = new HashMap<>(super.getNeutralTargets());

        goals.put(1, new NearestAttackableTargetGoal<>(this, Mob.class, true, (mob, level) -> doesAttack(mob.getType())));

        return goals;
    }

    @Override
    protected Map<Integer, Goal> getAggressiveTargets() {
        HashMap<Integer, Goal> goals = new HashMap<>(super.getAggressiveTargets());

        goals.put(3, new NearestAttackableTargetGoal<>(this, Mob.class, true, (mob, level) -> doesAttack(mob.getType())));

        return goals;
    }

    public static boolean doesAttack(EntityType<?> type) {
        return type.is(ModTags.SENTRY_DISLIKES);
    }

    @Override
    public ItemStack createMainHandItem(RandomSource random, float difficultyMultiplier) {
        float f = random.nextFloat() * 2.0f;
        if (f < difficultyMultiplier) return new ItemStack(Items.DIAMOND_AXE);
        return new ItemStack(Items.IRON_AXE);
    }
}
