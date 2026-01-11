package com.adex.entity.projectile.projectile;

import com.adex.entity.ModEntities;
import com.adex.entity.golem.Golem;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.hurtingprojectile.Fireball;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

public class GolemFireball extends Fireball {

    private final float damage;
    private final int burningTime;
    private final float explosionPower;

    public GolemFireball(EntityType<? extends Fireball> entityType, Level level) {
        super(entityType, level);
        damage = 1.0f;
        burningTime = 20;
        explosionPower = 1.0f;
    }

    public GolemFireball(Golem golem, Vec3 direction, Level level, float damage, int burningTime, float explosionPower, double yDifference) {
        super(ModEntities.GOLEM_FIREBALL_ENTITY, golem.getX(), golem.getY() + yDifference, golem.getZ(), direction, level);
        this.damage = damage;
        this.burningTime = burningTime;
        this.explosionPower = explosionPower;
    }

    @Override
    protected void onHitEntity(@NonNull EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        if (level() instanceof ServerLevel serverLevel) {
            Entity victim = entityHitResult.getEntity();
            DamageSource damageSource = damageSources().fireball(this, getOwner());
            victim.hurtServer(serverLevel, damageSource, damage);
            EnchantmentHelper.doPostAttackEffects(serverLevel, victim, damageSource);
            victim.igniteForTicks(burningTime);
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
