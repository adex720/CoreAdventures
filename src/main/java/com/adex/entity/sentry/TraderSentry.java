package com.adex.entity.sentry;

import com.adex.advancement.criterion.ModCriterionTriggers;
import com.adex.data.loottable.ModLootTables;
import com.adex.data.tag.ModTags;
import com.adex.entity.ai.GiveTradeItemGoal;
import com.adex.entity.ai.LookAtItemGoal;
import com.adex.entity.ai.MoveTowardsTradeItemGoal;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.Vec3i;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TraderSentry extends Sentry {

    public int lookedTicks;
    private int ticksBeforeGoalUpdate;

    public TraderSentry(EntityType<? extends Monster> entityType, Level level) {
        lookedTicks = 0;
        super(entityType, level);

        ticksBeforeGoalUpdate = -1;

        setDropChance(EquipmentSlot.MAINHAND, 0.0f);
    }

    @Override
    protected void addAdditionalSaveData(@NonNull ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putInt("looked_ticks", lookedTicks);
    }

    @Override
    protected void readAdditionalSaveData(@NonNull ValueInput input) {
        super.readAdditionalSaveData(input);
        lookedTicks = input.getIntOr("looked_ticks", 0);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        if (goalState == GoalState.TRADING) {
            registerGoals(getTradeGoals(), getEmptyTargets());
        }
    }

    protected Map<Integer, Goal> getTradeGoals() {
        return Map.of(1, new LookAtItemGoal(this, 100),
                2, new GiveTradeItemGoal(this),
                3, new MoveTowardsTradeItemGoal(this, 2.0f),
                4, new LookAtPlayerGoal(this, Player.class, 5.0f, 1.0f));
    }

    @Override
    protected Map<Integer, Goal> getNeutralGoals() {
        HashMap<Integer, Goal> goals = new HashMap<>(super.getNeutralGoals());

        goals.put(15, new MoveTowardsTradeItemGoal(this, 10.0f));
        return goals;
    }

    @Override
    protected Map<Integer, Goal> getAggressiveGoals() {
        HashMap<Integer, Goal> goals = new HashMap<>(super.getAggressiveGoals());

        goals.put(9, new MoveTowardsTradeItemGoal(this, 3.0f));
        return goals;
    }

    @Override
    public GoalState getGoalState() {
        return isHoldingItem() ? GoalState.TRADING : super.getGoalState();
    }

    @Override
    protected void pickUpItem(@NonNull ServerLevel level, ItemEntity onGroundEntity) {
        ItemStack onGround = onGroundEntity.getItem();
        ItemStack picked = equipItemIfPossible(level, onGround.copyWithCount(1));

        // Return if unable to pick up item
        if (picked.isEmpty()) return;

        // Create item entity for picked item and pick it up
        ItemEntity pickedEntity = cloneWithCount(onGroundEntity, 1);
        onItemPickup(pickedEntity);

        // Remove 1 item from ground
        take(onGroundEntity, 1);
        onGround.shrink(1);
        // Remove item entity if the picked item was the only one
        if (onGround.isEmpty()) {
            onGroundEntity.discard();
        }
    }

    public static ItemEntity cloneWithCount(ItemEntity itemEntity, int count) {
        Vec3 movement = itemEntity.getDeltaMovement();
        ItemEntity entity = new ItemEntity(itemEntity.level(), itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), itemEntity.getItem().copyWithCount(count), movement.x, movement.y, movement.z);
        if (itemEntity.getOwner() != null) entity.setThrower(itemEntity.getOwner());
        return entity;
    }

    @Override
    public void onItemPickup(@NonNull ItemEntity itemEntity) {
        super.onItemPickup(itemEntity);
        startTrade();
    }

    public void startTrade() {
        lookedTicks = 0;
        updateGoals();
    }

    public boolean isHoldingItem() {
        return getItemBySlot(EquipmentSlot.MAINHAND) != ItemStack.EMPTY;
    }

    @Override
    protected boolean canReplaceCurrentItem(@NonNull ItemStack newItem, @NonNull ItemStack old, EquipmentSlot equipmentSlot) {
        // Make picked up armor pieces get put to mainhand slot instead of armor slots
        if (equipmentSlot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR) return false;

        return super.canReplaceCurrentItem(newItem, old, equipmentSlot);
    }

    @Override
    public boolean canPickUpLoot() {
        if (level() instanceof ServerLevel serverLevel && !serverLevel.getGameRules().get(GameRules.MOB_GRIEFING))
            return false;

        return !isHoldingItem();
    }

    @Override
    protected @NonNull Vec3i getPickupReach() {
        return new Vec3i(1, 0, 1);
    }

    @Override
    public boolean canHoldItem(@NonNull ItemStack itemStack) {
        return canPickUpLoot() && itemStack.is(ModTags.TRADER_SENTRY_LIKES);
    }

    @Override
    public @NonNull InteractionResult mobInteract(@NonNull Player player, @NonNull InteractionHand hand) {
        ItemStack item = player.getItemInHand(hand);
        if (!canHoldItem(item)) return InteractionResult.PASS;

        setItemInHand(InteractionHand.MAIN_HAND, item.copyWithCount(1));
        startTrade();

        if (!player.isCreative() && !player.isSpectator())
            item.shrink(1);

        return InteractionResult.SUCCESS;
    }

    @Override
    public void tick() {
        super.tick();

        if (ticksBeforeGoalUpdate >= 0) {
            if (ticksBeforeGoalUpdate == 0) updateGoals();
            ticksBeforeGoalUpdate--;
        }
    }

    public void finishTrade() {
        Player player = throwItems(getTradeItems());
        decreaseAnger(2, false);

        if (player instanceof ServerPlayer serverPlayer) {
            ModCriterionTriggers.TRADE_WITH_SENTRY.trigger(serverPlayer);
        }

        setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
        ticksBeforeGoalUpdate = 20;
    }

    public List<ItemStack> getTradeItems() {
        MinecraftServer server = level().getServer();
        if (server == null) return Collections.emptyList();

        LootTable lootTable = server.reloadableRegistries().getLootTable(ModLootTables.TRADER_SENTRY_TRADES);
        return lootTable.getRandomItems(new LootParams.Builder((ServerLevel) level()).withParameter(LootContextParams.THIS_ENTITY, this).create(LootContextParamSets.PIGLIN_BARTER));
    }

    /**
     * Throws the given items to nearest player if distance to the player is at most 5,
     * otherwise in a random direction.
     *
     * @param items ItemStacks to drop
     * @return Player who items where thrown at, or null if no player was close enough
     */
    public @Nullable Player throwItems(List<ItemStack> items) {
        if (items.isEmpty()) return null;

        Player player = level().getNearestPlayer(this, 5.0d);
        Vec3 throwPos = getThrowPos(player).add(0.0d, 1.0d, 0.0d);
        swing(InteractionHand.MAIN_HAND);
        lookAt(EntityAnchorArgument.Anchor.FEET, throwPos);

        for (ItemStack item : items) {
            BehaviorUtils.throwItem(this, item, throwPos);
        }

        return player;
    }

    public Vec3 getThrowPos(@Nullable Player player) {
        if (player != null) return player.position();

        Vec3 randomPos = LandRandomPos.getPos(this, 5, 2);
        if (randomPos == null) randomPos = position();

        return randomPos.add(getRandomVariance());
    }

    public Vec3 getRandomVariance() {
        return new Vec3(random.nextDouble() * 2.0d - 1.0d, 0.0d, random.nextDouble() * 2.0d - 1.0d);
    }

    public void cancelTrade(boolean updateGoals) {
        setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
        if (updateGoals) updateGoals();
    }

    @Override
    public void hurtBySurvivalPlayer() {
        // super calls updateGoals(), so no need to call it a second time when hand is emptied before that
        cancelTrade(false);

        super.hurtBySurvivalPlayer();
    }
}
