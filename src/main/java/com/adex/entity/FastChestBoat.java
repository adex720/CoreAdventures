package com.adex.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import java.util.function.Supplier;

public class FastChestBoat extends ChestBoat {

    public final float accelerationMultiplier;

    public FastChestBoat(EntityType<? extends ChestBoat> entityType, Level level, Supplier<Item> supplier, float accelerationMultiplier) {
        super(entityType, level, supplier);
        this.accelerationMultiplier = accelerationMultiplier;
    }

}
