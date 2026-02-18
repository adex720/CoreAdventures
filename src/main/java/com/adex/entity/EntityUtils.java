package com.adex.entity;

import com.adex.mixin.VillagerHostilesSensorAccessor;
import com.google.common.collect.ImmutableMap;
import net.minecraft.world.entity.EntityType;

public class EntityUtils {

    public static void makeVillagerScaredOf(EntityType<?> entityType, float minDistance) {
        ImmutableMap<EntityType<?>, Float> oldMap = VillagerHostilesSensorAccessor.coread$getMap();
        ImmutableMap<EntityType<?>, Float> newMap = ImmutableMap.<EntityType<?>, Float>builder()
                .putAll(oldMap).put(entityType, minDistance).build();
        VillagerHostilesSensorAccessor.coread$setMap(newMap);
    }
}
