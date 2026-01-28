package com.adex.entity.sentry;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class WarriorSentry extends Sentry {

    public WarriorSentry(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public ItemStack createMainHandItem(RandomSource random, float difficultyMultiplier) {
        float f = random.nextFloat() * 2.0f;
        if (f < difficultyMultiplier) return new ItemStack(Items.DIAMOND_AXE);
        return new ItemStack(Items.IRON_AXE);
    }
}
