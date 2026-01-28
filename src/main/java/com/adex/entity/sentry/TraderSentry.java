package com.adex.entity.sentry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class TraderSentry extends Sentry{

    public TraderSentry(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }
}
