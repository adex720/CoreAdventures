package com.adex.entity;

import com.adex.mixin.VillagerHostilesSensorAccessor;
import com.google.common.collect.ImmutableMap;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.npc.wanderingtrader.WanderingTrader;

import java.util.HashMap;
import java.util.Map;

public class EntityUtils {

    private static final HashMap<Class<? extends LivingEntity>, Float> WANDERING_TRADER_AVOIDING = new HashMap<>();

    /**
     * Makes villagers avoid entities of the {@code entityType}.
     * Should be called once for each entity when initializing.
     *
     * @param entityType  EntityType to avoid
     * @param minDistance How far from an entity of this type the wandering trader should get away
     */
    public static void makeVillagerScaredOf(EntityType<?> entityType, float minDistance) {
        ImmutableMap<EntityType<?>, Float> oldMap = VillagerHostilesSensorAccessor.coread$getMap();
        ImmutableMap<EntityType<?>, Float> newMap = ImmutableMap.<EntityType<?>, Float>builder()
                .putAll(oldMap).put(entityType, minDistance).build();
        VillagerHostilesSensorAccessor.coread$setMap(newMap);
    }

    /**
     * Gets called from {@link com.adex.mixin.WanderingTraderMixin} to actually add the {@link AvoidEntityGoal}s
     * for each created entity.
     *
     * @param wanderingTrader Wandering trader entity to add goals for.
     * @param goalSelector    GoalSelector of the wandering trader.
     */
    public static void addWanderingTraderGoals(WanderingTrader wanderingTrader, GoalSelector goalSelector) {
        for (Map.Entry<Class<? extends LivingEntity>, Float> entry : WANDERING_TRADER_AVOIDING.entrySet()) {
            goalSelector.addGoal(1, new AvoidEntityGoal<>(wanderingTrader, entry.getKey(), entry.getValue(), 0.5d, 0.5f));
        }
    }

    /**
     * Adds a {@link AvoidEntityGoal} avoiding entities of a specific type.
     * Should be called once for each entity when initializing.
     *
     * @param type     Class of the entity.
     *                 If other classes extend this class, entities of those classes will also be avoided
     * @param distance How far from an entity of this type the wandering trader should get away
     */
    public static void makeWanderingTraderScaredOf(Class<? extends LivingEntity> type, float distance) {
        WANDERING_TRADER_AVOIDING.put(type, distance);
    }
}
