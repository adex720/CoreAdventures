package com.adex.entity.sentry;

import com.adex.data.structure.ModStructures;
import com.adex.entity.ai.SpreadAngerGoal;
import com.adex.mixin.GoalSelectorAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Map;

public class Sentry extends Monster {

    public static final int MIN_ANGER = -32;
    public static final int MAX_ANGER = 32;

    public static final int ANGER_UPDATE_TIME = 400; // How many ticks in average does it take for anger to decrease by 1.

    protected int anger;
    protected GoalState goalState;

    public Sentry(EntityType<? extends Monster> entityType, Level level) {
        anger = 0;
        goalState = GoalState.NEUTRAL;
        super(entityType, level);
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

    @Override
    protected void addAdditionalSaveData(@NonNull ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putInt("anger", anger);
        output.putInt("goal_state", goalState.id);
    }

    @Override
    protected void readAdditionalSaveData(@NonNull ValueInput input) {
        super.readAdditionalSaveData(input);
        anger = input.getIntOr("anger", 0);
        goalState = GoalState.getById(input.getIntOr("goal_state", 1));
        registerGoals();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 30.0d)
                .add(Attributes.ATTACK_DAMAGE, 5.0d)
                .add(Attributes.ATTACK_SPEED, 5.0d)
                .add(Attributes.MOVEMENT_SPEED, 0.3d)
                .add(Attributes.FOLLOW_RANGE, 20.0d)
                .add(Attributes.ARMOR, 3.0d);
    }

    public void updateGoals() {
        GoalState correct = getGoalState();
        if (goalState == correct) return;

        goalState = correct;
        registerGoals();
    }

    public void removeGoals() {
        // stop running goals
        for (WrappedGoal goal : goalSelector.getAvailableGoals()) {
            if (goal.isRunning()) goal.stop();
        }
        for (WrappedGoal goal : targetSelector.getAvailableGoals()) {
            if (goal.isRunning()) goal.stop();
        }

        // remove all goals
        goalSelector.removeAllGoals(_ -> true);
        targetSelector.removeAllGoals(_ -> true);

        // reset locked flags
        resetLockedGoals();
    }

    @Override
    protected void registerGoals() {
        removeGoals();

        switch (goalState) {
            case NEUTRAL -> registerGoals(getNeutralGoals(), getNeutralTargets());
            case AGGRESSIVE -> registerGoals(getAggressiveGoals(), getAggressiveTargets());
        }
    }

    protected void registerGoals(Map<Integer, Goal> goals, Map<Integer, Goal> targets) {
        goals.forEach(goalSelector::addGoal);
        targets.forEach(targetSelector::addGoal);
    }

    protected Map<Integer, Goal> getEmptyTargets() {
        return Map.of();
    }

    protected Map<Integer, Goal> getNeutralGoals() {
        return Map.of(45, new RandomLookAroundGoal(this),
                46, new LookAtPlayerGoal(this, Player.class, 8.0f),
                47, new LookAtPlayerGoal(this, LivingEntity.class, 8.0f),
                50, new WaterAvoidingRandomStrollGoal(this, 1.0d));
    }

    protected Map<Integer, Goal> getNeutralTargets() {
        return getEmptyTargets();
    }

    protected Map<Integer, Goal> getAggressiveGoals() {
        return Map.of(10, new SpreadAngerGoal(this, 16.0d, 40),
                20, new MeleeAttackGoal(this, 1.0d, true),
                30, new MoveTowardsTargetGoal(this, 1.0d, 24.0f),
                40, new WaterAvoidingRandomStrollGoal(this, 1.0d),
                55, new RandomLookAroundGoal(this),
                56, new LookAtPlayerGoal(this, Player.class, 8.0f));
    }

    protected Map<Integer, Goal> getAggressiveTargets() {
        return Map.of(1, new HurtByTargetGoal(this),
                2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 0, false, false, this::isValidPlayerTarget));
    }

    public void resetLockedGoals() {
        ((GoalSelectorAccessor) goalSelector).coread$getLockedFlags().clear();
    }

    protected boolean isValidPlayerTarget(LivingEntity livingEntity, ServerLevel level) {
        return livingEntity instanceof Player player && !player.isCreative() && !player.isSpectator();
    }

    public GoalState getGoalState() {
        return shouldAttackPlayer() ? GoalState.AGGRESSIVE : GoalState.NEUTRAL;
    }

    public void setAnger(int value) {
        anger = Math.clamp(value, MIN_ANGER, MAX_ANGER);
        updateGoals();
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

    public int getAnger() {
        return anger;
    }

    public boolean shouldAttackPlayer() {
        return anger > 0;
    }

    public void setAnger(int value, boolean updateGoals) {
        anger = Math.clamp(value, MIN_ANGER, MAX_ANGER);
        if (updateGoals) updateGoals();
    }

    public void increaseAnger(int amount, boolean updateGoals) {
        setAnger(anger + amount, updateGoals);
    }

    public void decreaseAnger(int amount, boolean updateGoals) {
        increaseAnger(-amount, updateGoals);
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
    protected void actuallyHurt(@NonNull ServerLevel serverLevel, @NonNull DamageSource damageSource, float f) {
        super.actuallyHurt(serverLevel, damageSource, f);

        if (!(damageSource.getEntity() instanceof Player player)) return;
        if (player.isCreative() || player.isSpectator()) return;

        hurtBySurvivalPlayer();
    }

    /**
     * Run when hit by a player whose game mode is survival or adventure
     */
    public void hurtBySurvivalPlayer() {
        setAnger(32);
    }

    @Override
    public void tick() {
        super.tick();

        if (level().random.nextInt(ANGER_UPDATE_TIME) == 0) updateAnger();
    }

    public void lookAtIfPossible(Entity entity) {
        GoalSelectorAccessor goalSelector = (GoalSelectorAccessor) this.goalSelector;
        if (goalSelector.coread$getLockedFlags().containsKey(Goal.Flag.LOOK)
                || goalSelector.coread$getDisabledFlags().contains(Goal.Flag.LOOK)) {
            return;
        }

        lookAt(entity, 30.0f, 30.0f);
    }

    @Override
    public @Nullable SpawnGroupData finalizeSpawn(@NonNull ServerLevelAccessor level, @NonNull DifficultyInstance difficulty, @NonNull EntitySpawnReason spawnReason, @Nullable SpawnGroupData spawnGroupData) {
        spawnGroupData = super.finalizeSpawn(level, difficulty, spawnReason, spawnGroupData);

        RandomSource random = level.getRandom();
        float difficultyMultiplier = difficulty.getDifficulty().getId() * 0.1f;

        setItemSlot(EquipmentSlot.MAINHAND, createMainHandItem(random, difficultyMultiplier));

        return spawnGroupData;
    }

    public ItemStack createMainHandItem(RandomSource random, float difficultyMultiplier) {
        return ItemStack.EMPTY;
    }

    public static void chestOpened(ServerLevel level, Player player, BlockPos pos) {
        if (player.isCreative() || player.isSpectator() || player.isInvisible()) return;

        // Only anger if chest is in a refuge
        if (level.structureManager().getAllStructuresAt(pos).entrySet().stream()
                .noneMatch(structureLongSetEntry -> structureLongSetEntry.getKey().type() == ModStructures.REFUGE_TYPE))
            return;

        level.getEntitiesOfClass(Sentry.class, new AABB(pos).inflate(32.0d)).forEach(sentry -> sentry.setAnger(32));
    }

    public enum GoalState {

        NONE(0),
        NEUTRAL(1),
        AGGRESSIVE(2),
        TRADING(3);

        public final int id;

        GoalState(int id) {
            this.id = id;
        }

        public static GoalState getById(int id) {
            for (GoalState state : values()) {
                if (state.id == id) return state;
            }

            return NONE;
        }
    }
}
