package com.adex.entity.golem;

import com.adex.mixin.DefaultAttributesAccessor;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class JadeGolem extends Golem {

    public JadeGolem(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level, BossEvent.BossBarColor.GREEN);
    }

    public static void registerAttributes(EntityType<? extends Golem> entityType) {
        DefaultAttributesAccessor.coread$getSuppliers().put(entityType, createAttributes().build());
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Golem.createAttributes().add(Attributes.ATTACK_KNOCKBACK, 3.0d);
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
