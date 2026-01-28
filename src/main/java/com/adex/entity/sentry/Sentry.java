package com.adex.entity.sentry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class Sentry extends Monster {

    public static final int MIN_ANGER = -32;
    public static final int MAX_ANGER = 32;

    public static final int ANGER_UPDATE_TIME = 1200; // How many ticks in average does it take for anger to decrease by 1.

    protected int anger;

    public Sentry(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.anger = 0;
    }

    public static <T extends Sentry> EntityType.Builder<T> builder(EntityType.EntityFactory<T> entityFactory) {
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

    public void setAnger(int value) {
        anger = Math.clamp(value, MIN_ANGER, MAX_ANGER);
    }

    public void increaseAnger(int amount) {
        setAnger(anger + amount);
    }

    public void increaseAnger() {
        increaseAnger(1);
    }

    public void decreaseAnger() {
        increaseAnger(-1);
    }

    public void decreaseAnger(int amount) {
        increaseAnger(-amount);
    }

    public int getAnger() {
        return anger;
    }

    public boolean shouldAttackPlayer() {
        return anger > 0;
    }

    /**
     * Sets anger of this Sentry to one lower as the source, if source has higher anger than this.
     * The anger is not set if anger of source is not greater than 0.
     */
    public void applyAnger(Sentry source) {
        if (source.anger > 0 && source.anger > this.anger) setAnger(source.anger - 1);
    }

    /**
     * Updates the value of anger to one closer to 0.
     */
    public void updateAnger() {
        if (anger > 0) decreaseAnger();
        else if (anger < 0) increaseAnger();
    }

    @Override
    public void tick() {
        super.tick();

        if (level().random.nextInt(ANGER_UPDATE_TIME) == 0) updateAnger();
    }
}
