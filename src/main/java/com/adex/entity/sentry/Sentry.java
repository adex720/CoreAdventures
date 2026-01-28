package com.adex.entity.sentry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class Sentry extends Monster {

    public Sentry(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    public static EntityType.Builder<Sentry> builder(EntityType.EntityFactory<Sentry> entityFactory) {
        return EntityType.Builder.of(entityFactory, MobCategory.MONSTER)
                .sized(0.6f, 1.95f)
                .eyeHeight(1.79f)
                .passengerAttachments(2.0125f)
                .ridingOffset(-0.7f)
                .clientTrackingRange(8)
                .notInPeaceful();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 30.0d)
                .add(Attributes.ATTACK_DAMAGE, 3.0d)
                .add(Attributes.ATTACK_SPEED, 5.0d)
                .add(Attributes.MOVEMENT_SPEED, 0.5d)
                .add(Attributes.FOLLOW_RANGE, 20.0d)
                .add(Attributes.ARMOR, 3.0d);
    }

    @Override
    protected void registerGoals() {
    }
}
