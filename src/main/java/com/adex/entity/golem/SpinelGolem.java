package com.adex.entity.golem;

import com.adex.entity.ai.ArrowAttackGoal;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.Optional;

public class SpinelGolem extends Golem {

    public SpinelGolem(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level, BossEvent.BossBarColor.PINK);
    }

    @Override
    public Goal getSpecialGoal() {
        return new ArrowAttackGoal(this, getMeleeRange(), getRangedRange(), 10, 6.0f, 3.0f);
    }

    @Override
    public boolean isInvulnerableTo(@NonNull ServerLevel serverLevel, @NonNull DamageSource damageSource) {
        Optional<ResourceKey<DamageType>> damageType = damageSource.typeHolder().unwrapKey();
        if (damageType.isPresent() && damageType.get() == DamageTypes.ARROW) return true;
        return super.isInvulnerableTo(serverLevel, damageSource);
    }
}
