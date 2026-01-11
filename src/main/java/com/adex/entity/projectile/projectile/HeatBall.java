package com.adex.entity.projectile.projectile;

import com.adex.data.damagetype.ModDamageTypes;
import com.adex.entity.ModEntities;
import com.adex.entity.attribute.HeatManager;
import com.adex.entity.golem.Golem;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.hurtingprojectile.Fireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

public class HeatBall extends Fireball {

    private final float damage;
    private final float heatIncrease;

    public HeatBall(EntityType<? extends Fireball> entityType, Level level) {
        super(entityType, level);
        damage = 1.0f;
        heatIncrease = 1.0f;
    }

    public HeatBall(Golem golem, Vec3 direction, Level level, float damage, float heatIncrease, double yDifference) {
        super(ModEntities.HEAT_BALL_ENTITY, golem.getX(), golem.getY() + yDifference, golem.getZ(), direction, level);
        this.damage = damage;
        this.heatIncrease = heatIncrease;
    }

    @Override
    public @NonNull ItemStack getItem() {
        return new ItemStack(Items.CLAY_BALL);
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
            DamageSource damageSource = damageSources().source(ModDamageTypes.HEAT_DAMAGE, this, getOwner());
            victim.hurtServer(serverLevel, damageSource, damage);

            if (victim instanceof ServerPlayer serverPlayer) {
                HeatManager.heat(serverPlayer, heatIncrease);
            }
        }
    }

    @Override
    protected void onHitBlock(@NonNull BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);
        if (!level().isClientSide()) discard();
    }
}
