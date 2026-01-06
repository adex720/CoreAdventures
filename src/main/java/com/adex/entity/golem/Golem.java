package com.adex.entity.golem;

import com.adex.mixin.DefaultAttributesAccessor;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public abstract class Golem extends Monster {

    protected final ServerBossEvent bossEvent;

    public Golem(EntityType<? extends Monster> entityType, Level level, BossEvent.BossBarColor color) {
        super(entityType, level);

        bossEvent = new ServerBossEvent(getDisplayName(), color, BossEvent.BossBarOverlay.PROGRESS);
    }

    public static <T extends Golem> EntityType.Builder<T> builder(EntityType.EntityFactory<T> entityFactory) {
        return EntityType.Builder.of(entityFactory, MobCategory.MONSTER).fireImmune().sized(T.getWidth(), T.getHeight()).clientTrackingRange(10).notInPeaceful();
    }

    public static float getWidth() {
        return 1.6f;
    }

    public static float getHeight() {
        return 1.6f;
    }

    public static void registerAttributes(EntityType<? extends Golem> entityType) {
        DefaultAttributesAccessor.coread$getSuppliers().put(entityType, createAttributes().build());
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 300.0)
                .add(Attributes.MOVEMENT_SPEED, 0.6F)
                .add(Attributes.FOLLOW_RANGE, 20.0)
                .add(Attributes.ARMOR, 5.0);
    }

    @Override
    protected void readAdditionalSaveData(@NonNull ValueInput valueInput) {
        super.readAdditionalSaveData(valueInput);
        if (hasCustomName()) bossEvent.setName(this.getDisplayName());
    }

    @Override
    public void setCustomName(@Nullable Component component) {
        super.setCustomName(component);
        bossEvent.setName(getDisplayName());
    }

    @Override
    public void startSeenByPlayer(@NonNull ServerPlayer serverPlayer) {
        super.startSeenByPlayer(serverPlayer);
        this.bossEvent.addPlayer(serverPlayer);
    }

    @Override
    public void stopSeenByPlayer(@NonNull ServerPlayer serverPlayer) {
        super.stopSeenByPlayer(serverPlayer);
        this.bossEvent.removePlayer(serverPlayer);
    }

    @Override
    protected void actuallyHurt(@NonNull ServerLevel serverLevel, @NonNull DamageSource damageSource, float f) {
        super.actuallyHurt(serverLevel, damageSource, f);
        bossEvent.setProgress(getHealth() / getMaxHealth());
    }
}
