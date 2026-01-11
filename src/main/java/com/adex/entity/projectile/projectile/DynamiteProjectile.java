package com.adex.entity.projectile.projectile;

import com.adex.entity.ModEntities;
import com.adex.entity.golem.Golem;
import com.adex.item.ModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.hurtingprojectile.Fireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

public class DynamiteProjectile extends Fireball {

    private final float damage;
    private final float explosionPower;

    public DynamiteProjectile(EntityType<? extends Fireball> entityType, Level level) {
        super(entityType, level);
        damage = 1.0f;
        explosionPower = 1.0f;
    }

    public DynamiteProjectile(Golem golem, Vec3 direction, Level level, float damage, float explosionPower, double yDifference) {
        super(ModEntities.DYNAMITE_PROJECTILE, golem.getX(), golem.getY() + yDifference, golem.getZ(), direction, level);
        this.damage = damage;
        this.explosionPower = explosionPower;
    }

    @Override
    public @NonNull ItemStack getItem() {
        return new ItemStack(ModItems.DYNAMITE);
    }

    @Override
    protected boolean shouldBurn() {
        return false;
    }

    @Override
    protected void onHitEntity(@NonNull EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        if (level() instanceof ServerLevel serverLevel) {
            Entity victim = entityHitResult.getEntity();
            level().explode(this, victim.getX(), victim.getY(0.5d), victim.getZ(), explosionPower, serverLevel.getGameRules().get(GameRules.MOB_GRIEFING), Level.ExplosionInteraction.MOB);
            DamageSource damageSource = damageSources().explosion(this, getOwner());
            victim.hurtServer(serverLevel, damageSource, damage);
        }
    }

    @Override
    protected void onHitBlock(@NonNull BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);
        if (level() instanceof ServerLevel serverLevel) {
            level().explode(this, getX(), getY(), getZ(), explosionPower, serverLevel.getGameRules().get(GameRules.MOB_GRIEFING), Level.ExplosionInteraction.MOB);
            discard();
        }
    }
}
