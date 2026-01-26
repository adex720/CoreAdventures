package com.adex.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import java.util.function.Supplier;

public class FastBoat extends Boat {

    public final float accelerationMultiplier;

    public FastBoat(EntityType<? extends Boat> entityType, Level level, Supplier<Item> supplier, float accelerationMultiplier) {
        super(entityType, level, supplier);
        this.accelerationMultiplier = accelerationMultiplier;
    }

}
