package com.adex.entity.golem;

import com.adex.entity.ai.MoveAwayFromCorePortalGoal;
import com.adex.entity.ai.MoveTowardsCorePortalGoal;
import com.adex.mixin.DefaultAttributesAccessor;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.PathType;
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
                .add(Attributes.MAX_HEALTH, 300.0d)
                .add(Attributes.ATTACK_DAMAGE, 8.0d)
                .add(Attributes.MOVEMENT_SPEED, 0.2d)
                .add(Attributes.KNOCKBACK_RESISTANCE, 2.0d)
                .add(Attributes.FOLLOW_RANGE, 20.0d)
                .add(Attributes.ARMOR, 5.0d);
    }

    @Override
    protected void registerGoals() {
        updatePathFindingMalus();

        goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0d, true));
        goalSelector.addGoal(3, new MoveTowardsTargetGoal(this, 1.0d, 16.0f));
        goalSelector.addGoal(4, new MoveTowardsCorePortalGoal(this, 1.0d, 32.0f));
        goalSelector.addGoal(5, new MoveAwayFromCorePortalGoal(this, 1.0d, 8.0f));
        goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0f, 0.25f));
        goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0d, 60));

        targetSelector.addGoal(1, new HurtByTargetGoal(this));
        targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 0, false, false, this::isValidPlayerTarget));
    }

    protected void updatePathFindingMalus() {
        setPathfindingMalus(PathType.LAVA, 8.0f);
        setPathfindingMalus(PathType.DAMAGE_FIRE, 8.0f);
        setPathfindingMalus(PathType.DANGER_FIRE, 4.0f);
    }

    protected boolean isValidPlayerTarget(LivingEntity livingEntity, ServerLevel level) {
        return livingEntity instanceof Player player && !player.isCreative() && !player.isSpectator();
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

    @Override
    public boolean removeWhenFarAway(double d) {
        return false;
    }
}
