package com.adex.mixin;

import com.google.common.collect.ImmutableMap;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.sensing.VillagerHostilesSensor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(VillagerHostilesSensor.class)
public interface VillagerHostilesSensorAccessor {

    @Mutable
    @Accessor("ACCEPTABLE_DISTANCE_FROM_HOSTILES")
    static ImmutableMap<EntityType<?>, Float> coread$getMap() {
        throw new AssertionError();
    }

    @Mutable
    @Accessor("ACCEPTABLE_DISTANCE_FROM_HOSTILES")
    static void coread$setMap(ImmutableMap<EntityType<?>, Float> map) {
        throw new AssertionError();
    }

}
